package com.web.controller.web.jiaoshi;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.annotation.LoginStatusAnnotation;
import com.web.model.App_FenZu;
import com.web.model.App_KaoPingJieGuo;
import com.web.model.App_KeChengKaoPing;
import com.web.model.App_XueXiZu;
import com.web.model.BanJi;
import com.web.model.JiaoShi;
import com.web.model.JiaoXueLou;
import com.web.model.KeCheng;
import com.web.model.XiaoQu;
import com.web.model.XueQi;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.service.App_FenZuService;
import com.web.service.App_KaoPingChouQianService;
import com.web.service.App_KaoPingJieGuoService;
import com.web.service.App_KeChengKaoPingService;
import com.web.service.App_XueXiZuService;
import com.web.service.BanJiService;
import com.web.service.JiaoShiService;
import com.web.service.JiaoXueLouService;
import com.web.service.KeChengService;
import com.web.service.NianFenService;
import com.web.service.XiaoQuService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.service.YuanXiService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
@LoginStatusAnnotation(status = "jiaoshi")
public class ShiJianKeKaoPingController {
	@Autowired
	private KeChengService kechengService;
	@Autowired
	private App_KeChengKaoPingService app_KeChengKaoPingService;
	@Autowired
	private App_XueXiZuService app_XueXiZuService;
	@Autowired
	private XueShengService xueShengService;
	@Autowired
	private App_FenZuService app_FenZuService;
	@Autowired
	private BanJiService banJiService;
	@Autowired
	private YuanXiService yuanxiService;
	@Autowired
	private XueQiService xueqiService;
	@Autowired
	private NianFenService nianfenService;
	@Autowired
	private App_KaoPingJieGuoService app_KaoPingJieGuoService;

