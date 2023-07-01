package change.company.cwpark.data.dao;

import change.company.cwpark.data.entity.Review;
import change.company.cwpark.data.entity.Sale;
import change.company.cwpark.data.entity.Store;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ReviewDao {
  Page<Review> getReview(Store store, String frDt, String toDt, int page);
}
