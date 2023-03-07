package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.MenuDao;
import change.company.cwpark.data.dto.MenuDto;
import change.company.cwpark.data.entity.Menu;
import change.company.cwpark.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MenuServiceImpl implements MenuService {

    private final MenuDao menuDao;

    @Autowired
    public MenuServiceImpl(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public List<Menu> getAllMenu() {
        return menuDao.getAllMenu();
    }
}
