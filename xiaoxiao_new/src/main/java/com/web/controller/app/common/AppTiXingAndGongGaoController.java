package com.web.controller.app.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.web.dao.TongZhiMapper;
import com.web.model.BeiWL;
import com.web.model.HuoDong;
import com.web.model.JCSJ;
import com.web.model.KeCheng;
import com.web.model.TiXing;
import com.web.model.TiXingTongZhiShiTu;
import com.web.model.TongZhi;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.service.BanJiService;
import com.web.service.BeiWLService;
import com.web.service.HuoDongService;
import com.web.service.JCSJService;
import com.web.service.JiaoShiService;
import com.web.service.KeChengService;
import com.web.service.NewsService;
import com.web.service.TiXingService;
import com.web.service.TiXingTongZhiShiTuService;
import com.web.service.XiaoQuService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
public class AppTiXingAndGongGaoController {
	@Autowired
	private TiXingService tiXingService;
	@Autowired
	private XueShengService xueshengService;
	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private KeChengService kechengService;
	@Autowired
	private HuoDongService huodongService;
	@Autowired
	private JCSJService jcsjService;
	@Autowired
	private XiaoQuService xiaoquService;
	@Autowired
	private JiaoShiService jiaoshiService;
	@Autowired
	private TiXingTongZhiShiTuService tiXingTongZhiShiTuService;
	@Autowired
	private TongZhiMapper tongZhiMapper;
	@Autowired
	private NewsService newsService;
	@Autowired
	private BeiWLService beiwlService;
	@Autowired
	private BanJiService banjiService;
	@Autowired
	private YuanXiService yuanxiService;
	
