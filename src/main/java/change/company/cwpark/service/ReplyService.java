package change.company.cwpark.service;

import change.company.cwpark.data.dto.ReplyDto;
import change.company.cwpark.data.dto.ReviewDto;
import change.company.cwpark.data.entity.Review;
import change.company.cwpark.data.entity.Store;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ReplyService {
  List<ReplyDto> getReply(Review review);
}
