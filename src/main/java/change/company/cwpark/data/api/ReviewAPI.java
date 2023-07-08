package change.company.cwpark.data.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewAPI {
  private Long id;
  private String saleDate;
  private String contents;
  private String image;
  private int starCnt;
  private ReplyAPI replyAPI;
}
