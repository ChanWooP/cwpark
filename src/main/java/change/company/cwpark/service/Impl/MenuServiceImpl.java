package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.MenuDao;
import change.company.cwpark.data.dto.MenuDto;
import change.company.cwpark.data.entity.Menu;
import change.company.cwpark.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuDao menuDao;

    @Autowired
    public MenuServiceImpl(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public List<MenuDto> getAllMenu() {
        List<Menu> menu = menuDao.getAllMenu();
        List<MenuDto> menuDto = new LinkedList<>();

        for(Menu m : menu) {
            menuDto.add(new MenuDto(m.getId(), m.getParentNum(), m.getDepth(), m.getName(), m.getPath(), m.getMenuName()));
        }

        return menuDto;
    }

    @Override
    public MenuDto saveMenu(MenuDto menuDto) {
        Menu menu = menuDao.saveMenu(new Menu(menuDto.getId(), menuDto.getParentNum(), menuDto.getDepth()
                , menuDto.getName(), menuDto.getPath(), menuDto.getMenuName()));

        MenuDto menuDtoRtn = new MenuDto(menu.getId(), menu.getParentNum(), menu.getDepth()
                , menu.getName(), menu.getPath(), menu.getMenuName());

        return menuDtoRtn;
    }
}
