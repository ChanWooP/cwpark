package change.company.cwpark.data.dto;

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
public class ReviewDto {

    private Long id;

    private Long storeId;

    private String storeName;

    private Long saleId;

    private String saleDate;

    private String image;

    private String contents;
}
