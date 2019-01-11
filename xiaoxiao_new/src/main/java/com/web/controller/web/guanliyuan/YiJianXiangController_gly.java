package com.web.controller.web.guanliyuan;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.annotation.LoginStatusAnnotation;
import com.web.model.BanJi;
import com.web.model.FuDaoYuan;
import com.web.model.XueSheng;
import com.web.model.YiJianHuiFu;
import com.web.model.YiJianXiang;
import com.web.model.YongHu;
import com.web.service.BanJiService;
import com.web.service.FuDaoYuanService;
import com.web.service.XueShengService;
import com.web.service.YiJianHuiFuService;
import com.web.service.YiJianXiangService;
import com.web.service.YongHuService;
import com.web.util.Util;

@Controller
@LoginStatusAnnotation(status="guanliyuan")
public class YiJianXiangController_gly {
	@Autowired
	private FuDaoYuanService fudaoyuanService;
	@Autowired
	private XueShengService xueshengService;
	@Autowired
	private BanJiService banjiService;
	@Autowired
	private YiJianXiangService yijianxiangService;
	@Autowired
	private YiJianHuiFuService yijianhuifuService;
	@Autowired
	private YongHuService yonghuService;
	
	@RequestMapping(value = "xueshengyijian_gly")//学生意见
	public ModelAndView xueshengyijian_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu)session.getAttribute("user");
			Map<String, String> map = new HashMap<>();
			map.put("jieshourenid", user.getYonghuid().toString());
			map.put("tijiaozhuangtai", "1");
			map.put("biaoqian", "");
			map.put("banjiid", "");
			map.put("nimingbiaoji", "");
			map.put("chulibiaoji", "");
			List<YiJianXiang> yiJianXiangs = yijianxiangService.getAllByjieShouRenIDandtiJiaoZhuangTai(map);
			if(yiJianXiangs!=null){
				for (YiJianXiang yiJianXiang : yiJianXiangs) {
					int xueshengid = yiJianXiang.getXueshengid();
					XueSheng xueSheng = xueshengService.getUserById(xueshengid);
					int banjiid = xueSheng.getBanjiid();
					String banjimingcheng = banjiService.selectByPrimaryKey(banjiid).getBanjimingcheng();
					String xingming = xueSheng.getXingming();
					yiJianXiang.setBanjimingcheng(banjimingcheng);
					yiJianXiang.setXueshengxingming(xingming);
				}
			}
			int count =yiJianXiangs.size();
			int pageSize = 10;
			int page=1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<YiJianXiang> yiJianXiangs2=new ArrayList<>();
				if(count<10){
					for(int i=0;i<count;i++){
						yiJianXiangs2.add(yiJianXiangs.get(i));
					}
					mv.addObject("yijian", yiJianXiangs2);
				}
				else{
					for(int i=0;i<10;i++){
						yiJianXiangs2.add(yiJianXiangs.get(i));
					}
					mv.addObject("yijian", yiJianXiangs2);
				}
			}
			else{
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<YiJianXiang> yiJianXiangs2=new ArrayList<>();
							if(count<10){
								for(int i=(page-1)*10;i<count;i++){
									yiJianXiangs2.add(yiJianXiangs.get(i));
								}
								mv.addObject("yijian", yiJianXiangs2);
							}
							else {
								for(int i=(page-1)*10;i<(page*10);i++){
									yiJianXiangs2.add(yiJianXiangs.get(i));
								}
								mv.addObject("yijian", yiJianXiangs2);
							}
					}
					else if (page == pages) {
						List<YiJianXiang> yiJianXiangs2=new ArrayList<>();
						for(int i=(page-1)*10;i<count;i++){
							yiJianXiangs2.add(yiJianXiangs.get(i));
						}
						mv.addObject("yijian", yiJianXiangs2);
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
			mv.addObject("yijian", yiJianXiangs);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("guanliyuan/xueshengyijian_gly");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "xueshengyijian_gly_chaxun")//学生意见——查询
	public ModelAndView xueshengyijian_gly_chaxun(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu)session.getAttribute("user");
			String biaoqian = request.getParameter("biaoqian");
			String niming = request.getParameter("niming");
			String chulibiaoji = request.getParameter("chulibiaoji");
			Map<String, String> map = new HashMap<>();
			map.put("jieshourenid", user.getYonghuid().toString());
			map.put("tijiaozhuangtai", "1");
			map.put("biaoqian", biaoqian);
			map.put("banjiid", "");
			map.put("nimingbiaoji", niming);
			map.put("chulibiaoji", chulibiaoji);
			List<YiJianXiang> yiJianXiangs = yijianxiangService.getAllByjieShouRenIDandtiJiaoZhuangTai(map);
			if(yiJianXiangs!=null){
				for (YiJianXiang yiJianXiang : yiJianXiangs) {
					int xueshengid = yiJianXiang.getXueshengid();
					XueSheng xueSheng = xueshengService.getUserById(xueshengid);
					int banjiid = xueSheng.getBanjiid();
					String banjimingcheng = banjiService.selectByPrimaryKey(banjiid).getBanjimingcheng();
					String xingming = xueSheng.getXingming();
					yiJianXiang.setBanjimingcheng(banjimingcheng);
					yiJianXiang.setXueshengxingming(xingming);
				}
			}
			int count =yiJianXiangs.size();
			int pageSize = 10;
			int page=1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<YiJianXiang> yiJianXiangs2=new ArrayList<>();
				if(count<10){
					for(int i=0;i<count;i++){
						yiJianXiangs2.add(yiJianXiangs.get(i));
					}
					mv.addObject("yijian", yiJianXiangs2);
				}
				else{
					for(int i=0;i<10;i++){
						yiJianXiangs2.add(yiJianXiangs.get(i));
					}
					mv.addObject("yijian", yiJianXiangs2);
				}
			}
			else{
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<YiJianXiang> yiJianXiangs2=new ArrayList<>();
							if(count<10){
								for(int i=(page-1)*10;i<count;i++){
									yiJianXiangs2.add(yiJianXiangs.get(i));
								}
								mv.addObject("yijian", yiJianXiangs2);
							}
							else {
								for(int i=(page-1)*10;i<(page*10);i++){
									yiJianXiangs2.add(yiJianXiangs.get(i));
								}
								mv.addObject("yijian", yiJianXiangs2);
							}
					}
					else if (page == pages) {
						List<YiJianXiang> yiJianXiangs2=new ArrayList<>();
						for(int i=(page-1)*10;i<count;i++){
							yiJianXiangs2.add(yiJianXiangs.get(i));
						}
						mv.addObject("yijian", yiJianXiangs2);
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
			mv.addObject("yijian", yiJianXiangs);
			mv.addObject("biaoqian", biaoqian);
			mv.addObject("niming", niming);
			mv.addObject("chulibiaoji", chulibiaoji);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("guanliyuan/xueshengyijian_gly");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "chuliyijian_gly")//处理意见
	public ModelAndView chuliyijian_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu)session.getAttribute("user");
			String yijianid = request.getParameter("id");
			if (!Util.isNumeric(yijianid)) {
				response.sendRedirect("logout");
				return null;
			}
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if(yiJianXiang!=null && yiJianXiang.getJieshourenid()==user.getYonghuid()){
				//插入班级、姓名
				int xueshengid = yiJianXiang.getXueshengid();
				XueSheng xueSheng = xueshengService.getUserById(xueshengid);
				String xingming = xueSheng.getXingming();
				int banjiid = xueSheng.getBanjiid();
				String banjimingcheng = banjiService.selectByPrimaryKey(banjiid).getBanjimingcheng();
				yiJianXiang.setBanjimingcheng(banjimingcheng);
				yiJianXiang.setXueshengxingming(xingming);
				//插入图片
				if(!(yiJianXiang.getTumingcheng()==null||yiJianXiang.getTumingcheng()=="")){
					List<String> tu= new ArrayList<>();
					String tString[]=yiJianXiang.getTumingcheng().split(",");
					for (int i = 0; i < tString.length; i++) {
						tu.add(tString[i]);
					}
					yiJianXiang.setTupian(tu);
				}
				mv.addObject("yijian", yiJianXiang);
				mv.setViewName("guanliyuan/chuliyijian_gly");
				return mv;
			}else{
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "submitchuli_gly")//提交意见处理
	@ResponseBody
	public String submitchuli_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
