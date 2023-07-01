package change.company.cwpark.data.dao;

import change.company.cwpark.data.entity.Reply;
import change.company.cwpark.data.entity.Review;
import change.company.cwpark.data.entity.Store;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ReplyDao {
  List<Reply> getReply(Review review);
}
