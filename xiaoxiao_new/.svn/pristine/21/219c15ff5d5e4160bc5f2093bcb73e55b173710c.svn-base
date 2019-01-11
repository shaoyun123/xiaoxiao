package com.web.dao;

import java.util.List;

import com.web.model.JiaoShi;

public interface JiaoShiMapper {
    int deleteByPrimaryKey(Integer jiaoshiid);

    int insert(JiaoShi record);

    int insertSelective(JiaoShi record);

    JiaoShi selectByPrimaryKey(Integer jiaoshiid);

    int updateByPrimaryKeySelective(JiaoShi record);

    int updateByPrimaryKey(JiaoShi record);
    
    List<JiaoShi> getAllByxiaoQuID(Integer xiaoquid);
    
    List<JiaoShi> getAllByPrimaryKey(Integer jiaoXueLouId);
    
    JiaoShi selectByJiaoXueLouIdAndJiaoShiMing(Integer jiaoxuelouid,String jiaoshiming);
}