package com.web.dao;

import java.util.List;

import com.web.model.FuDaoYuan;

public interface FuDaoYuanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FuDaoYuan record);

    int insertSelective(FuDaoYuan record);

    FuDaoYuan selectByPrimaryKey(Integer id);
    
   List<FuDaoYuan> getBybanJiID(String banjiid1,String banjiid2);//根据班级id查辅导员，1*2
    
    FuDaoYuan getByfuDaoYuanID(Integer fudaoyuanid);

    int updateByPrimaryKeySelective(FuDaoYuan record);

    int updateByPrimaryKey(FuDaoYuan record);
    
    List<FuDaoYuan> getAllByshuJiID(Integer shujiid);
    
}