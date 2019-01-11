package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.BanJi;
import com.web.model.XueSheng;

public interface XueShengMapper {
    int deleteByPrimaryKey(Integer xueshengid);

    int insert(XueSheng record);

    int insertSelective(XueSheng record);
    
    int updatePassWdByID(String passwd,int id);
    
    String selectPassWdByID(int id);

    XueSheng selectByYongHuMingAndPasswd(Map<String, String> map);
    
    XueSheng selectByPrimaryKey(Integer xueshengid);
    
    XueSheng selectXueShengByXueXiaoXueHao(String xuexiaoxuehao);

    int updateByPrimaryKeySelective(XueSheng record);

    int updateByPrimaryKey(XueSheng record);
    
    List<Integer> getXueShengsByBanJiID(int banjiid);
    
    List<XueSheng> getAllByBanJiID(int banjiid);
    
    //List<Integer> getxueShengIDByxueXiaoXueHao(String xuexiaoxuehao);
    
    List<Integer> getTongZhuanYeXueShengByBanJiID(int banjiid, String banjiqianzhui);
    
    Integer selectBanZhangIDByBanJiID(int banjiid);
    
    List<XueSheng> selectXueShengBysuSheIDandbanJiID(Map<String, String> map);
    
    List<Integer> getXueShengIDsByBanJiIdAndSuSheId(int banjiid, int susheid);
    
    int getcountByBanJiID(Integer banjiid);//某个班学生总数
    
    List<XueSheng> getAllXueSheng(String newxuexiaoid);

	BanJi getBanJiByParam(Map<String, Object> paramMap);

	List<XueSheng> getByBanJiIDPage(Map<String, String> paramMap);

	XueSheng getKaoShiXinXi(Map<String, String> map);

	XueSheng selectXueShengByXueHao(String xueHao);
	
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