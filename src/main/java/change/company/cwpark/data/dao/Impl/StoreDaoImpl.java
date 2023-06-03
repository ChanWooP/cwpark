package change.company.cwpark.data.dao.Impl;

import change.company.cwpark.data.dao.StoreDao;
import change.company.cwpark.data.dto.MemberDto;
import change.company.cwpark.data.dto.StoreDto;
import change.company.cwpark.data.emb.Address;
import change.company.cwpark.data.emb.Biz;
import change.company.cwpark.data.entity.Member;
import change.company.cwpark.data.entity.Store;
import change.company.cwpark.data.repository.StoreRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
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
          , s.getAccount().getId(), s.getAccount().getAccount()
          , s.getStoreName(), s.getTel(), s.getOperTime(), s.getLikeCnt(), s.getEtc()
          , s.getBiz().getBizNo(), s.getBiz().getBizName()
          , s.getAddress().getAddress1(), s.getAddress().getAddress2(), s.getAddress().getZipcode()));
    }

    return storeDtoList;
  }

  @Override
  public void saveStore(Map<StoreDto, Member> storeDtoList) {
    List<Store> storeList = new ArrayList<>();
    Iterator<StoreDto> keys = storeDtoList.keySet().iterator();

    while( keys.hasNext() ){
      StoreDto s = keys.next();

      storeList.add(new Store(s.getId()
          , storeDtoList.get(s)
          , s.getStoreName(), s.getTel()
          , s.getOperTime() ,s.getLikeCnt(), s.getEtc()
          , new Biz(s.getBizNo(), s.getBizName())
          , new Address(s.getAddress1(), s.getAddress2(), s.getZipcode())));
    }

    storeRepository.saveAll(storeList);
  }

  @Override
  public void deleteStore(Store menu) {

  }
}
