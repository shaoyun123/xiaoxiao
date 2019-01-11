package com.web.dao;

import com.web.model.ShangChuanWenDang;

public interface ShangChuanWenDangMapper {
	ShangChuanWenDang selectByPrimaryKey(Integer ID);
	int insertshangchuanwendang(ShangChuanWenDang record);
	int delshangchuanwendang(Integer shangchuanid);
}
