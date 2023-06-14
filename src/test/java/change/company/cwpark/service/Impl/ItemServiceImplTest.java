package change.company.cwpark.service.Impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import change.company.cwpark.data.dao.Impl.ItemDaoImpl;
import change.company.cwpark.data.dto.MemberDto;
import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.Item;
import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.entity.PlusItem;
import change.company.cwpark.data.entity.Store;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {
  @Mock
  ItemDaoImpl itemDao;

  @Test
  @DisplayName("상품 저장")
  public void saveItem() {
    // given
    Map<String, Object> categoryMap = new HashMap<>();
    Map<String, Object> itemMap = new HashMap<>();
    Map<String, Object> plusItemMap = new HashMap<>();

    List<Map<String, Object>> categoryList = new ArrayList<>();
    List<Map<String, Object>> itemList = new ArrayList<>();
    List<Map<String, Object>> plusItemList = new ArrayList<>();

    Category category = new Category(null, null, "category");
    Item item = new Item(null, category, "item", 2000, "find.png");
    PlusItem plusItem = new PlusItem(null, item, "plusItem", 1000);

    plusItemMap.put("itemName", "plusItem");
    plusItemMap.put("itemCost", 1000);
    plusItemList.add(plusItemMap);

    itemMap.put("itemName", "item");
    itemMap.put("itemCost", 2000);
    itemMap.put("itemImage", "find.png");
    itemMap.put("plusItem", plusItemList);
    itemList.add(itemMap);

    categoryMap.put("categoryName", "category");
    categoryMap.put("item", itemList);
    categoryList.add(categoryMap);

    Mockito.when(itemDao.saveCategory(any())).thenReturn(category);
    Mockito.when(itemDao.saveItem(any())).thenReturn(item);
    Mockito.when(itemDao.savePlusItem(any())).thenReturn(plusItem);

    for(Map<String, Object> c : categoryList) {
      category = new Category(null, null, c.get("categoryName").toString());
      category = itemDao.saveCategory(category);
      for(Map<String, Object> i : (List<Map<String, Object>>) c.get("item")) {
        item = new Item(null, category, i.get("itemName").toString(), (int) i.get("itemCost"),
            i.get("itemImage").toString());
        item = itemDao.saveItem(item);
        for (Map<String, Object> p : (List<Map<String, Object>>) i.get("plusItem")) {
          plusItem = new PlusItem(null, item, p.get("itemName").toString(),
              (int) p.get("itemCost"));
          plusItem = itemDao.savePlusItem(plusItem);
        }
      }
    }

    // then
    Assertions.assertEquals(plusItem.getItemId().getCategoryId().getCategoryName(), "category");
  }
}
