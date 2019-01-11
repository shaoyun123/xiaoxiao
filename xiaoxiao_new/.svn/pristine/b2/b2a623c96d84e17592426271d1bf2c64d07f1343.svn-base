package com.web.dao;

import java.util.List;

import com.web.model.SheTuanChuangJian;

public interface SheTuanChuangJianMapper {
	SheTuanChuangJian selectSheTuanChuangJianByXueXiaoIDAndMingCheng(int xuexiaoid,String mingcheng);
	
    int deleteByPrimaryKey(Integer chuangjianid);

    int insertSheTuanChuangJian(SheTuanChuangJian record);

    int insertSelective(SheTuanChuangJian record);

    SheTuanChuangJian selectByPrimaryKey(Integer chuangjianid);

    int updateByPrimaryKeySelective(SheTuanChuangJian record);

    int updateByPrimaryKey(SheTuanChuangJian record);
    
    List<SheTuanChuangJian> selectSheTuanChuangJianByXueShengID(int xueshengid);
    
    List<SheTuanChuangJian> selectSheTuanChuangJianByBanJiIDs(String banjiids);
    
    List<SheTuanChuangJian> selectSheTuanChuangJianByXueXiaoID(int xuexiaoid);
}