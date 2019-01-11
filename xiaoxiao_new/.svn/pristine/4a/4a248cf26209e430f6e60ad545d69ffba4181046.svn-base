package com.web.controller.web.shuji;

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

import org.aspectj.weaver.patterns.HasMemberTypePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xml.internal.security.keys.content.RetrievalMethod;
import com.web.annotation.LoginStatusAnnotation;
import com.web.model.BanJi;
import com.web.model.BeiWL;
import com.web.model.ChaQinAnPai;
import com.web.model.FuDaoYuan;
import com.web.model.HuoDong;
import com.web.model.JCSJ;
import com.web.model.JiaoShi;
import com.web.model.JiaoXueLou;
import com.web.model.KeCheng;
import com.web.model.KeChengJiBen;
import com.web.model.TiXing;
import com.web.model.XiaoQu;
import com.web.model.XiaoXiFaSong;
import com.web.model.XueQi;
import com.web.model.XueSheng;
import com.web.model.YiJianXiang;
import com.web.model.YongHu;
import com.web.service.BanJiService;
import com.web.service.BeiWLService;
import com.web.service.ChaQinService;
import com.web.service.FuDaoYuanService;
import com.web.service.HuoDongService;
import com.web.service.JCSJService;
import com.web.service.JiGuangService;
import com.web.service.JiaoShiService;
import com.web.service.JiaoXueLouService;
import com.web.service.JieCiFangAnService;
import com.web.service.KeChengJiBenService;
import com.web.service.KeChengService;
import com.web.service.TiXingService;
import com.web.service.XiaoQuService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.service.YiJianXiangService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
@LoginStatusAnnotation(status = "shuji")
public class RiChengController_sj {

	@Autowired
	private KeChengService kechengService;
	@Autowired
	private JCSJService jcsjService;
	@Autowired
	private JieCiFangAnService jiecifanganService;
	@Autowired
	private XueQiService xueqiService;
	@Autowired
	private XueShengService xueshengService;
	@Autowired
	private BanJiService banjiService;
	@Autowired
	private YuanXiService yuanxiService;
	@Autowired
	private FuDaoYuanService fudaoyuanService;
	@Autowired
	private HuoDongService huodongService;
	@Autowired
	private YongHuService yonghuService;
	@Autowired
	private BeiWLService beiwlService;
	@Autowired
	private XiaoQuService xiaoquService;
	@Autowired
	private JiaoShiService jiaoshiService;
	@Autowired
	private KeChengJiBenService kechengjibenService;
	@Autowired
	private TiXingService tixingService;
	@Autowired
	private JiGuangService jiGuangService;
	@Autowired
	private JiaoXueLouService jiaoXueLouService;
	@Autowired
	private YiJianXiangService yiJianXiangService;
	@Autowired
	private ChaQinService chaQinService;

