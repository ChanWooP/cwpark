package change.company.cwpark.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate  // 변경된 필드만 적용
@DynamicInsert  // 같음
@Table(name = "member")
public class Member extends Base{
/*
CREATE TABLE member (
	id INT NOT NULL AUTO_INCREMENT
	, name VARCHAR(255) NOT NULL
	, account VARCHAR(255) NOT NULL UNIQUE
	, password VARCHAR(255) NOT NULL
	, last_access_dt DATETIME
	, reg_date DATETIME
	, reg_id VARCHAR(255)
	, upd_date DATETIME
	, upd_id VARCHAR(255)
	, login_fail_cnt INT
	, PRIMARY KEY (id)
);
 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false, unique = true)
    private String account;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(name = "last_access_dt")
    private LocalDateTime lastAccessDt;

    @Column(name = "login_fail_cnt")
    private Integer loginFailCnt;

}
