package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
  List<Sale> findByStoreAndSaleDateGreaterThanEqualAndSaleDateLessThanEqualOrderByIdAsc(Store store, String frDt, String toDt);
}
