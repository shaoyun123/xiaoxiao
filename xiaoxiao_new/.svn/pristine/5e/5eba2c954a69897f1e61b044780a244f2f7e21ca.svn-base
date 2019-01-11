package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.BanJi;
import com.web.model.JiaoLiu;
import com.web.model.LoginInfo;
import com.web.model.YuanXi;

public interface BanJiService {
	BanJi selectByPrimaryKey(Integer banjiid);
	
	int insert(BanJi record);
	
	int updateByPrimaryKey(BanJi record);
	
	int updateByPrimaryKeySelective(BanJi record);
	
	int deleteByPrimaryKey(Integer banjiid);
	
	List<BanJi> getAllByzhuanYeDaiMaAndruXueNianFen(String zhuanyedaima,Integer ruxuenianfen);
	
	List<BanJi> getAllByyuanXiIDAndruXueNianFen(Integer yuanxiid,Integer ruxuenianfen);
	
	List<BanJi> getAllByYuanXiID(Integer yuanxiid);
	
	List<BanJi> getAllByYuanXiIDAndZhuanYeDaiMaAndRuXueNianFen(Integer yuanxiid,String zhuanyedaima,Integer ruxuenianfen);
	
	List<BanJi> getAllByYuanXiIdAndRuXueNianFenIdAndBanJiMingCheng(Integer yuanxiid,Integer ruxuenianfen,String banjimingcheng);

	BanJi selectBanJiByBanJiMingChengAndXueXiaoID(String banjimingcheng,Integer xuexiaoid);
	
	BanJi selectByBanJiMingChengAndYuanXiIdAndRuXueNianFenId(String banjimingcheng,Integer yuanxiid,Integer ruxuenianfenid);

	JiaoLiu selectBanJiJiaoLiuList(Map<String, Object> paramMap);

	List<LoginInfo> selectBanJiZuYongHuList(Map<String, Object> paramMap);

	List<BanJi> findByXueXiaoID(String xuexiaoid);

	String findXueXiaoIDByBanJiID(Integer banjiid);

	List<BanJi> selectBanJiByZhuanYeAndXueXiao(Map<String, String> map);
	
	
}
