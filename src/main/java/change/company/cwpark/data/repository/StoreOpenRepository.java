package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.entity.StoreOpen;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreOpenRepository extends JpaRepository<StoreOpen, Long> {
  List<StoreOpen> findByStore(Store store);
}