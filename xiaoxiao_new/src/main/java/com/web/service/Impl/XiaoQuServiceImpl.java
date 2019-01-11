package com.web.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.XiaoQuMapper;
import com.web.model.XiaoQu;
import com.web.service.XiaoQuService;

@Service
public class XiaoQuServiceImpl implements XiaoQuService{
	
	@Autowired
	private XiaoQuMapper xiaoQuMapper;

	@Override
	public List<XiaoQu> getAllByxueXiaoID(Integer xuexiaoid) {
		
		return xiaoQuMapper.getAllByxueXiaoID(xuexiaoid);
	}

	@Override
	public XiaoQu selectByPrimaryKey(Integer xiaoquid) {
		
		return xiaoQuMapper.selectByPrimaryKey(xiaoquid);
	}
	@Override
	public int insertXiaoQu(XiaoQu record){
		return xiaoQuMapper.insertXiaoQu(record);
	}
	@Override
	public int updateByPrimaryKey(XiaoQu record){
		return xiaoQuMapper.updateByPrimaryKey(record);
	}
	@Override
	public int deleteByPrimaryKey(Integer xiaoquid){
		return xiaoQuMapper.deleteByPrimaryKey(xiaoquid);
	}
	
}
