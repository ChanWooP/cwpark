package change.company.cwpark.data.dao;

import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.SalePlusItem;
import change.company.cwpark.data.entity.Store;
import java.util.List;

public interface SalePlusItemDao {
  List<SalePlusItem> getSale(Store store, String frDt, String toDt);

}
