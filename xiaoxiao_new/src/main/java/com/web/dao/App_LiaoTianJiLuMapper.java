package com.web.dao;

import java.util.List;
import java.util.Map;


import com.web.model.App_LiaoTianJiLu;

public interface App_LiaoTianJiLuMapper {
	App_LiaoTianJiLu selectByPrimaryKey(Integer jiluid);
	
	List<App_LiaoTianJiLu> getALLByQunID(Map<String, String> map);
	
	int selectCountLiaoTianJiLuByQunID(Integer qunid);
	
	int insertByQunID(App_LiaoTianJiLu jiLu);
}
