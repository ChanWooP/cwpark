package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByDepth(Integer depth);
}
