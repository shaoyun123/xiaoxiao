package com.web.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.JiaoShiMapper;
import com.web.model.JiaoShi;
import com.web.service.JiaoShiService;

@Service
public class JiaoShiServiceImpl implements JiaoShiService{

	@Autowired
	private JiaoShiMapper jiaoShiMapper;

	@Override
	public List<JiaoShi> getAllByxiaoQuID(Integer xiaoquid) {
		// TODO Auto-generated method stub
		return jiaoShiMapper.getAllByxiaoQuID(xiaoquid);
	}

	@Override
	public JiaoShi selectByPrimaryKey(Integer jiaoshiid) {
		// TODO Auto-generated method stub
		return jiaoShiMapper.selectByPrimaryKey(jiaoshiid);
	}
	@Override
	 public List<JiaoShi> getAllByPrimaryKey(Integer jiaoXueLouId){
		return jiaoShiMapper.getAllByPrimaryKey(jiaoXueLouId);
	}
	@Override
	public int insert(JiaoShi record){
		return jiaoShiMapper.insert(record);
	}
	@Override
	 public int updateByPrimaryKey(JiaoShi record){
		return jiaoShiMapper.updateByPrimaryKey(record);
	}
	@Override
	 public int deleteByPrimaryKey(Integer jiaoshiid){
		return  jiaoShiMapper.deleteByPrimaryKey(jiaoshiid);
	}
	@Override
	public JiaoShi selectByJiaoXueLouIdAndJiaoShiMing(Integer jiaoxuelouid,String jiaoshiming){
		return jiaoShiMapper.selectByJiaoXueLouIdAndJiaoShiMing(jiaoxuelouid, jiaoshiming);
	}
	@Override
	public int updateByPrimaryKeySelective(JiaoShi record){
		return jiaoShiMapper.updateByPrimaryKeySelective(record);
	}
	
}
