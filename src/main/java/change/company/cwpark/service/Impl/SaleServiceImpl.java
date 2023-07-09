package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.SaleDao;
import change.company.cwpark.data.dao.SaleItemDao;
import change.company.cwpark.data.dao.SalePlusItemDao;
import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.dto.SaleItemDto;
import change.company.cwpark.data.dto.SalePlusItemDto;
import change.company.cwpark.data.entity.Item;
import change.company.cwpark.data.entity.PlusItem;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.SaleItem;
import change.company.cwpark.data.entity.SalePlusItem;
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
  private final SaleItemDao saleItemDao;
  private final SalePlusItemDao salePlusItemDao;

  public SaleServiceImpl(SaleDao saleDao, SaleItemDao saleItemDao, SalePlusItemDao salePlusItemDao) {
    this.saleDao = saleDao;
    this.saleItemDao = saleItemDao;
    this.salePlusItemDao = salePlusItemDao;
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

  @Override
  public void saveSale(SaleDto saleDto, List<SaleItemDto> saleItemDto, List<SalePlusItemDto> salePlusItemDto) {
    Store store = new Store(saleDto.getStoreId());
    Sale sale = new Sale(saleDto.getId(), store, saleDto.getSaleDate(), saleDto.getAmt(), saleDto.getQty());
    List<SaleItem> saleItem = new ArrayList<>();
    List<SalePlusItem> salePlusItem = new ArrayList<>();

    sale = saleDao.saveSale(sale);

    for(SaleItemDto s : saleItemDto) {
      saleItem.add(new SaleItem(s.getId(), store, new Item(s.getItemId()), sale, sale.getSaleDate(), s.getAmt(), s.getQty()));
    }

    saleItemDao.saveSaleItem(saleItem);

    for(SalePlusItemDto s : salePlusItemDto) {
      salePlusItem.add(new SalePlusItem(s.getId(), store, new PlusItem(s.getItemId()), sale, sale.getSaleDate(), s.getAmt(), s.getQty()));
    }

    salePlusItemDao.saveSalePlusItem(salePlusItem);
  }
}
