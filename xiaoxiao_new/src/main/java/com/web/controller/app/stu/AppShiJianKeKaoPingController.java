package com.web.controller.app.stu;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.lang.Math;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.model.App_FenZu;
import com.web.model.App_KaoPingChouQian;
import com.web.model.App_KaoPingJieGuo;
import com.web.model.App_KeChengKaoPing;
import com.web.model.App_XueXiZu;
import com.web.model.JiaoShi;
import com.web.model.JiaoXueLou;
import com.web.model.XiaoQu;
import com.web.model.XueQi;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.service.App_FenZuService;
import com.web.service.App_KaoPingChouQianService;
import com.web.service.App_KaoPingJieGuoService;
import com.web.service.App_KeChengKaoPingService;
import com.web.service.App_XueXiZuService;
import com.web.service.JiaoShiService;
import com.web.service.JiaoXueLouService;
import com.web.service.KeChengService;
import com.web.service.XiaoQuService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.util.GetPic;
import com.web.util.UpdataImgerUtil;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
public class AppShiJianKeKaoPingController {
	@Autowired
	private KeChengService kechengService;
	@Autowired
	private JiaoShiService jiaoshiService;
	@Autowired
	private JiaoXueLouService jiaoXueLouService;
	@Autowired
	private XiaoQuService xiaoquService;
	@Autowired
	private App_KeChengKaoPingService app_KeChengKaoPingService;
	@Autowired
	private App_XueXiZuService app_XueXiZuService;
	@Autowired
	private XueShengService xueShengService;
	@Autowired
	private App_FenZuService app_FenZuService;
	@Autowired
	private App_KaoPingChouQianService app_KaoPingChouQianService;
	@Autowired
	private App_KaoPingJieGuoService app_KaoPingJieGuoService;
	@Autowired
	private XueQiService xueQiService;
	@Autowired
	private YongHuService yongHuService;

	/**
	 * 获得学生的实践课
	 * 
	 * @param xueshengid,token,status,banjiid
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "app_getshijiankecheng")
	public JSONObject app_getshijiankecheng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String banjiid = request.getParameter("banjiid");
		String xuenian = request.getParameter("xuenian");
		String xueqi = request.getParameter("xueqi");
		JSONObject jsonObject = new JSONObject();
		List<Map<String, Object>> keChengs2 = new ArrayList<>();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		XueQi xueQi = new XueQi();
		Map<String, String> map = new HashMap<>();
		String xuexiaoid = xueShengService.selectByPrimaryKey(Integer.parseInt(xueshengid)).getXuexiaoXuehao().split("_")[0];
		String nianfen = "";
		if(xuenian!=null &&! "".equals(xuenian)) {
			nianfen = xuenian.split("~")[0];
		}
		map.put("nianfen", nianfen);
		map.put("xuenian", xuenian);
		map.put("xueqi", xueqi);
		map.put("xuexiaoid", xuexiaoid);
		xueQi = xueQiService.getByXueXiaoIDAndXueNianAndXueQi(map);
		if(xueQi==null) {
			Date date = new Date();
			SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> maps = new HashMap<>();
			maps.put("riqi", riqi.format(date));
			maps.put("xueXiaoID", xuexiaoid);
			xueQi = xueQiService.getByxueXiaoIDandriQi(maps);
			if (xueQi == null) {
				List<XueQi> xueqis = xueQiService.getNewerXueQiByXueQi(map);
				if (xueqis != null && xueqis.size() > 0) {
					xueQi = xueqis.get(0);
				}
			}
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("banjiid", Integer.parseInt(banjiid));
		paraMap.put("xueshengid", Integer.parseInt(xueshengid));
		paraMap.put("xueqiid", xueQi.getXueqiid());
		List<Map<String, Object>> keChengs = kechengService.getByBanJiIDAndXueShengIDAndXueQi(paraMap);
		List<Object> shijiankeObjects = new ArrayList<Object>();
		for (int i = 0; i < keChengs.size(); i++) {
			List<Map<String, Object>> shiJianKeCheng = kechengService
					.selectShiJianKe((Integer) keChengs.get(i).get("ID"));
			if (shiJianKeCheng == null || shiJianKeCheng.size() == 0) {
				continue;
			}
			shijiankeObjects.add(shiJianKeCheng);
			for (int j = 0; j < shiJianKeCheng.size(); j++) {
				keChengs.get(i).put("shijiankechengid", (Integer) shiJianKeCheng.get(j).get("ID"));
				JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey((Integer) keChengs.get(i).get("jiaoShiID"));
				JiaoXueLou jiaoxuelou = jiaoXueLouService.selectByPrimaryKey(jiaoShi.getJiaoxuelouid());
				XiaoQu xiaoQu = xiaoquService.selectByPrimaryKey(jiaoxuelou.getXiaoQuId());
				String [] renkelaoshiidString = shiJianKeCheng.get(j).get("renKeLaoShiID").toString().split(";");
				getlaoshixinxi(keChengs, renkelaoshiidString);
				keChengs.get(i).put("jiaoxuelouming", jiaoxuelou.getJiaoXueLouMing());
				keChengs.get(i).put("jiaoshiming", jiaoShi.getJiaoshiming());
				keChengs.get(i).put("xiaoquming", xiaoQu.getMingcheng());
				try {
					if (shiJianKeCheng.get(0).get("sheZhi") == null) {
						keChengs2.add(keChengs.get(i));
						continue;
					}
					String fenzushezhi = shiJianKeCheng.get(0).get("sheZhi").toString();
					fenzuxinxi(fenzushezhi, keChengs.get(i));
				} catch (Exception e) {
					keChengs2.add(keChengs.get(i));
					e.printStackTrace();
					continue;
				}
				keChengs2.add(keChengs.get(i));
			}
		}
		if(shijiankeObjects.isEmpty()) {
			jsonObject.put("status", "noshijianke");
			return jsonObject;
		}
		quchong(keChengs2);
		List<String> xuenians = xueQiService.getXuenianByXuexiaoID(Integer.parseInt(xuexiaoid));
		jsonObject.put("xuenians", xuenians);
		jsonObject.put("kechengs", keChengs2);
		return jsonObject;
	}
	
	//去重
	private void quchong(List<Map<String, Object>> list) {
		HashSet<Map<String, Object>> h = new HashSet<Map<String, Object>>(list);
		list.clear();
		list.addAll(h);
	}
	//设置信息
	private void fenzuxinxi(String shezhi,Map<String, Object> kecheng) {
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
				if (strings.length == 3) {
					//一题一人或者一题多人
					kecheng.put("fentileixing", strings[2]);
				}
				break;
			case "3":
				kecheng.put("shifoufenzu", strings[1]);
				if (strings.length == 3) {
					//按不按题目分组
					kecheng.put("fenzuleixing", strings[2]);
				}
				break;
			default:
				break;
			}
		}
	}
	//得到老师信息
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
	

	/**
	 * 判断是否分组，如果只有一个考评并且状态为1，即处于就绪状态，则未分组
	 * 
	 * @param xueshengid,token,status,kechengid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_isfenzu")
	public JSONObject app_isfenzu(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kechengid = request.getParameter("kechengid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (!str.equals(token)) {
			return jsonObject;
		}
		Map<String, Object> shiJianKeCheng = kechengService.getshijianke(Integer.parseInt(kechengid));
		if (shiJianKeCheng.get("sheZhi") == null) {
			return jsonObject;
		}
		String[] fenzushezhi = shiJianKeCheng.get("sheZhi").toString().split(";");
		for (String string2 : fenzushezhi) {
			String[] strings = string2.split(",");
			switch (strings[0]) {
			case "1":
				if (strings[1].equals("1")) {
					List<Map<String, Object>> kaoPingList = app_KeChengKaoPingService
							.getKaoPing2(Integer.parseInt(kechengid));
					Boolean isfenzu = false;
					for (int i = 0; i < kaoPingList.size(); i++) {
						if (kaoPingList.get(i).get("zhuangTai").toString().equals("0")) {
							continue;
						} else {
							isfenzu = true;
						}
					}
					if (isfenzu) {
						jsonObject.put("xiaozustatus", "xiaozuyifen");
					} else {
						jsonObject.put("xiaozustatus", "xiaozuweifen");
					}
				}
				break;
			case "2":
				if (strings[1].equals("1")) {
					List<Map<String, Object>> dazuList = app_FenZuService.getDaZu(Integer.parseInt(kechengid));
					if (dazuList != null & !dazuList.isEmpty()) {
						jsonObject.put("dazustatus", "dazuyifen");
					} else {
						jsonObject.put("dazustatus", "dazuweifen");
					}
					break;
				}
			default:
				break;
			}
		}
		return jsonObject;
	}

	/**
	 * 得到所有实践课下的小组
	 * 
	 * @param xueshengid,token,status,kechengid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_getxiaozu")
	public JSONObject app_getxiaozu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kechengid = request.getParameter("kechengid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (!str.equals(token)) {
			return jsonObject;
		}
		List<Map<String, Object>> xueXiZus = app_XueXiZuService.getXueXiZuByKeCheng2(Integer.parseInt(kechengid));
		String string2 = "%," + xueshengid + ",%";
		App_XueXiZu app_XueXiZu = new App_XueXiZu();
		app_XueXiZu.setChengYuanLieBiao(string2);
		app_XueXiZu.setShiJianKeChengId(Integer.parseInt(kechengid));
		List<App_XueXiZu> xueXiZu = app_XueXiZuService.selectXueXiZuByXsID(app_XueXiZu);
		// 获取小组成员的姓名和小组长
		// String chengyuanids = "";
		// for (int j = 0; j < xueXiZus.size(); j++) {
		// if (xueXiZus.get(j).get("chengYuanIDLieBiao").toString() == null) {
		// continue;
		// }
		// chengyuanids +=
		// xueXiZus.get(j).get("chengYuanIDLieBiao").toString().substring(1);
		// }
		// List<Map<String, Object>> list =
		// xueShengService.selectByXueShengIDs(Arrays.asList(chengyuanids.split(",")));
		List<Map<String, Object>> list = getXiaoZuChengYuan(xueXiZus);
		for (int i = 0; i < xueXiZus.size(); i++) {
			List<Map<String, Object>> xsList = new ArrayList<>();
			for (int j = 0; j < list.size(); j++) {
				if (!xueXiZus.get(i).get("chengYuanIDLieBiao").toString()
						.contains("," + list.get(j).get("ID").toString() + ",")) {
					continue;
				}
				if (xueXiZus.get(i).get("xueShengID").toString().equals(list.get(j).get("ID").toString())) {
					xueXiZus.get(i).put("xiaozuzhang", list.get(j).get("xingming"));
				}
				xsList.add(list.get(j));
			}
			xueXiZus.get(i).put("chengyuan", xsList);
		}
		jsonObject.put("allXiaoZu", xueXiZus);
		if (xueXiZu.size() > 0) {
			jsonObject.put("suoxuanxiaozu", xueXiZu.get(0));
		} else {
			jsonObject.put("suoxuanxiaozu", "");
		}
		return jsonObject;
	}

	/**
	 * 得到小组名称，成员姓名，小组长等
	 * 
	 * @param xueshengid,token,status,kechengid,xiaozuid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_getxiaozuxinxi")
	public JSONObject app_getxiaozuxinxi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xiaozuid = request.getParameter("xiaozuid");
		String kechengid = request.getParameter("kechengid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (str.equals(token)) {
			Map<String, Object> shijiankemap = kechengService.getshijianke(Integer.parseInt(kechengid));
			Map<String, Object> xueXiMap = app_XueXiZuService.selectByPrimaryKey1(Integer.parseInt(xiaozuid));
			// if (xueXiMap.get("chengYuanIDLieBiao").toString() != null) {
			// String[] chengyuan =
			// xueXiMap.get("chengYuanIDLieBiao").toString().substring(1).split(",");
			// List<Map<String, Object>> member = new ArrayList<>();
			// for (int j = 0; j < chengyuan.length; j++) {
			// Map<String, Object> map = new HashMap<>();
			// XueSheng xSheng =
			// xueShengService.selectByPrimaryKey(Integer.parseInt(chengyuan[j]));
			// map.put("xingming", xSheng.getXingming());
			// map.put("xueshengid", xSheng.getXueshengid());
			// member.add(map);
			// }
			// xueXiMap.put("chengyuan", member);
			// xueXiMap.put("xiaozuzhang", xueShengService
			// .selectByPrimaryKey(Integer.parseInt(xueXiMap.get("xueShengID").toString())).getXingming());
			// }
			List<Map<String, Object>> temlistList = new ArrayList<Map<String, Object>>();
			temlistList.add(xueXiMap);
			xueXiMap.put("xiaozuzhang", xueShengService
					.selectByPrimaryKey(Integer.parseInt(xueXiMap.get("xueShengID").toString())).getXingming());
			xueXiMap.put("chengyuan", getXiaoZuChengYuan(temlistList));
			jsonObject.put("xiaozuxinxi", xueXiMap);
			jsonObject.put("xiaozurongliang", shijiankemap.get("xiaoZuRongLiang"));
		}
		return jsonObject;
	}

	/**
	 * 得到所有大组，大组下面的小组，以及大组长，小组长，小组组员姓名
	 * 
	 * @param xueshengid,token,status,kechengid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_getzuxinxi")
	public JSONObject app_getzuxinxi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kechengid = request.getParameter("kechengid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (str.equals(token)) {
			List<Map<String, Object>> daZuList = app_FenZuService.getFenZu(Integer.parseInt(kechengid));
			for (int i = 0; i < daZuList.size(); i++) {
				try {
					XueSheng xueSheng = xueShengService
							.selectByPrimaryKey(Integer.parseInt(daZuList.get(i).get("zuzhangid").toString()));
					daZuList.get(i).put("dazuzhang", xueSheng.getXingming());
					
				} catch (Exception e) {
					daZuList.get(i).put("dazuzhang","");
				}
			}
			jsonObject.put("dazuxinxi", daZuList);
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_getxiaozuinfo")
	public JSONObject app_getxiaozuinfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String dazuid = request.getParameter("dazuid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (str.equals(token)) {
			List<Map<String, Object>> xiaozus = app_XueXiZuService.getXueXZ(Integer.parseInt(dazuid));
			for (int k = 0; k < xiaozus.size(); k++) {
				String xueShengIDs = "";
				// 取得小组内学生的ID
				xueShengIDs += xiaozus.get(k).get("chengYuanIDLieBiao").toString().substring(1);
				if (xueShengIDs == null || "".equals(xueShengIDs)) {
					return jsonObject;
				}
				// 获取本组所有的学生信息
				List<Map<String, Object>> listXueSheng = null;
				listXueSheng = xueShengService.selectByXueShengIDs(Arrays.asList(xueShengIDs.split(",")));
				xiaozus.get(k).put("chengyuanxinxi", listXueSheng);
				xiaozus.get(k).put("xiaozuzhang",
						xueShengService
								.selectByPrimaryKey(Integer.parseInt(xiaozus.get(k).get("xueShengID").toString()))
								.getXingming());
			}
			jsonObject.put("xiaozuxinxi", xiaozus);
		}
		return jsonObject;
	}

	/**
	 * 学生新建小组，建小组的学生即为小组长
	 * 
	 * @param xueshengid,token,status,kechengid,xiaozuming
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_insertxiaozu")
	public JSONObject app_insertxiaozu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kechengid = request.getParameter("kechengid");
		String xiaozuming = request.getParameter("xiaozuming");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (str.equals(token)) {
			List<App_XueXiZu> xueXiZus = app_XueXiZuService.getXueXiZuByKeCheng(Integer.parseInt(kechengid));
			for (App_XueXiZu app_XueXiZu : xueXiZus) {
				if (app_XueXiZu.getXueXiZuMing().equals(xiaozuming)) {
					jsonObject.put("status", "samename");
					return jsonObject;
				} else {
					continue;
				}
			}
			App_XueXiZu app_XueXiZu = new App_XueXiZu();
			app_XueXiZu.setShiJianKeChengId(Integer.parseInt(kechengid));
			app_XueXiZu.setXueXiZuMing(xiaozuming);
			app_XueXiZu.setXiaoZuZhangId(Integer.parseInt(xueshengid));
			app_XueXiZu.setChengYuanLieBiao("," + xueshengid + ",");
			app_XueXiZu.setZhuangTai(0);
			int i = app_XueXiZuService.insert(app_XueXiZu);
			if (i != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		}
		return jsonObject;
	}

	/**
	 * 解散某个小组，即删除某个小组
	 * 
	 * @param xueshengid,token,status,xiaozuid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_deletexiaozu") // 解散小组
	public JSONObject app_deletexiaozu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xiaozuid = request.getParameter("xiaozuid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (str.equals(token)) {
			int i = app_XueXiZuService.delete(Integer.parseInt(xiaozuid));
			if (i != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		}
		return jsonObject;
	}

	/**
	 * 学生加入某个小组
	 * 
	 * @param xueshengid,token,status,xiaozuid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_tianjiachengyuan")
	public JSONObject app_tianjiachengyuan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xiaozuid = request.getParameter("xiaozuid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (str.equals(token)) {
			App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuid));
			if (app_XueXiZu == null) {
				jsonObject.put("status", "noxuexizu");
				return jsonObject;
			}
			String chengYuan = "";
			if (app_XueXiZu.getChengYuanLieBiao() != null && !"".equals(app_XueXiZu.getChengYuanLieBiao())) {
				chengYuan += app_XueXiZu.getChengYuanLieBiao() + xueshengid + ",";
			} else {
				chengYuan = "," + xueshengid + ",";
			}
			app_XueXiZu.setChengYuanLieBiao(chengYuan);
			int i = app_XueXiZuService.update(app_XueXiZu);
			if (i != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		}
		return jsonObject;
	}

	/**
	 * 学生退出某个小组
	 * 
	 * @param xueshengid,token,status,xiaozuid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_tuichu")
	public JSONObject app_tuichu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xiaozuid = request.getParameter("xiaozuid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (str.equals(token)) {
			App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuid));
			if (app_XueXiZu == null) {
				jsonObject.put("status", "noxuexizu");
				return jsonObject;
			}
			if (xueshengid.equals(app_XueXiZu.getXiaoZuZhangId().toString())) {
				app_XueXiZuService.delete(Integer.parseInt(xiaozuid));
				return jsonObject;
			}
			String chengYuan = "";
			if (app_XueXiZu.getChengYuanLieBiao() != null && !"".equals(app_XueXiZu.getChengYuanLieBiao())) {
				chengYuan = app_XueXiZu.getChengYuanLieBiao();
			} else {
				jsonObject.put("status", "nochengyuan");
			}
			// if(!chengYuan.contains(","+xueshengid+",")) {
			// jsonObject.put("status", "yituichu");
			// return jsonObject;
			// }
			String newchengyuan = "";
			List<String> list = getNewArray(xueshengid, chengYuan.substring(1).split(","));
			for (String s : list) {
				newchengyuan += s + ",";
			}
			app_XueXiZu.setChengYuanLieBiao("," + newchengyuan);
			int i = app_XueXiZuService.update(app_XueXiZu);
			if (i != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		}
		return jsonObject;
	}

	/**
	 * 获得剔除某个成员后的小组
	 * 
	 * @param vaule,array
	 * @return JSONObject
	 * @throws ParseException
	 */
	private List<String> getNewArray(String value, String[] array) {
		List<String> list = new ArrayList<>();
		for (int k = 0; k < array.length; k++) {
			if (value.equals(array[k])) {
				continue;
			}
			if (array[k] != null) {
				list.add(array[k]);
			}
		}
		return list;
	}

