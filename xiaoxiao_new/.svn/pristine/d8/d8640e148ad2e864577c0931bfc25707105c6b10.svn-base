package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.YiJianXiang;

public interface YiJianXiangService {
	
	int insert(YiJianXiang record);//有图插入
	
	int insert2(YiJianXiang record);//无图插入
	
	List<YiJianXiang> getAllByxueShengIDandtiJiaoZhuangTai(Integer xueshengid,String tijiaozhuangtai);

	YiJianXiang selectByPrimaryKey(Integer yijianid);
	
	int deleteByPrimaryKey(Integer yijianid);
	
	int updateByPrimaryKey(YiJianXiang record);//有图更新
	
	int updateByPrimaryKey2(YiJianXiang record);//无图更新
	
	int updatedianZanByByPrimaryKey(YiJianXiang record);//更新点赞量、点赞人
	
	int updatepingLunLiangByPrimaryKey(YiJianXiang record);//更新评论量
	
	int updatechuliByPrimaryKey(YiJianXiang record);//更新辅导员处理，包括处理标记、处理人，公布状态、评论量，可见人范围，可见学生id，可见教师id
	
	List<YiJianXiang> getAllByXSandBQandNM(Map<String, String> map);//根据可见学生ID、标签、匿名状态、提交状态查找
	
	List<YiJianXiang> getAllByXSandBQandNMandpage(Map<String, String> map);//根据可见学生ID、标签、匿名状态、提交状态查找
	
	List<YiJianXiang> getAllByXSandBQandBJandNM(Map<String, String> map);//根据可见学生ID、标签、班级、匿名状态、提交状态查找
	
	List<YiJianXiang> getAllByXSandBQandBJandNMandpage(Map<String, String> map);
	
	List<YiJianXiang> getAllByXSandBQandZYandNM(Map<String, String> map);//根据可见学生ID、标签、专业、匿名状态、提交状态查找
	
	List<YiJianXiang> getAllByXSandBQandZYandNMandpage(Map<String, String> map);
	
	List<YiJianXiang> getAllByXSandBQandYXandNM(Map<String, String> map);//根据可见学生ID、标签、院系、匿名状态、提交状态查找
	
	List<YiJianXiang> getAllByXSandBQandYXandNMandpage(Map<String, String> map);
	
	List<YiJianXiang> getAllByJSandBQandNM(Map<String, String> map);//辅导员、、根据标签、匿名状态、提交状态、公布状态查找
	
	List<YiJianXiang> getAllByjieShouRenIDandtiJiaoZhuangTai(Map<String, String> map);//根据接收人id和提交状态获取
	
	int getCountByXSandBQandNM(Map<String, String> map);
	
	int getCountByXSandBQandBJandNM(Map<String, String> map);
	
	int getCountByXSandBQandZYandNM(Map<String, String> map);
	
	int getCountByXSandBQandYXandNM(Map<String, String> map);
	
	int getCountByxueShengIDandtiJiaoZhuangTai(Integer xueshengid,String tijiaozhuangtai);
	
	List<YiJianXiang> getAllByxueShengIDandtiJiaoZhuangTaiandPage(Map<String, Integer> map);

	List<YiJianXiang> getYiJianXiangByjieShouRenIDAndRiQi(Map<String, String> paramMap);
}
