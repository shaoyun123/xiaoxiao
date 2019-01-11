package com.web.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.XueShengChuGuanLiYuanMapper;
import com.web.model.XueShengChuGuanLiYuan;
import com.web.service.XueShengChuGuanLiYuanService;

@Service
public class XueShengChuGuanLiYuanServiceImpl implements XueShengChuGuanLiYuanService {
	@Autowired
	private XueShengChuGuanLiYuanMapper xueShengChuGuanLiYuanMapper;
	
	@Override
	public XueShengChuGuanLiYuan selectByID(int id) {
		return xueShengChuGuanLiYuanMapper.selectByID(id);
	}

}
