package com.web.controller.web.jiaoshi;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.corba.se.spi.ior.IdentifiableContainerBase;
import com.web.model.App_FenZu;
import com.web.model.App_KaoTiKu;
import com.web.model.App_ShiJianTi;
import com.web.model.App_XueXiZu;
import com.web.model.BanJi;
import com.web.model.JCSJ;
import com.web.model.JiaoShi;
import com.web.model.JiaoXueLou;
import com.web.model.KeCheng;
import com.web.model.XiaoQu;
import com.web.model.XueQi;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.service.App_FenZuService;
import com.web.service.App_KaoTiKuService;
import com.web.service.App_ShiJianTiService;
import com.web.service.App_XueXiZuService;
import com.web.service.BanJiService;
import com.web.service.JCSJService;
import com.web.service.JiaoShiService;
import com.web.service.JiaoXueLouService;
import com.web.service.KeChengService;
import com.web.service.NianFenService;
import com.web.service.XiaoQuService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

import cn.jpush.api.report.UsersResult.User;
import edu.emory.mathcs.backport.java.util.Arrays;
import net.sf.json.JSONObject;
import sun.tools.tree.NewArrayExpression;

@Controller
public class ShiXiController_jiaoshi {
	@Autowired
	private KeChengService kechengService;
	@Autowired
	private JiaoShiService jiaoshiService;
	@Autowired
	private JiaoXueLouService jiaoXueLouService;
	@Autowired
	private XiaoQuService xiaoquService;
	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private App_KaoTiKuService app_KaoTiKuService;
	@Autowired
	private App_ShiJianTiService app_ShiJianTiService;
	@Autowired
	private YuanXiService yuanxiService;
	@Autowired
	private XueQiService xueqiService;
	@Autowired
	private NianFenService nianfenService;
	@Autowired
	private XueShengService xueShengService;
	@Autowired
	private BanJiService banJiService;
	@Autowired
	private App_XueXiZuService app_XueXiZuService;
	@Autowired
	private JCSJService jcsjService;
	@Autowired
	private App_FenZuService app_FenZuService;

	@RequestMapping(value = "getshixilist")
	public ModelAndView getshixilist(HttpServletRequest request, HttpServletResponse response) {
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
		String renkelaoshiid = user.getYonghuid().toString();
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
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("xueqiid", xueQi.getXueqiid().toString());
		map2.put("renkelaoshiid", renkelaoshiid);
		// 获得该老师的所有课程
		List<Map<String, Object>> keChengs = kechengService.getKeChengByRenKeLaoShiID(map2);
		// 课程为空,获取参加的其他实习
		if (keChengs == null || keChengs.isEmpty()) {
			String idStr = "";
			getqitashixi(user, keChengs2, idStr, xueQi.getXueqiid().toString());
			quchong(keChengs2);
			getshijian(keChengs2);
			mView.addObject("kechengs", keChengs2);
			mView.setViewName("jiaoshi/shijiankeguodu");
			return mView;
		}
		// 如果课程不为空
		for (int i = 0; i < keChengs.size(); i++) {
			// 获取某一门课程的实习课
			List<Map<String, Object>> shixi = kechengService
					.selectShiXiByKeChengID((Integer) keChengs.get(i).get("ID"));
			String idString = "";
			if (shixi != null && shixi.size() != 0) {
				for (int j = 0; j < shixi.size(); j++) {
					
					keChengs.get(i).put("shixiid", (Integer) shixi.get(j).get("ID"));
					keChengs.get(i).put("shixixinxi", shixi.get(j));
					String renkelaoshidString[] = shixi.get(j).get("renKeLaoShiID").toString().split(";");
					getlaoshixinxi(keChengs, renkelaoshidString);
					// 获取所有某一门课程的所有实习id
					idString += shixi.get(j).get("ID").toString() + ",";
					try {
						if(shixi.get(j).get("sheZhi")==null) {
							keChengs2.add(keChengs.get(i));
							continue;
						}
						String shezhi = shixi.get(j).get("sheZhi").toString();
						fenzuxinxi(shezhi, keChengs.get(i));
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						keChengs2.add(keChengs.get(i));
						continue;
					}
					keChengs2.add(keChengs.get(i));
				}
			} else {
				// shixi为空，获取除了本次实习外的其它所有实习
				getqitashixi(user, keChengs2, idString, xueQi.getXueqiid().toString());
			}
		}
		quchong(keChengs2);
		getshijian(keChengs2);
		mView.addObject("kechengs", keChengs2);
		mView.setViewName("jiaoshi/shijiankeguodu");
		return mView;
	}

