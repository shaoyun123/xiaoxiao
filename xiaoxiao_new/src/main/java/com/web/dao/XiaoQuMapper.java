package com.web.dao;

import java.util.List;

import com.web.model.XiaoQu;

public interface XiaoQuMapper {
    int deleteByPrimaryKey(Integer xiaoquid);

    int insertXiaoQu(XiaoQu record);

    int insertSelective(XiaoQu record);

    XiaoQu selectByPrimaryKey(Integer xiaoquid);

    int updateByPrimaryKeySelective(XiaoQu record);

    int updateByPrimaryKey(XiaoQu record);
    
    List<XiaoQu> getAllByxueXiaoID(Integer xuexiaoid);
 
}