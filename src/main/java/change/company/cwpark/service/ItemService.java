package change.company.cwpark.service;

import change.company.cwpark.data.api.CategoryAPI;
import change.company.cwpark.data.dto.CategoryDto;
import change.company.cwpark.data.dto.ItemDto;
import change.company.cwpark.data.dto.PlusItemDto;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.entity.Store;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {
  void saveItem(List<Map<String, Object>> param, List<MultipartFile> fileList) throws IOException;
  List<CategoryDto> getCategory(StoreDto storeId);
  List<ItemDto> getItem(CategoryDto categoryId);
  List<PlusItemDto> getPlusItem(ItemDto ItemId);
  List<CategoryAPI> getItemAPI(Store store);
}
