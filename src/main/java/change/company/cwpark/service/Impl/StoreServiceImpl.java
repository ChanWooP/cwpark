package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.StoreDao;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.service.StoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreDao storeDao;

    @Autowired
    public StoreServiceImpl(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Override
    public List<StoreDto> getStore(String storeName) {
        return storeDao.getStore(storeName);
    }

    @Override
    public void saveStore(List<StoreDto> storeDtoList) {
        storeDao.saveStore(storeDtoList);
    }
}
