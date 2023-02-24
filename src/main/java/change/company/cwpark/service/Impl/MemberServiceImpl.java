package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.MemberDao;
import change.company.cwpark.data.dto.MemberDto;
import change.company.cwpark.data.entity.Member;
import change.company.cwpark.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    // 사용자 인증
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberDao.getMember(username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        User user = null;

        // 관리자 권한으로 로그인 하고 싶으면 ROLE_MEMBER -> ROLE_ADMIN 으로 변경
        authorityList.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
        user = new User(member.getAccount(), member.getPassword(), authorityList);

        return user;
    }

    // 사용자 정보
    @Override
    public MemberDto getMember(String Account) {
        Member member = memberDao.getMember(Account);
        MemberDto memberDto = new MemberDto(member.getName(), member.getAccount(), member.getPassword(), member.getLastAccessDt(), member.getLoginFailCnt());

        return memberDto;
    }

    // 사용자 회원가입
    @Override
    public MemberDto saveMember(MemberDto memberDto) {
        Member member = new Member();
        MemberDto memberDtoRtn = null;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        member.setName(memberDto.getName());
        member.setAccount(memberDto.getAccount());
        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        member.setLastAccessDt(LocalDateTime.now());
        member.setLoginFailCnt(0);
        member = memberDao.saveMember(member);

        memberDtoRtn = new MemberDto(member.getName(), member.getAccount(), member.getPassword(), member.getLastAccessDt(), member.getLoginFailCnt());

        return memberDtoRtn;
    }

    // 사용자 로그인 성공
    @Override
    @Transactional
    public MemberDto loginSuccessMember(MemberDto memberDto) {
        Member member = memberDao.getMember(memberDto.getAccount());
        MemberDto memberDtoRtn = null;

        member.setLastAccessDt(LocalDateTime.now());
        member.setLoginFailCnt(0);

        memberDtoRtn = new MemberDto(member.getName(), member.getAccount(), member.getPassword(), member.getLastAccessDt(), member.getLoginFailCnt());

        return memberDtoRtn;
    }

    // 사용자 로그인 실패
    @Override
    @Transactional
    public MemberDto loginFailureMember(MemberDto memberDto) {
        Member member = memberDao.getMember(memberDto.getAccount());
        MemberDto memberDtoRtn = null;

        if(member.getLoginFailCnt() < 5) {
            member.setLoginFailCnt(member.getLoginFailCnt() + 1);
        }

        memberDtoRtn = new MemberDto(member.getName(), member.getAccount(), member.getPassword(), member.getLastAccessDt(), member.getLoginFailCnt());

        return memberDtoRtn;
    }

}
