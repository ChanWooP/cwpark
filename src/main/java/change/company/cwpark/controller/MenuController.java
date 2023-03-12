package change.company.cwpark.controller;

import change.company.cwpark.data.dto.MenuDto;
import change.company.cwpark.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value="/menu")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/all")
    public String getAllMenu(Model model) {
        List<MenuDto> menu = menuService.getAllMenu();

        model.addAttribute("menu", menu);

        return "pages/menu";
    }
}
