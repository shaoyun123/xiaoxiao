package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.JCSJ;

public interface JCSJService {
	
	JCSJ selectByPrimaryKey(Integer id);
	
	int insert(JCSJ record);
	
	int updateByPrimaryKey(JCSJ record);
	
	int deleteByPrimaryKey(Integer id);
	
	List<JCSJ> getAllByjieCiFangAnID(Integer jiecifanganid);//根据节次方案id获取所有节次
	
	String getkaiShiShiJianByid(Integer id);
	
	String getjieShuShiJianByid(Integer id);
	
	int getCountByJieCiFangAnID(Integer jiecifanganid);

	String getkaiShiShiJianByidTwo(Map<String, Object> paramMap);

	String getjieShuShiJianByidTwo(Map<String, Object> paramMap);

	int findJCSJIDbyFangAnIdandJcsj(int jiecifanganid, String string);
}
