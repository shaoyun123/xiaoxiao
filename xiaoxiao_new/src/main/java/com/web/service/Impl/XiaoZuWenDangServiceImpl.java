package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.XiaoZuWenDangMapper;
import com.web.model.XiaoZuWenDang;
import com.web.service.XiaoZuWenDangService;
@Service
public class XiaoZuWenDangServiceImpl implements XiaoZuWenDangService {
	@Autowired 
	private XiaoZuWenDangMapper xiaoZuWenDangMapper;
	@Override
	public XiaoZuWenDang selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return xiaoZuWenDangMapper.selectByPrimaryKey(id);
	}

	@Override
	public XiaoZuWenDang getXiaoZuWenDang(Integer wendangid, Integer xiaozuid) {
		// TODO Auto-generated method stub
		return xiaoZuWenDangMapper.getXiaoZuWenDang(wendangid, xiaozuid);
	}

	@Override
	public List<XiaoZuWenDang> getAllByWenDangID(Integer wendangid) {
		// TODO Auto-generated method stub
		return xiaoZuWenDangMapper.getAllByWenDangID(wendangid);
	}

	@Override
	public List<XiaoZuWenDang> getAllByXiaoZuID(Integer xiaozuid) {
		// TODO Auto-generated method stub
		return xiaoZuWenDangMapper.getAllByXiaoZuID(xiaozuid);
	}
	@Override
	public List<Map<String, Object>> getAllByKeChengIDAndXiaoZuID(Integer kechengid,Integer xiaozuid){
		return xiaoZuWenDangMapper.getAllByKeChengIDAndXiaoZuID(kechengid, xiaozuid);
	}
	@Override
	public int insert(XiaoZuWenDang record) {
		return xiaoZuWenDangMapper.insert(record);
	}
	@Override
	public int updateByPrimaryKey(XiaoZuWenDang record) {
		return xiaoZuWenDangMapper.updateByPrimaryKey(record);
	}
	@Override
	public int delete(Integer zuwendangid) {
		return xiaoZuWenDangMapper.delete(zuwendangid);
	}
	@Override
	public List<Map<String, Object>> getAllByKeChengID(Integer kechengid){
		return xiaoZuWenDangMapper.getAllByKeChengID(kechengid);
	}

}
