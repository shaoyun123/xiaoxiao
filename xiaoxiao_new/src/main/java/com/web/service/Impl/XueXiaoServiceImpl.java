package com.web.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.XueXiaoMapper;
import com.web.model.XueXiao;
import com.web.service.XueXiaoService;

@Service
public class XueXiaoServiceImpl implements XueXiaoService{
	@Autowired
	private XueXiaoMapper xueXiaoMapper;
	@Override
	public XueXiao selectByID(int id) {
		return xueXiaoMapper.selectByPrimaryKey(id);
	}

}
