package com.web.dao;


import java.util.List;
import java.util.Map;

import com.web.model.XueQi;

public interface XueQiMapper {
    int deleteByPrimaryKey(Integer xueqiid);

    int insert(XueQi record);

    XueQi selectByPrimaryKey(Integer xueqiid);

    int updateByPrimaryKey(XueQi record);
    
    XueQi getByxueXiaoIDandriQi(Map<String, String> map);
    
    XueQi getByXueXiaoIDAndXueNianAndXueQi(Map<String, String> map);
    
    List<XueQi> getXueQiByXueXiaoID(int xuexiaoid);

	List<String> getXuenianByXuexiaoID(int xuexiaoid);

	XueQi getByXueQiID(int xueqiid);

	List<XueQi> findByXueXiaoID(int xuexiaoid);

	XueQi getByXueXiaoIDAndXueQiID(int parseInt, Integer xueqiid);

	List<String> getXueqiByXuexiaoID(int xuexiaoid);
	
	List<String> getXueNian(int xuexiaoid);

	Map<String, Object> getMapXueQiByxueXiaoIDandriQi(Map<String, String> map);

	Map<String, Object> getMapXueQiByXueXiaoIDAndXueNianAndXueQi(Map<String, String> map);

	List<Map<String, Object>> getMapXueQiByXueXiaoID(Map<String, String> map);

	List<Map<String, Object>> getNewerXueQi(Map<String, String> map);

	List<XueQi> getNewerXueQiByXueQi(Map<String, String> m);
    
}