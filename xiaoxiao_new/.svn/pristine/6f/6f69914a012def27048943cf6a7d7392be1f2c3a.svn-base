package com.web.controller.app.stu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import java.io.IOException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.web.model.BanJi;
import com.web.model.DeYuPingFenFangAn;
import com.web.model.PingFenFangAn;
import com.web.model.XueQi;
import com.web.model.XueQiDeYu;
import com.web.model.XueSheng;
import com.web.model.XueShengDeYu;
import com.web.service.BanJiService;
import com.web.service.DeYuService;
import com.web.service.NianFenService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
public class AppDeYuController {
	@Autowired
	private DeYuService deYuService;

	@Autowired
	private XueQiService xueqiService;

	@Autowired
	private XueShengService xueShengService;

	@Autowired
	private BanJiService banJiService;
	@Autowired
	private NianFenService nianfenService;

	// 列表展示 此学生的 所有德育成绩
	@RequestMapping("app_listdeyuchengji")
	@ResponseBody
	public List<XueQi> app_getAllDeYu(HttpServletRequest request) {
		List<XueQi> allDeYuList = new ArrayList<>();
		Map<String, String> map2 = new HashMap<>();

		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String leixing = request.getParameter("leixing");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(data[0]));
			String xuexiaoid = banJiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			List<XueQiDeYu> xueQiDeYuList = deYuService.selectXueQiDeYuByBanJiID(data[2] + ",%", "%," + data[2] + ",%");

			if (xueQiDeYuList.size() > 0) {
				for (XueQiDeYu xueQiDeYu : xueQiDeYuList) {
					PingFenFangAn pingFenFangAn = deYuService.getFangAnMingChengByFangAnID(xueQiDeYu.getFanganid());
					XueQi xueQi = xueqiService.getByXueXiaoIDAndXueQiID(Integer.parseInt(xuexiaoid),
							xueQiDeYu.getXueqiid());
					if (xueQi != null) {
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						Date date = new Date();
						map2.put("riqi", format.format(date));
						map2.put("xueXiaoID", xuexiaoid);
						XueQi xueQis = xueqiService.getByxueXiaoIDandriQi(map2);
						if (xueQi.getXueqiid().toString().equals(xueQis.getXueqiid().toString())) {
							xueQi.setMingcheng("0");
						}
						int xn = nianfenService.selectByPrimaryKey(xueQi.getNianfenid()).getRuxuenianfen();
						xueQi.setXuenian(xn + "~" + (xn + 1));
						xueQi.setFanganmingcheng(pingFenFangAn.getFanganmingcheng());
						xueQi.setZhuangtai(xueQiDeYu.getZhuangtai());
						map2.put("xueShengID", data[0]);
						map2.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid() + "");
						XueShengDeYu xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map2);
						if (xueShengDeYu != null) {
							xueQi.setDeyufenid(xueShengDeYu.getDeyufenid());
							xueQi.setManfen(xueShengDeYu.getDeyufen());
							xueQi.setLeixing(xueShengDeYu.getLeixing());

						} else {
							xueQi.setDeyufenid(0);
						}
					} else {
						xueQi = new XueQi();
						xueQi.setFanganmingcheng(pingFenFangAn.getFanganmingcheng());
						xueQi.setDeyufenid(-1);
					}
					if ("2".equals(leixing)) {
						allDeYuList.add(xueQi);
					} else if ("0".equals(leixing)) {
						if (null != xueQi.getLeixing() && xueQi.getLeixing().toString().equals("0")) {
							allDeYuList.add(xueQi);
						}
					} else if ("1".equals(leixing)) {
						if (null != xueQi.getLeixing() && xueQi.getLeixing().toString().equals("1")) {
							allDeYuList.add(xueQi);
						}
					} else if ("3".equals(leixing)) {
						if (String.valueOf(xueQi.getDeyufenid()).equals("0")) {
							allDeYuList.add(xueQi);
						}
					}else if("4".equals(leixing)){
//						Date date = new Date();
//						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//						Map<String, String> map = new HashMap<>();
//						map.put("riqi", format.format(date));
//						map.put("xueXiaoID", xuexiaoid);
//						XueQi xueQi2 = xueqiService.getByxueXiaoIDandriQi(map);
//						XueQiDeYu xueQiDeYu2 = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi2.getXueqiid(),
//								xs.getBanjiid() + ",%", "%," + xs.getBanjiid() + ",%");
						XueQiDeYu xueQiDeYu2 = null;
						List<XueQi> xueQis = xueqiService.findByXueXiaoID(xuexiaoid);
						for (XueQi xueQi2 : xueQis) {
							XueQiDeYu deyu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi2.getXueqiid(),
									xs.getBanjiid() + ",%", "%," + xs.getBanjiid() + ",%");
							if(null != deyu){
								if(!deyu.getZhuangtai().toString().equals("2")){
									xueQiDeYu2 = deyu;
								}
							}
						}
						if(null == xueQiDeYu2){
							
						}else{
							if(xueQiDeYu2.getXueqideyuid().toString().equals(xueQiDeYu.getXueqideyuid().toString())){
								if(xueQiDeYu.getZhuangtai().toString().equals("3")){
									allDeYuList.add(xueQi);
								}
							}
						}
					}else if("5".equals(leixing)){
						if (null != xueQi.getLeixing() && xueQi.getLeixing().toString().equals("3")) {
							allDeYuList.add(xueQi);
						}
					}
				}
				return allDeYuList;
			}
			return allDeYuList;
		}

		return null;
	}

	// 获取德育分项目的各个项目名称
	// 获取本班的所有德育分，离本学期最近的德育成绩
	@RequestMapping(value = "app_DeYufengongshi")
	@ResponseBody
	public List<JSONObject> app_DeYufengongshi(HttpServletRequest request) {
		List<String> deyuitem = new ArrayList<String>();
		String code = request.getParameter("CODE");
		Map<String, String> map = new HashMap<String, String>();
		List<JSONObject> jsons = new ArrayList<>();
		List<DeYuPingFenFangAn> fangAns = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(data[0]));
			String xueXiaoXueHao[] = data[1].split("_");
			// String xueXiaoID = xueXiaoXueHao[0];
			String xueXiaoID = banJiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			XueQiDeYu xueQiDeYu =  null;
			List<XueQi> xueQis = xueqiService.findByXueXiaoID(xueXiaoID);
			for (XueQi xq : xueQis) {
				XueQiDeYu deyu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xq.getXueqiid(), data[2] + ",%",
						"%," + data[2] + ",%");
				if (null != deyu) {
					if (!deyu.getZhuangtai().toString().equals("2")) {
						xueQiDeYu = deyu;
					}
				}
			}
