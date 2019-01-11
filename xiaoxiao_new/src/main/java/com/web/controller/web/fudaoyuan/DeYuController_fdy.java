package com.web.controller.web.fudaoyuan;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.web.annotation.LoginStatusAnnotation;
import com.web.model.BanJi;
import com.web.model.DeYuPingFenFangAn;
import com.web.model.FuDaoYuan;
import com.web.model.NianFen;
import com.web.model.PingFenFangAn;
import com.web.model.XiaoXiFaSong;
import com.web.model.XueQi;
import com.web.model.XueQiDeYu;
import com.web.model.XueSheng;
import com.web.model.XueShengDeYu;
import com.web.model.YongHu;
import com.web.model.YuanXi;
import com.web.service.BanJiService;
import com.web.service.DeYuService;
import com.web.service.FuDaoYuanService;
import com.web.service.JiGuangService;
import com.web.service.NianFenService;
import com.web.service.PingFenFangAnService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
@LoginStatusAnnotation(status = "fudaoyuan")
public class DeYuController_fdy {
	static Logger logger = Logger.getLogger(DeYuController_fdy.class);

	@Autowired
	private FuDaoYuanService fuDaoYuanService;

	@Autowired
	private BanJiService banJiService;

	@Autowired
	private XueQiService xueQiService;

	@Autowired
	private YuanXiService yuanXiService;

	@Autowired
	private DeYuService deYuService;

	@Autowired
	private XueShengService xueShengService;

	@Autowired
	private PingFenFangAnService pingFenFangAnService;

	@Autowired
	private YongHuService yongHuService;

	@Autowired
	private JiGuangService jiGuangService;
	@Autowired
	private NianFenService nianfenService;

