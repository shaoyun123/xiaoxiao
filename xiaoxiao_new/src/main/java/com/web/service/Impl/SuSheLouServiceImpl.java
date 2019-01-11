package com.web.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.SuSheLouMapper;
import com.web.model.SuSheLou;
import com.web.service.SuSheLouService;

@Service
public class SuSheLouServiceImpl implements SuSheLouService {
	@Autowired
	private SuSheLouMapper suSheLouMapper;
	@Override
	public int deleteByPrimaryKey(Integer suSheLouId){
		return suSheLouMapper.deleteByPrimaryKey(suSheLouId);
	}
	@Override
	public int insert(SuSheLou record){
		return suSheLouMapper.insert(record);
	}
	@Override
	public SuSheLou selectByPrimaryKey(Integer suSheLouId){
		return suSheLouMapper.selectByPrimaryKey(suSheLouId);
	}
	@Override
	public int updateByPrimaryKey(SuSheLou record){
		return suSheLouMapper.updateByPrimaryKey(record);
	}
	@Override
	public List<SuSheLou> getAllByXiaoQuId(Integer xiaoQuId){
		return suSheLouMapper.getAllByXiaoQuId(xiaoQuId);
    }
	@Override
	public SuSheLou selectBySuSheLouMingAndXiaoQuId(String suSheLouMing,Integer xiaoQuId){
		return suSheLouMapper.selectBySuSheLouMingAndXiaoQuId(suSheLouMing, xiaoQuId);
	}
}
