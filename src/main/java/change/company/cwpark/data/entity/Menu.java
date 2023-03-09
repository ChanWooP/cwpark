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
CREATE TABLE MENU (
	  ID INT NOT NULL AUTO_INCREMENT
	, NUM INT NOT NULL
	, PARENT_NUM INT
	, DEPTH INT
	, NAME VARCHAR(100)
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY(id)
);
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

    public Menu(Long parentNum, Integer depth, String name) {
        this.parentNum = parentNum;
        this.depth = depth;
        this.name = name;
    }

}
