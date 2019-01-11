package com.web.service;

import java.util.List;

import com.web.model.SheTuanHuoDongXinXi;

public interface SheTuanHuoDongService {
	
	List<SheTuanHuoDongXinXi> selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(int shetuanid,int start,int stop);
	
	List<SheTuanHuoDongXinXi> selectSheTuanHuoDongXinXisBySheTuanID(int shetuanid);
	
	List<SheTuanHuoDongXinXi> selectSheTuanHuoDongXinXisByXueShengZuZhiIDAndLimit(int xueshengzuzhiid,int start,int stop);
	
	List<SheTuanHuoDongXinXi> selectSheTuanHuoDongXinXisByXueShengZuZhiID(int xueshengzuzhiid);
	
	SheTuanHuoDongXinXi selectByID(int id);
	
	int updateSheTuanHuoDongXinXiBySheTuanHuoDongXinXi(SheTuanHuoDongXinXi record);
	
	int insertSheTuanHuoDongXinXi(SheTuanHuoDongXinXi record);
	
	int getCountBySheTuanID(int shetuanid);
	
	int getCountByXueShengZuZhiID(int xueshengzuzhiid);
}
