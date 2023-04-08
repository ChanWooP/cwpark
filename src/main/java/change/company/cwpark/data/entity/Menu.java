package change.company.cwpark.data.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate  // 변경된 필드만 적용
@DynamicInsert  // 같음
@Table(name = "menu")
public class Menu extends Base{
/*
-- 메뉴
CREATE TABLE MENU (
	  ID INT NOT NULL AUTO_INCREMENT
	, PARENT_NUM INT
	, DEPTH INT
	, NAME VARCHAR(100)
	, PATH VARCHAR(100)
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY(id)
) DEFAULT CHARACTER SET UTF8;
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "parent_num")
    private Long parentNum;

    @Column(name = "depth")
    private Integer depth;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Transient
    private String menuName;

    @Transient
    private Integer childCnt;

    public Menu(Long parentNum, Integer depth, String name, String path, String menuName, Integer childCnt) {
        this.parentNum = parentNum;
        this.depth = depth;
        this.name = name;
        this.path = path;
        this.menuName = menuName;
        this.childCnt = childCnt;
    }

}
