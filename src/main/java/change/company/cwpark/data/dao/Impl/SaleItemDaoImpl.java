package change.company.cwpark.data.dao.Impl;

import change.company.cwpark.data.dao.SaleDao;
import change.company.cwpark.data.dao.SaleItemDao;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.SaleItem;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.repository.SaleItemRepository;
import change.company.cwpark.data.repository.SaleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleItemDaoImpl implements SaleItemDao {

  @Autowired
  private final SaleItemRepository saleRepository;

  public SaleItemDaoImpl(SaleItemRepository saleRepository) {
    this.saleRepository = saleRepository;
  }

  @Override
  public List<SaleItem> getSale(Store store, String frDt, String toDt) {
    List<SaleItem> list = saleRepository.findByStoreAndSaleDateGreaterThanEqualAndSaleDateLessThanEqualOrderBySaleAsc(store, frDt, toDt);

    return list;
  }

  @Override
  public void saveSaleItem(SaleItem saleItem) {
    saleRepository.save(saleItem);
  }
}
