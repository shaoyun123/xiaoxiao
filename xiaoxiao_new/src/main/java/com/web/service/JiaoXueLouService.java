package com.web.service;

import java.util.List;

import com.web.model.JiaoXueLou;

public interface JiaoXueLouService {
	List<JiaoXueLou> selectByXiaoQuId(Integer xiaoQuId);
	int insert(JiaoXueLou record);
	JiaoXueLou selectByPrimaryKey(Integer jiaoXueLouId);
	int updateByPrimaryKey(JiaoXueLou record);
	int deleteByPrimaryKey( Integer jiaoXueLouId);

}
