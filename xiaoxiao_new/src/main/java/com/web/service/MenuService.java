package com.web.service;

import java.util.List;

import com.web.model.Menu;

public interface MenuService {
	List<Menu> getAllMenuByStatus(String status);

	List<Menu> getDeYuMenuByStatus(String status);

	List<String> findAllDeYuAction(String status);

	
}
