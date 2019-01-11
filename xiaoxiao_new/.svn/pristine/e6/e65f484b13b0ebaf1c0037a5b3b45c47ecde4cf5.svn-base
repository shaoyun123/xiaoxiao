package com.web.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.dao.FuDaoYuanMapper;
import com.web.dao.NaXinJiBenXinXiMapper;
import com.web.dao.NaXinMapper;
import com.web.dao.SheTuanBuMenJiBenXinXinMapper;
import com.web.dao.SheTuanBuMenXinXinMapper;
import com.web.dao.SheTuanChuangJianMapper;
import com.web.dao.SheTuanJiBenXinXiMapper;
import com.web.dao.SheTuanJieSanMapper;
import com.web.dao.SheTuanJingFeiMapper;
import com.web.dao.SheTuanXinXiMapper;
import com.web.dao.TiXingMapper;
import com.web.dao.XueShengMapper;
import com.web.dao.XueShengZuZhiJiBenXinXiMapper;
import com.web.dao.XueShengZuZhiXinXiMapper;
import com.web.model.FuDaoYuan;
import com.web.model.NaXin;
import com.web.model.NaXinJiBenXinXi;
import com.web.model.SheTuanBuMenJiBenXinXin;
import com.web.model.SheTuanBuMenXinXin;
import com.web.model.SheTuanChuangJian;
import com.web.model.SheTuanJiBenXinXi;
import com.web.model.SheTuanJieSan;
import com.web.model.SheTuanJingFei;
import com.web.model.SheTuanXinXi;
import com.web.model.TiXing;
import com.web.model.XueShengZuZhiJiBenXinXi;
import com.web.model.XueShengZuZhiXinXi;
import com.web.service.SheTuanService;
@Service
public class SheTuanServiceImpl implements SheTuanService{
	
	@Autowired
	private SheTuanXinXiMapper sheTuanXinXiMapper;
	
	@Autowired
	private SheTuanJiBenXinXiMapper sheTuanJiBenXinXiMapper;
	
	@Autowired
	private XueShengZuZhiXinXiMapper xueShengZuZhiXinXiMapper;
	
	@Autowired
	private XueShengZuZhiJiBenXinXiMapper xueShengZuZhiJiBenXinXiMapper;
	
	@Autowired
	private SheTuanBuMenJiBenXinXinMapper sheTuanBuMenJiBenXinXinMapper;
	
	@Autowired
	private SheTuanBuMenXinXinMapper sheTuanBuMenXinXinMapper;
	
	@Autowired
	private NaXinJiBenXinXiMapper naXinJiBenXinXiMapper;
	
	@Autowired
	private NaXinMapper naXinMapper;
	
	@Autowired
	private SheTuanJieSanMapper sheTuanJieSanMapper;
	
	@Autowired
	private SheTuanChuangJianMapper sheTuanChuangJianMapper;
	
	@Autowired
	private SheTuanJingFeiMapper sheTuanJingFeiMapper;
	
	@Autowired
	private FuDaoYuanMapper fuDaoYuanMapper;
	
	@Autowired
	private XueShengMapper xueShengMapper;
	
	@Autowired 
	private TiXingMapper tiXingMapper;
	
	
	@Override
	public List<XueShengZuZhiJiBenXinXi> selectXueShengZuZhiJiBenXinXiByXueXiaoID(int xuexiaoid) {
		return xueShengZuZhiJiBenXinXiMapper.selectXueShengZuZhiJiBenXinXiByXueXiaoID(xuexiaoid);
	}

	@Override
	public XueShengZuZhiXinXi selectXueShengZuZhiXinXiByXueShengZuZhiID(int xueshengzuzhiid) {
		return xueShengZuZhiXinXiMapper.selectXueShengZuZhiXinXiByXueShengZuZhiID(xueshengzuzhiid);
	}

	@Override
	public List<SheTuanJiBenXinXi> selectSheTuanJiBenXinXiByXueXiaoID(int xuexiaoid) {
		return sheTuanJiBenXinXiMapper.selectSheTuanJiBenXinXiByXueXiaoID(xuexiaoid);
	}

	@Override
	public SheTuanXinXi selectSheTuanXinXiBySheTuanID(int shetuanid) {
		return sheTuanXinXiMapper.selectSheTuanXinXiBySheTuanID(shetuanid);
	}

