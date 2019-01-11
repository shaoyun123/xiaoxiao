package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.XueQiMapper;
import com.web.model.XueQi;
import com.web.service.XueQiService;
@Service
public class XueQiServiceImpl implements XueQiService{
	@Autowired
	private XueQiMapper xueqiMapper;


	@Override
	public XueQi getByxueXiaoIDandriQi(Map<String, String> map) {
		return xueqiMapper.getByxueXiaoIDandriQi(map);
	}


	@Override
	public XueQi getByXueXiaoIDAndXueNianAndXueQi(Map<String, String> map) {
		return xueqiMapper.getByXueXiaoIDAndXueNianAndXueQi(map);
	}


	@Override
	public List<XueQi> getXueQiByXueXiaoID(int xuexiaoid) {
		return xueqiMapper.getXueQiByXueXiaoID(xuexiaoid);
	}


	@Override
	public XueQi selectByID(int id) {
		return xueqiMapper.selectByPrimaryKey(id);
	}


	@Override
	public int insert(XueQi record) {
		// TODO Auto-generated method stub
		return xueqiMapper.insert(record);
	}


	@Override
	public int updateByPrimary(XueQi record) {
		// TODO Auto-generated method stub
		return xueqiMapper.updateByPrimaryKey(record);
	}


	@Override
	public int deleteByPrimaryKey(Integer xueqiid) {
		// TODO Auto-generated method stub
		return xueqiMapper.deleteByPrimaryKey(xueqiid);
	}


	@Override
	public List<String> getXuenianByXuexiaoID(int xueqiid) {
		return xueqiMapper.getXuenianByXuexiaoID(xueqiid);
	}


	@Override
	public XueQi getByXueQiID(int xueqiid) {
		return xueqiMapper.getByXueQiID(xueqiid);
	}


	@Override
	public List<XueQi> findByXueXiaoID(String xueXiaoID) {
		return xueqiMapper.findByXueXiaoID(Integer.parseInt(xueXiaoID));
	}


	@Override
	public XueQi getByXueXiaoIDAndXueQiID(int parseInt, Integer xueqiid) {
		return xueqiMapper.getByXueXiaoIDAndXueQiID(parseInt, xueqiid);
	}


	@Override
	public List<String> getXueqiByXuexiaoID(int parseInt) {
		// TODO Auto-generated method stub
		return xueqiMapper.getXueqiByXuexiaoID(parseInt);
	}
	
	@Override
	public List<String> getXueNian(int xuexiaoid){
		return xueqiMapper.getXueNian(xuexiaoid);
	}


	@Override
	public Map<String, Object> getMapXueQiByxueXiaoIDandriQi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return xueqiMapper.getMapXueQiByxueXiaoIDandriQi(map);
	}


	@Override
	public Map<String, Object> getMapXueQiByXueXiaoIDAndXueNianAndXueQi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return xueqiMapper.getMapXueQiByXueXiaoIDAndXueNianAndXueQi(map);
	}


	@Override
	public List<Map<String, Object>> getMapXueQiByXueXiaoID(Map<String, String> map) {
		// TODO Auto-generated method stub
		return xueqiMapper.getMapXueQiByXueXiaoID(map);
	}


	@Override
	public List<Map<String, Object>> getNewerXueQi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return xueqiMapper.getNewerXueQi(map);
	}


	@Override
	public List<XueQi> getNewerXueQiByXueQi(Map<String, String> m) {
		// TODO Auto-generated method stub
		return xueqiMapper.getNewerXueQiByXueQi(m);
	}



}
