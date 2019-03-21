package com.web.controller.app.stu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.tools.ant.filters.LineContains.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.xml.internal.xsom.impl.IdentityConstraintImpl;
import com.web.model.App_KaoTiKu;
import com.web.model.App_ShiJianTi;
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
import com.web.service.App_KaoTiKuService;
import com.web.service.App_KeChengKaoPingService;
import com.web.service.App_ShiJianTiService;
import com.web.service.App_XueXiZuService;
import com.web.service.BanJiService;
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

import edu.emory.mathcs.backport.java.util.Arrays;
import net.sf.json.JSONObject;
import sun.tools.tree.NewArrayExpression;

@Controller
public class App_ShiXiController {
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
	private XueShengService xueshengService;
	@Autowired
	private App_FenZuService app_FenZuService;
	@Autowired
	private App_XueXiZuService app_XueXiZuService;

	@ResponseBody
	@RequestMapping(value = "app_getshixi")
	public JSONObject app_getshixi(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String banjiid = request.getParameter("banjiid");
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		List<Map<String, Object>> keChengs2 = new ArrayList<>();
		// 获得该学生的所有课程
		List<Map<String, Object>> keChengs = kechengService.getByBanJiIDAndXueShengID(Integer.parseInt(banjiid),
				xueshengid);
		// 如果课程为空
		if (keChengs == null || keChengs.isEmpty()) {
			jsonObject.put("kechengs", keChengs2);
			return jsonObject;
		}
		// 如果课程不为空
		for (int i = 0; i < keChengs.size(); i++) {
			// 获取某一门课程的实习课
			List<Map<String, Object>> shixi = kechengService
					.selectShiXiByKeChengID((Integer) keChengs.get(i).get("ID"));
			if (shixi != null && shixi.size() != 0) {
				for (int j = 0; j < shixi.size(); j++) {
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
					keChengs2.add(keChengs.get(i));
				}
			}
		}
		// 去重
		quchong(keChengs2);
		jsonObject.put("kechengs", keChengs2);
		return jsonObject;
	}

	// 课程去重
	private void quchong(List<Map<String, Object>> list) {
		HashSet<Map<String, Object>> h = new HashSet<Map<String, Object>>(list);
		list.clear();
		list.addAll(h);
	}

	// 学生
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

	@ResponseBody
	@RequestMapping(value = "app_getkaotis")
	public JSONObject app_getkaotis(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String shixiid = request.getParameter("shixiid");
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		List<Map<String, Object>> kaotiList = app_ShiJianTiService.getShiJianTiByShiXiId(Integer.parseInt(shixiid));
		List<Map<String, Object>> xueshengList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> shenheList = new ArrayList<Map<String, Object>>();
		// 获取发布的考题
		for (int j = 0; j < kaotiList.size(); j++) {
			if (kaotiList.get(j).get("shiFouShenHe").toString().equals("3")
					|| kaotiList.get(j).get("shiFouShenHe").toString().equals("4")) {
				shenheList.add(kaotiList.get(j));
			} else {
				continue;
			}
		}
		if (shenheList.isEmpty()) {
			jsonObject.put("kaotilist", shenheList);
			return jsonObject;
		}
		// 查询选择该考题的学生
		for (Map<String, Object> map : shenheList) {
			String xueshengids = "";
			try {
				// resultType的返回值类型为map时，若果字段值为空，则不返回该属性，包括属性名
				// 用try catch是为了简单处理
				xueshengids = map.get("xueShengID").toString();
				if (xueshengids == null || "".equals(xueshengids)) {
					map.put("xueshengxinxi", xueshengList);
					continue;
				} else {
					xueshengList = getxueshenginfo(xueshengids);
					map.put("xueshengxinxi", xueshengList);
				}
			} catch (Exception e) {
				map.put("xueShengID", "");
				map.put("xueshengxinxi", new ArrayList<Map<String, Object>>());
			}
		}
		jsonObject.put("katilist", shenheList);
		return jsonObject;
	}

	// 查询学生信息
	private List<Map<String, Object>> getxueshenginfo(String xueshengids) {
		List<Map<String, Object>> xueshengMaps = xueshengService
				.selectByXueShengIDs2(Arrays.asList(xueshengids.split(",")));
		return xueshengMaps == null ? new ArrayList<Map<String, Object>>() : xueshengMaps;
	}

	// 选题
	@ResponseBody
	@RequestMapping(value = "app_xuanti")
	public JSONObject app_xuanti(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaotiid = request.getParameter("kaotiid");
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		String xueshengids = "";
		if (app_ShiJianTi == null) {
			jsonObject.put("status", "nokaoti");
			return jsonObject;
		}
		if (app_ShiJianTi.getXueshengid() == null || "".equals(app_ShiJianTi.getXueshengid())) {
			xueshengids = "," + xueshengid + ",";
		} else {
			xueshengids += app_ShiJianTi.getXueshengid().toString() + xueshengid + ",";
		}
		app_ShiJianTi.setXueshengid(xueshengids);
		try {
			int i = app_ShiJianTiService.update(app_ShiJianTi);
			if (i != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		} catch (Exception e) {
			jsonObject.put("status", "fail");
			e.printStackTrace();
		}
		return jsonObject;
	}

	// 查看考题分组信息
	@ResponseBody
	@RequestMapping(value = "app_kaotifenzuxinxi")
	public JSONObject app_kaotifenzuxinxi(HttpServletRequest request, HttpServletResponse response) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String shixiid = request.getParameter("shixiid");
		String laoshiid = request.getParameter("laoshiid");
		JSONObject jsonObject = new JSONObject();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kechengid", shixiid);
		map.put("laoshiid", laoshiid);
		List<Map<String, Object>> list = app_FenZuService.getByKeChengIDAndLaoShiID(map);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("zuZhangID") == null) {
				continue;
			}
			list.get(i).put("dazuzhangming", xueshengService
					.selectByPrimaryKey(Integer.parseInt(list.get(i).get("zuZhangID").toString())).getXingming());
			List<Map<String, Object>> xueshenglist = getxiaozu(list.get(i).get("ID").toString());
			list.get(i).put("xueshengxinxi", xueshenglist);
		}
		jsonObject.put("kaotifenzuxinxi", list);
		return jsonObject;
	}

	private List<Map<String, Object>> getxiaozu(String fenZuId) {
		List<Map<String, Object>> xiaozuList = app_XueXiZuService.selectXiaoZu(Integer.parseInt(fenZuId));
		List<Map<String, Object>> xueshenglist = new ArrayList<Map<String, Object>>();
		String xueshengids = "";
		for (int i = 0; i < xiaozuList.size(); i++) {
			xueshengids += xiaozuList.get(i).get("chengYuanIDLieBiao").toString();
		}
		xueshenglist = xueshengService.selectByXueShengIDs2(Arrays.asList(xueshengids.split(",")));
		return xueshenglist;
	}

}
