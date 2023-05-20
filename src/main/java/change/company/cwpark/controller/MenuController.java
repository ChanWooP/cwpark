package change.company.cwpark.controller;

import change.company.cwpark.data.dto.MenuDto;
import change.company.cwpark.data.entity.Menu;
import change.company.cwpark.data.vo.MenuVO;
import change.company.cwpark.service.MenuService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/menu")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllMenu(Model model) {
        return "pages/menu";
    }

    @ResponseBody
    @GetMapping("/view")
    public String viewMenu() {
        List<MenuDto> menu = menuService.getAllMenu();
        Gson gson = new Gson();
        String menuJson = gson.toJson(menu);

        return menuJson;
    }

    @ResponseBody
    @PostMapping("/save")
    public String saveMenu(@RequestBody MenuVO[] menuVOs, Model model) {
        List<MenuDto> menuList = new ArrayList<>();

        for(MenuVO m : menuVOs) {
            menuList.add(new MenuDto(m.getId(), m.getParentNum(), m.getDepth(), m.getName(), m.getPath()
                ,"", 0, m.getColStatus()));
        }

        menuService.saveMenu(menuList);

        return "저장되었습니다.";
    }
}
