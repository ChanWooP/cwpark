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
  private final ReviewDao reviewDao;

  public ReplyServiceImpl(ReplyDao replyDao, ReviewDao reviewDao) {
    this.replyDao = replyDao;
    this.reviewDao = reviewDao;
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

  @Override
  public void saveReply(List<ReplyDto> replyDtoList) {
    List<Reply> list = new ArrayList<>();

    for(ReplyDto r : replyDtoList) {
      Review review = new Review(r.getReviewId());
      review.setReplyYn("Y");
      reviewDao.updateReview(review);

      list.add(new Reply(null, review, r.getContents()));
    }

    replyDao.saveReply(list);

  }
}
