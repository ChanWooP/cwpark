package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.SaleDao;
import change.company.cwpark.data.dao.StoreOpenDao;
import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.dto.StoreOpenDto;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.entity.StoreOpen;
import change.company.cwpark.service.SaleService;
import change.company.cwpark.service.StoreOpenService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreOpenServiceImpl implements StoreOpenService {

  @Autowired
  private final StoreOpenDao storeOpenDao;

  public StoreOpenServiceImpl(StoreOpenDao storeOpenDao) {
    this.storeOpenDao = storeOpenDao;
  }

  @Override
  public List<StoreOpenDto> getStore(Store store) {
    List<StoreOpen> list = storeOpenDao.getStore(store);
    List<StoreOpenDto> rtnList = new ArrayList<>();

    for(StoreOpen s : list) {
      rtnList.add(new StoreOpenDto(s.getId(), s.getStore().getId(), s.getOpenYn()));
    }

    return rtnList;
  }

  @Override
  public void saveStore(StoreOpenDto storeOpenDto) {
    storeOpenDao.saveStore(new StoreOpen(storeOpenDto.getId(), new Store(storeOpenDto.getStoreId()), storeOpenDto.getOpenYn()));
  }
}
