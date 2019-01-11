package com.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.web.model.LoginInfo;
import com.web.model.YongHu;

public interface YongHuService {
	YongHu selectFuDaoYuanByIdAndPasswd(Map<String, String> map);
	
	YongHu selectJiaoShiByIdAndPasswd(Map<String, String> map);
	
	YongHu selectShuJiByIdAndPasswd(Map<String, String> map);
	
	YongHu selectXueShengChuGuanLiYuanByIdAndPasswd(Map<String, String> map);
	
	YongHu selectYongHuByID(int id);
	
	List<YongHu> getAllByYuanXiID(Integer yuanxiid);
	
	int updateYongHu(YongHu record);
	
	String selectPassWdByID(int id);
	
	int updatePassWdByID(String password,int id);
	
	List<LoginInfo> selectLoginInfo(Map<String,Object> paramMap);
	
	int updateYanZhengMa(Map<String,Object> paramMap);
	
	int updateCheckCodeFor(Map<String,Object> paramMap);
	
	int updatePasswordOrYanZhengMa(Map<String,Object> paramMap);
	
	int updatePasswordById(Map<String,Object> paramMap);
	int updatePasswordKaoShiById(Map<String,Object> paramMap);
	int insertKaoshiTongBuFaSong(Map<String,Object> paramMap);
	
	 int insert(YongHu yongHu);

	int updateTouXiangByID(Map<String, String> map);

	int updateTouXiang(MultipartFile file, Map<String, String> map);
	
}
