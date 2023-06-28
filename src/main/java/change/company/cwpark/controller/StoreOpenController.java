package change.company.cwpark.controller;

import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.dto.StoreOpenDto;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.entity.StoreOpen;
import change.company.cwpark.data.vo.IdDateVO;
import change.company.cwpark.data.vo.StoreOpenVO;
import change.company.cwpark.data.vo.StoreVO;
import change.company.cwpark.service.SaleService;
import change.company.cwpark.service.StoreOpenService;
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
@RequestMapping(value="/storeOpen")
public class StoreOpenController {

  @Autowired
  private final StoreOpenService storeOpenService;

  public StoreOpenController(StoreOpenService storeOpenService) {
    this.storeOpenService = storeOpenService;
  }

  @ResponseBody
  @PostMapping("/view")
  public String getStoreOpen(@RequestBody IdDateVO[] idDate) {
    List<StoreOpenDto> list = storeOpenService.getStore(new Store(idDate[0].getId()));

    Gson gson = new Gson();
    String json = gson.toJson(list);

    return json;
  }

  @ResponseBody
  @PostMapping("/save")
  public void saveStoreOpen(@RequestBody StoreOpenVO[] StoreOpenVOs) {
    List<StoreOpenDto> list = new ArrayList<>();

    for(StoreOpenVO s : StoreOpenVOs) {
      list.add(new StoreOpenDto(s.getId(), s.getStoreId(), s.getOpenYn()));
    }

    storeOpenService.saveStore(list.get(0));
  }
}
