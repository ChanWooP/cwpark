package change.company.cwpark.controller;

import change.company.cwpark.data.dto.MemberDto;
import change.company.cwpark.data.dto.MenuDto;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.vo.MenuVO;
import change.company.cwpark.data.vo.StoreVO;
import change.company.cwpark.service.MenuService;
import change.company.cwpark.service.StoreService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/store")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/")
    public String getAllMenu() {
        return "pages/store";
    }

    @ResponseBody
    @GetMapping("/view")
    public String viewMenu(@RequestBody String storeName) {
        List<StoreDto> storeDtoList = storeService.getStore(storeName);
        Gson gson = new Gson();
        String storeJson = gson.toJson(storeDtoList);

        return storeJson;
    }

    @ResponseBody
    @PostMapping("/save")
    public String saveMenu(@RequestBody StoreVO[] StoreVOs, Model model) {
        List<StoreDto> storeDtoList = new ArrayList<>();

        for(StoreVO s : StoreVOs) {
            storeDtoList.add(new StoreDto(s.getId()
                , new MemberDto(s.getAccount().getName(),s.getAccount().getAccount(), s.getAccount().getPassword()
                , s.getAccount().getLastAccessDt(), s.getAccount().getLoginFailCnt())
                , s.getStoreName(), s.getTel(), s.getOperTime(), s.getLikeCnt(), s.getEtc()
                , s.getBizNo(), s.getBizName()
                , s.getAddress1(), s.getAddress2(), s.getZipcode()));
        }

        storeService.saveStore(storeDtoList);

        return "저장되었습니다.";
    }
}
