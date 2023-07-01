package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.ReplyDao;
import change.company.cwpark.data.dao.ReviewDao;
import change.company.cwpark.data.dto.ReplyDto;
import change.company.cwpark.data.dto.ReviewDto;
import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.entity.Reply;
import change.company.cwpark.data.entity.Review;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.service.ReplyService;
import change.company.cwpark.service.SaleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

  @Autowired
  private final ReplyDao replyDao;

  public ReplyServiceImpl(ReplyDao replyDao) {
    this.replyDao = replyDao;
  }

  @Override
  public List<ReplyDto> getReply(Review review) {
    List<Reply> list = replyDao.getReply(review);
    List<ReplyDto> rtnList = new ArrayList<>();

    for(Reply r : list) {
      rtnList.add(new ReplyDto(r.getId(), r.getReview().getId(), r.getContents()));
    }

    return rtnList;
  }
}
