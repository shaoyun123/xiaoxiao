package com.web.dao;

import java.util.List;

import com.web.model.NaXin;

public interface NaXinMapper {
    int deleteByPrimaryKey(Integer naxingid);

    int insertNaXin(NaXin record);

    int insertSelective(NaXin record);

    NaXin selectByPrimaryKey(Integer naxingid);
    
    NaXin selectNaXinByXueShengIDAndSheTuanXinXiID(int xueshengid,int shetuanxinxiid);
    
    NaXin selectNaXinByXueShengIDAndXueShengZuZhiXinXiID(int xueshengid,int xueshengzuzhixinxiid);
    
    List<NaXin> selectNaXinBySheTuanXinXiID(int shetuanxinxiid);
	
	List<NaXin> selectNaXinByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid);

    int updateByPrimaryKeySelective(NaXin record);
    
    int updateNaXinZhuangTaiByNaXinID(int zhuangtai,int naxinid);

    int updateByPrimaryKey(NaXin record);
}