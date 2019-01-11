package com.web.controller.web.guanliyuan;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.annotation.LoginStatusAnnotation;
import com.web.dao.KeMuMapper;
import com.web.model.BeiWL;
import com.web.model.HuoDong;
import com.web.model.JCSJ;
import com.web.model.JiaoShi;
import com.web.model.JiaoXueLou;
import com.web.model.JieCiFangAn;
import com.web.model.KeCheng;
import com.web.model.KeMu;
import com.web.model.NianFen;
import com.web.model.TiXing;
import com.web.model.XiaoXiFaSong;
import com.web.model.XueQi;
import com.web.model.XueSheng;
import com.web.model.XueShengChuGuanLiYuan;
import com.web.model.YiJianXiang;
import com.web.model.YongHu;
import com.web.service.BanJiService;
import com.web.service.BeiWLService;
import com.web.service.HuoDongService;
import com.web.service.JCSJService;
import com.web.service.JiGuangService;
import com.web.service.JiaoShiService;
import com.web.service.JiaoXueLouService;
import com.web.service.JieCiFangAnService;
import com.web.service.KeChengJiBenService;
import com.web.service.KeChengService;
import com.web.service.NianFenService;
import com.web.service.TiXingService;
import com.web.service.XiaoQuService;
import com.web.service.XueQiService;
import com.web.service.XueShengChuGuanLiYuanService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
@LoginStatusAnnotation(status = "guanliyuan")
public class RiChengController_guanliyuan {

	@Autowired
	private YongHuService yonghuService;
	@Autowired
	private YuanXiService yuanxiService;
	@Autowired
	private KeChengJiBenService kechengjibenService;
	@Autowired
	private XueShengChuGuanLiYuanService guanLiYuanService;
	@Autowired
	private XueShengChuGuanLiYuanService xueShengChuGuanLiYuanService;
	@Autowired
	private JieCiFangAnService jiecifanganService;
	@Autowired
	private JCSJService jcsjService;
	@Autowired
	private XueQiService xueqiService;
	@Autowired
	private BeiWLService beiwlService;
	@Autowired
	private KeChengService kechengService;
	@Autowired
	private TiXingService tixingService;
	@Autowired
	private HuoDongService huodongService;
	@Autowired
	private JiaoShiService jiaoshiService;
	@Autowired
	private XiaoQuService xiaoquService;
	@Autowired
	private JiGuangService jiGuangService;
	@Autowired
	private XueShengService xueshengService;
	@Autowired
	private BanJiService banjiService;
	@Autowired
	private NianFenService nianFenService;
	@Autowired
	private KeMuMapper keMuMapper;
	@Autowired
	private JiaoXueLouService jiaoxuelouService;

