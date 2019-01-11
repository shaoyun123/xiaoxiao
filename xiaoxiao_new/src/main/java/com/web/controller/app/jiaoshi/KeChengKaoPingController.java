package com.web.controller.app.jiaoshi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ObjDoubleConsumer;

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
import com.web.model.BanJi;
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

import net.sf.json.JSONObject;

@Controller
public class KeChengKaoPingController {
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
	private YuanXiService yuanxiService;
	@Autowired
	private XueQiService xueqiService;
	@Autowired
	private NianFenService nianfenService;
	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private BanJiService banJiService;

	@ResponseBody
	@RequestMapping(value = "app_getshijiankelist")
	public JSONObject app_getshijiankelist(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			YongHu user = yongHuService.selectYongHuByID(Integer.parseInt(yonghuid));
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
			List<Map<String, Object>> keChengs = kechengService.getKeChengByRenKeLaoShiID(map);
			for (int i = 0; i < keChengs.size(); i++) {
				List<Map<String, Object>> shiJianKeCheng = kechengService
						.selectShiJianKe((Integer) keChengs.get(i).get("ID"));
				if (shiJianKeCheng != null && shiJianKeCheng.size() != 0) {
					for (int j = 0; j < shiJianKeCheng.size(); j++) {
						keChengs.get(i).put("shijiankechengid", (Integer) shiJianKeCheng.get(j).get("ID"));
						JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey((Integer) keChengs.get(i).get("jiaoShiID"));
						JiaoXueLou jiaoxuelou = jiaoXueLouService.selectByPrimaryKey(jiaoShi.getJiaoxuelouid());
						XiaoQu xiaoQu = xiaoquService.selectByPrimaryKey(jiaoxuelou.getXiaoQuId());
						if (shiJianKeCheng.get(0).get("xiaoZuRongLiang") != null) {
							String xiaozurongliang = shiJianKeCheng.get(0).get("xiaoZuRongLiang").toString()
									.split("_")[1];
							String fenxiaozu = shiJianKeCheng.get(0).get("xiaoZuRongLiang").toString().split("_")[0];
							String fendazu = shiJianKeCheng.get(0).get("xiaoZuRongLiang").toString().split("_")[2];
							if (fendazu.equals("0")) {
								String dazuzhangid = shiJianKeCheng.get(0).get("xiaoZuRongLiang").toString()
										.split("_")[3];
								keChengs.get(i).put("dazuzhangid", dazuzhangid);
								keChengs.get(i).put("dazuzhangxingming", xueShengService
										.selectByPrimaryKey(Integer.parseInt(dazuzhangid)).getXingming());
							}
							keChengs.get(i).put("xiaozurongliang", xiaozurongliang);
							keChengs.get(i).put("fenxiaozu", fenxiaozu);
							keChengs.get(i).put("fendazu", fendazu);
						}
						keChengs.get(i).put("jiaoxuelouming", jiaoxuelou.getJiaoXueLouMing());
						keChengs.get(i).put("jiaoshiming", jiaoShi.getJiaoshiming());
						keChengs.get(i).put("xiaoquming", xiaoQu.getMingcheng());
						keChengs.get(i).put("xiaozurongliang", shiJianKeCheng.get(0).get("xiaoZuRongLiang"));
						keChengs2.add(keChengs.get(i));
					}
				}
			}
			jsonObject.put("kechengs", keChengs2);
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_getdazuxinxi")
	public JSONObject app_getdazuxinxi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kechengid = request.getParameter("kechengid");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (str.equals(token)) {
			List<Map<String, Object>> daZuList = app_FenZuService.getFenZu(Integer.parseInt(kechengid));
			for (int i = 0; i < daZuList.size(); i++) {
				XueSheng xueSheng = xueShengService
						.selectByPrimaryKey(Integer.parseInt(daZuList.get(i).get("zuzhangid").toString()));
				daZuList.get(i).put("dazuzhang", xueSheng.getXingming());
			}
			jsonObject.put("dazuxinxi", daZuList);
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_xiaozuixinxi")
	public JSONObject app_xiaozuixinxi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String dazuid = request.getParameter("dazuid");
		String string = yonghuid + "," + status;
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

	private Map<String, Object> getBenZuXinXi(String keID, String kaoPingID, String dazuid, String fendazu,
			String fenxiaozu, String chouqianid, String dazuzhangid) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> xiaozus = new ArrayList<>();
		if (fendazu.equals("0")) {
			xiaozus = app_XueXiZuService.getXueXiZuByKeCheng2(Integer.parseInt(keID));
			map.put("dazuming", "");
			XueSheng xueSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(dazuzhangid));
			if (xueSheng == null) {
				return null;
			}
			map.put("dazuzhang", xueSheng.getXingming());
		} else {
			App_FenZu dazu = app_FenZuService.selectByPrimaryKey(Integer.parseInt(dazuid.toString()));
			xiaozus = app_XueXiZuService.getXueXZ(Integer.parseInt(dazuid.toString()));
			map.put("dazuming", dazu.getZuMingCheng());
			map.put("dazuzhang", xueShengService.selectByPrimaryKey(dazu.getZuZhangId()).getXingming());
		}
		for (int j = 0; j < xiaozus.size(); j++) {
			List<Object> members = new ArrayList<>();
			String[] item = xiaozus.get(j).get("chengYuanIDLieBiao").toString().substring(1).split(",");
			for (int m = 0; m < item.length; m++) {
				if (item[m].length() == 0) {
					continue;
				}
				Map<String, Object> mapss = new HashMap<>();
				XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(item[m]));
				mapss.put("xingming", xs.getXingming());
				mapss.put("xeushengid", xs.getXueshengid());
				members.add(mapss);
				if (xs.getXueshengid().toString().equals(xiaozus.get(j).get("xueShengID").toString())) {
					xiaozus.get(j).put("xiaozuzhang", xs.getXingming());
				} else {
					continue;
				}
			}
			xiaozus.get(j).put("chengyuan", members);
			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("kaopingid", kaoPingID);
			paraMap.put("dazuid", "");
			App_KaoPingChouQian chouqian = app_KaoPingChouQianService.selectByPrimaryKey(Integer.parseInt(chouqianid));
			if (chouqian == null) {
				// 还没有抽签的信息
				xiaozus.get(j).put("qiandaostatus", 0);
				return null;
			} else {
				if (chouqian.getQianDao() != null) {
					if (chouqian.getQianDao().indexOf("," + xiaozus.get(j).get("ID") + ",") >= 0) {
						xiaozus.get(j).put("qiandaostatus", 1);
					} else {
						xiaozus.get(j).put("qiandaostatus", 0);
					}
				} else {
					xiaozus.get(j).put("qiandaostatus", 0);
				}
			}
			map.put("chouqianxinxi", chouqian);
			map.put("baohanxiaozu", xiaozus);
		}
		return map;
	}

