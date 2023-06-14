package change.company.cwpark.service.Impl;

import change.company.cwpark.service.ItemService;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemServiceImpl implements ItemService {

  @Override
  public void saveItem(List<Map<String, Object>> param, List<MultipartFile> fileList) {
    for(Map<String, Object> m : param) {
    }
  }
}
