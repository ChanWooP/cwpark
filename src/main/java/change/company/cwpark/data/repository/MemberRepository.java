package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByAccount(String account);

}
