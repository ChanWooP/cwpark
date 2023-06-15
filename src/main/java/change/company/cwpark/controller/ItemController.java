package change.company.cwpark.controller;

import change.company.cwpark.service.ItemService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}