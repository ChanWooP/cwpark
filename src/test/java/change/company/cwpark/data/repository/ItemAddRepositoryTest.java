package change.company.cwpark.data.repository;

import change.company.cwpark.data.emb.Address;
import change.company.cwpark.data.emb.Biz;
import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.Item;
import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.entity.PlusItem;
import change.company.cwpark.data.entity.Store;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemAddRepositoryTest {

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  StoreRepository storeRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  ItemRepository itemRepository;

  @Autowired
  PlusItemRepository plusItemRepository;

  @Test
  @DisplayName("상품 최종 저장")
  @Transactional
  void saveItemTot() {
    // given
    Member member = memberRepository.save(new Member("123", "123", "123", LocalDateTime.now(), 0));
    Store store = storeRepository.save(new Store(null, member, "1", "2", "3", 4, "5"
        , new Biz("6", "7")
        , new Address("8", "9", "10")));

    // when
    Category category = new Category(null, store, "test");
    categoryRepository.save(category);
    List<Category> categoryList = categoryRepository.findAll();

    Item item = new Item(null, categoryList.get(0), "test", 1, "test");
    itemRepository.save(item);
    List<Item> itemList = itemRepository.findAll();

    PlusItem plusItem = new PlusItem(null, itemList.get(0), "test", 1);
    plusItemRepository.save(plusItem);
    List<PlusItem> plusItemsList = plusItemRepository.findAll();

    // then
    Assertions.assertThat(plusItemsList.get(0).getItemName()).isEqualTo(plusItem.getItemName());
  }
}
