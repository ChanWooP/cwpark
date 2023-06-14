package change.company.cwpark.service;

import change.company.cwpark.data.dto.MemberDto;
import change.company.cwpark.data.dto.MenuDto;
import change.company.cwpark.data.dto.StoreDto;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;

public interface MemberService extends UserDetailsService {
    MemberDto saveMember(MemberDto memberDto);
    MemberDto getMember(String Account);
    MemberDto loginSuccessMember(MemberDto memberDto);
    MemberDto loginFailureMember(MemberDto memberDto);
    Map<MenuDto, List<MenuDto>> getMenu();
    List<StoreDto> getStore();
}
