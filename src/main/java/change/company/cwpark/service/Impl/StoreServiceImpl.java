package change.company.cwpark.service.Impl;

import change.company.cwpark.data.api.StoreAPI;
import change.company.cwpark.data.dao.MemberDao;
import change.company.cwpark.data.dao.StoreDao;
import change.company.cwpark.data.dao.StoreOpenDao;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.emb.Address;
import change.company.cwpark.data.emb.Biz;
import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.entity.StoreOpen;
import change.company.cwpark.service.StoreService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreDao storeDao;
    private final MemberDao memberDao;
    private final StoreOpenDao storeOpenDao;

    @Autowired
    public StoreServiceImpl(StoreDao storeDao, MemberDao memberDao, StoreOpenDao storeOpenDao) {
        this.storeDao = storeDao;
        this.memberDao = memberDao;
        this.storeOpenDao = storeOpenDao;
    }

    @Override
    public List<StoreDto> getStore(String storeName) {
        return storeDao.getStore(storeName);
    }

    @Override
    public void saveStore(List<StoreDto> storeDtoList) {
        Map<StoreDto, Member> map1 = new HashMap<>();
        Map<StoreDto, Member> map2 = new HashMap<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        for(StoreDto s : storeDtoList) {
            Member member = memberDao.saveMember(new Member(s.getAccountid(), s.getStorename(), s.getAccount()
            ,passwordEncoder.encode(s.getAccount()), null, 0));

            if(s.getColStatus().equals("D")) {
                map2.put(s, member);
            } else {
                if(s.getId() != null) {
                    map1.put(s, member);
                } else {
                    Store store = storeDao.saveStoreOne(new Store(s.getId()
                        , member
                        , s.getStorename(), s.getTel()
                        , s.getOpertime() ,s.getLikecnt(), s.getEtc()
                        , new Biz(s.getBizno(), s.getBizname())
                        , new Address(s.getAddress1(), s.getAddress2(), s.getZipcode())));

                    storeOpenDao.saveStore(new StoreOpen(null, store, 0));
                }
            }
        }

        storeDao.saveStore(map1);
        storeDao.deleteStore(map2);
    }

    @Override
    public List<StoreAPI> getStoreAPI(String address1) {
        List<Store> list = storeDao.getStoreAPI(address1);
        List<StoreAPI> rtnList = new ArrayList<>();

        for(Store s : list) {
            rtnList.add(new StoreAPI(s.getId()
                , s.getStoreName(), s.getTel(), s.getOperTime(), s.getLikeCnt(), s.getEtc()
                , s.getBiz().getBizNo(), s.getBiz().getBizName()
                , s.getAddress().getAddress1(), s.getAddress().getAddress2(), s.getAddress().getZipcode()));
        }

        return rtnList;
    }
}
