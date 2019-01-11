package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.QingjiaMapper;
import com.web.model.Qingjia;
import com.web.service.QingjiaService;

@Service
public class QingjiaServiceImpl implements QingjiaService{

	@Autowired
	private QingjiaMapper qingjiaMapper;

	@Override
	public List<Qingjia> getAllByXueShengID(Map<String, Integer> map) {
		return qingjiaMapper.getAllByXueShengID(map);
	}

	@Override
	public Qingjia getById(int id) {
		return qingjiaMapper.getById(id);
	}

	@Override
	public int insertByXueShengID(Map<String, String> map) {
		return qingjiaMapper.insertByXueShengID(map);
	}

	@Override
	public int insertByXueShengIDWithFile(Map<String, String> map) {
		return qingjiaMapper.insertByXueShengIDWithFile(map);
	}

	@Override
	public int updateStatusByQingjiaId(int id) {
		return qingjiaMapper.updateStatusByQingjiaId(id);
	}

	@Override
	public int selectZhuangTaiByXueShengIDAndQingJiaId(Map<String, String> map) {
		return qingjiaMapper.selectZhuangTaiByXueShengIDAndQingJiaId(map);
	}

	@Override
	public int selectTiJiaoCiShuByXueShengIDAndQingJiaId(Map<String, String> map) {
		return qingjiaMapper.selectTiJiaoCiShuByXueShengIDAndQingJiaId(map);
	}

	@Override
	public int updateById(Map<String, String> map) {
		return qingjiaMapper.updateById(map);
	}

	@Override
	public int xiuGaiById(Map<String, String> map) {
		return qingjiaMapper.xiuGaiById(map);
	}

	@Override
	public int getCountByXueShengID(int id) {
		return qingjiaMapper.getCountByXueShengID(id);
	}

	@Override
	public List<Qingjia> getAllByXueShengID(int xueshengid) {
		return qingjiaMapper.getQingJiaByXueShengID(xueshengid);
	}

	@Override
	public List<Qingjia> selectFuDaoYuanDaiChuLiByBanJiID(Map<String, String> map) {
		return qingjiaMapper.selectFuDaoYuanDaiChuLiByBanJiID(map);
	}

	@Override
	public int selectCountFuDaoYuanDaiChuLiByBanJiIDs(String banjiids) {
		return qingjiaMapper.selectCountFuDaoYuanDaiChuLiByBanJiIDs(banjiids);
	}

	@Override
	public int updateByQingJia(Qingjia record) {
		return qingjiaMapper.updateByQingJia(record);
	}

	@Override
	public List<Qingjia> selectFuDaoYuanYiChuLiByBanJiID(Map<String, String> map) {
		return qingjiaMapper.selectFuDaoYuanYiChuLiByBanJiID(map);
	}

	@Override
	public int selectCountFuDaoYuanYiChuLiByBanJiIDsAndPiZhunRenID(Map<String, String> map) {
		return qingjiaMapper.selectCountFuDaoYuanYiChuLiByBanJiIDsAndPiZhunRenID(map);
	}

	@Override
	public List<Qingjia> selectFuDaoYuanYiShangJiaoByBanJiID(Map<String, String> map) {
		return qingjiaMapper.selectFuDaoYuanYiShangJiaoByBanJiID(map);
	}

	@Override
	public int selectCountFuDaoYuanYiShangJiaoByBanJiIDsAndPiZhunRenID(Map<String, String> map) {
		return qingjiaMapper.selectCountFuDaoYuanYiShangJiaoByBanJiIDsAndPiZhunRenID(map);
	}

	@Override
	public List<Qingjia> selectFuDaoYuanDaiXiaoJiaByBanJiID(Map<String, String> map) {
		return qingjiaMapper.selectFuDaoYuanDaiXiaoJiaByBanJiID(map);
	}

