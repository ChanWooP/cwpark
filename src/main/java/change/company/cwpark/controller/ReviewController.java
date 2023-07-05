package change.company.cwpark.controller;

import change.company.cwpark.data.dto.ReviewDto;
import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.vo.IdDatePageVO;
import change.company.cwpark.data.vo.IdDateVO;
import change.company.cwpark.data.vo.ReviewVO;
import change.company.cwpark.service.ReviewService;
import change.company.cwpark.service.SaleService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/review")
public class ReviewController {
  @Autowired
  private final ReviewService reviewService;

  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping("/")
  public String view() {
    return "pages/review";
  }

  @ResponseBody
  @PostMapping("/view")
  public String getReview(@RequestBody IdDatePageVO[] idDatePage) {
    Page<ReviewDto> reviews = reviewService.getReview(new Store(idDatePage[0].getId()), idDatePage[0].getFrDt(), idDatePage[0].getToDt(), idDatePage[0].getPage());
    Gson gson = new Gson();
    String json = gson.toJson(reviews);

    return json;
  }

  @ResponseBody
  @PostMapping("/save")
  public void saveReview(@RequestBody ReviewVO[] reviewVOS) {
    List<ReviewDto> reviews = new ArrayList<>();

    for(ReviewVO r : reviewVOS) {
      reviews.add(new ReviewDto(null, r.getStoreId(), null, r.getSaleId(), r.getSaleDate()
          , r.getImage(), r.getContents(), r.getStarCnt(), "N"));
    }

    reviewService.saveReview(reviews);
  }
}
