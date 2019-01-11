package com.web.controller.web.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.annotation.LoginStatusAnnotation;
import com.web.model.BanJi;
import com.web.model.NaXin;
import com.web.model.NaXinJiBenXinXi;
import com.web.model.SheTuanBuMenJiBenXinXin;
import com.web.model.SheTuanBuMenXinXin;
import com.web.model.SheTuanChuangJian;
import com.web.model.SheTuanJiBenXinXi;
import com.web.model.SheTuanJieSan;
import com.web.model.SheTuanJingFei;
import com.web.model.SheTuanRenYuanXinXi;
import com.web.model.SheTuanXinXi;
import com.web.model.TiXing;
import com.web.model.XueSheng;
import com.web.model.XueShengZuZhiJiBenXinXi;
import com.web.model.XueShengZuZhiXinXi;
import com.web.model.YongHu;
import com.web.service.BanJiService;
import com.web.service.SheTuanService;
import com.web.service.TiXingService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

@Controller
@LoginStatusAnnotation(status="xuesheng")
public class SheTuanController {
	static Logger logger = Logger.getLogger(SheTuanController.class);
	@Autowired
	private SheTuanService sheTuanService;
	@Autowired
	private XueShengService xueShengService;
	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private BanJiService banJiService;
	@Autowired
	private YuanXiService yuanXiService;
	@Autowired
	private TiXingService tiXingService;

	// 社团介绍
	@RequestMapping(value = "shetuanjieshao")
	public ModelAndView sheTuanJieShao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = sheTuanService
				.selectXueShengZuZhiJiBenXinXiByXueXiaoID(Integer.parseInt(xueXiaoXueHao[0]));
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = sheTuanService
				.selectSheTuanJiBenXinXiByXueXiaoID(Integer.parseInt(xueXiaoXueHao[0]));
		for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
			xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
					.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
			if (xueShengZuZhiJiBenXinXi.getZhidaojiaoshi()!=null) {
				xueShengZuZhiJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(xueShengZuZhiJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
			}
		}
		for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
			sheTuanJiBenXinXi.setChuanjianren(
					xueShengService.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
			if (sheTuanJiBenXinXi.getZhidaojiaoshi()!=null) {
				sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.addObject("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
		mView.addObject("shetuan", sheTuanJiBenXinXis);
		mView.setViewName("stu/shetuanjieshao");
		return mView;
	}

	@RequestMapping(value = "chaxunshetuan")
	public ModelAndView chaXunSheTuan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String xingJi = request.getParameter("xingji");
		logger.info(xingJi);
		String xingZhi = request.getParameter("xingzhi");
		logger.info(xingZhi);
		String mingCheng = request.getParameter("mingcheng");
		logger.info(mingCheng);
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = new ArrayList<>();
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = new ArrayList<>();
		if (xingZhi.equals("shetuan")) {
			Map<String, String> map = new HashMap<>();
			String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
			map.put("xuexiaoid", xueXiaoXueHao[0]);
			map.put("xingji", xingJi);
			map.put("mingcheng", mingCheng);
			sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
		}
		if (xingZhi.equals("xueshengzuzhi")) {
			Map<String, String> map = new HashMap<>();
			String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
			map.put("xuexiaoid", xueXiaoXueHao[0]);
			map.put("mingcheng", mingCheng);
			xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
			xingJi = "";
		}
		if (xingZhi.equals("")) {
			if (!mingCheng.equals("")) {
				Map<String, String> map = new HashMap<>();
				String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
				map.put("xuexiaoid", xueXiaoXueHao[0]);
				map.put("mingcheng", mingCheng);
				map.put("xingji", xingJi);
				sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
				xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
			} else if (!xingJi.equals("")) {
				Map<String, String> map = new HashMap<>();
				String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
				map.put("xuexiaoid", xueXiaoXueHao[0]);
				map.put("xingji", xingJi);
				map.put("mingcheng", mingCheng);
				sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
			} else {
				String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
				xueShengZuZhiJiBenXinXis = sheTuanService
						.selectXueShengZuZhiJiBenXinXiByXueXiaoID(Integer.parseInt(xueXiaoXueHao[0]));
				sheTuanJiBenXinXis = sheTuanService
						.selectSheTuanJiBenXinXiByXueXiaoID(Integer.parseInt(xueXiaoXueHao[0]));
			}
		}
		for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
			xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
					.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
			if (xueShengZuZhiJiBenXinXi.getZhidaojiaoshi()!=null) {
				xueShengZuZhiJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(xueShengZuZhiJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
			}
		}
		for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
			sheTuanJiBenXinXi.setChuanjianren(
					xueShengService.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
			if (sheTuanJiBenXinXi.getZhidaojiaoshi()!=null) {
				sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.addObject("xingji", xingJi);
		mView.addObject("xingzhi", xingZhi);
		mView.addObject("mingcheng", mingCheng);
		mView.addObject("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
		mView.addObject("shetuan", sheTuanJiBenXinXis);
		mView.setViewName("stu/shetuanjieshao");
		return mView;
	}

	@RequestMapping(value = "shetuanbaomingcx")
	public ModelAndView SheTuanbaoMingXC(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		StringBuffer xingJi = new StringBuffer();
		xingJi.append(request.getParameter("xingji"));
		logger.info(xingJi);
		String xingZhi = request.getParameter("xingzhi");
		logger.info(xingZhi);
		String mingCheng = request.getParameter("mingcheng");
		logger.info(mingCheng);
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = new ArrayList<>();
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = new ArrayList<>();
		if (xingZhi.equals("shetuan")) {
			Map<String, String> map = new HashMap<>();
			String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
			map.put("xuexiaoid", xueXiaoXueHao[0]);
			map.put("xingji", xingJi.toString());
			map.put("mingcheng", mingCheng);
			sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
		}
		if (xingZhi.equals("xueshengzuzhi")) {
			Map<String, String> map = new HashMap<>();
			String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
			map.put("xuexiaoid", xueXiaoXueHao[0]);
			map.put("mingcheng", mingCheng);
			xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
			xingJi.delete(0, xingJi.length());
		}
		if (xingZhi.equals("")) {
			if (!mingCheng.equals("")) {
				Map<String, String> map = new HashMap<>();
				String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
				map.put("xuexiaoid", xueXiaoXueHao[0]);
				map.put("xingji", xingJi.toString());
				map.put("mingcheng", mingCheng);
				sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
				xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
			} else if (!xingJi.toString().equals("")) {
				Map<String, String> map = new HashMap<>();
				String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
				map.put("xuexiaoid", xueXiaoXueHao[0]);
				map.put("xingji", xingJi.toString());
				map.put("mingcheng", mingCheng);
				sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
			} else {
				String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
				xueShengZuZhiJiBenXinXis = sheTuanService
						.selectXueShengZuZhiJiBenXinXiByXueXiaoID(Integer.parseInt(xueXiaoXueHao[0]));
				sheTuanJiBenXinXis = sheTuanService
						.selectSheTuanJiBenXinXiByXueXiaoID(Integer.parseInt(xueXiaoXueHao[0]));
			}
		}
		for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
			xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
					.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
			if (xueShengZuZhiJiBenXinXi.getZhidaojiaoshi()!=null) {
				xueShengZuZhiJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(xueShengZuZhiJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
			}
			XueShengZuZhiXinXi xShengZuZhiXinXi = sheTuanService.selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(
					xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(), format.format(date));
			if (xShengZuZhiXinXi != null) {
				NaXin naXin = sheTuanService.selectNaXinByXueShengIDAndXueShengZuZhiXinXiID(user.getXueshengid(),
						xShengZuZhiXinXi.getXueshengzuzhixinxiid());
				if (naXin != null) {
					if (naXin.getZhuangtai()==2) {
						xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(3);//已拒绝
					}
					if (naXin.getZhuangtai()==0) {
						xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(1);
					}
				} else {
					xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(0);
				}
				String renYuanIDs[] = xShengZuZhiXinXi.getRenyuanids().split(",");
				for (int i = 0; i < renYuanIDs.length; i++) {
					if (renYuanIDs[i].equals(user.getXueshengid().toString())) {
						xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(2);
					}
				}
				NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService
						.selectNaXinJiBenXinXiByXueShengZuZhiXinXiID(xShengZuZhiXinXi.getXueshengzuzhixinxiid());
				if (naXinJiBenXinXi != null && naXinJiBenXinXi.getZhuangtai() == true) {
					xueShengZuZhiJiBenXinXi.setNaxin(1);
				} else {
					xueShengZuZhiJiBenXinXi.setNaxin(0);
				}
			} else {
				xueShengZuZhiJiBenXinXi.setNaxin(0);
			}

		}
		for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
			sheTuanJiBenXinXi.setChuanjianren(
					xueShengService.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
			if(sheTuanJiBenXinXi.getZhidaojiaoshi()!=null){
				sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
			}
			SheTuanXinXi xSheTuanXinXi = sheTuanService
					.selectSheTuanXinXiBySheTuanIDAndNianDu(sheTuanJiBenXinXi.getShetuanid(), format.format(date));
			if (xSheTuanXinXi != null) {
				NaXin naXin = sheTuanService.selectNaXinByXueShengIDAndSheTuanXinXiID(user.getXueshengid(),
						xSheTuanXinXi.getShetuanxinxiid());
				if (naXin != null) {
					if (naXin.getZhuangtai()==2) {
						sheTuanJiBenXinXi.setBaomingzhuangtai(3);//已拒绝
					}
					if (naXin.getZhuangtai()==0) {
						sheTuanJiBenXinXi.setBaomingzhuangtai(1);
					}
				} else {
					sheTuanJiBenXinXi.setBaomingzhuangtai(0);
				}
				String sheYuanIDs[] = xSheTuanXinXi.getSheyuanids().split(",");
				for (int i = 0; i < sheYuanIDs.length; i++) {
					logger.info(sheYuanIDs[i]);
					if (sheYuanIDs[i].equals(user.getXueshengid().toString())) {
						sheTuanJiBenXinXi.setBaomingzhuangtai(2);
					}
				}
				NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService
						.selectNaXinJiBenXinXiBySheTuanXinXiID(xSheTuanXinXi.getShetuanxinxiid());
				if (naXinJiBenXinXi != null && naXinJiBenXinXi.getZhuangtai() == true) {
					sheTuanJiBenXinXi.setNaxin(1);
				} else {
					sheTuanJiBenXinXi.setNaxin(0);
				}
			} else {
				sheTuanJiBenXinXi.setNaxin(0);
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.addObject("xingji", xingJi);
		mView.addObject("xingzhi", xingZhi);
		mView.addObject("mingcheng", mingCheng);
		mView.addObject("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
		mView.addObject("shetuan", sheTuanJiBenXinXis);
		mView.setViewName("stu/shetuanbaoming");
		return mView;

	}

	/**
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "xueshengzuzhi")
	public ModelAndView xueShengZuZhi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String xueshengzuzhiid = request.getParameter("id");
		if (!Util.isNumeric(xueshengzuzhiid)) {
			response.sendRedirect("logout");
			return null;
		}
		Map<String, String> map = new HashMap<>();
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
		map.put("xuexiaoid", xueXiaoXueHao[0]);
		map.put("xueshengzuzhiid", xueshengzuzhiid);
		XueShengZuZhiJiBenXinXi xi = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndXueShengZuZhiID(map);
		if (xi == null) {
			response.sendRedirect("logout");
			return null;
		}
		if (xi.getZhidaojiaoshi()!=null) {
			xi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(xi.getZhidaojiaoshi())).getYonghuxingming());
		}
		xi.setChuangjianren(xueShengService.getUserById(Integer.parseInt(xi.getChuangjianren())).getXingming());
		ModelAndView mView = new ModelAndView();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
				.selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(xi.getXueshengzuzhiid(), format.format(date));
		if (xueShengZuZhiXinXi == null) {
			mView.setViewName("stu/xueshengzuzhi");
			mView.addObject("jibenxinxi", xi);
			return mView;
		}
		List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xi.getXueshengzuzhiid());
		if (sheTuanBuMenJiBenXinXins.isEmpty()) {
			xueShengZuZhiXinXi.setFuzeren(
					xueShengService.getUserById(Integer.parseInt(xueShengZuZhiXinXi.getFuzeren())).getXingming());
			mView.setViewName("stu/xueshengzuzhi");
			mView.addObject("jibenxinxi", xi);
			mView.addObject("xinxi", xueShengZuZhiXinXi);
			return mView;
		}
		for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
			SheTuanBuMenXinXin buMenXinXin = sheTuanService
					.selectSheTuanBuMenByBuMenIDAndNianDu(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
			buMenXinXin
					.setBuzhang(xueShengService.getUserById(Integer.parseInt(buMenXinXin.getBuzhang())).getXingming());
			buMenXinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
			buMenXinXin.setZhize(sheTuanBuMenJiBenXinXin.getZhize());
			buMenXinXins.add(buMenXinXin);
		}
		xueShengZuZhiXinXi.setFuzeren(
				xueShengService.getUserById(Integer.parseInt(xueShengZuZhiXinXi.getFuzeren())).getXingming());
		xueShengZuZhiXinXi.setBumen(buMenXinXins);
		xueShengZuZhiXinXi.setZhidaoren(yongHuService
				.selectYongHuByID(Integer.parseInt(xueShengZuZhiXinXi.getZhidaoren())).getYonghuxingming());
		mView.setViewName("stu/xueshengzuzhi");
		mView.addObject("jibenxinxi", xi);
		mView.addObject("xinxi", xueShengZuZhiXinXi);
		return mView;

	}

	// 社团详情
	@RequestMapping(value = "shetuan")
	public ModelAndView sheTuan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sheTuanID = request.getParameter("id");
		logger.info("社团ID：" + sheTuanID);
		if (!Util.isNumeric(sheTuanID)) {
			response.sendRedirect("logout");
			return null;
		}
		Map<String, String> map = new HashMap<>();
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
		map.put("xuexiaoid", xueXiaoXueHao[0]);
		map.put("shetuanid", sheTuanID);
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndSheTuanID(map);
		if (sheTuanJiBenXinXi == null) {
			response.sendRedirect("logout");
			return null;
		}
		if (sheTuanJiBenXinXi.getZhidaojiaoshi()!=null) {
			sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
		}
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		logger.info(format.format(date));
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanIDAndNianDu(Integer.parseInt(sheTuanID),
				format.format(date));
		if (sheTuanXinXi == null) {
			ModelAndView mView = new ModelAndView();
			mView.setViewName("stu/shetuan");
			mView.addObject("shetuanjibenxinxi", sheTuanJiBenXinXi);
			return mView;
		}
		List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanBuMenJiBenXinXins.isEmpty()) {
			ModelAndView mView = new ModelAndView();
			sheTuanJiBenXinXi.setChuanjianren(
					xueShengService.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
			sheTuanXinXi.setShezhang(
					xueShengService.getUserById(Integer.parseInt(sheTuanXinXi.getShezhang())).getXingming());
			mView.setViewName("stu/shetuan");
			mView.addObject("shetuanxinxi", sheTuanXinXi);
			mView.addObject("shetuanjibenxinxi", sheTuanJiBenXinXi);
			return mView;
		}
		logger.info(sheTuanBuMenJiBenXinXins.size());
		for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
			SheTuanBuMenXinXin buMenXinXin = sheTuanService
					.selectSheTuanBuMenByBuMenIDAndNianDu(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
			if (buMenXinXin.getBuzhang()!=null) {
				buMenXinXin
				.setBuzhang(xueShengService.getUserById(Integer.parseInt(buMenXinXin.getBuzhang())).getXingming());
			}
			buMenXinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
			buMenXinXin.setZhize(sheTuanBuMenJiBenXinXin.getZhize());
			buMenXinXins.add(buMenXinXin);
		}
		sheTuanJiBenXinXi.setChuanjianren(
				xueShengService.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
		sheTuanXinXi.setBumen(buMenXinXins);
		sheTuanXinXi
				.setShezhang(xueShengService.getUserById(Integer.parseInt(sheTuanXinXi.getShezhang())).getXingming());
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/shetuan");
		mView.addObject("shetuanjibenxinxi", sheTuanJiBenXinXi);
		mView.addObject("shetuanxinxi", sheTuanXinXi);
		return mView;
	}

	// 社团报名
	@RequestMapping(value = "shetuanbaoming")
	public ModelAndView sheTuanBaoMing(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = sheTuanService
				.selectXueShengZuZhiJiBenXinXiByXueXiaoID(Integer.parseInt(xueXiaoXueHao[0]));
		for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
			xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
					.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
			if (xueShengZuZhiJiBenXinXi.getZhidaojiaoshi()!=null) {
				xueShengZuZhiJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(xueShengZuZhiJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
			}
			XueShengZuZhiXinXi xShengZuZhiXinXi = sheTuanService.selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(
					xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(), format.format(date));
			if (xShengZuZhiXinXi != null) {
				NaXin naXin = sheTuanService.selectNaXinByXueShengIDAndXueShengZuZhiXinXiID(user.getXueshengid(),
						xShengZuZhiXinXi.getXueshengzuzhixinxiid());
				if (naXin != null) {
					if (naXin.getZhuangtai()==2) {
						xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(3);//已拒绝
					}
					if (naXin.getZhuangtai()==0) {
						xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(1);
					}
				} else {
					xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(0);
				}
				String renYuanIDs[] = xShengZuZhiXinXi.getRenyuanids().split(",");
				for (int i = 0; i < renYuanIDs.length; i++) {
					if (renYuanIDs[i].equals(user.getXueshengid().toString())) {
						xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(2);
					}
				}
				NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService
						.selectNaXinJiBenXinXiByXueShengZuZhiXinXiID(xShengZuZhiXinXi.getXueshengzuzhixinxiid());
				if (naXinJiBenXinXi != null && naXinJiBenXinXi.getZhuangtai() == true) {
					xueShengZuZhiJiBenXinXi.setNaxin(1);
				} else {
					xueShengZuZhiJiBenXinXi.setNaxin(0);
				}
			} else {
				xueShengZuZhiJiBenXinXi.setNaxin(0);
			}

		}
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = sheTuanService
				.selectSheTuanJiBenXinXiByXueXiaoID(Integer.parseInt(xueXiaoXueHao[0]));
		for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
			sheTuanJiBenXinXi.setChuanjianren(
					xueShengService.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
			if(sheTuanJiBenXinXi.getZhidaojiaoshi()!=null){
				sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
			}
			SheTuanXinXi xSheTuanXinXi = sheTuanService
					.selectSheTuanXinXiBySheTuanIDAndNianDu(sheTuanJiBenXinXi.getShetuanid(), format.format(date));
			if (xSheTuanXinXi != null) {
				NaXin naXin = sheTuanService.selectNaXinByXueShengIDAndSheTuanXinXiID(user.getXueshengid(),
						xSheTuanXinXi.getShetuanxinxiid());
				if (naXin != null) {
					if (naXin.getZhuangtai()==2) {
						sheTuanJiBenXinXi.setBaomingzhuangtai(3);//已拒绝
					}
					if (naXin.getZhuangtai()==0) {
						sheTuanJiBenXinXi.setBaomingzhuangtai(1);
					}
				} else {
					sheTuanJiBenXinXi.setBaomingzhuangtai(0);
				}
				String sheYuanIDs[] = xSheTuanXinXi.getSheyuanids().split(",");
				for (int i = 0; i < sheYuanIDs.length; i++) {
					logger.info(sheYuanIDs[i]);
					if (sheYuanIDs[i].equals(user.getXueshengid().toString())) {
						sheTuanJiBenXinXi.setBaomingzhuangtai(2);
					}
				}
				NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService
						.selectNaXinJiBenXinXiBySheTuanXinXiID(xSheTuanXinXi.getShetuanxinxiid());
				if (naXinJiBenXinXi != null && naXinJiBenXinXi.getZhuangtai() == true) {
					sheTuanJiBenXinXi.setNaxin(1);
				} else {
					sheTuanJiBenXinXi.setNaxin(0);
				}
			} else {
				sheTuanJiBenXinXi.setNaxin(0);
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.addObject("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
		mView.addObject("shetuan", sheTuanJiBenXinXis);
		mView.setViewName("stu/shetuanbaoming");
		return mView;
	}

	@RequestMapping(value = "baoming")
	public ModelAndView BaoMing(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String string = request.getParameter("id");
		if (string==null) {
			response.sendRedirect("logout");
			return null;
		}
		String id[]= string.split(",");
		logger.info(id[0] + "+" + id[1]);
		if (id.length != 2) {
			response.sendRedirect("logout");
			return null;
		}
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
		// 报名学生组织
		ModelAndView mView = new ModelAndView();
		if (id[0].equals("1")) {
			Map<String, String> map = new HashMap<>();
			map.put("xuexiaoid", xueXiaoXueHao[0]);
			map.put("xueshengzuzhiid", id[1]);
			XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi = sheTuanService
					.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndXueShengZuZhiID(map);
			if (xueShengZuZhiJiBenXinXi == null) {
				response.sendRedirect("logout");
				return null;
			}
			XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(
					xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(), format.format(date));
			NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService
					.selectNaXinJiBenXinXiByXueShengZuZhiXinXiID(xueShengZuZhiXinXi.getXueshengzuzhixinxiid());
			List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
			if (naXinJiBenXinXi.getZhuangtai() != true) {
				response.sendRedirect("logout");
				return null;
			} else {
				NaXin naXin = sheTuanService.selectNaXinByXueShengIDAndXueShengZuZhiXinXiID(user.getXueshengid(),
						xueShengZuZhiXinXi.getXueshengzuzhixinxiid());
				if (naXin != null) {
					PrintWriter out = response.getWriter();
					response.setContentType("text/html; charset=utf-8");
					out.print("<script>alert('你已报名该组织！');</script>");
					out.print("<script>location='shetuanbaoming';</script>");
					return null;
				}
				List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
						.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
				for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
					SheTuanBuMenXinXin buMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(
							sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
					buMenXinXin.setBuzhang(
							xueShengService.getUserById(Integer.parseInt(buMenXinXin.getBuzhang())).getXingming());
					buMenXinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					buMenXinXin.setZhize(sheTuanBuMenJiBenXinXin.getZhize());
					buMenXinXins.add(buMenXinXin);
				}
				xueShengZuZhiXinXi.setBumen(buMenXinXins);
				mView.addObject("xueshengzuzhixinxi", xueShengZuZhiXinXi);
				mView.addObject("xueshengzuzhijibenxinxi", xueShengZuZhiJiBenXinXi);
				mView.setViewName("stu/xueshengzuzhibaomingbiao");
				return mView;
			}
		}
		// 报名社团
		if (id[0].equals("0")) {
			Map<String, String> map = new HashMap<>();
			map.put("xuexiaoid", xueXiaoXueHao[0]);
			map.put("shetuanid", id[1]);
			SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndSheTuanID(map);
			if (sheTuanJiBenXinXi == null) {
				response.sendRedirect("logout");
				return null;
			}
			if (sheTuanJiBenXinXi.getZhuangtai()==false) {
				response.sendRedirect("logout");
				return null;
			}
			SheTuanXinXi sheTuanXinXi = sheTuanService
					.selectSheTuanXinXiBySheTuanIDAndNianDu(sheTuanJiBenXinXi.getShetuanid(), format.format(date));
			NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService
					.selectNaXinJiBenXinXiBySheTuanXinXiID(sheTuanXinXi.getShetuanxinxiid());
			List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
			if (naXinJiBenXinXi.getZhuangtai() != true) {
				response.sendRedirect("logout");
				return null;
			} else {
				String sheYuanIDs[] = sheTuanXinXi.getSheyuanids().split(",");
				for (int i = 0; i < sheYuanIDs.length; i++) {
					logger.info(sheYuanIDs[i]);
					if (sheYuanIDs[i].equals(user.getXueshengid().toString())) {
						PrintWriter out = response.getWriter();
						response.setContentType("text/html; charset=utf-8");
						out.print("<script>alert('你已经是该社团成员，无法报名！');</script>");
						out.print("<script>location='shetuanbaoming';</script>");
						return null;
					}
				}
				NaXin naXin = sheTuanService.selectNaXinByXueShengIDAndSheTuanXinXiID(user.getXueshengid(),
						sheTuanXinXi.getShetuanxinxiid());
				if (naXin != null) {
					PrintWriter out = response.getWriter();
					response.setContentType("text/html; charset=utf-8");
					out.print("<script>alert('你已报名该社团！');</script>");
					out.print("<script>location='shetuanbaoming';</script>");
					return null;
				}
				List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
						.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanJiBenXinXi.getShetuanid());
				if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
					for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
						SheTuanBuMenXinXin buMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(
								sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
						/*buMenXinXin.setBuzhang(
								xueShengService.getUserById(Integer.parseInt(buMenXinXin.getBuzhang())).getXingming());*/
						buMenXinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
						buMenXinXin.setZhize(sheTuanBuMenJiBenXinXin.getZhize());
						buMenXinXins.add(buMenXinXin);
					}
					sheTuanXinXi.setBumen(buMenXinXins);
				}
				mView.addObject("shetuanjibenxinxi", sheTuanJiBenXinXi);
				mView.addObject("shetuanxinxi", sheTuanXinXi);
				mView.setViewName("stu/shetuanbaomingbiao");
				return mView;
			}

		}
		return null;
	}

	// 提交报名表
	@RequestMapping(value = "tijiaobaoming")
	public void TiJiaoBaoMing(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		String leiXing = request.getParameter("leixing");
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int i = 0;
		if (leiXing.equals("0")) {
			String xueShengZuZhiXinXiID = request.getParameter("xueshengzuzhixinxiid");
			String xingBie = request.getParameter("xingbie");
			String dianHua = request.getParameter("dianhua");
			String chuShengRiQi = request.getParameter("chushengriqi");
			String buMenID = request.getParameter("bumen");
			String aiHao = request.getParameter("aihaotechang");
			String ziWoJieShao = request.getParameter("ziwojieshao");
			NaXin naXin = new NaXin();
			if (xingBie.equals("0")) {
				naXin.setXingbie(false);
			} else {
				naXin.setXingbie(true);
			}
			naXin.setAihaotechang(aiHao);
			if (buMenID!=null) {
				naXin.setBaomingbumenid(Integer.parseInt(buMenID));
			}
			naXin.setBaomingxueshengzuzhiid(Integer.parseInt(xueShengZuZhiXinXiID));
			naXin.setChushengriqi(format.parse(chuShengRiQi));
			naXin.setLianxidianhua(dianHua);
			naXin.setZiwojieshao(ziWoJieShao);
			naXin.setXueshengid(user.getXueshengid());
			naXin.setXingming(user.getXingming());
			naXin.setNaxingjibenxinxiid(
					sheTuanService.selectNaXinJiBenXinXiByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID))
							.getNaxingjibenxinxiid());
			i = sheTuanService.insertNaXin(naXin);
			logger.info(i);
		} else {
			String sheTuanXinXiID = request.getParameter("shetuanxinxiid");
			SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiID(Integer.parseInt(sheTuanXinXiID));
			SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
			if (sheTuanJiBenXinXi.getZhuangtai()==false) {
				response.sendRedirect("logout");
				return;
			}
			NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService
					.selectNaXinJiBenXinXiBySheTuanXinXiID(Integer.parseInt(sheTuanXinXiID));
			if (naXinJiBenXinXi.getZhuangtai() != true) {
				response.sendRedirect("logout");
				return;
			}
			String xingBie = request.getParameter("xingbie");
			String dianHua = request.getParameter("dianhua");
			String chuShengRiQi = request.getParameter("chushengriqi");
			String buMenID = request.getParameter("bumen");
			logger.info(buMenID);
			String aiHao = request.getParameter("aihaotechang");
			String ziWoJieShao = request.getParameter("ziwojieshao");
			logger.info(sheTuanXinXiID + ";" + xingBie + ";" + dianHua + ";" + chuShengRiQi + ";" + buMenID + ";"
					+ aiHao + ";" + ziWoJieShao);
			NaXin naXin = new NaXin();
			if (xingBie.equals("0")) {
				naXin.setXingbie(false);
			} else {
				naXin.setXingbie(true);
			}
			naXin.setAihaotechang(aiHao);
			if (buMenID != null) {
				naXin.setBaomingbumenid(Integer.parseInt(buMenID));
			}
			naXin.setBaomingshetuanid(Integer.parseInt(sheTuanXinXiID));
			naXin.setChushengriqi(format.parse(chuShengRiQi));
			naXin.setLianxidianhua(dianHua);
			naXin.setZiwojieshao(ziWoJieShao);
			naXin.setXueshengid(user.getXueshengid());
			naXin.setXingming(user.getXingming());
			naXin.setNaxingjibenxinxiid(sheTuanService
					.selectNaXinJiBenXinXiBySheTuanXinXiID(Integer.parseInt(sheTuanXinXiID)).getNaxingjibenxinxiid());
			i = sheTuanService.insertNaXin(naXin);
			logger.info(naXin.getNaxingid());
		}
		PrintWriter out = response.getWriter();
		if (i > 0) {
			response.setContentType("text/html; charset=utf-8");
			out.print("<script>alert('提交成功');</script>");
			out.print("<script>location='shetuanbaoming';</script>");
		} else {
			response.setContentType("text/html; charset=utf-8");
			out.print("<script>alert('提交失败');</script>");
		}
	}

