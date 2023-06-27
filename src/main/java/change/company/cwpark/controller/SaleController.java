package change.company.cwpark.controller;

import change.company.cwpark.data.dto.CategoryDto;
import change.company.cwpark.data.dto.ItemDto;
import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.vo.IdDateVO;
import change.company.cwpark.data.vo.IdVO;
import change.company.cwpark.service.SaleService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/sale")
public class SaleController {

  @Autowired
  private final SaleService saleService;

  public SaleController(SaleService saleService) {
    this.saleService = saleService;
  }

  @GetMapping("/")
  public String getAllMenu() {
    return "pages/sale";
  }

  @ResponseBody
  @PostMapping("/view")
  public String getItem(@RequestBody IdDateVO[] idDate) {
    List<SaleDto> list = saleService.getSale(new Store(idDate[0].getId()), idDate[0].getFrDt(), idDate[0].getToDt());

    Gson gson = new Gson();
    String json = gson.toJson(list);

    return json;
  }
}
