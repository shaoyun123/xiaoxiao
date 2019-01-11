package com.web.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.YuanXiZhuanYeMapper;
import com.web.model.YuanXiZhuanYe;
import com.web.service.YuanXiZhuanYeService;

@Service
public class YuanXiZhuanYeServiceImpl implements YuanXiZhuanYeService {
	@Autowired
	private YuanXiZhuanYeMapper yuanXiZhuanYeMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return yuanXiZhuanYeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(YuanXiZhuanYe record) {
		return yuanXiZhuanYeMapper.insert(record);
	}

	@Override
	public int insertSelective(YuanXiZhuanYe record) {
		return yuanXiZhuanYeMapper.insertSelective(record);
	}

	@Override
	public YuanXiZhuanYe selectByPrimaryKey(Integer id) {
		return yuanXiZhuanYeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(YuanXiZhuanYe record) {
		return yuanXiZhuanYeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(YuanXiZhuanYe record) {
		return yuanXiZhuanYeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<YuanXiZhuanYe> getAllByYuanXiID(Integer yuanxiid) {
		return yuanXiZhuanYeMapper.getAllByYuanXiID(yuanxiid);
	}
	
	@Override
	public YuanXiZhuanYe selectByZhuanYeId(Integer zhuanyeid){
		return yuanXiZhuanYeMapper.selectByZhuanYeId(zhuanyeid);
	}

}
