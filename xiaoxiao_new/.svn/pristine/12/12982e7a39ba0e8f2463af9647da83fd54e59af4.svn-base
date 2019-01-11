package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.KeChengJiBen;

public interface KeChengJiBenMapper {
    int deleteByPrimaryKey(Integer kechengid);

    int insert(KeChengJiBen record);

    KeChengJiBen selectByPrimaryKey(Integer kechengid);

    int updateByPrimaryKey(KeChengJiBen record);
    
    List<KeChengJiBen> getAllByyuanXiIDs(String yuanxiid1,String yuanxiid2);
    
    List<KeChengJiBen> getAllByrenKeJiaoShi(String jiaoshi1,String jiaoshi2);//根据任课教师get课程
    
    List<KeChengJiBen> getAllByxueXiaoID(Integer xuexiaoid);
    
    List<KeChengJiBen> getAllByxueXiaoIDandkeChengIDandyuanXiIDandjiaoShiID(Map<String, String> map);

	List<Map<String, Object>> getAllJiBenKeMuByxueXiaoID(int xuexiaoid);

	Map<String, Object> selectByIDAndXueXiaoID(Map<String, String> map);
}