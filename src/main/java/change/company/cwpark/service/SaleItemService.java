package change.company.cwpark.service;

import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.dto.SaleItemDto;
import change.company.cwpark.data.entity.SaleItem;
import change.company.cwpark.data.entity.Store;
import java.util.List;

public interface SaleItemService {
  List<SaleItemDto> getSale(Store store, String frDt, String toDt);
}
