package change.company.cwpark.data.dao.Impl;

import change.company.cwpark.data.dao.SaleDao;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.repository.SaleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleDaoImpl implements SaleDao {

  @Autowired
  private final SaleRepository saleRepository;

  public SaleDaoImpl(SaleRepository saleRepository) {
    this.saleRepository = saleRepository;
  }

  @Override
  public List<Sale> getSale(Store store, String frDt, String toDt) {
    List<Sale> list = saleRepository.findByStoreAndSaleDateGreaterThanEqualAndSaleDateLessThanEqualOrderByIdAsc(store, frDt, toDt);

    return list;
  }
}
