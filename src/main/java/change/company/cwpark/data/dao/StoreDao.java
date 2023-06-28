package change.company.cwpark.data.dao;

import change.company.cwpark.data.dto.MemberDto;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.entity.Menu;
import change.company.cwpark.data.entity.Store;
import java.util.List;
import java.util.Map;

public interface StoreDao {
    List<StoreDto> getStore(String storeName);
    void saveStore(Map<StoreDto, Member> storeDtoList);
    void deleteStore(Map<StoreDto, Member> storeDtoList);
    StoreDto getAccount(Member account);
    Store getStores(Long storeId);
    Store saveStoreOne(Store store);
}
