package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.App_FenZu;
import com.web.model.App_XueXiZu;

public interface App_FenZuMapper {
	App_FenZu selectByPrimaryKey(Integer id);
	int update(App_FenZu record);
	List<Map<String, Object>> getDaZu(Integer kechengid);
	List<Map<String, Object>> getFenZu(Integer shijiankechengid);
	List<App_XueXiZu> getXueXiZu(Integer fenzuid);
	List<App_FenZu> selectFenZu(Integer kechengid,Integer xueshengid);
	int delete(Integer id);
	
	int insertdazu(App_FenZu record);
}