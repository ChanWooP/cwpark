package change.company.cwpark.data.api;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAPI {
  private Long id;
  private String categoryName;
  private List<ItemAPI> itemAPIList;
}
