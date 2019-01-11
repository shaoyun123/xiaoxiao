package com.web.controller.web.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.eclipse.aether.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.annotation.LoginStatusAnnotation;
import com.web.model.BanJi;
import com.web.model.DeYuPingFenFangAn;
import com.web.model.PingFenFangAn;
import com.web.model.TiXing;
import com.web.model.XiaoXiFaSong;
import com.web.model.XueQi;
import com.web.model.XueQiDeYu;
import com.web.model.XueSheng;
import com.web.model.XueShengDeYu;
import com.web.service.BanJiService;
import com.web.service.DeYuService;
import com.web.service.JiGuangService;
import com.web.service.PingFenFangAnService;
import com.web.service.TiXingService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.util.Util;

@Controller
@LoginStatusAnnotation(status="xuesheng")
public class DeYuController {
	static Logger logger = Logger.getLogger(DeYuController.class);

	@Autowired
	private DeYuService deYuService;

	@Autowired
	private XueQiService xueqiService;

	@Autowired
	private XueShengService xueShengService;
	
	@Autowired
	private BanJiService banJiService;
	@Autowired
	private PingFenFangAnService  pingFenFangAnService;
	@Autowired
	private JiGuangService jiGuangService;
	@Autowired
	private TiXingService tixingService;

	/**
	 * 查询德育成绩
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "chaxundeyuchengji") 
	public ModelAndView chaXunDeYuChengJi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		List<String> xuenianList = new ArrayList<String>();
		if (Util.checkSession(request)) {
			ModelAndView mView = new ModelAndView();
			Map<String, String> map = new HashMap<>();
			XueSheng user = null;
			try {
				user = (XueSheng) session.getAttribute("user");
			} catch (Exception e) {
				logger.error(e);
				response.sendRedirect("logout");
				return null;
			}
			String xueNian = request.getParameter("xuenian");
			String xueQi = request.getParameter("xueqi");
			String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
			String xueXiaoID = xueXiaoXueHao[0];
			map.put("xuenian", xueNian);
			map.put("xueqi", xueQi);
			map.put("xuexiaoid", xueXiaoID);
			map.put("nianfen", xueNian.split("~")[0]);
//				查出本学校的所有学年
			xuenianList = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(user.getXuexiaoXuehao().split("_")[0]));
			XueQi xQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			if (xQi != null) {
				XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xQi.getXueqiid(),user.getBanjiid()+",%","%,"+user.getBanjiid()+",%");
				if (xueQiDeYu != null) {
					map.put("xueShengID", user.getXueshengid().toString());
					map.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid().toString());
					XueShengDeYu xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map);
					if (xueShengDeYu == null) {
						mView.addObject("xuenian", xueNian);
						mView.addObject("xueqi", xueQi);
						mView.setViewName("stu/deyuchengji");
						return mView;
					}
					String deFens[] = xueShengDeYu.getDefenxiangqing().split(",");
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
					List<DeYuPingFenFangAn> fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
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
									danXiangFenHe = deFenXiang.length*fangAn.getDanxiangfen();
									logger.info(fangAn.getMingcheng()+":"+danXiangFenHe);
									if (danXiangFenHe>fangAn.getManfen()) {
										erJiZongFen=erJiZongFen+fangAn.getManfen();
									}else {
										erJiZongFen = erJiZongFen + danXiangFenHe;
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
										danXiangFenHe = deFenXiang.length*an.getDanxiangfen();
										logger.info(an.getMingcheng()+":"+danXiangFenHe);
										if (danXiangFenHe>an.getManfen()) {
											erJiZongFen=erJiZongFen+an.getManfen();
										}else {
											erJiZongFen = erJiZongFen + danXiangFenHe;
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
					/*for (int i = 0;i < yiJiZongFens.size(); i++) {
						double defen = yiJiZongFens.get(i) / manFen * 100;
						DecimalFormat df = new DecimalFormat("#.00");
						String deFen = df.format(defen);
						defens.add(deFen);
					}*/
					mView.addObject("xuenianList",xuenianList);
					mView.addObject("fangAn", fangAns);
					mView.addObject("xuenian", xQi.getXuenian());
					mView.addObject("xueqi",xQi.getXueqi());
					mView.addObject("deYu", xueShengDeYu);
					mView.addObject("fenshu",yiJiZongFens);
					mView.addObject("zhuangtai", xueQiDeYu.getZhuangtai());
					mView.setViewName("stu/deyuchengji");
					return mView;
				} else {
					mView.addObject("xuenianList",xuenianList);
					mView.addObject("xuenian", xueNian);
					mView.addObject("xueqi", xueQi);
					mView.setViewName("stu/deyuchengji");
					return mView;
				}
			} else {
				mView.addObject("xuenianList",xuenianList);
				mView.addObject("xuenian", xueNian);
				mView.addObject("xueqi", xueQi);
				mView.setViewName("stu/deyuchengji");
				return mView;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;

	}

	@RequestMapping(value = "deyuchengji")
	public ModelAndView deYuChengJi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		List<Map<String,Object>> xuenianList = new ArrayList<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			ModelAndView mView = new ModelAndView();
			XueSheng user = null;
			try {
				user = (XueSheng) session.getAttribute("user");
			} catch (Exception e) {
				logger.error(e);
				response.sendRedirect("logout");
				return null;
			}
			Map<String, String> map = new HashMap<>();
			Map<String, String> map2 = new HashMap<>();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
			String xueXiaoID = xueXiaoXueHao[0];
			map.put("riqi", format.format(date));
			map.put("xueXiaoID", xueXiaoID);
			
			xuenianList = deYuService.findShouYeXueShengDeYuByBanJiID(user.getBanjiid() + ",%","%," + user.getBanjiid() + ",%");
			if(xuenianList.size()>0){
				for(Map<String,Object> maps : xuenianList){
					map2.put("xueShengID", user.getXueshengid()+"");
					map2.put("xueQiDeYuID", maps.get("xueqideyuid").toString());
					XueShengDeYu xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map2);
					if(xueShengDeYu !=null){
						maps.put("deyufen", xueShengDeYu.getDeyufen());
						maps.put("deyufenid", xueShengDeYu.getDeyufenid());
					}else{
						maps.put("deyufen", 0.00);
						maps.put("deyufenid", 0);
					}
				}
			}
			mView.addObject("xinxiList", xuenianList);
			
