package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Menu;
import change.company.cwpark.data.reinterface.MenuInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.LinkedList;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByDepth(Integer depth);

    @Query(value = "SELECT A.id /* 공백은 스페이스바 아니고 문자사용 'ㅤ' */ "
                 + "	   , A.parent_num AS parentNum "
                 + "	   , A.depth"
                 + "	   , case A.depth when 1 then CONCAT('ㅤ└ ', A.name) "
                 + "	         		      when 2 then CONCAT('ㅤㅤ└ ', A.name) "
                 + "	         	       	ELSE A.name END AS menuName "
                 + "	   , A.name "
                 + "	   , A.path "
                 + "  FROM menu A "
                 + "   LEFT OUTER JOIN menu B "
                 + "     ON A.parent_num = B.id "
                 + "  ORDER BY case A.depth when 1 then A.id "
                 + "	  					when 2 then A.parent_num "
                 + "	  					ELSE A.id END "
                 + "		     , A.id "
            , nativeQuery = true)
    LinkedList<MenuInterface> findAllMenu();
}
