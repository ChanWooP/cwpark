package change.company.cwpark.service.Impl;

import change.company.cwpark.data.api.CategoryAPI;
import change.company.cwpark.data.api.ItemAPI;
import change.company.cwpark.data.api.PlusItemAPI;
import change.company.cwpark.data.dao.ItemDao;
import change.company.cwpark.data.dao.StoreDao;
import change.company.cwpark.data.dto.CategoryDto;
import change.company.cwpark.data.dto.ItemDto;
import change.company.cwpark.data.dto.PlusItemDto;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.emb.Address;
import change.company.cwpark.data.emb.Biz;
import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.Item;
import change.company.cwpark.data.entity.PlusItem;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.service.ItemService;
import change.company.cwpark.util.FileUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
  public void saveItem(List<Map<String, Object>> param, List<MultipartFile> fileList) throws IOException {
    Store store = storeDao.getStores(Long.valueOf((String)param.get(0).get("storeId")));
    Category category;
    Item item;
    PlusItem plusItem;

    Long id = null;
    Date nowDate = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    String saveFileName;

    for(Map<String, Object> c : param) {
      id = c.get("id").toString().equals("") ? null : Long.valueOf((String)c.get("id"));
      category = new Category(id, store, c.get("categoryName").toString());

      if(String.valueOf(c.get("colStatus")).equals("D")) {
        itemDao.deleteCategory(category);
        category = null;
      } else {
        category = itemDao.saveCategory(category);
      }


      for(Map<String, Object> i : (List<Map<String, Object>>) c.get("item")) {
        id = String.valueOf(i.get("id")).equals("") ? null : Long.valueOf((String)i.get("id"));
        saveFileName = String.valueOf(i.get("itemImage"));

        if(fileList != null)  {
          for (MultipartFile mf : fileList) {
            if (mf.getOriginalFilename().substring(0, mf.getOriginalFilename().length() - 14)
                .equals(i.get("rowId").toString().substring(5))) {
              saveFileName = FileUtils.singleFileUpload(mf);
            }
          }
        }

        item = new Item(id, category, String.valueOf(i.get("itemName")), Integer.parseInt(i.get("itemCost").toString()), saveFileName);

        if(String.valueOf(i.get("colStatus")).equals("D")) {
          itemDao.deleteItem(item);
          item = null;
        } else {
          item = itemDao.saveItem(item);
        }

        if((List<Map<String, Object>>) i.get("plusItem") != null) {
          for (Map<String, Object> p : (List<Map<String, Object>>) i.get("plusItem")) {
            id = p.get("id").toString().equals("") ? null : Long.valueOf((String)p.get("id"));
            plusItem = new PlusItem(id, item, p.get("itemName").toString(), Integer.parseInt(p.get("itemCost").toString()));

            if(String.valueOf(p.get("colStatus")).equals("D")) {
              itemDao.deletePlusItem(plusItem);
            } else {
              itemDao.savePlusItem(plusItem);
            }

          }
        }
      }
    }
  }

  @Override
  public List<CategoryDto> getCategory(StoreDto storeId) {
    List<Category> list = itemDao.getCategory(new Store(storeId.getId()
        , null
        , storeId.getStorename(), storeId.getTel()
        , storeId.getOpertime() ,storeId.getLikecnt(), storeId.getEtc()
        , new Biz(storeId.getBizno(), storeId.getBizname())
        , new Address(storeId.getAddress1(), storeId.getAddress2(), storeId.getZipcode())));

    List<CategoryDto> rtn = new ArrayList<>();

    for(Category c : list) {
      rtn.add(new CategoryDto(c.getId(), c.getStoreId().getId(), c.getCategoryName()));
    }

    return rtn;
  }

  @Override
  public List<ItemDto> getItem(CategoryDto categoryId) {
    List<Item> list = itemDao.getItem(new Category(categoryId.getId(), null, null));
    List<ItemDto> rtn = new ArrayList<>();

    for(Item i : list) {
      rtn.add(new ItemDto(i.getId(), i.getCategoryId().getId(), i.getItemName(), i.getItemCost(), i.getItemImage()));
    }

    return rtn;
  }

  @Override
  public List<PlusItemDto> getPlusItem(ItemDto ItemId) {
    List<PlusItem> list = itemDao.getPlusItem(new Item(ItemId.getId(), null, null, 0, null));
    List<PlusItemDto> rtn = new ArrayList<>();

    for(PlusItem p : list) {
      rtn.add(new PlusItemDto(p.getId(), p.getItemId().getId(), p.getItemName(), p.getItemCost()));
    }

    return rtn;
  }

  @Override
  public List<CategoryAPI> getItemAPI(Store store) {
    List<CategoryAPI> rtnList = new ArrayList<>();
    List<Category> categoryList = itemDao.getCategory(store);

    for(Category c : categoryList) {
      List<ItemAPI> itemAPIList = new ArrayList<>();
      List<Item> itemList = itemDao.getItem(c);

      for(Item i : itemList) {
        List<PlusItemAPI> plusItemAPIList = new ArrayList<>();
        List<PlusItem> plusItemList = itemDao.getPlusItem(i);

        for(PlusItem p : plusItemList) {
          plusItemAPIList.add(new PlusItemAPI(p.getId(), p.getItemName(), p.getItemCost()));
        }

        itemAPIList.add(new ItemAPI(i.getId(), i.getItemName(), i.getItemCost(), i.getItemImage(), plusItemAPIList));
      }
      rtnList.add(new CategoryAPI(c.getId(), c.getCategoryName(), itemAPIList));
    }

    return rtnList;
  }


}
