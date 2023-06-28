package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.SaleItem;
import change.company.cwpark.data.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
  List<SaleItem> findByStoreAndSaleDateGreaterThanEqualAndSaleDateLessThanEqualOrderBySaleAsc(Store store, String frDt, String toDt);
}
