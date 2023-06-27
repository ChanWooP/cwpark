package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.SaleDao;
import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.service.SaleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {

  @Autowired
  private final SaleDao saleDao;

  public SaleServiceImpl(SaleDao saleDao) {
    this.saleDao = saleDao;
  }

  @Override
  public List<SaleDto> getSale(Store store, String frDt, String toDt) {
    List<Sale> list = saleDao.getSale(store, frDt, toDt);
    List<SaleDto> listDto = new ArrayList<>();

    for(Sale s : list) {
      listDto.add(new SaleDto(s.getId(), s.getStore().getId(), s.getStore().getStoreName(), s.getSaleDate(), s.getAmt(), s.getQty()));
    }

    return listDto;
  }
}
