package com.web.dao;

import java.util.List;

import com.web.model.YuanXi;

public interface YuanXiMapper {
    int deleteByPrimaryKey(Integer yuanxiid);

    int insert(YuanXi record);

    int insertSelective(YuanXi record);

    YuanXi selectByPrimaryKey(Integer yuanxiid);
    
    YuanXi selectByYuanXiMingChengAndXueXiaoId(String yuanximingcheng,Integer xuexiaoid);

    int updateByPrimaryKeySelective(YuanXi record);

    int updateByPrimaryKey(YuanXi record);
    
    List<YuanXi> getAllByxueXiaoID(Integer xuexiaoid);
}