package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.NeiRongTongZhiMapper;
import com.web.model.NeiRongTongZhi;
import com.web.service.NeiRongTongZhiService;

@Service
public class NeiRongTongZhiServiceImpl implements NeiRongTongZhiService {

	@Autowired
	private NeiRongTongZhiMapper neiRongTongZhiMapper;

	@Override
	public int insert(NeiRongTongZhi tongzhi) {
		return neiRongTongZhiMapper.insert(tongzhi);
	}

	@Override
	public List<NeiRongTongZhi> findByFaBuRenIDAndLeiXing(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return neiRongTongZhiMapper.findByFaBuRenIDAndLeiXing(paramMap);
	}

	@Override
	public NeiRongTongZhi selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return neiRongTongZhiMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return neiRongTongZhiMapper.delete(id);
	}

	@Override
	public int update(NeiRongTongZhi tongZhi) {
		// TODO Auto-generated method stub
		return neiRongTongZhiMapper.update(tongZhi);
	}

	@Override
	public List<NeiRongTongZhi> findByBanJiIDAndZhuanYeID(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return neiRongTongZhiMapper.findByBanJiIDAndZhuanYeID(paramMap);
	}
}
