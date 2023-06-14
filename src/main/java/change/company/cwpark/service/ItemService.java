package change.company.cwpark.service;

import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {
  void saveItem(List<Map<String, Object>> param, List<MultipartFile> fileList);
}
