package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Review;
import change.company.cwpark.data.entity.SalePlusItem;
import change.company.cwpark.data.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  Page<Review> findByStoreAndSaleDateGreaterThanEqualAndSaleDateLessThanEqual(Store store, String frDt, String toDt, Pageable pageable);
}
