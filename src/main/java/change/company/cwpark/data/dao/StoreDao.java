package change.company.cwpark.data.dao;

import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.entity.Menu;
import change.company.cwpark.data.entity.Store;
import java.util.List;

public interface StoreDao {
    List<StoreDto> getStore(String storeName);
    void saveStore(List<StoreDto> storeDtoList);
    void deleteStore(Store menu);
}
