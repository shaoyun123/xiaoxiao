package com.web.dao;

import java.util.List;

import com.web.model.PingFenFangAn;

public interface PingFenFangAnMapper {
    int deleteByPrimaryKey(Integer fanganid);

    int insert(PingFenFangAn record);

    int insertSelective(PingFenFangAn record);

    PingFenFangAn selectByPrimaryKey(Integer fanganid);
    
    List<PingFenFangAn> selectPingFenFangAnSByXueXiaoID(int xuexiaoid);

    int updateByPrimaryKeySelective(PingFenFangAn record);

    int updateByPrimaryKey(PingFenFangAn record);
}