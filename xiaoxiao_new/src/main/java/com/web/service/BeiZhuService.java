package com.web.service;

import java.util.List;
import java.util.Map;
import com.web.model.BeiZhu;

public interface BeiZhuService {
	
	public BeiZhu selectByPrimaryKey(int id);
	
	public int deleteByPrimaryKey(int id);
	
	public int insert(Map<String, String> map);
	
	int updateByPrimaryKey(BeiZhu kechengbeizhu);

	public List<BeiZhu> getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(Map<String, String> map);
	

}
