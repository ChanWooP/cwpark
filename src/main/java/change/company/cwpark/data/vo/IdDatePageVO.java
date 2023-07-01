package change.company.cwpark.data.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdDatePageVO {
  private Long id;
  private String frDt;
  private String toDt;
  private int page;
}
