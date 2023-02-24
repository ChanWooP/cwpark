package change.company.cwpark.config.security;

import change.company.cwpark.data.dto.MemberDto;
import change.company.cwpark.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class FailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final MemberService memberService;

    @Autowired
    public FailureHandler(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String msg = "";
        String username = request.getParameter("username");
        MemberDto memberDto = new MemberDto();

        if(exception instanceof BadCredentialsException){ // 인증 오류
            msg = "아이디 혹은 비밀번호가 맞지 않습니다.";
            memberDto.setAccount(username);
            memberService.loginFailureMember(memberDto);
        } else if (exception instanceof InternalAuthenticationServiceException) { // 시스템 오류
            msg = "아이디가 존재하지 않습니다.";
        } else if(exception instanceof LockedException) { // 잠금 오류(로그인 시 실패횟수 5회 이상인 경우 발생)
            msg = exception.getMessage();
        }

        // 기본 URL 지정
        setDefaultFailureUrl("/login?error=true&exception=" + URLEncoder.encode(msg,"UTF-8"));

        super.onAuthenticationFailure(request,response,exception);
    }
}
