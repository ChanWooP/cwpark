package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
  List<Item> findByCategoryId(Long categoryId);
}