//			map.put("riqi", format.format(date));
//			map.put("xueXiaoID", xueXiaoID);
//			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
			JSONObject json = new JSONObject();
//			if (xueQi != null) {
//				 xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(), data[2] + ",%",
//						"%," + data[2] + ",%");
				if (xueQiDeYu == null) {
					return jsons;
				}
				if (xueQiDeYu.getZhuangtai() == 3) {
					json.put("xueqideyu", xueQiDeYu);
					jsons.add(json);
					return jsons;
				} else {
					return jsons;
				}
//			} else {
//				return jsons;
//			}
		} else {
			return null;
		}
	}

	@RequestMapping(value = "app_DeYufenxiangqing")
	@ResponseBody
	public JSONObject app_DeYufenxiangqing(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xuenian = request.getParameter("xuenian");
		String xueqi = request.getParameter("xueqi");
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		List<DeYuPingFenFangAn> fangAns = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String data[] = code.split(",zytech,");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xueSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(data[0]));
			String xueXiaoXueHao[] = data[1].split("_");
			// String xueXiaoID = xueXiaoXueHao[0];
			String xueXiaoID = banJiService.findXueXiaoIDByBanJiID(xueSheng.getBanjiid());
			map.put("riqi", format.format(date));
			map.put("xueXiaoID", xueXiaoID);
			json.put("xingming", xueSheng.getXingming());
			json.put("xuehao", xueSheng.getXuehao());
			String xueshengdeyuid = request.getParameter("deyufenid");
			if (xueshengdeyuid != null) {
				XueShengDeYu xueShengDeYu = deYuService.selectByDeYuFenID(Integer.parseInt(xueshengdeyuid));
				if (xueShengDeYu != null) {
					json.put("deyufenid", xueShengDeYu.getDeyufenid());
					json.put("leixing", xueShengDeYu.getLeixing());
				}
				XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiDeYuID(xueShengDeYu.getXueqideyuid());
				json.put("zhuangtai", xueQiDeYu.getZhuangtai());
				int fanganid = deYuService.getFangAnIdByXueQiDeYuID(xueShengDeYu.getXueqideyuid());
				fangAns = deYuService.getListByFangAnID(fanganid);
				json.put("fangAns", fangAns);
				return json;
			} else {
				XueQi xueQi = new XueQi();
				if (null == xuenian || null == xueqi || "".equals(xuenian) || "".equals(xueqi)) {
//					xueQi = xueqiService.getByxueXiaoIDandriQi(map);
//					if (xueQi == null) {
//						List<XueQi> xueqis = xueqiService.getNewerXueQiByXueQi(map);
//						xueQi = xueqis.get(0);
//					}
//				} else {
//					map.put("nianfen", xuenian.split("~")[0]);
//					map.put("xueqi", xueqi);
//					map.put("xuexiaoid", banJiService.findXueXiaoIDByBanJiID(xueSheng.getBanjiid()));
//					xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
					List<XueQi> xueQis = xueqiService.findByXueXiaoID(xueXiaoID);
					for(XueQi xQi : xueQis) {
						XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xQi.getXueqiid(),
								data[2] + ",%", "%," + data[2] + ",%");
						if (xueQiDeYu != null && !xueQiDeYu.getZhuangtai().toString().equals("2")) {
							fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
							map2.put("xueShengID", xueSheng.getXueshengid() + "");
							map2.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid() + "");
							XueShengDeYu xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map2);
							if (xueShengDeYu != null) {
								json.put("deyufenid", xueShengDeYu.getDeyufenid());
								json.put("leixing", xueShengDeYu.getLeixing());
							}
							json.put("zhuangtai", xueQiDeYu.getZhuangtai());
							json.put("fangAns", fangAns);
							json.put("xueqideyuid", xueQiDeYu.getXueqideyuid());
							return json;
						}else {
							continue;
						}
					}
				}
			}
		} else {
			return null;
		}
		return json;
	}

	// 显示所有该班级德育分六大项的总分，和加权总分
	@RequestMapping(value = "app_GetXueShengdeyugongshi")
	@ResponseBody
	public List<JSONObject> app_GetXueShengdeyugongshi(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		Map<String, String> map = new HashMap<String, String>();
		List<Integer> xueShengIDs = new ArrayList<>();
		// List<XueShengDeYu> xueShengDeYus = new ArrayList<>();
		List<JSONObject> xueShengDeYus = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(data[0]));
			String xueXiaoXueHao[] = data[1].split("_");
			String xuexiaoid = banJiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			List<XueQi> xueQis = xueqiService.findByXueXiaoID(xuexiaoid);
			XueQi xueQi = null;
			XueQiDeYu xueQiDeYu = null;
			for (XueQi xq : xueQis) {
				XueQiDeYu deyu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xq.getXueqiid(), data[2] + ",%",
						"%," + data[2] + ",%");
				if (null != deyu) {
					if (!deyu.getZhuangtai().toString().equals("2")) {
						xueQiDeYu = deyu;
						xueQi = xq;
					}
				}
			}
			