	@RequestMapping(value = "getshijianke") // 得到实践课程
	public ModelAndView getshijianke(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		List<Map<String, Object>> keChengs2 = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> map = new HashMap<>();
		int yuanxiid = user.getYuanxiid();
		String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
		map.put("riqi", riqi.format(date));
		map.put("xueXiaoID", xueXiaoID);
		XueQi xueQi = new XueQi();
		xueQi = xueqiService.getByxueXiaoIDandriQi(map);
		if (xueQi == null) {
			List<XueQi> xueqis = xueqiService.getNewerXueQiByXueQi(map);
			if (xueqis != null && xueqis.size() > 0) {
				xueQi = xueqis.get(0);
			}
		}
		int nf = nianfenService.selectByPrimaryKey(xueQi.getNianfenid()).getRuxuenianfen();
		String xuenian = nf + "~" + (nf + 1);
		xueQi.setXuenian(xuenian);
		String renkelaoshiid = user.getYonghuid().toString();
		map.put("xueqiid", xueQi.getXueqiid().toString());
		map.put("renkelaoshiid", renkelaoshiid);
		List<Map<String, Object>> keChengs = kechengService.getKeChengByLaoShiID(map);
		for (int i = 0; i < keChengs.size(); i++) {
			List<Map<String, Object>> shiJianKeCheng = kechengService
					.selectShiJianKe((Integer) keChengs.get(i).get("ID"));
			if (shiJianKeCheng != null && shiJianKeCheng.size() != 0) {
				for (int j = 0; j < shiJianKeCheng.size(); j++) {
					keChengs.get(i).put("shijiankechengid", (Integer) shiJianKeCheng.get(j).get("ID"));
					keChengs.get(i).put("xiaozurongliang", shiJianKeCheng.get(j).get("xiaoZuRongLiang"));
					keChengs2.add(keChengs.get(i));
				}
			}
		}
		int count = keChengs2.size();
		int pageSize = 10;
		int page = 1;
		int pages = (count / pageSize) + 1;
		if (count % pageSize == 0) {
			pages = (count / pageSize);
		}
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			List<Map<String, Object>> keChengs3 = new ArrayList<>();
			if (count < 10) {
				for (int i = 0; i < count; i++) {
					keChengs3.add(keChengs2.get(i));
				}
				mView.addObject("kechengs", keChengs3);
			} else {
				for (int i = 0; i < 10; i++) {
					keChengs3.add(keChengs2.get(i));
				}
				mView.addObject("kechengs", keChengs3);
			}
		} else {
			if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<Map<String, Object>> keChengs3 = new ArrayList<>();
					if (count < 10) {
						for (int i = (page - 1) * 10; i < count; i++) {
							keChengs3.add(keChengs2.get(i));
						}
						mView.addObject("kechengs", keChengs3);
					} else {
						for (int i = (page - 1) * 10; i < (page * 10); i++) {
							keChengs3.add(keChengs2.get(i));
						}
						mView.addObject("kechengs", keChengs3);
					}
				} else if (page == pages) {
					List<Map<String, Object>> keChengs3 = new ArrayList<>();
					for (int i = (page - 1) * 10; i < count; i++) {
						keChengs3.add(keChengs2.get(i));
					}
					mView.addObject("kechengs", keChengs3);
				} else {
					try {
						response.sendRedirect("logout");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			} else {
				try {
					response.sendRedirect("logout");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}
		mView.addObject("page", page);
		mView.addObject("pages", pages);
		mView.setViewName("jiaoshi/shijianke");
		return mView;
	}

	@RequestMapping(value = "addshijianke") // 添加实践课
	public ModelAndView addshijianke(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		Date date = new Date();
		SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> map = new HashMap<>();
		int yuanxiid = user.getYuanxiid();
		String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
		map.put("riqi", riqi.format(date));
		map.put("xueXiaoID", xueXiaoID);
		XueQi xueQi = new XueQi();
		xueQi = xueqiService.getByxueXiaoIDandriQi(map);
		if (xueQi == null) {
			List<XueQi> xueqis = xueqiService.getNewerXueQiByXueQi(map);
			if (xueqis != null && xueqis.size() > 0) {
				xueQi = xueqis.get(0);
			}
		}
		int nf = nianfenService.selectByPrimaryKey(xueQi.getNianfenid()).getRuxuenianfen();
		String xuenian = nf + "~" + (nf + 1);
		xueQi.setXuenian(xuenian);
		String renkelaoshiid = user.getYonghuid().toString();
		map.put("xueqiid", xueQi.getXueqiid().toString());
		map.put("renkelaoshiid", renkelaoshiid);
		List<Map<String, Object>> keChengs = kechengService.getKeChengByLaoShiID(map);
		ModelAndView mView = new ModelAndView();
		mView.addObject("kechengs", keChengs);
		mView.setViewName("jiaoshi/addshijianke");
		return mView;
	}

	@ResponseBody
	@RequestMapping(value = "saveshijianke") // 保存实践课
	public JSONObject saveshijianke(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String kechengid = request.getParameter("shijianke");
		String rongliang = request.getParameter("rongliang");
		Map<String, Object> map = new HashMap<>();
		map.put("kechengid", kechengid);
		map.put("xiaozurongliang", rongliang);
		int i = kechengService.insertshijianke(map);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	@RequestMapping(value = "updateshijianke") // 更新实践课
	public ModelAndView updateshijianke(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String shijiankeid = request.getParameter("id");
		Map<String, Object> shijianke = kechengService.getshijianke(Integer.parseInt(shijiankeid));
		KeCheng keCheng2 = kechengService.selectByPrimaryKey(Integer.parseInt(shijianke.get("keChengID").toString()));
		mView.addObject("kecheng", shijianke);
		mView.addObject("kechengmingcheng", keCheng2.getKechengmingcheng());
		mView.setViewName("jiaoshi/modifyshijianke");
		return mView;
	}

	@ResponseBody
	@RequestMapping(value = "saveupdatekecheng") // 保存更新
	public JSONObject saveupdatekecheng(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String kechengid = request.getParameter("kechengid");
		String rongliang = request.getParameter("rongliang");
		Map<String, Object> map = new HashMap<>();
		map.put("id", kechengid);
		map.put("xiaozurongliang", rongliang);
		int i = kechengService.updateshijianke(map);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "delshijianke") // 删除实践课
	public JSONObject delshijianke(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String kechengid = request.getParameter("CODE");
		try {
			int i = kechengService.delshijianke(Integer.parseInt(kechengid));
			if (i != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		} catch (Exception e) {
			jsonObject.put("status", "limited");
		}

		return jsonObject;
	}

	@RequestMapping(value = "getxiaozu") // 得到小组信息
	public ModelAndView getxiaozu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String kechengid = request.getParameter("id");
		List<Map<String, Object>> unfenzu = unfenzu(kechengid);
		List<App_XueXiZu> xueXiZus = app_XueXiZuService.getXueXiZuByKeCheng(Integer.parseInt(kechengid));
		List<Map<String, Object>> xueXiZusMap = new ArrayList<>();
		Map<String, Object> keCheng = kechengService.getshijianke(Integer.parseInt(kechengid));
		// Map<String, Object> paramap = new HashMap<>();
		for (App_XueXiZu app_XueXiZu : xueXiZus) {
			Map<String, Object> mapss = new HashMap<>();
			mapss.put("ID", app_XueXiZu.getId());
			mapss.put("fenZuID", app_XueXiZu.getFenZuId());
			mapss.put("xueXiZuMing", app_XueXiZu.getXueXiZuMing());
			mapss.put("xueShengID", app_XueXiZu.getXiaoZuZhangId());
			mapss.put("keChengID", app_XueXiZu.getShiJianKeChengId());
			mapss.put("chengYuanIDLieBiao", app_XueXiZu.getChengYuanLieBiao());
			mapss.put("zhuangTai", app_XueXiZu.getZhuangTai());
			if (app_XueXiZu.getChengYuanLieBiao() != null && !"".equals(app_XueXiZu.getChengYuanLieBiao())) {
				String[] members = app_XueXiZu.getChengYuanLieBiao().substring(1).split(",");
				List<Map<String, Object>> list = new ArrayList<>();
				for (int k = 0; k < members.length; k++) {
					Map<String, Object> map = new HashMap<>();
					XueSheng xSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(members[k]));
					if (xSheng == null) {
						continue;
					}
					BanJi banJi = banJiService.selectByPrimaryKey(xSheng.getBanjiid());
					map.put("xingming", xSheng.getXingming());
					map.put("xuehao", xSheng.getXuehao());
					map.put("xueshengid", xSheng.getXueshengid());
					map.put("banjimingcheng", banJi.getBanjimingcheng());
					list.add(map);
				}
				mapss.put("chengyuan", list);
			}
			xueXiZusMap.add(mapss);
		}
		int count = xueXiZus.size();
		int pageSize = 10;
		int page = 1;
		int pages = (count / pageSize) + 1;
		if (count % pageSize == 0) {
			pages = (count / pageSize);
		}
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			List<Map<String, Object>> xiaozus = new ArrayList<>();
			if (count < 10) {
				for (int i = 0; i < count; i++) {
					xiaozus.add(xueXiZusMap.get(i));
				}
				mView.addObject("xiaozuxinxis", xiaozus);
			} else {
				for (int i = 0; i < 10; i++) {
					xiaozus.add(xueXiZusMap.get(i));
				}
				mView.addObject("xiaozuxinxis", xiaozus);
			}
		} else {
			if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<Map<String, Object>> xiaozus = new ArrayList<>();
					if (count < 10) {
						for (int i = (page - 1) * 10; i < count; i++) {
							xiaozus.add(xueXiZusMap.get(i));
						}
						mView.addObject("xiaozuxinxis", xiaozus);
					} else {
						for (int i = (page - 1) * 10; i < (page * 10); i++) {
							xiaozus.add(xueXiZusMap.get(i));
						}
						mView.addObject("xiaozuxinxis", xiaozus);
					}
				} else if (page == pages) {
					List<Map<String, Object>> xiaozus = new ArrayList<>();
					for (int i = (page - 1) * 10; i < count; i++) {
						xiaozus.add(xueXiZusMap.get(i));
					}
					mView.addObject("xiaozuxinxis", xiaozus);
				} else {
					try {
						response.sendRedirect("logout");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			} else {
				try {
					response.sendRedirect("logout");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}
		// mView.addObject("xiaozuxinxis", xueXiZusMap);
		mView.addObject("kecheng", keCheng);
		mView.addObject("unfenzu", unfenzu);
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.setViewName("jiaoshi/xiaozu");
		return mView;
	}

	@RequestMapping(value = "updatexiaozu") // 修改小组
	public ModelAndView updatexiaozu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String xiaozuid = request.getParameter("id");
		Map<String, Object> app_XueXiZu = app_XueXiZuService.selectByPrimaryKey1(Integer.parseInt(xiaozuid));
		if (app_XueXiZu.get("chengYuanIDLieBiao") != null && !"".equals(app_XueXiZu.get("chengYuanIDLieBiao"))) {
			String[] members = app_XueXiZu.get("chengYuanIDLieBiao").toString().substring(1).split(",");
			for (int k = 0; k < members.length; k++) {
				XueSheng xSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(members[k]));
				if (xSheng == null) {
					continue;
				}
				BanJi banJi = banJiService.selectByPrimaryKey(xSheng.getBanjiid());
				app_XueXiZu.put("xingming", xSheng.getXingming());
				app_XueXiZu.put("xuehao", xSheng.getXuehao());
				app_XueXiZu.put("xueshengid", xSheng.getXueshengid());
				app_XueXiZu.put("banjimingcheng", banJi.getBanjimingcheng());
			}
		}
		mView.addObject("xuexizu", app_XueXiZu);
		mView.setViewName("jiaoshi/updatexiaozu");
		return mView;
	}

