package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.XiaoXiFaSong;

public interface JiGuangMapper {

	/**
	 * 查询所有未发送的消息数据
	 * @return
	 */
	List<XiaoXiFaSong> chaXunWeiFaSongXiaoXi();
	
	void gengXinXiaoXiZhuangTai(Map<String,Integer> map);
	
	void insertXiaoXiFaSong(XiaoXiFaSong xiaoXiFaSong);
}
