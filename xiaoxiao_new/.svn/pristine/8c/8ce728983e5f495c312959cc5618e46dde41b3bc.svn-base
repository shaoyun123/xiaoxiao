package com.web.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.web.model.ChaQinAnPai;
import com.web.model.ChaQinJieGuo;
import com.web.model.YongHu;

public interface ChaQinService {
	List<ChaQinAnPai> selectByYongHuId(int yonghuid);
	
	List<ChaQinAnPai> selectByYongHuIdAndRiQi(Map<String,Object> paramMap);
	
	int insertChaQinAnPai(ChaQinAnPai anPai);
	
	Boolean insertChaQinAnPaiList(List<ChaQinAnPai> anPais,HttpServletRequest request);
	
	int insertChaQinJieGuo(ChaQinJieGuo record);
	
	int deleteChaQinJieGuoByanPaiIDAndsuSheID(Map<String, Integer> map);
	
	ChaQinAnPai selectChaQinAnPaiByPrimaryKey(Integer anpaiid);
	
	List<ChaQinJieGuo> selectChaQinJieGuoByanPaiIDAndsuSheID(Map<String, Integer> map);
	
	List<ChaQinJieGuo> selectByAnPaiID(Integer anpaiid);
	
	ChaQinJieGuo selectByByPrimaryKey(Integer id);
	
	Boolean updateChaQinJieGuoByXueShengIDsAndJieGuoList(List<Integer> list,List<ChaQinJieGuo> chaQinJieGuos);

	int selectChaQinTotal(Map<String, String> paramMap);

	int selectWanChengChaQin(Map<String, String> paramMap);

	List<ChaQinAnPai> getByYongHuIdAndRiQi(Map<String, String> paramMap);

	ChaQinJieGuo selectByAnPaiIDAndXueShengID(Map<String, String> paramMap);

	int getTotalCount(Map<String, String> paramMap);

	List<ChaQinAnPai> getChaQinByYongHuIdAndRiQi(Map<String, String> paramMap);

	List<ChaQinAnPai> selectByFuDaoYuanId(Map<String, String> paramMap);

	List<ChaQinAnPai> getChaQinByWeiShangChuan(Map<String, String> paramMap);

	List<ChaQinAnPai> getChaQinByWeiShenHe(Map<String, String> paramMap);

	List<ChaQinAnPai> getChaQinByQueQin(Map<String, String> paramMap);

	List<ChaQinAnPai> getChaQinByBuQueQin(Map<String, String> paramMap);

	Boolean insertAppChaQinAnPaiList(List<ChaQinAnPai> list, YongHu user);
}