//		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			String yijianid = request.getParameter("id");
			if (!Util.isNumeric(yijianid)) {
				response.sendRedirect("logout");
				return null;
			}
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if(yiJianXiang!=null && yiJianXiang.getJieshourenid()==user.getYonghuid()){
				String pinglun = request.getParameter("chuli");
				String gongbuzhuangtai = request.getParameter("isgongbu");
				String kejianxueshengid = "";
				String kejianjiaoshiid = "";
				String kejianrenfanwei = "";
				int xueshengid = yiJianXiang.getXueshengid();
				String banjiid = xueshengService.getUserById(xueshengid).getBanjiid().toString();
				if(gongbuzhuangtai.equals("0")){
					kejianrenfanwei = "——";
				}
				if(gongbuzhuangtai.equals("1")){
					String fudaoyuanid = fudaoyuanService.getBybanJiID(banjiid+",%","%,"+banjiid+",%").getFudaoyuanid().toString();
					String shujiid = fudaoyuanService.getByfuDaoYuanID(Integer.parseInt(fudaoyuanid)).getShujiid().toString();
					String xueshengchuguanliyuanid = user.getYonghuid().toString();
					kejianjiaoshiid = shujiid+","+xueshengchuguanliyuanid+",";
					kejianrenfanwei = request.getParameter("fanwei");
					if(kejianrenfanwei.equals("本班")){
						List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(banjiid));
						for (XueSheng xueSheng : xueShengs) {
							kejianxueshengid += xueSheng.getXueshengid()+",";
						} 
						kejianjiaoshiid += fudaoyuanid+",";
					}
					if(kejianrenfanwei.equals("本专业")){
						String zhuanyeid = banjiService.selectByPrimaryKey(Integer.parseInt(banjiid)).getZhuanyeid().toString();
						int ruxuenianfen = banjiService.selectByPrimaryKey(Integer.parseInt(banjiid)).getRuxuenianfenid();
						List<BanJi> banJis = banjiService.getAllByzhuanYeDaiMaAndruXueNianFen(zhuanyeid, ruxuenianfen);
						for (BanJi banJi : banJis) {
							List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(banJi.getBanjiid());
							for (XueSheng xueSheng : xueShengs) {
								kejianxueshengid += xueSheng.getXueshengid()+",";
							}
						}
						kejianjiaoshiid += fudaoyuanid+",";
					}
					if(kejianrenfanwei.equals("本学院")){
						int yuanxiid = banjiService.selectByPrimaryKey(Integer.parseInt(banjiid)).getYuanxiid();
						int ruxuenianfen = banjiService.selectByPrimaryKey(Integer.parseInt(banjiid)).getRuxuenianfenid();
						List<BanJi> banJis = banjiService.getAllByyuanXiIDAndruXueNianFen(yuanxiid, ruxuenianfen);
						for (BanJi banJi : banJis) {
							List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(banJi.getBanjiid());
							for (XueSheng xueSheng : xueShengs) {
								kejianxueshengid += xueSheng.getXueshengid()+",";
							}
						}
						List<FuDaoYuan> fuDaoYuans = fudaoyuanService.getAllByshuJiID(Integer.parseInt(shujiid));
						for (FuDaoYuan fuDaoYuan : fuDaoYuans) {
							kejianjiaoshiid += fuDaoYuan.getFudaoyuanid().toString()+",";
						}
					}
				}
				YiJianXiang yiJianXiang2 = new YiJianXiang();
				yiJianXiang2.setYijianid(Integer.parseInt(yijianid));
				yiJianXiang2.setChulibiaoji("1");
				yiJianXiang2.setChulirenid(user.getYonghuid());
				yiJianXiang2.setPinglunliang(yiJianXiang.getPinglunliang()+1);
				yiJianXiang2.setGongbuzhuangtai(gongbuzhuangtai);
				yiJianXiang2.setKejianrenfanwei(kejianrenfanwei);
				yiJianXiang2.setKejianjiaoshiid(kejianjiaoshiid);
				yiJianXiang2.setKejianxueshengid(kejianxueshengid);
				int i = yijianxiangService.updatechuliByPrimaryKey(yiJianXiang2);
				if(i!=0){
					Date date = new Date();
					YiJianHuiFu yiJianHuiFu = new YiJianHuiFu();
					yiJianHuiFu.setYijianid(Integer.parseInt(yijianid));
					yiJianHuiFu.setShijian(date);
					yiJianHuiFu.setJiaoshiid(user.getYonghuid());
					yiJianHuiFu.setHuifuneirong(pinglun);
					int j = yijianhuifuService.insert4(yiJianHuiFu);
					if(j!=0){
//						response.setContentType("text/html; charset=utf-8");
//						out.print("<script>alert('处理成功！');</script>");
//						out.print("<script>location='xueshengyijian_gly';</script>");
						return "success";
					}else{
//						response.setContentType("text/html; charset=utf-8");
//						out.print("<script>alert('处理失败！');</script>");
					}
				}else {
//					response.setContentType("text/html; charset=utf-8");
//					out.print("<script>alert('处理失败！');</script>");
				}
			}else{
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("login");
			return null;
		}
		return null;
	}
	
	@RequestMapping(value = "chakanyijian_gly")//查看已处理意见
	public ModelAndView chakanyijian_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu)session.getAttribute("user");
			String yijianid = request.getParameter("id");
			if (!Util.isNumeric(yijianid)) {
				response.sendRedirect("logout");
				return null;
			}
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if(yiJianXiang!=null && yiJianXiang.getJieshourenid()==user.getYonghuid()){
				//插入班级、姓名
				int xueshengid = yiJianXiang.getXueshengid();
				XueSheng xueSheng = xueshengService.getUserById(xueshengid);
				String xingming = xueSheng.getXingming();
				int banjiid = xueSheng.getBanjiid();
				String banjimingcheng = banjiService.selectByPrimaryKey(banjiid).getBanjimingcheng();
				yiJianXiang.setBanjimingcheng(banjimingcheng);
				yiJianXiang.setXueshengxingming(xingming);
				//插入图片
				if(!(yiJianXiang.getTumingcheng()==null||yiJianXiang.getTumingcheng()=="")){
					List<String> tu= new ArrayList<>();
					String tString[]=yiJianXiang.getTumingcheng().split(",");
					for (int i = 0; i < tString.length; i++) {
						tu.add(tString[i]);
					}
					yiJianXiang.setTupian(tu);
				}
				List<YiJianHuiFu> yiJianHuiFus = yijianhuifuService.getAllByyiJianID(Integer.parseInt(yijianid));
				mv.addObject("user", user);
				mv.addObject("pinglun", yiJianHuiFus);
				mv.addObject("yijian", yiJianXiang);
				mv.setViewName("guanliyuan/chakanyijian_gly");
				return mv;
			}else{
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "yijiandongtai_gly")//意见动态
	public ModelAndView yijiandongtai_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		List<YiJianXiang> yiJianXiangs = new ArrayList<>();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, String> map = new HashMap<>();
			map.put("kejianjiaoshiid1", user.getYonghuid().toString()+",%");
			map.put("kejianjiaoshiid2", "%,"+user.getYonghuid().toString()+",%");
			map.put("biaoqian", "");
			map.put("nimingbiaoji", "");
			map.put("banjiid", "");
			map.put("tijiaozhuangtai", "1");
			map.put("gongbuzhuangtai", "1");
			yiJianXiangs = yijianxiangService.getAllByJSandBQandNM(map);
			if(yiJianXiangs!=null){
				for (YiJianXiang yiJianXiang : yiJianXiangs) {
					//将图片加载到实体类
					if(!(yiJianXiang.getTumingcheng()==null||yiJianXiang.getTumingcheng()=="")){
						List<String> tu= new ArrayList<>();
						String tString[]=yiJianXiang.getTumingcheng().split(",");
						for (int i = 0; i < tString.length; i++) {
							tu.add(tString[i]);
						}
						yiJianXiang.setTupian(tu);
					}
					//将发布意见学生的班级名、姓名插到实体类
					int id = yiJianXiang.getXueshengid();
					String xueshengxingming = xueshengService.getUserById(id).getXingming();
					int banjiid = xueshengService.getUserById(id).getBanjiid();
					String banjimingcheng = banjiService.selectByPrimaryKey(banjiid).getBanjimingcheng();
					yiJianXiang.setXueshengxingming(xueshengxingming);
					yiJianXiang.setBanjimingcheng(banjimingcheng);
					//标记我有没有点赞这条意见
					String dianzanren = yiJianXiang.getDianzanrenid();
					int sign = 0;
					if(dianzanren!=null && dianzanren!=""){
						String dianzanrens[] = dianzanren.split(",");
						for (String string : dianzanrens) {
							if(string.equals(user.getYonghuid().toString())){
								sign = 1;
							}
						}
					}
					yiJianXiang.setIsdianzan(sign);
					//插入意见回复实体类列表
					List<YiJianHuiFu> yiJianHuiFus = yijianhuifuService.getAllByyiJianID(yiJianXiang.getYijianid());
					if(!yiJianHuiFus.isEmpty()){
						for (YiJianHuiFu yiJianHuiFu : yiJianHuiFus) {
							if(yiJianHuiFu.getJiaoshiid()!=null){
								String jiaoshixingming = yonghuService.selectYongHuByID(yiJianHuiFu.getJiaoshiid()).getYonghuxingming();
								yiJianHuiFu.setJiaoshixingming(jiaoshixingming);
							}else{
								String xueshengname = xueshengService.getUserById(yiJianHuiFu.getXueshengid()).getXingming();
								int banji = xueshengService.getUserById(yiJianHuiFu.getXueshengid()).getBanjiid();
								String banjiming = banjiService.selectByPrimaryKey(banji).getBanjimingcheng();
								yiJianHuiFu.setXueshengxingming(xueshengname);
								yiJianHuiFu.setBanjimingcheng(banjiming);
							}
							if(yiJianHuiFu.getBeihuifujiaoshiid()!=null){
								String beihuifujiaoshixingming = yonghuService.selectYongHuByID(yiJianHuiFu.getBeihuifujiaoshiid()).getYonghuxingming();
								yiJianHuiFu.setBeihuifujiaoshixingming(beihuifujiaoshixingming);
							}else if(yiJianHuiFu.getBeihuifuxueshengid()!=null){
								String beihuifuxueshengxingming = xueshengService.getUserById(yiJianHuiFu.getBeihuifuxueshengid()).getXingming();
								int beihuifubanjiid = xueshengService.getUserById(yiJianHuiFu.getBeihuifuxueshengid()).getBanjiid();
								String beihuifubanjiming = banjiService.selectByPrimaryKey(beihuifubanjiid).getBanjimingcheng();
								yiJianHuiFu.setBeihuifuxueshengxingming(beihuifuxueshengxingming);
								yiJianHuiFu.setBeihuifubanjimingcheng(beihuifubanjiming);
							}
						}
						yiJianXiang.setYijianhuifus(yiJianHuiFus);
					}
				}
			}
			int count =yiJianXiangs.size();
			int pageSize = 10;
			int page=1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<YiJianXiang> yiJianXiangs2=new ArrayList<>();
				if(count<10){
					for(int i=0;i<count;i++){
						yiJianXiangs2.add(yiJianXiangs.get(i));
					}
					mv.addObject("yijian", yiJianXiangs2);
				}
				else{
					for(int i=0;i<10;i++){
						yiJianXiangs2.add(yiJianXiangs.get(i));
					}
					mv.addObject("yijian", yiJianXiangs2);
				}
			}
			else{
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<YiJianXiang> yiJianXiangs2=new ArrayList<>();
							if(count<10){
								for(int i=(page-1)*10;i<count;i++){
									yiJianXiangs2.add(yiJianXiangs.get(i));
								}
								mv.addObject("yijian", yiJianXiangs2);
							}
							else {
								for(int i=(page-1)*10;i<(page*10);i++){
									yiJianXiangs2.add(yiJianXiangs.get(i));
								}
								mv.addObject("yijian", yiJianXiangs2);
							}
					}
					else if (page == pages) {
						List<YiJianXiang> yiJianXiangs2=new ArrayList<>();
						for(int i=(page-1)*10;i<count;i++){
							yiJianXiangs2.add(yiJianXiangs.get(i));
						}
						mv.addObject("yijian", yiJianXiangs2);
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
			mv.addObject("yijian", yiJianXiangs);
			mv.addObject("userid", user.getYonghuid());
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("guanliyuan/yijiandongtai_gly");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "chaxunyijian_gly")//按标签、班级、匿名查询意见动态
	public ModelAndView chaxunyijian_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		List<YiJianXiang> yiJianXiangs = new ArrayList<>();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, String> map = new HashMap<>();
			String biaoqian = request.getParameter("biaoqian");
			String nimingbiaoji = request.getParameter("niming");
			map.put("kejianjiaoshiid1", user.getYonghuid().toString()+",%");
			map.put("kejianjiaoshiid2", "%,"+user.getYonghuid().toString()+",%");
			map.put("biaoqian", biaoqian);
			map.put("nimingbiaoji", nimingbiaoji);
			map.put("banjiid", "");
			map.put("tijiaozhuangtai", "1");
			map.put("gongbuzhuangtai", "1");
			yiJianXiangs = yijianxiangService.getAllByJSandBQandNM(map);
			if(yiJianXiangs!=null){
				for (YiJianXiang yiJianXiang : yiJianXiangs) {
					//将图片加载到实体类
					if(!(yiJianXiang.getTumingcheng()==null||yiJianXiang.getTumingcheng()=="")){
						List<String> tu= new ArrayList<>();
						String tString[]=yiJianXiang.getTumingcheng().split(",");
						for (int i = 0; i < tString.length; i++) {
							tu.add(tString[i]);
						}
						yiJianXiang.setTupian(tu);
					}
					//将发布意见学生的班级名、姓名插到实体类
					int id = yiJianXiang.getXueshengid();
					String xueshengxingming = xueshengService.getUserById(id).getXingming();
					int banjiid = xueshengService.getUserById(id).getBanjiid();
					String banjimingcheng = banjiService.selectByPrimaryKey(banjiid).getBanjimingcheng();
					yiJianXiang.setXueshengxingming(xueshengxingming);
					yiJianXiang.setBanjimingcheng(banjimingcheng);
					//标记我有没有点赞这条意见
					String dianzanren = yiJianXiang.getDianzanrenid();
					int sign = 0;
					if(dianzanren!=null && dianzanren!=""){
						String dianzanrens[] = dianzanren.split(",");
						for (String string : dianzanrens) {
							if(string.equals(user.getYonghuid().toString())){
								sign = 1;
							}
						}
					}
					yiJianXiang.setIsdianzan(sign);
					//插入意见回复实体类列表
					List<YiJianHuiFu> yiJianHuiFus = yijianhuifuService.getAllByyiJianID(yiJianXiang.getYijianid());
					if(!yiJianHuiFus.isEmpty()){
						for (YiJianHuiFu yiJianHuiFu : yiJianHuiFus) {
							if(yiJianHuiFu.getJiaoshiid()!=null){
								String jiaoshixingming = yonghuService.selectYongHuByID(yiJianHuiFu.getJiaoshiid()).getYonghuxingming();
								yiJianHuiFu.setJiaoshixingming(jiaoshixingming);
							}else{
								String xueshengname = xueshengService.getUserById(yiJianHuiFu.getXueshengid()).getXingming();
								int banji = xueshengService.getUserById(yiJianHuiFu.getXueshengid()).getBanjiid();
								String banjiming = banjiService.selectByPrimaryKey(banji).getBanjimingcheng();
								yiJianHuiFu.setXueshengxingming(xueshengname);
								yiJianHuiFu.setBanjimingcheng(banjiming);
							}
							if(yiJianHuiFu.getBeihuifujiaoshiid()!=null){
								String beihuifujiaoshixingming = yonghuService.selectYongHuByID(yiJianHuiFu.getBeihuifujiaoshiid()).getYonghuxingming();
								yiJianHuiFu.setBeihuifujiaoshixingming(beihuifujiaoshixingming);
							}else if(yiJianHuiFu.getBeihuifuxueshengid()!=null){
								String beihuifuxueshengxingming = xueshengService.getUserById(yiJianHuiFu.getBeihuifuxueshengid()).getXingming();
								int beihuifubanjiid = xueshengService.getUserById(yiJianHuiFu.getBeihuifuxueshengid()).getBanjiid();
								String beihuifubanjiming = banjiService.selectByPrimaryKey(beihuifubanjiid).getBanjimingcheng();
								yiJianHuiFu.setBeihuifuxueshengxingming(beihuifuxueshengxingming);
								yiJianHuiFu.setBeihuifubanjimingcheng(beihuifubanjiming);
							}
						}
						yiJianXiang.setYijianhuifus(yiJianHuiFus);
					}
				}
			}
			int count =yiJianXiangs.size();
			int pageSize = 10;
			int page=1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<YiJianXiang> yiJianXiangs2=new ArrayList<>();
				if(count<10){
					for(int i=0;i<count;i++){
						yiJianXiangs2.add(yiJianXiangs.get(i));
					}
					mv.addObject("yijian", yiJianXiangs2);
				}
				else{
					for(int i=0;i<10;i++){
						yiJianXiangs2.add(yiJianXiangs.get(i));
					}
					mv.addObject("yijian", yiJianXiangs2);
				}
			}
			else{
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<YiJianXiang> yiJianXiangs2=new ArrayList<>();
							if(count<10){
								for(int i=(page-1)*10;i<count;i++){
									yiJianXiangs2.add(yiJianXiangs.get(i));
								}
								mv.addObject("yijian", yiJianXiangs2);
							}
							else {
								for(int i=(page-1)*10;i<(page*10);i++){
									yiJianXiangs2.add(yiJianXiangs.get(i));
								}
								mv.addObject("yijian", yiJianXiangs2);
							}
					}
					else if (page == pages) {
						List<YiJianXiang> yiJianXiangs2=new ArrayList<>();
						for(int i=(page-1)*10;i<count;i++){
							yiJianXiangs2.add(yiJianXiangs.get(i));
						}
						mv.addObject("yijian", yiJianXiangs2);
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
			mv.addObject("biaoqian", biaoqian);
			mv.addObject("niming", nimingbiaoji);
			mv.addObject("yijian", yiJianXiangs);
			mv.addObject("userid", user.getYonghuid());
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("guanliyuan/yijiandongtai_gly");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "dianzan_gly")//点赞
	@ResponseBody
	public String dianzan_gly(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (Util.checkSession(request)){
			YongHu user = (YongHu) session.getAttribute("user");
			String result = "";
			String yijianid = request.getParameter("CODE");
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			int dianzanliang = yiJianXiang.getDianzanliang();
			String dianzanren = yiJianXiang.getDianzanrenid();
			if(dianzanliang==0){
				dianzanren = user.getYonghuid()+",";
				dianzanliang++;
				result = "add,"+dianzanliang;
			}else{
				String dianzanrens[]=dianzanren.split(",");
				String newdianzanren="";
				int i = 0;
				for (String string : dianzanrens) {
					if(string.equals(user.getYonghuid().toString())){
						i++;
					}else{
						newdianzanren += string+",";
					}
				}
				if(i==0){
					dianzanren += user.getYonghuid()+",";
					dianzanliang++;
					result = "add,"+dianzanliang;
				}else{
					dianzanren = newdianzanren;
					dianzanliang--;
					result = "del,"+dianzanliang;
				}
			}
			YiJianXiang yiJianXiang2 = new YiJianXiang();
			yiJianXiang2.setYijianid(Integer.parseInt(yijianid));
			yiJianXiang2.setDianzanrenid(dianzanren);
			yiJianXiang2.setDianzanliang(dianzanliang);
			int j = yijianxiangService.updatedianZanByByPrimaryKey(yiJianXiang2);
			if(j==0){
				result = "fail,";
			}
			return result;
		}else{
			response.sendRedirect("login");
			return null;
		}
	}
	
	@RequestMapping(value = "subpinglun_gly")//意见评论,在意见动态里
	@ResponseBody
	public String subpinglun_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
