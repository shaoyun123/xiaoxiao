package com.web.service;

import java.util.List;

import com.web.model.XiaoQu;

public interface XiaoQuService {
	
	List<XiaoQu> getAllByxueXiaoID(Integer xuexiaoid);
	
	XiaoQu selectByPrimaryKey(Integer xiaoquid);
	int insertXiaoQu(XiaoQu record);
	int updateByPrimaryKey(XiaoQu record);
	int deleteByPrimaryKey(Integer xiaoquid);
	


}
