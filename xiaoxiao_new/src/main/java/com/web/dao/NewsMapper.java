package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.TongZhi;

public interface NewsMapper {
//	List<TongZhi> getNewsByParam(Map<String,Object> paramMap);
	List<Map<String,Object>> getNewsByParam(Map<String,Object> paramMap);
	
	void updateNewsByParam(Map<String,Object> paramMap);

	List<TongZhi> getNewsByParamID(Map<String, Object> paramMap);

	List<Map<String, Object>> getNewsLeiXingByParam(Map<String, Object> paramMap);
}
