package com.web.dao;

import java.util.List;

import com.web.model.App_KaoTiKu;

public interface App_KaoTiKuMapper {
	int insert(App_KaoTiKu record);
	int delete(Integer id);
	int update(App_KaoTiKu record);
	App_KaoTiKu selectByPrimaryKey(Integer id);
	List<App_KaoTiKu> getShiJianTiByLaoShiId(Integer laoshiid);
}
