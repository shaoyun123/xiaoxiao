package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.App_KeChengKaoPing;

public interface App_KeChengKaoPingService {
	List<Map<String, Object>> getKaoPing(Integer shiJianKeChengId);
	List<Map<String, Object>> getKaoPing2(Integer shiJianKeChengId);
	App_KeChengKaoPing selectByPrimaryKey(Integer id);
	List<Map<String, Object>> selectByFenZu(Integer kechengid,Integer xueshengid);
	List<Map<String, Object>> selectByXueXiZu(Integer kechengid,Integer xueshengid);
	List<App_KeChengKaoPing> selectByKeChengIdAndXueShengID(Integer kechengid,Integer xueshengid);
	int insert(App_KeChengKaoPing record);
	int update(App_KeChengKaoPing record);
	int delete(Integer kaopingid);
	Map<String, Object> getkaopingMap(Integer id);
}
