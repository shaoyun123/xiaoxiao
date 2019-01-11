package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.TiXingMapper;
import com.web.model.TiXing;
import com.web.service.TiXingService;
@Service
public class TiXingServiceImpl implements TiXingService{
	@Autowired
	private TiXingMapper tiXingMapper;

	@Override
	public int insert(TiXing record) {
		// TODO Auto-generated method stub
		return tiXingMapper.insert(record);
	}

	@Override
	public List<TiXing> getAllByjieShouRenID(Integer jieshourenid) {
		// TODO Auto-generated method stub
		return tiXingMapper.getAllByjieShouRenID(jieshourenid);
	}

	@Override
	public TiXing selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return tiXingMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updatezhuangTaiByPrimaryKey(TiXing record) {
		// TODO Auto-generated method stub
		return tiXingMapper.updatezhuangTaiByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return tiXingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int getCountByjieShouRenID(Integer jieshourenid) {
		return tiXingMapper.getCountByjieShouRenID(jieshourenid);
	}

	@Override
	public List<TiXing> getAllByjieShouRenIDAndLimit(Map<String, Integer> map) {
		return tiXingMapper.getAllByjieShouRenIDAndLimit(map);
	}

	@Override
	public List<TiXing> getTiXingByjieShouRenIDAndRiQi(Map<String, String> paramMap) {
		return tiXingMapper.getTiXingByjieShouRenIDAndRiQi(paramMap);
	}

}
