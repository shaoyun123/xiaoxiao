package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.BeiWL;

public interface BeiWLService {
	
	List<BeiWL> getAllByuserID(Integer userid);
	
 	List<BeiWL> getAllByuserIDAndjieShouRen(Map<String, String> map);//学生get自己的事件
	
	int deleteByPrimaryKey(Integer id);
	
	int insert(Map<String, String> map);
	
	int insertforxuesheng(BeiWL beiWL);//给学生添加事件
	
	int insertforbanji(BeiWL beiWL);//给班级添加事件
	
	List<BeiWL> getAllByBanJiIdsandJuJueRenIds(Map<String, String> paramMap);
	
	BeiWL selectByPrimaryKey(Integer id);
	
	BeiWL getByHuoDongId(Integer huodongid);//通过huodongid查询备忘
	
	int updateshijianforxuesheng(BeiWL record);//修改给学生的事件
	
	int updateByPrimaryKey(BeiWL record);
	
	int updateShiJian(BeiWL record);//修改活动时，修改事件
	
 	List<BeiWL> getAllByuserIDAndLeiXingOrJieShouRen(Map<String, Object> map);

	List<BeiWL> getBeiWLByJieShouRenAndRiQi(Map<String, String> paramMap);

	List<BeiWL> getAllByJieShouRenAndRiQi(Map<String, String> paramMap);

	List<BeiWL> getByJieShouRenAndRiQi(Map<String, String> map);

	List<BeiWL> getBeiWLByRenIDAndRenLeiXingAndRiQi(Map<String, String> paramMap);

	int insert_beiwangluren(Map<String, String> mapP);

	int insert_beiwanglubanji(Map<String, String> mapP);

	int delete_beiwanglubanji(Map<String, String> map);

	int delete_beiwangluren(Map<String, String> map);

	int delete_beiwlhuizhi(Map<String, String> map);

	List<String> getAllBanJiIDByBeiWLID(String id);

	List<Map<String, Object>> getBeiWLRenByBeiWLIDAndRenIDAndRenLeiXing(Map<String, String> map);

	List<Map<String, Object>> getBeiWLHuiZhiByBeiWLIDAndRenIDAndRenLeiXing(Map<String, String> paramMap);

	int insert_beiwlhuizhi(Map<String, String> map);

}
