package com.web.dao;

import java.util.List;

import com.web.model.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> getAllMenuByStatus(String status);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

	List<Menu> getDeYuMenuByStatus(String status);

	List<String> findAllDeYuAction(String status);

}