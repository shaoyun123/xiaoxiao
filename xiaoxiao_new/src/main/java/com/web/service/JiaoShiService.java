package com.web.service;

import java.util.List;

import com.web.model.JiaoShi;

public interface JiaoShiService {
	
	List<JiaoShi> getAllByxiaoQuID(Integer xiaoquid);
	JiaoShi selectByPrimaryKey(Integer jiaoshiid);
	List<JiaoShi> getAllByPrimaryKey(Integer jiaoXueLouId);
	int insert(JiaoShi record);
	int updateByPrimaryKey(JiaoShi record);
	int updateByPrimaryKeySelective(JiaoShi record);
	int deleteByPrimaryKey(Integer jiaoshiid);
	JiaoShi selectByJiaoXueLouIdAndJiaoShiMing(Integer jiaoxuelouid,String jiaoshiming);

}
