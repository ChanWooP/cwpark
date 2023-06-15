package change.company.cwpark.controller;

import change.company.cwpark.data.dto.CategoryDto;
import change.company.cwpark.data.dto.ItemDto;
import change.company.cwpark.data.dto.PlusItemDto;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.entity.Category;
import change.company.cwpark.data.entity.PlusItem;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.vo.IdVO;
import change.company.cwpark.data.vo.StoreVO;
import change.company.cwpark.service.ItemService;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/item")
public class ItemController {

  private final ItemService itemService;

  @Autowired
  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping("/")
  public String getAllMenu() {
    return "pages/item";
  }

  @ResponseBody
  @PostMapping("/save")
  public void saveItem(
        @RequestPart(value = "json") List<Map<String, Object>> param
      , @RequestPart(value = "file",required = false) List<MultipartFile> fileList) throws IOException {
    itemService.saveItem(param, fileList);
  }

  @ResponseBody
  @PostMapping("/view/category")
  public String getCategory(@RequestBody StoreVO[] storeId) {
    StoreDto dto = new StoreDto(storeId[0].getId()
        , storeId[0].getAccountid(), storeId[0].getAccount()
        , storeId[0].getStorename(), storeId[0].getTel(), storeId[0].getOpertime(), storeId[0].getLikecnt(), storeId[0].getEtc()
        , storeId[0].getBizno(), storeId[0].getBizname()
        , storeId[0].getAddress1(), storeId[0].getAddress2(), storeId[0].getZipcode(), storeId[0].getColStatus());
    List<CategoryDto> list = itemService.getCategory(dto);

    Gson gson = new Gson();
    String json = gson.toJson(list);

    return json;
  }

  @ResponseBody
  @PostMapping("/view/item")
  public String getItem(@RequestBody IdVO[] categoryId) {
    List<ItemDto> list = itemService.getItem(new CategoryDto(categoryId[0].getId(), null, null));
    Gson gson = new Gson();
    String json = gson.toJson(list);

    return json;
  }

  @ResponseBody
  @PostMapping("/view/plusItem")
  public String getPlusItem(@RequestBody IdVO[] itemId) {
    List<PlusItemDto> list = itemService.getPlusItem(new ItemDto(itemId[0].getId(), null, null, null, null));
    Gson gson = new Gson();
    String json = gson.toJson(list);

    return json;
  }

}