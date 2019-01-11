package com.web.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.YuanXiMapper;
import com.web.model.YuanXi;
import com.web.service.YuanXiService;

@Service
public class YuanXiServiceImpl implements YuanXiService{
	
	@Autowired
	private YuanXiMapper yuanXiMapper;

	@Override
	public YuanXi selectByPrimaryKey(Integer yuanxiid) {
		// TODO Auto-generated method stub
		return yuanXiMapper.selectByPrimaryKey(yuanxiid);
	}

	@Override
	public List<YuanXi> getAllByxueXiaoID(Integer xuexiaoid) {
		// TODO Auto-generated method stub
		return yuanXiMapper.getAllByxueXiaoID(xuexiaoid);
	}
	
	@Override
	public int insert(YuanXi record){
		
		return yuanXiMapper.insert(record);
	}
	@Override
	public int updateByPrimaryKey(YuanXi record){
		return yuanXiMapper.updateByPrimaryKey(record);
	}
	@Override
	public int deleteByPrimaryKey(Integer yuanxiid){
		return yuanXiMapper.deleteByPrimaryKey(yuanxiid);
	}
	
	@Override
	public YuanXi selectByYuanXiMingChengAndXueXiaoId(String yuanximingcheng,Integer xuexiaoid){
		return yuanXiMapper.selectByYuanXiMingChengAndXueXiaoId(yuanximingcheng, xuexiaoid);
	}
}
