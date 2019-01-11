package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.model.TiXingTongZhiShiTu;
import com.web.dao.TiXingTongZhiShiTuMapper;
import com.web.service.TiXingTongZhiShiTuService;

@Service
public class TiXingTongZhiShiTuServiceImpl implements TiXingTongZhiShiTuService{
	@Autowired
	private TiXingTongZhiShiTuMapper tiXingTongZhiShiTuMapper;

	@Override
	public List<TiXingTongZhiShiTu> selectAllByjieShouRenIDandNewTarget(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tiXingTongZhiShiTuMapper.selectAllByjieShouRenIDandNewTarget(map);
	}

	@Override
	public int getCountByjieShouRenIDandNewTarget(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tiXingTongZhiShiTuMapper.getCountByjieShouRenIDandNewTarget(map);
	}

	@Override
	public List<TiXingTongZhiShiTu> selectAllByjieShouRenIDandNewTargetandLimit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tiXingTongZhiShiTuMapper.selectAllByjieShouRenIDandNewTargetandLimit(map);
	}

}
