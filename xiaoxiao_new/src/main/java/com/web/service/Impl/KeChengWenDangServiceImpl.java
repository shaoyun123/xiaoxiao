package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.KeChengWenDangMapper;
import com.web.model.KeChengWenDang;
import com.web.service.KeChengWenDangService;
@Service
public class KeChengWenDangServiceImpl implements KeChengWenDangService {
	@Autowired
	private KeChengWenDangMapper keChengWenDangMapper;
	@Override
	public KeChengWenDang selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return keChengWenDangMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Map<String, Object>> getAllByKeChengID(Integer shijiankeid) {
		// TODO Auto-generated method stub
		return keChengWenDangMapper.getAllByKeChengID(shijiankeid);
	}
	@Override
	public int insert(KeChengWenDang record) {
		return keChengWenDangMapper.insert(record);
	}
	@Override
	public int updateByPrimaryKey(KeChengWenDang record) {
		return keChengWenDangMapper.updateByPrimaryKey(record);
	}
	@Override
	public int delete(KeChengWenDang record) {
		return keChengWenDangMapper.delete(record);
	}
	@Override
	public Map<String, Object> selectsaomaxinxi(String uuid){
		return keChengWenDangMapper.selectsaomaxinxi(uuid);
	}
	@Override
	public int insertshangchuan(Map<String, Object> map) {
		return keChengWenDangMapper.insertshangchuan(map);
	}
	@Override
	public int deletesaomashangchuan(Integer xueshengid, Integer uuid) {
		return keChengWenDangMapper.deletesaomashangchuan(xueshengid, uuid);
	}
	@Override
	public int updatesaomashangchuan(Map<String, Object> map) {
		return keChengWenDangMapper.updatesaomashangchuan(map);
	}

}
