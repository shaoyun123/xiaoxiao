package com.web.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.model.DeYuPingFenFangAn;
import com.web.model.PingFenFangAn;
import com.web.model.XueQiDeYu;
import com.web.model.XueShengDeYu;

public interface DeYuService {
	List<DeYuPingFenFangAn> getAllByFangAnID(int id);
	
	List<DeYuPingFenFangAn> getListByFangAnID(int id);
	
	List<XueShengDeYu> selectAllByXueShengID(int id);
	
	XueShengDeYu selectByXueShengID(int id);
	
	List<Integer> getPingfenIDByFangAnID(int fangAnId);
	
	int getFangAnIdByXueQiDeYuID(int xueQiDeYuId);
	
	int selectXueQiDeYuIDByXueQiID(int xueQiId);
	
	int insert(XueShengDeYu record);
	
	int insertXueQiDeYu(XueQiDeYu record);
	
	int updateXueQiDeYuByXueQiDeYu(XueQiDeYu record);
	
	XueQiDeYu selectXueQiDeYuByXueQiIDAndBanJiID(int xueQiId,String banJiID1,String banJiID2);
	
	XueQiDeYu selectXueQiDeYuByXueQiID(int xueQiId);
	
	XueQiDeYu selectXueQiDeYuByXueQiIDAndShiYongBanJiIDs(int xueqiid,String shiyongbanjiid);
	
	XueShengDeYu selectByXueShengIDAndXueQiDeYuID(Map<String, String> map);
	
	XueShengDeYu selectByXueShengIDAndDeYuFenID(Map<String, String> map);
	
	XueShengDeYu selectByDeYuFenID(int deYuFenId);
	
	XueQiDeYu selectXueQiDeYuByXueQiDeYuID(int id);
	
	int updateByDeYuFenID(Map<String, String> map);
	
	XueQiDeYu selectXueQiDeYuByXueQiDeYu(XueQiDeYu record);
	
	Boolean insertNewDeYuKaoPingBiao(HttpServletRequest request,HttpServletResponse response,int fanganid);

	List<XueQiDeYu> selectXueQiDeYuByBanJiID(String string, String string2);

	List<Map<String, Object>> findShouYeXueShengDeYuByBanJiID(String string, String string2);

	PingFenFangAn getFangAnMingChengByFangAnID(Integer fanganid);

	XueQiDeYu selectXueQiDeYuByXueQiIDAndBanJiIDAndFangAnID(Integer xueqiid, String string, String string2,
			String fanganid);

	int updateXueShengDeYuZhuangTaiByList(List<XueShengDeYu> xueShengDeYus);

	int updateByPrimaryKeySelective(XueShengDeYu xueShengDeYu);

	List<XueShengDeYu> selectAllByXueQiDeYuID(Integer xueqideyuid);
}
