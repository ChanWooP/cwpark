package change.company.cwpark.data.api;

import change.company.cwpark.data.entity.PlusItem;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemAPI {
  private Long id;
  private String itemName;
  private int itemCost;
  private String itemImage;
  private List<PlusItemAPI> plusItemAPIList;
}
