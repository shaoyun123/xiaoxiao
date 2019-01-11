package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.Qingjia;

public interface QingjiaService {
	
	/**
	 * 通过辅导员管理的班级IDs，查询辅导员待处理的请假记录
	 * Parameter banJiIDs
	 * return List<QingJia>
	 */
	List<Qingjia> selectFuDaoYuanDaiChuLiByBanJiID(Map<String, String> map);
	
	/**
	 * 通过书记管理的院系id，查询书记待处理的请假记录
	 * 状态为6已上报书记的记录
	 * Parameter 院系id
	 * return List<QingJia>
	 */
	List<Qingjia> selectShuJiDaiChuLiByYuanXiID(Map<String, String> map);
	
	/**
	 * 通过管理员管理的学校，查询管理员待处理的请假记录
	 * 状态为7已上报管理员的记录
	 * Parameter xuexiaoid
	 * return List<QingJia>
	 */
	List<Qingjia> selectGuanLiYuanDaiChuLiByXueXiaoID(Map<String, String> map);
	
	/**
	 * 通过管理员管理的学校，查询管理员已处理的请假记录
	 * 状态为2已通过或3已驳回且审核人为当前管理员ID
	 * Parameter xuexiaoid
	 * return List<QingJia>
	 */
	List<Qingjia> selectGuanLiYuanYiChuLiByXueXiaoIDAndPiZhunRenID(Map<String, String> map);
	
	
	/**
	 * 通过辅导员管理的班级IDs，查询辅导员以处理的请假记录
	 * 状态为2已通过或3已驳回且审核人为当前辅导员ID
	 * Parameter banJiIDs
	 * return List<QingJia>
	 */
	List<Qingjia> selectFuDaoYuanYiChuLiByBanJiID(Map<String, String> map);
	
	/**
	 * 通过书记管理的院系id，查询书记以处理的请假记录
	 * 状态为2已通过或3已驳回或7已上报且审核人为当前书记ID
	 * Parameter yuanXiID and shenPiRenID
	 * return List<QingJia>
	 */
	List<Qingjia> selectShujiYiChuLiByYuanXiIDAndShenPiRenID(Map<String, String> map);
	
	/**
	 * 通过辅导员管理的班级IDs，查询辅导员以上报的请假记录
	 * 状态为6已上报且审核人为当前辅导员ID
	 * Parameter banJiIDs，shenHeRen
	 * return List<QingJia>
	 */
	List<Qingjia> selectFuDaoYuanYiShangJiaoByBanJiID(Map<String, String> map);
	
	/**
	 * 通过辅导员管理的班级IDs，查询待销假的请假记录
	 * 状态为4待销假
	 * Parameter banJiIDs，shenHeRen
	 * return List<QingJia>
	 */
	List<Qingjia> selectFuDaoYuanDaiXiaoJiaByBanJiID(Map<String, String> map);
	
	List<Qingjia> selectAllByFuDaoYuanBanJiIDs(Map<String, String> map);
	
	List<Qingjia> selectAllByGuanLiYuanXueXiaoID(Map<String, String> map);
	
	List<Qingjia> selectAllByShuJiYuanXiID(Map<String, String> map);
	
	List<Qingjia> getAllByXueShengID(Map<String, Integer> map);
	
	List<Qingjia> getAllByXueShengID(int xueshengid);
	
	Qingjia getById(int id);
	
	int insertByXueShengID(Map<String, String> map);
	
	int insertByXueShengIDWithFile(Map<String, String> map);
	
	int updateStatusByQingjiaId(int id);
	
	int selectZhuangTaiByXueShengIDAndQingJiaId(Map<String, String> map);
	
	int selectTiJiaoCiShuByXueShengIDAndQingJiaId(Map<String, String> map);
	
	int updateById(Map<String, String> map);
	
	int xiuGaiById(Map<String, String> map);
	
	int getCountByXueShengID(int id);
	
	int selectCountFuDaoYuanDaiChuLiByBanJiIDs(String banjiids);
	
	int selectCountShuJiDaiChuLiByYuanXiID(int yuanxiid);
	
	int selectCountGuanLiYuanDaiChuLiByXueXiaoID(String xuexiaoid);
	
	int selectCountAllGuanLiYuanByXueXiaoID(String xuexiaoid);
	
	int selectCountGuanLiYuanYiChuLiByXueXiaoIDAndGuanLiYuanID(String xuexiaoid,String guanliyuanid);
	
	int selectCountShuJiYiChuLiByYuanXiIDAndPiZhunRenID(Map<String, String> map);
	
	int selectCountFuDaoYuanYiChuLiByBanJiIDsAndPiZhunRenID(Map<String, String> map);
	
	int selectCountFuDaoYuanYiShangJiaoByBanJiIDsAndPiZhunRenID(Map<String, String> map);
	
	int selectCountFuDaoYuanDaiXiaoJiaByBanJiIDs(Map<String, String> map);
	
	int selectCountAllByFuDaoYuanBanJiIDs(String banjiids);
	
	int selectCountAllByShuJiYuanXiID(String yuanxiid);
	
	int updateByQingJia(Qingjia record);

	Qingjia getByZhuangTaiAndShijianAndXueShengid(Map<String, Object> paramMap);

	List<Qingjia> getAllByXueShengIDAndZhuangtai(Map<String, Integer> map);
}
