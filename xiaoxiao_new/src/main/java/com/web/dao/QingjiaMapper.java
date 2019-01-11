package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.model.Qingjia;

public interface QingjiaMapper {
    int deleteByPrimaryKey(Integer qingjiaid);

    int insertByXueShengID(Map<String, String> map);
    
    int insertByXueShengIDWithFile(Map<String, String> map);

    int insertSelective(Qingjia record);

    int selectZhuangTaiByXueShengIDAndQingJiaId(Map<String, String> map);
    
    int selectTiJiaoCiShuByXueShengIDAndQingJiaId(Map<String, String> map);
    
    int updateStatusByQingjiaId(int id);
    
    int updateById(Map<String, String> map);
    
    int xiuGaiById(Map<String, String> map);
    
    int getCountByXueShengID(int id);
    
    int updateByQingJia(Qingjia record);
    
    int selectCountFuDaoYuanDaiChuLiByBanJiIDs(String banjiids);
    
    int selectCountFuDaoYuanYiChuLiByBanJiIDsAndPiZhunRenID(Map<String, String> map);
    
    int selectCountFuDaoYuanYiShangJiaoByBanJiIDsAndPiZhunRenID(Map<String, String> map);
    
    int selectCountFuDaoYuanDaiXiaoJiaByBanJiIDs(Map<String, String> map);
    
    int selectCountShuJiDaiChuLiByYuanXiID(int yuanxiid);
    
    int selectCountShuJiYiChuLiByYuanXiIDAndPiZhunRenID(Map<String, String> map);
    
    List<Qingjia> selectFuDaoYuanDaiXiaoJiaByBanJiID(Map<String, String> map);
    
    List<Qingjia> selectShuJiDaiChuLiByYuanXiID(Map<String, String> map);
    
    List<Qingjia> selectShujiYiChuLiByYuanXiIDAndShenPiRenID(Map<String, String> map);
    
    List<Qingjia> selectFuDaoYuanYiShangJiaoByBanJiID(Map<String, String> map);
    
    List<Qingjia> selectGuanLiYuanDaiChuLiByXueXiaoID(Map<String, String> map);
    
    List<Qingjia> selectGuanLiYuanYiChuLiByXueXiaoIDAndPiZhunRenID(Map<String, String> map);
    
    List<Qingjia> selectAllByFuDaoYuanBanJiIDs(Map<String, String> map);
    
    List<Qingjia> selectAllByShuJiYuanXiID(Map<String, String> map);
    
    List<Qingjia> selectAllByGuanLiYuanXueXiaoID(Map<String, String> map);
    
    int selectCountAllByShuJiYuanXiID(String yuanxiid);
    
    int selectCountAllGuanLiYuanByXueXiaoID(String xuexiaoid);
    
    int selectCountAllByFuDaoYuanBanJiIDs(String banjiids);
    
    int selectCountGuanLiYuanDaiChuLiByXueXiaoID(String xuexiaoid);
    
    int selectCountGuanLiYuanYiChuLiByXueXiaoIDAndGuanLiYuanID(String xuexiaoid, String guanliyuanid);
    
    List<Qingjia> getAllByXueShengID(Map<String, Integer> map);
    
    List<Qingjia> getQingJiaByXueShengID(int xueshengid);
    
    Qingjia getById(int id);
    
    List<Qingjia> selectFuDaoYuanDaiChuLiByBanJiID(Map<String, String> map);
    
    List<Qingjia> selectFuDaoYuanYiChuLiByBanJiID(Map<String, String> map);

	Qingjia getByZhuangTaiAndShijianAndXueShengid(Map<String, Object> paramMap);

	List<Qingjia> getAllByXueShengIDAndZhuangtai(Map<String, Integer> map);
}