package change.company.cwpark.data.dao.Impl;

import change.company.cwpark.data.dao.StoreDao;
import change.company.cwpark.data.dto.MemberDto;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.emb.Address;
import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.repository.StoreRepository;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class StoreDaoImpl implements StoreDao {

  private StoreRepository storeRepository;

  public StoreDaoImpl (StoreRepository storeRepository) {
    this.storeRepository = storeRepository;
  }

  @Override
  public List<StoreDto> getStore(String storeName) {
    List<Store> storeList = storeRepository.findByStoreNameContaining(storeName);
    List<StoreDto> storeDtoList = new LinkedList<>();

    for(Store s : storeList) {
      storeDtoList.add(new StoreDto(s.getId()
          , new MemberDto(s.getAccount().getName(),s.getAccount().getAccount(), s.getAccount().getPassword()
          , s.getAccount().getLastAccessDt(), s.getAccount().getLoginFailCnt())
          , s.getStoreName(), s.getRepreName()
          , s.getTel(), s.getOperTime(), s.getLikeCnt(), s.getEtc(), s.getAddress().getAddress1()
          , s.getAddress().getAddress2()));
    }

    return storeDtoList;
  }

  @Override
  public void saveStore(List<StoreDto> storeDtoList) {
    List<Store> storeList = new ArrayList<>();

    for(StoreDto s : storeDtoList) {
      storeList.add(new Store(s.getId()
          , new Member(s.getAccount().getName(), s.getAccount().getAccount(),s.getAccount().getPassword()
          , s.getAccount().getLastAccessDt() ,s.getAccount().getLoginFailCnt())
          , s.getStoreName(), s.getRepreName(), s.getTel()
          , s.getOperTime() ,s.getLikeCnt(), s.getEtc()
          , new Address(s.getAddress1(), s.getAddress2())));
    }

    storeRepository.saveAll(storeList);
  }

  @Override
  public void deleteStore(Store menu) {

  }
}