	@RequestMapping(value = "deyufanganlist")
	public ModelAndView deYuFangAnList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView();
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan == null) {
			response.sendRedirect("logout");
			return null;
		}
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(user.getYuanxiid());

		List<PingFenFangAn> pingFenFangAnList = pingFenFangAnService
				.selectPingFenFangAnSByXueXiaoID(yuanXi.getXuexiaoid());
		if (pingFenFangAnList == null || pingFenFangAnList.size() < 0) {
			model.addObject("list", null);
			model.setViewName("fudaoyuan/deyufanganlist");
		} else {
			for (PingFenFangAn pingFenFangAn : pingFenFangAnList) {
				pingFenFangAn.setJiaoshixingming(
						yongHuService.selectYongHuByID(pingFenFangAn.getJiaoshiid()).getYonghuxingming());

			}
			model.addObject("list", pingFenFangAnList);
			model.setViewName("fudaoyuan/deyufanganlist");
		}

		return model;
	}

	@RequestMapping(value = "deyuchengji_fdy")
	public ModelAndView deYuChengJi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan == null) {
			response.sendRedirect("logout");
			return null;
		}
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(user.getYuanxiid());
		if (fuDaoYuan.getBanjiid() == null || fuDaoYuan.getBanjiid().equals("")) {
			ModelAndView mView = new ModelAndView();
			mView.setViewName("fudaoyuan/deyuchengji");
			return mView;
		}
		String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
		List<BanJi> banJis = new ArrayList<>();
		List<String> xueNianList = new ArrayList<>();
		BanJi banJi = null;
		for (int i = 0; i < banJiIDs.length; i++) {
			banJi = new BanJi();
			banJi = banJiService.selectByPrimaryKey(Integer.parseInt(banJiIDs[i]));
			if (banJi != null) {
				banJis.add(banJi);
			}
		}
		xueNianList = xueQiService.getXuenianByXuexiaoID(yuanXi.getXuexiaoid());
		List<XueShengDeYu> xueShengDeYus = new ArrayList<>();
		List<DeYuPingFenFangAn> fangAns = new ArrayList<>();
		String xueNian = request.getParameter("xuenian");
		String xueQi = request.getParameter("xueqi");
		String banJiID = request.getParameter("banji");
		Map<String, String> map = new HashMap<>();
		XueQi xQi = new XueQi();
		Map<String, Object> mapP = new HashMap<>();

		if (xueNian == null && xueQi == null) {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			map.put("riqi", format.format(date));
			map.put("xueXiaoID", yuanXi.getXuexiaoid().toString());
			xQi = xueQiService.getByxueXiaoIDandriQi(map);
			mapP = xueQiService.getMapXueQiByxueXiaoIDandriQi(map);
		} else {
			map.put("xuenian", xueNian);
			map.put("xueqi", xueQi);
			map.put("xuexiaoid", yuanXi.getXuexiaoid().toString());
			map.put("nianfen", xueNian.split("~")[0]);
			xQi = xueQiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			mapP = xueQiService.getMapXueQiByXueXiaoIDAndXueNianAndXueQi(map);
		}
		if (banJiID == null) {
			banJiID = banJiIDs[0];
		}
		if (xQi == null) {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			map.put("riqi", format.format(date));
			map.put("xueXiaoID", yuanXi.getXuexiaoid().toString());
			List<Map<String, Object>> xueqimaps = xueQiService.getNewerXueQi(map);
			List<XueQi> xueqis = xueQiService.getNewerXueQiByXueQi(map);
			if (xueqis != null && xueqis.size() > 0) {
				xQi = xueqis.get(0);
			}
			if (xueqimaps != null && xueqimaps.size() > 0) {
				mapP = xueqimaps.get(0);
			}
		}

		PingFenFangAn pingFenFangAn = new PingFenFangAn();
		String fanganid = request.getParameter("fanganid");
		// if (xueNian!=null&&xueQi !=null&&banJiID!=null){

		if (xQi != null) {
			xueNian = mapP.get("ruXueNianFen").toString();
			xueQi = xQi.getXueqi().toString();
			pingFenFangAn = deYuService.getFangAnMingChengByFangAnID(Integer.parseInt(fanganid));

			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiIDAndFangAnID(xQi.getXueqiid(),
					banJiID + ",%", "%," + banJiID + ",%", fanganid);
			if (xueQiDeYu != null) {
				fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
				map.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid().toString());
				List<XueSheng> xueShengS = xueShengService.getAllByBanJiID(Integer.parseInt(banJiID));
				if (!xueShengS.isEmpty()) {
					XueShengDeYu xueShengDeYu = null;
					for (XueSheng xueSheng : xueShengS) {
						xueShengDeYu = new XueShengDeYu();
						map.put("xueShengID", xueSheng.getXueshengid().toString());
						xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map);
						List<String> yiJiZongFens = new ArrayList<>();
						if (xueShengDeYu != null) {
							xueShengDeYu.setXuehao(Integer.parseInt(xueSheng.getXuehao()));
							xueShengDeYu.setXingming(xueSheng.getXingming());
							String deFens[] = xueShengDeYu.getDefenxiangqing().split(",");
							Map<String, String> map3 = new HashMap<>();
							for (int j = 0; j < deFens.length; j++) {
								String defen[] = deFens[j].split(":");
								if (defen[1].endsWith(";")) {
									defen[1] = defen[1].substring(0, defen[1].length() - 1);
								}
								logger.info(defen[0] + ":" + defen[1]);
								map3.put(defen[0], defen[1]);
							}
							logger.info(map3);
							double manFen = 0;

							for (DeYuPingFenFangAn deYuPingFenFangAn : fangAns) {
								manFen = manFen + deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen();
								double yiJiZongFen = 0;
								for (DeYuPingFenFangAn fangAn : deYuPingFenFangAn.getChildList()) {
									List<Integer> fenShus = new ArrayList<>();
									double erJiZongFen = 0;
									if (fangAn.getXiangleixing() == 0) {
										if (fangAn.getXueshengtianxie() == 1) {
											logger.info(fangAn.getPingfenid() + ":"
													+ map3.get(fangAn.getPingfenid().toString()));
											erJiZongFen = erJiZongFen
													+ Integer.parseInt(map3.get(fangAn.getPingfenid().toString()));
										} else if (fangAn.getXueshengtianxie() == 2) {
											double danXiangFenHe = 0;
											String dString = map3.get(fangAn.getPingfenid().toString());
											String deFenXiang[] = dString.split(";");
											logger.info(fangAn.getMingcheng() + ":" + deFenXiang.length);
											if (!deFenXiang[0].equals("无")) {
												danXiangFenHe = deFenXiang.length * fangAn.getDanxiangfen();
												logger.info(fangAn.getMingcheng() + ":" + danXiangFenHe);
												if (danXiangFenHe > fangAn.getManfen()) {
													erJiZongFen = erJiZongFen + fangAn.getManfen();
												} else {
													erJiZongFen = erJiZongFen + danXiangFenHe;
												}
											}
										} else {
											String dString = map3.get(fangAn.getPingfenid().toString());
											if (null == dString) {

											} else {
												erJiZongFen = erJiZongFen + Integer.parseInt(dString);
											}
										}
									} else {
										if (null == fangAn.getChildList()) {

										} else {
											for (DeYuPingFenFangAn an : fangAn.getChildList()) {
												if (an.getXueshengtianxie() == 1) {
													if (an.getManfen() <= fangAn.getManfen()) {
														erJiZongFen = erJiZongFen + Integer
																.parseInt(map3.get(an.getPingfenid().toString()));
													}
												} else if (an.getXueshengtianxie() == 2) {
													double danXiangFenHe = 0;
													String dString = map3.get(an.getPingfenid().toString());
													String deFenXiang[] = dString.split(";");
													logger.info(an.getMingcheng() + ":" + deFenXiang.length);
													if (!deFenXiang[0].equals("无")) {
														danXiangFenHe = deFenXiang.length * an.getDanxiangfen();
														logger.info(an.getMingcheng() + ":" + danXiangFenHe);
														if (danXiangFenHe > an.getManfen()) {
															erJiZongFen = erJiZongFen + an.getManfen();
														} else {
															erJiZongFen = erJiZongFen + danXiangFenHe;
														}
													}
												} else {
													String dString = map3.get(an.getPingfenid().toString());
													// fenShus.add(Integer.parseInt(dString));
													if (null == dString) {

													} else {
														erJiZongFen = erJiZongFen + Integer.parseInt(dString);
													}
												}
											}
										}
										// if (!fenShus.isEmpty()) {
										// erJiZongFen = erJiZongFen +
										// Collections.max(fenShus);
										// }
									}
									if (erJiZongFen > fangAn.getManfen()) {
										yiJiZongFen = yiJiZongFen + fangAn.getManfen();
									} else {
										yiJiZongFen = yiJiZongFen + erJiZongFen;
									}
								}
								logger.info("一级总分" + yiJiZongFen);
								if (yiJiZongFen > deYuPingFenFangAn.getManfen()) {
									yiJiZongFens.add(deYuPingFenFangAn.getManfen().toString());
								} else {
									yiJiZongFens.add(Double.toString(yiJiZongFen));
								}
								logger.info(manFen);
							}
							xueShengDeYu.setFenshu(yiJiZongFens);
						} else {
							xueShengDeYu = new XueShengDeYu();
							xueShengDeYu.setXuehao(Integer.parseInt(xueSheng.getXuehao()));
							xueShengDeYu.setXingming(xueSheng.getXingming());
							// xueShengDeYu.setFenshu(yiJiZongFens);
						}
						xueShengDeYus.add(xueShengDeYu);
					}
				}
			}
		}
		logger.info(xueNian + "   " + xueQi + "   " + banJiID);
		logger.info(banJis.size());
		// }
		ModelAndView mView = new ModelAndView();
		mView.setViewName("fudaoyuan/deyuchengji");
		mView.addObject("banjis", banJis);
		mView.addObject("xuenian", xueNian);
		mView.addObject("xueqi", xueQi);
		mView.addObject("xueShengDeYus", xueShengDeYus);
		mView.addObject("fangAn", fangAns);
		mView.addObject("pingFenFangAn", pingFenFangAn);
		mView.addObject("xueNianList", xueNianList);
		mView.addObject("banjiid", banJiID);
		return mView;
	}

	@RequestMapping(value = "deYuDetail_fdy")
	public ModelAndView deYuXiangQing(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("login");
			return null;
		}
		String id = request.getParameter("id");
		if (!Util.isNumeric(id)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu yongHu = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(yongHu.getYonghuid());
		XueShengDeYu xueShengDeYu = deYuService.selectByDeYuFenID(Integer.parseInt(id));
		if (xueShengDeYu == null) {
			response.sendRedirect("logout");
			return null;
		}
		String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
		XueSheng xueSheng = xueShengService.getUserById(xueShengDeYu.getXueshengid());
		String banJiID = xueSheng.getBanjiid().toString();
		boolean isIn = false;
		for (int i = 0; i < banJiIDs.length; i++) {
			if (banJiID.equals(banJiIDs[i])) {
				isIn = true;
			}
		}
		if (isIn == false) {
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
		mView.addObject("xuehao", xueSheng.getXuehao());
		mView.addObject("xueshengxingming", xueSheng.getXingming());
		mView.addObject("tianxietiaomu", FangAnList);
		mView.addObject("fenshu", fenshu);
		mView.setViewName("fudaoyuan/deyuxiangqing");
		return mView;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "deyugongshi_fdy")
	public ModelAndView deYuGongShi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan == null) {
			response.sendRedirect("logout");
			return null;
		}
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(user.getYuanxiid());
		Map<String, String> map = new HashMap<>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String xueXiaoID = yuanXi.getXuexiaoid().toString();
		List<XueQi> xueQis = xueQiService.findByXueXiaoID(xueXiaoID);
		map.put("riqi", format.format(date));
		map.put("xueXiaoID", xueXiaoID);
		XueQi xueQi = xueQiService.getByxueXiaoIDandriQi(map);
		if (xueQi == null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.print("<script>alert('无当前学期信息，请联系管理员！');</script>");
			out.print("<script>location='index';</script>");
			return null;
		}
		String banjis[] = fuDaoYuan.getBanjiid().split(",");
		String banJis = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = sdf.format(new Date());
		for (String string2 : banjis) {
			BanJi bj = banJiService.selectByPrimaryKey(Integer.parseInt(string2));
			Integer ruXueNianFen = nianfenService.selectByPrimaryKey(bj.getRuxuenianfenid()).getRuxuenianfen();
			String biYeNianFen = (ruXueNianFen + bj.getLeixing()) + "-07-30";
			if (sdf.parse(biYeNianFen).getTime() > sdf.parse(nowTime).getTime()) {
				banJis += string2 + ",";
			}
		}
		// XueQiDeYu xueQiDeYu =
		// deYuService.selectXueQiDeYuByXueQiIDAndShiYongBanJiIDs(xueQi.getXueqiid(),
		// banJis);
		XueQiDeYu xueQiDeYu = null;
		for (XueQi xq : xueQis) {
			XueQiDeYu deyu = deYuService.selectXueQiDeYuByXueQiIDAndShiYongBanJiIDs(xq.getXueqiid(), banJis);
			if (null != deyu) {
				if (!deyu.getZhuangtai().toString().equals("2")) {
					xueQiDeYu = deyu;
				}
			}
		}
		if (xueQiDeYu == null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.print("<script>alert('当前学期暂未发布德育考评！');</script>");
			out.print("<script>location='index';</script>");
			return null;
		}
		if (xueQiDeYu.getZhuangtai().toString().equals("1") || xueQiDeYu.getZhuangtai().toString().equals("2")) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.print("<script>alert('当前学期德育考评状态未处于发布考核期！');</script>");
			out.print("<script>location='index';</script>");
			return null;
		}
		List<XueShengDeYu> xueShengDeYus = new ArrayList<>();
		Map<String, String> map2 = new HashMap<>();
		map2.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid().toString());
		String banJiIDs[] = xueQiDeYu.getShiyongbanjiids().split(",");
		List<XueSheng> xueShengs = new ArrayList<>();
		List<PingFenFangAn> pingFenFangAns = new ArrayList<>();
		PingFenFangAn pingFenFangAn = deYuService.getFangAnMingChengByFangAnID(xueQiDeYu.getFanganid());
		List<DeYuPingFenFangAn> fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
		for (int i = 0; i < banJiIDs.length; i++) {
			BanJi banJi = new BanJi();
			banJi = banJiService.selectByPrimaryKey(Integer.parseInt(banJiIDs[i]));
			xueShengs = xueShengService.getAllByBanJiID(Integer.parseInt(banJiIDs[i]));
			for (XueSheng xueSheng : xueShengs) {
				map2.put("xueShengID", xueSheng.getXueshengid().toString());
				XueShengDeYu xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map2);
				if (xueShengDeYu != null) {
					xueShengDeYu.setXuehao(Integer.parseInt(xueSheng.getXuehao()));
					xueShengDeYu.setXingming(xueSheng.getXingming());
					String deFens[] = xueShengDeYu.getDefenxiangqing().split(",");
					Map<String, String> map3 = new HashMap<>();
					for (int j = 0; j < deFens.length; j++) {
						String[] defen = deFens[j].split(":");
						if (defen[1].endsWith(";")) {
							defen[1] = defen[1].substring(0, defen[1].length() - 1);
						}
						logger.info(defen[0] + ":" + defen[1]);
						map3.put(defen[0], defen[1]);
					}
					logger.info(map3);
					double manFen = 0;
					List<String> yiJiZongFens = new ArrayList<>();
					for (DeYuPingFenFangAn deYuPingFenFangAn : fangAns) {
						manFen = manFen + deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen();
						double yiJiZongFen = 0;
						for (DeYuPingFenFangAn fangAn : deYuPingFenFangAn.getChildList()) {
							List<Integer> fenShus = new ArrayList<>();
							double erJiZongFen = 0;
							if (fangAn.getXiangleixing() == 0) {
								if (fangAn.getXueshengtianxie() == 1) {
									logger.info(
											fangAn.getPingfenid() + ":" + map3.get(fangAn.getPingfenid().toString()));
									erJiZongFen = erJiZongFen
											+ Integer.parseInt(map3.get(fangAn.getPingfenid().toString()));
								} else if (fangAn.getXueshengtianxie() == 2) {
									double danXiangFenHe = 0;
									String dString = map3.get(fangAn.getPingfenid().toString());
									String deFenXiang[] = dString.split(";");
									logger.info(fangAn.getMingcheng() + ":" + deFenXiang.length);
									if (!deFenXiang[0].equals("无")) {
										danXiangFenHe = deFenXiang.length * fangAn.getDanxiangfen();
										logger.info(fangAn.getMingcheng() + ":" + danXiangFenHe);
										if (danXiangFenHe > fangAn.getManfen()) {
											erJiZongFen = erJiZongFen + fangAn.getManfen();
										} else {
											erJiZongFen = erJiZongFen + danXiangFenHe;
										}
									}
								} else {
									String dString = map3.get(fangAn.getPingfenid().toString());
									logger.info(fangAn.getPingfenid() + ":" + dString);
									erJiZongFen = erJiZongFen + Integer.parseInt(dString);
								}
							} else {
								if (null == fangAn.getChildList()) {

								} else {
									for (DeYuPingFenFangAn an : fangAn.getChildList()) {
										if (an.getXueshengtianxie() == 1) {
											if (an.getManfen() <= fangAn.getManfen()) {
												erJiZongFen = erJiZongFen
														+ Integer.parseInt(map3.get(an.getPingfenid().toString()));
											}
										} else if (an.getXueshengtianxie() == 2) {
											double danXiangFenHe = 0;
											String dString = map3.get(an.getPingfenid().toString());
											String deFenXiang[] = dString.split(";");
											logger.info(an.getMingcheng() + ":" + deFenXiang.length);
											if (!deFenXiang[0].equals("无")) {
												danXiangFenHe = deFenXiang.length * an.getDanxiangfen();
												logger.info(an.getMingcheng() + ":" + danXiangFenHe);
												if (danXiangFenHe > an.getManfen()) {
													erJiZongFen = erJiZongFen + an.getManfen();
												} else {
													erJiZongFen = erJiZongFen + danXiangFenHe;
												}
											}
										} else {
											String dString = map3.get(an.getPingfenid().toString());
											// fenShus.add(Integer.parseInt(dString));
											erJiZongFen = erJiZongFen + Integer.parseInt(dString);
										}
									}
								}
								// if (!fenShus.isEmpty()) {
								// erJiZongFen = erJiZongFen +
								// Collections.max(fenShus);
								// }
							}
							if (erJiZongFen > fangAn.getManfen()) {
								yiJiZongFen = yiJiZongFen + fangAn.getManfen();
							} else {
								yiJiZongFen = yiJiZongFen + erJiZongFen;
							}
						}
						logger.info("一级总分" + yiJiZongFen);
						if (yiJiZongFen > deYuPingFenFangAn.getManfen()) {
							yiJiZongFens.add(deYuPingFenFangAn.getManfen().toString());
						} else {
							yiJiZongFens.add(Double.toString(yiJiZongFen));
						}
						logger.info(manFen);
					}
					xueShengDeYu.setFenshu(yiJiZongFens);
					xueShengDeYu.setDefenxiangqing(banJi.getBanjimingcheng());
				} else {
					xueShengDeYu = new XueShengDeYu();
					xueShengDeYu.setXuehao(Integer.parseInt(xueSheng.getXuehao()));
					xueShengDeYu.setXingming(xueSheng.getXingming());
					xueShengDeYu.setDefenxiangqing(banJi.getBanjimingcheng());
				}
				if (null != xueQiDeYu.getFankui()) {
					String fankui[] = xueQiDeYu.getFankui().split(";");
					for (String string : fankui) {
						String s[] = string.split(":");
						if (s[0].equals(xueSheng.getXueshengid().toString())) {
							xueShengDeYu.setFankui(s[1]);
							break;
						}
					}
				}
				xueShengDeYus.add(xueShengDeYu);
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("fudaoyuan/deyufengongshi");
		mView.addObject("xueqideyuid", xueQiDeYu.getXueqideyuid());
		mView.addObject("zhuangtai", xueQiDeYu.getZhuangtai());
		mView.addObject("xueShengDeYus", xueShengDeYus);
		mView.addObject("fangAn", fangAns);
		mView.addObject("pingFenFangAn", pingFenFangAn);
		return mView;
	}

	/*
	 * @RequestMapping(value="kaiqigongshi_fdy")
	 * 
	 * @ResponseBody public String kaiQiGongShi(HttpServletRequest
	 * request,HttpServletResponse response) throws IOException{ if
	 * (!Util.checkSession(request)) { response.sendRedirect("login"); return
	 * null; } if (!Util.isFuDaoYuan(request)) {
	 * response.sendRedirect("logout"); return null; } String xueQiDeYuID =
	 * request.getParameter("CODE"); logger.info(xueQiDeYuID); YongHu user =
	 * (YongHu)request.getSession().getAttribute("user"); FuDaoYuan fuDaoYuan =
	 * fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid()); YuanXi yuanXi =
	 * yuanXiService.selectByPrimaryKey(user.getYuanxiid()); XueQiDeYu xueQiDeYu
	 * =
	 * deYuService.selectXueQiDeYuByXueQiDeYuID(Integer.parseInt(xueQiDeYuID));
	 * if (xueQiDeYu==null) { response.sendRedirect("logout"); return null; } if
	 * (xueQiDeYu.getZhuangtai()!=1) { response.sendRedirect("logout"); return
	 * null; } String ret =""; xueQiDeYu.setZhuangtai(2); int i =
	 * deYuService.updateXueQiDeYuByXueQiDeYu(xueQiDeYu); if (i>0) {
	 * ret="success"; }else { ret = "fail"; } return ret; }
	 */
	@RequestMapping(value = "xiugaideyu_fdy")
	public ModelAndView xiuGaiDeYu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String deYuFenID = request.getParameter("id");
		if (!Util.isNumeric(deYuFenID)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		XueShengDeYu deYu = deYuService.selectByDeYuFenID(Integer.parseInt(deYuFenID));
		if (deYu == null) {
			response.sendRedirect("logout");
			return null;
		}
		String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
		XueSheng xueSheng = xueShengService.getUserById(deYu.getXueshengid());
		String banJiID = xueSheng.getBanjiid().toString();
		boolean isIn = false;
		for (int i = 0; i < banJiIDs.length; i++) {
			if (banJiID.equals(banJiIDs[i])) {
				isIn = true;
			}
		}
		if (isIn == false) {
			response.sendRedirect("logout");
			return null;
		}
		XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiDeYuID(deYu.getXueqideyuid());
		if (xueQiDeYu.getZhuangtai() == 2) {
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
		mView.addObject("fenshu", fenshu);
		mView.addObject("id", deYuFenID);
		mView.setViewName("fudaoyuan/xiugaideyu");
		return mView;
	}

	@RequestMapping(value = "updatedeyu_fdy")
	@ResponseBody
	public String updatedeyu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String fanganid = request.getParameter("fanganid");
		String deYuFenID = request.getParameter("deyufenid");
		if (!Util.isNumeric(deYuFenID)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		XueShengDeYu deYu = deYuService.selectByDeYuFenID(Integer.parseInt(deYuFenID));
		if (deYu == null) {
			response.sendRedirect("logout");
			return null;
		}
		String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
		XueSheng xueSheng = xueShengService.getUserById(deYu.getXueshengid());
		String banJiID = xueSheng.getBanjiid().toString();
		boolean isIn = false;
		for (int i = 0; i < banJiIDs.length; i++) {
			if (banJiID.equals(banJiIDs[i])) {
				isIn = true;
			}
		}
		if (isIn == false) {
			response.sendRedirect("logout");
			return null;
		}
		XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiDeYuID(deYu.getXueqideyuid());
		if (xueQiDeYu.getZhuangtai() == 2) {
			response.sendRedirect("logout");
			return null;
		}
		List<Integer> pingFenIdList = deYuService.getPingfenIDByFangAnID(Integer.parseInt(fanganid));
		List<DeYuPingFenFangAn> list = deYuService.getAllByFangAnID(Integer.parseInt(fanganid));
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < pingFenIdList.size(); i++) {
			for (DeYuPingFenFangAn fangAn : list) {
				if (fangAn.getPingfenid().toString().equals(pingFenIdList.get(i).toString())) {
					if (fangAn.getXueshengtianxie().intValue() == 1 || fangAn.getXueshengtianxie().intValue() == 3) {
						String value = request.getParameter(pingFenIdList.get(i).toString());
						if ("".equals(value) || value == null || "undefined".equalsIgnoreCase(value)) {
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
						if ("".equals(xString) || xString == null || "undefined".equalsIgnoreCase(xString)) {
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
			logger.info(defen[0] + ":" + defen[1]);
			map2.put(defen[0], defen[1]);
		}
		logger.info(map2);
		double manFen = 0;
		List<Double> yiJiZongFens = new ArrayList<>();
		List<DeYuPingFenFangAn> fangAns = deYuService.getListByFangAnID(Integer.parseInt(fanganid));
		for (DeYuPingFenFangAn deYuPingFenFangAn : fangAns) {
			manFen = manFen + deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen();
			double yiJiZongFen = 0;
			for (DeYuPingFenFangAn fangAn : deYuPingFenFangAn.getChildList()) {
				List<Integer> fenShus = new ArrayList<>();
				double erJiZongFen = 0;
				if (fangAn.getXiangleixing() == 0) {
					if (fangAn.getXueshengtianxie() == 1) {
						logger.info(fangAn.getPingfenid() + ":" + map2.get(fangAn.getPingfenid().toString()));
						erJiZongFen = erJiZongFen + Integer.parseInt(map2.get(fangAn.getPingfenid().toString()));
					} else if (fangAn.getXueshengtianxie() == 2) {
						double danXiangFenHe = 0;
						String dString = map2.get(fangAn.getPingfenid().toString());
						String deFenXiang[] = dString.split(";");
						logger.info(fangAn.getMingcheng() + ":" + deFenXiang.length);
						if (!deFenXiang[0].equals("无")) {
							danXiangFenHe = deFenXiang.length * fangAn.getDanxiangfen();
							logger.info(fangAn.getMingcheng() + ":" + danXiangFenHe);
							if (danXiangFenHe > fangAn.getManfen()) {
								erJiZongFen = erJiZongFen + fangAn.getManfen();
							} else {
								erJiZongFen = erJiZongFen + danXiangFenHe;
							}
						}
					} else {
						String dString = map2.get(fangAn.getPingfenid().toString());
						erJiZongFen = erJiZongFen + Integer.parseInt(dString);
					}
				} else {
					if (null == fangAn.getChildList()) {

					} else {
						for (DeYuPingFenFangAn an : fangAn.getChildList()) {
							if (an.getXueshengtianxie() == 1) {
								if (an.getManfen() <= fangAn.getManfen()) {
									erJiZongFen = erJiZongFen
											+ Integer.parseInt(map2.get(an.getPingfenid().toString()));
								}
							} else if (an.getXueshengtianxie() == 2) {
								double danXiangFenHe = 0;
								String dString = map2.get(an.getPingfenid().toString());
								String deFenXiang[] = dString.split(";");
								logger.info(an.getMingcheng() + ":" + deFenXiang.length);
								if (!deFenXiang[0].equals("无")) {
									danXiangFenHe = deFenXiang.length * an.getDanxiangfen();
									logger.info(an.getMingcheng() + ":" + danXiangFenHe);
									if (danXiangFenHe > an.getManfen()) {
										erJiZongFen = erJiZongFen + an.getManfen();
									} else {
										erJiZongFen = erJiZongFen + danXiangFenHe;
									}
								}
							} else {
								String dString = map2.get(an.getPingfenid().toString());
								// fenShus.add(Integer.parseInt(dString));
								erJiZongFen = erJiZongFen + Integer.parseInt(dString);
							}
						}
					}
					// if (!fenShus.isEmpty()) {
					// erJiZongFen = erJiZongFen + Collections.max(fenShus);
					// }
				}
				if (erJiZongFen > fangAn.getManfen()) {
					yiJiZongFen = yiJiZongFen + fangAn.getManfen();
				} else {
					yiJiZongFen = yiJiZongFen + erJiZongFen;
				}
			}
			logger.info("一级总分" + yiJiZongFen);
			if (yiJiZongFen > deYuPingFenFangAn.getManfen()) {
				yiJiZongFens.add(deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen());
			} else {
				yiJiZongFens.add(yiJiZongFen * deYuPingFenFangAn.getXuefen());
			}

			logger.info(manFen);
		}
		double deFen = 0;
		for (int i = 0; i < yiJiZongFens.size(); i++) {
			deFen = deFen + yiJiZongFens.get(i);
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
		// PrintWriter out = response.getWriter();
		if (i > 0) {
			// response.setContentType("text/html; charset=utf-8");
			// out.print("<script>alert('提交成功');</script>");
			// out.print("<script>location='deyugongshi_fdy';</script>");
			return "success";
		} else {
			// response.setContentType("text/html; charset=utf-8");
			// out.print("<script>alert('提交失败');</script>");
			// out.print("<script>location='deyugongshi_fdy';</script>");
		}
		return null;
	}

	@RequestMapping(value = "fabukaoping")
	public ModelAndView faBuKaoPing(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan == null) {
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
		mView.setViewName("fudaoyuan/fabukaoping");
		mView.addObject("fangAns", pingFenFangAns);
		return mView;
	}

	@RequestMapping(value = "fangAnDetail_fdy")
	public ModelAndView fangAnDeTail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan == null) {
			logger.error("无辅导员信息" + user.getYonghuid());
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
		mView.setViewName("fudaoyuan/deyudetail");
		mView.addObject("tianxietiaomu", fangAns);
		return mView;
	}

	@RequestMapping(value = "xiuGai_fdy")
	public ModelAndView XiuGai(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan == null) {
			logger.error("无辅导员信息" + user.getYonghuid());
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
		mView.setViewName("fudaoyuan/deyuxiugai");
		mView.addObject("tianxietiaomu", fangAns);
		return mView;
	}

	@RequestMapping(value = "fabu_fdy")
	public ModelAndView faBu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan == null) {
			logger.error("无辅导员信息" + user.getYonghuid());
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
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(user.getYuanxiid());
		if (!yuanXi.getXuexiaoid().equals(yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid())) {
			if (!fangAnID.equals("1")) {
				response.sendRedirect("logout");
				return null;
			}
		}
		fangAn.setJiaoshixingming(yongHuService.selectYongHuByID(fangAn.getJiaoshiid()).getYonghuxingming());
		Map<String, String> m = new HashMap<>();
		m.put("xueXiaoID", yuanXi.getXuexiaoid().toString());
		List<Map<String, Object>> riqiMap = xueQiService.getMapXueQiByXueXiaoID(m);
		// List<XueQi> xueQis =
		// xueQiService.getXueQiByXueXiaoID(yuanXi.getXuexiaoid());
		List<XueQi> xueQis = new ArrayList<>();
		for (Map<String, Object> xueqi : riqiMap) {
			if (xueqi.containsKey("ID") && xueqi.get("ID").toString() != null
					&& !"".equals(xueqi.get("ID").toString())) {
				XueQi xq = new XueQi();
				xq.setXueqiid(Integer.parseInt(xueqi.get("ID").toString()));
				xq.setXuenian(xueqi.get("ruXueNianFen").toString());
				xq.setXueqi(Integer.parseInt(xueqi.get("xueQi").toString()));
				xueQis.add(xq);
			}
		}
		/*
		 * List<YuanXi> yuanXis =
		 * yuanXiService.getAllByxueXiaoID(guanLiYuan.getXuexiaoid());
		 */
		ModelAndView mView = new ModelAndView();
		mView.setViewName("fudaoyuan/fabu");
		mView.addObject("xueqis", xueQis);
		/* mView.addObject("yuanxis", yuanXis); */
		mView.addObject("fangan", fangAn);
		return mView;

	}

	@RequestMapping(value = "subfabu")
	@ResponseBody
	public String subFaBu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan == null) {
			logger.error("无辅导员信息" + user.getYonghuid());
			response.sendRedirect("logout");
			return null;
		}
		String code = request.getParameter("CODE");
		String ret = "";
		String string[] = code.split(";");
		if (string.length != 2) {
			ret = "fail";
			return ret;
		}
		if (string[0].equals("") || string[1].equals("")) {
			ret = "fail";
			return ret;
		}
		PingFenFangAn pingFenFangAn = pingFenFangAnService.selectByID(Integer.parseInt(string[0]));
		if (pingFenFangAn == null) {
			response.sendRedirect("logout");
			return null;
		}

		YongHu yongHu = yongHuService.selectYongHuByID(pingFenFangAn.getJiaoshiid());
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(yongHu.getYuanxiid());
		if (!yuanXi.getXuexiaoid().equals(yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid())) {
			if (!string[0].equals("1")) {
				response.sendRedirect("logout");
				return null;
			}
		}
		XueQiDeYu xueQiDeYu = new XueQiDeYu();
		String banjis[] = fuDaoYuan.getBanjiid().split(",");
		String banJis = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = sdf.format(new Date());
		for (String string2 : banjis) {
			BanJi bj = banJiService.selectByPrimaryKey(Integer.parseInt(string2));
			Integer ruXueNianFen = nianfenService.selectByPrimaryKey(bj.getRuxuenianfenid()).getRuxuenianfen();
			String biYeNianFen = (ruXueNianFen + bj.getLeixing()) + "-07-30";
			if (sdf.parse(biYeNianFen).getTime() > sdf.parse(nowTime).getTime()) {
				banJis += string2 + ",";
			}
		}
		xueQiDeYu.setFanganid(Integer.parseInt(string[0]));
		xueQiDeYu.setXueqiid(Integer.parseInt(string[1]));
		xueQiDeYu.setZhuangtai(1);
		xueQiDeYu.setShiyongbanjiids(banJis);
		XueQiDeYu deYu = deYuService.selectXueQiDeYuByXueQiDeYu(xueQiDeYu);
		if (deYu != null) {
			ret = "same";
			return ret;
		}
		int i = deYuService.insertXueQiDeYu(xueQiDeYu);
		if (i > 0) {
			String[] banJiIds = banJis.split(",");
			for (int j = 0; j < banJiIds.length; j++) {
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("辅导员发布了新的德育分统计！");
				xiaoXiFaSong.setXiaoXiNeiRong("辅导员发布了新的德育分统计！请按时完成填写！");
				xiaoXiFaSong.setShuJuId(xueQiDeYu.getXueqideyuid());
				xiaoXiFaSong.setShuJuLeiXing(6);
				xiaoXiFaSong.setFaSongMuBiao(banJiIds[j]);
				xiaoXiFaSong.setFaSongLeiXing(2);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(yuanXi.getXuexiaoid().toString());
				jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
			}
			ret = "success";
		} else {
			ret = "fail";
		}
		return ret;
	}

	@RequestMapping(value = "deyushezhi")
	public ModelAndView deYuSheZhi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan == null) {
			logger.error("无辅导员信息" + user.getYonghuid());
			response.sendRedirect("logout");
			return null;
		}
		String xueXiaoID = yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString();
		// Map<String, String> map = new HashMap<>();
		// Date date = new Date();
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// map.put("riqi", format.format(date));
		// map.put("xueXiaoID", xueXiaoID);
		List<XueQi> xueQis = xueQiService.findByXueXiaoID(xueXiaoID);
		XueQi xueQi = new XueQi();
		// Map<String, Object> riqiMap = new HashMap<>();
		// riqiMap = xueQiService.getMapXueQiByxueXiaoIDandriQi(map);
		// if (riqiMap == null) {
		// List<Map<String, Object>> xueqis = xueQiService.getNewerXueQi(map);
		// if (xueqis != null && xueqis.size() > 0) {
		// riqiMap = xueqis.get(0);
		// }
		// }
		// xueQi.setXuenian(riqiMap.get("ruXueNianFen").toString());
		// xueQi.setXueqi(Integer.parseInt(riqiMap.get("xueQi").toString()));
		// xueQi.setXueqiid(Integer.parseInt(riqiMap.get("ID").toString()));
		XueQiDeYu xueQiDeYu = null;
		// if (xueQi != null) {
		String banjis[] = fuDaoYuan.getBanjiid().split(",");
		String banJis = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = sdf.format(new Date());
		for (String string2 : banjis) {
			BanJi bj = banJiService.selectByPrimaryKey(Integer.parseInt(string2));
			Integer ruXueNianFen = nianfenService.selectByPrimaryKey(bj.getRuxuenianfenid()).getRuxuenianfen();
			String biYeNianFen = (ruXueNianFen + bj.getLeixing()) + "-07-30";
			if (sdf.parse(biYeNianFen).getTime() > sdf.parse(nowTime).getTime()) {
				banJis += string2 + ",";
			}
		}
		// xueQiDeYu =
		// deYuService.selectXueQiDeYuByXueQiIDAndShiYongBanJiIDs(xueQi.getXueqiid(),
		// banJis);
		for (XueQi xq : xueQis) {
			XueQiDeYu deyu = deYuService.selectXueQiDeYuByXueQiIDAndShiYongBanJiIDs(xq.getXueqiid(), banJis);
			if (null != deyu) {
				if (!deyu.getZhuangtai().toString().equals("2")) {
					xueQiDeYu = deyu;
					NianFen nianFen = nianfenService.selectByPrimaryKey(xq.getNianfenid());
					xueQi.setXuenian(nianFen.getRuxuenianfen() + "~" + (nianFen.getRuxuenianfen() + 1));
					xueQi.setXueqi(xq.getXueqi());
					xueQi.setXueqiid(xq.getXueqiid());
				}
			}
		}
		// }
		ModelAndView mView = new ModelAndView();
		mView.addObject("xueqi", xueQi);
		mView.addObject("xueqideyu", xueQiDeYu);
		mView.setViewName("guanliyuan/deyushezhi");
		return mView;
	}

	@RequestMapping(value = "xgdyzt")
	@ResponseBody
	public String xiuGaiDeYuZhuangTai(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan == null) {
			logger.error("无辅导员信息" + user.getYonghuid());
			response.sendRedirect("logout");
			return null;
		}
		String ret = "";
		String code[] = request.getParameter("CODE").split(";");
		if (code.length != 2) {
			ret = "fail";
			return ret;
		}
		XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiDeYuID(Integer.parseInt(code[0]));
		if (xueQiDeYu == null) {
			ret = "fail";
			return ret;
		}
		XueQi xueQi = xueQiService.selectByID(xueQiDeYu.getXueqiid());
		if (!xueQi.getXuexiaoid().equals(yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid())) {
			ret = "fail";
			return ret;
		}
		int zhuangTai = Integer.parseInt(code[1]);
		if (zhuangTai > 4) {
			ret = "fail";
			return ret;
		}
		xueQiDeYu.setZhuangtai(Integer.parseInt(code[1]));
		int i = deYuService.updateXueQiDeYuByXueQiDeYu(xueQiDeYu);
		if (i > 0) {
			int j = 0;
			if (zhuangTai == 3) {
				List<XueShengDeYu> xueShengDeYus = new ArrayList<>();
				String banJis[] = xueQiDeYu.getShiyongbanjiids().split(",");
				for (String string : banJis) {
					List<XueSheng> xueShengs = xueShengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs) {
						Map<String, String> map = new HashMap<>();
						map.put("xueShengID", xueSheng.getXueshengid().toString());
						map.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid().toString());
						XueShengDeYu xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map);
						if (null != xueShengDeYu) {
							if ("1".equals(xueShengDeYu.getLeixing().toString())) {
								xueShengDeYu.setLeixing(2);
								xueShengDeYus.add(xueShengDeYu);
							}
						}
					}
				}
				j = deYuService.updateXueShengDeYuZhuangTaiByList(xueShengDeYus);
			} else {
				ret = "success";
			}
			if (j > 0) {
				ret = "success";
			}
		} else {
			ret = "fail";
		}
		return ret;
	}

	@RequestMapping(value = "sub-new-deyu")
	@ResponseBody
	public String SubNewDeYu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
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

	@RequestMapping(value = "exportdeyuchengji_fdy")
	public ModelAndView exportdeyuchengji_fdy(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		Map<String, String> paramMap = new HashMap<>();
		String xuenian = request.getParameter("xuenian");
		String xueqi = request.getParameter("xueqi");
		String banjiid = request.getParameter("banji");
		BanJi bj = banJiService.selectByPrimaryKey(Integer.parseInt(banjiid));
		String xueXiaoID = yuanXiService.selectByPrimaryKey(bj.getYuanxiid()).getXuexiaoid().toString();
		paramMap.put("xueqi", xueqi);
		paramMap.put("nianfen", xuenian.split("~")[0]);
		paramMap.put("xuexiaoid", xueXiaoID);
		XueQi xueQi = xueQiService.getByXueXiaoIDAndXueNianAndXueQi(paramMap);
		XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(), bj.getBanjiid() + ",%",
				"%," + bj.getBanjiid() + ",%");
		List<DeYuPingFenFangAn> fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
		// List<DeYuPingFenFangAn> xiangQings =
		// deYuService.getAllByFangAnID(xueQiDeYu.getFanganid());
		List<XueShengDeYu> xueShengDeYus = new ArrayList<>();
		int num = 0;
		if (null != xueQiDeYu) {
			List<XueSheng> xueShengs = xueShengService.getAllByBanJiID(Integer.parseInt(banjiid));
			for (XueSheng xueSheng : xueShengs) {
				paramMap.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid().toString());
				paramMap.put("xueShengID", xueSheng.getXueshengid().toString());
				XueShengDeYu xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(paramMap);
				if (null == xueShengDeYu) {
					xueShengDeYu = new XueShengDeYu();
					xueShengDeYu.setXuehao(Integer.parseInt(xueSheng.getXuehao()));
					xueShengDeYu.setXingming(xueSheng.getXingming());
					xueShengDeYu.setFenshu(null);
					xueShengDeYus.add(xueShengDeYu);
				} else {
					if (!"3".equals(xueShengDeYu.getLeixing().toString())) {
						xueShengDeYu = new XueShengDeYu();
						xueShengDeYu.setXuehao(Integer.parseInt(xueSheng.getXuehao()));
						xueShengDeYu.setXingming(xueSheng.getXingming());
						xueShengDeYu.setFenshu(null);
						xueShengDeYus.add(xueShengDeYu);
					} else {
						xueShengDeYu.setXuehao(Integer.parseInt(xueSheng.getXuehao()));
						xueShengDeYu.setXingming(xueSheng.getXingming());
						String deFens[] = xueShengDeYu.getDefenxiangqing().split(",");
						num = deFens.length;
						Map<String, String> map3 = new HashMap<>();
						for (int j = 0; j < deFens.length; j++) {
							String defen[] = deFens[j].split(":");
							if (defen[1].endsWith(";")) {
								defen[1] = defen[1].substring(0, defen[1].length() - 1);
							}
							map3.put(defen[0], defen[1]);
						}
						double manFen = 0;
						List<String> yiJiZongFens = new ArrayList<>();
						List<String> fenShus = new ArrayList<>();
						for (DeYuPingFenFangAn deYuPingFenFangAn : fangAns) {
							if (deYuPingFenFangAn.getShangjiid().toString().equals("0")) {
								manFen = manFen + deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen();
								double yiJiZongFen = 0;
								for (DeYuPingFenFangAn fangAn : deYuPingFenFangAn.getChildList()) {
									double erJiZongFen = 0;
									if (fangAn.getXiangleixing() == 0) {
										for (int j = 0; j < deFens.length; j++) {
											String defen[] = deFens[j].split(":");
											if (fangAn.getPingfenid().toString().equals(defen[0])) {
												if (fangAn.getXueshengtianxie() == 1) {
													erJiZongFen = erJiZongFen + Integer
															.parseInt(map3.get(fangAn.getPingfenid().toString()));
													fenShus.add(map3.get(fangAn.getPingfenid().toString()));
												} else if (fangAn.getXueshengtianxie() == 2) {
													double danXiangFenHe = 0;
													String dString = map3.get(fangAn.getPingfenid().toString());
													String deFenXiang[] = dString.split(";");
													if (!deFenXiang[0].equals("无")) {
														danXiangFenHe = deFenXiang.length * fangAn.getDanxiangfen();
														if (danXiangFenHe > fangAn.getManfen()) {
															erJiZongFen = erJiZongFen + fangAn.getManfen();
															fenShus.add(fangAn.getManfen().toString());
														} else {
															erJiZongFen = erJiZongFen + danXiangFenHe;
															fenShus.add(Double.toString(danXiangFenHe));
														}
													} else {
														fenShus.add("0");
													}
												} else {
													String dString = map3.get(fangAn.getPingfenid().toString());
													erJiZongFen = erJiZongFen + Integer.parseInt(dString);
													fenShus.add(dString);
												}
												break;
											}
										}

									} else {
										if (null == fangAn.getChildList()) {

										} else {
											for (DeYuPingFenFangAn an : fangAn.getChildList()) {
												for (int j1 = 0; j1 < deFens.length; j1++) {
													String defen1[] = deFens[j1].split(":");
													if (an.getPingfenid().toString().equals(defen1[0])) {
														if (an.getXueshengtianxie() == 1) {
															if (an.getManfen() <= fangAn.getManfen()) {
																erJiZongFen = erJiZongFen + Integer.parseInt(
																		map3.get(an.getPingfenid().toString()));
																fenShus.add(map3.get(an.getPingfenid().toString()));
															}
														} else if (an.getXueshengtianxie() == 2) {
															double danXiangFenHe = 0;
															String dString = map3.get(an.getPingfenid().toString());
															String deFenXiang[] = dString.split(";");
															if (!deFenXiang[0].equals("无")) {
																danXiangFenHe = deFenXiang.length * an.getDanxiangfen();
																if (danXiangFenHe > an.getManfen()) {
																	erJiZongFen = erJiZongFen + an.getManfen();
																	fenShus.add(an.getManfen().toString());
																} else {
																	erJiZongFen = erJiZongFen + danXiangFenHe;
																	fenShus.add(Double.toString(danXiangFenHe));
																}
															} else {
																fenShus.add("0");
															}
														} else {
															String dString = map3.get(an.getPingfenid().toString());
															erJiZongFen = erJiZongFen + Integer.parseInt(dString);
															fenShus.add(dString);
														}
														break;
													}
												}
											}
										}
									}
									if (erJiZongFen > fangAn.getManfen()) {
										yiJiZongFen = yiJiZongFen + fangAn.getManfen();
									} else {
										yiJiZongFen = yiJiZongFen + erJiZongFen;
									}
								}
								if (yiJiZongFen > deYuPingFenFangAn.getManfen()) {
									yiJiZongFens.add(deYuPingFenFangAn.getManfen().toString());
									fenShus.add(deYuPingFenFangAn.getManfen().toString());
								} else {
									yiJiZongFens.add(Double.toString(yiJiZongFen));
									fenShus.add(Double.toString(yiJiZongFen));
								}
								xueShengDeYu.setFenshu(fenShus);
							}
						}
						xueShengDeYus.add(xueShengDeYu);
					}
				}
			}
		}
		if (num == 0) {
			for (DeYuPingFenFangAn fangAn : fangAns) {
				int i = fangAn.getChildList().size();
				int m = 0;
				for (DeYuPingFenFangAn an : fangAn.getChildList()) {
					if (null != an.getChildList()) {
						int k = an.getChildList().size() - 1;
						m += k;
						k = 0;
					}
				}
				num += (i + m);
				i = 0;
			}
		}
		mv.addObject("mingcheng", bj.getBanjimingcheng());
		mv.addObject("xuenian", xuenian);
		mv.addObject("xueqi", xueqi);
		mv.addObject("mingchengs", xuenian + "第" + xueqi + "学期" + bj.getBanjimingcheng() + "班德育成绩");
		mv.addObject("num", num + fangAns.size());
		mv.addObject("xueShengDeYus", xueShengDeYus);
		mv.addObject("fangAns", fangAns);
		mv.setViewName("fudaoyuan/exportdeyu");
		return mv;
	}

	@RequestMapping(value = "xgdeyulx_fdy")
	@ResponseBody
	public String xgdeyulx_fdy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String id = request.getParameter("id");
		String leixing = request.getParameter("leixing");
		if (!Util.isNumeric(id)) {
			response.sendRedirect("logout");
			return null;
		}
		XueShengDeYu xueShengDeYu = deYuService.selectByDeYuFenID(Integer.parseInt(id));
		xueShengDeYu.setLeixing(Integer.parseInt(leixing));
		int i = deYuService.updateByPrimaryKeySelective(xueShengDeYu);
		if (i > 0) {
			return "success";
		}
		return null;
	}

	@RequestMapping(value = "shenhedeyu_fdy")
	@ResponseBody
	public String shenhedeyu_fdy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String ids = request.getParameter("ids");
		int i = 0;
		List<XueShengDeYu> xueShengDeYuss = new ArrayList<>();
		for (String id : ids.split(",")) {
			if (!"".equals(id)) {
				XueShengDeYu xueShengDeYu = deYuService.selectByDeYuFenID(Integer.parseInt(id));
				if (null != xueShengDeYu) {
					if (xueShengDeYu.getLeixing().toString().equals("2")) {
						xueShengDeYu.setLeixing(3);
						xueShengDeYuss.add(xueShengDeYu);
					}
				}
			}
		}
		// i = deYuService.updateByPrimaryKeySelective(xueShengDeYu);
		i = deYuService.updateXueShengDeYuZhuangTaiByList(xueShengDeYuss);
		if (i > 0) {
			List<XueShengDeYu> xueShengDeYus = deYuService
					.selectAllByXueQiDeYuID(xueShengDeYuss.get(0).getXueqideyuid());
			int j = 0;
			for (XueShengDeYu xueShengDeYu2 : xueShengDeYus) {
				if ("2".equals(xueShengDeYu2.getLeixing().toString())) {
					j = 1;
					break;
				}
			}
			if (j == 0) {
				XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiDeYuID(xueShengDeYuss.get(0).getXueqideyuid());
				xueQiDeYu.setZhuangtai(2);
				int k = deYuService.updateXueQiDeYuByXueQiDeYu(xueQiDeYu);
				if (k > 0) {
					return "success";
				}
			}
			return "success";
		}
		return null;
	}
}
