package com.web.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.dao.BanJiMapper;
import com.web.dao.ChaQinAnPaiMapper;
import com.web.dao.ChaQinJieGuoMapper;
import com.web.dao.FuDaoYuanMapper;
import com.web.dao.JiGuangMapper;
import com.web.dao.NianFenMapper;
import com.web.dao.YuanXiMapper;
import com.web.model.BanJi;
import com.web.model.ChaQinAnPai;
import com.web.model.ChaQinJieGuo;
import com.web.model.FuDaoYuan;
import com.web.model.XiaoXiFaSong;
import com.web.model.YongHu;
import com.web.model.YuanXi;
import com.web.service.ChaQinService;


@Service
public class ChaQinServiceImpl implements ChaQinService{

	@Autowired
	private ChaQinAnPaiMapper chaQinAnPaiMapper;
	@Autowired
	private ChaQinJieGuoMapper chaQinJieGuoMapper;
	@Autowired
	private FuDaoYuanMapper fuDaoYuanMapper;
	@Autowired
	private JiGuangMapper jiGuangMapper;
	@Autowired
	private YuanXiMapper yuanXiMapper;
	@Autowired
	private BanJiMapper banJiMapper;
	@Autowired
	private NianFenMapper nianFenMapper;
	
	@Override
	public List<ChaQinAnPai> selectByYongHuId(int yonghuid) {
		return chaQinAnPaiMapper.selectByYongHuId(yonghuid);
	}

	@Override
	public List<ChaQinAnPai> selectByYongHuIdAndRiQi(Map<String,Object> paramMap) {
		// TODO Auto-generated method stub
		return chaQinAnPaiMapper.selectByYongHuIdAndRiQi(paramMap);
	}

	@Override
	public int insertChaQinAnPai(ChaQinAnPai anPai) {
		return chaQinAnPaiMapper.insert(anPai);
	}

