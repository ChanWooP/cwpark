package change.company.cwpark.service.Impl;

import change.company.cwpark.data.dao.MemberDao;
import change.company.cwpark.data.dao.StoreDao;
import change.company.cwpark.data.dto.MemberDto;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.entity.Member;
import change.company.cwpark.service.StoreService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreDao storeDao;
    private final MemberDao memberDao;

    @Autowired
    public StoreServiceImpl(StoreDao storeDao, MemberDao memberDao) {
        this.storeDao = storeDao;
        this.memberDao = memberDao;
    }

    @Override
    public List<StoreDto> getStore(String storeName) {
        return storeDao.getStore(storeName);
    }

    @Override
    public void saveStore(List<StoreDto> storeDtoList) {
        Map<StoreDto, Member> map = new HashMap<>();

        for(StoreDto s : storeDtoList) {
            Member member = memberDao.saveMember(new Member(s.getAccountid(), s.getStoreName(), s.getAccount()
            ,s.getAccount(), null, 0));

            map.put(s, member);
        }

        storeDao.saveStore(map);
    }
}
