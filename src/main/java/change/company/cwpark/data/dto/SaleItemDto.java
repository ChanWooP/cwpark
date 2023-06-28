package change.company.cwpark.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemDto {

    private Long id;

    private Long storeId;

    private String storeName;

    private Long itemId;

    private String itemName;

    private Long saleId;

    private String saleDate;

    private int amt;

    private int qty;
}
