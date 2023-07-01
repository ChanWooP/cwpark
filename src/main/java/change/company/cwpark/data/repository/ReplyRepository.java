package change.company.cwpark.data.repository;

import change.company.cwpark.data.entity.Reply;
import change.company.cwpark.data.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
  List<Reply> findByReview(Review review);
}