//		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			String yijianid = request.getParameter("id");
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if(yiJianXiang == null){
				response.sendRedirect("logout");
				return null;
			}
			String huifuneirong = request.getParameter("pinglunneirong");
			System.out.println(huifuneirong);
			Date date = new Date();
			YiJianHuiFu yiJianHuiFu = new YiJianHuiFu();
			yiJianHuiFu.setYijianid(Integer.parseInt(yijianid));
			yiJianHuiFu.setHuifuneirong(huifuneirong);
			yiJianHuiFu.setShijian(date);
			yiJianHuiFu.setJiaoshiid(user.getYonghuid());
			int i = yijianhuifuService.insert4(yiJianHuiFu);
			if(i!=0){
				int pinglunliang = yiJianXiang.getPinglunliang();
				YiJianXiang yiJianXiang2 = new YiJianXiang();
				yiJianXiang2.setYijianid(yiJianXiang.getYijianid());
				yiJianXiang2.setPinglunliang(pinglunliang+1);
				yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang2);
//				response.setContentType("text/html; charset=utf-8");
//				out.print("<script>alert('评论成功！');</script>");
//				out.print("<script>location='yijiandongtai_gly';</script>");
				return "success";
			}else{
//				response.setContentType("text/html; charset=utf-8");
//				out.print("<script>alert('评论失败！');</script>");
			}
