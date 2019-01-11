package com.web.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.web.dao.MenuMapper;
import com.web.model.Menu;
import com.web.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuMapper MenuMapper;
	
	@Cacheable(value="menuCache", key ="#status")
	@Override
	public List<Menu> getAllMenuByStatus(String status) {
		return MenuMapper.getAllMenuByStatus(status);
	}

	@Override
	public List<Menu> getDeYuMenuByStatus(String status) {
		return MenuMapper.getDeYuMenuByStatus(status);
	}

	@Override
	public List<String> findAllDeYuAction(String status) {
		return MenuMapper.findAllDeYuAction(status);
	}


}
