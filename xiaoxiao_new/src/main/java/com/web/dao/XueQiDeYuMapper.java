package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.XueQiDeYu;

public interface XueQiDeYuMapper {
	int getFangAnIdByXueQiDeYuID(int xueQiDeYuId);

    int insert(XueQiDeYu record);

    int insertSelective(XueQiDeYu record);

    XueQiDeYu selectXueQiDeYuByXueQiIDAndBanJiID(int xueQiId,String banJiID1,String banJiID2);
    
    XueQiDeYu selectXueQiDeYuByXueQiID(int xueQiId);

    int updateByPrimaryKeySelective(XueQiDeYu record);

    int updateByPrimaryKey(XueQiDeYu record);
    
    int selectXueQiDeYuIDByXueQiID(int xueQiId);
    
    XueQiDeYu selectXueQiDeYuByXueQiDeYuID(int id);
    
    int updateXueQiDeYuByXueQiDeYu(XueQiDeYu record);
    
    XueQiDeYu selectXueQiDeYuByXueQiDeYu(XueQiDeYu record);
    
    XueQiDeYu selectXueQiDeYuByXueQiIDAndShiYongBanJiIDs(int xueqiid, String shiyongbanjiid);

	List<XueQiDeYu> selectXueQiDeYuByBanJiID(String string, String string2);

	List<Map<String, Object>> findShouYeXueShengDeYuByBanJiID(String string, String string2);

	XueQiDeYu selectXueQiDeYuByXueQiIDAndBanJiID(Integer xueQiId, String banJiID1, String banJiID2, String fanganid);

	XueQiDeYu selectXueQiDeYuByXueQiIDAndBanJiIDAndFangAnID(Integer xueQiId, String banJiID1, String banJiID2,
			String fanganid);
    
}