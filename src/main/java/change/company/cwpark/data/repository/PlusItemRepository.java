package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Item;
import change.company.cwpark.data.entity.PlusItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlusItemRepository extends JpaRepository<PlusItem, Long> {
  List<PlusItem> findByItemId(Item itemId);
}
