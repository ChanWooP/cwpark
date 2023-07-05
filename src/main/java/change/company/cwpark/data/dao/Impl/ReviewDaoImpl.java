package change.company.cwpark.data.dao.Impl;

import change.company.cwpark.data.dao.ReviewDao;
import change.company.cwpark.data.entity.Review;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.repository.ReviewRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReviewDaoImpl implements ReviewDao {

  @Autowired
  private final ReviewRepository reviewRepository;

  public ReviewDaoImpl(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  @Override
  public Page<Review> getReview(Store store, String frDt, String toDt, int page) {
    Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "sale_id"));
    return reviewRepository.findByStoreAndSaleDateGreaterThanEqualAndSaleDateLessThanEqual(store, frDt, toDt, pageable);
  }

  @Override
  public void saveReview(List<Review> list) {
    reviewRepository.saveAll(list);
  }

  @Override
  public void updateReview(Review review) {
    reviewRepository.updateReplyYn(review.getReplyYn(), review.getId());
  }


}
