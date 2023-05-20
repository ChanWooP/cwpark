package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.entity.Menu;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.reinterface.MenuInterface;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreRepository extends JpaRepository<Store, Long> {
  List<Store> findByStoreNameContaining(String storeName);
}
