package com.web.controller.web.guanliyuan;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.web.annotation.LoginStatusAnnotation;
import com.web.model.BanJi;
import com.web.model.DeYuPingFenFangAn;
import com.web.model.FuDaoYuan;
import com.web.model.NianFen;
import com.web.model.PingFenFangAn;
import com.web.model.XueQi;
import com.web.model.XueQiDeYu;
import com.web.model.XueSheng;
import com.web.model.XueShengChuGuanLiYuan;
import com.web.model.XueShengDeYu;
import com.web.model.YongHu;
import com.web.model.YuanXi;
import com.web.model.ZhuanYe;
import com.web.service.BanJiService;
import com.web.service.DeYuService;
import com.web.service.NianFenService;
import com.web.service.PingFenFangAnService;
import com.web.service.XueQiService;
import com.web.service.XueShengChuGuanLiYuanService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.service.ZhuanYeService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
@LoginStatusAnnotation(status="guanliyuan")
public class DeYuController_gly {
	static Logger logger = Logger.getLogger(DeYuController_gly.class);
	@Autowired
	private XueShengChuGuanLiYuanService guanLiYuanService;
	
	@Autowired
	private YuanXiService yuanXiService;
	
	@Autowired 
	private ZhuanYeService zhuanYeService;
	
	@Autowired
	private NianFenService nianFenService; 
	
	@Autowired
	private BanJiService banJiService;
	
	@Autowired
	private XueQiService xueQiService;
	
	@Autowired
	private DeYuService deYuService;
	
	@Autowired
	private XueShengService xueShengService; 
	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private PingFenFangAnService pingFenFangAnService;
	
