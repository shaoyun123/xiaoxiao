package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.App_KaoPingChouQian;

public interface App_KaoPingChouQianService {
	App_KaoPingChouQian selectByPrimaryKey(Integer id);
	App_KaoPingChouQian selectByPrimaryKey1(Integer kaopingid,Integer fenzuid);
	int insert(App_KaoPingChouQian record);
	int updateByPrimaryKey(App_KaoPingChouQian record);
	Map<String, Object> selectchouqian(Map<String, Object> map);
	App_KaoPingChouQian selectchouqian2(Map<String, Object> map);
	List<App_KaoPingChouQian> getchouqian(Map<String, Object>map);
}
