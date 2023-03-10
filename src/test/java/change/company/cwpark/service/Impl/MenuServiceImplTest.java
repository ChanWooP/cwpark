package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.Impl.MenuDaoImpl;
import change.company.cwpark.data.dto.MenuDto;
import change.company.cwpark.data.entity.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MenuServiceImplTest {
    @Mock
    MenuDaoImpl menuDao;

    @InjectMocks
    MenuServiceImpl menuService;

    @Test
    @DisplayName("메뉴 전체 조회")
    public void getAllMenuTest() {
        // given
        List<Menu> menu = new ArrayList<>();
        menu.add(new Menu(null, 0, "ROOT"));
        menu.add(new Menu(1L, 0, "대1"));
        menu.add(new Menu(2L, 0, "중1"));

        Mockito.when(menuDao.getAllMenu()).thenReturn(menu);

        // when
        List<MenuDto> menuDto = menuService.getAllMenu();

        // then
        Assertions.assertEquals(menuDto.get(0).getName(), "ROOT");
        Assertions.assertEquals(menuDto.get(1).getName(), "대1");
        Assertions.assertEquals(menuDto.get(2).getName(), "중1");

        verify(menuDao).getAllMenu();
    }
}
