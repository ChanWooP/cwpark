package change.company.cwpark.data.repository;

import change.company.cwpark.data.emb.Address;
import change.company.cwpark.data.emb.Biz;
import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.entity.Store;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class StoreRepositoryTest {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("점포 검색")
    @Transactional
    void findByIdLikeOrStoreNameLike() {
        // given
        List<Store> storeList = new ArrayList<>();
        Member member = new Member(null, "test3", "test3", "test", null, 0);
        Biz biz = new Biz("123456", "test");
        Address address = new Address("서울시", "송파구", "1234");

        member = memberRepository.save(member);
        storeList.add(new Store(null, member, "test1", "01011112222", "1234", 0, "", biz , address));
        storeRepository.saveAll(storeList);

        // when
        List<Store> storeList2 = storeRepository.findByStoreNameContaining("");
        //List<Store> storeList2 = storeRepository.findAll();

        // then
        Assertions.assertThat("test3").isEqualTo(storeList2.get(0).getAccount().getAccount());
    }

    @Test
    @DisplayName("주소로 점포 검색")
    void findByAddressContaining() {
        List<Store> storeList = storeRepository.findByAddressAddress1Containing("경기");

        Assertions.assertThat(3).isEqualTo(storeList.size());
    }

}
