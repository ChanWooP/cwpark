package change.company.cwpark.data.dao;

import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.entity.StoreOpen;
import java.util.List;
import java.util.Map;

public interface StoreOpenDao {
    List<StoreOpen> getStore(Store store);

    void saveStore(StoreOpen storeOpen);
}