//			map.put("riqi", format.format(date));
//			map.put("xueXiaoID", xuexiaoid);
//			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
//			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(), data[2] + ",%",
//					"%," + data[2] + ",%");
			if (xueQiDeYu.getZhuangtai() == 3) {
				List<DeYuPingFenFangAn> fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
				xueShengIDs = xueShengService.getXueShengsByBanJiID(Integer.parseInt(data[2]));
				Map<String, String> map2 = new HashMap<>();
				map2.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid().toString());
				for (int i = 0; i < xueShengIDs.size(); i++) {
					JSONObject json = new JSONObject();
					int num = 0;
					for (int i1 = 0; i1 < fangAns.size(); i1++) {
						if (fangAns.get(i1).getShangjiid() == 0) {
							num++;
						}
					}
					json.put("num", num);
					String xueShengID = xueShengIDs.get(i).toString();
					map2.put("xueShengID", xueShengID);
					XueShengDeYu xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map2);
					if (xueShengDeYu != null) {
						if(xueShengID.equals(data[0])){
							if(null != xueQiDeYu.getFankui()){
								String fanKuis[] = xueQiDeYu.getFankui().split(";");
								for (String string2 : fanKuis) {
									String s[] = string2.split(":");
									if(s[0].equals(data[0])){
										xueShengDeYu.setFankui(s[1]);
									}
								}
							}
						}
						String xuehao = xueShengService.getUserById(Integer.parseInt(xueShengID)).getXuehao();
						xueShengDeYu.setXuehao(Integer.parseInt(xuehao));
						xueShengDeYu
								.setXingming(xueShengService.getUserById(Integer.parseInt(xueShengID)).getXingming());
						String deFens[] = xueShengDeYu.getDefenxiangqing().split(",");
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
						for (DeYuPingFenFangAn deYuPingFenFangAn : fangAns) {
							manFen = manFen + deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen();
							double yiJiZongFen = 0;
							for (DeYuPingFenFangAn fangAn : deYuPingFenFangAn.getChildList()) {
								List<Integer> fenShus = new ArrayList<>();
								double erJiZongFen = 0;
								if (fangAn.getXiangleixing() == 0) {
									if (fangAn.getXueshengtianxie() == 1) {
										erJiZongFen = erJiZongFen
												+ Integer.parseInt(map3.get(fangAn.getPingfenid().toString()));
									} else if (fangAn.getXueshengtianxie() == 2) {
										double danXiangFenHe = 0;
										String dString = map3.get(fangAn.getPingfenid().toString());
										String deFenXiang[] = dString.split(";");
										if (!deFenXiang[0].equals("无")) {
											danXiangFenHe = deFenXiang.length * fangAn.getDanxiangfen();
											if (danXiangFenHe > fangAn.getManfen()) {
												erJiZongFen = erJiZongFen + fangAn.getManfen();
											} else {
												erJiZongFen = erJiZongFen + danXiangFenHe;
											}
										}
									} else {
										String dString = map3.get(fangAn.getPingfenid().toString());
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
												if (!deFenXiang[0].equals("无")) {
													danXiangFenHe = deFenXiang.length * an.getDanxiangfen();
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
							if (yiJiZongFen > deYuPingFenFangAn.getManfen()) {
								yiJiZongFens.add(deYuPingFenFangAn.getManfen().toString());
							} else {
								yiJiZongFens.add(Double.toString(yiJiZongFen));
							}
							json.put("defen", yiJiZongFens);
						}
						xueShengDeYu.setFenshu(yiJiZongFens);
					} else {
						xueShengDeYu = new XueShengDeYu();
						String xuehao = xueShengService.getUserById(Integer.parseInt(xueShengID)).getXuehao();
						xueShengDeYu.setXuehao(Integer.parseInt(xuehao));
						xueShengDeYu
								.setXingming(xueShengService.getUserById(Integer.parseInt(xueShengID)).getXingming());
						xueShengDeYu.setFenshu(null);
					}
					json.put("status", xueQiDeYu.getZhuangtai());
					json.put("xueqi", xueQi);
					json.put("fanAns", fangAns);
					json.put("deYu", xueShengDeYu);
					json.put("xueqideyuid", xueQiDeYu.getXueqideyuid());
					xueShengDeYus.add(json);
				}
			} else {
				return xueShengDeYus;
			}
		} else {
			return null;
		}
		return xueShengDeYus;
	}

	// 通过deyufenid返回个人德育分详情
	@RequestMapping(value = "app_BackGeRenDeYuxiangqing")
	@ResponseBody
	public List<String[]> app_BackGeRenDeYuxiangqing(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			List<String[]> fenshu = new ArrayList<>();

			if (code != null) {

				XueShengDeYu xueShengDeYu = deYuService.selectByDeYuFenID(Integer.parseInt(code));
				if (xueShengDeYu == null) {
					return fenshu;
				} else {
					String deFens[] = xueShengDeYu.getDefenxiangqing().split(",");
					for (int i = 0; i < deFens.length; i++) {
						String[] s = new String[2];
						String defen[] = deFens[i].split(":");
						if (defen[1].endsWith(";")) {
							defen[1] = defen[1].substring(0, defen[1].length() - 1);
						}
						s[0] = defen[0];
						s[1] = defen[1];
						fenshu.add(s);
					}
				}
				return fenshu;
			} else {
				return fenshu;
			}
		} else {
			return null;
		}
	}

	@RequestMapping(value = "app_DeYufengeren")
	@ResponseBody
	public List<JSONObject> app_DeYufengeren(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		List<String> xuenianList = new ArrayList<>();
		List<String> deyuitem = new ArrayList<String>();
		String code = request.getParameter("CODE");
		Map<String, String> map = new HashMap<String, String>();
		List<JSONObject> fangAns = new ArrayList<>();
		List<DeYuPingFenFangAn> list = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);

		json.put("xuexiao_xuehao", data[1]);
		json.put("id", data[0]);
		json.put("banJiID", data[2]);

		if (str.equals(token)) {
			XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(data[0]));
			String xueXiaoXueHao[] = data[1].split("_");
			// String xueXiaoID = xueXiaoXueHao[0];
			String xueXiaoID = banJiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			System.out.println(format.format(date));
			System.out.println(xueXiaoID);
			map.put("riqi", format.format(date));
			map.put("xueXiaoID", xueXiaoID);

			xuenianList = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xueXiaoID));
			json.put("xuenianList", xuenianList);

			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
			if (xueQi != null) {
				XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(), data[2] + ",%",
						"%," + data[2] + ",%");
				if (xueQiDeYu == null) {

					json.put("zhuangtai", xueQiDeYu.getZhuangtai());

					return fangAns;
				}
				list = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getShangjiid() == 0) {
						deyuitem.add(list.get(i).getMingcheng());
					}
				}

				json.put("zhuangtai", xueQiDeYu.getZhuangtai());
				json.put("xueqi", xueQi);
				json.put("xuenian", xueQi.getXuenian());
				json.put("xueqi", xueQi.getXueqi());
				json.put("fangAns", list);

				fangAns.add(json);

				System.out.println(fangAns);
				return fangAns;
			} else {
				return fangAns;
			}
		} else {
			return null;
		}
	}

	// 显示该学生德育分六大项的总分，和加权总分
	@RequestMapping(value = "app_GetBenRendeyu")
	@ResponseBody
	public JSONObject app_GetBenRendeyu(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String code = request.getParameter("CODE");
		Map<String, String> map = new HashMap<String, String>();
		Date date = new Date();
		XueShengDeYu xueShengDeYu = new XueShengDeYu();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<String> yiJiZongFens = new ArrayList<>();
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(data[0]));
			String xueXiaoXueHao[] = data[1].split("_");
			String xuexiaoid = banJiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			map.put("riqi", format.format(date));
			map.put("xueXiaoID", xuexiaoid);
			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(), data[2] + ",%",
					"%," + data[2] + ",%");
			if (xueQiDeYu == null) {
				return json;
			}
			map.put("xueShengID", data[0]);
			map.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid().toString());
			xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map);
			if (xueShengDeYu == null) {
				return json;
			}
			String xuehao = xueShengService.getUserById(Integer.parseInt(data[0])).getXuehao();
			xueShengDeYu.setXuehao(Integer.parseInt(xuehao));
			xueShengDeYu.setXingming(xueShengService.getUserById(Integer.parseInt(data[0])).getXingming());

			json.put("zhuangtai", xueQiDeYu.getZhuangtai());
			json.put("xuehao", xuehao);
			json.put("xingming", xueShengService.getUserById(Integer.parseInt(data[0])).getXingming());
			json.put("deyufenid", xueShengDeYu.getDeyufenid());

			String deFens[] = xueShengDeYu.getDefenxiangqing().split(",");
			Map<String, String> map2 = new HashMap<>();
			for (int i = 0; i < deFens.length; i++) {
				String defen[] = deFens[i].split(":");
				if (defen[1].endsWith(";")) {
					defen[1] = defen[1].substring(0, defen[1].length() - 1);
				}
				map2.put(defen[0], defen[1]);
			}
			double manFen = 0;
			List<DeYuPingFenFangAn> fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
			for (DeYuPingFenFangAn deYuPingFenFangAn : fangAns) {
				manFen = manFen + deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen();
				double yiJiZongFen = 0;
				for (DeYuPingFenFangAn fangAn : deYuPingFenFangAn.getChildList()) {
					List<Integer> fenShus = new ArrayList<>();
					double erJiZongFen = 0;
					if (fangAn.getXiangleixing() == 0) {
						if (fangAn.getXueshengtianxie() == 1) {
							erJiZongFen = erJiZongFen + Integer.parseInt(map2.get(fangAn.getPingfenid().toString()));
						} else if (fangAn.getXueshengtianxie() == 2) {
							double danXiangFenHe = 0;
							String dString = map2.get(fangAn.getPingfenid().toString());
							String deFenXiang[] = dString.split(";");
							if (!deFenXiang[0].equals("无")) {
								danXiangFenHe = deFenXiang.length * fangAn.getDanxiangfen();
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
								if (!deFenXiang[0].equals("无")) {
									danXiangFenHe = deFenXiang.length * an.getDanxiangfen();
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
				if (yiJiZongFen > deYuPingFenFangAn.getManfen()) {
					yiJiZongFens.add(deYuPingFenFangAn.getManfen().toString());

				} else {
					yiJiZongFens.add(Double.toString(yiJiZongFen));
				}
			}
			xueShengDeYu.setFenshu(yiJiZongFens);
			json.put("fangAns", fangAns);
			json.put("fenshu", yiJiZongFens);
		} else {
			return null;
		}
		json.put("xueShengDeYu", xueShengDeYu);
		json.put("deyufen", xueShengDeYu.getDeyufen());
		return json;
	}

	// 根据选定的学年学期查询德育分

	// 获得学年学期
	@RequestMapping(value = "app_GetXueNianXueQi")
	@ResponseBody
	public List<String> app_GetXueNianXueQi(HttpServletRequest request) {
		List<String> datetime = new ArrayList<String>();
		String code = request.getParameter("CODE");
		Date date = new Date();
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);
		datetime.add(data[1]);
		datetime.add(data[0]);
		datetime.add(data[2]);
		// datetime.add(status);
		if (str.equals(token)) {
			XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(data[0]));
			String xueXiaoXueHao[] = data[1].split("_");
			// String xueXiaoID = xueXiaoXueHao[0];
			String xueXiaoID = banJiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			map.put("riqi", format.format(date));
			map.put("xueXiaoID", xueXiaoID);
			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
			if (xueQi != null) {
				XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(), data[2] + ",%",
						"%," + data[2] + ",%");
				datetime.add(xueQi.getXuenian());
				datetime.add(Integer.toString(xueQi.getXueqi()));
				if (xueQiDeYu != null) {
					datetime.add(Integer.toString(xueQiDeYu.getZhuangtai()));
					datetime.add(Integer.toString(xueQiDeYu.getFanganid()));
				}
			}
		} else {
			return null;
		}
		return datetime;
	}

	// 根据学年学期查询个人德育分情况
	@RequestMapping(value = "app_ChaXunByxuenianxueqi")
	@ResponseBody
	public JSONObject app_ChaXunByxuenianxueqi(String xuenian, String xueqi, String xuexiaoXuehao,
			HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Map<Object, Object> map2 = new HashMap<>();
		String banjiid = request.getParameter("banjiid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String studentid = request.getParameter("studentid");
		String string = studentid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			// if (true) {
			XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(studentid));
			List<DeYuPingFenFangAn> fangAns = new ArrayList<>();
			map.put("xuenian", xuenian);
			map.put("xueqi", xueqi);
			Integer zhuangtai;
			String xueXiaoXueHao[] = xuexiaoXuehao.split("_");
			// String xueXiaoID = xueXiaoXueHao[0];
			String xueXiaoID = banJiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			map.put("xuexiaoid", xueXiaoID);
			XueQi xQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			map2.put("xueqiid", xQi.getXueqiid());
			if (xQi != null) {
				XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xQi.getXueqiid(), banjiid + ",%",
						"%," + banjiid + ",%");
				if (xueQiDeYu == null) {
					map2.put("zhuangtai", "");
					map2.put("fangAns", "");
					return JSONObject.fromObject(map2);
				}
				fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
				zhuangtai = xueQiDeYu.getZhuangtai();
				map2.put("zhuangtai", zhuangtai);
				map2.put("fangAns", fangAns);
				if (fangAns == null) {
					map2.put("zhuangtai", "");
					map2.put("fangAns", "");
					return JSONObject.fromObject(map2);
				}
			} else {
				map2.put("zhuangtai", "");
				map2.put("fangAns", "");
				return JSONObject.fromObject(map2);
			}
			return JSONObject.fromObject(map2);
		} else {
			return null;
		}
	}

	// 根据学年，学期，学校id找到学期，然后通过xueqiID找到XueQiDeYu
	// 然后通过XueQiDeYuID和xueshengID找到该学生德育总分
	@RequestMapping(value = "app_WantToDeYuLiuXiang")
	@ResponseBody
	public JSONObject app_WantToDeYuLiuXiang(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		Map<String, String> map = new HashMap<String, String>();
		List<DeYuPingFenFangAn> fangAns = new ArrayList<>();

		String xueqiid = request.getParameter("xueqiid");

		String code = request.getParameter("CODE");
		String banjiid = request.getParameter("banjiid");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = data[3] + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xuesheng = xueShengService.getUserById(Integer.parseInt(data[3]));
			String xueXiaoXueHao[] = data[2].split("_");
			// String xueXiaoID = xueXiaoXueHao[0];
			String xueXiaoID = banJiService.findXueXiaoIDByBanJiID(xuesheng.getBanjiid());
			json.put("xueshengid", data[3]);
			json.put("xuehao", xuesheng.getXuehao());
			// 通过学生id获得学生
			json.put("xingming", xuesheng.getXingming());
			// map.put("xuenian", data[0]);
			// map.put("xueqi", data[1]);
			if (banjiid == null || "".equals(banjiid)) {
				banjiid = xuesheng.getBanjiid().toString();
			}
			map.put("xuexiaoid", xueXiaoID);
			XueShengDeYu xueShengDeYu = new XueShengDeYu();
			// XueQi xQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			XueQi xQi = xueqiService.getByXueQiID(Integer.parseInt(xueqiid));
			if (xQi != null) {
				XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xQi.getXueqiid(), banjiid + ",%",
						"%," + banjiid + ",%");
				if (xueQiDeYu == null) {
					return json;
				}
				map.put("xueShengID", data[3]);
				map.put("xueQiDeYuID", Integer.toString(xueQiDeYu.getXueqideyuid()));
				xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map);
				if (xueShengDeYu != null) {
					String deFens[] = xueShengDeYu.getDefenxiangqing().split(",");
					Map<String, String> map2 = new HashMap<>();
					for (int i = 0; i < deFens.length; i++) {
						String defen[] = deFens[i].split(":");
						if (defen[1].endsWith(";")) {
							defen[1] = defen[1].substring(0, defen[1].length() - 1);
						}
						map2.put(defen[0], defen[1]);
					}
					double manFen = 0;
					List<String> yiJiZongFens = new ArrayList<>();
					fangAns = deYuService.getListByFangAnID(xueQiDeYu.getFanganid());
					for (DeYuPingFenFangAn deYuPingFenFangAn : fangAns) {
						manFen = manFen + deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen();
						double yiJiZongFen = 0;
						for (DeYuPingFenFangAn fangAn : deYuPingFenFangAn.getChildList()) {
							List<Integer> fenShus = new ArrayList<>();
							double erJiZongFen = 0;
							if (fangAn.getXiangleixing() == 0) {
								if (fangAn.getXueshengtianxie() == 1) {
									erJiZongFen = erJiZongFen
											+ Integer.parseInt(map2.get(fangAn.getPingfenid().toString()));
								} else if (fangAn.getXueshengtianxie() == 2) {
									double danXiangFenHe = 0;
									String dString = map2.get(fangAn.getPingfenid().toString());
									String deFenXiang[] = dString.split(";");
									danXiangFenHe = deFenXiang.length * fangAn.getDanxiangfen();
									if (danXiangFenHe > fangAn.getManfen()) {
										erJiZongFen = erJiZongFen + fangAn.getManfen();
									} else {
										erJiZongFen = erJiZongFen + danXiangFenHe;
									}
								} else {
									String dString = map2.get(fangAn.getPingfenid().toString());
									erJiZongFen = erJiZongFen + Integer.parseInt(dString);
								}
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
										danXiangFenHe = deFenXiang.length * an.getDanxiangfen();
										if (danXiangFenHe > an.getManfen()) {
											erJiZongFen = erJiZongFen + an.getManfen();
										} else {
											erJiZongFen = erJiZongFen + danXiangFenHe;
										}
									} else {
										String dString = map2.get(an.getPingfenid().toString());
										fenShus.add(Integer.parseInt(dString));
									}
								}
								if (!fenShus.isEmpty()) {
									erJiZongFen = erJiZongFen + Collections.max(fenShus);
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
							deYuPingFenFangAn.setZuidifen(deYuPingFenFangAn.getManfen());
						} else {
							yiJiZongFens.add(Double.toString(yiJiZongFen));
							deYuPingFenFangAn.setZuidifen((new Double(yiJiZongFen)).intValue());
						}
					}

					json.put("fenshu", yiJiZongFens);
					xueShengDeYu.setFenshu(yiJiZongFens);
				} else {
					json.put("fangAns", fangAns);
					return json;
				}
			} else {
				json.put("fangAns", fangAns);
				return json;
			}

			json.put("xueShengDeYu", xueShengDeYu);
			json.put("deyufenid", xueShengDeYu.getDeyufenid());
			json.put("fangAns", fangAns);
			return json;
		} else {
			return null;
		}
	}

	// 对于修改德育分之后进行提交修改
	@RequestMapping(value = "app_TiJiaoXiuGaideyu")
	@ResponseBody
	public String app_TiJiaoXiuGaideyu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String retinfo = "";
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			String fanganid = request.getParameter("fanganid");
			String deYuFenID = request.getParameter("deyufenid");
			List<Integer> pingFenIdList = deYuService.getPingfenIDByFangAnID(Integer.parseInt(fanganid));
			List<DeYuPingFenFangAn> list = deYuService.getAllByFangAnID(Integer.parseInt(fanganid));
			StringBuffer sBuffer = new StringBuffer();
			for (int i = 0; i < pingFenIdList.size(); i++) {
				for (DeYuPingFenFangAn fangAn : list) {
					if (fangAn.getPingfenid().toString().equals(pingFenIdList.get(i).toString())) {
						if (fangAn.getXueshengtianxie().intValue() == 1
								|| fangAn.getXueshengtianxie().intValue() == 3) {
							String value = request.getParameter(pingFenIdList.get(i).toString());
							if ("".equals(value) || value == null || "0".equals(value) || "undefined".equals(value)) {
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
							if ("".equals(xString) || xString == null || "0".equals(xString)
									|| "undefined".equals(xString)) {
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
						}
					}
				}
			}
			String deFens[] = sBuffer.toString().split(",");
			Map<String, String> map2 = new HashMap<>();
			for (int i = 0; i < deFens.length; i++) {
				String defen[] = deFens[i].split(":");
				if (defen[1].endsWith(";")) {
					defen[1] = defen[1].substring(0, defen[1].length() - 1);
				}
				map2.put(defen[0], defen[1]);
			}
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
							if ("0".equals(map2.get(fangAn.getPingfenid().toString()))
									|| null == map2.get(fangAn.getPingfenid().toString())
									|| "".equals(map2.get(fangAn.getPingfenid().toString()))) {
								erJiZongFen = erJiZongFen + 0;
							} else {
								erJiZongFen = erJiZongFen
										+ Integer.parseInt(map2.get(fangAn.getPingfenid().toString()));
							}
						} else if (fangAn.getXueshengtianxie() == 2) {
							double danXiangFenHe = 0;
							String dString = map2.get(fangAn.getPingfenid().toString());
							if ("0".equals(dString) || null == dString || "".equals(dString)) {

							} else {
								String deFenXiang[] = dString.split(";");
								if (!deFenXiang[0].equals("无")) {
									danXiangFenHe = deFenXiang.length * fangAn.getDanxiangfen();
									if (danXiangFenHe > fangAn.getManfen()) {
										erJiZongFen = erJiZongFen + fangAn.getManfen();
									} else {
										erJiZongFen = erJiZongFen + danXiangFenHe;
									}
								}
							}
						} else {
							String dString = map2.get(fangAn.getPingfenid().toString());
							if ("0".equals(dString) || null == dString || "".equals(dString)) {
								erJiZongFen = erJiZongFen + 0;
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
										if ("0".equals(map2.get(an.getPingfenid().toString()))
												|| null == map2.get(an.getPingfenid().toString())
												|| "".equals(map2.get(an.getPingfenid().toString()))) {
											erJiZongFen = erJiZongFen + 0;
										} else {
											erJiZongFen = erJiZongFen
													+ Integer.parseInt(map2.get(an.getPingfenid().toString()));
										}
									}
								} else if (an.getXueshengtianxie() == 2) {
									double danXiangFenHe = 0;
									String dString = map2.get(an.getPingfenid().toString());
									String deFenXiang[] = dString.split(";");
									if (!deFenXiang[0].equals("无")) {
										danXiangFenHe = deFenXiang.length * an.getDanxiangfen();
										if (danXiangFenHe > an.getManfen()) {
											erJiZongFen = erJiZongFen + an.getManfen();
										} else {
											erJiZongFen = erJiZongFen + danXiangFenHe;
										}
									}
								} else {
									String dString = map2.get(an.getPingfenid().toString());
									if ("0".equals(dString) || null == dString || "".equals(dString)) {
										// fenShus.add(0);
										erJiZongFen = erJiZongFen + 0;
									} else {
										// fenShus.add(Integer.parseInt(dString));
										erJiZongFen = erJiZongFen + Integer.parseInt(dString);
									}
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
				if (yiJiZongFen > deYuPingFenFangAn.getManfen()) {
					yiJiZongFens.add(deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen());
				} else {
					yiJiZongFens.add(yiJiZongFen * deYuPingFenFangAn.getXuefen());
				}
			}
			double deFen = 0;
			for (int i = 0; i < yiJiZongFens.size(); i++) {
				deFen = deFen + yiJiZongFens.get(i);
			}
			double deyufen = deFen / manFen * 100;
			DecimalFormat df = new DecimalFormat("#.00");
			String deYuFen = df.format(deyufen);
			Map<String, String> map = new HashMap<>();
			map.put("defenxiangqing", sBuffer.toString());
			map.put("deyufen", deYuFen);
			map.put("deyufenid", deYuFenID);
			map.put("leixing", request.getParameter("leixing").toString());
			int i = deYuService.updateByDeYuFenID(map);
			if (i > 0) {
				response.setContentType("text/html; charset=utf-8");
				retinfo = "success";
				return retinfo;
			} else {
				response.setContentType("text/html; charset=utf-8");
				retinfo = "fail";
				return retinfo;
			}
		} else {
			return null;
		}
	}

	// 进入填写考评表，进行判断是否已填或过期等
	@RequestMapping(value = "app_PanDuantianxie")
	@ResponseBody
	public JSONObject app_PanDuantianxie(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String code = request.getParameter("CODE");
		String xuenian = request.getParameter("xuenian");
		String xueqi = request.getParameter("xueqi");
		String retInfo = "";
		Map<String, String> map = new HashMap<String, String>();
		Date date = new Date();
		XueShengDeYu xueShengDeYu = new XueShengDeYu();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xueSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(data[0]));
			String xueXiaoXueHao[] = data[1].split("_");
			XueQi xueQi = new XueQi();
			String xueXiaoID = banJiService.findXueXiaoIDByBanJiID(xueSheng.getBanjiid());
			List<XueQi> xueQis = xueqiService.findByXueXiaoID(xueXiaoID);
			if (null == xuenian || "".equals(xuenian) || null == xueqi || "".equals(xueqi)) {
				map.put("riqi", format.format(date));
				map.put("xueXiaoID", xueXiaoID);
				System.out.println(format.format(date));
				xueQi = xueqiService.getByxueXiaoIDandriQi(map);
				if (xueQi == null) {
					retInfo = "fail1";
					json.put("retInfo", "fail1");
					return json;
				}
			} else {
				map.put("nianfen", xuenian.split("~")[0]);
				map.put("xueqi", xueqi);
				map.put("xuexiaoid", xueXiaoID);
				xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
				if (xueQi == null) {
					retInfo = "fail1";
					json.put("retInfo", "fail1");
					return json;
				}
			}
			if (data[2] == null || "".equals(data[2])) {
				data[2] = xueSheng.getBanjiid().toString();
			}
