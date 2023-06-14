package change.company.cwpark.data.dao.Impl;

import change.company.cwpark.data.dao.ItemDao;
import change.company.cwpark.data.dto.CategoryDto;
import change.company.cwpark.data.dto.ItemDto;
import change.company.cwpark.data.dto.PlusItemDto;
import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.Item;
import change.company.cwpark.data.entity.PlusItem;
import change.company.cwpark.data.repository.CategoryRepository;
import change.company.cwpark.data.repository.ItemRepository;
import change.company.cwpark.data.repository.PlusItemRepository;
import change.company.cwpark.data.repository.StoreRepository;
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
}
