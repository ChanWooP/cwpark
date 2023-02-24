package change.company.cwpark.service;

import change.company.cwpark.data.dto.MemberDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;

public interface MemberService extends UserDetailsService {
    MemberDto saveMember(MemberDto memberDto);
    MemberDto getMember(String Account);
    MemberDto loginSuccessMember(MemberDto memberDto);
    MemberDto loginFailureMember(MemberDto memberDto);

}
