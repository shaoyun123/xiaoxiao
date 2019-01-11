package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.codehaus.groovy.classgen.ReturnAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.BanJiMapper;
import com.web.model.BanJi;
import com.web.model.JiaoLiu;
import com.web.model.LoginInfo;
import com.web.model.YuanXi;
import com.web.service.BanJiService;
@Service
public class BanJiServiceImpl implements BanJiService{
	@Autowired
	private BanJiMapper banJiMapper;

	@Override
	public BanJi selectByPrimaryKey(Integer banjiid) {
		// TODO Auto-generated method stub
		return banJiMapper.selectByPrimaryKey(banjiid);
	}

	@Override
	public List<BanJi> getAllByzhuanYeDaiMaAndruXueNianFen(String zhuanyedaima, Integer ruxuenianfen) {
		// TODO Auto-generated method stub
		return banJiMapper.getAllByzhuanYeDaiMaAndruXueNianFen(zhuanyedaima, ruxuenianfen);
	}

	@Override
	public List<BanJi> getAllByyuanXiIDAndruXueNianFen(Integer yuanxiid, Integer ruxuenianfen) {
		// TODO Auto-generated method stub
		return banJiMapper.getAllByyuanXiIDAndruXueNianFen(yuanxiid, ruxuenianfen);
	}

	@Override
	public List<BanJi> getAllByYuanXiID(Integer yuanxiid) {
		// TODO Auto-generated method stub
		return banJiMapper.getAllByYuanXiID(yuanxiid);
	}

	@Override
	public List<BanJi> getAllByYuanXiIDAndZhuanYeDaiMaAndRuXueNianFen(Integer yuanxiid, String zhuanyedaima,
			Integer ruxuenianfen) {
		return banJiMapper.getAllByYuanXiIDAndZhuanYeDaiMaAndRuXueNianFen(yuanxiid, zhuanyedaima, ruxuenianfen);
	}
	@Override
	public int insert(BanJi record){
		return banJiMapper.insert(record);
	}
	@Override
	public int updateByPrimaryKey(BanJi record){
		return banJiMapper.updateByPrimaryKey(record);
	}
	@Override
	public int updateByPrimaryKeySelective(BanJi record){
		return banJiMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int deleteByPrimaryKey(Integer banjiid){
		return banJiMapper.deleteByPrimaryKey(banjiid);
	}
	
	
	/* (non-Javadoc)
	 * @see com.web.service.BanJiService#selectBanJiByBanJiMingChengAndXueXiaoID(java.lang.String, java.lang.Integer)
	 */
	@Override
	public BanJi selectBanJiByBanJiMingChengAndXueXiaoID(String banjimingcheng, Integer xuexiaoid) {
		return banJiMapper.selectBanJiByBanJiMingChengAndXueXiaoID(banjimingcheng, xuexiaoid);
	}
	
	@Override
	public BanJi selectByBanJiMingChengAndYuanXiIdAndRuXueNianFenId(String banjimingcheng,Integer yuanxiid,Integer ruxuenianfenid){
		return banJiMapper.selectByBanJiMingChengAndYuanXiIdAndRuXueNianFenId(banjimingcheng, yuanxiid, ruxuenianfenid);
	}
	@Override
	public List<BanJi> getAllByYuanXiIdAndRuXueNianFenIdAndBanJiMingCheng(Integer yuanxiid,Integer ruxuenianfen,String banjimingcheng){
		return banJiMapper.getAllByYuanXiIdAndRuXueNianFenIdAndBanJiMingCheng(yuanxiid, ruxuenianfen, banjimingcheng);
	}

	@Override
	public JiaoLiu selectBanJiJiaoLiuList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return banJiMapper.selectBanJiJiaoLiuList(paramMap);
	}

	@Override
	public List<LoginInfo> selectBanJiZuYongHuList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return banJiMapper.selectBanJiZuYongHuList(paramMap);
	}

	@Override
	public List<BanJi> findByXueXiaoID(String xuexiaoid) {
		// TODO Auto-generated method stub
		return banJiMapper.findByXueXiaoID(xuexiaoid);
	}

	@Override
	public String findXueXiaoIDByBanJiID(Integer banjiid) {
		// TODO Auto-generated method stub
		return banJiMapper.findXueXiaoIDByBanJiID(banjiid);
	}

	@Override
	public List<BanJi> selectBanJiByZhuanYeAndXueXiao(Map<String, String> map) {
		// TODO Auto-generated method stub
		return banJiMapper.selectBanJiByZhuanYeAndXueXiao(map);
	}
	
		
}