	@RequestMapping(value = "addkecheng_gly") // 添加课程
	public ModelAndView addkecheng_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			// 选择院系列表
			// int xuexiaoid =
			// guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
			// List<YuanXi> yuanXis =
			// yuanxiService.getAllByxueXiaoID(xuexiaoid);
			// 选择教师列表
			// List<YongHu> yongHus =
			// yonghuService.getAllByYuanXiID(user.getYuanxiid());
			// mv.addObject("jiaoshi", yongHus);
			// mv.addObject("yuanxi", yuanXis);
			List<KeMu> keMus = keMuMapper.selectAllKeMu();
			mv.addObject("kemus", keMus);
			mv.setViewName("guanliyuan/addkecheng_gly");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savekecheng_gly") // 保存新增课程
	@ResponseBody
	public String savekecheng_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			YongHu user = (YongHu) session.getAttribute("user");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out = response.getWriter();
			String[] kemuid = request.getParameterValues("kemuid");
			int xuexiaoid = guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
			String zhuangtai = request.getParameter("zhuangtai");
			if (kemuid != null && !"".equals(kemuid)) {
				int j = 0;
				for (int i = 0; i < kemuid.length; i++) {
					Map<String, Integer> map = new HashMap<>();
					map.put("kemuid", Integer.parseInt(kemuid[i]));
					map.put("xuexiaoid", xuexiaoid);
					map.put("zhuangtai", Integer.parseInt(zhuangtai));
					int k = keMuMapper.getOne(map);
					if (k == 0) {
						j = keMuMapper.insertXueXiaoKeMu(map);
						if (j != 0) {
							// out.print("<script>alert('加课成功！');</script>");
							// out.print("<script>location='kechengliebiao_gly';</script>");
							return "success";
						} else {
							// out.print("<script>alert('加课失败！');</script>");
							// out.print("<script>location='kechengliebiao_gly';</script>");
						}
					} else {
						KeMu kemu = keMuMapper.selectByPrimaryKey(kemuid[i]);
						// out.print("<script>alert('" + kemu.getMingcheng() +
						// "已添加！');</script>");
						// out.print("<script>location='kechengliebiao_gly';</script>");
						return "yitianjia";
					}
				}
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savekemu_gly") // 保存新增课目
	@ResponseBody
	public String savekemu_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		YongHu user = (YongHu) session.getAttribute("user");
		if (Util.checkSession(request)) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out = response.getWriter();
			int xuexiaoid = guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
			String mingcheng = request.getParameter("mingcheng");
			String daima = request.getParameter("daima");
			// Map<String, String> m = new HashMap<>();
			KeMu kemu = new KeMu();
			kemu.setMingcheng(mingcheng);
			kemu.setDaima(daima);
			// m.put("mingcheng", mingcheng);
			// m.put("daima", daima);
			int n = keMuMapper.getKeMu(kemu);
			if (n == 0) {
				int i = keMuMapper.insertSelective(kemu);
				if (i != 0) {
					int j = 0;
					Map<String, Integer> map = new HashMap<>();
					map.put("kemuid", kemu.getKemuid());
					map.put("xuexiaoid", xuexiaoid);
					map.put("zhuangtai", 1);
					int k = keMuMapper.getOne(map);
					if (k == 0) {
						j = keMuMapper.insertXueXiaoKeMu(map);
						if (j != 0) {
							// out.print("<script>alert('加课成功！');</script>");
							// out.print("<script>location='kechengliebiao_gly';</script>");
							return "success";
						} else {
							// out.print("<script>alert('加课失败！');</script>");
							// out.print("<script>location='kechengliebiao_gly';</script>");
						}
					} else {
						// out.print("<script>alert('" + mingcheng +
						// "已添加！');</script>");
						// out.print("<script>location='kechengliebiao_gly';</script>");
						return "yitianjia";
					}
				} else {
					// out.print("<script>alert('加课失败！');</script>");
					// out.print("<script>location='kechengliebiao_gly';</script>");
				}
			} else {
				// out.print("<script>alert('" + mingcheng +
				// "已添加！');</script>");
				// out.print("<script>location='kechengliebiao_gly';</script>");
				return "yitianjia";
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "kechengliebiao_gly") // 课程列表
	public ModelAndView kechengliebiao_gly(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			YongHu user = (YongHu) session.getAttribute("user");
			// 选择院系列表
			int xuexiaoid = guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
			// 查询课程，首次默认是查询全部
			Map<String, String> map = new HashMap<>();
			map.put("xuexiaoid", String.valueOf(xuexiaoid));
			List<Map<String, Object>> keMus = keMuMapper.selectKeMuByXueXiaoId(xuexiaoid);
			int count = keMus.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (count % pageSize == 0) {
				pages = count / pageSize;
			}
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<Map<String, Object>> keMus2 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						keMus2.add(keMus.get(i));
					}
					mv.addObject("kecheng", keMus2);
				} else {
					for (int i = 0; i < 10; i++) {
						keMus2.add(keMus.get(i));
					}
					mv.addObject("kecheng", keMus2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<Map<String, Object>> keMus2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								keMus2.add(keMus.get(i));
							}
							mv.addObject("kecheng", keMus2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								keMus2.add(keMus.get(i));
							}
							mv.addObject("kecheng", keMus2);
						}
					} else if (page == pages) {
						List<Map<String, Object>> keMus2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							keMus2.add(keMus.get(i));
						}
						mv.addObject("kecheng", keMus2);
					} else {
						response.sendRedirect("logout");
						return null;
					}
				} else {
					response.sendRedirect("logout");
					return null;
				}
			}
			mv.addObject("user", user);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("guanliyuan/kechengliebiao_gly");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	// @RequestMapping(value = "chaxunkecheng_gly") // 查询课程
	// public ModelAndView chaxunkecheng_gly(HttpServletRequest request,
	// HttpServletResponse response) throws IOException {
	// ModelAndView mv = new ModelAndView();
	// if (Util.checkSession(request)) {
	// HttpSession session = request.getSession();
	// YongHu user = (YongHu) session.getAttribute("user");
	// // 选择院系列表
	// int xuexiaoid =
	// guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
	// List<YuanXi> yuanXis = yuanxiService.getAllByxueXiaoID(xuexiaoid);
	// // 选择教师列表
	// List<YongHu> yongHus =
	// yonghuService.getAllByYuanXiID(user.getYuanxiid());
	// // 课程列表
	// List<KeChengJiBen> keChengJiBens =
	// kechengjibenService.getAllByxueXiaoID(xuexiaoid);
	// String kechengid = request.getParameter("kechengid");
	// String yuanxiid = request.getParameter("yuanxiid");
	// String jiaoshiid = request.getParameter("jiaoshiid");
	// // 查询课程，首次默认是查询全部
	// Map<String, String> map = new HashMap<>();
	// map.put("xuexiaoid", String.valueOf(xuexiaoid));
	// map.put("kechengid", kechengid);
	// map.put("yuanxiid1", yuanxiid + ",%");
	// map.put("yuanxiid2", "%," + yuanxiid + ",%");
	// map.put("jiaoshiid1", jiaoshiid + ",%");
	// map.put("jiaoshiid2", "%," + jiaoshiid + ",%");
	// List<KeChengJiBen> keChengJiBens2 = kechengjibenService
	// .getAllByxueXiaoIDandkeChengIDandyuanXiIDandjiaoShiID(map);
	// for (KeChengJiBen keChengJiBen : keChengJiBens2) {
	// // 将教师IDs转换成名字
	// String jiaoshiids = keChengJiBen.getRenkejiaoshiids();
	// String jiaoshiid2[] = jiaoshiids.split(",");
	// String renkejiaoshi = "";
	// for (String string : jiaoshiid2) {
	// renkejiaoshi +=
	// yonghuService.selectYongHuByID(Integer.parseInt(string)).getYonghuxingming()
	// + "/";
	// }
	// renkejiaoshi = renkejiaoshi.substring(0, renkejiaoshi.lastIndexOf("/"));
	// keChengJiBen.setRenkejiaoshi(renkejiaoshi);
	// // 将院系IDs转换成名称
	// String yuanxiids = keChengJiBen.getYuanxiids();
	// String yuanxiid2[] = yuanxiids.split(",");
	// String yuanxi = "";
	// for (String string : yuanxiid2) {
	// yuanxi +=
	// yuanxiService.selectByPrimaryKey(Integer.parseInt(string)).getYuanximingcheng()
	// + "/";
	// }
	// yuanxi = yuanxi.substring(0, yuanxi.lastIndexOf("/"));
	// keChengJiBen.setYuanxi(yuanxi);
	// }
	// int count = keChengJiBens2.size();
	// int pageSize = 10;
	// int page = 1;
	// int pages = (count / pageSize) + 1;
	// if (request.getParameter("page") == null || request.getParameter("page")
	// == "") {
	// List<KeChengJiBen> keChengJiBens3 = new ArrayList<>();
	// if (count < 10) {
	//
	// for (int i = 0; i < count; i++) {
	// keChengJiBens3.add(keChengJiBens2.get(i));
	// }
	// mv.addObject("kecheng", keChengJiBens3);
	// } else {
	// for (int i = 0; i < 10; i++) {
	// keChengJiBens3.add(keChengJiBens2.get(i));
	// }
	// mv.addObject("kecheng", keChengJiBens3);
	// }
	// } else {
	// if (Util.isNumeric(request.getParameter("page"))) {
	// page = Integer.parseInt(request.getParameter("page"));
	// if (page > 0 && page < pages) {
	// List<KeChengJiBen> keChengJiBens3 = new ArrayList<>();
	// if (count < 10) {
	// for (int i = (page - 1) * 10; i < count; i++) {
	// keChengJiBens3.add(keChengJiBens2.get(i));
	// }
	// mv.addObject("kecheng", keChengJiBens3);
	// } else {
	// for (int i = (page - 1) * 10; i < (page * 10); i++) {
	// keChengJiBens3.add(keChengJiBens2.get(i));
	// }
	// mv.addObject("kecheng", keChengJiBens3);
	// }
	// } else if (page == pages) {
	// List<KeChengJiBen> keChengJiBens3 = new ArrayList<>();
	// for (int i = (page - 1) * 10; i < count; i++) {
	// keChengJiBens3.add(keChengJiBens2.get(i));
	// }
	// mv.addObject("kecheng", keChengJiBens3);
	// } else {
	// response.sendRedirect("logout");
	// return null;
	// }
	// } else {
	// response.sendRedirect("logout");
	// return null;
	// }
	// }
	// mv.addObject("kechengliebiao", keChengJiBens);
	// mv.addObject("yuanxi", yuanXis);
	// mv.addObject("jiaoshi", yongHus);
	// mv.addObject("kechengid", kechengid);
	// mv.addObject("yuanxiid", yuanxiid);
	// mv.addObject("jiaoshiid", jiaoshiid);
	// mv.addObject("user", user);
	// mv.addObject("pages", pages);
	// mv.addObject("page", page);
	// mv.setViewName("guanliyuan/kechengliebiao_gly");
	// return mv;
	// } else {
	// response.sendRedirect("login");
	// }
	// return null;
	// }

	@RequestMapping(value = "xiugaikecheng_gly") // 修改课程
	public ModelAndView xiugaikecheng_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			String xxkmuid = request.getParameter("id");
			if (!Util.isNumeric(xxkmuid)) {
				response.sendRedirect("logout");
				return null;
			}
			Map<String, Integer> keCheng = keMuMapper.selectXueXiaoKeMu(Integer.parseInt(xxkmuid));
			KeMu keMu = keMuMapper.selectByPrimaryKey(keCheng.get("kemuid").toString());
			if (keMu == null) {
				response.sendRedirect("logout");
				return null;
			}
			// if (keCheng.getTianjiarenid() != user.getYonghuid()) {
			// response.sendRedirect("logout");
			// return null;
			// }
			// 选择院系列表
			// int xuexiaoid =
			// guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
			// List<YuanXi> yuanXis =
			// yuanxiService.getAllByxueXiaoID(xuexiaoid);
			// 已选院系
			// String yuanxiids = keCheng.getYuanxiids();
			// String yuanxiid[] = yuanxiids.split(",");
			// List<YuanXi> yuanXis2 = new ArrayList<>();
			// for (String string : yuanxiid) {
			// YuanXi yuanXi =
			// yuanxiService.selectByPrimaryKey(Integer.parseInt(string));
			// yuanXis2.add(yuanXi);
			// }
			// 选择教师列表
			// List<YongHu> yongHus =
			// yonghuService.getAllByYuanXiID(user.getYuanxiid());
			// 已选教师
			// String jiaoshiids = keCheng.getRenkejiaoshiids();
			// String jiaoshiid[] = jiaoshiids.split(",");
			// List<YongHu> jiaoshis = new ArrayList<>();
			// for (String string : jiaoshiid) {
			// YongHu jiaoshi =
			// yonghuService.selectYongHuByID(Integer.parseInt(string));
			// jiaoshis.add(jiaoshi);
			// }
			// mv.addObject("jiaoshi", yongHus);
			// mv.addObject("yuanxi", yuanXis);
			// mv.addObject("kecheng", keCheng);
			// mv.addObject("yixuanyuanxi", yuanXis2);
			// mv.addObject("yixuanjiaoshi", jiaoshis);
			mv.addObject("xuexiaokemu", keCheng);
			mv.addObject("kemu", keMu);
			mv.setViewName("guanliyuan/xiugaikecheng_gly");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "save_update_kecheng_gly") // 保存修改课程
	@ResponseBody
	public String save_update_kecheng_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out = response.getWriter();
			String xxkmid = request.getParameter("id");
			String zhuangTai = request.getParameter("zhuangtai");
			if (!Util.isNumeric(xxkmid)) {
				response.sendRedirect("logout");
				return null;
			}
			Map<String, Integer> keCheng = keMuMapper.selectXueXiaoKeMu(Integer.parseInt(xxkmid));
			if (keCheng == null) {
				response.sendRedirect("logout");
				return null;
			}

			// String kechengmingcheng =
			// request.getParameter("kechengmingcheng");
			// String yuanxiids = request.getParameter("yuanxiids");
			// String renkejiaoshiids = request.getParameter("jiaoshiids");
			// KeChengJiBen keChengJiBen = new KeChengJiBen();
			// keChengJiBen.setKechengid(Integer.parseInt(kechengid));
			// keChengJiBen.setKechengmingcheng(kechengmingcheng);
			// keChengJiBen.setYuanxiids(yuanxiids);
			// keChengJiBen.setRenkejiaoshiids(renkejiaoshiids);
			// int i = kechengjibenService.updateByPrimaryKey(keChengJiBen);
			int i = keMuMapper.updateXueXiaoKeMu(keCheng.get("xxkmid"), Integer.parseInt(zhuangTai));
			if (i != 0) {
				// 发送激光消息
				// XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				// xiaoXiFaSong.setXiaoXiMingCheng("修改课程");
				// xiaoXiFaSong.setXiaoXiNeiRong(
				// user.getYonghuxingming() + "修改了课程《" +
				// keChengJiBen.getKechengmingcheng() + "》");
				// xiaoXiFaSong.setShuJuId(keChengJiBen.getKechengid());
				// xiaoXiFaSong.setShuJuLeiXing(1);
				// xiaoXiFaSong.setFaSongLeiXing(2);
				// xiaoXiFaSong.setShiFouChengGong(0);
				// xiaoXiFaSong
				// .setXueXiaoId(yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString());
				// 发送消息提醒
				// TiXing tiXing = new TiXing();
				// Date date2 = new Date();
				// tiXing.setNeirong(user.getYonghuxingming() + "修改了课程《" +
				// keChengJiBen.getKechengmingcheng() + "》");
				// tiXing.setShijian(date2);
				// tiXing.setZhuangtai(0);
				// tiXing.setShujuid(keChengJiBen.getKechengid());
				// tiXing.setType(1);
				// String cyr[] = renkejiaoshiids.split(",");
				// for (String string : cyr) {
				// tiXing.setJieshourenid(Integer.parseInt(string));
				// tixingService.insert(tiXing);
				//
				// xiaoXiFaSong.setFaSongMuBiao(string);
				// jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				// }
				// out.print("<script>alert('修改成功！');</script>");
				// out.print("<script>location='kechengliebiao_gly';</script>");
				return "success";
			} else {
				// out.print("<script>alert('修改失败！');</script>");
				// out.print("<script>location='kechengliebiao_gly';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "shanchukecheng_gly") // 删除课程
	@ResponseBody
	public String shanchukecheng_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out = response.getWriter();
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			List<KeCheng> keChengs = kechengService.selectByKeChengId(Integer.parseInt(id));
			if (keChengs != null && keChengs.size() != 0) {
				// out.print("<script>alert('请先删除关联课程!！');</script>");
				// out.print("<script>location='kechengliebiao_gly';</script>");
				return "guanlian";
			} else {
				int i = keMuMapper.deleteXueXiaoKeMu(Integer.parseInt(id));
				if (i != 0) {
					// out.print("<script>alert('删除成功！');</script>");
					// out.print("<script>location='kechengliebiao_gly';</script>");
					return "success";
				} else {
					// out.print("<script>alert('删除失败！');</script>");
					// out.print("<script>location='kechengliebiao_gly';</script>");
				}
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "jiecishijianguanli") // 节次时间管理
	public ModelAndView jiecishijianguanli(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			YongHu user = (YongHu) session.getAttribute("user");
			int xuexiaoid = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
			List<JieCiFangAn> jieCiFangAns = jiecifanganService.getAllByxueXiaoID(xuexiaoid);
			mv.addObject("jiecifangan", jieCiFangAns);
			mv.setViewName("guanliyuan/jiecishijianguanli");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addjiecifangan") // 新增节次方案
	public ModelAndView addjiecifangan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("guanliyuan/addjiecifangan");
		return mv;
	}

	@RequestMapping(value = "save_addjiecifangan") // 保存 新增节次方案
	@ResponseBody
	public String save_addjiecifangan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		// PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		// 用户
		YongHu user = (YongHu) session.getAttribute("user");
		// 学校id
		int xuexiaoid = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();

		String mingcheng = request.getParameter("mingcheng");
		JieCiFangAn jieCiFangAn = new JieCiFangAn();
		jieCiFangAn.setMingcheng(mingcheng);
		jieCiFangAn.setXuexiaoid(xuexiaoid);
		jieCiFangAn.setZhuangtai(0);
		jieCiFangAn.setQiyongguo(0);
		int i = jiecifanganService.insert(jieCiFangAn);
		if (i == 0) {
			// out.print("<script>alert('新增失败！');</script>");
			// out.print("<script>location='jiecishijianguanli';</script>");
			return null;
		}
		int fanganid = jieCiFangAn.getId();
		String jiecidata = request.getParameter("jiecidata");
		String data[] = jiecidata.split(";");
		for (String string : data) {
			String str[] = string.split(",");
			JCSJ jcsj = new JCSJ();
			jcsj.setJieci(Integer.parseInt(str[0]));
			jcsj.setKaishishijian(str[1]);
			jcsj.setJieshushijian(str[2]);
			jcsj.setShijianduan(Integer.parseInt(str[3]));
			jcsj.setJiecifanganid(fanganid);
			int j = jcsjService.insert(jcsj);
			if (j == 0) {
				// out.print("<script>alert('新增失败！');</script>");
				// out.print("<script>location='jiecishijianguanli';</script>");
				return null;
			}
		}
		// out.print("<script>alert('新增成功！');</script>");
		// out.print("<script>location='jiecishijianguanli';</script>");
		return "success";
	}

	@RequestMapping(value = "xiugaijiecifangan") // 修改节次方案
	public ModelAndView xiugaijiecifangan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		String fanganid = request.getParameter("id");
		if (!Util.isNumeric(fanganid)) {
			response.sendRedirect("logout");
			return null;
		}
		JieCiFangAn jieCiFangAn = jiecifanganService.selectByPrimaryKey(Integer.parseInt(fanganid));
		if (jieCiFangAn == null) {
			response.sendRedirect("logout");
			return null;
		}
		List<JCSJ> jcsjs = new ArrayList<>();
		jcsjs = jcsjService.getAllByjieCiFangAnID(Integer.parseInt(fanganid));
		mv.addObject("fangan", jieCiFangAn);
		mv.addObject("jcsj", jcsjs);
		mv.setViewName("guanliyuan/xiugaijiecifangan");
		return mv;
	}

	@RequestMapping(value = "save_xiugaijiecifangan") // 保存 修改节次方案
	@ResponseBody
	public String save_xiugaijiecifangan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		// PrintWriter out = response.getWriter();
		// 获取要修改的方案id
		String fanganid = request.getParameter("id");
		if (!Util.isNumeric(fanganid)) {
			response.sendRedirect("logout");
			return null;
		}
		JieCiFangAn jieCiFangAn = jiecifanganService.selectByPrimaryKey(Integer.parseInt(fanganid));
		if (jieCiFangAn == null) {
			response.sendRedirect("logout");
			return null;
		}

		String mingcheng = request.getParameter("mingcheng");
		JieCiFangAn jieCiFangAn2 = new JieCiFangAn();
		jieCiFangAn2.setId(Integer.parseInt(fanganid));
		jieCiFangAn2.setMingcheng(mingcheng);
		jieCiFangAn2.setXuexiaoid(jieCiFangAn.getXuexiaoid());
		jieCiFangAn2.setZhuangtai(jieCiFangAn.getZhuangtai());
		jieCiFangAn2.setQiyongguo(jieCiFangAn.getQiyongguo());
		int i = jiecifanganService.updateByPrimaryKey(jieCiFangAn2);
		if (i == 0) {
			// out.print("<script>alert('修改失败！');</script>");
			// out.print("<script>location='xiugaijiecifangan?id=" + fanganid +
			// "';</script>");
			return null;
		}
		String jiecidata = request.getParameter("jiecidata");
		String data[] = jiecidata.split(";");
		for (String string : data) {
			String str[] = string.split(",");
			JCSJ jcsj = new JCSJ();
			jcsj.setId(Integer.parseInt(str[0]));
			jcsj.setJieci(Integer.parseInt(str[1]));
			jcsj.setKaishishijian(str[2]);
			jcsj.setJieshushijian(str[3]);
			jcsj.setShijianduan(Integer.parseInt(str[4]));
			jcsj.setJiecifanganid(Integer.parseInt(fanganid));
			int j = jcsjService.updateByPrimaryKey(jcsj);
			if (j == 0) {
				// out.print("<script>alert('修改失败！')</script>");
				// out.print("<script>location='xiugaijiecifangan?id=" +
				// fanganid + "';</script>");
				return null;
			}
		}
		// out.print("<script>alert('修改成功！');</script>");
		// out.print("<script>location='chakanjiecifangan?id=" + fanganid +
		// "';</script>");
		return "success";
	}

	@RequestMapping(value = "chakanjiecifangan") // 查看节次方案
	public ModelAndView chakanjiecifangan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		String fanganid = request.getParameter("id");
		if (!Util.isNumeric(fanganid)) {
			response.sendRedirect("logout");
			return null;
		}
		JieCiFangAn jieCiFangAn = jiecifanganService.selectByPrimaryKey(Integer.parseInt(fanganid));
		if (jieCiFangAn == null) {
			response.sendRedirect("logout");
			return null;
		}
		List<JCSJ> jcsjs = new ArrayList<>();
		jcsjs = jcsjService.getAllByjieCiFangAnID(Integer.parseInt(fanganid));
		mv.addObject("fangan", jieCiFangAn);
		mv.addObject("jcsj", jcsjs);
		mv.setViewName("guanliyuan/chakanjiecifangan");
		return mv;
	}

	@RequestMapping(value = "qiyongfangan") // 启用节次方案
	@ResponseBody
	public String qiyongfangan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		// PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		// 用户
		YongHu user = (YongHu) session.getAttribute("user");
		// 学校id
		int xuexiaoid = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
		String id = request.getParameter("id");
		if (!Util.isNumeric(id)) {
			response.sendRedirect("logout");
			return null;
		}
		JieCiFangAn jieCiFangAn = jiecifanganService.selectByPrimaryKey(Integer.parseInt(id));
		if (jieCiFangAn == null) {
			response.sendRedirect("logout");
			return null;
		}
		List<JieCiFangAn> jieCiFangAns = jiecifanganService.getAllByxueXiaoID(xuexiaoid);
		for (JieCiFangAn jieCiFangAn2 : jieCiFangAns) {
			if (jieCiFangAn2.getZhuangtai() == 1) {
				// out.print("<script>alert('请先将已启用的方案停用！');</script>");
				// out.print("<script>location='jiecishijianguanli';</script>");
				return "qiyong";
			}
		}
		JieCiFangAn jieCiFangAn3 = new JieCiFangAn();
		jieCiFangAn3.setId(Integer.parseInt(id));
		jieCiFangAn3.setZhuangtai(1);
		jieCiFangAn3.setQiyongguo(1);
		int i = jiecifanganService.updatezhuangTaiAndqiYongGuoByPrimaryKey(jieCiFangAn3);
		if (i == 0) {
			// out.print("<script>alert('启用失败！');</script>");
			// out.print("<script>location='jiecishijianguanli';</script>");
			return null;
		}
		// out.print("<script>alert('启用成功！');</script>");
		// out.print("<script>location='jiecishijianguanli';</script>");
		return "success";
	}

	@RequestMapping(value = "tingyongfangan") // 停用节次方案
	@ResponseBody
	public String tingyongfangan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		// PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		if (!Util.isNumeric(id)) {
			response.sendRedirect("logout");
			return null;
		}
		JieCiFangAn jieCiFangAn = jiecifanganService.selectByPrimaryKey(Integer.parseInt(id));
		if (jieCiFangAn == null) {
			response.sendRedirect("logout");
			return null;
		}
		if (jieCiFangAn.getZhuangtai() == 0) {
			// out.print("<script>alert('此方案已被停用！');</script>");
			// out.print("<script>location='jiecishijianguanli';</script>");
			return "tingyong";
		}
		JieCiFangAn jieCiFangAn2 = new JieCiFangAn();
		jieCiFangAn2.setId(Integer.parseInt(id));
		jieCiFangAn2.setZhuangtai(0);
		jieCiFangAn2.setQiyongguo(1);
		int i = jiecifanganService.updatezhuangTaiAndqiYongGuoByPrimaryKey(jieCiFangAn2);
		if (i == 0) {
			// out.print("<script>alert('停用失败！');</script>");
			// out.print("<script>location='jiecishijianguanli';</script>");
			return null;
		}
		// out.print("<script>alert('停用成功！');</script>");
		// out.print("<script>location='jiecishijianguanli';</script>");
		return "success";
	}

	@RequestMapping(value = "deljiecifangan") // 删除节次方案
	@ResponseBody
	public String deljiecifangan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		// PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		if (!Util.isNumeric(id)) {
			response.sendRedirect("logout");
			return null;
		}
		JieCiFangAn jieCiFangAn = jiecifanganService.selectByPrimaryKey(Integer.parseInt(id));
		if (jieCiFangAn == null) {
			response.sendRedirect("logout");
			return null;
		}
		if (jieCiFangAn.getZhuangtai() == 1) {
			// out.print("<script>alert('要先停用此方案才能删除！');</script>");
			// out.print("<script>location='jiecishijianguanli';</script>");
			return "tingyong";
		}
		List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(Integer.parseInt(id));
		for (JCSJ jcsj : jcsjs) {
			int i = jcsjService.deleteByPrimaryKey(jcsj.getId());
			if (i == 0) {
				// out.print("<script>alert('删除失败！');</script>");
				// out.print("<script>location='jiecishijianguanli';</script>");
				return null;
			}
		}
		int j = jiecifanganService.deleteByPrimaryKey(Integer.parseInt(id));
		if (j == 0) {
			// out.print("<script>alert('删除失败！');</script>");
			// out.print("<script>location='jiecishijianguanli';</script>");
			return null;
		}
		// out.print("<script>alert('删除成功！');</script>");
		// out.print("<script>location='jiecishijianguanli';</script>");
		return "success";
	}

	@RequestMapping(value = "xueqiguanli") // 学期管理
	public ModelAndView xueqiguanli(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			YongHu user = (YongHu) session.getAttribute("user");
			int xuexiaoid = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
			List<XueQi> xueQis = xueqiService.getXueQiByXueXiaoID(xuexiaoid);
			for (int i = 0; i < xueQis.size(); i++) {
				Integer nianfen = nianFenService.selectByPrimaryKey(xueQis.get(i).getNianfenid()).getRuxuenianfen();
				xueQis.get(i).setXuenian(nianfen + "~" + (nianfen + 1));
			}
			mv.addObject("xueqi", xueQis);
			mv.setViewName("guanliyuan/xueqiliebiao");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addxueqi") // 新增学期
	public ModelAndView addxueqi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		YongHu user = (YongHu) session.getAttribute("user");
		int xuexiaoid = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
		// List<String> xueNians = xueqiService.getXueNian(xuexiaoid);
		List<String> xueNians = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String ruxuenianfen = sdf.format(new Date());
		List<NianFen> nianfens = nianFenService.findByNianFen(ruxuenianfen);
		for (NianFen nianFen : nianfens) {
			xueNians.add(nianFen.getRuxuenianfen() + "~" + (nianFen.getRuxuenianfen() + 1));
		}
		mv.addObject("xuenians", xueNians);
		mv.setViewName("guanliyuan/addxueqi");
		return mv;
	}

	@RequestMapping(value = "save_addxueqi") // 保存新增学期
	@ResponseBody
	public String save_addxueqi(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		// PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		// 用户
		YongHu user = (YongHu) session.getAttribute("user");
		// 学校id
		int xuexiaoid = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
		// 获取前端数据
		String xuenian = request.getParameter("xuenian");
		String xueqi = request.getParameter("xueqi");
		String kaishiriqi = request.getParameter("kaishiriqi");
		String jieshuriqi = request.getParameter("jieshuriqi");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		XueQi xueQi = new XueQi();
		List<NianFen> nianFens = nianFenService.getNianFen();
		for (int i = 0; i < nianFens.size(); i++) {
			if (nianFens.get(i).getRuxuenianfen().toString().equals(xuenian.substring(0, 4))) {
				xueQi.setNianfenid(nianFens.get(i).getRuxuenianfenid());
				break;
			} else {
				continue;
			}
		}
		if (xueqi.equals("1")) {
			xueQi.setMingcheng("第一学期");
		}
		if (xueqi.equals("2")) {
			xueQi.setMingcheng("第二学期");
		}
		if (xueqi.equals("3")) {
			xueQi.setMingcheng("第三学期");
		}
		xueQi.setXueqi(Integer.parseInt(xueqi));
		xueQi.setKaishiriqi(simpleDateFormat.parse(kaishiriqi));
		xueQi.setJieshuriqi(simpleDateFormat.parse(jieshuriqi));
		xueQi.setXuexiaoid(xuexiaoid);
		int i = xueqiService.insert(xueQi);
		if (i != 0) {
			// out.print("<script>alert('新增成功！');</script>");
			// out.print("<script>location='xueqiguanli';</script>");
			return "success";
		} else {
			// out.print("<script>alert('新增失败！');</script>");
			// out.print("<script>location='xueqiguanli';</script>");
		}
		return null;
	}

	@RequestMapping(value = "xiugaixueqi") // 修改学期
	public ModelAndView xiugaixueqi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		String xueqiid = request.getParameter("id");
		if (!Util.isNumeric(xueqiid)) {
			response.sendRedirect("logout");
			return null;
		}
		XueQi xueQi = xueqiService.selectByID(Integer.parseInt(xueqiid));
		if (xueQi == null) {
			response.sendRedirect("logout");
			return null;
		}
		HttpSession session = request.getSession();
		// 用户
		YongHu user = (YongHu) session.getAttribute("user");
		// 学校id
		int xuexiaoid = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
		if (xueQi.getXuexiaoid() != xuexiaoid) {
			response.sendRedirect("logout");
			return null;
		}
		int nianfen = nianFenService.selectByPrimaryKey(xueQi.getNianfenid()).getRuxuenianfen();
		xueQi.setXuenian(nianfen + "~" + (nianfen + 1));
		mv.addObject("xueqi", xueQi);
		mv.setViewName("guanliyuan/xiugaixueqi");
		return mv;
	}

	@RequestMapping(value = "save_xiugaixueqi") // 保存修改学期
	@ResponseBody
	public String save_xiugaixueqi(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		// PrintWriter out = response.getWriter();
		// 获取前端数据
		String kaishiriqi = request.getParameter("kaishiriqi");
		String jieshuriqi = request.getParameter("jieshuriqi");
		String xueqiid = request.getParameter("id");
		if (!Util.isNumeric(xueqiid)) {
			response.sendRedirect("logout");
			return null;
		}
		XueQi xueQi = xueqiService.selectByID(Integer.parseInt(xueqiid));
		if (xueQi == null) {
			response.sendRedirect("logout");
			return null;
		}
		HttpSession session = request.getSession();
		// 用户
		YongHu user = (YongHu) session.getAttribute("user");
		// 学校id
		int xuexiaoid = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
		if (xueQi.getXuexiaoid() != xuexiaoid) {
			response.sendRedirect("logout");
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		XueQi xueQi2 = new XueQi();
		xueQi2.setXueqiid(Integer.parseInt(xueqiid));
		xueQi2.setKaishiriqi(simpleDateFormat.parse(kaishiriqi));
		xueQi2.setJieshuriqi(simpleDateFormat.parse(jieshuriqi));
		int i = xueqiService.updateByPrimary(xueQi2);
		if (i != 0) {
			// out.print("<script>alert('修改成功！');</script>");
			// out.print("<script>location='xueqiguanli';</script>");
			return "success";
		} else {
			// out.print("<script>alert('修改失败！');</script>");
			// out.print("<script>location='xueqiguanli';</script>");
		}
		return null;
	}

	@RequestMapping(value = "delxueqi") // 删除学期
	public ModelAndView delxueqi(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		// 获取前端数据
		String xueqiid = request.getParameter("id");
		if (!Util.isNumeric(xueqiid)) {
			response.sendRedirect("logout");
			return null;
		}
		XueQi xueQi = xueqiService.selectByID(Integer.parseInt(xueqiid));
		if (xueQi == null) {
			response.sendRedirect("logout");
			return null;
		}
		HttpSession session = request.getSession();
		// 用户
		YongHu user = (YongHu) session.getAttribute("user");
		// 学校id
		int xuexiaoid = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
		if (xueQi.getXuexiaoid() != xuexiaoid) {
			response.sendRedirect("logout");
			return null;
		}
		int i = xueqiService.deleteByPrimaryKey(Integer.parseInt(xueqiid));
		if (i != 0) {
			out.print("<script>alert('删除成功！');</script>");
			out.print("<script>location='xueqiguanli';</script>");
		} else {
			out.print("<script>alert('删除失败！');</script>");
			out.print("<script>location='xueqiguanli';</script>");
		}
		return null;
	}

	@RequestMapping(value = "wodebeiwanglu_gly") // 我的备忘录
	public ModelAndView wodebeiwanglu_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String qufen = request.getParameter("qufen");
			String id = request.getParameter("id");
			List<BeiWL> beiWLs = new ArrayList<>();
			if (id != null && !(id.equals(""))) {
				beiWLs.add(beiwlService.selectByPrimaryKey(Integer.parseInt(id)));
			} else {

				beiWLs = beiwlService.getAllByuserID(user.getYonghuid());
			}
			mv.addObject("qufen", qufen);
			mv.addObject("beiwl", beiWLs);
			mv.setViewName("guanliyuan/wodebeiwanglu_gly");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addbeiwang_gly") // 新增备忘录
	public ModelAndView addbeiwang_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			mv.setViewName("guanliyuan/addbeiwanglu_gly");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savebeiwang_gly") // 保存新增备忘录
	@ResponseBody
	public String savebeiwang_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String neirong = request.getParameter("neirong");
			// String didian = request.getParameter("didian");
			String date = request.getParameter("riqi");
			String time = request.getParameter("shijian");
			String shijian = date + " " + time;
			/*
			 * System.out.println(neirong); System.out.println(didian);
			 * System.out.println(shijian);
			 */
			map.put("neirong", neirong);
			// map.put("didian", didian);
			map.put("userid", user.getYonghuid().toString());
			map.put("shijian", shijian);
			map.put("leixing", "0");
			int i = beiwlService.insert(map);
			if (i != 0) {
//				out.print("<script>alert('新增成功！');</script>");
//				out.print("<script>location='wodebeiwanglu_gly';</script>");
				return "success";
			} else {
//				out.print("<script>alert('新增失败！');</script>");
//				out.print("<script>location='wodebeiwanglu_gly';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "xiugaibeiwanglu_gly") // 修改备忘录
	public ModelAndView xiugaibeiwanglu_gly(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String guanliyuanid = user.getYonghuid().toString();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if ((beiWL != null) && (beiWL.getUserid().equals(guanliyuanid))) {
				String datetime = beiWL.getShijian();
				String date = datetime.substring(0, datetime.indexOf(" "));
				String time = datetime.substring(datetime.indexOf(" ") + 1, datetime.length());
				// System.out.println(datetime);
				// System.out.println(date);
				// System.out.println(time);
				mv.addObject("beiwanglu", beiWL);
				mv.addObject("date", date);
				mv.addObject("time", time);
				mv.addObject("qufen", qufen);
				mv.setViewName("guanliyuan/xiugaibeiwanglu_gly");
				return mv;
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "saveupdatebeiwanglu_gly") // 保存修改备忘录
	@ResponseBody
	public String saveupdatebeiwanglu_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String guanliyuanid = user.getYonghuid().toString();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			BeiWL beiwanglu = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if (beiwanglu != null && beiwanglu.getUserid().equals(guanliyuanid)) {
				String neirong = request.getParameter("neirong");
				// String didian = request.getParameter("didian");
				String date = request.getParameter("riqi");
				String time = request.getParameter("shijian");
				String shijian = date + " " + time;

				beiwanglu.setShijian(shijian);
				beiwanglu.setNeirong(neirong);
				int i = beiwlService.updateByPrimaryKey(beiwanglu);
				if (i != 0) {
//					if (qufen.equals("1")) {
//						out.print("<script>alert('修改成功！');</script>");
//						out.print("<script>location='wodericheng_gly';</script>");
//					} else {
//						out.print("<script>alert('修改成功！');</script>");
//						out.print("<script>location='wodebeiwanglu_gly';</script>");
//					}
					return "success";
				} else {
//					if (qufen.equals("1")) {
//						out.print("<script>alert('修改失败！');</script>");
//						out.print("<script>location='wodericheng_gly';</script>");
//					} else {
//						out.print("<script>alert('修改失败！');</script>");
//						out.print("<script>location='wodebeiwanglu_gly';</script>");
//					}
				}
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "delbeiwl_gly") // 删除备忘录
	@ResponseBody
	public String delbeiwl_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
//		PrintWriter out = response.getWriter();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String guanliyuanid = user.getYonghuid().toString();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if (beiWL != null && beiWL.getUserid().equals(guanliyuanid)) {
				int i = beiwlService.deleteByPrimaryKey(Integer.parseInt(id));
				if (i != 0) {
					response.setContentType("text/html; charset=utf-8");
//					if (qufen.equals("1")) {
//						out.print("<script>alert('删除成功!');</script>");
//						out.print("<script>location='wodericheng_gly';</script>");
//					} else {
//						out.print("<script>alert('删除成功!');</script>");
//						out.print("<script>location='wodebeiwanglu_gly';</script>");
//					}
					return "success";
				} else {
					response.setContentType("text/html; charset=utf-8");
//					if (qufen.equals("1")) {
//						out.print("<script>alert('删除失败！');</script>");
//						out.print("<script>location='wodericheng_gly';</script>");
//					} else {
//						out.print("<script>alert('删除失败!');</script>");
//						out.print("<script>location='wodebeiwanglu_gly';</script>");
//					}
				}
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping("wodericheng_gly")
	public ModelAndView wodericheng_laoshi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			mv.addObject("t", new Date().getTime());
			mv.setViewName("guanliyuan/wodericheng");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping("chaxunrichengbydate_gly")
	@ResponseBody
	public JSONObject chaxunrichengbydate(HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		JSONObject json = new JSONObject();
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			Map<String, String> paramMap = new HashMap<>();
			YongHu user = (YongHu) session.getAttribute("user");
			String riqi = request.getParameter("riqi");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			long time = format.parse(riqi).getTime();
			time = time + 24 * 60 * 60 * 1000;
			Date date = new Date(time);
			String nextriqi = format.format(date);
			
			List<HuoDong> huodongs = new ArrayList<>();
			List<KeCheng> kechengs = new ArrayList<>();
			List<BeiWL> beiwanglus = new ArrayList<>();
			List<TiXing> tixings = new ArrayList<>();
			List<YiJianXiang> yijianxiangs = new ArrayList<>();

			if (user != null) {
				paramMap.put("riqi", riqi);
				paramMap.put("renid", user.getYonghuid().toString());
				paramMap.put("renleixing", "3");
				huodongs = huodongService.getAllByRiQiAndRenIDAndRenLeiXing(paramMap);
				for (HuoDong huoDong : huodongs) {
					paramMap.put("huodongid", huoDong.getHuodongid().toString());
					List<Map<String, Object>> canyuren = huodongService
							.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
					List<Map<String, Object>> jujueren = huodongService
							.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
					int i = 0;
					if (jujueren.size() > 0 && jujueren != null) {
						for (Map<String, Object> map : jujueren) {
							if (map.get("juJueRenID").toString().equals(user.getYonghuid().toString())
									&& map.get("leiXing").toString().equals("3")) {
								huoDong.setZhuangtai(2);
								i = 1;
								break;
							}
						}
					}
					if (canyuren.size() > 0 && canyuren != null) {
						for (Map<String, Object> map : canyuren) {
							if (map.get("canYuRenID").toString().equals(user.getYonghuid().toString())
									&& map.get("leiXing").toString().equals("3")) {
								huoDong.setZhuangtai(1);
								i = 1;
								break;
							}
						}
					}
					if (huoDong.getTianjiarenid() == user.getYonghuid() && huoDong.getTianjiarenleixing() == 3) {
						huoDong.setZhuangtai(1);
						i = 1;
					}
					if (i == 0) {
						huoDong.setZhuangtai(0);
					}
				}

				String xuexiaoid = yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString();
				paramMap.put("xueXiaoID", xuexiaoid);
				XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(paramMap);
				if (xueQi == null) {
					returnMap.put("kechengs", kechengs);
				} else {
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(format.parse(xueQi.getKaishiriqi()));
					Calendar cal2 = Calendar.getInstance();
					cal2.setTime(format.parse(riqi));
					int day1 = cal1.get(Calendar.DAY_OF_YEAR);
					int day2 = cal2.get(Calendar.DAY_OF_YEAR);
					int year1 = cal1.get(Calendar.YEAR);
					int year2 = cal2.get(Calendar.YEAR);
					int timeDistance = 0;
					if (year1 != year2) { // 不同年
						for (int i = year1; i < year2; i++) {
							if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {// 闰年
								timeDistance += 366;
							} else { // 不是闰年
								timeDistance += 365;
							}
							timeDistance += day2 - day1;
						}
					} else {// 同年
						timeDistance = day2 - day1;
					}
					int zhou = timeDistance / 7 + 1;

					Calendar c = Calendar.getInstance();
					c.setTime(format.parse(riqi));
					int zhouci = 0;
					if (c.get(Calendar.DAY_OF_WEEK) == 1) {
						zhouci = 7;
					} else {
						zhouci = c.get(Calendar.DAY_OF_WEEK) - 1;
					}

					// paramMap.put("renkelaoshiid",
					// user.getYonghuid().toString());
					// paramMap.put("shangkeriqi", "%" + riqi + ",%");
					paramMap.put("xueqiid", xueQi.getXueqiid().toString());
					paramMap.put("renkelaoshiid", user.getYonghuid().toString());
					paramMap.put("zhouci", zhouci + "");
					paramMap.put("zhou", zhou + "");
					// List<KeCheng> keChengs =
					// kechengService.getKeChengBycanYuRenAndRiQi(paramMap);
					List<Map<String, Object>> keChengMap = kechengService.getKeChengByRenKeLaoShiID(paramMap);

					for (Map<String, Object> map : keChengMap) {
						if (map.containsKey("shiJianLeiXing")) {
							if (map.get("shiJianLeiXing").toString().equals("1")) {
								if (zhou <= Integer.parseInt(map.get("jieShuZhou").toString())
										&& zhou >= Integer.parseInt(map.get("kaiShiZhou").toString())) {
									KeCheng kecheng = new KeCheng();
									kecheng.setId(Integer.parseInt(map.get("ID").toString()));
									kecheng.setKechengmingcheng(map.get("keChengMingCheng").toString());
									kecheng.setRenkejiaoshi(map.get("renKeJiaoShi").toString());
									kecheng.setKaishizhou(Integer.parseInt(map.get("kaiShiZhou").toString()));
									kecheng.setJieshuzhou(Integer.parseInt(map.get("jieShuZhou").toString()));
									kecheng.setKaishijieci(jcsjService
											.selectByPrimaryKey(Integer.parseInt(map.get("kaiShiJieCi").toString()))
											.getJieci());
									kecheng.setJieshujieci(jcsjService
											.selectByPrimaryKey(Integer.parseInt(map.get("jieShuJieCi").toString()))
											.getJieci());
									JiaoShi js = jiaoshiService
											.selectByPrimaryKey(Integer.parseInt(map.get("jiaoShiID").toString()));
									JiaoXueLou jxl = jiaoxuelouService.selectByPrimaryKey(js.getJiaoxuelouid());
									kecheng.setJiaoshiming(js.getJiaoshiming());
									kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
									kecheng.setXiaoquming(
											xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());

									kechengs.add(kecheng);
								}
							}
							if (map.containsKey("shiJianLeiXing") && map.get("shiJianLeiXing").toString().equals("2")) {
								if (zhou % 2 != 0) {
									if (zhou <= Integer.parseInt(map.get("jieShuZhou").toString())
											&& zhou >= Integer.parseInt(map.get("kaiShiZhou").toString())) {
										KeCheng kecheng = new KeCheng();
										kecheng.setId(Integer.parseInt(map.get("ID").toString()));
										kecheng.setKechengmingcheng(map.get("keChengMingCheng").toString());
										kecheng.setRenkejiaoshi(map.get("renKeJiaoShi").toString());
										kecheng.setKaishizhou(Integer.parseInt(map.get("kaiShiZhou").toString()));
										kecheng.setJieshuzhou(Integer.parseInt(map.get("jieShuZhou").toString()));
										kecheng.setKaishijieci(jcsjService
												.selectByPrimaryKey(Integer.parseInt(map.get("kaiShiJieCi").toString()))
												.getJieci());
										kecheng.setJieshujieci(jcsjService
												.selectByPrimaryKey(Integer.parseInt(map.get("jieShuJieCi").toString()))
												.getJieci());
										JiaoShi js = jiaoshiService
												.selectByPrimaryKey(Integer.parseInt(map.get("jiaoShiID").toString()));
										JiaoXueLou jxl = jiaoxuelouService.selectByPrimaryKey(js.getJiaoxuelouid());
										kecheng.setJiaoshiming(js.getJiaoshiming());
										kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());

										kechengs.add(kecheng);
									}
								}
							}
							if (map.containsKey("shiJianLeiXing") && map.get("shiJianLeiXing").toString().equals("3")) {
								if (zhou % 2 == 0) {
									if (zhou <= Integer.parseInt(map.get("jieShuZhou").toString())
											&& zhou >= Integer.parseInt(map.get("kaiShiZhou").toString())) {
										KeCheng kecheng = new KeCheng();
										kecheng.setId(Integer.parseInt(map.get("ID").toString()));
										kecheng.setKechengmingcheng(map.get("keChengMingCheng").toString());
										kecheng.setRenkejiaoshi(map.get("renKeJiaoShi").toString());
										kecheng.setKaishizhou(Integer.parseInt(map.get("kaiShiZhou").toString()));
										kecheng.setJieshuzhou(Integer.parseInt(map.get("jieShuZhou").toString()));
										kecheng.setKaishijieci(jcsjService
												.selectByPrimaryKey(Integer.parseInt(map.get("kaiShiJieCi").toString()))
												.getJieci());
										kecheng.setJieshujieci(jcsjService
												.selectByPrimaryKey(Integer.parseInt(map.get("jieShuJieCi").toString()))
												.getJieci());
										JiaoShi js = jiaoshiService
												.selectByPrimaryKey(Integer.parseInt(map.get("jiaoShiID").toString()));
										JiaoXueLou jxl = jiaoxuelouService.selectByPrimaryKey(js.getJiaoxuelouid());
										kecheng.setJiaoshiming(js.getJiaoshiming());
										kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());

										kechengs.add(kecheng);
									}
								}
							}
							if (map.containsKey("shiJianLeiXing") && map.get("shiJianLeiXing").toString().equals("4")) {
								if (zhou == Integer.parseInt(map.get("kaiShiZhou").toString())) {
									KeCheng kecheng = new KeCheng();
									kecheng.setId(Integer.parseInt(map.get("ID").toString()));
									kecheng.setKechengmingcheng(map.get("keChengMingCheng").toString());
									kecheng.setRenkejiaoshi(map.get("renKeJiaoShi").toString());
									kecheng.setKaishizhou(Integer.parseInt(map.get("kaiShiZhou").toString()));
									kecheng.setKaishijieci(jcsjService
											.selectByPrimaryKey(Integer.parseInt(map.get("kaiShiJieCi").toString()))
											.getJieci());
									kecheng.setJieshujieci(jcsjService
											.selectByPrimaryKey(Integer.parseInt(map.get("jieShuJieCi").toString()))
											.getJieci());
									JiaoShi js = jiaoshiService
											.selectByPrimaryKey(Integer.parseInt(map.get("jiaoShiID").toString()));
									JiaoXueLou jxl = jiaoxuelouService.selectByPrimaryKey(js.getJiaoxuelouid());
									kecheng.setJiaoshiming(js.getJiaoshiming());
									kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
									kecheng.setXiaoquming(
											xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());

									kechengs.add(kecheng);
								}
							}
							if (map.containsKey("shiJianLeiXing") && map.get("shiJianLeiXing").toString().equals("6")) {
								if (zhou == Integer.parseInt(map.get("kaiShiZhou").toString())) {
									KeCheng kecheng = new KeCheng();
									kecheng.setId(Integer.parseInt(map.get("ID").toString()));
									kecheng.setKechengmingcheng(map.get("keChengMingCheng").toString());
									kecheng.setRenkejiaoshi(map.get("renKeJiaoShi").toString());
									kecheng.setKaishizhou(Integer.parseInt(map.get("kaiShiZhou").toString()));
									kecheng.setKaishijieci(jcsjService
											.selectByPrimaryKey(Integer.parseInt(map.get("kaiShiJieCi").toString()))
											.getJieci());
									kecheng.setJieshujieci(jcsjService
											.selectByPrimaryKey(Integer.parseInt(map.get("jieShuJieCi").toString()))
											.getJieci());
									JiaoShi js = jiaoshiService
											.selectByPrimaryKey(Integer.parseInt(map.get("jiaoShiID").toString()));
									JiaoXueLou jxl = jiaoxuelouService.selectByPrimaryKey(js.getJiaoxuelouid());
									kecheng.setJiaoshiming(js.getJiaoshiming());
									kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
									kecheng.setXiaoquming(
											xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());

									kechengs.add(kecheng);
								}
							}
						}
					}
					/**
					 * for (KeCheng keCheng : keChengs) { String shangkeriqi[] =
					 * keCheng.getShangkeriqi().split(","); for (String string :
					 * shangkeriqi) { String riqi1[] = string.split(";");
					 * KeCheng newkecheng = new KeCheng(); if
					 * (riqi1[0].equals(riqi) && riqi1.length != 1) {
					 * newkecheng.setXiaoqu(riqi1[1]);
					 * newkecheng.setShangkejiaoshi(riqi1[2]);
					 * newkecheng.setKaishijieci(Integer.parseInt(riqi1[3]));
					 * newkecheng.setJieshujieci(Integer.parseInt(riqi1[4]));
					 * int kaishijieci = newkecheng.getKaishijieci(); int
					 * jieshujieci = newkecheng.getJieshujieci(); String
					 * kaishishijian =
					 * jcsjService.getkaiShiShiJianByid(kaishijieci); String
					 * jieshushijian =
					 * jcsjService.getjieShuShiJianByid(jieshujieci);
					 * newkecheng.setKaishishijian(kaishishijian);
					 * newkecheng.setJieshushijian(jieshushijian); String
					 * xiaoquming = xiaoquService
					 * .selectByPrimaryKey(Integer.parseInt(keCheng.getXiaoqu())
					 * ).getMingcheng(); String jiaoshiming = jiaoshiService
					 * .selectByPrimaryKey(Integer.parseInt(keCheng.
					 * getShangkejiaoshi())) .getJiaoshiming();
					 * newkecheng.setXiaoquming(xiaoquming);
					 * newkecheng.setJiaoshiming(jiaoshiming);
					 * newkecheng.setKechengmingcheng(keCheng.
					 * getKechengmingcheng());
					 * newkecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
					 * newkecheng.setCanyuren(keCheng.getCanyuren());
					 * kechengs.add(newkecheng); } } int kaishijieci =
					 * keCheng.getKaishijieci(); int jieshujieci =
					 * keCheng.getJieshujieci(); String kaishishijian =
					 * jcsjService.getkaiShiShiJianByid(kaishijieci); String
					 * jieshushijian =
					 * jcsjService.getjieShuShiJianByid(jieshujieci);
					 * keCheng.setKaishishijian(kaishishijian);
					 * keCheng.setJieshushijian(jieshushijian); String
					 * xiaoquming =
					 * xiaoquService.selectByPrimaryKey(Integer.parseInt(keCheng
					 * .getXiaoqu())) .getMingcheng(); String jiaoshiming =
					 * jiaoshiService
					 * .selectByPrimaryKey(Integer.parseInt(keCheng.
					 * getShangkejiaoshi())).getJiaoshiming();
					 * keCheng.setXiaoquming(xiaoquming);
					 * keCheng.setJiaoshiming(jiaoshiming);
					 * kechengs.add(keCheng); }
					 */
				}

				paramMap.put("kaishi", riqi + " 00:00");
				paramMap.put("jieshu", nextriqi + " 00:00");
				beiwanglus = beiwlService.getBeiWLByRenIDAndRenLeiXingAndRiQi(paramMap);
				// for (BeiWL beiWL : beiwanglus) {
				// YongHu yongHu =
				// yonghuService.selectYongHuByID(Integer.parseInt(beiWL.getUserid()));
				// if (yongHu != null) {
				// beiWL.setUsername(yongHu.getYonghuxingming());
				// } else {
				// XueSheng xueSheng =
				// xueshengService.getUserById(Integer.parseInt(beiWL.getUserid()));
				// beiWL.setUsername(xueSheng.getXingming());
				// }
				// if (beiWL.getHuizhi() != null && beiWL.getHuizhi() == 1) {
				// if (!"".equals(beiWL.getJieshouren()) && null !=
				// beiWL.getJieshouren()) {
				// String jieshourens[] = beiWL.getJieshouren().split(";");
				// for (String jieshourenn : jieshourens) {
				// String jieshou[] = jieshourenn.split(",");
				// if (jieshou[0].equals(user.getYonghuid())) {
				// beiWL.setZhuangtai(jieshou[1]);
				// }
				// }
				// }
				// }
				// }
				paramMap.put("kaishishijian", riqi + " 00:00:00");
				paramMap.put("jieshushijian", nextriqi + " 00:00:00");
				tixings = tixingService.getTiXingByjieShouRenIDAndRiQi(paramMap);
				// yijianxiangs =
				// yiJianXiangService.getYiJianXiangByjieShouRenIDAndRiQi(paramMap);

				returnMap.put("huodongs", huodongs);
				returnMap.put("kechengs", kechengs);
				returnMap.put("beiwanglus", beiwanglus);
				returnMap.put("tixings", tixings);
				returnMap.put("yijianxiangs", yijianxiangs);

			} else {
				returnMap.put("huodongs", huodongs);
				returnMap.put("kechengs", kechengs);
				returnMap.put("beiwanglus", beiwanglus);
				returnMap.put("tixings", tixings);
				returnMap.put("yijianxiangs", yijianxiangs);
			}
			json.put("shuju", returnMap);
		} else {
			response.sendRedirect("login");
			return null;
		}

		return json;
	}

	@RequestMapping("richeng_huodong_gly")
	public ModelAndView riChengHuoDong(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			if (user != null) {
				String id = request.getParameter("id");
				String zhuangtai = request.getParameter("zhuangtai");
				String qufen = request.getParameter("qufen");
				List<HuoDong> huodongs = new ArrayList<>();
				HuoDong huodong = null;
				if (id == null || id == "") {
					huodong = new HuoDong();
					huodongs.add(huodong);
					mv.addObject("huodongs", huodongs);
				} else {
					huodong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
					if (huodong != null) {
						if ("0".equals(zhuangtai)) {
							huodong.setZhuangtai(0);
						}
						if ("1".equals(zhuangtai)) {
							huodong.setZhuangtai(1);
						}
						if ("2".equals(zhuangtai)) {
							huodong.setZhuangtai(2);
						}
						// if (!"".equals(huodong.getCanyuren()) && null !=
						// huodong.getCanyuren()) {
						// String canyurens[] =
						// huodong.getCanyuren().split(";");
						// for (String str : canyurens) {
						// String canyurenid[] = str.split(",");
						// if
						// (huodong.getTianjiarenid().toString().equals(user.getYonghuid().toString())
						// && canyurenid[2].equals("0")) {
						// mv.addObject("xiugai", 1);
						// }
						// }
						// }
						huodongs.add(huodong);
						mv.addObject("huodongs", huodongs);
						mv.addObject("xueshengchuguanliyuanid", user.getYonghuid());
					} else {
						huodongs.add(huodong);
						mv.addObject("huodongs", huodongs);
					}
				}
				mv.addObject("qufen", qufen);
			} else {
				response.sendRedirect("login");
				return null;
			}
		}
		mv.setViewName("guanliyuan/myhuodong_gly");
		return mv;
	}

	/*
	 * @RequestMapping(value = "chakancanyuren_gly") // 查看参与人 public
	 * ModelAndView chakancanyuren_gly(HttpServletRequest request,
	 * HttpServletResponse response) throws IOException { ModelAndView mv = new
	 * ModelAndView(); HttpSession session = request.getSession(); if
	 * (session.getAttribute("user") != null && session.getAttribute("user") !=
	 * "") { YongHu user = (YongHu) session.getAttribute("user"); String
	 * huodongid = request.getParameter("id"); if (!Util.isNumeric(huodongid)) {
	 * response.sendRedirect("logout"); return null; } HuoDong huoDong =
	 * huodongService.selectByPrimaryKey(Integer.parseInt(huodongid)); if
	 * (huoDong == null) { response.sendRedirect("logout"); return null; }
	 * String canyuren = huoDong.getCanyuren(); String canyurens[] =
	 * canyuren.split(";"); String sign = "1," + user.getYonghuid().toString() +
	 * ",0"; String sign1 = "1," + user.getYonghuid().toString() + ",1"; String
	 * sign2 = "1," + user.getYonghuid().toString() + ",2"; int i = 0; for
	 * (String string : canyurens) { if (string.equals(sign) ||
	 * string.equals(sign1) || string.equals(sign2)) { i = 1; } } if (i == 0) {
	 * response.sendRedirect("logout"); return null; } List<Map<String, String>>
	 * maps = new ArrayList<>(); List<Map<String, String>> maps2 = new
	 * ArrayList<>(); for (String string : canyurens) { String canyr[] =
	 * string.split(","); Map<String, String> map = new HashMap<>(); if
	 * (canyr[0].equals("0")) { XueSheng xueSheng =
	 * xueshengService.getUserById(Integer.parseInt(canyr[1])); String
	 * banjimingcheng =
	 * banjiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng(
	 * ); map.put("banjimingcheng", banjimingcheng); map.put("xueshengid",
	 * canyr[1]); map.put("xuehao", xueSheng.getXuehao()); map.put("xingming",
	 * xueSheng.getXingming()); map.put("canyuzhuangtai", canyr[2]); if
	 * (canyr[2].equals("2")) { map.put("liyou", canyr[3]); } maps.add(map); }
	 * if (canyr[0].equals("1")) { YongHu jiaoshi =
	 * yonghuService.selectYongHuByID(Integer.parseInt(canyr[1]));
	 * map.put("jiaoshiid", canyr[1]); map.put("xingming",
	 * jiaoshi.getYonghuming()); map.put("canyuzhuangtai", canyr[2]);
	 * maps2.add(map); } } mv.addObject("huodong", huoDong);
	 * mv.addObject("student", maps); mv.addObject("fudaoyuan", maps2);
	 * mv.setViewName("guanliyuan/chakancanyuren_gly"); return mv; } else {
	 * response.sendRedirect("login"); } return null; }
	 */

	@RequestMapping(value = "myhuodong_gly") // 我的活动
	public ModelAndView myhuodong_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			XueShengChuGuanLiYuan xueShengChuGuanLiYuan = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid());
			Map<String, String> map = new HashMap<>();
			List<HuoDong> huoDongs = new ArrayList<>();
			map.put("renid", xueShengChuGuanLiYuan.getXueshengchuguanliyuanid().toString());
			map.put("renleixing", "3");
			huoDongs = huodongService.getAllByRenIDAndRenLeiXing(map);
			for (HuoDong huoDong : huoDongs) {
				map.put("huodongid", huoDong.getHuodongid().toString());
				List<Map<String, Object>> canyuren = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				List<Map<String, Object>> jujueren = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);

				if (huoDong.getTianjiarenid() == user.getYonghuid()) {
					if (jujueren != null && jujueren.size() > 0) {
						huoDong.setZhuangtai(2);
					} else {
						huoDong.setZhuangtai(1);
					}
				} else {
					int i = 0;
					if (canyuren != null && canyuren.size() > 0) {
						i = 1;
						huoDong.setZhuangtai(1);
					}
					if (jujueren != null && jujueren.size() > 0) {
						i = 1;
						huoDong.setZhuangtai(2);
					}
					if (i == 0) {
						huoDong.setZhuangtai(0);
					}
				}
			}
			int count = huoDongs.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (count % pageSize == 0) {
				pages = count / pageSize;
			}
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<HuoDong> huoDongs2 = new ArrayList<>();
				if (count < 10) {

					for (int i = 0; i < count; i++) {
						huoDongs2.add(huoDongs.get(i));
					}
					mv.addObject("huodongs", huoDongs2);
				} else {
					for (int i = 0; i < 10; i++) {
						huoDongs2.add(huoDongs.get(i));
					}
					mv.addObject("huodongs", huoDongs2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<HuoDong> huoDongs2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								huoDongs2.add(huoDongs.get(i));
							}
							mv.addObject("huodongs", huoDongs2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								huoDongs2.add(huoDongs.get(i));
							}
							mv.addObject("huodongs", huoDongs2);
						}
					} else if (page == pages) {
						List<HuoDong> huoDongs2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							huoDongs2.add(huoDongs.get(i));
						}
						mv.addObject("huodongs", huoDongs2);
					} else {
						response.sendRedirect("logout");
						return null;
					}
				} else {
					response.sendRedirect("logout");
					return null;
				}
			}
			mv.addObject("xueshengchuguanliyuanid", user.getYonghuid());
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("guanliyuan/myhuodong_gly");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addhuodong_gly") // 新增活动
	public ModelAndView addhuodong_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			// String banjiids =
			// fudaoyuanService.getByguanliyuanid(user.getYonghuid()).getBanjiid();
			// String banjiid[] = banjiids.split(",");
			// List<Map<String, String>> xueShengs = new ArrayList<>();
			// List<Map<String, String>> banJis = new ArrayList<>();
			// for (String string : banjiid) {
			// //班级
			// BanJi banJi =
			// banjiService.selectByPrimaryKey(Integer.parseInt(string));
			// Map<String, String> map2 = new HashMap<>();
			// map2.put("banjiid", banJi.getBanjiid().toString());
			// map2.put("banjimingcheng", banJi.getBanjimingcheng());
			// banJis.add(map2);
			// //学生
			// List<XueSheng> xueShengs2 =
			// xueshengService.getAllByBanJiID(Integer.parseInt(string));
			// for (XueSheng xueSheng : xueShengs2) {
			// Map<String, String> map = new HashMap<>();
			// String banji =
			// banjiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng();
			// map.put("banjiid", xueSheng.getBanjiid().toString());
			// map.put("banji", banji);
			// map.put("xueshengid", xueSheng.getXueshengid().toString());
			// map.put("xuehao", xueSheng.getXuehao());
			// map.put("xingming", xueSheng.getXingming());
			// xueShengs.add(map);
			// }
			// }
			// mv.addObject("xuesheng", xueShengs);
			// mv.addObject("banji", banJis);
			mv.setViewName("guanliyuan/addhuodong_gly");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savehuodong_gly") // 保存新增活动
	@ResponseBody
	public String savehuodong_gly(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			// 获取学校id
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String mingcheng = request.getParameter("mingcheng");
			String didian = request.getParameter("didian");
			String beizhu = request.getParameter("beizhu");
			// String canyuren = request.getParameter("canyuren");
			String riqi = request.getParameter("riqi");
			String kaishishijian = request.getParameter("kaishishijian");
			String jieshushijian = request.getParameter("jieshushijian");
			// if (canyuren.equals("")) {
			// canyuren = "1," + user.getYonghuid().toString() + ",1;";
			// } else {
			// // 把参与人的类型加上，此处全为0——学生
			// String canyurens[] = canyuren.split(";");
			// String newcanyuren = "";
			// for (String string : canyurens) {
			// newcanyuren += "0," + string + ";";
			// }
			// canyuren = "1," + user.getYonghuid().toString() + ",1;" +
			// newcanyuren;
			// }

			map.put("huodongmingcheng", mingcheng);
			map.put("didian", didian);
			map.put("shuoming", beizhu);
			map.put("tianjiaren", user.getYonghuid().toString());
			map.put("tianjiarenleixing", "3");// 添加人是管理员
			map.put("riqi", riqi);
			map.put("kaishishijian", kaishishijian);
			map.put("jieshushijian", jieshushijian);
			map.put("leixing", "2");
			map.put("tianjiarenid", user.getYonghuid().toString());
			int i = huodongService.insert(map);
			if (i != 0) {
				// 发送激光消息
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("新增活动");
				xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "您添加了活动#" + mingcheng + "#");
				xiaoXiFaSong.setShuJuId(Integer.parseInt(map.get("huodongid")));
				xiaoXiFaSong.setShuJuLeiXing(2);
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(xueXiaoID);
				// 发送提醒消息
				TiXing tiXing = new TiXing();
				Date date = new Date();
				tiXing.setNeirong(user.getYonghuxingming() + "邀请你参加活动#" + mingcheng + "#");
				tiXing.setShijian(date);
				tiXing.setZhuangtai(0);
				tiXing.setShujuid(Integer.parseInt(map.get("huodongid")));
				tiXing.setType(2);
				tiXing.setJieshourenid(user.getYonghuid());
				tixingService.insert(tiXing);
				xiaoXiFaSong.setFaSongMuBiao(user.getYonghuid().toString());
				jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
//				out.print("<script>alert('新增成功！');</script>");
//				out.print("<script>location='wodericheng_gly';</script>");
				return "success";
			} else {
//				out.print("<script>alert('新增失败！');</script>");
//				out.print("<script>location='wodericheng_gly';</script>");
			}

		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "xiugaihuodong_gly") // 修改活动
	public ModelAndView xiugaihuodong_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String huodongid = request.getParameter("id");
			if (!Util.isNumeric(huodongid)) {
				response.sendRedirect("logout");
				return null;
			}
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(huodongid));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			mv.addObject("huodong", huoDong);
			// mv.addObject("canyuren", canyr);
			mv.setViewName("guanliyuan/xiugaihuodong_gly");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "saveupdatehuodong_gly") // 保存修改活动
	@ResponseBody
	public String saveupdatehuodong_gly(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			// 获取学校id
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			String huodongid = request.getParameter("id");
			if (!Util.isNumeric(huodongid)) {
				response.sendRedirect("logout");
				return null;
			}
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(huodongid));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
//			PrintWriter out = response.getWriter();
			String mingcheng = request.getParameter("mingcheng");
			String didian = request.getParameter("didian");
			String shuoming = request.getParameter("shuoming");
			// String canyuren2 = request.getParameter("canyuren");
			String riqi = request.getParameter("riqi");
			String kaishishijian = request.getParameter("kaishishijian");
			String jieshushijian = request.getParameter("jieshushijian");
			HuoDong huoDong2 = new HuoDong();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			huoDong2.setHuodongid(Integer.parseInt(huodongid));
			huoDong2.setHuodongmingcheng(mingcheng);
			huoDong2.setDidian(didian);
			huoDong2.setRiqi(df.parse(riqi));
			// huoDong2.setCanyuren(canyuren2);
			huoDong2.setKaishishijian(kaishishijian);
			huoDong2.setJieshushijian(jieshushijian);
			huoDong2.setTianjiarenid(user.getYonghuid());
			huoDong2.setLeixing(huoDong.getLeixing());
			huoDong2.setTianjiarenleixing(3);// 管理员
			huoDong2.setShuoming(shuoming);
			int j = huodongService.updateByPrimaryKey(huoDong2);
			if (j != 0) {
				// 发送激光消息
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("修改活动");
				xiaoXiFaSong
						.setXiaoXiNeiRong(user.getYonghuxingming() + "修改了活动#" + huoDong.getHuodongmingcheng() + "#");
				xiaoXiFaSong.setShuJuId(Integer.parseInt(huodongid));
				xiaoXiFaSong.setShuJuLeiXing(2);
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(xueXiaoID);
				// 发送提醒消息
				TiXing tiXing = new TiXing();
				Date date = new Date();
				tiXing.setNeirong(user.getYonghuxingming() + "修改了活动#" + huoDong.getHuodongmingcheng() + "#");
				tiXing.setShijian(date);
				tiXing.setZhuangtai(0);
				tiXing.setShujuid(Integer.parseInt(huodongid));
				tiXing.setType(2);
				tiXing.setJieshourenid(user.getYonghuid());
				tixingService.insert(tiXing);
				xiaoXiFaSong.setFaSongMuBiao(user.getYonghuid().toString());
				jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
//				out.print("<script>alert('修改成功！');</script>");
//				out.print("<script>location='wodericheng_gly';</script>");
				return "success";
			} else {
//				out.print("<script>alert('修改失败！');</script>");
//				out.print("<script>location='wodericheng_gly';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "delhuodong_gly") // 拒绝活动——将参与状态改为2，参与人还存在
	@ResponseBody
	public String delhuodong_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
//		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		Map<String, String> map = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			YongHu user = (YongHu) session.getAttribute("user");
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			int sign = 0;
			map.put("huodongid", huoDong.getHuodongid().toString());
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", huoDong.getTianjiarenleixing().toString());
			List<Map<String, Object>> canYuRens = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
			if (canYuRens != null && canYuRens.size() != 0) {
				try {
					huodongService.delete_huodongcanyuren(map);
					huodongService.insert_huodongjujueren(map);
					sign = 1;
				} catch (Exception e) {
					e.printStackTrace();
					sign = 0;
				}
			} else {
				try {
					huodongService.insert_huodongjujueren(map);
					sign = 1;
				} catch (Exception e) {
					e.printStackTrace();
					sign = 0;
				}
			}
			if (sign == 1) {
//				out.print("<script>alert('拒绝成功！');</script>");
//				out.print("<script>location='wodericheng_gly';</script>");
				return "success";
			} else {
//				out.print("<script>alert('拒绝失败！');</script>");
//				out.print("<script>location='myhuodong_gly';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}


	@RequestMapping(value = "newhuodong_gly") // 待确认活动
	public ModelAndView newhuodong_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, String> map = new HashMap<>();
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "3");
			List<HuoDong> huodongs = huodongService.getAllByRenIDAndRenLeiXing(map);// 查询用户的全部活动
			List<HuoDong> huoDongs = new ArrayList<>();
			for (HuoDong huoDong : huodongs) {
				map.put("huodongid", huoDong.getHuodongid().toString());
				List<Map<String, Object>> juJueRen = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				String tianjiarenid = huoDong.getTianjiarenid().toString();
				if (tianjiarenid.equals(user.getYonghuid().toString())) {
					if (juJueRen != null && juJueRen.size() > 0) {
						huoDong.setZhuangtai(2);
						huoDongs.add(huoDong);
					}
				} else {// 未参加，也未拒绝，待确认
					if (juJueRen.equals("") && juJueRen.size() <= 0) {
						List<Map<String, Object>> canYuRen = huodongService
								.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
						if (canYuRen == null || canYuRen.size() <= 0) {
							YongHu tianjiaren = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
							String faqiren = "";
							if (tianjiaren == null) {
								faqiren = xueshengService.getUserById(Integer.parseInt(tianjiarenid)).getXingming();
							} else {
								faqiren = tianjiaren.getYonghuxingming();
							}
							huoDong.setFaqiren(faqiren);
							huoDong.setZhuangtai(0);
							huoDongs.add(huoDong);
						} else {
							break;
						}
					} else {
						huoDong.setZhuangtai(2);
						huoDongs.add(huoDong);
					}
				}
			}
			mv.addObject("huodong", huoDongs);
			mv.setViewName("guanliyuan/newhuodong_gly");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "confirmhuodong_gly") // 确认参加活动——将参与状态改为1
	@ResponseBody
	public String confirmhuodong_gly(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<>();
		if (Util.checkSession(request)) {
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			YongHu user = (YongHu) session.getAttribute("user");
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			int sign = 0;
			map.put("huodongid", huoDong.getHuodongid().toString());
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", huoDong.getTianjiarenleixing().toString());
			List<Map<String, Object>> juJuRens = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);
			if (juJuRens != null && juJuRens.size() != 0) {
				try {
					huodongService.delete_huodongjujueren(map);
					huodongService.insert_huodongcanyuren(map);
					sign = 1;
				} catch (Exception e) {
					e.printStackTrace();
					sign = 0;
				}
			} else {
				try {
					huodongService.insert_huodongcanyuren(map);
					sign = 1;
				} catch (Exception e) {
					e.printStackTrace();
					sign = 0;
				}
			}
			if (sign == 1) {
//				response.setContentType("text/html; charset=utf-8");
//				out.print("<script>alert('参与成功！');</script>");
//				out.print("<script>location='newhuodong_gly';</script>");
				return "success";
			} else {
//				response.setContentType("text/html; charset=utf-8");
//				out.print("<script>alert('参与失败！');</script>");
//				out.print("<script>location='newhuodong_gly';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "canyuhuodong_gly") // 确认参加活动——将参与状态改为1
	@ResponseBody
	public String canyuhuodong_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		YongHu user = yonghuService.selectYongHuByID((Integer) session.getAttribute("xuexiaoid"));
		Map<String, String> map = new HashMap<>();
		if (Util.checkSession(request)) {
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			int sign = 0;
			map.put("huodongid", id);
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "3");
			sign = huodongService.insert_huodongcanyuren(map);
			if (sign == 1) {
//				out.print("<script>alert('参与成功！');</script>");
//				out.print("<script>location='wodericheng_gly';</script>");
				return "success";
			} else {
//				out.print("<script>alert('参与成功！');</script>");
//				out.print("<script>location='myhuodong_gly';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "cleanhuodong_gly") // 彻底清除活动,将参与人剔除，活动还存在
	@ResponseBody
	public String cleanhuodong_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
//		PrintWriter out = response.getWriter();
		Map<String, String> map = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			String canyuren = huoDong.getCanyuren();
			if (canyuren.equals("")) {
				response.sendRedirect("logout");
				return null;
			}
			map.put("huodongid", huoDong.getHuodongid().toString());
			int sign = 0;
			try {
				if (huoDong.getLeixing() == 1) {
					huodongService.delete_huodongbanji(map);
				}
				if (huoDong.getLeixing() == 2) {
					huodongService.delete_huodongren(map);
				}
				sign = 1;
			} catch (Exception e) {
				e.printStackTrace();
				sign = 0;
			}
			if (sign == 1) {
//				out.print("<script>alert('删除成功！');</script>");
//				out.print("<script>location='newhuodong_gly';</script>");
				return "success";
			} else {
//				out.print("<script>alert('删除失败！');</script>");
//				out.print("<script>location='newhuodong_gly';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

}