	@ResponseBody
	@RequestMapping(value = "saveupdatexuexizu") // 保存小组
	public JSONObject saveupdatexuexizu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String kaopingid = request.getParameter("kaopingid");
		String mingcheng = request.getParameter("kaopingmingcheng");
		App_KeChengKaoPing keChengKaoPing = app_KeChengKaoPingService.selectByPrimaryKey(Integer.parseInt(kaopingid));
		keChengKaoPing.setKaoPingMingCheng(mingcheng);
		int i = app_KeChengKaoPingService.update(keChengKaoPing);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	@RequestMapping(value = "xiaozudetail") // 查看小组详细信息
	public ModelAndView xiaozudetail(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String xiaozuid = request.getParameter("id");
		Map<String, Object> app_XueXiZu = app_XueXiZuService.selectByPrimaryKey1(Integer.parseInt(xiaozuid));
		if (app_XueXiZu.get("chengYuanIDLieBiao") != null && !"".equals(app_XueXiZu.get("chengYuanIDLieBiao"))) {
			String[] members = app_XueXiZu.get("chengYuanIDLieBiao").toString().substring(1).split(",");
			List<Map<String, Object>> list = new ArrayList<>();
			for (int k = 0; k < members.length; k++) {
				Map<String, Object> map = new HashMap<>();
				XueSheng xSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(members[k]));
				if (xSheng == null) {
					continue;
				}
				BanJi banJi = banJiService.selectByPrimaryKey(xSheng.getBanjiid());
				map.put("xingming", xSheng.getXingming());
				map.put("xuehao", xSheng.getXuehao());
				map.put("xueshengid", xSheng.getXueshengid());
				map.put("banjimingcheng", banJi.getBanjimingcheng());
				list.add(map);
			}
			app_XueXiZu.put("chengyuan", list);
			app_XueXiZu.put("xiaozuzhang", xueShengService
					.selectByPrimaryKey(Integer.parseInt(app_XueXiZu.get("xueShengID").toString())).getXingming());
		}
		mView.addObject("xiaozuxinxi", app_XueXiZu);
		mView.setViewName("jiaoshi/xiaozuxiangqing");
		return mView;
	}

	@RequestMapping(value = "addchengyuan")
	public ModelAndView addchengyuan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		String xiaozuid = request.getParameter("xiaozuid");
		App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuid));
		List<Map<String, Object>> list = unfenzu(app_XueXiZu.getShiJianKeChengId().toString());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("xuexizu", app_XueXiZu);
		modelAndView.addObject("unfenzu", list);
		modelAndView.setViewName("jiaoshi/addchengyuan");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "savechengyuan") // 保存修改
	public JSONObject savechengyuan(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String xiaozuid = request.getParameter("xiaozuid");
		String xueshengid = request.getParameter("xueshengid");
		App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuid));
		if (app_XueXiZu == null) {
			jsonObject.put("status", "noxuexizu");
			return jsonObject;
		}
		Map<String, Object> shijiankemap = kechengService.getshijianke(app_XueXiZu.getShiJianKeChengId());
		// String xiaozurongliang = shijiankemap.get("xiaoZuRongLiang").toString();
		String chengYuan = "";
		if (app_XueXiZu.getChengYuanLieBiao() != null && !"".equals(app_XueXiZu.getChengYuanLieBiao())) {
			chengYuan += app_XueXiZu.getChengYuanLieBiao() + xueshengid + ",";
			// if (app_XueXiZu.getChengYuanLieBiao().substring(1).length() >
			// Integer.parseInt(xiaozurongliang)) {
			// jsonObject.put("status", "full");
			// return jsonObject;
			// }
		} else {
			chengYuan = "," + xueshengid + ",";
		}
		app_XueXiZu.setChengYuanLieBiao(chengYuan);
		try {
			int i = app_XueXiZuService.update(app_XueXiZu);
			if (i != 0) {
				jsonObject.put("status", "success");
			}
		} catch (Exception e) {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	@RequestMapping(value = "weifenzustu") // 未分组学生
	public ModelAndView weifenzustu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String shijiankeid = request.getParameter("id");
		List<Map<String, Object>> unFenZu = unfenzu(shijiankeid);
		int count = unFenZu.size();
		int pageSize = 10;
		int page = 1;
		int pages = (count / pageSize) + 1;
		if (count % pageSize == 0) {
			pages = (count / pageSize);
		}
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			List<Map<String, Object>> unFenZu2 = new ArrayList<>();
			if (count < 10) {
				for (int i = 0; i < count; i++) {
					unFenZu2.add(unFenZu.get(i));
				}
				mView.addObject("xueshengs", unFenZu2);
			} else {
				for (int i = 0; i < 10; i++) {
					unFenZu2.add(unFenZu.get(i));
				}
				mView.addObject("xueshengs", unFenZu2);
			}
		} else {
			if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<Map<String, Object>> unFenZu2 = new ArrayList<>();
					if (count < 10) {
						for (int i = (page - 1) * 10; i < count; i++) {
							unFenZu2.add(unFenZu.get(i));
						}
						mView.addObject("xueshengs", unFenZu2);
					} else {
						for (int i = (page - 1) * 10; i < (page * 10); i++) {
							unFenZu2.add(unFenZu.get(i));
						}
						mView.addObject("xueshengs", unFenZu2);
					}
				} else if (page == pages) {
					List<Map<String, Object>> unFenZu2 = new ArrayList<>();
					for (int i = (page - 1) * 10; i < count; i++) {
						unFenZu2.add(unFenZu.get(i));
					}
					mView.addObject("xueshengs", unFenZu2);
				} else {
					try {
						response.sendRedirect("logout");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			} else {
				try {
					response.sendRedirect("logout");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

		}
		mView.addObject("kechengid", shijiankeid);
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		// mView.addObject("xueshengs",unFenZu);
		mView.setViewName("jiaoshi/unfenzu");
		return mView;
	}

	private List<Map<String, Object>> unfenzu(String shijiankeid) {
		Map<String, Object> shijiankemap = kechengService.getshijianke(Integer.parseInt(shijiankeid));
		List<String> banjiids = kechengService.getByAllBanJiIDByID(shijiankemap.get("keChengID").toString());
		List<String> xueshengids = kechengService.getAllMianXiuIDByID(shijiankemap.get("keChengID").toString());
		List<String> xueshengids2 = kechengService.getAllXuanXiuIDByID(shijiankemap.get("keChengID").toString());
		List<Map<String, Object>> dazuList = app_FenZuService.getDaZu(Integer.parseInt(shijiankeid));
		List<Map<String, Object>> unFenZu = new ArrayList<>();
		String chenguanIDs = "";
		for (int i = 0; i < dazuList.size(); i++) {
			List<App_XueXiZu> xiaozulist = app_XueXiZuService
					.getXueXiZu(Integer.parseInt(dazuList.get(i).get("ID").toString()));
			for (App_XueXiZu app_XueXiZu : xiaozulist) {
				chenguanIDs += app_XueXiZu.getChengYuanLieBiao().substring(1);
			}
		}
		Set<XueSheng> xueShengss = new HashSet<>();
		for (String banjiid : banjiids) {
			List<XueSheng> xShengs = xueShengService.getAllByBanJiID(Integer.parseInt(banjiid));
			for (XueSheng xueSheng : xShengs) {
				xueShengss.add(xueSheng);
			}
		}
		for (String xueshengid : xueshengids2) {
			XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
			if (xs == null) {
				continue;
			}
			xueShengss.add(xs);
		}
		for (XueSheng xs : xueShengss) {
			for (String xueshengid : xueshengids) {
				if (xs.getXueshengid().toString().equals(xueshengid)) {
					xueShengss.remove(xs);
					continue;
				}
			}
		}
		List<XueSheng> xueShengs = new ArrayList<>(xueShengss);
		for (int j = 0; j < xueShengss.size(); j++) {
			if (("," + chenguanIDs).contains("," + xueShengs.get(j).getXueshengid().toString() + ",")) {
				continue;
			}
			Map<String, Object> mapss = new HashMap<>();
			BanJi banji = banJiService.selectByPrimaryKey(xueShengs.get(j).getBanjiid());
			mapss.put("xingming", xueShengs.get(j).getXingming());
			mapss.put("xuehao", xueShengs.get(j).getXuehao());
			mapss.put("xueshengid", xueShengs.get(j).getXueshengid());
			if (banji != null) {
				mapss.put("banjimingcheng", banji.getBanjimingcheng());
			} else {
				mapss.put("banjimingcheng", "");
			}
			unFenZu.add(mapss);
		}

		return unFenZu;
	}

	@RequestMapping(value = "addxiaozu") // 添加小组
	public ModelAndView addxiaozu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String kechengid = request.getParameter("id");
		Map<String, Object> keCheng = kechengService.getshijianke(Integer.parseInt(kechengid));
		List<Map<String, Object>> daZuList = app_FenZuService.getDaZu(Integer.parseInt(kechengid));
		mView.addObject("dazuList", daZuList);
		List<Map<String, Object>> unfenzuxuesheng = unfenzu(kechengid);
		mView.addObject("unfenzu", unfenzuxuesheng);
		mView.addObject("kechengid", kechengid);
		mView.addObject("xiaozurongliang", keCheng.get("xiaoZuRongLiang").toString());
		mView.setViewName("jiaoshi/addxiaozu");
		return mView;
	}

	@ResponseBody
	@RequestMapping(value = "savexiaozu") // 保存修改
	public JSONObject savexiaozu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String keChengID = request.getParameter("kechengid");
		String xiaoZuMing = request.getParameter("xiaozuming");
		String daZuID = request.getParameter("dazuid");
		String xiaoZuZhangID = request.getParameter("xiaozuzhangid");
		String[] xueShengID = request.getParameterValues("xueshengid");
		String chengYuanLieBiao = "";
		for (String xueshengid : xueShengID) {
			chengYuanLieBiao += "," + xueshengid;
		}
		App_XueXiZu app_XueXiZu = new App_XueXiZu();
		app_XueXiZu.setShiJianKeChengId(Integer.parseInt(keChengID));
		app_XueXiZu.setXueXiZuMing(xiaoZuMing);
		app_XueXiZu.setFenZuId(Integer.parseInt(daZuID));
		app_XueXiZu.setXiaoZuZhangId(Integer.parseInt(xiaoZuZhangID));
		app_XueXiZu.setChengYuanLieBiao(chengYuanLieBiao + ",");
		app_XueXiZu.setZhuangTai(1);
		try {
			int i = app_XueXiZuService.insert(app_XueXiZu);
			if (i != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "delchengyuan") // 踢掉某个小组成员
	public JSONObject delchengyuan(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String kickid = request.getParameter("xueshengid");
		String xiaozuid = request.getParameter("xiaozuid");
		App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuid));
		if (app_XueXiZu == null) {
			jsonObject.put("status", "noxuexizu");
			return jsonObject;
		}
		if (kickid == null || "".equals(kickid)) {
			jsonObject.put("status", "nokickmember");
			return jsonObject;
		}
		String kickids = "," + kickid + ",";
		if (kickids.contains("," + app_XueXiZu.getXiaoZuZhangId().toString() + ",")) {
			// app_XueXiZuService.delete(Integer.parseInt(xiaozuid));
			jsonObject.put("status", "jiesan");
			return jsonObject;
		}
		String chengYuan = "";
		if (app_XueXiZu.getChengYuanLieBiao() != null && !"".equals(app_XueXiZu.getChengYuanLieBiao())) {
			chengYuan = app_XueXiZu.getChengYuanLieBiao();
		} else {
			jsonObject.put("status", "nochengyuan");
		}
		String[] kickmemberid = kickid.split(",");
		String[] chengyuanid = chengYuan.substring(1).split(",");
		String newchengyuanid = "";
		Set<String> set = new HashSet<>();
		for (int m = 0; m < chengyuanid.length; m++) {
			set.add(chengyuanid[m]);
		}
		for (int k = 0; k < kickmemberid.length; k++) {
			if (set.contains(kickmemberid[k])) {
				set.remove(kickmemberid[k]);
			}
		}
		if (set.size() > 0) {
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()) {
				newchengyuanid += iterator.next() + ",";
			}
		}
		app_XueXiZu.setChengYuanLieBiao("," + newchengyuanid);
		int i = app_XueXiZuService.update(app_XueXiZu);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}

		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "delxiaozu") // 删除小组
	public JSONObject delxiaozu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String xiaozuid = request.getParameter("xiaozuid");
		App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuid));
		App_FenZu app_FenZu = new App_FenZu();
		if(app_XueXiZu!=null) {
			 app_FenZu = app_FenZuService.selectByPrimaryKey(app_XueXiZu.getFenZuId());
		}
		try {
			int i = app_XueXiZuService.delete(Integer.parseInt(xiaozuid));
			if (i != 0) {
				jsonObject.put("status", "success");
				app_FenZu.setYiJiaRu(app_FenZu.getYiJiaRu()-1);
				app_FenZuService.update(app_FenZu);
			} else {
				jsonObject.put("status", "fail");
			}
		} catch (Exception e) {
			jsonObject.put("status", "limited");
		}

		return jsonObject;
	}

	@RequestMapping(value = "getdazu") // 得到大组
	public ModelAndView getdazu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String kechengid = request.getParameter("id");
		Map<String, Object> keCheng = kechengService.getshijianke(Integer.parseInt(kechengid));
		List<Map<String, Object>> daZus = app_FenZuService.getDaZu(Integer.parseInt(kechengid));
		mView.addObject("dazus", daZus);
		mView.addObject("kecheng", keCheng);
		mView.setViewName("jiaoshi/dazu");
		return mView;
	}

	@RequestMapping(value = "adddazu") // 添加大组
	public ModelAndView adddazu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String shijiankeid = request.getParameter("id");
		Map<String, Object> shijiankemap = kechengService.getshijianke(Integer.parseInt(shijiankeid));
		// List<String> banjiids =
		// kechengService.getByAllBanJiIDByID(shijiankemap.get("keChengID").toString());
		// List<String> xueshengids =
		// kechengService.getAllMianXiuIDByID(shijiankemap.get("keChengID").toString());
		// List<XueSheng> xueShengs = new ArrayList<>();
		// for (String banjiid : banjiids) {
		// List<XueSheng> xShengs =
		// xueShengService.getAllByBanJiID(Integer.parseInt(banjiid));
		// for (XueSheng xueSheng : xShengs) {
		// xueShengs.add(xueSheng);
		// }
		// }
		// for (String xueshengid : xueshengids) {
		// XueSheng xs =
		// xueShengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
		// if (xs == null) {
		// continue;
		// }
		// xueShengs.add(xs);
		// }
		mView.addObject("kechengid", shijiankeid);
		// mView.addObject("xueshengs", xueShengs);
		mView.setViewName("jiaoshi/adddazu");
		return mView;
	}

	@ResponseBody
	@RequestMapping(value = "savedazu") // 保存修改
	public JSONObject savedazu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String rongLiang = request.getParameter("rongliang");
		String zuMingCheng = request.getParameter("zuMingCheng");
		// String dazuzhangid = request.getParameter("dazuzhangid");
		String kechengid = request.getParameter("id");
		App_FenZu app_FenZu = new App_FenZu();
		app_FenZu.setRongLiang(Integer.parseInt(rongLiang));
		app_FenZu.setZuMingCheng(zuMingCheng);
		app_FenZu.setShiJianKeChengId(Integer.parseInt(kechengid));
		// app_FenZu.setZuZhangId(Integer.parseInt(dazuzhangid));
		jsonObject.put("kechengid", kechengid);
		try {
			int i = app_FenZuService.insertdazu(app_FenZu);
			if (i != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	@RequestMapping(value = "updatedazu") // 修改大组
	public ModelAndView updatedazu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String dazuid = request.getParameter("id");
		App_FenZu app_FenZu = app_FenZuService.selectByPrimaryKey(Integer.parseInt(dazuid));
		List<App_XueXiZu> xueXiZus = app_XueXiZuService.getXueXiZu(Integer.parseInt(dazuid));
		List<XueSheng> xueShengs = new ArrayList<>();
		for (App_XueXiZu app_XueXiZu : xueXiZus) {
			String chengyuanids = app_XueXiZu.getChengYuanLieBiao().substring(1);
			if (chengyuanids != null && !"".equals(chengyuanids)) {
				for (String xueshengid : chengyuanids.split(",")) {
					XueSheng xueSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
					if (xueSheng != null) {
						xueShengs.add(xueSheng);
					} else {
						continue;
					}
				}
			}
		}
		mView.addObject("dazu", app_FenZu);
		mView.addObject("xueshengs", xueShengs);
		mView.setViewName("jiaoshi/updatedazu");
		return mView;
	}

	@ResponseBody
	@RequestMapping(value = "saveupdatedazu") // 保存修改
	public JSONObject saveupdatedazu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String dazuid = request.getParameter("id");
		String rongLiang = request.getParameter("rongliang");
		String zuMingCheng = request.getParameter("zuMingCheng");
		String dazuzhangid = request.getParameter("dazuzhangid");
		App_FenZu app_FenZu = app_FenZuService.selectByPrimaryKey(Integer.parseInt(dazuid));
		Map<String, Object> shijianke = kechengService.getshijianke(app_FenZu.getShiJianKeChengId());
		app_FenZu.setRongLiang(Integer.parseInt(rongLiang));
		app_FenZu.setZuMingCheng(zuMingCheng);
		app_FenZu.setZuZhangId(Integer.parseInt(dazuzhangid));
		jsonObject.put("kechengid", shijianke.get("ID").toString());
		try {
			int i = app_FenZuService.update(app_FenZu);
			if (i != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "deldazu") // 删除大组
	public JSONObject deldazu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String dazuid = request.getParameter("id");
		try {
			int i = app_FenZuService.delete(Integer.parseInt(dazuid));
			if (i != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		} catch (Exception e) {
			jsonObject.put("status", "limited");
		}
		return jsonObject;
	}

	@RequestMapping(value = "chakanxiaozu") // 查看大组下的小组
	public ModelAndView chakanxiaozu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String fenzuid = request.getParameter("id");
		App_FenZu app_FenZu = app_FenZuService.selectByPrimaryKey(Integer.parseInt(fenzuid));
		List<Map<String, Object>> xueXiZus = app_XueXiZuService.getXueXZ(Integer.parseInt(fenzuid));
		Map<String, Object> keCheng = kechengService.getshijianke(app_FenZu.getShiJianKeChengId());
		// Map<String, Object> paramap = new HashMap<>();
		for (Map<String, Object> app_XueXiZu : xueXiZus) {
			if (app_XueXiZu.get("chengYuanIDLieBiao") != null
					&& !"".equals(app_XueXiZu.get("chengYuanIDLieBiao").toString())) {
				String[] members = app_XueXiZu.get("chengYuanIDLieBiao").toString().substring(1).split(",");
				List<Map<String, Object>> list = new ArrayList<>();
				for (int k = 0; k < members.length; k++) {
					Map<String, Object> map = new HashMap<>();
					XueSheng xSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(members[k]));
					if (xSheng == null) {
						continue;
					}
					BanJi banJi = banJiService.selectByPrimaryKey(xSheng.getBanjiid());
					map.put("xingming", xSheng.getXingming());
					map.put("xuehao", xSheng.getXuehao());
					map.put("xueshengid", xSheng.getXueshengid());
					map.put("banjimingcheng", banJi.getBanjimingcheng());
					list.add(map);
				}
				app_XueXiZu.put("chengyuan", list);
			}
		}
		mView.addObject("xiaozuxinxis", xueXiZus);
		mView.addObject("kecheng", keCheng);
		mView.setViewName("jiaoshi/xiaozu");
		return mView;
	}

	// @RequestMapping(value = "getjianbao") // 得到简报
	// public ModelAndView getjianbao(HttpServletRequest request,
	// HttpServletResponse response) {
	// if (!Util.checkSession(request)) {
	// try {
	// response.sendRedirect("logout");
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	// ModelAndView mView = new ModelAndView();
	// String kechengid = request.getParameter("id");
	// Map<String, Object> keCheng =
	// kechengService.getshijianke(Integer.parseInt(kechengid));
	// List<Map<String, Object>> jianbaolist =
	// app_KeChengKaoPingService.getKaoPing(Integer.parseInt(kechengid));
	// mView.addObject("jianbao", jianbaolist);
	// mView.addObject("kecheng", keCheng);
	// mView.setViewName("jiaoshi/jianbao");
	// return mView;
	// }

	@RequestMapping(value = "getkaoping") // 得到考评
	public ModelAndView getkaoping(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String kechengid = request.getParameter("id");
		Map<String, Object> shijiankecheng = kechengService.getshijianke(Integer.parseInt(kechengid));
		KeCheng kecheng = kechengService
				.selectByPrimaryKey(Integer.parseInt(shijiankecheng.get("keChengID").toString()));
		List<Map<String, Object>> kaopinglist = app_KeChengKaoPingService.getKaoPing2(Integer.parseInt(kechengid));
		mView.addObject("kecheng", shijiankecheng);
		mView.addObject("kecheng2", kecheng);
		mView.addObject("kaopings", kaopinglist);
		mView.setViewName("jiaoshi/kaoping");
		return mView;
	}

	@RequestMapping(value = "addkaoping") // 添加课程考评
	public ModelAndView addkaoping(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		// YongHu user = (YongHu) request.getSession().getAttribute("user");
		// Date date = new Date();
		// SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
		// Map<String, String> map = new HashMap<>();
		// int yuanxiid = user.getYuanxiid();
		// String xueXiaoID =
		// yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
		// map.put("riqi", riqi.format(date));
		// map.put("xueXiaoID", xueXiaoID);
		// XueQi xueQi = new XueQi();
		// xueQi = xueqiService.getByxueXiaoIDandriQi(map);
		// if (xueQi == null) {
		// List<XueQi> xueqis = xueqiService.getNewerXueQiByXueQi(map);
		// if (xueqis != null && xueqis.size() > 0) {
		// xueQi = xueqis.get(0);
		// }
		// }
		// int nf =
		// nianfenService.selectByPrimaryKey(xueQi.getNianfenid()).getRuxuenianfen();
		// String xuenian = nf + "~" + (nf + 1);
		// xueQi.setXuenian(xuenian);
		// String renkelaoshiid = user.getYonghuid().toString();
		// map.put("xueqiid", xueQi.getXueqiid().toString());
		// map.put("renkelaoshiid", renkelaoshiid);
		// List<Map<String, Object>> keChengs =
		// kechengService.getKeChengByRenKeLaoShiID(map);
		ModelAndView mView = new ModelAndView();
		String kechengid = request.getParameter("id");
		Map<String, Object> shijiankemap = kechengService.getshijianke(Integer.parseInt(kechengid));
		KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(shijiankemap.get("keChengID").toString()));
		if (keCheng != null) {
			mView.addObject("kechengmingcheng", keCheng.getKechengmingcheng());
		}
		mView.addObject("kechengid", kechengid);
		mView.setViewName("jiaoshi/addkaoping");
		return mView;
	}

	@ResponseBody
	@RequestMapping(value = "savekaoping") // 保存考评
	public JSONObject savekaoping(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String kechengid = request.getParameter("kechengid");
		String mingcheng = request.getParameter("kaopingmingcheng");
		String zhuangtai = request.getParameter("zhuangtai");
		String kaishishijian = request.getParameter("riqi");
		String fenshuzhanbi = request.getParameter("fenshuzhanbi");
		List<Map<String, Object>> kaopinglist = app_KeChengKaoPingService.getKaoPing2(Integer.parseInt(kechengid));
		for (int i = 0; i < kaopinglist.size(); i++) {
			if (kaopinglist.get(i).get("kaoPingMingCheng").toString().equals(mingcheng)) {
				jsonObject.put("status", "samename");
				return jsonObject;
			}
		}
		App_KeChengKaoPing keChengKaoPing = new App_KeChengKaoPing();
		keChengKaoPing.setKaoPingMingCheng(mingcheng);
		keChengKaoPing.setZhuangTai(Integer.parseInt(zhuangtai));
		keChengKaoPing.setKaiShiShiJian(kaishishijian);
		keChengKaoPing.setFenShuZhanBi(Double.valueOf(fenshuzhanbi));
		keChengKaoPing.setShiJianKeChengId(Integer.parseInt(kechengid));
		int i = app_KeChengKaoPingService.insert(keChengKaoPing);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	@RequestMapping(value = "updatekaoping") // 修改考评
	public ModelAndView updatekaoping(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String kaopingid = request.getParameter("kaopingid");
		App_KeChengKaoPing keChengKaoPing = app_KeChengKaoPingService.selectByPrimaryKey(Integer.parseInt(kaopingid));
		mView.addObject("kechengkaoping", keChengKaoPing);
		mView.setViewName("jiaoshi/modifykaoping");
		return mView;
	}

	@ResponseBody
	@RequestMapping(value = "saveupdatekaoping") // 保存考评
	public JSONObject saveupdatekaoping(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String kaopingid = request.getParameter("kaopingid");
		String shiJianKeChengId = request.getParameter("kechengid");
		String mingcheng = request.getParameter("kaopingmingcheng");
		String zhuangtai = request.getParameter("zhuangtai");
		String kaishishijian = request.getParameter("riqi");
		String fenshuzhanbi = request.getParameter("fenshuzhanbi");
		List<Map<String, Object>> kaopinglist = app_KeChengKaoPingService
				.getKaoPing2(Integer.parseInt(shiJianKeChengId));
		for (int i = 0; i < kaopinglist.size(); i++) {
			if (kaopinglist.get(i).get("kaoPingMingCheng").toString().equals(mingcheng)) {
				if (kaopinglist.get(i).get("ID").toString().equals(kaopingid)) {
					continue;
				}
				jsonObject.put("status", "samename");
				return jsonObject;
			}
		}
		App_KeChengKaoPing keChengKaoPing = app_KeChengKaoPingService.selectByPrimaryKey(Integer.parseInt(kaopingid));
		keChengKaoPing.setKaoPingMingCheng(mingcheng);
		keChengKaoPing.setZhuangTai(Integer.parseInt(zhuangtai));
		keChengKaoPing.setKaiShiShiJian(kaishishijian);
		keChengKaoPing.setFenShuZhanBi(Double.valueOf(fenshuzhanbi));
		int i = app_KeChengKaoPingService.update(keChengKaoPing);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	@RequestMapping(value = "chakandefen") // 查看得分
	public ModelAndView chakandefen(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		String kaopingid = request.getParameter("kaopingid");
		String dazuid = request.getParameter("dazuid");
		App_KeChengKaoPing app_KeChengKaoPing = app_KeChengKaoPingService
				.selectByPrimaryKey(Integer.parseInt(kaopingid));
		List<Map<String, Object>> daZuList = new ArrayList<>();
		List<Map<String, Object>> daZus = app_FenZuService.getFenZu(app_KeChengKaoPing.getShiJianKeChengId());
		Map<String, Object> mapss = new HashMap<>();
		if (dazuid == null || "".equals(dazuid)) {
			List<Map<String, Object>> list = app_FenZuService.getFenZu(app_KeChengKaoPing.getShiJianKeChengId());
			if (list != null && list.size() > 0) {
				mapss.put("dazuming", list.get(0).get("dazuming"));
			} else {
				mapss.put("dazuming", "");
			}
			mapss.put("fenzuid", list.get(0).get("fenzuid"));
			dazuid = list.get(0).get("fenzuid").toString();
			daZuList.add(mapss);
		} else {
			App_FenZu app_FenZu = app_FenZuService.selectByPrimaryKey(Integer.parseInt(dazuid));
			if (app_FenZu != null) {
				mapss.put("dazuming", app_FenZu.getZuMingCheng());
			} else {
				mapss.put("dazuming", "");
			}
			mapss.put("fenzuid", dazuid);
			daZuList.add(mapss);
		}
		List<Map<String, Object>> allxiaozu = new ArrayList<>();
		for (int i = 0; i < daZuList.size(); i++) {
			List<Map<String, Object>> xiaozus = app_XueXiZuService
					.getXueXZ(Integer.parseInt(daZuList.get(i).get("fenzuid").toString()));
			for (int k = 0; k < xiaozus.size(); k++) {
				Map<String, Object> xueXiMap = new HashMap<>();
				if (xiaozus.get(k).get("chengYuanIDLieBiao").toString() != null) {
					String[] chengyuan = xiaozus.get(k).get("chengYuanIDLieBiao").toString().substring(1).split(",");
					List<Map<String, Object>> member = new ArrayList<>();
					for (int j = 0; j < chengyuan.length; j++) {
						Map<String, Object> map = new HashMap<>();
						XueSheng xSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(chengyuan[j]));
						BanJi banJi = banJiService.selectByPrimaryKey(xSheng.getBanjiid());
						map.put("xingming", xSheng.getXingming());
						map.put("xuehao", xSheng.getXuehao());
						if (banJi != null) {
							map.put("banjimingcheng", banJi.getBanjimingcheng());
						} else {
							map.put("banjimingcheng", "");
						}
						member.add(map);
					}
					xueXiMap.put("chengyuan", member);
					xueXiMap.put("xiaozuzhang",
							xueShengService
									.selectByPrimaryKey(Integer.parseInt(xiaozus.get(k).get("xueShengID").toString()))
									.getXingming());
					App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService.selectJieGuoByXiaoZuId(
							Integer.parseInt(kaopingid), Integer.parseInt(xiaozus.get(k).get("ID").toString()));
					xiaozus.get(k).put("chengyuanxinxi", xueXiMap);
					xiaozus.get(k).put("defen", app_KaoPingJieGuo);
					if (app_KaoPingJieGuo != null) {
						xiaozus.get(k).put("mingci", app_KaoPingJieGuo.getMingCi());
					}
					allxiaozu.add(xiaozus.get(k));
				}
			}
		}
		Collections.sort(allxiaozu, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				if (Integer.parseInt(o1.get("fenZuID").toString()) > Integer.parseInt(o1.get("fenZuID").toString())) {
					return -1;
				} else if (Integer.parseInt(o1.get("fenZuID").toString()) == Integer
						.parseInt(o1.get("fenZuID").toString())) {
					if (Integer.parseInt(o1.get("mingci").toString()) > Integer.parseInt(o2.get("mingci").toString())) {
						return -1;
					} else if (Integer.parseInt(o1.get("mingci").toString()) == Integer
							.parseInt(o2.get("mingci").toString())) {
						return 0;
					} else {
						return 1;
					}
				} else {
					return 1;
				}
			}
		});
		ModelAndView mView = new ModelAndView();
		mView.addObject("dazuxinxi", daZuList);
		mView.addObject("xiaozus", allxiaozu);
		mView.addObject("kaopingid", kaopingid);
		mView.addObject("kechengid", app_KeChengKaoPing.getShiJianKeChengId());
		mView.addObject("dazuid", dazuid);
		mView.addObject("dazus", daZus);
		mView.setViewName("jiaoshi/defendetail");
		return mView;
	}

	@ResponseBody
	@RequestMapping(value = "delkaoping") // 保存考评
	public JSONObject delkaoping(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String kaopingid = request.getParameter("kaopingid");
		try {
			int i = app_KeChengKaoPingService.delete(Integer.parseInt(kaopingid));
			if (i != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		} catch (Exception e) {
			jsonObject.put("status", "limited");
		}

		return jsonObject;
	}

	@RequestMapping(value = "forward") // 得到实践课程
	public ModelAndView forward(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		String kechengid = request.getParameter("id");
		Map<String, Object> keCheng = kechengService.getshijianke(Integer.parseInt(kechengid));
		ModelAndView mView = new ModelAndView();
		mView.addObject("kecheng", keCheng);
		mView.setViewName("jiaoshi/kechengkaoping");
		return mView;
	}

}