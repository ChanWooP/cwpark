package change.company.cwpark.controller;

import change.company.cwpark.data.api.SaleAPI;
import change.company.cwpark.data.dto.CategoryDto;
import change.company.cwpark.data.dto.ItemDto;
import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.dto.SaleItemDto;
import change.company.cwpark.data.dto.SalePlusItemDto;
import change.company.cwpark.data.entity.SaleItem;
import change.company.cwpark.data.entity.SalePlusItem;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.vo.IdDateVO;
import change.company.cwpark.data.vo.IdVO;
import change.company.cwpark.data.vo.SaleItemVO;
import change.company.cwpark.data.vo.SalePlusItemVO;
import change.company.cwpark.data.vo.SaleVO;
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

  @ResponseBody
  @PostMapping("/posts/sale")
  public void saveItem(@RequestBody SaleAPI saleAPI) {
    SaleVO saleVO = saleAPI.getSale();
    List<SaleItemVO> saleItemVO = saleAPI.getSaleItem();
    List<SalePlusItemVO> salePlusItemVO = saleAPI.getSalePlusItem();

    SaleDto saleDto = new SaleDto(null, saleVO.getStoreId(), null, saleVO.getSaleDate(), saleVO.getAmt(), saleVO.getQty());
    List<SaleItemDto> saleItemDto = new ArrayList<>();
    List<SalePlusItemDto> salePlusItemDto = new ArrayList<>();

    for(SaleItemVO s : saleItemVO) {
      saleItemDto.add(new SaleItemDto(null, null, null, s.getItemId(), null, null, null, s.getAmt(), s.getQty()));
    }

    for(SalePlusItemVO s : salePlusItemVO) {
      salePlusItemDto.add(new SalePlusItemDto(null, null, null, s.getItemId(), null, null, null, s.getAmt(), s.getQty()));
    }

    saleService.saveSale(saleDto, saleItemDto, salePlusItemDto);
  }

}
