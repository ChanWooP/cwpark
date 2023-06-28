package change.company.cwpark.controller;

import change.company.cwpark.data.dto.SaleItemDto;
import change.company.cwpark.data.dto.SalePlusItemDto;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.vo.IdDateVO;
import change.company.cwpark.service.SaleItemService;
import change.company.cwpark.service.SalePlusItemService;
import com.google.gson.Gson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/salePlusItem")
public class SalePlusItemController {

  @Autowired
  private final SalePlusItemService saleService;

  public SalePlusItemController(SalePlusItemService saleService) {
    this.saleService = saleService;
  }

  @GetMapping("/")
  public String getAllMenu() {
    return "pages/salePlusItem";
  }

  @ResponseBody
  @PostMapping("/view")
  public String getItem(@RequestBody IdDateVO[] idDate) {
    List<SalePlusItemDto> list = saleService.getSale(new Store(idDate[0].getId()), idDate[0].getFrDt(), idDate[0].getToDt());

    Gson gson = new Gson();
    String json = gson.toJson(list);

    return json;
  }
}
