package com.web.controller.web.student;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.web.annotation.LoginStatusAnnotation;
import com.web.model.Qingjia;
import com.web.model.XueSheng;
import com.web.service.QingjiaService;
import com.web.service.YongHuService;
import com.web.util.Util;

/*包括所有的学生menu请求操作 */
/*dispatcher接受所有的请求操作，
 * 执行对应的方法，
 * 读取相应的数据，
 * 返回给相应的前端页面*/
@Controller
@LoginStatusAnnotation(status="xuesheng")
public class SMenuController {
	static Logger logger = Logger.getLogger(SMenuController.class);
	@Autowired
	private QingjiaService qingjiaService;
	
	@Autowired
	private YongHuService yonghuService;

	// 请求当前用户请假列表
	// 分页查询
	@RequestMapping(value = "myApplication")
	public ModelAndView myAppliction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mView = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			Map<String, Integer> map = new HashMap<>();
			int count = qingjiaService.getCountByXueShengID(user.getXueshengid());
			int pageSize = 10;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				map.put("id", user.getXueshengid());
				map.put("start", 0);
				map.put("stop", 10);
				List<Qingjia> jiatiao = qingjiaService.getAllByXueShengID(map);
				if (!jiatiao.isEmpty()) {
					for (Qingjia qingjia : jiatiao) {
						if (qingjia.getPizhunren()!=null) {
							qingjia.setPizhunren(yonghuService.selectYongHuByID(Integer.parseInt(qingjia.getPizhunren())).getYonghuxingming());
						}					
					}
				}
				mView.addObject("jiatiao", jiatiao);
				mView.addObject("pages", pages);
				mView.addObject("page", 1);
				mView.setViewName("stu/myApplication");
				return mView;
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					int page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page <= pages) {
						map.put("id", user.getXueshengid());
						map.put("start", (page - 1) * 10);
						map.put("stop", 10);
						List<Qingjia> jiatiao = qingjiaService.getAllByXueShengID(map);
						if (!jiatiao.isEmpty()) {
							for (Qingjia qingjia : jiatiao) {
								if (qingjia.getPizhunren()!=null) {
									qingjia.setPizhunren(yonghuService.selectYongHuByID(Integer.parseInt(qingjia.getPizhunren())).getYonghuxingming());
								}
							}
						}
						mView.addObject("jiatiao", jiatiao);
						mView.addObject("pages", pages);
						mView.addObject("page", page);
						mView.setViewName("stu/myApplication");
						return mView;
					} else {
						response.sendRedirect("logout");
					}
				} else {
					response.sendRedirect("logout");
				}
			}
		} else {
			response.sendRedirect("login");
		}
		return null;

	}

	@RequestMapping(value = "forApplication")
	public ModelAndView forApplication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("stu/forApplication");
		return mv;
	}

	// 提交请假申请
	@RequestMapping(value = "subapplication")
	@ResponseBody
	public void subapplication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			XueSheng user = (XueSheng) request.getSession().getAttribute("user");
			Map<String, String> map = new HashMap<String, String>();
			String leibie = multipartRequest.getParameter("qingjialeibie");
			String shiyou = multipartRequest.getParameter("qingjiashiyou");
			String kaishishijian = multipartRequest.getParameter("kaishishijian");
			String jieshushijian = multipartRequest.getParameter("jieshushijian");
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("xueshengID", user.getXueshengid().toString());
			map.put("leibie", leibie);
			map.put("shiyou", shiyou);
			map.put("kaishishijian", kaishishijian);
			map.put("jieshushijian", jieshushijian);
			map.put("shenqingshijian", df.format(date));
			List<MultipartFile> files = multipartRequest.getFiles("files");
			logger.info(files.isEmpty());
			logger.info(files.size());
			if (files.isEmpty()) {
				int i = qingjiaService.insertByXueShengID(map);
				PrintWriter out = response.getWriter();
				if (i != 0) {
					response.setContentType("text/html; charset=utf-8");
					out.print("<script>alert('提交成功');</script>");
					out.print("<script>location='myApplication';</script>");
				} else {
					response.setContentType("text/html; charset=utf-8");
					out.print("<script>alert('fail');</script>");
				}
				out.close();
			} else {
				MultipartFile newFile;
				StringBuffer sBuffer = new StringBuffer();
				for (int i = 0; i < files.size(); i++) {
					newFile = files.get(i);
					if (newFile.isEmpty()) {
						continue;
					}
					String s = newFile.getOriginalFilename();
					String Type = s.substring(s.lastIndexOf(".") + 1);
					String filename = UUID.randomUUID().toString().replaceAll("-", "");
					String path = request.getSession().getServletContext().getRealPath("/") +"upload/" + filename + "." + Type;
					logger.info(path);
					File localFile = new File(path);
					localFile.setWritable(true, false);
					localFile.setExecutable(true, false);
					localFile.setReadable(true, false);
					System.out.println(localFile.getAbsolutePath());
					if (!localFile.exists()) {
						localFile.mkdirs();
					}
					newFile.transferTo(localFile);
					sBuffer.append(filename +"."+Type+",");
				}
				map.put("bingjiazhengming", sBuffer.toString());
				int i = qingjiaService.insertByXueShengIDWithFile(map);
				PrintWriter out = response.getWriter();
				if (i != 0) {
					response.setContentType("text/html; charset=utf-8");
					out.print("<script>alert('提交成功');</script>");
					out.print("<script>location='myApplication';</script>");
				} else {
					response.setContentType("text/html; charset=utf-8");
					out.print("<script>alert('fail');</script>");
				}
				out.close();
			}
		} else {
			response.sendRedirect("logout");
		}
	}

	// 销假
	@RequestMapping(value = "cfm")
	public void cfm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getSession().getAttribute("user") != null && request.getSession().getAttribute("user") != "") {
			// 获取前端通过url传递的参数
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
			}
			XueSheng user = (XueSheng) request.getSession().getAttribute("user");
			Map<String, String> map = new HashMap<String, String>();
			map.put("xueshengid", user.getXueshengid().toString());
			map.put("qingjiaid", id);
			// 防止用户通过url恶意传递参数修改数据库，和数据库进行验证
			int zhuangTai = qingjiaService.selectZhuangTaiByXueShengIDAndQingJiaId(map);
			if (zhuangTai == 2) {
				int i = qingjiaService.updateStatusByQingjiaId(Integer.parseInt(id));
				if (i != 0) {
					response.sendRedirect("myApplication");
				} else {
					response.sendRedirect("logout");
				}
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}

	}

	// 修改假条
	@RequestMapping(value = "xiuGai")
	public ModelAndView xiuGai(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(Util.checkSession(request)) {
			// 获取前端通过url传递的参数
			String id = request.getParameter("id");
			XueSheng user = (XueSheng) request.getSession().getAttribute("user");
			String xueshengID = user.getXueshengid().toString();
			Map<String, String> map = new HashMap<String, String>();
			map.put("xueshengid", xueshengID);
			map.put("qingjiaid", id);
			// 防止用户通过url恶意传递参数修改数据库，和数据库进行验证
			int zhuangTai = qingjiaService.selectZhuangTaiByXueShengIDAndQingJiaId(map);
			if (zhuangTai == 1) {
				Qingjia jiatiao = qingjiaService.getById(Integer.parseInt(id));
				if (jiatiao.getBingjiazhengming()!=null&&!jiatiao.getBingjiazhengming().equals("")) {
					List<String> tuPian = new ArrayList<>();
					String tuPianS[]=jiatiao.getBingjiazhengming().split(",");
					for (int i = 0; i < tuPianS.length; i++) {
						tuPian.add(tuPianS[i]);
					}
					jiatiao.setTuPian(tuPian);
				}
				ModelAndView mView = new ModelAndView();
				mView.setViewName("stu/xiugaijiatiao");
				mView.addObject("jiatiao", jiatiao);
				return mView;
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	// 再次提交
	@RequestMapping(value = "reSub")
	public ModelAndView reSub(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getSession().getAttribute("user") != null && request.getSession().getAttribute("user") != "") {
			// 获取前端通过url传递的参数
			String id = request.getParameter("id");
			XueSheng user = (XueSheng) request.getSession().getAttribute("user");
			String xueshengID = user.getXueshengid().toString();
			Map<String, String> map = new HashMap<String, String>();
			map.put("xueshengid", xueshengID);
			map.put("qingjiaid", id);
			// 防止用户通过url恶意传递参数修改数据库，和数据库进行验证
			int zhuangTai = qingjiaService.selectZhuangTaiByXueShengIDAndQingJiaId(map);
			int tiJiaoCiShu = qingjiaService.selectTiJiaoCiShuByXueShengIDAndQingJiaId(map);
			if (zhuangTai == 3 && tiJiaoCiShu < 2) {
				Qingjia jiatiao = qingjiaService.getById(Integer.parseInt(id));
				if (jiatiao.getBingjiazhengming()!=null&&!jiatiao.getBingjiazhengming().equals("")) {
					List<String> tuPian = new ArrayList<>();
					String tuPianS[]=jiatiao.getBingjiazhengming().split(",");
					for (int i = 0; i < tuPianS.length; i++) {
						tuPian.add(tuPianS[i]);
					}
					jiatiao.setTuPian(tuPian);
				}
				ModelAndView mView = new ModelAndView();
				mView.setViewName("stu/zaicitijiao");
				mView.addObject("jiatiao", jiatiao);
				return mView;
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	// 再次提交时更新假条
	@RequestMapping(value = "updateapplication")
	@ResponseBody
	public void updateapplication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getSession().getAttribute("user") != null && request.getSession().getAttribute("user") != "") {
			// 获取前端通过url传递的参数
			String id = request.getParameter("id");
			System.out.println(id);
			String imgSrc[] = request.getParameter("src").split(",");
			List<String> imgList = Arrays.asList(imgSrc);
			XueSheng user = (XueSheng) request.getSession().getAttribute("user");
			String xueshengid = user.getXueshengid().toString();
			Map<String, String> map = new HashMap<String, String>();
			map.put("xueshengid", xueshengid);
			map.put("qingjiaid", id);
			// 防止用户通过url恶意传递参数修改数据库，和数据库进行验证
			int zhuangTai = qingjiaService.selectZhuangTaiByXueShengIDAndQingJiaId(map);
			int tiJiaoCiShu = qingjiaService.selectTiJiaoCiShuByXueShengIDAndQingJiaId(map);
			Qingjia qingjia = qingjiaService.getById(Integer.parseInt(id));
			if (zhuangTai == 3 && tiJiaoCiShu < 3) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				String leibie = multipartRequest.getParameter("qingjialeibie");
				String shiyou = multipartRequest.getParameter("qingjiashiyou");
				String kaishishijian = multipartRequest.getParameter("kaishishijian");
				System.out.println(kaishishijian);
				String jieshushijian = multipartRequest.getParameter("jieshushijian");
				Date date = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				map.put("leibie", leibie);
				map.put("shiyou", shiyou);
				map.put("kaishishijian", kaishishijian);
				map.put("jieshushijian", jieshushijian);
				map.put("shenqingshijian", df.format(date));
				if(Integer.parseInt(leibie)==2){
					List<MultipartFile> files = multipartRequest.getFiles("files");
					String[] imgs = qingjia.getBingjiazhengming().split(",");
					if(files.isEmpty()){
						StringBuffer sBuffer = new StringBuffer();
						for (int i = 0; i < imgs.length; i++) {
							if (imgList.contains(imgs[i])) {
								sBuffer.append(imgs[i]+",");
							}
						}
						map.put("bingjiazhengming", sBuffer.toString());
						int i = qingjiaService.updateById(map);
						PrintWriter out = response.getWriter();
						if (i != 0) {
							response.setContentType("text/html; charset=utf-8");
							out.print("<script>alert('提交成功');</script>");
							out.print("<script>location='myApplication';</script>");
						} else {
							response.setContentType("text/html; charset=utf-8");
							out.print("<script>alert('fail');</script>");
						}
						out.close();

					} else {
						MultipartFile newFile;
						StringBuffer sBuffer = new StringBuffer();
						for (int i = 0; i < imgs.length; i++) {
							if (imgList.contains(imgs[i])) {
								sBuffer.append(imgs[i]+",");
							}
						}
						for (int i = 0; i < files.size(); i++) {
							newFile = files.get(i);
							if (newFile.isEmpty()) {
								continue;
							}
							String s = newFile.getOriginalFilename();
							String Type = s.substring(s.lastIndexOf(".") + 1);
							String filename = UUID.randomUUID().toString().replaceAll("-", "");
							String path = request.getSession().getServletContext().getRealPath("/") +"upload/" + filename + "." + Type;
							File localFile = new File(path);
							localFile.setWritable(true, false);
							if (!localFile.exists()) {
								localFile.mkdirs();
							}
							newFile.transferTo(localFile);
							sBuffer.append(filename +"."+Type+",");
							System.out.println(sBuffer);
						}
						map.put("bingjiazhengming", sBuffer.toString());
						int i = qingjiaService.updateById(map);
						PrintWriter out = response.getWriter();
						if (i != 0) {
							response.setContentType("text/html; charset=utf-8");
							out.print("<script>alert('提交成功');</script>");
							out.print("<script>location='myApplication';</script>");
						} else {
							response.setContentType("text/html; charset=utf-8");
							out.print("<script>alert('fail');</script>");
						}
						out.close();
					}
				}
				else{
					int i = qingjiaService.updateById(map);
					PrintWriter out = response.getWriter();
					if (i != 0) {
						response.setContentType("text/html; charset=utf-8");
						out.print("<script>alert('提交成功');</script>");
						out.print("<script>location='myApplication';</script>");
					} else {
						response.setContentType("text/html; charset=utf-8");
						out.print("<script>alert('fail');</script>");
					}
				}
				
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
	}

	// 修改假条
	@RequestMapping(value = "SubXiuGai")
	@ResponseBody
	public void SubXiuGai(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getSession().getAttribute("user") != null && request.getSession().getAttribute("user") != "") {
			// 获取前端通过url传递的参数
			String id = request.getParameter("id");
			System.out.println(id);
			String imgSrc[] = request.getParameter("src").split(",");
			List<String> imgList = Arrays.asList(imgSrc);
			XueSheng user = (XueSheng) request.getSession().getAttribute("user");
			String xueshengid = user.getXueshengid().toString();
			Map<String, String> map = new HashMap<String, String>();
			map.put("xueshengid",xueshengid);
			map.put("qingjiaid", id);
			// 防止用户通过url恶意传递参数修改数据库，和数据库进行验证
			int zhuangTai = qingjiaService.selectZhuangTaiByXueShengIDAndQingJiaId(map);
			Qingjia qingjia = qingjiaService.getById(Integer.parseInt(id));
			if (zhuangTai == 1) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				String leibie = multipartRequest.getParameter("qingjialeibie");
				String shiyou = multipartRequest.getParameter("qingjiashiyou");
				String kaishishijian = multipartRequest.getParameter("kaishishijian");
				System.out.println(kaishishijian);
				String jieshushijian = multipartRequest.getParameter("jieshushijian");
				Date date = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				map.put("leibie", leibie);
				map.put("shiyou", shiyou);
				map.put("kaishishijian", kaishishijian);
				map.put("jieshushijian", jieshushijian);
				map.put("shenqingshijian", df.format(date));
				List<MultipartFile> files = multipartRequest.getFiles("files");
				String[] imgs = qingjia.getBingjiazhengming().split(",");
				if(files.isEmpty()) {
					StringBuffer sBuffer = new StringBuffer();
					for (int i = 0; i < imgs.length; i++) {
						if (imgList.contains(imgs[i])) {
							sBuffer.append(imgs[i]+",");
						}
					}
					map.put("bingjiazhengming", sBuffer.toString());
					int i = qingjiaService.xiuGaiById(map);
					PrintWriter out = response.getWriter();
					if (i != 0) {
						response.setContentType("text/html; charset=utf-8");
						out.print("<script>alert('提交成功');</script>");
						out.print("<script>location='myApplication';</script>");
					} else {
						response.setContentType("text/html; charset=utf-8");
						out.print("<script>alert('fail');</script>");
					}
					out.close();

				} else {
					MultipartFile newFile;
					StringBuffer sBuffer = new StringBuffer();
					for (int i = 0; i < imgs.length; i++) {
						if (imgList.contains(imgs[i])) {
							sBuffer.append(imgs[i]+",");
						}
					}
					for (int i = 0; i < files.size(); i++) {
						newFile = files.get(i);
						if (newFile.isEmpty()) {
							continue;
						}
						String s = newFile.getOriginalFilename();
						String Type = s.substring(s.lastIndexOf(".") + 1);
						String filename = UUID.randomUUID().toString().replaceAll("-", "");
						String path = request.getSession().getServletContext().getRealPath("/") +"upload/" + filename + "." + Type;
						File localFile = new File(path);
						localFile.setWritable(true, false);
						if (!localFile.exists()) {
							localFile.mkdirs();
						}
						newFile.transferTo(localFile);
						sBuffer.append(filename +"."+Type+",");
					}
					map.put("bingjiazhengming", sBuffer.toString());
					int i = qingjiaService.xiuGaiById(map);
					PrintWriter out = response.getWriter();
					if (i != 0) {
						response.setContentType("text/html; charset=utf-8");
						out.print("<script>alert('提交成功');</script>");
						out.print("<script>location='myApplication';</script>");
					} else {
						response.setContentType("text/html; charset=utf-8");
						out.print("<script>alert('fail');</script>");
					}
					out.close();
				}
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
	}
}
