package com.web.controller.web.student;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.servlet.ModelAndView;

import com.web.annotation.LoginStatusAnnotation;
import com.web.model.FuDaoYuan;
import com.web.model.KeCheng;
import com.web.model.ShuJi;
import com.web.model.XueSheng;
import com.web.model.YiJianHuiFu;
import com.web.model.YiJianXiang;
import com.web.service.BanJiService;
import com.web.service.FuDaoYuanService;
import com.web.service.ShuJiService;
import com.web.service.XueShengService;
import com.web.service.YiJianHuiFuService;
import com.web.service.YiJianXiangService;
import com.web.service.YongHuService;
import com.web.service.ZhuanYeService;
import com.web.util.Util;

@Controller
@LoginStatusAnnotation(status="xuesheng")
public class YiJianXiangController {
	
	@Autowired
	private FuDaoYuanService fudaoyuanService;
	@Autowired
	private ShuJiService shujiService;
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
	@Autowired 
	private ZhuanYeService zhuanyeService;
	
	@RequestMapping(value = "wodeyijian")//我的意见
	public ModelAndView wodeyijian(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			int xueshengid= user.getXueshengid();
			List<YiJianXiang> yiJianXiangs = yijianxiangService.getAllByxueShengIDandtiJiaoZhuangTai(xueshengid, "1");
			for (YiJianXiang yiJianXiang : yiJianXiangs) {
				if(!(yiJianXiang.getTumingcheng()==null||yiJianXiang.getTumingcheng()=="")){
					List<String> tu= new ArrayList<>();
					String tString[]=yiJianXiang.getTumingcheng().split(",");
					for (int i = 0; i < tString.length; i++) {
						tu.add(tString[i]);
					}
					yiJianXiang.setTupian(tu);
				}
				//标记我有没有点赞这条意见
				String dianzanren = yiJianXiang.getDianzanrenid();
				int sign = 0;
				if(dianzanren!=null && dianzanren!=""){
					String dianzanrens[] = dianzanren.split(",");
					for (String string : dianzanrens) {
						if(string.equals(user.getXueshengid().toString())){
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
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.addObject("userid", user.getXueshengid());
			mv.setViewName("stu/wodeyijian");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "addyijian")//新增意见
	public ModelAndView addyijian(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			mv.setViewName("stu/addyijian");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "saveorsubmityijian")//保存或是直接提交新增的意见
	@ResponseBody
	public ModelAndView saveorsubmityijian(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			YiJianXiang yiJianXiang = new YiJianXiang();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			XueSheng user = (XueSheng) session.getAttribute("user");
			String mingcheng = request.getParameter("mingcheng");//获取名称
			String neirong = request.getParameter("neirong");//获取内容
			//获取图片***********
			List<MultipartFile> files = multipartRequest.getFiles("tumingcheng");
			String tumingcheng = "";
			if(files.get(0).isEmpty()){
				
			}else{
				MultipartFile newFile;
				StringBuffer sBuffer = new StringBuffer();
				for (int i = 0; i < files.size(); i++) {
					newFile = files.get(i);
					String s = newFile.getOriginalFilename();
					String Type = s.substring(s.lastIndexOf(".") + 1);
					String filename = UUID.randomUUID().toString().replaceAll("-", "");
					String path = request.getSession().getServletContext().getRealPath("/") +"upload/" + filename + "." + Type;
					File localFile = new File(path);
					localFile.setWritable(true, false);
					System.out.println(localFile.getAbsolutePath());
					if (!localFile.exists()) {
						localFile.mkdirs();
					}
					newFile.transferTo(localFile);
					sBuffer.append(filename +"."+Type+ ",");
				}
				tumingcheng = sBuffer.toString();
			}
			//获取接收人***********
			String jieshourenvalue = request.getParameter("jieshouren");
			//根据前台返回的jieshouren查到接收人id，辅导员、书记、学生处管理员
			int jieshouren = 0;
			String banjiid1 = user.getBanjiid().toString()+",%";
			String banjiid2 = "%,"+user.getBanjiid().toString()+",%";
			FuDaoYuan fuDaoYuan = fudaoyuanService.getBybanJiID(banjiid1,banjiid2);
			if(jieshourenvalue.equals("1")){//辅导员
				jieshouren = fuDaoYuan.getFudaoyuanid();
			}else if(jieshourenvalue.equals("2")){//书记
				jieshouren = fuDaoYuan.getShujiid();
			}else if(jieshourenvalue.equals("3")){//学生处管理员
				ShuJi shuJi = shujiService.selectByPrimaryKey(fuDaoYuan.getShujiid());
				jieshouren = shuJi.getXueshengchuguanliyuanid();
			}
			
			String biaoqian = request.getParameter("biaoqian");//获取标签
			String niming = request.getParameter("isniming");//获取匿名状态
			//赋值给实体类*********
			yiJianXiang.setYijianmingcheng(mingcheng);//意见名称
			yiJianXiang.setXueshengid(user.getXueshengid());//学生id
			yiJianXiang.setJieshourenleixing(Integer.parseInt(jieshourenvalue));//接收人类型
			yiJianXiang.setJieshourenid(jieshouren);//接收人id
			yiJianXiang.setWenzineirong(neirong);//文字内容
			yiJianXiang.setTumingcheng(tumingcheng);//图片名称
			if(niming.equals("0")){
				yiJianXiang.setNimingbiaoji("0");//匿名标记
			}else{
				yiJianXiang.setNimingbiaoji("1");//匿名标记
			}
			yiJianXiang.setChulibiaoji("0");//处理标记为0
			yiJianXiang.setGongbuzhuangtai("0");//加精标记为0
			yiJianXiang.setGuoqibiaoji("0");//过期标记为0
			yiJianXiang.setDianzanliang(0);//点赞量为0
			yiJianXiang.setLiulanliang(0);//浏览量为0
			yiJianXiang.setPinglunliang(0);//评论量为0
			yiJianXiang.setBiaoqian(biaoqian);//标签，意见类型
			//yiJianXiang.setKejianxueshengid(kejianren);//可见学生id
			//yiJianXiang.setKejianrenfanwei(kejianrenvalue);//可见人范围
			
			if(request.getParameter("action").equals("提交")){//提交给接收人
				yiJianXiang.setTijiaozhuangtai("1");//提交状态为1
				Date date = new Date();
				yiJianXiang.setTijiaoshijian(date);
				yiJianXiang.setBaocunshijian(date);
				int i=0;
				if(tumingcheng.equals("")){
					i=yijianxiangService.insert2(yiJianXiang);
				}else{
					i = yijianxiangService.insert(yiJianXiang);
				}
				/*System.out.println(neirong);
				System.out.println(tumingcheng);
				System.out.println(jieshouren);
				System.out.println(kejianren);
				System.out.println(biaoqian);
				System.out.println(niming);
				System.out.println(tijiaozhuangtai);*/
				if(i!=0){
					out.print("<script>alert('提交成功！');</script>");
					out.print("<script>location='wodeyijian';</script>");
				}else{
					response.setContentType("text/html; charset=utf-8");
					out.print("<script>alert('fail');</script>");
				}
				out.close();
			}else if(request.getParameter("action").equals("保存")){//保存草稿箱
				yiJianXiang.setTijiaozhuangtai("0");//提交状态为0
				Date date = new Date();
				yiJianXiang.setBaocunshijian(date);//保存时间
				yiJianXiang.setTijiaoshijian(date);
				int i=0;
				if(tumingcheng.equals("")){
					i=yijianxiangService.insert2(yiJianXiang);
				}else{
					i = yijianxiangService.insert(yiJianXiang);
				}
				/*System.out.println(neirong);
				System.out.println(tumingcheng);
				System.out.println(jieshouren);
				System.out.println(kejianren);
				System.out.println(biaoqian);
				System.out.println(niming);
				System.out.println(tijiaozhuangtai);*/
				if(i!=0){
					out.print("<script>alert('保存成功！');</script>");
					out.print("<script>location='yijiancaogaoxiang';</script>");
				}else{
					response.setContentType("text/html; charset=utf-8");
					out.print("<script>alert('fail');</script>");
				}
				out.close();
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	
	@RequestMapping(value = "yijiancaogaoxiang")//草稿箱，查看保存但未提交的意见
	public ModelAndView yijiancaogaoxiang(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			List<YiJianXiang> yiJianXiangs = yijianxiangService.getAllByxueShengIDandtiJiaoZhuangTai(user.getXueshengid(), "0");
			mv.addObject("yijian", yiJianXiangs);
			mv.setViewName("stu/yijiancaogaoxiang");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "deleteyijian")//删除意见
	public void deleteyijian(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String xueshengid = user.getXueshengid().toString();
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return;
			}
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(id));
			//System.out.println(yiJianXiang);
			if(yiJianXiang!=null && yiJianXiang.getXueshengid().toString().equals(xueshengid)){
				yijianxiangService.deleteByPrimaryKey(Integer.parseInt(id));
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>location='yijiancaogaoxiang';</script>");
			}else{
				response.sendRedirect("logout");
				return;
			}
		} else {
			response.sendRedirect("login");
			return;
		}
	}
	
	@RequestMapping(value = "xiugaiyijian")//修改意见
	public ModelAndView xiugaiyijian(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String xueshengid = user.getXueshengid().toString();
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(id));
			if(yiJianXiang!=null && yiJianXiang.getXueshengid().toString().equals(xueshengid)){
				mv.addObject("yijian", yiJianXiang);
				mv.setViewName("stu/xiugaiyijian");
				return mv;
			}else{
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("login");
			return null;
		}
	}
	
	@RequestMapping(value = "saveorsubmitupdateyijian")//保存或是直接提交修改的意见
	@ResponseBody
	public void saveorsubmitupdateyijian(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			YiJianXiang yiJianXiang = new YiJianXiang();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			XueSheng user = (XueSheng) session.getAttribute("user");
			String yijianid = request.getParameter("id");//获取意见id
			if (!Util.isNumeric(yijianid)) {
				response.sendRedirect("logout");
				return;
			}
			YiJianXiang yiJianXiang2 = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if(yiJianXiang2!=null && yiJianXiang2.getXueshengid().equals(user.getXueshengid())){
				String mingcheng = request.getParameter("mingcheng");//获取名称
				String neirong = request.getParameter("neirong");//获取内容
				//获取图片***********
				List<MultipartFile> files = multipartRequest.getFiles("tumingcheng");
				String tumingcheng = "";
				if(files.get(0).isEmpty()){
					
				}else{
					MultipartFile newFile;
					StringBuffer sBuffer = new StringBuffer();
					for (int i = 0; i < files.size(); i++) {
						newFile = files.get(i);
						String s = newFile.getOriginalFilename();
						String Type = s.substring(s.lastIndexOf(".") + 1);
						String filename = UUID.randomUUID().toString().replaceAll("-", "");
						String path = request.getSession().getServletContext().getRealPath("/") +"upload/" + filename + "." + Type;
						File localFile = new File(path);
						localFile.setWritable(true, false);
						System.out.println(localFile.getAbsolutePath());
						if (!localFile.exists()) {
							localFile.mkdirs();
						}
						newFile.transferTo(localFile);
						sBuffer.append(filename +"."+Type+ ",");
					}
					tumingcheng = sBuffer.toString();
				}
				//获取接收人***********
				String jieshourenvalue = request.getParameter("jieshouren");
				//根据前台返回的jieshouren查到接收人id，辅导员、书记、学生处管理员
				int jieshouren = 0;
				String banjiid1 = user.getBanjiid().toString()+",%";
				String banjiid2 = "%,"+user.getBanjiid().toString()+",%";
				FuDaoYuan fuDaoYuan = fudaoyuanService.getBybanJiID(banjiid1,banjiid2);
				if(jieshourenvalue.equals("1")){//辅导员
					jieshouren = fuDaoYuan.getFudaoyuanid();
				}else if(jieshourenvalue.equals("2")){//书记
					jieshouren = fuDaoYuan.getShujiid();
				}else if(jieshourenvalue.equals("3")){//学生处管理员
					ShuJi shuJi = shujiService.selectByPrimaryKey(fuDaoYuan.getShujiid());
					jieshouren = shuJi.getXueshengchuguanliyuanid();
				}
				
				String biaoqian = request.getParameter("biaoqian");//获取标签
				String niming = request.getParameter("isniming");//获取匿名状态
				//赋值给实体类*********
				yiJianXiang.setYijianid(Integer.parseInt(yijianid));//意见id
				yiJianXiang.setYijianmingcheng(mingcheng);//意见名称
				yiJianXiang.setXueshengid(user.getXueshengid());//学生id
				yiJianXiang.setJieshourenleixing(Integer.parseInt(jieshourenvalue));//接收人类型
				yiJianXiang.setJieshourenid(jieshouren);//接收人id
				yiJianXiang.setWenzineirong(neirong);//文字内容
				yiJianXiang.setTumingcheng(tumingcheng);//图片名称
				if(niming.equals("0")){
					yiJianXiang.setNimingbiaoji("0");//匿名标记
				}else{
					yiJianXiang.setNimingbiaoji("1");//匿名标记
				}
				yiJianXiang.setChulibiaoji("0");//处理标记为0
				yiJianXiang.setGongbuzhuangtai("0");//加精标记为0
				yiJianXiang.setGuoqibiaoji("0");//过期标记为0
				yiJianXiang.setDianzanliang(0);//点赞量为0
				yiJianXiang.setLiulanliang(0);//浏览量为0
				yiJianXiang.setPinglunliang(0);//评论量为0
				yiJianXiang.setBiaoqian(biaoqian);//标签，意见类型
				//yiJianXiang.setKejianxueshengid(kejianren);//可见学生id
				//yiJianXiang.setKejianrenfanwei(kejianrenvalue);//可见人范围
				
				if(request.getParameter("action").equals("保存")){//保存草稿箱
					yiJianXiang.setTijiaozhuangtai("0");//提交状态为0
					Date date = new Date();
					yiJianXiang.setBaocunshijian(date);//保存时间
					yiJianXiang.setTijiaoshijian(date);
					int i=0;
					if(tumingcheng.equals("")){
						i = yijianxiangService.updateByPrimaryKey2(yiJianXiang);
					}else{
						i = yijianxiangService.updateByPrimaryKey(yiJianXiang);
					}
					/*System.out.println(neirong);
					System.out.println(tumingcheng);
					System.out.println(jieshouren);
					System.out.println(kejianren);
					System.out.println(biaoqian);
					System.out.println(niming);
					System.out.println(tijiaozhuangtai);*/
					if(i!=0){
						out.print("<script>alert('保存成功！');</script>");
						out.print("<script>location='yijiancaogaoxiang';</script>");
					}else{
						response.setContentType("text/html; charset=utf-8");
						out.print("<script>alert('fail');</script>");
					}
					out.close();
				}else if(request.getParameter("action").equals("提交")){//提交给接收人
					yiJianXiang.setTijiaozhuangtai("1");//提交状态为1
					Date date = new Date();
					yiJianXiang.setTijiaoshijian(date);
					yiJianXiang.setBaocunshijian(date);
					int i=0;
					if(tumingcheng.equals("")){
						i = yijianxiangService.updateByPrimaryKey2(yiJianXiang);
					}else{
						i = yijianxiangService.updateByPrimaryKey(yiJianXiang);
					}
					/*System.out.println(neirong);
					System.out.println(tumingcheng);
					System.out.println(jieshouren);
					System.out.println(kejianren);
					System.out.println(biaoqian);
					System.out.println(niming);
					System.out.println(tijiaozhuangtai);*/
					if(i!=0){
						out.print("<script>alert('提交成功！');</script>");
						out.print("<script>location='wodeyijian';</script>");
					}else{
						response.setContentType("text/html; charset=utf-8");
						out.print("<script>alert('fail');</script>");
					}
					out.close();
				}
			}else{
				response.sendRedirect("logout");
				return;
			}
		} else {
			response.sendRedirect("login");
		}
		return;
	}
	
	@RequestMapping(value = "yijiandongtai")//意见动态
	public ModelAndView yijiandongtai(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		List<YiJianXiang> yiJianXiangs = new ArrayList<>();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			Map<String, String> map = new HashMap<>();
			map.put("kejianxueshengid1", user.getXueshengid().toString()+",%");
			map.put("kejianxueshengid2", "%,"+user.getXueshengid().toString()+",%");
			map.put("biaoqian", "");
			map.put("nimingbiaoji", "");
			map.put("tijiaozhuangtai", "1");
			map.put("gongbuzhuangtai", "1");
			yiJianXiangs = yijianxiangService.getAllByXSandBQandNM(map);
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
						if(string.equals(user.getXueshengid().toString())){
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
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.addObject("userid", user.getXueshengid());
			mv.setViewName("stu/yijiandongtai");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "chaxunyijian")//查询意见
	public ModelAndView chaxunyijian(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			List<YiJianXiang> yiJianXiangs = new ArrayList<>();
			String biaoqian = request.getParameter("biaoqian");
			String fanwei = request.getParameter("fanwei");
			String niming =  request.getParameter("niming");
			if(fanwei.equals("")){
				Map<String, String> map = new HashMap<>();
				map.put("kejianxueshengid1", user.getXueshengid().toString()+",%");
				map.put("kejianxueshengid2", "%,"+user.getXueshengid().toString()+",%");
				map.put("biaoqian", biaoqian);
				map.put("nimingbiaoji", niming);
				map.put("tijiaozhuangtai", "1");
				map.put("gongbuzhuangtai", "1");
				yiJianXiangs = yijianxiangService.getAllByXSandBQandNM(map);
			}
			if(fanwei.equals("本班")){
				Map<String, String> map = new HashMap<>();
				map.put("kejianxueshengid1", user.getXueshengid().toString()+",%");
				map.put("kejianxueshengid2", "%,"+user.getXueshengid().toString()+",%");
				map.put("biaoqian", biaoqian);
				map.put("banji", user.getBanjiid().toString());
				map.put("nimingbiaoji", niming);
				map.put("tijiaozhuangtai", "1");
				map.put("gongbuzhuangtai", "1");
				yiJianXiangs = yijianxiangService.getAllByXSandBQandBJandNM(map);
			}
			if(fanwei.equals("本专业")){
				String zhuanyedaima = zhuanyeService.selectByPrimaryKey(banjiService.selectByPrimaryKey(user.getBanjiid()).getZhuanyeid()).getDaima();
				String yuanxiid = banjiService.selectByPrimaryKey(user.getBanjiid()).getYuanxiid().toString();
				Map<String, String> map = new HashMap<>();
				map.put("kejianxueshengid1", user.getXueshengid().toString()+",%");
				map.put("kejianxueshengid2", "%,"+user.getXueshengid().toString()+",%");
				map.put("biaoqian", biaoqian);
				map.put("zhuanyedaima", zhuanyedaima);
				map.put("yuanxiid", yuanxiid);
				map.put("nimingbiaoji", niming);
				map.put("tijiaozhuangtai", "1");
				map.put("gongbuzhuangtai", "1");
				yiJianXiangs = yijianxiangService.getAllByXSandBQandZYandNM(map);
			}
			if(fanwei.equals("本学院")){
				String yuanxiid = banjiService.selectByPrimaryKey(user.getBanjiid()).getYuanxiid().toString();
				Map<String, String> map = new HashMap<>();
				map.put("kejianxueshengid1", user.getXueshengid().toString()+",%");
				map.put("kejianxueshengid2", "%,"+user.getXueshengid().toString()+",%");
				map.put("biaoqian", biaoqian);
				map.put("yuanxiid", yuanxiid);
				map.put("nimingbiaoji", niming);
				map.put("tijiaozhuangtai", "1");
				map.put("gongbuzhuangtai", "1");
				yiJianXiangs = yijianxiangService.getAllByXSandBQandYXandNM(map);
			}
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
						if(string.equals(user.getXueshengid().toString())){
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
			mv.addObject("fanwei", fanwei);
			mv.addObject("niming", niming);
			mv.addObject("userid", user.getXueshengid());
			mv.setViewName("stu/yijiandongtai");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	
	@RequestMapping(value = "dianzan")//点赞
	@ResponseBody
	public String dianzan(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
			XueSheng user = (XueSheng) session.getAttribute("user");
			String result = "";
			String yijianid = request.getParameter("CODE");
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			int dianzanliang = yiJianXiang.getDianzanliang();
			String dianzanren = yiJianXiang.getDianzanrenid();
			if(dianzanliang==0){
				dianzanren = user.getXueshengid()+",";
				dianzanliang++;
				result = "add,"+dianzanliang;
			}else{
				String dianzanrens[]=dianzanren.split(",");
				String newdianzanren="";
				int i = 0;
				for (String string : dianzanrens) {
					if(string.equals(user.getXueshengid().toString())){
						i++;
					}else{
						newdianzanren += string+",";
					}
				}
				if(i==0){
					dianzanren += user.getXueshengid()+",";
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
		}
	
	@RequestMapping(value = "submitpinglun")//意见评论,在我的意见
	public void submitpinglun(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String yijianid = request.getParameter("id");
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if(yiJianXiang == null){
				response.sendRedirect("logout");
				return;
			}
			String huifuneirong = request.getParameter("pinglunneirong");
			System.out.println(huifuneirong);
			Date date = new Date();
			YiJianHuiFu yiJianHuiFu = new YiJianHuiFu();
			yiJianHuiFu.setYijianid(Integer.parseInt(yijianid));
			yiJianHuiFu.setHuifuneirong(huifuneirong);
			yiJianHuiFu.setShijian(date);
			yiJianHuiFu.setXueshengid(user.getXueshengid());
			int i = yijianhuifuService.insert(yiJianHuiFu);
			if(i!=0){
				int pinglunliang = yiJianXiang.getPinglunliang();
				YiJianXiang yiJianXiang2 = new YiJianXiang();
				yiJianXiang2.setYijianid(yiJianXiang.getYijianid());
				yiJianXiang2.setPinglunliang(pinglunliang+1);
				yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang2);
				out.print("<script>alert('评论成功！');</script>");
				out.print("<script>location='wodeyijian';</script>");
			}else{
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('评论失败！');</script>");
			}
			out.close();
		} else {
			response.sendRedirect("login");
			return;
		}
	}
	
	@RequestMapping(value = "submithuifujiaoshi")//在评论下回复评论人，被回复人--教师,在我的意见
	public void submithuifujiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String id = request.getParameter("id");
			String ids[] = id.split("_");
			String yijianid = ids[0];
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if(yiJianXiang == null){
				response.sendRedirect("logout");
				return;
			}
			String beihuifujiaoshiid = ids[1];
			String huifuneirong = request.getParameter("huifuneirong");
			Date date = new Date();
			YiJianHuiFu yiJianHuiFu = new YiJianHuiFu();
			yiJianHuiFu.setYijianid(Integer.parseInt(yijianid));
			yiJianHuiFu.setHuifuneirong(huifuneirong);
			yiJianHuiFu.setShijian(date);
			yiJianHuiFu.setXueshengid(user.getXueshengid());
			yiJianHuiFu.setBeihuifujiaoshiid(Integer.parseInt(beihuifujiaoshiid));
			int i = yijianhuifuService.insert2(yiJianHuiFu);
			if(i!=0){
				int pinglunliang = yiJianXiang.getPinglunliang();
				YiJianXiang yiJianXiang2 = new YiJianXiang();
				yiJianXiang2.setYijianid(yiJianXiang.getYijianid());
				yiJianXiang2.setPinglunliang(pinglunliang+1);
				yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang2);
				out.print("<script>alert('回复成功！');</script>");
				out.print("<script>location='wodeyijian';</script>");
			}else{
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('回复失败！');</script>");
			}
			out.close();
		} else {
			response.sendRedirect("login");
			return;
		}
	}
	
	@RequestMapping(value = "submithuifuxuesheng")//在评论下回复评论人，被回复人--学生,在我的意见
	public void submithuifuxuesheng(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String id = request.getParameter("id");
			String ids[] = id.split("_");
			String yijianid = ids[0];
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if(yiJianXiang == null){
				response.sendRedirect("logout");
				return;
			}
			String beihuifuxueshengid = ids[1];
			String huifuneirong = request.getParameter("huifuneirong");
			Date date = new Date();
			YiJianHuiFu yiJianHuiFu = new YiJianHuiFu();
			yiJianHuiFu.setYijianid(Integer.parseInt(yijianid));
			yiJianHuiFu.setHuifuneirong(huifuneirong);
			yiJianHuiFu.setShijian(date);
			yiJianHuiFu.setXueshengid(user.getXueshengid());
			yiJianHuiFu.setBeihuifuxueshengid(Integer.parseInt(beihuifuxueshengid));
			int i = yijianhuifuService.insert3(yiJianHuiFu);
			if(i!=0){
				int pinglunliang = yiJianXiang.getPinglunliang();
				YiJianXiang yiJianXiang2 = new YiJianXiang();
				yiJianXiang2.setYijianid(yiJianXiang.getYijianid());
				yiJianXiang2.setPinglunliang(pinglunliang+1);
				yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang2);
				out.print("<script>alert('回复成功！');</script>");
				out.print("<script>location='wodeyijian';</script>");
			}else{
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('回复失败！');</script>");
			}
			out.close();
		} else {
			response.sendRedirect("login");
			return;
		}
	}
	
	@RequestMapping(value = "deletehuifu")//删除意见,在意见动态里
	@ResponseBody
	public String deletehuifu(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (Util.checkSession(request)){
			XueSheng user = (XueSheng) session.getAttribute("user");
			String result = "";
				String id = request.getParameter("CODE");
				YiJianHuiFu yiJianHuiFu = yijianhuifuService.selectByPrimaryKey(Integer.parseInt(id));
				if(yiJianHuiFu == null){
					result= "null,";
					return result;
				}
				if(yiJianHuiFu.getXueshengid()==null){
					return "false,";
				}
				int xueshengid = yiJianHuiFu.getXueshengid();
				if(xueshengid!=user.getXueshengid()){
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
	
	@RequestMapping(value = "subpinglun")//意见评论,在意见动态里
	public void subpinglun(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String yijianid = request.getParameter("id");
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if(yiJianXiang == null){
				response.sendRedirect("logout");
				return;
			}
			String huifuneirong = request.getParameter("pinglunneirong");
			System.out.println(huifuneirong);
			Date date = new Date();
			YiJianHuiFu yiJianHuiFu = new YiJianHuiFu();
			yiJianHuiFu.setYijianid(Integer.parseInt(yijianid));
			yiJianHuiFu.setHuifuneirong(huifuneirong);
			yiJianHuiFu.setShijian(date);
			yiJianHuiFu.setXueshengid(user.getXueshengid());
			int i = yijianhuifuService.insert(yiJianHuiFu);
			if(i!=0){
				int pinglunliang = yiJianXiang.getPinglunliang();
				YiJianXiang yiJianXiang2 = new YiJianXiang();
				yiJianXiang2.setYijianid(yiJianXiang.getYijianid());
				yiJianXiang2.setPinglunliang(pinglunliang+1);
				yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang2);
				out.print("<script>alert('评论成功！');</script>");
				out.print("<script>location='yijiandongtai';</script>");
			}else{
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('评论失败！');</script>");
			}
			out.close();
		} else {
			response.sendRedirect("login");
			return;
		}
	}
	
	@RequestMapping(value = "subhuifujiaoshi")//在评论下回复评论人，被回复人--教师,在意见动态里
	public void subhuifujiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String id = request.getParameter("id");
			String ids[] = id.split("_");
			String yijianid = ids[0];
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if(yiJianXiang == null){
				response.sendRedirect("logout");
				return;
			}
			String beihuifujiaoshiid = ids[1];
			String huifuneirong = request.getParameter("huifuneirong");
			Date date = new Date();
			YiJianHuiFu yiJianHuiFu = new YiJianHuiFu();
			yiJianHuiFu.setYijianid(Integer.parseInt(yijianid));
			yiJianHuiFu.setHuifuneirong(huifuneirong);
			yiJianHuiFu.setShijian(date);
			yiJianHuiFu.setXueshengid(user.getXueshengid());
			yiJianHuiFu.setBeihuifujiaoshiid(Integer.parseInt(beihuifujiaoshiid));
			int i = yijianhuifuService.insert2(yiJianHuiFu);
			if(i!=0){
				int pinglunliang = yiJianXiang.getPinglunliang();
				YiJianXiang yiJianXiang2 = new YiJianXiang();
				yiJianXiang2.setYijianid(yiJianXiang.getYijianid());
				yiJianXiang2.setPinglunliang(pinglunliang+1);
				yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang2);
				out.print("<script>alert('回复成功！');</script>");
				out.print("<script>location='yijiandongtai';</script>");
			}else{
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('回复失败！');</script>");
			}
			out.close();
		} else {
			response.sendRedirect("login");
			return;
		}
	}
	
	@RequestMapping(value = "subhuifuxuesheng")//在评论下回复评论人，被回复人--学生,在意见动态里
	public void subhuifuxuesheng(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String id = request.getParameter("id");
			String ids[] = id.split("_");
			String yijianid = ids[0];
			YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if(yiJianXiang == null){
				response.sendRedirect("logout");
				return;
			}
			String beihuifuxueshengid = ids[1];
			String huifuneirong = request.getParameter("huifuneirong");
			Date date = new Date();
			YiJianHuiFu yiJianHuiFu = new YiJianHuiFu();
			yiJianHuiFu.setYijianid(Integer.parseInt(yijianid));
			yiJianHuiFu.setHuifuneirong(huifuneirong);
			yiJianHuiFu.setShijian(date);
			yiJianHuiFu.setXueshengid(user.getXueshengid());
			yiJianHuiFu.setBeihuifuxueshengid(Integer.parseInt(beihuifuxueshengid));
			int i = yijianhuifuService.insert3(yiJianHuiFu);
			if(i!=0){
				int pinglunliang = yiJianXiang.getPinglunliang();
				YiJianXiang yiJianXiang2 = new YiJianXiang();
				yiJianXiang2.setYijianid(yiJianXiang.getYijianid());
				yiJianXiang2.setPinglunliang(pinglunliang+1);
				yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang2);
				out.print("<script>alert('回复成功！');</script>");
				out.print("<script>location='yijiandongtai';</script>");
			}else{
				response.setContentType("text/html; charset=utf-8");
				out.print("<script>alert('回复失败！');</script>");
			}
			out.close();
		} else {
			response.sendRedirect("login");
			return;
		}
	}
	
	@RequestMapping(value = "delhuifu")//删除意见,在意见动态里
	@ResponseBody
	public String delhuifu(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (Util.checkSession(request)){
			XueSheng user = (XueSheng) session.getAttribute("user");
			String result = "";
				String id = request.getParameter("CODE");
				YiJianHuiFu yiJianHuiFu = yijianhuifuService.selectByPrimaryKey(Integer.parseInt(id));
				if(yiJianHuiFu == null){
					result= "null,";
					return result;
				}
				if(yiJianHuiFu.getXueshengid()==null){
					return "false,";
				}
				int xueshengid = yiJianHuiFu.getXueshengid();
				if(xueshengid!=user.getXueshengid()){
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
