package com.web.controller.web.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.web.controller.web.common.util.DownloadUtil;
import com.web.model.BanJi;
import com.web.model.BeiWL;
import com.web.model.ChaQinAnPai;
import com.web.model.FuDaoYuan;
import com.web.model.MemoryData;
import com.web.model.Menu;
import com.web.model.TiXing;
import com.web.model.XueSheng;
import com.web.model.XueShengChuGuanLiYuan;
import com.web.model.XueXiao;
import com.web.model.YongHu;
import com.web.model.YuanXi;
import com.web.model.ZhuanYe;
import com.web.service.BanJiService;
import com.web.service.BeiWLService;
import com.web.service.ChaQinService;
import com.web.service.FuDaoYuanService;
import com.web.service.MenuService;
import com.web.service.QingjiaService;
import com.web.service.TiXingService;
import com.web.service.XueShengChuGuanLiYuanService;
import com.web.service.XueShengService;
import com.web.service.XueXiaoService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.service.ZhuanYeService;
import com.web.util.Util;

/*登录相关*/

@Controller
public class LoginController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private XueShengService xueShengService;
	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private TiXingService tixingService;
	@Autowired
	private XueXiaoService xueXiaoService;
	@Autowired
	private YuanXiService yuanXiService;
	@Autowired 
	private BanJiService banJiService;
	@Autowired
	private ZhuanYeService zhuanYeService;
	@Autowired
	private XueShengChuGuanLiYuanService guanLiYuanService;
	@Autowired
	private FuDaoYuanService fuDaoYuanService;
	@Autowired
	private QingjiaService qingjiaService;
	@Autowired
	private ChaQinService chaQinService;
	@Autowired
	private BeiWLService beiWLService;
	// 从index访问登录页
	@RequestMapping(value = "/login_toLogin")
	public ModelAndView initToLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("user")!=null) {
			response.sendRedirect("index");
			return null;
		}else {
			mv.setViewName("login/login");
		}
		return mv;
	}

	// 接受前端传递的用户名、密码、身份等参数，在数据库中查询，根据结果给前端返回相应的retInfo
	@RequestMapping(value = "tologin")
	@ResponseBody
	public String toLogin(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String retInfo = "";
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		map.put("id", data[0]);
		map.put("password", data[1]);
		map.put("status", data[2]);
		if (data[2].equals("xuesheng")) {
			XueSheng xueSheng =xueShengService.selectUserByIdAndPasswd(map);
			if (xueSheng != null) {
				xueSheng.setStatus("xuesheng");
				HttpSession session = request.getSession();
				String sessionID = request.getRequestedSessionId();
				String user = xueSheng.getXueshengid().toString();
				if (!MemoryData.getSessionIDMap().containsKey(user)) { //不存在，首次登陆，放入Map
					MemoryData.getSessionIDMap().put(user, sessionID);
				}else if(MemoryData.getSessionIDMap().containsKey(user)&&(!sessionID.equals(MemoryData.getSessionIDMap().get(user)))){
					MemoryData.getSessionIDMap().remove(user);
					MemoryData.getSessionIDMap().put(user, sessionID);
				}
				session.setAttribute("user", xueSheng);
				session.setAttribute("xingming", xueSheng.getXingming());
				session.setAttribute("status", "xuesheng");
				retInfo = "success";
			} else {
				retInfo = "fail";
			}
		}
		else if (data[2].equals("fudaoyuan")) {
			YongHu yongHu = yongHuService.selectFuDaoYuanByIdAndPasswd(map);
			if (yongHu != null) {
				yongHu.setStatus("fudaoyuan");
				HttpSession session = request.getSession();
				String sessionID = request.getRequestedSessionId();
				String user = yongHu.getYonghuid().toString();
				if (!MemoryData.getSessionIDMap().containsKey(user)) { //不存在，首次登陆，放入Map
					MemoryData.getSessionIDMap().put(user, sessionID);
				}else if(MemoryData.getSessionIDMap().containsKey(user)&&(!sessionID.equals(MemoryData.getSessionIDMap().get(user)))){
					MemoryData.getSessionIDMap().remove(user);
					MemoryData.getSessionIDMap().put(user, sessionID);
				}
				session.setAttribute("user", yongHu);
				session.setAttribute("xingming", yongHu.getYonghuxingming());
				session.setAttribute("status", "fudaoyuan");
				YuanXi yuanXi=yuanXiService.selectByPrimaryKey(yongHu.getYuanxiid());
				Integer xueXiaoId=yuanXi.getXuexiaoid();
				session.setAttribute("xuexiaoid", xueXiaoId);
				retInfo = "success";
			} else {
				retInfo = "fail";
			}
		}
		else if(data[2].equals("shuji")) {
			YongHu yongHu = yongHuService.selectShuJiByIdAndPasswd(map);
			if (yongHu!=null) {
				yongHu.setStatus("shuji");
				HttpSession session = request.getSession();
				session.setAttribute("user", yongHu);
				String sessionID = request.getRequestedSessionId();
				String user = yongHu.getYonghuid().toString();
				if (!MemoryData.getSessionIDMap().containsKey(user)) { //不存在，首次登陆，放入Map
					MemoryData.getSessionIDMap().put(user, sessionID);
				}else if(MemoryData.getSessionIDMap().containsKey(user)&&(!sessionID.equals(MemoryData.getSessionIDMap().get(user)))){
					MemoryData.getSessionIDMap().remove(user);
					MemoryData.getSessionIDMap().put(user, sessionID);
				}
				session.setAttribute("xingming", yongHu.getYonghuxingming());
				session.setAttribute("status", "shuji");
				retInfo = "success";
			}else {
				retInfo = "fail";
			}
		}
		else if(data[2].equals("jiaoshi")) {
			YongHu yongHu = yongHuService.selectJiaoShiByIdAndPasswd(map);
			if (yongHu!=null) {
				yongHu.setStatus("jiaoshi");
				HttpSession session = request.getSession();
				session.setAttribute("user", yongHu);
				String sessionID = request.getRequestedSessionId();
				String user = yongHu.getYonghuid().toString();
				if (!MemoryData.getSessionIDMap().containsKey(user)) { //不存在，首次登陆，放入Map
					MemoryData.getSessionIDMap().put(user, sessionID);
				}else if(MemoryData.getSessionIDMap().containsKey(user)&&(!sessionID.equals(MemoryData.getSessionIDMap().get(user)))){
					MemoryData.getSessionIDMap().remove(user);
					MemoryData.getSessionIDMap().put(user, sessionID);
				}
				session.setAttribute("xingming", yongHu.getYonghuxingming());
				session.setAttribute("status", "jiaoshi");
				retInfo = "success";
			}else {
				retInfo = "fail";
			}
		}
		else if (data[2].equals("guanliyuan")) {
			YongHu yongHu = yongHuService.selectXueShengChuGuanLiYuanByIdAndPasswd(map);
			if (yongHu!=null) {
				yongHu.setStatus("guanliyuan");
				HttpSession session = request.getSession();
				session.setAttribute("user", yongHu);
				String sessionID = request.getRequestedSessionId();
				String user = yongHu.getYonghuid().toString();
				if (!MemoryData.getSessionIDMap().containsKey(user)) { //不存在，首次登陆，放入Map
					MemoryData.getSessionIDMap().put(user, sessionID);
				}else if(MemoryData.getSessionIDMap().containsKey(user)&&(!sessionID.equals(MemoryData.getSessionIDMap().get(user)))){
					MemoryData.getSessionIDMap().remove(user);
					MemoryData.getSessionIDMap().put(user, sessionID);
				}
				session.setAttribute("xingming", yongHu.getYonghuxingming());
				session.setAttribute("status", "guanliyuan");
				YuanXi yuanXi=yuanXiService.selectByPrimaryKey(yongHu.getYuanxiid());
				Integer xueXiaoId=yuanXi.getXuexiaoid();
				session.setAttribute("xuexiaoid", xueXiaoId);
				retInfo = "success";
			}else {
				retInfo = "fail";
			}
		}
		return retInfo;

	}
	
	
	@RequestMapping(value = "downApp")
	@ResponseBody
	public void downApp(HttpServletRequest request,HttpServletResponse response) {

    	ServletOutputStream sos = null;
    	FileInputStream fis = null;
    	try {
			String contentType = getContentType("xiaoyuan.apk");
			String filePath = request.getSession().getServletContext().getRealPath("/")+"static"+File.separator +"xuaoyuan.apk";

			HttpServletResponse res =response;
			sos = res.getOutputStream();
			res.setContentType(contentType + "; charset=UTF-8");
			res.setHeader("Content-disposition", "attachment; filename=" + new String("xuaoyuan.apk".getBytes("UTF-8"), "ISO-8859-1"));
			
			fis = new FileInputStream(filePath); //读入文件
	        sos.flush();
	        byte[] data = new byte[1024 * 10];
	        int len = 0;

	        while ((len = fis.read(data)) > 0) {
	            sos.write(data, 0, len);
	        }
	        sos.flush();
	       
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
			if(sos != null) {
				try {
					sos.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		}

	}
	
	private String getContentType(String fileName) {
		List<String> resContList = DownloadUtil.getResContList();
		String contentType = "application/x-msdownload";
		if (fileName.indexOf(".") > -1) {
			String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
			int size = resContList.size();
			String contTypeName = "";
			fileType = fileType.toLowerCase();
			if("mp4".equals(fileType)) {
				fileType = "mpeg";
			}
			for (int i = 0; i < size; i++) {
				contTypeName = (String) resContList.get(i);
				if (contTypeName.indexOf(fileType) > -1) {
					contentType = contTypeName;
					break;
				}
			}
		}
		return contentType;
	}

	@RequestMapping(value = "index")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Menu> menuList = new ArrayList<Menu>();
		List<Menu> menus =new ArrayList<>();
		Integer id = null;
		HttpSession session= request.getSession();
		ModelAndView mView = new ModelAndView();
		if (request.getSession().getAttribute("menu")==null || request.getSession().getAttribute("menu")=="") {
			if (session.getAttribute("status").equals("xuesheng")) {
				XueSheng user = (XueSheng) session.getAttribute("user");
				id = user.getXueshengid();
				menus = menuService.getAllMenuByStatus(user.getStatus().toString());
				}
			else{
				YongHu user =(YongHu) session.getAttribute("user");
				id = user.getYonghuid();
				menus = menuService.getAllMenuByStatus(user.getStatus().toString());
				}
			for (int i = 0; i < menus.size(); i++) {
				// 一级菜单Fatherid为-1
				if (menus.get(i).getFatherid() == -1) {
					menuList.add(menus.get(i));
				}
			}
			// 为一级菜单设置子菜单，getChild是递归调用的
			for (Menu menu : menuList) {
				menu.setChildMenus(getChild(menu.getId(), menus));
			}
			session.setAttribute("menu", menuList);
		}else {
			if (Util.isXueSheng(request)) {
				XueSheng user = (XueSheng) request.getSession().getAttribute("user");
				id = user.getXueshengid();
			}else {
				YongHu user = (YongHu)request.getSession().getAttribute("user");
				id = user.getYonghuid();
			}
		}
		List<TiXing> tiXings = tixingService.getAllByjieShouRenID(id);
		int sum =0;
		for (TiXing tiXing : tiXings) {
			if (tiXing.getZhuangtai()==0) {
				sum++;
			}
		}
		
		if(session.getAttribute("status").toString()=="fudaoyuan"){
			YongHu user=(YongHu)session.getAttribute("user");
			FuDaoYuan fuDaoYuan=fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
			if (fuDaoYuan==null) {
				response.sendRedirect("logout");
				return null;
			}
			//获得请假待处理数量
			if (fuDaoYuan.getBanjiid()!=null&&!fuDaoYuan.getBanjiid().equals("")) {
				String banJiIDs = fuDaoYuan.getBanjiid().substring(0, fuDaoYuan.getBanjiid().length()-1);
				int count = qingjiaService.selectCountFuDaoYuanDaiChuLiByBanJiIDs(banJiIDs);
				mView.addObject("qjcount", count);
			}
//			Map<String,String> paramMap = new HashMap<>();
//			paramMap.put("fudaoyuanid", fuDaoYuan.getFudaoyuanid().toString());
			//获取查寝安排数量
			List<ChaQinAnPai> chaQinAnPais=chaQinService.selectByYongHuId(fuDaoYuan.getFudaoyuanid());
			List<ChaQinAnPai> chaQinAnPais2=new ArrayList<>();
			Date date =new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String kaiShiRiQi=  simpleDateFormat.format(date);
			if(chaQinAnPais!=null&&"".equals(chaQinAnPais)){
				for(ChaQinAnPai chaQinAnPai : chaQinAnPais){
					if(chaQinAnPai.getRiqi()==kaiShiRiQi){
						chaQinAnPais2.add(chaQinAnPai);
					}
					else{
						continue;
					}
				}
				int countChaqinanPai=chaQinAnPais2.size();
				mView.addObject("countchaqin", countChaqinanPai);
				
			}
			//今日备忘
			List<BeiWL>	 beiWLs=beiWLService.getAllByuserID(fuDaoYuan.getFudaoyuanid());
			List<BeiWL>  beiWLs2=new ArrayList<>();
			for(int i=0;i<beiWLs.size();i++){
				if(date.equals(beiWLs.get(i).getShijian().trim().substring(0,10))){
					beiWLs2.add(beiWLs.get(i));
				}
				else{
					continue;
				}
			}
			mView.addObject("beiwls2",beiWLs2);
			mView.addObject("countbeiwl", beiWLs2.size());
			//活动
			Map<String,String> paraMap=new HashMap<>();
			paraMap.put("riqi",kaiShiRiQi);
			paraMap.put("canyuren", "%,"+fuDaoYuan.getFudaoyuanid().toString()+",%,");
			
		}
			
		
		
		mView.setViewName("system/main");
		mView.addObject("sum", sum);
		return mView;
	}

	// 获取父菜单对应的子菜单
	private List<Menu> getChild(Integer id, List<Menu> menus) {
		// 子菜单
		List<Menu> childList = new ArrayList<>();
		for (Menu menu : menus) {
			// 遍历所有节点，将父菜单id与传过来的id比较
			if (menu.getFatherid() != null) {
				if (menu.getFatherid().equals(id)) {
					childList.add(menu);
				}
			}
		}
		if (childList.size() == 0) {
			return null;
		}
		return childList;
	}

	@RequestMapping(value = "login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ModelAndView mView = new ModelAndView();
		if (request.getSession().getAttribute("user")!=null) {
			response.sendRedirect("index");
			return null;
		}else {
			mView.setViewName("login/login");
		}
		return mView;

	}

	// 登出操作，销毁session，返回login页面
	@RequestMapping(value = "logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (Util.isXueSheng(request)) {
			XueSheng xueSheng = (XueSheng) request.getSession().getAttribute("user");
			MemoryData.getSessionIDMap().remove(xueSheng.getXueshengid().toString());
		}else {
			YongHu yongHu = (YongHu) request.getSession().getAttribute("user");
			MemoryData.getSessionIDMap().remove(yongHu.getYonghuid().toString());
		}
		session.invalidate();
		response.sendRedirect("login");

	}
	
	@RequestMapping(value = "chaxuntixing")//查询提醒，小铃铛
	public ModelAndView chaxuntixing(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			List<TiXing> tiXings = new ArrayList<>();
			if(session.getAttribute("status").equals("xuesheng")){
				XueSheng user = (XueSheng) session.getAttribute("user");
				tiXings = tixingService.getAllByjieShouRenID(user.getXueshengid());
			}else{
				YongHu user = (YongHu) session.getAttribute("user");
				tiXings = tixingService.getAllByjieShouRenID(user.getYonghuid());
			}
			int count =tiXings.size();
			int pageSize = 10;
			int page=1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
					List<TiXing> tiXings2=new ArrayList<>();
				if(count<10){
					
					for(int i=0;i<count;i++){
						tiXings2.add(tiXings.get(i));
					}
					mv.addObject("tixing",tiXings2);
				}
				else{
					for(int i=0;i<10;i++){
						tiXings2.add(tiXings.get(i));
					}
					mv.addObject("tixing",tiXings2);
				}
			}
			else{
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<TiXing> tiXings2=new ArrayList<>();
							if(count<10){
								for(int i=(page-1)*10;i<count;i++){
									tiXings2.add(tiXings.get(i));
								}
								mv.addObject("tixing",tiXings2);
							}
							else {
								for(int i=(page-1)*10;i<(page*10);i++){
									tiXings2.add(tiXings.get(i));
								}
								mv.addObject("tixing",tiXings2);
							}
					}
					else if (page == pages) {
						List<TiXing> tiXings2=new ArrayList<>();
						
						for(int i=(page-1)*10;i<count;i++){
							tiXings2.add(tiXings.get(i));
						}
						mv.addObject("tixing",tiXings2);
					}
					else{
						response.sendRedirect("logout");
					}
				}
			}
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("common/chaxuntixing");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "biaojiyidu")//将提醒标记为已读
	@ResponseBody
	public String biaojiyidu(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();	
		XueSheng user = (XueSheng) session.getAttribute("user");
		String result = "";
			String id = request.getParameter("CODE");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			TiXing tiXing2 = tixingService.selectByPrimaryKey(Integer.parseInt(id));
			if(tiXing2==null){
				response.sendRedirect("logout");
				return null;
			}
			if(!tiXing2.getJieshourenid().equals(user.getXueshengid())){
				response.sendRedirect("logout");
				return null;
			}
			TiXing tiXing = new TiXing();
			tiXing.setId(Integer.parseInt(id));
			tiXing.setZhuangtai(1);
			int i = tixingService.updatezhuangTaiByPrimaryKey(tiXing);
			if(i!=0){
				result =  "success";
			}
			return result;
	}
	
	@RequestMapping(value = "deltixing") // 删除提醒
	@ResponseBody
	public String deltixing(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String result = "";
		String id = request.getParameter("CODE");
		if (!Util.isNumeric(id)) {
			response.sendRedirect("logout");
			return null;
		}
		TiXing tiXing2 = tixingService.selectByPrimaryKey(Integer.parseInt(id));
		if (session.getAttribute("status").toString().equals("xuesheng")) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			if (!tiXing2.getJieshourenid().equals(user.getXueshengid())) {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			YongHu user = (YongHu) session.getAttribute("user");
			if (!tiXing2.getJieshourenid().equals(user.getYonghuid())) {
				response.sendRedirect("logout");
				return null;
			}
		}
		int i = tixingService.deleteByPrimaryKey(Integer.parseInt(id));
		if (i != 0) {
			result = "success";
		}
		return result;
	}
	
	@RequestMapping(value="info")
	public ModelAndView info(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView mView =new ModelAndView();
		if (Util.isXueSheng(request)) {
			XueSheng user1 = (XueSheng) request.getSession().getAttribute("user");
			XueSheng user = xueShengService.selectByPrimaryKey(user1.getXueshengid());
			String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
			XueXiao xueXiao = xueXiaoService.selectByID(Integer.parseInt(xueXiaoXueHao[0]));
			BanJi banJi = banJiService.selectByPrimaryKey(user.getBanjiid());
			YuanXi yuanXi = yuanXiService.selectByPrimaryKey(banJi.getYuanxiid());
			ZhuanYe zhuanYe = zhuanYeService.selectByPrimaryKey(banJi.getZhuanyeid());
			mView.setViewName("common/gerenxinxi");
			mView.addObject("xuexiao", xueXiao);
			mView.addObject("yuanxi", yuanXi);
			mView.addObject("zhuanye", zhuanYe);
			mView.addObject("banji", banJi);
			mView.addObject("touxiang", user.getTouxiang());
			mView.addObject("user", user);
			return mView;
		}else if (Util.isGuanLiYuan(request)) {
			YongHu user1 = (YongHu)request.getSession().getAttribute("user");
			YongHu user = yongHuService.selectYongHuByID(user1.getYonghuid());
			XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
			XueXiao xueXiao = xueXiaoService.selectByID(guanLiYuan.getXuexiaoid());
			if (user.getYuanxiid()!=null) {
				YuanXi yuanXi = yuanXiService.selectByPrimaryKey(user.getYuanxiid());
				mView.addObject("yuanxi", yuanXi);
			}
			mView.setViewName("common/info");
			mView.addObject("touxiang", user.getTouxiang());
			mView.addObject("user", user);
			mView.addObject("xuexiao", xueXiao);
			return mView;
		}else{
			YongHu user1 = (YongHu)request.getSession().getAttribute("user");
			YongHu user = yongHuService.selectYongHuByID(user1.getYonghuid());
			YuanXi yuanXi = yuanXiService.selectByPrimaryKey(user.getYuanxiid());
			XueXiao xueXiao = xueXiaoService.selectByID(yuanXi.getXuexiaoid());
			mView.setViewName("common/info");
			mView.addObject("yuanxi", yuanXi);
			mView.addObject("xuexiao", xueXiao);
			mView.addObject("touxiang", user.getTouxiang());
			mView.addObject("user", user);
			return mView;
		}
	}
	
	@RequestMapping(value="modifyinfo")
	@ResponseBody
	public String ModifyInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf-8");
		if (Util.isXueSheng(request)) {
			XueSheng user = (XueSheng) request.getSession().getAttribute("user");
			user.setDianhua(request.getParameter("phone"));
			user.setYouxiang(request.getParameter("mail"));
			int i = xueShengService.updateInfo(user);
			if (i>0) {
//				out.print("<script>alert('修改成功');</script>");
//				out.print("<script>location='info';</script>");
				return "success";
			}else {
//				response.sendError(500, "fail");
			}
		}else {
			YongHu user = (YongHu)request.getSession().getAttribute("user");
			user.setYonghushouji(request.getParameter("phone"));
			user.setYonghuyouxiang(request.getParameter("mail"));
			int i = yongHuService.updateYongHu(user);
			if (i>0) {
//				out.print("<script>alert('修改成功');</script>");
//				out.print("<script>location='info';</script>");
				return "success";
			}else {
//				response.sendError(500, "fail");
			}
		}
		return null;
	}
	
	@RequestMapping(value="modifyPs")
	@ResponseBody
	public String ModifyPassWd(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (Util.isXueSheng(request)) {
			XueSheng user = (XueSheng) request.getSession().getAttribute("user");
			String xuexiaoxuehao = user.getXuexiaoXuehao();
			String status = "xuesheng";
			String code[] = request.getParameter("CODE").split(",zytech,");
			if (code.length!=3) {
				response.sendRedirect("logout");
				return "fail";
			}
			if (!code[1].equals(code[2])) {
				response.sendRedirect("logout");
				return "fail";
			}
			String passWord = xueShengService.selectPassWdByID(user.getXueshengid());
			if (!passWord.equals(code[0])) {
				return "wrong";
			}
			int i = xueShengService.updatePassWdByID(code[1], user.getXueshengid());
			if (i>0) {
				request.getSession().invalidate();
				String password = code[0].toString();
				Map<String,Object> parmMap = new HashMap<String,Object>();
				parmMap.put("password", password);
				parmMap.put("xuexiaoxuehao", xuexiaoxuehao);
				parmMap.put("status", status);
				int c = yongHuService.updatePasswordKaoShiById(parmMap);
				if(c > 0){
					String shunxuhao = System.currentTimeMillis()+"";
					String dizhiliebiao = "2,3";
					String tongbuneirong = "update xuesheng set miMaMD5 = '"+code[1].toString()+"' where xuexiao_xuehao = '"+xuexiaoxuehao+"' ";
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
			}else {
				return "fail";
			}
			
		}else {
			YongHu user = (YongHu) request.getSession().getAttribute("user");
			String code[] = request.getParameter("CODE").split(",zytech,");
			if (code.length!=3) {
				response.sendRedirect("logout");
				return "fail";
			}
			if (!code[1].equals(code[2])) {
				response.sendRedirect("logout");
				return "fail";
			}
			String passWord = yongHuService.selectPassWdByID(user.getYonghuid());
			if (!passWord.equals(code[0])) {
				return "wrong";
			}
			int i = yongHuService.updatePassWdByID(code[1], user.getYonghuid());
			
			if (i>0) {
				request.getSession().invalidate();
				String password = code[1].toString();
				Map<String,Object> parmMap = new HashMap<String,Object>();
				parmMap.put("password", password);
				parmMap.put("loginName", user.getYonghuming());
				String status = "laoshi";
				parmMap.put("status", status);
				int c = yongHuService.updatePasswordKaoShiById(parmMap);
				if(c > 0){
					String shunxuhao = System.currentTimeMillis()+"";
					String dizhiliebiao = "2,3";
					String tongbuneirong = "update yonghu set miMaMD5 = '"+code[1].toString()+"' where yongHuMing = '"+user.getYonghuming()+"' ";
					String leimingcheng = "com.ccbupt.kaoshi.dao.YongHu";
					Map<String,Object> pMap = new HashMap<String,Object>();
					pMap.put("shunxuhao", shunxuhao);
					pMap.put("dizhiliebiao", dizhiliebiao);
					pMap.put("tongbuneirong", tongbuneirong);
					pMap.put("leimingcheng", leimingcheng);
					yongHuService.insertKaoshiTongBuFaSong(pMap);
					
					//String leimingcheng = "com.ccbupt.kaoshi.dao.YongHu";
				}
				
				return "success";
			}else {
				return "fail";
			}
		}
	}
	
	@ResponseBody
	@RequestMapping(value="updateTouXiang", method = RequestMethod.POST)
	public Map<String,Object> updateTouXiang(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
		Map<String,Object> m = new HashMap<>();
		int result = 0;
		try {
			Map<String,String> map = new HashMap<>();
			String prefix = file.getOriginalFilename().substring((file.getOriginalFilename().lastIndexOf(".") + 1));
			String name = UUID.randomUUID().toString().replaceAll("-", "");
			map.put("touxiang", name + "." + prefix);
			map.put("avatar_data", avatar_data);
			map.put("path",  request.getSession().getServletContext().getRealPath("/"));
			if(Util.isXueSheng(request)){
				XueSheng xueSheng = (XueSheng) request.getSession().getAttribute("user");
				map.put("id", xueSheng.getXueshengid().toString());
				map.put("status", "xuesheng");
			}else{
				YongHu yongHu = (YongHu) request.getSession().getAttribute("user");
				map.put("id", yongHu.getYonghuid().toString());
				map.put("status", "yonghu");
			}
			result = yongHuService.updateTouXiang(file,map);
		} catch (Exception e) {
			m.put("code", "2");
			m.put("msg", "更新头像失败！");
		}
		if(result != 0){
			m.put("code", "1");
			m.put("msg", "头像更新成功");
		}else {
			m.put("code", "2");
			m.put("msg", "更新头像失败！");
		}
		return m;
	}
}
