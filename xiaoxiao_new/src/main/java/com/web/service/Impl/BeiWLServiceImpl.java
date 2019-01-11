package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.BeiWLMapper;
import com.web.model.BeiWL;
import com.web.service.BeiWLService;
@Service
public class BeiWLServiceImpl implements BeiWLService{
	@Autowired
	private BeiWLMapper beiwlMapper;

	@Override
	public List<BeiWL> getAllByuserID(Integer userid) {
		// TODO Auto-generated method stub
		return beiwlMapper.getAllByuserID(userid);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return beiwlMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Map<String, String> map) {
		// TODO Auto-generated method stub
		return beiwlMapper.insert(map);
	}

	@Override
	public BeiWL selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return beiwlMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(BeiWL record) {
		// TODO Auto-generated method stub
		return beiwlMapper.updateByPrimaryKey(record);
	}

	@Override
	public int insertforxuesheng(BeiWL beiWL) {
		// TODO Auto-generated method stub
		return beiwlMapper.insertforxuesheng(beiWL);
	}

	@Override
	public int updateshijianforxuesheng(BeiWL record) {
		// TODO Auto-generated method stub
		return beiwlMapper.updateshijianforxuesheng(record);
	}

	@Override
	public List<BeiWL> getAllByuserIDAndLeiXingOrJieShouRen(Map<String, Object> map) {
		return beiwlMapper.getAllByuserIDAndLeiXingOrJieShouRen(map);
	}
//
	@Override
	public List<BeiWL> getAllByuserIDAndjieShouRen(Map<String, String> map) {
		return beiwlMapper.getAllByuserIDAndjieShouRen(map);
	}

	@Override
	public List<BeiWL> getBeiWLByJieShouRenAndRiQi(Map<String, String> paramMap) {
		return beiwlMapper.getBeiWLByJieShouRenAndRiQi(paramMap);
	}

	@Override
	public List<BeiWL> getAllByJieShouRenAndRiQi(Map<String, String> paramMap) {
		return beiwlMapper.getAllByJieShouRenAndRiQi(paramMap);
	}

	@Override
	public List<BeiWL> getByJieShouRenAndRiQi(Map<String, String> map) {
		return beiwlMapper.getByJieShouRenAndRiQi(map);
	}
	
	@Override
	public int insertforbanji(BeiWL beiWL){
		return beiwlMapper.insertforbanji(beiWL);
	}
	
	@Override
	public List<BeiWL> getAllByBanJiIdsandJuJueRenIds(Map<String, String> paramMap){
		return beiwlMapper.getAllByBanJiIdsandJuJueRenIds(paramMap); 
	}
	@Override
	public BeiWL getByHuoDongId(Integer huodongid){
		return beiwlMapper.getByHuoDongId(huodongid);
	}
	@Override
	public int updateShiJian(BeiWL record){
		return beiwlMapper.updateShiJian(record);
	}

	@Override
	public List<BeiWL> getBeiWLByRenIDAndRenLeiXingAndRiQi(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return beiwlMapper.getBeiWLByRenIDAndRenLeiXingAndRiQi(paramMap);
	}

	@Override
	public int insert_beiwangluren(Map<String, String> mapP) {
		// TODO Auto-generated method stub
		return beiwlMapper.insert_beiwangluren(mapP);
	}

	@Override
	public int insert_beiwanglubanji(Map<String, String> mapP) {
		// TODO Auto-generated method stub
		return beiwlMapper.insert_beiwanglubanji(mapP);
	}

	@Override
	public int delete_beiwanglubanji(Map<String, String> map) {
		// TODO Auto-generated method stub
		return beiwlMapper.delete_beiwanglubanji(map);
	}

	@Override
	public int delete_beiwangluren(Map<String, String> map) {
		// TODO Auto-generated method stub
		return beiwlMapper.delete_beiwangluren(map);
	}

	@Override
	public int delete_beiwlhuizhi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return beiwlMapper.delete_beiwlhuizhi(map);
	}

	@Override
	public List<String> getAllBanJiIDByBeiWLID(String id) {
		// TODO Auto-generated method stub
		return beiwlMapper.getAllBanJiIDByBeiWLID(id);
	}

	@Override
	public List<Map<String, Object>> getBeiWLRenByBeiWLIDAndRenIDAndRenLeiXing(Map<String, String> map) {
		// TODO Auto-generated method stub
		return beiwlMapper.getBeiWLRenByBeiWLIDAndRenIDAndRenLeiXing(map);
	}

	@Override
	public List<Map<String, Object>> getBeiWLHuiZhiByBeiWLIDAndRenIDAndRenLeiXing(Map<String, String> map) {
		// TODO Auto-generated method stub
		return beiwlMapper.getBeiWLHuiZhiByBeiWLIDAndRenIDAndRenLeiXing(map);
	}

	@Override
	public int insert_beiwlhuizhi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return beiwlMapper.insert_beiwlhuizhi(map);
	}
}
