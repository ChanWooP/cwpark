package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Menu;
import change.company.cwpark.data.reinterface.MenuInterface;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class MenuRepositoryTest {
    @Autowired
    MenuRepository menuRepository;

    @Test
    @DisplayName("전체 메뉴 가져오기")
    @Transactional
    void findAllMenu() {
        // given
        Menu menu1 = menuRepository.save(new Menu(null, 0, "ROOT", "", ""));
        Menu menu2 = menuRepository.save(new Menu(menu1.getId(), 1, "대1", "", ""));
        Menu menu3 = menuRepository.save(new Menu(menu2.getId(), 2, "중1", "", ""));

        // when
        List<MenuInterface> menuList = menuRepository.findAllMenu();

        // then
        Assertions.assertThat(menu1.getName()).isEqualTo(menuList.get(0).getName());
        Assertions.assertThat(menu2.getName()).isEqualTo(menuList.get(1).getName());
        Assertions.assertThat(menu3.getName()).isEqualTo(menuList.get(2).getName());
    }
}
