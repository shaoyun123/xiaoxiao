package com.web.service.Impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.BanBenMapper;
import com.web.model.BanBen;
import com.web.service.BanBenService;

@Service
public class BanBenServiceImpl implements BanBenService{
	@Autowired
	private BanBenMapper banBenMapper;
	
	@Override
	public BanBen getZuiXinBanBenXinXi(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub
		return banBenMapper.getZuiXinBanBenXinXi(param);
	}

}
