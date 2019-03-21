package com.web.service.Impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.App_ShiJianTiMapper;
import com.web.model.App_ShiJianTi;
import com.web.service.App_ShiJianTiService;
@Service
public class App_ShiJianTiServiceImpl implements App_ShiJianTiService{
	@Autowired 
	private App_ShiJianTiMapper app_ShiJianTiMapper;
	@Override
	public int insert(App_ShiJianTi record){
		return app_ShiJianTiMapper.insert(record);
	}
	@Override
	public int delete(Integer id) {
		return app_ShiJianTiMapper.delete(id);
	}
	@Override
	public int update(App_ShiJianTi record) {
		return app_ShiJianTiMapper.update(record);
		
	}
	@Override
	public App_ShiJianTi selectByPrimaryKey(Integer id) {
		return app_ShiJianTiMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Map<String, Object>> getShiJianTiByShiXiId(Integer shixiid){
		return app_ShiJianTiMapper.getShiJianTiByShiXiId(shixiid);
	}
	@Override
	public List<Map<String, Object>> getKaoTiByLaoShiId(Integer laoshiid,Integer shixiid){
		return app_ShiJianTiMapper.getKaoTiByLaoShiId(laoshiid,shixiid);
	}
	@Override
	public List<App_ShiJianTi> getKaoTiList(Integer shixiid){
		return app_ShiJianTiMapper.getKaoTiList(shixiid);
	}
}
