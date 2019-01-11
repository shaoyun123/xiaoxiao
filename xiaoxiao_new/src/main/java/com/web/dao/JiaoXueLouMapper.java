package com.web.dao;

import java.util.List;

import com.web.model.JiaoXueLou;

public interface JiaoXueLouMapper {
	List<JiaoXueLou> selectByXiaoQuId(Integer xiaoQuId);
	int insert(JiaoXueLou record);
	JiaoXueLou selectByPrimaryKey(Integer jiaoXueLouId);
	 int updateByPrimaryKey(JiaoXueLou record);
	 int deleteByPrimaryKey( Integer jiaoXueLouId);

}
