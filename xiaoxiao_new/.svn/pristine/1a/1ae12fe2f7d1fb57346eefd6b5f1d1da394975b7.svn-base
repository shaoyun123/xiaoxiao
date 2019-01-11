package com.web.controller.web.student;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.web.annotation.LoginStatusAnnotation;
import com.web.model.JLNR;
import com.web.model.KeCheng;
import com.web.model.XueSheng;
import com.web.service.FDAPService;
import com.web.service.JLNRService;
import com.web.service.YongHuService;
import com.web.util.Util;

@Controller
@LoginStatusAnnotation(status="xuesheng")
public class JLNRController {
	@Autowired
	private JLNRService jlnrService;

	@Autowired
	private FDAPService fdapSrevice;
	
	@Autowired
	private YongHuService yonghuService;

	@RequestMapping(value = "fudaoanpai")//辅导安排列表
	public ModelAndView fudaoanpai(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			// System.out.println(user.getId());
			List<JLNR> jlnrs = jlnrService.getALLByxueShengID(user.getXueshengid());
			for (JLNR jlnr : jlnrs) {
				int fudaoyuanid = jlnr.getFudaoyuanid();
				String shenheren = yonghuService.selectYongHuByID(fudaoyuanid).getYonghuxingming();
				jlnr.setShenheren(shenheren);
				int anpaiid = jlnr.getAnpaiid();
				String jiezhiriqi = fdapSrevice.getjieZhiShiJianByanPaiID(anpaiid);
				jlnr.setJiezhiriqi(jiezhiriqi);
			}
			int count =jlnrs.size();
			int pageSize = 10;
			int page=1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<JLNR> jlnrs2=new ArrayList<>();
				if(count<10){
					for(int i=0;i<count;i++){
						jlnrs2.add(jlnrs.get(i));
					}
					mv.addObject("jlnr", jlnrs2);
				}
				else{
					for(int i=0;i<10;i++){
						jlnrs2.add(jlnrs.get(i));
					}
					mv.addObject("jlnr", jlnrs2);
				}
			}
			else{
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<JLNR> jlnrs2=new ArrayList<>();
							if(count<10){
								for(int i=(page-1)*10;i<count;i++){
									jlnrs2.add(jlnrs.get(i));
								}
								mv.addObject("jlnr", jlnrs2);
							}
							else {
								for(int i=(page-1)*10;i<(page*10);i++){
									jlnrs2.add(jlnrs.get(i));
								}
								mv.addObject("jlnr", jlnrs2);
							}
					}
					else if (page == pages) {
						List<JLNR> jlnrs2=new ArrayList<>();
						for(int i=(page-1)*10;i<count;i++){
							jlnrs2.add(jlnrs.get(i));
						}
						mv.addObject("jlnr", jlnrs2);
					}
					else{
						response.sendRedirect("logout");
						return null;
					}
				}
				else{
					response.sendRedirect("logout");
					return null;
				}
			}
			// System.out.println(jlnrs.size());
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("stu/fudaoanpai");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "shangchuanhuibao")//上传汇报
	public ModelAndView shangchuanhuibao(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			PrintWriter out = response.getWriter();
			String anpaiid = request.getParameter("anpaiid");
			String jiaoliuid = request.getParameter("jiaoliuid");
			Date date = new Date();
			String jiezhishijian = fdapSrevice.getjieZhiShiJianByanPaiID(Integer.parseInt(anpaiid));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
		    String now = sdf.format(date);
			if(now.compareTo(jiezhishijian)>0){
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('已超过截止日期，无法上传！');</script>");
				out.print("<script>location='fudaoanpai';</script>");
			}else{
				String mingcheng = fdapSrevice.getmingChengByanPaiID(Integer.parseInt(anpaiid));
				String yaoqiu = fdapSrevice.getyaoQiuByanPaiID(Integer.parseInt(anpaiid));
				String xueshengshangchuan = jlnrService.getxueShengShangChuanByjiaoLiuID(Integer.parseInt(jiaoliuid));
				// System.out.println(mingcheng);
				// System.out.println(yaoqiu);
				// System.out.println(jiezhishijian);
				mv.setViewName("stu/shangchuanhuibao");
				mv.addObject("mingcheng", mingcheng);
				mv.addObject("yaoqiu", yaoqiu);
				mv.addObject("jiezhishijian", jiezhishijian);
				mv.addObject("xueshengshangchuan", xueshengshangchuan);
				mv.addObject("jiaoliuid", jiaoliuid);
				return mv;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "tijiaohuibao")//提交汇报
	public void tijiaohuibao(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			request.setCharacterEncoding("UTF-8");
			String code = request.getParameter("huibao");
			String jiaoliuid = request.getParameter("jiaoliuid");
			String shangchuanzhuangtai = jlnrService.getshangchuanzhuangtaiByjiaoLiuID(Integer.parseInt(jiaoliuid));
			System.out.println(shangchuanzhuangtai);
			PrintWriter out = response.getWriter();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			if (shangchuanzhuangtai.equals("0")) {
				if (code != null && code != "") {
					map.put("jiaoliuid", jiaoliuid);
					map.put("xueshengid", user.getXueshengid().toString());
					map.put("shangchuanzhuangtai", "1");
					map.put("xueshengshangchuan", code);
					map.put("shangchuanriqi", df.format(date));
					System.out.println(map);
					jlnrService.updateByPrimaryKey(map);
					response.setContentType("text/html; charset=utf-8");
					out.print("<script>alert('提交成功');</script>");
					out.print("<script>location='fudaoanpai';</script>");
				} else {
					response.setContentType("text/html; charset=utf-8");
					out.print("<script>alert('内容不能为空');</script>");
					out.print("<script>location='fudaoanpai';</script>");
				}
			} else {
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('已经提交过，无法重复提交');</script>");
				out.print("<script>location='fudaoanpai';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
	}

	@RequestMapping(value = "chehui")//撤回汇报
	public ModelAndView chehuiortixing(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			// System.out.println(user.getId());
			List<JLNR> jlnrs = jlnrService.getALLByxueShengID(user.getXueshengid());
			mv.addObject("jlnr", jlnrs);
			mv.setViewName("stu/chehui");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "con")
	public void con(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String jiaoliuid = request.getParameter("id");
			String shenhezhuangtai = jlnrService.selectByPrimaryKey(Integer.parseInt(jiaoliuid)).getShenhezhuangtai();
			if(shenhezhuangtai.equals("0")){
				map.put("jiaoliuid", jiaoliuid);
				map.put("shangchuanzhuangtai", "0");
				jlnrService.updateshangchuanzhuangtaiByjiaoLiuID(map);
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>location='fudaoanpai';</script>");
			}else{
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('已经审核，无法撤回！');</script>");
				out.print("<script>location='fudaoanpai';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
	}

	@RequestMapping(value = "jiaoliuneirong")//交流内容列表
	public ModelAndView jiaoliuneirong(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			// System.out.println(user.getId());
			List<JLNR> jlnrs = jlnrService.getALLByxueShengID(user.getXueshengid());
			for (JLNR jlnr : jlnrs) {
				int fudaoyuanid = jlnr.getFudaoyuanid();
				String shenheren = yonghuService.selectYongHuByID(fudaoyuanid).getYonghuxingming();
				jlnr.setShenheren(shenheren);
			}
			int count =jlnrs.size();
			int pageSize = 10;
			int page=1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<JLNR> jlnrs2=new ArrayList<>();
				if(count<10){
					for(int i=0;i<count;i++){
						jlnrs2.add(jlnrs.get(i));
					}
					mv.addObject("jlnr", jlnrs2);
				}
				else{
					for(int i=0;i<10;i++){
						jlnrs2.add(jlnrs.get(i));
					}
					mv.addObject("jlnr", jlnrs2);
				}
			}
			else{
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<JLNR> jlnrs2=new ArrayList<>();
							if(count<10){
								for(int i=(page-1)*10;i<count;i++){
									jlnrs2.add(jlnrs.get(i));
								}
								mv.addObject("jlnr", jlnrs2);
							}
							else {
								for(int i=(page-1)*10;i<(page*10);i++){
									jlnrs2.add(jlnrs.get(i));
								}
								mv.addObject("jlnr", jlnrs2);
							}
					}
					else if (page == pages) {
						List<JLNR> jlnrs2=new ArrayList<>();
						for(int i=(page-1)*10;i<count;i++){
							jlnrs2.add(jlnrs.get(i));
						}
						mv.addObject("jlnr", jlnrs2);
					}
					else{
						response.sendRedirect("logout");
						return null;
					}
				}
				else {
					response.sendRedirect("logout");
					return null;
				}
			}
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("stu/jiaoliuneirong");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "jiaoliuneirongjuti")
	public ModelAndView jiaoliuneirongjuti(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String jiaoliuid = request.getParameter("id");
			String xueshengshangchuan = jlnrService.getxueShengShangChuanByjiaoLiuID(Integer.parseInt(jiaoliuid));
			String fudaoyuanshenhe = jlnrService.getfuDaoYuanShenHeByjiaoLiuID(Integer.parseInt(jiaoliuid));
			String jiaoliumingcheng = jlnrService.getjiaoLiuMingChengByjiaoLiuID(Integer.parseInt(jiaoliuid));
			mv.addObject("xueshengshangchuan", xueshengshangchuan);
			mv.addObject("fudaoyuanshenhe", fudaoyuanshenhe);
			mv.addObject("jiaoliumingcheng", jiaoliumingcheng);
			mv.setViewName("stu/jiaoliuneirongjuti");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

}
