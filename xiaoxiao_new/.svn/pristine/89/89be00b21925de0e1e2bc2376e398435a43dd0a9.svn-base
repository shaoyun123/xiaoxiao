package com.web.dao;

import java.util.List;

import com.web.model.SheTuanBuMenJiBenXinXin;

public interface SheTuanBuMenJiBenXinXinMapper {
    
	List<SheTuanBuMenJiBenXinXin> selectSheTuanBuMenJiBenXinXiBySheTuanID(int shetuanid);
	
	List<SheTuanBuMenJiBenXinXin> selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(int xueshengzuzhiid);
	
	SheTuanBuMenJiBenXinXin selectSheTuanBuMenJiBenXinXiBySheTuanIDAndMingCheng(int shetuanid,String mingcheng);
	
	SheTuanBuMenJiBenXinXin selectSheTuanBuMenJiBenXinXiByXueShengZuZhiIDAndMingCheng(int xueshengzuzhiid,String mingcheng);
	
	int deleteByPrimaryKey(Integer bumenid);

	int insertSheTuanBuMenJiBenXinXi(SheTuanBuMenJiBenXinXin record);

    int insertSelective(SheTuanBuMenJiBenXinXin record);

    SheTuanBuMenJiBenXinXin selectByPrimaryKey(Integer bumenid);

    int updateByPrimaryKeySelective(SheTuanBuMenJiBenXinXin record);

    int updateByPrimaryKey(SheTuanBuMenJiBenXinXin record);
    
    int updateSheTuanBuMenJiBenXinXiZhuangTaiByBuMenID(int bumenid);
}