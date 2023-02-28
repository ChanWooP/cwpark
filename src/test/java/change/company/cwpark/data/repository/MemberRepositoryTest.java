package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void generateDate() {
        memberRepository.save(new Member("123", "123", "123", LocalDateTime.now(), 0));
    }

    @Test
    @DisplayName("계정 조회 테스트")
    @Transactional
    void findByAccountTest() {
        // given
        Member member = new Member();
        member.setAccount("123");

        // when
        Optional<Member> memberWrapper = memberRepository.findByAccount("123");
        Member member1 = memberWrapper.orElse(null);

        // then
        Assertions.assertThat(member.getAccount()).isEqualTo(member1.getAccount());
    }

}
