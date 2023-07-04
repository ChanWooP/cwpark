package change.company.cwpark.controller;

import change.company.cwpark.data.dto.ReplyDto;
import change.company.cwpark.data.dto.ReviewDto;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.entity.Review;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.vo.IdDateVO;
import change.company.cwpark.data.vo.IdVO;
import change.company.cwpark.data.vo.ReplyVO;
import change.company.cwpark.data.vo.StoreVO;
import change.company.cwpark.service.ReplyService;
import change.company.cwpark.service.ReviewService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/reply")
public class ReplyController {
  @Autowired
  private final ReplyService replyService;

  public ReplyController(ReplyService replyService) {
    this.replyService = replyService;
  }

  @ResponseBody
  @PostMapping("/view")
  public String getReply(@RequestBody IdVO[] id) {
    List<ReplyDto> replies = replyService.getReply(new Review(id[0].getId()));
    Gson gson = new Gson();
    String json = gson.toJson(replies);

    return json;
  }

  @ResponseBody
  @PostMapping("/save")
  public void saveReply(@RequestBody ReplyVO[] replyVOS) {
    List<ReplyDto> replyDtoList = new ArrayList<>();

    for(ReplyVO r : replyVOS) {
      replyDtoList.add(new ReplyDto(null, r.getReviewId(), r.getContents()));
    }

    replyService.saveReply(replyDtoList);

  }

}
