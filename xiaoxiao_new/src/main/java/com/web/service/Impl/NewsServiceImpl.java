package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.NewsMapper;
import com.web.model.TongZhi;
@Service
public class NewsServiceImpl implements com.web.service.NewsService {
	
	@Autowired
	private NewsMapper newsMapper;
	
//	@Override
//	public List<TongZhi> getNewsByParam(Map<String, Object> paramMap) {
//		// TODO Auto-generated method stub
//		return newsMapper.getNewsByParam(paramMap);
//	}
	@Override
	public List<Map<String,Object>> getNewsByParam(Map<String, Object> paramMap) {
		return newsMapper.getNewsByParam(paramMap);
	}

	@Override
	public void updateNewsByParam(Map<String, Object> paramMap) {
		newsMapper.updateNewsByParam(paramMap);
		
	}

	@Override
	public List<TongZhi> getNewsByParamID(Map<String, Object> paramMap) {
		return newsMapper.getNewsByParamID(paramMap);
	}

	@Override
	public List<Map<String, Object>> getNewsLeiXingByParam(Map<String, Object> paramMap) {
		return newsMapper.getNewsLeiXingByParam(paramMap);
	}

}
