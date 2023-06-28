package change.company.cwpark.service;

import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.dto.StoreOpenDto;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.entity.StoreOpen;
import java.util.List;

public interface StoreOpenService {
    List<StoreOpenDto> getStore(Store store);
    void saveStore(StoreOpenDto storeOpenDto);
}