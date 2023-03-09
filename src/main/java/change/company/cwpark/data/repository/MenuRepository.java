package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByDepth(Integer depth);

    @Query(value = "SELECT A.ID, A.NAME, B.NAME AS P_NAME, A.DEPTH " +
            "          FROM menu A LEFT OUTER JOIN menu B ON A.PARENT_NUM = B.ID", nativeQuery = true)
    List<Menu> findAllMenu();
}
