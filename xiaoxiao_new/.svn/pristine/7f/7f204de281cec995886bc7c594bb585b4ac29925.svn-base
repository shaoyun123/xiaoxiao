package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.ChaQinJieGuo;

public interface ChaQinJieGuoMapper {
    int deleteByPrimaryKey(Integer jieguoid);

    int insertChaQinJieGuo(ChaQinJieGuo record);

    int insertSelective(ChaQinJieGuo record);

    ChaQinJieGuo selectByPrimaryKey(Integer jieguoid);

    int updateByPrimaryKeySelective(ChaQinJieGuo record);

    int updateByPrimaryKey(ChaQinJieGuo record);
    
    int deleteChaQinJieGuoByanPaiIDAndsuSheID(Map<String, Integer> map);
    
    List<ChaQinJieGuo> selectChaQinJieGuoByanPaiIDAndsuSheID(Map<String, Integer> map);
    
    List<ChaQinJieGuo> selectByAnPaiID(Integer anpaiid);

	int selectWanChengChaQin(Map<String, String> paramMap);

	ChaQinJieGuo selectByAnPaiIDAndXueShengID(Map<String, String> paramMap);
}