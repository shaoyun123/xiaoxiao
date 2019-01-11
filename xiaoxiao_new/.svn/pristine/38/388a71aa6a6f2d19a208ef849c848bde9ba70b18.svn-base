package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.XueQi;

public interface XueQiService {
	XueQi getByxueXiaoIDandriQi(Map<String, String> map);
	
	XueQi getByXueXiaoIDAndXueNianAndXueQi(Map<String, String> map);
	
	List<XueQi> getXueQiByXueXiaoID(int xuexiaoid);
	
	XueQi selectByID(int id);
	
	int insert(XueQi record);
	
	int updateByPrimary(XueQi record);
	
	int deleteByPrimaryKey(Integer xueqiid);

	List<String> getXuenianByXuexiaoID(int parseInt);

	XueQi getByXueQiID(int parseInt);

	List<XueQi> findByXueXiaoID(String xueXiaoID);

	XueQi getByXueXiaoIDAndXueQiID(int parseInt, Integer xueqiid);

	List<String> getXueqiByXuexiaoID(int parseInt);
	
	List<String> getXueNian(int xuexiaoid);

	Map<String, Object> getMapXueQiByxueXiaoIDandriQi(Map<String, String> map);

	Map<String, Object> getMapXueQiByXueXiaoIDAndXueNianAndXueQi(Map<String, String> map);

	List<Map<String, Object>> getMapXueQiByXueXiaoID(Map<String, String> m);

	List<Map<String, Object>> getNewerXueQi(Map<String, String> map);

	List<XueQi> getNewerXueQiByXueQi(Map<String, String> m);

}
