package change.company.cwpark.data.entity;

import lombok.*;
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
CREATE TABLE MEMBER (
	  ID INT NOT NULL AUTO_INCREMENT
	, NAME VARCHAR(255) NOT NULL
	, ACCOUNT VARCHAR(255) NOT NULL UNIQUE
	, PASSWORD VARCHAR(255) NOT NULL
	, AUTHORITY INT
	, LOGIN_FAIL_CNT INT
	, LAST_ACCESS_DT  DATETIME
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY (id)
);
 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    // 테스트를 위한 생성자
    public Member(String name, String account, String password, LocalDateTime lastAccessDt, Integer loginFailCnt) {
        this.name = name;
        this.account = account;
        this.password = password;
        this.lastAccessDt = lastAccessDt;
        this.loginFailCnt = loginFailCnt;
    }
}
