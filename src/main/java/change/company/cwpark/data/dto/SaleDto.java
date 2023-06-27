package change.company.cwpark.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto {

    private Long id;

    private Long storeId;

    private String storeName;

    private String saleDate;

    private int amt;

    private int qty;
}
