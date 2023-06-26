package change.company.cwpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/sale")
public class SaleController {
  @GetMapping("/")
  public String getAllMenu() {
    return "pages/sale";
  }
}
