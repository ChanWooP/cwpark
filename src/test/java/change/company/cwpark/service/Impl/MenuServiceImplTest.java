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
        menu.add(new Menu(null, null, 0, "root", "", "", 0, ""));
        menu.add(new Menu(null, menu.get(0).getId(), 1, "대1", "", "", 0, ""));
        menu.add(new Menu(null, menu.get(1).getId(), 2, "중1", "", "", 0, ""));

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