	@RequestMapping(value = "show_student_sj") // 根据班级，显示班级所有学生
	@ResponseBody
	public List<XueSheng> show_student(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			YongHu user = (YongHu) session.getAttribute("user");
			String banjiid = request.getParameter("CODE");
			List<XueSheng> xueShengs = new ArrayList<>();
			if (banjiid.equals("")) {
				String banjiids = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid();
				String banji[] = banjiids.split(",");
				for (String string : banji) {
					List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs2) {
						xueShengs.add(xueSheng);
					}
				}
			} else {
				xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(banjiid));
			}
			return xueShengs;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "wodericheng_sj") // 我的日程
	public ModelAndView wodericheng_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			mv.addObject("t", new Date().getTime());
			mv.setViewName("shuji/wodericheng");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping("chaxunrichengbydate_sj")
	@ResponseBody
	public JSONObject chaxunrichengbydate(HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		JSONObject json = new JSONObject();
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isShuJi(request)) {
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
			List<BeiWL> beiwanglus = new ArrayList<>();
			List<TiXing> tixings = new ArrayList<>();
			List<YiJianXiang> yijianxiangs = new ArrayList<>();
			if (user != null) {
				paramMap.put("renid", user.getYonghuid().toString());
				paramMap.put("renleixing", "4");
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
									&& map.get("leiXing").toString().equals("4")) {
								huoDong.setZhuangtai(2);
								i = 1;
								break;
							}
						}
					}
					if (canyuren.size() > 0 && canyuren != null) {
						for (Map<String, Object> map : canyuren) {
							if (map.get("canYuRenID").toString().equals(user.getYonghuid().toString())
									&& map.get("leiXing").toString().equals("4")) {
								huoDong.setZhuangtai(1);
								i = 1;
								break;
							}
						}
					}
					if (huoDong.getTianjiarenid().toString().equals(user.getYonghuid().toString()) && huoDong.getTianjiarenleixing().toString().equals("4")) {
						huoDong.setZhuangtai(1);
						i = 1;
					}
					if (i == 0) {
						huoDong.setZhuangtai(0);
					}
				}

				paramMap.put("renkelaoshiid", user.getYonghuid().toString());
				paramMap.put("shangkeriqi", "%" + riqi + ",%");

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
				// if (!"".equals(beiWL.getJieshouren()) &&
				// null!=beiWL.getJieshouren()) {
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
				returnMap.put("huodongs", huodongs);
				returnMap.put("beiwanglus", beiwanglus);
				returnMap.put("tixings", tixings);
				returnMap.put("yijianxiangs", yijianxiangs);

			} else {
				returnMap.put("huodongs", huodongs);
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

	@RequestMapping("richeng_huodong_sj")
	public ModelAndView riChengHuoDong(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isShuJi(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		int page = 1;
		int pages = 1;
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			if (user != null) {
				String id = request.getParameter("id");
				String zhuangtai = request.getParameter("zhuangtai");
				String qufen = request.getParameter("qufen");
				HuoDong huodong = null;
				List<HuoDong> huodongs = new ArrayList<>();
				if (id == null || id == "") {
					huodong = new HuoDong();
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
						huodongs.add(huodong);
						mv.addObject("huodongs", huodongs);
					} else {
						mv.addObject("huodongs", huodongs);
					}
					mv.addObject("qufen", qufen);
					mv.addObject("yonghuid", user.getYonghuid());
					mv.addObject("shijian", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				}
			} else {
				response.sendRedirect("login");
				return null;
			}
		}
		mv.setViewName("shuji/myhuodong_sj");
		mv.addObject("page",page);
		mv.addObject("pages", pages);
		return mv;
	}

	@RequestMapping(value = "chaxunricheng_sj") // 查询日程
	public ModelAndView chaxunricheng_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String canyuren1 = "1," + user.getYonghuid().toString() + ",1;%";
			String canyuren2 = "%;1," + user.getYonghuid().toString() + ",1;%";

			List<HuoDong> huodongs = huodongService.getAllBycanYuRen(canyuren1, canyuren2);
			for (HuoDong huoDong : huodongs) {
				String tianjiarenid = huoDong.getTianjiarenid().toString();
				YongHu tianjiaren = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
				String faqiren = "";
				if (tianjiaren == null) {
					faqiren = xueshengService.getUserById(Integer.parseInt(tianjiarenid)).getXingming();
				} else {
					faqiren = tianjiaren.getYonghuxingming();
				}
				huoDong.setFaqiren(faqiren);
			}
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String chaxunriqi = year + "-" + month + "-" + day;
			mv.addObject("year", year);
			mv.addObject("month", month);
			mv.addObject("day", day);
			mv.addObject("user", user);
			mv.addObject("chaxunriqi", chaxunriqi);
			mv.addObject("huodong", huodongs);
			mv.setViewName("shuji/wodericheng_sj");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chakancanyuren_sj") // 查看参与人
	public ModelAndView chakancanyuren_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String huodongid = request.getParameter("id");
			String banjiid = request.getParameter("banjiid");
			List<BanJi> banJis = new ArrayList<>();
			List<Map<String, String>> maps = new ArrayList<>();
			List<Map<String, String>> maps2 = new ArrayList<>();
			if (!Util.isNumeric(huodongid)) {
				response.sendRedirect("logout");
				return null;
			}
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(huodongid));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}

			int leixing = huoDong.getLeixing();
			Map<String,String> paramMap = new HashMap<>();
			paramMap.put("huodongid", huodongid);
			List<Map<String,Object>> canYuRens =  huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
			List<Map<String,Object>> juJueRens =  huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
			if(leixing==1){
				List<String> banJIs = huodongService.getAllBanJiIDByHuoDongID(Integer.parseInt(huodongid));
				if(banJIs.size()>0 && banJIs != null){
				for (String string : banJIs) {
					banJis.add(banjiService.selectByPrimaryKey(Integer.parseInt(string)));
				}
				if(banjiid==null || "".equals(banjiid)){
					banjiid = banJIs.get(0);
				}
				if (!banJIs.contains(banjiid)) {
					response.sendRedirect("logout");
					return null;
				}
				List<XueSheng> xueShengList = xueshengService.getAllByBanJiID(Integer.parseInt(banjiid));
				for (XueSheng xueSheng : xueShengList) {
					int c = 0;
					int j = 0;
					if(canYuRens!=null && canYuRens.size()>0){
						for(Map<String,Object> map : canYuRens){
							if(xueSheng.getXueshengid().toString().equals(map.get("canYuRenID").toString()) && map.get("leiXing").toString().equals("0")){
								c=1;
								break;
							}
						}
					}
					if(juJueRens!=null && juJueRens.size()>0){
						for(Map<String,Object> map : juJueRens){
							if(xueSheng.getXueshengid().toString().equals(map.get("juJueRenID").toString()) && map.get("leiXing").toString().equals("0")){
								j=1;
								break;
							}
						}
					}
					if(c==0 && j==1){
						paramMap.put("renid", xueSheng.getXueshengid().toString());
						paramMap.put("renleixing", "0");
						List<Map<String,Object>> mapS =  huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
						Map<String, String> map = new HashMap<>();
						String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
								.getBanjimingcheng();
						map.put("banjimingcheng", banjimingcheng);
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						map.put("canyuzhuangtai", "2");
						if(mapS.get(0).containsKey("liYou") &&  mapS.get(0).get("liYou").toString()!=null && !"".equals(mapS.get(0).get("liYou").toString())){
							map.put("liyou", mapS.get(0).get("liYou").toString());
						}
						maps.add(map);
					}else if(c==1 && j==0){
						Map<String, String> map = new HashMap<>();
						String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
								.getBanjimingcheng();
						map.put("banjimingcheng", banjimingcheng);
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						map.put("canyuzhuangtai", "1");
						maps.add(map);
					}else{
						Map<String, String> map = new HashMap<>();
						String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
								.getBanjimingcheng();
						map.put("banjimingcheng", banjimingcheng);
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						map.put("canyuzhuangtai", "0");
						maps.add(map);
					}
				}
			  }
			}
			if(leixing==2){
				List<Map<String,Object>> yaoQingRens = huodongService.getAllYaoQingRenByHuoDongIDAndRenIDAndRenLeiXing(paramMap);
				if(yaoQingRens.size()>0 && yaoQingRens!=null){
				for (Map<String, Object> mapS : yaoQingRens) {
					int c = 0;
					int j = 0;
					if(canYuRens!=null && canYuRens.size()>0){
						for(Map<String,Object> map : canYuRens){
							if(mapS.get("yaoQingRenID").toString().equals(map.get("canYuRenID").toString()) ){
								c=1;
								break;
							}
						}
					}
					if(juJueRens!=null && juJueRens.size()>0){
						for(Map<String,Object> map : juJueRens){
							if(mapS.get("yaoQingRenID").toString().equals(map.get("juJueRenID").toString()) ){
								j=1;
								break;
							}
						}
					}
					if(c==0 && j==1){
						paramMap.put("renid", mapS.get("yaoQingRenID").toString());
						paramMap.put("renleixing", mapS.get("leiXing").toString());
						List<Map<String,Object>> jmapS =  huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
						Map<String, String> map = new HashMap<>();
						if(mapS.get("leiXing").toString().equals("0")){
							XueSheng xueSheng = xueshengService.selectByPrimaryKey(Integer.parseInt(mapS.get("yaoQingRenID").toString()));
							String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
									.getBanjimingcheng();
							map.put("banjimingcheng", banjimingcheng);
							map.put("xueshengid", xueSheng.getXueshengid().toString());
							map.put("xuehao", xueSheng.getXuehao());
							map.put("xingming", xueSheng.getXingming());
							map.put("canyuzhuangtai", "2");
							if(jmapS.get(0).containsKey("liYou") && jmapS.get(0).get("liYou").toString()!=null && !"".equals(jmapS.get(0).get("liYou").toString())){
								map.put("liyou", jmapS.get(0).get("liYou").toString());
							}
							maps.add(map);
						}else{
							YongHu fudaoyuan = yonghuService.selectYongHuByID(Integer.parseInt(mapS.get("yaoQingRenID").toString()));
							map.put("yonghuid", mapS.get("yaoQingRenID").toString());
							map.put("xingming", fudaoyuan.getYonghuxingming());
							map.put("canyuzhuangtai", "2");
							if(jmapS.get(0).containsKey("liYou") && jmapS.get(0).get("liYou").toString()!=null && !"".equals(jmapS.get(0).get("liYou").toString())){
								map.put("liyou", jmapS.get(0).get("liYou").toString());
							}
							maps2.add(map);
						}
					}
					if(c==1 && j==0){
						Map<String, String> map = new HashMap<>();
						if(mapS.get("leiXing").toString().equals("0")){
							XueSheng xueSheng = xueshengService.selectByPrimaryKey(Integer.parseInt(mapS.get("yaoQingRenID").toString()));
							String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
									.getBanjimingcheng();
							map.put("banjimingcheng", banjimingcheng);
							map.put("xueshengid", xueSheng.getXueshengid().toString());
							map.put("xuehao", xueSheng.getXuehao());
							map.put("xingming", xueSheng.getXingming());
							map.put("canyuzhuangtai", "1");
							maps.add(map);
						}else{
							YongHu fudaoyuan = yonghuService.selectYongHuByID(Integer.parseInt(mapS.get("yaoQingRenID").toString()));
							map.put("yonghuid", mapS.get("yaoQingRenID").toString());
							map.put("xingming", fudaoyuan.getYonghuxingming());
							map.put("canyuzhuangtai", "1");
							maps2.add(map);
						}
					}
					if(c==0 && j==0){
						Map<String, String> map = new HashMap<>();
						if(mapS.get("leiXing").toString().equals("0")){
							XueSheng xueSheng = xueshengService.selectByPrimaryKey(Integer.parseInt(mapS.get("yaoQingRenID").toString()));
							String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
									.getBanjimingcheng();
							map.put("banjimingcheng", banjimingcheng);
							map.put("xueshengid", xueSheng.getXueshengid().toString());
							map.put("xuehao", xueSheng.getXuehao());
							map.put("xingming", xueSheng.getXingming());
							map.put("canyuzhuangtai", "0");
							maps.add(map);
						}else{
							YongHu fudaoyuan = yonghuService.selectYongHuByID(Integer.parseInt(mapS.get("yaoQingRenID").toString()));
							map.put("yonghuid", mapS.get("yaoQingRenID").toString());
							map.put("xingming", fudaoyuan.getYonghuxingming());
							map.put("canyuzhuangtai", "0");
							maps2.add(map);
						}
					}
				}
			  }
			}
			String tianjiarenid = huoDong.getTianjiarenid().toString();
			if(huoDong.getTianjiarenleixing().toString().equals("0")){//学生
				Map<String, String> map = new HashMap<>();
				if(leixing==2){
					if(canYuRens!=null && canYuRens.size()>0){
						for(Map<String,Object> map2 : canYuRens){
							if(tianjiarenid.equals(map2.get("canYuRenID").toString()) ){
								XueSheng xueSheng = xueshengService.selectByPrimaryKey(Integer.parseInt(tianjiarenid));
								String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
										.getBanjimingcheng();
								map.put("banjimingcheng", banjimingcheng);
								map.put("xueshengid", xueSheng.getXueshengid().toString());
								map.put("xuehao", xueSheng.getXuehao());
								map.put("xingming", xueSheng.getXingming());
								map.put("canyuzhuangtai", "1");
								maps.add(map);
								break;
							}
						}
					}
					if(juJueRens!=null && juJueRens.size()>0){
						for(Map<String,Object> map2 : juJueRens){
							if(tianjiarenid.equals(map2.get("juJueRenID").toString()) ){
								XueSheng xueSheng = xueshengService.selectByPrimaryKey(Integer.parseInt(tianjiarenid));
								paramMap.put("renid", xueSheng.getXueshengid().toString());
								paramMap.put("renleixing", "0");
								List<Map<String,Object>> mapS =  huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
								String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
										.getBanjimingcheng();
								map.put("banjimingcheng", banjimingcheng);
								map.put("xueshengid", xueSheng.getXueshengid().toString());
								map.put("xuehao", xueSheng.getXuehao());
								map.put("xingming", xueSheng.getXingming());
								map.put("canyuzhuangtai", "2");
								if(mapS.get(0).containsKey("liYou") &&  mapS.get(0).get("liYou").toString()!=null && !"".equals(mapS.get(0).get("liYou").toString())){
									map.put("liyou", mapS.get(0).get("liYou").toString());
								}
								maps.add(map);
								break;
							}
						}
					}
				}
			}else{
				paramMap.put("renid", tianjiarenid.toString());
				paramMap.put("renleixing", "1");
				List<Map<String,Object>> jmapS =  huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
				Map<String, String> map = new HashMap<>();
				if(jmapS!=null && jmapS.size()>0){
					map.put("yonghuid", tianjiarenid.toString());
					map.put("xingming", user.getYonghuxingming());
					map.put("canyuzhuangtai", "2");
					if(jmapS.get(0).containsKey("liYou") && jmapS.get(0).get("liYou").toString()!=null && !"".equals(jmapS.get(0).get("liYou").toString())){
						map.put("liyou", jmapS.get(0).get("liYou").toString());
					}
					maps2.add(map);
				}else{
					YongHu fudaoyuan = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
					map.put("yonghuid", tianjiarenid.toString());
					map.put("xingming", fudaoyuan.getYonghuxingming());
					map.put("canyuzhuangtai", "1");
					maps2.add(map);
				}
			}
			mv.addObject("banjis", banJis);
			mv.addObject("bjid", banjiid);
			mv.addObject("student", maps);
			mv.addObject("shuji", maps2);
			mv.addObject("huodong", huoDong);
			mv.setViewName("shuji/chakancanyuren_sj");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "myhuodong_sj") // 我的活动
	public ModelAndView myhuodong_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, String> map = new HashMap<>();
			List<HuoDong> huoDongs = new ArrayList<>();
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "4");
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
				pages = (count / pageSize);
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
			mv.addObject("shijian", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			mv.addObject("yonghuid", user.getYonghuid().toString());
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("shuji/myhuodong_sj");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addhuodong_sj") // 新增活动
	public ModelAndView addhuodong_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			mv.setViewName("shuji/addhuodong_sj");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "show_wodexuesheng_sj") // 添加活动参与人——根据班级显示我的学生
	@ResponseBody
	public List<Map<String, String>> show_wodexuesheng(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			String banjiid = request.getParameter("CODE");
			List<Map<String, String>> xueShengs = new ArrayList<>();
			if (banjiid.equals("")) {
				String banjiids = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid();
				String banjiid2[] = banjiids.split(",");
				for (String string : banjiid2) {
					List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs2) {
						Map<String, String> map = new HashMap<>();
						String banji = banjiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng();
						map.put("banjiid", xueSheng.getBanjiid().toString());
						map.put("banji", banji);
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						xueShengs.add(map);
					}
				}
			} else {
				List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(Integer.parseInt(banjiid));
				for (XueSheng xueSheng : xueShengs2) {
					Map<String, String> map = new HashMap<>();
					String banji = banjiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng();
					map.put("banjiid", xueSheng.getBanjiid().toString());
					map.put("banji", banji);
					map.put("xueshengid", xueSheng.getXueshengid().toString());
					map.put("xuehao", xueSheng.getXuehao());
					map.put("xingming", xueSheng.getXingming());
					xueShengs.add(map);
				}
			}
			return xueShengs;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "savehuodong_sj") // 保存新增活动
	@ResponseBody
	public Object savehuodong_sj(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> map2 = new HashMap<>();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			// 获取学校id
			int yuanxiid = user.getYonghuid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			String mingcheng = request.getParameter("mingcheng");
			String didian = request.getParameter("didian");
			String beizhu = request.getParameter("shuoming");
//			String canyuren = request.getParameter("canyuren");
			String riqi = request.getParameter("riqi");
			String kaishishijian = request.getParameter("kaishishijian");
			String jieshushijian = request.getParameter("jieshushijian");
			map.put("huodongmingcheng", mingcheng);
			map.put("didian", didian);
			map.put("shuoming", beizhu);
			map.put("tianjiarenid", user.getYonghuid().toString());
			map.put("tianjiarenleixing", "4");
			map.put("riqi", riqi);
			map.put("leixing", "2");
			map.put("kaishishijian", kaishishijian);
			map.put("jieshushijian", jieshushijian);
			int i = huodongService.insert(map);
			if (i != 0) {
				// // 发送激光消息
				// XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				// xiaoXiFaSong.setXiaoXiMingCheng("新增活动");
				// xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() +
				// "邀请你参加活动#" + mingcheng + "#");
				// xiaoXiFaSong.setShuJuId(Integer.parseInt(map.get("huodongid")));
				// xiaoXiFaSong.setShuJuLeiXing(2);
				// xiaoXiFaSong.setFaSongLeiXing(1);
				// xiaoXiFaSong.setShiFouChengGong(0);
				// xiaoXiFaSong.setXueXiaoId(xueXiaoID);
				// // 发送提醒消息
				// TiXing tiXing = new TiXing();
				// Date date = new Date();
				// tiXing.setNeirong(user.getYonghuxingming() + "邀请你参加活动#" +
				// mingcheng + "#");
				// tiXing.setShijian(date);
				// tiXing.setZhuangtai(0);
				// tiXing.setShujuid(Integer.parseInt(map.get("huodongid")));
				// tiXing.setType(2);
				// String canyurens[] = canyuren.split(";");
				// for (String string : canyurens) {
				// String cyr[] = string.split(",");
				// tiXing.setJieshourenid(Integer.parseInt(cyr[1]));
				// tixingService.insert(tiXing);
				// xiaoXiFaSong.setFaSongMuBiao(cyr[1]);
				// jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				// }
				map2.put("status", "success");
			} else {
				map2.put("status", "fail");
			}
			return JSON.toJSON(map2);
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "xiugaihuodong_sj") // 修改活动
	public ModelAndView xiugaihuodong_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String huodongid = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(huodongid)) {
				response.sendRedirect("logout");
				return null;
			}
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(huodongid));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			mv.addObject("qufen", qufen);
			mv.addObject("huodong", huoDong);
			mv.setViewName("shuji/xiugaihuodong_sj");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "saveupdatehuodong_sj") // 保存修改活动
	@ResponseBody
	public Object saveupdatehuodong_sj(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, Object> map2 = new HashMap<>();
			// 获取学校id
			int yuanxiid = user.getYonghuid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			String huodongid = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(huodongid)) {
				response.sendRedirect("logout");
				return null;
			}
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(huodongid));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			String mingcheng = request.getParameter("mingcheng");
			String didian = request.getParameter("didian");
			String beizhu = request.getParameter("shuoming");
