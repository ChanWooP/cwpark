package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.Impl.MemberDaoImpl;
import change.company.cwpark.data.dto.MemberDto;
import change.company.cwpark.data.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Mock 객체를 사용하기 위함
public class MemberServiceImplTest {

    @Mock
    MemberDaoImpl memberDao;

    @InjectMocks
    MemberServiceImpl memberService;

    @Test
    @DisplayName("해당 사용자가 없는 경우")
    public void getMemberNullTest() {
        // given
        Mockito.when(memberDao.getMember("123")).thenReturn(null);

        // when
        MemberDto memberDto = memberService.getMember("123");

        // then
        Assertions.assertEquals(memberDto, null);

        verify(memberDao).getMember("123");
    }

    @Test
    @DisplayName("해당 사용자가 있는 경우")
    public void getMemberNotNullTest() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("1234");
        LocalDateTime localDateTime = LocalDateTime.now();

        // given
        Mockito.when(memberDao.getMember("1234"))
                .thenReturn(new Member("1234", "1234", password, localDateTime, 0));

        // when
        MemberDto memberDto = memberService.getMember("1234");

        // then
        Assertions.assertEquals(memberDto.getName(), "1234");
        Assertions.assertEquals(memberDto.getAccount(), "1234");
        Assertions.assertEquals(memberDto.getPassword(), password);
        Assertions.assertEquals(memberDto.getLastAccessDt(), localDateTime);
        Assertions.assertEquals(memberDto.getLoginFailCnt(), 0);

        verify(memberDao).getMember("1234");
    }

    @Test
    @DisplayName("회원가입")
    public void saveMember() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("1234");
        LocalDateTime localDateTime = LocalDateTime.now();
        Member member = new Member("1234", "1234", password, localDateTime, 0);

        // given
        // 어떤 값이 넣던지 내가 정한 값을 가져오도록 지정
        Mockito.when(memberDao.saveMember(any())).thenReturn(member);

        // when
        MemberDto memberDto = memberService.saveMember(new MemberDto(member.getName(), member.getAccount(), password, localDateTime, 0));

        // then
        Assertions.assertEquals(memberDto.getName(), "1234");
        Assertions.assertEquals(memberDto.getAccount(), "1234");
        Assertions.assertEquals(memberDto.getPassword(), password);
        Assertions.assertEquals(memberDto.getLastAccessDt(), localDateTime);
        Assertions.assertEquals(memberDto.getLoginFailCnt(), 0);

        verify(memberDao).saveMember(any());
    }
}
