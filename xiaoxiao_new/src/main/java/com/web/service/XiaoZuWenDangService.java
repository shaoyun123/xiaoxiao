package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.XiaoZuWenDang;

public interface XiaoZuWenDangService {
	XiaoZuWenDang selectByPrimaryKey(Integer id);
	XiaoZuWenDang getXiaoZuWenDang(Integer wendangid,Integer xiaozuid);
	List<XiaoZuWenDang> getAllByWenDangID(Integer wendangid);
	List<XiaoZuWenDang> getAllByXiaoZuID(Integer xiaozuid);
	List<Map<String, Object>> getAllByKeChengIDAndXiaoZuID(Integer kechengid,Integer xiaozuid);
	List<Map<String, Object>> getAllByKeChengID(Integer kechengid);
	int insert(XiaoZuWenDang record);
	int updateByPrimaryKey(XiaoZuWenDang record);
	int delete(Integer zuwendangid);
}
