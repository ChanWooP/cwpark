package change.company.cwpark.data.api;

import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.vo.SaleItemVO;
import change.company.cwpark.data.vo.SalePlusItemVO;
import change.company.cwpark.data.vo.SaleVO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleAPI {
  private SaleVO sale;
  private List<SaleItemVO> saleItem;
  private List<SalePlusItemVO> salePlusItem;
}
