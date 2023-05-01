package change.company.cwpark.data.dao;

import change.company.cwpark.data.entity.Menu;

import java.util.List;

public interface MenuDao {
    List<Menu> getAllMenu();
    void saveMenu(Menu menu);
    void deleteMenu(Menu menu);
}
