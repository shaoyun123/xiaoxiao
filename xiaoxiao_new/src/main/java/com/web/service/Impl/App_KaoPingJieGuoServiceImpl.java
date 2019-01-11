package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.App_KaoPingJieGuoMapper;
import com.web.model.App_KaoPingJieGuo;
import com.web.service.App_KaoPingJieGuoService;
@Service
public class App_KaoPingJieGuoServiceImpl implements App_KaoPingJieGuoService{
	@Autowired
	private App_KaoPingJieGuoMapper app_KaoPingJieGuoMapper;
	@Override
	public App_KaoPingJieGuo selectByPrimaryKey(Integer id) {
		return app_KaoPingJieGuoMapper.selectByPrimaryKey(id);
	}
	@Override
	public int insert(App_KaoPingJieGuo record) {
		return app_KaoPingJieGuoMapper.insert(record);
	}
	@Override
	public List<App_KaoPingJieGuo> getJieGuo(Integer kaopingid){
		return app_KaoPingJieGuoMapper.getJieGuo(kaopingid);
	}
	@Override
	public int update (App_KaoPingJieGuo record) {
		return app_KaoPingJieGuoMapper.update(record);
	}
	@Override
	public App_KaoPingJieGuo selectJieGuoByXiaoZuId(Integer kaopingid,Integer xiaozuid) {
		return app_KaoPingJieGuoMapper.selectJieGuoByXiaoZuId(kaopingid, xiaozuid);
	}
	@Override
	public List<Map<String, Object>> getJieGuoByKaoPingIDAndXiaoZuID(Map<String, Object>map){
		return app_KaoPingJieGuoMapper.getJieGuoByKaoPingIDAndXiaoZuID(map);
	}
}
