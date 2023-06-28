package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.SaleItem;
import change.company.cwpark.data.entity.SalePlusItem;
import change.company.cwpark.data.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalePlusItemRepository extends JpaRepository<SalePlusItem, Long> {
  List<SalePlusItem> findByStoreAndSaleDateGreaterThanEqualAndSaleDateLessThanEqualOrderBySaleAsc(Store store, String frDt, String toDt);
}
