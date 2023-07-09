package change.company.cwpark.data.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleVO {

  private Long storeId;

  private String saleDate;

  private int amt;

  private int qty;
}