	// 我的社团
	@RequestMapping(value = "wodeshetuan")
	public ModelAndView woDeSheTuan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
		List<SheTuanXinXi> sheTuanXinXis = sheTuanService.selectSheTuanXinXiByXueShengIDAndNianDuAndXueXiaoID(
				"%," + user.getXueshengid().toString() + ",%", user.getXueshengid().toString() + ",%",
				format.format(date),Integer.parseInt(xueXiaoXueHao[0]));
		SheTuanJiBenXinXi sheTuanJiBenXinXi = null;
		NaXinJiBenXinXi naXinJiBenXinXi = null;
		for (SheTuanXinXi sheTuanXinXi : sheTuanXinXis) {
			naXinJiBenXinXi = new NaXinJiBenXinXi();
			naXinJiBenXinXi = sheTuanService.selectNaXinJiBenXinXiBySheTuanXinXiID(sheTuanXinXi.getShetuanxinxiid());
			if (naXinJiBenXinXi==null) {
				sheTuanXinXi.setIsnaxin(false);
			}else {
				if (naXinJiBenXinXi.getZhuangtai()==true) {
					sheTuanXinXi.setIsnaxin(true);
				}else {
					sheTuanXinXi.setIsnaxin(false);
				}
			}
			sheTuanJiBenXinXi = new SheTuanJiBenXinXi();
			sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
			sheTuanXinXi.setMingcheng(sheTuanJiBenXinXi.getMingcheng());
			List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
					.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanJiBenXinXi.getShetuanid());
			if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
				List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
				for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
					SheTuanBuMenXinXin buMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(
							sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
					if (buMenXinXin!=null) {
						buMenXinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
						buMenXinXins.add(buMenXinXin);
					}
				}
				sheTuanXinXi.setBumen(buMenXinXins);
			}
			if (Integer.parseInt(sheTuanXinXi.getShezhang())==user.getXueshengid()) {
				sheTuanXinXi.setZhiwu("社长");
			}
			if (sheTuanXinXi.getBianji()!=null) {
				if (sheTuanXinXi.getBianji().equals(user.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (sheTuanXinXi.getZhiwu()==null||sheTuanXinXi.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					}else {
							sBuffer = sBuffer.append(sheTuanXinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
					sheTuanXinXi.setZhiwu(sBuffer.toString());
				}
			}
			if (sheTuanXinXi.getBumen()!=null&&!sheTuanXinXi.getBumen().isEmpty()) {
				for (SheTuanBuMenXinXin sheTuanBuMenXinXin : sheTuanXinXi.getBumen()) {
					if (sheTuanBuMenXinXin.getBuzhang()==null) {
						continue;
					}
					if (Integer.parseInt(sheTuanBuMenXinXin.getBuzhang())==user.getXueshengid()) {
						StringBuffer sBuffer = new StringBuffer();
						if (sheTuanXinXi.getZhiwu()!=null&&sheTuanXinXi.getZhiwu()!="") {
							sBuffer.append(sheTuanXinXi.getZhiwu());
							sBuffer.append(","+sheTuanBuMenXinXin.getMingcheng()+"部长");
						}else {
							sBuffer.append(sheTuanBuMenXinXin.getMingcheng()+"部长");
						}
						sheTuanXinXi.setZhiwu(sBuffer.toString());
					}
				}
			}
			if (sheTuanXinXi.getZhiwu()==null||sheTuanXinXi.getZhiwu().equals("")) {
				sheTuanXinXi.setZhiwu("社员");
			}
			
		}
		List<XueShengZuZhiXinXi> xueShengZuZhiXinXis = sheTuanService.selectXueShengZuZhiXinXiByXueShengIDAndNianDu(
				"%," + user.getXueshengid().toString() + ",%", user.getXueshengid().toString() + ",%",
				format.format(date));
		XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi = null;
		for (XueShengZuZhiXinXi xueShengZuZhiXinXi : xueShengZuZhiXinXis) {
			naXinJiBenXinXi = new NaXinJiBenXinXi();
			naXinJiBenXinXi = sheTuanService.selectNaXinJiBenXinXiByXueShengZuZhiXinXiID(xueShengZuZhiXinXi.getXueshengzuzhixinxiid());
			if (naXinJiBenXinXi==null) {
				xueShengZuZhiXinXi.setIsnaxin(false);
			}else {
				if (naXinJiBenXinXi.getZhuangtai()==true) {
					xueShengZuZhiXinXi.setIsnaxin(true);
				}else {
					xueShengZuZhiXinXi.setIsnaxin(false);
				}
			}
			xueShengZuZhiJiBenXinXi = new XueShengZuZhiJiBenXinXi();
			xueShengZuZhiJiBenXinXi = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
			xueShengZuZhiXinXi.setMingcheng(xueShengZuZhiJiBenXinXi.getMingcheng());
			List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
					.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
			if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
				List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
				for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
					SheTuanBuMenXinXin buMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(
							sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
					if (buMenXinXin!=null) {
						buMenXinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
						buMenXinXins.add(buMenXinXin);
					}
				}
				xueShengZuZhiXinXi.setBumen(buMenXinXins);
			}
			if (Integer.parseInt(xueShengZuZhiXinXi.getFuzeren())==user.getXueshengid()) {
				xueShengZuZhiXinXi.setZhiwu("负责人");
			}
			if (xueShengZuZhiXinXi.getBianji()!=null) {
				if (xueShengZuZhiXinXi.getBianji().equals(user.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xueShengZuZhiXinXi.getZhiwu()==null||xueShengZuZhiXinXi.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					}else {
							sBuffer = sBuffer.append(xueShengZuZhiXinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
					xueShengZuZhiXinXi.setZhiwu(sBuffer.toString());
				}
			}
			if (xueShengZuZhiXinXi.getBumen()!=null&&!xueShengZuZhiXinXi.getBumen().isEmpty()) {
				for (SheTuanBuMenXinXin sheTuanBuMenXinXin : xueShengZuZhiXinXi.getBumen()) {
					if (sheTuanBuMenXinXin.getBuzhang()==null) {
						continue;
					}
					if (Integer.parseInt(sheTuanBuMenXinXin.getBuzhang())==user.getXueshengid()) {
						StringBuffer sBuffer = new StringBuffer();
						if (xueShengZuZhiXinXi.getZhiwu()!=null&&xueShengZuZhiXinXi.getZhiwu()!="") {
							sBuffer.append(xueShengZuZhiXinXi.getZhiwu());
							sBuffer.append(","+sheTuanBuMenXinXin.getMingcheng()+"部长");
						}else {
							sBuffer.append(sheTuanBuMenXinXin.getMingcheng()+"部长");
						}
						xueShengZuZhiXinXi.setZhiwu(sBuffer.toString());
					}
				}
			}
			if (xueShengZuZhiXinXi.getZhiwu()==null||xueShengZuZhiXinXi.getZhiwu().equals("")) {
				xueShengZuZhiXinXi.setZhiwu("成员");
			}
		}
		List<SheTuanChuangJian> sheTuanChuangJians = sheTuanService.selectSheTuanChuangJianByXueShengID(user.getXueshengid());
		ModelAndView mView = new ModelAndView();
		mView.addObject("chuangjian",sheTuanChuangJians);
		mView.addObject("shetuan",sheTuanXinXis);
		mView.addObject("xueshengzuzhi", xueShengZuZhiXinXis);
		mView.setViewName("stu/wodeshetuan");
		return mView;
	}
	
	@RequestMapping(value="ssqjf")
	public ModelAndView SheTuanShenQingJingFei(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String sheTuanXinXiID=request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID),user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanXinXi.setMingcheng(sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid()).getMingcheng());
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/sshenqingjingfei");
		mView.addObject("shetuan",sheTuanXinXi);
		return mView;	
	}
	
	@RequestMapping(value="subssqjf")
	@ResponseBody
	public String subSheTuanShenQingJingFei(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String retInfo ="";
		String date[]=request.getParameter("CODE").split(",zytech,");
		if (date.length!=3) {
			response.sendRedirect("logout");
			return null;
		}
		if (!Util.isNumeric(date[0])) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(date[0]), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJingFei sheTuanJingFei = new SheTuanJingFei();
		sheTuanJingFei.setShetuanxinxiid(sheTuanXinXi.getShetuanxinxiid());
		sheTuanJingFei.setJine(Integer.parseInt(date[1]));
		sheTuanJingFei.setShenqingren(user.getXueshengid());
		sheTuanJingFei.setYongtu(date[2]);
		sheTuanJingFei.setShifoupizhun(0);
		Date time = new Date();
		sheTuanJingFei.setShenqingshijian(time);
		int i = sheTuanService.insertSheTuanJingFeiShenQing(sheTuanJingFei);
		if (i>0) {
			retInfo="success";
		}else {
			retInfo="fail";
		}
		return retInfo;
	}
	
	@RequestMapping(value="subxsqjf")
	@ResponseBody
	public String subXueShengZuZhiShenQingJingFei(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String retInfo ="";
		String date[]=request.getParameter("CODE").split(",zytech,");
		if (date.length!=3) {
			response.sendRedirect("logout");
			return null;
		}
		if (!Util.isNumeric(date[0])) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(date[0]));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJingFei sheTuanJingFei = new SheTuanJingFei();
		sheTuanJingFei.setXueshengzuzhixinxiid(xueShengZuZhiXinXi.getXueshengzuzhixinxiid());
		sheTuanJingFei.setJine(Integer.parseInt(date[1]));
		sheTuanJingFei.setShenqingren(user.getXueshengid());
		sheTuanJingFei.setYongtu(date[2]);
		sheTuanJingFei.setShifoupizhun(0);
		Date time = new Date();
		sheTuanJingFei.setShenqingshijian(time);
		int i = sheTuanService.insertSheTuanJingFeiShenQing(sheTuanJingFei);
		if (i>0) {
			retInfo="success";
		}else {
			retInfo="fail";
		}
		return retInfo;
	}
	
	@RequestMapping(value="cjst")
	public ModelAndView chuangJianSheTuan(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		BanJi banJi = banJiService.selectByPrimaryKey(user.getBanjiid());
		List<YongHu> jiaoShis = yongHuService.getAllByYuanXiID(banJi.getYuanxiid());
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/chuangjianshetuan");
		mView.addObject("jiaoshis", jiaoShis);
		return mView;
	}
	
	@RequestMapping(value="xsqjf")
	public ModelAndView xueShengZuZhiShenQingJingFei(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String xueShengZuZhiXinXiID=request.getParameter("id");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi	 xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		xueShengZuZhiXinXi.setMingcheng(sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid()).getMingcheng());
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/xshenqingjingfei");
		mView.addObject("xueshengzuzhi",xueShengZuZhiXinXi);
		return mView;	
	}
	
	@RequestMapping(value="sjfjd")
	public ModelAndView sheTuanJingFeiJinDu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String sheTuanXinXiID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView =new ModelAndView();
		List<SheTuanJingFei> sheTuanJingFeis = sheTuanService.selectBySheTuanXinXiID(Integer.parseInt(sheTuanXinXiID));
		mView.setViewName("stu/jingfeijindu");
		mView.addObject("jingfei",sheTuanJingFeis);
		return mView;
	}
	
	@RequestMapping(value="xjfjd")
	public ModelAndView xueShengZuZhiJingFeiJinDu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String xueShengZuZhiXinXiID = request.getParameter("id");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView =new ModelAndView();
		List<SheTuanJingFei> sheTuanJingFeis = sheTuanService.selectSheTuanJinFeiByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		mView.setViewName("stu/jingfeijindu");
		mView.addObject("jingfei",sheTuanJingFeis);
		return mView;
	}
	
	@RequestMapping(value="cjxszz")
	public ModelAndView chuangJianXueShengZuZhi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		BanJi banJi = banJiService.selectByPrimaryKey(user.getBanjiid());
		List<YongHu> jiaoShis = yongHuService.getAllByYuanXiID(banJi.getYuanxiid());
		ModelAndView mView = new ModelAndView();
		mView.addObject("jiaoshis", jiaoShis);
		mView.setViewName("stu/chuangjianxueshengzuzhi");
		return mView;
	}
	
	@RequestMapping(value="subcjst")
	@ResponseBody
	public String subCJST(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String retInfo = "";
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		logger.info(data[0]+data[1]+data[2]+data[3]+data[4]);
		XueSheng user = (XueSheng)request.getSession().getAttribute("user");
		String xueXiaoXueHao[]=user.getXuexiaoXuehao().split("_");
		SheTuanChuangJian sJian = sheTuanService.selectSheTuanChuangJianByXueXiaoIDAndMingCheng(Integer.parseInt(xueXiaoXueHao[0]), data[1]);
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndMingCheng(Integer.parseInt(xueXiaoXueHao[0]), data[1]);
		if (sJian!=null||sheTuanJiBenXinXi!=null) {
			retInfo = "mingcheng";
		}else {
			SheTuanChuangJian sheTuanChuangJian = new SheTuanChuangJian();
			sheTuanChuangJian.setChuangjianren(user.getXueshengid());
			sheTuanChuangJian.setDianhua(data[0]);
			sheTuanChuangJian.setMingcheng(data[1]);
			sheTuanChuangJian.setJieshao(data[2]);
			sheTuanChuangJian.setChuangjianliyou(data[3]);
			sheTuanChuangJian.setXuexiaoid(Integer.parseInt(xueXiaoXueHao[0]));
			sheTuanChuangJian.setLeixing(false);
			sheTuanChuangJian.setZhidaojiaoshi(data[4]);
			sheTuanChuangJian.setZhuangtai(0);
			int i = sheTuanService.insertSheTuanChuangJian(sheTuanChuangJian);
			if (i>0) {
				retInfo="success";
			}else {
				retInfo="fail";
			}
		}
		return retInfo;
	}
	
	@RequestMapping(value="subcjxszz")
	@ResponseBody
	public String subCJXSZZ(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String retInfo = "";
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		logger.info(data[0]+data[1]+data[2]+data[3]);
		XueSheng user = (XueSheng)request.getSession().getAttribute("user");
		String xueXiaoXueHao[]=user.getXuexiaoXuehao().split("_");
		SheTuanChuangJian sJian = sheTuanService.selectSheTuanChuangJianByXueXiaoIDAndMingCheng(Integer.parseInt(xueXiaoXueHao[0]), data[1]);
		Map<String, String> map =new HashMap<>();
		map.put("xuexiaoid", xueXiaoXueHao[0]);
		map.put("mingcheng", data[1]);
		List<XueShengZuZhiJiBenXinXi> xi = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
		if (sJian!=null||(xi!=null&&!xi.isEmpty())) {
			retInfo = "mingcheng";
		}else {
			SheTuanChuangJian sheTuanChuangJian = new SheTuanChuangJian();
			sheTuanChuangJian.setChuangjianren(user.getXueshengid());
			sheTuanChuangJian.setDianhua(data[0]);
			sheTuanChuangJian.setMingcheng(data[1]);
			sheTuanChuangJian.setJieshao(data[2]);
			sheTuanChuangJian.setChuangjianliyou(data[3]);
			sheTuanChuangJian.setXuexiaoid(Integer.parseInt(xueXiaoXueHao[0]));
			sheTuanChuangJian.setLeixing(true);
			sheTuanChuangJian.setZhidaojiaoshi(data[4]);
			sheTuanChuangJian.setZhuangtai(0);
			int i = sheTuanService.insertSheTuanChuangJian(sheTuanChuangJian);
			if (i>0) {
				retInfo="success";
			}else {
				retInfo="fail";
			}
		}
		return retInfo;
	}
	
	@RequestMapping(value="scksy")
	public ModelAndView sheTuanChaKanSheYuanXinXi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String sheTuanXinXiID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		String sheTuanRenYuanIDs[] = sheTuanXinXi.getSheyuanids().split(",");
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		SheTuanRenYuanXinXi xinXi = null;
		XueSheng xueSheng = null;
		BanJi banJi = null;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		for (int i = 0; i < sheTuanRenYuanIDs.length; i++) {
			xinXi = new SheTuanRenYuanXinXi();
			xueSheng = new XueSheng();
			banJi = new BanJi();
			xueSheng = xueShengService.getUserById(Integer.parseInt(sheTuanRenYuanIDs[i]));
			banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
			xinXi.setXueshengid(xueSheng.getXueshengid());
			xinXi.setXuehao(xueSheng.getXuehao());
			xinXi.setXingming(xueSheng.getXingming());
			xinXi.setBanji(banJi.getBanjimingcheng());
			xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
			xinXi.setShoujihao(xueSheng.getDianhua());
			if (sheTuanXinXi.getShezhang().equals(xueSheng.getXueshengid().toString())) {
				xinXi.setZhiwu("社长");
			}
			if (sheTuanXinXi.getBianji()!=null) {
				if (sheTuanXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					}else {
							sBuffer = sBuffer.append(xinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xinXi.setZhiwu(sBuffer.toString());
				}
			}
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%", xueSheng.getXueshengid().toString() + ",%");
				if (sheTuanBuMenXinXin!=null) {
					if (sheTuanBuMenXinXin.getZhiwu()!=null) {
						String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
						for (int j = 0; j < zhiwus.length; j++) {
							String zhiwu[] =zhiwus[j].split(",");
							if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
								StringBuffer sBuffer = new StringBuffer();
								if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
									sBuffer = sBuffer.append(zhiwu[0]);
								}else {
									sBuffer = sBuffer.append(xinXi.getZhiwu());
									sBuffer = sBuffer.append(","+zhiwu[0]);
								}
								logger.info(sBuffer);
								xinXi.setZhiwu(sBuffer.toString());
							}
						}
					}
					xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
				}
			}
			if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
				xinXi.setZhiwu("成员");
			}
			xinXis.add(xinXi);
		}
		List<SheTuanBuMenXinXin> sheTuanBuMenXinXins = new ArrayList<>();
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(sheTuanBuMenJiBenXinXin.getBumenid(),format.format(date));
				if (xinXin!=null) {
					xinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					sheTuanBuMenXinXins.add(xinXin);
				}
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/renyuanxinxi");
		mView.addObject("bumen", sheTuanBuMenXinXins);
		mView.addObject("shetuanxinxiid", sheTuanXinXiID);
		mView.addObject("renyuans",xinXis);
		return mView;
	}
	
	@RequestMapping(value="xcksy")
	public ModelAndView xueShengZuZhiChaKanSheYuanXinXi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String xueShengZuZhiXinXiID = request.getParameter("id");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		String RenYuanIDs[] = xueShengZuZhiXinXi.getRenyuanids().split(",");
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		SheTuanRenYuanXinXi xinXi = null;
		XueSheng xueSheng = null;
		BanJi banJi = null;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		for (int i = 0; i < RenYuanIDs.length; i++) {
			xinXi = new SheTuanRenYuanXinXi();
			xueSheng = new XueSheng();
			banJi = new BanJi();
			xueSheng = xueShengService.getUserById(Integer.parseInt(RenYuanIDs[i]));
			banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
			xinXi.setXueshengid(xueSheng.getXueshengid());
			xinXi.setXuehao(xueSheng.getXuehao());
			xinXi.setXingming(xueSheng.getXingming());
			xinXi.setBanji(banJi.getBanjimingcheng());
			xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
			xinXi.setShoujihao(xueSheng.getDianhua());
			if (xueShengZuZhiXinXi.getFuzeren().equals(xueSheng.getXueshengid().toString())) {
				xinXi.setZhiwu("负责人");
			}
			if (xueShengZuZhiXinXi.getBianji()!=null) {
				if (xueShengZuZhiXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					}else {
							sBuffer = sBuffer.append(xinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xinXi.setZhiwu(sBuffer.toString());
				}
			}
			if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
				for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
					SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%", xueSheng.getXueshengid().toString() + ",%");
					if (sheTuanBuMenXinXin!=null) {
						if (sheTuanBuMenXinXin.getZhiwu()!=null) {
							String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
							for (int j = 0; j < zhiwus.length; j++) {
								String zhiwu[] =zhiwus[j].split(",");
								if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
									StringBuffer sBuffer = new StringBuffer();
									if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
										sBuffer = sBuffer.append(zhiwu[0]);
									}else {
										sBuffer = sBuffer.append(xinXi.getZhiwu());
										sBuffer = sBuffer.append(","+zhiwu[0]);
									}
									logger.info(sBuffer);
									xinXi.setZhiwu(sBuffer.toString());
								}
							}
						}
						xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					}
				}
			}
			if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
				xinXi.setZhiwu("成员");
			}
			xinXis.add(xinXi);
		}
		List<SheTuanBuMenXinXin> sheTuanBuMenXinXins = new ArrayList<>();
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(sheTuanBuMenJiBenXinXin.getBumenid(),format.format(date));
				if (xinXin!=null) {
					xinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					sheTuanBuMenXinXins.add(xinXin);
				}
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/xrenyuanxinxi");
		mView.addObject("bumen", sheTuanBuMenXinXins);
		mView.addObject("xueshengzuzhixinxiid", xueShengZuZhiXinXiID);
		mView.addObject("renyuans",xinXis);
		return mView;
	}
	
	@RequestMapping(value="xfzr")
	public ModelAndView xueShengZuZhiZhiDingFuZeRen(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String xueShengZuZhiXinXiID = request.getParameter("id");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		String RenYuanIDs[] = xueShengZuZhiXinXi.getRenyuanids().split(",");
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		SheTuanRenYuanXinXi xinXi = null;
		XueSheng xueSheng = null;
		BanJi banJi = null;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		for (int i = 0; i < RenYuanIDs.length; i++) {
			xinXi = new SheTuanRenYuanXinXi();
			xueSheng = new XueSheng();
			banJi = new BanJi();
			xueSheng = xueShengService.getUserById(Integer.parseInt(RenYuanIDs[i]));
			banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
			xinXi.setXueshengid(xueSheng.getXueshengid());
			xinXi.setXuehao(xueSheng.getXuehao());
			xinXi.setXingming(xueSheng.getXingming());
			xinXi.setBanji(banJi.getBanjimingcheng());
			xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
			xinXi.setShoujihao(xueSheng.getDianhua());
			if (xueShengZuZhiXinXi.getFuzeren().equals(xueSheng.getXueshengid().toString())) {
				xinXi.setZhiwu("负责人");
			}
			if (xueShengZuZhiXinXi.getBianji()!=null) {
				if (xueShengZuZhiXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					}else {
							sBuffer = sBuffer.append(xinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xinXi.setZhiwu(sBuffer.toString());
				}
			}
			List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
			if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
				for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
					SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%", xueSheng.getXueshengid().toString() + ",%");
					if (sheTuanBuMenXinXin!=null) {
						if (sheTuanBuMenXinXin.getZhiwu()!=null) {
							String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
							for (int j = 0; j < zhiwus.length; j++) {
								String zhiwu[] =zhiwus[j].split(",");
								if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
									StringBuffer sBuffer = new StringBuffer();
									if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
										sBuffer = sBuffer.append(zhiwu[0]);
									}else {
										sBuffer = sBuffer.append(xinXi.getZhiwu());
										sBuffer = sBuffer.append(","+zhiwu[0]);
									}
									logger.info(sBuffer);
									xinXi.setZhiwu(sBuffer.toString());
								}
							}
							xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
						}
					}
				}
			}
			if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
				xinXi.setZhiwu("成员");
			}
			xinXis.add(xinXi);
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/zhidingfuzeren");
		mView.addObject("renyuans",xinXis);
		mView.addObject("xueshengzuzhixinxiid",xueShengZuZhiXinXiID);
		return mView;
	}
	
	@RequestMapping(value="szdsz")
	public ModelAndView sheTuanZhiDingSheZhang(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String sheTuanXinXiID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		String sheTuanRenYuanIDs[] = sheTuanXinXi.getSheyuanids().split(",");
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		SheTuanRenYuanXinXi xinXi = null;
		XueSheng xueSheng = null;
		BanJi banJi = null;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		for (int i = 0; i < sheTuanRenYuanIDs.length; i++) {
			xinXi = new SheTuanRenYuanXinXi();
			xueSheng = new XueSheng();
			banJi = new BanJi();
			xueSheng = xueShengService.getUserById(Integer.parseInt(sheTuanRenYuanIDs[i]));
			banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
			xinXi.setXueshengid(xueSheng.getXueshengid());
			xinXi.setXuehao(xueSheng.getXuehao());
			xinXi.setXingming(xueSheng.getXingming());
			xinXi.setBanji(banJi.getBanjimingcheng());
			xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
			xinXi.setShoujihao(xueSheng.getDianhua());
			if (sheTuanXinXi.getShezhang().equals(xueSheng.getXueshengid().toString())) {
				xinXi.setZhiwu("社长");
			}
			if (sheTuanXinXi.getBianji()!=null) {
				if (sheTuanXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					}else {
							sBuffer = sBuffer.append(xinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xinXi.setZhiwu(sBuffer.toString());
				}
			}
			List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%", xueSheng.getXueshengid().toString() + ",%");
				if (sheTuanBuMenXinXin!=null) {
					if (sheTuanBuMenXinXin.getZhiwu()!=null) {
						String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
						for (int j = 0; j < zhiwus.length; j++) {
							String zhiwu[] =zhiwus[j].split(",");
							if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
								StringBuffer sBuffer = new StringBuffer();
								if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
									sBuffer = sBuffer.append(zhiwu[0]);
								}else {
									sBuffer = sBuffer.append(xinXi.getZhiwu());
									sBuffer = sBuffer.append(","+zhiwu[0]);
								}
								logger.info(sBuffer);
								xinXi.setZhiwu(sBuffer.toString());
							}
						}
						xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					}
				}
			}
			if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
				xinXi.setZhiwu("成员");
			}
			xinXis.add(xinXi);
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/zhidingshezhang");
		mView.addObject("renyuans",xinXis);
		mView.addObject("shetuanxinxiid",sheTuanXinXi.getShetuanxinxiid());
		return mView;
	}
	
	@RequestMapping(value="sshedingsz")
	public void sZhiDingSheZhang(HttpServletRequest request,HttpServletResponse response) throws IOException{
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String xueShengID = request.getParameter("id");
		String sheTuanXinXiID = request.getParameter("stxx");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return;
		}
		if (!Util.isNumeric(xueShengID)) {
			response.sendRedirect("logout");
			return;
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return;
		}
		int i = sheTuanService.updateSheZhangBySheTuanXinXiID(Integer.parseInt(sheTuanXinXiID), Integer.parseInt(xueShengID));
		if (i>0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueShengID));
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你已被设为"+sheTuanJiBenXinXi.getMingcheng()+"社长");
			tiXingService.insert(tiXing);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.print("<script>alert('设定成功！你将失去权限！');</script>");
			out.print("<script>location='wodeshetuan';</script>");
		}else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.print("<script>alert('设定失败');</script>");
		}
	}
	
	@RequestMapping(value="xzdfzr")
	public void xZhiDingFuZeRen(HttpServletRequest request,HttpServletResponse response) throws IOException{
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String xueShengID = request.getParameter("id");
		String xueShengZuZhiXinXiID = request.getParameter("xsxx");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return;
		}
		if (!Util.isNumeric(xueShengID)) {
			response.sendRedirect("logout");
			return;
		}
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return;
		}
		int i = sheTuanService.updateFuZeRenByXueShengZuZhiXinXiID(xueShengZuZhiXinXi.getXueshengzuzhixinxiid(), Integer.parseInt(xueShengID));
		if (i>0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueShengID));
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你已被设定为"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid())+"负责人");
			tiXingService.insert(tiXing);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.print("<script>alert('设定成功！你将失去权限！');</script>");
			out.print("<script>location='wodeshetuan';</script>");
		}else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.print("<script>alert('设定失败');</script>");
		}
	}
	
	@RequestMapping(value="scknx")
	public ModelAndView sheTuanChaKanNaXin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String sheTuanXinXiID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		List<NaXin> naXins = sheTuanService.selectNaXinBySheTuanXinXiID(Integer.parseInt(sheTuanXinXiID));
		for (NaXin naXin : naXins) {
			if (naXin.getBaomingbumenid()!=null) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(naXin.getBaomingbumenid());
				SheTuanBuMenJiBenXinXin xin = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid());
				if (xin!=null) {
					naXin.setBaomingbumen(xin.getBumenmingcheng());
				}
			}
		}
		ModelAndView mView= new ModelAndView();
		mView.setViewName("stu/baomingrenyuan");
		mView.addObject("renyuans", naXins);
		mView.addObject("id",sheTuanXinXiID);
		return mView;
	
	}
	
	@RequestMapping(value="xcknx")
	public ModelAndView xueShengZuZhiChaKanNaXin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String xueShengZuZhiXinXiID = request.getParameter("id");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueShengZuZhiXinXi xi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		List<NaXin> naXins = sheTuanService.selectNaXinByXueShengZuZhiXinXiID(xi.getXueshengzuzhixinxiid());
		for (NaXin naXin : naXins) {
			if (naXin.getBaomingbumenid()!=null) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(naXin.getBaomingbumenid());
				SheTuanBuMenJiBenXinXin xin = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid());
				if (xin!=null) {
					naXin.setBaomingbumen(xin.getBumenmingcheng());
				}
			}
		}
		ModelAndView mView= new ModelAndView();
		mView.setViewName("stu/xbaomingrenyuan");
		mView.addObject("renyuans", naXins);
		mView.addObject("id",xi.getXueshengzuzhixinxiid());
		return mView;
	
	}
	
	@RequestMapping(value="acceptbaoming")
	@ResponseBody
	public String acceptBaoMing(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		String naXinID = request.getParameter("CODE");
		if (!Util.isNumeric(naXinID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		NaXin naXin = sheTuanService.selectNaXinByNaXinID(Integer.parseInt(naXinID));
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(naXin.getBaomingshetuanid(), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		logger.info(naXinID);
		String ret="";
		int i = sheTuanService.updateNaXinZhuangTaiByNaXinID(1, Integer.parseInt(naXinID));
		if (i>0) {
			logger.info(naXin.getBaomingbumenid());
			logger.info(naXin.getBaomingshetuanid());
			StringBuffer sheTuanRenYuans=new StringBuffer(sheTuanXinXi.getSheyuanids());
			sheTuanRenYuans=sheTuanRenYuans.append(naXin.getXueshengid()+",");
			int j = sheTuanService.updateSheTuanRenYuanBySheTuanXinXiID(sheTuanXinXi.getShetuanxinxiid(), sheTuanRenYuans.toString());
			if (naXin.getBaomingbumenid()!=null) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(naXin.getBaomingbumenid());
				StringBuffer buMenRenYuans =null;
				if (sheTuanBuMenXinXin.getRenyuanids()==null) {
					buMenRenYuans = new StringBuffer();
				}else {
					buMenRenYuans = new StringBuffer(sheTuanBuMenXinXin.getRenyuanids());
				}
				buMenRenYuans=buMenRenYuans.append(naXin.getXueshengid()+",");
				int k = sheTuanService.updateSheTuanBuMenRenYuanByBuMenXinXiID(sheTuanBuMenXinXin.getShetuanbumenxinxiid(), buMenRenYuans.toString());
				if (j>0&&k>0) {
					TiXing tiXing = new TiXing();
					tiXing.setJieshourenid(naXin.getXueshengid());
					tiXing.setZhuangtai(0);
					tiXing.setShijian(new Date());
					tiXing.setNeirong("你的"+sheTuanJiBenXinXi.getMingcheng()+"报名已通过");
					tiXingService.insert(tiXing);
					ret="success";
				}
			}else {
				if (j>0) {
					TiXing tiXing = new TiXing();
					tiXing.setJieshourenid(naXin.getXueshengid());
					tiXing.setZhuangtai(0);
					tiXing.setShijian(new Date());
					tiXing.setNeirong("你的"+sheTuanJiBenXinXi.getMingcheng()+"报名已通过");
					tiXingService.insert(tiXing);
					ret="success";
				}
			}
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="xacceptbaoming")
	@ResponseBody
	public String xacceptBaoMing(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		String naXinID = request.getParameter("CODE");
		if (!Util.isNumeric(naXinID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		NaXin naXin = sheTuanService.selectNaXinByNaXinID(Integer.parseInt(naXinID));
		if (naXin==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueShengZuZhiXinXi xi = sheTuanService.selectByXueShengZuZhiXinXiID(naXin.getBaomingxueshengzuzhiid());
		if (xi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		logger.info(naXinID);
		String ret="";
		int i = sheTuanService.updateNaXinZhuangTaiByNaXinID(1, Integer.parseInt(naXinID));
		if (i>0) {
			logger.info(naXin.getBaomingbumenid());
			StringBuffer renYuans=new StringBuffer(xi.getRenyuanids());
			renYuans=renYuans.append(naXin.getXueshengid()+",");
			xi.setRenyuanids(renYuans.toString());
			int j = sheTuanService.updateXueShengZuZhiXinXi(xi);
			if (naXin.getBaomingbumenid()!=null) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(naXin.getBaomingbumenid());
				StringBuffer buMenRenYuans =null;
				if (sheTuanBuMenXinXin.getRenyuanids()==null) {
					buMenRenYuans = new StringBuffer();
				}else {
					buMenRenYuans = new StringBuffer(sheTuanBuMenXinXin.getRenyuanids());
				}
				buMenRenYuans=buMenRenYuans.append(naXin.getXueshengid()+",");
				int k = sheTuanService.updateSheTuanBuMenRenYuanByBuMenXinXiID(sheTuanBuMenXinXin.getShetuanbumenxinxiid(), buMenRenYuans.toString());
				if (j>0&&k>0) {
					TiXing tiXing = new TiXing();
					tiXing.setJieshourenid(naXin.getXueshengid());
					tiXing.setZhuangtai(0);
					tiXing.setShijian(new Date());
					tiXing.setNeirong("你的"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xi.getXueshengzuzhiid()).getMingcheng()+"报名已通过");
					tiXingService.insert(tiXing);
					ret="success";
				}
			}else {
				if (j>0) {
					TiXing tiXing = new TiXing();
					tiXing.setJieshourenid(naXin.getXueshengid());
					tiXing.setZhuangtai(0);
					tiXing.setShijian(new Date());
					tiXing.setNeirong("你的"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xi.getXueshengzuzhiid()).getMingcheng()+"报名已通过");
					tiXingService.insert(tiXing);
					ret="success";
				}
			}
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="denybaoming")
	@ResponseBody
	public String denyBaoMing(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		String naXinID = request.getParameter("CODE");
		if (!Util.isNumeric(naXinID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		NaXin naXin = sheTuanService.selectNaXinByNaXinID(Integer.parseInt(naXinID));
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(naXin.getBaomingshetuanid(), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		logger.info(naXinID);
		String ret="";
		int i = sheTuanService.updateNaXinZhuangTaiByNaXinID(2, Integer.parseInt(naXinID));
		if (i>0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(naXin.getXueshengid());
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你的"+sheTuanJiBenXinXi.getMingcheng()+"报名已被拒绝");
			tiXingService.insert(tiXing);
			ret="success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="xdenybaoming")
	@ResponseBody
	public String xdenyBaoMing(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		String naXinID = request.getParameter("CODE");
		if (!Util.isNumeric(naXinID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		NaXin naXin = sheTuanService.selectNaXinByNaXinID(Integer.parseInt(naXinID));
		if (naXin==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueShengZuZhiXinXi xi = sheTuanService.selectByXueShengZuZhiXinXiID(naXin.getBaomingxueshengzuzhiid());
		if (xi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		logger.info(naXinID);
		String ret="";
		int i = sheTuanService.updateNaXinZhuangTaiByNaXinID(2, Integer.parseInt(naXinID));
		if (i>0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(naXin.getXueshengid());
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你的"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xi.getXueshengzuzhiid()).getMingcheng()+"报名已被拒绝");
			tiXingService.insert(tiXing);
			ret="success";
		}else {
			ret="fail";
		}
		return ret;
	}

	@RequestMapping(value="szdbz")
	public ModelAndView sheTuanZhiDingBuZhang(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String sheTuanXinXiID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		String bumen = request.getParameter("bumen");
		logger.info(bumen);
		if (bumen==null||bumen.equals("")) {
			String sheTuanRenYuanIDs[] = sheTuanXinXi.getSheyuanids().split(",");
			SheTuanRenYuanXinXi xinXi = null;
			XueSheng xueSheng = null;
			BanJi banJi = null;
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			for (int i = 0; i < sheTuanRenYuanIDs.length; i++) {
				xinXi = new SheTuanRenYuanXinXi();
				xueSheng = new XueSheng();
				banJi = new BanJi();
				xueSheng = xueShengService.getUserById(Integer.parseInt(sheTuanRenYuanIDs[i]));
				banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
				xinXi.setXueshengid(xueSheng.getXueshengid());
				xinXi.setXuehao(xueSheng.getXuehao());
				xinXi.setXingming(xueSheng.getXingming());
				xinXi.setBanji(banJi.getBanjimingcheng());
				xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
				xinXi.setShoujihao(xueSheng.getDianhua());
				if (sheTuanXinXi.getShezhang().equals(xueSheng.getXueshengid().toString())) {
					xinXi.setZhiwu("社长");
				}
				if (sheTuanXinXi.getBianji()!=null) {
					if (sheTuanXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
						StringBuffer sBuffer = new StringBuffer();
						if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
							sBuffer = sBuffer.append("编辑");
						}else {
								sBuffer = sBuffer.append(xinXi.getZhiwu());
								sBuffer = sBuffer.append(",编辑");
							}
							xinXi.setZhiwu(sBuffer.toString());
					}
				}
				List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
				for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
					SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%", xueSheng.getXueshengid().toString() + ",%");
					if (sheTuanBuMenXinXin!=null) {
						if (sheTuanBuMenXinXin.getZhiwu()!=null) {
							String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
							for (int j = 0; j < zhiwus.length; j++) {
								String zhiwu[] =zhiwus[j].split(",");
								if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
									StringBuffer sBuffer = new StringBuffer();
									if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
										sBuffer = sBuffer.append(zhiwu[0]);
									}else {
										sBuffer = sBuffer.append(xinXi.getZhiwu());
										sBuffer = sBuffer.append(","+zhiwu[0]);
									}
									logger.info(sBuffer);
									xinXi.setZhiwu(sBuffer.toString());
								}
							}
						}
						xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
						xinXi.setBumenid(sheTuanBuMenXinXin.getShetuanbumenxinxiid());
					}
				}
				if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
					xinXi.setZhiwu("成员");
				}
				xinXis.add(xinXi);
			}
		}else {
			SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(Integer.parseInt(bumen));
			SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid());
			if (sheTuanBuMenXinXin!=null) {
				String buMenRenYuans[]= sheTuanBuMenXinXin.getRenyuanids().split(",");
				SheTuanRenYuanXinXi xinXi = null;
				XueSheng xueSheng = null;
				BanJi banJi = null;
				for (int i = 0; i < buMenRenYuans.length; i++) {
					xinXi = new SheTuanRenYuanXinXi();
					xueSheng = new XueSheng();
					banJi = new BanJi();
					xueSheng = xueShengService.getUserById(Integer.parseInt(buMenRenYuans[i]));
					banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
					xinXi.setXueshengid(xueSheng.getXueshengid());
					xinXi.setXuehao(xueSheng.getXuehao());
					xinXi.setXingming(xueSheng.getXingming());
					xinXi.setBanji(banJi.getBanjimingcheng());
					xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
					xinXi.setShoujihao(xueSheng.getDianhua());
					xinXi.setBumenid(sheTuanBuMenXinXin.getShetuanbumenxinxiid());
					if (sheTuanXinXi.getShezhang().equals(xueSheng.getXueshengid().toString())) {
						xinXi.setZhiwu("社长");
					}
					if (sheTuanXinXi.getBianji()!=null) {
						if (sheTuanXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
							StringBuffer sBuffer = new StringBuffer();
							if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
								sBuffer = sBuffer.append("编辑");
							}else {
									sBuffer = sBuffer.append(xinXi.getZhiwu());
									sBuffer = sBuffer.append(",编辑");
								}
								xinXi.setZhiwu(sBuffer.toString());
						}
					}
					String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
					for (int j = 0; j < zhiwus.length; j++) {
						String zhiwu[] =zhiwus[j].split(",");
						if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
							StringBuffer sBuffer = new StringBuffer();
							if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
								sBuffer = sBuffer.append(zhiwu[0]);
							}else {
								sBuffer = sBuffer.append(xinXi.getZhiwu());
								sBuffer = sBuffer.append(","+zhiwu[0]);
							}
							logger.info(sBuffer);
							xinXi.setZhiwu(sBuffer.toString());
						}
					}
					xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
						xinXi.setZhiwu("成员");
					}
					xinXis.add(xinXi);
				}
			}
		}
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		List<SheTuanBuMenXinXin> sheTuanBuMenXinXins = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(sheTuanBuMenJiBenXinXin.getBumenid(),format.format(date));
				if (xinXin!=null) {
					xinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					sheTuanBuMenXinXins.add(xinXin);
				}
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.addObject("shetuanxinxiid", sheTuanXinXiID);
		mView.addObject("renyuans", xinXis);
		mView.addObject("bumen",sheTuanBuMenXinXins);
		mView.addObject("bumenxinxiid", bumen);
		mView.setViewName("stu/szhidingbuzhang");
		return mView;
	}
	
	@RequestMapping(value="xzdbz")
	public ModelAndView xueShengZuZhiZhiDingBuZhang(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String xueShengZuZhiXinXiID = request.getParameter("id");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		String bumen = request.getParameter("bumen");
		logger.info(bumen);
		if (bumen==null||bumen.equals("")) {
			String sheTuanRenYuanIDs[] = xueShengZuZhiXinXi.getRenyuanids().split(",");
			SheTuanRenYuanXinXi xinXi = null;
			XueSheng xueSheng = null;
			BanJi banJi = null;
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			for (int i = 0; i < sheTuanRenYuanIDs.length; i++) {
				xinXi = new SheTuanRenYuanXinXi();
				xueSheng = new XueSheng();
				banJi = new BanJi();
				xueSheng = xueShengService.getUserById(Integer.parseInt(sheTuanRenYuanIDs[i]));
				banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
				xinXi.setXueshengid(xueSheng.getXueshengid());
				xinXi.setXuehao(xueSheng.getXuehao());
				xinXi.setXingming(xueSheng.getXingming());
				xinXi.setBanji(banJi.getBanjimingcheng());
				xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
				xinXi.setShoujihao(xueSheng.getDianhua());
				if (xueShengZuZhiXinXi.getFuzeren().equals(xueSheng.getXueshengid().toString())) {
					xinXi.setZhiwu("负责人");
				}
				if (xueShengZuZhiXinXi.getBianji()!=null) {
					if (xueShengZuZhiXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
						StringBuffer sBuffer = new StringBuffer();
						if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
							sBuffer = sBuffer.append("编辑");
						}else {
								sBuffer = sBuffer.append(xinXi.getZhiwu());
								sBuffer = sBuffer.append(",编辑");
							}
							xinXi.setZhiwu(sBuffer.toString());
					}
				}
				if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
					for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
						SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%", xueSheng.getXueshengid().toString() + ",%");
						if (sheTuanBuMenXinXin!=null) {
							if (sheTuanBuMenXinXin.getZhiwu()!=null) {
								String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
								for (int j = 0; j < zhiwus.length; j++) {
									String zhiwu[] =zhiwus[j].split(",");
									if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
										StringBuffer sBuffer = new StringBuffer();
										if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
											sBuffer = sBuffer.append(zhiwu[0]);
										}else {
											sBuffer = sBuffer.append(xinXi.getZhiwu());
											sBuffer = sBuffer.append(","+zhiwu[0]);
										}
										logger.info(sBuffer);
										xinXi.setZhiwu(sBuffer.toString());
									}
								}
							}
							xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
							xinXi.setBumenid(sheTuanBuMenXinXin.getShetuanbumenxinxiid());
						}
					}
				}
				if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
					xinXi.setZhiwu("成员");
				}
				xinXis.add(xinXi);
			}
		}else {
			SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(Integer.parseInt(bumen));
			SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid());
			if (sheTuanBuMenXinXin!=null) {
				if (sheTuanBuMenXinXin.getRenyuanids()!=null) {
					String buMenRenYuans[]= sheTuanBuMenXinXin.getRenyuanids().split(",");
					SheTuanRenYuanXinXi xinXi = null;
					XueSheng xueSheng = null;
					BanJi banJi = null;
					for (int i = 0; i < buMenRenYuans.length; i++) {
						xinXi = new SheTuanRenYuanXinXi();
						xueSheng = new XueSheng();
						banJi = new BanJi();
						xueSheng = xueShengService.getUserById(Integer.parseInt(buMenRenYuans[i]));
						banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
						xinXi.setXueshengid(xueSheng.getXueshengid());
						xinXi.setXuehao(xueSheng.getXuehao());
						xinXi.setXingming(xueSheng.getXingming());
						xinXi.setBanji(banJi.getBanjimingcheng());
						xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
						xinXi.setShoujihao(xueSheng.getDianhua());
						xinXi.setBumenid(sheTuanBuMenXinXin.getShetuanbumenxinxiid());
						if (xueShengZuZhiXinXi.getFuzeren().equals(xueSheng.getXueshengid().toString())) {
							xinXi.setZhiwu("负责人");
						}
						if (xueShengZuZhiXinXi.getBianji()!=null) {
							if (xueShengZuZhiXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
								StringBuffer sBuffer = new StringBuffer();
								if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
									sBuffer = sBuffer.append("编辑");
								}else {
										sBuffer = sBuffer.append(xinXi.getZhiwu());
										sBuffer = sBuffer.append(",编辑");
									}
									xinXi.setZhiwu(sBuffer.toString());
							}
						}
						if (sheTuanBuMenXinXin.getZhiwu()!=null) {
							String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
							for (int j = 0; j < zhiwus.length; j++) {
								String zhiwu[] =zhiwus[j].split(",");
								if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
									StringBuffer sBuffer = new StringBuffer();
									if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
										sBuffer = sBuffer.append(zhiwu[0]);
									}else {
										sBuffer = sBuffer.append(xinXi.getZhiwu());
										sBuffer = sBuffer.append(","+zhiwu[0]);
									}
									logger.info(sBuffer);
									xinXi.setZhiwu(sBuffer.toString());
								}
							}
						}
						xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
						if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
							xinXi.setZhiwu("成员");
						}
						xinXis.add(xinXi);
					}
				}
				
			}
		}
		List<SheTuanBuMenXinXin> sheTuanBuMenXinXins = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(sheTuanBuMenJiBenXinXin.getBumenid(),format.format(date));
				if (xinXin!=null) {
					xinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					sheTuanBuMenXinXins.add(xinXin);
				}
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.addObject("renyuans", xinXis);
		mView.addObject("bumen",sheTuanBuMenXinXins);
		mView.addObject("xueshengzuzhixinxiid", xueShengZuZhiXinXiID);
		mView.addObject("bumenxinxiid", bumen);
		mView.setViewName("stu/xzhidingbuzhang");
		return mView;
	}
	
	@RequestMapping(value="sshedingbz")
	@ResponseBody
	public String sheTuanSheZhiBuZhang(HttpServletRequest request,HttpServletResponse response) throws IOException{
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String sheTuanXinXiID = data[0];
		String xueShengID = data[1];
		String buMenXinXiID = data[2];
		if (!Util.isNumeric(xueShengID)||!Util.isNumeric(buMenXinXiID)||!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		logger.info(sheTuanXinXiID);
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(Integer.parseInt(buMenXinXiID));
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanBuMenXinXin.setBuzhang(xueShengID);
		if (sheTuanBuMenXinXin.getZhiwu()!=null) {
			String zhiWus[]=sheTuanBuMenXinXin.getZhiwu().split(";");
			StringBuffer sBuffer = new StringBuffer();
			for (int i = 0; i < zhiWus.length; i++) {
				if (zhiWus[i].startsWith("部长")) {
					sBuffer.append("部长,"+xueShengID+";");
				}else {
					sBuffer.append(zhiWus[i]);
				}
			}
			sheTuanBuMenXinXin.setZhiwu(sBuffer.toString());
		}else {
			sheTuanBuMenXinXin.setZhiwu("部长,"+xueShengID+";");
		}
		boolean isIn;
		if (sheTuanBuMenXinXin.getRenyuanids()!=null) {
			String sheYuanS=sheTuanBuMenXinXin.getRenyuanids();
			if (sheYuanS.startsWith(xueShengID+",")) {
				isIn=true;
			}else {
				if (sheYuanS.lastIndexOf(","+xueShengID+",")!=-1) {
					isIn=true;
				}else {
					isIn = false;
				}
			}
		}else {
			isIn = false;
		}
		if (isIn==false) {
			StringBuffer sBuffer2 = null;
			if (sheTuanBuMenXinXin.getRenyuanids()==null) {
				sBuffer2 = new StringBuffer();
			}else {
				sBuffer2 = new StringBuffer(sheTuanBuMenXinXin.getRenyuanids());
			}
			sBuffer2= sBuffer2.append(xueShengID+",");
			sheTuanBuMenXinXin.setRenyuanids(sBuffer2.toString());
			int renshu = Integer.parseInt(sheTuanBuMenXinXin.getBumenrenshu())+1;
			sheTuanBuMenXinXin.setBumenrenshu(String.valueOf(renshu));
		}
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String xueshengid1 = "%,"+xueShengID+",%";
		String xueshengid2 = xueShengID+",%";
		if (sheTuanBuMenJiBenXinXins!=null) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(new Date()), xueshengid1, xueshengid2);
				if (xin==null||xin.getShetuanbumenxinxiid()==sheTuanBuMenXinXin.getShetuanbumenxinxiid()) {
					continue;
				}else {
					String renYuanID[]=xin.getRenyuanids().split(",");
					StringBuffer renYuanS = new StringBuffer();
					for (int i = 0; i < renYuanID.length; i++) {
						if (renYuanID[i].equals(xueShengID)) {
							continue;
						}else {
							renYuanS.append(renYuanID[i]+",");
						}
					}
					xin.setRenyuanids(renYuanS.toString());
					xin.setBumenrenshu(String.valueOf(Integer.parseInt(xin.getBumenrenshu())-1));
					try {
						sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(xin);
					} catch (Exception e) {
						logger.error(e);
					}
				}
			}
		}
		String ret = "";
		int i = sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(sheTuanBuMenXinXin);
		if (i>0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueShengID));
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你被设为"+sheTuanJiBenXinXi.getMingcheng()+""+sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid()).getBumenmingcheng()+"部长");
			tiXingService.insert(tiXing);
			ret="success";
		}else {
			ret = "fail";
		}
		return ret;
	}
	
	@RequestMapping(value="xshedingbz")
	@ResponseBody
	public String xueShengZuZhiSheZhiBuZhang(HttpServletRequest request,HttpServletResponse response) throws IOException{
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String xueShengZuZhiXinXiID = data[0];
		String xueShengID = data[1];
		String buMenXinXiID = data[2];
		if (!Util.isNumeric(xueShengID)||!Util.isNumeric(buMenXinXiID)||!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		logger.info(xueShengZuZhiXinXiID);
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(Integer.parseInt(buMenXinXiID));
		sheTuanBuMenXinXin.setBuzhang(xueShengID);
		if (sheTuanBuMenXinXin.getZhiwu()!=null) {
			String zhiWus[]=sheTuanBuMenXinXin.getZhiwu().split(";");
			StringBuffer sBuffer = new StringBuffer();
			for (int i = 0; i < zhiWus.length; i++) {
				if (zhiWus[i].startsWith("部长")) {
					sBuffer.append("部长,"+xueShengID+";");
				}else {
					sBuffer.append(zhiWus[i]);
				}
			}
			sheTuanBuMenXinXin.setZhiwu(sBuffer.toString());
		}else {
			sheTuanBuMenXinXin.setZhiwu("部长,"+xueShengID+";");
		}
		boolean isIn;
		if (sheTuanBuMenXinXin.getRenyuanids()!=null) {
			String sheYuanS=sheTuanBuMenXinXin.getRenyuanids();
			if (sheYuanS.startsWith(xueShengID+",")) {
				isIn=true;
			}else {
				if (sheYuanS.lastIndexOf(","+xueShengID+",")!=-1) {
					isIn=true;
				}else {
					isIn = false;
				}
			}
		}else {
			isIn = false;
		}
		if (isIn==false) {
			StringBuffer sBuffer2 = null;
			if (sheTuanBuMenXinXin.getRenyuanids()==null) {
				sBuffer2 = new StringBuffer();
			}else {
				sBuffer2 = new StringBuffer(sheTuanBuMenXinXin.getRenyuanids());
			}
			sBuffer2= sBuffer2.append(xueShengID+",");
			sheTuanBuMenXinXin.setRenyuanids(sBuffer2.toString());
			int renshu = Integer.parseInt(sheTuanBuMenXinXin.getBumenrenshu())+1;
			sheTuanBuMenXinXin.setBumenrenshu(String.valueOf(renshu));
		}
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String xueshengid1 = "%,"+xueShengID+",%";
		String xueshengid2 = xueShengID+",%";
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(new Date()), xueshengid1, xueshengid2);
				if (xin==null||xin.getShetuanbumenxinxiid()==sheTuanBuMenXinXin.getShetuanbumenxinxiid()) {
					continue;
				}else {
					String renYuanID[]=xin.getRenyuanids().split(",");
					StringBuffer renYuanS = new StringBuffer();
					for (int i = 0; i < renYuanID.length; i++) {
						if (renYuanID[i].equals(xueShengID)) {
							continue;
						}else {
							renYuanS.append(renYuanID[i]+",");
						}
					}
					try {
						sheTuanService.updateSheTuanBuMenRenYuanByBuMenXinXiID(xin.getShetuanbumenxinxiid(),renYuanS.toString());
					} catch (Exception e) {
						logger.error(e);
					}
				}
			}
		}
		String ret = "";
		int i = sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(sheTuanBuMenXinXin);
		if (i>0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueShengID));
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你被设为"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid()).getMingcheng()+""+sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid()).getBumenmingcheng()+"部长");
			tiXingService.insert(tiXing);
			ret="success";
		}else {
			ret = "fail";
		}
		return ret;
	}
	
	@RequestMapping(value="sckby")
	public ModelAndView sheTuanChaKanBuMenRenYuan(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String sheTuanXinXiID=request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiID(Integer.parseInt(sheTuanXinXiID));
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		Date date=new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		SheTuanBuMenXinXin xinXi = null;
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
			SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
			if (sheTuanBuMenXinXin.getBuzhang()==null) {
				continue;
			}
			if (sheTuanBuMenXinXin.getBuzhang().equals(user.getXueshengid().toString())) {
				xinXi = sheTuanBuMenXinXin;
			}
		}
		if (xinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		List<SheTuanRenYuanXinXi> renyuans = new ArrayList<>();
		String renYuans[] = xinXi.getRenyuanids().split(",");
		SheTuanRenYuanXinXi xin = null;
		XueSheng xueSheng = null;
		BanJi banJi = null;
		for (int i = 0; i < renYuans.length; i++) {
			xin = new SheTuanRenYuanXinXi();
			xueSheng = new XueSheng();
			banJi = new BanJi();
			xueSheng = xueShengService.getUserById(Integer.parseInt(renYuans[i]));
			banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
			xin.setXueshengid(xueSheng.getXueshengid());
			xin.setXuehao(xueSheng.getXuehao());
			xin.setXingming(xueSheng.getXingming());
			xin.setBanji(banJi.getBanjimingcheng());
			xin.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
			xin.setShoujihao(xueSheng.getDianhua());
			xin.setBumenid(xinXi.getShetuanbumenxinxiid());
			if (sheTuanXinXi.getShezhang().equals(xueSheng.getXueshengid().toString())) {
				xin.setZhiwu("社长");
			}
			if (sheTuanXinXi.getBianji()!=null) {
				if (sheTuanXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xin.getZhiwu()==null||xin.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					}else {
							sBuffer = sBuffer.append(xin.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xin.setZhiwu(sBuffer.toString());
				}
			}
			String zhiwus[] = xinXi.getZhiwu().split(";");
			for (int j = 0; j < zhiwus.length; j++) {
				String zhiwu[] =zhiwus[j].split(",");
				if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xin.getZhiwu()==null||xin.getZhiwu().equals("")) {
						sBuffer = sBuffer.append(zhiwu[0]);
					}else {
						sBuffer = sBuffer.append(xin.getZhiwu());
						sBuffer = sBuffer.append(","+zhiwu[0]);
					}
					logger.info(sBuffer);
					xin.setZhiwu(sBuffer.toString());
				}
			}
			SheTuanBuMenJiBenXinXin sBuMenJiBenXinXin = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(xinXi.getBumenid());
			xin.setBumen(sBuMenJiBenXinXin.getBumenmingcheng());
			if (xin.getZhiwu()==null||xin.getZhiwu().equals("")) {
				xin.setZhiwu("成员");
			}
			renyuans.add(xin);
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/bumenrenyuanxinxi");
		mView.addObject("renyuans",renyuans);
		return mView;
	}
	
	@RequestMapping(value="xckby")
	public ModelAndView xueShengZuZhiChaKanBuMenRenYuan(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String xueShengZuZhiXinXiID=request.getParameter("id");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		Date date=new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		SheTuanBuMenXinXin xinXi = null;
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
				if (sheTuanBuMenXinXin.getBuzhang()==null) {
					continue;
				}
				if (sheTuanBuMenXinXin.getBuzhang().equals(user.getXueshengid().toString())) {
					xinXi = sheTuanBuMenXinXin;
				}
			}
		}
		
		if (xinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		List<SheTuanRenYuanXinXi> renyuans = new ArrayList<>();
		String renYuans[] = xinXi.getRenyuanids().split(",");
		SheTuanRenYuanXinXi xin = null;
		XueSheng xueSheng = null;
		BanJi banJi = null;
		for (int i = 0; i < renYuans.length; i++) {
			xin = new SheTuanRenYuanXinXi();
			xueSheng = new XueSheng();
			banJi = new BanJi();
			xueSheng = xueShengService.getUserById(Integer.parseInt(renYuans[i]));
			banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
			xin.setXueshengid(xueSheng.getXueshengid());
			xin.setXuehao(xueSheng.getXuehao());
			xin.setXingming(xueSheng.getXingming());
			xin.setBanji(banJi.getBanjimingcheng());
			xin.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
			xin.setShoujihao(xueSheng.getDianhua());
			xin.setBumenid(xinXi.getShetuanbumenxinxiid());
			if (xueShengZuZhiXinXi.getFuzeren().equals(xueSheng.getXueshengid().toString())) {
				xin.setZhiwu("负责人");
			}
			if (xueShengZuZhiXinXi.getBianji()!=null) {
				if (xueShengZuZhiXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xin.getZhiwu()==null||xin.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					}else {
							sBuffer = sBuffer.append(xin.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xin.setZhiwu(sBuffer.toString());
				}
			}
			String zhiwus[] = xinXi.getZhiwu().split(";");
			for (int j = 0; j < zhiwus.length; j++) {
				String zhiwu[] =zhiwus[j].split(",");
				if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xin.getZhiwu()==null||xin.getZhiwu().equals("")) {
						sBuffer = sBuffer.append(zhiwu[0]);
					}else {
						sBuffer = sBuffer.append(xin.getZhiwu());
						sBuffer = sBuffer.append(","+zhiwu[0]);
					}
					logger.info(sBuffer);
					xin.setZhiwu(sBuffer.toString());
				}
			}
			SheTuanBuMenJiBenXinXin sBuMenJiBenXinXin = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(xinXi.getBumenid());
			xin.setBumen(sBuMenJiBenXinXin.getBumenmingcheng());
			if (xin.getZhiwu()==null||xin.getZhiwu().equals("")) {
				xin.setZhiwu("成员");
			}
			renyuans.add(xin);
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/bumenrenyuanxinxi");
		mView.addObject("renyuans",renyuans);
		return mView;
	}
	
	@RequestMapping(value="szsbm")
	public ModelAndView sheTuanZengShanBuMen(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String sheTuanXinXiID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng)request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		ModelAndView mView = new ModelAndView();
		mView.addObject("shetuanxinxiid", sheTuanXinXiID);
		mView.addObject("shetuanid",sheTuanXinXi.getShetuanid());
		mView.addObject("bumen", sheTuanBuMenJiBenXinXins);
		mView.setViewName("stu/szengshanbumen");
		return mView;
	}
	
	@RequestMapping(value="saddbumen")
	public ModelAndView addBuMen(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String sheTuanXinXiID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiID(Integer.parseInt(sheTuanXinXiID));
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanXinXi.setMingcheng(sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid()).getMingcheng());
		ModelAndView mView = new ModelAndView();
		mView.addObject("shetuan", sheTuanXinXi);
		mView.setViewName("stu/saddbumen");
		return mView;
	}
	
	@RequestMapping(value="xaddbumen")
	public ModelAndView xueShengZuZhiAddBuMen(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String xueShengZuZhiXinXiID = request.getParameter("id");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		xueShengZuZhiXinXi.setMingcheng(sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid()).getMingcheng());
		ModelAndView mView = new ModelAndView();
		mView.addObject("xueshengzuzhi", xueShengZuZhiXinXi);
		mView.setViewName("stu/xaddbumen");
		return mView;
	}

	@RequestMapping(value="subsaddbumen")
	@ResponseBody
	public String subAddBuMen(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String date[]=request.getParameter("CODE").split(",zytech,");
		String sheTuanXinXiID = date[0];
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanIDAndMingCheng(sheTuanXinXi.getShetuanid(), date[1]);
		String ret = "";
		if (sheTuanBuMenJiBenXinXin!=null) {
			ret = "same";
		}else {
			SheTuanBuMenJiBenXinXin xin = new SheTuanBuMenJiBenXinXin();
			xin.setShetuanid(sheTuanXinXi.getShetuanid());
			xin.setBumenmingcheng(date[1]);
			xin.setZhize(date[2]);
			xin.setZhuangtai("1");
			xin.setChuangjianshijian(new Date());
			int i = sheTuanService.insertSheTuanBuMenJiBenXinXi(xin);
			if (i>0) {
				SimpleDateFormat format =new SimpleDateFormat("yyyy"); 
				SheTuanBuMenXinXin sheTuanBuMenXinXin = new SheTuanBuMenXinXin();
				sheTuanBuMenXinXin.setBumenid(xin.getBumenid());
				sheTuanBuMenXinXin.setNiandu(Integer.parseInt(format.format(new Date())));
				int j = sheTuanService.insertSheTuanBuMenXinXi(sheTuanBuMenXinXin);
				if (j>0) {
					ret = "success";
				}else {
					ret = "fail";
				}
			}else {
				ret = "fail";
			}
		}
		return ret;
	}
	
	@RequestMapping(value="subxaddbumen")
	@ResponseBody
	public String subXueShengZuZhiAddBuMen(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String date[]=request.getParameter("CODE").split(",zytech,");
		String xueShengZuZhiXinXiID = date[0];
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin = sheTuanService.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiIDAndMingCheng(xueShengZuZhiXinXi.getXueshengzuzhiid(), date[1]);
		String ret = "";
		if (sheTuanBuMenJiBenXinXin!=null) {
			ret = "same";
		}else {
			SheTuanBuMenJiBenXinXin xin = new SheTuanBuMenJiBenXinXin();
			xin.setXueshengzuzhiid(xueShengZuZhiXinXi.getXueshengzuzhiid());
			xin.setBumenmingcheng(date[1]);
			xin.setZhize(date[2]);
			xin.setZhuangtai("1");
			xin.setChuangjianshijian(new Date());
			int i = sheTuanService.insertSheTuanBuMenJiBenXinXi(xin);
			if (i>0) {
				SimpleDateFormat format =new SimpleDateFormat("yyyy"); 
				SheTuanBuMenXinXin sheTuanBuMenXinXin = new SheTuanBuMenXinXin();
				sheTuanBuMenXinXin.setBumenid(xin.getBumenid());
				sheTuanBuMenXinXin.setNiandu(Integer.parseInt(format.format(new Date())));
				int j = sheTuanService.insertSheTuanBuMenXinXi(sheTuanBuMenXinXin);
				if (j>0) {
					ret = "success";
				}else {
					ret = "fail";
				}
			}else {
				ret = "fail";
			}
		}
		return ret;
	}
	@RequestMapping(value="sdelbumen")
	@ResponseBody
	public String delBuMen(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		if (!Util.isNumeric(data[0])||!Util.isNumeric(data[1])) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(data[0]), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		int i = sheTuanService.updateSheTuanBuMenJiBenXinXiZhuangTaiByBuMenID(Integer.parseInt(data[1]));
		String ret="";
		if (i>0) {
			ret="success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="xdelbumen")
	@ResponseBody
	public String xueShengZuZhiDelBuMen(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		if (!Util.isNumeric(data[0])||!Util.isNumeric(data[1])) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(data[0]));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		int i = sheTuanService.updateSheTuanBuMenJiBenXinXiZhuangTaiByBuMenID(Integer.parseInt(data[1]));
		String ret="";
		if (i>0) {
			ret="success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value = "xzsbm")
	public ModelAndView xueShengZuZhiZengShanBuMen(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String xueShengZuZhiXinXiID = request.getParameter("id");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng)request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		ModelAndView mView = new ModelAndView();
		mView.addObject("xueshengzuzhixinxiid", xueShengZuZhiXinXiID);
		mView.addObject("xueshengzuzhiid",xueShengZuZhiXinXi.getXueshengzuzhiid());
		mView.addObject("bumen", sheTuanBuMenJiBenXinXins);
		mView.setViewName("stu/xzengshanbumen");
		return mView;
	}
	
	@RequestMapping(value="stc")
	@ResponseBody
	public String sheTuanTuiChu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String ret = "";
		XueSheng user = (XueSheng)request.getSession().getAttribute("user");
		String xueshengid1 = "%,"+user.getXueshengid()+",%";
		String xueshengid2 = user.getXueshengid()+",%";
		String sheTuanXinXiID = request.getParameter("CODE");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			logger.error("社团信息ID："+sheTuanXinXiID);
			response.sendRedirect("logout");
			return null;
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndXueShengID(Integer.parseInt(sheTuanXinXiID), xueshengid1,xueshengid2);
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanBuMenXinXin sheTuanBuMenXinXin=null;
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xinXi = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(new Date()), xueshengid1, xueshengid2);
				if (xinXi!=null) {
					sheTuanBuMenXinXin = xinXi;
				}
			}
		}
		if (sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			ret = "havejob";
		}else {
			if (sheTuanBuMenXinXin!=null) {
				if (sheTuanBuMenXinXin.getBuzhang().equals(user.getXueshengid().toString())) {
					ret = "havejob";
				}else {
					String buMenRenYuan[] = sheTuanBuMenXinXin.getRenyuanids().split(",");
					StringBuffer sBuffer = new StringBuffer();
					for (int i = 0; i < buMenRenYuan.length; i++) {
						if (buMenRenYuan[i].equals(user.getXueshengid().toString())) {
							continue;
						}else {
							sBuffer.append(buMenRenYuan[i]+",");
						}
					}
					sheTuanBuMenXinXin.setRenyuanids(sBuffer.toString());
					sheTuanBuMenXinXin.setBumenrenshu(String.valueOf(Integer.parseInt(sheTuanBuMenXinXin.getBumenrenshu())-1));
					int j = sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(sheTuanBuMenXinXin);
					if (j>0) {
						String sheYuanIDs[] = sheTuanXinXi.getSheyuanids().split(",");
						StringBuffer sBuffer2 = new StringBuffer();
						for (int i = 0; i < sheYuanIDs.length; i++) {
							if (sheYuanIDs[i].equals(user.getXueshengid().toString())) {
								continue;
							}else {
								sBuffer2.append(sheYuanIDs[i]+",");
							}
						}
						sheTuanXinXi.setSheyuanids(sBuffer2.toString());
						sheTuanXinXi.setSheyuanrenshu(sheTuanXinXi.getSheyuanrenshu()-1);
						int k = sheTuanService.updateSheTuanXinXi(sheTuanXinXi);
						if (k>0) {
							ret = "success";
						}else {
							ret= "fail";
						}
					}else {
						ret= "fail";
					}
				}
			}else {
				String sheYuanIDs[] = sheTuanXinXi.getSheyuanids().split(",");
				StringBuffer sBuffer2 = new StringBuffer();
				for (int i = 0; i < sheYuanIDs.length; i++) {
					if (sheYuanIDs[i].equals(user.getXueshengid().toString())) {
						continue;
					}else {
						sBuffer2.append(sheYuanIDs[i]+",");
					}
				}
				sheTuanXinXi.setSheyuanids(sBuffer2.toString());
				sheTuanXinXi.setSheyuanrenshu(sheTuanXinXi.getSheyuanrenshu()-1);
				int k = sheTuanService.updateSheTuanXinXi(sheTuanXinXi);
				if (k>0) {
					ret = "success";
				}else {
					ret= "fail";
				}
			}
		}
		return ret;
	}
	
	@RequestMapping(value="xtc")
	@ResponseBody
	public String xueShengZuZhiTuiChu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String ret = "";
		XueSheng user = (XueSheng)request.getSession().getAttribute("user");
		String xueshengid1 = "%,"+user.getXueshengid()+",%";
		String xueshengid2 = user.getXueshengid()+",%";
		String xueShengZuZhiXinXiID = request.getParameter("CODE");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			logger.error("社团信息ID："+xueShengZuZhiXinXiID);
			response.sendRedirect("logout");
			return null;
		}
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectXueShengZuZhiXinXiByIDAndXueShengID(Integer.parseInt(xueShengZuZhiXinXiID), xueshengid1, xueshengid2);
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanBuMenXinXin sheTuanBuMenXinXin=null;
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xinXi = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(new Date()), xueshengid1, xueshengid2);
				if (xinXi!=null) {
					sheTuanBuMenXinXin = xinXi;
				}
			}
		}
		if (xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			ret = "havejob";
		}else {
			if (sheTuanBuMenXinXin!=null) {
				if (sheTuanBuMenXinXin.getBuzhang().equals(user.getXueshengid().toString())) {
					ret = "havejob";
				}else {
					String buMenRenYuan[] = sheTuanBuMenXinXin.getRenyuanids().split(",");
					StringBuffer sBuffer = new StringBuffer();
					for (int i = 0; i < buMenRenYuan.length; i++) {
						if (buMenRenYuan[i].equals(user.getXueshengid().toString())) {
							continue;
						}else {
							sBuffer.append(buMenRenYuan[i]+",");
						}
					}
					sheTuanBuMenXinXin.setRenyuanids(sBuffer.toString());
					sheTuanBuMenXinXin.setBumenrenshu(String.valueOf(Integer.parseInt(sheTuanBuMenXinXin.getBumenrenshu())-1));
					int j = sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(sheTuanBuMenXinXin);
					if (j>0) {
						String sheYuanIDs[] = xueShengZuZhiXinXi.getRenyuanids().split(",");
						StringBuffer sBuffer2 = new StringBuffer();
						for (int i = 0; i < sheYuanIDs.length; i++) {
							if (sheYuanIDs[i].equals(user.getXueshengid().toString())) {
								continue;
							}else {
								sBuffer2.append(sheYuanIDs[i]+",");
							}
						}
						xueShengZuZhiXinXi.setRenyuanids(sBuffer2.toString());
						xueShengZuZhiXinXi.setRenshu(xueShengZuZhiXinXi.getRenshu()-1);
						int k = sheTuanService.updateXueShengZuZhiXinXi(xueShengZuZhiXinXi);
						if (k>0) {
							ret = "success";
						}else {
							ret= "fail";
						}
					}else {
						ret= "fail";
					}
				}
			}else {
				String sheYuanIDs[] = xueShengZuZhiXinXi.getRenyuanids().split(",");
				StringBuffer sBuffer2 = new StringBuffer();
				for (int i = 0; i < sheYuanIDs.length; i++) {
					if (sheYuanIDs[i].equals(user.getXueshengid().toString())) {
						continue;
					}else {
						sBuffer2.append(sheYuanIDs[i]+",");
					}
				}
				xueShengZuZhiXinXi.setRenyuanids(sBuffer2.toString());
				xueShengZuZhiXinXi.setRenshu(xueShengZuZhiXinXi.getRenshu()-1);
				int k = sheTuanService.updateXueShengZuZhiXinXi(xueShengZuZhiXinXi);
				if (k>0) {
					ret = "success";
				}else {
					ret= "fail";
				}
			}
		}
		return ret;
	}
	
	@RequestMapping(value="sjs")
	public ModelAndView sheTuanJieSan(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String sheTuanXinXiID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiID(Integer.parseInt(sheTuanXinXiID));
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanXinXi.setMingcheng(sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid()).getMingcheng());
		SheTuanJieSan sheTuanJieSan = sheTuanService.selectSheTuanJieSanBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJieSan!=null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.print("<script>alert('已提交申请，请等待审核！');</script>");
			out.print("<script>location='wodeshetuan';</script>");
			return null;
		}
		ModelAndView mView = new ModelAndView();
		mView.addObject("shetuan", sheTuanXinXi);
		mView.setViewName("stu/sjiesan");
		return mView;
	}
	
	@RequestMapping(value="subsjiesan")
	@ResponseBody
	public String subSheTuanJieSan(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String date[]=request.getParameter("CODE").split(",zytech,");
		String sheTuanXinXiID = date[0];
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		String ret ="";
		SheTuanJieSan sheTuanJieSan = new SheTuanJieSan();
		sheTuanJieSan.setFuzeren(user.getXueshengid());
		sheTuanJieSan.setJiesanyuanyin(date[1]);
		sheTuanJieSan.setShetuanid(sheTuanXinXi.getShetuanid());
		sheTuanJieSan.setShifoupizhun(0);
		int i = sheTuanService.insertSheTuanJieSan(sheTuanJieSan);
		if (i>0) {
			ret = "success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="syrbm")
	@ResponseBody
	public String sheTuanRenYuanYiRuBuMen(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		String data[]=request.getParameter("CODE").split(",zytech,");
		String xueShengID = data[0];
		String sheTuanXinXiID = data[1];
		String buMenXinXiID = data[2];
		if (!Util.isNumeric(xueShengID)||!Util.isNumeric(sheTuanXinXiID)||!Util.isNumeric(buMenXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String xueshengid1 = "%,"+xueShengID+",%";
		String xueshengid2 = xueShengID+",%";
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(new Date()), xueshengid1, xueshengid2);
				if (xin==null||xin.getShetuanbumenxinxiid()==Integer.parseInt(buMenXinXiID)) {
					continue;
				}else {
					if (xin.getRenyuanids()!=null) {
						String renYuanIDs[] = xin.getRenyuanids().split(",");
						StringBuffer sBuffer = new StringBuffer();
						for (int i = 0; i < renYuanIDs.length; i++) {
							if (renYuanIDs[i].equals(xueShengID)) {
								continue;
							}else {
								sBuffer.append(renYuanIDs[i]+",");
							}
						}
						xin.setRenyuanids(sBuffer.toString());
						xin.setBumenrenshu(String.valueOf(Integer.parseInt(xin.getBumenrenshu())-1));
						try {
							sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(xin);
						} catch (Exception e) {
							logger.error(e);
						}
					}
				}
			}
		}
		SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(Integer.parseInt(buMenXinXiID));
		StringBuffer sBuffer = null;
		if (sheTuanBuMenXinXin.getRenyuanids()!=null) {
			sBuffer = new StringBuffer(sheTuanBuMenXinXin.getRenyuanids());
		}else {
			sBuffer = new StringBuffer();
		}
		sBuffer.append(xueShengID+",");
		sheTuanBuMenXinXin.setRenyuanids(sBuffer.toString());
		sheTuanBuMenXinXin.setBumenrenshu(String.valueOf(Integer.parseInt(sheTuanBuMenXinXin.getBumenrenshu())+1));
		int i = sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(sheTuanBuMenXinXin);
		String ret="";
		if (i>0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueShengID));
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你被移入"+sheTuanJiBenXinXi.getMingcheng()+""+sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid()).getBumenmingcheng());
			tiXingService.insert(tiXing);
			ret="success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="xyrbm")
	@ResponseBody
	public String xueShengZuZhiRenYuanYiRuBuMen(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		String data[]=request.getParameter("CODE").split(",zytech,");
		String xueShengID = data[0];
		String xueShengZuZhiXinXiID = data[1];
		String buMenXinXiID = data[2];
		if (!Util.isNumeric(xueShengID)||!Util.isNumeric(xueShengZuZhiXinXiID)||!Util.isNumeric(buMenXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String xueshengid1 = "%,"+xueShengID+",%";
		String xueshengid2 = xueShengID+",%";
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(new Date()), xueshengid1, xueshengid2);
				if (xin==null||xin.getShetuanbumenxinxiid()==Integer.parseInt(buMenXinXiID)) {
					continue;
				}else {
					if (xin.getRenyuanids()!=null) {
						String renYuanIDs[] = xin.getRenyuanids().split(",");
						StringBuffer sBuffer = new StringBuffer();
						for (int i = 0; i < renYuanIDs.length; i++) {
							if (renYuanIDs[i].equals(xueShengID)) {
								continue;
							}else {
								sBuffer.append(renYuanIDs[i]+",");
							}
						}
						xin.setRenyuanids(sBuffer.toString());
						xin.setBumenrenshu(String.valueOf(Integer.parseInt(xin.getBumenrenshu())-1));
						try {
							sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(xin);
						} catch (Exception e) {
							logger.error(e);
						}
					}
				}
			}
		}
		SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(Integer.parseInt(buMenXinXiID));
		StringBuffer sBuffer = null;
		if (sheTuanBuMenXinXin.getRenyuanids()!=null) {
			sBuffer = new StringBuffer(sheTuanBuMenXinXin.getRenyuanids());
		}else {
			sBuffer = new StringBuffer();
		}
		sBuffer.append(xueShengID+",");
		sheTuanBuMenXinXin.setRenyuanids(sBuffer.toString());
		sheTuanBuMenXinXin.setBumenrenshu(String.valueOf(Integer.parseInt(sheTuanBuMenXinXin.getBumenrenshu())+1));
		int i = sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(sheTuanBuMenXinXin);
		String ret="";
		if (i>0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueShengID));
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你被移入"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid()).getMingcheng()+""+sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid()).getBumenmingcheng());
			tiXingService.insert(tiXing);
			ret="success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="szdbj")
	public ModelAndView sheTuanZhiDingBianJi(HttpServletRequest request ,HttpServletResponse response ) throws IOException{
		String sheTuanXinXiID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		String sheTuanRenYuanIDs[] = sheTuanXinXi.getSheyuanids().split(",");
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		SheTuanRenYuanXinXi xinXi = null;
		XueSheng xueSheng = null;
		BanJi banJi = null;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		for (int i = 0; i < sheTuanRenYuanIDs.length; i++) {
			xinXi = new SheTuanRenYuanXinXi();
			xueSheng = new XueSheng();
			banJi = new BanJi();
			xueSheng = xueShengService.getUserById(Integer.parseInt(sheTuanRenYuanIDs[i]));
			banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
			xinXi.setXueshengid(xueSheng.getXueshengid());
			xinXi.setXuehao(xueSheng.getXuehao());
			xinXi.setXingming(xueSheng.getXingming());
			xinXi.setBanji(banJi.getBanjimingcheng());
			xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
			xinXi.setShoujihao(xueSheng.getDianhua());
			if (sheTuanXinXi.getShezhang().equals(xueSheng.getXueshengid().toString())) {
				xinXi.setZhiwu("社长");
			}
			if (sheTuanXinXi.getBianji()!=null) {
				if (sheTuanXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					}else {
							sBuffer = sBuffer.append(xinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xinXi.setZhiwu(sBuffer.toString());
				}
			}
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%", xueSheng.getXueshengid().toString() + ",%");
				if (sheTuanBuMenXinXin!=null) {
					if (sheTuanBuMenXinXin.getZhiwu()!=null) {
						String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
						for (int j = 0; j < zhiwus.length; j++) {
							String zhiwu[] =zhiwus[j].split(",");
							if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
								StringBuffer sBuffer = new StringBuffer();
								if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
									sBuffer = sBuffer.append(zhiwu[0]);
								}else {
									sBuffer = sBuffer.append(xinXi.getZhiwu());
									sBuffer = sBuffer.append(","+zhiwu[0]);
								}
								logger.info(sBuffer);
								xinXi.setZhiwu(sBuffer.toString());
							}
						}
					}
					xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
				}
			}
			if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
				xinXi.setZhiwu("成员");
			}
			xinXis.add(xinXi);
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/szhidingbianji");
		mView.addObject("renyuans", xinXis);
		mView.addObject("shetuanxinxiid", sheTuanXinXiID);
		return mView;
	}
	
	@RequestMapping(value="xzdbj")
	public ModelAndView xueShengZuZhiZhiDingBianJi(HttpServletRequest request ,HttpServletResponse response ) throws IOException{
		String xueShengZuZhiXinXiID = request.getParameter("id");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		String RenYuanIDs[] = xueShengZuZhiXinXi.getRenyuanids().split(",");
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		SheTuanRenYuanXinXi xinXi = null;
		XueSheng xueSheng = null;
		BanJi banJi = null;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		for (int i = 0; i < RenYuanIDs.length; i++) {
			xinXi = new SheTuanRenYuanXinXi();
			xueSheng = new XueSheng();
			banJi = new BanJi();
			xueSheng = xueShengService.getUserById(Integer.parseInt(RenYuanIDs[i]));
			banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
			xinXi.setXueshengid(xueSheng.getXueshengid());
			xinXi.setXuehao(xueSheng.getXuehao());
			xinXi.setXingming(xueSheng.getXingming());
			xinXi.setBanji(banJi.getBanjimingcheng());
			xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
			xinXi.setShoujihao(xueSheng.getDianhua());
			if (xueShengZuZhiXinXi.getFuzeren().equals(xueSheng.getXueshengid().toString())) {
				xinXi.setZhiwu("负责人");
			}
			if (xueShengZuZhiXinXi.getBianji()!=null) {
				if (xueShengZuZhiXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					}else {
							sBuffer = sBuffer.append(xinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xinXi.setZhiwu(sBuffer.toString());
				}
			}
			if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
				for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
					SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%", xueSheng.getXueshengid().toString() + ",%");
					if (sheTuanBuMenXinXin!=null) {
						if (sheTuanBuMenXinXin.getZhiwu()!=null) {
							String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
							for (int j = 0; j < zhiwus.length; j++) {
								String zhiwu[] =zhiwus[j].split(",");
								if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
									StringBuffer sBuffer = new StringBuffer();
									if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
										sBuffer = sBuffer.append(zhiwu[0]);
									}else {
										sBuffer = sBuffer.append(xinXi.getZhiwu());
										sBuffer = sBuffer.append(","+zhiwu[0]);
									}
									logger.info(sBuffer);
									xinXi.setZhiwu(sBuffer.toString());
								}
							}
						}
						xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					}
				}
			}
			if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
				xinXi.setZhiwu("成员");
			}
			xinXis.add(xinXi);
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/xzhidingbianji");
		mView.addObject("renyuans", xinXis);
		mView.addObject("xueshengzuzhixinxiid", xueShengZuZhiXinXiID);
		return mView;
	}
	
	@RequestMapping(value="ssdbj")
	@ResponseBody
	public String sheTuanSheDingBianJi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String data[]=request.getParameter("CODE").split(",zytech,");
		String sheTuanXinXiID = data[1];
		String xueShengID = data[0];
		if (!Util.isNumeric(sheTuanXinXiID)||!Util.isNumeric(xueShengID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng)request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		String xueshengid1 = "%,"+xueShengID+",%";
		String xueshengid2 = xueShengID+",%";
		SheTuanXinXi xinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndXueShengID(Integer.parseInt(sheTuanXinXiID), xueshengid1, xueshengid2);
		if (xinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanXinXi.setBianji(xueShengID);
		int i = sheTuanService.updateSheTuanXinXi(sheTuanXinXi);
		String ret="";		
		if (i>0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueShengID));
			tiXing.setShijian(new Date());
			tiXing.setZhuangtai(0);
			tiXing.setNeirong("你被设定为"+sheTuanJiBenXinXi.getMingcheng()+"编辑");
			tiXingService.insert(tiXing);
			ret="success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="xsdbj")
	@ResponseBody
	public String xueShengZuZhiSheDingBianJi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String data[]=request.getParameter("CODE").split(",zytech,");
		String xueShengZuZhiXinXiID = data[1];
		String xueShengID = data[0];
		if (!Util.isNumeric(xueShengZuZhiXinXiID)||!Util.isNumeric(xueShengID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		String xueshengid1 = "%,"+xueShengID+",%";
		String xueshengid2 = xueShengID+",%";
		XueShengZuZhiXinXi xinXi = sheTuanService.selectXueShengZuZhiXinXiByIDAndXueShengID(xueShengZuZhiXinXi.getXueshengzuzhixinxiid(), xueshengid1, xueshengid2);
		if (xinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		xueShengZuZhiXinXi.setBianji(xueShengID);
		int i = sheTuanService.updateXueShengZuZhiXinXi(xueShengZuZhiXinXi);
		String ret="";		
		if (i>0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueShengID));
			tiXing.setShijian(new Date());
			tiXing.setZhuangtai(0);
			tiXing.setNeirong("你被设定为"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid()).getMingcheng()+"编辑");
			tiXingService.insert(tiXing);
			ret="success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="skfbm")
	@ResponseBody
	public String sheTuanKaiFangBaoMing(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String sheTuanXinXiID = request.getParameter("CODE");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng)request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiID(Integer.parseInt(sheTuanXinXiID));
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService.selectNaXinJiBenXinXiBySheTuanXinXiID(sheTuanXinXi.getShetuanxinxiid());
		if (naXinJiBenXinXi==null) {
			NaXinJiBenXinXi nJiBenXinXi = new NaXinJiBenXinXi();
			nJiBenXinXi.setShetuanxinxiid(sheTuanXinXi.getShetuanxinxiid());
			nJiBenXinXi.setZhuangtai(true);
			int i = sheTuanService.insertNaXinJiBenXinXi(nJiBenXinXi);
			if (i>0) {
				return "success";
			}else {
				return "fail";
			}
		}else {
			if (naXinJiBenXinXi.getZhuangtai()!=false) {
				response.sendRedirect("logout");
				return null;
			}else {
				naXinJiBenXinXi.setZhuangtai(true);
				int i = sheTuanService.updateNaXinJiBenXinXi(naXinJiBenXinXi);
				if (i>0) {
					return "success";
				}else {
					return "fail";
				}
			}
		}
	}
	
	@RequestMapping(value="xkfbm")
	@ResponseBody
	public String xueShengZuZhiKaiFangBaoMing(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String xueShengZuZhiXinXiID = request.getParameter("CODE");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng)request.getSession().getAttribute("user");
		XueShengZuZhiXinXi	 xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService.selectNaXinJiBenXinXiByXueShengZuZhiXinXiID(xueShengZuZhiXinXi.getXueshengzuzhixinxiid());
		if (naXinJiBenXinXi==null) {
			NaXinJiBenXinXi nJiBenXinXi = new NaXinJiBenXinXi();
			nJiBenXinXi.setXueshengzuzhiid(xueShengZuZhiXinXi.getXueshengzuzhixinxiid());
			nJiBenXinXi.setZhuangtai(true);
			int i = sheTuanService.insertNaXinJiBenXinXi(nJiBenXinXi);
			if (i>0) {
				return "success";
			}else {
				return "fail";
			}
		}else {
			if (naXinJiBenXinXi.getZhuangtai()!=false) {
				response.sendRedirect("logout");
				return null;
			}else {
				naXinJiBenXinXi.setZhuangtai(true);
				int i = sheTuanService.updateNaXinJiBenXinXi(naXinJiBenXinXi);
				if (i>0) {
					return "success";
				}else {
					return "fail";
				}
			}
		}
		
	}
	
	@RequestMapping(value="sgbbm")
	@ResponseBody
	public String sheTuanGuanBiBaoMing(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String sheTuanXinXiID = request.getParameter("CODE");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng)request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiID(Integer.parseInt(sheTuanXinXiID));
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService.selectNaXinJiBenXinXiBySheTuanXinXiID(sheTuanXinXi.getShetuanxinxiid());
		if (naXinJiBenXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (naXinJiBenXinXi.getZhuangtai()!=true) {
			response.sendRedirect("logout");
			return null;
		}else {
				naXinJiBenXinXi.setZhuangtai(false);
				int i = sheTuanService.updateNaXinJiBenXinXi(naXinJiBenXinXi);
				if (i>0) {
					return "success";
				}else {
					return "fail";
				}
			}
		}
	
	@RequestMapping(value="xgbbm")
	@ResponseBody
	public String xueShengZuZhiGuanBiBaoMing(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String xueShengZuZhiXinXiID = request.getParameter("CODE");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng)request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService.selectNaXinJiBenXinXiByXueShengZuZhiXinXiID(xueShengZuZhiXinXi.getXueshengzuzhixinxiid());
		if (naXinJiBenXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (naXinJiBenXinXi.getZhuangtai()!=true) {
			response.sendRedirect("logout");
			return null;
		}else {
				naXinJiBenXinXi.setZhuangtai(false);
				int i = sheTuanService.updateNaXinJiBenXinXi(naXinJiBenXinXi);
				if (i>0) {
					return "success";
				}else {
					return "fail";
				}
			}
		}
	
	@RequestMapping(value="shjxxwh")
	public ModelAndView SheTuanHuanJieXinXiWeiHu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String sheTuanXinXiID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		String sheTuanRenYuanIDs[] = sheTuanXinXi.getSheyuanids().split(",");
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		SheTuanRenYuanXinXi xinXi = null;
		XueSheng xueSheng = null;
		BanJi banJi = null;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		for (int i = 0; i < sheTuanRenYuanIDs.length; i++) {
			xinXi = new SheTuanRenYuanXinXi();
			xueSheng = new XueSheng();
			banJi = new BanJi();
			xueSheng = xueShengService.getUserById(Integer.parseInt(sheTuanRenYuanIDs[i]));
			banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
			xinXi.setXueshengid(xueSheng.getXueshengid());
			xinXi.setXuehao(xueSheng.getXuehao());
			xinXi.setXingming(xueSheng.getXingming());
			xinXi.setBanji(banJi.getBanjimingcheng());
			xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
			xinXi.setShoujihao(xueSheng.getDianhua());
			if (sheTuanXinXi.getShezhang().equals(xueSheng.getXueshengid().toString())) {
				xinXi.setZhiwu("社长");
			}
			if (sheTuanXinXi.getBianji()!=null) {
				if (sheTuanXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					}else {
							sBuffer = sBuffer.append(xinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xinXi.setZhiwu(sBuffer.toString());
				}
			}
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%", xueSheng.getXueshengid().toString() + ",%");
				if (sheTuanBuMenXinXin!=null) {
					if (sheTuanBuMenXinXin.getZhiwu()!=null) {
						String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
						for (int j = 0; j < zhiwus.length; j++) {
							String zhiwu[] =zhiwus[j].split(",");
							if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
								StringBuffer sBuffer = new StringBuffer();
								if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
									sBuffer = sBuffer.append(zhiwu[0]);
								}else {
									sBuffer = sBuffer.append(xinXi.getZhiwu());
									sBuffer = sBuffer.append(","+zhiwu[0]);
								}
								logger.info(sBuffer);
								xinXi.setZhiwu(sBuffer.toString());
							}
						}
					}
					xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
				}
			}
			if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
				xinXi.setZhiwu("成员");
			}
			xinXis.add(xinXi);
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/shetuanhuanjiexinxiweihu");
		mView.addObject("renyuans", xinXis);
		mView.addObject("shetuanxinxiid", sheTuanXinXiID);
		return mView;
	}
	
	@RequestMapping(value="xhjxxwh")
	public ModelAndView XueShengZuZhiHuanJieXinXiWeiHu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String xueShengZuZhiXinXiID = request.getParameter("id");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		if (xueShengZuZhiJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		String sheTuanRenYuanIDs[] = xueShengZuZhiXinXi.getRenyuanids().split(",");
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiJiBenXinXi.getXueshengzuzhiid());
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		SheTuanRenYuanXinXi xinXi = null;
		XueSheng xueSheng = null;
		BanJi banJi = null;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		for (int i = 0; i < sheTuanRenYuanIDs.length; i++) {
			xinXi = new SheTuanRenYuanXinXi();
			xueSheng = new XueSheng();
			banJi = new BanJi();
			xueSheng = xueShengService.getUserById(Integer.parseInt(sheTuanRenYuanIDs[i]));
			banJi=banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
			xinXi.setXueshengid(xueSheng.getXueshengid());
			xinXi.setXuehao(xueSheng.getXuehao());
			xinXi.setXingming(xueSheng.getXingming());
			xinXi.setBanji(banJi.getBanjimingcheng());
			xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
			xinXi.setShoujihao(xueSheng.getDianhua());
			if (xueShengZuZhiXinXi.getFuzeren().equals(xueSheng.getXueshengid().toString())) {
				xinXi.setZhiwu("负责人");
			}
			if (xueShengZuZhiXinXi.getBianji()!=null) {
				if (xueShengZuZhiXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					}else {
							sBuffer = sBuffer.append(xinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xinXi.setZhiwu(sBuffer.toString());
				}
			}
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%", xueSheng.getXueshengid().toString() + ",%");
				if (sheTuanBuMenXinXin!=null) {
					if (sheTuanBuMenXinXin.getZhiwu()!=null) {
						String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
						for (int j = 0; j < zhiwus.length; j++) {
							String zhiwu[] =zhiwus[j].split(",");
							if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
								StringBuffer sBuffer = new StringBuffer();
								if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
									sBuffer = sBuffer.append(zhiwu[0]);
								}else {
									sBuffer = sBuffer.append(xinXi.getZhiwu());
									sBuffer = sBuffer.append(","+zhiwu[0]);
								}
								logger.info(sBuffer);
								xinXi.setZhiwu(sBuffer.toString());
							}
						}
					}
					xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
				}
			}
			if (xinXi.getZhiwu()==null||xinXi.getZhiwu().equals("")) {
				xinXi.setZhiwu("成员");
			}
			xinXis.add(xinXi);
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("stu/xueshengzuzhihuanjiexinxiweihu");
		mView.addObject("renyuans", xinXis);
		mView.addObject("xueshengzuzhixinxiid", xueShengZuZhiXinXiID);
		return mView;
	}
	
	@RequestMapping(value="ssubhjxxwh")
	public void SheTuanSubHuanJieXinXiWeiHu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return;
		}
		String sheTuanXinXiID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return;
		}
		String renYuans[] = request.getParameterValues("choice");
		logger.info(Arrays.toString(renYuans));
		SheTuanXinXi xinXi = sheTuanService.selectSheTuanXinXiBySheTuanIDAndNianDu(sheTuanXinXi.getShetuanid(), String.valueOf(sheTuanXinXi.getNiandu()+1));
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf-8");
		if (xinXi!=null) {
			out.print("<script>alert('你当前已完成维护！如需修改请联系管理员！');</script>");
			out.print("<script>location='wodeshetuan';</script>");
			return;
		}
		Boolean b = sheTuanService.sheTuanXinXiWeiHu(sheTuanXinXi, renYuans);
		if (b) {
			out.print("<script>alert('完成！');</script>");
			out.print("<script>location='wodeshetuan';</script>");
			return;
		}else {
			out.print("<script>alert('fail！');</script>");
			out.print("<script>location='wodeshetuan';</script>");
			return;
		}
	}
	
	@RequestMapping(value="xsubhjxxwh")
	public void XueShengZuZhiSubHuanJieXinXiWeiHu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return;
		}
		String xueShengZuZhiXinXiID= request.getParameter("id");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return;
		}
		XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		if (xueShengZuZhiJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return;
		}
		String renYuans[] = request.getParameterValues("choice");
		logger.info(Arrays.toString(renYuans));
		XueShengZuZhiXinXi xinXi = sheTuanService.selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(xueShengZuZhiXinXi.getXueshengzuzhiid(), String.valueOf(xueShengZuZhiXinXi.getNiandu()+1));
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf-8");
		if (xinXi!=null) {
			out.print("<script>alert('你当前已完成维护！如需修改请联系管理员！');</script>");
			out.print("<script>location='wodeshetuan';</script>");
			return;
		}
		Boolean b = sheTuanService.xueShengZuZhiWeiHu(xueShengZuZhiXinXi, renYuans);
		if (b) {
			out.print("<script>alert('完成！');</script>");
			out.print("<script>location='wodeshetuan';</script>");
			return;
		}else {
			out.print("<script>alert('fail！');</script>");
			out.print("<script>location='wodeshetuan';</script>");
			return;
		}
	}
	
	@RequestMapping(value="sxxwh")
	public ModelAndView SheTuanXinXiWeiHu(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String sheTuanXinXiID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(sheTuanXinXiID), user.getXueshengid());
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanJiBenXinXi.getShetuanid());
		ModelAndView mView = new ModelAndView();
		mView.addObject("shetuanjibenxinxi", sheTuanJiBenXinXi);
		mView.addObject("shetuanbumenxinxi", sheTuanBuMenJiBenXinXins);
		mView.setViewName("stu/shetuanxinxiweihu");
		return mView;
	}
	
	@RequestMapping(value="xxxwh")
	public ModelAndView XueShengZuZhiXinXiWeiHu(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String xueShengZuZhiXinXiID = request.getParameter("id");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		XueShengZuZhiXinXi	xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		if (xueShengZuZhiJiBenXinXi.getZhuangtai()==false) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiJiBenXinXi.getXueshengzuzhiid());
		ModelAndView mView = new ModelAndView();
		mView.addObject("xueshengzuzhijibenxinxi", xueShengZuZhiJiBenXinXi);
		mView.addObject("shetuanbumenxinxi", sheTuanBuMenJiBenXinXins);
		mView.setViewName("stu/xueshengzuzhixinxiweihu");
		return mView;
	}
	
	@RequestMapping(value="subsxxwh")
	@ResponseBody
	public String SubSheTuanXinXiWeiHu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String data[] = request.getParameter("CODE").split(",zytech,");
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(Integer.parseInt(data[0]));
		if (sheTuanJiBenXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanIDAndNianDu(sheTuanJiBenXinXi.getShetuanid(),format.format(date));
		if (sheTuanXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanXinXi.getShezhang().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		if (!data[1].startsWith("http")) {
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("http://");
			sBuffer.append(data[1]);
			sheTuanJiBenXinXi.setShetuanjieshaourl(sBuffer.toString());
		}else {
			sheTuanJiBenXinXi.setShetuanjieshaourl(data[1]);
		}
		sheTuanJiBenXinXi.setShetuanjieshao(data[2]);
		int i = sheTuanService.updateSheTuanJiBenXinXi(sheTuanJiBenXinXi);
		if (i>0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@RequestMapping(value="subxxxwh")
	@ResponseBody
	public String SubXueShengZuZhiXinXiWeiHu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng user = (XueSheng) request.getSession().getAttribute("user");
		String data[] = request.getParameter("CODE").split(",zytech,");
		XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(Integer.parseInt(data[0]));
		if (xueShengZuZhiJiBenXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(),format.format(date));
		if (xueShengZuZhiXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(user.getXueshengid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		if (!data[1].startsWith("http")) {
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("http://");
			sBuffer.append(data[1]);
			xueShengZuZhiJiBenXinXi.setJieshaourl(sBuffer.toString());
		}else {
			xueShengZuZhiJiBenXinXi.setJieshaourl(data[1]);
		}
		xueShengZuZhiJiBenXinXi.setJianjie(data[2]);
		int i = sheTuanService.updateXueShengZuZhiJiBenXinXi(xueShengZuZhiJiBenXinXi);
		if (i>0) {
			return "success";
		}else {
			return "fail";
		}
	}
}
