package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.JCSJ;

public interface JCSJMapper {
	
	JCSJ selectByPrimaryKey(Integer id);
	
    int insert(JCSJ record);

    int deleteByPrimaryKey(Integer id);
    
    int updateByPrimaryKey(JCSJ record);
    
    List<JCSJ> getAllByjieCiFangAnID(Integer jiecifanganid);
    
    String getkaiShiShiJianByid(Integer id);
    
    String getjieShuShiJianByid(Integer id);
    
    int getCountByJieCiFangAnID(Integer jiecifanganid);

	String getkaiShiShiJianByidTwo(Map<String, Object> paramMap);

	String getjieShuShiJianByidTwo(Map<String, Object> paramMap);

	int findJCSJIDbyFangAnIdandJcsj(int jiecifanganid, String jcsj);
}