package change.company.cwpark.data.dao.Impl;

import change.company.cwpark.data.dao.MenuDao;
import change.company.cwpark.data.entity.Menu;
import change.company.cwpark.data.reinterface.MenuInterface;
import change.company.cwpark.data.repository.MenuRepository;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MenuDaoImpl implements MenuDao {

    private MenuRepository menuRepository;

    @Autowired
    public MenuDaoImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // 메뉴 전체 조회
    @Override
    public List<Menu> getAllMenu() {
        List<MenuInterface> menu = menuRepository.findAllMenu();
        List<Menu> rtnMenu = new ArrayList<>();

        for(MenuInterface m : menu) {
            rtnMenu.add(new Menu(m.getId(), m.getParentNum(), m.getDepth(), m.getName()));
        }

        return rtnMenu;
    }
}
