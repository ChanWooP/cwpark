package change.company.cwpark.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private Long id;
    
    private Long parentNum;

    private Integer depth;

    private String name;

    private String path;

    private String menuName;

    private Integer childCnt;

    private String colStatus;
}