//			out.close();
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "subhuifujiaoshi_gly")//在评论下回复评论人，被回复人--教师,在意见动态里
	@ResponseBody
	public String subhuifujiaoshi_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
//		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			String id = request.getParameter("id");
			String ids[] = id.split("_");
			String yijianid = ids[0];
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if(yiJianXiang == null){
				response.sendRedirect("logout");
				return null;
			}
			String beihuifujiaoshiid = ids[1];
			String huifuneirong = request.getParameter("huifuneirong");
			Date date = new Date();
			YiJianHuiFu yiJianHuiFu = new YiJianHuiFu();
			yiJianHuiFu.setYijianid(Integer.parseInt(yijianid));
			yiJianHuiFu.setHuifuneirong(huifuneirong);
			yiJianHuiFu.setShijian(date);
			yiJianHuiFu.setJiaoshiid(user.getYonghuid());
			yiJianHuiFu.setBeihuifujiaoshiid(Integer.parseInt(beihuifujiaoshiid));
			int i = yijianhuifuService.insert5(yiJianHuiFu);
			if(i!=0){
				int pinglunliang = yiJianXiang.getPinglunliang();
				YiJianXiang yiJianXiang2 = new YiJianXiang();
				yiJianXiang2.setYijianid(yiJianXiang.getYijianid());
				yiJianXiang2.setPinglunliang(pinglunliang+1);
				yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang2);
