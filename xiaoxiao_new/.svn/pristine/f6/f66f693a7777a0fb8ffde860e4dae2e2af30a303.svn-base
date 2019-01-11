package com.web.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.App_LiaoTianQunMapper;
import com.web.model.App_LiaoTianQun;
import com.web.service.App_LiaoTianQunService;

@Service
public class App_LiaoTianQunServiceImpl implements App_LiaoTianQunService{
	@Autowired
	private App_LiaoTianQunMapper liaoTianQunMapper;
	
	@Override
	public App_LiaoTianQun selectByPrimaryKey(Integer qunid) {
		// TODO Auto-generated method stub
		return liaoTianQunMapper.selectByPrimaryKey(qunid);
	}

	@Override
	public App_LiaoTianQun selectByBanJiID(Integer banjiid) {
		// TODO Auto-generated method stub
		return liaoTianQunMapper.selectByBanJiID(banjiid);
	}
}
