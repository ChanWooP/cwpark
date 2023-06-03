package change.company.cwpark.data.vo;

import change.company.cwpark.data.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreVO {
  private String rowId;

  private Long id;

  private Long accountid;

  private String storename;

  private String account;

  private String bizno;

  private String bizname;

  private String tel;

  private String opertime;

  private Integer likecnt;

  private String zipcode;

  private String address1;

  private String address2;

  private String etc;

  private String colStatus;
}
