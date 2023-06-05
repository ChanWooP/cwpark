package change.company.cwpark.data.dto;

import change.company.cwpark.data.entity.Member;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

    private Long id;

    private Long accountid;

    private String account;

    private String storename;

    private String tel;

    private String opertime;

    private Integer likecnt;

    private String etc;

    private String bizno;

    private String bizname;

    private String address1;

    private String address2;

    private String zipcode;

    private String colStatus;
}
