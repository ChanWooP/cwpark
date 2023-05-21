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

    private MemberDto account;

    private String storeName;

    private String tel;

    private String operTime;

    private Integer likeCnt;

    private String etc;

    private String bizNo;

    private String bizName;

    private String address1;

    private String address2;

    private String zipcode;
}
