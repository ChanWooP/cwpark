package change.company.cwpark.service;

import change.company.cwpark.data.dto.CategoryDto;
import change.company.cwpark.data.dto.ItemDto;
import change.company.cwpark.data.dto.PlusItemDto;
import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.dto.SaleItemDto;
import change.company.cwpark.data.dto.SalePlusItemDto;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.entity.Store;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface SaleService {
  List<SaleDto> getSale(Store store, String frDt, String toDt);
  void saveSale(SaleDto saleDto, List<SaleItemDto> saleItemDto, List<SalePlusItemDto> salePlusItemDto);
}
