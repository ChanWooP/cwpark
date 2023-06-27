package change.company.cwpark.data.entity;

import change.company.cwpark.data.emb.Address;
import change.company.cwpark.data.emb.Biz;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate  // 변경된 필드만 적용
@DynamicInsert  // 같음
@Table(name = "store")
public class Store extends Base{
  /*
-- 점포
CREATE TABLE Store (
	  ID INT NOT NULL AUTO_INCREMENT
	, ACCOUNT INT NOT NULL UNIQUE
	, STORE_NAME VARCHAR(255) NOT NULL
	, BIZ_NO VARCHAR(255) NOT NULL
	, BIZ_NAME VARCHAR(255) NOT NULL
	, ADDRESS1 VARCHAR(255)
	, ADDRESS2 VARCHAR(255)
	, TEL VARCHAR(255)
	, OPER_TIME VARCHAR(4)
	, LIKE_CNT INT
	, ETC VARCHAR(255)
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY (id)
	, FOREIGN KEY (ACCOUNT) REFERENCES MEMBER (ID)
) DEFAULT CHARACTER SET UTF8;
  */

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="account")
  private Member account;

  @Column(name = "store_name")
  private String storeName;

  @Column(name = "TEL")
  private String tel;

  @Column(name = "OPER_TIME")
  private String operTime;

  @Column(name = "LIKE_CNT")
  private Integer likeCnt;

  @Column(name = "ETC")
  private String etc;

  @Embedded
  @AttributeOverride(name = "bizNo", column = @Column(name = "BIZ_NO"))
  @AttributeOverride(name = "bizName", column = @Column(name = "BIZ_NAME"))
  private Biz biz;

  @Embedded
  @AttributeOverride(name = "zipcode", column = @Column(name = "zipcode"))
  @AttributeOverride(name = "address1", column = @Column(name = "address1"))
  @AttributeOverride(name = "address2", column = @Column(name = "address2"))
  private Address address;

  public Store(Long id) {
    this.id = id;
  }
}
