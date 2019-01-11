package com.web.service;

import java.util.List;

import com.web.model.YuanXi;

public interface YuanXiService {
	
	YuanXi selectByPrimaryKey(Integer yuanxiid);
	
	YuanXi selectByYuanXiMingChengAndXueXiaoId(String yuanximingcheng,Integer xuexiaoid);
	
	List<YuanXi> getAllByxueXiaoID(Integer xuexiaoid);
	
	int insert(YuanXi record);
	
	int updateByPrimaryKey(YuanXi record);
	
	int deleteByPrimaryKey(Integer yuanxiid);

}
