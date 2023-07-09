package change.company.cwpark.data.dao;

import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.Item;
import change.company.cwpark.data.entity.PlusItem;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.Store;
import java.util.List;

public interface SaleDao {
  List<Sale> getSale(Store store, String frDt, String toDt);
  Sale saveSale(Sale sale);

}
