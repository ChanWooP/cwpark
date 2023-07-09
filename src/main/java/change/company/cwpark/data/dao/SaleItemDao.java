package change.company.cwpark.data.dao;

import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.SaleItem;
import change.company.cwpark.data.entity.Store;
import java.util.List;

public interface SaleItemDao {
  List<SaleItem> getSale(Store store, String frDt, String toDt);
  void saveSaleItem(SaleItem saleItem);

}
