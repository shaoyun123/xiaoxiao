package com.web.controller.web.guanliyuan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.web.annotation.LoginStatusAnnotation;
import com.web.model.JiaoShi;
import com.web.model.JiaoXueLou;
import com.web.model.KeChengJiBen;
import com.web.model.SuSheLou;
import com.web.model.XiaoQu;
import com.web.model.XueShengChuGuanLiYuan;
import com.web.model.XueShengSuShe;
import com.web.model.YongHu;
import com.web.service.JiaoShiService;
import com.web.service.JiaoXueLouService;
import com.web.service.SuSheLouService;
import com.web.service.XiaoQuService;
import com.web.service.XueShengChuGuanLiYuanService;
import com.web.service.XueShengSuSheService;
import com.web.util.Util;

@Controller
@LoginStatusAnnotation(status = "guanliyuan")
public class JiChuSheShiController {

	@Autowired
	private JiaoShiService jiaoShiService;
	@Autowired
	private XiaoQuService xiaoQuService;
	@Autowired
	private JiaoXueLouService jiaoXueLouService;
	@Autowired
	private XueShengChuGuanLiYuanService xueShengChuGuanLiYuanService;
	@Autowired
	private SuSheLouService suSheLouService;
	@Autowired
	private XueShengSuSheService xueShengSuSheService;

