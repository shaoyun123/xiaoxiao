package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.SheTuanJiBenXinXi;

public interface SheTuanJiBenXinXiMapper {
	List<SheTuanJiBenXinXi> selectSheTuanJiBenXinXiByXueXiaoID(int xuexiaoid);
	
	List<SheTuanJiBenXinXi> selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(Map<String, String> map);
	
	SheTuanJiBenXinXi selectSheTuanJiBenXinXiByXueXiaoIDAndSheTuanID(Map<String, String> map);
	
	SheTuanJiBenXinXi selectSheTuanJiBenXinXiBySheTuanID(int shetuanid);
	
	SheTuanJiBenXinXi selectSheTuanJiBenXinXiByXueXiaoIDAndMingCheng(int xuexiaoid,String mingcheng);
	
	int insertSheTuanJiBenXinXi(SheTuanJiBenXinXi record);
	
	int updateSheTuanJiBenXinXi(SheTuanJiBenXinXi record);
}