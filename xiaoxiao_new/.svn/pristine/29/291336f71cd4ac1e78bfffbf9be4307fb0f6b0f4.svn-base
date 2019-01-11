package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.JLNR;

public interface JLNRService {

	int updateByPrimaryKey(Map<String, String> map);
	
	int deleteByanPaiID(Integer anpaiid);
	
	List<JLNR> getALLByxueShengID(Integer xueshengid);
	
	String getxueShengShangChuanByjiaoLiuID(Integer jiaoliuid);
	
	String getxueShengShangChuanByanPaiID(Integer anpaiid);
	
	String getfuDaoYuanShenHeByjiaoLiuID(Integer jiaoliuid);
	
	String getjiaoLiuMingChengByjiaoLiuID(Integer jiaoliuid);
	
	String getshangchuanzhuangtaiByjiaoLiuID(Integer jiaoliuid);
	
	int updateshangchuanzhuangtaiByjiaoLiuID(Map<String, String> map);
	
	int updatexueshengshangchuanByanPaiID(Map<String, String> map);
	
	int updatexueshengshangchuanByjiaoLiuID(Map<String, String> map);
	
	JLNR selectByPrimaryKey(Integer jiaoliuid);
	
	JLNR selectByanPaiID(Integer anpaiid);
	
	int insert(JLNR record);
	
	int updateByanPaiID(JLNR record);
	
	List<JLNR> getAllByanPaiID(Integer anpaiid);
	
	int updatefuDaoYuanShenHeByjiaoLiuID(JLNR record);
	
	int getCountByxueShengIDAndUnaudited(Integer xueshengid);
	
	int getCountByxueShengIDAndPass(Integer xueshengid);
	
	List<JLNR> getALLByxueShengIDAndLimitAndPass(Integer xueshengid,int start,int stop);
	
	List<JLNR> getALLByxueShengIDAndLimitAndUnaudited(Integer xueshengid,int start,int stop);
}
