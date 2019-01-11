package com.web.controller.web.shuji;

import java.io.IOException;
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
import com.web.model.NianFen;
import com.web.model.PingFenFangAn;
import com.web.model.ShuJi;
import com.web.model.XueQi;
import com.web.model.XueQiDeYu;
import com.web.model.XueSheng;
import com.web.model.XueShengDeYu;
import com.web.model.YongHu;
import com.web.model.YuanXi;
import com.web.service.BanJiService;
import com.web.service.DeYuService;
import com.web.service.NianFenService;
import com.web.service.ShuJiService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.service.YuanXiService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
@LoginStatusAnnotation(status="shuji")
public class DeYuController_sj {
	static Logger logger = Logger.getLogger(DeYuController_sj.class);
	
	@Autowired
	private ShuJiService shuJiService;
	
	@Autowired 
	private YuanXiService yuanXiService;
	
	@Autowired
	private BanJiService banJiService;
	
	@Autowired 
	private XueQiService xueQiService;
	
	@Autowired
	private DeYuService deYuService;
	
	@Autowired
	private XueShengService xueShengService; 
	
	@Autowired
	private NianFenService nianFenService;
	
	@RequestMapping(value="deyuchengji_sj")
	public ModelAndView deYuChengJi(HttpServletRequest request,HttpServletResponse response) throws IOException {
		if (!Util.isShuJi(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		ShuJi shuJi = shuJiService.selectByPrimaryKey(user.getYonghuid());
		if (shuJi==null) {
			response.sendRedirect("logout");
			return null;
		}
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(shuJi.getYuanxiid());
		List<BanJi> banJis = new ArrayList<>();
		List<String> xueNianList = new ArrayList<>();
		List<NianFen> nianFenList = new ArrayList<>();
		List<XueShengDeYu> xueShengDeYus = new ArrayList<>();
		List<DeYuPingFenFangAn> fangAns = new ArrayList<>();
		String xueNian = request.getParameter("xuenian");
		String xueQi = request.getParameter("xueqi");
		String banJiID = request.getParameter("banji");
		String nianFen = request.getParameter("nianfen");
		Map<String, String> map = new HashMap<>();
		XueQi xQi = new XueQi();
		
		xueNianList = xueQiService.getXuenianByXuexiaoID(yuanXi.getXuexiaoid());
		nianFenList = nianFenService.getNianFen();
		
		
		if(xueNian==null || xueQi == null){
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			map.put("riqi", format.format(date));
			map.put("xueXiaoID",yuanXi.getXuexiaoid().toString());
			xQi = xueQiService.getByxueXiaoIDandriQi(map);
			if(xQi!=null){
				int nianfen = nianFenService.selectByPrimaryKey(xQi.getNianfenid()).getRuxuenianfen();
				xueNian = nianfen+"~"+(nianfen+1);
				xueQi = xQi.getXueqi().toString();
			}
		}else{
			map.put("xuenian", xueNian);
			map.put("xueqi", xueQi);
			map.put("xuexiaoid",yuanXi.getXuexiaoid().toString());
			map.put("nianfen", xueNian.split("~")[0]);
			xQi = xueQiService.getByXueXiaoIDAndXueNianAndXueQi(map);
		}
		if(xQi==null){
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			map.put("riqi", format.format(date));
			map.put("xueXiaoID",yuanXi.getXuexiaoid().toString());
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
		NianFen nFen = nianFenService.selectNianFenByRuXueNianFen(Integer.parseInt(nianFen));
		banJis = banJiService.getAllByyuanXiIDAndruXueNianFen(yuanXi.getYuanxiid(), nFen.getRuxuenianfenid());
		
		if( banJiID== null){
			banJiID = banJis.get(0).getBanjiid().toString();
		}
		BanJi banji = banJiService.selectByPrimaryKey(Integer.parseInt(banJiID));
		PingFenFangAn pingFenFangAn =  new PingFenFangAn();
		
			if (xQi != null) {
				XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiID(xQi.getXueqiid());
				if (xueQiDeYu != null) {
					fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
					pingFenFangAn = deYuService.getFangAnMingChengByFangAnID(xueQiDeYu.getFanganid());
					map.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid().toString());
					List<XueSheng> xueShengS = xueShengService.getAllByBanJiID(Integer.parseInt(banJiID));
					if (!xueShengS.isEmpty()) {
						XueShengDeYu xueShengDeYu = null;
						for (XueSheng xueSheng : xueShengS) {
							xueShengDeYu = new XueShengDeYu();
							map.put("xueShengID", xueSheng.getXueshengid().toString());
							xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map);
							List<String> yiJiZongFens=new ArrayList<>();
							if (xueShengDeYu!=null) {
//								xueShengDeYu.setXueshengid(xueQiDeYu.getZhuangtai()); // 将状态放在了学生id里
//								xueShengDeYu.setXuehao(Integer.parseInt(xueShengService.getUserById(xueShengDeYu.getXueshengid()).getXuehao()));
//								xueShengDeYu.setXingming(xueShengService.getUserById(xueShengDeYu.getXueshengid()).getXingming());
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
								xueShengDeYus.add(xueShengDeYu);
							}else{
								xueShengDeYu = new XueShengDeYu();
								xueShengDeYu.setXuehao(Integer.parseInt(xueSheng.getXuehao()));
								xueShengDeYu.setXingming(xueSheng.getXingming());
								xueShengDeYu.setFenshu(yiJiZongFens);
								xueShengDeYus.add(xueShengDeYu);
							}
						}
					}
				}
			}
			logger.info(xueNian+"   "+xueQi+"   "+banJiID);
		
		ModelAndView mView = new ModelAndView();
		mView.setViewName("shuji/deyuchengji");
		mView.addObject("xuenian", xueNian);
		mView.addObject("xueqi", xueQi);
		mView.addObject("nianfen", nianFen);
		mView.addObject("xueShengDeYus", xueShengDeYus);
		mView.addObject("fangAn", fangAns);
		mView.addObject("size", fangAns.size());
		mView.addObject("banjiid", banJiID);
		mView.addObject("banji", banji);
		mView.addObject("banjis", banJis);
		mView.addObject("xueNianList", xueNianList);
		mView.addObject("nianFenList", nianFenList);
		mView.addObject("pingFenFangAn", pingFenFangAn);
		return mView;
	}
	
	@RequestMapping(value="getbanji")
	@ResponseBody
	public JSONObject getBanJi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		JSONObject json = new JSONObject();
		if (!Util.isShuJi(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		ShuJi shuJi = shuJiService.selectByPrimaryKey(user.getYonghuid());
		if (shuJi==null) {
			response.sendRedirect("logout");
			return null;
		}
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(shuJi.getYuanxiid());
		List<BanJi> banjis = new ArrayList<>();
		String code = request.getParameter("CODE");
		NianFen nianFen = nianFenService.selectNianFenByRuXueNianFen(Integer.parseInt(code));
		if (nianFen!=null) {
			banjis = banJiService.getAllByyuanXiIDAndruXueNianFen(yuanXi.getYuanxiid(), nianFen.getRuxuenianfenid());
		}
			request.getSession().setAttribute("banjiid", null);
				json.put("banjis", banjis);
		return  json;
	}
	
	@RequestMapping(value="deYuDetail_sj")
	public ModelAndView deYuXiangQing(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isShuJi(request)) {
			response.sendRedirect("login");
			return null;
		}
		String id = request.getParameter("id");
		if (!Util.isNumeric(id)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu yongHu = (YongHu)request.getSession().getAttribute("user");
		ShuJi shuJi = shuJiService.selectByPrimaryKey(yongHu.getYonghuid());
		XueShengDeYu xueShengDeYu = deYuService.selectByDeYuFenID(Integer.parseInt(id));
		if (xueShengDeYu==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(xueShengDeYu.getXueshengid());
		BanJi banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
		if (banJi.getYuanxiid()!=shuJi.getYuanxiid()) {
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
		mView.setViewName("shuji/deyuxiangqing");
		return mView;
	}
}
