package change.company.cwpark.data.dao.Impl;

import change.company.cwpark.data.dao.MenuDao;
import change.company.cwpark.data.entity.Menu;
import change.company.cwpark.data.repository.MenuRepository;
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
        List<Menu> menu = menuRepository.findAllMenu();

        return menu;
    }
}
