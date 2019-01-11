package com.web.controller.web.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.web.model.BanJi;
import com.web.model.FuDaoYuan;
import com.web.model.HuoDong;
import com.web.model.NeiRongTongZhi;
import com.web.model.YongHu;
import com.web.model.ZhuanYe;
import com.web.service.BanJiService;
import com.web.service.FuDaoYuanService;
import com.web.service.NeiRongTongZhiService;
import com.web.service.NianFenService;
import com.web.service.XueShengChuGuanLiYuanService;
import com.web.service.YuanXiService;
import com.web.service.ZhuanYeService;
import com.web.util.PageData;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
public class NeiRongTongZhiController {

	@Autowired
	private NeiRongTongZhiService neirongtongzhiService;
	@Autowired
	private FuDaoYuanService fudaoyuanService;
	@Autowired
	private BanJiService banjiService;
	@Autowired
	private NianFenService nianfenService;
	@Autowired
	private ZhuanYeService zhuanyeService;
	@Autowired
	private YuanXiService yuanxiService;
	@Autowired
	private XueShengChuGuanLiYuanService xueshengchuGuanliyuanService;

	@RequestMapping(value = "fabutongzhi")
	public ModelAndView fabutongzhi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("user") != null) {
			// 专业，班级 ： 排除已经毕业的班级
			List<ZhuanYe> zhuanyes = new ArrayList<>();
			List<BanJi> banjis = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowTime = sdf.format(new Date());
			YongHu user = (YongHu) request.getSession().getAttribute("user");
			if (user.getStatus().equals("fudaoyuan")) {
				FuDaoYuan fudaoyuan = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid());
				String banjiids = fudaoyuan.getBanjiid();
				if (banjiids != null && !"".equals(banjiids)) {
					String banjiid[] = banjiids.split(",");
					for (String string : banjiid) {
						BanJi banJi = banjiService.selectByPrimaryKey(Integer.parseInt(string));
						Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid())
								.getRuxuenianfen();
						String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
						if (sdf.parse(biYeNianFen).getTime() > sdf.parse(nowTime).getTime()) {
							if ((!banjis.isEmpty()) && banjis.size() > 0) {
								int tmp = 0;
								for (BanJi bj : banjis) {
									if (string.equals(bj.getBanjiid().toString())) {
										tmp = 1;
										break;
									}
								}
								if (tmp == 0) {
									banjis.add(banJi);
									ZhuanYe zy = zhuanyeService.selectByPrimaryKey(banJi.getZhuanyeid());
									if ((!zhuanyes.isEmpty()) && zhuanyes.size() > 0) {
										int temp = 0;
										for (ZhuanYe zhuanYe : zhuanyes) {
											if (String.valueOf(zhuanYe.getZhuanyeid())
													.equals(banJi.getZhuanyeid().toString())) {
												temp = 1;
												break;
											}
										}
										if (temp == 0) {
											zhuanyes.add(zy);
										}
									} else {
										zhuanyes.add(zy);
									}
								}
							} else {
								banjis.add(banJi);
								ZhuanYe zy = zhuanyeService.selectByPrimaryKey(banJi.getZhuanyeid());
								if ((!zhuanyes.isEmpty()) && zhuanyes.size() > 0) {
									int temp = 0;
									for (ZhuanYe zhuanYe : zhuanyes) {
										if (String.valueOf(zhuanYe.getZhuanyeid())
												.equals(banJi.getZhuanyeid().toString())) {
											temp = 1;
											break;
										}
									}
									if (temp == 0) {
										zhuanyes.add(zy);
									}
								} else {
									zhuanyes.add(zy);
								}
							}
						}
					}
				} else {
					response.sendRedirect("logout");
					return null;
				}
			}
			if (user.getStatus().equals("guanliyuan")) {
				String xuexiaoid = xueshengchuGuanliyuanService.selectByID(user.getYonghuid()).getXuexiaoid()
						.toString();
				zhuanyes = zhuanyeService.findByXueXiaoID(xuexiaoid);
				List<BanJi> banjiList = banjiService.findByXueXiaoID(xuexiaoid);
				for (BanJi banJi2 : banjiList) {
					Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi2.getRuxuenianfenid())
							.getRuxuenianfen();
					String biYeNianFen = (ruXueNianFen + 4) + "-07-30";
					if (sdf.parse(biYeNianFen).getTime() > sdf.parse(nowTime).getTime()) {
						if ((!banjis.isEmpty()) && banjis.size() > 0) {
							int tmp = 0;
							for (BanJi bj : banjis) {
								if (banJi2.getBanjiid().toString().equals(bj.getBanjiid().toString())) {
									tmp = 1;
									break;
								}
							}
							if (tmp == 0) {
								banjis.add(banJi2);
							}
						} else {
							banjis.add(banJi2);
						}
					}
				}
			}
			mv.addObject("banjis", banjis);
			mv.addObject("zhuanyes", zhuanyes);
		} else {
			response.sendRedirect("logout");
			return null;
		}
		mv.setViewName("common/fabutongzhi");
		return mv;
	}

	@RequestMapping(value = "saveneirongtongzhi")
	@ResponseBody
	public String xinZengTongZhi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String retInfo = "";
		if (request.getSession().getAttribute("user") != null) {
			YongHu user = (YongHu) request.getSession().getAttribute("user");
			NeiRongTongZhi tongzhi = new NeiRongTongZhi();
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String fabufanwei = request.getParameter("fabufanwei");
			String istop = request.getParameter("istop");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String riqi = sdf.format(new Date());
			tongzhi.setTitle(title);
			tongzhi.setContent(content);
			tongzhi.setFaburenid(user.getYonghuid());
			if ("fudaoyuan".equals(user.getStatus())) {
				tongzhi.setFaburenleixing(1);
			}
			if ("guanliyuan".equals(user.getStatus())) {
				tongzhi.setFaburenleixing(2);
			}
			if ("1".equals(istop)) {
				tongzhi.setIstop(true);
			} else {
				tongzhi.setIstop(false);
			}
			tongzhi.setFaburiqi(sdf.parse(riqi));
			if ("1".equals(fabufanwei)) {
				tongzhi.setFabufanwei(1);
				List<BanJi> bjs = banjiService.findByXueXiaoID(
						yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString());
				String tj = "";
				for (BanJi banJi : bjs) {
					Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid())
							.getRuxuenianfen();
					String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
					if (sdf.parse(biYeNianFen).getTime() > sdf.parse(riqi).getTime()) {
						tj += banJi.getBanjiid() + ',';
					}
				}
				tongzhi.setTiaojian(tj.substring(0, tj.lastIndexOf(',')));
			}
			if ("2".equals(fabufanwei)) {
				tongzhi.setFabufanwei(2);
				String tj = "";
				String zhuanyes[] = request.getParameter("zhuanye").split(",");
				for (String string : zhuanyes) {
					Map<String,String> map  = new HashMap<>();
					map.put("zhuanyeid", string);
					map.put("xuexiaoid", yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString());
					List<BanJi> bjs = banjiService.selectBanJiByZhuanYeAndXueXiao(map);
					for (BanJi banJi : bjs) {
						Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid())
								.getRuxuenianfen();
						String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
						if (sdf.parse(biYeNianFen).getTime() > sdf.parse(riqi).getTime()) {
							FuDaoYuan fdy = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid());
							if(null == fdy){
								tj += banJi.getBanjiid().toString() + ',';
							}else{
								String bj[] = fdy.getBanjiid().split(",");
								for (String string2 : bj) {
									if(string2.equals(banJi.getBanjiid().toString())){
										tj += banJi.getBanjiid().toString() + ',';
									}
								}
							}
						}
					}
				}
				tongzhi.setTiaojian(tj.substring(0, tj.lastIndexOf(',')));
			}
			if ("3".equals(fabufanwei)) {
				tongzhi.setFabufanwei(3);
				String banjis = request.getParameter("banji");
				tongzhi.setTiaojian(banjis);
			}
			int i = neirongtongzhiService.insert(tongzhi);
			if (i > 0) {
				retInfo = "success";
			}
		} else {
			response.sendRedirect("logout");
			return null;
		}
		return retInfo;
	}

	@RequestMapping(value = "tongzhilist")
	public ModelAndView tongZhiList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("user") != null) {
			YongHu user = (YongHu) request.getSession().getAttribute("user");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("faburenid", user.getYonghuid());
			if ("fudaoyuan".equals(user.getStatus())) {
				paramMap.put("faburenleixing", 1);
			}
			if ("guanliyuan".equals(user.getStatus())) {
				paramMap.put("faburenleixing", 2);
			}
			List<NeiRongTongZhi> tongzhis = neirongtongzhiService.findByFaBuRenIDAndLeiXing(paramMap);
			for (NeiRongTongZhi tongZhi : tongzhis) {
				String tiaojian = "";
				if ("2".equals(String.valueOf(tongZhi.getFabufanwei()))) {
					String banjis[] = tongZhi.getTiaojian().split(",");
					List<String> zhuanyes = new ArrayList<>();
					for (String string : banjis) {
						BanJi bj = banjiService.selectByPrimaryKey(Integer.parseInt(string));
						ZhuanYe zy = new ZhuanYe();
						if(null != bj.getZhuanyeid()){
							zy = zhuanyeService.selectByPrimaryKey(bj.getZhuanyeid());
							if(!zhuanyes.contains(zy.getZhuanyemingcheng())){
								zhuanyes.add(zy.getZhuanyemingcheng());
							}
						}
					}
					for (String string : zhuanyes) {
						tiaojian += string + ",";
					}
				}
				if ("3".equals(String.valueOf(tongZhi.getFabufanwei()))) {
					String banjis[] = tongZhi.getTiaojian().split(",");
					for (String string : banjis) {
						BanJi bj = banjiService.selectByPrimaryKey(Integer.parseInt(string));
						tiaojian += bj.getBanjimingcheng() + ",";
					}
				}
				tongZhi.setTiaojian(tiaojian.substring(0, tiaojian.lastIndexOf(",")));
			}
			int count = tongzhis.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (count % pageSize == 0) {
				pages = (count / pageSize);
			}
			List<NeiRongTongZhi> tongZhiS = new ArrayList<>();
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						tongZhiS.add(tongzhis.get(i));
					}
					mv.addObject("tongzhis", tongZhiS);
				} else {
					for (int i = 0; i < 10; i++) {
						tongZhiS.add(tongzhis.get(i));
					}
					mv.addObject("tongzhis", tongZhiS);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								tongZhiS.add(tongzhis.get(i));
							}
							mv.addObject("tongzhis", tongZhiS);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								tongZhiS.add(tongzhis.get(i));
							}
							mv.addObject("tongzhis", tongZhiS);
						}
					} else if (page == pages) {
						for (int i = (page - 1) * 10; i < count; i++) {
							tongZhiS.add(tongzhis.get(i));
						}
						mv.addObject("tongzhis", tongZhiS);
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
		} else {
			response.sendRedirect("logout");
			return null;
		}
		mv.setViewName("common/tongzhilist");
		return mv;
	}

	@RequestMapping(value = "deltongzhi")
	@ResponseBody
	public String delTongZhi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String retInfo = "";
		if (request.getSession().getAttribute("user") != null) {
			YongHu user = (YongHu) request.getSession().getAttribute("user");
			String id = request.getParameter("id");
			NeiRongTongZhi tongZhi = neirongtongzhiService.selectByPrimaryKey(id);
			if (String.valueOf(tongZhi.getFaburenid()).equals(user.getYonghuid().toString())) {
				int i = neirongtongzhiService.delete(id);
				if (i > 0) {
					retInfo = "success";
				}
			} else {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("logout");
			return null;
		}
		return retInfo;
	}

	@RequestMapping(value = "xiugaitongzhi")
	public ModelAndView xiuGaiTongZhi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("user") != null) {
			YongHu user = (YongHu) request.getSession().getAttribute("user");
			String id = request.getParameter("id");
			NeiRongTongZhi tongZhi = neirongtongzhiService.selectByPrimaryKey(id);
			if (String.valueOf(tongZhi.getFaburenid()).equals(user.getYonghuid().toString())) {
				// 获取已经选中的条件
				List<ZhuanYe> zhuanYes = new ArrayList<>();
				List<BanJi> banJis = new ArrayList<>();
				if ("2".equals(String.valueOf(tongZhi.getFabufanwei()))) {
					String banjis[] = tongZhi.getTiaojian().split(",");
					for (String string : banjis) {
						BanJi bj = banjiService.selectByPrimaryKey(Integer.parseInt(string));
						if(null != bj.getZhuanyeid()){
							ZhuanYe zy = zhuanyeService.selectByPrimaryKey(bj.getZhuanyeid());
							if(zhuanYes.size() <= 0 || null == zhuanYes){
								zhuanYes.add(zy);
							}else{
								for (ZhuanYe string2 : zhuanYes) {
									if(! (String.valueOf(string2.getZhuanyeid()).equals(String.valueOf(zy.getZhuanyeid())))){
										zhuanYes.add(zy);
									}
								}
							}
						}
					}
				}
				if ("3".equals(String.valueOf(tongZhi.getFabufanwei()))) {
					String banjis[] = tongZhi.getTiaojian().split(",");
					for (String string : banjis) {
						BanJi bj = banjiService.selectByPrimaryKey(Integer.parseInt(string));
						banJis.add(bj);
					}
				}
				// 获取所有条件
				List<ZhuanYe> zhuanyes = new ArrayList<>();
				List<BanJi> banjis = new ArrayList<>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String nowTime = sdf.format(new Date());
				if (user.getStatus().equals("fudaoyuan")) {
					FuDaoYuan fudaoyuan = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid());
					String banjiids = fudaoyuan.getBanjiid();
					if (banjiids != null && !"".equals(banjiids)) {
						String banjiid[] = banjiids.split(",");
						for (String string : banjiid) {
							BanJi banJi = banjiService.selectByPrimaryKey(Integer.parseInt(string));

							Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid())
									.getRuxuenianfen();
							String biYeNianFen = (ruXueNianFen + 4) + "-07-30";
							if (sdf.parse(biYeNianFen).getTime() > sdf.parse(nowTime).getTime()) {
								if ((!banjis.isEmpty()) && banjis.size() > 0) {
									int tmp = 0;
									for (BanJi bj : banjis) {
										if (string.equals(bj.getBanjiid().toString())) {
											tmp = 1;
											break;
										}
									}
									if (tmp == 0) {
										banjis.add(banJi);
										ZhuanYe zy = zhuanyeService.selectByPrimaryKey(banJi.getZhuanyeid());
										if ((!zhuanyes.isEmpty()) && zhuanyes.size() > 0) {
											int temp = 0;
											for (ZhuanYe zhuanYe : zhuanyes) {
												if (String.valueOf(zhuanYe.getZhuanyeid())
														.equals(banJi.getZhuanyeid().toString())) {
													temp = 1;
													break;
												}
											}
											if (temp == 0) {
												zhuanyes.add(zy);
											}
										} else {
											zhuanyes.add(zy);
										}
									}
								} else {
									banjis.add(banJi);
									ZhuanYe zy = zhuanyeService.selectByPrimaryKey(banJi.getZhuanyeid());
									if ((!zhuanyes.isEmpty()) && zhuanyes.size() > 0) {
										int temp = 0;
										for (ZhuanYe zhuanYe : zhuanyes) {
											if (String.valueOf(zhuanYe.getZhuanyeid())
													.equals(banJi.getZhuanyeid().toString())) {
												temp = 1;
												break;
											}
										}
										if (temp == 0) {
											zhuanyes.add(zy);
										}
									} else {
										zhuanyes.add(zy);
									}
								}
							}
						}
					} else {
						response.sendRedirect("logout");
						return null;
					}
				}
				if (user.getStatus().equals("guanliyuan")) {
					String xuexiaoid = xueshengchuGuanliyuanService.selectByID(user.getYonghuid()).getXuexiaoid()
							.toString();
					zhuanyes = zhuanyeService.findByXueXiaoID(xuexiaoid);
					List<BanJi> banjiList = banjiService.findByXueXiaoID(xuexiaoid);
					for (BanJi banJi2 : banjiList) {
						Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi2.getRuxuenianfenid())
								.getRuxuenianfen();
						String biYeNianFen = (ruXueNianFen + 4) + "-07-30";
						if (sdf.parse(biYeNianFen).getTime() > sdf.parse(nowTime).getTime()) {
							if ((!banjis.isEmpty()) && banjis.size() > 0) {
								int tmp = 0;
								for (BanJi bj : banjis) {
									if (banJi2.getBanjiid().toString().equals(bj.getBanjiid().toString())) {
										tmp = 1;
										break;
									}
								}
								if (tmp == 0) {
									banjis.add(banJi2);
								}
							} else {
								banjis.add(banJi2);
							}
						}
					}
				}
				mv.addObject("banjis", banjis);
				mv.addObject("zhuanyes", zhuanyes);
				mv.addObject("zhuanYes", zhuanYes);
				mv.addObject("banJis", banJis);
				mv.addObject("tongzhi", tongZhi);
			} else {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("logout");
			return null;
		}
		mv.setViewName("common/xiugaitongzhi");
		return mv;
	}

	@RequestMapping(value = "chakantongzhi")
	public ModelAndView chaKanTongZhi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("user") != null) {
			YongHu user = (YongHu) request.getSession().getAttribute("user");
			String id = request.getParameter("id");
			NeiRongTongZhi tongZhi = neirongtongzhiService.selectByPrimaryKey(id);
			if (String.valueOf(tongZhi.getFaburenid()).equals(user.getYonghuid().toString())) {
				mv.addObject("tongzhi", tongZhi);
			} else {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("logout");
			return null;
		}
		mv.setViewName("common/chakantongzhi");
		return mv;
	}

	@RequestMapping(value = "updateneirongtongzhi")
	@ResponseBody
	public String updateneirongtongzhi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String retInfo = "";
		if (request.getSession().getAttribute("user") != null) {
			YongHu user = (YongHu) request.getSession().getAttribute("user");
			String id = request.getParameter("id");
			if (Util.isNumeric(id)) {
				NeiRongTongZhi tongZhi = neirongtongzhiService.selectByPrimaryKey(id);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String riqi = sdf.format(new Date());
				if (String.valueOf(tongZhi.getFaburenid()).equals(user.getYonghuid().toString())) {
					String title = request.getParameter("title");
					String content = request.getParameter("content");
					String fabufanwei = request.getParameter("fabufanwei");
					String istop = request.getParameter("istop");
					if ("1".equals(istop)) {
						tongZhi.setIstop(true);
					} else {
						tongZhi.setIstop(false);
					}
					if ("1".equals(fabufanwei)) {
						tongZhi.setFabufanwei(1);
						List<BanJi> bjs = banjiService.findByXueXiaoID(
								yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString());
						String tj = "";
						for (BanJi banJi : bjs) {
							Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid())
									.getRuxuenianfen();
							String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
							if (sdf.parse(biYeNianFen).getTime() > sdf.parse(riqi).getTime()) {
								tj += banJi.getBanjiid() + ',';
							}
						}
						tongZhi.setTiaojian(tj.substring(0, tj.lastIndexOf(',')));
					}
					if ("2".equals(fabufanwei)) {
						tongZhi.setFabufanwei(2);
						String tj = "";
						String zhuanyes[] = request.getParameter("zhuanye").split(",");
						for (String string : zhuanyes) {
							Map<String,String> map  = new HashMap<>();
							map.put("zhuanyeid", string);
							map.put("xuexiaoid", yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString());
							List<BanJi> bjs = banjiService.selectBanJiByZhuanYeAndXueXiao(map);
							for (BanJi banJi : bjs) {
								Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid())
										.getRuxuenianfen();
								String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
								if (sdf.parse(biYeNianFen).getTime() > sdf.parse(riqi).getTime()) {
									FuDaoYuan fdy = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid());
									if(null == fdy){
										tj += banJi.getBanjiid().toString() + ',';
									}else{
										String bj[] = fdy.getBanjiid().split(",");
										for (String string2 : bj) {
											if(string2.equals(banJi.getBanjiid().toString())){
												tj += banJi.getBanjiid().toString() + ',';
											}
										}
									}
								}
							}
						}
						tongZhi.setTiaojian(tj.substring(0, tj.lastIndexOf(',')));
					}
					if ("3".equals(fabufanwei)) {
						String banjis = request.getParameter("banji");
						tongZhi.setFabufanwei(3);
						tongZhi.setTiaojian(banjis);
					}
					tongZhi.setTitle(title);
					tongZhi.setContent(content);
					tongZhi.setFaburiqi(sdf.parse(sdf.format(new Date())));

					int i = neirongtongzhiService.update(tongZhi);
					if (i > 0) {
						retInfo = "success";
					}

				} else {
					response.sendRedirect("logout");
					return null;
				}
			} else {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("logout");
			return null;
		}
		return retInfo;
	}

}
