package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MenuRepositoryTest {
    @Autowired
    MenuRepository menuRepository;

    @Test
    @DisplayName("전체 메뉴 가져오기")
    @Transactional
    void findAllMenu() {
        // given

        // when 흠... id 값이 밀리면 불러오지를 못하네?
        Menu menu = menuRepository.findByDepth(0);

        System.out.println("S");
    }
}
