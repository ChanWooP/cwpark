package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findByStoreId(Store storeId);
}
