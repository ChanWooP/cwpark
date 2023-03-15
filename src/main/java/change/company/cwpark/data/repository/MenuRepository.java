package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.LinkedList;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByDepth(Integer depth);

    @Query(value = "SELECT A.id                                               "
                 + "	 , A.parent_num                                       "
                 + "	 , A.depth                                            "
                 + "	 , case A.depth when 1 then CONCAT(' └ ', A.name)     "
                 + "	         		when 2 then CONCAT('   └ ', A.name)   "
                 + "	         		ELSE A.name END AS name               "
                 + "  FROM menu A                                             "
                 + "   LEFT OUTER JOIN menu B                                 "
                 + "     ON A.parent_num = B.id                               "
                 + "  ORDER BY case A.depth when 1 then A.id                  "
                 + "	  					when 2 then A.parent_num          "
                 + "	  					ELSE A.id END                     "
                 + "		 , A.id                                           "
            , nativeQuery = true)
    LinkedList<Menu> findAllMenu();
}
