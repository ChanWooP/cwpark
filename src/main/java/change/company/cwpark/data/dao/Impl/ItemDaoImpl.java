package change.company.cwpark.data.dao.Impl;

import change.company.cwpark.data.dao.ItemDao;
import change.company.cwpark.data.dto.CategoryDto;
import change.company.cwpark.data.dto.ItemDto;
import change.company.cwpark.data.dto.PlusItemDto;
import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.Item;
import change.company.cwpark.data.entity.PlusItem;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.repository.CategoryRepository;
import change.company.cwpark.data.repository.ItemRepository;
import change.company.cwpark.data.repository.PlusItemRepository;
import change.company.cwpark.data.repository.StoreRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDaoImpl implements ItemDao {
  private CategoryRepository categoryRepository;
  private ItemRepository itemRepository;
  private PlusItemRepository plusItemRepository;

  @Autowired
  public ItemDaoImpl (CategoryRepository categoryRepository, ItemRepository itemRepository, PlusItemRepository plusItemRepository) {
    this.categoryRepository = categoryRepository;
    this.itemRepository = itemRepository;
    this.plusItemRepository = plusItemRepository;
  }

  @Override
  public Category saveCategory(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public Item saveItem(Item item) {
    return itemRepository.save(item);
  }

  @Override
  public PlusItem savePlusItem(PlusItem plusItem) {
    return plusItemRepository.save(plusItem);
  }

  @Override
  public List<Category> getCategory(Store storeId) {
    return categoryRepository.findByStoreId(storeId);
  }

  @Override
  public List<Item> getItem(Category categoryId) {
    return itemRepository.findByCategoryId(categoryId);
  }

  @Override
  public List<PlusItem> getPlusItem(Item itemId) {
    return plusItemRepository.findByItemId(itemId);
  }

  @Override
  public void deleteCategory(Category category) {
    categoryRepository.delete(category);
  }

  @Override
  public void deleteItem(Item item) {
    itemRepository.delete(item);
  }

  @Override
  public void deletePlusItem(PlusItem plusItem) {
    plusItemRepository.delete(plusItem);
  }
}