//				out.print("<script>alert('回复成功！');</script>");
//				out.print("<script>location='yijiandongtai_gly';</script>");
				return "success";
			}else{
//				response.setContentType("text/html; charset=utf-8");
//				out.print("<script>alert('回复失败！');</script>");
			}
//			out.close();
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "subhuifuxuesheng_gly")//在评论下回复评论人，被回复人--学生,在意见动态里
	@ResponseBody
	public String subhuifuxuesheng_gly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
//		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			String id = request.getParameter("id");
			String ids[] = id.split("_");
			String yijianid = ids[0];
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if(yiJianXiang == null){
				response.sendRedirect("logout");
				return null;
			}
			String beihuifuxueshengid = ids[1];
			String huifuneirong = request.getParameter("huifuneirong");
			Date date = new Date();
			YiJianHuiFu yiJianHuiFu = new YiJianHuiFu();
			yiJianHuiFu.setYijianid(Integer.parseInt(yijianid));
			yiJianHuiFu.setHuifuneirong(huifuneirong);
			yiJianHuiFu.setShijian(date);
			yiJianHuiFu.setJiaoshiid(user.getYonghuid());
			yiJianHuiFu.setBeihuifuxueshengid(Integer.parseInt(beihuifuxueshengid));
			int i = yijianhuifuService.insert6(yiJianHuiFu);
			if(i!=0){
				int pinglunliang = yiJianXiang.getPinglunliang();
				YiJianXiang yiJianXiang2 = new YiJianXiang();
				yiJianXiang2.setYijianid(yiJianXiang.getYijianid());
				yiJianXiang2.setPinglunliang(pinglunliang+1);
				yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang2);
