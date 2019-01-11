package com.web.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.FuDaoYuanMapper;
import com.web.model.FuDaoYuan;
import com.web.service.FuDaoYuanService;
@Service
public class FuDaoYuanServiceImpl implements FuDaoYuanService{
	@Autowired
	private FuDaoYuanMapper fuDaoYuanMapper;

	
	@Override
	public FuDaoYuan getByfuDaoYuanID(Integer fudaoyuanid) {
		// TODO Auto-generated method stub
		return fuDaoYuanMapper.getByfuDaoYuanID(fudaoyuanid);
	}

	@Override
	public List<FuDaoYuan> getAllByshuJiID(Integer shujiid) {
		// TODO Auto-generated method stub
		return fuDaoYuanMapper.getAllByshuJiID(shujiid);
	}

	@Override
	public int updateByFuDaoYuan(FuDaoYuan fuDaoYuan) {
		return fuDaoYuanMapper.updateByPrimaryKey(fuDaoYuan);
	}

	@Override
	public FuDaoYuan getBybanJiID(String banjiid1, String banjiid2) {
		// TODO Auto-generated method stub
		List<FuDaoYuan> list = fuDaoYuanMapper.getBybanJiID(banjiid1, banjiid2);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
