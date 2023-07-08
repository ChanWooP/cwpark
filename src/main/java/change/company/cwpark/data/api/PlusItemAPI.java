package change.company.cwpark.data.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlusItemAPI {
  private Long id;
  private String itemName;
  private int itemCost;
}