	@Override
	public List<SheTuanJiBenXinXi> selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(Map<String, String> map) {
		return sheTuanJiBenXinXiMapper.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
	}

	@Override
	public List<XueShengZuZhiJiBenXinXi> selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(Map<String, String> map) {
		return xueShengZuZhiJiBenXinXiMapper.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
	}

	@Override
	public SheTuanJiBenXinXi selectSheTuanJiBenXinXiByXueXiaoIDAndSheTuanID(Map<String, String> map) {
		return sheTuanJiBenXinXiMapper.selectSheTuanJiBenXinXiByXueXiaoIDAndSheTuanID(map);
	}

	@Override
	public SheTuanXinXi selectSheTuanXinXiBySheTuanIDAndNianDu(int shetuanid, String niandu) {
		return sheTuanXinXiMapper.selectSheTuanXinXiBySheTuanIDAndNianDu(shetuanid, niandu);
	}

	@Override
	public List<SheTuanBuMenJiBenXinXin> selectSheTuanBuMenJiBenXinXiBySheTuanID(int shetuanid) {
		return sheTuanBuMenJiBenXinXinMapper.selectSheTuanBuMenJiBenXinXiBySheTuanID(shetuanid);
	}

	@Override
	public SheTuanBuMenXinXin selectSheTuanBuMenByBuMenIDAndNianDu(int bumenid,String niandu) {
		return sheTuanBuMenXinXinMapper.selectSheTuanBuMenByBuMenID(bumenid,niandu);
	}

	@Override
	public XueShengZuZhiJiBenXinXi selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndXueShengZuZhiID(Map<String, String> map) {
		return xueShengZuZhiJiBenXinXiMapper.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndXueShengZuZhiID(map);
	}

	@Override
	public XueShengZuZhiXinXi selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(int xueshengzuzhiid, String niandu) {
		return xueShengZuZhiXinXiMapper.selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(xueshengzuzhiid, niandu);
	}