	@RequestMapping(value = "addxiaoqu") // 添加校区
	public ModelAndView addxiaoqu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			mv.setViewName("guanliyuan/addxiaoqu");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savexiaoqu") // 保存添加
	@ResponseBody
	public String savexiaoqu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out=response.getWriter();
			Integer xueXiaoId = (Integer) session.getAttribute("xuexiaoid");
			List<XiaoQu> xiaoQus = xiaoQuService.getAllByxueXiaoID(xueXiaoId);
			String xiaoQuMingCheng = request.getParameter("xiaoqumingcheng");
			for (int i = 0; i < xiaoQus.size(); i++) {// 校区名称查重
				if (xiaoQuMingCheng.equals(xiaoQus.get(i).getMingcheng())) {
					// out.print("<script>alert('与其他校区名称相同!');</script>");
					// out.print("<script>location='addxiaoqu';</script>");
					return "chongming";
				}
			}
			XiaoQu xiaoQu = new XiaoQu();
			xiaoQu.setMingcheng(xiaoQuMingCheng);
			xiaoQu.setXuexiaoid(xueXiaoId);
			xiaoQu.setZhuangtai(1);
			int j = xiaoQuService.insertXiaoQu(xiaoQu);
			if (j != 0) {
				// out.print("<script>alert('添加成功!');</script>");
				// out.print("<script>location='xiaoquliebiao';</script>");
				return "success";
			} else {
				// out.print("<script>alert('添加失败!');</script>");
				// out.print("<script>location='addxiaoqu';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "xiaoquliebiao") // 校区列表
	public ModelAndView xiaoquliebiao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			List<XiaoQu> xiaoQus = xiaoQuService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));
			int count = xiaoQus.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<XiaoQu> xiaoQus2 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						xiaoQus2.add(xiaoQus.get(i));
					}
					mv.addObject("xiaoqu", xiaoQus2);
				} else {
					for (int i = 0; i < 10; i++) {
						xiaoQus2.add(xiaoQus.get(i));
					}
					mv.addObject("xiaoqu", xiaoQus2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<XiaoQu> xiaoQus2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								xiaoQus2.add(xiaoQus.get(i));
							}
							mv.addObject("xiaoqu", xiaoQus2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								xiaoQus2.add(xiaoQus.get(i));
							}
							mv.addObject("xiaoqu", xiaoQus2);
						}
					} else if (page == pages) {
						List<XiaoQu> xiaoQus2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							xiaoQus2.add(xiaoQus.get(i));
						}
						mv.addObject("xiaoqu", xiaoQus2);
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
			mv.setViewName("guanliyuan/xiaoquliebiao");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "modifyxiaoqu") // 修改校区名称
	public ModelAndView modifyxiaoqu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			String xiaoQuId = request.getParameter("id");
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(Integer.parseInt(xiaoQuId));
			mv.addObject("xiaoqu1", xiaoQu);
			mv.setViewName("guanliyuan/modifyxiaoqu");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savemodifyxiaoqu") // 保存修改
	@ResponseBody
	public String savemodifyxiaoqu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 检查session是否为空，如果不空保存修改的信息
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out=response.getWriter();
			String xiaoQuId = request.getParameter("id");
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(Integer.parseInt(xiaoQuId));
			String xiaoQuMingCheng = request.getParameter("xiaoqumingcheng");
			List<XiaoQu> xiaoQus = xiaoQuService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));
			String zhuangTai = request.getParameter("xiaoquzhuangtai");
			for (XiaoQu xiaoqu : xiaoQus) {
				if (xiaoQuMingCheng.equals(xiaoqu.getMingcheng())) {
					if (!xiaoQuMingCheng.equals(xiaoQu.getMingcheng())) {// 校区名称可以不修改，只有和其他校区名称相同时才算重名
						// out.print("<script>alert('与其他校区重名!');</script>");
						// out.print("<script>location='xiaoquliebiao';</script>");
						return "chongming";
					}
				}
			}
			xiaoQu.setMingcheng(xiaoQuMingCheng);
			xiaoQu.setXiaoquid(Integer.parseInt(xiaoQuId));
			xiaoQu.setZhuangtai(Integer.parseInt(zhuangTai));
			xiaoQu.setXuexiaoid((Integer) session.getAttribute("xuexiaoid"));
			int j = xiaoQuService.updateByPrimaryKey(xiaoQu);
			if (j != 0) {
				// out.print("<script>alert('修改成功!');</script>");
				return "success";
			} else {
				// out.print("<script>alert('修改失败!');</script>");
			}
			// out.print("<script>location='xiaoquliebiao';</script>");
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "deletexiaoqu") // 删除校区
	@ResponseBody
	public String deletexiaoqu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out = response.getWriter();
			String xiaoQuId = request.getParameter("id");
			List<JiaoXueLou> jiaoXueLous = jiaoXueLouService.selectByXiaoQuId(Integer.parseInt(xiaoQuId));
			if (!Util.isNumeric(xiaoQuId)) {// 检查xiaoquid是否是数字
				response.sendRedirect("logout");
				return null;
			}
			if (jiaoXueLous.size() == 0) {
				int i = xiaoQuService.deleteByPrimaryKey(Integer.parseInt(xiaoQuId));
				if (i != 0) {
					// out.print("<script>alert('删除成功!');</script>");
					return "success";
				} else {
					// out.print("<script>alert('删除失败!');</script>");
				}
			} else {
				// out.println("<script>alert('请先删除该校区下的所有教学楼!');</script>");
				return "jiaoxuelou";
			}
			// out.print("<script>location='xiaoquliebiao';</script>");
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chakanjiaoxuelou") // 查看教学楼
	public ModelAndView chakanjiaoxuelou(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			String xiaoQuId = request.getParameter("xiaoquid");
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(Integer.parseInt(xiaoQuId));
			session.setAttribute("xiaoquid", xiaoQuId);
			List<JiaoXueLou> jiaoXueLous = jiaoXueLouService.selectByXiaoQuId(Integer.parseInt(xiaoQuId));
			int count = jiaoXueLous.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<JiaoXueLou> jiaoXueLous2 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						jiaoXueLous2.add(jiaoXueLous.get(i));
					}
					mv.addObject("jiaoxuelou", jiaoXueLous2);
				} else {
					for (int i = 0; i < 10; i++) {
						jiaoXueLous2.add(jiaoXueLous.get(i));
					}
					mv.addObject("jiaoxuelou", jiaoXueLous2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<JiaoXueLou> jiaoXueLous2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								jiaoXueLous2.add(jiaoXueLous.get(i));
							}
							mv.addObject("jiaoxuelou", jiaoXueLous2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								jiaoXueLous2.add(jiaoXueLous.get(i));
							}
							mv.addObject("jiaoxuelou", jiaoXueLous2);
						}
					} else if (page == pages) {
						List<JiaoXueLou> jiaoXueLous2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							jiaoXueLous2.add(jiaoXueLous.get(i));
						}
						mv.addObject("jiaoxuelou", jiaoXueLous2);
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
			mv.addObject("xiaoqu", xiaoQu);
			mv.setViewName("guanliyuan/jiaoxuelouliebiao");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addjiaoxuelou") // 添加教学楼
	public ModelAndView addjiaoxuelou(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			XiaoQu xiaoQu = xiaoQuService
					.selectByPrimaryKey(Integer.parseInt((String) session.getAttribute("xiaoquid")));
			// 检查校区是否启用
			if (xiaoQu.getZhuangtai() == 0) {
				out.print("<script>alert('该校区已停用！请先启用该校区！');</script>");
				out.println("<script>location='index'</script>");
				return null;
			}
			if (xiaoQu.getZhuangtai() == 1) {
				List<XiaoQu> xiaoQus = xiaoQuService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));
				mv.addObject("xiaoqu2", xiaoQu);
				mv.addObject("xiaoqu", xiaoQus);
				mv.setViewName("guanliyuan/addjiaoxuelou");
				return mv;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savejiaoxuelou") // 保存添加
	@ResponseBody
	public String savejiaoxuelou(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out=response.getWriter();
			String jiaoXueLouMingCheng = request.getParameter("jiaoxueloumingcheng");
			String xiaoQuId = request.getParameter("xiaoquid");
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(Integer.parseInt(xiaoQuId));
			// 检查所选校区是否启用
			if (xiaoQu.getZhuangtai() == 0) {
				// out.print("<script>alert('该校区已停用！请先启用该校区！');</script>");
				// out.println("<script>location='xiaoquliebiao'</script>");
				return "qiyong_";
			}

			List<JiaoXueLou> jiaoXueLous = jiaoXueLouService.selectByXiaoQuId(Integer.parseInt(xiaoQuId));
			// 教学楼名称查重
			for (int i = 0; i < jiaoXueLous.size(); i++) {
				if (jiaoXueLouMingCheng.equals(jiaoXueLous.get(i).getJiaoXueLouMing().toString())) {
					// out.print("<script>alert('与其他教学楼重名!');</script>");
					// out.print("<script>location='addjiaoxuelou';</script>");
					return "chongming_";
				}
			}
			JiaoXueLou jiaoXueLou = new JiaoXueLou();
			jiaoXueLou.setXiaoQuId(Integer.parseInt(xiaoQuId));
			jiaoXueLou.setJiaoXueLouMing(jiaoXueLouMingCheng);
			int j = jiaoXueLouService.insert(jiaoXueLou);
			if (j != 0) {
				session.setAttribute("xiaoquid", xiaoQuId);
				// response.sendRedirect("chakanjiaoxuelou?xiaoquid="+xiaoQuId);
				return "success_" + xiaoQuId;
			} else {
				// response.sendRedirect("chakanjiaoxuelou?xiaoquid="+session.getAttribute("xiaoquid"));
			}
		} else {
			response.sendRedirect("login");
		}
		return null;

	}

	@RequestMapping(value = "modifyjiaoxuelou") // 修改教学楼
	public ModelAndView modifyjiaoxuelou(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			String jiaoXueLouId = request.getParameter("id");
			JiaoXueLou jiaoXueLou = jiaoXueLouService.selectByPrimaryKey(Integer.parseInt(jiaoXueLouId));
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(jiaoXueLou.getXiaoQuId());
			List<XiaoQu> xiaoQus = xiaoQuService.getAllByxueXiaoID((Integer) (session.getAttribute("xuexiaoid")));
			mv.addObject("xiaoqu", xiaoQus);
			mv.addObject("xiaoqu3", xiaoQu);
			mv.addObject("jiaoxuelou", jiaoXueLou);
			mv.setViewName("guanliyuan/modifyjiaoxuelou");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savemodifyjiaoxuelou") // 保存修改
	@ResponseBody
	public String savemodifyjiaoxuelou(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out=response.getWriter();
			String jiaoXueLouId = request.getParameter("id");
			JiaoXueLou jiaoXueLou = jiaoXueLouService.selectByPrimaryKey(Integer.parseInt(jiaoXueLouId));
			String jiaoXueLouMingCheng = request.getParameter("jiaoxueloumingcheng");
			String xiaoQuId = request.getParameter("xiaoquid");
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(Integer.parseInt(xiaoQuId));
			List<JiaoXueLou> jiaoXueLous = jiaoXueLouService.selectByXiaoQuId(Integer.parseInt(xiaoQuId));
			// 检查校区是否启用
			if (xiaoQu.getZhuangtai() == 0) {
				// out.print("<script>alert('该校区已停用！请先启用该校区！');</script>");
				// out.println("<script>location='xiaoquliebiao'</script>");
				return "qiyong_";
			}
			// 教学楼名称查重
			for (JiaoXueLou jiaoxuelou : jiaoXueLous) {
				if (jiaoXueLouMingCheng.equals(jiaoxuelou.getJiaoXueLouMing())
						&& !jiaoXueLouMingCheng.equals(jiaoXueLou.getJiaoXueLouMing())) {
					// out.print("<script>alert('该教学楼已存在!');</script>");
					// out.println("<script>location='xiaoquliebiao';</script>");
					return "chongming_";
				}
			}
			jiaoXueLou.setJiaoXueLouId(Integer.parseInt(jiaoXueLouId));
			jiaoXueLou.setJiaoXueLouMing(jiaoXueLouMingCheng);
			jiaoXueLou.setXiaoQuId(Integer.parseInt(xiaoQuId));

			int j = jiaoXueLouService.updateByPrimaryKey(jiaoXueLou);
			if (j != 0) {
				session.setAttribute("xiaoquid", xiaoQuId);
				// response.sendRedirect("chakanjiaoxuelou?xiaoquid="+xiaoQuId);
				return "success_" + xiaoQuId;
			} else {
				// response.sendRedirect("chakanjiaoxuelou?xiaoquid="+session.getAttribute("xiaoquid"));
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "deletejiaoxuelou") // 删除教学楼
	@ResponseBody
	public String deletejiaoxuelou(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out = response.getWriter();
			String jiaoXueLouId = request.getParameter("id");
			List<JiaoShi> jiaoShis = jiaoShiService.getAllByPrimaryKey(Integer.parseInt(jiaoXueLouId));
			if (!Util.isNumeric(jiaoXueLouId)) {
				response.sendRedirect("logout");
				return null;
			}
			if (jiaoShis.size() == 0) {
				int i = jiaoXueLouService.deleteByPrimaryKey(Integer.parseInt(jiaoXueLouId));
				if (i != 0) {
					// out.print("<script>alert('删除成功!');</script>");
					return "success";

				} else {
					// out.print("<script>alert('删除失败!');</script>");
				}
				// response.sendRedirect("chakanjiaoxuelou?xiaoquid="+session.getAttribute("xiaoquid"));
			} else {
				// out.println("<script>alert('请先删除该教学楼下的所有教室!');</script>");
				// out.println("<script>location='xiaoquliebiao';</script>");
				return "jiaoshi";
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chakanjiaoshi") // 查看教室
	public ModelAndView chakanjiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			String jiaoXueLouId = request.getParameter("jiaoxuelouid");
			List<JiaoShi> jiaoShis = jiaoShiService.getAllByPrimaryKey(Integer.parseInt(jiaoXueLouId));
			JiaoXueLou jiaoXueLou = jiaoXueLouService.selectByPrimaryKey(Integer.parseInt(jiaoXueLouId));
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(jiaoXueLou.getXiaoQuId());
			session.setAttribute("jiaoxuelouid", jiaoXueLouId);
			int count = jiaoShis.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<JiaoShi> jiaoShis2 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						jiaoShis2.add(jiaoShis.get(i));
					}
					mv.addObject("jiaoshi", jiaoShis2);
				} else {
					for (int i = 0; i < 10; i++) {
						jiaoShis2.add(jiaoShis.get(i));
					}
					mv.addObject("jiaoshi", jiaoShis2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<JiaoShi> jiaoShis2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								jiaoShis2.add(jiaoShis.get(i));
							}
							mv.addObject("jiaoshi", jiaoShis2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								jiaoShis2.add(jiaoShis.get(i));
							}
							mv.addObject("jiaoshi", jiaoShis2);
						}
					} else if (page == pages) {
						List<JiaoShi> jiaoShis2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							jiaoShis2.add(jiaoShis.get(i));
						}
						mv.addObject("jiaoshi", jiaoShis2);
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
			mv.addObject("jiaoxuelou", jiaoXueLou);
			mv.addObject("xiaoqu", xiaoQu);
			mv.setViewName("guanliyuan/jiaoshiliebiao");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addjiaoshi") // 添加教室
	public ModelAndView addjiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();
			JiaoXueLou jiaoXueLou = jiaoXueLouService
					.selectByPrimaryKey(Integer.parseInt((String) session.getAttribute("jiaoxuelouid")));
			XiaoQu xiaoQu = xiaoQuService
					.selectByPrimaryKey(Integer.parseInt((String) session.getAttribute("xiaoquid")));
			List<XiaoQu> xiaoQus = xiaoQuService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));
			List<JiaoXueLou> jiaoXueLous = jiaoXueLouService
					.selectByXiaoQuId(Integer.parseInt((String) session.getAttribute("xiaoquid")));
			if (xiaoQu.getZhuangtai() == 0) {
				out.print("<script>alert('该校区已停用！请先启用该校区！');</script>");
				out.println("<script>location='xiaoquliebiao'</script>");
				return null;
			}
			mv.addObject("jiaoxuelou", jiaoXueLous);
			mv.addObject("jiaoxuelou1", jiaoXueLou);
			mv.addObject("xiaoqu4", xiaoQu);
			mv.addObject("xiaoqu", xiaoQus);
			mv.setViewName("guanliyuan/addjiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savejiaoshi") // 保存添加
	@ResponseBody
	public String savejiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			int y = 0;
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// PrintWriter out=response.getWriter();
			String qianZhui = request.getParameter("qianzhui");
			String houZhui = request.getParameter("houzhui");
			String[] louCeng = request.getParameterValues("louceng");
			Integer shuLiang = Integer.parseInt(request.getParameter("shuliang"));
			String xiaoQuId = request.getParameter("xiaoquid");
			String jiaoXueLouId = request.getParameter("jiaoxuelouid");
			String jiaoShiMing = null;
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(Integer.parseInt(xiaoQuId));
			List<JiaoShi> jiaoShis = jiaoShiService.getAllByPrimaryKey(Integer.parseInt(jiaoXueLouId));
			List<String> jiaoShiMingList = new ArrayList<>();
			if (xiaoQu.getZhuangtai() == 0) {
				// out.print("<script>alert('该校区已停用！请先启用该校区！');</script>");
				// out.println("<script>location='xiaoquliebiao'</script>");
				return "qiyong_";
			}
			// 获取所有教室名称
			for (int i = 0; i < jiaoShis.size(); i++) {
				jiaoShiMingList.add(jiaoShis.get(i).getJiaoshiming());
			}
			// 批量添加教室
			for (int m = 0; m < louCeng.length; m++) {
				// 数量小于10时，批量添加教室
				if (shuLiang < 10) {
					for (int k = 1; k <= shuLiang; k++) {
						jiaoShiMing = qianZhui + louCeng[m] + 0 + k + houZhui;
						JiaoShi jiaoShi = jiaoShiService
								.selectByJiaoXueLouIdAndJiaoShiMing(Integer.parseInt(jiaoXueLouId), jiaoShiMing);
						if (jiaoShi != null && !"".equals(jiaoShi)) {// 教室查重
							continue;
						}
						JiaoShi jiaoShi2 = new JiaoShi();
						jiaoShi2.setJiaoshiming(jiaoShiMing);
						jiaoShi2.setJiaoxuelouid(Integer.parseInt(jiaoXueLouId));
						// jiaoShi2.setXiaoquid(Integer.parseInt(xiaoQuId));
						int j = jiaoShiService.insert(jiaoShi2);
						if (j != 0) {
							session.setAttribute("jiaoxuelouid", jiaoXueLouId);
							y = 1;
						} else {
							// out.print("<script>alert('添加失败!');</script>");
//							continue;
							return null;
						}
					}
				} else if (shuLiang >= 100) {
					// out.print("<script>alert('请输入1到100之间的整数!');</script>");
					// out.print("<script>location='addjiaoshi';</script>");
					return "zhengshu_";
				}
				// 数量介于10到100之间，批量添加教室
				else {
					for (int k = 1; k < 10; k++) {// k小于10的部分
						jiaoShiMing = qianZhui + louCeng[m] + 0 + k + houZhui;
						JiaoShi jiaoShi = jiaoShiService
								.selectByJiaoXueLouIdAndJiaoShiMing(Integer.parseInt(jiaoXueLouId), jiaoShiMing);
						if (jiaoShi != null && !"".equals(jiaoShi)) {// 教室查重
							continue;
						}
						JiaoShi jiaoShi2 = new JiaoShi();
						jiaoShi2.setJiaoshiming(jiaoShiMing);
						jiaoShi2.setJiaoxuelouid(Integer.parseInt(jiaoXueLouId));
						// jiaoShi2.setXiaoquid(Integer.parseInt(xiaoQuId));
						int j = jiaoShiService.insert(jiaoShi2);
						if (j != 0) {
							session.setAttribute("jiaoxuelouid", jiaoXueLouId);
							y = 1;
						} else {
							// out.print("<script>alert('添加失败!');</script>");
//							continue;
							return null;
						}
					}
					for (int k = 10; k <= shuLiang; k++) {// k不小于10的部分
						jiaoShiMing = qianZhui + louCeng[m] + k + houZhui;
						JiaoShi jiaoShi = jiaoShiService
								.selectByJiaoXueLouIdAndJiaoShiMing(Integer.parseInt(jiaoXueLouId), jiaoShiMing);
						if (jiaoShi != null && !"".equals(jiaoShi)) {// 教室查重
							continue;
						}
						JiaoShi jiaoShi2 = new JiaoShi();
						jiaoShi2.setJiaoshiming(jiaoShiMing);
						jiaoShi2.setJiaoxuelouid(Integer.parseInt(jiaoXueLouId));
						// jiaoShi2.setXiaoquid(Integer.parseInt(xiaoQuId));
						int j = jiaoShiService.insert(jiaoShi2);
						if (j != 0) {
							session.setAttribute("jiaoxuelouid", jiaoXueLouId);
							y = 1;
						} else {
							// out.print("<script>alert('添加失败!');</script>");
//							continue;
							return null;
						}
					}
				}
			}
			// response.sendRedirect("chakanjiaoshi?jiaoxuelouid="+jiaoXueLouId);
			if(y == 1){
				return "success_"+jiaoXueLouId;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	// 校区改变时教学楼也改变，添加教室时使用
	@RequestMapping(value = "getJiaoXueLou")
	@ResponseBody
	public Object getJiaoXueLou(HttpServletRequest request, HttpServletResponse response) {

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
		HttpSession session = request.getSession();
		YongHu user = (YongHu) session.getAttribute("user");
		XueShengChuGuanLiYuan xueShengChuGuanLiYuan = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid());
		if (xueShengChuGuanLiYuan == null) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {

				System.out.println("额...程序好像出错了!");
			}
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
		List<JiaoXueLou> jiaoxuelou = jiaoXueLouService.selectByXiaoQuId(Integer.parseInt(xiaoQuId));
		return JSON.toJSON(jiaoxuelou);
	}

	@RequestMapping(value = "modifyjiaoshi") // 修改教室
	public ModelAndView modifyjiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			String jiaoShiId = request.getParameter("id");
			JiaoShi jiaoShi = jiaoShiService.selectByPrimaryKey(Integer.parseInt(jiaoShiId));
			JiaoXueLou jiaoXueLou = jiaoXueLouService.selectByPrimaryKey(jiaoShi.getJiaoxuelouid());
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(jiaoXueLou.getXiaoQuId());
			List<XiaoQu> xiaoQus = xiaoQuService.getAllByxueXiaoID((Integer) (session.getAttribute("xuexiaoid")));
			List<JiaoXueLou> jiaoXueLous = jiaoXueLouService.selectByXiaoQuId(jiaoXueLou.getXiaoQuId());
			mv.addObject("jiaoshi", jiaoShi);
			mv.addObject("xiaoqu5", xiaoQu);
			mv.addObject("jiaoxuelou1", jiaoXueLou);
			mv.addObject("xiaoqu", xiaoQus);
			mv.addObject("jiaoxuelou", jiaoXueLous);
			mv.setViewName("guanliyuan/modifyjiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savemodifyjiaoshi") // 保存修改
	@ResponseBody
	public String savemodifyjiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String jiaoShiId = request.getParameter("id");
			String jiaoXueLouId = request.getParameter("jiaoxuelouid");
			String xiaoQuId = request.getParameter("xiaoquid");
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(Integer.parseInt(xiaoQuId));
			String jiaoShiMingCheng = request.getParameter("jiaoshimingcheng");
			JiaoShi jiaoShi = jiaoShiService.selectByPrimaryKey(Integer.parseInt(jiaoShiId));
			if (xiaoQu.getZhuangtai() == 0) {
//				out.print("<script>alert('该校区已停用！请先启用该校区！');</script>");
//				out.println("<script>location='xiaoquliebiao'</script>");
				return "qiyong_";
			}

			List<JiaoShi> jiaoShis = jiaoShiService.getAllByPrimaryKey(Integer.parseInt(jiaoXueLouId));
			for (JiaoShi jiaoshi : jiaoShis) {
				if (jiaoShiMingCheng.equals(jiaoshi.getJiaoshiming())
						&& !jiaoShiMingCheng.equals(jiaoShi.getJiaoshiming())) {
//					out.print("<script>alert('该教室已经存在!');</script>");
//					out.println("<script>location='xiaoquliebiao'</script>");
					return "chongming_";
				}
			}
			jiaoShi.setJiaoshiming(jiaoShiMingCheng);
			jiaoShi.setJiaoxuelouid(Integer.parseInt(jiaoXueLouId));
			int j = jiaoShiService.updateByPrimaryKey(jiaoShi);
			if (j != 0) {
				session.setAttribute("xiaoquid", xiaoQuId);
				session.setAttribute("jiaoxuelouid", jiaoXueLouId);
//				response.sendRedirect("chakanjiaoshi?jiaoxuelouid=" + jiaoXueLouId);
				return "success_"+jiaoXueLouId;
			} else {
//				response.sendRedirect("chakanjiaoshi?jiaoxuelouid=" + session.getAttribute("jiaoxuelouid"));
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "deletejiaoshi") // 删除教室
	@ResponseBody
	public String deletejiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String jiaoShiId = request.getParameter("id");
			if (Util.isNumeric(jiaoShiId)) {
				int j = jiaoShiService.deleteByPrimaryKey(Integer.parseInt(jiaoShiId));
				if (j != 0) {
//					out.print("<script>alert('删除成功!');</script>");
					return "success";
				} else {
//					out.print("<script>alert('删除失败!');</script>");
				}
//				response.sendRedirect("chakanjiaoshi?jiaoxuelouid=" + session.getAttribute("jiaoxuelouid"));
			} else {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "sushelouliebiao") // 宿舍楼列表
	public ModelAndView sushelouliebiao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			List<XiaoQu> xiaoQus = xiaoQuService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));
			String xiaoQuId = null;
			// 判断是学校校区是否为空
			if (xiaoQus.size() == 0) {
				out.print("<script>alert('校区为空，请先添加校区!');</script>");
				out.print("<script>location='index';</script>");
				return null;
			}else if(request.getParameter("xiaoquid") != null && !"".equals(request.getParameter("xiaoquid"))){
				xiaoQuId = request.getParameter("xiaoquid");
			}
			// 取校区列表中第一个校区的xiaoquid
			else if (xiaoQuId == null && (String) session.getAttribute("xiaoquid") == null) {
				xiaoQuId = xiaoQus.get(0).getXiaoquid().toString();
				session.setAttribute("xiaoquid", xiaoQuId);
			}
			// 取session中的xiaoquid
			else if (xiaoQuId == null && session.getAttribute("xiaoquid").toString() != null) {
				xiaoQuId = (String) session.getAttribute("xiaoquid");

			}
			// 取前台传来xiaoquid
			else {
				xiaoQuId = request.getParameter("xiaoquid");
			}
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(Integer.parseInt(xiaoQuId));
			List<SuSheLou> suSheLous = suSheLouService.getAllByXiaoQuId(Integer.parseInt(xiaoQuId));
			int count = suSheLous.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<SuSheLou> suSheLous2 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						suSheLous2.add(suSheLous.get(i));
					}
					mv.addObject("sushelou", suSheLous2);
				} else {
					for (int i = 0; i < 10; i++) {
						suSheLous2.add(suSheLous.get(i));
					}
					mv.addObject("sushelou", suSheLous2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<SuSheLou> suSheLous2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								suSheLous2.add(suSheLous.get(i));
							}
							mv.addObject("sushelou", suSheLous2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								suSheLous2.add(suSheLous.get(i));
							}
							mv.addObject("sushelou", suSheLous2);
						}
					} else if (page == pages) {
						List<SuSheLou> suSheLous2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							suSheLous2.add(suSheLous.get(i));
						}
						mv.addObject("sushelou", suSheLous2);
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
			mv.addObject("xiaoqu1", xiaoQu);
			mv.addObject("xiaoqu", xiaoQus);
			mv.addObject("sushelou", suSheLous);
			mv.setViewName("guanliyuan/sushelouliebiao");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chaxunsushelou") // 查询宿舍楼
	public ModelAndView chaxunsushelou(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			List<XiaoQu> xiaoQus = xiaoQuService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));
			String xiaoQuId = request.getParameter("xiaoquid");
			session.setAttribute("xiaoquid", xiaoQuId);
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(Integer.parseInt(xiaoQuId));
			List<SuSheLou> suSheLous = suSheLouService.getAllByXiaoQuId(Integer.parseInt(xiaoQuId));
			int count = suSheLous.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<SuSheLou> suSheLous2 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						suSheLous2.add(suSheLous.get(i));
					}
					mv.addObject("sushelou", suSheLous2);
				} else {
					for (int i = 0; i < 10; i++) {
						suSheLous2.add(suSheLous.get(i));
					}
					mv.addObject("sushelou", suSheLous2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<SuSheLou> suSheLous2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								suSheLous2.add(suSheLous.get(i));
							}
							mv.addObject("sushelou", suSheLous2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								suSheLous2.add(suSheLous.get(i));
							}
							mv.addObject("sushelou", suSheLous2);
						}
					} else if (page == pages) {
						List<SuSheLou> suSheLous2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							suSheLous2.add(suSheLous.get(i));
						}
						mv.addObject("sushelou", suSheLous2);
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
			mv.addObject("xiaoqu1", xiaoQu);
			mv.addObject("xiaoqu", xiaoQus);
			mv.addObject("sushelou", suSheLous);
			mv.setViewName("guanliyuan/sushelouliebiao");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addsushelou") // 添加宿舍楼
	public ModelAndView addsushelou(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();
			List<XiaoQu> xiaoQus = xiaoQuService.getAllByxueXiaoID((Integer) session.getAttribute("xuexiaoid"));
			XiaoQu xiaoQu = xiaoQuService
					.selectByPrimaryKey(Integer.parseInt((String) session.getAttribute("xiaoquid")));
			if (xiaoQu.getZhuangtai() == 0) {
				out.print("<script>alert('该校区已停用,如需添加请先启用该校区!');</script>");
				out.print("<script>location='index';</script>");
			}
			mv.addObject("xiaoqu1", xiaoQu);
			mv.addObject("xiaoqu", xiaoQus);
			mv.setViewName("guanliyuan/addsushelou");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savesushelou") // 保存添加
	@ResponseBody
	public String savesushelou(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
//			PrintWriter out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			String xiaoQuId = request.getParameter("xiaoquid");
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(Integer.parseInt(xiaoQuId));
			if (xiaoQu.getZhuangtai() == 0) {
//				out.print("<script>alert('该校区已停用,如需添加请先启用该校区!');</script>");
//				out.print("<script>location='xiaoquliebiao';</script>");
				return "qiyong";
			}
			List<SuSheLou> suSheLous = suSheLouService.getAllByXiaoQuId(Integer.parseInt(xiaoQuId));
			String suSheLouMingCheng = request.getParameter("susheloumingcheng");
			List<String> suSheLouMingList = new ArrayList<>();
			for (int i = 0; i < suSheLous.size(); i++) {
				suSheLouMingList.add(suSheLous.get(i).getMingCheng());
			}
			// 宿舍楼名称查重
			if (suSheLouMingList.contains(suSheLouMingCheng)) {
//				out.print("<script>alert('该宿舍楼与其他宿舍楼重名!');</script>");
//				out.print("<script>location='sushelouliebiao';</script>");
				return "chongming";
			}
			SuSheLou suSheLou = new SuSheLou();
			suSheLou.setMingCheng(suSheLouMingCheng);
			suSheLou.setXiaoQuId(Integer.parseInt(xiaoQuId));
			suSheLou.setZhuangTai(1);
			int j = suSheLouService.insert(suSheLou);
			if (j != 0) {
//				response.sendRedirect("chaxunsushelou?xiaoquid=" + xiaoQuId);
				return "success";
			} else {
//				out.print("<script>alert('添加失败!');</script>");
//				out.print("<script>location='addsushelou';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;

	}

	@RequestMapping(value = "modifysushelou") // 修改宿舍楼
	public ModelAndView modifysushelou(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			String suSheLouId = request.getParameter("id");
			SuSheLou suSheLou = suSheLouService.selectByPrimaryKey(Integer.parseInt(suSheLouId));
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(suSheLou.getXiaoQuId());
			List<XiaoQu> xiaoQus = xiaoQuService.getAllByxueXiaoID((Integer) (session.getAttribute("xuexiaoid")));
			mv.addObject("xiaoqu1", xiaoQu);
			mv.addObject("sushelou", suSheLou);
			mv.addObject("xiaoqu", xiaoQus);
			mv.setViewName("guanliyuan/modifysushelou");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savemodifysushelou") // 保存修改
	@ResponseBody
	public String savemodifysushelou(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String suSheLouId = request.getParameter("id");
			SuSheLou suSheLou = suSheLouService.selectByPrimaryKey(Integer.parseInt(suSheLouId));
			String suSheLouMingCheng = request.getParameter("susheloumingcheng");
			String xiaoQuId = request.getParameter("xiaoquid");
			String zhuangTai = request.getParameter("sushelouzhuangtai");
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(Integer.parseInt(xiaoQuId));
			List<SuSheLou> suSheLous = suSheLouService.getAllByXiaoQuId(Integer.parseInt(xiaoQuId));
			if (xiaoQu.getZhuangtai() == 0) {
//				out.print("<script>alert('该校区已停用！请先启用该校区！');</script>");
//				out.println("<script>location='xiaoquliebiao'</script>");
				return "qiyong_";
			}
			// 宿舍楼名称查重
			if (suSheLou.getXiaoQuId().equals(xiaoQuId)) {
				for (SuSheLou sushelou : suSheLous) {
					if (suSheLouMingCheng.equals(sushelou.getMingCheng())
							&& !suSheLouMingCheng.equals(suSheLou.getMingCheng())) {
//						out.print("<script>alert('该宿舍楼已存在!');</script>");
//						out.print("<script>location='sushelouliebiao';</script>");
						return "chongming_";
					}
				}
			} else {
				for (SuSheLou sushelou : suSheLous) {
					if (suSheLouMingCheng.equals(sushelou.getMingCheng())) {
//						out.print("<script>alert('该宿舍楼已存在!');</script>");
//						out.print("<script>location='sushelouliebiao';</script>");
						return "chongming_";
					}
				}
			}

			suSheLou.setMingCheng(suSheLouMingCheng);
			suSheLou.setZhuangTai(Integer.parseInt(zhuangTai));
			suSheLou.setXiaoQuId(Integer.parseInt(xiaoQuId));
			int j = suSheLouService.updateByPrimaryKey(suSheLou);

			if (j != 0) {
				session.setAttribute("xiaoquid", xiaoQuId);
//				response.sendRedirect("chaxunsushelou?xiaoquid=" + xiaoQuId);
				return "success_"+xiaoQuId;
			} else {
//				response.sendRedirect("chaxunsushelou?xiaoquid=" + suSheLou.getXiaoQuId());
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "deletesushelou") // 删除宿舍楼
	@ResponseBody
	public String deletesushelou(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String suSheLouId = request.getParameter("id");
			SuSheLou suSheLou = suSheLouService.selectByPrimaryKey(Integer.parseInt(suSheLouId));
			List<XueShengSuShe> xueShengSuShes = xueShengSuSheService.getAllBySuSheLouId(Integer.parseInt(suSheLouId));
			if (!Util.isNumeric(suSheLouId)) {
				response.sendRedirect("logout");
				return null;
			}
			// 宿舍楼下宿舍为空，直接删除宿舍楼
			if (xueShengSuShes.size() == 0) {
				int i = suSheLouService.deleteByPrimaryKey(Integer.parseInt(suSheLouId));
				if (i != 0) {
//					out.print("<script>alert('删除成功!');</script>");
					return "success_"+suSheLou.getXiaoQuId();

				} else {
//					out.print("<script>alert('删除失败!');</script>");
					return "fail_"+suSheLou.getXiaoQuId();
				}
//				response.sendRedirect("chaxunsushelou?xiaoquid=" + suSheLou.getXiaoQuId());
//				return null;
			}
			// 宿舍楼下宿舍不为空，不能直接删除宿舍楼
			else {
//				out.println("<script>alert('请先删除该宿舍楼下的所有寝室!');</script>");
//				response.sendRedirect("chakansushe?sushelouid=" + suSheLou.getSuSheLouId());
				return "sushe_"+suSheLou.getXiaoQuId();
			}

		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chakansushe") // 查看宿舍
	public ModelAndView chakansushe(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			String suSheLouId = request.getParameter("sushelouid");
			session.setAttribute("sushelouid", suSheLouId);
			List<XueShengSuShe> xueShengSuShes = xueShengSuSheService.getAllBySuSheLouId(Integer.parseInt(suSheLouId));
			SuSheLou suSheLou = suSheLouService.selectByPrimaryKey(Integer.parseInt(suSheLouId));
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(suSheLou.getXiaoQuId());
			int count = xueShengSuShes.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (count % pageSize == 0) {
				pages = count / pageSize;
			}
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<XueShengSuShe> xueShengSuShes2 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						xueShengSuShes2.add(xueShengSuShes.get(i));
					}
					mv.addObject("xueshengsushe", xueShengSuShes2);
				} else {
					for (int i = 0; i < 10; i++) {
						xueShengSuShes2.add(xueShengSuShes.get(i));
					}
					mv.addObject("xueshengsushe", xueShengSuShes2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<XueShengSuShe> xueShengSuShes2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								xueShengSuShes2.add(xueShengSuShes.get(i));
							}
							mv.addObject("xueshengsushe", xueShengSuShes2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								xueShengSuShes2.add(xueShengSuShes.get(i));
							}
							mv.addObject("xueshengsushe", xueShengSuShes2);
						}
					} else if (page == pages) {
						List<XueShengSuShe> xueShengSuShes2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							xueShengSuShes2.add(xueShengSuShes.get(i));
						}
						mv.addObject("xueshengsushe", xueShengSuShes2);
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
			mv.addObject("sushelou", suSheLou);
			mv.addObject("xiaoqu", xiaoQu);
			mv.setViewName("guanliyuan/susheliebiao");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addxueshengsushe") // 添加学生宿舍
	public ModelAndView addxueshengsushe(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();
			SuSheLou suSheLou = suSheLouService
					.selectByPrimaryKey(Integer.parseInt((String) session.getAttribute("sushelouid")));
			List<SuSheLou> suSheLous = suSheLouService.getAllByXiaoQuId(suSheLou.getXiaoQuId());
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(suSheLou.getXiaoQuId());
			if (suSheLou.getZhuangTai() == 0) {
				out.print("<script>alert('该宿舍楼已停用,如需添加请先启用该宿舍楼!');</script>");
				response.sendRedirect("index");
//				response.sendRedirect("chaxunsushelou?xiaoquid=" + suSheLou.getXiaoQuId());
				return null;
			}
			mv.addObject("xiaoqu", xiaoQu);
			mv.addObject("sushelou1", suSheLou);
			mv.addObject("sushelous", suSheLous);
			mv.setViewName("guanliyuan/addsushe");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savexueshengsushe") // 保存添加
	@ResponseBody
	public String savexueshengsushe(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String suSheLouId = request.getParameter("sushelouid");
			SuSheLou suSheLou = suSheLouService.selectByPrimaryKey(Integer.parseInt(suSheLouId));
			String menPaiHao = null;
			String qianZhui = request.getParameter("qianzhui");
			String houZhui = request.getParameter("houzhui");
			Integer shuLiang = Integer.parseInt(request.getParameter("shuliang"));
			String[] louCeng = request.getParameterValues("louceng");
			List<XueShengSuShe> xueShengSuShes = xueShengSuSheService.getAllBySuSheLouId(Integer.parseInt(suSheLouId));
			List<String> menPaiHaoList = new ArrayList<>();
			for (int i = 0; i < xueShengSuShes.size(); i++) {
				menPaiHaoList.add(xueShengSuShes.get(i).getMenPaiHao());
			}
			// 检查宿舍楼状态
			if (suSheLou.getZhuangTai() == 0) {
//				out.print("<script>alert('该宿舍楼已停用！请先启用该宿舍楼!');</script>");
//				out.println("<script>location='sushelouliebiao';</script>");
				return "qiyong_";
			}
			int y = 0;
			// 宿舍楼启用状态下，批量添加宿舍
			if (suSheLou.getZhuangTai() == 1) {
				// 多楼层，批量添加宿舍
				for (int m = 0; m < louCeng.length; m++) {
					// 数量小于10的部分
					if (shuLiang < 10) {
						for (int k = 1; k <= shuLiang; k++) {
							menPaiHao = qianZhui + louCeng[m] + 0 + k + houZhui;
							// 宿舍名称查重
							if (menPaiHaoList.contains(menPaiHao)) {
								continue;
							}
							XueShengSuShe xueShengSuShe = new XueShengSuShe();
							xueShengSuShe.setMenPaiHao(menPaiHao);
							xueShengSuShe.setSuSheLouId(Integer.parseInt(suSheLouId));
							xueShengSuShe.setZhuangTai(1);
							xueShengSuShe.setLouCeng(Integer.parseInt(louCeng[m]));
							int j = xueShengSuSheService.insert(xueShengSuShe);
							if (j != 0) {
								session.setAttribute("sushelouid", suSheLouId);
								y = 1;
							} else {
//								out.print("<script>alert('添加失败!');</script>");
								continue;
							}
						}

					} else if (shuLiang >= 100) {
//						out.print("<script>alert('请输入1到100之间的整数!');</script>");
//						out.print("<script>location='addxueshengsushe';</script>");
						return "zhengshu_";
					}
					// 数量介于10与100之间的部分
					else {
						// 数量小于10的部分
						for (int k = 1; k < 10; k++) {
							menPaiHao = qianZhui + louCeng[m] + 0 + k + houZhui;
							// 宿舍名称查重
							if (menPaiHaoList.contains(menPaiHao)) {
								continue;
							}
							XueShengSuShe xueShengSuShe = new XueShengSuShe();
							xueShengSuShe.setMenPaiHao(menPaiHao);
							xueShengSuShe.setSuSheLouId(Integer.parseInt(suSheLouId));
							xueShengSuShe.setZhuangTai(1);
							xueShengSuShe.setLouCeng(Integer.parseInt(louCeng[m]));
							int j = xueShengSuSheService.insert(xueShengSuShe);
							if (j != 0) {
								session.setAttribute("sushelouid", suSheLouId);
								y = 1;
							} else {
//								out.print("<script>alert('添加失败!');</script>");
								continue;
							}
						}
						// 数量大于等于10的部分
						for (int k = 10; k <= shuLiang; k++) {
							menPaiHao = qianZhui + louCeng[m] + k + houZhui;
							// 宿舍名称查重
							if (menPaiHaoList.contains(menPaiHao)) {
								continue;
							}
							XueShengSuShe xueShengSuShe = new XueShengSuShe();
							xueShengSuShe.setMenPaiHao(menPaiHao);
							xueShengSuShe.setSuSheLouId(Integer.parseInt(suSheLouId));
							xueShengSuShe.setZhuangTai(1);
							xueShengSuShe.setLouCeng(Integer.parseInt(louCeng[m]));
							int j = xueShengSuSheService.insert(xueShengSuShe);
							if (j != 0) {
								session.setAttribute("sushelouid", suSheLouId);
								y = 1;
							} else {
//								out.print("<script>alert('添加失败!');</script>");
								continue;
							}
						}
					}
				}
//				response.sendRedirect("chakansushe?sushelouid=" + suSheLouId);
				if(y == 1){
					return "success_"+suSheLouId;
				}else{
					return "fail_"+suSheLouId;
				}
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "modifyxueshengsushe") // 修改学生宿舍
	public ModelAndView modifyxueshengsushe(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			String suSheId = request.getParameter("id");
			XueShengSuShe xueShengSuShe = xueShengSuSheService.selectByPrimaryKey(Integer.parseInt(suSheId));
			SuSheLou suSheLou = suSheLouService.selectByPrimaryKey(xueShengSuShe.getSuSheLouId());
			List<SuSheLou> suSheLous = suSheLouService.getAllByXiaoQuId(suSheLou.getXiaoQuId());
			XiaoQu xiaoQu = xiaoQuService.selectByPrimaryKey(suSheLou.getXiaoQuId());
			mv.addObject("xiaoqu", xiaoQu);
			mv.addObject("sushelou2", suSheLou);
			mv.addObject("sushelous", suSheLous);
			mv.addObject("xueshengsushe", xueShengSuShe);
			mv.setViewName("guanliyuan/modifysushe");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savemodifyxueshengsushe") // 保存修改
	@ResponseBody
	public String savemodifyxueshengsushe(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String suSheId = request.getParameter("id");
			String suSheLouId = request.getParameter("sushelouid");
			XueShengSuShe xueShengSuShe = xueShengSuSheService.selectByPrimaryKey(Integer.parseInt(suSheId));
			SuSheLou suSheLou = suSheLouService.selectByPrimaryKey(Integer.parseInt(suSheLouId));
			if (suSheLou.getZhuangTai() == 0) {
//				out.print("<script>alert('该宿舍楼已停用,如需添加请先启用该宿舍楼!');</script>");
//				out.print("<script>location='sushelouliebiao';</script>");// 查看宿舍的时候session里存入xiaoquid
				return "qiyong_";
			}
			List<XueShengSuShe> xueShengSuShes = xueShengSuSheService.getAllBySuSheLouId(Integer.parseInt(suSheLouId));
			String menPaiHao = request.getParameter("menpaihao");
			// 宿舍名称查重
			for (int i = 0; i < xueShengSuShes.size(); i++) {
				if (menPaiHao.equals(xueShengSuShes.get(i).getMenPaiHao())
						&& !menPaiHao.equals(xueShengSuShe.getMenPaiHao())) {
//					out.print("<script>alert('与其他宿舍名称相同!');</script>");
//					out.println("<script>location='sushelouliebiao';</script>");
					return "chongming_";
				}
			}
			xueShengSuShe.setMenPaiHao(menPaiHao);
			xueShengSuShe.setSuSheLouId(Integer.parseInt(suSheLouId));
			xueShengSuShe.setZhuangTai(1);
			int j = xueShengSuSheService.updateByPrimaryKey(xueShengSuShe);
			if (j != 0) {
				session.setAttribute("sushelouid", suSheLouId);
//				response.sendRedirect("chakansushe?sushelouid=" + suSheLouId);
				return "success_"+suSheLouId;
			} else {
//				out.print("<script>alert('修改失败!');</script>");
//				response.sendRedirect("chakansushe?sushelouid=" + (String) session.getAttribute("sushelouid"));
				return null;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;

	}

	@RequestMapping(value = "deletexueshengsushe") // 删除学生宿舍
	@ResponseBody
	public String deletexueshengsushe(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String suSheId = request.getParameter("id");
			XueShengSuShe xueShengSuShe = xueShengSuSheService.selectByPrimaryKey(Integer.parseInt(suSheId));
			if (!Util.isNumeric(suSheId)) {
				response.sendRedirect("logout");
				return null;
			}

			int i = xueShengSuSheService.deleteByPrimaryKey(Integer.parseInt(suSheId));
			if (i != 0) {
//				out.print("<script>alert('删除成功!');</script>");
//				response.sendRedirect("chakansushe?sushelouid=" + xueShengSuShe.getSuSheLouId());
				return "success";
			} else {
//				out.print("<script>alert('删除失败!');</script>");
//				response.sendRedirect("chaxkansushe?sushelouid=" + (String) session.getAttribute("sushelouid"));
				return null;
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

}
