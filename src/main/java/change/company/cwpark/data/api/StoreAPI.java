package change.company.cwpark.data.api;

import change.company.cwpark.data.entity.Member;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreAPI {
  private Long id;
  private String storeName;
  private String tel;
  private String operTime;
  private int likeCnt;
  private String etc;
  private String bizNo;
  private String bizName;
  private String address1;
  private String address2;
  private String zipcode;
}
