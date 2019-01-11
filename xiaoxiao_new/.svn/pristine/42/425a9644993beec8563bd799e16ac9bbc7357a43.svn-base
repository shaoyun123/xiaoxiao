package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.JieCiFangAn;

public interface JieCiFangAnMapper {
	
	int deleteByPrimaryKey(Integer id);
    
    int insert(JieCiFangAn record);
    
    JieCiFangAn selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKey(JieCiFangAn record);
    
    int updatezhuangTaiAndqiYongGuoByPrimaryKey(JieCiFangAn record);//更新状态、启用过 根据主键id
    
    List<JieCiFangAn> getAllByxueXiaoID(Integer xuexiaoid);//根据学校get所有节次方案
    
    JieCiFangAn selectByxueXiaoIDAndZhuangTai(Map<String, Integer> map);
}