package change.company.cwpark.data.dao.Impl;

import change.company.cwpark.data.dao.SaleItemDao;
import change.company.cwpark.data.dao.SalePlusItemDao;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.SalePlusItem;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.repository.SaleItemRepository;
import change.company.cwpark.data.repository.SalePlusItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalePlusItemDaoImpl implements SalePlusItemDao {

  @Autowired
  private final SalePlusItemRepository saleRepository;

  public SalePlusItemDaoImpl(SalePlusItemRepository saleRepository) {
    this.saleRepository = saleRepository;
  }

  @Override
  public List<SalePlusItem> getSale(Store store, String frDt, String toDt) {
    List<SalePlusItem> list = saleRepository.findByStoreAndSaleDateGreaterThanEqualAndSaleDateLessThanEqualOrderBySaleAsc(store, frDt, toDt);

    return list;
  }

  @Override
  public void SaveSalePlusItem(SalePlusItem salePlusItem) {
    saleRepository.save(salePlusItem);
  }
}
