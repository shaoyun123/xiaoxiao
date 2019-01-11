package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.App_KaoPingJieGuo;

public interface App_KaoPingJieGuoService {
	App_KaoPingJieGuo selectByPrimaryKey(Integer id);
	int insert(App_KaoPingJieGuo record);
	List<App_KaoPingJieGuo> getJieGuo(Integer kaopingid);
	int update (App_KaoPingJieGuo record);
	App_KaoPingJieGuo selectJieGuoByXiaoZuId(Integer kaopingid,Integer xiaozuid);
	List<Map<String, Object>> getJieGuoByKaoPingIDAndXiaoZuID(Map<String, Object>map);
}
