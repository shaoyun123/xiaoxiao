package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.TiXing;

public interface TiXingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TiXing record);

    int insertSelective(TiXing record);

    TiXing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TiXing record);

    int updatezhuangTaiByPrimaryKey(TiXing record);
    
    List<TiXing> getAllByjieShouRenID(Integer jieshourenid);
    
    List<TiXing> getAllByjieShouRenIDAndLimit(Map<String, Integer> map);
    
    int getCountByjieShouRenID(Integer jieshourenid);

	List<TiXing> getTiXingByjieShouRenIDAndRiQi(Map<String, String> paramMap);
}