package com.web.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.ShangChuanWenDangMapper;
import com.web.model.ShangChuanWenDang;
import com.web.service.ShangChuanWenDangService;

@Service
public class ShangChuanWenDangServiceImpl implements ShangChuanWenDangService {
	@Autowired
	ShangChuanWenDangMapper shanChuanWenDangMapper;

	@Override
	public ShangChuanWenDang selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return shanChuanWenDangMapper.selectByPrimaryKey(id);
	}
	@Override
	public int insertshangchuanwendang(ShangChuanWenDang record) {
		return shanChuanWenDangMapper.insertshangchuanwendang(record);
	}
	@Override
	public int delshangchuanwendang(Integer shangchuanid) {
		return shanChuanWenDangMapper.delshangchuanwendang(shangchuanid);
	}

}
