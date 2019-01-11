package com.web.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.FDAPMapper;
import com.web.model.FDAP;
import com.web.service.FDAPService;
@Service
public class FDAPServiceImpl implements FDAPService {
	@Autowired
	private FDAPMapper fdapMapper;

	@Override
	public String getmingChengByanPaiID(int anpaiid) {
		// TODO Auto-generated method stub
		return fdapMapper.getmingChengByanPaiID(anpaiid);
	}

	@Override
	public String getyaoQiuByanPaiID(int anpaiid) {
		// TODO Auto-generated method stub
		return fdapMapper.getyaoQiuByanPaiID(anpaiid);
	}

	@Override
	public String getjieZhiShiJianByanPaiID(int anpaiid) {
		// TODO Auto-generated method stub
		return fdapMapper.getjieZhiShiJianByanPaiID(anpaiid);
	}

	@Override
	public FDAP getByanPaiID(Integer anpaiid) {
		// TODO Auto-generated method stub
		return fdapMapper.getByanPaiID(anpaiid);
	}

	@Override
	public int insert(FDAP record) {
		// TODO Auto-generated method stub
		return fdapMapper.insert(record);
	}

	@Override
	public List<FDAP> getAllByfuDaoYuanID(Integer fudaoyuanid) {
		// TODO Auto-generated method stub
		return fdapMapper.getAllByfuDaoYuanID(fudaoyuanid);
	}

	@Override
	public int updateByPrimaryKey(FDAP record) {
		// TODO Auto-generated method stub
		return fdapMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer anpaiid) {
		// TODO Auto-generated method stub
		return fdapMapper.deleteByPrimaryKey(anpaiid);
	}


}
