package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.HuoDong;

public interface HuoDongMapper {
    int deleteByPrimaryKey(Integer id);

    int insert2(HuoDong record);

    int insertSelective(HuoDong record);

    HuoDong selectByPrimaryKey(Integer huodongid);
    
    String getcanYuRenByhuoDongID(int huodongid);

    int updateByPrimaryKeySelective(Map<String, String> map);

    int updateByPrimaryKey(HuoDong record);
    
    List<HuoDong> getAllBycanYuRen2(String canyuren1,String canyuren2,String canyuren3,String canyuren4);//参与人状态只允许两种、开头和非开头两种参与人情况2*2
    
    List<HuoDong> getAllBycanYuRen(String canyuren1,String canyuren2);//参与人状态只允许一种、开头和非开头两种参与人情况1*2
    
    List<HuoDong> getAllByTianJiaRen(String tianjiaren);
    
    List<HuoDong> getALLBycanYuRenAndBanJiIds(String canyuren1,String canyuren2,String banjiids1,String banjiids2);
    
    int insert(Map<String, String> map);

	List<HuoDong> getAllBycanYuRenAndRiQi(Map<String, String> map);
	
	List<HuoDong> getAllBycanYuRenAndRiQiInAppMain(Map<String, String> map);
	
	List<HuoDong> getHistoryHuoDongBycanYuRen(Map<String, Object> map);
	
	int getCountHistoryHuoDongBycanYuRen(String canyuren1,String canyuren2);

	int getCountHistoryHuoDong(Map<String, String> paramMap);

	int updateBeiZhuByID(HuoDong huodong);

	List<HuoDong> getAllByRiQiAndRenIDAndRenLeiXing(Map<String, String> paramMap);

	List<Map<String, Object>> getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(Map<String, String> paramMap);

	List<Map<String, Object>> getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(Map<String, String> paramMap);

	List<String> getAllBanJiIDByHuoDongID(int huodongid);

	List<Map<String, Object>> getAllYaoQingRenByHuoDongIDAndRenIDAndRenLeiXing(Map<String, String> paramMap);

	List<HuoDong> getAllByTianJiaRenAndLeiXing(Map<String, String> map);
	
	List<HuoDong> getAllByRenAndLeiXing(Map<String, String> map);

	int insert_huodongren(Map<String, String> map);

	int insert_huodongbanji(Map<String, String> mapP);

	int delete_huodongbanji(Map<String, String> map);

	int delete_huodongren(Map<String, String> map);

	int delete_huodongcanyuren(Map<String, String> map);

	int insert_huodongjujueren(Map<String, String> map);

	List<HuoDong> getAllByRenIDAndRenLeiXing(Map<String, String> map);

	int delete_huodongjujueren(Map<String, String> map);

	int insert_huodongcanyuren(Map<String, String> map);

	int getCountByRenIDAndRenLeiXing(Map<String, String> paramMap);
}