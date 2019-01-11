package com.web.dao;


import java.util.List;
import java.util.Map;

import com.web.model.KeMu;

public interface KeMuMapper {
	
	   int deleteByPrimaryKey(String kemuid);

	    int insert(KeMu record);

	    int insertSelective(KeMu record);

	    KeMu selectByPrimaryKey(String kemuid);

	    int updateByPrimaryKeySelective(KeMu record);

	    int updateByPrimaryKey(KeMu record);
	    
	    List<Map<String, Object>> selectKeMuByXueXiaoId(Integer xuexiaoid);
	    
	    int insertXueXiaoKeMu(Map<String, Integer> map);
	    
	    int updateXueXiaoKeMu(Integer id,Integer zhuangtai);
	    
	    Map<String, Integer> selectXueXiaoKeMu(Integer id);
	    
	    int deleteXueXiaoKeMu(Integer id);

		List<KeMu> selectAllKeMu();

		int getOne(Map<String, Integer> map);

		int getKeMu(KeMu kemu);

}
