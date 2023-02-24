package change.company.cwpark.config.security;

import change.company.cwpark.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 해당 클래스를 Configuration으로 등록합니다.
@Configuration
// Spring Security를 활성화 시킵니다.
@EnableWebSecurity
// Controller에서 특정 페이지에 특정 권한이 있는 유저만 접근을 허용할 경우 @PreAuthorize 어노테이션을 사용하는데
// , 해당 어노테이션에 대한 설정을 활성화시키는 어노테이션입니다. (필수는 아닙니다.)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    // 사용할 서비스
    private final MemberService memberService;
    // 성공시 핸들러
    private final SuccessHandler successHandler;
    // 실패시 핸들러
    private final FailureHandler failureHandler;

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Autowired
    public SecurityConfig(MemberService memberService, SuccessHandler successHandler, FailureHandler failureHandler) {
        this.memberService = memberService;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    // 비밀번호를 복호화/암호화하는 로직이 담긴 객체를 Bean으로 등록합니다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 해당 자원들에 대해서는 시큐리티 설정을 적용하지 않음
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // HttpServletRequest 요청 URL에 따라 접근 권한을 설정합니다.
        http.authorizeRequests()
                .antMatchers("/member/**").authenticated() // /member/**  경로는 인증된 사용자만 요청이 가능합니다.
                .antMatchers("/admin/**").authenticated() // /admin/**  경로는 인증된 사용자만 요청이 가능합니다.
                .antMatchers("/**").permitAll(); // http 요청에 대해서 모든 사용자가 /** 경로로 요청할 수 있지만

        // form Login 설정을 진행합니다.
        http.formLogin()
                .loginPage("/login") // 커스텀 로그인 페이지 경로와 로그인 인증 경로를 등록합니다.
                .defaultSuccessUrl("/index") // 로그인 인증을 성공하면 이동하는 페이지를 등록합니다.
                .successHandler(successHandler) // 성공했을 시 핸들러 지정
                .failureHandler(failureHandler) // 실패했을 시 핸들러 지정
                .permitAll();

        // 로그아웃 설정을 진행합니다.
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 경로를 지정합니다.
                .logoutSuccessUrl("/login") // 로그아웃 성공 시 이동할 경로를 지정합니다.
                .invalidateHttpSession(true); // 로그아웃 성공 시 세션을 제거합니다.

        // 권한이 없는 사용자가 접근했을 경우 이동할 경로를 지정합니다.
        http.exceptionHandling()
                .accessDeniedPage("/");
        
        http.sessionManagement()
                .maximumSessions(1) // 세션 최대 허용 수
                .maxSessionsPreventsLogin(false) // false이면 중복 로그인 시 이전 로그인이 풀림
                .expiredUrl("/")
                .sessionRegistry(sessionRegistry());
        
    }

    // AuthenticationManager는 사용자 인증을 담당합니다.
    // auth.userDetailsService(service)에 org.springframework.security.core.userdetails.UserDetailsService 인터페이스를
    // 구현한 Service를 넘겨야합니다.
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }


}
