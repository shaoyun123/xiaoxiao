package com.web.dao;

import java.util.List;

import com.web.model.FDAP;

public interface FDAPMapper {
    int deleteByPrimaryKey(Integer anpaiid);

    int insert(FDAP record);

    int insertSelective(FDAP record);

    String getmingChengByanPaiID(Integer anpaiid);
    
    String getyaoQiuByanPaiID(Integer anpaiid);
    
    String getjieZhiShiJianByanPaiID(Integer anpaiid);
    
    int updateByPrimaryKeySelective(FDAP record);

    int updateByPrimaryKey(FDAP record);
    
    FDAP getByanPaiID(Integer anpaiid);
    
    List<FDAP> getAllByfuDaoYuanID(Integer fudaoyuanid);
}