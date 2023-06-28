package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.SaleDao;
import change.company.cwpark.data.dao.SaleItemDao;
import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.dto.SaleItemDto;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.SaleItem;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.service.SaleItemService;
import change.company.cwpark.service.SalePlusItemService;
import change.company.cwpark.service.SaleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleItemServiceImpl implements SaleItemService {

  @Autowired
  private final SaleItemDao saleDao;

  public SaleItemServiceImpl(SaleItemDao saleDao) {
    this.saleDao = saleDao;
  }

  @Override
  public List<SaleItemDto> getSale(Store store, String frDt, String toDt) {
    List<SaleItem> list = saleDao.getSale(store, frDt, toDt);
    List<SaleItemDto> listDto = new ArrayList<>();

    for(SaleItem s : list) {
      listDto.add(new SaleItemDto(s.getId(), s.getStore().getId(), s.getStore().getStoreName()
          , s.getItem().getId(), s.getItem().getItemName(), s.getSale().getId()
          , s.getSaleDate(), s.getAmt(), s.getQty()));
    }

    return listDto;
  }
}