//	@ResponseBody
//	@RequestMapping(value = "app_getqiandao")
//	public JSONObject app_getqiandao(HttpServletRequest request, HttpServletResponse response) {
//		String yonghuid = request.getParameter("yonghuid");
//		String token = request.getParameter("token");
//		String status = request.getParameter("status");
//		String kaopingid = request.getParameter("kaopingid");
//		String kechengid = request.getParameter("kechengid");
//		String dazuid = request.getParameter("dazuid");
//		String fendazu = request.getParameter("fendazu");
//		String fenxiaozu = request.getParameter("fenxiaozu");
//		String dazuzhangid = request.getParameter("dazuzhangid");
//		String chouqianid = request.getParameter("chouqianid");
//		JSONObject jsonObject = new JSONObject();
//		String string = yonghuid + "," + status;
//		String str = Util.SHA1Encode(string);
//		if (str.equals(token)) {
//			App_KeChengKaoPing keChengKaoPing = app_KeChengKaoPingService
//					.selectByPrimaryKey(Integer.parseInt(kaopingid));
//			Map<String, Object> map = null;
//			map = getBenZuXinXi(kechengid, kaopingid, dazuid, fendazu, fenxiaozu, chouqianid, dazuzhangid);
//			jsonObject.put("qiandaoxinxi", map);
//		}
//		return jsonObject;
//	}

	@ResponseBody
	@RequestMapping(value = "app_undodafen")
	public JSONObject app_undodafen(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaopingid = request.getParameter("kaopingid");
		String xiaozuid = request.getParameter("xiaozuid");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
					.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), Integer.parseInt(xiaozuid));
			App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuid));
			if (app_XueXiZu == null) {
				return jsonObject;
			}
			App_KaoPingChouQian app_KaoPingChouQian = app_KaoPingChouQianService
					.selectByPrimaryKey1(Integer.parseInt(kaopingid), app_XueXiZu.getFenZuId());
			if (app_KaoPingChouQian == null) {
				return jsonObject;
			}
			if (app_KaoPingChouQian.getZhuangTai() != 3) {
				jsonObject.put("status", "limited");
				return jsonObject;
			}
			app_XueXiZu.setZhuangTai(3);
			app_XueXiZuService.update(app_XueXiZu);

			app_KaoPingJieGuo.setPaiXu("");
			int n = app_KaoPingJieGuoService.update(app_KaoPingJieGuo);
			if (n != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("fail", "fail");
			}
		}
		return jsonObject;
	}

	public List<Map<String, Object>> getdefen(List<Map<String, Object>> list, String kaopingid, Object xiaozuid) {
		List<Map<String, Object>> beidafens = new ArrayList<>();
		if (xiaozuid == null || "".equals(xiaozuid)) {
			return beidafens;
		}
		for (int j = 0; j < list.size(); j++) {// 其他小组给本组打分
			if (!xiaozuid.toString().equals(list.get(j).get("ID").toString())) {
				App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService.selectJieGuoByXiaoZuId(
						Integer.parseInt(kaopingid), Integer.parseInt(list.get(j).get("ID").toString()));
				if (app_KaoPingJieGuo != null) {
					if (app_KaoPingJieGuo.getPaiXu() == null || "".equals(app_KaoPingJieGuo.getPaiXu())) {
						return beidafens;
					}
					String[] paiMing = app_KaoPingJieGuo.getPaiXu().split(";");
					if (paiMing == null || paiMing.length == 0) {
						return beidafens;
					}
					for (int k = 0; k < paiMing.length; k++) {
						Map<String, Object> map2 = new HashMap<>();
						String[] mingci = paiMing[k].split("_");
						if (mingci == null || mingci.length == 0) {
							return beidafens;
						}
						if (mingci[1].equals(xiaozuid.toString())) {
							if (mingci[2] != null && !"".equals(mingci[2])) {
								map2.put("dafenxiaozuming", app_XueXiZuService
										.selectByPrimaryKey(Integer.parseInt(mingci[0])).getXueXiZuMing());
								map2.put("dafenxiaozuid", mingci[0]);
								map2.put("defen", mingci[2]);
							} else {
								map2.put("defen", 0d);
							}
							beidafens.add(map2);
						} else {
							continue;
						}

					}

				} else {
					list.get(j).put("dafenstatus", "nokaopingjieguo");
				}
			}
		}

		return beidafens;
	}

	@ResponseBody
	@RequestMapping(value = "getDangQianAndNextZu")
	public JSONObject getDangQianAndNextZu(HttpServletRequest request, HttpServletResponse response) {
		String kechengid = request.getParameter("kechengid");
		String kaopingid = request.getParameter("kaopingid");
		String dazuid = request.getParameter("dazuid");
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			// Boolean isKaiShi = false;
			Map<String, Object> map = new HashMap<>();
			App_KaoPingChouQian app_KaoPingChouQian = app_KaoPingChouQianService
					.selectByPrimaryKey1(Integer.parseInt(kaopingid), Integer.parseInt(dazuid));
			App_FenZu app_FenZu = app_FenZuService.selectByPrimaryKey(Integer.parseInt(dazuid.toString()));
			if (app_KaoPingChouQian.getXiaozupaixu() != null && app_KaoPingChouQian.getXiaozupaixu().length() > 0) {
				List<App_XueXiZu> xueXiZus2 = new ArrayList<>();
				String[] paixuxiaozus = app_KaoPingChouQian.getXiaozupaixu().split(",");
				for (int j = 0; j < paixuxiaozus.length; j++) {
					App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(paixuxiaozus[j]));
					if (app_XueXiZu != null) {
						xueXiZus2.add(app_XueXiZu);
					} else {
						continue;
					}
				}
				map.put("yipaixuxuexizu", xueXiZus2);
				map.put("paixustatus", "sort");
			} else {
				map.put("paixustatus", "unsort");
				return jsonObject;
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
				if (app_KaoPingChouQian.getDangQianZuId() != null
						&& !"".equals(app_KaoPingChouQian.getDangQianZuId().toString())) {
					app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(app_KaoPingChouQian.getDangQianZuId());
				} else {
					return jsonObject;
				}
				if (app_XueXiZu != null) {
					Map<String, Object> dangQianMap = new HashMap<>();
					App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
							.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), app_XueXiZu.getId());
					if (app_XueXiZu.getChengYuanLieBiao() != null) {
						String[] aMembers = app_XueXiZu.getChengYuanLieBiao().substring(1).split(",");
						List<Object> list2 = new ArrayList<>();
						if (aMembers != null && aMembers.length > 0) {
							for (int j = 0; j < aMembers.length; j++) {
								Map<String, Object> map2 = new HashMap<>();
								if (aMembers[j] != null) {
									XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(aMembers[j]));
									if (xs != null) {
										map2.put("xingming", xs.getXingming());
										map2.put("xueshengid", xs.getXueshengid());
									} else {
										continue;
									}
									list2.add(map2);
								} else {
									continue;
								}
							}
							dangQianMap.put("chengyuan", list2);
							dangQianMap.put("dangqianzuming", app_XueXiZu.getXueXiZuMing());
							dangQianMap.put("dangqianyanjiangzuid", app_XueXiZu.getId());
							if (app_KaoPingJieGuo != null) {
								Map<String, Object> map2 = new HashMap<>();
								Integer fayanrenid = app_KaoPingJieGuo.getFaYanRenId();
								XueSheng xSheng = xueShengService.selectByPrimaryKey(fayanrenid);
								map2.put("fayanrenid", xSheng.getXueshengid());
								map2.put("fayanrenxingming", xSheng.getXingming());
								dangQianMap.put("fayanren", map2);
							} else {
								map.put("status", "nokaopingjieguo");// 可以新插入一条数据
							}
						}
					}
					yanjiangmap.put("dangqianyanjiang", dangQianMap);
				}
				if (app_KaoPingChouQian.getChouQian() != null && !"".equals(app_KaoPingChouQian.getChouQian())) {
					fanyanrenid = app_KaoPingChouQian.getChouQian();
					Map<String, Object> parammap = new HashMap<>();
					String xueshengid2 = "%," + fanyanrenid + ",%";
					parammap.put("kaopingid", kaopingid);
					parammap.put("xueshengid2", xueshengid2);
					parammap.put("kechengid", kechengid);
					List<Map<String, Object>> app_XueXiZu3 = app_XueXiZuService
							.selectBykaoPingIdAndXueshengId(parammap);
					if (app_XueXiZu3 != null && app_XueXiZu3.size() > 0) {
						Map<String, Object> xiaYiMap = new HashMap<>();
						App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService.selectJieGuoByXiaoZuId(
								Integer.parseInt(kaopingid),
								Integer.parseInt(app_XueXiZu3.get(0).get("xiaozuid").toString()));
						if (app_XueXiZu3.get(0).get("chengYuanIDLieBiao").toString() != null) {
							String[] aMembers = app_XueXiZu3.get(0).get("chengYuanIDLieBiao").toString().substring(1)
									.split(",");
							List<Object> list2 = new ArrayList<>();
							if (aMembers != null && aMembers.length > 0) {
								for (int j = 0; j < aMembers.length; j++) {
									Map<String, Object> map2 = new HashMap<>();
									if (aMembers[j] != null) {
										XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(aMembers[j]));
										if (xs != null) {
											map2.put("xingming", xs.getXingming());
											map2.put("xueshengid", xs.getXueshengid());
										} else {
											continue;
										}
										list2.add(map2);
									} else {
										continue;
									}
								}
								xiaYiMap.put("chengyuan", list2);
								xiaYiMap.put("xiayizuming", app_XueXiZu3.get(0).get("xiaozuming").toString());
								xiaYiMap.put("xiayiyanjiangzuid", app_XueXiZu3.get(0).get("xiaozuid").toString());
								if (app_KaoPingJieGuo != null) {
									Map<String, Object> map2 = new HashMap<>();
									Integer fayanrenid = app_KaoPingJieGuo.getFaYanRenId();
									XueSheng xSheng = xueShengService.selectByPrimaryKey(fayanrenid);
									map2.put("fayanrenid", xSheng.getXueshengid());
									map2.put("fayanrenxingming", xSheng.getXingming());
									xiaYiMap.put("fayanren", map2);
								} else {
									map.put("status", "nokaopingjieguo");// 可以新插入一条数据
								}
							}
						}
						yanjiangmap.put("xiayiyanjiang", xiaYiMap);
					}
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
						App_KaoPingJieGuo app_KaoPingJieGuo = app_KaoPingJieGuoService
								.selectJieGuoByXiaoZuId(Integer.parseInt(kaopingid), Integer.parseInt(xiayizuid));
						if (app_XueXiZu3.getChengYuanLieBiao() != null) {
							String[] aMembers = app_XueXiZu3.getChengYuanLieBiao().substring(1).split(",");
							List<Object> list2 = new ArrayList<>();
							if (aMembers != null && aMembers.length > 0) {
								for (int j = 0; j < aMembers.length; j++) {
									Map<String, Object> map2 = new HashMap<>();
									if (aMembers[j] != null) {
										XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(aMembers[j]));
										if (xs != null) {
											map2.put("xingming", xs.getXingming());
											map2.put("xueshengid", xs.getXueshengid());
										} else {
											continue;
										}
										list2.add(map2);
									} else {
										continue;
									}
								}
								xiaYiMap.put("chengyuan", list2);
								xiaYiMap.put("xiayizuming", app_XueXiZu3.getXueXiZuMing());
								xiaYiMap.put("xiayiyanjiangzuid", app_XueXiZu3.getId());
								if (app_KaoPingJieGuo != null) {
									Map<String, Object> map2 = new HashMap<>();
									Integer fayanrenid = app_KaoPingJieGuo.getFaYanRenId();
									XueSheng xSheng = xueShengService.selectByPrimaryKey(fayanrenid);
									map2.put("fayanrenid", xSheng.getXueshengid());
									map2.put("fayanrenxingming", xSheng.getXingming());
									xiaYiMap.put("fayanren", map2);
								} else {
									Map<String, Object> map2 = new HashMap<>();
									map.put("status", "nokaopingjieguo");// 可以新插入一条数据
									map2.put("fayanrenid", "");
									map2.put("fayanrenxingming", "");
									xiaYiMap.put("fayanren", map2);
								}
							}
						}
					}
					yanjiangmap.put("xiayiyanjiang", xiaYiMap);
				}
				map.put("yanjiang", yanjiangmap);
			}
			map.put("chouqian", app_KaoPingChouQian);
			map.put("dazuzhangid", app_FenZu.getZuZhangId());
			jsonObject.put("dangqian", map);
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_inforcestart")
	public JSONObject app_inforcestart(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaopingid = request.getParameter("kaopingid");
		String kechengid = request.getParameter("shijiankechengid");
		App_KeChengKaoPing app_KeChengKaoPing = app_KeChengKaoPingService
				.selectByPrimaryKey(Integer.parseInt(kaopingid));
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {

			List<Map<String, Object>> kaoPingList = app_KeChengKaoPingService.getKaoPing2(Integer.parseInt(kechengid));
			for (int i = 0; i < kaoPingList.size(); i++) {
				if (kaoPingList.get(i).get("zhuangTai").toString().equals("1")) {
					jsonObject.put("status", "started");
					return jsonObject;
				}
			}
			if (app_KeChengKaoPing.getZhuangTai() != 0) {// kaopingid传错
				jsonObject.put("status", "fail");
				return jsonObject;

			}
			app_KeChengKaoPing.setZhuangTai(1);
			int k = app_KeChengKaoPingService.update(app_KeChengKaoPing);
			if (k != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_getXinXi")
	public JSONObject app_getXinXi(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaopingid = request.getParameter("kaopingid");
		String kechengid = request.getParameter("kechengid");
		String dazuid = request.getParameter("dazuid");
		String fendazu = request.getParameter("fendazu");
		String fenxiaozu = request.getParameter("fenxiaozu");
		String dazuzhangid = request.getParameter("dazuzhangid");
		// App_KeChengKaoPing app_KeChengKaoPing = app_KeChengKaoPingService
		// .selectByPrimaryKey(Integer.parseInt(kaopingid));
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			Map<String, Object> shiJianKeCheng = kechengService
					.getshijianke(Integer.parseInt(kechengid));
			if(shiJianKeCheng==null) {
				return null;
			}
			if (shiJianKeCheng.get("xiaoZuRongLiang") != null) {
				dazuzhangid = shiJianKeCheng.get("xiaoZuRongLiang").toString().split("_")[3];
			}
			Map<String, Object> chouqian = new HashMap<>();
			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("kaopingid", kaopingid);
			if (fendazu.equals("0")) {
				paraMap.put("dazuid", dazuid);
				chouqian = app_KaoPingChouQianService.selectchouqian(paraMap);
			}
			if (fendazu.equals("1")) {
				paraMap.put("dazuid", dazuid);
				chouqian = app_KaoPingChouQianService.selectchouqian(paraMap);
			}
			if (chouqian == null) {
				// 还没有抽签的信息
				chouqian = new HashMap<>();
				App_KaoPingChouQian app_KaoPingChouQian = new App_KaoPingChouQian();
				app_KaoPingChouQian.setKaoPingId(Integer.parseInt(kaopingid));
				app_KaoPingChouQian.setZhuangTai(1);
				int n = app_KaoPingChouQianService.insert(app_KaoPingChouQian);
				chouqian.put("ID", app_KaoPingChouQian.getId());
				chouqian.put("kaoPingID", kaopingid);
				chouqian.put("zhuangTai", 1);
				if (fendazu.equals("0")) {
					chouqian.put("daZuXueHao", "");
				} else {
					chouqian.put("daZuXueHao", dazuid);
				}
			}
			Map<String, Object> map = null;
			switch (chouqian.get("zhuangTai").toString()) {
			case "1":
				// 准备签到，显示本组的详细信息,开始
				map = getBenZuXinXi(kechengid, kaopingid, dazuid, fendazu, fenxiaozu, chouqian.get("ID").toString(),
						dazuzhangid);
				break;
			case "2":
				// 处于简报过程中，显示当前正在答辩的小组信息和下一个小组的信息
				map = getBenZuXinXi(kechengid, kaopingid, dazuid, fendazu, fenxiaozu, chouqian.get("ID").toString(),
						dazuzhangid);
				break;
			case "3":
				// 显示本组打分排序的情况，小组长提交后可以看到本组排序的结果
				map = getBenZuXinXi(kechengid, kaopingid, dazuid, fendazu, fenxiaozu, chouqian.get("ID").toString(),
						dazuzhangid);
				break;
			case "4":
				// 显示本组打分排序的情况，小组长提交后可以看到本组排序的结果
				map = getBenZuXinXi(kechengid, kaopingid, dazuid, fendazu, fenxiaozu, chouqian.get("ID").toString(),
						dazuzhangid);
				break;
			default:
				break;
			}
			jsonObject.put("qiandaoxinxi", map);
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_getkaopinglist")
	public JSONObject app_getkaopinglist(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String shijiankechengid = request.getParameter("shijiankechengid");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			// 登录信息失效
			return jsonObject;
		}
		List<Map<String, Object>> kaoPingList = app_KeChengKaoPingService
				.getKaoPing2(Integer.parseInt(shijiankechengid));
		if (kaoPingList == null || kaoPingList.size() == 0) {
			// 没有查到课程的考评信息
			return jsonObject;
		}
		List<Map<String, Object>> daZuList = app_FenZuService.getDaZu(Integer.parseInt(shijiankechengid));

		jsonObject.put("kaoPing", kaoPingList);
		jsonObject.put("dazu", daZuList);
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_finish")
	public JSONObject app_finish(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaopingid = request.getParameter("kaopingid");
		App_KeChengKaoPing app_KeChengKaoPing = app_KeChengKaoPingService
				.selectByPrimaryKey(Integer.parseInt(kaopingid));
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			List<Map<String, Object>> daZuList = app_FenZuService.getFenZu(app_KeChengKaoPing.getShiJianKeChengId());
			for (int i = 0; i < daZuList.size(); i++) {
				List<App_XueXiZu> xiaozus = app_XueXiZuService
						.getXueXiZu(Integer.parseInt(daZuList.get(i).get("fenzuid").toString()));
				for (int k = 0; k < xiaozus.size(); k++) {
					xiaozus.get(k).setZhuangTai(1);
				}
				daZuList.get(i).put("baohanxiaozu", xiaozus);
				XueSheng xueSheng = xueShengService
						.selectByPrimaryKey(Integer.parseInt(daZuList.get(i).get("zuzhangid").toString()));
				daZuList.get(i).put("dazuzhang", xueSheng.getXingming());
			}
			jsonObject.put("dazuxinxi", daZuList);
		}
		return jsonObject;
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

	@ResponseBody
	@RequestMapping(value = "app_xiaozuzhuangtai")
	public JSONObject app_xiaozuzhuangtai(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String shijiankechengid = request.getParameter("shijiankechengid");
		String fendazu = request.getParameter("fendazu");
		String fenxiaozu = request.getParameter("fenxiaozu");
		String dazuzhangid = request.getParameter("dazuzhangid");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (str.equals(token)) {
			List<Map<String, Object>> dazuList = new ArrayList<>();
			List<Map<String, Object>> xueXiZus = app_XueXiZuService
					.getXueXiZuByKeCheng2(Integer.parseInt(shijiankechengid));
			if (fendazu.equals("0")) {
				jsonObject.put("xiaozulist", xueXiZus);
				List<Map<String, Object>> unfenzuxuesheng = unfenzu(shijiankechengid);
				jsonObject.put("unfenzuxuesheng", unfenzuxuesheng);
				return jsonObject;
			}
			dazuList = app_FenZuService.getDaZu(Integer.parseInt(shijiankechengid));
			if (dazuList.size() != 0 && dazuList != null) {
				for (int i = 0; i < dazuList.size(); i++) {
					XueSheng xueSheng = xueShengService
							.selectByPrimaryKey(Integer.parseInt(dazuList.get(i).get("zuZhangID").toString()));
					if (xueSheng != null) {
						dazuList.get(i).put("dazuzhang", xueSheng.getXingming());
					} else {
						dazuList.get(i).put("dazuzhang", "");
					}
				}
			}
			List<Map<String, Object>> unfenzuxuesheng = unfenzu(shijiankechengid);
			jsonObject.put("xiaozulist", xueXiZus);
			jsonObject.put("unfenzuxuesheng", unfenzuxuesheng);
			jsonObject.put("dazuList", dazuList);
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_choosedazuzhang")
	public JSONObject app_choosedazuzhang(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String dazuid = request.getParameter("dazuid");
		String fendazu = request.getParameter("fendazu");
		String fenxiaozu = request.getParameter("fenxiaozu");
		String kechengid = request.getParameter("shijiankechengid");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		if (str.equals(token)) {
			List<App_XueXiZu> xueXiZus = new ArrayList<>();
			if (fendazu.equals("0")) {
				xueXiZus = app_XueXiZuService.getXueXiZuByKeCheng(Integer.parseInt(kechengid));
			} else {
				xueXiZus = app_XueXiZuService.getXueXiZu(Integer.parseInt(dazuid));
			}
			List<Map<String, Object>> xueShengs = new ArrayList<>();
			for (App_XueXiZu app_XueXiZu : xueXiZus) {
				String chengyuanids = app_XueXiZu.getChengYuanLieBiao().substring(1);
				if (chengyuanids != null && !"".equals(chengyuanids)) {
					for (String xueshengidss : chengyuanids.split(",")) {
						XueSheng xueSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(xueshengidss));
						if (xueSheng != null) {
							Map<String, Object> xshengMap = new HashMap<>();
							xshengMap.put("xingming", xueSheng.getXingming());
							xshengMap.put("xuehao", xueSheng.getXuehao());
							xshengMap.put("xueshengid", xueSheng.getXueshengid());
							xueShengs.add(xshengMap);
						} else {
							continue;
						}
					}
				}
			}
			jsonObject.put("bendazuxuesheng", xueShengs);
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_updatedazu")
	public JSONObject app_updatedazu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String dazuid = request.getParameter("dazuid");
		String rongliang = request.getParameter("rongliang");
		String dazuzhangid = request.getParameter("dazuzhangid");
		String fendazu = request.getParameter("fendazu");
		String fenxiaozu = request.getParameter("fenxiaozu");
		String shijiankechengid = request.getParameter("shijiankechengid");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		JSONObject jsonObject = new JSONObject();
		App_FenZu app_FenZu = new App_FenZu();
		if (str.equals(token)) {
			int i = 0;
			if (fendazu.equals("0")) {
				Map<String, Object> paraMap = new HashMap<>();
				Map<String, Object> shijianMap = kechengService.getshijianke(Integer.parseInt(shijiankechengid));
				String xiaozurongliang = shijianMap.get("xiaoZuRongLiang").toString().trim();
				xiaozurongliang = xiaozurongliang.substring(0, xiaozurongliang.lastIndexOf("_") + 1) 
						+ dazuzhangid;
				System.out.println( xiaozurongliang.lastIndexOf(fendazu));
				paraMap.put("xiaozurongliang", xiaozurongliang);
				paraMap.put("id", shijiankechengid);
				i = kechengService.updateshijianke(paraMap);
			} else {
				app_FenZu = app_FenZuService.selectByPrimaryKey(Integer.parseInt(dazuid));
				app_FenZu.setRongLiang(Integer.parseInt(rongliang));
				app_FenZu.setZuZhangId(Integer.parseInt(dazuzhangid));
				i = app_FenZuService.update(app_FenZu);
			}
			if (i != 0) {
				jsonObject.put("status", "success");

			} else {
				jsonObject.put("status", "fail");
			}
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_delmember") // 踢掉某个小组成员
	public JSONObject app_delmember(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		JSONObject jsonObject = new JSONObject();
		String kickid = request.getParameter("xueshengid");
		String xiaozuid = request.getParameter("xiaozuid");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
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
			String kickids = "," + kickid + ",";
			if (kickids.contains("," + app_XueXiZu.getXiaoZuZhangId().toString() + ",")) {
				// app_XueXiZuService.delete(Integer.parseInt(xiaozuid));
				jsonObject.put("status", "isxiaozuzhang");
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

	@ResponseBody
	@RequestMapping(value = "app_addmember")
	public JSONObject app_addmember(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		JSONObject jsonObject = new JSONObject();
		String xiaozuid = request.getParameter("xiaozuid");
		String xueshengid = request.getParameter("xueshengid");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		App_XueXiZu app_XueXiZu = app_XueXiZuService.selectByPrimaryKey(Integer.parseInt(xiaozuid));
		if (str.equals(token)) {
			if (app_XueXiZu == null) {
				jsonObject.put("status", "noxuexizu");
				return jsonObject;
			}
			String chengYuan = "";
			if (app_XueXiZu.getChengYuanLieBiao() != null && !"".equals(app_XueXiZu.getChengYuanLieBiao())) {
				if (app_XueXiZu.getChengYuanLieBiao().contains("," + xueshengid.toString() + ",")) {
					// app_XueXiZuService.delete(Integer.parseInt(xiaozuid));
					jsonObject.put("status", "existed");
					return jsonObject;
				}
				chengYuan += app_XueXiZu.getChengYuanLieBiao() + xueshengid + ",";
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

		}
		return jsonObject;
	}

}
