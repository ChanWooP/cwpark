package change.company.cwpark.data.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewVO {
  private Long storeId;
  private Long saleId;
  private String saleDate;
  private String image;
  private String contents;
  private int starCnt;
}
