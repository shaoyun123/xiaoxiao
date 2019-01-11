package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.JiGuangMapper;
import com.web.model.XiaoXiFaSong;
import com.web.service.JiGuangService;

@Service("jiGuangService")
public class JiGuangServiceImpl implements JiGuangService{
	@Autowired
	private JiGuangMapper jiGuangMapper;

	/**
	 * 查询所有未发送的消息数据
	 * @return
	 */
	@Override
	public List<XiaoXiFaSong> chaXunWeiFaSongXiaoXi(){
		return jiGuangMapper.chaXunWeiFaSongXiaoXi();
	}

	@Override
	public void gengXinXiaoXiZhuangTai(Map<String,Integer> map) {
		jiGuangMapper.gengXinXiaoXiZhuangTai(map);
		
	}
	
	@Override
	public void insertXiaoXiFaSong(XiaoXiFaSong xiaoXiFaSong) {
		jiGuangMapper.insertXiaoXiFaSong(xiaoXiFaSong);
		
	}

	

}
