package com.web.service;

import java.util.List;

import com.web.model.FuDaoYuan;

public interface FuDaoYuanService {
	
	FuDaoYuan getBybanJiID(String banjiid1,String banjiid2);//根据班级id查辅导员，1*2
	
	FuDaoYuan getByfuDaoYuanID(Integer fudaoyuanid);
	
	List<FuDaoYuan> getAllByshuJiID(Integer shujiid);
	
	int updateByFuDaoYuan(FuDaoYuan fuDaoYuan);

}
