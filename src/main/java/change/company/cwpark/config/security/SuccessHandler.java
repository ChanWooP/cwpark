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

        // ?????? url ??????
        setDefaultTargetUrl("/index");

        // ?????? ???????????? ??????
        request.getSession().setMaxInactiveInterval(sessionTimeOut);

        // ????????? ???????????? ??????????????? ??????
        if(savedRequest != null) {
            // ?????? ?????? ??? url??? ????????????
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request,response,targetUrl);
        } else {
            if(memberDto.getLoginFailCnt() >= 5) {
                // ???????????? 5??? ?????? ?????? ??? ?????? ?????? ??????
                throw new LockedException("????????? ???????????????.");
            } else {
                // ????????? ?????? ??? ????????????????????? ?????? ??? ???????????? ???????????? ?????????
                memberService.loginSuccessMember(memberDto);
            }
            // ?????? url??? ????????? ???
            redirectStrategy.sendRedirect(request,response,getDefaultTargetUrl());
        }
    }
}