	//是否有消息提醒
	@RequestMapping(value="app_IsHaveTiXing")
	@ResponseBody
	public String app_IsHaveTiXing(HttpServletRequest request){
		String jieshourenid=request.getParameter("id");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xuexiaoXuehao= request.getParameter("xuexiaoXuehao");
		String string= jieshourenid+","+status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			/*
			List<TiXing> tiXing=tiXingService.getAllByjieShouRenID(Integer.parseInt(jieshourenid));
			if(!tiXing.isEmpty()){
				for(TiXing tXing : tiXing){
					System.out.println(tXing.getZhuangtai());
					if(tXing.getZhuangtai()==0){
						return "yes";
					}
				}
			}*/
//			String xuexiaoid=xuexiaoXuehao.split("_")[0];
			String xuexiaoid = "";
			if("xuesheng".equals(status)){
				XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(jieshourenid));
				xuexiaoid=banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			}else{
				YongHu yh = yongHuService.selectYongHuByID(Integer.parseInt(jieshourenid));
				xuexiaoid = yuanxiService.selectByPrimaryKey(yh.getYuanxiid()).getXuexiaoid().toString();
			}
			Map<String, Object> map2 = new HashMap<>();
			String target1="xuesheng";
			String target2="xuesheng%";
			map2.put("target1", target1);
			map2.put("target2", target2);
			map2.put("school_id", xuexiaoid);
			map2.put("jieshourenid", Integer.parseInt(jieshourenid));
			List<TiXingTongZhiShiTu> tiXingTongZhiShiTus=tiXingTongZhiShiTuService.selectAllByjieShouRenIDandNewTarget(map2);
			for (TiXingTongZhiShiTu tiXingTongZhiShiTu : tiXingTongZhiShiTus) {
				int k=0;
				if(tiXingTongZhiShiTu.getType()!=null){
					if(tiXingTongZhiShiTu.getType()==4){
						if(tiXingTongZhiShiTu.getChakanrenids()!=null&&tiXingTongZhiShiTu.getChakanrenids()!=""){
							String chakanrenids=tiXingTongZhiShiTu.getChakanrenids();
							if(chakanrenids!=null&&chakanrenids!=""){
								String chakanrens[]=chakanrenids.split(",");
								for (String string2 : chakanrens) {
									if(string2.equals(jieshourenid)){
										k=1;
										break;
									}
								}
								tiXingTongZhiShiTu.setZhuangtai(k);
								if(tiXingTongZhiShiTu.getZhuangtai()==0){
									return "yes";
								}
							}
						}
						else {
							return "yes";
						}
					}
					else {
						if(tiXingTongZhiShiTu.getZhuangtai()==0){
							return "yes";
						}
					}
				}else{
					if(tiXingTongZhiShiTu.getZhuangtai()==0){
				
						return "yes";
					}
				}
			}
			return "noo";
		}
		else {
			return "dengchu";
		}
	}
	
	//显示提醒
	@RequestMapping(value="app_XianShiTiXing")
	@ResponseBody
	public Object app_XianShiTiXing(HttpServletRequest request){
		Map<String, Integer> map = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		String id=request.getParameter("id");
		Map<String, Object> mpp=new HashMap<>();
		String xuexiaoXuehao= request.getParameter("xuexiaoXuehao");
		
		String status =request.getParameter("status");
		String token = request.getParameter("token");
		String searchStatus = request.getParameter("searchStatus");
		String string= id+","+status;
		String ren= id+"_"+status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			//int count = tiXingService.getCountByjieShouRenID(Integer.parseInt(id));
//			String xuexiaoid=xuexiaoXuehao.split("_")[0];
			String xuexiaoid="";
			if("xuesheng".equals(status)){
				XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(id));
				xuexiaoid=banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			}else{
				YongHu yh = yongHuService.selectYongHuByID(Integer.parseInt(id));
				xuexiaoid = yuanxiService.selectByPrimaryKey(yh.getYuanxiid()).getXuexiaoid().toString();
			}
			String target1="xuesheng";
			String target2="xuesheng%";
			map2.put("target1", target1);
			map2.put("target2", target2);
			map2.put("school_id", xuexiaoid);
			map2.put("searchStatus", searchStatus);
			map2.put("jieshourenid", Integer.parseInt(id));
			int count = tiXingTongZhiShiTuService.getCountByjieShouRenIDandNewTarget(map2);
			int pageSize = 10;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				/*
				map.put("jieshourenid", Integer.parseInt(id));
				map.put("start", 0);
				map.put("stop", 10);
				List<TiXing> tiXings=tiXingService.getAllByjieShouRenIDAndLimit(map);
				if(!tiXings.isEmpty()){
					for(TiXing tiXing : tiXings){
						if(status.equals("xuesheng")){
							tiXing.setXingming(xueshengService.getUserById(Integer.parseInt(id)).getXingming());
						}
						else{
							tiXing.setXingming(yongHuService.selectYongHuByID(Integer.parseInt(id)).getYonghuxingming());
						}
					}
				}*/
				map2.put("start", 0);
				map2.put("stop", 10);
				List<TiXingTongZhiShiTu> tiXingTongZhiShiTus=tiXingTongZhiShiTuService.selectAllByjieShouRenIDandNewTargetandLimit(map2);
				if(!tiXingTongZhiShiTus.isEmpty()){
					for (TiXingTongZhiShiTu tiXingTongZhiShiTu : tiXingTongZhiShiTus) {
						int k=0;
						if(status.equals("xuesheng")){
							tiXingTongZhiShiTu.setXingming(xueshengService.getUserById(Integer.parseInt(id)).getXingming());
							if(tiXingTongZhiShiTu.getType()!=null){
								if(tiXingTongZhiShiTu.getType()==4){
									String chakanrenids=tiXingTongZhiShiTu.getChakanrenids();
									if(chakanrenids!=null&&chakanrenids!=""){
										String chakanrens[]=chakanrenids.split(",");
										for (String string2 : chakanrens) {
											if(string2.equals(ren)){
												k=1;
												break;
											}
										}
										tiXingTongZhiShiTu.setZhuangtai(k);
									}
									else {
										tiXingTongZhiShiTu.setZhuangtai(0);
									}
								}
							}
						}
					}
				}
				//mpp.put("tixing", tiXings);
				mpp.put("pages", pages);
				mpp.put("txtzst", tiXingTongZhiShiTus);
				mpp.put("page", 1);
				return JSON.toJSON(mpp);
			}
			else {
				if (Util.isNumeric(request.getParameter("page"))) {
					int page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page <= pages) {
						/*
						map.put("jieshourenid", Integer.parseInt(id));
						map.put("start", (page - 1) * 10);
						map.put("stop", 10);
						List<TiXing> tiXings=tiXingService.getAllByjieShouRenIDAndLimit(map);
						if(!tiXings.isEmpty()){
							for(TiXing tiXing : tiXings){
								if(status.equals("xuesheng")){
									tiXing.setXingming(xueshengService.getUserById(Integer.parseInt(id)).getXingming());
								}
								else{
									tiXing.setXingming(yongHuService.selectYongHuByID(Integer.parseInt(id)).getYonghuxingming());
								}
							}
						}
						mpp.put("tixing", tiXings);
						*/
						map2.put("start", (page - 1) * 10);
						map2.put("stop", 10);
						List<TiXingTongZhiShiTu> tiXingTongZhiShiTus=tiXingTongZhiShiTuService.selectAllByjieShouRenIDandNewTargetandLimit(map2);
						if(!tiXingTongZhiShiTus.isEmpty()){
							for (TiXingTongZhiShiTu tiXingTongZhiShiTu : tiXingTongZhiShiTus) {
								int k=0;
								if(status.equals("xuesheng")){
									tiXingTongZhiShiTu.setXingming(xueshengService.getUserById(Integer.parseInt(id)).getXingming());
									if(tiXingTongZhiShiTu.getType()!=null){
										if(tiXingTongZhiShiTu.getType()==4){
											String chakanrenids=tiXingTongZhiShiTu.getChakanrenids();
											if(chakanrenids!=null&&chakanrenids!=""){
												String chakanrens[]=chakanrenids.split(",");
												for (String string2 : chakanrens) {
													if(string2.equals(ren)){
														k=1;
														break;
													}
												}
												tiXingTongZhiShiTu.setZhuangtai(k);
											}
											else {
												tiXingTongZhiShiTu.setZhuangtai(0);
											}
										}
									}
								}
							}
						}
						mpp.put("pages", pages);
						mpp.put("page", page);
						mpp.put("txtzst", tiXingTongZhiShiTus);
						return JSON.toJSON(mpp);
					}
					else{
						//mpp.put("tixing", "");
						mpp.put("txtzst", "");
						mpp.put("pages", "");
						mpp.put("page", "");
						return JSON.toJSON(mpp);
					}
				}
				else{
					//mpp.put("tixing", "");
					mpp.put("txtzst", "");
					mpp.put("pages", "");
					mpp.put("page", "");
					return JSON.toJSON(mpp);
				}
			}
		}
		else {
			return null;
		}
		
	}
	
	//查看提醒详情
	@RequestMapping(value="app_TiXingXiangQing")
	@ResponseBody
	public Object app_TiXingXiangQing(HttpServletRequest request){
		String jieshourenid=request.getParameter("jieshourenid");
		String id=request.getParameter("id");
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String data[] = code.split(",zytech,");
		String string = data[0] + "," + data[1];
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			TiXing tiXing=new TiXing();
			System.out.println(id+jieshourenid);
			Map<String, Object> mpp=new HashMap<>();
			mpp.put("tixing", "");
			if(jieshourenid!=null&&jieshourenid!=""){
				tiXing=tiXingService.selectByPrimaryKey(Integer.parseInt(id));
				if(tiXing.getJieshourenid()==Integer.parseInt(jieshourenid)){
					if(tiXing.getType()!=null){
						if(tiXing.getType()==1){
							KeCheng keCheng=kechengService.selectByPrimaryKey(tiXing.getShujuid());
							mpp.put("kecheng", keCheng);
						} else if (tiXing.getType()==2) {
							HuoDong huoDong=huodongService.selectByPrimaryKey(tiXing.getShujuid());
							mpp.put("huodong", huoDong);
						} else {
							BeiWL beiWL = beiwlService.selectByPrimaryKey(tiXing.getShujuid());
							mpp.put("beiwang", beiWL);
						}
					}
					tiXing.setZhuangtai(1);
					int i=tiXingService.updatezhuangTaiByPrimaryKey(tiXing);
					if(i!=0){
						mpp.put("tixing", tiXing);
						return JSON.toJSON(mpp);
					}
					mpp.put("tixing", tiXing);
					return JSON.toJSON(mpp);
				}
				else {
					return JSON.toJSON(mpp);
				}
			}
			return JSON.toJSON(mpp);
		}
		else {
			return null;
		}
	}
	
	//查看通知新闻详情
	@RequestMapping(value="app_TongZhiXiangQing")
	@ResponseBody
	public Object app_TongZhiXiangQing(HttpServletRequest request){
		String id=request.getParameter("id");
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String data[] = code.split(",zytech,");
		String string = data[0] + "," + data[1];
		String str = Util.SHA1Encode(string);
		Map<String, Object> map=new HashMap<>();
		if(str.equals(token)){
			Map<String,Object> paramMap = new HashMap<String,Object>();
			String regexp =  data[0]+"_"+data[1]+",";
			paramMap.put("regexp",regexp);
			paramMap.put("id", id);
			List<TongZhi> newsList = newsService.getNewsByParamID(paramMap);
			TongZhi tongZhi=newsList.size() > 0 ? newsList.get(0) : null;
			if(tongZhi != null && !"1".equals(tongZhi.getDq())){
				String chakanrenids = tongZhi.getChakanrenids() == null ? "" : tongZhi.getChakanrenids();
				paramMap.put("chaKanRenIDs", chakanrenids+regexp);
				tongZhi.setChakanrenids( chakanrenids+regexp);
				newsService.updateNewsByParam(paramMap);
			}
			map.put("tongzhi", tongZhi);
			return JSON.toJSON(map);
		}
		else{
			return null;
		}
	}
	
	//删除提醒
	@RequestMapping(value="app_DeleteTiXing")
	@ResponseBody
	public String app_DeleteTiXing(HttpServletRequest request) throws IOException {
		String result = "";
		String id = request.getParameter("CODE");
		String code = request.getParameter("key");
		String token = request.getParameter("token");
		String data[] = code.split(",zytech,");
		String string = data[0] + "," + data[1];
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			int i = tiXingService.deleteByPrimaryKey(Integer.parseInt(id));
			if(i!=0){
				result =  "success";
			}
			return result;
		}
		else {
			return "dengchu";
		}
	}
	
	//查询是否有日程安排
	@RequestMapping(value="app_TanChuTongZhi")
	@ResponseBody
	public Object app_TanChuTongZhi(HttpServletRequest request){
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status =request.getParameter("status");
		Map<Object, Object> map2 = new HashMap<>();
		Map<String, String> mapp = new HashMap<>();
		String strings= xueshengid+","+status;
		String str = Util.SHA1Encode(strings);
		if(str.equals(token)){
			String canyuren = "%0," + xueshengid + ",1%";
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			//int year = c.get(Calendar.YEAR);
			//int month = c.get(Calendar.MONTH)+1;
			//int day = c.get(Calendar.DATE);
			String riqi = df.format(date);
			Map<String, String> map = new HashMap<>();
			mapp.put("canyuren", canyuren);
			mapp.put("riqi", riqi);
			List<HuoDong> huodongs = huodongService.getAllBycanYuRenAndRiQi(mapp);
			map.put("canyuren1", xueshengid + ",1;%");
			map.put("canyuren2", "%;"+xueshengid + ",1;%");
			map.put("shangkeriqi1", riqi+"%");
			map.put("shangkeriqi2", "%"+riqi+"%");
			List<KeCheng> keChengs = kechengService.getAllByxueShengIDandshangKeRiQi(map);
			List<KeCheng> keChengs2 = new ArrayList<>();
			for (KeCheng keCheng : keChengs) {
				String shangkeriqi[] = keCheng.getShangkeriqi().split(",");
				for (String string : shangkeriqi) {
					String riqi1[] = string.split(";");
					KeCheng newkecheng = new KeCheng();
					if(riqi1[0].equals(riqi)&&riqi1.length!=1){
						newkecheng.setXiaoqu(riqi1[1]);
						newkecheng.setShangkejiaoshi(riqi1[2]);
						newkecheng.setKaishijieci(Integer.parseInt(riqi1[3]));
						newkecheng.setJieshujieci(Integer.parseInt(riqi1[4]));
						int kaishijieci = newkecheng.getKaishijieci();
						int jieshujieci = newkecheng.getJieshujieci();
						JCSJ jcsj1 =jcsjService.selectByPrimaryKey(kaishijieci);
						JCSJ jcsj2 =jcsjService.selectByPrimaryKey(jieshujieci);
						String kaishishijian = jcsjService.getkaiShiShiJianByid(jcsj1.getId());
						String jieshushijian = jcsjService.getjieShuShiJianByid(jcsj2.getId());
						newkecheng.setKaishishijian(kaishishijian);
						newkecheng.setJieshushijian(jieshushijian);
						String xiaoquming = xiaoquService.selectByPrimaryKey(Integer.parseInt(keCheng.getXiaoqu()))
								.getMingcheng();
						String jiaoshiming = jiaoshiService.selectByPrimaryKey(Integer.parseInt(keCheng.getShangkejiaoshi()))
								.getJiaoshiming();
						newkecheng.setXiaoquming(xiaoquming);
						newkecheng.setJiaoshiming(jiaoshiming);
						newkecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
					    newkecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
					    newkecheng.setCanyuren(keCheng.getCanyuren());
					    keChengs2.add(newkecheng);
					}
				}
				int kaishijieci = keCheng.getKaishijieci();
				int jieshujieci = keCheng.getJieshujieci();
				JCSJ jcsj1 =jcsjService.selectByPrimaryKey(kaishijieci);
				JCSJ jcsj2 =jcsjService.selectByPrimaryKey(jieshujieci);
				String kaishishijian = jcsjService.getkaiShiShiJianByid(jcsj1.getId());
				String jieshushijian = jcsjService.getjieShuShiJianByid(jcsj2.getId());
				keCheng.setKaishishijian(kaishishijian);
				keCheng.setJieshushijian(jieshushijian);
				String xiaoquming = xiaoquService.selectByPrimaryKey(Integer.parseInt(keCheng.getXiaoqu())).getMingcheng();
				String jiaoshiming = jiaoshiService.selectByPrimaryKey(Integer.parseInt(keCheng.getShangkejiaoshi())).getJiaoshiming();
				keCheng.setXiaoquming(xiaoquming);
				keCheng.setJiaoshiming(jiaoshiming);
				keChengs2.add(keCheng);
			}
			map2.put("keChengs", keChengs2);
			map2.put("huodongs", huodongs);
			return JSONObject.fromObject(map2);
		}
		else {
			return null;
		}
	}
}
