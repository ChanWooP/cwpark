package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.SaleItemDao;
import change.company.cwpark.data.dao.SalePlusItemDao;
import change.company.cwpark.data.dto.SaleItemDto;
import change.company.cwpark.data.dto.SalePlusItemDto;
import change.company.cwpark.data.entity.SaleItem;
import change.company.cwpark.data.entity.SalePlusItem;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.service.SalePlusItemService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalePlusItemServiceImpl implements SalePlusItemService {

  @Autowired
  private final SalePlusItemDao saleDao;

  public SalePlusItemServiceImpl(SalePlusItemDao saleDao) {
    this.saleDao = saleDao;
  }

  @Override
  public List<SalePlusItemDto> getSale(Store store, String frDt, String toDt) {
    List<SalePlusItem> list = saleDao.getSale(store, frDt, toDt);
    List<SalePlusItemDto> listDto = new ArrayList<>();

    for(SalePlusItem s : list) {
      listDto.add(new SalePlusItemDto(s.getId(), s.getStore().getId(), s.getStore().getStoreName()
          , s.getItem().getId(), s.getItem().getItemName(), s.getSale().getId()
          , s.getSaleDate(), s.getAmt(), s.getQty()));
    }

    return listDto;
  }
}
