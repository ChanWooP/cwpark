package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.MemberDao;
import change.company.cwpark.data.dao.MenuDao;
import change.company.cwpark.data.dao.StoreDao;
import change.company.cwpark.data.dto.MemberDto;
import change.company.cwpark.data.dto.MenuDto;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.entity.Menu;
import change.company.cwpark.service.MemberService;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final MenuDao menuDao;
    private final StoreDao storeDao;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao, MenuDao menuDao, StoreDao storeDao) {
        this.memberDao = memberDao;
        this.menuDao = menuDao;
        this.storeDao = storeDao;
    }

    // 사용자 인증
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberDao.getMember(username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        User user = null;

        if(member.getAccount().equals("ADMIN")) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
            authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorityList.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
        }

        user = new User(member.getAccount(), member.getPassword(), authorityList);

        return user;
    }

    // 사용자 정보
    @Override
    public MemberDto getMember(String Account) {
        Member member = memberDao.getMember(Account);
        MemberDto memberDto = null;

        if(member != null) {
            memberDto = new MemberDto(member.getName(), member.getAccount(), member.getPassword(), member.getLastAccessDt(), member.getLoginFailCnt());
        }
        return memberDto;
    }

    // 사용자 회원가입
    @Override
    public MemberDto saveMember(MemberDto memberDto) {
        Member member = memberDao.getMember(memberDto.getAccount());
        MemberDto memberDtoRtn = null;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 아이디 중복 체크
        if(member == null) {
            member = new Member();
            member.setName(memberDto.getName());
            member.setAccount(memberDto.getAccount());
            member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
            member.setLastAccessDt(LocalDateTime.now());
            member.setLoginFailCnt(0);
            member = memberDao.saveMember(member);

            if(member != null) {
                memberDtoRtn = new MemberDto(member.getName(), member.getAccount(), member.getPassword(), member.getLastAccessDt(), member.getLoginFailCnt());
            }
        }

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

    @Override
    public Map<MenuDto, List<MenuDto>> getMenu() {
        List<Menu> menu = menuDao.getAllMenu();
        Map<MenuDto, List<MenuDto>> map = new LinkedHashMap<>();
        List<MenuDto> list = null;

        for(Menu m : menu) {
            if(m.getDepth() == 1) {
                list = new LinkedList<>();
                map.put(new MenuDto(m.getId(), m.getParentNum(), m.getDepth(), m.getName(), m.getPath(), m.getMenuName(), m.getChildCnt(), ""), list);
            } else if(m.getDepth() == 2) {
                list.add(new MenuDto(m.getId(), m.getParentNum(), m.getDepth(), m.getName(), m.getPath(), m.getMenuName(), m.getChildCnt(), ""));
            }
        }

        return map;
    }

    @Override
    public Long getStore() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        Member account = memberDao.getMember(userDetails.getUsername());
        StoreDto s = null;

        if(iterator.next().getAuthority().equals("ROLE_MEMBER")) {
            return storeDao.getAccount(account).getId();
        } else {
            return 0L;
        }
    }

}
