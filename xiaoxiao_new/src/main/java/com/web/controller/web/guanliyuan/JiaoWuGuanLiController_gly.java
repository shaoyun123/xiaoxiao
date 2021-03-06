package com.web.controller.web.guanliyuan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.web.annotation.LoginStatusAnnotation;
import com.web.model.BanJi;
import com.web.model.JiaoShi;
import com.web.model.JiaoXueLou;
import com.web.model.NianFen;
import com.web.model.SuSheLou;
import com.web.model.XiaoQu;
import com.web.model.XueSheng;
import com.web.model.XueShengChuGuanLiYuan;
import com.web.model.XueShengSuShe;
import com.web.model.XueXiao;
import com.web.model.YongHu;
import com.web.model.YuanXi;
import com.web.model.ZhuanYe;
import com.web.service.BanJiService;
import com.web.service.JiaoShiService;
import com.web.service.JiaoXueLouService;
import com.web.service.NianFenService;
import com.web.service.SuSheLouService;
import com.web.service.XiaoQuService;
import com.web.service.XueShengChuGuanLiYuanService;
import com.web.service.XueShengService;
import com.web.service.XueShengSuSheService;
import com.web.service.XueXiaoService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.service.ZhuanYeService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
@LoginStatusAnnotation(status = "guanliyuan")
public class JiaoWuGuanLiController_gly {

	@Autowired
	private YuanXiService yuanXiService;

	@Autowired
	private XiaoQuService xiaoQuService;

	@Autowired
	private ZhuanYeService zhuanYeService;

	@Autowired
	private XueXiaoService xueXiaoService;

	@Autowired
	private BanJiService banJiService;

	@Autowired
	private NianFenService nianFenService;

	@Autowired
	private XueShengService xueShengService;
	@Autowired
	private YongHuService yongHuService;

	@Autowired
	private XueShengSuSheService xueShengSuSheService;

	@Autowired
	private SuSheLouService suSheLouService;

	@Autowired
	private XueShengChuGuanLiYuanService xueShengChuGuanLiYuanService;

	@Autowired
	private JiaoXueLouService jiaoXueLouService;

	@Autowired
	private JiaoShiService jiaoShiService;

