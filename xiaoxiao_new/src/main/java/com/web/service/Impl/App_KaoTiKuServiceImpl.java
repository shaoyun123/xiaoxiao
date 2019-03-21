package com.web.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.App_KaoTiKuMapper;
import com.web.model.App_KaoTiKu;
import com.web.service.App_KaoTiKuService;
@Service
public class App_KaoTiKuServiceImpl implements App_KaoTiKuService{
	@Autowired
	private App_KaoTiKuMapper app_KaoTiKuMapper;
	@Override
	public int insert(App_KaoTiKu record) {
		return app_KaoTiKuMapper.insert(record);
	}
	@Override
	public int delete(Integer id) {
		return app_KaoTiKuMapper.delete(id);
	}
	@Override
	public int update(App_KaoTiKu record) {
		return app_KaoTiKuMapper.update(record);
	}
	@Override
	public App_KaoTiKu selectByPrimaryKey(Integer id) {
		return app_KaoTiKuMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<App_KaoTiKu> getShiJianTiByLaoShiId(Integer laoshiid){
		return app_KaoTiKuMapper.getShiJianTiByLaoShiId(laoshiid);
	}
}
