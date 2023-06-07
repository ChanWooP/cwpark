package change.company.cwpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/item")
public class ItemController {

  @GetMapping("/")
  public String getAllMenu() {
    return "pages/item";
  }

}