//			查出本学校的所有学年
//			xuenianList = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xueXiaoID));
//			mView.addObject("xuenianList", xuenianList);
			
//			List<XueQiDeYu> xueQiDeYuList = deYuService.selectXueQiDeYuByBanJiID(user.getBanjiid() + ",%","%," + user.getBanjiid() + ",%");
//			
//			if(xueQiDeYuList.size()>0){
//				for(XueQiDeYu xueQiDeYu:xueQiDeYuList){
//					Map<String,Object> mapList = new HashMap<>();
//					XueQi xueQi = xueqiService.getByXueXiaoIDAndXueQiID(Integer.parseInt(user.getXuexiaoXuehao().split("_")[0]),xueQiDeYu.getXueqiid());
//					if(xueQi!=null){
//						List<DeYuPingFenFangAn> fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
//						xueQi.setZhuangtai(xueQiDeYu.getZhuangtai());
//						map2.put("xueShengID", user.getXueshengid()+"");
//						map2.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid()+"");
//						XueShengDeYu xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map2);
//						if(xueShengDeYu != null){
//							xueQi.setDeyufenid(xueShengDeYu.getDeyufenid());
//							xueQi.setManfen(xueShengDeYu.getDeyufen());
//							
//							
//							String deFens[] = xueShengDeYu.getDefenxiangqing().split(",");
//							Map<String, String> map3 = new HashMap<>();
//							for (int i = 0; i < deFens.length; i++) {
//								String defen[] = deFens[i].split(":");
//								if (defen[1].endsWith(";")) {
//									defen[1] = defen[1].substring(0, defen[1].length() - 1);
//								}
//								logger.info(defen[0]+":"+defen[1]);
//								map3.put(defen[0], defen[1]);
//							}
//							logger.info(map3);
//							double manFen = 0;
//							List<Double> yiJiZongFens=new ArrayList<>();
//							
//							for (DeYuPingFenFangAn deYuPingFenFangAn : fangAns) {
//								manFen = manFen + deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen();
//								double yiJiZongFen = 0;
//								for (DeYuPingFenFangAn fangAn: deYuPingFenFangAn.getChildList()) {
//									List<Integer> fenShus = new ArrayList<>();
//									double erJiZongFen =0;
//									if (fangAn.getXiangleixing()==0) {
//										if (fangAn.getXueshengtianxie()==1) {
//											logger.info(fangAn.getPingfenid()+":"+map3.get(fangAn.getPingfenid().toString()));
//											erJiZongFen =erJiZongFen+Integer.parseInt(map3.get(fangAn.getPingfenid().toString()));
//										}else if (fangAn.getXueshengtianxie()==2) {
//											double danXiangFenHe = 0;
//											String dString = map3.get(fangAn.getPingfenid().toString());
//											String deFenXiang[] =dString.split(";");
//											logger.info(fangAn.getMingcheng()+":"+deFenXiang.length);
//											if (!deFenXiang[0].equals("无")){
//												danXiangFenHe = deFenXiang.length*fangAn.getDanxiangfen();
//												logger.info(fangAn.getMingcheng()+":"+danXiangFenHe);
//												if (danXiangFenHe>fangAn.getManfen()) {
//													erJiZongFen=erJiZongFen+fangAn.getManfen();
//												}else {
//													erJiZongFen = erJiZongFen + danXiangFenHe;
//												}
//											}
//										}else {
//											String dString = map3.get(fangAn.getPingfenid().toString());
//											erJiZongFen =erJiZongFen + Integer.parseInt(dString);
//										}
//									}
//									else {
//										for (DeYuPingFenFangAn an : fangAn.getChildList()) {
//											if (an.getXueshengtianxie()==1) {
//												if (an.getManfen() <= fangAn.getManfen()) {
//													erJiZongFen = erJiZongFen +Integer.parseInt(map3.get(an.getPingfenid().toString()));
//												}	
//											}else if (an.getXueshengtianxie()==2) {
//												double danXiangFenHe = 0;
//												String dString = map3.get(an.getPingfenid().toString());
//												String deFenXiang[] =dString.split(";");
//												logger.info(an.getMingcheng()+":"+deFenXiang.length);
//												if (!deFenXiang[0].equals("无")) {
//													danXiangFenHe = deFenXiang.length*an.getDanxiangfen();
//													logger.info(an.getMingcheng()+":"+danXiangFenHe);
//													if (danXiangFenHe>an.getManfen()) {
//														erJiZongFen=erJiZongFen+an.getManfen();
//													}else {
//														erJiZongFen = erJiZongFen + danXiangFenHe;
//													}
//												}
//											}else {
//												String dString = map3.get(an.getPingfenid().toString());
//												fenShus.add(Integer.parseInt(dString));
//											}
//										}
//										if (!fenShus.isEmpty()) {
//											erJiZongFen = erJiZongFen+Collections.max(fenShus);
//										}
//									}
//									if (erJiZongFen>fangAn.getManfen()) {
//										yiJiZongFen = yiJiZongFen+fangAn.getManfen();
//									}else {
//										yiJiZongFen = yiJiZongFen+erJiZongFen;
//									}
//								}
//								logger.info("一级总分"+yiJiZongFen);
//								if (yiJiZongFen>deYuPingFenFangAn.getManfen()) {
//									yiJiZongFens.add(deYuPingFenFangAn.getManfen().doubleValue());
//								}else {
//									yiJiZongFens.add(yiJiZongFen);
//								}
//								logger.info(manFen);
//							}
//							
//							mView.addObject("fangAn", xueQiDeYuList);
////							mView.addObject("deYu", xueShengDeYu);
////							mView.addObject("fenshu",yiJiZongFens);
//							mapList.put("zhuangtai", xueQiDeYu.getZhuangtai());
//							mapList.put("fangAn", fangAns);
//							mapList.put("fenshu",yiJiZongFens);
//							mView.addObject("size",yiJiZongFens.size());
//							mapList.put("deYu", xueShengDeYu);
//							
//						}else{
//							// 本学期没有填写德育成绩
//							mapList.put("fenshu",null);
//							mapList.put("fangAn", fangAns);
//							mapList.put("size",fangAns.size());
//							mView.addObject("fangAn", xueQiDeYuList);
//							xueQi.setDeyufenid(0);
//						}
//					}else{
//						//  本学期没有德育成绩
//						mapList.put("fenshu",null);
//						xueQi.setDeyufenid(-1);
//					}
//					mapList.put("xueqi",xueQi);
//					xuenianList.add(mapList);
//				}
//			}else{
//				mView.addObject("fangAn", xueQiDeYuList);
//			}
//			mView.addObject("xuenianList", xuenianList);
		
			
			//  取得最新学期的德育成绩  
			
