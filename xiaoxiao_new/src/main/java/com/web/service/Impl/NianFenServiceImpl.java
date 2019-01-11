package com.web.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.NianFenMapper;
import com.web.model.NianFen;
import com.web.service.NianFenService;

@Service
public class NianFenServiceImpl implements NianFenService{

	@Autowired
	private NianFenMapper nianFenMapper;
	
	@Override
	public NianFen selectNianFenByRuXueNianFen(int ruxuenianfen) {
		return nianFenMapper.selectByRuXueNianFen(ruxuenianfen);
	}
	@Override
	public List<NianFen> getNianFen(){
		return nianFenMapper.getNianFen();
	}
	@Override
	public NianFen selectByPrimaryKey(Integer ruxuenianfenid){
		return nianFenMapper.selectByPrimaryKey(ruxuenianfenid);
	}
	@Override
	public List<NianFen> findByNianFen(String ruxuenianfen) {
		// TODO Auto-generated method stub
		return nianFenMapper.findByNianFen(ruxuenianfen);
	}
}
