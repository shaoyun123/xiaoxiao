package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.TiXing;

public interface TiXingService {
	
	int insert(TiXing record);
	
	List<TiXing> getAllByjieShouRenID(Integer jieshourenid);
	
	List<TiXing> getAllByjieShouRenIDAndLimit(Map<String, Integer> map);
	
	TiXing selectByPrimaryKey(Integer id);
	
	int updatezhuangTaiByPrimaryKey(TiXing record);
	
	int deleteByPrimaryKey(Integer id);
	
	int getCountByjieShouRenID(Integer jieshourenid);

	List<TiXing> getTiXingByjieShouRenIDAndRiQi(Map<String, String> paramMap);

}
