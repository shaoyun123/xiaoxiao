package com.web.controller.web.fudaoyuan;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.web.annotation.LoginStatusAnnotation;
import com.web.model.BanJi;
import com.web.model.FuDaoYuan;
import com.web.model.JLNR;
import com.web.model.KeCheng;
import com.web.model.SuSheLou;
import com.web.model.XueSheng;
import com.web.model.XueShengSuShe;
import com.web.model.YongHu;
import com.web.service.BanJiService;
import com.web.service.FuDaoYuanService;
import com.web.service.NianFenService;
import com.web.service.SuSheLouService;
import com.web.service.XueShengService;
import com.web.service.XueShengSuSheService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

@Controller
@LoginStatusAnnotation(status="fudaoyuan")
public class BanJiGuanLiController_fdy {
	static Logger logger = Logger.getLogger(BanJiGuanLiController_fdy.class);
	
	@Autowired
	private FuDaoYuanService fuDaoYuanService;
	
	@Autowired
	private BanJiService banJiService;
	@Autowired
	private YuanXiService yuanXiService;
	@Autowired
	private XueShengService xueShengService;
	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private XueShengSuSheService xueShengSuSheService;
	@Autowired
	private SuSheLouService suSheLouService;
	@Autowired
	private NianFenService nianfenService;
	
