package change.company.cwpark.controller;

import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.vo.IdDateVO;
import change.company.cwpark.service.SaleService;
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
@RequestMapping(value="/review")
public class ReviewController {

  @GetMapping("/")
  public String getAllMenu() {
    return "pages/review";
  }

}