	// 跳转到实习
	@RequestMapping(value = "getshixi")
	public ModelAndView getshixi(HttpServletRequest request, HttpServletResponse response) {
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
		String shijiankeidString = request.getParameter("shijiankeid");
		String fuzerenid = request.getParameter("fuzerenid");
		Map<String, Object> shijiankeMap = kechengService.getshijianke(Integer.parseInt(shijiankeidString));
		KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(shijiankeMap.get("keChengID").toString()));
//		List<Map<String, Object>> xueshengsList = unkaotixuesheng(shijiankeidString);
		shijiankeMap.put("keChengMingCheng", keCheng.getKechengmingcheng());
		mView.addObject("kecheng", shijiankeMap);
		mView.addObject("shijiankeid", shijiankeidString);
		mView.addObject("fuzerenid", fuzerenid);
		mView.addObject("yonghuid", user.getYonghuid());
//		mView.addObject("xueshengs",xueshengsList == null?new ArrayList<Map<String, Object>>():xueshengsList);
		mView.setViewName("jiaoshi/shixi");
		return mView;
	}

	// 得到为选考题学生
	@RequestMapping(value = "unkaotixuesheng")
	public ModelAndView unkaotixuesheng(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String shijiankeidString = request.getParameter("shixiid");
		List<Map<String, Object>> unFenZu = unkaotixuesheng(shijiankeidString);
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
						e.printStackTrace();
					}
					return null;
				}
			} else {
				try {
					response.sendRedirect("logout");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		}
		mView.addObject("kechengid", shijiankeidString);
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.setViewName("jiaoshi/unkaotixuesheng");
		return mView;
	}

	// 跳转到实践课
	@RequestMapping(value = "getshijiankecheng")
	public ModelAndView getshijiankecheng(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String shijiankeidString = request.getParameter("shijiankeid");
		String xiaozurongliang = request.getParameter("xiaozurongliang");
		Map<String, Object> shijiankeMap = kechengService.getshijianke(Integer.parseInt(shijiankeidString));
		KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(shijiankeMap.get("keChengID").toString()));
		shijiankeMap.put("keChengMingCheng", keCheng.getKechengmingcheng());
		shijiankeMap.put("xiaozurongliang", xiaozurongliang);
		mView.addObject("kecheng", shijiankeMap);
		mView.addObject("shijiankeid", shijiankeidString);
		mView.setViewName("jiaoshi/shijianke");
		return mView;
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

	@RequestMapping(value = "updateshixi") // 更新实习
	public ModelAndView updateshixi(HttpServletRequest request, HttpServletResponse response) {
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
		mView.setViewName("jiaoshi/modifyshixi");
		return mView;
	}

	// 获取老师参加的实践课
	private void getqitashixi(YongHu user, List<Map<String, Object>> keChengs2, String idStr, String xueqiid) {
		Map<String, Object> map2 = new HashMap<String, Object>();
		if (idStr == null || idStr.equals("")) {
			map2.put("id", idStr);
		} else {
			map2.put("id", Arrays.asList(idStr.split(",")));
		}
		// 获取除了本次实习外的其它所有实习
		List<Map<String, Object>> allshixi = (List<Map<String, Object>>) kechengService.getallshixi(map2);
		if (allshixi == null || allshixi.isEmpty()) {
			return;
		}
		for (int m = 0; m < allshixi.size(); m++) {
			String renkelaoshidString[] = allshixi.get(m).get("renKeLaoShiID").toString().split(";");
			String renkelaoshiids = "";
			for (String string2 : renkelaoshidString) {
				renkelaoshiids += "," + string2.split(",")[0];
			}
			// 获取老师参加的其他实习,该实习是其他老师创建的
			if ((renkelaoshiids + ",").contains("," + user.getYonghuid().toString() + ",")) {
				String kechengidString = allshixi.get(m).get("keChengID").toString();
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("kechengid", Integer.parseInt(kechengidString));
//				KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(kechengidString));
//				paramMap.put("renkelaoshiid", Integer.parseInt(keCheng.getRenkelaoshiid()));
//				List<Map<String, Object>> kechengMap = kechengService.getKeChengAndShiJianByKeChengID(paramMap);
//				 获取课程和对应的时间
				List<Map<String, Object>> kechengMap = kechengService.getKeChengAndShiJianByKeChengID(paramMap);
				for (int j = 0; j < kechengMap.size(); j++) {
					if (!kechengMap.get(j).get("xueQiID").toString().equals(xueqiid)) {
						continue;
					}
					kechengMap.get(j).put("shixiid", (Integer) allshixi.get(m).get("ID"));
					kechengMap.get(j).put("shixixinxi", allshixi.get(m));
					getlaoshixinxi(kechengMap, renkelaoshidString);
					try {
						if(allshixi.get(m).get("sheZhi")==null) {
							keChengs2.add(kechengMap.get(j));
							continue;
						}
						String shezhi = allshixi.get(m).get("sheZhi").toString();
						fenzuxinxi(shezhi, kechengMap.get(j));
					} catch (Exception e) {
						// TODO: handle exception
						keChengs2.add(kechengMap.get(j));
						e.printStackTrace();
						continue;
					}
					keChengs2.add(kechengMap.get(j));
				}
			}
		}
	}

	// 去重
	private void quchong(List<Map<String, Object>> list) {
		HashSet<Map<String, Object>> h = new HashSet<Map<String, Object>>(list);
		list.clear();
		list.addAll(h);
	}

	// 得到时间
	private void getshijian(List<Map<String, Object>> list) {
		for (int i = 0; i < list.size(); i++) {
			List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
			for (int j = 0; j < list.size(); j++) {
				if (i != j) {
					Map<String, Object> map = new HashMap<String, Object>();
					if (list.get(i).get("ID").toString().equals(list.get(j).get("ID").toString())) {
						// 获取课程的其它节次时间和上课地点
						map = getdidian(list.get(j));
						maps.add(map);
						//移除迭代时会有一个元素始终不能删除，这里新建了一个列表用来存储符合条件的对象
						list.remove(j);
					}
				}
			}
			// 添加本条记录的上课时间和地点
			maps.add(getdidian(list.get(i)));
			list.get(i).put("jiecishijian", maps);
			//会有重复的去重
//			quchong(list);
		}
	}

	// 获取上课地点和节次时间
	private Map<String, Object> getdidian(Map<String, Object> paraMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey((Integer) paraMap.get("jiaoShiID"));
		JiaoXueLou jiaoxuelou = jiaoXueLouService.selectByPrimaryKey(jiaoShi.getJiaoxuelouid());
		XiaoQu xiaoQu = xiaoquService.selectByPrimaryKey(jiaoxuelou.getXiaoQuId());
		JCSJ kaishijieci = jcsjService.selectByPrimaryKey(Integer.parseInt(paraMap.get("kaiShiJieCi").toString()));
		JCSJ jieshujieci = jcsjService.selectByPrimaryKey(Integer.parseInt(paraMap.get("jieShuJieCi").toString()));
		map.put("jiaoxuelouming", jiaoxuelou.getJiaoXueLouMing());
		map.put("jiaoshiming", jiaoShi.getJiaoshiming());
		map.put("xiaoquming", xiaoQu.getMingcheng());
		map.put("kaishizhou", paraMap.get("kaiShiZhou"));
		map.put("jieshuzhou", paraMap.get("jieShuZhou"));
		map.put("kaishijieci", kaishijieci.getJieci());
		map.put("jieshujieci", jieshujieci.getJieci());
		map.put("zhouci", paraMap.get("zhouCi"));
		return map;
	}

	// 获取老师信息
	private void getlaoshixinxi(List<Map<String, Object>> kechengMap, String[] renkelaoshidString) {
		for (int j = 0; j < kechengMap.size(); j++) {
			List<Map<String, Object>> laoshiMap = new ArrayList<Map<String, Object>>();
			for (String string2 : renkelaoshidString) {
				// 查询实习负责人
				if ((string2.split(",")[0]).equals(kechengMap.get(j).get("renKeLaoShiID").toString())) {
					YongHu fuzeren = yongHuService
							.selectYongHuByID(Integer.parseInt(kechengMap.get(j).get("renKeLaoShiID").toString()));
					if (fuzeren == null) {
						return;
					}
					Map<String, Object> fuzenrenMap = new HashMap<String, Object>();
					fuzenrenMap.put("fuzerenxingming", fuzeren.getYonghuxingming());
					fuzenrenMap.put("fuzerenid", fuzeren.getYonghuid());
					kechengMap.get(j).put("fuzeren", fuzenrenMap);
					continue;
				}
				// 查询其他参与老师姓名和id
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("laoshiid", string2.split(",")[0]);
				YongHu yongHu = yongHuService.selectYongHuByID(Integer.parseInt(string2.split(",")[0]));
				if (yongHu == null) {
					map.put("laoshixingming", "");
				} else {
					map.put("laoshixingming", yongHu.getYonghuxingming());
				}
				laoshiMap.add(map);
			}
			kechengMap.get(j).put("laoshixinxi", laoshiMap);
		}
	}

	private void fenzuxinxi(String shezhi, Map<String, Object> kecheng) {
		String[] fenzushezhi = shezhi.split(";");
		for (String string2 : fenzushezhi) {
			String[] strings = string2.split(",");
			switch (strings[0]) {
			case "1":
				kecheng.put("fenxiaozu", strings[1]);
				if (strings.length == 3) {
					kecheng.put("xiaozurongliang", strings[2]);
				}
				break;
			case "2":
				kecheng.put("fendazu", strings[1]);
				if (strings.length == 3) {
					kecheng.put("dazuzhangid", strings[2]);
					XueSheng xsSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(strings[2]));
					if (xsSheng != null) {
						kecheng.put("dazuzhang", xsSheng.getXingming());
					}
				}
				break;
			case "4":
				kecheng.put("isyouti", strings[1]);
				// 判断课程类型
				// 实践课
				if (strings[1].equals("0")) {
					kecheng.put("shijiankeleixing", "shijianke");
				}
				// 实习
				if (strings[1].equals("1")) {
					kecheng.put("shijiankeleixing", "shixi");
				}
				if (strings.length == 3) {
					kecheng.put("fentileixing", strings[2]);
				}
				break;
			case "3":
				kecheng.put("shifoufenzu", strings[1]);
				if (strings.length == 3) {
					kecheng.put("fenzuleixing", strings[2]);
				}
				break;
			default:
				break;
			}
		}
	}

	@RequestMapping(value = "shezhi")
	public ModelAndView shezhi(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		// 查询老师自己添加的实践课
		List<Map<String, Object>> list = getshijianke(user);
		List<YongHu> yongHus = yongHuService.getAllByYuanXiID(user.getYuanxiid());
		mView.addObject("yonghus", yongHus);
		mView.addObject("shijiankelist", list);
		mView.addObject("yonghuid", user.getYonghuid());
		mView.setViewName("jiaoshi/shezhi");
		return mView;
	}

	// 得到用户信息
	@ResponseBody
	@RequestMapping(value = "getyonghuinfo")
	public JSONObject getyonghuinfo(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		List<YongHu> yongHus = yongHuService.getAllByYuanXiID(user.getYuanxiid());
		jsonObject.put("data", yongHus);
		return jsonObject;
	}

	// 保存设置
	// 不保存是否分组和分组方式，在实践题里面单设
	@ResponseBody
	@RequestMapping(value = "saveshezhi")
	public JSONObject saveshezhi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		JSONObject jsonObject = new JSONObject();
		// 选择课程
		String shijiankeidString = request.getParameter("shijiankeid");
		// 选择一个老师还是多个老师参加
		String xuanzelaoshi = request.getParameter("leixing");
		// 获取老师id
