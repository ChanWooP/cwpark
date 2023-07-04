package change.company.cwpark.data.dao.Impl;

import change.company.cwpark.data.dao.ReplyDao;
import change.company.cwpark.data.dao.ReviewDao;
import change.company.cwpark.data.entity.Reply;
import change.company.cwpark.data.entity.Review;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.repository.ReplyRepository;
import change.company.cwpark.data.repository.ReviewRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReplyDaoImpl implements ReplyDao {

  @Autowired
  private final ReplyRepository replyRepository;

  public ReplyDaoImpl(ReplyRepository replyRepository) {
    this.replyRepository = replyRepository;
  }

  @Override
  public List<Reply> getReply(Review review) {
    return replyRepository.findByReview(review);
  }

  @Override
  public void saveReply(List<Reply> reply) {
    replyRepository.saveAll(reply);
  }
}
