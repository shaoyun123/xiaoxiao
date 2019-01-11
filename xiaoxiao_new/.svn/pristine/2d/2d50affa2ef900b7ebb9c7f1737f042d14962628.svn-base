package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.KeChengJiBenMapper;
import com.web.model.KeChengJiBen;
import com.web.service.KeChengJiBenService;

@Service
public class KeChengJiBenServiceImpl implements KeChengJiBenService{

	@Autowired
	private KeChengJiBenMapper keChengJiBenMapper;

	@Override
	public List<KeChengJiBen> getAllByyuanXiIDs(String yuanxiid1, String yuanxiid2) {
		// TODO Auto-generated method stub
		return keChengJiBenMapper.getAllByyuanXiIDs(yuanxiid1, yuanxiid2);
	}

	@Override
	public KeChengJiBen selectByPrimaryKey(Integer kechengid) {
		// TODO Auto-generated method stub
		return keChengJiBenMapper.selectByPrimaryKey(kechengid);
	}

	@Override
	public List<KeChengJiBen> getAllByrenKeJiaoShi(String jiaoshi1, String jiaoshi2) {
		// TODO Auto-generated method stub
		return keChengJiBenMapper.getAllByrenKeJiaoShi(jiaoshi1, jiaoshi2);
	}

	@Override
	public int insert(KeChengJiBen record) {
		// TODO Auto-generated method stub
		return keChengJiBenMapper.insert(record);
	}

	@Override
	public List<KeChengJiBen> getAllByxueXiaoID(Integer xuexiaoid) {
		// TODO Auto-generated method stub
		return keChengJiBenMapper.getAllByxueXiaoID(xuexiaoid);
	}

	@Override
	public int updateByPrimaryKey(KeChengJiBen record) {
		// TODO Auto-generated method stub
		return keChengJiBenMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer kechengid) {
		// TODO Auto-generated method stub
		return keChengJiBenMapper.deleteByPrimaryKey(kechengid);
	}

	
	@Override
	public List<KeChengJiBen> getAllByxueXiaoIDandkeChengIDandyuanXiIDandjiaoShiID(Map<String, String> map) {
		// TODO Auto-generated method stub
		return keChengJiBenMapper.getAllByxueXiaoIDandkeChengIDandyuanXiIDandjiaoShiID(map);
	}

	@Override
	public List<Map<String, Object>> getAllJiBenKeMuByxueXiaoID(int xuexiaoid) {
		// TODO Auto-generated method stub
		return keChengJiBenMapper.getAllJiBenKeMuByxueXiaoID(xuexiaoid);
	}

	@Override
	public Map<String, Object> selectByIDAndXueXiaoID(Map<String, String> map) {
		// TODO Auto-generated method stub
		return keChengJiBenMapper.selectByIDAndXueXiaoID(map);
	}
}
