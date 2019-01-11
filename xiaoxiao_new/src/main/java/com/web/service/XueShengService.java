package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.BanJi;
import com.web.model.XueSheng;

public interface XueShengService {
	
	int deleteByPrimaryKey(Integer xueshengid);
	
	XueSheng getUserById(int id);

	XueSheng selectUserByIdAndPasswd(Map<String, String> map);

	XueSheng selectXueShengByXueXiaoXueHao(String xuexiaoxuehao);
	
	int insert(XueSheng record);
	
	int insertSelective(XueSheng record);
	
	XueSheng selectByPrimaryKey(Integer xueshengid);

	List<Integer> getXueShengsByBanJiID(int banjiid);

	List<XueSheng> getAllByBanJiID(int banjiid);

	//List<Integer> getxueShengIDByxueXiaoXueHao(String xuexiaoxuehao);

	int updateInfo(XueSheng xueSheng);

	int updatePassWdByID(String passwd, int data);
	
	int updateByPrimaryKey(XueSheng record);
	
	int updateByPrimaryKeySelective(XueSheng record);

	String selectPassWdByID(int id);

	List<Integer> getTongZhuanYeXueShengByBanJiID(int banjiid, String banjiqianzhui);

	Boolean updateBanZhangByXueSheng(XueSheng record);

	List<XueSheng> selectXueShengBysuSheIDandbanJiID(Map<String, String> map);

	List<Integer> getXueShengIDsByBanJiIdAndSuSheId(int banjiid, int susheid);
	
	int getcountByBanJiID(Integer banjiid);//某个班学生总数
	
	List<XueSheng> getAllXueSheng(String newxuexiaoid);

	BanJi getBanJiByParam(Map<String, Object> paramMap);
	List<XueSheng> getByBanJiIDPage(Map<String, String> paramMap);

	XueSheng getKaoShiXinXi(Map<String, String> paramMap);

	XueSheng selectXueShengByXueHao(String xueHao);

	XueSheng selectByXueHao(String xhao);
	
	Map<String, Object> getuserinfo(String username);
	
	List<Map<String, Object>> getkaoshi(Map<String, Integer> map);
	
	Map<String, Object> getstat(Integer kaoshiid,Integer kaoshixueshengid);
	
	Map<String, Object> getfangshi(Integer kaoshiid,Integer kaoshixueshengid);
	
	Map<String, Object> getkaoshistatus(Integer kaoshiid);
	
	Map<String, Object> getcontent(Integer kaoshiid,Integer kaoshixueshengid); 
	
	Map<String, Object> getkaoti(Integer kaotiid);
	
	Map<String, Object> selectkaoshi(Integer kaoshiid);
	
	int update(Map<String, Object> map);
	
	Map<String , Object> getshenqingkaojuan(Integer kaoshixueshengid);
	
	int insertshenqingkaojuan(Map<String, Object> paraMap);
	
	Map<String, Object> getkaochangid(Map<String, Object> paraMap);
	
	int deletedajuan(Integer kaoshiid,Integer kaoshixueshengid);
	
	int insertTongBuFaSong(Map<String, Object> map);
	
	int insertKaoShiRiZhi(Map<String, Object> map);
	
	List<Map<String, Object>> getKaoShiRiZhi(Integer kaoshiid,Integer xueshengid);
	
	List<Map<String, Object>> selectByXueShengIDs(List<String>list);
	

}