	@RequestMapping(value="deyuchengji_gly")
	public ModelAndView deYuChengJi(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView = new ModelAndView();
		List<YuanXi> yuanXis = yuanXiService.getAllByxueXiaoID(guanLiYuan.getXuexiaoid());
		if(yuanXis==null||yuanXis.size()==0){
			
		}
		String xueNian = request.getParameter("xuenian");
		String xueQi = request.getParameter("xueqi");
		String yuanXi = request.getParameter("yuanxi");
		String zhuanYe = request.getParameter("zhuanye");
		logger.info(zhuanYe);
		String nianFen = request.getParameter("nianfen");
		String banJi = request.getParameter("banji");
		
		List<ZhuanYe> zhuanYes = new ArrayList<>();
		List<String> xueNians = new ArrayList<>();
		List<NianFen> nianFens = new ArrayList<>();
		List<BanJi> banJis = new ArrayList<>();
		List<XueShengDeYu> xueShengDeYus = new ArrayList<>();
		List<DeYuPingFenFangAn> fangAns = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		XueQi xQi = new XueQi();
		
		if(yuanXi==null || "".equals(yuanXi)){
			yuanXi = yuanXis.get(0).getYuanxiid().toString();
		}
		YuanXi yXi = yuanXiService.selectByPrimaryKey(Integer.parseInt(yuanXi));
		xueNians = xueQiService.getXuenianByXuexiaoID(yXi.getXuexiaoid());
		nianFens = nianFenService.getNianFen();
		
		if(xueNian==null || xueQi == null){
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			map.put("riqi", format.format(date));
			map.put("xueXiaoID",yXi.getXuexiaoid().toString());
			xQi = xueQiService.getByxueXiaoIDandriQi(map);
			if(xQi!=null){
				int nianfen = nianFenService.selectByPrimaryKey(xQi.getNianfenid()).getRuxuenianfen();
				xueNian = nianfen+"~"+(nianfen+1);
				xueQi = xQi.getXueqi().toString();
			}
		}else{
			map.put("xuenian", xueNian);
			map.put("xueqi", xueQi);
			map.put("xuexiaoid",yXi.getXuexiaoid().toString());
			map.put("nianfen", xueNian.split("~")[0]);
			xQi = xueQiService.getByXueXiaoIDAndXueNianAndXueQi(map);
		}
		if(xQi==null){
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			map.put("riqi", format.format(date));
			map.put("xueXiaoID",yXi.getXuexiaoid().toString());
			List<XueQi> xueqis = xueQiService.getNewerXueQiByXueQi(map);
			if(xueqis != null &&xueqis.size() > 0){
				xQi = xueqis.get(0);
				int nianfen = nianFenService.selectByPrimaryKey(xQi.getNianfenid()).getRuxuenianfen();
				xueNian = nianfen+"~"+(nianfen+1);
				xueQi = xQi.getXueqi().toString();
			}
		}
		if(nianFen == null){
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(new Date()); 
			int month = cal.get(Calendar.MONTH)+1;
			if(month >= 9){
				nianFen = String.valueOf(cal.get(Calendar.YEAR));
			}else{
				nianFen = String.valueOf(cal.get(Calendar.YEAR)-1);
			}
		}
		List<Map<String, Integer>> yuanxizhuanyes = zhuanYeService.getAllByYuanXiID(yXi.getYuanxiid());
		if(yuanxizhuanyes != null && yuanxizhuanyes.size() > 0){
			ZhuanYe zYe = null;
			for (int i = 0; i < yuanxizhuanyes.size(); i++) {
				Integer a = yuanxizhuanyes.get(i).get("zhuanyeid");
				zYe = zhuanYeService.selectZhuanYeByDaiMa(String.valueOf(a));
				zhuanYes.add(zYe);
			}
			if(zhuanYe == null || "".equals(zhuanYe)){
				zhuanYe = zhuanYes.get(0).getZhuanyeid()+"";
			}
		}
		NianFen nFen = nianFenService.selectNianFenByRuXueNianFen(Integer.parseInt(nianFen));
			
		PingFenFangAn pingFenFangAn = new PingFenFangAn();
		banJis = banJiService.getAllByYuanXiIDAndZhuanYeDaiMaAndRuXueNianFen(Integer.parseInt(yuanXi), zhuanYe, nFen.getRuxuenianfenid());
		if(banJis.size()>0){
			if(banJi == null || "".equals(banJi)){
				banJi = banJis.get(0).getBanjiid().toString();
			}
					
			if (xQi != null) {
				XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xQi.getXueqiid(), banJi+",%","%,"+banJi+",%");
				if (xueQiDeYu != null) {
					fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
					pingFenFangAn = deYuService.getFangAnMingChengByFangAnID(xueQiDeYu.getFanganid());
					map.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid().toString());
					List<XueSheng> xueShengS = xueShengService.getAllByBanJiID(Integer.parseInt(banJi));
					if (!xueShengS.isEmpty()) {
						XueShengDeYu xueShengDeYu = null;
						for (XueSheng xueSheng : xueShengS) {
							xueShengDeYu = new XueShengDeYu();
							map.put("xueShengID", xueSheng.getXueshengid().toString());
							xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map);
							if (xueShengDeYu!=null) {
								xueShengDeYu.setXuehao(Integer.parseInt(xueSheng.getXuehao()));
								xueShengDeYu.setXingming(xueSheng.getXingming());
								String deFens[] = xueShengDeYu.getDefenxiangqing().split(",");
								Map<String, String> map3 = new HashMap<>();
								for (int j = 0; j < deFens.length; j++) {
									String defen[] = deFens[j].split(":");
									if (defen[1].endsWith(";")) {
										defen[1] = defen[1].substring(0, defen[1].length() - 1);
									}
									logger.info(defen[0]+":"+defen[1]);
									map3.put(defen[0], defen[1]);
								}
								logger.info(map3);
								double manFen = 0;
								List<String> yiJiZongFens=new ArrayList<>();
								for (DeYuPingFenFangAn deYuPingFenFangAn : fangAns) {
									manFen = manFen + deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen();
									double yiJiZongFen = 0;
									for (DeYuPingFenFangAn fangAn: deYuPingFenFangAn.getChildList()) {
										List<Integer> fenShus = new ArrayList<>();
										double erJiZongFen =0;
										if (fangAn.getXiangleixing()==0) {
											if (fangAn.getXueshengtianxie()==1) {
												logger.info(fangAn.getPingfenid()+":"+map3.get(fangAn.getPingfenid().toString()));
												erJiZongFen =erJiZongFen+Integer.parseInt(map3.get(fangAn.getPingfenid().toString()));
											}else if (fangAn.getXueshengtianxie()==2) {
												double danXiangFenHe = 0;
												String dString = map3.get(fangAn.getPingfenid().toString());
												String deFenXiang[] =dString.split(";");
												logger.info(fangAn.getMingcheng()+":"+deFenXiang.length);
												if (!deFenXiang[0].equals("无")){
													danXiangFenHe = deFenXiang.length*fangAn.getDanxiangfen();
													logger.info(fangAn.getMingcheng()+":"+danXiangFenHe);
													if (danXiangFenHe>fangAn.getManfen()) {
														erJiZongFen=erJiZongFen+fangAn.getManfen();
													}else {
														erJiZongFen = erJiZongFen + danXiangFenHe;
													}
												}
											}else {
												String dString = map3.get(fangAn.getPingfenid().toString());
												logger.info(fangAn.getPingfenid()+":"+dString);
												erJiZongFen =erJiZongFen + Integer.parseInt(dString);
											}
										}
										else {
											for (DeYuPingFenFangAn an : fangAn.getChildList()) {
												if (an.getXueshengtianxie()==1) {
													if (an.getManfen() <= fangAn.getManfen()) {
														erJiZongFen = erJiZongFen +Integer.parseInt(map3.get(an.getPingfenid().toString()));
													}	
												}else if (an.getXueshengtianxie()==2) {
													double danXiangFenHe = 0;
													String dString = map3.get(an.getPingfenid().toString());
													String deFenXiang[] =dString.split(";");
													logger.info(an.getMingcheng()+":"+deFenXiang.length);
													if (!deFenXiang[0].equals("无")) {
														danXiangFenHe = deFenXiang.length*an.getDanxiangfen();
														logger.info(an.getMingcheng()+":"+danXiangFenHe);
														if (danXiangFenHe>an.getManfen()) {
															erJiZongFen=erJiZongFen+an.getManfen();
														}else {
															erJiZongFen = erJiZongFen + danXiangFenHe;
														}
													}
												}else {
													String dString = map3.get(an.getPingfenid().toString());
													fenShus.add(Integer.parseInt(dString));
												}
											}
											if (!fenShus.isEmpty()) {
												erJiZongFen = erJiZongFen+Collections.max(fenShus);
											}
										}
										if (erJiZongFen>fangAn.getManfen()) {
											yiJiZongFen = yiJiZongFen+fangAn.getManfen();
										}else {
											yiJiZongFen = yiJiZongFen+erJiZongFen;
										}
									}
									logger.info("一级总分"+yiJiZongFen);
									if (yiJiZongFen>deYuPingFenFangAn.getManfen()) {
										yiJiZongFens.add(deYuPingFenFangAn.getManfen().toString());
									}else {
										yiJiZongFens.add(Double.toString(yiJiZongFen));
									}
									logger.info(manFen);
								}
								xueShengDeYu.setFenshu(yiJiZongFens);
							}else{
								xueShengDeYu = new XueShengDeYu();
								xueShengDeYu.setXuehao(Integer.parseInt(xueSheng.getXuehao()));
								xueShengDeYu.setXingming(xueSheng.getXingming());
							}
							xueShengDeYus.add(xueShengDeYu);
						}
					}
					mView.addObject("zhuangtai", xueQiDeYu.getZhuangtai());
				}
			}
		}
		mView.setViewName("guanliyuan/deyuchengji");
		mView.addObject("yuanxis", yuanXis);
		mView.addObject("nianfen", nianFen);
		mView.addObject("xueqi", xueQi);
		mView.addObject("xuenian", xueNian);
		mView.addObject("xuenians", xueNians);
		mView.addObject("yuanxiid", yuanXi);
		mView.addObject("zhuanyeid", zhuanYe);
		mView.addObject("nianfen", nianFen);
		mView.addObject("nianfens", nianFens);
		mView.addObject("banjiid", banJi);
		mView.addObject("banjis", banJis);
		mView.addObject("zhuanyes", zhuanYes);
		mView.addObject("xueShengDeYus", xueShengDeYus);
		mView.addObject("fangAn", fangAns);
		mView.addObject("pingFenFangAn", pingFenFangAn);
		mView.addObject("size", fangAns.size());
		
		return mView;
	}
	
	@RequestMapping(value="getzhuanye")
	@ResponseBody
	public Object getZhuanYe(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		String yuanXiID = request.getParameter("CODE");
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(Integer.parseInt(yuanXiID));
		List<ZhuanYe> zhuanYes = new ArrayList<>();
		List<Map<String, Integer>> yuanxizhuanyes = zhuanYeService.getAllByYuanXiID(yuanXi.getYuanxiid());
		if (yuanxizhuanyes!=null && yuanxizhuanyes.size() > 0) {
			ZhuanYe zhuanYe = null;
			for (int i = 0; i < yuanxizhuanyes.size(); i++) {
				zhuanYe = new ZhuanYe();
				Integer a = yuanxizhuanyes.get(i).get("zhuanyeid");
				zhuanYe = zhuanYeService.selectZhuanYeByDaiMa(String.valueOf(a));
				zhuanYes.add(zhuanYe);
			}
		}
		return JSON.toJSON(zhuanYes);
	}
	
	@RequestMapping(value="getbanji_gly")
	@ResponseBody
	public JSONObject getBanJi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		JSONObject json = new JSONObject();
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		
		List<BanJi> banJis = new ArrayList<>();
		String code[] = request.getParameter("CODE").split(",zytech,");
		String zhuanYeDaiMa = code[1];
		NianFen nianFen = nianFenService.selectNianFenByRuXueNianFen(Integer.parseInt(code[0]));
		if (nianFen!=null) {
			banJis = banJiService.getAllByYuanXiIDAndZhuanYeDaiMaAndRuXueNianFen(Integer.parseInt(code[2]), zhuanYeDaiMa, nianFen.getRuxuenianfenid());
		}
		json.put("banjis", banJis);
		return json;
	}
	
	@RequestMapping(value="deYuDetail_gly")
	public ModelAndView deYuXiangQing(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if(!Util.isGuanLiYuan(request)){
		response.sendRedirect("login");
			return null;
		}
		String id = request.getParameter("id");
		if (!Util.isNumeric(id)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu yongHu = (YongHu)request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(yongHu.getYonghuid());
		XueShengDeYu xueShengDeYu = deYuService.selectByDeYuFenID(Integer.parseInt(id));
		if (xueShengDeYu==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(xueShengDeYu.getXueshengid());
		BanJi banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(banJi.getYuanxiid());
		if (!yuanXi.getXuexiaoid().equals(guanLiYuan.getXuexiaoid())) {
			response.sendRedirect("logout");
			return null;
		}
		XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiDeYuID(xueShengDeYu.getXueqideyuid());
		List<DeYuPingFenFangAn> FangAnList = new ArrayList<>();
		FangAnList = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
		String deFens[] = xueShengDeYu.getDefenxiangqing().split(",");
		List<String> fenshu = new ArrayList<>();
		for (int i = 0; i < deFens.length; i++) {
			String defen[] = deFens[i].split(":");
			if (defen[1].endsWith(";")) {
				defen[1] = defen[1].substring(0, defen[1].length() - 1);
			}
			fenshu.add(defen[1]);
		}
		ModelAndView mView = new ModelAndView();
		mView.addObject("xuehao",xueSheng.getXuehao());
		mView.addObject("xueshengxingming",xueSheng.getXingming());
		mView.addObject("tianxietiaomu",FangAnList);
		mView.addObject("fenshu",fenshu);
		mView.setViewName("guanliyuan/deyuxiangqing");
		return mView;
	}
	
	@RequestMapping(value = "xiugaideyu_gly")
	public ModelAndView xiuGaiDeYu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String deYuFenID = request.getParameter("id");
		if (!Util.isNumeric(deYuFenID)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user=(YongHu)request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		XueShengDeYu deYu = deYuService.selectByDeYuFenID(Integer.parseInt(deYuFenID));
		if (deYu == null) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(deYu.getXueshengid());
		BanJi banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(banJi.getYuanxiid());
		if (!yuanXi.getXuexiaoid().equals(guanLiYuan.getXuexiaoid())) {
			response.sendRedirect("logout");
			return null;
		}
		XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiDeYuID(deYu.getXueqideyuid());
		String deFens[] = deYu.getDefenxiangqing().split(",");
		List<String> fenshu = new ArrayList<>();
		for (int i = 0; i < deFens.length; i++) {
			String defen[] = deFens[i].split(":");
			if (defen[1].endsWith(";")) {
				defen[1] = defen[1].substring(0, defen[1].length() - 1);
			}
			fenshu.add(defen[1]);
		}
		List<DeYuPingFenFangAn> FangAnList = new ArrayList<>();
		FangAnList = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
		mView.addObject("tianxietiaomu", FangAnList);
		mView.addObject("fenshu",fenshu);
		mView.addObject("id", deYuFenID);
		mView.addObject("xuenian", request.getParameter("xuenian"));
		mView.addObject("xueqi", request.getParameter("xueqi"));
		mView.addObject("yuanxi", request.getParameter("yuanxi"));
		mView.addObject("zhuanye", request.getParameter("zhuanye"));
		mView.addObject("banji", request.getParameter("banji"));
		mView.addObject("nianfen", request.getParameter("nianfen"));
		mView.setViewName("guanliyuan/xiugaideyu");
		return mView;
	}
	
	@RequestMapping(value = "updatedeyu_gly")
	@ResponseBody
	public String updatedeyu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String fanganid = request.getParameter("fanganid");
		String deYuFenID = request.getParameter("deyufenid");
		if (!Util.isNumeric(deYuFenID)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user=(YongHu)request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		XueShengDeYu deYu = deYuService.selectByDeYuFenID(Integer.parseInt(deYuFenID));
		if (deYu == null) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(deYu.getXueshengid());
		BanJi banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(banJi.getYuanxiid());
		if (!yuanXi.getXuexiaoid().equals(guanLiYuan.getXuexiaoid())) {
			response.sendRedirect("logout");
			return null;
		}
		List<Integer> pingFenIdList = deYuService.getPingfenIDByFangAnID(Integer.parseInt(fanganid));
		List<DeYuPingFenFangAn> list = deYuService.getAllByFangAnID(Integer.parseInt(fanganid));
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < pingFenIdList.size(); i++) {
			for (DeYuPingFenFangAn fangAn : list) {
				if (fangAn.getPingfenid() == pingFenIdList.get(i)) {
					if (fangAn.getXueshengtianxie().intValue() == 1 || fangAn.getXueshengtianxie().intValue() == 3) {
						String value = request.getParameter(pingFenIdList.get(i).toString());
						if (value == "" || value == null) {
							sBuffer.append(pingFenIdList.get(i) + ":0,");
						} else {
							if (fangAn.getManfen() >= Integer.parseInt(value)) {
								sBuffer.append(pingFenIdList.get(i) + ":"
										+ request.getParameter(pingFenIdList.get(i).toString()) + ",");
							} else {
								sBuffer.append(pingFenIdList.get(i) + ":" + fangAn.getManfen() + ",");
							}
						}
					} else if (fangAn.getXueshengtianxie().intValue() == 2) {
						String xString = request.getParameter(pingFenIdList.get(i).toString());
						if (xString == "" || xString == null) {
							sBuffer.append(pingFenIdList.get(i) + ":无" + ";");
						} else {
							sBuffer.append(pingFenIdList.get(i) + ":"
									+ request.getParameter(pingFenIdList.get(i).toString()) + ";");
						}
						for (int j = 1; request.getParameter(pingFenIdList.get(i).toString() + "-" + j) != null; j++) {
							sBuffer.append(request.getParameter(pingFenIdList.get(i).toString() + "-" + j) + ";");
						}
						sBuffer.append(",");
						logger.info(sBuffer);
					}
				}
			}
			logger.info(sBuffer);
		}
		String deFens[] = sBuffer.toString().split(",");
		Map<String, String> map2 = new HashMap<>();
		for (int i = 0; i < deFens.length; i++) {
			String defen[] = deFens[i].split(":");
			if (defen[1].endsWith(";")) {
				defen[1] = defen[1].substring(0, defen[1].length() - 1);
			}
			logger.info(defen[0]+":"+defen[1]);
			map2.put(defen[0], defen[1]);
		}
		logger.info(map2);
		double manFen = 0;
		List<Double> yiJiZongFens=new ArrayList<>();
		List<DeYuPingFenFangAn> fangAns = deYuService.getListByFangAnID(Integer.parseInt(fanganid));
		for (DeYuPingFenFangAn deYuPingFenFangAn : fangAns) {
			manFen = manFen + deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen();
			double yiJiZongFen = 0;
			for (DeYuPingFenFangAn fangAn: deYuPingFenFangAn.getChildList()) {
				List<Integer> fenShus = new ArrayList<>();
				double erJiZongFen =0;
				if (fangAn.getXiangleixing()==0) {
					if (fangAn.getXueshengtianxie()==1) {
						logger.info(fangAn.getPingfenid()+":"+map2.get(fangAn.getPingfenid().toString()));
						erJiZongFen =erJiZongFen+Integer.parseInt(map2.get(fangAn.getPingfenid().toString()));
					}else if (fangAn.getXueshengtianxie()==2) {
						double danXiangFenHe = 0;
						String dString = map2.get(fangAn.getPingfenid().toString());
						String deFenXiang[] =dString.split(";");
						logger.info(fangAn.getMingcheng()+":"+deFenXiang.length);
						if (!deFenXiang[0].equals("无")){
							danXiangFenHe = deFenXiang.length*fangAn.getDanxiangfen();
							logger.info(fangAn.getMingcheng()+":"+danXiangFenHe);
							if (danXiangFenHe>fangAn.getManfen()) {
								erJiZongFen=erJiZongFen+fangAn.getManfen();
							}else {
								erJiZongFen = erJiZongFen + danXiangFenHe;
							}
						}
					}else {
						String dString = map2.get(fangAn.getPingfenid().toString());
						erJiZongFen =erJiZongFen + Integer.parseInt(dString);
					}
				}
				else {
					for (DeYuPingFenFangAn an : fangAn.getChildList()) {
						if (an.getXueshengtianxie()==1) {
							if (an.getManfen() <= fangAn.getManfen()) {
								erJiZongFen = erJiZongFen +Integer.parseInt(map2.get(an.getPingfenid().toString()));
							}	
						}else if (an.getXueshengtianxie()==2) {
							double danXiangFenHe = 0;
							String dString = map2.get(an.getPingfenid().toString());
							String deFenXiang[] =dString.split(";");
							logger.info(an.getMingcheng()+":"+deFenXiang.length);
							if (!deFenXiang[0].equals("无")) {
								danXiangFenHe = deFenXiang.length*an.getDanxiangfen();
								logger.info(an.getMingcheng()+":"+danXiangFenHe);
								if (danXiangFenHe>an.getManfen()) {
									erJiZongFen=erJiZongFen+an.getManfen();
								}else {
									erJiZongFen = erJiZongFen + danXiangFenHe;
								}
							}
						}else {
							String dString = map2.get(an.getPingfenid().toString());
							fenShus.add(Integer.parseInt(dString));
						}
					}
					if (!fenShus.isEmpty()) {
						erJiZongFen = erJiZongFen+Collections.max(fenShus);
					}
				}
				if (erJiZongFen>fangAn.getManfen()) {
					yiJiZongFen = yiJiZongFen+fangAn.getManfen();
				}else {
					yiJiZongFen = yiJiZongFen+erJiZongFen;
				}
			}
			logger.info("一级总分"+yiJiZongFen);
			if (yiJiZongFen>deYuPingFenFangAn.getManfen()) {
				yiJiZongFens.add(deYuPingFenFangAn.getManfen()*deYuPingFenFangAn.getXuefen());
			}else {
				yiJiZongFens.add(yiJiZongFen*deYuPingFenFangAn.getXuefen());
			}
			
			logger.info(manFen);
		}
		double deFen = 0;
		for (int i = 0; i < yiJiZongFens.size(); i++) {
			deFen = deFen+yiJiZongFens.get(i);
		}
		logger.info("总学分*满分:" + manFen);
		logger.info("总学分*得分:" + deFen);
		double deyufen = deFen / manFen * 100;
		DecimalFormat df = new DecimalFormat("#.00");
		String deYuFen = df.format(deyufen);
		Map<String, String> map = new HashMap<>();
		map.put("defenxiangqing", sBuffer.toString());
		map.put("deyufen", deYuFen);
		map.put("deyufenid", deYuFenID);
		int i = deYuService.updateByDeYuFenID(map);
//		PrintWriter out = response.getWriter();
		if (i > 0) {
//			response.setContentType("text/html; charset=utf-8");
//			out.print("<script>alert('提交成功');</script>");
//			out.print("<script>window.opener=null;window.close();</script>");
			return "success";
		} else {
//			response.setContentType("text/html; charset=utf-8");
//			out.print("<script>alert('提交失败');</script>");
		}
		return null;
	}
	
	@RequestMapping(value = "deyufangan_gly")
	public ModelAndView faBuKaoPing(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan == null) {
			response.sendRedirect("logout");
			return null;
		}
		List<PingFenFangAn> pingFenFangAns = new ArrayList<>();
		pingFenFangAns = pingFenFangAnService
				.selectPingFenFangAnSByXueXiaoID(yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid());
		if (!pingFenFangAns.isEmpty()) {
			for (PingFenFangAn pingFenFangAn : pingFenFangAns) {
				pingFenFangAn.setJiaoshixingming(
						yongHuService.selectYongHuByID(pingFenFangAn.getJiaoshiid()).getYonghuxingming());
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("guanliyuan/deyufangan");
		mView.addObject("fangAns", pingFenFangAns);
		return mView;
	}
	
	@RequestMapping(value = "fangAnDetail_gly")
	public ModelAndView fangAnDeTail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan == null) {
			response.sendRedirect("logout");
			return null;
		}
		String fangAnID = request.getParameter("id");
		if (!Util.isNumeric(fangAnID)) {
			response.sendRedirect("logout");
			return null;
		}
		PingFenFangAn fangAn = pingFenFangAnService.selectByID(Integer.parseInt(fangAnID));
		if (fangAn == null) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu yongHu = yongHuService.selectYongHuByID(fangAn.getJiaoshiid());
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(yongHu.getYuanxiid());
		if (!yuanXi.getXuexiaoid().equals(yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid())) {
			if (!fangAnID.equals("1")) {
				response.sendRedirect("logout");
				return null;
			}
		}
		List<DeYuPingFenFangAn> fangAns = new ArrayList<>();
		fangAns = deYuService.getListByFangAnID(Integer.parseInt(fangAnID));
		ModelAndView mView = new ModelAndView();
		mView.setViewName("guanliyuan/deyudetail");
		mView.addObject("tianxietiaomu", fangAns);
		return mView;
	}
	
	@RequestMapping(value = "xiuGai_gly")
	public ModelAndView XiuGai(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan == null) {
			response.sendRedirect("logout");
			return null;
		}
		String fangAnID = request.getParameter("id");
		if (!Util.isNumeric(fangAnID)) {
			response.sendRedirect("logout");
			return null;
		}
		PingFenFangAn fangAn = pingFenFangAnService.selectByID(Integer.parseInt(fangAnID));
		if (fangAn == null) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu yongHu = yongHuService.selectYongHuByID(fangAn.getJiaoshiid());
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(yongHu.getYuanxiid());
		if (!yuanXi.getXuexiaoid().equals(yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid())) {
			if (!fangAnID.equals("1")) {
				response.sendRedirect("logout");
				return null;
			}
		}
		List<DeYuPingFenFangAn> fangAns = new ArrayList<>();
		fangAns = deYuService.getListByFangAnID(Integer.parseInt(fangAnID));
		ModelAndView mView = new ModelAndView();
		mView.setViewName("guanliyuan/deyuxiugai");
		mView.addObject("tianxietiaomu", fangAns);
		return mView;
	}
	
	@RequestMapping(value = "sub-new-deyu-gly")
	@ResponseBody
	public String SubNewDeYu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String fangAnID = request.getParameter("fanganid");
		Boolean bool = deYuService.insertNewDeYuKaoPingBiao(request, response, Integer.parseInt(fangAnID));
		if (bool) {
			return "success";
		} else {
			return "fail";
		}
	}
}
