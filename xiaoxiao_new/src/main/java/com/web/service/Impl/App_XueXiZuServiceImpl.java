package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.App_XueXiZuMapper;
import com.web.model.App_XueXiZu;
import com.web.service.App_XueXiZuService;
@Service
public class App_XueXiZuServiceImpl implements App_XueXiZuService{
	@Autowired
	private App_XueXiZuMapper app_XueXiZuMapper;
	@Override
	public List<App_XueXiZu> getXueXiZu(Integer fenZuId) {
		return app_XueXiZuMapper.getXueXiZu(fenZuId);
	}
	@Override
	public App_XueXiZu selectByPrimaryKey(Integer id) {
		return app_XueXiZuMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<App_XueXiZu> selectXueXiZu(Integer kechengid,Integer xueshengid){
		return app_XueXiZuMapper.selectXueXiZu(kechengid, xueshengid);
	}
	@Override
	public List<Map<String, Object>> selectBykaoPingIdAndXueshengId(Map<String, Object> map){
		return app_XueXiZuMapper.selectBykaoPingIdAndXueshengId(map);
	}
	@Override
	public int update(App_XueXiZu record) {
		return app_XueXiZuMapper.update(record);
	}
	@Override
	public int insert(App_XueXiZu record) {
		return app_XueXiZuMapper.insert(record);
	}
	@Override
	public List<Map<String, Object>> selectXiaoZu(Integer fenZuId){
		return app_XueXiZuMapper.selectXiaoZu(fenZuId);
	}
	@Override
	public List<App_XueXiZu> selectXueXiZuByXsID(App_XueXiZu xueXiZu){
		return app_XueXiZuMapper.selectXueXiZuByXsID(xueXiZu);
	}
	@Override
	public List<Map<String, Object>> getXueXZ(Integer fenzuid){
		return app_XueXiZuMapper.getXueXZ(fenzuid);
	}
	@Override
	public List<App_XueXiZu> getXueXiZuByKeCheng(Integer kechengid){
		return app_XueXiZuMapper.getXueXiZuByKeCheng(kechengid);
	}
	@Override
	public Map<String, Object> selectByPrimaryKey1(Integer id){
		return app_XueXiZuMapper.selectByPrimaryKey1(id);
	}
	@Override
	public int delete(Integer id) {
		return app_XueXiZuMapper.delete(id);
	}
	@Override
	public List<Map<String, Object>> getXueXiZuByKeCheng2(Integer kechengid){
		return app_XueXiZuMapper.getXueXiZuByKeCheng2(kechengid);
	}
	@Override
	public List<Map<String, Object>> selectAllXiaoZu(Map<String, Object> map){
		return app_XueXiZuMapper.selectAllXiaoZu(map);
	}
	@Override
	public List<Map<String, Object>> selectXueXiZuByXsID1(App_XueXiZu app_XueXiZu){
		return app_XueXiZuMapper.selectXueXiZuByXsID1(app_XueXiZu);
	}
	@Override
	public List<Map<String, Object>> getByXiaoZuIDs(Map<String, Object> map){
		return app_XueXiZuMapper.getByXiaoZuIDs(map);
	}
}