	/**
	 * 剔除小组成员
	 * 
	 * @param xueshengid,token,status,xiaozuid,kickid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_kickmember")
	public JSONObject app_kickmember(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xiaozuid = request.getParameter("xiaozuid");
		String kickid = request.getParameter("kickid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (str.equals(token)) {
			App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuid));
			if (app_XueXiZu == null) {
				jsonObject.put("status", "noxuexizu");
				return jsonObject;
			}
			if (kickid == null || "".equals(kickid) || kickid.equals("undefined")) {
				jsonObject.put("status", "nokickmember");
				return jsonObject;
			}
			String kickids = "," + kickid;
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
		}
		return jsonObject;
	}

	/**
	 * count是大组数量，随机选择一个大组
	 * 
	 * @param count
	 * @return JSONObject
	 * @throws ParseException
	 */
	private int randomChioce(int count) {
		Random random = new Random();
		int k = random.nextInt(100);
		int m = k % count;
		return m;
	}

	/**
	 * 提交创建小组，并为小组随机分配大组
	 * 
	 * @param xueshengid,
	 *            token, status, xiaozuid, kechengid, chengyuanid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_submitxiaozu")
	public JSONObject app_submitxiaozu(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xiaozuid = request.getParameter("xiaozuid");
		String kechengid = request.getParameter("kechengid");
		String chengyuanid = request.getParameter("chengyuanid");
		String fendazu = request.getParameter("fendazu");
		String fenxiaozu = request.getParameter("fenxiaozu");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (str.equals(token)) {
			App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuid));
			if (app_XueXiZu == null) {
				jsonObject.put("status", "noxuexizu");
				return jsonObject;
			}
			// 不分小组
			if (fenxiaozu.equals("0")) {
				// 不分大组
				if (fendazu.equals("0")) {
					app_XueXiZu.setZhuangTai(1);
					int i = app_XueXiZuService.update(app_XueXiZu);
					if (i != 0) {
						jsonObject.put("status", "success");
					} else {
						jsonObject.put("status", "fail");
					}
				}
				// 分大组
				if (fendazu.equals("1")) {
					Integer fenZuId = randomfendazu(kechengid);
					if (fenZuId == -2) {
						jsonObject.put("status", "fail");
						return jsonObject;
					}
					if (fenZuId == 0) {
						jsonObject.put("status", "nodazu");
						return jsonObject;
					}
					if (fenZuId == -1) {
						jsonObject.put("status", "full");
						return jsonObject;
					}
					app_XueXiZu.setZhuangTai(1);
					app_XueXiZu.setFenZuId(fenZuId);
					int i = app_XueXiZuService.update(app_XueXiZu);
					if (i != 0) {
						jsonObject.put("status", "success");
						// 更新大组容量
						App_FenZu app_FenZus = app_FenZuService.selectByPrimaryKey(fenZuId);
						List<App_XueXiZu> xiaozulists = app_FenZuService.getXueXiZu(fenZuId);
						app_FenZus.setYiJiaRu(xiaozulists.size());
						app_FenZuService.update(app_FenZus);
					} else {
						jsonObject.put("status", "fail");
					}
				}
				return jsonObject;
			}
			// 分小组不分大组,fenZuID字段不插入
			if (fendazu.equals("0")) {
				app_XueXiZu.setChengYuanLieBiao(chengyuanid);
				app_XueXiZu.setZhuangTai(1);
				int i = app_XueXiZuService.update(app_XueXiZu);
				if (i != 0) {
					jsonObject.put("status", "success");
				} else {
					jsonObject.put("status", "fail");
				}
				return jsonObject;
			}
			// 分小组分大组
			Integer fenZuId = randomfendazu(kechengid);
			// fenZuId的几种状态
			if (fenZuId == -2) {
				jsonObject.put("status", "fail");
				return jsonObject;
			}
			if (fenZuId == 0) {
				jsonObject.put("status", "nodazu");
				return jsonObject;
			}
			if (fenZuId == -1) {
				jsonObject.put("status", "full");
				return jsonObject;
			}
			app_XueXiZu.setChengYuanLieBiao(chengyuanid);
			app_XueXiZu.setFenZuId(fenZuId);
			app_XueXiZu.setZhuangTai(1);
			int i = app_XueXiZuService.update(app_XueXiZu);
			if (i != 0) {
				jsonObject.put("status", "success");
				// 更新大组容量
				App_FenZu app_FenZus = app_FenZuService.selectByPrimaryKey(fenZuId);
				List<App_XueXiZu> xiaozulists = app_FenZuService.getXueXiZu(fenZuId);
				app_FenZus.setYiJiaRu(xiaozulists.size());
				app_FenZuService.update(app_FenZus);
			} else {
				jsonObject.put("status", "fail");
			}
		}
		return jsonObject;
	}

	private Integer randomfendazu(String kechengid) {
		Integer fenZuId = -2;
		List<Map<String, Object>> daZuList = app_FenZuService.getDaZu(Integer.parseInt(kechengid));
		if (daZuList.size() <= 0) {
			return 0;
		}
		int m = randomChioce(daZuList.size());
		fenZuId = Integer.parseInt(daZuList.get(m).get("ID").toString());
		App_FenZu app_FenZu = app_FenZuService.selectByPrimaryKey(fenZuId);
		List<App_XueXiZu> xiaozulist = app_FenZuService.getXueXiZu(fenZuId);
		int count = 1;
		// 所选大组容量已满，选择其他大组，如果选择次数大于大组数量说明大组已满
		while (app_FenZu.getRongLiang() - 1 < xiaozulist.size()) {
			m = randomChioce(daZuList.size());
			fenZuId = Integer.parseInt(daZuList.get(m).get("ID").toString());
			app_FenZu = app_FenZuService.selectByPrimaryKey(fenZuId);
			xiaozulist = app_FenZuService.getXueXiZu(fenZuId);
			++count;
			if (count > daZuList.size()) {
				return -1;
			}
		}
		return fenZuId;
	}

	// @ResponseBody
	// @RequestMapping(value = "app_chongXinFenDaZu")
	// public JSONObject app_chongXinFenDaZu(HttpServletRequest request,
	// HttpServletResponse response) {
	// String xueshengid = request.getParameter("xueshengid");
	// String token = request.getParameter("token");
	// String status = request.getParameter("status");
	// String xiaozuid = request.getParameter("xiaozuid");
	// String kechengid = request.getParameter("kechengid");
	// String fendazu = request.getParameter("fendazu");
	// String fenxiaozu = request.getParameter("fenxiaozu");
	// String string = xueshengid + "," + status;
	// String str = Util.SHA1Encode(string);
	// JSONObject jsonObject = new JSONObject();
	// if (str.equals(token)) {
	// App_XueXiZu app_XueXiZu =
	// app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuid));
	// if (app_XueXiZu == null) {
	// jsonObject.put("status", "noxuexizu");
	// return jsonObject;
	// }
	// Integer fenZuId = randomfendazu(kechengid, jsonObject);
	// if(fenZuId==-1) {
	// jsonObject.put("status", "fail");
	// return jsonObject;
	// }
	// app_XueXiZu.setFenZuId(fenZuId);
	// int i = app_XueXiZuService.update(app_XueXiZu);
	// if (i != 0) {
	// jsonObject.put("status", "success");
	// } else {
	// jsonObject.put("status", "fail");
	// }
	// }
	// return jsonObject;
	// }

	/**
	 * 得到某个小组的基本信息，包括小组长，组员姓名，大组长，小组排序状态，签到状态
	 * 
	 * @param keID,
	 *            kaoPingID, xueshengid,
	 * @return JSONObject
	 * @throws ParseException
	 */
	private Map<String, Object> getBenZuXinXi(Integer keID, Integer kaoPingID, Integer xueshengid, String fendazu,
			String fenxiaozu, String chouqianid, String dazuzhangid) {
		Map<String, Object> map = new HashMap<>();
		App_XueXiZu tmp = new App_XueXiZu();
		tmp.setShiJianKeChengId(keID);
		String str = "%," + xueshengid + ",%";
		tmp.setChengYuanLieBiao(str);
		List<Map<String, Object>> xueXiZus = app_XueXiZuService.selectXueXiZuByXsID1(tmp);
		if (xueXiZus.size() == 0) {
			return map;
		}
		map.put("zuming", xueXiZus.get(0).get("xueXiZuMing"));
		map.put("suozaixiaozuid", xueXiZus.get(0).get("ID"));
		map.put("xiaozuzhang", xueXiZus.get(0).get("xueShengID"));
		List<Map<String, Object>> members = new ArrayList<>();
		members = getXiaoZuChengYuan(xueXiZus);
		map.put("dangqianxiaozu", members);
		// 新建考评结果，初始状态为1，即签到状态
		App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService.selectJieGuoByXiaoZuId(kaoPingID,
				Integer.parseInt(xueXiZus.get(0).get("ID").toString()));
		if (app_KaoPingJieGuo == null) {
			app_KaoPingJieGuo = new App_KaoPingJieGuo();
			app_KaoPingJieGuo.setKaoPingId(kaoPingID);
			app_KaoPingJieGuo.setXueXiZuId(Integer.parseInt(xueXiZus.get(0).get("ID").toString()));
			if (fenxiaozu.equals("0")) {
				app_KaoPingJieGuo.setFaYanRenId(Integer.parseInt(xueXiZus.get(0).get("xueShengID").toString()));
			}
			app_KaoPingJieGuo.setZhuangTai(1);
			app_KaoPingJieGuoService.insert(app_KaoPingJieGuo);
		}
		if (fendazu.equals("0")) {
			if (dazuzhangid == null || "".equals(dazuzhangid)) {

				map.put("dazuzhang", "");
				map.put("dazuzhangid", "");
			}
			XueSheng xueSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(dazuzhangid));
			if (xueSheng != null) {
				map.put("dazuzhang", xueSheng.getXingming());
				map.put("dazuzhangid", xueSheng.getXueshengid());
			}
			map.put("dazumingcheng", "");
		} else {
			App_FenZu fenZu = app_FenZuService
					.selectByPrimaryKey(Integer.parseInt(xueXiZus.get(0).get("fenZuID").toString()));
			if (fenZu == null) {
				map.put("dazumingcheng", "");
				map.put("dazuzhang", "");
			}
			XueSheng xueSheng = xueShengService.selectByPrimaryKey(fenZu.getZuZhangId());
			if (xueSheng != null) {
				map.put("dazuzhang", xueSheng.getXingming());
			}
			map.put("dazumingcheng", fenZu.getZuMingCheng());
		}
		App_KaoPingChouQian chouqian = app_KaoPingChouQianService.selectByPrimaryKey(Integer.parseInt(chouqianid));
		if (chouqian == null) {
			// 还没有抽签的信息
			map.put("qiandaostatus", 0);
			return null;
		}
		if (chouqian.getQianDao() == null) {
			map.put("qiandaostatus", 0);
		}
		if (chouqian.getQianDao() != null) {
			if (chouqian.getQianDao().toString().indexOf("," + xueXiZus.get(0).get("ID").toString() + ",") >= 0) {
				map.put("qiandaostatus", 1);
			} else {
				map.put("qiandaostatus", 0);
			}
		}
		List<App_XueXiZu> xueXiZus2 = new ArrayList<>();
		if (chouqian.getXiaozupaixu() == null || chouqian.getXiaozupaixu().toString().length() <= 0) {
			map.put("paixustatus", "unsort");
		} else {
			map.put("paixustatus", "sort");
			String[] paixuxiaozus = chouqian.getXiaozupaixu().toString().split(",");
			for (int j = 0; j < paixuxiaozus.length; j++) {
				App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(paixuxiaozus[j]));
				if (app_XueXiZu == null) {
					continue;
				}
				xueXiZus2.add(app_XueXiZu);
			}
		}
		map.put("yipaixuxuexizu", xueXiZus2);
		// map.put("chouqian", chouqian);
		return map;
	}

	private List<Map<String, Object>> getXiaoZuChengYuan(List<Map<String, Object>> xiaozuList) {
		String chengyuanids = "";
		for (int k = 0; k < xiaozuList.size(); k++) {
			chengyuanids += xiaozuList.get(k).get("chengYuanIDLieBiao").toString().substring(1);
		}
		List<Map<String, Object>> xueshengList = new ArrayList<Map<String, Object>>();
		xueshengList = xueShengService.selectByXueShengIDs(Arrays.asList(chengyuanids.split(",")));
		// for (int i = 0; i < xiaozuList.size(); i++) {
		// for (int k = 0; k < xueshengList.size(); k++) {
		// if
		// (!xueshengList.get(k).get("ID").toString().equals(xiaozuList.get(i).get("xueShengID").toString()))
		// {
		// continue;
		// }
		// xiaozuList.get(i).put("xiaozuzhang", xueshengList.get(k).get("xingming"));
		// }
		// }
		return xueshengList;
	}

	private List<Map<String, Object>> getOneXiaoZuChengYuan(App_XueXiZu app_XueXiZu) {
		String chengyuanids = app_XueXiZu.getChengYuanLieBiao();
		List<Map<String, Object>> xueshengList = new ArrayList<Map<String, Object>>();
		xueshengList = xueShengService.selectByXueShengIDs(Arrays.asList(chengyuanids.split(",")));
		// for (int k = 0; k < xueshengList.size(); k++) {
		// if
		// (!xueshengList.get(k).get("ID").toString().equals(app_XueXiZu.getXiaoZuZhangId().toString()))
		// {
		// continue;
		// }
		// xueshengList.add(xueshengList.get(k));
		// }
		return xueshengList;
	}

	/**
	 * 获取正在进行的考评信息，根据学生所在大组的抽签状态，获得对应的信息
	 * 
	 * @param kechengid
	 *            kaopingid xueshengid
	 * @return JSONObject
	 * @throws ParseException
	 */
	private Map<String, Object> getXinXi(Integer kechengid, Integer kaopingid, Integer xueshengid, String fendazu,
			String fenxiaozu, String dazuzhangid) {
		Map<String, Object> map = new HashMap<>();
		App_XueXiZu tmp = new App_XueXiZu();
		tmp.setShiJianKeChengId(kechengid);
		String str = "%," + xueshengid + ",%";
		tmp.setChengYuanLieBiao(str);
		List<App_XueXiZu> xueXiZus = app_XueXiZuService.selectXueXiZuByXsID(tmp);
		if (xueXiZus.size() == 0) {
			return map;
		}
		Map<String, Object> chouqian = new HashMap<>();
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("kaopingid", kaopingid);
		if (fendazu.equals("0")) {
			chouqian = app_KaoPingChouQianService.getchouqianbyfenzu1(kaopingid);
		}
		if (fendazu.equals("1")) {
			paraMap.put("dazuid", xueXiZus.get(0).getFenZuId());
			chouqian = app_KaoPingChouQianService.selectchouqian(paraMap);
		}
		if (chouqian == null) {
			// 还没有抽签的信息
			chouqian = new HashMap<>();
			App_KaoPingChouQian app_KaoPingChouQian = new App_KaoPingChouQian();
			app_KaoPingChouQian.setKaoPingId(kaopingid);
			app_KaoPingChouQian.setZhuangTai(1);
			// 如果分大组则设置大组序号，否则不设置
			if (!fendazu.equals("0")) {
				app_KaoPingChouQian.setDaZuXuHao(xueXiZus.get(0).getFenZuId());
			}
			int n = app_KaoPingChouQianService.insert(app_KaoPingChouQian);
			chouqian.put("ID", app_KaoPingChouQian.getId());
			chouqian.put("kaoPingID", kaopingid);
			chouqian.put("zhuangTai", 1);
			if (fendazu.equals("0")) {
				String dazuxuhao = "";
				chouqian.put("daZuxuHao", dazuxuhao);
			} else {
				chouqian.put("daZuxuHao", xueXiZus.get(0).getFenZuId());
			}
		}
		switch (chouqian.get("zhuangTai").toString()) {

		case "1":
			// 准备签到，显示本组的详细信息,开始
			map = getBenZuXinXi(kechengid, kaopingid, xueshengid, fendazu, fenxiaozu, chouqian.get("ID").toString(),
					dazuzhangid);
			break;
		case "2":
			// 处于简报过程中，显示当前正在答辩的小组信息和下一个小组的信息
			// 不分小组,不分大组,不分小组分大组用同一个方法
			if (fenxiaozu.equals("0")) {
				map = getNowAndNext(kechengid, kaopingid, xueshengid, fendazu, fenxiaozu, chouqian.get("ID").toString(),
						dazuzhangid);
				break;
			}
			// 分大组也分小组,不分大组分小组,用另一个方法
			if (fenxiaozu.equals("1")) {
				map = getDangQianAndNextZu(kechengid, kaopingid, xueshengid, fendazu, fenxiaozu,
						chouqian.get("ID").toString(), dazuzhangid);
				break;
			}
		case "3":
			// 显示本组打分排序的情况，小组长提交后可以看到本组排序的结果
			map = getQianDaoXiaoZuXinXi(kaopingid, kechengid, xueshengid, fendazu, fenxiaozu,
					chouqian.get("ID").toString(), dazuzhangid);
			break;
		case "4":
			// 显示本组打分排序的情况，小组长提交后可以看到本组排序的结果
			map = getDaZuFenShu(kaopingid, kechengid, xueshengid, fendazu, fenxiaozu, chouqian.get("ID").toString(),
					dazuzhangid);
			break;
		default:
			break;
		}
		map.put("chouqian", chouqian);
		return map;
	}

	/**
	 * 未分组情况下获取所有小组的信息，前端自取上一组和下一组
	 * 
	 * @param kaopingid
	 * @param kechengid
	 * @param xueshengid
	 * @param fendazu
	 * @param fenxiaozu
	 * @param chouqianid
	 * @param dazuzhangid
	 * @return
	 */
	private Map<String, Object> getNowAndNext(Integer kechengid, Integer kaopingid, Integer xueshengid, String fendazu,
			String fenxiaozu, String chouqianid, String dazhuzhangid) {
		Map<String, Object> map = new HashMap<>();
		String dangqianzuid = null;
		App_KaoPingChouQian chouqian = app_KaoPingChouQianService.selectByPrimaryKey(Integer.parseInt(chouqianid));
		if (chouqian == null) {
			return map;
		}
		if (fenxiaozu.equals("1")) {
			return map;
		}
		if (chouqian.getXiaozupaixu() == null) {
			return map;
		}
		if (fendazu.equals("1")) {
			if (chouqian.getDaZuXuHao() == null) {
				return map;
			}
			App_FenZu app_FenZu = app_FenZuService.selectByPrimaryKey(chouqian.getDaZuXuHao());
			// XueSheng dazuzhang =
			// xueShengService.selectByPrimaryKey(app_FenZu.getZuZhangId());
			map.put("dazuzhangid", app_FenZu.getZuZhangId());
			// map.put("dazuzhang",dazuzhang);
		}
		if (fendazu.equals("0")) {
			map.put("dazuzhangid", dazhuzhangid);
		}
		List<App_KaoPingJieGuo> kaoPingJieGuos = app_KaoPingJieGuoService.getJieGuo(kaopingid);
		String paixuexiaozu[] = chouqian.getXiaozupaixu().split(",");
		List<Map<String, Object>> xuexizuList = new ArrayList<Map<String, Object>>();
		for (String xiaozuid : paixuexiaozu) {
			Map<String, Object> app_XueXiZu = app_XueXiZuService.selectByPrimaryKey1(Integer.parseInt(xiaozuid));
			if (app_XueXiZu == null) {
				continue;
			}
			xuexizuList.add(app_XueXiZu);
			for (App_KaoPingJieGuo kaoPingJieGuo : kaoPingJieGuos) {
				if (kaoPingJieGuo.getXueXiZuId().toString().equals(xiaozuid)) {
					app_XueXiZu.put("kaopingjieguo", kaoPingJieGuo);
					break;
				}
			}
		}
		map.put("paixuxiaozu", xuexizuList);
		for (App_KaoPingJieGuo kaoPingJieGuo : kaoPingJieGuos) {
			if (kaoPingJieGuo.getZhuangTai().toString().equals("2")) {
				dangqianzuid = kaoPingJieGuo.getXueXiZuId().toString();
				break;
			}
		}
		if (dangqianzuid == null) {
			dangqianzuid = paixuexiaozu[0];
		}
		map.put("dangqianzuid", dangqianzuid);
		return map;
	}

	/**
	 * 不分小组的时候，小组完成
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "app_completenew") // 小组演讲完成
	public JSONObject app_completenew(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaopingid = request.getParameter("kaopingid");
		String xiaozuid = request.getParameter("dangqianxiaozuid");
		String chouqianid = request.getParameter("kaopingchouqianid");
		String qiangzhi = request.getParameter("qiangzhi");
		String fayanrenid = request.getParameter("fayanrenid");
		App_KaoPingChouQian app_KaoPingChouQian = app_KaoPingChouQianService
				.selectByPrimaryKey(Integer.parseInt(chouqianid));
		App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuid));
		App_KaoPingJieGuo app_kaopingjieguo = app_KaoPingJieGuoService
				.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), Integer.parseInt(xiaozuid));
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		if (app_KaoPingChouQian == null) {
			jsonObject.put("status", "unchouqian");
			return jsonObject;
		}
		if (app_KaoPingChouQian.getXiaozupaixu() == null && app_KaoPingChouQian.getXiaozupaixu().length() <= 0) {
			jsonObject.put("status", "unsort");
			return jsonObject;
		}
		String[] paixuxiaozu = app_KaoPingChouQian.getXiaozupaixu().split(",");
		if (qiangzhi == null || "".equals(qiangzhi)) {
			if (app_kaopingjieguo.getZhaoPian() == null || "".equals(app_kaopingjieguo.getZhaoPian())) {
				jsonObject.put("status", "nopaizhao");
				return jsonObject;
			}
		}
		if (app_XueXiZu == null) {
			jsonObject.put("status", "none");
			return jsonObject;
		}
		// 当前组为本大组最后一组，抽签状态变为3，进入打分状态
		if (xiaozuid.equals(paixuxiaozu[paixuxiaozu.length - 1])) {
			jsonObject.put("status", "last");
			app_KaoPingChouQian.setZhuangTai(3);
			int m = app_KaoPingChouQianService.updateByPrimaryKey(app_KaoPingChouQian);
			app_KaoPingChouQian.setDangQianZuId(Integer.parseInt(xiaozuid));
			App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
					.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), app_XueXiZu.getId());
			app_KaoPingJieGuo.setZhuangTai(3);
			app_KaoPingJieGuo.setFaYanRenId(Integer.parseInt(fayanrenid));
			int k = app_KaoPingJieGuoService.update(app_KaoPingJieGuo);
			return jsonObject;
		}
		// 选取下一组演讲人，作为当前组演讲人
		App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
				.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), app_XueXiZu.getId());
		app_KaoPingJieGuo.setZhuangTai(3);
		app_KaoPingJieGuo.setFaYanRenId(Integer.parseInt(fayanrenid));
		int k = app_KaoPingJieGuoService.update(app_KaoPingJieGuo);
		Map<String, Object> map = new HashMap<String, Object>();
		String xiayixiaozuid = getxiayizuid(app_KaoPingChouQian, map, paixuxiaozu);
		if (xiayixiaozuid == "") {
			return jsonObject;
		}
		jsonObject.put("dangqianzuid", xiayixiaozuid);
		app_KaoPingChouQian.setDangQianZuId(Integer.parseInt(xiayixiaozuid));
		app_KaoPingChouQianService.updateByPrimaryKey(app_KaoPingChouQian);
		App_KaoPingJieGuo app_KaoPingJieGuo2 = app_KaoPingJieGuoService
				.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), Integer.parseInt(xiayixiaozuid));
		if (app_KaoPingJieGuo2 == null) {
			return jsonObject;
		}
		app_KaoPingJieGuo2.setZhuangTai(2);
		app_KaoPingJieGuoService.update(app_KaoPingJieGuo2);
		return jsonObject;
	}

	// 获取下一小组ID
	private String getxiayizuid(App_KaoPingChouQian app_KaoPingChouQian, Map<String, Object> map,
			String[] paixuxiaozu) {
		String xiayizuid = "";
		if (app_KaoPingChouQian.getDangQianZuId().toString().equals(paixuxiaozu[paixuxiaozu.length - 1])) {
			map.put("yanjiangstatus", "last");
			return null;
		}
		for (int m = 0; m < paixuxiaozu.length; m++) {
			if (paixuxiaozu[m].equals(app_KaoPingChouQian.getDangQianZuId().toString())) {
				if (m + 1 <= paixuxiaozu.length - 1) {
					xiayizuid = paixuxiaozu[m + 1];
					break;
				} else {
					break;
				}
			} else {
				continue;
			}
		}
		return xiayizuid;
	}

	/**
	 * 获得大组信息
	 * 
	 * @param kaopingid
	 *            kechengid xueshengid
	 * @return JSONObject
	 * @throws ParseException
	 */
	public Map<String, Object> getDaZuFenShu(Integer kaopingid, Integer kechengid, Integer xueshengid, String fendazu,
			String fenxiaozu, String chouqianid, String dazuzhangid) {
		Map<String, Object> map = new HashMap<>();
		App_XueXiZu tmp = new App_XueXiZu();
		tmp.setShiJianKeChengId(kechengid);
		String str = "%," + xueshengid + ",%";
		tmp.setChengYuanLieBiao(str);
		List<App_XueXiZu> xueXiZus = app_XueXiZuService.selectXueXiZuByXsID(tmp);
		if (xueXiZus.size() == 0) {
			return map;
		}
		List<Map<String, Object>> daZuList = new ArrayList<>();
		if (fendazu.equals("0")) {
			App_KaoPingChouQian chouqian = app_KaoPingChouQianService.selectByPrimaryKey(Integer.parseInt(chouqianid));
			if (chouqian == null) {
				return null;
			}
			if (chouqian.getZhuangTai() == 4) {
				List<Map<String, Object>> xiaozus = app_XueXiZuService.getXueXiZuByKeCheng2(kechengid);
				for (int i = 0; i < xiaozus.size(); i++) {
					App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService.selectJieGuoByXiaoZuId(kaopingid,
							Integer.parseInt(xiaozus.get(i).get("ID").toString()));
					if (app_KaoPingJieGuo == null) {
						app_KaoPingJieGuo = new App_KaoPingJieGuo();
					}
					xiaozus.get(i).put("kaopingjieguo", app_KaoPingJieGuo);
				}
				Map<String, Object> mapss = new HashMap<>();
				daZuList.add(mapss);
				daZuList.get(0).put("xiaozu", xiaozus);
				daZuList.get(0).put("dazuzhangid", dazuzhangid);
			}
			map.put("chouqian", chouqian);
			map.put("dazulist", daZuList);
			return map;
		}
		daZuList = app_FenZuService.getDaZu(kechengid);
		for (int k = 0; k < daZuList.size(); k++) {
			App_KaoPingChouQian chouqian2 = app_KaoPingChouQianService.selectByPrimaryKey1(kaopingid,
					Integer.parseInt(daZuList.get(k).get("ID").toString()));
			if (chouqian2 == null) {
				// 还没有抽签信息
				App_KaoPingChouQian app_KaoPingChouQian = new App_KaoPingChouQian();
				chouqian2 = new App_KaoPingChouQian();
				app_KaoPingChouQian.setKaoPingId(kaopingid);
				app_KaoPingChouQian.setDaZuXuHao(Integer.parseInt(daZuList.get(k).get("ID").toString()));
				app_KaoPingChouQian.setZhuangTai(1);
				int n = app_KaoPingChouQianService.insert(app_KaoPingChouQian);
				chouqian2.setId(app_KaoPingChouQian.getId());
				chouqian2.setKaoPingId(kaopingid);
				chouqian2.setDaZuXuHao(xueXiZus.get(0).getFenZuId());
				chouqian2.setZhuangTai(1);
			}
			if (chouqian2.getZhuangTai() == 4) {
				List<Map<String, Object>> xiaozus = app_XueXiZuService
						.selectXiaoZu(Integer.parseInt(daZuList.get(k).get("ID").toString()));
				for (int i = 0; i < xiaozus.size(); i++) {
					App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService.selectJieGuoByXiaoZuId(kaopingid,
							Integer.parseInt(xiaozus.get(i).get("ID").toString()));
					if (app_KaoPingJieGuo == null) {
						app_KaoPingJieGuo = new App_KaoPingJieGuo();
					}
					xiaozus.get(i).put("kaopingjieguo", app_KaoPingJieGuo);
				}
				daZuList.get(k).put("xiaozu", xiaozus);
				daZuList.get(k).put("dazuzhangid", daZuList.get(k).get("zuZhangID"));
				daZuList.get(k).put("chouqian", chouqian2);
			}
		}
		map.put("dazulist", daZuList);
		return map;
	}

	/**
	 * 获得每个小组的分数
	 * 
	 * @param kaopingid
	 *            kechengid xueshengid
	 * @return JSONObject
	 * @throws ParseException
	 */
	public Map<String, Object> getFenShu(Integer kaopingid, Integer kechengid, Integer xueshengid, String fendazu,
			String fenxiaozu, String dazuzhangid) {
		Map<String, Object> map = new HashMap<>();
		App_XueXiZu tmp = new App_XueXiZu();
		tmp.setShiJianKeChengId(kechengid);
		String str = "%," + xueshengid + ",%";
		tmp.setChengYuanLieBiao(str);

		// SELECT * FROM app_kechengkaoping WHERE ID in( SELECT kaoPingID FROM
		// app_kaopingjieguo WHERE xueXiZuID in( SELECT ID FROM app_xuexizu WHERE
		// chengYuanIDLieBiao LIKE '%,10038,%'))
		List<App_XueXiZu> xueXiZus = app_XueXiZuService.selectXueXiZuByXsID(tmp);
		if (xueXiZus.size() == 0) {
			return map;
		}
		Map<String, Object> chouqian = new HashMap<>();
		Map<String, Object> paraMap = new HashMap<>();
		String dazuid = null;
		paraMap.put("kaopingid", kaopingid);
		if (fendazu.equals("0")) {
			paraMap.put("dazuid", dazuid);
			chouqian = app_KaoPingChouQianService.getchouqianbyfenzu1(kaopingid);
			List<Map<String, Object>> daZuList = new ArrayList<>();
			map.put("dazuxinxi", daZuList);
		}
		if (fendazu.equals("1")) {
			paraMap.put("dazuid", xueXiZus.get(0).getFenZuId());
			chouqian = app_KaoPingChouQianService.selectchouqian(paraMap);
			List<Map<String, Object>> daZuList = new ArrayList<>();
			daZuList = app_FenZuService.getFenZu(kechengid);
			map.put("dazuxinxi", daZuList);
		}
		if (chouqian == null) {
			// 还没有抽签的信息
			chouqian = new HashMap<>();
			App_KaoPingChouQian app_KaoPingChouQian = new App_KaoPingChouQian();
			app_KaoPingChouQian.setKaoPingId(kaopingid);
			app_KaoPingChouQian.setZhuangTai(1);
			if (!fendazu.equals("0")) {
				app_KaoPingChouQian.setDaZuXuHao(xueXiZus.get(0).getFenZuId());
			}
			int n = app_KaoPingChouQianService.insert(app_KaoPingChouQian);
			chouqian.put("ID", app_KaoPingChouQian.getId());
			chouqian.put("kaoPingID", kaopingid);
			chouqian.put("zhuangTai", 1);
			if (fendazu.equals("0")) {
				String dazuxuehao = "";
				chouqian.put("daZuXuHao", dazuxuehao);
			} else {
				chouqian.put("daZuxuHao", xueXiZus.get(0).getFenZuId());
			}
		}
		map.put("chouqian", chouqian);
		return map;
	}

	/**
	 * 获取签到小组的信息
	 * 
	 * @param kaopingid
	 *            kechengid xueshengid
	 * @return JSONObject
	 * @throws ParseException
	 */
	private Map<String, Object> getQianDaoXiaoZuXinXi(Integer kaopingid, Integer kechengid, Integer xueshengid,
			String fendazu, String fenxiaozu, String chouqianid, String dazuzhangid) {
		Map<String, Object> map = new HashMap<>();
		List<Object> qiandaoxiaozu = new ArrayList<>();
		App_XueXiZu tmp = new App_XueXiZu();
		tmp.setShiJianKeChengId(kechengid);
		String str = "%," + xueshengid + ",%";
		tmp.setChengYuanLieBiao(str);
		List<App_XueXiZu> xueXiZus = app_XueXiZuService.selectXueXiZuByXsID(tmp);
		if (xueXiZus.size() == 0) {
			return map;
		}
		if (fendazu.equals("0")) {
			map.put("dazuzhangid", dazuzhangid);
		}
		if (fendazu.equals("1")) {
			App_FenZu app_fenzu = app_FenZuService.selectByPrimaryKey(xueXiZus.get(0).getFenZuId());
			if (app_fenzu == null) {
				return null;
			}
			map.put("dazuzhangid", app_fenzu.getZuZhangId());
		}
		App_KaoPingChouQian app_KaoPingChouQian = new App_KaoPingChouQian();
		app_KaoPingChouQian = app_KaoPingChouQianService.selectByPrimaryKey(Integer.parseInt(chouqianid));
		if (app_KaoPingChouQian == null) {
			return null;
		}
		if (app_KaoPingChouQian.getQianDao() != null) {
			String[] qiandao = app_KaoPingChouQian.getQianDao().substring(1).split(",");
			for (int k = 0; k < qiandao.length; k++) {
				App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(qiandao[k]));
				App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService.selectJieGuoByXiaoZuId(kaopingid,
						app_XueXiZu.getId());
				if (app_KaoPingJieGuo != null) {
					if (app_KaoPingJieGuo.getPaiXu() != null)
						app_XueXiZu.setPaixu(app_KaoPingJieGuo.getPaiXu());
					else {
						app_XueXiZu.setPaixu("");
					}
				} else {
					app_XueXiZu.setPaixu("");
				}
				qiandaoxiaozu.add(app_XueXiZu);
			}
			map.put("yiqiandaoxiaozu", qiandaoxiaozu);
			map.put("suozaixiaozuid", xueXiZus.get(0).getId());
			map.put("xiaozupaixu", app_KaoPingChouQian.getXiaozupaixu());
			map.put("chouqian", app_KaoPingChouQian);
		} else {
			return map;
		}
		return map;
	}

	/**
	 * 获得当前和下一小组的信息
	 * 
	 * @param kechengid
	 *            kaopingid xueshengid
	 * @return JSONObject
	 * @throws ParseException
	 */
	private Map<String, Object> getDangQianAndNextZu(Integer kechengid, Integer kaopingid, Integer xueshengid,
			String fendazu, String fenxiaozu, String chouqianid, String dazhuzhangid) {
		Map<String, Object> map = new HashMap<>();
		App_XueXiZu tmp = new App_XueXiZu();
		tmp.setShiJianKeChengId(kechengid);
		String str = "%," + xueshengid + ",%";
		tmp.setChengYuanLieBiao(str);
		List<App_XueXiZu> xueXiZus = app_XueXiZuService.selectXueXiZuByXsID(tmp);
		if (xueXiZus.size() == 0) {
			return map;
		}
		if (fendazu.equals("0")) {
			if (dazhuzhangid == null) {
				return map;
			}
		}
		App_KaoPingChouQian app_KaoPingChouQian = new App_KaoPingChouQian();
		app_KaoPingChouQian = app_KaoPingChouQianService.selectByPrimaryKey(Integer.parseInt(chouqianid));
		if (fendazu.equals("0")) {
			map.put("dazuzhangid", dazhuzhangid);
			XueSheng xueSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(dazhuzhangid));
			if (xueSheng != null) {
				map.put("dazuzhang", xueSheng.getXingming());
			}
		} else {
			App_FenZu app_fenzu = app_FenZuService.selectByPrimaryKey(xueXiZus.get(0).getFenZuId());
			if (app_fenzu == null) {
				return null;
			}
			XueSheng xueSheng = xueShengService.selectByPrimaryKey(app_fenzu.getZuZhangId());
			if (xueSheng != null) {
				map.put("dazuzhang", xueSheng.getXingming());
			}
			map.put("dazuzhangid", app_fenzu.getZuZhangId());
		}
		if (app_KaoPingChouQian.getXiaozupaixu() != null && app_KaoPingChouQian.getXiaozupaixu().length() > 0) {
			List<App_XueXiZu> xueXiZus2 = new ArrayList<>();
			String[] paixuxiaozus = app_KaoPingChouQian.getXiaozupaixu().split(",");
			for (int j = 0; j < paixuxiaozus.length; j++) {
				App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(paixuxiaozus[j]));
				if (app_XueXiZu == null) {
					continue;
				}
				xueXiZus2.add(app_XueXiZu);
			}
			map.put("yipaixuxuexizu", xueXiZus2);
			map.put("paixustatus", "sort");
		} else {
			map.put("paixustatus", "unsort");
			return map;
		}
		if ((app_KaoPingChouQian.getChouQian() == null || "".equals(app_KaoPingChouQian.getChouQian()))
				&& (app_KaoPingChouQian.getDangQianZuId() == null
						|| "".equals(app_KaoPingChouQian.getDangQianZuId().toString()))) {
			map.put("chouqianstatus", "init");
		} else {
			map.put("chouqianstatus", "started");
			Map<String, Object> yanjiangmap = new HashMap<>();
			String[] paixuxiaozu = app_KaoPingChouQian.getXiaozupaixu().split(",");
			String fanyanrenid = "";
			App_XueXiZu app_XueXiZu = null;
			if (app_KaoPingChouQian.getDangQianZuId() == null
					&& "".equals(app_KaoPingChouQian.getDangQianZuId().toString())) {
				return map;
			}
			app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(app_KaoPingChouQian.getDangQianZuId());
			if (app_XueXiZu == null) {
				return map;
			}
			Map<String, Object> dangQianMap = new HashMap<>();
			App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService.selectJieGuoByXiaoZuId(kaopingid,
					app_XueXiZu.getId());
			if (app_XueXiZu.getChengYuanLieBiao() == null) {
				return map;
			}
			List<Map<String, Object>> list2 = new ArrayList<>();
			list2 = getOneXiaoZuChengYuan(app_XueXiZu);
			dangQianMap.put("chengyuan", list2);
			dangQianMap.put("dangqianzuming", app_XueXiZu.getXueXiZuMing());
			dangQianMap.put("dangqianyanjiangzuid", app_XueXiZu.getId());
			if (app_KaoPingJieGuo == null) {
				map.put("status", "nokaopingjieguo");
				map.put("kaopingjieguo", app_KaoPingJieGuo);
				return map;
			}
			Map<String, Object> map2 = new HashMap<>();
			Integer fayanrenid = app_KaoPingJieGuo.getFaYanRenId();
			XueSheng xSheng = xueShengService.selectByPrimaryKey(fayanrenid);
			map2.put("fayanrenid", xSheng.getXueshengid());
			map2.put("fayanrenxingming", xSheng.getXingming());
			dangQianMap.put("fayanren", map2);
			map.put("kaopingjieguo", app_KaoPingJieGuo);
			yanjiangmap.put("dangqianyanjiang", dangQianMap);
			// 获取下一小组信息
			if (app_KaoPingChouQian.getChouQian() != null && !"".equals(app_KaoPingChouQian.getChouQian())) {
				fanyanrenid = app_KaoPingChouQian.getChouQian();
				App_XueXiZu tmp2 = new App_XueXiZu();
				tmp2.setShiJianKeChengId(kechengid);
				String str2 = "%," + fanyanrenid + ",%";
				tmp2.setChengYuanLieBiao(str2);
				List<App_XueXiZu> app_XueXiZu3 = app_XueXiZuService.selectXueXiZuByXsID(tmp2);
				if (app_XueXiZu3 == null || app_XueXiZu3.size() < 0) {
					return map;
				}
				Map<String, Object> xiaYiMap = new HashMap<>();
				App_KaoPingJieGuo app_KaoPingJieGuoss = app_KaoPingJieGuoService.selectJieGuoByXiaoZuId(kaopingid,
						Integer.parseInt(app_XueXiZu3.get(0).getId().toString()));
				if (app_XueXiZu3.get(0).getChengYuanLieBiao().toString() == null) {
					return null;
				}
				List<Map<String, Object>> ls = new ArrayList<>();
				ls = getOneXiaoZuChengYuan(app_XueXiZu3.get(0));
				xiaYiMap.put("chengyuan", ls);
				xiaYiMap.put("xiayizuming", app_XueXiZu3.get(0).getXueXiZuMing());
				xiaYiMap.put("xiayiyanjiangzuid", app_XueXiZu3.get(0).getId().toString());
				if (app_KaoPingJieGuoss != null) {
					Map<String, Object> map3 = new HashMap<>();
					Integer fayanrenids = app_KaoPingJieGuoss.getFaYanRenId();
					XueSheng xShengs = xueShengService.selectByPrimaryKey(fayanrenids);
					map3.put("fayanrenid", xShengs.getXueshengid());
					map3.put("fayanrenxingming", xShengs.getXingming());
					xiaYiMap.put("fayanren", map3);
				} else {
					map.put("status", "nokaopingjieguo");// 可以新插入一条数据
				}
				yanjiangmap.put("xiayiyanjiang", xiaYiMap);
			} else {
				String xiayizuid = "";
				if (!app_KaoPingChouQian.getDangQianZuId().toString().equals(paixuxiaozu[paixuxiaozu.length - 1])) {
					for (int m = 0; m < paixuxiaozu.length - 1; m++) {
						if (paixuxiaozu[m].equals(app_KaoPingChouQian.getDangQianZuId().toString())) {
							if (m + 1 <= paixuxiaozu.length - 1) {
								xiayizuid = paixuxiaozu[m + 1];
								break;
							} else {
								break;
							}
						} else {
							continue;
						}
					}
				} else {
					map.put("yanjiangstatus", "last");
				}
				Map<String, Object> xiaYiMap = new HashMap<>();
				if (xiayizuid != null && !"".equals(xiayizuid)) {
					App_XueXiZu app_XueXiZu3 = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiayizuid));
					App_KaoPingJieGuo app_KaoPingJieGuoss = app_KaoPingJieGuoService.selectJieGuoByXiaoZuId(kaopingid,
							Integer.parseInt(xiayizuid));
					if (app_XueXiZu3.getChengYuanLieBiao() == null) {
						return null;
					}
					List<Map<String, Object>> list3 = new ArrayList<>();
					list3 = getOneXiaoZuChengYuan(app_XueXiZu3);
					xiaYiMap.put("chengyuan", list3);
					xiaYiMap.put("xiayizuming", app_XueXiZu3.getXueXiZuMing());
					xiaYiMap.put("xiayiyanjiangzuid", app_XueXiZu3.getId());
					if (app_KaoPingJieGuoss != null) {
						Map<String, Object> map3 = new HashMap<>();
						Integer fayanrenid2 = app_KaoPingJieGuoss.getFaYanRenId();
						if (fayanrenid2 == null) {
							map3.put("fayanrenid", "");
							map3.put("fayanrenxingming", "");
						} else {

							XueSheng xSheng2 = xueShengService.selectByPrimaryKey(fayanrenid2);
							map3.put("fayanrenid", xSheng2.getXueshengid());
							map3.put("fayanrenxingming", xSheng2.getXingming());
						}
						xiaYiMap.put("fayanren", map3);
					}
				}
				yanjiangmap.put("xiayiyanjiang", xiaYiMap);
			}
			map.put("yanjiang", yanjiangmap);
		}
		map.put("chouqian", app_KaoPingChouQian);
		map.put("suozaixiaozuid", xueXiZus.get(0).getId());
		return map;
	}

	/**
	 * 得到所有实践课考评
	 * 
	 * @param xueshengid
	 *            token status
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_getkaoping")
	public JSONObject app_getkaoping(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		JSONObject jsonObject = new JSONObject();
		String shijiankechengid = request.getParameter("shijiankechengid");
		String fendazu = request.getParameter("fendazu");
		String fenxiaozu = request.getParameter("fenxiaozu");
		String dazuzhangid = request.getParameter("dazuzhangid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			// 登录信息失效
			return jsonObject;
		}
		List<Map<String, Object>> kaoPingList = app_KeChengKaoPingService
				.getKaoPing(Integer.parseInt(shijiankechengid));
		if (kaoPingList == null || kaoPingList.size() == 0) {
			// 没有查到课程的考评信息
			return jsonObject;
		}
		for (int i = 0; i < kaoPingList.size(); i++) {
			// 检查是否有正在进行的考评，如果有，转入考评，没有，则返回考评列表
			// if (Integer.parseInt(kaoPingList.get(i).get("zhuangTai").toString()) >3) {
			// continue;
			// }
			Map<String, Object> map = null;
			// 找到了正在进行的考评
			switch (kaoPingList.get(i).get("zhuangTai").toString()) {
			case "1":
				// 考评开始，打分
				map = getXinXi(Integer.parseInt(shijiankechengid),
						Integer.parseInt(kaoPingList.get(i).get("ID").toString()), Integer.parseInt(xueshengid),
						fendazu, fenxiaozu, dazuzhangid);
				break;
			case "4":
				// 查看各小组排序结果
				map = getFenShu(Integer.parseInt(kaoPingList.get(i).get("ID").toString()),
						Integer.parseInt(shijiankechengid), Integer.parseInt(xueshengid), fendazu, fenxiaozu,
						dazuzhangid);
				// break;
			default:
				break;
			}
			kaoPingList.get(i).put("qiandaoxinxi", map);
			// break;
		}
		jsonObject.put("kaoPing", kaoPingList);
		return jsonObject;
	}

	/**
	 * 获得大组信息
	 * 
	 * @param xueshengid
	 *            token status
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_getdazu")
	public JSONObject app_getdazu(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		JSONObject jsonObject = new JSONObject();
		String shijiankechengid = request.getParameter("kechengid");
		String kaopingid = request.getParameter("kaopingid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		List<Map<String, Object>> daZuList = app_FenZuService.getDaZu(Integer.parseInt(shijiankechengid));
		for (int k = 0; k < daZuList.size(); k++) {
			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("kaopingid", Integer.parseInt(kaopingid));
			paraMap.put("dazuid", Integer.parseInt(daZuList.get(k).get("ID").toString()));
			Map<String, Object> chouqian = app_KaoPingChouQianService.selectchouqian(paraMap);
			if (chouqian == null) {
				// 还没有抽签的信息
				App_KaoPingChouQian app_KaoPingChouQian = new App_KaoPingChouQian();
				chouqian = new HashMap<String, Object>();
				app_KaoPingChouQian.setKaoPingId(Integer.parseInt(kaopingid));
				app_KaoPingChouQian.setDaZuXuHao(Integer.parseInt(daZuList.get(k).get("ID").toString()));
				app_KaoPingChouQian.setZhuangTai(1);
				int n = app_KaoPingChouQianService.insert(app_KaoPingChouQian);
				chouqian.put("ID", app_KaoPingChouQian.getId());
				chouqian.put("kaoPingID", Integer.parseInt(kaopingid));
				chouqian.put("daZuXuHao", Integer.parseInt(daZuList.get(k).get("ID").toString()));
				chouqian.put("zhuangTai", 1);
			}
			daZuList.get(k).put("chouqian", chouqian);
		}
		jsonObject.put("dazuxinxi", daZuList);
		// }
		return jsonObject;
	}

	/**
	 * 小组长签到
	 * 
	 * @param xueshengid
	 *            status kaopingchouqianid suozaixiaozuid dazuid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_qiandao")
	public JSONObject app_qiandao(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String chouqianid = request.getParameter("kaopingchouqianid");
		String xiaozuid = request.getParameter("suozaixiaozuid");
		String dazuid = request.getParameter("dazuid");
		String fendazu = request.getParameter("fendazu");
		String fenxiaozu = request.getParameter("fenxiaozu");
		String shijiankechengid = request.getParameter("shijiankechengid");
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		Map<String, Object> map = new HashMap<>();
		App_KaoPingChouQian app_KaoPingChouQian = app_KaoPingChouQianService
				.selectByPrimaryKey(Integer.parseInt(chouqianid));
		List<App_XueXiZu> xueXiZus = new ArrayList<>();
		String qianDao = null;
		if (app_KaoPingChouQian != null) {
			if (app_KaoPingChouQian.getQianDao() != null && app_KaoPingChouQian.getQianDao().length() > 0) {
				qianDao = app_KaoPingChouQian.getQianDao();
				if (qianDao.contains("," + xiaozuid + ",")) {
					jsonObject.put("yiqiandao", "yiqiandao");
				} else {
					qianDao += xiaozuid + ",";
				}
			} else {
				qianDao = "," + xiaozuid + ",";
			}
			app_KaoPingChouQian.setQianDao(qianDao);
			int k = app_KaoPingChouQianService.updateByPrimaryKey(app_KaoPingChouQian);
			if (k != 0) {
				jsonObject.put("status", "success");
				String[] qiandaoxiaozu = qianDao.substring(1).split(",");
				for (int i = 0; i < qiandaoxiaozu.length; i++) {
					App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(qiandaoxiaozu[i]));
					if (app_XueXiZu != null) {
						xueXiZus.add(app_XueXiZu);
					} else {
						continue;
					}
				}
				List<App_XueXiZu> allXueXiZu = new ArrayList<>();
				if (fendazu.equals("0")) {
					allXueXiZu = app_XueXiZuService.getXueXiZuByKeCheng(Integer.parseInt(shijiankechengid));
					map.put("dazuming", "");
				} else {
					App_FenZu app_FenZu = app_FenZuService.selectByPrimaryKey(Integer.parseInt(dazuid));
					allXueXiZu = app_XueXiZuService.getXueXiZu(Integer.parseInt(dazuid));
					map.put("dazuming", app_FenZu.getZuMingCheng());
				}

				for (int i = 0; i < allXueXiZu.size(); i++) {
					allXueXiZu.get(i).setXiaozuzhang(
							xueShengService.selectByPrimaryKey(allXueXiZu.get(i).getXiaoZuZhangId()).getXingming());
					for (String string2 : qiandaoxiaozu) {
						if (allXueXiZu.get(i).getId().toString().equals(string2)) {
							allXueXiZu.get(i).setQiandao("1");
						} else {
							continue;
						}
					}
				}
				map.put("mxiaozu", allXueXiZu);
				jsonObject.put("dazu", map);
			} else {
				jsonObject.put("status", "fail");
			}
		} else {
			jsonObject.put("error", "error");
		}
		jsonObject.put("yiqiandaoxiaozu", xueXiZus);

		return jsonObject;
	}

	/**
	 * 大组组长对所有已签到小组排序
	 * 
	 * @param xueshengid
	 *            token status yidao kaopingchouqianid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_xiaozupaixu")
	public JSONObject app_xiaozupaixu(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String[] xiaozuids = request.getParameterValues("yidao");
		String chouqianid = request.getParameter("kaopingchouqianid");
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			App_KaoPingChouQian app_KaoPingChouQian = app_KaoPingChouQianService
					.selectByPrimaryKey(Integer.parseInt(chouqianid));
			List<App_XueXiZu> xueXiZusNew = new ArrayList<>();
			if (xiaozuids != null && xiaozuids.length > 0) {
				for (int i = 0; i < xiaozuids.length; i++) {
					if (xiaozuids[i] != null) {
						Random random = new Random();
						int k = random.nextInt(xiaozuids.length);
						String temp = xiaozuids[i];
						xiaozuids[i] = xiaozuids[k];
						xiaozuids[k] = temp;
					} else {
						continue;
					}
				}
				String string2 = "";
				for (int i = 0; i < xiaozuids.length; i++) {
					if (xiaozuids[i] != null && !"".equals(xiaozuids[i])) {
						string2 += xiaozuids[i] + ",";
					} else {
						continue;
					}
				}
				app_KaoPingChouQian.setXiaozupaixu(string2);
				// app_KaoPingChouQian.setZhuangTai(2);
				int k = app_KaoPingChouQianService.updateByPrimaryKey(app_KaoPingChouQian);
				if (k != 0) {
					jsonObject.put("status", "success");
				} else {
					jsonObject.put("status", "fail");
				}
			} else {
				jsonObject.put("error", "error");
			}
			if (xiaozuids != null) {
				for (int k = 0; k < xiaozuids.length; k++) {
					App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuids[k]));
					if (app_XueXiZu != null) {
						xueXiZusNew.add(app_XueXiZu);
					} else {
						continue;
					}
				}
			}
			jsonObject.put("xuexizus", xueXiZusNew);
		}
		return jsonObject;
	}

	/**
	 * 考评开始
	 * 
	 * @param xueshengid
	 *            token status kaopingid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_kaopingkaishi")
	public JSONObject app_kaishi(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaopingid = request.getParameter("kaopingid");
		String fendazu = request.getParameter("fendazu");
		String fenxiaozu = request.getParameter("fenxiaozu");
		String chouqianid = request.getParameter("chouqianid");
		App_KeChengKaoPing app_KeChengKaoPing = app_KeChengKaoPingService
				.selectByPrimaryKey(Integer.parseInt(kaopingid));
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			App_XueXiZu tmp = new App_XueXiZu();
			tmp.setShiJianKeChengId(app_KeChengKaoPing.getShiJianKeChengId());
			String chengyuanid = "%," + xueshengid + ",%";
			tmp.setChengYuanLieBiao(chengyuanid);
			List<App_XueXiZu> xueXiZus = app_XueXiZuService.selectXueXiZuByXsID(tmp);
			if (xueXiZus.size() == 0) {
				return jsonObject;
			}
			App_KaoPingChouQian app_KaoPingChouQian = app_KaoPingChouQianService
					.selectByPrimaryKey(Integer.parseInt(chouqianid));
			app_KaoPingChouQian.setZhuangTai(2);
			if (app_KaoPingChouQian.getXiaozupaixu() == null) {
				return null;
			}
			if (fenxiaozu.equals("0")) {
				String paixuxiaozu[] = app_KaoPingChouQian.getXiaozupaixu().split(",");
				App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
						.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), Integer.parseInt(paixuxiaozu[0]));
				if (app_KaoPingJieGuo == null) {
					return null;
				}
				app_KaoPingJieGuo.setZhuangTai(2);
				app_KaoPingJieGuoService.update(app_KaoPingJieGuo);
				app_KaoPingChouQian.setDangQianZuId(Integer.parseInt(paixuxiaozu[0]));
			}
			int k = app_KaoPingChouQianService.updateByPrimaryKey(app_KaoPingChouQian);
			if (k != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		}
		return jsonObject;
	}

	/**
	 * 小组长抽签，抽取下一组演讲人信息
	 * 
	 * @param xueshengid
	 *            token status kaopingid dangqianxiaozuid chouqianid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_xiaozuchouqian")
	public JSONObject app_xiaozuchouqian(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaopingid = request.getParameter("kaopingid");
		String dangqianxiaozuid = request.getParameter("dangqianxiaozuid");
		String chouqianid = request.getParameter("chouqianid");
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			jsonObject.put("status", "unsort");
		}
		App_KaoPingChouQian app_KaoPingChouQian = app_KaoPingChouQianService
				.selectByPrimaryKey(Integer.parseInt(chouqianid));
		App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(dangqianxiaozuid));
		if (app_KaoPingChouQian == null) {
			jsonObject.put("status", "nokaopingchouqian");
		}
		String fayanrenid = "";
		if (!xueshengid.equals(app_XueXiZu.getXiaoZuZhangId().toString())) {
			jsonObject.put("status", "notxiaozuzhang");
		}
		if (app_KaoPingChouQian.getXiaozupaixu() == null) {
			jsonObject.put("status", "unsort");
		}
		String[] paixuxiaozu = app_KaoPingChouQian.getXiaozupaixu().split(",");
		// 当前组是最后一组,没有下一组
		if (dangqianxiaozuid.equals(paixuxiaozu[paixuxiaozu.length - 1])) {
			App_XueXiZu app_XueXiZu2 = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(paixuxiaozu[0]));
			if (app_XueXiZu2 != null && app_XueXiZu2.getChengYuanLieBiao() != null) {
				String[] memberid = app_XueXiZu2.getChengYuanLieBiao().substring(1).split(",");
				List<Object> list = new ArrayList<>();
				Random random = new Random();
				int n = random.nextInt(100);
				int k = n % (memberid.length);
				Map<String, Object> map = new HashMap<>();
				XueSheng xSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(memberid[k]));
				fayanrenid = xSheng.getXueshengid().toString();
				map.put("fayanrenid", fayanrenid);
				map.put("fayanrenxingming", xSheng.getXingming());
				List<Map<String, Object>> xueshenglist = xueShengService.selectByXueShengIDs(Arrays.asList(memberid));
				list.add(xueshenglist);
				map.put("xiayixiaozuid", paixuxiaozu[0]);
				map.put("xiaozuxinxi", list);
				jsonObject.put("fayanren", map);
			}
			// 新建考评结果,初始化转态为2
			App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
					.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), app_XueXiZu2.getId());
			if (app_KaoPingJieGuo == null) {
				return null;
			}
			app_KaoPingJieGuo.setZhuangTai(2);
			app_KaoPingJieGuo.setFaYanRenId(Integer.parseInt(fayanrenid));
			app_KaoPingJieGuoService.update(app_KaoPingJieGuo);
			app_KaoPingChouQian.setDangQianZuId(Integer.parseInt(paixuxiaozu[0]));
			int i = app_KaoPingChouQianService.updateByPrimaryKey(app_KaoPingChouQian);
			return jsonObject;
		}
		Map<String, Object> mapss = new HashMap<String, Object>();
		String xiayixiaozuid = getxiayizuid(app_KaoPingChouQian, mapss, paixuxiaozu);
		if (xiayixiaozuid != null) {
			App_XueXiZu app_XueXiZu2 = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiayixiaozuid));
			if (app_XueXiZu2 != null && app_XueXiZu2.getChengYuanLieBiao() != null) {
				String[] memberid = app_XueXiZu2.getChengYuanLieBiao().substring(1).split(",");
				Random random = new Random();
				int k = random.nextInt(memberid.length);
				Map<String, Object> map = new HashMap<>();
				List<Object> list = new ArrayList<>();
				XueSheng xSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(memberid[k]));
				fayanrenid = xSheng.getXueshengid().toString();
				map.put("fayanrenid", fayanrenid);
				map.put("fayanrenxingming", xSheng.getXingming());
				List<Map<String, Object>> xueshenglist = xueShengService.selectByXueShengIDs(Arrays.asList(memberid));
				list.add(xueshenglist);
				map.put("xiayixiaozuid", xiayixiaozuid);
				map.put("xiaozuxinxi", list);
				jsonObject.put("fayanren", map);
			}
			// 新建考评结果，初始状态为2
			App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
					.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), Integer.parseInt(xiayixiaozuid));
			if (app_KaoPingJieGuo == null) {
				return null;
			}
			app_KaoPingJieGuo.setFaYanRenId(Integer.parseInt(fayanrenid));
			app_KaoPingJieGuoService.update(app_KaoPingJieGuo);
			app_KaoPingChouQian.setChouQian(fayanrenid);
			int i = app_KaoPingChouQianService.updateByPrimaryKey(app_KaoPingChouQian);
			return jsonObject;
		}
		return jsonObject;
	}

	/**
	 * 大组长拍照
	 * 
	 * @param xueshengid
	 *            token status xiaozuid kaopingid base64Str
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_paizhao")
	public JSONObject app_paizhao(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xiaozuid = request.getParameter("xiaozuid");
		String kaopingid = request.getParameter("kaopingid");
		String base64Str = request.getParameter("base64Str");
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
				.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), Integer.parseInt(xiaozuid));
		String image[] = base64Str.split(",");
		String name = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
		String path = request.getSession().getServletContext().getRealPath("/") + "upload/kckp/" + xiaozuid + "/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		boolean x = UpdataImgerUtil.GenerateImage(image[1], path + name);
		if (x) {
			if (app_KaoPingJieGuo != null) {
				app_KaoPingJieGuo.setZhaoPian(name);
				app_KaoPingJieGuoService.update(app_KaoPingJieGuo);
				jsonObject.put("kaopingjieguo", app_KaoPingJieGuo);
				jsonObject.put("status", "success");
			} else {
				App_KaoPingJieGuo app_KaoPingJieGuo2 = new App_KaoPingJieGuo();
				app_KaoPingJieGuo2.setKaoPingId(Integer.parseInt(kaopingid));
				app_KaoPingJieGuo2.setXueXiZuId(Integer.parseInt(xiaozuid));
				app_KaoPingJieGuo2.setZhaoPian(name);
				app_KaoPingJieGuoService.insert(app_KaoPingJieGuo2);
				jsonObject.put("status", "success");
			}
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	/**
	 * 解析大组长所拍照片
	 * 
	 * @param id
	 *            xiaozuid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@RequestMapping(value = "app_getPict")
	@ResponseBody
	public void app_getPict(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String filename = request.getParameter("id");
		String xiaozuid = request.getParameter("xiaozuid");
		// 设置header
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		OutputStream os = response.getOutputStream();
		byte[] btImg = null;
		btImg = GetPic.getBytes(request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator
				+ "kckp" + File.separator + xiaozuid + File.separator + filename);
		if (btImg != null) {
			os.write(btImg);
			os.flush();
		}
	}

	/**
	 * 小组结束演讲
	 * 
	 * @param xueshengid
	 *            token status kaopingid dangqianxiaozuid kaopingchouqianid qiangzhi
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_complete") // 小组演讲完成
	public JSONObject app_complete(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaopingid = request.getParameter("kaopingid");
		String xiaozuid = request.getParameter("dangqianxiaozuid");
		String chouqianid = request.getParameter("kaopingchouqianid");
		String qiangzhi = request.getParameter("qiangzhi");
		App_KeChengKaoPing app_KeChengKaoPing = app_KeChengKaoPingService
				.selectByPrimaryKey(Integer.parseInt(kaopingid));
		App_KaoPingChouQian app_KaoPingChouQian = app_KaoPingChouQianService
				.selectByPrimaryKey(Integer.parseInt(chouqianid));
		App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuid));
		App_KaoPingJieGuo app_kaopingjieguo = app_KaoPingJieGuoService
				.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), Integer.parseInt(xiaozuid));
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		if (app_KaoPingChouQian == null) {
			jsonObject.put("status", "unchouqian");
		}
		String fayanrenid = app_KaoPingChouQian.getChouQian();
		Map<String, Object> mapss = new HashMap<>();
		Map<String, Object> yanjiangmap = new HashMap<>();
		if (app_KaoPingChouQian.getXiaozupaixu() == null && app_KaoPingChouQian.getXiaozupaixu().length() <= 0) {
			jsonObject.put("status", "unsort");
		}
		String[] paixuxiaozu = app_KaoPingChouQian.getXiaozupaixu().split(",");
		if ((fayanrenid == null || "".equals(fayanrenid)) && !xiaozuid.equals(paixuxiaozu[paixuxiaozu.length - 1])) {
			jsonObject.put("status", "nochouqian");
			return jsonObject;
		}
		if (qiangzhi == null || "".equals(qiangzhi)) {
			if (app_kaopingjieguo.getZhaoPian() == null || "".equals(app_kaopingjieguo.getZhaoPian())) {
				jsonObject.put("status", "nopaizhao");
				return jsonObject;
			}
		}
		if (app_XueXiZu == null) {
			jsonObject.put("status", "none");
			return jsonObject;
		}
		String string2 = "%," + fayanrenid + ",%";
		App_XueXiZu temp = new App_XueXiZu();
		temp.setChengYuanLieBiao(string2);
		temp.setShiJianKeChengId(app_KeChengKaoPing.getShiJianKeChengId());
		List<App_XueXiZu> list = app_XueXiZuService.selectXueXiZuByXsID(temp);
		// 当前组为本大组最后一组，抽签状态变为3，进入打分状态
		if (xiaozuid.equals(paixuxiaozu[paixuxiaozu.length - 1])) {
			app_KaoPingChouQian.setZhuangTai(3);
			int m = app_KaoPingChouQianService.updateByPrimaryKey(app_KaoPingChouQian);
			App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
					.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), app_XueXiZu.getId());
			app_KaoPingJieGuo.setZhuangTai(3);
			int k = app_KaoPingJieGuoService.update(app_KaoPingJieGuo);
		} else { // 选取下一组演讲人，作为当前组演讲人
			String xiayixiaozuid = "";
			String currentxiaozuid = list.get(0).getId().toString();
			if (currentxiaozuid.equals(paixuxiaozu[paixuxiaozu.length - 1])) {
				jsonObject.put("status", "last");
			}
			for (int i = 0; i < paixuxiaozu.length - 1; i++) {
				if (!paixuxiaozu[i].equals(currentxiaozuid)) {
					continue;
				}
				if (i + 1 <= paixuxiaozu.length - 1) {
					xiayixiaozuid = paixuxiaozu[i + 1];
					break;
				}
				continue;
			}
			App_XueXiZu app_XueXiZu3 = new App_XueXiZu();
			App_XueXiZu app_XueXiZu2 = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(currentxiaozuid));
			// 小组演讲完毕，考评结果状态变为3
			App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
					.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), app_XueXiZu.getId());
			app_KaoPingJieGuo.setZhuangTai(3);
			int k = app_KaoPingJieGuoService.update(app_KaoPingJieGuo);
			if (k != 0) {
				jsonObject.put("status", "success");
				if (app_XueXiZu2 == null) {
					jsonObject.put("status", "none");
					return jsonObject;
				}
				Map<String, Object> map = new HashMap<>();
				List<Object> list2 = new ArrayList<>();
				XueSheng xSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(fayanrenid));
				map.put("fayanrenid", fayanrenid);
				map.put("fayanrenxingming", xSheng.getXingming());
				if (app_XueXiZu2.getChengYuanLieBiao() != null) {
					String[] memeber = app_XueXiZu2.getChengYuanLieBiao().substring(1).split(",");
					List<Map<String, Object>> xueshenglist = xueShengService
							.selectByXueShengIDs(Arrays.asList(memeber));
					list2.add(xueshenglist);
					map.put("chengyuan", list2);
					yanjiangmap.put("dangqianyan", map);
				}
				App_KaoPingJieGuo app_KaoPingJieGuoss = app_KaoPingJieGuoService
						.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), app_XueXiZu2.getId());
				if (app_KaoPingJieGuoss == null) {
					return jsonObject;
				}
				// 本次考评结束状态变为3,下次考评开始状态变为2
				app_KaoPingJieGuoss.setZhuangTai(2);
				app_KaoPingJieGuoService.update(app_KaoPingJieGuoss);
				app_KaoPingChouQian.setDangQianZuId(app_XueXiZu2.getId());
				app_KaoPingChouQian.setChouQian("");
				int n = app_KaoPingChouQianService.updateByPrimaryKey(app_KaoPingChouQian);
				// 选取下下一组的演讲人，作为即将开始演讲的下一组的演讲人
				if (xiayixiaozuid == null || "".equals(xiayixiaozuid)) {
					jsonObject.put("status", "none");
					return jsonObject;
				}
				app_XueXiZu3 = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiayixiaozuid));
				if (app_XueXiZu3 == null) {
					jsonObject.put("status", "none");
					return jsonObject;
				}
				Map<String, Object> maps = new HashMap<>();
				List<Object> list3 = new ArrayList<>();
				if (app_XueXiZu3.getChengYuanLieBiao() == null) {
					return jsonObject;
				}
				String[] memeber = app_XueXiZu3.getChengYuanLieBiao().substring(1).split(",");
				List<Map<String, Object>> xueshenglist = xueShengService.selectByXueShengIDs(Arrays.asList(memeber));
				list3.add(xueshenglist);
				maps.put("chengyuan", list3);
				maps.put("fayanrenid", "");
				maps.put("fayanrenxingming", "");
				yanjiangmap.put("xiayiyanjiang", maps);
				jsonObject.put("yanjiang", yanjiangmap);
			} else {
				jsonObject.put("status", "fail");
			}

		}

		return jsonObject;
	}

	/**
	 * 小组长给其他小组打分
	 * 
	 * @param xueshengid
	 *            token status dangqianxiaozuid xiaozupaimingid kaopingid kechengid
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_dafen")
	public JSONObject app_dafen(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xiaozuid = request.getParameter("dangqianxiaozuid");
		String dazuid = request.getParameter("dangqiandazuid");
		String paiXu = request.getParameter("xiaozupaimingid");
		String kaopingid = request.getParameter("kaopingid");
		String kechengid = request.getParameter("kechengid");
		String fendazu = request.getParameter("fendazu");
		String fenxiaozu = request.getParameter("fenxiaozu");
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			jsonObject.put("status", "shixiao");
			return jsonObject;
		}
		if (paiXu == null) {
			return null;
		}
		// 更新本组的打分信息,更新考评结果的状态，打分完成考评结果状态置为4
		List<Map<String, Object>> daZuList = new ArrayList<>();
		App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
				.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), Integer.parseInt(xiaozuid));
		app_KaoPingJieGuo.setPaiXu(paiXu);
		app_KaoPingJieGuo.setZhuangTai(4);
		int k = app_KaoPingJieGuoService.update(app_KaoPingJieGuo);
		if (k != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		// 检查本次考评，是否所有的打分都已经完成
		switch (fendazu) {
		case "0":
			dazuid = null;
			jianChaDaZuDaFen(kaopingid, dazuid, fendazu, kechengid);
			break;
		case "1":
			// 分大组打分，检查本大组的情况
			jianChaDaZuDaFen(kaopingid, dazuid, fendazu, kechengid);
			break;
		}
		return jsonObject;
	}

	/*
	 * 检查大组内某次考评是否都已经完成了
	 * 
	 */
	private void jianChaDaZuDaFen(String kaoPingID, String daZuID, String fendazu, String kechengid) {
		// 获取本次考评的签到信息
		// SELECT * FROM app_kaopingchouqian WHERE kaoPingID=5
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("kaopingid", kaoPingID);
		paraMap.put("dazuid", daZuID);
		App_KaoPingChouQian chouqian = app_KaoPingChouQianService.selectchouqian2(paraMap);
		// 获取所有小组的考评结果
		// SELECT * FROM app_kaopingjieguo WHERE kaoPingID=5 AND xueXiZuID in
		// (-1,8,20,28,14,29,33,23,6,26,-1)
		// 检查每个小组的考评结果状态，如果发现不是4的，没有打分提交，退出
		if (chouqian.getQianDao() == null) {
			return;
		}
		Map<String, Object> paramap = new HashMap<>();
		paramap.put("kaopingid", kaoPingID);
		paramap.put("xiaozuids", Arrays.asList(chouqian.getXiaozupaixu().split(",")));
		List<Map<String, Object>> kaopingjieguoss = app_KaoPingJieGuoService.getJieGuoByKaoPingIDAndXiaoZuID(paramap);
		for (Map<String, Object> app_KaoPingJieGuo : kaopingjieguoss) {
			if (!app_KaoPingJieGuo.get("zhuangTai").toString().equals("4")) {
				return;
			}
		}
		// 所有小组都已经完成考评了,算分
		Map<String, Object> map = new HashMap<>();
		map.put("xiaozuids", Arrays.asList(chouqian.getXiaozupaixu().split(",")));
		map.put("kaopingid", kaoPingID);
		List<Map<String, Object>> list = app_XueXiZuService.selectAllXiaoZu(map);
		list = scoredefen(list);
		// 更新本大组考评的状态
		// app_kaopingchouqian
		chouqian.setZhuangTai(4);
		app_KaoPingChouQianService.updateByPrimaryKey(chouqian);
		// 获取本次考评的所有大组的考评状态
		// SELECT * FROM app_kaopingchouqian WHERE kaoPingID=5
		Map<String, Object> mapsss = new HashMap<>();
		mapsss.put("kaopingid", kaoPingID);
		List<App_KaoPingChouQian> chouQians = null;
		if (fendazu.equals("0")) {
			chouQians = app_KaoPingChouQianService.getchouqian(mapsss);
			for (App_KaoPingChouQian chouQian : chouQians) {
				if (chouQian == null) {
					return;
				}
				if (chouQian.getZhuangTai() != 4) {
					return;
				}
			}
		}
		if (fendazu.equals("1")) {
			List<Map<String, Object>> fenzulist = app_FenZuService.getDaZu(Integer.parseInt(kechengid));
			if (fenzulist.size() <= 0) {
				return;
			}
			for (int i = 0; i < fenzulist.size(); i++) {
				App_KaoPingChouQian app_KaoPingChouQian = app_KaoPingChouQianService.selectByPrimaryKey1(
						Integer.parseInt(kaoPingID), Integer.parseInt(fenzulist.get(i).get("ID").toString()));
				if (app_KaoPingChouQian == null) {
					return;
				}
				if (app_KaoPingChouQian.getZhuangTai() != 4) {
					return;
				}
			}

		}

		// 如果有没有完成的大组
		// 结束
		// 否则更新本次考评的状态
		// app_kechengkaoping
		App_KeChengKaoPing kaoPing = app_KeChengKaoPingService.selectByPrimaryKey(Integer.parseInt(kaoPingID));
		kaoPing.setZhuangTai(4);
		app_KeChengKaoPingService.update(kaoPing);
		return;
	}
	//只有一个小组时,跳过不判分
	@ResponseBody
	@RequestMapping(value = "app_shixidafen")
	public JSONObject app_shixidafen(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xiaozuid = request.getParameter("dangqianxiaozuid");
		String kaopingid = request.getParameter("kaopingid");
		String chouqianid = request.getParameter("chouqianid");
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			jsonObject.put("status", "shixiao");
			return jsonObject;
		}
		// 更新本组的打分信息,更新考评结果的状态，打分完成考评结果状态置为4
		App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
				.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), Integer.parseInt(xiaozuid));
		app_KaoPingJieGuo.setZhuangTai(4);
		App_KaoPingChouQian app_KaoPingChouQian = app_KaoPingChouQianService.selectByPrimaryKey(Integer.parseInt(chouqianid));
		app_KaoPingChouQian.setZhuangTai(4);
		app_KaoPingChouQianService.updateByPrimaryKey(app_KaoPingChouQian);
		int k = app_KaoPingJieGuoService.update(app_KaoPingJieGuo);
		if (k != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}


	/**
	 * 计算每个小组的得分，有则从考评结果里面取，没有则计算
	 * 
	 * @param count
	 * @return JSONObject
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "app_score")
	public String app_score(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String dazuid = request.getParameter("dazuid");
		String kaopingid = request.getParameter("kaopingid");
		String fendazu = request.getParameter("fendazu");
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		System.out.println("app_score");
		if (!str.equals(token)) {
			return jsonObject.toString();
		}
		// 还没有抽签，不存在查询成绩
		Map<String, Object> paraMap = new HashMap<>();
		Map<String, Object> chouqian = new HashMap<String, Object>();
		paraMap.put("kaopingid", kaopingid);
		paraMap.put("dazuid", dazuid);
		if (fendazu.equals("0")) {
			chouqian = app_KaoPingChouQianService.getchouqianbyfenzu1(Integer.parseInt(kaopingid));
		}
		if (fendazu.equals("1")) {
			chouqian = app_KaoPingChouQianService.selectchouqian(paraMap);
		}
		if (chouqian == null) {
			return null;
		}
		System.out.println("2");
		// 获取了本次参加考评的所有小组的信息
		Map<String, Object> map = new HashMap<>();
		map.put("xiaozuids", Arrays.asList(chouqian.get("xiaoZuPaiXu").toString().split(",")));
		map.put("kaopingid", kaopingid);
		List<Map<String, Object>> list = app_XueXiZuService.selectAllXiaoZu(map);
		if (list == null) {
			return null;
		}
		if (list.size() == 0 ) {
			return jsonObject.toString();
		}
		System.out.println("3");
		String xueShengIDs = "";
		for (int i = 0; i < list.size(); i++) {
			// 取得小组内学生的ID
			xueShengIDs += list.get(i).get("chengYuanIDLieBiao").toString().substring(1);
		}
		if (xueShengIDs == null || "".equals(xueShengIDs)) {
			return jsonObject.toString();
		}
		// 获取只有一个小组时所有的学生信息
		System.out.println("4");
		List<Map<String, Object>> listXueSheng = null;
		listXueSheng = xueShengService.selectByXueShengIDs(Arrays.asList(xueShengIDs.split(",")));
		if (list.size() == 1 ) {
			for(int i=0;i<list.size();i++) {
				list.get(i).put("fayanren",
						getXueShengXingMing(listXueSheng, Integer.parseInt(list.get(i).get("faYanRenID").toString())));
				if (list.get(i).get("chengYuanIDLieBiao") != null && !"".equals(list.get(i).get("chengYuanIDLieBiao"))) {
					String[] members = list.get(i).get("chengYuanIDLieBiao").toString().substring(1).split(",");
					List<Map<String, Object>> list2 = new ArrayList<>();
					for (int m = 0; m < members.length; m++) {
						// 按照学生的ID，从listXueSheng中找到这个学生
						Map<String, Object> map2 = getXueShengXinXi(listXueSheng, Integer.parseInt(members[m]));
						list2.add(map2);
					}
					list.get(i).put("chengyuan", list2);
				}
				list.get(i).put("mingci", 1);
			}
			jsonObject.put("xiaozulist", list);
			return jsonObject.toString();
		}
		// 每个小组的总得分
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> kaoPingJieGuo = list.get(i);
			if (kaoPingJieGuo.get("mingci") == null) {
				// 调用计算分数的函数
				list = scoredefen(list);
				// 重新读取这个list
				i = -1;
				continue;
			}
		}
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> kaoPingJieGuo = list.get(i);
			list.get(i).put("fayanren",
					getXueShengXingMing(listXueSheng, Integer.parseInt(kaoPingJieGuo.get("faYanRenID").toString())));
			if (list.get(i).get("chengYuanIDLieBiao") != null && !"".equals(list.get(i).get("chengYuanIDLieBiao"))) {
				String[] members = list.get(i).get("chengYuanIDLieBiao").toString().substring(1).split(",");
				List<Map<String, Object>> list2 = new ArrayList<>();
				for (int m = 0; m < members.length; m++) {
					// 按照学生的ID，从listXueSheng中找到这个学生
					Map<String, Object> map2 = getXueShengXinXi(listXueSheng, Integer.parseInt(members[m]));
					list2.add(map2);
				}
				list.get(i).put("chengyuan", list2);
			}

			// 41_45_8;41_42_7;41_48_6;41_43_5;41_44_4;41_49_3;41_47_2;41_46_1;
			// 调用函数，获取小组名
			String[] info = kaoPingJieGuo.get("paiXu").toString().split(";");
			String dafen = "";
			for (int j = 0; j < info.length; j++) {
				String[] item = info[j].split("_");
				dafen += getXiaoZuMing(list, Integer.parseInt(item[1])) + "_" + item[2] + ";";
			}
			list.get(i).put("dafen", dafen);

			// 计算小组排名
			String defen = "";
			for (int j = 0; j < list.size(); j++) {
				Map<String, Object> jg = list.get(j);
				if (jg.get("ID").equals(kaoPingJieGuo.get("ID"))) {
					continue;
				}
				// 读取排序42_44_8;42_48_7;42_45_6;42_49_5;42_41_4;42_43_3;42_46_2;42_47_1;
				String[] info1 = jg.get("paiXu").toString().split(";");
				for (int k = 0; k < info1.length; k++) {
					if (!info1[k].contains("_" + kaoPingJieGuo.get("ID").toString() + "_")) {
						continue;
					}
					String[] item = info1[k].split("_");
					defen += jg.get("xueXiZuMing") + "_" + item[2] + ";";
					break;
				}
			}
			list.get(i).put("defenliebiao", defen);
		}
		jsonObject.put("xiaozulist", list);
		return jsonObject.toString();
	}

	private String getXiaoZuMing(List<Map<String, Object>> list, int xiaoZuID) {
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> kp = list.get(i);
			if (kp.get("ID").equals(xiaoZuID)) {
				return kp.get("xueXiZuMing").toString();
			}
		}
		return null;
	}

	private String getXueShengXingMing(List<Map<String, Object>> listXueSheng, int xueShengID) {
		for (int i = 0; i < listXueSheng.size(); i++) {
			Map<String, Object> xs = listXueSheng.get(i);
			if (xs.get("ID").equals(xueShengID)) {
				return xs.get("xingming").toString();
			}
		}
		return null;
	}

	private Map<String, Object> getXueShengXinXi(List<Map<String, Object>> listXueSheng, int xueShengID) {
		for (int i = 0; i < listXueSheng.size(); i++) {
			Map<String, Object> xs = listXueSheng.get(i);
			if (xs.get("ID").equals(xueShengID)) {
				return xs;
			}
		}
		return null;
	}

	private List<Map<String, Object>> scoredefen(List<Map<String, Object>> list) {
		// 每个小组的总得分
		Map<String, Double> aveMap = new HashMap<String, Double>();
		Integer shuliang = list.size();
		for (int i = 0; i < list.size(); i++) {
			// 计算当前小组得分
			Double defensum = 0d;
			List<Map<String, Object>> beidafen = new ArrayList<>();
			List<Double> beidafen2 = new ArrayList<>();
			if (list.get(i).get("ID") == null || "".equals(list.get(i).get("ID"))) {
				return null;
			}
			for (int j = 0; j < list.size(); j++) {// 其他小组给本组打分
				if (list.get(i).get("ID").toString().equals(list.get(j).get("ID").toString())) {
					continue;
				}

				Map<String, Object> app_KaoPingJieGuo = list.get(j);
				if (app_KaoPingJieGuo == null) {
					list.get(j).put("dafenstatus", "nokaopingjieguo");
					return list;
				}
				if (app_KaoPingJieGuo.get("paiXu") == null || "".equals(app_KaoPingJieGuo.get("paiXu"))) {
					return null;
				}
				String[] paiMing = app_KaoPingJieGuo.get("paiXu").toString().split(";");
				if (paiMing == null || paiMing.length == 0) {
					return null;
				}
				for (int k = 0; k < paiMing.length; k++) {
					Map<String, Object> map2 = new HashMap<>();
					String[] mingci = paiMing[k].split("_");
					if (mingci == null || mingci.length == 0) {
						return null;
					}
					if (mingci[1].equals(list.get(i).get("ID").toString())) {
						if (mingci[2] != null && !"".equals(mingci[2])) {
							map2.put("dafenxiaozuming", app_XueXiZuService
									.selectByPrimaryKey(Integer.parseInt(mingci[0])).getXueXiZuMing());
							map2.put("dafenxiaozuid", mingci[0]);
							map2.put("defen", mingci[2]);
							beidafen2.add(Double.valueOf(mingci[2]));
							defensum += Double.valueOf(mingci[2]);
						} else {
							map2.put("defen", 0d);
							beidafen2.add(0d);
							defensum += Double.valueOf(mingci[2]);
						}
						beidafen.add(map2);
					}
				}

			}
			// 计算总得分，去掉最高分和最低分后的得分
			Collections.sort(beidafen2);
			double tempdefen = defensum - (double) Math.round((beidafen2.get(beidafen2.size() - 1)) * 100) / 100
					- (double) Math.round((beidafen2.get(0)) * 100) / 100;
			if (beidafen2.size() > 0 && beidafen2 != null) {
				list.get(i).put("defenliebiao", beidafen);
				list.get(i).put("zuigaofen", beidafen2.get(beidafen2.size() - 1));
				list.get(i).put("zuidifen", beidafen2.get(0));
				list.get(i).put("zongdefen", (double) Math.round(defensum * 100) / 100);
				list.get(i).put("quMaxAndMin", defensum - (beidafen2.get(beidafen.size() - 1)) - (beidafen2.get(0)));
				Double aveValue = 0.;
				if (beidafen2.size() > 2) {
					aveValue = tempdefen / ((double) Math.round((beidafen2.size() - 2) * 100) / 100);
				} else {
					aveValue = defensum / ((double) Math.round((beidafen2.size() * 100) / 100));
				}
				list.get(i).put("average", aveValue);
				aveMap.put(list.get(i).get("ID").toString(), Double.valueOf(list.get(i).get("average").toString()));
			} else {
				list.get(i).put("defenliebiao", 0d);
				list.get(i).put("zuigaofen", 0d);
				list.get(i).put("zuidifen", 0d);
				list.get(i).put("zongdefen", 0d);
				list.get(i).put("quMaxAndMin", 0d);
				list.get(i).put("average", 0d);
				aveMap.put(list.get(i).get("ID").toString(), 0d);
			}
			// 给其他小组打分
			for (int j = 0; j < list.size(); j++) {
				List<Map<String, Object>> dafen = new ArrayList<>();
				if (list.get(i).get("ID").toString().equals(list.get(j).get("ID").toString())) {
					Map<String, Object> app_KaoPingJieGuo = list.get(j);
					if (app_KaoPingJieGuo == null) {
						list.get(i).put("paixustatus", "nokaopingjieguo");
						return list;
					}
					if (app_KaoPingJieGuo.get("paiXu") == null || "".equals(app_KaoPingJieGuo.get("paiXu"))) {
						return null;
					}
					String[] paiMing = app_KaoPingJieGuo.get("paiXu").toString().split(";");
					if (paiMing == null || paiMing.length == 0) {
						return null;
					}
					for (int k = 0; k < paiMing.length; k++) {
						String[] mingci = paiMing[k].split("_");
						if (mingci == null || mingci.length == 0) {
							return null;
						}
						Map<String, Object> map2 = new HashMap<>();
						if (!mingci[0].equals(list.get(j).get("ID").toString())) {
							continue;
						}
						map2.put("dafenxiaozuid", mingci[0]);
						map2.put("beidafenxiaozuid", mingci[1]);
						map2.put("beidafenxiaozuming", list.get(j).get("xueXiZuMing"));
						if (mingci[2] != null && !"".equals(mingci[2])) {
							map2.put("dafen", mingci[2]);
						} else {
							map2.put("dafen", 0d);
						}
						dafen.add(map2);
						list.get(i).put("dafen", dafen);
					}

				}
			}
		}
		// 分数写入数据库,计算排名
		List<Double> paiMing = new ArrayList<>();
		List<Map<String, Object>> paixuxinxi = new ArrayList<>();
		Double aveTmepNum = 0d;
		Double tempNum = 0d;
		Double sumNum = 0d;
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> kaoPingJieGuo = list.get(i);
			System.out.println(kaoPingJieGuo.get("zongdefen").toString());
			if (Double.parseDouble(kaoPingJieGuo.get("zongdefen").toString()) <= 0) {
				break;
			}
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> dafen = (List<Map<String, Object>>) list.get(i).get("dafen");
			sumNum = 0d;
			if (dafen == null) {
				list.get(i).put("paixustatus", "unsort");
				continue;
			}
			for (Map<String, Object> tempMap : dafen) {
				aveTmepNum = aveMap.get(tempMap.get("beidafenxiaozuid"));
				if (aveTmepNum == null)
					continue;
				tempNum = Double.valueOf(tempMap.get("dafen").toString());
				if (aveTmepNum != tempNum) {
					sumNum = (sumNum + Math.abs((aveTmepNum - tempNum) / 2));
				}
			}
			list.get(i).put("zongkoufen", (double) Math.round((sumNum / (list.size() - 1)) * 100) / 100);
			list.get(i).put("finalscore",
					String.format("%.2f",
							(((Double.parseDouble(list.get(i).get("average").toString())))
									- ((double) Math.round((sumNum / (list.size() - 1)) * 100) / 100))
									/ (Double.valueOf((shuliang - 1)))));
			paiMing.add(Double.valueOf(list.get(i).get("finalscore").toString()));
			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("score", Double.valueOf(list.get(i).get("finalscore").toString()));
			paraMap.put("xiaozu", list.get(i).get("ID"));
			paraMap.put("xiaozuming", list.get(i).get("xueXiZuMing"));
			paraMap.put("zongkoufen", (double) Math.round((sumNum / (list.size() - 1)) * 100) / 100);
			paixuxinxi.add(paraMap);
			App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
					.selectByPrimaryKey(Integer.parseInt(list.get(i).get("kaopingjieguoid").toString()));
			app_KaoPingJieGuo.setZongFen(Double.valueOf(list.get(i).get("zongdefen").toString()));
			app_KaoPingJieGuo.setQuMaxAndMin(Double.valueOf(list.get(i).get("quMaxAndMin").toString().toString()));
			app_KaoPingJieGuo.setPingJunFen(Double.valueOf(list.get(i).get("average").toString().toString()));
			app_KaoPingJieGuo.setZongKouFen(Double.valueOf(list.get(i).get("zongkoufen").toString().toString()));
			app_KaoPingJieGuo.setZuiZhongDeFen(Double.valueOf(list.get(i).get("finalscore").toString().toString()));
			app_KaoPingJieGuoService.update(app_KaoPingJieGuo);
		}
		// 排名
		if (paiMing != null && paiMing.size() != 0) {// 只要有为0.0的说明前面的考评结果有不为空的，所以该大组下的所有小组应该都不为空
			Double[] paiMing2 = new HashSet<>(paiMing).toArray(new Double[0]);
			Arrays.sort(paiMing2);
			for (int m = 0; m < paiMing2.length; m++) {
				for (int k = 0; k < paixuxinxi.size(); k++) {
					if (!paixuxinxi.get(k).get("score").equals(paiMing2[m])) {
						continue;
					}
					App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
							.selectByPrimaryKey(Integer.parseInt(list.get(k).get("kaopingjieguoid").toString()));
					app_KaoPingJieGuo.setMingCi(paiMing2.length - m);
					app_KaoPingJieGuoService.update(app_KaoPingJieGuo);
					for (Map<String, Object> mapss : list) {
						if (!mapss.get("ID").toString().equals(paixuxinxi.get(k).get("xiaozu").toString())) {
							continue;
						}
						mapss.put("mingci", paiMing2.length - m);
						break;
					}
				}
			}

		}
		App_KaoPingJieGuo kaoPingJieGuoss = app_KaoPingJieGuoService
				.selectByPrimaryKey(Integer.parseInt(list.get(0).get("kaopingjieguoid").toString()));
		if (kaoPingJieGuoss.getMingCi() != null && !"".equals(kaoPingJieGuoss.getMingCi().toString())) {
			Collections.sort(list, new Comparator<Map<String, Object>>() {
				@Override
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {

					if (Integer.parseInt(o1.get("mingci").toString()) > Integer.parseInt(o2.get("mingci").toString())) {
						return -1;
					}
					if (Integer.parseInt(o1.get("mingci").toString()) == Integer
							.parseInt(o2.get("mingci").toString())) {
						return 0;
					}
					return 1;
				}
			});
		}
		return list;
	}
}