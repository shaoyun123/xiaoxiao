package com.web.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.PingFenFangAnMapper;
import com.web.model.PingFenFangAn;
import com.web.service.PingFenFangAnService;

@Service
public class PingFenFangAnServiceImpl implements PingFenFangAnService{

	@Autowired
	private PingFenFangAnMapper pingFenFangAnMapper;
	
	@Override
	public List<PingFenFangAn> selectPingFenFangAnSByXueXiaoID(int xuexiaoid) {
		return pingFenFangAnMapper.selectPingFenFangAnSByXueXiaoID(xuexiaoid);
	}

	@Override
	public PingFenFangAn selectByID(int id) {
		return pingFenFangAnMapper.selectByPrimaryKey(id);
	}

}