//				out.print("<script>alert('回复成功！');</script>");
//				out.print("<script>location='yijiandongtai_gly';</script>");
				return "success";
			}else{
//				out.print("<script>alert('回复失败！');</script>");
			}
//			out.close();
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	@RequestMapping(value = "delhuifu_gly")//删除意见,在意见动态里
	@ResponseBody
	public String delhuifu_gly(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (Util.checkSession(request)){
			YongHu user = (YongHu) session.getAttribute("user");
			String result = "";
				String id = request.getParameter("CODE");
				YiJianHuiFu yiJianHuiFu = yijianhuifuService.selectByPrimaryKey(Integer.parseInt(id));
				if(yiJianHuiFu == null){
					result= "null,";
					return result;
				}
				if(yiJianHuiFu.getJiaoshiid()==null){
					return "false,";
				}
				int jiaoshiid = yiJianHuiFu.getJiaoshiid();
				if(jiaoshiid!=user.getYonghuid()){
					return "false,";
				}
				int i = yijianhuifuService.deleteByPrimaryKey(Integer.parseInt(id));
				if(i!=0){
					YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(yiJianHuiFu.getYijianid());
					int pinglunliang = yiJianXiang.getPinglunliang()-1;
					YiJianXiang yiJianXiang2 = new YiJianXiang();
					yiJianXiang2.setYijianid(yiJianHuiFu.getYijianid());
					yiJianXiang2.setPinglunliang(pinglunliang);
					int j = yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang2);
					if(j!=0){
						result = "true,"+pinglunliang;
					}else{
						result = "false,";
					}
				}else{
					result = "false,";
				}
			return result;
		}else{
			response.sendRedirect("login");
			return null;
		}
	}

}
