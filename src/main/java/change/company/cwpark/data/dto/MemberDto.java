package change.company.cwpark.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String name;
    private String account;
    private String password;
    private LocalDateTime lastAccessDt;
    private Integer loginFailCnt;

}
