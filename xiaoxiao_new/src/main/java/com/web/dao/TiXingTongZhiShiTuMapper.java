package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.TiXingTongZhiShiTu;;

public interface TiXingTongZhiShiTuMapper {
	List<TiXingTongZhiShiTu> selectAllByjieShouRenIDandNewTarget(Map<String, Object> map);
	
	int getCountByjieShouRenIDandNewTarget(Map<String, Object> map);
	
	List<TiXingTongZhiShiTu> selectAllByjieShouRenIDandNewTargetandLimit(Map<String, Object> map);
}
