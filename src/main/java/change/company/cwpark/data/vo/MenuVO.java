package change.company.cwpark.data.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO {
  private String rowId;
  private Long parentNum;
  private Integer depth;
  private String name;
  private String path;
  private Long id;
  private String colStatus;
}
