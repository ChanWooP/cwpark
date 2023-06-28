package change.company.cwpark.service;

import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.dto.SalePlusItemDto;
import change.company.cwpark.data.entity.Store;
import java.util.List;

public interface SalePlusItemService {
  List<SalePlusItemDto> getSale(Store store, String frDt, String toDt);
}
