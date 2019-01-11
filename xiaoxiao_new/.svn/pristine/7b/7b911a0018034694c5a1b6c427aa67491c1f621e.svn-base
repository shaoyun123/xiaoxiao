package com.web.controller.web.fudaoyuan;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.web.annotation.LoginStatusAnnotation;
import com.web.model.BanJi;
import com.web.model.FDAP;
import com.web.model.FuDaoYuan;
import com.web.model.JLNR;
import com.web.model.KeCheng;
import com.web.model.TiXing;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.service.BanJiService;
import com.web.service.FDAPService;
import com.web.service.FuDaoYuanService;
import com.web.service.JLNRService;
import com.web.service.TiXingService;
import com.web.service.XueShengService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
@LoginStatusAnnotation(status = "fudaoyuan")
public class ShenDuFuDaoController {

	@Autowired
	private FuDaoYuanService fudaoyuanService;
	@Autowired
	private BanJiService banjiService;
	@Autowired
	private XueShengService xueshengService;
	@Autowired
	private FDAPService fdapService;
	@Autowired
	private JLNRService jlnrService;
	@Autowired
	private TiXingService tixingService;

	@RequestMapping(value = "huibaorenwu") // 汇报任务列表
	public ModelAndView huibaorenwu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			List<FDAP> fdaps = fdapService.getAllByfuDaoYuanID(user.getYonghuid());
			int count = fdaps.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<FDAP> fdaps2 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						fdaps2.add(fdaps.get(i));
					}
					mv.addObject("fdap", fdaps2);
				} else {
					for (int i = 0; i < 10; i++) {
						fdaps2.add(fdaps.get(i));
					}
					mv.addObject("fdap", fdaps2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<FDAP> fdaps2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								fdaps2.add(fdaps.get(i));
							}
							mv.addObject("fdap", fdaps2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								fdaps2.add(fdaps.get(i));
							}
							mv.addObject("fdap", fdaps2);
						}
					} else if (page == pages) {
						List<FDAP> fdaps2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							fdaps2.add(fdaps.get(i));
						}
						mv.addObject("fdap", fdaps2);
					} else {
						response.sendRedirect("logout");
						return null;
					}
				} else {
					response.sendRedirect("logout");
					return null;
				}
			}
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("fudaoyuan/huibaorenwu");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addhuibaorenwu") // 新增汇报任务
	public ModelAndView addhuibaorenwu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			int fudaoyuanid = user.getYonghuid();
			FuDaoYuan fuDaoYuan = fudaoyuanService.getByfuDaoYuanID(fudaoyuanid);
			String banjiids = fuDaoYuan.getBanjiid();
			String banjiid[] = banjiids.split(",");
			List<BanJi> banJis = new ArrayList<>();
			for (String string : banjiid) {
				BanJi banji = banjiService.selectByPrimaryKey(Integer.parseInt(string));
				banJis.add(banji);
			}
			// System.out.println(banjimingchengs);
			mv.addObject("banji", banJis);
			mv.setViewName("fudaoyuan/addhuibaorenwu");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "pubhuibaorenwu") // 发布汇报任务
	public Map<String, Object> pubhuibaorenwu(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, Object> map2 = new HashMap<>();
			String mingcheng = request.getParameter("mingcheng");
			String yaoqiu = request.getParameter("yaoqiu");
			String banjiids = request.getParameter("banjis");
			String jiezhiriqi = request.getParameter("jiezhiriqi");
			System.out.println(jiezhiriqi);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date jiezhishijian = sdf.parse(jiezhiriqi);
			// System.out.println(banjiids);
			Date kaishishijian = new Date();
			String banjiid[] = banjiids.split(",");
			String xueshengids = "";
			for (String string : banjiid) {
				List<XueSheng> xueShengs = new ArrayList<>();
				xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
				for (XueSheng xueSheng : xueShengs) {
					xueshengids += xueSheng.getXueshengid() + ",";
				}
			}
			// System.out.println(xueshengids);
			FDAP fdap = new FDAP();
			fdap.setMingcheng(mingcheng);
			fdap.setYaoqiu(yaoqiu);
			fdap.setFudaoyuanid(user.getYonghuid());
			fdap.setKaishishijian(kaishishijian);
			fdap.setJiezhishijian(jiezhishijian);
			fdap.setBanjiids(banjiids);
			fdap.setXueshengids(xueshengids);
			int i = fdapService.insert(fdap);
			int anpaiid = fdap.getAnpaiid();
			if (i != 0) {
				String xueshengs[] = xueshengids.split(",");
				// 提醒消息
				TiXing tiXing = new TiXing();
				tiXing.setNeirong("辅导员" + user.getYonghuxingming() + "发布了新任务$" + mingcheng + "$,快去完成吧！");
				Date date = new Date();
				tiXing.setShijian(date);
				tiXing.setZhuangtai(0);
				for (String string : xueshengs) {
					JLNR jlnr = new JLNR();
					jlnr.setJiaoliumingcheng(mingcheng);
					jlnr.setAnpaiid(anpaiid);
					jlnr.setXueshengid(Integer.parseInt(string));
					jlnr.setFudaoyuanid(user.getYonghuid());
					jlnr.setShangchuanzhuangtai("0");
					jlnr.setShenhezhuangtai("0");
					jlnrService.insert(jlnr);
					// 提醒消息
					tiXing.setJieshourenid(Integer.parseInt(string));
					tixingService.insert(tiXing);
				}
				map2.put("status", "success");
			} else {
				map2.put("status", "fail");
			}
			return map2;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "updatehuibaorenwu") // 修改汇报任务
	public ModelAndView updatehuibaorenwu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			int fudaoyuanid = user.getYonghuid();
			String anpaiid = request.getParameter("id");
			if (!Util.isNumeric(anpaiid)) {
				response.sendRedirect("logout");
				return null;
			}
			FDAP fdap = fdapService.getByanPaiID(Integer.parseInt(anpaiid));
			if (fdap != null && fdap.getFudaoyuanid() == fudaoyuanid) {
				FuDaoYuan fuDaoYuan = fudaoyuanService.getByfuDaoYuanID(fudaoyuanid);
				String banjiids = fuDaoYuan.getBanjiid();
				String banjiid[] = banjiids.split(",");
				List<BanJi> banJis = new ArrayList<>();
				for (String string : banjiid) {
					BanJi banji = banjiService.selectByPrimaryKey(Integer.parseInt(string));
					banJis.add(banji);
				}
				// System.out.println(banjimingchengs);
				mv.addObject("banji", banJis);
				mv.addObject("fdap", fdap);
				mv.setViewName("fudaoyuan/xiugaihuibaorenwu");
				return mv;
			} else {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "saveupdatehuibaorenwu") // 发布修改过的汇报任务
	public Map<String, Object> saveupdatehuibaorenwu(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, Object> map2 = new HashMap<>();
			String anpaiid = request.getParameter("id");
			if (!Util.isNumeric(anpaiid)) {
				response.sendRedirect("logout");
				return null;
			}
			FDAP fdap2 = fdapService.getByanPaiID(Integer.parseInt(anpaiid));
			if (fdap2 != null && fdap2.getFudaoyuanid() == user.getYonghuid()) {
				String mingcheng = request.getParameter("mingcheng");
				String yaoqiu = request.getParameter("yaoqiu");
				String banjiids = request.getParameter("banjis");
				String jiezhiriqi = request.getParameter("jiezhiriqi");
				System.out.println(jiezhiriqi);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date jiezhishijian = sdf.parse(jiezhiriqi);
				// System.out.println(banjiids);
				Date kaishishijian = new Date();
				String banjiid[] = banjiids.split(",");
				String xueshengids = "";
				for (String string : banjiid) {
					List<XueSheng> xueShengs = new ArrayList<>();
					xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs) {
						xueshengids += xueSheng.getXueshengid() + ",";
					}
				}
				// System.out.println(xueshengids);
				FDAP fdap = new FDAP();
				fdap.setAnpaiid(Integer.parseInt(anpaiid));
				fdap.setMingcheng(mingcheng);
				fdap.setYaoqiu(yaoqiu);
				fdap.setFudaoyuanid(user.getYonghuid());
				fdap.setKaishishijian(kaishishijian);
				fdap.setJiezhishijian(jiezhishijian);
				fdap.setBanjiids(banjiids);
				fdap.setXueshengids(xueshengids);
				int i = fdapService.updateByPrimaryKey(fdap);
				if (i != 0) {
					JLNR jlnr = new JLNR();
					jlnr.setJiaoliumingcheng(mingcheng);
					jlnr.setAnpaiid(Integer.parseInt(anpaiid));
					jlnrService.updateByanPaiID(jlnr);
					// 发送提醒消息
					String xueshengid[] = xueshengids.split(",");
					TiXing tiXing = new TiXing();
					tiXing.setNeirong("辅导员" + user.getYonghuxingming() + "修改了任务$" + fdap2.getMingcheng() + "$,修改后名字为$"
							+ fdap.getMingcheng() + "$");
					Date date = new Date();
					tiXing.setShijian(date);
					tiXing.setZhuangtai(0);
					for (String string : xueshengid) {
						tiXing.setJieshourenid(Integer.parseInt(string));
						tixingService.insert(tiXing);
					}
					map2.put("status", "success");
				} else {
					map2.put("status", "fail");
				}
				return map2;
			} else {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "deletehuibaorenwu") // 删除汇报任务（辅导安排）
	public Object deletehuibaorenwu(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			String anpaiid = request.getParameter("id");
			if (!Util.isNumeric(anpaiid)) {
				response.sendRedirect("logout");
				return null;
			}
			FDAP fdap = fdapService.getByanPaiID(Integer.parseInt(anpaiid));
			if (fdap != null && fdap.getFudaoyuanid() == user.getYonghuid()) {
				int i = fdapService.deleteByPrimaryKey(Integer.parseInt(anpaiid));
				if (i != 0) {
					jlnrService.deleteByanPaiID(Integer.parseInt(anpaiid));
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					tiXing.setNeirong("辅导员" + user.getYonghuxingming() + "删除了任务$" + fdap.getMingcheng() + "$");
					Date date = new Date();
					tiXing.setShijian(date);
					tiXing.setZhuangtai(0);
					String xueshengid[] = fdap.getXueshengids().split(",");
					for (String string : xueshengid) {
						tiXing.setJieshourenid(Integer.parseInt(string));
						tixingService.insert(tiXing);
					}
					map.put("status", "success");
				} else {
					map.put("status", "fail");
				}
				return JSON.toJSON(map);
			} else {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chakanxiangqing") // 查询学生上传详情，全部显示
	public ModelAndView chakanxiangqing(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			int fudaoyuanid = user.getYonghuid();
			String anpaiid = request.getParameter("id");
			if (!Util.isNumeric(anpaiid)) {
				response.sendRedirect("logout");
				return null;
			}
			FDAP fdap = fdapService.getByanPaiID(Integer.parseInt(anpaiid));
			if (fdap != null && fdap.getFudaoyuanid() == fudaoyuanid) {
				FuDaoYuan fuDaoYuan = fudaoyuanService.getByfuDaoYuanID(fudaoyuanid);
				String banjiids = fuDaoYuan.getBanjiid();
				String banjiid[] = banjiids.split(",");
				List<BanJi> banJis = new ArrayList<>();
				for (String string : banjiid) {
					BanJi banji = banjiService.selectByPrimaryKey(Integer.parseInt(string));
					banJis.add(banji);
				}
				List<JLNR> jlnrs = jlnrService.getAllByanPaiID(Integer.parseInt(anpaiid));
				for (JLNR jlnr : jlnrs) {
					String xueshengid = jlnr.getXueshengid().toString();
					XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(xueshengid));
					String banjiid2 = xueSheng.getBanjiid().toString();
					String xuehao = xueSheng.getXuehao();
					String xingming = xueSheng.getXingming();
					String banjimingcheng = banjiService.selectByPrimaryKey(Integer.parseInt(banjiid2))
							.getBanjimingcheng();
					jlnr.setBanjimingcheng(banjimingcheng);
					jlnr.setXuehao(xuehao);
					jlnr.setXingming(xingming);
				}
				int count = jlnrs.size();
				int pageSize = 10;
				int page = 1;
				int pages = (count / pageSize) + 1;
				if (request.getParameter("page") == null || request.getParameter("page") == "") {
					List<JLNR> jlnrs2 = new ArrayList<>();
					if (count < 10) {
						for (int i = 0; i < count; i++) {
							jlnrs2.add(jlnrs.get(i));
						}
						mv.addObject("jlnr", jlnrs2);
					} else {
						for (int i = 0; i < 10; i++) {
							jlnrs2.add(jlnrs.get(i));
						}
						mv.addObject("jlnr", jlnrs2);
					}
				} else {
					if (Util.isNumeric(request.getParameter("page"))) {
						page = Integer.parseInt(request.getParameter("page"));
						if (page > 0 && page < pages) {
							List<JLNR> jlnrs2 = new ArrayList<>();
							if (count < 10) {
								for (int i = (page - 1) * 10; i < count; i++) {
									jlnrs2.add(jlnrs.get(i));
								}
								mv.addObject("jlnr", jlnrs2);
							} else {
								for (int i = (page - 1) * 10; i < (page * 10); i++) {
									jlnrs2.add(jlnrs.get(i));
								}
								mv.addObject("jlnr", jlnrs2);
							}
						} else if (page == pages) {
							List<JLNR> jlnrs2 = new ArrayList<>();
							for (int i = (page - 1) * 10; i < count; i++) {
								jlnrs2.add(jlnrs.get(i));
							}
							mv.addObject("jlnr", jlnrs2);
						} else {
							response.sendRedirect("logout");
						}
					} else {
						response.sendRedirect("logout");
						return null;
					}
				}
				mv.addObject("banji", banJis);
				mv.addObject("fdap", fdap);
				mv.addObject("pages", pages);
				mv.addObject("page", page);
				mv.setViewName("fudaoyuan/chakanxiangqing");
				return mv;
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chakanxiangqing_banji") // 查询学生上传详情,按班级查询
	public ModelAndView chakanxiangqing_banji(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			int fudaoyuanid = user.getYonghuid();
			String anpaiid = request.getParameter("id");
			if (!Util.isNumeric(anpaiid)) {
				response.sendRedirect("logout");
				return null;
			}
			FDAP fdap = fdapService.getByanPaiID(Integer.parseInt(anpaiid));
			if (fdap != null && fdap.getFudaoyuanid() == fudaoyuanid) {
				FuDaoYuan fuDaoYuan = fudaoyuanService.getByfuDaoYuanID(fudaoyuanid);
				String banjiid_chaxun = request.getParameter("banjiid");
				String banjiids = fuDaoYuan.getBanjiid();
				String banjiid[] = banjiids.split(",");
				List<BanJi> banJis = new ArrayList<>();
				for (String string : banjiid) {
					BanJi banji = banjiService.selectByPrimaryKey(Integer.parseInt(string));
					banJis.add(banji);
				}
				List<JLNR> jlnrs = jlnrService.getAllByanPaiID(Integer.parseInt(anpaiid));
				List<JLNR> jlnrs2 = new ArrayList<>();
				for (JLNR jlnr : jlnrs) {
					String xueshengid = jlnr.getXueshengid().toString();
					XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(xueshengid));
					String banjiid2 = xueSheng.getBanjiid().toString();
					if (banjiid2.equals(banjiid_chaxun) || banjiid_chaxun.equals("")) {
						String xuehao = xueSheng.getXuehao();
						String xingming = xueSheng.getXingming();
						String banjimingcheng = banjiService.selectByPrimaryKey(Integer.parseInt(banjiid2))
								.getBanjimingcheng();
						jlnr.setBanjimingcheng(banjimingcheng);
						jlnr.setXuehao(xuehao);
						jlnr.setXingming(xingming);
						jlnrs2.add(jlnr);
					}
				}
				int count = jlnrs2.size();
				int pageSize = 10;
				int page = 1;
				int pages = (count / pageSize) + 1;
				if (request.getParameter("page") == null || request.getParameter("page") == "") {
					List<JLNR> jlnrs3 = new ArrayList<>();
					if (count < 10) {
						for (int i = 0; i < count; i++) {
							jlnrs3.add(jlnrs2.get(i));
						}
						mv.addObject("jlnr", jlnrs3);
					} else {
						for (int i = 0; i < 10; i++) {
							jlnrs3.add(jlnrs2.get(i));
						}
						mv.addObject("jlnr", jlnrs3);
					}
				} else {
					if (Util.isNumeric(request.getParameter("page"))) {
						page = Integer.parseInt(request.getParameter("page"));
						if (page > 0 && page < pages) {
							List<JLNR> jlnrs3 = new ArrayList<>();
							if (count < 10) {
								for (int i = (page - 1) * 10; i < count; i++) {
									jlnrs3.add(jlnrs2.get(i));
								}
								mv.addObject("jlnr", jlnrs3);
							} else {
								for (int i = (page - 1) * 10; i < (page * 10); i++) {
									jlnrs3.add(jlnrs2.get(i));
								}
								mv.addObject("jlnr", jlnrs3);
							}
						} else if (page == pages) {
							List<JLNR> jlnrs3 = new ArrayList<>();
							for (int i = (page - 1) * 10; i < count; i++) {
								jlnrs3.add(jlnrs2.get(i));
							}
							mv.addObject("jlnr", jlnrs3);
						} else {
							response.sendRedirect("logout");
							return null;
						}
					} else {
						response.sendRedirect("logout");
						return null;
					}
				}
				mv.addObject("banjiid", banjiid_chaxun);
				mv.addObject("banji", banJis);
				mv.addObject("fdap", fdap);
				mv.addObject("pages", pages);
				mv.addObject("page", page);
				mv.setViewName("fudaoyuan/chakanxiangqing");
				return mv;
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "shenhehuibao") // 审核汇报
	public ModelAndView shenhehuibao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			int fudaoyuanid = user.getYonghuid();
			String jiaoliuid = request.getParameter("id");
			if (!Util.isNumeric(jiaoliuid)) {
				response.sendRedirect("logout");
				return null;
			}
			JLNR jlnr = jlnrService.selectByPrimaryKey(Integer.parseInt(jiaoliuid));
			if (jlnr != null && jlnr.getFudaoyuanid() == fudaoyuanid) {
				int xuehsengid = jlnr.getXueshengid();
				XueSheng xueSheng = xueshengService.getUserById(xuehsengid);
				String xingming = xueSheng.getXingming();
				String xuehao = xueSheng.getXuehao();
				int banjiid = xueSheng.getBanjiid();
				String banjimingcheng = banjiService.selectByPrimaryKey(banjiid).getBanjimingcheng();
				jlnr.setBanjimingcheng(banjimingcheng);
				jlnr.setXingming(xingming);
				jlnr.setXuehao(xuehao);
				mv.addObject("jlnr", jlnr);
				mv.setViewName("fudaoyuan/shenhehuibao");
				return mv;
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "submitshenhe") // 提交审核内容
	public Object submitshenhe(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			String jiaoliuid = request.getParameter("id");
			if (!Util.isNumeric(jiaoliuid)) {
				response.sendRedirect("logout");
				return null;
			}
			JLNR jlnr = jlnrService.selectByPrimaryKey(Integer.parseInt(jiaoliuid));
			if (jlnr != null && jlnr.getFudaoyuanid() == user.getYonghuid()) {
				String shenheneirong = request.getParameter("shenhe");
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				JLNR jlnr2 = new JLNR();
				jlnr2.setJiaoliuid(Integer.parseInt(jiaoliuid));
				jlnr2.setFudaoyuanshenhe(shenheneirong);
				jlnr2.setShenhezhuangtai("1");
				jlnr2.setShenheriqi(format.format(date));
				int i = jlnrService.updatefuDaoYuanShenHeByjiaoLiuID(jlnr2);
				if (i != 0) {
					map.put("status", "success");
				} else {
					map.put("status", "fail");
				}
				return JSON.toJSON(map);
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chakanjiaoliuneirong") // 查看交流内容
	public ModelAndView chakanjiaoliuneirong(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			int fudaoyuanid = user.getYonghuid();
			String jiaoliuid = request.getParameter("id");
			if (!Util.isNumeric(jiaoliuid)) {
				response.sendRedirect("logout");
				return null;
			}
			JLNR jlnr = jlnrService.selectByPrimaryKey(Integer.parseInt(jiaoliuid));
			if (jlnr != null && jlnr.getFudaoyuanid() == fudaoyuanid) {
				int xuehsengid = jlnr.getXueshengid();
				XueSheng xueSheng = xueshengService.getUserById(xuehsengid);
				String xingming = xueSheng.getXingming();
				String xuehao = xueSheng.getXuehao();
				int banjiid = xueSheng.getBanjiid();
				String banjimingcheng = banjiService.selectByPrimaryKey(banjiid).getBanjimingcheng();
				jlnr.setBanjimingcheng(banjimingcheng);
				jlnr.setXingming(xingming);
				jlnr.setXuehao(xuehao);
				mv.addObject("jlnr", jlnr);
				mv.setViewName("fudaoyuan/chakanjiaoliuneirong");
				return mv;
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
}
