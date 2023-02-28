package change.company.cwpark.data.dao.Impl;

import change.company.cwpark.data.dao.MemberDao;
import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.repository.MemberRepository;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class MemberDaoImpl implements MemberDao {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberDaoImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    // 사용자 정보 조회
    @Override
    public Member getMember(String account) {
        Optional<Member> memberWrapper = memberRepository.findByAccount(account);
        Member member = memberWrapper.orElse(null);

        return member;
    }

    // 사용자 정보 저장
    @Override
    public Member saveMember(Member member) {
        Member memberSave = null;

        if(member.getId() == null) {
            memberSave = memberRepository.save(member);
        } else {
            memberSave = member;
        }

        return memberSave;
    }

}
