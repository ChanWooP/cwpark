package change.company.cwpark.config.security;

import change.company.cwpark.data.dto.MemberDto;
import change.company.cwpark.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final MemberService memberService;
    
    @Value("${server.servlet.session.timeout}")
    private Integer sessionTimeOut;

    @Autowired
    public SuccessHandler(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        String username = userDetails.getUsername();
        MemberDto memberDto = memberService.getMember(username);

        // 기본 url 지정
        setDefaultTargetUrl("/index");

        // 세션 타임아웃 지정
        request.getSession().setMaxInactiveInterval(sessionTimeOut);

        // 캐시를 가져와서 비어있는지 확인
        if(savedRequest != null) {
            // 인증 받기 전 url로 이동하기
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request,response,targetUrl);
        } else {
            if(memberDto.getLoginFailCnt() >= 5) {
                // 비밀번호 5회 연속 틀릴 시 계정 잠금 처리
                throw new LockedException("계정이 잠겼습니다.");
            } else {
                // 로그인 성공 시 최종로그인날짜 수정 및 비밀번호 실패횟수 초기화
                memberService.loginSuccessMember(memberDto);
            }
            // 기본 url로 가도록 함
            redirectStrategy.sendRedirect(request,response,getDefaultTargetUrl());
        }
    }
}
