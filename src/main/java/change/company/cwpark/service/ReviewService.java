package change.company.cwpark.service;

import change.company.cwpark.data.api.ReviewAPI;
import change.company.cwpark.data.dto.ReviewDto;
import change.company.cwpark.data.dto.SaleDto;
import change.company.cwpark.data.entity.Store;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ReviewService {
  Page<ReviewDto> getReview(Store store, String frDt, String toDt, int page);
  void saveReview(List<ReviewDto> list);
  Page<ReviewAPI> getReviewAPI(Store store, String frDt, String toDt, int page);
}
