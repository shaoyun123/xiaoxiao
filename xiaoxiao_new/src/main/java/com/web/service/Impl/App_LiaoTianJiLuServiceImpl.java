package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.dao.App_LiaoTianJiLuMapper;
import com.web.model.App_LiaoTianJiLu;
import com.web.service.App_LiaoTianJiLuService;

@Service
public class App_LiaoTianJiLuServiceImpl implements App_LiaoTianJiLuService{
	@Autowired
	private App_LiaoTianJiLuMapper liaoTianJiLuMapper;

	@Override
	public App_LiaoTianJiLu selectByPrimaryKey(Integer jiluid) {
		// TODO Auto-generated method stub
		return liaoTianJiLuMapper.selectByPrimaryKey(jiluid);
	}

	@Override
	public List<App_LiaoTianJiLu> getALLByQunID(Map<String, String> map) {
		// TODO Auto-generated method stub
		return liaoTianJiLuMapper.getALLByQunID(map);
	}

	@Override
	public int selectCountLiaoTianJiLuByQunID(Integer qunid) {
		// TODO Auto-generated method stub
		return liaoTianJiLuMapper.selectCountLiaoTianJiLuByQunID(qunid);
	}

	@Override
	public int insertByQunID(App_LiaoTianJiLu jiLu) {
		// TODO Auto-generated method stub
		return liaoTianJiLuMapper.insertByQunID(jiLu);
	}

}
