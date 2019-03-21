package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.App_ShiJianTi;

public interface App_ShiJianTiService {
	int insert(App_ShiJianTi record);
	int delete(Integer id);
	int update(App_ShiJianTi record);
	App_ShiJianTi selectByPrimaryKey(Integer id);
	List<Map<String, Object>>getShiJianTiByShiXiId(Integer shixiid);
	List<Map<String, Object>> getKaoTiByLaoShiId(Integer laoshiid,Integer shixiid);
	List<App_ShiJianTi> getKaoTiList(Integer shixiid);
}
