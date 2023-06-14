package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.ItemDao;
import change.company.cwpark.data.dao.StoreDao;
import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.Item;
import change.company.cwpark.data.entity.PlusItem;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.service.ItemService;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemServiceImpl implements ItemService {
  private final ItemDao itemDao;
  private final StoreDao storeDao;

  @Autowired
  public ItemServiceImpl(ItemDao itemDao, StoreDao storeDao) {
    this.itemDao = itemDao;
    this.storeDao = storeDao;
  }

  @Transactional
  @Override
  public void saveItem(List<Map<String, Object>> param, List<MultipartFile> fileList) {
    Store store = storeDao.getStores(Long.valueOf((String)param.get(0).get("storeId")));
    Category category;
    Item item;
    PlusItem plusItem;
    Long id = null;

    for(Map<String, Object> c : param) {
      id = c.get("id").toString().equals("") ? null : Long.valueOf((String)c.get("id"));
      category = new Category(id, store, c.get("categoryName").toString());
      category = itemDao.saveCategory(category);
      for(Map<String, Object> i : (List<Map<String, Object>>) c.get("item")) {
        id = String.valueOf(i.get("id")).equals("") ? null : Long.valueOf((String)i.get("id"));
        item = new Item(id, category, String.valueOf(i.get("itemName")), Integer.parseInt(i.get("itemCost").toString()), String.valueOf(i.get("itemImage")));
        item = itemDao.saveItem(item);
        for (Map<String, Object> p : (List<Map<String, Object>>) i.get("plusItem")) {
          id = p.get("id").toString().equals("") ? null : Long.valueOf((String)p.get("id"));
          plusItem = new PlusItem(id, item, p.get("itemName").toString(), Integer.parseInt(i.get("itemCost").toString()));
          itemDao.savePlusItem(plusItem);
        }
      }
    }
  }
}
