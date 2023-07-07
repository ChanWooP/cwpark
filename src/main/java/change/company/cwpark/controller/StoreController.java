package change.company.cwpark.controller;

import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.vo.StoreVO;
import change.company.cwpark.service.StoreService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/view")
    public String viewStore(@RequestBody String storeName) {
        List<StoreDto> storeDtoList = storeService.getStore(storeName);
        Gson gson = new Gson();
        String storeJson = gson.toJson(storeDtoList);

        return storeJson;
    }

    @ResponseBody
    @PostMapping("/save")
    public void saveStore(@RequestBody StoreVO[] StoreVOs, Model model) {
        List<StoreDto> storeDtoList = new ArrayList<>();

        for(StoreVO s : StoreVOs) {
            storeDtoList.add(new StoreDto(s.getId()
                , s.getAccountid(), s.getAccount()
                , s.getStorename(), s.getTel(), s.getOpertime(), s.getLikecnt(), s.getEtc()
                , s.getBizno(), s.getBizname()
                , s.getAddress1(), s.getAddress2(), s.getZipcode(), s.getColStatus()));
        }

        storeService.saveStore(storeDtoList);
    }

    @ResponseBody
    @GetMapping("/gets/address")
    public String getStoreAddress() {
        List<StoreDto> storeDtoList = storeService.getStoreAddress("경기");
        Gson gson = new Gson();
        String storeJson = gson.toJson(storeDtoList);

        return storeJson;
    }
}
