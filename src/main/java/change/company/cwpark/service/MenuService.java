package change.company.cwpark.service;

import change.company.cwpark.data.dto.MenuDto;

import change.company.cwpark.data.multiRow.MultiMenu;
import java.util.List;

public interface MenuService {
    List<MenuDto> getAllMenu();

    void saveMenu(MultiMenu menus);
}
