package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.KeChengJiBen;

public interface KeChengJiBenService {
	
	List<KeChengJiBen> getAllByyuanXiIDs(String yuanxiid1,String yuanxiid2);
	
	KeChengJiBen selectByPrimaryKey(Integer kechengid);
	
	List<KeChengJiBen> getAllByrenKeJiaoShi(String jiaoshi1,String jiaoshi2);//根据任课教师get课程
	
	int insert(KeChengJiBen record);
	
	List<KeChengJiBen> getAllByxueXiaoID(Integer xuexiaoid);
	
	int updateByPrimaryKey(KeChengJiBen record);
	
	int deleteByPrimaryKey(Integer kechengid);
	
	List<KeChengJiBen> getAllByxueXiaoIDandkeChengIDandyuanXiIDandjiaoShiID(Map<String, String> map);

	List<Map<String, Object>> getAllJiBenKeMuByxueXiaoID(int xuexiaoid);

	Map<String, Object> selectByIDAndXueXiaoID(Map<String, String> map);

}
