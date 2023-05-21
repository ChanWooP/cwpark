package change.company.cwpark.service;

import change.company.cwpark.data.dto.MenuDto;
import change.company.cwpark.data.dto.StoreDto;
import java.util.List;

public interface StoreService {
    List<StoreDto> getStore(String storeName);

    void saveStore(List<StoreDto> storeDtoList);
}
