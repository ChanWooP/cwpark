package change.company.cwpark.data.repository;

import change.company.cwpark.data.emb.Address;
import change.company.cwpark.data.emb.Biz;
import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.Item;
import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.entity.PlusItem;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.Store;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SaleRepositoryTest {

  @Autowired
  SaleRepository saleRepository;

  @Test
  @DisplayName("매출 조회")
  @Transactional
  void saveItemTot() {
    // given
    Store store = new Store(8L);
    List<Sale> list = saleRepository.findByStoreAndSaleDateGreaterThanEqualAndSaleDateLessThanEqualOrderByIdAsc(store, "20230626", "20230627");

    // then
    Assertions.assertThat(list.size()).isEqualTo(5);
  }
}
