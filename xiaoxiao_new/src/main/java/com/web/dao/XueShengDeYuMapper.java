package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.XueShengDeYu;

public interface XueShengDeYuMapper {
    int deleteByPrimaryKey(Integer deyufenid);

    int insert(XueShengDeYu record);

    int insertSelective(XueShengDeYu record);

    List<XueShengDeYu> selectAllByXueShengID(Integer id);
    
    XueShengDeYu selectByXueShengID(Integer id);

    int updateByDeYuFenID(Map<String, String> map);
    
    XueShengDeYu selectByXueShengIDAndXueQiDeYuID(Map<String, String> map);
    
    XueShengDeYu selectByXueShengIDAndDeYuFenID(Map<String, String> map);
    
    XueShengDeYu selectByDeYuFenID(int deYuFenId);

	int updateByPrimaryKeySelective(XueShengDeYu xueShengDeYu);

	List<XueShengDeYu> selectAllByXueQiDeYuID(Integer xueqideyuid);
}