	@Override
	public List<SheTuanBuMenJiBenXinXin> selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(int xueshengzuzhiid) {
		return sheTuanBuMenJiBenXinXinMapper.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueshengzuzhiid);
	}

	@Override
	public NaXinJiBenXinXi selectNaXinJiBenXinXiBySheTuanXinXiID(int shetuanxinxiid) {
		return naXinJiBenXinXiMapper.selectNaXinJiBenXinXiBySheTuanXinXiID(shetuanxinxiid);
	}

	@Override
	public NaXinJiBenXinXi selectNaXinJiBenXinXiByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid) {
		return naXinJiBenXinXiMapper.selectNaXinJiBenXinXiByXueShengZuZhiXinXiID(xueshengzuzhixinxiid);
	}

	@Override
	public int insertNaXin(NaXin record) {
		return naXinMapper.insertNaXin(record);
	}

	@Override
	public NaXin selectNaXinByXueShengIDAndSheTuanXinXiID(int xueshengid, int shetuanxinxiid) {
		return naXinMapper.selectNaXinByXueShengIDAndSheTuanXinXiID(xueshengid, shetuanxinxiid);
	}

	@Override
	public NaXin selectNaXinByXueShengIDAndXueShengZuZhiXinXiID(int xueshengid, int xueshengzuzhixinxiid) {
		return naXinMapper.selectNaXinByXueShengIDAndXueShengZuZhiXinXiID(xueshengid, xueshengzuzhixinxiid);
	}

	@Override
	public SheTuanXinXi selectSheTuanXinXiBySheTuanXinXiIDAndXueShengID(int shetuanxinxiid, String sheyuanid1,String sheyuanid2) {
		return sheTuanXinXiMapper.selectSheTuanXinXiBySheTuanXinXiIDAndXueShengID(shetuanxinxiid, sheyuanid1,sheyuanid2);
	}

	@Override
	public List<SheTuanXinXi> selectSheTuanXinXiByXueShengIDAndNianDuAndXueXiaoID(String sheyuanid1,String sheyuanid2,String niandu,int xuexiaoid) {
		return sheTuanXinXiMapper.selectSheTuanXinXiByXueShengIDAndNianDuAndXueXiaoID(sheyuanid1,sheyuanid2,niandu,xuexiaoid);
	}

	@Override
	public SheTuanJiBenXinXi selectSheTuanJiBenXinXiBySheTuanID(int shetuanid) {
		return sheTuanJiBenXinXiMapper.selectSheTuanJiBenXinXiBySheTuanID(shetuanid);
	}

	@Override
	public List<XueShengZuZhiXinXi> selectXueShengZuZhiXinXiByXueShengIDAndNianDu(String renyuanid1, String renyuanid2,
			String niandu) {
		return xueShengZuZhiXinXiMapper.selectXueShengZuZhiXinXiByXueShengIDAndNianDu(renyuanid1, renyuanid2, niandu);
	}

	@Override
	public XueShengZuZhiJiBenXinXi selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(int xueshengzuzhiid) {
		return xueShengZuZhiJiBenXinXiMapper.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueshengzuzhiid);
	}


	@Override
	public SheTuanJiBenXinXi selectSheTuanJiBenXinXiByXueXiaoIDAndMingCheng(int xuexiaoid, String mingcheng) {
		return sheTuanJiBenXinXiMapper.selectSheTuanJiBenXinXiByXueXiaoIDAndMingCheng(xuexiaoid, mingcheng);
	}

	@Override
	public SheTuanChuangJian selectSheTuanChuangJianByXueXiaoIDAndMingCheng(int xuexiaoid, String mingcheng) {
		return sheTuanChuangJianMapper.selectSheTuanChuangJianByXueXiaoIDAndMingCheng(xuexiaoid, mingcheng);
	}

	@Override
	public int insertSheTuanChuangJian(SheTuanChuangJian sheTuanChuangJian) {
		return sheTuanChuangJianMapper.insertSheTuanChuangJian(sheTuanChuangJian);
	}

	@Override
	public List<SheTuanChuangJian> selectSheTuanChuangJianByXueShengID(int xueshengid) {
		return sheTuanChuangJianMapper.selectSheTuanChuangJianByXueShengID(xueshengid);
	}

	@Override
	public SheTuanXinXi selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(int shetuanxinxiid, int shezhang) {
		return sheTuanXinXiMapper.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(shetuanxinxiid, shezhang);
	}

	@Override
	public int insertSheTuanJingFeiShenQing(SheTuanJingFei record) {
		return sheTuanJingFeiMapper.insertSheTuanJingFei(record);
	}

	@Override
	public List<SheTuanJingFei> selectBySheTuanXinXiID(int shetuanxinxiid) {
		return sheTuanJingFeiMapper.selectBySheTuanXinXiID(shetuanxinxiid);
	}

	@Override
	public XueShengZuZhiXinXi selectByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid) {
		return xueShengZuZhiXinXiMapper.selectByXueShengZuZhiXinXiID(xueshengzuzhixinxiid);
	}

	@Override
	public List<SheTuanJingFei> selectSheTuanJinFeiByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid) {
		return sheTuanJingFeiMapper.selectSheTuanJinFeiByXueShengZuZhiXinXiID(xueshengzuzhixinxiid);
	}

	@Override
	public SheTuanBuMenXinXin selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(int bumenid, String niandu,
			String renyuanid_1, String renyuanid_2) {
		return sheTuanBuMenXinXinMapper.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(bumenid, niandu, renyuanid_1, renyuanid_2);
	}

	@Override
	public int updateSheZhangBySheTuanXinXiID(int shetuanxinxiid, int shezhangid) {
		return sheTuanXinXiMapper.updateSheZhangBySheTuanXinXiID(shetuanxinxiid, shezhangid);
	}

	@Override
	public List<NaXin> selectNaXinBySheTuanXinXiID(int shetuanxinxiid) {
		return naXinMapper.selectNaXinBySheTuanXinXiID(shetuanxinxiid);
	}

	@Override
	public List<NaXin> selectNaXinByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid) {
		return naXinMapper.selectNaXinByXueShengZuZhiXinXiID(xueshengzuzhixinxiid);
	}

	@Override
	public int updateNaXinZhuangTaiByNaXinID(int zhuangtai, int naxinid) {
		return naXinMapper.updateNaXinZhuangTaiByNaXinID(zhuangtai, naxinid);
	}

	@Override
	public NaXin selectNaXinByNaXinID(int naxinid) {
		return naXinMapper.selectByPrimaryKey(naxinid);
	}

	@Override
	public SheTuanBuMenXinXin selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(int shetuanbumenxinxiid) {
		return sheTuanBuMenXinXinMapper.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(shetuanbumenxinxiid);
	}

	@Override
	public SheTuanBuMenJiBenXinXin selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(int shetuanbumenid) {
		return sheTuanBuMenJiBenXinXinMapper.selectByPrimaryKey(shetuanbumenid);
	}

	@Override
	public int updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(SheTuanBuMenXinXin record) {
		return sheTuanBuMenXinXinMapper.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(record);
	}

	@Override
	public SheTuanXinXi selectSheTuanXinXiBySheTuanXinXiID(int shetuanxinxiid) {
		return sheTuanXinXiMapper.selectSheTuanXinXiBySheTuanXinXiID(shetuanxinxiid);
	}

	@Override
	public List<SheTuanBuMenXinXin> selectSheTuanBuMenXinXiByXueShengIDAndNianDu(String xueshengid1, String xueshengid2,
			String niandu) {
		return sheTuanBuMenXinXinMapper.selectSheTuanBuMenXinXiByXueShengIDAndNianDu(xueshengid1, xueshengid2, niandu);
	}

	@Override
	public SheTuanBuMenJiBenXinXin selectSheTuanBuMenJiBenXinXiBySheTuanIDAndMingCheng(int shetuanid,
			String mingcheng) {
		return sheTuanBuMenJiBenXinXinMapper.selectSheTuanBuMenJiBenXinXiBySheTuanIDAndMingCheng(shetuanid, mingcheng);
	}

	@Override
	public int insertSheTuanBuMenJiBenXinXi(SheTuanBuMenJiBenXinXin record) {
		return sheTuanBuMenJiBenXinXinMapper.insertSheTuanBuMenJiBenXinXi(record);
	}

	@Override
	public int insertSheTuanBuMenXinXi(SheTuanBuMenXinXin record) {
		return sheTuanBuMenXinXinMapper.insertSheTuanBuMenXinXi(record);
	}

	@Override
	public int updateSheTuanRenYuanBySheTuanXinXiID(int shetuanxinxiid, String renyuan) {
		return sheTuanXinXiMapper.updateSheTuanRenYuanBySheTuanXinXiID(shetuanxinxiid, renyuan);
	}

	@Override
	public int updateSheTuanBuMenRenYuanByBuMenXinXiID(int bumenxinxiid, String renyuan) {
		return sheTuanBuMenXinXinMapper.updateSheTuanBuMenRenYuanByBuMenXinXiID(bumenxinxiid, renyuan);
	}

	@Override
	public int updateSheTuanBuMenJiBenXinXiZhuangTaiByBuMenID(int bumenid) {
		return sheTuanBuMenJiBenXinXinMapper.updateSheTuanBuMenJiBenXinXiZhuangTaiByBuMenID(bumenid);
	}

	@Override
	public SheTuanBuMenJiBenXinXin selectSheTuanBuMenJiBenXinXiByXueShengZuZhiIDAndMingCheng(int xueshengzuzhiid,
			String mingcheng) {
		return sheTuanBuMenJiBenXinXinMapper.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiIDAndMingCheng(xueshengzuzhiid, mingcheng);
	}

	@Override
	public int updateFuZeRenByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid, int fuzerenid) {
		return xueShengZuZhiXinXiMapper.updateFuZeRenByXueShengZuZhiXinXiID(xueshengzuzhixinxiid, fuzerenid);
	}

	@Override
	public int updateSheTuanXinXi(SheTuanXinXi record) {
		return  sheTuanXinXiMapper.updateSheTuanXinXi(record);
	}

	@Override
	public XueShengZuZhiXinXi selectXueShengZuZhiXinXiByIDAndXueShengID(int xueshengzuzhixinxiid, String sheyuanid1,
			String sheyuanid2) {
		return xueShengZuZhiXinXiMapper.selectXueShengZuZhiXinXiByIDAndXueShengID(xueshengzuzhixinxiid, sheyuanid1, sheyuanid2);
	}

	@Override
	public int updateXueShengZuZhiXinXi(XueShengZuZhiXinXi xi) {
		return xueShengZuZhiXinXiMapper.updateXueShengZuZhiXinXi(xi);
	}

	@Override
	public int insertSheTuanJieSan(SheTuanJieSan record) {
		return sheTuanJieSanMapper.insertSheTuanJieSan(record);
	}

	@Override
	public SheTuanJieSan selectSheTuanJieSanBySheTuanID(int shetuanid) {
		return sheTuanJieSanMapper.selectSheTuanJieSanBySheTuanID(shetuanid);
	}

	@Override
	public List<SheTuanChuangJian> selectSheTuanChuangJianByBanJiIDs(String banjiids) {
		return sheTuanChuangJianMapper.selectSheTuanChuangJianByBanJiIDs(banjiids);
	}

	@Override
	public SheTuanChuangJian selectByID(int id) {
		return sheTuanChuangJianMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateSheTuanChuangJianBySheTuanChuangJian(SheTuanChuangJian record) {
		return sheTuanChuangJianMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SheTuanChuangJian> selectSheTuanChuangJianByXueXiaoID(int xuexiaoid) {
		return sheTuanChuangJianMapper.selectSheTuanChuangJianByXueXiaoID(xuexiaoid);
	}

	@Override
	@Transactional
	public Boolean tongYiChuangJianSheTuan(SheTuanChuangJian record) {
		SheTuanJiBenXinXi sheTuanJiBenXinXi = new SheTuanJiBenXinXi();
		sheTuanJiBenXinXi.setXuexiaoid(record.getXuexiaoid());
		sheTuanJiBenXinXi.setMingcheng(record.getMingcheng());
		sheTuanJiBenXinXi.setXingji(3);
		sheTuanJiBenXinXi.setShetuanjieshaourl("URL");
		sheTuanJiBenXinXi.setShetuanjieshao(record.getJieshao());
		sheTuanJiBenXinXi.setChuangjianshijian(new Date());
		sheTuanJiBenXinXi.setChuanjianren(record.getChuangjianren().toString());
		sheTuanJiBenXinXi.setZhuangtai(true);
		sheTuanJiBenXinXi.setZhidaojiaoshi(record.getZhidaojiaoshi());
		boolean b = false; 
		int i = sheTuanJiBenXinXiMapper.insertSheTuanJiBenXinXi(sheTuanJiBenXinXi);
		if (i>0) {
			SheTuanXinXi xinXi = new SheTuanXinXi();
			xinXi.setShetuanid(sheTuanJiBenXinXi.getShetuanid());
			xinXi.setSheyuanids(record.getChuangjianren()+",");
			xinXi.setSheyuanrenshu(1);
			xinXi.setShezhang(record.getChuangjianren().toString());
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			xinXi.setNiandu(Integer.parseInt(format.format(date)));
			int j = sheTuanXinXiMapper.insertSheTuanXinXi(xinXi);
			if (j>0) {
				record.setZhuangtai(2);
				int k = sheTuanChuangJianMapper.updateByPrimaryKey(record);
				if (k>0) {
					b = true;
				}
			}
		}
		return b;
	}

	@Override
	public int insertSheTuanJiBenXinXi(SheTuanJiBenXinXi record) {
		return sheTuanJiBenXinXiMapper.insertSheTuanJiBenXinXi(record);
	}

	@Override
	@Transactional
	public Boolean tongYiChuangJianXueShengZuZhi(SheTuanChuangJian record) {
		XueShengZuZhiJiBenXinXi xi = new XueShengZuZhiJiBenXinXi();
		xi.setXuexiaoid(record.getXuexiaoid());
		xi.setMingcheng(record.getMingcheng());
		xi.setJieshaourl("URL");
		xi.setJianjie(record.getJieshao());
		xi.setChuangjianren(record.getChuangjianren().toString());
		xi.setChuangjianshijian(new Date());
		xi.setZhuangtai(true);
		boolean b = false; 
		int i = xueShengZuZhiJiBenXinXiMapper.insertXueShengZuZhiJiBenXinXi(xi);
		if (i>0) {
			XueShengZuZhiXinXi xinXi = new XueShengZuZhiXinXi();
			xinXi.setXueshengzuzhiid(xi.getXueshengzuzhiid());
			xinXi.setRenshu(1);
			xinXi.setRenyuanids(record.getChuangjianren()+",");
			xinXi.setFuzeren(record.getChuangjianren().toString());
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			xinXi.setNiandu(Integer.parseInt(format.format(date)));
			String banjiid = xueShengMapper.selectByPrimaryKey(record.getChuangjianren()).getBanjiid().toString();
			List<FuDaoYuan> fuDaoYuans = fuDaoYuanMapper.getBybanJiID(banjiid+",%","%,"+banjiid+",%");
			FuDaoYuan fuDaoYuan = fuDaoYuans.get(0);
			xinXi.setZhidaoren(fuDaoYuan.getFudaoyuanid().toString());
			int j = xueShengZuZhiXinXiMapper.insertXueShengZuZhiXinXi(xinXi);
			if (j>0) {
				record.setZhuangtai(2);
				int k = sheTuanChuangJianMapper.updateByPrimaryKey(record);
				if (k>0) {
					b = true;
				}
			}
		}
		return b;
	}

	@Override
	public int insertXueShengZuZhiJiBenXinXi(XueShengZuZhiJiBenXinXi record) {
		return xueShengZuZhiJiBenXinXiMapper.insertXueShengZuZhiJiBenXinXi(record);
	}

	@Override
	public int insertNaXinJiBenXinXi(NaXinJiBenXinXi record) {
		return naXinJiBenXinXiMapper.insertNaXinJiBenXinXi(record);
	}

	@Override
	public int updateNaXinJiBenXinXi(NaXinJiBenXinXi record) {
		return naXinJiBenXinXiMapper.updateNaXinJiBenXinXi(record);
	}

	@Override
	public List<SheTuanJingFei> selectByXueXiaoID(int xuexiaoid) {
		return sheTuanJingFeiMapper.selectByXueXiaoID(xuexiaoid);
	}

	@Override
	public SheTuanJingFei selectByJingFeiID(int jingfeiid) {
		return sheTuanJingFeiMapper.selectByPrimaryKey(jingfeiid);
	}

	@Override
	public int updateSheTuanJingFei(SheTuanJingFei record) {
		return sheTuanJingFeiMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SheTuanJieSan> selectSheTuanJieSanByXueXiaoID(int xuexiaoid) {
		return sheTuanJieSanMapper.selectSheTuanJieSanByXueXiaoID(xuexiaoid);
	}

	@Override
	public SheTuanJieSan selectSheTuanJieSanByID(int id) {
		return sheTuanJieSanMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateSheTuanJieSan(SheTuanJieSan record) {
		return sheTuanJieSanMapper.updateByPrimaryKey(record);
	}

	@Override
	@Transactional
	public Boolean tongYiJieSan(SheTuanJieSan record) {
		boolean b = false;
		if (record.getShetuanid()!=null) { //社团解散
			SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanJiBenXinXiMapper.selectSheTuanJiBenXinXiBySheTuanID(record.getShetuanid());
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			SheTuanXinXi sheTuanXinXi = sheTuanXinXiMapper.selectSheTuanXinXiBySheTuanIDAndNianDu(sheTuanJiBenXinXi.getShetuanid(), format.format(new Date()));
			sheTuanJiBenXinXi.setZhuangtai(false);
			int i = sheTuanJiBenXinXiMapper.updateSheTuanJiBenXinXi(sheTuanJiBenXinXi);
			if (i>0) {
				int k = sheTuanJieSanMapper.updateByPrimaryKey(record);
				if(sheTuanXinXi!=null){
					String sheYuanIDs[] = sheTuanXinXi.getSheyuanids().split(",");
					TiXing tiXing = null;
					for (int j = 0; j < sheYuanIDs.length; j++) {
						tiXing = new TiXing();
						tiXing.setJieshourenid(Integer.parseInt(sheYuanIDs[j]));
						tiXing.setShijian(new Date());
						tiXing.setZhuangtai(0);
						tiXing.setNeirong("你加入的"+sheTuanJiBenXinXi.getMingcheng()+"社团已被解散");
						tiXingMapper.insert(tiXing);
					}
				}
				if (k>0) {
					b = true;
				}
			}
		}else { //学生组织解散
			XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi = xueShengZuZhiJiBenXinXiMapper.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(record.getXueshengzuzhiid());
			xueShengZuZhiJiBenXinXi.setZhuangtai(false);
			int i  = xueShengZuZhiJiBenXinXiMapper.updateXueShengZuZhiJiBenXinXi(xueShengZuZhiJiBenXinXi);
			if (i>0) {
				int k = sheTuanJieSanMapper.updateByPrimaryKey(record);
				if (k>0) {
					b = true;
				}
			}
		}
		return b;
	}

	@Override
	public int insertSheTuanXinXi(SheTuanXinXi record) {
		return sheTuanXinXiMapper.insertSheTuanXinXi(record);
	}

	@Override
	@Transactional
	public Boolean sheTuanXinXiWeiHu(SheTuanXinXi record, String renyuans[]) {
		StringBuffer sBuffer = new StringBuffer();
		if (renyuans!=null) {
			for (int i = 0; i < renyuans.length; i++) {
				sBuffer.append(renyuans[i]+",");
			}
		}
		SheTuanXinXi sheTuanXinXi = new SheTuanXinXi();
		sheTuanXinXi.setShetuanid(record.getShetuanid());
		sheTuanXinXi.setShezhang(record.getShezhang());
		sheTuanXinXi.setSheyuanids(sBuffer.toString());
		sheTuanXinXi.setSheyuanrenshu(renyuans.length);
		sheTuanXinXi.setNiandu(record.getNiandu()+1);
		if (!Arrays.asList(renyuans).contains(record.getBianji())){
			sheTuanXinXi.setBianji(null);
		}else {
			sheTuanXinXi.setBianji(record.getBianji());
		}
		int i = sheTuanXinXiMapper.insertSheTuanXinXi(sheTuanXinXi);
		if (i>0) {
			List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanBuMenJiBenXinXinMapper.selectSheTuanBuMenJiBenXinXiBySheTuanID(record.getShetuanid());
			if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
				for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
					SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanBuMenXinXinMapper.selectSheTuanBuMenByBuMenID(sheTuanBuMenJiBenXinXin.getBumenid(), record.getNiandu().toString());
					if (sheTuanBuMenXinXin!=null) {
						SheTuanBuMenXinXin sheTuanBuMenXinXin2 = new SheTuanBuMenXinXin();
						if (sheTuanBuMenXinXin.getRenyuanids()!=null) {
							String renYuanIDs[] = sheTuanBuMenXinXin.getRenyuanids().split(",");
							StringBuffer sBuffer2 = new StringBuffer();
							for (int j = 0; j < renYuanIDs.length; j++) {
								if (Arrays.asList(renyuans).contains(renYuanIDs[j])) {
									sBuffer2.append(renYuanIDs[j]+",");
								}else {
									continue;
								}
							}
							sheTuanBuMenXinXin2.setRenyuanids(sBuffer2.toString());
						}
						if (sheTuanBuMenXinXin.getBuzhang()!=null) {
							if (!Arrays.asList(renyuans).contains(sheTuanBuMenXinXin.getBuzhang())) {
								sheTuanBuMenXinXin2.setBuzhang(null);
							}else {
								sheTuanBuMenXinXin2.setBuzhang(sheTuanBuMenXinXin.getBuzhang());
							}
						}
						if (sheTuanBuMenXinXin.getZhiwu()!=null) {
							String[] zhiwu = sheTuanBuMenXinXin.getZhiwu().split(";");
		 					String[] zhiWuAndID = zhiwu[0].split(","); 
							if (!Arrays.asList(renyuans).contains(zhiWuAndID[1])) {
								sheTuanBuMenXinXin2.setZhiwu(null);
							}else {
								sheTuanBuMenXinXin2.setZhiwu(sheTuanBuMenXinXin.getZhiwu());
							}
						}
						if (sheTuanBuMenXinXin2.getRenyuanids()!=null) {
							String renYuans[]= sheTuanBuMenXinXin2.getRenyuanids().split(",");
							sheTuanBuMenXinXin2.setBumenrenshu(String.valueOf(renYuans.length));
						}else {
							sheTuanBuMenXinXin2.setBumenrenshu("0");
						}
						sheTuanBuMenXinXin2.setNiandu(sheTuanBuMenXinXin.getNiandu()+1);
						sheTuanBuMenXinXin2.setBumenid(sheTuanBuMenXinXin.getBumenid());
						sheTuanBuMenXinXinMapper.insertSheTuanBuMenXinXi(sheTuanBuMenXinXin2);
					}
				}
			}
			return true;
		}else {
			return false;
		}
	}

	@Override
	@Transactional
	public Boolean xueShengZuZhiWeiHu(XueShengZuZhiXinXi record, String[] renyuans) {
		StringBuffer sBuffer = new StringBuffer();
		if (renyuans!=null) {
			for (int i = 0; i < renyuans.length; i++) {
				sBuffer.append(renyuans[i]+",");
			}
		}
		XueShengZuZhiXinXi xueShengZuZhiXinXi = new XueShengZuZhiXinXi();
		xueShengZuZhiXinXi.setXueshengzuzhiid(record.getXueshengzuzhiid());
		xueShengZuZhiXinXi.setFuzeren(record.getFuzeren());
		xueShengZuZhiXinXi.setRenyuanids(sBuffer.toString());
		xueShengZuZhiXinXi.setRenshu(renyuans.length);
		xueShengZuZhiXinXi.setNiandu(record.getNiandu()+1);
		xueShengZuZhiXinXi.setZhidaoren(record.getZhidaoren());
		if (!Arrays.asList(renyuans).contains(record.getBianji())){
			xueShengZuZhiXinXi.setBianji(null);
		}else {
			xueShengZuZhiXinXi.setBianji(record.getBianji());
		}
		int i = xueShengZuZhiXinXiMapper.insertXueShengZuZhiXinXi(xueShengZuZhiXinXi);
		if (i>0) {
			List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanBuMenJiBenXinXinMapper.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(record.getXueshengzuzhiid());
			if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
				for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
					SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanBuMenXinXinMapper.selectSheTuanBuMenByBuMenID(sheTuanBuMenJiBenXinXin.getBumenid(), record.getNiandu().toString());
					SheTuanBuMenXinXin sheTuanBuMenXinXin2 = new SheTuanBuMenXinXin();
					String renYuanIDs[] = sheTuanBuMenXinXin.getRenyuanids().split(",");
					StringBuffer sBuffer2 = new StringBuffer();
					for (int j = 0; j < renYuanIDs.length; j++) {
						if (Arrays.asList(renyuans).contains(renYuanIDs[j])) {
							sBuffer2.append(renYuanIDs[j]+",");
						}else {
							continue;
						}
					}
					sheTuanBuMenXinXin2.setRenyuanids(sBuffer2.toString());
					if (!Arrays.asList(renyuans).contains(sheTuanBuMenXinXin.getBuzhang())) {
						sheTuanBuMenXinXin2.setBuzhang(null);
					}else {
						sheTuanBuMenXinXin2.setBuzhang(sheTuanBuMenXinXin.getBuzhang());
					}
					if (sheTuanBuMenXinXin.getZhiwu()!=null) {
						String[] zhiwu = sheTuanBuMenXinXin.getZhiwu().split(";");
	 					String[] zhiWuAndID = zhiwu[0].split(","); 
						if (!Arrays.asList(renyuans).contains(zhiWuAndID[1])) {
							sheTuanBuMenXinXin2.setZhiwu(null);
						}else {
							sheTuanBuMenXinXin2.setZhiwu(sheTuanBuMenXinXin.getZhiwu());
						}
					}
					String renYuans[]= sheTuanBuMenXinXin2.getRenyuanids().split(",");
					sheTuanBuMenXinXin2.setBumenrenshu(String.valueOf(renYuans.length));
					sheTuanBuMenXinXin2.setNiandu(sheTuanBuMenXinXin.getNiandu()+1);
					sheTuanBuMenXinXin2.setBumenid(sheTuanBuMenXinXin.getBumenid());
					sheTuanBuMenXinXinMapper.insertSheTuanBuMenXinXi(sheTuanBuMenXinXin2);
				}
			}
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int updateSheTuanJiBenXinXi(SheTuanJiBenXinXi record) {
		return sheTuanJiBenXinXiMapper.updateSheTuanJiBenXinXi(record);
	}

	@Override
	public int updateXueShengZuZhiJiBenXinXi(XueShengZuZhiJiBenXinXi record) {
		return xueShengZuZhiJiBenXinXiMapper.updateXueShengZuZhiJiBenXinXi(record);
	}
}