//			String canyuren2 = request.getParameter("canyuren");
			String riqi = request.getParameter("riqi");
			String kaishishijian = request.getParameter("kaishishijian");
			String jieshushijian = request.getParameter("jieshushijian");
			HuoDong huoDong2 = new HuoDong();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			huoDong2.setHuodongid(Integer.parseInt(huodongid));
			huoDong2.setHuodongmingcheng(mingcheng);
			huoDong2.setDidian(didian);
			huoDong2.setRiqi(df.parse(riqi));
			huoDong2.setKaishishijian(kaishishijian);
			huoDong2.setJieshushijian(jieshushijian);
			huoDong2.setTianjiarenid(user.getYonghuid());
			huoDong2.setTianjiarenleixing(4);
			huoDong2.setShuoming(beizhu);
			huoDong2.setLeixing(2);
			int j = huodongService.updateByPrimaryKey(huoDong2);
			if (j != 0) {
				// // 发送激光消息
				// XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				// xiaoXiFaSong.setXiaoXiMingCheng("修改活动");
				// xiaoXiFaSong
				// .setXiaoXiNeiRong(user.getYonghuxingming() + "修改了活动#" +
				// huoDong.getHuodongmingcheng() + "#");
				// xiaoXiFaSong.setShuJuId(Integer.parseInt(huodongid));
				// xiaoXiFaSong.setShuJuLeiXing(2);
				// xiaoXiFaSong.setFaSongLeiXing(1);
				// xiaoXiFaSong.setShiFouChengGong(0);
				// xiaoXiFaSong.setXueXiaoId(xueXiaoID);
				// // 发送提醒消息
				// TiXing tiXing = new TiXing();
				// Date date = new Date();
				// tiXing.setNeirong(user.getYonghuxingming() + "修改了活动#" +
				// huoDong.getHuodongmingcheng() + "#");
				// tiXing.setShijian(date);
				// tiXing.setZhuangtai(0);
				// tiXing.setShujuid(Integer.parseInt(huodongid));
				// tiXing.setType(2);
				// String canyurens1[] = canyuren2.split(";");
				// for (String string : canyurens1) {
				// String cyr[] = string.split(",");
				// tiXing.setJieshourenid(Integer.parseInt(cyr[1]));
				// tixingService.insert(tiXing);
				// xiaoXiFaSong.setFaSongMuBiao(cyr[1]);
				// jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				// }
				map2.put("status", "success");
			} else {
				map2.put("status", "fail");
			}
			return JSON.toJSON(map2);
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "delhuodong_sj") // 拒绝活动——将参与状态改为2，参与人还存在
	@ResponseBody
	public Object delhuodong_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			// System.out.println(id);
			YongHu user = (YongHu) session.getAttribute("user");
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			int sign = 0;
			map.put("huodongid", id);
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "4");
			List<Map<String, Object>> canYuRens = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
			if (canYuRens != null && canYuRens.size() > 0) {
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
				map2.put("status", "success");
			} else {
				map2.put("status", "fail");
			}
			return JSON.toJSON(map2);
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "newhuodong_sj") // 待确认活动
	public ModelAndView newhuodong_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, String> map = new HashMap<>();
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "4");
			List<HuoDong> huodongs = huodongService.getAllByRenIDAndRenLeiXing(map);
			List<HuoDong> huoDongs = new ArrayList<>();
			for (HuoDong huoDong : huodongs) {
				map.put("huodongid", huoDong.getHuodongid().toString());
				List<Map<String, Object>> juJueRen = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				List<Map<String, Object>> canYuRen = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				String tianjiarenid = huoDong.getTianjiarenid().toString();
				YongHu tianjiaren = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
				String faqiren = "";
				if (tianjiaren == null) {
					faqiren = xueshengService.getUserById(Integer.parseInt(tianjiarenid)).getXingming();
				} else {
					faqiren = tianjiaren.getYonghuxingming();
					if (tianjiarenid.equals(user.getYonghuid().toString())) {
						if (juJueRen != null && juJueRen.size() > 0) {
							huoDong.setFaqiren(faqiren);
							huoDong.setZhuangtai(2);
							huoDongs.add(huoDong);
						} else {
							break;
						}
					} else {
						if (juJueRen == null || juJueRen.size() <= 0) {
							if (canYuRen == null || juJueRen.size() <= 0) {
								huoDong.setFaqiren(tianjiaren.getYonghuxingming());
								huoDong.setZhuangtai(0);
								huoDongs.add(huoDong);
							} else {
								break;
							}
						} else {
							huoDong.setFaqiren(faqiren);
							huoDong.setZhuangtai(2);
							huoDongs.add(huoDong);
						}
					}
				}

			}
			mv.addObject("huodong", huoDongs);
			mv.setViewName("shuji/newhuodong_sj");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "confirmhuodong_sj") // 确认参加活动——将参与状态改为1
	@ResponseBody
	public Object confirmhuodong_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
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
			map.put("huodongid", id);
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "4");
			List<Map<String, Object>> jujueren = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);
			if (jujueren != null && jujueren.size() > 0) {
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
				map2.put("status", "success");
			} else {
				map2.put("status", "fail");
			}
			return JSON.toJSON(map2);
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "canyuhuodong_sj") // 确认参加活动——将参与状态改为1
	@ResponseBody
	public Object canyuhuodong_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		if (Util.checkSession(request)) {
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
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
			map.put("huodongid", id);
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "4");
			List<Map<String, Object>> jujueren = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);
			if (jujueren != null && jujueren.size() > 0) {
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
				map2.put("status", "success");
			} else {
				map2.put("status", "fail");
			}
			return JSON.toJSON(map2);
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "cleanhuodong_sj") // 彻底清除活动,将参与人剔除，活动还存在
	@ResponseBody
	public Object cleanhuodong_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			// System.out.println(id);
			YongHu user = (YongHu) session.getAttribute("user");
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			map.put("huodongid", id);
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
				map2.put("status", "success");
			} else {
				map2.put("status", "fail");
			}
			return JSON.toJSON(map2);
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "wodebeiwanglu_sj") // 我的备忘录
	public ModelAndView wodebeiwanglu_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");

			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");

			List<BeiWL> beiWLs = new ArrayList<>();

			if (id == null || id.equals("")) {
				mv.addObject("qufen", null);
				Map<String, String> map = new HashMap<>();
				map.put("renid", user.getYonghuid().toString());
				map.put("renleixing", "4");
				beiWLs = beiwlService.getBeiWLByRenIDAndRenLeiXingAndRiQi(map);
			} else {
				mv.addObject("qufen", qufen);
				BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
				beiWLs.add(beiWL);
			}
			mv.addObject("beiwl", beiWLs);
			mv.setViewName("shuji/mybeiwanglu_sj");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addbeiwang_sj") // 新增备忘录
	public ModelAndView addbeiwang_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			mv.setViewName("shuji/addbeiwanglu_sj");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savebeiwang_sj") // 保存新增备忘录———自己备忘//或下一步跳到给学生添加事件页面
	@ResponseBody
	public Object savebeiwang_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		Map<String, Object> map2 = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String neirong = request.getParameter("neirong");
			String date = request.getParameter("riqi");
			String time = request.getParameter("shijian");
			String shijian = date + " " + time;
				map.put("leixing", "0");
				map.put("neirong", neirong);
				map.put("userid", user.getYonghuid().toString());
				map.put("shijian", shijian);
				int i = beiwlService.insert(map);
				if (i != 0) {
					map2.put("status", "success");
				} else {
					map2.put("status", "fail");
				}
				return JSON.toJSON(map2);