//			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
//			System.out.println("学年："+xueQi.getXuenian()+"----------------");
//			mView.addObject("xuenian", xueQi.getXuenian());
//			mView.addObject("xueqi", xueQi.getXueqi());
			
//			if (xueQi==null) {
//				mView.setViewName("stu/deyuchengji");
//				return mView;
//			}
//			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(), user.getBanjiid()+",%","%,"+user.getBanjiid()+",%");
//			if (xueQiDeYu==null) {
//				mView.setViewName("stu/deyuchengji");
//				return mView;
//			}
//			map.put("xueShengID", user.getXueshengid().toString());
//			map.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid().toString());
//			XueShengDeYu xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map);
//			if (xueShengDeYu == null) {
//				mView.setViewName("stu/deyuchengji");
//				return mView;
//			}
//			
//			
//			
//			String deFens[] = xueShengDeYu.getDefenxiangqing().split(",");
//			Map<String, String> map3 = new HashMap<>();
//			for (int i = 0; i < deFens.length; i++) {
//				String defen[] = deFens[i].split(":");
//				if (defen[1].endsWith(";")) {
//					defen[1] = defen[1].substring(0, defen[1].length() - 1);
//				}
//				logger.info(defen[0]+":"+defen[1]);
//				map3.put(defen[0], defen[1]);
//			}
//			logger.info(map3);
//			double manFen = 0;
//			List<Double> yiJiZongFens=new ArrayList<>();
//			List<DeYuPingFenFangAn> fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
//			for (DeYuPingFenFangAn deYuPingFenFangAn : fangAns) {
//				manFen = manFen + deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen();
//				double yiJiZongFen = 0;
//				for (DeYuPingFenFangAn fangAn: deYuPingFenFangAn.getChildList()) {
//					List<Integer> fenShus = new ArrayList<>();
//					double erJiZongFen =0;
//					if (fangAn.getXiangleixing()==0) {
//						if (fangAn.getXueshengtianxie()==1) {
//							logger.info(fangAn.getPingfenid()+":"+map3.get(fangAn.getPingfenid().toString()));
//							erJiZongFen =erJiZongFen+Integer.parseInt(map3.get(fangAn.getPingfenid().toString()));
//						}else if (fangAn.getXueshengtianxie()==2) {
//							double danXiangFenHe = 0;
//							String dString = map3.get(fangAn.getPingfenid().toString());
//							String deFenXiang[] =dString.split(";");
//							logger.info(fangAn.getMingcheng()+":"+deFenXiang.length);
//							if (!deFenXiang[0].equals("无")){
//								danXiangFenHe = deFenXiang.length*fangAn.getDanxiangfen();
//								logger.info(fangAn.getMingcheng()+":"+danXiangFenHe);
//								if (danXiangFenHe>fangAn.getManfen()) {
//									erJiZongFen=erJiZongFen+fangAn.getManfen();
//								}else {
//									erJiZongFen = erJiZongFen + danXiangFenHe;
//								}
//							}
//						}else {
//							String dString = map3.get(fangAn.getPingfenid().toString());
//							erJiZongFen =erJiZongFen + Integer.parseInt(dString);
//						}
//					}
//					else {
//						for (DeYuPingFenFangAn an : fangAn.getChildList()) {
//							if (an.getXueshengtianxie()==1) {
//								if (an.getManfen() <= fangAn.getManfen()) {
//									erJiZongFen = erJiZongFen +Integer.parseInt(map3.get(an.getPingfenid().toString()));
//								}	
//							}else if (an.getXueshengtianxie()==2) {
//								double danXiangFenHe = 0;
//								String dString = map3.get(an.getPingfenid().toString());
//								String deFenXiang[] =dString.split(";");
//								logger.info(an.getMingcheng()+":"+deFenXiang.length);
//								if (!deFenXiang[0].equals("无")) {
//									danXiangFenHe = deFenXiang.length*an.getDanxiangfen();
//									logger.info(an.getMingcheng()+":"+danXiangFenHe);
//									if (danXiangFenHe>an.getManfen()) {
//										erJiZongFen=erJiZongFen+an.getManfen();
//									}else {
//										erJiZongFen = erJiZongFen + danXiangFenHe;
//									}
//								}
//							}else {
//								String dString = map3.get(an.getPingfenid().toString());
//								fenShus.add(Integer.parseInt(dString));
//							}
//						}
//						if (!fenShus.isEmpty()) {
//							erJiZongFen = erJiZongFen+Collections.max(fenShus);
//						}
//					}
//					if (erJiZongFen>fangAn.getManfen()) {
//						yiJiZongFen = yiJiZongFen+fangAn.getManfen();
//					}else {
//						yiJiZongFen = yiJiZongFen+erJiZongFen;
//					}
//				}
//				logger.info("一级总分"+yiJiZongFen);
//				if (yiJiZongFen>deYuPingFenFangAn.getManfen()) {
//					yiJiZongFens.add(deYuPingFenFangAn.getManfen().doubleValue());
//				}else {
//					yiJiZongFens.add(yiJiZongFen);
//				}
//				logger.info(manFen);
//			}
//			/*for (int i = 0;i < yiJiZongFens.size(); i++) {
//				double defen = yiJiZongFens.get(i) / manFen * 100;
//				DecimalFormat df = new DecimalFormat("#.00");
//				String deFen = df.format(defen);
//				defens.add(deFen);
//			}*/
//			
//			
////			System.out.println(xueQi.getXueqi()+"====================="+xueQi.getXuenian());
////		
//			mView.addObject("fangAn", fangAns);
//			mView.addObject("deYu", xueShengDeYu);
//			mView.addObject("fenshu",yiJiZongFens);
//			mView.addObject("zhuangtai", xueQiDeYu.getZhuangtai());
			mView.setViewName("stu/deyuchengji");
			return mView;
		} else {
			response.sendRedirect("login");
		}
		return null;

	}

	@RequestMapping(value = "tijiaobiaoge")
	public void tiJiaoBiaoGe(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = null;
			try {
				user = (XueSheng) session.getAttribute("user");
			} catch (Exception e) {
				logger.error(e);
				response.sendRedirect("logout");
				return;
			}
			String fanganid = request.getParameter("fanganid");
			PingFenFangAn pingFenFangAn = pingFenFangAnService.selectByID(Integer.parseInt(fanganid));
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> map = new HashMap<>();
			String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
			String xueXiaoID = xueXiaoXueHao[0];
			map.put("riqi", format.format(date));
			map.put("xueXiaoID", xueXiaoID);
			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(),user.getBanjiid()+",%","%,"+user.getBanjiid()+",%");
			Map<String, String> map3 = new HashMap<>();
			map3.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid().toString());
			map3.put("xueShengID", user.getXueshengid().toString());
			XueShengDeYu xueShengDeYu2 = deYuService.selectByXueShengIDAndXueQiDeYuID(map3);
			PrintWriter out = response.getWriter();
			if (xueShengDeYu2!=null) {
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('当前已提交');</script>");
				out.print("<script>location='deyuchengji';</script>");
				return ;
			}
			List<Integer> pingFenIdList = deYuService.getPingfenIDByFangAnID(Integer.parseInt(fanganid));
			List<DeYuPingFenFangAn> list = deYuService.getAllByFangAnID(Integer.parseInt(fanganid));
			StringBuffer sBuffer = new StringBuffer();
			for (int i = 0; i < pingFenIdList.size(); i++) {
				for (DeYuPingFenFangAn fangAn : list) {
					if (fangAn.getPingfenid() == pingFenIdList.get(i)) {
						if (fangAn.getXueshengtianxie().intValue() == 1
								|| fangAn.getXueshengtianxie().intValue() == 3) {
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
							for (int j = 1; request
									.getParameter(pingFenIdList.get(i).toString() + "-" + j) != null; j++) {
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
			/*for (int i = 0;i < yiJiZongFens.size(); i++) {
				double defen = yiJiZongFens.get(i) / manFen * 100;
				DecimalFormat df = new DecimalFormat("#.00");
				String deFen = df.format(defen);
				defens.add(deFen);
			}*/
			double deFen = 0;
			for (int i = 0; i < yiJiZongFens.size(); i++) {
				deFen = deFen+yiJiZongFens.get(i);
			}
			logger.info("总学分*满分:" + manFen);
			logger.info("总学分*得分:" + deFen);
			double deyufen = deFen / manFen * 100;
			DecimalFormat df = new DecimalFormat("#.00");
			String deYuFen = df.format(deyufen);
			XueShengDeYu xueShengDeYu = new XueShengDeYu();
			xueShengDeYu.setDefenxiangqing(sBuffer.toString());
			xueShengDeYu.setXueshengid(user.getXueshengid());
			xueShengDeYu.setXueqideyuid(xueQiDeYu.getXueqideyuid());
			xueShengDeYu.setDeyufen(Double.valueOf(deYuFen).doubleValue());
			int i = deYuService.insert(xueShengDeYu);
			if (i > 0) {
				logger.info("学生德育添加成功：" + xueShengDeYu.getXueshengid());
				
				//发送激光消息
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("填写德育成绩");
				xiaoXiFaSong.setXiaoXiNeiRong(user.getXingming()+"填写德育成绩@"+pingFenFangAn.getFanganmingcheng()+"@");
				xiaoXiFaSong.setShuJuId(xueShengDeYu.getDeyufenid());
				xiaoXiFaSong.setShuJuLeiXing(6);
				xiaoXiFaSong.setFaSongLeiXing(2);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(user.getXuexiaoXuehao().split("_")[0]);
				//发送提醒消息
				TiXing tiXing = new TiXing();
				tiXing.setNeirong(user.getXingming()+"填写德育成绩@"+pingFenFangAn.getFanganmingcheng()+"$");
				tiXing.setShijian(date);
				tiXing.setZhuangtai(0);
				tiXing.setShujuid(xueShengDeYu.getDeyufenid());
				tiXing.setType(5);
				tiXing.setJieshourenid(pingFenFangAn.getJiaoshiid());
				tixingService.insert(tiXing);
				xiaoXiFaSong.setFaSongMuBiao(pingFenFangAn.getJiaoshiid().toString());
				jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('提交成功');</script>");
				out.print("<script>location='deyuchengji';</script>");
			} else {
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('提交失败');</script>");
				out.print("<script>location='tianxiekaopingbiao';</script>");
			}
			logger.info(list);
			logger.info(fanganid);

		} else {
			response.sendRedirect("login");
		}
	}

	@RequestMapping(value = "deyufengongshi")
	public ModelAndView deYuFenGongShi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			ModelAndView mView = new ModelAndView();
			XueSheng user = null;
			try {
				user = (XueSheng) session.getAttribute("user");
			} catch (Exception e) {
				logger.error(e);
				response.sendRedirect("logout");
				return null;
			}
			Map<String, String> map = new HashMap<>();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
			logger.info(xueXiaoXueHao[0]);
			logger.info(xueXiaoXueHao[1]);
			String xueXiaoID = xueXiaoXueHao[0];
			map.put("riqi", format.format(date));
			map.put("xueXiaoID", xueXiaoID);
			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
			if (xueQi==null) {
				mView.setViewName("stu/deyufengongshi");
				mView.addObject("intime", 0);
				return mView;
			}
			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(),user.getBanjiid()+",%","%,"+user.getBanjiid()+",%");
			if (xueQiDeYu==null) {
				mView.setViewName("stu/deyufengongshi");
				mView.addObject("intime", 0);
				return mView;
			}
			if (xueQiDeYu.getZhuangtai() != 3) {
				mView.setViewName("stu/deyufengongshi");
				mView.addObject("intime", 0);
				return mView;
			} else {
				List<Integer> xueShengIDs = new ArrayList<>();
				List<XueShengDeYu> xueShengDeYus = new ArrayList<>();
				List<DeYuPingFenFangAn> fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
				BanJi banJi = banJiService.selectByPrimaryKey(user.getBanjiid());
				xueShengIDs = xueShengService.getTongZhuanYeXueShengByBanJiID(user.getBanjiid(),banJi.getBanjimingcheng().substring(0, banJi.getBanjimingcheng().length()-2)+"%");
				Map<String, String> map2 = new HashMap<>();
				map2.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid().toString());
				for (int i = 0; i < xueShengIDs.size(); i++) {
					String xueShengID = xueShengIDs.get(i).toString();
					map2.put("xueShengID", xueShengID);
					XueSheng xueSheng = xueShengService.getUserById(Integer.parseInt(xueShengID));
					XueShengDeYu xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map2);
					if (xueShengDeYu != null) {
						xueShengDeYu.setXuehao(Integer.parseInt(xueSheng.getXuehao()));
						xueShengDeYu
								.setXingming(xueSheng.getXingming());
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
						xueShengDeYu.setXingming(xueSheng.getXingming());
						xueShengDeYu.setXuehao(Integer.parseInt(xueSheng.getXuehao()));
					}
					xueShengDeYus.add(xueShengDeYu);
				}
				logger.info(xueShengDeYus.size());
				logger.info(xueShengIDs.size());
				mView.setViewName("stu/deyufengongshi");
				mView.addObject("xueShengDeYus", xueShengDeYus);
				mView.addObject("fangAn", fangAns);
				mView.addObject("size", fangAns.size());
				mView.addObject("intime", 1);
				return mView;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;

	}

	@RequestMapping(value = "tianxiekaopingbiao")
	public ModelAndView tianXieKaoPing(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			ModelAndView mView = new ModelAndView();
			XueSheng user = null;
			try {
				user = (XueSheng) session.getAttribute("user");
			} catch (Exception e) {
				logger.error(e);
				response.sendRedirect("logout");
				return null;
			}
			List<DeYuPingFenFangAn> FangAnList = new ArrayList<>();
			Map<String, String> map = new HashMap<>();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
			logger.info(xueXiaoXueHao[0]);
			logger.info(xueXiaoXueHao[1]);
			String xueXiaoID = xueXiaoXueHao[0];
			map.put("riqi", format.format(date));
			map.put("xueXiaoID", xueXiaoID);
			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
			if (xueQi==null) {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('当前时间不在学期范围内！');</script>");
				out.print("<script>location='deyuchengji';</script>");
				return null;
			}
			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(), user.getBanjiid()+",%","%,"+user.getBanjiid()+",%");
			if (xueQiDeYu==null) {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('当前学期还未发布德育！');</script>");
				out.print("<script>location='deyuchengji';</script>");
				return null;
			}
			map.put("xueShengID", user.getXueshengid().toString());
			map.put("xueQiDeYuID", Integer.toString(xueQiDeYu.getXueqideyuid()));
			XueShengDeYu deYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map);
			if (deYu == null) {
				if (xueQiDeYu.getZhuangtai()!=1) {
					PrintWriter out = response.getWriter();
					response.setContentType("text/html; charset=utf-8");
					out.print("<script>alert('已过填写日期，请联系管理员！');</script>");
					out.print("<script>location='deyuchengji';</script>");
					return null;
				}
				FangAnList = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
				mView.setViewName("stu/tianxiekaopingbiao");
				logger.info(FangAnList.size());
				mView.addObject("tianxietiaomu", FangAnList);
				return mView;
			} else if(xueQiDeYu.getZhuangtai()==1){
				// 修改德育成绩 
				mView.addObject("id", deYu.getDeyufenid());
				mView.setViewName("redirect:xiuGaiDeYu");
				return mView;
				
				
				}else{
					PrintWriter out = response.getWriter();
					response.setContentType("text/html; charset=utf-8");
					out.print("<script>alert('当前已填写或没有需要填写的德育分表格！');</script>");
					out.print("<script>location='deyuchengji';</script>");
				}

		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "xiuGaiDeYu")
	public ModelAndView xiuGaiDeYu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mView = new ModelAndView();
		String deYuFenID = request.getParameter("id");
		if (!Util.isNumeric(deYuFenID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = null;
		try {
			user = (XueSheng) request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		Map<String, String> map = new HashMap<>();
		map.put("xueShengID", user.getXueshengid().toString());
		map.put("deYuFenID", deYuFenID);
		XueShengDeYu deYu = deYuService.selectByXueShengIDAndDeYuFenID(map);
		if (deYu == null) {
			response.sendRedirect("logout");
			return null;
		}
		XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiDeYuID(deYu.getXueqideyuid());
		if (xueQiDeYu.getZhuangtai() != 1) {
			response.sendRedirect("logout");
			return null;
		}
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
		mView.setViewName("stu/xiugaideyu");
		return mView;
	}

	@RequestMapping(value = "updatedeyu")
	public void updatedeyu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return;
		}
		XueSheng user = (XueSheng)request.getSession().getAttribute("user");
		String fanganid = request.getParameter("fanganid");
		String deYuFenID = request.getParameter("deyufenid");
		PingFenFangAn pingFenFangAn = pingFenFangAnService.selectByID(Integer.parseInt(fanganid));
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
		PrintWriter out = response.getWriter();
		if (i > 0) {
			
			//发送激光消息
			XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
			xiaoXiFaSong.setXiaoXiMingCheng("修改德育成绩");
			xiaoXiFaSong.setXiaoXiNeiRong(user.getXingming()+"修改德育成绩@"+pingFenFangAn.getFanganmingcheng()+"@");
			xiaoXiFaSong.setShuJuId(Integer.parseInt(deYuFenID));
			xiaoXiFaSong.setShuJuLeiXing(6);
			xiaoXiFaSong.setFaSongLeiXing(2);
			xiaoXiFaSong.setShiFouChengGong(0);
			xiaoXiFaSong.setXueXiaoId(user.getXuexiaoXuehao().split("_")[0]);
			//发送提醒消息
			TiXing tiXing = new TiXing();
			Date date = new Date();
			tiXing.setNeirong(user.getXingming()+"修改德育成绩@"+pingFenFangAn.getFanganmingcheng()+"$");
			tiXing.setShijian(date);
			tiXing.setZhuangtai(0);
			tiXing.setShujuid(Integer.parseInt(deYuFenID));
			tiXing.setType(5);
			tiXing.setJieshourenid(pingFenFangAn.getJiaoshiid());
			tixingService.insert(tiXing);
			xiaoXiFaSong.setFaSongMuBiao(pingFenFangAn.getJiaoshiid().toString());
			jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
			
			
			
			response.setContentType("text/html; charset=utf-8");
			out.print("<script>alert('提交成功');</script>");
			out.print("<script>location='deyuchengji';</script>");
		} else {
			response.setContentType("text/html; charset=utf-8");
			out.print("<script>alert('提交失败');</script>");
			out.print("<script>location='deyuchengji';</script>");
		}

	}
	
	@RequestMapping(value="deYuDetail")
	public ModelAndView deYuXiangQing(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if(!Util.isXueSheng(request)){
			response.sendRedirect("logout");
			return null;
		}
		String id = request.getParameter("id");
		if (!Util.isNumeric(id)) {
			response.sendRedirect("logout");
			return null;
		}
		
		ModelAndView mView = new ModelAndView();
		XueSheng user = null;
		
		try {
			user = (XueSheng) request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		if(id.equals("0") || id.equals("-1")){
			mView.addObject("xuehao",user.getXuehao());
			mView.addObject("xueshengxingming",user.getXingming());
			mView.setViewName("stu/deyuxiangqing");
			return mView;
		}
		Map<String, String> map =new HashMap<>();
		map.put("xueShengID", user.getXueshengid().toString());
		map.put("deYuFenID", id);
		XueShengDeYu xueShengDeYu = deYuService.selectByXueShengIDAndDeYuFenID(map);
		if (xueShengDeYu==null) {
			xueShengDeYu = deYuService.selectByDeYuFenID(Integer.parseInt(id));
			if (xueShengDeYu==null) {
				response.sendRedirect("logout");
				return null;
			}
			XueSheng xueSheng = xueShengService.getUserById(xueShengDeYu.getXueshengid());
			logger.info(xueSheng.getBanjiid());
			logger.info(user.getBanjiid());
			BanJi banJi1 = banJiService.selectByPrimaryKey(user.getBanjiid());
			BanJi banji2 = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
			if (banJi1.getYuanxiid().equals(banji2.getYuanxiid())) {
				response.sendRedirect("logout");
				return null;
			}
			if (banJi1.getBanjimingcheng().substring(0, banJi1.getBanjimingcheng().length()-2).equals(banji2.getBanjimingcheng().substring(0, banji2.getBanjimingcheng().length()-2))) {
				response.sendRedirect("logout");
				return null;
			}
			if (!xueSheng.getBanjiid().equals(user.getBanjiid())) {
				response.sendRedirect("logout");
				return null;
			}
			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiDeYuID(xueShengDeYu.getXueqideyuid());
			if (xueQiDeYu.getZhuangtai()!=2) {
				response.sendRedirect("logout");
				return null;
			}
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
			mView.addObject("xuehao",xueSheng.getXuehao());
			mView.addObject("xueshengxingming",xueSheng.getXingming());
			mView.addObject("tianxietiaomu",FangAnList);
			mView.addObject("fenshu",fenshu);
			mView.setViewName("stu/deyuxiangqing");
			return mView;
		}else {
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
			mView.addObject("xuehao",user.getXuehao());
			mView.addObject("xueshengxingming",user.getXingming());
			mView.addObject("tianxietiaomu",FangAnList);
			mView.addObject("fenshu",fenshu);
			mView.setViewName("stu/deyuxiangqing");
			return mView;
		}
	}
}
