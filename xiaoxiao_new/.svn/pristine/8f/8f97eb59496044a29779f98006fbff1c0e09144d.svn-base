package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.JieCiFangAnMapper;
import com.web.model.JieCiFangAn;
import com.web.service.JieCiFangAnService;
@Service
public class JieCiFangAnServiceImpl implements JieCiFangAnService{
	
	@Autowired
	private JieCiFangAnMapper jieCiFangAnMapper;

	@Override
	public List<JieCiFangAn> getAllByxueXiaoID(Integer xuexiaoid) {
		// TODO Auto-generated method stub
		return jieCiFangAnMapper.getAllByxueXiaoID(xuexiaoid);
	}

	@Override
	public JieCiFangAn selectByxueXiaoIDAndZhuangTai(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return jieCiFangAnMapper.selectByxueXiaoIDAndZhuangTai(map);
	}

	@Override
	public int insert(JieCiFangAn record) {
		// TODO Auto-generated method stub
		return jieCiFangAnMapper.insert(record);
	}

	@Override
	public JieCiFangAn selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return jieCiFangAnMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(JieCiFangAn record) {
		// TODO Auto-generated method stub
		return jieCiFangAnMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updatezhuangTaiAndqiYongGuoByPrimaryKey(JieCiFangAn record) {
		// TODO Auto-generated method stub
		return jieCiFangAnMapper.updatezhuangTaiAndqiYongGuoByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return jieCiFangAnMapper.deleteByPrimaryKey(id);
	}

}