	@Override
	@Transactional
	public Boolean insertChaQinAnPaiList(List<ChaQinAnPai> anPais,HttpServletRequest request) {
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanMapper.getByfuDaoYuanID(user.getYonghuid());
		YuanXi yuanXi = yuanXiMapper.selectByPrimaryKey(user.getYuanxiid());
		try {
			for (ChaQinAnPai chaQinAnPai : anPais) {
				chaQinAnPaiMapper.insert(chaQinAnPai);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowTime = sdf.format(new Date());
			String[] banJiIds = fuDaoYuan.getBanjiid().split(",");
			List<String> banJis = new ArrayList<>();
			for (String string2 : banJiIds) {
				BanJi bj = banJiMapper.selectByPrimaryKey(Integer.parseInt(string2));
				Integer ruXueNianFen = nianFenMapper.selectByPrimaryKey(bj.getRuxuenianfenid())
						.getRuxuenianfen();
				String biYeNianFen = (ruXueNianFen + bj.getLeixing()) + "-09-01";
				if (sdf.parse(biYeNianFen).getTime() > sdf.parse(nowTime).getTime()) {
					banJis.add(string2);
				}
			}
			for (int j = 0; j < banJis.size(); j++) {
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("辅导员发布了新的查寝安排！");
				xiaoXiFaSong.setXiaoXiNeiRong("辅导员发布了新的查寝安排，请按时上传照片！");
				xiaoXiFaSong.setShuJuId(anPais.get(0).getAnpaiid());
				xiaoXiFaSong.setShuJuLeiXing(5);
				xiaoXiFaSong.setFaSongMuBiao(banJis.get(j));
				xiaoXiFaSong.setFaSongLeiXing(2);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(yuanXi.getXuexiaoid().toString());
				jiGuangMapper.insertXiaoXiFaSong(xiaoXiFaSong);
			}
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	@Transactional
	public Boolean insertAppChaQinAnPaiList(List<ChaQinAnPai> anPais,YongHu user) {
		FuDaoYuan fuDaoYuan = fuDaoYuanMapper.getByfuDaoYuanID(user.getYonghuid());
		YuanXi yuanXi = yuanXiMapper.selectByPrimaryKey(user.getYuanxiid());
		try {
			for (ChaQinAnPai chaQinAnPai : anPais) {
				chaQinAnPaiMapper.insert(chaQinAnPai);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowTime = sdf.format(new Date());
			String[] banJiIds = fuDaoYuan.getBanjiid().split(",");
			List<String> banJis = new ArrayList<>();
			for (String string2 : banJiIds) {
				BanJi bj = banJiMapper.selectByPrimaryKey(Integer.parseInt(string2));
				Integer ruXueNianFen = nianFenMapper.selectByPrimaryKey(bj.getRuxuenianfenid())
						.getRuxuenianfen();
				String biYeNianFen = (ruXueNianFen + bj.getLeixing()) + "-09-01";
				if (sdf.parse(biYeNianFen).getTime() > sdf.parse(nowTime).getTime()) {
					banJis.add(string2);
				}
			}
			for (int j = 0; j < banJis.size(); j++) {
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("辅导员发布了新的查寝安排！");
				xiaoXiFaSong.setXiaoXiNeiRong("辅导员发布了新的查寝安排，请按时上传照片！");
				xiaoXiFaSong.setShuJuId(anPais.get(0).getAnpaiid());
				xiaoXiFaSong.setShuJuLeiXing(5);
				xiaoXiFaSong.setFaSongMuBiao(banJis.get(j));
				xiaoXiFaSong.setFaSongLeiXing(2);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(yuanXi.getXuexiaoid().toString());
				jiGuangMapper.insertXiaoXiFaSong(xiaoXiFaSong);
			}
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public int insertChaQinJieGuo(ChaQinJieGuo record) {
		// TODO Auto-generated method stub
		return chaQinJieGuoMapper.insertChaQinJieGuo(record);
	}

	@Override
	public int deleteChaQinJieGuoByanPaiIDAndsuSheID(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return chaQinJieGuoMapper.deleteChaQinJieGuoByanPaiIDAndsuSheID(map);
	}

	@Override
	public ChaQinAnPai selectChaQinAnPaiByPrimaryKey(Integer anpaiid) {
		// TODO Auto-generated method stub
		return chaQinAnPaiMapper.selectChaQinAnPaiByPrimaryKey(anpaiid);
	}

	@Override
	public List<ChaQinJieGuo> selectChaQinJieGuoByanPaiIDAndsuSheID(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return chaQinJieGuoMapper.selectChaQinJieGuoByanPaiIDAndsuSheID(map);
	}

	@Override
	public List<ChaQinJieGuo> selectByAnPaiID(Integer anpaiid) {
		return chaQinJieGuoMapper.selectByAnPaiID(anpaiid);
	}

	@Override
	public ChaQinJieGuo selectByByPrimaryKey(Integer id) {
		return chaQinJieGuoMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public Boolean updateChaQinJieGuoByXueShengIDsAndJieGuoList(List<Integer> list, List<ChaQinJieGuo> chaQinJieGuos) {
		for (ChaQinJieGuo chaQinJieGuo : chaQinJieGuos) {
			if (list.contains(Integer.parseInt(chaQinJieGuo.getXueshengid()))) {
				chaQinJieGuo.setZhuangtai(1);
			}else {
				chaQinJieGuo.setZhuangtai(0);
			}
			chaQinJieGuoMapper.updateByPrimaryKey(chaQinJieGuo);
		}
		return true;
	}

	@Override
	public int selectChaQinTotal(Map<String, String> paramMap) {
		return chaQinAnPaiMapper.selectChaQinTotal(paramMap);
	}

	@Override
	public int selectWanChengChaQin(Map<String, String> paramMap) {
		return chaQinJieGuoMapper.selectWanChengChaQin(paramMap);
	}

	@Override
	public List<ChaQinAnPai> getByYongHuIdAndRiQi(Map<String, String> paramMap) {
		return chaQinAnPaiMapper.getByYongHuIdAndRiQi(paramMap);
	}

	@Override
	public ChaQinJieGuo selectByAnPaiIDAndXueShengID(Map<String, String> paramMap) {
		return chaQinJieGuoMapper.selectByAnPaiIDAndXueShengID(paramMap);
	}

	@Override
	public int getTotalCount(Map<String, String> paramMap) {
		return chaQinAnPaiMapper.selectChaQinTotal(paramMap);
	}

	@Override
	public List<ChaQinAnPai> getChaQinByYongHuIdAndRiQi(Map<String, String> paramMap) {
		return chaQinAnPaiMapper.getChaQinByYongHuIdAndRiQi(paramMap);
	}

	@Override
	public List<ChaQinAnPai> selectByFuDaoYuanId(Map<String, String> paramMap) {
		return chaQinAnPaiMapper.selectByFuDaoYuanId(paramMap);
	}

	@Override
	public List<ChaQinAnPai> getChaQinByWeiShangChuan(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return chaQinAnPaiMapper.getChaQinByWeiShangChuan(paramMap);
	}

	@Override
	public List<ChaQinAnPai> getChaQinByWeiShenHe(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return chaQinAnPaiMapper.getChaQinByWeiShenHe(paramMap);
	}

	@Override
	public List<ChaQinAnPai> getChaQinByQueQin(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return chaQinAnPaiMapper.getChaQinByQueQin(paramMap);
	}

	@Override
	public List<ChaQinAnPai> getChaQinByBuQueQin(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return chaQinAnPaiMapper.getChaQinByBuQueQin(paramMap);
	}

}
