package change.company.cwpark.service;

import change.company.cwpark.data.dto.MenuDto;
import change.company.cwpark.data.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getAllMenu();
}
