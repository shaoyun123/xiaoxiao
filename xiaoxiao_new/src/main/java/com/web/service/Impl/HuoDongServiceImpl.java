package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.web.dao.HuoDongMapper;
import com.web.model.HuoDong;
import com.web.service.HuoDongService;
@Service
public class HuoDongServiceImpl implements HuoDongService{
	@Autowired
	private HuoDongMapper huodongMapper;


	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return huodongMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Map<String, String> map) {
		// TODO Auto-generated method stub
		return huodongMapper.insert(map);
	}

	@Override
	public HuoDong selectByPrimaryKey(Integer huodongid) {
		// TODO Auto-generated method stub
		return huodongMapper.selectByPrimaryKey(huodongid);
	}

	@Override
	public int updateByPrimaryKey(HuoDong record) {
		// TODO Auto-generated method stub
		return huodongMapper.updateByPrimaryKey(record);
	}

	@Override
	public String getcanYuRenByhuoDongID(int huodongid) {
		// TODO Auto-generated method stub
		return huodongMapper.getcanYuRenByhuoDongID(huodongid);
	}

	@Override
	public int updateByPrimaryKeySelective(Map<String, String> map) {
		// TODO Auto-generated method stub
		return huodongMapper.updateByPrimaryKeySelective(map);
	}


	@Override
	public List<HuoDong> getAllBycanYuRenAndRiQi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return huodongMapper.getAllBycanYuRenAndRiQi(map);
	}


	@Override
	public List<HuoDong> getAllBycanYuRenAndRiQiInAppMain(Map<String, String> map) {
		// TODO Auto-generated method stub
		return huodongMapper.getAllBycanYuRenAndRiQiInAppMain(map);
	}


	@Override
	public List<HuoDong> getHistoryHuoDongBycanYuRen(Map<String, Object> map) {
		return huodongMapper.getHistoryHuoDongBycanYuRen(map);
	}


	@Override
	public int getCountHistoryHuoDongBycanYuRen(String canyuren1,String canyuren2) {
		return huodongMapper.getCountHistoryHuoDongBycanYuRen(canyuren1,canyuren2);
	}


	@Override
	public List<HuoDong> getAllBycanYuRen(String canyuren1, String canyuren2) {
		// TODO Auto-generated method stub
		return huodongMapper.getAllBycanYuRen(canyuren1, canyuren2);
	}

	@Override
	public List<HuoDong> getAllBycanYuRen2(String canyuren1, String canyuren2, String canyuren3, String canyuren4) {
		// TODO Auto-generated method stub
		return huodongMapper.getAllBycanYuRen2(canyuren1, canyuren2, canyuren3, canyuren4);

	}
	
	
	@Override
	public int insert2(HuoDong record){
		return huodongMapper.insert2(record);
	}

	
	@Override
	public int getCountHistoryHuoDong(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return huodongMapper.getCountHistoryHuoDong(paramMap);
	}
	
	
	@Override
	public List<HuoDong> getAllByTianJiaRen(String tianjiaren){
		return huodongMapper.getAllByTianJiaRen(tianjiaren);
	}
	
	
	@Override
	public List<HuoDong> getALLBycanYuRenAndBanJiIds(String canyuren1,String canyuren2,String banjiids1,String banjiids2){
		return huodongMapper.getALLBycanYuRenAndBanJiIds(canyuren1, canyuren2, banjiids1, banjiids2);
	}
	
	@Override
	public int updateBeiZhuByID(HuoDong huodong) {
		// TODO Auto-generated method stub
		return huodongMapper.updateBeiZhuByID(huodong) ;
	}

	@Override
	public List<HuoDong> getAllByRiQiAndRenIDAndRenLeiXing(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return huodongMapper.getAllByRiQiAndRenIDAndRenLeiXing(paramMap) ;
	}

	@Override
	public List<Map<String, Object>> getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return huodongMapper.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap) ;
	}

	@Override
	public List<Map<String, Object>> getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return huodongMapper.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap) ;
	}

	@Override
	public List<String> getAllBanJiIDByHuoDongID(int huodongid) {
		// TODO Auto-generated method stub
		return huodongMapper.getAllBanJiIDByHuoDongID(huodongid) ;
	}

	@Override
	public List<Map<String, Object>> getAllYaoQingRenByHuoDongIDAndRenIDAndRenLeiXing(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return huodongMapper.getAllYaoQingRenByHuoDongIDAndRenIDAndRenLeiXing(paramMap) ;
	}

	@Override
	public List<HuoDong> getAllByTianJiaRenAndLeiXing(Map<String, String> map) {
		// TODO Auto-generated method stub
		return huodongMapper.getAllByTianJiaRenAndLeiXing(map) ;
	}

	@Override
	public int insert_huodongren(Map<String, String> map) {
		// TODO Auto-generated method stub
		return huodongMapper.insert_huodongren(map) ;
	}

	@Override
	public int insert_huodongbanji(Map<String, String> mapP) {
		// TODO Auto-generated method stub
		return  huodongMapper.insert_huodongbanji(mapP) ;
	}

	@Override
	public int delete_huodongbanji(Map<String, String> map) {
		// TODO Auto-generated method stub
		return  huodongMapper.delete_huodongbanji(map) ;
	}

	@Override
	public int delete_huodongren(Map<String, String> map) {
		// TODO Auto-generated method stub
		return  huodongMapper.delete_huodongren(map) ;
	}

	@Override
	public int delete_huodongcanyuren(Map<String, String> map) {
		// TODO Auto-generated method stub
		return huodongMapper.delete_huodongcanyuren(map) ;
	}

	@Override
	public int insert_huodongjujueren(Map<String, String> map) {
		// TODO Auto-generated method stub
		return huodongMapper.insert_huodongjujueren(map) ;
	}

	@Override
	public List<HuoDong> getAllByRenIDAndRenLeiXing(Map<String, String> map) {
		// TODO Auto-generated method stub
		return huodongMapper.getAllByRenIDAndRenLeiXing(map) ;
	}

	@Override
	public int delete_huodongjujueren(Map<String, String> map) {
		// TODO Auto-generated method stub
		return huodongMapper.delete_huodongjujueren(map) ;
	}

	@Override
	public int insert_huodongcanyuren(Map<String, String> map) {
		// TODO Auto-generated method stub
		return huodongMapper.insert_huodongcanyuren(map) ;
	}
	
	@Override
	public List<HuoDong> getAllByRenAndLeiXing(Map<String, String> map){
		return huodongMapper.getAllByRenAndLeiXing(map);
	}

	@Override
	public int getCountByRenIDAndRenLeiXing(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return huodongMapper.getCountByRenIDAndRenLeiXing(paramMap) ;
	}


	
}
