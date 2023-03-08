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
        
        // when 흠... ROOT가 ID가 1이아니면 안되나..? 아무튼 저장 까지 해서 테스트 만들어보자
        Menu menu = menuRepository.findByDepth(0);

        System.out.println("S");
    }
}
