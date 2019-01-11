package com.web.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.JiaoXueLouMapper;
import com.web.model.JiaoXueLou;
import com.web.service.JiaoXueLouService;
@Service
public class JiaoXueLouServiceImpl implements JiaoXueLouService {
	@Autowired
	private JiaoXueLouMapper jiaoXueLouMapper;
	@Override
	public List<JiaoXueLou> selectByXiaoQuId(Integer xiaoQuId){
		return jiaoXueLouMapper.selectByXiaoQuId(xiaoQuId);
	}
	@Override
	public int insert(JiaoXueLou record){
		return jiaoXueLouMapper.insert(record);
	}
	@Override
	 public JiaoXueLou selectByPrimaryKey(Integer jiaoXueLouId){
		return jiaoXueLouMapper.selectByPrimaryKey(jiaoXueLouId);
		
	}
	@Override
	public int updateByPrimaryKey(JiaoXueLou record){
		return jiaoXueLouMapper.updateByPrimaryKey(record);
	}
	@Override
	 public int deleteByPrimaryKey( Integer jiaoXueLouId){
		return jiaoXueLouMapper.deleteByPrimaryKey(jiaoXueLouId);
	}
}
