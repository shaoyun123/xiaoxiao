package com.web.dao;

import java.util.List;

import com.web.model.YiJianHuiFu;

public interface YiJianHuiFuMapper {
    int deleteByPrimaryKey(Integer huifuid);

    int insert(YiJianHuiFu record);//评论人为学生，直接评论，无被回复人
    
    int insert2(YiJianHuiFu record);//回复人为学生，被回复人为教师
    
    int insert3(YiJianHuiFu record);//回复人为学生，被回复人为学生
    
    int insert4(YiJianHuiFu record);//评论人为教师，直接评论，无被回复人
    
    int insert5(YiJianHuiFu record);//回复人为教师，被回复人为教师
    
    int insert6(YiJianHuiFu record);//回复人为教师，被回复人为学生

    int insertSelective(YiJianHuiFu record);

    YiJianHuiFu selectByPrimaryKey(Integer huifuid);
    
    List<YiJianHuiFu> getAllByyiJianID(Integer yijianid);

    int updateByPrimaryKeySelective(YiJianHuiFu record);

    int updateByPrimaryKey(YiJianHuiFu record);
    
}