package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Review;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.Store;
import java.util.List;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class ReviewRepositoryTest {

  @Autowired
  ReviewRepository repository;

  @Test
  @DisplayName("페이징 조회")
  @Transactional
  void viewPaging() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "sale_id"));
    Store store = new Store(8L);

    Page<Review> page = repository.findByStoreAndSaleDateGreaterThanEqualAndSaleDateLessThanEqual(store, "20230601", "20230701", pageable);

    System.out.println(page.getSize());
    System.out.println(page.getTotalPages());
    System.out.println(page.nextPageable());

    Assertions.assertThat(page.getSize()).isEqualTo(10);
    Assertions.assertThat(page.getTotalPages()).isEqualTo(2);
  }
}