	@RequestMapping(value="bjgl")
	public ModelAndView BanJiGuanLi(HttpServletRequest request,HttpServletResponse response) throws IOException, Exception {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String bj = request.getParameter("bj")==null? "1" :request.getParameter("bj");
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = sdf.format(new Date());
		List<BanJi> banJis = new ArrayList<>();
		if (fuDaoYuan.getBanjiid()!=null&&!"".equals(fuDaoYuan.getBanjiid())) {
			String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
			BanJi banJi = null;
			for (int i = 0; i < banJiIDs.length; i++) {
				banJi = new BanJi();
				banJi = banJiService.selectByPrimaryKey(Integer.parseInt(banJiIDs[i]));
				if (banJi !=null) {
					if(bj.equals("1")){
						Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid()).getRuxuenianfen();
						String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
						if (sdf.parse(biYeNianFen).getTime() > sdf.parse(nowTime).getTime()) {
							banJi.setYuanximingcheng(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
							banJis.add(banJi);
						}
					}else{
						banJi.setYuanximingcheng(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
						banJis.add(banJi);
					}
				}
			}
		}
		int count = banJis.size();
		int pageSize = 10;
		int page = 1;
		int pages = (count / pageSize) + 1;
		if (count % pageSize == 0) {
			pages = (count / pageSize);
		}
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			List<BanJi> banJis2 = new ArrayList<>();
			if (count < 10) {
				for (int i = 0; i < count; i++) {
					banJis2.add(banJis.get(i));
				}
				mView.addObject("banjis", banJis2);
			} else {
				for (int i = 0; i < 10; i++) {
					banJis2.add(banJis.get(i));
				}
				mView.addObject("banjis", banJis2);
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
						mView.addObject("banjis", banJis2);
					} else {
						for (int i = (page - 1) * 10; i < (page * 10); i++) {
							banJis2.add(banJis.get(i));
						}
						mView.addObject("banjis", banJis2);
					}
				} else if (page == pages) {
					List<BanJi> banJis2 = new ArrayList<>();
					for (int i = (page - 1) * 10; i < count; i++) {
						banJis2.add(banJis.get(i));
					}
					mView.addObject("banjis", banJis2);
				} else {
					response.sendRedirect("logout");
					return null;
				}
			} else {
				response.sendRedirect("logout");
				return null;
			}
		}
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.setViewName("fudaoyuan/banji");
		return mView;
	}
	
	@RequestMapping(value="addbanji")
	public ModelAndView addBanJi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		List<BanJi> banJis = banJiService.getAllByYuanXiID(user.getYuanxiid());
		if (!banJis.isEmpty()) {
			if (fuDaoYuan.getBanjiid()!=null&&fuDaoYuan.getBanjiid()!="") {
				String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
				Iterator<BanJi> iterator = banJis.iterator();
				while (iterator.hasNext()){
					BanJi banJi = iterator.next(); 
					boolean isIn = false;
					for (int i = 0; i < banJiIDs.length; i++) {
						logger.info(banJiIDs[i]+"   "+banJi.getBanjiid());
						if (banJiIDs[i].equals(banJi.getBanjiid().toString())) {
							isIn = true;
							logger.info(banJi.getBanjiid()+"isIn banJiIDs");
						}else {
							continue;
						}
					}
					if (isIn) {
						logger.info("remove"+banJi.getBanjimingcheng());
						iterator.remove();
					}
				}
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("fudaoyuan/addbanji");
		mView.addObject("banjis", banJis);
		return mView;
	}
	
	@RequestMapping(value="subaddbanji")
	@ResponseBody
	public String subAddBanJi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		String banJiIDs[] =request.getParameter("CODE").split(",");
		BanJi banJi = null;
		String banJis[] = fuDaoYuan.getBanjiid().split(","); 
		StringBuffer sBuffer = new StringBuffer(fuDaoYuan.getBanjiid());
		for (int i = 0; i < banJiIDs.length; i++) {
			banJi = new BanJi();
			banJi = banJiService.selectByPrimaryKey(Integer.parseInt(banJiIDs[i]));
			if (banJi!=null) {
				if (!banJi.getYuanxiid().equals(user.getYuanxiid())) {
					response.sendRedirect("logout");
					return null;
				}else {
					boolean isIn = false;
					for (int j = 0; j < banJis.length; j++) {
						if (banJis[j].equals(banJiIDs[i])) {
							isIn = true;
						} else {
							continue;
						}
					}
					if (isIn == false) {
						sBuffer.append(banJiIDs[i]+",");
					}
				}
			}else {
				response.sendRedirect("logout");
				return null;
			}
		}
		fuDaoYuan.setBanjiid(sBuffer.toString());
		int i  = fuDaoYuanService.updateByFuDaoYuan(fuDaoYuan);
		if (i>0) {
			return "success";
		}else {
			return "fail";
		}
	}
	@RequestMapping(value="delbanji")
	@ResponseBody
	public String delBanJi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		String banJiID = request.getParameter("CODE");
		logger.info(banJiID);
		String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < banJiIDs.length; i++) {
			if (banJiIDs[i].equals(banJiID)) {
				continue;
			}else {
				sBuffer.append(banJiIDs[i]+",");
			}
		}
		fuDaoYuan.setBanjiid(sBuffer.toString());
		int i = fuDaoYuanService.updateByFuDaoYuan(fuDaoYuan);
		if (i>0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@RequestMapping(value="banji")
	public ModelAndView banJi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView = new ModelAndView();
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		String banJiID = request.getParameter("id");
		BanJi banJi2=banJiService.selectByPrimaryKey(Integer.parseInt(banJiID));
		String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
		boolean isIn = false;
		List<BanJi> banJis=new ArrayList<>();
		for(int i=0;i<banJiIDs.length;i++){
			BanJi banJi=banJiService.selectByPrimaryKey(Integer.parseInt(banJiIDs[i]));
			banJis.add(banJi);
		}
		for (int i = 0; i < banJiIDs.length; i++) {
			if (banJiID.equals(banJiIDs[i])) {
				isIn = true;
			} else {
				continue;
			}
		}
		if (isIn == false) {
			response.sendRedirect("logout");
			return null;
		}
		List<XueSheng> xueShengList = xueShengService.getAllByBanJiID(Integer.parseInt(banJiID));
		List<Object> list=new ArrayList<>();
		for(int i=0;i<xueShengList.size();i++){
			Map<String, Object> map=new HashMap<>();
			XueShengSuShe xueShengSuShe=xueShengSuSheService.selectByPrimaryKey(xueShengList.get(i).getSusheid());
			SuSheLou suSheLou=null;
			if(xueShengSuShe==null||"".equals(xueShengSuShe)){
				suSheLou=null;
			}
			else{
				suSheLou=suSheLouService.selectByPrimaryKey(xueShengSuShe.getSuSheLouId());
			}
			map.put("xuesheng", xueShengList.get(i));
			map.put("xueshengsushe", xueShengSuShe);
			map.put("sushelou", suSheLou);
			list.add(map);
		}
		int count =list.size();
		int pageSize = 10;
		int page=1;
		int pages = (count / pageSize) + 1;
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			List<Object> list2=new ArrayList<>();
			if(count<10){
				for(int i=0;i<count;i++){
					list2.add(list.get(i));
				}
				mView.addObject("xueshengs", list2);
			}
			else{
				for(int i=0;i<10;i++){
					list2.add(list.get(i));
				}
				mView.addObject("xueshengs", list2);
			}
		}
		else{
			if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<Object> list2=new ArrayList<>();
						if(count<10){
							for(int i=(page-1)*10;i<count;i++){
								list2.add(list.get(i));
							}
							mView.addObject("xueshengs", list2);
						}
						else {
							for(int i=(page-1)*10;i<(page*10);i++){
								list2.add(list.get(i));
							}
							mView.addObject("xueshengs", list2);
						}
				}
				else if (page == pages) {
					List<Object> list2=new ArrayList<>();
					for(int i=(page-1)*10;i<count;i++){
						list2.add(list.get(i));
					}
					mView.addObject("xueshengs", list2);
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
		mView.addObject("banjis", banJis);
		mView.addObject("banji2", banJi2);
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.setViewName("fudaoyuan/banjirenyuan");
		return mView;
	}
	
	@RequestMapping(value="updatePassWord")
	@ResponseBody
	public String updatePassWord(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
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
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
		String banJiId=xueShengService.selectByPrimaryKey(Integer.parseInt(xueShengID)).getBanjiid().toString();
		//判断学生是否在辅导员管理的班级内
		boolean isIn = false;
		for (int j = 0; j < banJiIDs.length; j++) {
			if (banJiId.equals(banJiIDs[j])) {
				isIn = true;
			}
		}
		if (isIn == false) {
			response.sendRedirect("logout");
			return null;
		}
		int i=xueShengService.updatePassWdByID(xueHao, Integer.parseInt(xueShengID));
		if(i!=0){
			Map<String,Object> parmMap = new HashMap<String,Object>();
			parmMap.put("password", xueHao);
			parmMap.put("xuexiaoxuehao", xuexiaoxuehao);
			parmMap.put("status", "xuesheng");
			int c = yongHuService.updatePasswordKaoShiById(parmMap);
			if(c > 0){
				String shunxuhao = System.currentTimeMillis()+"";
				String dizhiliebiao = "2,3";
				String tongbuneirong = "update xuesheng set miMaMD5 = '"+xueHao+"' where xuexiao_xuehao = '"+xuexiaoxuehao+"' ";
				String leimingcheng = "com.ccbupt.kaoshi.dao.XueSheng";
				Map<String,Object> pMap = new HashMap<String,Object>();
				pMap.put("shunxuhao", shunxuhao);
				pMap.put("dizhiliebiao", dizhiliebiao);
				pMap.put("tongbuneirong", tongbuneirong);
				pMap.put("leimingcheng", leimingcheng);
				yongHuService.insertKaoshiTongBuFaSong(pMap);
				
				//String leimingcheng = "com.ccbupt.kaoshi.dao.YongHu";
			}
			return "success";
		}
		else{
			return "fail";
		}
	}
	
	@RequestMapping(value = "show_MyStudents")//挑选班长——根据班级显示我的学生
	@ResponseBody
	public List<Map<String, String>> show_MyStudents(HttpServletRequest request,HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		HttpSession session = request.getSession();
		if (Util.checkSession(request)){
			YongHu user = (YongHu) session.getAttribute("user");
			String banjiid = request.getParameter("CODE");
			List<Map<String, String>> xueShengs = new ArrayList<>();
			if(banjiid.equals("")){
				String banjiids = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid();
				String banjiid2[] = banjiids.split(",");
				for (String string : banjiid2) {
					List<XueSheng> xueShengs2 = xueShengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs2) {
						Map<String, String> map = new HashMap<>();
						String banji = banJiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng();
						map.put("banjiid", xueSheng.getBanjiid().toString());
						map.put("banji", banji);
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						xueShengs.add(map);
					}
				}
			}else{
				List<XueSheng> xueShengs2 = xueShengService.getAllByBanJiID(Integer.parseInt(banjiid));
				for (XueSheng xueSheng : xueShengs2) {
					Map<String, String> map = new HashMap<>();
					String banji = banJiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng();
					map.put("banjiid", xueSheng.getBanjiid().toString());
					map.put("banji", banji);
					map.put("xueshengid", xueSheng.getXueshengid().toString());
					map.put("xuehao", xueSheng.getXuehao());
					map.put("xingming", xueSheng.getXingming());
					xueShengs.add(map);
				}
			}
			return xueShengs;
		}else{
			response.sendRedirect("login");
			return null;
		}
	}
	
	/*@RequestMapping(value="setBanZhang")
	@ResponseBody
	public String SetBanZhang(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String xueShengID = request.getParameter("CODE");
		if (!Util.isNumeric(xueShengID)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		XueSheng xueSheng = xueShengService.getUserById(Integer.parseInt(xueShengID));
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
		String banJiID = xueSheng.getBanjiid().toString();
		boolean isIn = false;
		for (int i = 0; i < banJiIDs.length; i++) {
			if (banJiID.equals(banJiIDs[i])) {
				isIn = true;
			}
		}
		if (isIn == false) {
			response.sendRedirect("logout");
			return null;
		}
		xueSheng.setIsbanzhang(true);
		Boolean b = xueShengService.updateBanZhangByXueSheng(xueSheng);
		if (b) {
			return "success";
		}else {
			return "fail";
		}
	}*/
	@ResponseBody
	@RequestMapping(value="setBanZhang")
	public Object setBanZhang(HttpServletRequest request,HttpServletResponse response,String xueShengIds) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		Map<String, Object> map = new HashMap<>();
		String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
		String[] xueShengIDs =request.getParameter("canyuren").split(",");
		for(int i=0;i<xueShengIDs.length;i++){
			XueSheng xueSheng = xueShengService.getUserById(Integer.parseInt(xueShengIDs[i]));
			String banJiID = xueSheng.getBanjiid().toString();
			//判断学生是否在辅导员管理的班级内
			boolean isIn = false;
			for (int j = 0; j < banJiIDs.length; j++) {
				if (banJiID.equals(banJiIDs[j])) {
					isIn = true;
				}
			}
			if (isIn == false) {
				response.sendRedirect("logout");
				return null;
			}
			xueSheng.setIsbanzhang(true);
			Boolean b = xueShengService.updateBanZhangByXueSheng(xueSheng);
			if (b) {
				map.put("status", "success");
			}else {
				map.put("status", "fail");
			}
			return JSON.toJSON(map);
		}
		return null;
	}
	
	
	@RequestMapping(value = "addstudent")//添加学生
	public  ModelAndView addstudent(HttpServletRequest request,HttpServletResponse response)throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mv=new ModelAndView();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu)request.getSession().getAttribute("user");
			FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
			String [] banJiIds=fuDaoYuan.getBanjiid().split(",");
			List<BanJi> banJis=new ArrayList<>();
			for(int i=0;i<banJiIds.length;i++){
				BanJi banJi=banJiService.selectByPrimaryKey(Integer.parseInt(banJiIds[i]));
				banJis.add(banJi);
			}
			mv.addObject("banjis",banJis);
			mv.setViewName("fudaoyuan/addstudent");
			return mv;
		}
		else{
			response.sendRedirect("login");
		}
		return null;
		}
	
	@RequestMapping(value = "savestudent")//保存添加
	@ResponseBody
	public  String savestudent(HttpServletRequest request,HttpServletResponse response)throws IOException  {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		if (Util.checkSession(request)) {
			HttpSession session=request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out=response.getWriter();
			String banJiId=request.getParameter("banjiid");
			String xingMing=request.getParameter("xingming");
			String xueHao=request.getParameter("xuehao");
			Integer xueXiaoId=(Integer)session.getAttribute("xuexiaoid");
			List<XueSheng> xueShengs=xueShengService.getAllXueSheng(xueXiaoId.toString()+"_"+"%");
			List<String> xueHaoList=new ArrayList<>();
			for(int i=0;i<xueShengs.size();i++){
				xueHaoList.add(xueShengs.get(i).getXuehao());
			}
			if(xueHaoList.contains(xueHao)){
				XueSheng xueSheng=xueShengService.selectXueShengByXueXiaoXueHao(((Integer)session.getAttribute("xuexiaoid")).toString()+"_"+xueHao);
				xueSheng.setBanjiid(Integer.parseInt(banJiId));
				xueSheng.setXingming(xingMing);
				int j=xueShengService.updateByPrimaryKeySelective(xueSheng);
				if(j!=0){
//					out.print("<script>alert('更新成功!');</script>");
//					out.print("<script>location='bjgl';</script>");
					return "gengxin";
				}
				else {
//					out.print("<script>alert('更新失败!');</script>");
//					out.print("<script>location='bjgl';</script>");
				}
			}
			else{
				String xueXiao_XueHao=xueXiaoId.toString()+"_"+xueHao;
				String yongHuMing=xueXiaoId.toString()+xueHao;
				String miMa=xueHao;
				XueSheng xueSheng=new XueSheng();
				xueSheng.setBanjiid(Integer.parseInt(banJiId));
				xueSheng.setXingming(xingMing);
				xueSheng.setXuehao(xueHao);
				xueSheng.setMimamd5(miMa);
				xueSheng.setYonghuming(yongHuMing);
				xueSheng.setIsbanzhang(false);
				xueSheng.setXuexiaoXuehao(xueXiao_XueHao);
				int j=xueShengService.insert(xueSheng);
				if(j!=0){
//					out.print("<script>alert('添加成功!');</script>");
//					out.print("<script>location='bjgl';</script>");
					return "success";
				}
				else {
//					out.print("<script>alert('添加失败!');</script>");
//					out.print("<script>location='bjgl';</script>");
				}
			}
		}else{
			response.sendRedirect("login");
		}
		return null;
	} 
	
	@RequestMapping(value = "modifystudent")//修改学生信息	
	public ModelAndView modifyxuesheng (HttpServletRequest request,HttpServletResponse response)throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mv=new ModelAndView();
		if(Util.checkSession(request)){
			String xueShengId=request.getParameter("id");
			XueSheng xueSheng=xueShengService.selectByPrimaryKey(Integer.parseInt(xueShengId));
			String banJiId=xueSheng.getBanjiid().toString();
			BanJi banJi2=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
			YongHu user = (YongHu)request.getSession().getAttribute("user");
			FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
			String [] banJiIds=fuDaoYuan.getBanjiid().split(",");
			//判断学生是否在辅导员管理的班级内
			boolean isIn = false;
			for (int j = 0; j < banJiIds.length; j++) {
				if (banJiId.equals(banJiIds[j])) {
					isIn = true;
				}
			}
			if (isIn == false) {
				response.sendRedirect("logout");
				return null;
			}
			List<BanJi> banJis=new ArrayList<>();
			for(int i=0;i<banJiIds.length;i++){
				BanJi banJi=banJiService.selectByPrimaryKey(Integer.parseInt(banJiIds[i]));
				banJis.add(banJi);
			}
			mv.addObject("banjis",banJis);
			mv.addObject("banji2",banJi2);
			mv.addObject("xuesheng",xueSheng);
			mv.setViewName("fudaoyuan/modifystudent");
			return mv;
		}else{
			response.sendRedirect("login");
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "savemodifystudent")//保存修改	
	public Object savemodifyxuesheng(HttpServletRequest request,HttpServletResponse response)throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		HttpSession session=request.getSession();
		if(Util.checkSession(request)){
			Map<String, Object> map = new HashMap<>();
			String xueShengId=request.getParameter("id");
			String banJiId=request.getParameter("banjiid");
			String xueShengXingMing=request.getParameter("xueshengxingming");
			XueSheng xueSheng=xueShengService.selectByPrimaryKey(Integer.parseInt(xueShengId));
			xueSheng.setBanjiid(Integer.parseInt(banJiId));
			xueSheng.setXingming(xueShengXingMing);
			int j=xueShengService.updateByPrimaryKey(xueSheng);
				if(j!=0){
					map.put("status", "success");
					session.setAttribute("banjiid", banJiId);
				}else{
					map.put("status", "fail");
				}
				return JSON.toJSON(map);
		}else{
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value="delStudent")//删除学生
	@ResponseBody
	public String delStudent(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		String [] banJiIds=fuDaoYuan.getBanjiid().split(",");
		String xueShengId = request.getParameter("CODE");
		String banJiId=xueShengService.selectByPrimaryKey(Integer.parseInt(xueShengId)).getBanjiid().toString();
		if(!Util.isNumeric(xueShengId)){
			response.sendRedirect("logout");
			return null;
		}
		//判断学生是否在辅导员管理的班级内
		boolean isIn = false;
		for (int j = 0; j < banJiIds.length; j++) {
			if (banJiId.equals(banJiIds[j])) {
				isIn = true;
			}
		}
		if (isIn == false) {
			response.sendRedirect("logout");
			return null;
		}
		int j=xueShengService.deleteByPrimaryKey(Integer.parseInt(xueShengId));
			if (j!=0) {
				return "success";
			}else {
				return "fail";
			}
	}
}
