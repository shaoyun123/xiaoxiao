package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.ChaQinAnPai;

public interface ChaQinAnPaiMapper {
    int deleteByPrimaryKey(Integer anpaiid);

    int insert(ChaQinAnPai record);

    int insertSelective(ChaQinAnPai record);

    ChaQinAnPai selectChaQinAnPaiByPrimaryKey(Integer anpaiid);
    
    List<ChaQinAnPai> selectByYongHuId(Integer yonghuid);

    int updateByPrimaryKeySelective(ChaQinAnPai record);

    int updateByPrimaryKey(ChaQinAnPai record);
    
    
    List<ChaQinAnPai> selectByYongHuIdAndRiQi(Map<String,Object> paramMap);

	int selectChaQinTotal(Map<String, String> paramMap);

	List<ChaQinAnPai> getByYongHuIdAndRiQi(Map<String, String> paramMap);

	List<ChaQinAnPai> getChaQinByYongHuIdAndRiQi(Map<String, String> paramMap);

	List<ChaQinAnPai> selectByFuDaoYuanId(Map<String, String> paramMap);

	List<ChaQinAnPai> getChaQinByWeiShangChuan(Map<String, String> paramMap);

	List<ChaQinAnPai> getChaQinByWeiShenHe(Map<String, String> paramMap);

	List<ChaQinAnPai> getChaQinByQueQin(Map<String, String> paramMap);

	List<ChaQinAnPai> getChaQinByBuQueQin(Map<String, String> paramMap);

}