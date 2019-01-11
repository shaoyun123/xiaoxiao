package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.ZhuanYeMapper;
import com.web.model.ZhuanYe;
import com.web.service.ZhuanYeService;

@Service
public class ZhuanYeServiceImpl implements ZhuanYeService{

	@Autowired
	private ZhuanYeMapper zhuanYeMapper;
	@Override
	public ZhuanYe selectZhuanYeByDaiMa(String zhuanyeid) {
		return zhuanYeMapper.selectByPrimaryKey(Integer.parseInt(zhuanyeid));
	}
	
	@Override
	public List<ZhuanYe> getZhuanYe(){
		return zhuanYeMapper.getZhuanYe();
	}
	
	@Override
	public int deleteByPrimaryKey(String daima){
		return zhuanYeMapper.deleteByPrimaryKey(daima);
	}

	@Override
	public ZhuanYe selectByPrimaryKey(Integer zhuanyeid) {
		return zhuanYeMapper.selectByPrimaryKey(zhuanyeid);
	}
	
	@Override
	public List<ZhuanYe> getXueKeMenLei(String mingcheng){
		return zhuanYeMapper.getXueKeMenLei(mingcheng);
	}
	
	@Override
	public List<ZhuanYe> getXueKe(String daima,String mingcheng1,String mingcheng2){
		return zhuanYeMapper.getXueKe(daima, mingcheng1, mingcheng2);
	}
	
	@Override
	public List<ZhuanYe> selectZhuanYeByXueKe(String daima,String mingcheng1,String mingcheng2){
		return zhuanYeMapper.selectZhuanYeByXueKe(daima, mingcheng1, mingcheng2);
	}
	@Override
	public int deleteYuanXiZhuanYeByPrimaryKey(Integer id){
		return zhuanYeMapper.deleteYuanXiZhuanYeByPrimaryKey(id);
	}
	@Override
	public int insertYuanXiZhuanYe(Map<String, Integer> map){
		return zhuanYeMapper.insertYuanXiZhuanYe(map);
	}
	@Override
    public int insertYuanXiZhuanYeSelective(Map<String, Integer> map){
		return zhuanYeMapper.insertYuanXiZhuanYeSelective(map);
	}
	@Override
    public Map<String, Integer> selectYuanXiZhuanYeByPrimaryKey(Integer id){
		return zhuanYeMapper.selectYuanXiZhuanYeByPrimaryKey(id);
	}
	@Override
	public Map<String, Integer> selectByZhuanYeId(Integer zhuanyeid,String zhuanYeId){
		return zhuanYeMapper.selectByZhuanYeId(zhuanyeid,zhuanYeId);
	}
	@Override
    public int updateYuanXiZhuanYeByPrimaryKeySelective(Map<String, Integer> map){
		return zhuanYeMapper.updateYuanXiZhuanYeByPrimaryKeySelective(map);
	}
	@Override
	public int updateYuanXiZhuanYeByPrimaryKey(Map<String, Integer> map){
		return zhuanYeMapper.updateYuanXiZhuanYeByPrimaryKey(map);
	}
	@Override
    public List<Map<String, Integer>> getAllByYuanXiID(Integer yuanxiid){
		return zhuanYeMapper.getAllByYuanXiID(yuanxiid);
	}

	@Override
	public List<ZhuanYe> findByXueXiaoID(String xuexiaoid) {
		// TODO Auto-generated method stub
		return zhuanYeMapper.findByXueXiaoID(xuexiaoid);
	}
}
