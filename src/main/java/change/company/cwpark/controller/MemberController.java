package change.company.cwpark.controller;

import change.company.cwpark.data.dto.MemberDto;
import change.company.cwpark.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 기본 페이지(로그인)
    @GetMapping("/")
    public String homeView() {
        return "pages/login";
    }

    // 로그인
    @GetMapping("/login")
    public String loginView() {
        return "pages/login";
    }

    // 회원가입
    @GetMapping("/register")
    public String signupView() {
        return "pages/register";
    }

    // 회원가입 전송
    @PostMapping("/register")
    public String signup(MemberDto memberDto) {
        memberService.saveMember(memberDto);
        return "redirect:/login";
    }

    // 메인 화면
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/index")
    public String indexView() {
        return "pages/index";
    }

}
