package com.web.dao;

import java.util.List;

import com.web.model.SheTuanBuMenXinXin;

public interface SheTuanBuMenXinXinMapper {
	SheTuanBuMenXinXin selectSheTuanBuMenByBuMenID(int bumenid,String niandu);
	
	SheTuanBuMenXinXin selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(int shetuanbumenxinxiid);
	
	SheTuanBuMenXinXin selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(int bumenid, String niandu,String renyuanid_1, String renyuanid_2);
	
	int updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(SheTuanBuMenXinXin record);
	
	List<SheTuanBuMenXinXin> selectSheTuanBuMenXinXiByXueShengIDAndNianDu(String xueshengid1,String xueshengid2,String niandu);
	
	int insertSheTuanBuMenXinXi(SheTuanBuMenXinXin record);
	
	int updateSheTuanBuMenRenYuanByBuMenXinXiID(int bumenxinxiid, String renyuan);

}