	@RequestMapping(value = "yuanxiliebiao") // 院系列表
	public ModelAndView yuanxiliebiao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			XueXiao xueXiao = xueXiaoService.selectByID((Integer) session.getAttribute("xuexiaoid"));
			List<YuanXi> yuanXis = yuanXiService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));
			int count = yuanXis.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (count % pageSize == 0) {
				pages = count / pageSize;
			}
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<YuanXi> yuanXis2 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						yuanXis2.add(yuanXis.get(i));
					}
					mv.addObject("yuanxis", yuanXis2);
				} else {
					for (int i = 0; i < 10; i++) {
						yuanXis2.add(yuanXis.get(i));
					}
					mv.addObject("yuanxis", yuanXis2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<YuanXi> yuanXis2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								yuanXis2.add(yuanXis.get(i));
							}
							mv.addObject("yuanxis", yuanXis2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								yuanXis2.add(yuanXis.get(i));
							}
							mv.addObject("yuanxis", yuanXis2);
						}
					} else if (page == pages) {
						List<YuanXi> yuanXis2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							yuanXis2.add(yuanXis.get(i));
						}
						mv.addObject("yuanxis", yuanXis2);
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
			mv.addObject("xuexiao", xueXiao);
			mv.setViewName("guanliyuan/yuanxiliebiao");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addyuanxi") // 添加院系
	public ModelAndView addyuanxi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			// List<ZhuanYe> zhuanYes=zhuanYeService.getZhuanYe();
			// mv.addObject("zhuanyes",zhuanYes);
			mv.setViewName("guanliyuan/addyuanxi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "saveyuanxi") // 保存添加
	@ResponseBody
	public String saveyuanxi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 检查session是否为空，如果不空保存要添加的院系
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out=response.getWriter();
			Integer xueXiaoId = (Integer) session.getAttribute("xuexiaoid");
			List<YuanXi> yuanXis = yuanXiService.getAllByxueXiaoID(xueXiaoId);
			String yuanXiMingCheng = request.getParameter("yuanximingcheng");
			YuanXi yuanXi = new YuanXi();
			// 检查院系是否重名
			for (int i = 0; i < yuanXis.size(); i++) {
				if (yuanXiMingCheng.equals(yuanXis.get(i).getYuanximingcheng())) {
					// out.print("<script>alert('与其它院系名称相同!');</script>");
					// out.print("<script>location='addyuanxi';</script>");
					return "chongming";
				}
			}
			// 数据库中插入新添加的院系
			yuanXi.setYuanximingcheng(yuanXiMingCheng);
			yuanXi.setXuexiaoid(xueXiaoId);
			int j = yuanXiService.insert(yuanXi);
			if (j != 0) {
				// out.print("<script>alert('添加成功!');</script>");
				return "success";
			} else {
				// out.print("<script>alert('添加失败!');</script>");
			}
			// response.sendRedirect("yuanxiliebiao");
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "modifyyuanxi") // 修改院系
	public ModelAndView modifyyuanxi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			String yuanXiId = request.getParameter("id");
			YuanXi yuanXi = yuanXiService.selectByPrimaryKey(Integer.parseInt(yuanXiId));
			mv.addObject("yuanxi", yuanXi);
			mv.setViewName("guanliyuan/modifyyuanxi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savemodifyyuanxi") // 保存修改
	@ResponseBody
	public String savemodifyyuanxi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out=response.getWriter();
			String yuanXiId = request.getParameter("id");
			YuanXi yuanXi = yuanXiService.selectByPrimaryKey(Integer.parseInt(yuanXiId));
			String yuanXiMingCheng = request.getParameter("yuanximingcheng");
			List<YuanXi> yuanXis = yuanXiService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));
			// 检查修改后的院系名称，是否与其他院系重名
			for (YuanXi yuanxi : yuanXis) {
				if (yuanXiMingCheng.equals(yuanxi.getYuanximingcheng())) {
					if (!yuanXiMingCheng.equals(yuanXi.getYuanximingcheng())) {
						// out.print("<script>alert('与其他院系重名!');</script>");
						// out.print("<script>location='yuanxiliebiao';</script>");
						return "chongming";
					}
				}
			}
			// 更新院系名称
			yuanXi.setYuanximingcheng(yuanXiMingCheng);
			int j = yuanXiService.updateByPrimaryKey(yuanXi);
			if (j != 0) {
				// out.print("<script>alert('修改成功!');</script>");
				// out.print("<script>location='yuanxiliebiao';</script>");
				return "success";
			} else {
				// out.print("<script>alert('修改失败!');</script>");
				// out.print("<script>location='yuanxiliebiao';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "delYuanXi") // 删除院系
	@ResponseBody
	public String delYuanXi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String yuanXiId = request.getParameter("CODE");
		List<Map<String, Integer>> yuanXiZhuanYes = zhuanYeService.getAllByYuanXiID(Integer.parseInt(yuanXiId));
		// 如果院系下面专业不为空，不能直接删除，需要先删除下属的专业
		if (yuanXiZhuanYes != null && yuanXiZhuanYes.size() != 0) {
			return "restrict";
		} else {
			int i = yuanXiService.deleteByPrimaryKey(Integer.parseInt(yuanXiId));
			if (i > 0) {
				return "success";
			} else {
				return "fail";
			}
		}
	}

	@RequestMapping(value = "chakanzhuanye") // 查看专业
	public ModelAndView chakanzhuanye(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			String yuanXiId = request.getParameter("yuanxiid");
			session.setAttribute("yuanxiid", yuanXiId);
			XueXiao xueXiao = xueXiaoService.selectByID((Integer) session.getAttribute("xuexiaoid"));
			YuanXi yuanXi = yuanXiService.selectByPrimaryKey(Integer.parseInt(yuanXiId));
			List<ZhuanYe> zhuanYes = new ArrayList<>();
			// if(yuanXi.getZhuanyedaima()!=null&&!"".equals(yuanXi.getZhuanyedaima())){
			// //院系不空时，查看院系专业
			// String[] zhuanYeDaiMa=yuanXi.getZhuanyedaima().split(",");
			// for(int i=0;i<zhuanYeDaiMa.length;i++){
			// ZhuanYe
			// zhuanYe=zhuanYeService.selectZhuanYeByDaiMa(zhuanYeDaiMa[i]);
			// zhuanYes.add(zhuanYe);
			// }
			// }
			List<Map<String, Integer>> yuanXiZhuanYes = zhuanYeService.getAllByYuanXiID(yuanXi.getYuanxiid());
			if (yuanXiZhuanYes != null && !"".equals(yuanXiZhuanYes)) {
				for (int i = 0; i < yuanXiZhuanYes.size(); i++) {
					ZhuanYe zhuanYe = zhuanYeService.selectByPrimaryKey(yuanXiZhuanYes.get(i).get("zhuanyeid"));
					zhuanYes.add(zhuanYe);
				}
			}
			int count = zhuanYes.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (count % pageSize == 0) {
				pages = count / pageSize;
			}
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<ZhuanYe> zhuanYes2 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						zhuanYes2.add(zhuanYes.get(i));
					}
					mv.addObject("zhuanyes", zhuanYes2);
				} else {
					for (int i = 0; i < 10; i++) {
						zhuanYes2.add(zhuanYes.get(i));
					}
					mv.addObject("zhuanyes", zhuanYes2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<ZhuanYe> zhuanYes2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								zhuanYes2.add(zhuanYes.get(i));
							}
							mv.addObject("zhuanyes", zhuanYes2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								zhuanYes2.add(zhuanYes.get(i));
							}
							mv.addObject("zhuanyes", zhuanYes2);
						}
					} else if (page == pages) {
						List<ZhuanYe> zhuanYes2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							zhuanYes2.add(zhuanYes.get(i));
						}
						mv.addObject("zhuanyes", zhuanYes2);
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
			mv.addObject("xuexiao", xueXiao);
			mv.addObject("yuanxi", yuanXi);
			mv.setViewName("guanliyuan/zhuanyeliebiao");
			return mv;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "addzhuanye") // 为院系添加专业
	public ModelAndView addzhuanye(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		// session不为空，为院系添加专业
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			String zhuanYeMingCheng = "学科门类" + "%";
			List<ZhuanYe> zhuanYes = zhuanYeService.getXueKeMenLei(zhuanYeMingCheng);
			List<Map<String, String>> maps = new ArrayList<>();
			if (zhuanYes != null && !"".equals(zhuanYes)) {
				for (int i = 0; i < zhuanYes.size(); i++) {
					Map<String, String> map = new HashMap<>();
					String mingcheng = zhuanYes.get(i).getZhuanyemingcheng().substring(5);
					map.put("mingcheng", mingcheng);
					map.put("id", String.valueOf(zhuanYes.get(i).getZhuanyeid()));
					maps.add(map);
				}
			}
			YuanXi yuanXi = yuanXiService
					.selectByPrimaryKey(Integer.parseInt((String) session.getAttribute("yuanxiid")));
			// if(yuanXi.getZhuanyedaima()!=null&&!"".equals(yuanXi.getZhuanyedaima())){
			// String[] daiMa=yuanXi.getZhuanyedaima().split(",");
			// List<String> daiMaList=new ArrayList<>();
			// for(int i=0;i<zhuanYes.size();i++){
			// daiMaList.add(zhuanYes.get(i).getDaima());
			// }
			// //所有可选专业代码除去院系中已有的专业代码,只能选择院系中没有的其它专业
			// for(int i=0;i<daiMa.length;i++){
			// if(daiMaList.contains(daiMa[i])){
			// daiMaList.remove(daiMa[i]);
			// }
			// }
			// //专业代码不为空，把专业添加到新的专业列表中
			// if(daiMaList.size()!=0){
			// for(int i=0;i<daiMaList.size();i++){
			// ZhuanYe
			// zhuanYe=zhuanYeService.selectZhuanYeByDaiMa(daiMaList.get(i));
			// newZhuanYes.add(zhuanYe);
			// }
			// }
			// mv.addObject("yuanxi",yuanXi);
			// mv.addObject("zhuanyes",newZhuanYes);
			// mv.setViewName("guanliyuan/addzhuanye");
			// }
			// //院系下面没有专业，不必去重
			// else{
			mv.addObject("yuanxi", yuanXi);
			mv.addObject("xuekemenleis", maps);
			mv.setViewName("guanliyuan/addzhuanye");
			// }
			return mv;
		}
		// session为空，登出
		else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "getxueke")
	@ResponseBody
	public Object getxueke(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String daima = request.getParameter("CODE");
		String daimas = daima + "%";
		String mingcheng1 = "学科门类" + "%";
		String mingcheng2 = "%" + "类";
		List<ZhuanYe> zhuanYes = zhuanYeService.getXueKe(daimas, mingcheng1, mingcheng2);
		Map<String, Object> map = new HashMap<>();
		if (zhuanYes != null && zhuanYes.size() != 0) {
			// for(int i=0;i<zhuanYes.size();i++){
			// Map<String,String> map = new HashMap<>();
			// map.put("id", String.valueOf(zhuanYes.get(i).getZhuanyeid()));
			// map.put("mingcheng",zhuanYes.get(i).getZhuanyemingcheng());
			// maps.add(map);
			// }
			map.put("data", zhuanYes);
			map.put("status", "success");
			return JSON.toJSON(map);
		} else {
			map.put("status", "none");
			return JSON.toJSON(map);
		}
	}

	@RequestMapping(value = "selectZhuanYeByXueKe")
	@ResponseBody
	public Object selectZhuanYeByXueKe(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String daima = request.getParameter("CODE");
		String daimas = daima + "%";
		String mingcheng1 = "学科门类" + "%";
		String mingcheng2 = "%" + "类";
		List<ZhuanYe> zhuanYes = zhuanYeService.selectZhuanYeByXueKe(daimas, mingcheng1, mingcheng2);
		Map<String, Object> map = new HashMap<>();
		if (zhuanYes != null && zhuanYes.size() != 0) {
			// for(int i=0;i<zhuanYes.size();i++){
			// Map<String,String> map = new HashMap<>();
			// map.put("id", String.valueOf(zhuanYes.get(i).getZhuanyeid()));
			// map.put("mingcheng",zhuanYes.get(i).getZhuanyemingcheng());
			// maps.add(map);
			// }
			map.put("data", zhuanYes);
			map.put("status", "success");
			return JSON.toJSON(map);
		} else {
			map.put("status", "none");
			return JSON.toJSON(map);
		}
	}

	@RequestMapping(value = "selectzhuanye") // 得到专业
	@ResponseBody
	public Object getzhuanye(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("CODE");
		Map<String, Object> map = new HashMap<>();
		List<ZhuanYe> zhuanYes = new ArrayList<>();
		List<Map<String, Integer>> yuanXiZhuanYes = zhuanYeService.getAllByYuanXiID(Integer.parseInt(id));
		if (yuanXiZhuanYes != null && yuanXiZhuanYes.size() != 0) {
			for (int i = 0; i < yuanXiZhuanYes.size(); i++) {
				ZhuanYe zhuanYe = zhuanYeService.selectByPrimaryKey(yuanXiZhuanYes.get(i).get("zhuanyeid"));
				zhuanYes.add(zhuanYe);
			}
			map.put("data", zhuanYes);
			map.put("status", "success");
			return JSON.toJSON(map);
		} else {
			map.put("status", "none");
			return JSON.toJSON(map);
		}

	}

	@RequestMapping(value = "savezhuanye") // 保存添加
	@ResponseBody
	public String savezhuanye(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out=response.getWriter();
			YuanXi yuanXi = yuanXiService
					.selectByPrimaryKey(Integer.parseInt((String) session.getAttribute("yuanxiid")));
			String[] zhuanYeId = request.getParameterValues("zhuanyeid");
			// 如果专业不为空，保存要添加的专业
			if (zhuanYeId != null && !"".equals(zhuanYeId)) {
				for (int j = 0; j < zhuanYeId.length; j++) {
					Map<String, Integer> yuanXiZhuanYe = new HashMap<>();
					yuanXiZhuanYe.put("zhuanyeid", Integer.parseInt(zhuanYeId[j]));
					yuanXiZhuanYe.put("yuanxiid", yuanXi.getYuanxiid());
					yuanXiZhuanYe.put("zhuangtai", 1);
					int i = zhuanYeService.insertYuanXiZhuanYe(yuanXiZhuanYe);
					if (i != 0) {
						// out.print("<script>alert('添加成功!');</script>");
						// out.print("<script>location='yuanxiliebiao';</script>");
						return "success";
					} else {
						// out.print("<script>alert('添加失败!');</script>");
						// out.print("<script>location='yuanxiliebiao';</script>");
					}
					// response.sendRedirect("chakanzhuanye?yuanxiid="+session.getAttribute("yuanxiid"));
				}
			} else {
				// out.print("<script>alert('请选择专业!');</script>");
				// out.print("<script>location='addzhuanye';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "delZhuanYe") // 删除某院系下的专业
	@ResponseBody
	public String delZhuanYe(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String zhuanYeId = request.getParameter("CODE");
		String yuanXiId = request.getParameter("yuanxiid");
		Map<String, Integer> yuanXiZhuanYe = zhuanYeService.selectByZhuanYeId(Integer.parseInt(zhuanYeId),yuanXiId);
		List<NianFen> nianFens = nianFenService.getNianFen();
		List<Map<String, Object>> maps = new ArrayList<>();
		for (int i = 0; i < nianFens.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			List<BanJi> banJis = banJiService.getAllByYuanXiIDAndZhuanYeDaiMaAndRuXueNianFen(Integer.parseInt(yuanXiId),
					zhuanYeId.toString(), nianFens.get(i).getRuxuenianfenid());
			if (banJis.size() > 0 && banJis != null) {
				map.put("banji", banJis);
				maps.add(map);
			}
		}
		if (maps != null && maps.size() != 0) {
			return "restrict";
		} else {
			int j = zhuanYeService.deleteYuanXiZhuanYeByPrimaryKey(yuanXiZhuanYe.get("yxzyid"));
			if (j != 0) {
				return "success";
			} else {
				return "fail";
			}
		}
	}

	@RequestMapping(value = "chakanbanji") // 班级列表
	public ModelAndView chakanbanji(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			String yuanXiId = request.getParameter("yuanxiid");
			YuanXi yuanXi = yuanXiService.selectByPrimaryKey(Integer.parseInt(yuanXiId));
			session.setAttribute("yuanxiid", yuanXiId);
			List<ZhuanYe> zhuanYes2 = new ArrayList<>();
			List<Map<String, Integer>> yuanXiZhuanYes = zhuanYeService.getAllByYuanXiID(Integer.parseInt(yuanXiId));
			if (yuanXiZhuanYes != null && yuanXiZhuanYes.size() != 0) {
				for (int i = 0; i < yuanXiZhuanYes.size(); i++) {
					ZhuanYe zhuanYe = zhuanYeService.selectByPrimaryKey(yuanXiZhuanYes.get(i).get("zhuanyeid"));
					zhuanYes2.add(zhuanYe);
				}
			}
			List<NianFen> nianFens = nianFenService.getNianFen();
			String ruXueNianFenId = "";
			if (((String) session.getAttribute("ruxuenianfenid")) != null) {
				ruXueNianFenId = ((String) session.getAttribute("ruxuenianfenid"));
			} else { // 如果session中没有该属性的值，从nianFens列表中选取一个
				Calendar calendar = Calendar.getInstance();
				for (int j = 0; j < nianFens.size(); j++) {
					if (nianFens.get(j).getRuxuenianfen() == calendar.get(Calendar.YEAR) - 1) {
						ruXueNianFenId = nianFens.get(j).getRuxuenianfenid().toString();
						session.setAttribute("ruxuenianfenid", ruXueNianFenId);
						break;
					} else {
						continue;
					}
				}
			}
			XueXiao xueXiao = xueXiaoService.selectByID((Integer) session.getAttribute("xuexiaoid"));
			List<BanJi> banJis = banJiService.getAllByyuanXiIDAndruXueNianFen(Integer.parseInt(yuanXiId),
					Integer.parseInt(ruXueNianFenId));
			NianFen nianFen = nianFenService.selectByPrimaryKey(Integer.parseInt(ruXueNianFenId));
			int count = banJis.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (count % pageSize == 0) {
				pages = count / pageSize;
			}
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<BanJi> banJis2 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						banJis2.add(banJis.get(i));
					}
					mv.addObject("banjis", banJis2);
				} else {
					for (int i = 0; i < 10; i++) {
						banJis2.add(banJis.get(i));
					}
					mv.addObject("banjis", banJis2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<BanJi> banJis2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								banJis2.add(banJis.get(i));
							}
							mv.addObject("banjis", banJis2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								banJis2.add(banJis.get(i));
							}
							mv.addObject("banjis", banJis2);
						}
					} else if (page == pages) {
						List<BanJi> banJis2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							banJis2.add(banJis.get(i));
						}
						mv.addObject("banjis", banJis2);
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
			mv.addObject("xuexiao", xueXiao);
			mv.addObject("yuanxi", yuanXi);
			mv.addObject("zhuanyeselective", zhuanYes2);
			mv.addObject("nianfen", nianFen);
			mv.addObject("nianfens", nianFens);
			mv.setViewName("guanliyuan/banjiliebiao");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chaxunbanji") // 查询班级
	public ModelAndView chaxunbanji(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			String ruXueNianFenId = request.getParameter("ruxuenianfenid");
			session.setAttribute("ruxuenianfenid", ruXueNianFenId);
			String zhuanyeid = request.getParameter("zhuanyeid");
			session.setAttribute("zhuanyeid", zhuanyeid);
			XueXiao xueXiao = xueXiaoService.selectByID((Integer) session.getAttribute("xuexiaoid"));
			YuanXi yuanXi = yuanXiService
					.selectByPrimaryKey(Integer.parseInt((String) session.getAttribute("yuanxiid")));
			List<ZhuanYe> zhuanYes2 = new ArrayList<>();
			List<BanJi> banJis = new ArrayList<>();
			List<Map<String, Integer>> yuanXiZhuanYes = zhuanYeService.getAllByYuanXiID(yuanXi.getYuanxiid());
			if (yuanXiZhuanYes != null && yuanXiZhuanYes.size() != 0) {
				for (int i = 0; i < yuanXiZhuanYes.size(); i++) {
					ZhuanYe zhuanYe = zhuanYeService.selectByPrimaryKey(yuanXiZhuanYes.get(i).get("zhuanyeid"));
					zhuanYes2.add(zhuanYe);
				}
			}

			if (zhuanyeid != null && !"".equals(zhuanyeid)) {
				banJis = banJiService.getAllByYuanXiIDAndZhuanYeDaiMaAndRuXueNianFen(yuanXi.getYuanxiid(), zhuanyeid,
						Integer.parseInt(ruXueNianFenId));
			} else {
				banJis = banJiService.getAllByyuanXiIDAndruXueNianFen(yuanXi.getYuanxiid(),
						Integer.parseInt(ruXueNianFenId));
			}
			List<NianFen> nianFens = nianFenService.getNianFen();
			NianFen nianFen = nianFenService.selectByPrimaryKey(Integer.parseInt(ruXueNianFenId));
			int count = banJis.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (count % pageSize == 0) {
				pages = count / pageSize;
			}
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<BanJi> banJis2 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						banJis2.add(banJis.get(i));
					}
					mv.addObject("banjis", banJis2);
				} else {
					for (int i = 0; i < 10; i++) {
						banJis2.add(banJis.get(i));
					}
					mv.addObject("banjis", banJis2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<BanJi> banJis2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								banJis2.add(banJis.get(i));
							}
							mv.addObject("banjis", banJis2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								banJis2.add(banJis.get(i));
							}
							mv.addObject("banjis", banJis2);
						}
					} else if (page == pages) {
						List<BanJi> banJis2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							banJis2.add(banJis.get(i));
						}
						mv.addObject("banjis", banJis2);
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
			mv.addObject("xuexiao", xueXiao);
			mv.addObject("yuanxi", yuanXi);
			mv.addObject("zhuanyeselective", zhuanYes2);
			mv.addObject("nianfens", nianFens);
			mv.addObject("nianfen", nianFen);
			mv.setViewName("guanliyuan/banjiliebiao");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addBanJi") // 添加班级
	public ModelAndView addBanJi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			YuanXi yuanXi = yuanXiService
					.selectByPrimaryKey(Integer.parseInt((String) session.getAttribute("yuanxiid")));
			List<ZhuanYe> zhuanYes = new ArrayList<>();
			List<Map<String, Integer>> yuanXiZhuanYes = zhuanYeService.getAllByYuanXiID(yuanXi.getYuanxiid());
			if (yuanXiZhuanYes != null && yuanXiZhuanYes.size() != 0) {
				for (int i = 0; i < yuanXiZhuanYes.size(); i++) {
					ZhuanYe zhuanYe = zhuanYeService.selectByPrimaryKey(yuanXiZhuanYes.get(i).get("zhuanyeid"));
					zhuanYes.add(zhuanYe);
				}
			}
			List<YuanXi> yuanXis = yuanXiService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));
			List<NianFen> nianFens = nianFenService.getNianFen();
			mv.addObject("yuanxis", yuanXis);
			mv.addObject("yuanxi1", yuanXi);
			mv.addObject("zhuanyes", zhuanYes);
			mv.addObject("nianfens", nianFens);
			mv.setViewName("guanliyuan/addbanji");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savebanji") // 保存添加
	@ResponseBody
	public String savebanji(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out=response.getWriter();
			String qianZhui = request.getParameter("qianzhui");
			String houZhui = request.getParameter("houzhui");
			String yuanxiid = request.getParameter("yuanxiid");
			String zhuanyeid = request.getParameter("zhuanyeid");
			String leixing = request.getParameter("leixing");
			String ruXueNianFenId = request.getParameter("ruxuenianfenid");
			session.setAttribute("ruxuenianfenid", ruXueNianFenId);
			String ruXueNianFen = nianFenService.selectByPrimaryKey(Integer.parseInt(ruXueNianFenId)).getRuxuenianfen()
					.toString().substring(2);
			Integer shuLiang = Integer.parseInt(request.getParameter("shuliang"));
			// String string="banJiMingCheng
			// like'"+qianZhui+ruXueNianFen+"%'"+houZhui;
			String string = qianZhui + ruXueNianFen + "%" + houZhui;
			List<BanJi> banJis2 = banJiService.getAllByYuanXiIdAndRuXueNianFenIdAndBanJiMingCheng(
					Integer.parseInt(yuanxiid), Integer.parseInt(ruXueNianFenId), string);
			Integer kaiShi = banJis2.size();// kaiShi代表特定院系，学年下的班级数量，添加新班级时，决定班级序号
			String mingCheng = null;
			List<BanJi> banJis = new ArrayList<>();
			List<String> mingChengList = new ArrayList<>();
			if (zhuanyeid != null && !"".equals(zhuanyeid)) {
				banJis = banJiService.getAllByYuanXiIDAndZhuanYeDaiMaAndRuXueNianFen(Integer.parseInt(yuanxiid),
						zhuanyeid, Integer.parseInt(ruXueNianFenId));
			} else {
				banJis = banJiService.getAllByyuanXiIDAndruXueNianFen(Integer.parseInt(yuanxiid),
						Integer.parseInt(ruXueNianFenId));
			}
			int temp = 0;
			// 批量添加班级
			for (int i = 0; i < banJis.size(); i++) {
				mingChengList.add(banJis.get(i).getBanjimingcheng());
			}
			// kaiShi+shuLiang小于10时 ，添加班级
			if (kaiShi + shuLiang < 10) {
				for (int k = kaiShi + 1; k <= kaiShi + shuLiang; k++) {// 班级名称从kaShi+1开始循环生成
					mingCheng = qianZhui + ruXueNianFen + 0 + k + houZhui;// 班级名称的组成
					if (mingChengList.contains(mingCheng)) { // 班级名称查重
						// out.print("<script>alert('与其他班级名称相同!');</script>");
						continue;
					}
					// 班级名称唯一，没有该班级，插入该班级
					BanJi banJi = new BanJi();
					if (zhuanyeid != null && !"".equals(zhuanyeid)) {
						banJi.setZhuanyeid(Integer.parseInt(zhuanyeid));
					}
					banJi.setLeixing(Integer.parseInt(leixing));
					banJi.setBanjimingcheng(mingCheng);
					banJi.setRuxuenianfenid(Integer.parseInt(ruXueNianFenId));
					banJi.setYuanxiid(Integer.parseInt(yuanxiid));
					int j = banJiService.insert(banJi);
					if (j != 0) {
						session.setAttribute("yuanxiid", yuanxiid);
						temp = 1;
						continue;
					} else {
//						out.print("<script>alert('添加失败!');</script>");
						continue;
					}
				}
			}
			// kaiShi+shuLiang不能大于100
			else if (kaiShi + shuLiang >= 100) {
//				out.print("<script>alert('请输入1到100之间的整数!');</script>");
//				out.print("<script>location='addbanji';</script>");
				return "zhengshu_";
			}
			// kaiShi+shuLiang介于10到100之间，插入班级
			else {
				// 总数量不小于10，k的初始值小于9，分两部分插入
				if (kaiShi <= 8) {
					// k小于10的部分
					for (int k = kaiShi + 1; k <= 9; k++) {
						mingCheng = qianZhui + ruXueNianFen + 0 + k + houZhui;
						if (mingChengList.contains(mingCheng)) {
//							out.print("<script>alert('与其他班级名称相同!');</script>");
							continue;
						}
						BanJi banJi = new BanJi();
						if (zhuanyeid != null && !"".equals(zhuanyeid)) {
							banJi.setZhuanyeid(Integer.parseInt(zhuanyeid));
						}
						banJi.setLeixing(Integer.parseInt(leixing));
						banJi.setBanjimingcheng(mingCheng);
						banJi.setRuxuenianfenid(Integer.parseInt(ruXueNianFenId));
						banJi.setYuanxiid(Integer.parseInt(yuanxiid));
						int j = banJiService.insert(banJi);
						if (j != 0) {
							session.setAttribute("yuanxiid", yuanxiid);
							temp = 1;
							continue;
						} else {
//							out.print("<script>alert('添加失败!');</script>");
							continue;
						}
					}
					// k不小于10的部分
					for (int m = 10; m <= kaiShi + shuLiang; m++) {
						mingCheng = qianZhui + ruXueNianFen + m + houZhui;
						if (mingChengList.contains(mingCheng)) {
//							out.print("<script>alert('与其他班级名称相同!');</script>");
							continue;
						}
						BanJi banJi = new BanJi();
						if (zhuanyeid != null && !"".equals(zhuanyeid)) {
							banJi.setZhuanyeid(Integer.parseInt(zhuanyeid));
						}
						banJi.setLeixing(Integer.parseInt(leixing));
						banJi.setBanjimingcheng(mingCheng);
						banJi.setRuxuenianfenid(Integer.parseInt(ruXueNianFenId));
						banJi.setYuanxiid(Integer.parseInt(yuanxiid));
						int j = banJiService.insert(banJi);
						if (j != 0) {
							session.setAttribute("yuanxiid", yuanxiid);
							temp = 1;
							continue;
						} else {
//							out.print("<script>alert('添加失败!');</script>");
							continue;
						}
					}
				} else {
					// k的初始值不小于10,最大值小于100
					for (int m = kaiShi + 1; m <= kaiShi + shuLiang; m++) {
						mingCheng = qianZhui + ruXueNianFen + m + houZhui;
						if (mingChengList.contains(mingCheng)) {
//							out.print("<script>alert('与其他班级名称相同!');</script>");
							continue;
						}

						BanJi banJi = new BanJi();
						if (zhuanyeid != null && !"".equals(zhuanyeid)) {
							banJi.setZhuanyeid(Integer.parseInt(zhuanyeid));
						}
						banJi.setLeixing(Integer.parseInt(leixing));
						banJi.setBanjimingcheng(mingCheng);
						banJi.setRuxuenianfenid(Integer.parseInt(ruXueNianFenId));
						banJi.setYuanxiid(Integer.parseInt(yuanxiid));
						int j = banJiService.insert(banJi);
						if (j != 0) {
							session.setAttribute("yuanxiid", yuanxiid);
							temp = 1;
							continue;
						} else {
//							out.print("<script>alert('添加失败!');</script>");
							continue;
						}
					}
				}
			}
//			response.sendRedirect("chakanbanji?yuanxiid=" + session.getAttribute("yuanxiid"));
			if(temp == 1){
				return "success_"+yuanxiid;
			}

		} else {
			response.sendRedirect("login");
		}
		return null;

	}

	@RequestMapping(value = "modifybanji") // 修改班级
	public ModelAndView modifybanji(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			String banJiId = request.getParameter("id");
			BanJi banJi = banJiService.selectByPrimaryKey(Integer.parseInt(banJiId));
			// ZhuanYe
			// zhuanYe2=zhuanYeService.selectZhuanYeByDaiMa(banJi.getZhuanyedaima());
			ZhuanYe zhuanYe2 = zhuanYeService.selectByPrimaryKey(banJi.getZhuanyeid());
			YuanXi yuanXi = yuanXiService
					.selectByPrimaryKey(Integer.parseInt((String) session.getAttribute("yuanxiid")));
			List<NianFen> nianFens = nianFenService.getNianFen();
			List<ZhuanYe> zhuanYes = new ArrayList<>();
			List<Map<String, Integer>> yuanXiZhuanYes = zhuanYeService.getAllByYuanXiID(yuanXi.getYuanxiid());
			if (yuanXiZhuanYes != null && yuanXiZhuanYes.size() != 0) {
				for (int i = 0; i < yuanXiZhuanYes.size(); i++) {
					ZhuanYe zhuanYe = zhuanYeService.selectByPrimaryKey(yuanXiZhuanYes.get(i).get("zhuanyeid"));
					zhuanYes.add(zhuanYe);
				}
			}
			mv.addObject("zhuanye", zhuanYe2);
			mv.addObject("zhuanyes", zhuanYes);
			mv.addObject("nianfens", nianFens);
			mv.addObject("banji", banJi);
			mv.setViewName("guanliyuan/modifybanji");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savemodifybanji") // 保存修改
	@ResponseBody
	public String savemodifybanji(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String banJiId = request.getParameter("id");
			BanJi banJi = banJiService.selectByPrimaryKey(Integer.parseInt(banJiId));
			String banJiMingCheng = request.getParameter("banjimingcheng");
			String ruXueNianFenId = request.getParameter("ruxuenianfen");
			String zhuanyeid = request.getParameter("zhuanyeid");
			// 获取特定专业下的全部班级名称
			List<BanJi> banJis = new ArrayList<>();
			if (zhuanyeid != null && !"".equals(zhuanyeid)) {
				banJis = banJiService.getAllByYuanXiIDAndZhuanYeDaiMaAndRuXueNianFen(
						Integer.parseInt((String) session.getAttribute("yuanxiid")), zhuanyeid,
						Integer.parseInt(ruXueNianFenId));
				banJi.setZhuanyeid(Integer.parseInt(zhuanyeid));
			} else {
				banJis = banJiService.getAllByyuanXiIDAndruXueNianFen(
						Integer.parseInt((String) session.getAttribute("yuanxiid")), Integer.parseInt(ruXueNianFenId));
			}

			List<String> mingChengList = new ArrayList<>();
			for (int i = 0; i < banJis.size(); i++) {
				mingChengList.add(banJis.get(i).getBanjimingcheng());
			}
			// 班级名称查重
			if (mingChengList.contains(banJiMingCheng) && !banJiMingCheng.equals(banJi.getBanjimingcheng())) {
//				out.print("<script>alert('与其他班级名称相同!');</script>");
				return "chongming_"+zhuanyeid+"_"+ruXueNianFenId;
			}
			banJi.setBanjimingcheng(banJiMingCheng);
			banJi.setRuxuenianfenid(Integer.parseInt(ruXueNianFenId));
			int j = banJiService.updateByPrimaryKeySelective(banJi);
			if (j != 0) {
//				out.print("<script>alert('修改成功!');</script>");
				session.setAttribute("ruxuenianfenid", ruXueNianFenId);
				return "success_"+zhuanyeid+"_"+ruXueNianFenId;
			} else {
//				out.print("<script>alert('修改失败!');</script>");
				return "fail_"+zhuanyeid+"_"+ruXueNianFenId;
			}
//			response.sendRedirect("chaxunbanji?zhuanyeid=" + zhuanyeid + "&ruxuenianfenid=" + ruXueNianFenId);

		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "delBanJi") // 删除班级
	@ResponseBody
	public String delBanJi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String banJiId = request.getParameter("CODE");
		List<XueSheng> xueShengs = xueShengService.getAllByBanJiID(Integer.parseInt(banJiId));
		// 班级下面有学生，则不能直接删除班级
		if (xueShengs.size() != 0 && xueShengs != null) {
			return "restrict";
		}
		// 班级下面没有一个学生，删除该班级
		else {
			int i = banJiService.deleteByPrimaryKey(Integer.parseInt(banJiId));
			if (i > 0) {
				return "success";
			} else {
				return "fail";
			}
		}

	}

	@RequestMapping(value = "chakanxuesheng") // 学生列表
	public ModelAndView chakanxuesheng(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			String banJiId = request.getParameter("banjiid");
			session.setAttribute("banjiid", banJiId);
			BanJi banJi = banJiService.selectByPrimaryKey(Integer.parseInt(banJiId));
			YuanXi yuanXi = yuanXiService.selectByPrimaryKey(banJi.getYuanxiid());
			// ZhuanYe
			// zhuanYe=zhuanYeService.selectZhuanYeByDaiMa((String)session.getAttribute("zhuanyedaima"));
			List<XueSheng> xueShengs = xueShengService.getAllByBanJiID(Integer.parseInt(banJiId));
			int count = xueShengs.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (count % pageSize == 0) {
				pages = count / pageSize;
			}
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<XueSheng> xueShengs2 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						xueShengs2.add(xueShengs.get(i));
					}
					mv.addObject("xueshengs", xueShengs2);
				} else {
					for (int i = 0; i < 10; i++) {
						xueShengs2.add(xueShengs.get(i));
					}
					mv.addObject("xueshengs", xueShengs2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<XueSheng> xueShengs2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								xueShengs2.add(xueShengs.get(i));
							}
							mv.addObject("xueshengs", xueShengs2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								xueShengs2.add(xueShengs.get(i));
							}
							mv.addObject("xueshengs", xueShengs2);
						}
					} else if (page == pages) {
						List<XueSheng> xueShengs2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							xueShengs2.add(xueShengs.get(i));
						}
						mv.addObject("xueshengs", xueShengs2);
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
			mv.addObject("yuanxi", yuanXi);
			// mv.addObject("zhuanye", zhuanYe);
			mv.addObject("banji", banJi);
			mv.setViewName("guanliyuan/xueshengliebiao");
			return mv;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "addxuesheng") // 添加学生
	public ModelAndView addxuesheng(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			List<YuanXi> yuanXis = yuanXiService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));
			List<NianFen> nianFens = nianFenService.getNianFen();
			YuanXi yuanXi = yuanXiService
					.selectByPrimaryKey(Integer.parseInt((String) session.getAttribute("yuanxiid")));
			// ZhuanYe
			// zhuanYe=zhuanYeService.selectZhuanYeByDaiMa((String)session.getAttribute("zhuanyedaima"));
			BanJi banJi = banJiService.selectByPrimaryKey(Integer.parseInt((String) session.getAttribute("banjiid")));
			mv.addObject("nianfens", nianFens);
			mv.addObject("yuanxis", yuanXis);
			mv.addObject("yuanxi", yuanXi);
			// mv.addObject("zhuanye",zhuanYe);
			mv.addObject("banji", banJi);
			mv.setViewName("guanliyuan/addxuesheng");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "getZhuanYe") // 得到专业
	@ResponseBody
	public Object getZhuanYe(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String yuanXiId = request.getParameter("CODE");
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(Integer.parseInt(yuanXiId));
		List<ZhuanYe> zhuanYes = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		if (yuanXi.getZhuanyedaima() != null && !"".equals(yuanXi.getZhuanyedaima())) {
			String[] zhuanYeDaiMa = yuanXi.getZhuanyedaima().split(",");
			for (int i = 0; i < zhuanYeDaiMa.length; i++) {
				ZhuanYe zhuanYe = zhuanYeService.selectZhuanYeByDaiMa(zhuanYeDaiMa[i]);
				zhuanYes.add(zhuanYe);
			}
			map.put("data", zhuanYes);
			map.put("status", "success");
		} else {
			map.put("status", "non");
		}
		return JSON.toJSON(map);
	}

	@RequestMapping(value = "getBanJi") // 得到班级
	@ResponseBody
	public Object getBanJi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		// String zhuanYeId=request.getParameter("CODE").split(",")[0];
		String ruXueNianFenId = request.getParameter("CODE").split(",")[0];
		String yuanXiId = request.getParameter("CODE").split(",")[1];
		List<BanJi> banJis = new ArrayList<>();
		// if(zhuanYeId!=null&&!"".equals(zhuanYeId)){
		// banJis=banJiService.getAllByYuanXiIDAndZhuanYeDaiMaAndRuXueNianFen(Integer.parseInt(yuanXiId),zhuanYeId,Integer.parseInt(ruXueNianFenId));
		// }
		// else{
		banJis = banJiService.getAllByyuanXiIDAndruXueNianFen(Integer.parseInt(yuanXiId),
				Integer.parseInt(ruXueNianFenId));
		// }
		return JSON.toJSON(banJis);
	}

	@RequestMapping(value = "savexuesheng") // 保存添加
	@ResponseBody
	public String savexuesheng(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String banJiId = request.getParameter("banjiid");
			String xingMing = request.getParameter("xingming");
			String xueHao = request.getParameter("xuehao");
			// String daiMa=request.getParameter("daima");
			Integer xueXiaoId = (Integer) session.getAttribute("xuexiaoid");
			String xueXiao_XueHao = xueXiaoId.toString() + "_" + xueHao;
			List<XueSheng> xueShengList = xueShengService.getAllXueSheng(xueXiaoId + "_" + "%");
			List<String> xueHaoList = new ArrayList<>();
			for (int i = 0; i < xueShengList.size(); i++) {
				xueHaoList.add(xueShengList.get(i).getXuehao());
			}
			int temp = 0;
			if (xueHaoList.contains(xueHao)) {
				XueSheng xueSheng = xueShengService.selectXueShengByXueXiaoXueHao(xueXiao_XueHao);
				xueSheng.setBanjiid(Integer.parseInt(banJiId));
				xueSheng.setXingming(xingMing);
				int i = xueShengService.updateByPrimaryKeySelective(xueSheng);
				if (i != 0) {
//					out.print("<script>alert('已有该学生，更新学生信息成功!');</script>");
					session.setAttribute("banjiid", banJiId);
					temp = 1;
				} else {
//					out.print("<script>alert('已有该学生，更新学生信息失败!');</script>");
					temp = 2;
				}
			} else {
				String yongHuMing = xueXiaoId + xueHao;
				String miMaMd5 = xueHao;
				String dianHua = request.getParameter("dianhua");
				String email = request.getParameter("youxiang");
				XueSheng xueSheng = new XueSheng();
				xueSheng.setBanjiid(Integer.parseInt(banJiId));
				xueSheng.setXingming(xingMing);
				xueSheng.setXuehao(xueHao);
				xueSheng.setMimamd5(miMaMd5);
				xueSheng.setYonghuming(yongHuMing);
				xueSheng.setYouxiang(email);
				xueSheng.setDianhua(dianHua);
				xueSheng.setIsbanzhang(false);
				xueSheng.setXuexiaoXuehao(xueXiao_XueHao);
				int j = xueShengService.insert(xueSheng);
				if (j != 0) {
//					out.print("<script>alert('添加成功!');</script>");
					session.setAttribute("banjiid", banJiId);
					temp = 3;
				} else {
//					out.print("<script>alert('添加失败!');</script>");
				}
			}
//			response.sendRedirect("chakanxuesheng?banjiid=" + session.getAttribute("banjiid"));
			if(temp == 1){
				return "gxsuccess_"+banJiId;
			}
			if(temp == 2){
				return "gxfail_";
			}
			if(temp == 3){
				return "success_"+banJiId;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "modifyxuesheng") // 修改学生信息
	public ModelAndView modifyxuesheng(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			String xueShengId = request.getParameter("id");
			XueSheng xueSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(xueShengId));
			BanJi banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
			YuanXi yuanXi = yuanXiService.selectByPrimaryKey(banJi.getYuanxiid());
			List<YuanXi> yuanXis = yuanXiService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));
			// ZhuanYe
			// zhuanYe=zhuanYeService.selectZhuanYeByDaiMa((String)session.getAttribute("zhuanyedaima"));
			List<NianFen> nianFens = nianFenService.getNianFen();
			NianFen nianFen = nianFenService.selectByPrimaryKey(banJi.getRuxuenianfenid());
			List<BanJi> banJis = banJiService.getAllByyuanXiIDAndruXueNianFen(yuanXi.getYuanxiid(),
					banJi.getRuxuenianfenid());
			List<XiaoQu> xiaoQus = xiaoQuService.getAllByxueXiaoID((Integer) (session.getAttribute("xuexiaoid")));
			mv.addObject("yuanxis", yuanXis);
			mv.addObject("yuanxi", yuanXi);
			// mv.addObject("zhuanye", zhuanYe);
			mv.addObject("nianfens", nianFens);
			mv.addObject("nianfen", nianFen);
			mv.addObject("banjis", banJis);
			mv.addObject("banji", banJi);
			mv.addObject("xuesheng", xueSheng);
			mv.addObject("xiaoqus", xiaoQus);
			mv.setViewName("guanliyuan/modifyxuesheng");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savemodifyxuesheng") // 保存修改
	@ResponseBody
	public String savemodifyxuesheng(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String xueShengId = request.getParameter("id");
			String banJiId = request.getParameter("banjiid");
			String suSheId = request.getParameter("susheid");
			String xingming = request.getParameter("xingming");
			XueSheng xueSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(xueShengId));
			xueSheng.setBanjiid(Integer.parseInt(banJiId));
			xueSheng.setSusheid(Integer.parseInt(suSheId));
			xueSheng.setXingming(xingming);
			int j = xueShengService.updateByPrimaryKey(xueSheng);
			if (j != 0) {
//				out.print("<script>alert('修改成功!');</script>");
				session.setAttribute("banjiid", banJiId);
				return "success_"+banJiId;
			} else {
//				out.print("<script>alert('修改失败!');</script>");
			}
