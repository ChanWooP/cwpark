package change.company.cwpark.data.dao.Impl;

import change.company.cwpark.data.dao.SaleDao;
import change.company.cwpark.data.dao.StoreOpenDao;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.entity.StoreOpen;
import change.company.cwpark.data.repository.SaleRepository;
import change.company.cwpark.data.repository.StoreOpenRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreOpenDaoImpl implements StoreOpenDao {

  @Autowired
  private final StoreOpenRepository storeOpenRepository;

  public StoreOpenDaoImpl(StoreOpenRepository storeOpenRepository) {
    this.storeOpenRepository = storeOpenRepository;
  }

  @Override
  public List<StoreOpen> getStore(Store store) {
    return storeOpenRepository.findByStore(store);
  }

  @Override
  public void saveStore(StoreOpen storeOpen) {
    storeOpenRepository.save(storeOpen);
  }
}
