package change.company.cwpark.data.dao;

import change.company.cwpark.data.entity.Member;

import java.util.Optional;

public interface MemberDao {
    Member getMember(String account);
    Member saveMember(Member member);
}
