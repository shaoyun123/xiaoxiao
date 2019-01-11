package com.web.dao;

import java.util.List;

import com.web.model.SheTuanHuoDongXinXi;

public interface SheTuanHuoDongXinXiMapper {
	
	List<SheTuanHuoDongXinXi> selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(int shetuanid,int start,int stop);
	
	List<SheTuanHuoDongXinXi> selectSheTuanHuoDongXinXisBySheTuanID(int shetuanid);
	
	List<SheTuanHuoDongXinXi> selectSheTuanHuoDongXinXisByXueShengZuZhiIDAndLimit(int xueshengzuzhiid,int start,int stop);
	
	List<SheTuanHuoDongXinXi> selectSheTuanHuoDongXinXisByXueShengZuZhiID(int xueshengzuzhiid);
	
	int deleteByPrimaryKey(Integer huodongid);

    int insert(SheTuanHuoDongXinXi record);

    SheTuanHuoDongXinXi selectByPrimaryKey(Integer huodongid);

    int updateByPrimaryKey(SheTuanHuoDongXinXi record);
    
    int getCountBySheTuanID(int shetuanid);
    
    int getCountByXueShengZuZhiID(int xueshengzuzhiid);
}