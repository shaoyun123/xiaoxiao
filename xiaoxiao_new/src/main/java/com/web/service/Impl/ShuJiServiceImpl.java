package com.web.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.ShuJiMapper;
import com.web.model.ShuJi;
import com.web.service.ShuJiService;
@Service
public class ShuJiServiceImpl implements ShuJiService{
	@Autowired
	private ShuJiMapper shuJiMapper;

	@Override
	public ShuJi selectByPrimaryKey(Integer shujiid) {
		// TODO Auto-generated method stub
		return shuJiMapper.selectByPrimaryKey(shujiid);
	}

}
