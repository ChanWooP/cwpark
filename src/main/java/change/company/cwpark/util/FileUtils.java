package change.company.cwpark.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
  public static String singleFileUpload(MultipartFile fileList) throws IOException {
    Date nowDate = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    String saveFileName = simpleDateFormat.format(nowDate) + ".png";

    File file = new File("C://uploadFile/item" + saveFileName);

    if(!file.exists()){
      file.mkdirs(); //디렉토리가 존재하지 않는다면 생성
    }

    fileList.transferTo(file);

    return saveFileName;
  }
}
