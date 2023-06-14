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
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StoreDaoImpl implements StoreDao {

  private StoreRepository storeRepository;

  public StoreDaoImpl (StoreRepository storeRepository) {
    this.storeRepository = storeRepository;
  }

  @Override
  public List<StoreDto> getStore(String storeName) {
    List<Store> storeList = null;
    List<StoreDto> storeDtoList = new LinkedList<>();

    if(storeName.equals(" ")) {
      storeList = storeRepository.findAllStore();
    } else {
      storeList = storeRepository.findLikeStoreName(storeName);
    }

    for(Store s : storeList) {
      storeDtoList.add(new StoreDto(s.getId()
          , s.getAccount().getId(), s.getAccount().getAccount()
          , s.getStoreName(), s.getTel(), s.getOperTime(), s.getLikeCnt(), s.getEtc()
          , s.getBiz().getBizNo(), s.getBiz().getBizName()
          , s.getAddress().getAddress1(), s.getAddress().getAddress2(), s.getAddress().getZipcode(), "default"));
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
          , s.getStorename(), s.getTel()
          , s.getOpertime() ,s.getLikecnt(), s.getEtc()
          , new Biz(s.getBizno(), s.getBizname())
          , new Address(s.getAddress1(), s.getAddress2(), s.getZipcode())));
    }

    storeRepository.saveAll(storeList);
  }

  @Override
  public void deleteStore(Map<StoreDto, Member> storeDtoList) {
    List<Store> storeList = new ArrayList<>();
    Iterator<StoreDto> keys = storeDtoList.keySet().iterator();

    while( keys.hasNext() ){
      StoreDto s = keys.next();

      storeList.add(new Store(s.getId()
          , storeDtoList.get(s)
          , s.getStorename(), s.getTel()
          , s.getOpertime() ,s.getLikecnt(), s.getEtc()
          , new Biz(s.getBizno(), s.getBizname())
          , new Address(s.getAddress1(), s.getAddress2(), s.getZipcode())));
    }

    storeRepository.deleteAll(storeList);
  }

  @Override
  public StoreDto getAccount(Member account) {
    List<Store> s = storeRepository.findByAccount(account);

    return new StoreDto(s.get(0).getId()
        , s.get(0).getAccount().getId(), s.get(0).getAccount().getAccount()
        , s.get(0).getStoreName(), s.get(0).getTel(), s.get(0).getOperTime(), s.get(0).getLikeCnt(), s.get(0).getEtc()
        , s.get(0).getBiz().getBizNo(), s.get(0).getBiz().getBizName()
        , s.get(0).getAddress().getAddress1(), s.get(0).getAddress().getAddress2(), s.get(0).getAddress().getZipcode(), "default");
  }

  @Override
  public Store getStores(Long storeId) {
    Optional<Store> storeWrapper = storeRepository.findById(storeId);
    return storeWrapper.orElse(null);
  }

}
