package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.entity.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MenuRepositoryTest {
    @Autowired
    MenuRepository menuRepository;

    @Test
    @DisplayName("전체 메뉴 가져오기")
    void findAllMenu() {
        // given

        // when
        List<Menu> menu = menuRepository.findAll();

        System.out.println("S");
    }
}