//		String laoshiid [] = request.getParameterValues("laoshiid");
		// 选择是否有题，有题的是实习，无题的是实践课
		String isxuanti = request.getParameter("isxuanti");
		// 实习设置
		// 选择一题一人还是一题多人，只有在有题的情况下才可选
		String xuantifangshi = request.getParameter("xuantifangshi");
		// 选择是否分组
//		String shifoufenzu = request.getParameter("shifoufenzu");
		// 只有在选择分组的情况下才能选择是否按题目分组
//		String fenzufangshi = request.getParameter("fenzufangshi");
		// 实践课设置
		// 无题的情况下，是否分大组
		String fendazu = request.getParameter("fendazu");
		// 不分大组的情况下，需要制定大组长
		String dazuzhangid = request.getParameter("dazuzhangid");
		// 是否分小组
		String fenxiaozu = request.getParameter("fenxiaozu");
		// 分小组的情况下，需要制定小组容量
		String rongliang = request.getParameter("rongliang");
		// 获取参加课程的而老师和容量
		String renkelaoshids = request.getParameter("renkelaoshiids");
		String shezhiString = "";
		String renkelaoshiidString = "";
		Map<String, Object> shijiankeMap = kechengService.getshijianke(Integer.parseInt(shijiankeidString));
		if (shijiankeMap == null) {
			jsonObject.put("status", "noshijianke");
			return jsonObject;
		}
		if (xuanzelaoshi.equals("0")) {
			renkelaoshiidString += user.getYonghuid() + ",;";
		} else {
			renkelaoshiidString = renkelaoshids;
		}

		if (isxuanti.equals("1")) {
			shezhiString = "4," + isxuanti + "," + xuantifangshi + ";";
		}
		if (isxuanti.equals("0")) {
			shezhiString = "4," + isxuanti + ";";
			if (fendazu.equals("0")) {
				shezhiString += "2,0," + dazuzhangid + ";";
			} else {
				shezhiString += "2,1;";
			}
			if (fenxiaozu.equals("1")) {
				shezhiString += "1,1," + rongliang + ";";
			} else {
				shezhiString += "1,0;";
			}
		}
		shijiankeMap.put("renkelaoshiid", renkelaoshiidString);
		shijiankeMap.put("shezhi", shezhiString);
		shijiankeMap.put("id", Integer.parseInt(shijiankeidString));
		try {
			int i = kechengService.updateshijianke(shijiankeMap);
			if (i != 0) {
				jsonObject.put("status", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	// 得到未分组学生
	@ResponseBody
	@RequestMapping(value = "getunfenzustu")
	public JSONObject getunfenzustu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String shijiankeid = request.getParameter("shijiankeid");
		List<Map<String, Object>> xueshengList = unfenzu(shijiankeid);
		jsonObject.put("xueshengs", xueshengList);
		return jsonObject;
	}

	private List<Map<String, Object>> unfenzu(String shijiankeid) {
		Map<String, Object> shijiankemap = kechengService.getshijianke(Integer.parseInt(shijiankeid));
		List<String> banjiids = kechengService.getByAllBanJiIDByID(shijiankemap.get("keChengID").toString());
		List<String> xueshengids = kechengService.getAllMianXiuIDByID(shijiankemap.get("keChengID").toString());
		List<String> xueshengids2 = kechengService.getAllXuanXiuIDByID(shijiankemap.get("keChengID").toString());
//		List<Map<String, Object>> dazuList = app_FenZuService.getDaZu(Integer.parseInt(shijiankeid));
		List<Map<String, Object>> unFenZu = new ArrayList<>();
		String chenguanIDs = "";
//		for (int i = 0; i < dazuList.size(); i++) {
		List<App_XueXiZu> xiaozulist = app_XueXiZuService.getXueXiZuByKeCheng(Integer.parseInt(shijiankeid));
		for (App_XueXiZu app_XueXiZu : xiaozulist) {
			chenguanIDs += app_XueXiZu.getChengYuanLieBiao().substring(1);
		}
//		}
		List<XueSheng> xueShengss = new ArrayList<XueSheng>();
		// 根据班级得到学生
		for (String banjiid : banjiids) {
			List<XueSheng> xShengs = xueShengService.getAllByBanJiID(Integer.parseInt(banjiid));
			for (XueSheng xueSheng : xShengs) {
				xueShengss.add(xueSheng);
			}
		}
		// 得到选修学生
		for (String xueshengid : xueshengids2) {
			XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
			if (xs == null) {
				continue;
			}
			xueShengss.add(xs);
		}
		// 去掉免修学生
		List<XueSheng> newxueshengList = new ArrayList<XueSheng>();
		for (XueSheng xs : xueShengss) {
			if (xueshengids.isEmpty()) {
				break;
			}
			for (String xueshengid : xueshengids) {
				if (!xs.getXueshengid().toString().equals(xueshengid)) {
					newxueshengList.add(xs);
				}
			}
		}
		List<XueSheng> xueShengs = new ArrayList<>(xueShengss);
		for (int j = 0; j < newxueshengList.size(); j++) {
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
	
	

	private List<Map<String, Object>> getshijianke(YongHu user) {
		List<Map<String, Object>> keChengs2 = new ArrayList<>();
		String renkelaoshiid = user.getYonghuid().toString();
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
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("xueqiid", xueQi.getXueqiid().toString());
		map2.put("renkelaoshiid", renkelaoshiid);
		// 获得该老师的所有课程
		List<Map<String, Object>> keChengs = kechengService.getKeChengByRenKeLaoShiID(map2);
		// 如果课程不为空
		for (int i = 0; i < keChengs.size(); i++) {
			// 获取某一门课程的实习课
			// 该实践课的负责人都是该用户
			List<Map<String, Object>> shixi = kechengService
					.selectShiXiByKeChengID((Integer) keChengs.get(i).get("ID"));
			String idString = "";
			if (shixi != null && shixi.size() != 0) {
				for (int j = 0; j < shixi.size(); j++) {
					String shezhi = "";
					keChengs.get(i).put("shixiid", (Integer) shixi.get(j).get("ID"));
					JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey((Integer) keChengs.get(i).get("jiaoShiID"));
					JiaoXueLou jiaoxuelou = jiaoXueLouService.selectByPrimaryKey(jiaoShi.getJiaoxuelouid());
					XiaoQu xiaoQu = xiaoquService.selectByPrimaryKey(jiaoxuelou.getXiaoQuId());
					keChengs.get(i).put("jiaoxuelouming", jiaoxuelou.getJiaoXueLouMing());
					keChengs.get(i).put("jiaoshiming", jiaoShi.getJiaoshiming());
					keChengs.get(i).put("xiaoquming", xiaoQu.getMingcheng());
					keChengs.get(i).put("shixixinxi", shixi.get(j));
					String renkelaoshidString[] = shixi.get(j).get("renKeLaoShiID").toString().split(";");
					getlaoshixinxi(keChengs, renkelaoshidString);
					// 获取所有某一门课程的所有实习id
					idString += shixi.get(j).get("ID").toString() + ",";
					try {
						if(shixi.get(j).get("sheZhi")==null) {
							keChengs2.add(keChengs.get(i));
							continue;
						}
						shezhi = shixi.get(j).get("sheZhi").toString();
						fenzuxinxi(shezhi, keChengs.get(i));
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
					keChengs2.add(keChengs.get(i));
				}
			} else {
				// shixi为空，跳过
				continue;
			}
		}
		quchong(keChengs2);
		getshijian(keChengs2);
		return keChengs2;
	}

	@RequestMapping(value = "addshijianke") // 添加实习
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
		mView.setViewName("jiaoshi/addshixi");
		return mView;
	}

	@ResponseBody
	@RequestMapping(value = "saveshixi") // 保存实习
	public JSONObject saveshixi(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		String kechengid = request.getParameter("shixi");
		List<Map<String, Object>> shixiList = kechengService.selectShiXiByKeChengID(Integer.parseInt(kechengid));
		if (shixiList.size() >= 1) {
			jsonObject.put("status", "existed");
			return jsonObject;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("kechengid", kechengid);
		map.put("renkelaoshiid", user.getYonghuid()+",;");
		int i = kechengService.insertshixi(map);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	@RequestMapping(value = "kaotilist") // 查看所有考题
	public ModelAndView kaotilist(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String shixiid = request.getParameter("shixiid");
		List<Map<String, Object>> allshijiantiList = app_ShiJianTiService
				.getShiJianTiByShiXiId(Integer.parseInt(shixiid));
		mView.addObject("kaotis", allshijiantiList);
		mView.addObject("shixiid", shixiid);
		mView.setViewName("jiaoshi/kaoti");
		return mView;
	}

	@RequestMapping(value = "laoshikaoti") // 查询老师自己添加的考题
	public ModelAndView laoshikaoti(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		ModelAndView mView = new ModelAndView();
		String shixiid = request.getParameter("shixiid");
		List<Map<String, Object>> kaotis = app_ShiJianTiService.getKaoTiByLaoShiId(user.getYonghuid(),
				Integer.parseInt(shixiid));
		mView.addObject("kaotis", kaotis);
		mView.addObject("shixiid", shixiid);
		mView.setViewName("jiaoshi/laoshikaoti");
		return mView;
	}

	@RequestMapping(value = "laoshixuanti") // 老师从题库中选择实践题
	public ModelAndView laoshixuanti(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		ModelAndView mView = new ModelAndView();
		List<App_KaoTiKu> kaotis = app_KaoTiKuService.getShiJianTiByLaoShiId(user.getYonghuid());
		String shixiid = request.getParameter("shixiid");
		mView.addObject("kaotis", kaotis);
		mView.addObject("shixiid", shixiid);
		mView.setViewName("jiaoshi/laoshixuanti");
		return mView;
	}

	@ResponseBody
	@RequestMapping(value = "saveshijianti") // 保存选题
	public JSONObject saveshijianti(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		String timuid[] = request.getParameterValues("kaotikuid");
		String shixiid = request.getParameter("shixiid");
		List<Map<String, Object>> shiJianTis = app_ShiJianTiService.getKaoTiByLaoShiId(user.getYonghuid(),
				Integer.parseInt(shixiid));
		for (int j = 0; j < shiJianTis.size(); j++) {
			if (shiJianTis.get(j).get("shiXiID").toString().equals(shixiid)) {
				for (int k = 0; k < timuid.length; k++) {
					if (shiJianTis.get(j).get("kaotikuid").toString().equals(timuid[k])) {
						jsonObject.put("status", "yixuan");
						return jsonObject;
					}
				}
			}
		}
		for (int i = 0; i < timuid.length; i++) {
			App_KaoTiKu app_KaoTiKu = app_KaoTiKuService.selectByPrimaryKey(Integer.parseInt(timuid[i]));
			if (app_KaoTiKu == null) {
				jsonObject.put("status", "nokaoti");
				return jsonObject;
			}
			App_ShiJianTi app_ShiJianTi = new App_ShiJianTi();
			app_ShiJianTi.setTimuid(Integer.parseInt(timuid[i]));
			app_ShiJianTi.setShixiid(Integer.parseInt(shixiid));
			app_ShiJianTi.setMingcheng(app_KaoTiKu.getMingcheng());
			app_ShiJianTi.setShifoushenhe(0);
			app_ShiJianTi.setShezhi("0,;");
			int j = app_ShiJianTiService.insert(app_ShiJianTi);
			if (j != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		}
		return jsonObject;
	}

	@RequestMapping(value = "addshijianti") // 向考题库中添加考题
	public ModelAndView addshijianti(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ModelAndView mView = new ModelAndView();
		String shixiidString = request.getParameter("shixiid");
		mView.addObject("shixiid", shixiidString);
		mView.setViewName("jiaoshi/addkaoshiti");
		return mView;

	}

	@ResponseBody
	@RequestMapping(value = "savekaoti") // 保存老师添加的考题到考题库中
	public JSONObject savekaoti(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		String kaotimingcheng = request.getParameter("kaotimingcheng");
		String shixiid = request.getParameter("shixiid");
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		List<App_KaoTiKu> kaotis = app_KaoTiKuService.getShiJianTiByLaoShiId(user.getYonghuid());
		for (int i = 0; i < kaotis.size(); i++) {
			if (kaotis.get(i).getMingcheng().equals(kaotimingcheng)) {
				jsonObject.put("status", "samename");
				return jsonObject;
			}
		}
		App_KaoTiKu app_KaoTiKu = new App_KaoTiKu();
		app_KaoTiKu.setMingcheng(kaotimingcheng);
		app_KaoTiKu.setShangchuanrenid(user.getYonghuid());
		int i = app_KaoTiKuService.insert(app_KaoTiKu);
		jsonObject.put("shixiid", shixiid);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	// 审核
	@ResponseBody
	@RequestMapping(value = "shenhe") // 保存老师添加的考题到考题库中
	public JSONObject shenhe(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		String kaotiid = request.getParameter("kaotiid");
		String shixiid = request.getParameter("shixiid");
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		if (app_ShiJianTi == null) {
			jsonObject.put("status", "nokaoti");
			return jsonObject;
		}
		app_ShiJianTi.setShifoushenhe(1);
		int i = app_ShiJianTiService.update(app_ShiJianTi);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	// 发布
	@ResponseBody
	@RequestMapping(value = "fabu") // 保存老师添加的考题到考题库中
	public JSONObject fabu(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		String kaotiid = request.getParameter("kaotiid");
		String shixiid = request.getParameter("shixiid");
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		if (app_ShiJianTi == null) {
			jsonObject.put("status", "nokaoti");
			return jsonObject;
		}
		app_ShiJianTi.setShifoushenhe(3);
		int i = app_ShiJianTiService.update(app_ShiJianTi);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	@RequestMapping(value = "updateshijianti") // 更新已选考题的名称和容量
	public ModelAndView updateshijianti(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mView = new ModelAndView();
		String kaotiid = request.getParameter("id");
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		mView.addObject("kaoti", app_ShiJianTi);
		mView.setViewName("jiaoshi/updatekaoti");
		return mView;
	}

	@ResponseBody
	@RequestMapping(value = "saveupdateshijianti") // 保存更新的shijianti属性值
	public JSONObject saveupdateshijianti(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String fabumingcheng = request.getParameter("fabumingcheng");
		String rongliangString = request.getParameter("rongliang");
		String shifoufenzu = request.getParameter("shifoufenzu");
		String fenzufangshi = request.getParameter("fenzufangshi");
		String dazuzhangid = request.getParameter("dazuzhangid");
		String kaotiid = request.getParameter("kaotiid");
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		if(app_ShiJianTi==null) {
			jsonObject.put("status", "error");
			return jsonObject;
		}
		App_KaoTiKu app_KaoTiKu = app_KaoTiKuService.selectByPrimaryKey(app_ShiJianTi.getTimuid());
		if(app_KaoTiKu==null) {
			jsonObject.put("status", "error");
			return jsonObject;
		}
		List<Map<String, Object>> kaotis = app_ShiJianTiService.getKaoTiByLaoShiId(app_KaoTiKu.getShangchuanrenid(),app_ShiJianTi.getShixiid());
		for (int i = 0; i < kaotis.size(); i++) {
			if (kaotis.get(i).get("fabumingcheng").equals(fabumingcheng)
					&& !kaotis.get(i).get("kaotiid").toString().equals(kaotiid)) {
				jsonObject.put("status", "samename");
				return jsonObject;
			}
		}
		String shezhiString = "";
		if (shifoufenzu.equals("0")) {
			shezhiString = "0,;";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("kechengid", app_ShiJianTi.getShixiid());
			map.put("zumingcheng", app_ShiJianTi.getMingcheng());
			App_FenZu app_FenZu = app_FenZuService.getByKeChengIdAndMingCheng(map);
			if (app_FenZu == null) {
				app_FenZu = new App_FenZu();
				app_FenZu.setZuMingCheng(app_ShiJianTi.getMingcheng());
				app_FenZu.setShiJianKeChengId(app_ShiJianTi.getShixiid());
				app_FenZu.setLaoshiid(app_KaoTiKu.getShangchuanrenid());
				app_FenZu.setZuZhangId(Integer.parseInt(dazuzhangid));
				app_FenZuService.insertdazu(app_FenZu);
			}else {
				app_FenZu.setZuZhangId(Integer.parseInt(dazuzhangid));
				app_FenZuService.update(app_FenZu);
			}
			try {
				if (app_ShiJianTi.getXueshengid() != null) {
					String xueshengidString = app_ShiJianTi.getXueshengid().toString()
							.substring(1);
					List<Map<String, Object>> xueshengList = xueShengService
							.selectByXueShengIDs2(Arrays.asList(xueshengidString.split(",")));
					insertxuexizu(xueshengidString, app_ShiJianTi.getShixiid().toString(),app_FenZu.getId().toString(),xueshengList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (shifoufenzu.equals("1")) {
			shezhiString = "1," + fenzufangshi + ";";
		}
		app_ShiJianTi.setRongliang(Integer.parseInt(rongliangString));
		app_ShiJianTi.setMingcheng(fabumingcheng);
		app_ShiJianTi.setShezhi(shezhiString);
		int i = app_ShiJianTiService.update(app_ShiJianTi);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	// 审核
	@ResponseBody
	@RequestMapping(value = "delshijianti") // 保存老师添加的考题到考题库中
	public JSONObject delshijianti(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		String kaotiid = request.getParameter("kaotiid");
		String shixiid = request.getParameter("shixiid");
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		if (app_ShiJianTi == null) {
			jsonObject.put("status", "nokaoti");
			return jsonObject;
		}
		if (app_ShiJianTi.getShifoushenhe() != 0) {
			jsonObject.put("status", "limited");
			return jsonObject;
		}
		int i = app_ShiJianTiService.delete(Integer.parseInt(kaotiid));
		jsonObject.put("shixiid", shixiid);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	// 老师选学生
	@RequestMapping(value = "laoshixuanxuesheng") // 保存老师添加的考题到考题库中
	public ModelAndView laoshixuanxuesheng(HttpServletRequest request, HttpServletResponse response) {
		String kaotiid = request.getParameter("id");
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mviewAndView = new ModelAndView();
		List<Map<String, Object>> xueshengList = new ArrayList<Map<String, Object>>();
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		if (app_ShiJianTi == null) {
			mviewAndView.addObject("status", "nokaoti");
			return mviewAndView;
		}
		if (app_ShiJianTi.getXueshengid() == null || "".equals(app_ShiJianTi.getXueshengid())) {
			mviewAndView.addObject("xueshengs", xueshengList);
		} else {
			String xueshengids = app_ShiJianTi.getXueshengid();
			xueshengList = getxueshenginfo(xueshengids);
			mviewAndView.addObject("xueshengs", xueshengList);
		}
		mviewAndView.addObject("kaoti", app_ShiJianTi);
		mviewAndView.setViewName("jiaoshi/xuanxuesheng");
		return mviewAndView;
	}

	// 保存老师已选学生
	@ResponseBody
	@RequestMapping(value = "savelaoshixuanxuesheng") // 保存老师添加的考题到考题库中
	public JSONObject savelaoshixuanxuesheng(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String kaotiid = request.getParameter("kaotiid");
		String xueshengidString[] = request.getParameterValues("xueshengid");
		String xueshengids = "";
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		if (app_ShiJianTi == null) {
			jsonObject.put("status", "nokaoti");
			return jsonObject;
		}
		for (int k = 0; k < xueshengidString.length; k++) {
			xueshengids += xueshengidString[k] + ",";
		}
		app_ShiJianTi.setXueshengid("," + xueshengids);
		app_ShiJianTi.setShifoushenhe(4);
		int i = app_ShiJianTiService.update(app_ShiJianTi);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	// 考题详情
	@RequestMapping(value = "detail") // 保存老师添加的考题到考题库中
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mviewAndView = new ModelAndView();
		String kaotiid = request.getParameter("id");
		List<Map<String, Object>> xueshengList = new ArrayList<Map<String, Object>>();
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		if (app_ShiJianTi == null) {
			mviewAndView.addObject("status", "nokaoti");
			return mviewAndView;
		}
		if (app_ShiJianTi.getXueshengid() == null || "".equals(app_ShiJianTi.getXueshengid())) {
			mviewAndView.addObject("xueshengs", xueshengList);
		} else {
			String xueshengids = app_ShiJianTi.getXueshengid();
			xueshengList = getxueshenginfo(xueshengids);
			mviewAndView.addObject("xueshengs", xueshengList);
		}
		mviewAndView.addObject("kaoti", app_ShiJianTi);
		mviewAndView.setViewName("jiaoshi/detailkaoti");
		return mviewAndView;
	}

	// 分组
	@RequestMapping(value = "fenzu") // 保存老师添加的考题到考题库中
	public ModelAndView fenzu(HttpServletRequest request, HttpServletResponse response) {
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
		String shixiid = request.getParameter("shixiid");
		List<Map<String, Object>> kaotifenzuList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> nokaotifenzuList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> kaotisList = app_ShiJianTiService.getKaoTiByLaoShiId(user.getYonghuid(),
				Integer.parseInt(shixiid));
		if (kaotisList == null || kaotisList.isEmpty()) {
			return mView;
		}
		for (int i = 0; i < kaotisList.size(); i++) {
			if (!kaotisList.get(i).get("shiFouShenHe").toString().equals("4")) {
				continue;
			} else {
				if (kaotisList.get(i).get("sheZhi") != null) {
					String shezhiString = kaotisList.get(i).get("sheZhi").toString();
					for (String string : shezhiString.split(";")) {
						String isfenzu = string.split(",")[0];
						if (isfenzu.equals("0")) {
							continue;
						}
						if (isfenzu.equals("1")) {
							if (string.split(",")[1].equals("0")) {
								nokaotifenzuList.add(kaotisList.get(i));
							}
							if (string.split(",")[1].equals("1")) {
								kaotifenzuList.add(kaotisList.get(i));
							}
						}
					}
				}
			}
		}
		// 获取按课题分组的学生信息
		String xueshengids = "";
		List<App_XueXiZu> xueXiZus = app_XueXiZuService.getXueXiZuByKeCheng(Integer.parseInt(shixiid));
		for (int i = 0; i < kaotifenzuList.size(); i++) {
			try {
				String newxueshengidString = "";
				String xueshengid = kaotifenzuList.get(i).get("xueShengID").toString().substring(1);
				newxueshengidString = quchuyifenzustu(xueshengid, xueXiZus);
				List<Map<String, Object>> xueshengs = xueShengService
						.selectByXueShengIDs2(Arrays.asList(newxueshengidString.split(",")));
				kaotifenzuList.get(i).put("xuesheng",
						xueshengs == null ? new ArrayList<Map<String, Object>>() : xueshengs);
			} catch (Exception e) {
				kaotifenzuList.get(i).put("xuesheng", "");
			}
		}
		// 获取不按课题分组的学生信息
		for (int i = 0; i < nokaotifenzuList.size(); i++) {
			// resultType的返回值为map类型时，字段为空，则不返回该字段
			try {
				if (nokaotifenzuList.get(i).get("xueShengID") == null) {
					continue;
				}
			} catch (Exception e) {
				continue;
			}
			xueshengids += nokaotifenzuList.get(i).get("xueShengID").toString().substring(1);
		}
		String newxueshengidString2 = quchuyifenzustu(xueshengids, xueXiZus);
		List<Map<String, Object>> list = xueShengService
				.selectByXueShengIDs2(Arrays.asList(newxueshengidString2.split(",")));
		mView.addObject("xueshenglist2", list == null ? new ArrayList<Map<String, Object>>() : list);
		mView.addObject("kaotis", kaotisList);
		mView.addObject("kaotifenzu", kaotifenzuList);
		mView.addObject("nokaotifenzu", nokaotifenzuList);
		mView.addObject("shixiid", shixiid);
		mView.setViewName("jiaoshi/fenzu");
		return mView;
	}

	// 分组
	@RequestMapping(value = "kaotifenzu")
	public ModelAndView kaotifenzu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String kaotiid = request.getParameter("id");
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		if (app_ShiJianTi.getXueshengid() != null) {
			String xueshengidString = app_ShiJianTi.getXueshengid().substring(1);
			List<Map<String, Object>> xueshengList = xueShengService
					.selectByXueShengIDs2(Arrays.asList(xueshengidString.split(",")));
			mView.addObject("xueshenglist2",
					xueshengList == null ? new ArrayList<Map<String, Object>>() : xueshengList);
		}
		mView.addObject("kaoti", app_ShiJianTi);
		mView.setViewName("jiaoshi/kaotifenzu");
		return mView;
	}

	// 保存老师已选学生
	@ResponseBody
	@RequestMapping(value = "savekaotifenzu")
	public JSONObject savekaotifenzu(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		String dazuming = request.getParameter("zuMingCheng");
		String dazuzhangid = request.getParameter("dazuzhangid");
		String xueshengid[] = request.getParameterValues("xueshengid");
		String shixiid = request.getParameter("shixiid");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kechengid", shixiid);
		map.put("zumingcheng", dazuming);
		App_FenZu app_FenZu = app_FenZuService.getByKeChengIdAndMingCheng(map);
		if (app_FenZu == null) {
			app_FenZu = new App_FenZu();
			app_FenZu.setZuMingCheng(dazuming);
			app_FenZu.setZuZhangId(Integer.parseInt(dazuzhangid));
			app_FenZu.setShiJianKeChengId(Integer.parseInt(shixiid));
			app_FenZu.setLaoshiid(user.getYonghuid());
			app_FenZuService.insertdazu(app_FenZu);
		} else {
			app_FenZu.setZuZhangId(Integer.parseInt(dazuzhangid));
			app_FenZuService.update(app_FenZu);
		}
		if (xueshengid == null) {
			return jsonObject;
		}
		String xueshengidString = "";
		for (int i = 0; i < xueshengid.length; i++) {
			xueshengidString += xueshengid[i] + ",";
		}
		List<Map<String, Object>> xueshengList = xueShengService
				.selectByXueShengIDs2(Arrays.asList(xueshengidString.split(",")));
		for (int i = 0; i < xueshengid.length; i++) {
			List<App_XueXiZu> xueXiZus = app_XueXiZuService.selectXueXiZu(Integer.parseInt(shixiid),
					Integer.parseInt(xueshengid[i]));
			if (xueXiZus == null || xueXiZus.isEmpty()) {
				App_XueXiZu app_XueXiZu = new App_XueXiZu();
				app_XueXiZu.setChengYuanLieBiao("," + xueshengid[i] + ",");
				app_XueXiZu.setFenZuId(app_FenZu.getId());
				app_XueXiZu.setXiaoZuZhangId(Integer.parseInt(xueshengid[i]));
				app_XueXiZu.setShiJianKeChengId(Integer.parseInt(shixiid));
				app_XueXiZu.setXueXiZuMing(getxueshengname(xueshengList, xueshengid[i]));
				app_XueXiZu.setZhuangTai(1);
				int j = app_XueXiZuService.insert(app_XueXiZu);
				if (j != 0) {
					jsonObject.put("status", "success");

				} else {
					jsonObject.put("status", "fail");
				}
			} else {
//				jsonObject.put("status", "existed");
				xueXiZus.get(0).setFenZuId(app_FenZu.getId());
				int k = app_XueXiZuService.update(xueXiZus.get(0));
				if (k != 0) {
					jsonObject.put("status", "success");

				} else {
					jsonObject.put("status", "fail");
				}
			}
		}
		return jsonObject;
	}

	// 查看考题分组信息
	@RequestMapping(value = "kaotifenzuxinxi")
	public ModelAndView kaotifenzuxinxi(HttpServletRequest request, HttpServletResponse response) {
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
		String shixiid = request.getParameter("shixiid");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kechengid", shixiid);
		map.put("laoshiid", user.getYonghuid());
		List<Map<String, Object>> list = app_FenZuService.getByKeChengIDAndLaoShiID(map);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("zuZhangID") == null) {
				continue;
			}
			list.get(i).put("dazuzhangming", xueShengService
					.selectByPrimaryKey(Integer.parseInt(list.get(i).get("zuZhangID").toString())).getXingming());
			List<Map<String, Object>> xueshenglist = getxiaozu(list.get(i).get("fenZuID").toString());
			list.get(i).put("xueshengxinxi", xueshenglist);
		}
		mView.addObject("fenzuinfo", list);
		mView.setViewName("jiaoshi/fenzuinfo");
		return mView;
	}

	private List<Map<String, Object>> getxiaozu(String fenZuId) {
		List<Map<String, Object>> xiaozuList = app_XueXiZuService.selectXiaoZu(Integer.parseInt(fenZuId));
		List<Map<String, Object>> xueshenglist = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < xiaozuList.size(); i++) {
			String xueshengids = "";
			xueshengids += xiaozuList.get(i).get("chengYuanIDLieBiao").toString().substring(1);
			xueshenglist = xueShengService.selectByXueShengIDs2(Arrays.asList(xueshengids.split(",")));
		}
		return xueshenglist;
	}

	private String getxueshengname(List<Map<String, Object>> xueshengList, String xueshengid) {
		String xueshengxingmingString = "";
		for (int i = 0; i < xueshengList.size(); i++) {
			if (!xueshengList.get(i).get("ID").toString().equals(xueshengid)) {
//					xueshengxingmingString =  xueshengList.get(i).get("xingming").toString();
//					return xueshengxingmingString;
				continue;
			}
			xueshengxingmingString = xueshengList.get(i).get("xingming").toString();
		}
		return xueshengxingmingString;
	}

	// 查询学生信息
	private List<Map<String, Object>> getxueshenginfo(String xueshengids) {
		List<Map<String, Object>> xueshengMaps = xueShengService
				.selectByXueShengIDs2(Arrays.asList(xueshengids.split(",")));
		return xueshengMaps == null ? new ArrayList<Map<String, Object>>() : xueshengMaps;
	}

	// 去除已分组学生
	private String quchuyifenzustu(String xueshengids, List<App_XueXiZu> xueXiZus) {
		String xueshengidString = "";
		String newxueshengidString = "";
		if (xueshengids == "") {
			return xueshengids;
		}
		String xueshengid[] = xueshengids.split(",");
		for (int i = 0; i < xueXiZus.size(); i++) {
			xueshengidString += xueXiZus.get(i).getChengYuanLieBiao().substring(1);
		}
		for (int i = 0; i < xueshengid.length; i++) {
			if (("," + xueshengidString).contains("," + xueshengid[i] + ",")) {
				continue;
			}
			newxueshengidString += xueshengid[i] + ",";
		}
		return newxueshengidString;
	}

	// 插入不分组学生到app_xuexizu
	private void insertxuexizu(String xueshengids, String shixiid,String fenzuid,List<Map<String, Object>> xueshengList) {
		if (xueshengids == null || "".equals(xueshengids)) {
			return;
		}
		String xueshengidString[] = xueshengids.trim().split(",");
		for (int i = 0; i < xueshengidString.length; i++) {
			List<App_XueXiZu> xueXiZus = app_XueXiZuService.selectXueXiZu(Integer.parseInt(shixiid),
					Integer.parseInt(xueshengidString[i]));
			if (xueXiZus == null || xueXiZus.isEmpty()) {
				App_XueXiZu app_XueXiZu = new App_XueXiZu();
				app_XueXiZu.setChengYuanLieBiao("," + xueshengidString[i] + ",");
				app_XueXiZu.setXiaoZuZhangId(Integer.parseInt(xueshengidString[i]));
				app_XueXiZu.setShiJianKeChengId(Integer.parseInt(shixiid));
				app_XueXiZu.setXueXiZuMing(getxueshengname(xueshengList, xueshengidString[i]));
				app_XueXiZu.setFenZuId(Integer.parseInt(fenzuid));
				app_XueXiZu.setZhuangTai(1);
				int j = app_XueXiZuService.insert(app_XueXiZu);
			} else {
				xueXiZus.get(0).setFenZuId(Integer.parseInt(fenzuid));
				app_XueXiZuService.update(xueXiZus.get(0));
			}
		}
	}

	// 得到未选题学生
	private List<Map<String, Object>> unkaotixuesheng(String shijiankeid) {
		Map<String, Object> shijiankemap = kechengService.getshijianke(Integer.parseInt(shijiankeid));
		List<String> banjiids = kechengService.getByAllBanJiIDByID(shijiankemap.get("keChengID").toString());
		List<String> xueshengids = kechengService.getAllMianXiuIDByID(shijiankemap.get("keChengID").toString());
		List<String> xueshengids2 = kechengService.getAllXuanXiuIDByID(shijiankemap.get("keChengID").toString());
		List<Map<String, Object>> unFenZu = new ArrayList<>();
		String chenguanIDs = "";
		List<App_ShiJianTi> kaotilist = app_ShiJianTiService.getKaoTiList(Integer.parseInt(shijiankeid));
		for (App_ShiJianTi app_shijianti : kaotilist) {
			if (app_shijianti.getXueshengid() == null) {
				continue;
			}
			chenguanIDs += app_shijianti.getXueshengid().substring(1);
		}
		List<XueSheng> xueShengss = new ArrayList<XueSheng>();
		// 根据班级得到学生
		for (String banjiid : banjiids) {
			List<XueSheng> xShengs = xueShengService.getAllByBanJiID(Integer.parseInt(banjiid));
			for (XueSheng xueSheng : xShengs) {
				xueShengss.add(xueSheng);
			}
		}
		// 得到选修学生
		for (String xueshengid : xueshengids2) {
			XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
			if (xs == null) {
				continue;
			}
			xueShengss.add(xs);
		}
		// 去掉免修学生
		List<XueSheng> newxueshengssList = new ArrayList<XueSheng>();
		for (XueSheng xs : xueShengss) {
			if (xueshengids.isEmpty()) {
				newxueshengssList.add(xs);
			}
			for (String xueshengid : xueshengids) {
				if (!xs.getXueshengid().toString().equals(xueshengid)) {
//					xueShengss.remove(xs);
					newxueshengssList.add(xs);
				}
			}
		}
		List<XueSheng> xueShengs = new ArrayList<>(xueShengss);
		for (int j = 0; j < newxueshengssList.size(); j++) {
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
	
	// 得到未分组学生
		@ResponseBody
		@RequestMapping(value = "getbufenzustu")
		public JSONObject getbufenzustu(HttpServletRequest request, HttpServletResponse response) {
			if (!Util.checkSession(request)) {
				try {
					response.sendRedirect("logout");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			JSONObject jsonObject = new JSONObject();
			YongHu user = (YongHu) request.getSession().getAttribute("user");
			String shijiankeid = request.getParameter("shijiankeid");
			List<Map<String, Object>> xueshengList = bufenzustu(shijiankeid,user.getYonghuid());
			jsonObject.put("xueshengs", xueshengList);
			return jsonObject;
		}
		private List<Map<String, Object>> bufenzustu(String shijiankeid,Integer yonghuid) {
			List<Map<String, Object>> xueshengList = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> kaotiList = app_ShiJianTiService.getKaoTiByLaoShiId(yonghuid, Integer.parseInt(shijiankeid));
			List<Map<String, Object>> newkaotiList = new ArrayList<Map<String,Object>>();
			if(kaotiList==null || kaotiList.isEmpty()) {
				return xueshengList; 
			}
			for(int i =0;i<kaotiList.size();i++) {
				String shezhiString = "";
				try {
					if(kaotiList.get(i).get("sheZhi")==null) {
						continue;
					}
					shezhiString = kaotiList.get(i).get("sheZhi").toString();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					continue;
				}
				for(int j=0;j<shezhiString.split(";").length;j++) {
					if(shezhiString.split(";")[0].split(",")[0].equals("1")) {
						continue;
					}
					newkaotiList.add(kaotiList.get(i));
				}
			}
			String xueshengidString = "";
			for(int m =0;m<newkaotiList.size();m++) {
				try {
					xueshengidString+=newkaotiList.get(m).get("xueShengID").toString().substring(1);
					
				} catch (Exception e) {
					continue;
				}
			}
			xueshengList = xueShengService.selectByXueShengIDs2(Arrays.asList(xueshengidString.split(",")));
			return xueshengList;
		}
}
