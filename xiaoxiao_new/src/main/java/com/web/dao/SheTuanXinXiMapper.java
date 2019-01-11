package com.web.dao;

import java.util.List;

import com.web.model.SheTuanXinXi;

public interface SheTuanXinXiMapper {
	SheTuanXinXi selectSheTuanXinXiBySheTuanID(int shetuanid);
	
	SheTuanXinXi selectSheTuanXinXiBySheTuanIDAndNianDu(int shetuanid,String niandu);
	
	SheTuanXinXi selectSheTuanXinXiBySheTuanXinXiIDAndXueShengID(int shetuanxinxiid,String sheyuanid1,String sheyuanid2s);
	
	List<SheTuanXinXi> selectSheTuanXinXiByXueShengIDAndNianDuAndXueXiaoID(String sheyuanid1,String sheyuanid2,String niandu,int xuexiaoid);
	
	SheTuanXinXi selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(int shetuanxinxiid, int shezhang);
	
	int updateSheZhangBySheTuanXinXiID(int shetuanxinxiid, int shezhangid);
	
	SheTuanXinXi selectSheTuanXinXiBySheTuanXinXiID(int shetuanxinxiid);
	
	int updateSheTuanRenYuanBySheTuanXinXiID(int shetuanxinxiid, String renyuan);
	
	int updateSheTuanXinXi(SheTuanXinXi record);
	
	int insertSheTuanXinXi(SheTuanXinXi record);
	
	SheTuanXinXi selectSheTuanXinXiBySheTuanIDAndNianDu(int shetuanid, int niandu);
}