//			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(), data[2] + ",%",
//					"%," + data[2] + ",%");
			XueQiDeYu xueQiDeYu = null;
			for (XueQi xq : xueQis) {
				XueQiDeYu deyu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xq.getXueqiid(), data[2] + ",%",
						"%," + data[2] + ",%");
				if (null != deyu) {
					if (!deyu.getZhuangtai().toString().equals("2")) {
						xueQiDeYu = deyu;
					}
				}
			}
			if (xueQiDeYu == null) {
				retInfo = "fail1";
				json.put("retInfo", "fail1");
				return json;
			}
			if (xueQiDeYu.getZhuangtai() != 1) {
				retInfo = "fail0";
				json.put("retInfo", "fail0");
				return json;
			}
			map.put("xueShengID", data[0]);
			map.put("xueQiDeYuID", xueQiDeYu.getXueqideyuid().toString());
			xueShengDeYu = deYuService.selectByXueShengIDAndXueQiDeYuID(map);
			if (xueShengDeYu == null) {
				retInfo = "success";
				json.put("retInfo", "success");
				return json;
			}

			json.put("xingming", xueSheng.getXingming());
			json.put("xuehao", xueSheng.getXuehao());
			json.put("deyufenid", xueShengDeYu.getDeyufenid());

			retInfo = "fail2";
			json.put("retInfo", "fail2");
			return json;
		} else {
			return null;
		}
	}

	// 提交新填写的德育成绩表格
	@RequestMapping(value = "app_TiJiaoXinBiaoGe")
	@ResponseBody
	public String app_TiJiaoXinBiaoGe(Integer studentid, String xuexiaoXuehao, String fanganid,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		// String fanganid = request.getParameter("fanganid");
		String retinfo = "";
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = studentid.toString() + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			List<Integer> pingFenIdList = deYuService.getPingfenIDByFangAnID(Integer.parseInt(fanganid));
			List<DeYuPingFenFangAn> list = deYuService.getAllByFangAnID(Integer.parseInt(fanganid));
			StringBuffer sBuffer = new StringBuffer();
			for (int i = 0; i < pingFenIdList.size(); i++) {
				for (DeYuPingFenFangAn fangAn : list) {
					if (fangAn.getPingfenid().toString().equals(pingFenIdList.get(i).toString())) {
						if (fangAn.getXueshengtianxie().intValue() == 1
								|| fangAn.getXueshengtianxie().intValue() == 3) {
							String value = request.getParameter(pingFenIdList.get(i).toString());
							System.out.println(value);
							if ((value == null) || ("".equals(value)) || ("null".equalsIgnoreCase(value))
									|| ("undefined".equalsIgnoreCase(value))) {
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
							if ("".equals(xString) || null == xString || ("undefined".equalsIgnoreCase(xString))) {
								sBuffer.append(pingFenIdList.get(i) + ":无" + ";");
							} else {
								sBuffer.append(pingFenIdList.get(i) + ":"
										+ request.getParameter(pingFenIdList.get(i).toString()) + ";");
							}
							for (int j = 1; request
									.getParameter(pingFenIdList.get(i).toString() + "-" + j) != null; j++) {

								if ("".equals(request.getParameter(pingFenIdList.get(i).toString() + "-" + j))
										|| null == request.getParameter(pingFenIdList.get(i).toString() + "-" + j)
										|| ("undefined".equalsIgnoreCase(
												request.getParameter(pingFenIdList.get(i).toString() + "-" + j)))) {
									sBuffer.append("无;");
								} else {
									sBuffer.append(
											request.getParameter(pingFenIdList.get(i).toString() + "-" + j) + ";");
								}
							}
							sBuffer.append(",");
						}
					}
				}
			}
			String deFens[] = sBuffer.toString().split(",");
			Map<String, String> map2 = new HashMap<>();
			for (int i = 0; i < deFens.length; i++) {
				String defen[] = deFens[i].split(":");
				if (defen[1].endsWith(";")) {
					defen[1] = defen[1].substring(0, defen[1].length() - 1);
				}
				map2.put(defen[0], defen[1]);
			}
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
							erJiZongFen = erJiZongFen + Integer.parseInt(map2.get(fangAn.getPingfenid().toString()));
						} else if (fangAn.getXueshengtianxie() == 2) {
							double danXiangFenHe = 0;
							String dString = map2.get(fangAn.getPingfenid().toString());
							String deFenXiang[] = dString.split(";");
							if (!deFenXiang[0].equals("无")) {
								danXiangFenHe = deFenXiang.length * fangAn.getDanxiangfen();
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
									if (!deFenXiang[0].equals("无")) {
										danXiangFenHe = deFenXiang.length * an.getDanxiangfen();
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
				if (yiJiZongFen > deYuPingFenFangAn.getManfen()) {
					yiJiZongFens.add(deYuPingFenFangAn.getManfen() * deYuPingFenFangAn.getXuefen());
				} else {
					yiJiZongFens.add(yiJiZongFen * deYuPingFenFangAn.getXuefen());
				}
			}
			double deFen = 0;
			for (int i = 0; i < yiJiZongFens.size(); i++) {
				deFen = deFen + yiJiZongFens.get(i);
			}
			double deyufen = deFen / manFen * 100;
			String xuenian = request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi");
			DecimalFormat df = new DecimalFormat("#.00");
			String xueqideyuid = request.getParameter("xueqideyuid");
			String deYuFen = df.format(deyufen);
//			Date date = new Date();
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			Map<String, String> map = new HashMap<>();
//			XueSheng xs = xueShengService.selectByPrimaryKey(studentid);
//			String xueXiaoXueHao[] = xuexiaoXuehao.split("_");
			// String xueXiaoID = xueXiaoXueHao[0];
//			String xueXiaoID = banJiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
//			XueQi xueQi = new XueQi();
//			if (null == xuenian || null == xueqi || "".equals(xuenian) || "".equals(xueqi)
//					|| "undefined".equals(xuenian) || "undefined".equals(xueqi)) {
//				map.put("riqi", format.format(date));
//				map.put("xueXiaoID", xueXiaoID);
//				xueQi = xueqiService.getByxueXiaoIDandriQi(map);
//			} else {
//				map.put("nianfen", xuenian.split("~")[0]);
//				map.put("xueqi", xueqi);
//				map.put("xuexiaoid", xueXiaoID);
//				xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
//			}
			// int xueQiDeYuID =
			// deYuService.selectXueQiDeYuIDByXueQiID(xueQi.getXueqiid());
//			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(),
//					xs.getBanjiid() + ",%", "%," + xs.getBanjiid() + ",%");
//			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiDeYuID(Integer.parseInt(xueqideyuid));
			XueShengDeYu xueShengDeYu = new XueShengDeYu();
			xueShengDeYu.setLeixing(Integer.parseInt(request.getParameter("leixing")));
			xueShengDeYu.setDefenxiangqing(sBuffer.toString());
			xueShengDeYu.setXueshengid(studentid);
			xueShengDeYu.setXueqideyuid(Integer.parseInt(xueqideyuid));
			xueShengDeYu.setDeyufen(Double.valueOf(deYuFen).doubleValue());
			int i = deYuService.insert(xueShengDeYu);
			if (i > 0) {
				response.setContentType("text/html; charset=utf-8");
				retinfo = "success";
				return retinfo;
			} else {
				response.setContentType("text/html; charset=utf-8");
				retinfo = "fail";
				return retinfo;
			}
		} else {
			return null;
		}
	}

	@RequestMapping(value = "app_DeYufenfankui")
	@ResponseBody
	public JSONObject app_DeYufenfankui(HttpServletRequest request, HttpServletResponse response) {
		String studentid = request.getParameter("studentid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xueqideyuid = request.getParameter("xueqideyuid");
		String string = studentid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			String fankui = request.getParameter("fankui");
			XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(studentid));
			String xueXiaoID = banJiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			JSONObject json = new JSONObject();
			String retInfo = "";
//			Date date = new Date();
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			Map<String, String> map = new HashMap<>();
//			map.put("riqi", format.format(date));
//			map.put("xueXiaoID", xueXiaoID);
//			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
//			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(),
//					xs.getBanjiid() + ",%", "%," + xs.getBanjiid() + ",%");
			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiDeYuID(Integer.parseInt(xueqideyuid));
			if (null != xueQiDeYu) {
				String neirong = "";
				if (null != xueQiDeYu.getFankui()) {
					String fankuis[] = xueQiDeYu.getFankui().split(";");
					for (String string2 : fankuis) {
						String s[] = string2.split(":");
						if (s[0].equals(studentid)) {
							continue;
						} else {
							neirong += string2 + ";";
						}
					}
					neirong += studentid + ":" + fankui + ";";
				} else {
					neirong = studentid + ":" + fankui + ";";
				}
				xueQiDeYu.setFankui(neirong);
				int i = deYuService.updateXueQiDeYuByXueQiDeYu(xueQiDeYu);
				if (i > 0) {
					retInfo = "success";
				}
			}
			json.put("retInfo", retInfo);
			return json;
		} else {
			return null;
		}
	}
}
