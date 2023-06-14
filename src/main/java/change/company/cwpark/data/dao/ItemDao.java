package change.company.cwpark.data.dao;

import change.company.cwpark.data.dto.CategoryDto;
import change.company.cwpark.data.dto.ItemDto;
import change.company.cwpark.data.dto.PlusItemDto;
import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.Item;
import change.company.cwpark.data.entity.PlusItem;

public interface ItemDao {
  Category saveCategory(Category category);
  Item saveItem(Item item);
  PlusItem savePlusItem(PlusItem plusItem);
}
