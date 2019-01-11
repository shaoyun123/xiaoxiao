package com.web.controller.web.jiaoshi;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.ModelAndView;

import com.web.model.App_KeChengKaoPing;
import com.web.model.App_XueXiZu;
import com.web.model.KeCheng;
import com.web.model.KeChengWenDang;
import com.web.model.ShangChuanWenDang;
import com.web.model.XueSheng;
import com.web.service.App_XueXiZuService;
import com.web.service.KeChengService;
import com.web.service.KeChengWenDangService;
import com.web.service.ShangChuanWenDangService;
import com.web.service.XiaoZuWenDangService;
import com.web.service.XueShengService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
public class KeChengWenDangController_jiaoshi {

	@Autowired
	private KeChengWenDangService keChengWenDangService;
	@Autowired
	private XiaoZuWenDangService xiaoZuWenDangService;
	@Autowired
	private KeChengService keChengService;
	@Autowired
	private ShangChuanWenDangService shangChuanWenDangService;
	@Autowired
	private XueShengService xueShengService;

	@RequestMapping(value = "getwendang")
	public ModelAndView getwendang(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String kechengid = request.getParameter("kechengid");
		ModelAndView mView = new ModelAndView();
		Map<String, Object> shijianMap = keChengService.getshijianke(Integer.parseInt(kechengid));
		if (shijianMap != null && shijianMap.get("keChengID") != null) {
			KeCheng keCheng = keChengService
					.selectByPrimaryKey(Integer.parseInt(shijianMap.get("keChengID").toString()));
			if (keCheng != null) {
				mView.addObject("kechengmingcheng", keCheng.getKechengmingcheng());
			} else {
				mView.addObject("kechengmingcheng", "");
			}
		}
		List<Map<String, Object>> wendangs = keChengWenDangService.getAllByKeChengID(Integer.parseInt(kechengid));
		int count = wendangs.size();
		int pageSize = 10;
		int page = 1;
		int pages = (count / pageSize) + 1;
		if (count % pageSize == 0) {
			pages = (count / pageSize);
		}
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			List<Map<String, Object>> wendangs2 = new ArrayList<>();
			if (count < 10) {
				for (int i = 0; i < count; i++) {
					wendangs2.add(wendangs.get(i));
				}
				mView.addObject("kechengwendangs", wendangs2);
			} else {
				for (int i = 0; i < 10; i++) {
					wendangs2.add(wendangs.get(i));
				}
				mView.addObject("kechengwendangs", wendangs2);
			}
		} else {
			if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<Map<String, Object>> wendangs2 = new ArrayList<>();
					if (count < 10) {
						for (int i = (page - 1) * 10; i < count; i++) {
							wendangs2.add(wendangs.get(i));
						}
						mView.addObject("kechengwendangs", wendangs2);
					} else {
						for (int i = (page - 1) * 10; i < (page * 10); i++) {
							wendangs2.add(wendangs.get(i));
						}
						mView.addObject("kechengwendangs", wendangs2);
					}
				} else if (page == pages) {
					List<Map<String, Object>> wendangs2 = new ArrayList<>();
					for (int i = (page - 1) * 10; i < count; i++) {
						wendangs2.add(wendangs.get(i));
					}
					mView.addObject("kechengwendangs", wendangs2);
				} else {
					try {
						response.sendRedirect("logout");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			} else {
				try {
					response.sendRedirect("logout");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}
		mView.addObject("kechengid", kechengid);
		mView.addObject("page", page);
		mView.addObject("pages", pages);
		mView.setViewName("jiaoshi/jianbao");
		return mView;
	}

	@RequestMapping(value = "getAllWenDang")
	public ModelAndView getAllWenDang(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String kechengid = request.getParameter("kechengid");
		ModelAndView mView = new ModelAndView();
		List<Map<String, Object>> xiaoZuWenDangs = xiaoZuWenDangService.getAllByKeChengID(Integer.parseInt(kechengid));
		for(int i=0;i<xiaoZuWenDangs.size();i++) {
			for(int j=0;j<xiaoZuWenDangs.size();j++) {
				if(xiaoZuWenDangs.get(i).get("zuWenDangID").toString().equals(xiaoZuWenDangs.get(j).get("zuWenDangID").toString())) {
					continue;
				}
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(xiaoZuWenDangs.get(i).get("wenDangMing").toString().equals(xiaoZuWenDangs.get(j).get("wenDangMing").toString())) {
					Date date1 = simpleDateFormat.parse(xiaoZuWenDangs.get(i).get("shangChuanShiJian").toString());
					Date date2 = simpleDateFormat.parse(xiaoZuWenDangs.get(j).get("shangChuanShiJian").toString());
					if(date1.getTime()-date2.getTime()>=0) {
					xiaoZuWenDangs.remove(j);
					}else {
						xiaoZuWenDangs.remove(i);
					}
			}
		}
		}
		for(Map<String, Object> xZuWenDang :xiaoZuWenDangs) {
			XueSheng xueSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(xZuWenDang.get("xueShengID").toString()));
			if(xueSheng!=null) {
				xZuWenDang.put("xingming", xueSheng.getXingming());
			}
		}
		int count = xiaoZuWenDangs.size();
		int pageSize = 10;
		int page = 1;
		int pages = (count / pageSize) + 1;
		if (count % pageSize == 0) {
			pages = (count / pageSize);
		}
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			List<Map<String, Object>> wendangs2 = new ArrayList<>();
			if (count < 10) {
				for (int i = 0; i < count; i++) {
					wendangs2.add(xiaoZuWenDangs.get(i));
				}
				mView.addObject("xiaozuwendangs", wendangs2);
			} else {
				for (int i = 0; i < 10; i++) {
					wendangs2.add(xiaoZuWenDangs.get(i));
				}
				mView.addObject("xiaozuwendangs", wendangs2);
			}
		} else {
			if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<Map<String, Object>> wendangs2 = new ArrayList<>();
					if (count < 10) {
						for (int i = (page - 1) * 10; i < count; i++) {
							wendangs2.add(xiaoZuWenDangs.get(i));
						}
						mView.addObject("xiaozuwendangs", wendangs2);
					} else {
						for (int i = (page - 1) * 10; i < (page * 10); i++) {
							wendangs2.add(xiaoZuWenDangs.get(i));
						}
						mView.addObject("xiaozuwendangs", wendangs2);
					}
				} else if (page == pages) {
					List<Map<String, Object>> wendangs2 = new ArrayList<>();
					for (int i = (page - 1) * 10; i < count; i++) {
						wendangs2.add(xiaoZuWenDangs.get(i));
					}
					mView.addObject("xiaozuwendangs", wendangs2);
				} else {
					try {
						response.sendRedirect("logout");
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}
			}
		}
		mView.addObject("kechengid", kechengid);
		mView.addObject("page", page);
		mView.addObject("pages", pages);
		mView.setViewName("jiaoshi/jianbao");
		mView.setViewName("jiaoshi/wendangliebiao");
		return mView;
	}
	
	@RequestMapping(value = "addjianbao") // 添加课程考评
	public ModelAndView addjianbao(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String kechengid = request.getParameter("kechengid");
		Map<String, Object> shijiankemap = keChengService.getshijianke(Integer.parseInt(kechengid));
		KeCheng keCheng = keChengService.selectByPrimaryKey(Integer.parseInt(shijiankemap.get("keChengID").toString()));
		if (keCheng != null) {
			mView.addObject("kechengmingcheng", keCheng.getKechengmingcheng());
		}
		mView.addObject("kechengid", kechengid);
		mView.setViewName("jiaoshi/addjianbao");
		return mView;
	}
	@ResponseBody
	@RequestMapping(value = "savejianbao") // 保存考评
	public JSONObject savejianbao(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String kechengid = request.getParameter("kechengid");
		String jianbaomingcheng = request.getParameter("jianbaomingcheng");
		String kaishishijian = request.getParameter("kaishishijian");
		String jieshushijian = request.getParameter("jieshushijian");
		String zhuangtai = request.getParameter("zhuangtai");
		String shuoming = request.getParameter("shuoming");
		List<Map<String, Object>> wendangs = keChengWenDangService.getAllByKeChengID(Integer.parseInt(kechengid));
		for (int i = 0; i < wendangs.size(); i++) {
			if (wendangs.get(i).get("mingCheng").toString().equals(jianbaomingcheng)) {
				jsonObject.put("status", "samename");
				return jsonObject;
			}
		}
		KeChengWenDang keChengWenDang = new KeChengWenDang();
		keChengWenDang.setShijiankeid(Integer.parseInt(kechengid));
		keChengWenDang.setKaishishijian(kaishishijian);
		keChengWenDang.setJieshushijian(jieshushijian);
		keChengWenDang.setZhuangtai(Integer.parseInt(zhuangtai));
		keChengWenDang.setShuoming(shuoming);
		keChengWenDang.setMingcheng(jianbaomingcheng);
		int i = keChengWenDangService.insert(keChengWenDang);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}
	
	@RequestMapping(value = "updatejianbao") // 修改考评
	public ModelAndView updatejianbao(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mView = new ModelAndView();
		String wendangid = request.getParameter("wendangid");
		KeChengWenDang  keChengWenDang =keChengWenDangService.selectByPrimaryKey(Integer.parseInt(wendangid));
		Map<String, Object> shijiankemap = keChengService.getshijianke(keChengWenDang.getShijiankeid());
		KeCheng keCheng = keChengService.selectByPrimaryKey(Integer.parseInt(shijiankemap.get("keChengID").toString()));
		if (keCheng != null) {
			mView.addObject("kechengmingcheng", keCheng.getKechengmingcheng());
		}
		mView.addObject("keChengWenDang", keChengWenDang);
		mView.setViewName("jiaoshi/updatejianbao");
		return mView;
	}
	@ResponseBody
	@RequestMapping(value = "saveupdatejiabao") // 保存考评
	public JSONObject saveupdatejiabao(HttpServletRequest request, HttpServletResponse response) {
		if (!Util.checkSession(request)) {
			try {
				response.sendRedirect("logout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		String wendangid = request.getParameter("wendangid");
		String kechengid = request.getParameter("kechengid");
		String jianbaomingcheng = request.getParameter("jianbaomingcheng");
		String kaishishijian = request.getParameter("kaishishijian");
		String jieshushijian = request.getParameter("jieshushijian");
		String zhuangtai = request.getParameter("zhuangtai");
		String shuoming = request.getParameter("shuoming");
		KeChengWenDang  keChengWenDang =keChengWenDangService.selectByPrimaryKey(Integer.parseInt(wendangid));
		keChengWenDang.setKaishishijian(kaishishijian);
		keChengWenDang.setJieshushijian(jieshushijian);
		keChengWenDang.setZhuangtai(Integer.parseInt(zhuangtai));
		keChengWenDang.setShuoming(shuoming);
		keChengWenDang.setMingcheng(jianbaomingcheng);
		List<Map<String, Object>> wendangs = keChengWenDangService.getAllByKeChengID(Integer.parseInt(kechengid));
		for (int i = 0; i < wendangs.size(); i++) {
				if (wendangs.get(i).get("ID").toString().equals(wendangid)) {
					continue;
				}
				if (wendangs.get(i).get("mingCheng").toString().equals(jianbaomingcheng)) {
				jsonObject.put("status", "samename");
				return jsonObject;
				}
			}
		int i = keChengWenDangService.updateByPrimaryKey(keChengWenDang);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}


	@ResponseBody
	@RequestMapping(value = "delwendang")
	public JSONObject delwendang(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String zuwendangid = request.getParameter("zuwendangid");
		String shangchuanid = request.getParameter("shangchuanid");
		JSONObject jsonObject = new JSONObject();
		try {
			ShangChuanWenDang shangChuanWenDang = shangChuanWenDangService
					.selectByPrimaryKey(Integer.parseInt(shangchuanid));
			File file = new File(shangChuanWenDang.getBendilujing());
			if (file.exists() && file.isFile())
				file.delete();
			int i = shangChuanWenDangService.delshangchuanwendang(Integer.parseInt(shangchuanid));
			if (i != 0) {
				try {
					int j = xiaoZuWenDangService.delete(Integer.parseInt(zuwendangid));
					if (j != 0) {
						jsonObject.put("status", "success");
					}
				} catch (Exception e) {
					e.printStackTrace();
					jsonObject.put("status", "fail");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("status", "fail");
		}
		return jsonObject;

	}
}
