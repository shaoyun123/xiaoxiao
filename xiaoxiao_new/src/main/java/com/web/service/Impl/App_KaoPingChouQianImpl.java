package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.App_KaoPingChouQianMapper;
import com.web.model.App_KaoPingChouQian;
import com.web.service.App_KaoPingChouQianService;
@Service
public class App_KaoPingChouQianImpl implements App_KaoPingChouQianService {
	@Autowired
	private App_KaoPingChouQianMapper app_KaoPingChouQianMapper;
	@Override
	public App_KaoPingChouQian selectByPrimaryKey(Integer id) {
		return app_KaoPingChouQianMapper.selectByPrimaryKey(id);
	}
	@Override
	public int insert(App_KaoPingChouQian record) {
		return app_KaoPingChouQianMapper.insert(record);
	}
	@Override
	public int updateByPrimaryKey(App_KaoPingChouQian record) {
		return app_KaoPingChouQianMapper.updateByPrimaryKey(record);
	}
	@Override
	public Map<String, Object> selectchouqian(Map<String, Object> map){
		return app_KaoPingChouQianMapper.selectchouqian(map);
	}
	@Override
	public App_KaoPingChouQian selectByPrimaryKey1(Integer kaopingid,Integer fenzuid) {
		return app_KaoPingChouQianMapper.selectByPrimaryKey1(kaopingid, fenzuid);
	}
	@Override
	public App_KaoPingChouQian selectchouqian2(Map<String, Object> map) {
		return app_KaoPingChouQianMapper.selectchouqian2(map);
	}
	@Override
	public List<App_KaoPingChouQian> getchouqian(Map<String, Object> map){
		return app_KaoPingChouQianMapper.getchouqian(map);
	}
	@Override
	public Map<String, Object> selectByPrimaryKey2(Integer kaopingid,Integer fenzuid){
		return app_KaoPingChouQianMapper.selectByPrimaryKey2(kaopingid, fenzuid);
	}
	@Override
	public App_KaoPingChouQian  getchouqianbyfenzu(Integer kaopingid) {
		return app_KaoPingChouQianMapper.getchouqianbyfenzu(kaopingid);
	}
	@Override
	public Map<String, Object>  getchouqianbyfenzu1(Integer kaopingid){
		return app_KaoPingChouQianMapper.getchouqianbyfenzu1(kaopingid);
	}
	@Override
	public List<App_KaoPingChouQian> getByDaZu(Map<String, Object> map){
		return app_KaoPingChouQianMapper.getByDaZu(map);
	}
}
