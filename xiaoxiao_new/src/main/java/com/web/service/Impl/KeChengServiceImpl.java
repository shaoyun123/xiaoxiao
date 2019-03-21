package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.KeChengMapper;
import com.web.model.BeiZhu;
import com.web.model.JiaoLiu;
import com.web.model.KeCheng;
import com.web.model.LoginInfo;
import com.web.service.KeChengService;
@Service
public class KeChengServiceImpl  implements KeChengService{
	@Autowired
	private KeChengMapper kechengMapper;
	@Override
	public List<KeCheng> getAllByxueShengID(String xueshengid1,String xueshengid2) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllByxueShengID(xueshengid1,xueshengid2);
	}
	/*@Override
	public List<KeCheng> getAllByxueShengIDandXueNianXueQi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllByxueShengIDandXueNianXueQi(map);
	}*/
	@Override
	public int insertSelective(KeCheng record) {
		// TODO Auto-generated method stub
		return kechengMapper.insertSelective(record);
	}
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return kechengMapper.deleteByPrimaryKey(id);
	}
	@Override
	public KeCheng selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return kechengMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKey(KeCheng record) {
		// TODO Auto-generated method stub
		return kechengMapper.updateByPrimaryKey(record);
	}
	@Override
	public List<KeCheng> getAllByxueShengIDandshangKeRiQi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllByxueShengIDandshangKeRiQi(map);
	}
	
	/*@Override
	public List<KeCheng> getAllBykaiFangYuanXiandxueNianxueQi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllBykaiFangYuanXiandxueNianxueQi(map);
	}*/
	
	@Override
	public int updatecanYuRenByPrimaryKey(KeCheng record) {
		// TODO Auto-generated method stub
		return kechengMapper.updatecanYuRenByPrimaryKey(record);
	}
	@Override
	public List<KeCheng> getAllByTianJiaRen(String tianjiaren) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllByTianJiaRen(tianjiaren);
	}
	@Override
	public int updateByPrimaryKey2(KeCheng record) {
		// TODO Auto-generated method stub
		return kechengMapper.updateByPrimaryKey2(record);
	}
	@Override
	public int insert_addkecheng_zizhu(KeCheng record) {
		// TODO Auto-generated method stub
		return kechengMapper.insert_addkecheng_zizhu(record);
	}
	@Override
	public List<KeCheng> getAllBycanYuRenandxueNianxueQi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllBycanYuRenandxueNianxueQi(map);
	}
	@Override
	public int updateByPrimaryKey3(KeCheng record) {
		// TODO Auto-generated method stub
		return kechengMapper.updateByPrimaryKey3(record);
	}
	@Override
	public int updateshangKeRiQiByPrimaryKey(KeCheng record) {
		// TODO Auto-generated method stub
		return kechengMapper.updateshangKeRiQiByPrimaryKey(record);
	}
	@Override
	public List<KeCheng> getAllBytianJiaRenandxueNianxueQi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllBytianJiaRenandxueNianxueQi(map);
	}
	@Override
	public List<KeCheng> getAllBybanJiIDs(String banjiids) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllBybanJiIDs(banjiids);
	}
	@Override
	public List<KeCheng> getAllBybanJiID(String banjiid1,String banjiid2) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllBybanJiID(banjiid1,banjiid2);
	}
	@Override
	public List<KeCheng> getAllByxiaoQuandshangKeJiaoShiandzhouCi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllByxiaoQuandshangKeJiaoShiandzhouCi(map);
	}
	@Override
	public int updatebanJiIDsandcanYuRenByPrimaryKey(KeCheng record) {
		// TODO Auto-generated method stub
		return kechengMapper.updatebanJiIDsandcanYuRenByPrimaryKey(record);
	}
	@Override
	public List<KeCheng> getAllBybanJiIDsAndxuanXiuIDsAndmianXiuIDs(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllBybanJiIDsAndxuanXiuIDsAndmianXiuIDs(map);
	}
	@Override
	public List<KeCheng> getAllBybanJiIDsandmianXiuIDsandxueNianxueQi(Map<String, String> map) {
		return kechengMapper.getAllBybanJiIDsandmianXiuIDsandxueNianxueQi(map);
	}
	
	@Override
	public int updateByKeDaiBiao(KeCheng record) {
		// TODO Auto-generated method stub
		return kechengMapper.updateByKeDaiBiao(record);
	}
	@Override
	public List<KeCheng> selectByKeChengId(Integer kechengid){
		return kechengMapper.selectByKeChengId(kechengid);
	}

	@Override
	public List<KeCheng> getAllBybanJiIDsAndxuanXiuIDsAndmianXiuIDsAndshangKeRiQi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllBybanJiIDsAndxuanXiuIDsAndmianXiuIDsAndshangKeRiQi(map);
	}
	
	@Override
	public int updateBJandXXandMXByPrimaryKey(KeCheng keCheng) {
		// TODO Auto-generated method stub
		return kechengMapper.updateBJandXXandMXByPrimaryKey(keCheng);
	}
	
	public List<JiaoLiu> selectJiaoLiuList(Map<String, Object> map){
		return kechengMapper.selectJiaoLiuList(map);
	}
	@Override
	public List<KeCheng> getAllByrenKeLaoShiID(String jiaoshi) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllByrenKeLaoShiID(jiaoshi);
	}
	
	public List<JiaoLiu> selectPingBiList(Map<String, Object> map){
		return kechengMapper.selectPingBiList(map);
	}

	public List<LoginInfo> selectZuYongHuList(Map<String, Object> map){
		return kechengMapper.selectZuYongHuList(map);
	}
	
	public void deletePingBiXinXiById(Map<String, Object> map){
		kechengMapper.deletePingBiXinXiById(map);
	}
	
	public int insertPingBiXinXi(Map<String, Object> map){
		return kechengMapper.insertPingBiXinXi(map);
	}
	@Override
	public List<KeCheng> getKeChengBycanYuRenAndRiQi(Map<String, String> paramMap) {
		return kechengMapper.getKeChengBycanYuRenAndRiQi(paramMap);
	}
	@Override
	public int updatedanShuangZhouShuoMingByPrimaryKey(KeCheng keCheng2) {
		// TODO Auto-generated method stub
		return kechengMapper.updatedanShuangZhouShuoMingByPrimaryKey(keCheng2);
	}
	@Override
	public List<KeCheng> selectAllByRiQiAndBanji(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return kechengMapper.selectAllByRiQiAndBanji(paramMap);
	}
	@Override
	public List<KeCheng> getAllByxueShengIDandRiQiandBanJi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllByxueShengIDandRiQiandBanJi(map) ;
	}
	@Override
	public int insert_danshaungzhou(KeCheng keCheng) {
		// TODO Auto-generated method stub
		return kechengMapper.insert_danshaungzhou(keCheng) ;
	}
	@Override
	public int updateByDanShuangZhou(KeCheng keCheng) {
		// TODO Auto-generated method stub
		return kechengMapper.updateByDanShuangZhou(keCheng);
	}
	@Override
	public List<KeCheng> getAllByShangKeJiaoShiAndXuenianAndXueqi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllByShangKeJiaoShiAndXuenianAndXueqi(map);
	}
	@Override
	public int updateKeChengBeiZhuID(KeCheng kecheng) {
		// TODO Auto-generated method stub
		return kechengMapper.updateKeChengBeiZhuID(kecheng);
	}
	@Override
	public List<KeCheng> getAllBybanJiIDsAndXuenianAndXueqi(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllBybanJiIDsAndXuenianAndXueqi(map);
	}
	@Override
	public List<Map<String, Object>> getByOneBanJiID(String banjiid) {
		// TODO Auto-generated method stub
		return kechengMapper.getByOneBanJiID(banjiid);
	}
	@Override
	public List<Map<String, Object>> getByBanJiIDAndXueShengID(int banjiid2, String xueshengid) {
		// TODO Auto-generated method stub
		return kechengMapper.getByBanJiIDAndXueShengID( banjiid2, xueshengid);
	}
	@Override
	public List<Map<String, Object>> getByAllBanJiID(String parse) {
		// TODO Auto-generated method stub
		return kechengMapper.getByAllBanJiID(parse);
	}
	@Override
	public List<String> getByAllBanJiIDByID(String string) {
		// TODO Auto-generated method stub
		return kechengMapper.getByAllBanJiIDByID(string);
	}
	@Override
	public List<String> getAllMianXiuIDByID(String kechengid) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllMianXiuIDByID(kechengid);
	}
	@Override
	public List<String> getAllXuanXiuIDByID(String kechengid) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllXuanXiuIDByID(kechengid);
	}
	@Override
	public List<Map<String, Object>> getByJiaoShiIDAndZhouCiAndXueQiID(Map<String, String> map2) {
		// TODO Auto-generated method stub
		return  kechengMapper.getByJiaoShiIDAndZhouCiAndXueQiID(map2);
	}
	@Override
	public int insert_shangkebanji(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.insert_shangkebanji(map);
	}
	@Override
	public int insert_shangkexuanxiuren(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.insert_shangkexuanxiuren(map);
	}
	@Override
	public int insert_shangkeshijian(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.insert_shangkeshijian(map);
	}
	@Override
	public int delete_shangkexuanxiuren(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.delete_shangkexuanxiuren(map);
	}
	@Override
	public List<Map<String, Object>> getShangKeShiJianByID(String id) {
		// TODO Auto-generated method stub
		return kechengMapper.getShangKeShiJianByID(id);
	}
	@Override
	public int delete_allshangkebanjiByID(String id) {
		// TODO Auto-generated method stub
		return kechengMapper.delete_allshangkebanjiByID(id);
	}
	@Override
	public int delete_allshangkexuanxiurenByID(String id) {
		// TODO Auto-generated method stub
		return kechengMapper.delete_allshangkexuanxiurenByID(id);
	}
	@Override
	public int update_shangkeshijian(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return kechengMapper.update_shangkeshijian(paramMap);
	}
	@Override
	public int delete_allshangkeshijianByID(String id) {
		// TODO Auto-generated method stub
		return kechengMapper.delete_allshangkeshijianByID(id);
	}
	@Override
	public int delete_shangkebanji(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.delete_shangkebanji(map);
	}
	@Override
	public int delete_shangkeshijian(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.delete_shangkeshijian(map);
	}
	@Override
	public List<Map<String, Object>> selectAllByRiQiAndBanjiAndXueQiID(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return kechengMapper.selectAllByRiQiAndBanjiAndXueQiID(paramMap);
	}
	@Override
	public int insert_shangkemianxiuren(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.insert_shangkemianxiuren(map);
	}
	@Override
	public int delete_shangkemianxiuren(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.delete_shangkemianxiuren(map);
	}
	@Override
	public List<Map<String, Object>> getAllKeChengByBanJiIDAndXueShengID(Map<String, String> map) {
		// TODO Auto-generated method stub
		return kechengMapper.getAllKeChengByBanJiIDAndXueShengID(map);
	}
	@Override
	public List<Map<String, Object>> getKeChengByRenKeLaoShiID(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return kechengMapper.getKeChengByRenKeLaoShiID(paramMap);
	}
	@Override
	public List<Map<String, Object>> selectShiJianKe(Integer kechengid){
		return kechengMapper.selectShiJianKe(kechengid);
	}
	@Override
	public Map<String, Object> getshijianke(Integer id){
		return kechengMapper.getshijianke(id);
	}
	@Override
	public int delshijianke(Integer id) {
		return kechengMapper.delshijianke(id);
	}
	@Override
	public int insertshijianke(Map<String, Object> map) {
		return kechengMapper.insertshijianke(map);
	}
	@Override
	public int updateshijianke(Map<String, Object> map) {
		return kechengMapper.updateshijianke(map);
	}
	@Override
	public List<Map<String, Object>> getKeChengByLaoShiID(Map<String, String> paramMap){
		return kechengMapper.getKeChengByLaoShiID(paramMap);
	}
	@Override
	public List<Map<String, Object>> selectShiXiByKeChengID(Integer kechengid){
		return kechengMapper.selectShiXiByKeChengID(kechengid);
	}
	@Override
	public Map<String, Object> getshixi(Integer id){
		return kechengMapper.getshixi(id);
	}
	@Override
	public List<Map<String, Object>> getallshixi(Map<String, Object> map){
		return kechengMapper.getallshixi(map);
	}
	@Override
	public int insertshixi(Map<String, Object> map) {
		return kechengMapper.insertshixi(map);
	}
	@Override
	public int updateshixi(Map<String, Object> map) {
		return kechengMapper.updateshixi(map);
	}
	@Override
	public int delshixi(Integer id) {
		return kechengMapper.delshixi(id);
	}
	@Override
	public List<Map<String, Object>> getShangKeShiJianByKeChengID(Integer kechengid){
		return kechengMapper.getShangKeShiJianByKeChengID(kechengid);
	}
	@Override
	public Map<String, Object> getkechengMap(Integer id){
		return kechengMapper.getkechengMap(id);
	}
	@Override
	public List<Map<String, Object>> getKeChengAndShiJianByKeChengID(Map<String, Object> paramMap){
		return kechengMapper.getKeChengAndShiJianByKeChengID(paramMap);
	}
	@Override
	public List<Map<String, Object>> getByBanJiIDAndXueShengIDAndXueQi(Map<String, Object> paramMap){
		return kechengMapper.getByBanJiIDAndXueShengIDAndXueQi(paramMap);
	}
}
