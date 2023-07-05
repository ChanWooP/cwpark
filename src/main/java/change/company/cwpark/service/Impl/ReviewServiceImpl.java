package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.ReviewDao;
import change.company.cwpark.data.dao.SaleDao;
import change.company.cwpark.data.dto.ReviewDto;
import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.entity.Review;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.service.ReviewService;
import change.company.cwpark.service.SaleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private final ReviewDao reviewDao;

  public ReviewServiceImpl(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
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
}
