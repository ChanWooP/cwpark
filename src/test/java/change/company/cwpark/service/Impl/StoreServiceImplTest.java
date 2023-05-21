package change.company.cwpark.service.Impl;

import static org.mockito.Mockito.verify;

import change.company.cwpark.data.dao.Impl.MenuDaoImpl;
import change.company.cwpark.data.dao.Impl.StoreDaoImpl;
import change.company.cwpark.data.dto.MenuDto;
import change.company.cwpark.data.entity.Menu;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.service.StoreService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StoreServiceImplTest {
    @Mock
    StoreDaoImpl storeDao;

    @InjectMocks
    StoreService storeService;

    @Test
    @DisplayName("점포 조회")
    public void getStore() {
        // given
        List<Store> storeList = new ArrayList<>();
    }
}
