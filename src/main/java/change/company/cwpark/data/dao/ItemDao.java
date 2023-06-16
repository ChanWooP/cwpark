package change.company.cwpark.data.dao;

import change.company.cwpark.data.dto.CategoryDto;
import change.company.cwpark.data.dto.ItemDto;
import change.company.cwpark.data.dto.PlusItemDto;
import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.Item;
import change.company.cwpark.data.entity.PlusItem;
import change.company.cwpark.data.entity.Store;
import java.util.List;

public interface ItemDao {
  Category saveCategory(Category category);
  Item saveItem(Item item);
  PlusItem savePlusItem(PlusItem plusItem);
  List<Category> getCategory(Store storeId);
  List<Item> getItem(Category categoryId);
  List<PlusItem> getPlusItem(Item ItemId);
  void deleteCategory(Category category);
  void deleteItem(Item item);
  void deletePlusItem(PlusItem plusItem);

}
