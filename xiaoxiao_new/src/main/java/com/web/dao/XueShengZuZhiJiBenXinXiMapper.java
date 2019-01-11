package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.XueShengZuZhiJiBenXinXi;

public interface XueShengZuZhiJiBenXinXiMapper {
	List<XueShengZuZhiJiBenXinXi> selectXueShengZuZhiJiBenXinXiByXueXiaoID(int xuexiaoid);
	
	List<XueShengZuZhiJiBenXinXi> selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(Map<String, String> map);
	
	XueShengZuZhiJiBenXinXi selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndXueShengZuZhiID(Map<String, String> map);
	
	XueShengZuZhiJiBenXinXi selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(int xueshengzuzhiid);
	
	int insertXueShengZuZhiJiBenXinXi(XueShengZuZhiJiBenXinXi record);
	
	int updateXueShengZuZhiJiBenXinXi(XueShengZuZhiJiBenXinXi record);
	
}