//			if (action.equals("下一步")) {
//				String xuesheng_banji = request.getParameter("ziji_xuesheng");
//				// 班级
//				List<BanJi> banjis = banjiService.getAllByYuanXiID(user.getYuanxiid());
//				List<Map<String, String>> maps = new ArrayList<>();
//				for (BanJi banji : banjis) {
//					List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(banji.getBanjiid());
//					for (XueSheng xueSheng : xueShengs2) {
//						Map<String, String> map2 = new HashMap<>();
//						map2.put("banji", banji.getBanjimingcheng());
//						map2.put("banjiid", banji.getBanjiid().toString());
//						map2.put("xueshengid", xueSheng.getXueshengid().toString());
//						map2.put("xuehao", xueSheng.getXuehao());
//						map2.put("xingming", xueSheng.getXingming());
//						maps.add(map2);
//					}
//				}
//				if (xuesheng_banji.equals("1")) { // 学生
//					mv.addObject("xuesheng", maps);
//					mv.addObject("banji", banjis);
//					mv.addObject("neirong", neirong);
//					// mv.addObject("didian", didian);
//					mv.addObject("shijian", shijian);
//					mv.setViewName("shuji/addshijianforxuesheng");
//					return mv;
//				}
//
//				if ("2".equals(xuesheng_banji)) { // 班级
//					mv.addObject("xuesheng", maps);
//					mv.addObject("banji", banjis);
//					mv.addObject("neirong", neirong);
//					// mv.addObject("didian", didian);
//					mv.addObject("shijian", shijian);
//					mv.setViewName("shuji/addshijianforbanji");
//					return mv;
//				}

