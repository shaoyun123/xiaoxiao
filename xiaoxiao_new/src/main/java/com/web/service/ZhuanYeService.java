package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.ZhuanYe;

public interface ZhuanYeService {
	ZhuanYe selectZhuanYeByDaiMa(String daima);
	
	List<ZhuanYe> getZhuanYe();
	
	int deleteByPrimaryKey(String daima);

	ZhuanYe selectByPrimaryKey(Integer zhuanyeid);
	
	List<ZhuanYe> getXueKeMenLei(String mingcheng);
	
	List<ZhuanYe> getXueKe(String daima,String mingcheng1,String mingcheng2);
	
	List<ZhuanYe> selectZhuanYeByXueKe(String daima,String mingcheng1,String mingcheng2);
	
	int deleteYuanXiZhuanYeByPrimaryKey(Integer id);

    int insertYuanXiZhuanYe(Map<String, Integer> map);

    int insertYuanXiZhuanYeSelective(Map<String, Integer> map);

    Map<String, Integer> selectYuanXiZhuanYeByPrimaryKey(Integer id);
    
    Map<String, Integer> selectByZhuanYeId(Integer zhuanyeid, String yuanXiId);

    int updateYuanXiZhuanYeByPrimaryKeySelective(Map<String, Integer> map);

    int updateYuanXiZhuanYeByPrimaryKey(Map<String, Integer> map);
    
    List<Map<String, Integer>> getAllByYuanXiID(Integer yuanxiid);

	List<ZhuanYe> findByXueXiaoID(String xuexiaoid);
}
