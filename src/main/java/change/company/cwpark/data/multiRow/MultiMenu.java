package change.company.cwpark.data.multiRow;

import change.company.cwpark.data.dto.MenuDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MultiMenu {
    private List<MenuDto> menus;

}
