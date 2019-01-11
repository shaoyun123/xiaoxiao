package com.web.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.SheTuanHuoDongXinXiMapper;
import com.web.model.SheTuanHuoDongXinXi;
import com.web.service.SheTuanHuoDongService;

@Service
public class SheTuanHuoDongServiceImpl implements SheTuanHuoDongService{
	
	@Autowired
	private SheTuanHuoDongXinXiMapper sheTuanHuoDongXinXiMapper;
	
	@Override
	public List<SheTuanHuoDongXinXi> selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(int shetuanid,int start,int stop) {
		return sheTuanHuoDongXinXiMapper.selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(shetuanid,start,stop);
	}

	@Override
	public List<SheTuanHuoDongXinXi> selectSheTuanHuoDongXinXisByXueShengZuZhiIDAndLimit(int xueshengzuzhiid,int start,int stop) {
		return sheTuanHuoDongXinXiMapper.selectSheTuanHuoDongXinXisByXueShengZuZhiIDAndLimit(xueshengzuzhiid,start,stop);
	}

	@Override
	public SheTuanHuoDongXinXi selectByID(int huodongid) {
		return sheTuanHuoDongXinXiMapper.selectByPrimaryKey(huodongid);
	}

	@Override
	public int updateSheTuanHuoDongXinXiBySheTuanHuoDongXinXi(SheTuanHuoDongXinXi record) {
		return sheTuanHuoDongXinXiMapper.updateByPrimaryKey(record);
	}

	@Override
	public int insertSheTuanHuoDongXinXi(SheTuanHuoDongXinXi record) {
		return sheTuanHuoDongXinXiMapper.insert(record);
	}

	@Override
	public int getCountBySheTuanID(int shetuanid) {
		return sheTuanHuoDongXinXiMapper.getCountBySheTuanID(shetuanid);
	}

	@Override
	public int getCountByXueShengZuZhiID(int xueshengzuzhiid) {
		return sheTuanHuoDongXinXiMapper.getCountByXueShengZuZhiID(xueshengzuzhiid);
	}

	@Override
	public List<SheTuanHuoDongXinXi> selectSheTuanHuoDongXinXisBySheTuanID(int shetuanid) {
		return sheTuanHuoDongXinXiMapper.selectSheTuanHuoDongXinXisBySheTuanID(shetuanid);
	}

	@Override
	public List<SheTuanHuoDongXinXi> selectSheTuanHuoDongXinXisByXueShengZuZhiID(int xueshengzuzhiid) {
		return sheTuanHuoDongXinXiMapper.selectSheTuanHuoDongXinXisByXueShengZuZhiID(xueshengzuzhiid);
	}

}