	@Override
	public int selectCountFuDaoYuanDaiXiaoJiaByBanJiIDs(Map<String, String> map) {
		return qingjiaMapper.selectCountFuDaoYuanDaiXiaoJiaByBanJiIDs(map);
	}

	@Override
	public List<Qingjia> selectShuJiDaiChuLiByYuanXiID(Map<String, String> map) {
		return qingjiaMapper.selectShuJiDaiChuLiByYuanXiID(map);
	}

	@Override
	public int selectCountShuJiDaiChuLiByYuanXiID(int yuanxiid) {
		return qingjiaMapper.selectCountShuJiDaiChuLiByYuanXiID(yuanxiid);
	}

	@Override
	public List<Qingjia> selectShujiYiChuLiByYuanXiIDAndShenPiRenID(Map<String, String> map) {
		return qingjiaMapper.selectShujiYiChuLiByYuanXiIDAndShenPiRenID(map);
	}

	@Override
	public int selectCountShuJiYiChuLiByYuanXiIDAndPiZhunRenID(Map<String, String> map) {
		return qingjiaMapper.selectCountShuJiYiChuLiByYuanXiIDAndPiZhunRenID(map);
	}

	@Override
	public List<Qingjia> selectGuanLiYuanDaiChuLiByXueXiaoID(Map<String, String> map) {
		return qingjiaMapper.selectGuanLiYuanDaiChuLiByXueXiaoID(map);
	}

	@Override
	public int selectCountGuanLiYuanDaiChuLiByXueXiaoID(String xuexiaoid) {
		return qingjiaMapper.selectCountGuanLiYuanDaiChuLiByXueXiaoID(xuexiaoid);
	}

	@Override
	public List<Qingjia> selectGuanLiYuanYiChuLiByXueXiaoIDAndPiZhunRenID(Map<String, String> map) {
		return qingjiaMapper.selectGuanLiYuanYiChuLiByXueXiaoIDAndPiZhunRenID(map);
	}

	@Override
	public int selectCountGuanLiYuanYiChuLiByXueXiaoIDAndGuanLiYuanID(String xuexiaoid, String guanliyuanid) {
		return qingjiaMapper.selectCountGuanLiYuanYiChuLiByXueXiaoIDAndGuanLiYuanID(xuexiaoid, guanliyuanid);
	}

	@Override
	public List<Qingjia> selectAllByFuDaoYuanBanJiIDs(Map<String, String> map) {
		return qingjiaMapper.selectAllByFuDaoYuanBanJiIDs(map);
	}

	@Override
	public int selectCountAllByFuDaoYuanBanJiIDs(String banjiids) {
		return qingjiaMapper.selectCountAllByFuDaoYuanBanJiIDs(banjiids);
	}

	@Override
	public List<Qingjia> selectAllByShuJiYuanXiID(Map<String, String> map) {
		return qingjiaMapper.selectAllByShuJiYuanXiID(map);
	}

	@Override
	public int selectCountAllByShuJiYuanXiID(String yuanxiid) {
		return qingjiaMapper.selectCountAllByShuJiYuanXiID(yuanxiid);
	}

	@Override
	public List<Qingjia> selectAllByGuanLiYuanXueXiaoID(Map<String, String> map) {
		return qingjiaMapper.selectAllByGuanLiYuanXueXiaoID(map);
	}

	@Override
	public int selectCountAllGuanLiYuanByXueXiaoID(String xuexiaoid) {
		return qingjiaMapper.selectCountAllGuanLiYuanByXueXiaoID(xuexiaoid);
	}

	@Override
	public Qingjia getByZhuangTaiAndShijianAndXueShengid(Map<String, Object> paramMap) {
		return qingjiaMapper.getByZhuangTaiAndShijianAndXueShengid(paramMap);
	}

	@Override
	public List<Qingjia> getAllByXueShengIDAndZhuangtai(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return qingjiaMapper.getAllByXueShengIDAndZhuangtai(map);
	}


}
