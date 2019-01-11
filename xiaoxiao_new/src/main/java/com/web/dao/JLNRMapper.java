package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.JLNR;

public interface JLNRMapper {
    int deleteByPrimaryKey(Integer jiaoliuid);
    
    int deleteByanPaiID(Integer anpaiid);

    int insert(JLNR record);

    int insertSelective(JLNR record);
    
    JLNR selectByPrimaryKey(Integer jiaoliuid);

    int updateByPrimaryKeySelective(JLNR record);

    int updateByPrimaryKey(Map<String, String> map);
	
	List<JLNR> getALLByxueShengID(Integer xueshengid);
	
	String getxueShengShangChuanByjiaoLiuID(Integer jiaoliuid);
	
	String getxueShengShangChuanByanPaiID(Integer anpaiid);
	
	String getfuDaoYuanShenHeByjiaoLiuID(Integer jiaoliuid);
	
	String getjiaoLiuMingChengByjiaoLiuID(Integer jiaoliuid);
	
	String getshangchuanzhuangtaiByjiaoLiuID(Integer jiaoliuid);
	
	int updateshangchuanzhuangtaiByjiaoLiuID(Map<String, String> map);

	int updatexueshengshangchuanByanPaiID(Map<String, String> map);

	int updatexueshengshangchuanByjiaoLiuID(Map<String, String> map);

	JLNR selectByanPaiID(Integer anpaiid);
	
	int updateByanPaiID(JLNR record);
	
	List<JLNR> getAllByanPaiID(Integer anpaiid);
	
	int updatefuDaoYuanShenHeByjiaoLiuID(JLNR record);
	
	int getCountByxueShengIDAndUnaudited(Integer xueshengid);
	
	int getCountByxueShengIDAndPass(Integer xueshengid);
	
	List<JLNR> getALLByxueShengIDAndLimitAndPass(Integer xueshengid,int start,int stop);
	
	List<JLNR> getALLByxueShengIDAndLimitAndUnaudited(Integer xueshengid,int start,int stop);
	
}