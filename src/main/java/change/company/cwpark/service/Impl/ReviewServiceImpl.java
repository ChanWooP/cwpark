package change.company.cwpark.service.Impl;

import change.company.cwpark.data.api.ReplyAPI;
import change.company.cwpark.data.api.ReviewAPI;
import change.company.cwpark.data.dao.ReplyDao;
import change.company.cwpark.data.dao.ReviewDao;
import change.company.cwpark.data.dao.SaleDao;
import change.company.cwpark.data.dto.ReplyDto;
import change.company.cwpark.data.dto.ReviewDto;
import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.entity.Reply;
import change.company.cwpark.data.entity.Review;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.service.ReviewService;
import change.company.cwpark.service.SaleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private final ReviewDao reviewDao;
  private final ReplyDao replyDao;

  public ReviewServiceImpl(ReviewDao reviewDao, ReplyDao replyDao) {
    this.reviewDao = reviewDao;
    this.replyDao = replyDao;
  }

  @Override
  public Page<ReviewDto> getReview(Store store, String frDt, String toDt, int page) {
    Page<Review> pages = reviewDao.getReview(store, frDt, toDt, page);
    Page<ReviewDto> rtnPage = pages.map(p -> ReviewDto.builder()
        .id(p.getId())
        .storeId(p.getStore().getId())
        .storeName(p.getStore().getStoreName())
        .saleId(p.getSale().getId())
        .saleDate(p.getSaleDate())
        .image(p.getImage())
        .contents(p.getContents())
        .starCnt(p.getStarCnt())
        .replyYn(p.getReplyYn())
        .build());

    return rtnPage;
  }

  @Override
  public void saveReview(List<ReviewDto> list) {
    List<Review> reviews = new ArrayList<>();

    for(ReviewDto r : list) {
      reviews.add(new Review(null, new Store(r.getStoreId()), new Sale(r.getSaleId())
      , r.getSaleDate(), r.getImage(), r.getContents(), r.getStarCnt(), r.getReplyYn()));
    }

    reviewDao.saveReview(reviews);
  }

  @Override
  public Page<ReviewAPI> getReviewAPI(Store store, String frDt, String toDt, int page) {
    Page<Review> pages = reviewDao.getReview(store, frDt, toDt, page);
    Page<ReviewAPI> rtnPage = pages.map(p -> ReviewAPI.builder()
        .id(p.getId())
        .saleDate(p.getSaleDate())
        .image(p.getImage())
        .contents(p.getContents())
        .starCnt(p.getStarCnt())
        .build());

    for(int i=0; i<pages.getSize(); i++) {
      List<Reply> reply = null;

      if(pages.getContent().get(i).getReplyYn() == "Y") {
        reply = replyDao.getReply(pages.getContent().get(i));
      }

      if(reply.size() > 0) {
        rtnPage.getContent().get(0).setReplyAPI(new ReplyAPI(reply.get(0).getId(), reply.get(0).getContents()));
      }
    }

    return rtnPage;
  }
}