//			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	@RequestMapping(value = "addshijianforxuesheng_sj") // 给学生添加事件
	public ModelAndView addshijianforxuesheng_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String neirong = request.getParameter("neirong");
			// String didian = request.getParameter("didian");
			String date = request.getParameter("riqi");
			String time = request.getParameter("shijian");
			String shijian = date + " " + time;
			// 班级
			List<BanJi> banjis = banjiService.getAllByYuanXiID(user.getYuanxiid());
			List<Map<String, String>> maps = new ArrayList<>();
			for (BanJi banji : banjis) {
				List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(banji.getBanjiid());
				for (XueSheng xueSheng : xueShengs2) {
					Map<String, String> map2 = new HashMap<>();
					map2.put("banji", banji.getBanjimingcheng());
					map2.put("banjiid", banji.getBanjiid().toString());
					map2.put("xueshengid", xueSheng.getXueshengid().toString());
					map2.put("xuehao", xueSheng.getXuehao());
					map2.put("xingming", xueSheng.getXingming());
					maps.add(map2);
				}
			}
			mv.addObject("xuesheng", maps);
			mv.addObject("banji", banjis);
			mv.addObject("neirong", neirong);
			// mv.addObject("didian", didian);
			mv.addObject("shijian", shijian);
			mv.setViewName("shuji/addshijianforxuesheng");
			return mv;
		}
		else{
			response.sendRedirect("login");
			return null;
		}
	}
		@RequestMapping(value = "addshijianforbanji_sj") // 给班级添加事件
		public ModelAndView addshijianforbanji_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
			HttpSession session = request.getSession();
			ModelAndView mv = new ModelAndView();
			if (session.getAttribute("user") != null && session.getAttribute("user") != "") { // 班级
				YongHu user = (YongHu) session.getAttribute("user");
				String neirong = request.getParameter("neirong");
				// String didian = request.getParameter("didian");
				String date = request.getParameter("riqi");
				String time = request.getParameter("shijian");
				String shijian = date + " " + time;
				// 班级
				List<BanJi> banjis = banjiService.getAllByYuanXiID(user.getYuanxiid());
				List<Map<String, String>> maps = new ArrayList<>();
				for (BanJi banji : banjis) {
					List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(banji.getBanjiid());
					for (XueSheng xueSheng : xueShengs2) {
						Map<String, String> map2 = new HashMap<>();
						map2.put("banji", banji.getBanjimingcheng());
						map2.put("banjiid", banji.getBanjiid().toString());
						map2.put("xueshengid", xueSheng.getXueshengid().toString());
						map2.put("xuehao", xueSheng.getXuehao());
						map2.put("xingming", xueSheng.getXingming());
						maps.add(map2);
					}
				}
				mv.addObject("xuesheng", maps);
				mv.addObject("banji", banjis);
				mv.addObject("neirong", neirong);
				// mv.addObject("didian", didian);
				mv.addObject("shijian", shijian);
				mv.setViewName("shuji/addshijianforbanji");
				return mv;
			}
			else{
				response.sendRedirect("login");
				return null;
			}
		}
	@RequestMapping(value = "saveshijianforxuesheng_sj") // 保存事件——为学生添加
	@ResponseBody
	public Object saveshijianforxuesheng_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, Object> map2 = new HashMap<>();
			// 获取学校id
			int yuanxiid = user.getYonghuid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			String neirong = request.getParameter("neirong");
			String didian = request.getParameter("didian");
			String shijian = request.getParameter("shijian");
			String huizhi = request.getParameter("huizhi");
			String xueshengids = request.getParameter("xueshengids");
			String xueshengid[] = xueshengids.split(",");
			String jieshouren = "";
			for (String string : xueshengid) {
				jieshouren += string + ",0;";
			}
			BeiWL beiWL = new BeiWL();
			beiWL.setLeixing(2);
			beiWL.setNeirong(neirong);
			beiWL.setShijian(shijian);
			// beiWL.setDidian(didian);
			beiWL.setUserid(user.getYonghuid().toString());
			// beiWL.setJieshouren(jieshouren);
			beiWL.setHuizhi(Integer.parseInt(huizhi));
			int i = beiwlService.insertforxuesheng(beiWL);
			if (i != 0) {
				for (String string : xueshengid) {
					Map<String, String> map = new HashMap<>();
					map.put("beiwlid", beiWL.getId().toString());
					map.put("jieshourenid", string);
					map.put("leixing", "0");
					beiwlService.insert_beiwangluren(map);
				}
				// 发送激光消息
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("添加事件");
				xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "给你添加了一个事件$" + neirong + "$");
				xiaoXiFaSong.setShuJuId(beiWL.getId());
				xiaoXiFaSong.setShuJuLeiXing(3);
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(xueXiaoID);
				// 发送提醒消息
				TiXing tiXing = new TiXing();
				Date date2 = new Date();
				tiXing.setNeirong(user.getYonghuxingming() + "给你添加了一个事件$" + neirong + "$");
				tiXing.setShijian(date2);
				tiXing.setZhuangtai(0);
				tiXing.setShujuid(beiWL.getId());
				tiXing.setType(3);
				for (String string : xueshengid) {
					tiXing.setJieshourenid(Integer.parseInt(string));
					tixingService.insert(tiXing);
					xiaoXiFaSong.setFaSongMuBiao(string);
					jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				}
				map2.put("status", "success");
			} else {
				map2.put("status", "fail");
			}
			return JSON.toJSON(map2);
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "saveshijianforbanji_sj") // 保存事件——为班级添加
	@ResponseBody
	public Object saveshijianforbanji_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, Object> map2 = new HashMap<>();
			// 获取学校id
			int yuanxiid = user.getYonghuid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			String neirong = request.getParameter("neirong");
			String didian = request.getParameter("didian");
			String shijian = request.getParameter("shijian");
			String huizhi = request.getParameter("huizhi");
			String banjiids = request.getParameter("xueshengids");
			String banjiid[] = banjiids.split(",");
			String jieshouren = "";
			for (String string : banjiid) {
				jieshouren += string + ":";
				List<XueSheng> xueshengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
				for (XueSheng xueSheng : xueshengs) {
					jieshouren += xueSheng.getXueshengid() + ",0!";
				}
				jieshouren += ";";
			}
			BeiWL beiWL = new BeiWL();
			beiWL.setLeixing(1);
			beiWL.setNeirong(neirong);
			beiWL.setShijian(shijian);
			// beiWL.setDidian(didian);
			beiWL.setUserid(user.getYonghuid().toString());
			// beiWL.setJieshouren(jieshouren);
			// beiWL.setBanjiids(banjiids);
			beiWL.setHuizhi(Integer.parseInt(huizhi));
			int i = beiwlService.insertforbanji(beiWL);
			if (i != 0) {
				for (String string : banjiid) {
					Map<String, String> map = new HashMap<>();
					map.put("beiwlid", beiWL.getId().toString());
					map.put("banjiid", string);
					beiwlService.insert_beiwanglubanji(map);
				}
				// 发送激光消息
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("添加事件");
				xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "给你添加了一个事件$" + neirong + "$");
				xiaoXiFaSong.setShuJuId(beiWL.getId());
				xiaoXiFaSong.setShuJuLeiXing(3);
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(xueXiaoID);
				// 发送提醒消息
				TiXing tiXing = new TiXing();
				Date date2 = new Date();
				tiXing.setNeirong(user.getYonghuxingming() + "给你添加了一个事件$" + neirong + "$");
				tiXing.setShijian(date2);
				tiXing.setZhuangtai(0);
				tiXing.setShujuid(beiWL.getId());
				tiXing.setType(3);
				for (String string : banjiid) {
					List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					if (xueShengs.size() > 0) {
						for (XueSheng xueSheng : xueShengs) {
							tiXing.setJieshourenid(xueSheng.getXueshengid());
							tixingService.insert(tiXing);
							xiaoXiFaSong.setFaSongMuBiao(xueSheng.getXueshengid().toString());
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
					}
				}
				map2.put("status", "success");
			} else {
				map2.put("status", "fail");
			}
			return JSON.toJSON(map2);
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "xiugaibeiwanglu_sj") // 修改备忘录
	public ModelAndView xiugaibeiwanglu_sj(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String shujiid = user.getYonghuid().toString();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if ((beiWL != null) && (beiWL.getUserid().equals(shujiid))) {
				Map<String, String> map = new HashMap<>();
				int leixing = beiWL.getLeixing();
				String datetime = beiWL.getShijian();
				String date = datetime.substring(0, datetime.indexOf(" "));
				String time = datetime.substring(datetime.indexOf(" ") + 1, datetime.length());
				map.put("beiwlid", id);
				String jsr = "";
				if (leixing == 0) {
					mv.addObject("beiwanglu", beiWL);
					mv.addObject("date", date);
					mv.addObject("time", time);
					mv.addObject("qufen", qufen);
					mv.setViewName("shuji/xiugaibeiwanglu_sj");
				}
				int huizhi = 0;
				if(beiWL.getHuizhi() != null && !"".equals(beiWL.getHuizhi().toString())){
					huizhi = beiWL.getHuizhi();
				}
				if (leixing == 2) {
					List<Map<String, Object>> xuesheng = beiwlService.getBeiWLRenByBeiWLIDAndRenIDAndRenLeiXing(map);
					List<XueSheng> xueShengs = new ArrayList<>();
					for (Map<String, Object> string : xuesheng) {
						if (string.get("leiXing").toString().equals("0")) {
							jsr += string.get("jieShouRenID").toString() + ",";
							XueSheng xueSheng2 = xueshengService
									.getUserById(Integer.parseInt(string.get("jieShouRenID").toString()));
							xueShengs.add(xueSheng2);
						}
					}
					// 班级、学生
					List<BanJi> banjis = banjiService.getAllByYuanXiID(user.getYuanxiid());
					List<Map<String, String>> maps = new ArrayList<>();
					for (BanJi banJi : banjis) {
						List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(banJi.getBanjiid());
						for (XueSheng xueSheng : xueShengs2) {
							Map<String, String> map2 = new HashMap<>();
							map2.put("banji", banJi.getBanjimingcheng());
							map2.put("banjiid", banJi.getBanjiid().toString());
							map2.put("xueshengid", xueSheng.getXueshengid().toString());
							map2.put("xuehao", xueSheng.getXuehao());
							map2.put("xingming", xueSheng.getXingming());
							maps.add(map2);
						}
					}
					beiWL.setJieshouren(jsr);
					mv.addObject("xuesheng", maps);
					mv.addObject("xuesheng2", xueShengs);
					mv.addObject("huizhi", huizhi);
					mv.addObject("beiwanglu", beiWL);
					mv.addObject("date", date);
					mv.addObject("time", time);
					mv.addObject("qufen", qufen);
					mv.setViewName("shuji/xiugaishijianforxuesheng");
				}

				if (leixing == 1) {
					List<BanJi> banjis = new ArrayList<>();
					List<BanJi> allbanjis = new ArrayList<>();
					List<String> str = beiwlService.getAllBanJiIDByBeiWLID(id);
					for (String string : str) {
						jsr += string + ",";
						BanJi banji = banjiService.selectByPrimaryKey(Integer.parseInt(string));
						banjis.add(banji);
					}
					beiWL.setBanjiids(jsr);
					allbanjis = banjiService.getAllByYuanXiID(user.getYuanxiid());

					mv.addObject("huizhi", huizhi);
					mv.addObject("beiwanglu", beiWL);
					mv.addObject("date", date);
					mv.addObject("time", time);
					mv.addObject("qufen", qufen);
					mv.addObject("banjis", banjis);
					mv.addObject("allbanjis", allbanjis);
					mv.setViewName("shuji/xiugaishijianforbanji");
				}

				return mv;
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "saveupdatebeiwanglu_sj") // 保存修改备忘录
	@ResponseBody
	public Object saveupdatebeiwanglu_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		BeiWL beiWL = new BeiWL();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			String fudaoyuanid = user.getYonghuid().toString();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			BeiWL beiwanglu = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if (beiwanglu != null && beiwanglu.getUserid().equals(fudaoyuanid)) {
				String neirong = request.getParameter("neirong");
				String didian = request.getParameter("didian");
				String date = request.getParameter("riqi");
				String time = request.getParameter("shijian");
				String shijian = date + " " + time;

				beiWL.setId(Integer.parseInt(id));
				beiWL.setLeixing(0);
				beiWL.setNeirong(neirong);
				// beiWL.setDidian(didian);
				beiWL.setShijian(shijian);
				beiWL.setUserid(user.getYonghuid().toString());
				int i = beiwlService.updateByPrimaryKey(beiWL);
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

	@RequestMapping(value = "saveupdateshijianforxuesheng_sj") // 保存修改给学生的事件
	@ResponseBody
	public Object saveupdateshijianforxuesheng(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession();
		BeiWL beiWL = new BeiWL();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, Object> map2 = new HashMap<>();
			// 获取学校id
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();

			String shujiid = user.getYonghuid().toString();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			BeiWL beiwanglu = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if (beiwanglu != null && beiwanglu.getUserid().equals(shujiid)) {
				String neirong = request.getParameter("neirong");
				String didian = request.getParameter("didian");
				String date = request.getParameter("riqi");
				String time = request.getParameter("shijian");
				String shijian = date + " " + time;
				String huizhi = request.getParameter("huizhi");
				String xueshengids = request.getParameter("xueshengids");
				String xueshengid[] = xueshengids.split(",");
				String jieshouren = "";
				String jsr = "";
				if (beiwanglu.getLeixing() == 1) {
					beiWL.setLeixing(1);
					xueshengid = xueshengids.split(",");
					for (String string : xueshengid) {
						List<XueSheng> xueshengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
						for (XueSheng xueSheng : xueshengs) {
							jsr += xueSheng.getXueshengid() + ",";
						}
					}
				}
				if (beiwanglu.getLeixing() == 2) {
					xueshengid = xueshengids.split(",");
					beiWL.setLeixing(2);
					for (String string : xueshengid) {
						jsr += string + ",";
					}
				}
				beiWL.setId(Integer.parseInt(id));
				beiWL.setHuodongid(beiwanglu.getHuodongid());
				beiWL.setNeirong(neirong);
				// beiWL.setDidian(didian);
				beiWL.setShijian(shijian);
				beiWL.setUserid(user.getYonghuid().toString());
				beiWL.setHuizhi(Integer.parseInt(huizhi));
				int i = beiwlService.updateshijianforxuesheng(beiWL);
				if (i != 0) {
					Map<String, String> map = new HashMap<>();
					map.put("beiwlid", id);
					if (beiWL.getLeixing() == 1) {
						beiwlService.delete_beiwanglubanji(map);
						if (xueshengids != null && !"".equals(xueshengids)) {
							String ids[] = xueshengids.split(",");
							for (String string : ids) {
								map.put("banjiid", string);
								beiwlService.insert_beiwanglubanji(map);
							}
						}
					}
					if (beiWL.getLeixing() == 2) {
						beiwlService.delete_beiwangluren(map);
						if (xueshengids != null && !"".equals(xueshengids)) {
							String ids[] = xueshengids.split(",");
							for (String string : ids) {
								map.put("jieshourenid", string);
								map.put("leixing", "0");
								beiwlService.insert_beiwangluren(map);
							}
						}
					}
					// 发送激光消息
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					xiaoXiFaSong.setXiaoXiMingCheng("修改事件");
					xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "修改了事件$" + neirong + "$");
					xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
					xiaoXiFaSong.setShuJuLeiXing(3);
					xiaoXiFaSong.setFaSongLeiXing(1);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(xueXiaoID);
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					Date date2 = new Date();
					tiXing.setNeirong(user.getYonghuxingming() + "修改了事件$" + neirong + "$");
					tiXing.setShijian(date2);
					tiXing.setZhuangtai(0);
					tiXing.setShujuid(Integer.parseInt(id));
					tiXing.setType(3);
					if (jsr != null && !"".equals(jsr)) {
						for (String string : jsr.split(",")) {
							tiXing.setJieshourenid(Integer.parseInt(string));
							tixingService.insert(tiXing);
							xiaoXiFaSong.setFaSongMuBiao(string);
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
					}
					map2.put("status", "success");
				} else {
					map2.put("status", "fail");
				}
				return JSON.toJSON(map2);
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "delbeiwl_sj") // 删除备忘录
	@ResponseBody
	public Object delbeiwl_sj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, Object> map3 = new HashMap<>();
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			String shujiid = user.getYonghuid().toString();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			String jsr = "";
			BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			Map<String, String> map = new HashMap<>();
			map.put("beiwlid", id);
			if (beiWL != null && beiWL.getUserid().equals(shujiid)) {
				if (beiWL.getLeixing() == 0) {
					jsr += beiWL.getUserid() + ",";
				} else if (beiWL.getLeixing() == 1) {
					List<String> banjiids = beiwlService.getAllBanJiIDByBeiWLID(id);
					for (String string : banjiids) {
						List<XueSheng> xueshengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
						for (XueSheng xueSheng : xueshengs) {
							jsr += xueSheng.getXueshengid() + ",";
						}
					}
				} else {
					List<Map<String, Object>> ids = beiwlService.getBeiWLRenByBeiWLIDAndRenIDAndRenLeiXing(map);
					for (Map<String, Object> string : ids) {
						if (string != null && string.containsKey("jieShouRenID")) {
							jsr += string.get("jieShouRenID").toString() + ",";
						}
					}
				}
				int i = 0;
				try {
					beiwlService.delete_beiwlhuizhi(map);
					beiwlService.delete_beiwanglubanji(map);
					beiwlService.delete_beiwangluren(map);
					beiwlService.deleteByPrimaryKey(Integer.parseInt(id));
					i = 1;
				} catch (Exception e) {
					e.printStackTrace();
					i = 0;
				}
				if (i != 0) {
					// 发送激光消息
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					xiaoXiFaSong.setXiaoXiMingCheng("删除事件");
					xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "删除了事件$" + beiWL.getNeirong() + "$");
					xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
					xiaoXiFaSong.setShuJuLeiXing(3);
					xiaoXiFaSong.setFaSongLeiXing(1);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(xueXiaoID);
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					Date date2 = new Date();
					tiXing.setNeirong(user.getYonghuxingming() + "删除了事件$" + beiWL.getNeirong() + "$");
					tiXing.setShijian(date2);
					tiXing.setZhuangtai(0);
					tiXing.setShujuid(Integer.parseInt(id));
					tiXing.setType(3);
					String jieshouren[] = jsr.split(",");
					for (String string : jieshouren) {
						tiXing.setJieshourenid(Integer.parseInt(string));
						tixingService.insert(tiXing);
						xiaoXiFaSong.setFaSongMuBiao(string);
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
					}
					map3.put("status", "success");
				} else {
					map3.put("status", "fail");
				}
				return JSON.toJSON(map3);
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chakanjieshouren_sj") // 查看接收人
	public ModelAndView chakanjieshouren(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String fudaoyuanid = user.getYonghuid().toString();
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if ((beiWL != null) && (beiWL.getUserid().equals(fudaoyuanid))) {
				String jieshouren = beiWL.getJieshouren();
				int huizhi = 0;
				if(beiWL.getHuizhi() != null && !"".equals(beiWL.getHuizhi().toString())) {
					huizhi = beiWL.getHuizhi();
				}

				List<Map<String, String>> maps = new ArrayList<>();
				List<BanJi> banjis = new ArrayList<>();
				List<String> banjiids = new ArrayList<>();
				Map<String, String> paramMap = new HashMap<>();
				paramMap.put("beiwlid", id);
				if (beiWL.getLeixing() == 2) {
					List<Map<String, Object>> jieshourenids = beiwlService
							.getBeiWLRenByBeiWLIDAndRenIDAndRenLeiXing(paramMap);
					List<Map<String, Object>> huizhiids = beiwlService
							.getBeiWLHuiZhiByBeiWLIDAndRenIDAndRenLeiXing(paramMap);
					if (jieshourenids != null && jieshourenids.size() > 0) {
						for (Map<String, Object> string : jieshourenids) {
							int i = 0;
							for (Map<String, Object> strings : huizhiids) {
								if (string.containsKey("jieShouRenID") && strings.containsKey("huiZhiRenID")) {
									if (string.get("jieShouRenID").toString()
											.equals(strings.get("huiZhiRenID").toString())
											&& string.get("leiXing").toString()
													.equals(strings.get("leiXing").toString())) {
										Map<String, String> map = new HashMap<>();
										XueSheng xueSheng2 = xueshengService
												.getUserById(Integer.parseInt(strings.get("huiZhiRenID").toString()));
										String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng2.getBanjiid())
												.getBanjimingcheng();
										map.put("banji", banjimingcheng);
										map.put("xueshengid", xueSheng2.getXueshengid().toString());
										map.put("xuehao", xueSheng2.getXuehao());
										map.put("xingming", xueSheng2.getXingming());
										if (huizhi == 1) {
											map.put("zhuangtai", strings.get("zhuangTai").toString());
										}
										maps.add(map);
										i = 1;
									}
								}
							}
							if (i == 0) {
								Map<String, String> map = new HashMap<>();
								XueSheng xueSheng2 = xueshengService
										.getUserById(Integer.parseInt(string.get("jieShouRenID").toString()));
								String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng2.getBanjiid())
										.getBanjimingcheng();
								map.put("banji", banjimingcheng);
								map.put("xueshengid", xueSheng2.getXueshengid().toString());
								map.put("xuehao", xueSheng2.getXuehao());
								map.put("xingming", xueSheng2.getXingming());
								if (huizhi == 1) {
									map.put("zhuangtai", "0");
								}
								maps.add(map);
							}
						}
					}
					mv.addObject("shijian", beiWL);
					mv.addObject("xuesheng", maps);
					mv.setViewName("shuji/chakanjieshouren");
					return mv;
				}
				if (beiWL.getLeixing() == 1) {
					banjiids = beiwlService.getAllBanJiIDByBeiWLID(id);
					if (banjiids != null && banjiids.size() > 0) {
						String banjiid = request.getParameter("banjiid");
						for (String s : banjiids) {
							banjis.add(banjiService.selectByPrimaryKey(Integer.parseInt(s)));
						}
						if (banjiid == null || banjiid == "") {
							banjiid = banjiids.get(0);
						}
						if (!banjiids.contains(banjiid)) {
							response.sendRedirect("logout");
							return null;
						}
						BanJi bj = banjiService.selectByPrimaryKey(Integer.parseInt(banjiid));
						List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(banjiid));
						List<Map<String, Object>> huizhiids = beiwlService
								.getBeiWLHuiZhiByBeiWLIDAndRenIDAndRenLeiXing(paramMap);
						if (huizhiids == null || huizhiids.size() <= 0) {
							for (XueSheng xuess : xueShengs) {
								Map<String, String> map = new HashMap<>();
								map.put("banji", bj.getBanjimingcheng());
								map.put("xueshengid", xuess.getXueshengid().toString());
								map.put("xuehao", xuess.getXuehao());
								map.put("xingming", xuess.getXingming());
								if (huizhi == 1) {
									map.put("zhuangtai", "0");
								}
								maps.add(map);
							}
						} else {
							for (XueSheng xuess : xueShengs) {
								int i = 0;
								for (Map<String, Object> string : huizhiids) {
									if (xuess.getXueshengid().toString().equals(string.get("huiZhiRenID").toString())) {
										Map<String, String> map = new HashMap<>();
										map.put("banji", bj.getBanjimingcheng());
										map.put("xueshengid", xuess.getXueshengid().toString());
										map.put("xuehao", xuess.getXuehao());
										map.put("xingming", xuess.getXingming());
										if (huizhi == 1) {
											map.put("zhuangtai", string.get("zhuangTai").toString());
										}
										maps.add(map);
										i = 1;
									}
								}
								if (i == 0) {
									Map<String, String> map = new HashMap<>();
									map.put("banji", bj.getBanjimingcheng());
									map.put("xueshengid", xuess.getXueshengid().toString());
									map.put("xuehao", xuess.getXuehao());
									map.put("xingming", xuess.getXingming());
									if (huizhi == 1) {
										map.put("zhuangtai", "0");
									}
									maps.add(map);
								}
							}
						}
						mv.addObject("shijian", beiWL);
						mv.addObject("xuesheng", maps);
						mv.addObject("banjis", banjis);
						mv.addObject("bjid", bj.getBanjiid());
						mv.setViewName("shuji/chakanjieshourenbanji");
						return mv;
					} else {
						response.sendRedirect("login");
					}
				}
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
}
