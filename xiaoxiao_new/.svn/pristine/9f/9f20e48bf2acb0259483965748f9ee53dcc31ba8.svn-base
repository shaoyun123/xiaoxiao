package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.BeiZhuMapper;
import com.web.model.BeiZhu;
import com.web.service.BeiZhuService;
@Service
public class BeiZhuServiceImpl implements BeiZhuService {
	@Autowired
	private BeiZhuMapper beiZhuMapper;
	@Override
	public BeiZhu selectByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return beiZhuMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(int id) {
		return beiZhuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Map<String, String> map) {
		// TODO Auto-generated method stub
		return beiZhuMapper.insert(map);
	}

	@Override
	public int updateByPrimaryKey(BeiZhu kechengbeizhu) {
		// TODO Auto-generated method stub
		return beiZhuMapper.updateByPrimaryKey(kechengbeizhu);
	}

	@Override
	public List<BeiZhu> getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(Map<String, String> map) {
		// TODO Auto-generated method stub
		return beiZhuMapper.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(map);
	}

}