//			response.sendRedirect("chakanxuesheng?banjiid=" + session.getAttribute("banjiid"));
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "getsushelou")
	@ResponseBody
	public Object getsushelou(HttpServletRequest request, HttpServletResponse response) {

		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {

				System.out.println("(⊙o⊙)…程序好像出错了!");
			}
			return null;
		}
		if (!Util.isGuanLiYuan(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				System.out.println("(⊙o⊙)…程序好像出错了!");
			}
			return null;
		}
		String xiaoQuId = request.getParameter("CODE");
		if (xiaoQuId == null) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {

				System.out.println("呃...程序好像出错了!");
			}
			return null;
		}
		List<SuSheLou> suSheLous = suSheLouService.getAllByXiaoQuId(Integer.parseInt(xiaoQuId));
		return JSON.toJSON(suSheLous);
	}

	@RequestMapping(value = "getsushe")
	@ResponseBody
	public Object getsushe(HttpServletRequest request, HttpServletResponse response) {

		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {

				System.out.println("(⊙o⊙)…程序好像出错了!");
			}
			return null;
		}
		if (!Util.isGuanLiYuan(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				System.out.println("(⊙o⊙)…程序好像出错了!");
			}
			return null;
		}
		String sushelouid = request.getParameter("CODE");
		if (sushelouid == null) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {

				System.out.println("呃...程序好像出错了!");
			}
			return null;
		}
		List<XueShengSuShe> suShes = xueShengSuSheService.getAllBySuSheLouId(Integer.parseInt(sushelouid));
		return JSON.toJSON(suShes);
	}

	@RequestMapping(value = "updatepassword")
	@ResponseBody
	public String updatepassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String xueShengID = request.getParameter("xueshengid");
		String xuexiaoxuehao = request.getParameter("xuexiaoxuehao");
		String xueHao = request.getParameter("xuehao");
		if (!Util.isNumeric(xueShengID)) {
			response.sendRedirect("logout");
			return null;
		}
		int i = xueShengService.updatePassWdByID(xueHao, Integer.parseInt(xueShengID));
		if (i != 0) {
			Map<String, Object> parmMap = new HashMap<String, Object>();
			parmMap.put("password", xueHao);
			parmMap.put("xuexiaoxuehao", xuexiaoxuehao);
			parmMap.put("status", "xuesheng");
			int c = yongHuService.updatePasswordKaoShiById(parmMap);
			if (c > 0) {
				String shunxuhao = System.currentTimeMillis() + "";
				String dizhiliebiao = "2,3";
				String tongbuneirong = "update xuesheng set miMaMD5 = '" + xueHao + "' where xuexiao_xuehao = '"
						+ xuexiaoxuehao + "' ";
				String leimingcheng = "com.ccbupt.kaoshi.dao.XueSheng";
				Map<String, Object> pMap = new HashMap<String, Object>();
				pMap.put("shunxuhao", shunxuhao);
				pMap.put("dizhiliebiao", dizhiliebiao);
				pMap.put("tongbuneirong", tongbuneirong);
				pMap.put("leimingcheng", leimingcheng);
				yongHuService.insertKaoshiTongBuFaSong(pMap);

				// String leimingcheng = "com.ccbupt.kaoshi.dao.YongHu";
			}
			return "success";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "delXueSheng") // 删除学生
	@ResponseBody
	public String delXueSheng(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String xueShengId = request.getParameter("CODE");
		int j = xueShengService.deleteByPrimaryKey(Integer.parseInt(xueShengId));
		if (j > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	/**
	 * @discription 批量添加学生 页面选择院系和入学年份 获取上传文件，解析，并返回前台显示 前台页面点击确认导入按钮，开始导入学生
	 * @return xueshenglist,存入session
	 * @param
	 */

	@RequestMapping(value = "getyuanxiandnianfen") // 得到院系和班级
	public ModelAndView getyuanxiandnianfen(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		List<YuanXi> yuanXis = yuanXiService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));
		List<NianFen> nianFens = nianFenService.getNianFen();
		mv.addObject("yuanxis", yuanXis);
		mv.addObject("nianfens", nianFens);
		mv.setViewName("guanliyuan/importxuesheng");
		return mv;

	}

	@RequestMapping(value = "importFile") // 获取上传文件
	@ResponseBody
	public Object importFile(HttpServletRequest request, HttpServletResponse response) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 获取导入的指定格式的excel文件，把解析的excel文件的数据，返回给前台
		try {
			if (!multipartResolver.isMultipart(request)) {
				response.sendRedirect("logout");
				return null;
			}
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile myfile = multipartRequest.getFile("file");// 获取文件
			if (myfile == null || myfile.isEmpty()) {
				response.sendRedirect("logout");
				return null;
			}
			String s = myfile.getOriginalFilename();
			String endName = s.substring(s.lastIndexOf("."), s.length());
			Map<String, Object> map = new HashMap<>();
			if (endName.equals(".txt") || endName.equals(".xlsx")) {// 获取指定格式的文件
				String filename = UUID.randomUUID().toString().replaceAll("-", "");
				String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/excel/" + filename
						+ endName;
				File localFile = new File(filePath);
				localFile.setWritable(true, false);
				localFile.setExecutable(true, false);
				localFile.setReadable(true, false);
				if (!localFile.exists()) {
					localFile.mkdirs();
				}
				myfile.transferTo(localFile);
				List<Object> dataList = this.parseFile(filePath);// 解析excel文件
				request.getSession().setAttribute("tempdata", dataList);
				if (dataList == null) {
					map.put("status", "null");
					return JSON.toJSON(map);
				} else {
					map.put("status", "success");
					map.put("data", dataList);// xueShenglist为经过处理的正确的数据
					return JSON.toJSON(map);
				}
			} else {
				map.put("status", "format");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 解析要上传的文件
	public List<Object> parseFile(String filepath) {
		List<Object> xueShenglist = new ArrayList<>();
		File media = new File(filepath);
		if (media.isFile() && media.exists()) {
			InputStreamReader isr = null;
			try {
				isr = new InputStreamReader(new FileInputStream(media), "Unicode");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader br = new BufferedReader(isr);
			String lineTxt = null;
			try {
				while ((lineTxt = br.readLine()) != null) {
					System.out.println(lineTxt);
					String[] item = lineTxt.split("	");
					xueShenglist.add(item);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("文件不存在!");
		}
		return xueShenglist;
	}

	@RequestMapping(value = "importXueSheng") // 导入学生
	@ResponseBody
	public Object importXueSheng(HttpServletRequest request, HttpServletResponse response) {
		Integer xuexiaoid = (Integer) request.getSession().getAttribute("xuexiaoid");
		// String yuanXiId=request.getParameter("yuanxiid");
		String ruXueNianFenId = request.getParameter("ruxuenianfenid");
		// 获取excel文件
		try {
			@SuppressWarnings("unchecked")
			List<String[]> objects =(List<String[]>) request.getSession().getAttribute("tempdata");
			List<XueSheng> xueShengs = new ArrayList<>();
			for (int i = 0; i < objects.size(); i++) {
				XueSheng xueSheng = new XueSheng();
				xueSheng.setYuanximingcheng(objects.get(i)[0]);
				xueSheng.setBanjimingcheng(objects.get(i)[1]);
				xueSheng.setXuehao(objects.get(i)[2]);
				xueSheng.setXingming(objects.get(i)[3]);
				if (objects.get(i).length == 4) {
					xueSheng.setMimamd5("");
					xueSheng.setYouxiang("");
				}
				if (objects.get(i).length == 5) {
					xueSheng.setMimamd5(objects.get(i)[4]);
					xueSheng.setYouxiang("");

				}
				if (objects.get(i).length == 6) {
					xueSheng.setMimamd5(objects.get(i)[4]);
					xueSheng.setYouxiang(objects.get(i)[5]);
				}
				xueShengs.add(xueSheng);
			}
			List<String> xueHaoList = new ArrayList<>();
			Map<String, Object> map = new HashMap<>();
			List<XueSheng> xueShengListError = new ArrayList<>();
			List<XueSheng> xueShengListTrue = new ArrayList<>();
			String xueXiaoXueHao = xuexiaoid.toString() + "_" + "%";
			List<XueSheng> xueShengList = xueShengService.getAllXueSheng(xueXiaoXueHao);
			// 获取学校学生的全部学号
			for (int i = 0; i < xueShengList.size(); i++) {
				xueHaoList.add(xueShengList.get(i).getXuehao());
			}
			// 检查读入的每个学生的信息
			for (XueSheng xuesheng : xueShengs) {
				// 检查学生所在院系是否存在
				YuanXi yuanXi = null;
				while (true) {
					yuanXi = yuanXiService.selectByYuanXiMingChengAndXueXiaoId(xuesheng.getYuanximingcheng(),
							xuexiaoid);
					if (yuanXi != null) {
						break;
					}
					YuanXi yuanXi2 = new YuanXi();
					yuanXi2.setXuexiaoid(xuexiaoid);
					yuanXi2.setYuanximingcheng(xuesheng.getYuanximingcheng());
					int i = yuanXiService.insert(yuanXi2);
				}
				// 检查学生所在的班级是否存在
				BanJi banJi = null;
				while (true) {
					banJi = banJiService.selectByBanJiMingChengAndYuanXiIdAndRuXueNianFenId(
							xuesheng.getBanjimingcheng(), yuanXi.getYuanxiid(), Integer.parseInt(ruXueNianFenId));
					if (banJi != null) {
						break;
					}
					// 没有班级，插入班级
					BanJi banJi2 = new BanJi();
					banJi2.setYuanxiid(yuanXi.getYuanxiid());
					banJi2.setRuxuenianfenid(Integer.parseInt(ruXueNianFenId));
					banJi2.setBanjimingcheng(xuesheng.getBanjimingcheng());
					int i = banJiService.insert(banJi2);
				}
				// 如果excel表里面的学号和数据库中的相同，更新该学生的班级，姓名，用户名
				if (xueHaoList.contains(xuesheng.getXuehao())) {
					XueSheng xueSheng = xueShengService
							.selectXueShengByXueXiaoXueHao(xuexiaoid.toString() + "_" + xuesheng.getXuehao());
					xueSheng.setBanjiid(banJi.getBanjiid());
					xueSheng.setXingming(xuesheng.getXingming());
					xueSheng.setYonghuming(xuexiaoid.toString() + xuesheng.getXuehao());
					if (xuesheng.getMimamd5() != null && !"".equals(xuesheng.getMimamd5())) {

						xueSheng.setMimamd5(xuesheng.getMimamd5());
					} else {
						if (xueSheng.getMimamd5() == null || "".equals(xueSheng.getMimamd5())) {
							xueSheng.setMimamd5(xuesheng.getXuehao());
						}
					}
					xueSheng.setYouxiang(xuesheng.getYouxiang());
					int i = xueShengService.updateByPrimaryKey(xueSheng);
					if (i != 0) {// 更新成功
						xueShengListTrue.add(xuesheng);
					} else {// 更新失败
						xueShengListError.add(xuesheng);
						continue;
					}
				} else {
					XueSheng xueSheng = new XueSheng();
					xueSheng.setBanjiid(banJi.getBanjiid());
					xueSheng.setXingming(xuesheng.getXingming());
					xueSheng.setXuehao(xuesheng.getXuehao());
					xueSheng.setYonghuming(xuexiaoid + xuesheng.getXuehao());
					if (xuesheng.getMimamd5() != null && !"".equals(xuesheng.getMimamd5())) {
						xueSheng.setMimamd5(xuesheng.getMimamd5());
					} else {
						xueSheng.setMimamd5(xuesheng.getXuehao());
					}
					xueSheng.setYouxiang(xuesheng.getYouxiang());
					xueSheng.setXuexiaoXuehao(xuexiaoid + "_" + xuesheng.getXuehao());
					xueSheng.setIsbanzhang(false);
					int k = xueShengService.insertSelective(xueSheng);
					if (k != 0) {// 学生信息插入成功!
						xueShengListTrue.add(xuesheng);// excel表里的该条记录，插入到更新成功的列表里，前台展示插入成功的数据
					} else {// 学生信息插入失败!
						xueShengListTrue.add(xuesheng);// excel表里的该条记录，插入到更新失败的列表里，前台展示插入失败的数据，以及错误信息
						continue;
					}
				}
			}
			map.put("xueshenglisttrue", xueShengListTrue);
			map.put("xueshenglisterror", xueShengListError);
			return JSON.toJSON(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "getxiaoqu") // 获得校区
	public ModelAndView getxiaoqu(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		List<XiaoQu> xiaoQus = xiaoQuService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));

		mv.addObject("xiaoqus", xiaoQus);
		mv.setViewName("guanliyuan/importsushe");
		return mv;

	}

	@RequestMapping(value = "importSuShe") // 导入宿舍
	@ResponseBody
	public Object importSuShe(HttpServletRequest request, HttpServletResponse response) {
		Integer xuexiaoid = (Integer) request.getSession().getAttribute("xuexiaoid");
		String xiaoQuId = request.getParameter("xiaoquid");
		// 获取excel文件
		try {
			@SuppressWarnings("unchecked")
			List<String[]> objects = (List<String[]>) request.getSession().getAttribute("tempdata");
			List<XueSheng> xueShengs = new ArrayList<>();
			for (int i = 0; i < objects.size(); i++) {
				XueSheng xueSheng = new XueSheng();
				xueSheng.setXuehao(objects.get(i)[0]);
				xueSheng.setSushelouming(objects.get(i)[1]);
				xueSheng.setMenpaihao(objects.get(i)[2]);
				xueShengs.add(xueSheng);
			}
			List<String> xueHaoList = new ArrayList<>();
			Map<String, Object> map = new HashMap<>();
			List<Map<String, Object>> xueShengListError = new ArrayList<>();
			List<XueSheng> xueShengListTrue = new ArrayList<>();
			String xueXiaoXueHao = xuexiaoid.toString() + "_" + "%";
			List<XueSheng> xueShengList = xueShengService.getAllXueSheng(xueXiaoXueHao);
			// 获取学校学生的全部学号
			for (int i = 0; i < xueShengList.size(); i++) {
				xueHaoList.add(xueShengList.get(i).getXuehao());
			}
			// 检查读入的每个学生的信息
			for (XueSheng xuesheng : xueShengs) {
				// 如果excel表里面的学号和数据库中的相同，更新该学生的宿舍信息
				if (xueHaoList.contains(xuesheng.getXuehao())) {
					// 检查宿舍楼是否存在
					SuSheLou suSheLou = null;
					while (true) {
						suSheLou = suSheLouService.selectBySuSheLouMingAndXiaoQuId(xuesheng.getSushelouming(),
								Integer.parseInt(xiaoQuId));
						if (suSheLou != null) {
							break;
						}
						// 没有宿舍楼，插入宿舍楼
						SuSheLou suSheLou2 = new SuSheLou();
						suSheLou2.setXiaoQuId(Integer.parseInt(xiaoQuId));
						suSheLou2.setMingCheng(xuesheng.getSushelouming());
						suSheLou2.setZhuangTai(1);
						int i = suSheLouService.insert(suSheLou2);
					}
					// 检查宿舍是否存在
					XueShengSuShe xueShengSuShe = null;
					while (true) {
						xueShengSuShe = xueShengSuSheService.selectXueShengSuSheBySuSheLouIdAndMenPaiHao(
								suSheLou.getSuSheLouId(), xuesheng.getMenpaihao());
						// 有该宿舍，跳过
						if (xueShengSuShe != null) {
							break;
						}
						// 没有宿舍，插入宿舍
						XueShengSuShe xueShengSuShe2 = new XueShengSuShe();
						xueShengSuShe2.setSuSheLouId(suSheLou.getSuSheLouId());
						xueShengSuShe2.setMenPaiHao(xuesheng.getMenpaihao());
						xueShengSuShe2.setLouCeng(Integer.parseInt(xuesheng.getMenpaihao().substring(0, 1)));
						xueShengSuShe2.setZhuangTai(1);
						int i = xueShengSuSheService.insert(xueShengSuShe2);
					}
					XueSheng xueSheng = xueShengService
							.selectXueShengByXueXiaoXueHao(xuexiaoid.toString() + "_" + xuesheng.getXuehao());
					xueSheng.setSusheid(xueShengSuShe.getSuSheId());
					int i = xueShengService.updateByPrimaryKey(xueSheng);
					if (i != 0) {// 更新成功
						xueShengListTrue.add(xuesheng);
					} else {// 更新失败
						Map<String, Object> xsMap = new HashMap<>();
						xsMap.put("xuehao", xuesheng.getXuehao());
						xsMap.put("sushelouming", xuesheng.getSushelouming());
						xsMap.put("menpaihao", xuesheng.getMenpaihao());
						xsMap.put("liyou", "更新数据库失败!");
						xueShengListError.add(xsMap);
						continue;
					}
				} else {
					Map<String, Object> xsMap = new HashMap<>();
					xsMap.put("xuehao", xuesheng.getXuehao());
					xsMap.put("sushelouming", xuesheng.getSushelouming());
					xsMap.put("menpaihao", xuesheng.getMenpaihao());
					xsMap.put("liyou", "学号不存在，没有该学生!");
					xueShengListError.add(xsMap);
					continue;
				}
			}
			map.put("xueshenglisttrue", xueShengListTrue);
			map.put("xueshenglisterror", xueShengListError);
			return JSON.toJSON(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "importuser") //
	public ModelAndView importuser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mView = new ModelAndView();
		if (!Util.checkSession(request)) {
			response.sendRedirect("logout");
			return null;
		}
		mView.setViewName("guanliyuan/importyonghu");
		return mView;
	}

	@RequestMapping(value = "importYongHu") // 导入用户
	@ResponseBody
	public Object importYongHu(HttpServletRequest request, HttpServletResponse response) {
		Integer xuexiaoid = (Integer) request.getSession().getAttribute("xuexiaoid");
		// String yuanXiId=request.getParameter("yuanxiid");
		// String ruXueNianFenId = request.getParameter("ruxuenianfenid");
		// 获取excel文件
		try {
			@SuppressWarnings("unchecked")
			List<String[]> objects = (List<String[]>) request.getSession().getAttribute("tempdata");
			List<YongHu> yongHus = new ArrayList<>();
			for (int i = 0; i < objects.size(); i++) {
				YongHu yongHu = new YongHu();
				String yuanximingcheng = (objects.get(i)[0]);
				YuanXi yuanXi = null;
				while (true) {
					yuanXi = yuanXiService.selectByYuanXiMingChengAndXueXiaoId(yuanximingcheng, xuexiaoid);
					if (yuanXi != null) {
						break;
					}
					YuanXi yuanXi2 = new YuanXi();
					yuanXi2.setXuexiaoid(xuexiaoid);
					yuanXi2.setYuanximingcheng(yuanximingcheng);
					int j = yuanXiService.insert(yuanXi2);
				}
				yongHu.setYuanxiid(yuanXi.getYuanxiid());
				yongHu.setJueseid(Integer.parseInt((objects.get(i)[1])));
				yongHu.setYonghuming(objects.get(i)[2]);
				yongHu.setYonghuxingming(objects.get(i)[3]);
				yongHu.setYuanximingcheng(yuanximingcheng);
				if (objects.get(i).length == 4) {
					yongHu.setMimamd5("000000");
					yongHu.setYonghuyouxiang("");
				}
				if (objects.get(i).length == 5) {
					if (objects.get(i)[4] != null && !"".equals(objects.get(i)[4])) {
						yongHu.setMimamd5(objects.get(i)[4]);
					} else {
						yongHu.setMimamd5("000000");
					}

					yongHu.setYonghuyouxiang("");

				}
				if (objects.get(i).length == 6) {
					if (objects.get(i)[4] != null && !"".equals(objects.get(i)[4])) {
						yongHu.setMimamd5(objects.get(i)[4]);
					} else {
						yongHu.setMimamd5("000000");
					}
					yongHu.setYonghuyouxiang(objects.get(i)[5]);
				}
				yongHus.add(yongHu);
			}

			Map<String, Object> map = new HashMap<>();
			List<YongHu> yongHuListError = new ArrayList<>();
			List<YongHu> yongHuListTrue = new ArrayList<>();
			if (yongHus != null && !"".equals(yongHus)) {
				for (int i = 0; i < yongHus.size(); i++) {
					int j = yongHuService.insert(yongHus.get(i));
					if (j != 0) {
						yongHuListTrue.add(yongHus.get(i));
					} else {
						yongHuListError.add(yongHus.get(i));
					}
				}
			} else {

			}
			map.put("yonghulisttrue", yongHuListTrue);
			map.put("yonghulisterror", yongHuListError);
			return JSON.toJSON(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "addyonghu")
	public ModelAndView addyonghu(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mView = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			String xuexiaoid = session.getAttribute("xuexiaoid").toString();
			List<YuanXi> yuanXis = new ArrayList<>();
			List<YuanXi> yuanXiss = yuanXiService.getAllByxueXiaoID(Integer.parseInt(xuexiaoid));
			for (int i = 0; i < yuanXiss.size(); i++) {
				if (yuanXiss.get(i) != null && !"".equals(yuanXiss.get(i))) {
					yuanXis.add(yuanXiss.get(i));
				}
			}
			mView.addObject("yuanxis", yuanXis);
			mView.setViewName("guanliyuan/addyonghu");
		} else {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		return mView;
	}

	@RequestMapping(value = "saveyonghu")
	public ModelAndView saveyonghu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.checkSession(request)) {
			response.sendRedirect("logout");
			return null;
		}
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		ModelAndView mView = new ModelAndView();
		String yuanxiid = request.getParameter("yuanxiid");
		String[] jueseid = request.getParameterValues("jueseid");
		String yonghuming = request.getParameter("yonghuming");
		String yonghuxingming = request.getParameter("yonghuxingming");
		String mima = request.getParameter("mima");
		String yonghuyouxiang = request.getParameter("yonghuyouxiang");
		YongHu yongHu = new YongHu();
		yongHu.setYuanxiid(Integer.parseInt(yuanxiid));
		if (jueseid.length == 1) {
			yongHu.setJueseid(Integer.parseInt(jueseid[0]));
		} else {
			yongHu.setJueseid(Integer.parseInt(jueseid[0]));
			String string = "";
			for (int i = 1; i < jueseid.length; i++) {
				string += jueseid[i] + ",";
			}
			yongHu.setQitajuese(string.substring(0, string.length() - 1));
		}
		yongHu.setYonghuming(yonghuming);
		yongHu.setYonghuxingming(yonghuxingming);
		yongHu.setMimamd5(mima);
		yongHu.setYonghuyouxiang(yonghuyouxiang);
		int j = yongHuService.insert(yongHu);
		if (j != 0) {
			out.print("<script>alert('添加成功!');</script>");
			out.print("<script>location='addyonghu';</script>");
		} else {
			out.print("<script>alert('添加失败');</script>");
			out.print("<script>location='addyonghu';</script>");
		}
		return null;
	}
	@RequestMapping(value = "searchxuesheng")
	public ModelAndView searchxuesheng(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.checkSession(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView = new ModelAndView();
		HttpSession session = request.getSession();
		String xuexiaoid = session.getAttribute("xuexiaoid").toString();
		String xuehao = request.getParameter("xuehao");
		String xuexiaoxuehao = xuexiaoid+"_" +xuehao ;
		XueSheng xSheng = xueShengService.selectXueShengByXueXiaoXueHao(xuexiaoxuehao);
		BanJi banJi = null;
		if(xSheng!=null) {
			banJi = banJiService.selectByPrimaryKey(xSheng.getBanjiid());
		}
		mView.addObject("xuesheng",xSheng);
		mView.addObject("banji",banJi);
		mView.addObject("xuehao",xuehao);
		mView.setViewName("guanliyuan/resetpasswd");
		return mView;
	}
	
	@RequestMapping(value = "searchxueshengs")
	public ModelAndView searchxueshengs(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.checkSession(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView = new ModelAndView();
		HttpSession session = request.getSession();
		String xuexiaoid = session.getAttribute("xuexiaoid").toString();
		String xuehao = request.getParameter("xuehao");
		String xuexiaoxuehao = xuexiaoid+"_" +xuehao ;
		XueSheng xSheng = xueShengService.selectXueShengByXueXiaoXueHao(xuexiaoxuehao);
		BanJi banJi = null;
		if(xSheng!=null) {
			banJi = banJiService.selectByPrimaryKey(xSheng.getBanjiid());
		}
		mView.addObject("xuesheng",xSheng);
		mView.addObject("banji",banJi);
		mView.addObject("xuehao",xuehao);
		mView.setViewName("guanliyuan/yanzhengyouxiang");
		return mView;
	}
	@ResponseBody
	@RequestMapping(value = "yanzhengyouxiang")
	public JSONObject yanzhengyouxiang(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.checkSession(request)) {
			response.sendRedirect("logout");
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String xushengid = request.getParameter("xueshengid");
		XueSheng xueSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(xushengid));
		if(xueSheng!=null) {
			xueSheng.setCheckcodefor("");
		}
		try {
			int i = xueShengService.updateByPrimaryKey(xueSheng);
			if(i!=0) {
				jsonObject.put("status", "success");
			}
		} catch (Exception e) {
			jsonObject.put("status", "fail");
		}
		
		return jsonObject;
	}
}
