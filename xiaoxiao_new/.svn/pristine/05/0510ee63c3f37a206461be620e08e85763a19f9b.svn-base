package com.web.dao;

import java.util.List;

import com.web.model.XueShengZuZhiXinXi;

public interface XueShengZuZhiXinXiMapper {
	XueShengZuZhiXinXi selectXueShengZuZhiXinXiByXueShengZuZhiID(int xueshengzuzhiid);
	
	XueShengZuZhiXinXi selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(int xueshengzuzhiid, String niandu);
	
	List<XueShengZuZhiXinXi> selectXueShengZuZhiXinXiByXueShengIDAndNianDu(String renyuanid1,String renyuanid2,String niandu);
	
	XueShengZuZhiXinXi selectByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid);
	
	int updateFuZeRenByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid, int fuzerenid);
	
	XueShengZuZhiXinXi selectXueShengZuZhiXinXiByIDAndXueShengID(int xueshengzuzhixinxiid, String sheyuanid1,String sheyuanid2);

	int updateXueShengZuZhiXinXi(XueShengZuZhiXinXi xi);
	
	int insertXueShengZuZhiXinXi(XueShengZuZhiXinXi record);
}