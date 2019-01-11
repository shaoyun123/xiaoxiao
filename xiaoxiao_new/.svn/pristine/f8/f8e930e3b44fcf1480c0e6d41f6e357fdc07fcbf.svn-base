package com.web.controller.app.stu;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.web.model.BanJi;
import com.web.model.NaXin;
import com.web.model.NaXinJiBenXinXi;
import com.web.model.SheTuanBuMenJiBenXinXin;
import com.web.model.SheTuanBuMenXinXin;
import com.web.model.SheTuanChuangJian;
import com.web.model.SheTuanHuoDongXinXi;
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
import com.web.service.SheTuanHuoDongService;
import com.web.service.SheTuanService;
import com.web.service.TiXingService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
public class AppSheTuanController {
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
	private SheTuanHuoDongService sheTuanHuoDongService;
	@Autowired
	private TiXingService tiXingService;

	// 社团介绍
	@RequestMapping(value = "app_SheTuanJieShao")
	@ResponseBody
	public JSONObject app_SheTuanJieShao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String code = request.getParameter("CODE");
		String xueXiaoXueHao[] = code.split("_");
		Map<Object, Object> map = new HashMap<>();
		if (code != null && code != "") {
			List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = sheTuanService
					.selectXueShengZuZhiJiBenXinXiByXueXiaoID(Integer.parseInt(xueXiaoXueHao[0]));
			for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
				xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
						.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
				if (xueShengZuZhiJiBenXinXi.getZhidaojiaoshi()!=null) {
					xueShengZuZhiJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(xueShengZuZhiJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
				}
			}
			List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = sheTuanService
					.selectSheTuanJiBenXinXiByXueXiaoID(Integer.parseInt(xueXiaoXueHao[0]));
			for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
				sheTuanJiBenXinXi.setChuanjianren(xueShengService
						.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
				if (sheTuanJiBenXinXi.getZhidaojiaoshi()!=null) {
					sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
				}
			}
			map.put("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
			map.put("shetuan", sheTuanJiBenXinXis);
		}
		return JSONObject.fromObject(map);
	}

	// 查看学生组织详情
	@RequestMapping(value = "app_XueShengZuZhi")
	@ResponseBody
	public JSONObject app_XueShengZuZhi(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		Map<Object, Object> map2 = new HashMap<>();
		XueShengZuZhiJiBenXinXi xi = new XueShengZuZhiJiBenXinXi();
		XueShengZuZhiXinXi xueShengZuZhiXinXi = new XueShengZuZhiXinXi();
		map2.put("jibenxinxi", xi);
		map2.put("xinxi", xueShengZuZhiXinXi);
		if (data[0] != null && data[0] != "") {
			Map<String, String> map = new HashMap<>();
			String xueXiaoXueHao[] = data[0].split("_");
			map.put("xuexiaoid", xueXiaoXueHao[0]);
			map.put("xueshengzuzhiid", data[1]);
			xi = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndXueShengZuZhiID(map);	
			if (xi == null) {
				return JSONObject.fromObject(null);
			}
			if (xi.getZhidaojiaoshi()!=null) {
				xi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(xi.getZhidaojiaoshi())).getYonghuxingming());
			}
			xi.setChuangjianren(xueShengService.getUserById(Integer.parseInt(xi.getChuangjianren())).getXingming());
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			xueShengZuZhiXinXi = sheTuanService
					.selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(xi.getXueshengzuzhiid(), format.format(date));
			if (xueShengZuZhiXinXi == null) {
				map2.put("jibenxinxi", xi);
				return JSONObject.fromObject(map2);
			}
			List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
			List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
					.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xi.getXueshengzuzhiid());
			if (sheTuanBuMenJiBenXinXins.isEmpty()) {
				xueShengZuZhiXinXi.setFuzeren(
						xueShengService.getUserById(Integer.parseInt(xueShengZuZhiXinXi.getFuzeren())).getXingming());
				xueShengZuZhiXinXi.setZhidaoren(yongHuService
						.selectYongHuByID(Integer.parseInt(xueShengZuZhiXinXi.getZhidaoren())).getYonghuxingming());
				map2.put("jibenxinxi", xi);
				map2.put("xinxi", xueShengZuZhiXinXi);
				return JSONObject.fromObject(map2);
			}
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin buMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(
						sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
				buMenXinXin.setBuzhang(
						xueShengService.getUserById(Integer.parseInt(buMenXinXin.getBuzhang())).getXingming());
				buMenXinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
				buMenXinXin.setZhize(sheTuanBuMenJiBenXinXin.getZhize());
				buMenXinXins.add(buMenXinXin);
			}
			xueShengZuZhiXinXi.setFuzeren(
					xueShengService.getUserById(Integer.parseInt(xueShengZuZhiXinXi.getFuzeren())).getXingming());
			xueShengZuZhiXinXi.setBumen(buMenXinXins);
			xueShengZuZhiXinXi.setZhidaoren(yongHuService
					.selectYongHuByID(Integer.parseInt(xueShengZuZhiXinXi.getZhidaoren())).getYonghuxingming());
			map2.put("jibenxinxi", xi);
			map2.put("xinxi", xueShengZuZhiXinXi);
		}
		return JSONObject.fromObject(map2);
	}

	// 查看社团详情
	@RequestMapping(value = "app_SheTuan")
	@ResponseBody
	public JSONObject app_SheTuan(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		Map<Object, Object> map2 = new HashMap<>();
		SheTuanJiBenXinXi sheTuanJiBenXinXi = new SheTuanJiBenXinXi();
		SheTuanXinXi sheTuanXinXi = new SheTuanXinXi();
		map2.put("shetuanjibenxinxi", sheTuanJiBenXinXi);
		map2.put("shetuanxinxi", sheTuanXinXi);
		if (data[0] != null && data[0] != "") {
			Map<String, String> map = new HashMap<>();
			String xueXiaoXueHao[] = data[0].split("_");
			map.put("xuexiaoid", xueXiaoXueHao[0]);
			map.put("shetuanid", data[1]);
			sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndSheTuanID(map);
			if (sheTuanJiBenXinXi == null) {
				return JSONObject.fromObject(null);
			}
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanIDAndNianDu(Integer.parseInt(data[1]),
					format.format(date));
			if (sheTuanXinXi == null) {
				map2.put("shetuanjibenxinxi", sheTuanJiBenXinXi);
				return JSONObject.fromObject(map2);
			}
			List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
			List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
					.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
			if (sheTuanBuMenJiBenXinXins.isEmpty()) {
				sheTuanJiBenXinXi.setChuanjianren(xueShengService
						.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
				if (sheTuanJiBenXinXi.getZhidaojiaoshi()!=null) {
					sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
				}
				sheTuanXinXi.setShezhang(
						xueShengService.getUserById(Integer.parseInt(sheTuanXinXi.getShezhang())).getXingming());
				map2.put("shetuanjibenxinxi", sheTuanJiBenXinXi);
				map2.put("shetuanxinxi", sheTuanXinXi);
				return JSONObject.fromObject(map2);
			}
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin buMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(
						sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
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
			if (sheTuanJiBenXinXi.getZhidaojiaoshi()!=null) {
				sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
			}
			sheTuanXinXi.setBumen(buMenXinXins);
			sheTuanXinXi.setShezhang(
					xueShengService.getUserById(Integer.parseInt(sheTuanXinXi.getShezhang())).getXingming());
			map2.put("shetuanjibenxinxi", sheTuanJiBenXinXi);
			map2.put("shetuanxinxi", sheTuanXinXi);
		}
		return JSONObject.fromObject(map2);
	}

	// 通过条件查询社团
	@RequestMapping(value = "app_ChaXun_SheTuan")
	@ResponseBody
	public JSONObject app_ChaXunSheTuan(HttpServletRequest request) {
		String xingJi = request.getParameter("xingji");
		String xingZhi = request.getParameter("xingzhi");
		String code = request.getParameter("xuexiaoxuehao");
		Map<Object, Object> map2 = new HashMap<>();
		String mingCheng = request.getParameter("mingcheng");
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = new ArrayList<>();
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = new ArrayList<>();
		map2.put("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
		map2.put("shetuan", sheTuanJiBenXinXis);
		map2.put("xingji", xingJi);
		if (code != null && code != "") {
			if (xingZhi.equals("shetuan")) {
				Map<String, String> map = new HashMap<>();
				String xueXiaoXueHao[] = code.split("_");
				map.put("xuexiaoid", xueXiaoXueHao[0]);
				map.put("xingji", xingJi.toString());
				map.put("mingcheng", mingCheng);
				sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
			}
			if (xingZhi.equals("xueshengzuzhi")) {
				Map<String, String> map = new HashMap<>();
				String xueXiaoXueHao[] = code.split("_");
				map.put("xuexiaoid", xueXiaoXueHao[0]);
				map.put("mingcheng", mingCheng);
				xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
				xingJi = "";
			}
			if (xingZhi.equals("")) {
				if (!mingCheng.equals("") && !xingJi.equals("")) {
					Map<String, String> map = new HashMap<>();
					String xueXiaoXueHao[] = code.split("_");
					map.put("xuexiaoid", xueXiaoXueHao[0]);
					map.put("mingcheng", mingCheng);
					map.put("xingji", xingJi);
					sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
					xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
				} else if (!mingCheng.equals("") && xingJi.equals("")) {
					Map<String, String> map = new HashMap<>();
					String xueXiaoXueHao[] = code.split("_");
					map.put("xuexiaoid", xueXiaoXueHao[0]);
					map.put("mingcheng", mingCheng);
					map.put("xingji", xingJi);
					sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
					xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
				} else if (mingCheng.equals("") && !xingJi.equals("")) {
					Map<String, String> map = new HashMap<>();
					String xueXiaoXueHao[] = code.split("_");
					map.put("xuexiaoid", xueXiaoXueHao[0]);
					map.put("xingji", xingJi.toString());
					map.put("mingcheng", mingCheng);
					sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
				} else {
					String xueXiaoXueHao[] = code.split("_");
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
				sheTuanJiBenXinXi.setChuanjianren(xueShengService
						.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
				if (sheTuanJiBenXinXi.getZhidaojiaoshi()!=null) {
					sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
				}
			}
			map2.put("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
			map2.put("shetuan", sheTuanJiBenXinXis);
			map2.put("xingji", xingJi);

			System.out.println(map2.get("xingji"));
		}
		return JSONObject.fromObject(map2);
	}

	@RequestMapping(value = "app_SheTuanBaoMing")
	@ResponseBody
	public JSONObject app_SheTuanBaoMing(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String xueXiaoXueHao[] = data[1].split("_");
		Map<Object, Object> map = new HashMap<>();
		Boolean co=false;
		if (code != null && code != "") {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
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
					NaXin naXin = sheTuanService.selectNaXinByXueShengIDAndXueShengZuZhiXinXiID(Integer.parseInt(data[0]),
							xShengZuZhiXinXi.getXueshengzuzhixinxiid());
					if (naXin != null) {
						if (naXin.getZhuangtai()==2) {
							xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(3);//已拒绝
						}
						if (naXin.getZhuangtai()==0) {
							xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(1);
						}
						if (naXin.getZhuangtai()==1) {
							xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(2);
						}
					} else {
						xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(0);
					}
					String renYuanIDs[] = xShengZuZhiXinXi.getRenyuanids().split(",");
					for (int i = 0; i < renYuanIDs.length; i++) {
						if (renYuanIDs[i].equals(data[0])) {
							xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(2);
							co=true;
						}
					}
					if(co==false&&xueShengZuZhiJiBenXinXi.getBaomingzhuangtai()==2){
						xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(4);
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
			co=false;
			List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = sheTuanService
					.selectSheTuanJiBenXinXiByXueXiaoID(Integer.parseInt(xueXiaoXueHao[0]));
			for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
				sheTuanJiBenXinXi.setChuanjianren(
						xueShengService.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
				if (sheTuanJiBenXinXi.getZhidaojiaoshi()!=null) {
					sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
				}
				SheTuanXinXi xSheTuanXinXi = sheTuanService
						.selectSheTuanXinXiBySheTuanIDAndNianDu(sheTuanJiBenXinXi.getShetuanid(), format.format(date));
				if (xSheTuanXinXi != null) {
					NaXin naXin = sheTuanService.selectNaXinByXueShengIDAndSheTuanXinXiID(Integer.parseInt(data[0]),
							xSheTuanXinXi.getShetuanxinxiid());
					if (naXin != null) {
						if (naXin.getZhuangtai()==2) {
							sheTuanJiBenXinXi.setBaomingzhuangtai(3);//已拒绝
						}
						if (naXin.getZhuangtai()==0) {
							sheTuanJiBenXinXi.setBaomingzhuangtai(1);
						}
						if (naXin.getZhuangtai()==1) {
							sheTuanJiBenXinXi.setBaomingzhuangtai(2);
						}
					} else {
						sheTuanJiBenXinXi.setBaomingzhuangtai(0);
					}
					String sheYuanIDs[] = xSheTuanXinXi.getSheyuanids().split(",");
					for (int i = 0; i < sheYuanIDs.length; i++) {
						if (sheYuanIDs[i].equals(data[0])) {
							sheTuanJiBenXinXi.setBaomingzhuangtai(2);
							co=true;
						}
					}
					if(co==false&&sheTuanJiBenXinXi.getBaomingzhuangtai()==2){
						sheTuanJiBenXinXi.setBaomingzhuangtai(4);
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
			map.put("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
			map.put("shetuan", sheTuanJiBenXinXis);
		}
		return JSONObject.fromObject(map);
	}

	// 在社团报名页面你查询社团
	@RequestMapping(value = "app_SheTuanBaoMingCX")
	@ResponseBody
	public JSONObject app_SheTuanBaoMingCX(HttpServletRequest request) {
		//String xingJi = request.getParameter("xingji");
		StringBuffer xingJi = new StringBuffer();
		xingJi.append(request.getParameter("xingji"));
		String xingZhi = request.getParameter("xingzhi");
		String code = request.getParameter("xuexiaoxuehao");
		Map<Object, Object> map2 = new HashMap<>();
		String xueshengid = request.getParameter("studentid");
		String mingCheng = request.getParameter("mingcheng");
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = new ArrayList<>();
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = new ArrayList<>();
		Boolean co=false;
		map2.put("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
		map2.put("shetuan", sheTuanJiBenXinXis);
		map2.put("xingji", xingJi);
		if (code != null && code != "") {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			if (xingZhi.equals("shetuan")) {
				Map<String, String> map = new HashMap<>();
				String xueXiaoXueHao[] = code.split("_");
				map.put("xuexiaoid", xueXiaoXueHao[0]);
				map.put("xingji", xingJi.toString());
				map.put("mingcheng", mingCheng);
				sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
			}
			if (xingZhi.equals("xueshengzuzhi")) {
				Map<String, String> map = new HashMap<>();
				String xueXiaoXueHao[] = code.split("_");
				map.put("xuexiaoid", xueXiaoXueHao[0]);
				map.put("mingcheng", mingCheng);
				xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
				xingJi.delete(0, xingJi.length());
			}
			if (xingZhi.equals("")) {
				if (!mingCheng.equals("")) {
					Map<String, String> map = new HashMap<>();
					String xueXiaoXueHao[] = code.split("_");
					map.put("xuexiaoid", xueXiaoXueHao[0]);
					map.put("xingji", xingJi.toString());
					map.put("mingcheng", mingCheng);
					sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
					xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
				} else if (!xingJi.toString().equals("")) {
					Map<String, String> map = new HashMap<>();
					String xueXiaoXueHao[] = code.split("_");
					map.put("xuexiaoid", xueXiaoXueHao[0]);
					map.put("xingji", xingJi.toString());
					map.put("mingcheng", mingCheng);
					sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
				} else {
					String xueXiaoXueHao[] = code.split("_");
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
					NaXin naXin = sheTuanService.selectNaXinByXueShengIDAndXueShengZuZhiXinXiID(Integer.parseInt(xueshengid),
							xShengZuZhiXinXi.getXueshengzuzhixinxiid());
					if (naXin != null) {
						if (naXin.getZhuangtai()==2) {
							xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(3);//已拒绝
						}
						if (naXin.getZhuangtai()==0) {
							xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(1);
						}
						if (naXin.getZhuangtai()==1) {
							xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(2);
						}
					} else {
						xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(0);
					}
					String renYuanIDs[] = xShengZuZhiXinXi.getRenyuanids().split(",");
					for (int i = 0; i < renYuanIDs.length; i++) {
						if (renYuanIDs[i].equals(xueshengid)) {
							xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(2);
							co=true;
						}
					}
					if(co==false&&xueShengZuZhiJiBenXinXi.getBaomingzhuangtai()==2){
						xueShengZuZhiJiBenXinXi.setBaomingzhuangtai(4);
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
			co=false;
			for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
				sheTuanJiBenXinXi.setChuanjianren(
						xueShengService.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
				if (sheTuanJiBenXinXi.getZhidaojiaoshi()!=null) {
					sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
				}
				SheTuanXinXi xSheTuanXinXi = sheTuanService
						.selectSheTuanXinXiBySheTuanIDAndNianDu(sheTuanJiBenXinXi.getShetuanid(), format.format(date));
				if (xSheTuanXinXi != null) {
					NaXin naXin = sheTuanService.selectNaXinByXueShengIDAndSheTuanXinXiID(Integer.parseInt(xueshengid),
							xSheTuanXinXi.getShetuanxinxiid());
					if (naXin != null) {
						if (naXin.getZhuangtai()==2) {
							sheTuanJiBenXinXi.setBaomingzhuangtai(3);//已拒绝
						}
						if (naXin.getZhuangtai()==0) {
							sheTuanJiBenXinXi.setBaomingzhuangtai(1);
						}
						if (naXin.getZhuangtai()==1) {
							sheTuanJiBenXinXi.setBaomingzhuangtai(2);
						}
					} else {
						sheTuanJiBenXinXi.setBaomingzhuangtai(0);
					}
					String sheYuanIDs[] = xSheTuanXinXi.getSheyuanids().split(",");
					for (int i = 0; i < sheYuanIDs.length; i++) {
						if (sheYuanIDs[i].equals(xueshengid)) {
							sheTuanJiBenXinXi.setBaomingzhuangtai(2);
							co=true;
						}
					}
					if(co==false&&sheTuanJiBenXinXi.getBaomingzhuangtai()==2){
						sheTuanJiBenXinXi.setBaomingzhuangtai(4);
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
			map2.put("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
			map2.put("shetuan", sheTuanJiBenXinXis);
			map2.put("xingji", xingJi.toString());
		}
		return JSONObject.fromObject(map2);
	}

	// 社团或学生组织的报名
	@RequestMapping(value = "app_BaoMing")
	@ResponseBody
	public JSONObject app_BaoMing(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String xueshengid = data[0];
		String xuexiaoxuehao = data[1];
		Map<Object, Object> map2 = new HashMap<>();
		if (xueshengid != null && xueshengid != "") {
			String idx = request.getParameter("idx");
			System.out.println(idx);
			if (idx.equals("")) {
				return null;
			}
			String id[] = idx.split(",");
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			String xueXiaoXueHao[] = xuexiaoxuehao.split("_");
			// 报名学生组织
			if (id[0].equals("1")) {
				Map<String, String> map = new HashMap<>();
				map.put("xuexiaoid", xueXiaoXueHao[0]);
				map.put("xueshengzuzhiid", id[1]);
				XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi = sheTuanService
						.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndXueShengZuZhiID(map);
				if (xueShengZuZhiJiBenXinXi == null) {
					return null;
				}
				XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
						.selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(
								xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(), format.format(date));
				NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService
						.selectNaXinJiBenXinXiByXueShengZuZhiXinXiID(xueShengZuZhiXinXi.getXueshengzuzhixinxiid());
				List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
				if (naXinJiBenXinXi.getZhuangtai() != true) {

					return null;
				} else {
					NaXin naXin = sheTuanService.selectNaXinByXueShengIDAndXueShengZuZhiXinXiID(
							Integer.parseInt(xueshengid), xueShengZuZhiXinXi.getXueshengzuzhixinxiid());
					if (naXin != null) {
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
					map2.put("xinxi", xueShengZuZhiXinXi);
					map2.put("jibenxinxi", xueShengZuZhiJiBenXinXi);
					map2.put("key", true);
				}
			}
			// 报名社团
			if (id[0].equals("0")) {
				Map<String, String> map = new HashMap<>();
				map.put("xuexiaoid", xueXiaoXueHao[0]);
				map.put("shetuanid", id[1]);
				SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService
						.selectSheTuanJiBenXinXiByXueXiaoIDAndSheTuanID(map);
				if (sheTuanJiBenXinXi == null) {
					return null;
				}
				SheTuanXinXi sheTuanXinXi = sheTuanService
						.selectSheTuanXinXiBySheTuanIDAndNianDu(sheTuanJiBenXinXi.getShetuanid(), format.format(date));
				NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService
						.selectNaXinJiBenXinXiBySheTuanXinXiID(sheTuanXinXi.getShetuanxinxiid());
				List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
				if (naXinJiBenXinXi.getZhuangtai() != true) {
					return null;
				} else {
					String sheYuanIDs[] = sheTuanXinXi.getSheyuanids().split(",");
					for (int i = 0; i < sheYuanIDs.length; i++) {
						if (sheYuanIDs[i].equals(xueshengid)) {
							return null;
						}
					}
					NaXin naXin = sheTuanService.selectNaXinByXueShengIDAndSheTuanXinXiID(Integer.parseInt(xueshengid),
							sheTuanXinXi.getShetuanxinxiid());
					if (naXin != null) {
						return null;
					}
					List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
							.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanJiBenXinXi.getShetuanid());
					if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
						for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
							SheTuanBuMenXinXin buMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(
									sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
							buMenXinXin.setBuzhang(xueShengService
									.getUserById(Integer.parseInt(buMenXinXin.getBuzhang())).getXingming());
							buMenXinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
							buMenXinXin.setZhize(sheTuanBuMenJiBenXinXin.getZhize());
							buMenXinXins.add(buMenXinXin);
						}
						sheTuanXinXi.setBumen(buMenXinXins);
					}
					map2.put("jibenxinxi", sheTuanJiBenXinXi);
					map2.put("xinxi", sheTuanXinXi);
					map2.put("key", false);
				}
			}
		}
		return JSONObject.fromObject(map2);
	}

	// 提交报名表
	@RequestMapping(value = "app_TiJiaoBaoMingBiao")
	@ResponseBody
	public String app_TiJiaoBaoMingBiao(HttpServletRequest request) throws ParseException {
		String xingming = request.getParameter("xingming");
		String xueshengid = request.getParameter("xueshengid");
		String retInfo = "";
		String leiXing = request.getParameter("leixing");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int i = 0;
		if (xueshengid != null && xueshengid != "") {
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
				naXin.setBaomingbumenid(Integer.parseInt(buMenID));
				naXin.setBaomingxueshengzuzhiid(Integer.parseInt(xueShengZuZhiXinXiID));
				naXin.setChushengriqi(format.parse(chuShengRiQi));
				naXin.setLianxidianhua(dianHua);
				naXin.setZiwojieshao(ziWoJieShao);
				naXin.setXueshengid(Integer.parseInt(xueshengid));
				naXin.setXingming(xingming);
				naXin.setNaxingjibenxinxiid(sheTuanService
						.selectNaXinJiBenXinXiByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID))
						.getNaxingjibenxinxiid());
				i = sheTuanService.insertNaXin(naXin);
			} else {
				String sheTuanXinXiID = request.getParameter("shetuanxinxiid");
				NaXinJiBenXinXi naXinJiBenXinXi = sheTuanService
						.selectNaXinJiBenXinXiBySheTuanXinXiID(Integer.parseInt(sheTuanXinXiID));
				if (naXinJiBenXinXi.getZhuangtai() != true) {
					retInfo = "notime";
					return retInfo;
				}
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
				if (buMenID != null) {
					naXin.setBaomingbumenid(Integer.parseInt(buMenID));
				}
				naXin.setBaomingshetuanid(Integer.parseInt(sheTuanXinXiID));
				naXin.setChushengriqi(format.parse(chuShengRiQi));
				naXin.setLianxidianhua(dianHua);
				naXin.setZiwojieshao(ziWoJieShao);
				naXin.setXueshengid(Integer.parseInt(xueshengid));
				naXin.setXingming(xingming);
				naXin.setNaxingjibenxinxiid(
						sheTuanService.selectNaXinJiBenXinXiBySheTuanXinXiID(Integer.parseInt(sheTuanXinXiID))
								.getNaxingjibenxinxiid());
				i = sheTuanService.insertNaXin(naXin);
			}
			if (i > 0) {
				retInfo = "success";
			} else {
				retInfo = "fail";
			}
		} else {
			retInfo = "dengchu";
		}
		return retInfo;
	}

	// 返回我的社团数目
	@RequestMapping(value = "app_YiJinSheTuanShu")
	@ResponseBody
	public int app_YiJinSheTuanShu(HttpServletRequest request) {
		int count = 0;
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = new ArrayList<>();
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		if (data[0] != null && data[0] != "") {
			String xuexiaoxuehao[] = data[1].split("_");
			xueShengZuZhiJiBenXinXis = sheTuanService
					.selectXueShengZuZhiJiBenXinXiByXueXiaoID(Integer.parseInt(xuexiaoxuehao[0]));
			for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
				xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
						.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
				XueShengZuZhiXinXi xShengZuZhiXinXi = sheTuanService.selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(
						xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(), format.format(date));
				String renYuanIDs[] = xShengZuZhiXinXi.getRenyuanids().split(",");
				for (int i = 0; i < renYuanIDs.length; i++) {
					if (renYuanIDs[i].equals(data[0])) {
						count++;
					}
				}

			}
			sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoID(Integer.parseInt(xuexiaoxuehao[0]));
			for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
				sheTuanJiBenXinXi.setChuanjianren(xueShengService
						.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
				SheTuanXinXi xSheTuanXinXi = sheTuanService
						.selectSheTuanXinXiBySheTuanIDAndNianDu(sheTuanJiBenXinXi.getShetuanid(), format.format(date));
				if (xSheTuanXinXi != null) {
					System.out.println(xSheTuanXinXi.getSheyuanids());
					String sheYuanIDs[] = xSheTuanXinXi.getSheyuanids().split(",");
					for (int i = 0; i < sheYuanIDs.length; i++) {
						if (sheYuanIDs[i].equals(data[0])) {
							count++;
						}
					}
				}
			}

		} else {
			count = -1;
		}
		return count;
	}

	// 显示社团目录（已加入社团数大于1时）
	@RequestMapping(value = "app_XianShiSheTuanMuLu")
	@ResponseBody
	public JSONObject app_XianShiSheTuanMuLu(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		Map<Object, Object> map = new HashMap<>();
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = new ArrayList<>();
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = new ArrayList<>();
		List<SheTuanXinXi> sheTuanXinXis2 = new ArrayList<>();
		List<XueShengZuZhiXinXi> xueShengZuZhiXinXis2 = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String xuexiaoxuehao[] = data[1].split("_");
		if (data[0] != null && data[0] != "") {
			xueShengZuZhiJiBenXinXis = sheTuanService
					.selectXueShengZuZhiJiBenXinXiByXueXiaoID(Integer.parseInt(xuexiaoxuehao[0]));
			for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
				xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
						.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
				XueShengZuZhiXinXi xShengZuZhiXinXi = sheTuanService.selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(
						xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(), format.format(date));
				xShengZuZhiXinXi.setMingcheng(xueShengZuZhiJiBenXinXi.getMingcheng());
				String renYuanIDs[] = xShengZuZhiXinXi.getRenyuanids().split(",");
				for (int i = 0; i < renYuanIDs.length; i++) {
					if (renYuanIDs[i].equals(data[0])) {
						xueShengZuZhiXinXis2.add(xShengZuZhiXinXi);
					}
				}

			}
			sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoID(Integer.parseInt(xuexiaoxuehao[0]));
			for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
				sheTuanJiBenXinXi.setChuanjianren(xueShengService
						.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
				SheTuanXinXi xSheTuanXinXi = sheTuanService
						.selectSheTuanXinXiBySheTuanIDAndNianDu(sheTuanJiBenXinXi.getShetuanid(), format.format(date));
				if (xSheTuanXinXi != null) {
					xSheTuanXinXi.setMingcheng(sheTuanJiBenXinXi.getMingcheng());
					System.out.println(xSheTuanXinXi.getSheyuanids());
					String sheYuanIDs[] = xSheTuanXinXi.getSheyuanids().split(",");
					for (int i = 0; i < sheYuanIDs.length; i++) {
						if (sheYuanIDs[i].equals(data[0])) {
							sheTuanXinXis2.add(xSheTuanXinXi);
						}
					}
				}
			}
		} else {
			return null;
		}
		map.put("xszuzhis", xueShengZuZhiXinXis2);
		map.put("shetuans", sheTuanXinXis2);
		return JSONObject.fromObject(map);
	}

	// 社团能进行的操作显示
	@RequestMapping(value = "app_SheTuanCaoZuo")
	@ResponseBody
	public SheTuanXinXi app_SheTuanCaoZuo(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		SheTuanXinXi sheTuanXinXi = new SheTuanXinXi();
		if (data[0] != null && data[0] != "") {
			sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiID(Integer.parseInt(data[1]));
			SheTuanJiBenXinXi sheTuanJiBenXinXi = new SheTuanJiBenXinXi();
			sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
			sheTuanXinXi.setMingcheng(sheTuanJiBenXinXi.getMingcheng());
			List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
					.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanJiBenXinXi.getShetuanid());
			if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
				List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
				for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
					SheTuanBuMenXinXin buMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(
							sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
					buMenXinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					buMenXinXins.add(buMenXinXin);
				}
				sheTuanXinXi.setBumen(buMenXinXins);
			}
			if (Integer.parseInt(sheTuanXinXi.getShezhang()) == Integer.parseInt(data[0])) {
				sheTuanXinXi.setZhiwu("社长");
			}
			if (sheTuanXinXi.getBianji() != null) {
				if (sheTuanXinXi.getBianji().equals(data[0])) {
					StringBuffer sBuffer = new StringBuffer();
					if (sheTuanXinXi.getZhiwu() == null || sheTuanXinXi.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					} else {
						sBuffer = sBuffer.append(sheTuanXinXi.getZhiwu());
						sBuffer = sBuffer.append(",编辑");
					}
					sheTuanXinXi.setZhiwu(sBuffer.toString());
				}
			}
			if (sheTuanXinXi.getBumen() != null && !sheTuanXinXi.getBumen().isEmpty()) {
				for (SheTuanBuMenXinXin sheTuanBuMenXinXin : sheTuanXinXi.getBumen()) {
					if (sheTuanBuMenXinXin.getBuzhang() == null) {
						continue;
					}
					if (Integer.parseInt(sheTuanBuMenXinXin.getBuzhang()) == Integer.parseInt(data[0])) {
						StringBuffer sBuffer = new StringBuffer();
						if (sheTuanXinXi.getZhiwu() != null && sheTuanXinXi.getZhiwu() != "") {
							sBuffer.append(sheTuanXinXi.getZhiwu());
							sBuffer.append("," + sheTuanBuMenXinXin.getMingcheng() + "部长");
						} else {
							sBuffer.append(sheTuanBuMenXinXin.getMingcheng() + "部长");
						}
						sheTuanXinXi.setZhiwu(sBuffer.toString());
					}
				}
			}
			if (sheTuanXinXi.getZhiwu() == null || sheTuanXinXi.getZhiwu().equals("")) {
				sheTuanXinXi.setZhiwu("社员");
			}
		}
		return sheTuanXinXi;
	}

	// 学生组织能进行的操作显示
	@RequestMapping(value = "app_ZuZhiCaoZuo")
	@ResponseBody
	public XueShengZuZhiXinXi app_ZuZhiCaoZuo(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = new XueShengZuZhiXinXi();
		XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi = null;
		if (data[0] != null && data[0] != "") {
			xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(data[1]));
			xueShengZuZhiJiBenXinXi = new XueShengZuZhiJiBenXinXi();
			xueShengZuZhiJiBenXinXi = sheTuanService
					.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
			xueShengZuZhiXinXi.setMingcheng(xueShengZuZhiJiBenXinXi.getMingcheng());
			List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
					.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
			if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
				List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
				for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
					SheTuanBuMenXinXin buMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(
							sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
					buMenXinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					buMenXinXins.add(buMenXinXin);
				}
				xueShengZuZhiXinXi.setBumen(buMenXinXins);
			}
			if (Integer.parseInt(xueShengZuZhiXinXi.getFuzeren()) == Integer.parseInt(data[0])) {
				xueShengZuZhiXinXi.setZhiwu("负责人");
			}
			if (xueShengZuZhiXinXi.getBianji() != null) {
				if (xueShengZuZhiXinXi.getBianji().equals(data[0])) {
					StringBuffer sBuffer = new StringBuffer();
					if (xueShengZuZhiXinXi.getZhiwu() == null || xueShengZuZhiXinXi.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					} else {
						sBuffer = sBuffer.append(xueShengZuZhiXinXi.getZhiwu());
						sBuffer = sBuffer.append(",编辑");
					}
					xueShengZuZhiXinXi.setZhiwu(sBuffer.toString());
				}
			}
			if (xueShengZuZhiXinXi.getBumen() != null && !xueShengZuZhiXinXi.getBumen().isEmpty()) {
				for (SheTuanBuMenXinXin sheTuanBuMenXinXin : xueShengZuZhiXinXi.getBumen()) {
					if (sheTuanBuMenXinXin.getBuzhang() == null) {
						continue;
					}
					if (Integer.parseInt(sheTuanBuMenXinXin.getBuzhang()) == Integer.parseInt(data[0])) {
						StringBuffer sBuffer = new StringBuffer();
						if (xueShengZuZhiXinXi.getZhiwu() != null || xueShengZuZhiXinXi.getZhiwu() != "") {
							sBuffer.append(xueShengZuZhiXinXi.getZhiwu());
							sBuffer.append("," + sheTuanBuMenXinXin.getMingcheng() + "部长");
						} else {
							sBuffer.append(sheTuanBuMenXinXin.getMingcheng() + "部长");
						}
						xueShengZuZhiXinXi.setZhiwu(sBuffer.toString());
					}
				}
			}
			if (xueShengZuZhiXinXi.getZhiwu() == null || xueShengZuZhiXinXi.getZhiwu().equals("")) {
				xueShengZuZhiXinXi.setZhiwu("成员");
			}
		}
		return xueShengZuZhiXinXi;
	}

	// 提交申请社团经费表单
	@RequestMapping(value = "app_Subssqjf")
	@ResponseBody
	public String app_SubshetuanShenQingJingFei(HttpServletRequest request) {
		String retInfo = "";
		String yongtu = request.getParameter("yongtu");
		String jine = request.getParameter("jine");
		String xueshengid = request.getParameter("xueshengid");
		String shetuanxinxiid = request.getParameter("shetuanxinxiid");
		if (xueshengid != null && xueshengid != "") {
			SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
					Integer.parseInt(shetuanxinxiid), Integer.parseInt(xueshengid));
			if (sheTuanXinXi == null) {
				retInfo = "dengchu";
				return retInfo;
			}
			SheTuanJingFei sheTuanJingFei = new SheTuanJingFei();
			sheTuanJingFei.setShetuanxinxiid(sheTuanXinXi.getShetuanxinxiid());
			sheTuanJingFei.setJine(Integer.parseInt(jine));
			sheTuanJingFei.setShenqingren(Integer.parseInt(xueshengid));
			sheTuanJingFei.setYongtu(yongtu);
			sheTuanJingFei.setShifoupizhun(0);
			Date time = new Date();
			sheTuanJingFei.setShenqingshijian(time);
			int i = sheTuanService.insertSheTuanJingFeiShenQing(sheTuanJingFei);
			if (i > 0) {
				retInfo = "success";
			} else {
				retInfo = "fail";
			}
		} else {
			retInfo = "dengchu";
		}
		return retInfo;
	}

	// 社团经费申请进度查看
	@RequestMapping(value = "app_JingFeisqjd")
	@ResponseBody
	public JSONObject app_JingFeiShenQingJinDu(HttpServletRequest request) {
		String shetuanxinxiid = request.getParameter("shetuanxinxiid");
		String xueshengid = request.getParameter("xueshengid");
		List<String> xingming = new ArrayList<>();
		Map<Object, Object> map = new HashMap<>();
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
				Integer.parseInt(shetuanxinxiid), Integer.parseInt(xueshengid));
		List<SheTuanJingFei> sheTuanJingFeis = new ArrayList<>();
		map.put("jingfeis", sheTuanJingFeis);
		map.put("shenqingren", xingming);
		if (sheTuanXinXi == null) {
			return JSONObject.fromObject(map);
		}
		sheTuanJingFeis = sheTuanService.selectBySheTuanXinXiID(Integer.parseInt(shetuanxinxiid));
		if (sheTuanJingFeis != null) {
			for (int i = 0; i < sheTuanJingFeis.size(); i++) {
				XueSheng xueSheng = xueShengService.getUserById(sheTuanJingFeis.get(i).getShenqingren());
				xingming.add(xueSheng.getXingming());
			}
		}
		map.put("jingfeis", sheTuanJingFeis);
		map.put("shenqingren", xingming);
		return JSONObject.fromObject(map);
	}

	// 提交学生组织申请经费表
	@RequestMapping(value = "app_Subxsqjf")
	@ResponseBody
	public String app_SubxueshengzuzhiShenQingJingFei(HttpServletRequest request) {
		String retInfo = "";
		String yongtu = request.getParameter("yongtu");
		String jine = request.getParameter("jine");
		String xueshengid = request.getParameter("xueshengid");
		String zuzhixinxiid = request.getParameter("zuzhixinxiid");
		if (xueshengid != null && xueshengid != "") {
			XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
					.selectByXueShengZuZhiXinXiID(Integer.parseInt(zuzhixinxiid));
			if (xueShengZuZhiXinXi == null) {
				retInfo = "dengchu";
				return retInfo;
			}
			SheTuanJingFei sheTuanJingFei = new SheTuanJingFei();
			sheTuanJingFei.setXueshengzuzhixinxiid(xueShengZuZhiXinXi.getXueshengzuzhixinxiid());
			sheTuanJingFei.setJine(Integer.parseInt(jine));
			sheTuanJingFei.setShenqingren(Integer.parseInt(xueshengid));
			sheTuanJingFei.setYongtu(yongtu);
			sheTuanJingFei.setShifoupizhun(0);
			Date time = new Date();
			sheTuanJingFei.setShenqingshijian(time);
			int i = sheTuanService.insertSheTuanJingFeiShenQing(sheTuanJingFei);
			if (i > 0) {
				retInfo = "success";
			} else {
				retInfo = "fail";
			}
		} else {
			retInfo = "dengchu";
		}
		return retInfo;
	}

	// 学生组织申请经费进度查看
	@RequestMapping(value = "app_JingFeiXSsqjd")
	@ResponseBody
	public JSONObject app_XSJingFeiShenQingJinDu(HttpServletRequest request) {
		String zuzhixinxiid = request.getParameter("zuzhixinxiid");
		String xueshengid = request.getParameter("xueshengid");
		List<String> xingming = new ArrayList<>();
		Map<Object, Object> map = new HashMap<>();
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
				.selectByXueShengZuZhiXinXiID(Integer.parseInt(zuzhixinxiid));
		List<SheTuanJingFei> sheTuanJingFeis = new ArrayList<>();
		map.put("jingfeis", sheTuanJingFeis);
		map.put("shenqingren", xingming);
		if (xueshengid == null || xueshengid == "") {
			return JSONObject.fromObject(map);
		}
		if (xueShengZuZhiXinXi == null) {
			return JSONObject.fromObject(map);
		}
		sheTuanJingFeis = sheTuanService.selectSheTuanJinFeiByXueShengZuZhiXinXiID(Integer.parseInt(zuzhixinxiid));
		if (sheTuanJingFeis != null) {
			for (int i = 0; i < sheTuanJingFeis.size(); i++) {
				XueSheng xueSheng = xueShengService.getUserById(sheTuanJingFeis.get(i).getShenqingren());
				xingming.add(xueSheng.getXingming());
			}
		}
		map.put("jingfeis", sheTuanJingFeis);
		map.put("shenqingren", xingming);
		return JSONObject.fromObject(map);
	}

	// 社团指定新社长页面显示
	@RequestMapping(value = "app_ST_ZhiDingXinSheZhang")
	@ResponseBody
	public List<SheTuanRenYuanXinXi> app_ST_ZhiDingXinSheZhang(HttpServletRequest request) {
		String shetuanxinxiid = request.getParameter("shetuanxinxiid");
		String xueshengid = request.getParameter("xueshengid");
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		List<SheTuanRenYuanXinXi> xinXis2 = new ArrayList<>();
		if (xueshengid != null && xueshengid != "") {
			SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
					Integer.parseInt(shetuanxinxiid), Integer.parseInt(xueshengid));
			if (sheTuanXinXi == null) {
				return xinXis2;
			}
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
				banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
				xinXi.setXueshengid(xueSheng.getXueshengid());
				xinXi.setXuehao(xueSheng.getXuehao());
				xinXi.setXingming(xueSheng.getXingming());
				xinXi.setBanji(banJi.getBanjimingcheng());
				xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
				xinXi.setShoujihao(xueSheng.getDianhua());
				if (sheTuanXinXi.getShezhang().equals(xueSheng.getXueshengid().toString())) {
					xinXi.setZhiwu("社长");
				}
				if (sheTuanXinXi.getBianji() != null) {
					if (sheTuanXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
						StringBuffer sBuffer = new StringBuffer();
						if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
							sBuffer = sBuffer.append("编辑");
						} else {
							sBuffer = sBuffer.append(xinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xinXi.setZhiwu(sBuffer.toString());
					}
				}
				List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
						.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
				for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
					SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
							.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(),
									format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%",
									xueSheng.getXueshengid().toString() + ",%");
					if (sheTuanBuMenXinXin != null) {
						if (sheTuanBuMenXinXin.getZhiwu() != null) {
							String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
							for (int j = 0; j < zhiwus.length; j++) {
								String zhiwu[] = zhiwus[j].split(",");
								if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
									StringBuffer sBuffer = new StringBuffer();
									if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
										sBuffer = sBuffer.append(zhiwu[0]);
									} else {
										sBuffer = sBuffer.append(xinXi.getZhiwu());
										sBuffer = sBuffer.append("," + zhiwu[0]);
									}
									xinXi.setZhiwu(sBuffer.toString());
								}
							}
						}
						xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					}
				}
				if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
					xinXi.setZhiwu("成员");
				}
				xinXis.add(xinXi);
			}
		}
		return xinXis;
	}

	// 指定某成员为社长
	@RequestMapping(value = "app_ST_SubZhiDingXinSheZhang")
	@ResponseBody
	public String app_ST_SubZhiDingXinSheZhang(HttpServletRequest request) {
		String shetuanxinxiid = request.getParameter("shetuanxinxiid");
		String xueshengid = request.getParameter("xueshengid");
		String userid = request.getParameter("userid");
		if (userid == null || userid == "")
			return "dengchu";
		if (xueshengid == null || xueshengid == "") {
			return "dengchu";
		}
		if (shetuanxinxiid == null || shetuanxinxiid == "") {
			return "dengchu";
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
				Integer.parseInt(shetuanxinxiid), Integer.parseInt(userid));
		if (sheTuanXinXi == null) {
			return "dengchu";
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			return "dengchu";
		}
		int i = sheTuanService.updateSheZhangBySheTuanXinXiID(Integer.parseInt(shetuanxinxiid),
				Integer.parseInt(xueshengid));
		if (i > 0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueshengid));
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你已被设定为"+sheTuanJiBenXinXi.getMingcheng()+"社长");
			tiXingService.insert(tiXing);
			return "success";
		} else {
			return "fail";
		}
	}

	// 学生组织指定新社长页面显示数据
	@RequestMapping(value = "app_XS_ZhiDingXinSheZhang")
	@ResponseBody
	public List<SheTuanRenYuanXinXi> app_XS_ZhiDingXinSheZhang(HttpServletRequest request) {
		String zuzhixinxiid = request.getParameter("zuzhixinxiid");
		String xueshengid = request.getParameter("xueshengid");
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		if (xueshengid != null && xueshengid != "") {
			XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
					.selectByXueShengZuZhiXinXiID(Integer.parseInt(zuzhixinxiid));
			if (xueShengZuZhiXinXi == null) {
				return xinXis;
			}
			if (!xueShengZuZhiXinXi.getFuzeren().equals(xueshengid)) {
				return xinXis;
			}
			String RenYuanIDs[] = xueShengZuZhiXinXi.getRenyuanids().split(",");
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
				banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
				xinXi.setXueshengid(xueSheng.getXueshengid());
				xinXi.setXuehao(xueSheng.getXuehao());
				xinXi.setXingming(xueSheng.getXingming());
				xinXi.setBanji(banJi.getBanjimingcheng());
				xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
				xinXi.setShoujihao(xueSheng.getDianhua());
				if (xueShengZuZhiXinXi.getFuzeren().equals(xueSheng.getXueshengid().toString())) {
					xinXi.setZhiwu("负责人");
				}
				if (xueShengZuZhiXinXi.getBianji() != null) {
					if (xueShengZuZhiXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
						StringBuffer sBuffer = new StringBuffer();
						if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
							sBuffer = sBuffer.append("编辑");
						} else {
							sBuffer = sBuffer.append(xinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xinXi.setZhiwu(sBuffer.toString());
					}
				}
				List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
						.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
				if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
					for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
						SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
								.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(),
										format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%",
										xueSheng.getXueshengid().toString() + ",%");
						if (sheTuanBuMenXinXin != null) {
							if (sheTuanBuMenXinXin.getZhiwu() != null) {
								String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
								for (int j = 0; j < zhiwus.length; j++) {
									String zhiwu[] = zhiwus[j].split(",");
									if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
										StringBuffer sBuffer = new StringBuffer();
										if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
											sBuffer = sBuffer.append(zhiwu[0]);
										} else {
											sBuffer = sBuffer.append(xinXi.getZhiwu());
											sBuffer = sBuffer.append("," + zhiwu[0]);
										}
										xinXi.setZhiwu(sBuffer.toString());
									}
								}
							}
							xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
						}
					}
				}
				if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
					xinXi.setZhiwu("成员");
				}
				xinXis.add(xinXi);
			}
		}
		return xinXis;
	}

	// 学生组织指定某社员为负责人
	@RequestMapping(value = "app_XS_SubZhiDingXinSheZhang")
	@ResponseBody
	public String app_XS_SubZhiDingXinSheZhang(HttpServletRequest request) {
		String zuzhixinxiid = request.getParameter("zuzhixinxiid");
		String xueshengid = request.getParameter("xueshengid");
		String userid = request.getParameter("userid");
		if (userid == null || userid == "")
			return "dengchu";
		if (xueshengid == null || xueshengid == "") {
			return "dengchu";
		}
		if (zuzhixinxiid == null || zuzhixinxiid == "") {
			return "dengchu";
		}
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
				.selectByXueShengZuZhiXinXiID(Integer.parseInt(zuzhixinxiid));
		if (xueShengZuZhiXinXi == null) {
			return "dengchu";
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(userid)) {
			return "dengchu";
		}
		int i = sheTuanService.updateFuZeRenByXueShengZuZhiXinXiID(xueShengZuZhiXinXi.getXueshengzuzhixinxiid(),
				Integer.parseInt(xueshengid));
		if (i > 0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueshengid));
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你已被设定为"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid())+"负责人");
			tiXingService.insert(tiXing);
			return "success";
		} else {
			return "fail";
		}
	}

	// 社团增删部门页面显示数据
	@RequestMapping(value = "app_ST_ZengShanBuMen")
	@ResponseBody
	public JSONObject app_ST_ZengShanBuMen(HttpServletRequest request) {
		String shetuanxinxiid = request.getParameter("shetuanxinxiid");
		String xueshengid = request.getParameter("xueshengid");
		Map<String, Object> map = new HashMap<>();
		SheTuanXinXi sheTuanXinXi = new SheTuanXinXi();
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = new ArrayList<>();
		map.put("bumen", sheTuanBuMenJiBenXinXins);
		map.put("shetuanid", "");
		if (xueshengid == null || xueshengid == "") {
			return JSONObject.fromObject(map);
		}
		sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(Integer.parseInt(shetuanxinxiid),
				Integer.parseInt(xueshengid));
		if (sheTuanXinXi == null) {
			return JSONObject.fromObject(map);
		}
		sheTuanBuMenJiBenXinXins = sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		map.put("bumen", sheTuanBuMenJiBenXinXins);
		map.put("shetuanid", sheTuanXinXi.getShetuanid());
		return JSONObject.fromObject(map);
	}

	// 学生组织增删部门显示数据
	@RequestMapping(value = "app_XS_ZengShanBuMen")
	@ResponseBody
	public JSONObject app_XS_ZengShanBuMen(HttpServletRequest request) {
		String zuzhixinxiid = request.getParameter("zuzhixinxiid");
		String xueshengid = request.getParameter("xueshengid");
		Map<String, Object> map = new HashMap<>();
		XueShengZuZhiXinXi xueShengZuZhiXinXi = new XueShengZuZhiXinXi();
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = new ArrayList<>();
		map.put("bumen", sheTuanBuMenJiBenXinXins);
		map.put("xueshengzuzhiid", "");
		if (xueshengid == null || xueshengid == "") {
			return JSONObject.fromObject(map);
		}
		xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(zuzhixinxiid));
		if (xueShengZuZhiXinXi == null) {
			return JSONObject.fromObject(map);
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(xueshengid)) {
			return JSONObject.fromObject(map);
		}
		sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		map.put("bumen", sheTuanBuMenJiBenXinXins);
		map.put("xueshengzuzhiid", xueShengZuZhiXinXi.getXueshengzuzhiid());
		return JSONObject.fromObject(map);
	}

	// 社团删除部门
	@RequestMapping(value = "app_ST_DeleteBuMen")
	@ResponseBody
	public String app_ST_DeleteBuMen(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String xueshengid = request.getParameter("xueshengid");
		String ret = "";
		if (!Util.isNumeric(data[0]) || !Util.isNumeric(data[1])) {
			return "denchu";
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
				Integer.parseInt(data[0]), Integer.parseInt(xueshengid));
		if (sheTuanXinXi == null) {
			return "denchu";
		}
		int i = sheTuanService.updateSheTuanBuMenJiBenXinXiZhuangTaiByBuMenID(Integer.parseInt(data[1]));
		if (i > 0) {
			ret = "success";
		} else {
			ret = "fail";
		}
		return ret;
	}

	// 学生组织删除部门
	@RequestMapping(value = "app_XS_DeleteBuMen")
	@ResponseBody
	public String app_XS_DeleteBuMen(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String xueshengid = request.getParameter("xueshengid");
		String ret = "";
		if (!Util.isNumeric(data[0]) || !Util.isNumeric(data[1])) {
			return "dengchu";
		}
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(data[0]));
		if (xueShengZuZhiXinXi == null) {
			return "dengchu";
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(xueshengid)) {
			return "dengchu";
		}
		int i = sheTuanService.updateSheTuanBuMenJiBenXinXiZhuangTaiByBuMenID(Integer.parseInt(data[1]));
		if (i > 0) {
			ret = "success";
		} else {
			ret = "fail";
		}
		return ret;
	}

	// 提交添加社团部门表
	@RequestMapping(value = "app_ST_SubAddBuMen")
	@ResponseBody
	public String app_ST_SubAddBuMen(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String shetuanxinxiid = request.getParameter("shetuanxinxiid");
		String zhize = request.getParameter("zhize");
		String ming = request.getParameter("ming");
		String ret = "";
		if (xueshengid == null || xueshengid == "") {
			return "dengchu";
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
				Integer.parseInt(shetuanxinxiid), Integer.parseInt(xueshengid));
		if (sheTuanXinXi == null) {
			return "dengchu";
		}
		SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin = sheTuanService
				.selectSheTuanBuMenJiBenXinXiBySheTuanIDAndMingCheng(sheTuanXinXi.getShetuanid(), ming);
		if (sheTuanBuMenJiBenXinXin != null) {
			ret = "same";
		} else {
			SheTuanBuMenJiBenXinXin xin = new SheTuanBuMenJiBenXinXin();
			xin.setShetuanid(sheTuanXinXi.getShetuanid());
			xin.setBumenmingcheng(ming);
			xin.setZhize(zhize);
			xin.setZhuangtai("1");
			xin.setChuangjianshijian(new Date());
			int i = sheTuanService.insertSheTuanBuMenJiBenXinXi(xin);
			if (i > 0) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy");
				SheTuanBuMenXinXin sheTuanBuMenXinXin = new SheTuanBuMenXinXin();
				sheTuanBuMenXinXin.setBumenid(xin.getBumenid());
				sheTuanBuMenXinXin.setNiandu(Integer.parseInt(format.format(new Date())));
				int j = sheTuanService.insertSheTuanBuMenXinXi(sheTuanBuMenXinXin);
				if (j > 0) {
					ret = "success";
				} else {
					ret = "fail";
				}
			} else {
				ret = "fail";
			}
		}
		return ret;
	}

	// 提交添加学生组织部门表
	@RequestMapping(value = "app_XS_SubAddBuMen")
	@ResponseBody
	public String app_XS_SubAddBuMen(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String zuzhixinxiid = request.getParameter("zuzhixinxiid");
		String zhize = request.getParameter("zhize");
		String ming = request.getParameter("ming");
		String ret = "";
		if (xueshengid == null || xueshengid == "") {
			return "dengchu";
		}
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
				.selectByXueShengZuZhiXinXiID(Integer.parseInt(zuzhixinxiid));
		if (xueShengZuZhiXinXi == null) {
			return "dengchu";
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(xueshengid)) {
			return "dengchu";
		}
		SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin = sheTuanService
				.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiIDAndMingCheng(xueShengZuZhiXinXi.getXueshengzuzhiid(),
						ming);
		if (sheTuanBuMenJiBenXinXin != null) {
			ret = "same";
		} else {
			SheTuanBuMenJiBenXinXin xin = new SheTuanBuMenJiBenXinXin();
			xin.setXueshengzuzhiid(xueShengZuZhiXinXi.getXueshengzuzhiid());
			xin.setBumenmingcheng(ming);
			xin.setZhize(zhize);
			xin.setZhuangtai("1");
			xin.setChuangjianshijian(new Date());
			int i = sheTuanService.insertSheTuanBuMenJiBenXinXi(xin);
			if (i > 0) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy");
				SheTuanBuMenXinXin sheTuanBuMenXinXin = new SheTuanBuMenXinXin();
				sheTuanBuMenXinXin.setBumenid(xin.getBumenid());
				sheTuanBuMenXinXin.setNiandu(Integer.parseInt(format.format(new Date())));
				int j = sheTuanService.insertSheTuanBuMenXinXi(sheTuanBuMenXinXin);
				if (j > 0) {
					ret = "success";
				} else {
					ret = "fail";
				}
			} else {
				ret = "fail";
			}
		}
		return ret;
	}

	// 社团指定部长页面显示数据
	@RequestMapping(value = "app_ST_ZhiDingBuzhang")
	@ResponseBody
	public JSONObject app_ST_ZhiDingBuzhang(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String shetuanxinxiid = request.getParameter("shetuanxinxiid");
		Map<String, Object> map = new HashMap<>();
		map.put("bumenxinxiid", "fff");
		if (!Util.isNumeric(shetuanxinxiid)) {
			return JSONObject.fromObject(map);
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
				Integer.parseInt(shetuanxinxiid), Integer.parseInt(xueshengid));
		if (sheTuanXinXi == null) {
			return JSONObject.fromObject(map);
		}
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		String bumen = request.getParameter("bumen");
		if (bumen == null || bumen.equals("")) {
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
				banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
				xinXi.setXueshengid(xueSheng.getXueshengid());
				xinXi.setXuehao(xueSheng.getXuehao());
				xinXi.setXingming(xueSheng.getXingming());
				xinXi.setBanji(banJi.getBanjimingcheng());
				xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
				xinXi.setShoujihao(xueSheng.getDianhua());
				if (sheTuanXinXi.getShezhang().equals(xueSheng.getXueshengid().toString())) {
					xinXi.setZhiwu("社长");
				}
				if (sheTuanXinXi.getBianji() != null) {
					if (sheTuanXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
						StringBuffer sBuffer = new StringBuffer();
						if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
							sBuffer = sBuffer.append("编辑");
						} else {
							sBuffer = sBuffer.append(xinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xinXi.setZhiwu(sBuffer.toString());
					}
				}
				List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
						.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
				for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
					SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
							.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(),
									format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%",
									xueSheng.getXueshengid().toString() + ",%");
					if (sheTuanBuMenXinXin != null) {
						if (sheTuanBuMenXinXin.getZhiwu() != null) {
							String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
							for (int j = 0; j < zhiwus.length; j++) {
								String zhiwu[] = zhiwus[j].split(",");
								if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
									StringBuffer sBuffer = new StringBuffer();
									if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
										sBuffer = sBuffer.append(zhiwu[0]);
									} else {
										sBuffer = sBuffer.append(xinXi.getZhiwu());
										sBuffer = sBuffer.append("," + zhiwu[0]);
									}
									xinXi.setZhiwu(sBuffer.toString());
								}
							}
						}
						xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
						xinXi.setBumenid(sheTuanBuMenXinXin.getShetuanbumenxinxiid());
					}
				}
				if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
					xinXi.setZhiwu("成员");
				}
				xinXis.add(xinXi);
			}
		} else {
			SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
					.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(Integer.parseInt(bumen));
			SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin = sheTuanService
					.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid());
			if (sheTuanBuMenXinXin != null) {
				String buMenRenYuans[] = sheTuanBuMenXinXin.getRenyuanids().split(",");
				SheTuanRenYuanXinXi xinXi = null;
				XueSheng xueSheng = null;
				BanJi banJi = null;
				for (int i = 0; i < buMenRenYuans.length; i++) {
					xinXi = new SheTuanRenYuanXinXi();
					xueSheng = new XueSheng();
					banJi = new BanJi();
					xueSheng = xueShengService.getUserById(Integer.parseInt(buMenRenYuans[i]));
					banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
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
					if (sheTuanXinXi.getBianji() != null) {
						if (sheTuanXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
							StringBuffer sBuffer = new StringBuffer();
							if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
								sBuffer = sBuffer.append("编辑");
							} else {
								sBuffer = sBuffer.append(xinXi.getZhiwu());
								sBuffer = sBuffer.append(",编辑");
							}
							xinXi.setZhiwu(sBuffer.toString());
						}
					}
					String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
					for (int j = 0; j < zhiwus.length; j++) {
						String zhiwu[] = zhiwus[j].split(",");
						if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
							StringBuffer sBuffer = new StringBuffer();
							if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
								sBuffer = sBuffer.append(zhiwu[0]);
							} else {
								sBuffer = sBuffer.append(xinXi.getZhiwu());
								sBuffer = sBuffer.append("," + zhiwu[0]);
							}
							xinXi.setZhiwu(sBuffer.toString());
						}
					}
					xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
						xinXi.setZhiwu("成员");
					}
					xinXis.add(xinXi);
				}
			}
		}
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		List<SheTuanBuMenXinXin> sheTuanBuMenXinXins = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(
						sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
				if (xinXin != null) {
					xinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					sheTuanBuMenXinXins.add(xinXin);
				}
			}
		}
		map.put("bumen", sheTuanBuMenXinXins);
		map.put("renyuans", xinXis);
		map.put("bumenxinxiid", bumen);
		return JSONObject.fromObject(map);
	}

	// 学生组织指定部长页面显示数据
	@RequestMapping(value = "app_XS_ZhiDingBuzhang")
	@ResponseBody
	public JSONObject app_XS_ZhiDingBuzhang(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String zuzhixinxiid = request.getParameter("zuzhixinxiid");
		Map<String, Object> map = new HashMap<>();
		map.put("bumenxinxiid", "fff");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
				.selectByXueShengZuZhiXinXiID(Integer.parseInt(zuzhixinxiid));
		if (xueShengZuZhiXinXi == null) {
			return null;
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(xueshengid)) {
			return null;
		}
		List<SheTuanRenYuanXinXi> xinXis = new ArrayList<>();
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		String bumen = request.getParameter("bumen");
		if (bumen == null || bumen.equals("")) {
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
				banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
				xinXi.setXueshengid(xueSheng.getXueshengid());
				xinXi.setXuehao(xueSheng.getXuehao());
				xinXi.setXingming(xueSheng.getXingming());
				xinXi.setBanji(banJi.getBanjimingcheng());
				xinXi.setXueyuan(yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getYuanximingcheng());
				xinXi.setShoujihao(xueSheng.getDianhua());
				if (xueShengZuZhiXinXi.getFuzeren().equals(xueSheng.getXueshengid().toString())) {
					xinXi.setZhiwu("负责人");
				}
				if (xueShengZuZhiXinXi.getBianji() != null) {
					if (xueShengZuZhiXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
						StringBuffer sBuffer = new StringBuffer();
						if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
							sBuffer = sBuffer.append("编辑");
						} else {
							sBuffer = sBuffer.append(xinXi.getZhiwu());
							sBuffer = sBuffer.append(",编辑");
						}
						xinXi.setZhiwu(sBuffer.toString());
					}
				}
				if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
					for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
						SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
								.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(sheTuanBuMenJiBenXinXin.getBumenid(),
										format.format(date), "%," + xueSheng.getXueshengid().toString() + ",%",
										xueSheng.getXueshengid().toString() + ",%");
						if (sheTuanBuMenXinXin != null) {
							if (sheTuanBuMenXinXin.getZhiwu() != null) {
								String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
								for (int j = 0; j < zhiwus.length; j++) {
									String zhiwu[] = zhiwus[j].split(",");
									if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
										StringBuffer sBuffer = new StringBuffer();
										if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
											sBuffer = sBuffer.append(zhiwu[0]);
										} else {
											sBuffer = sBuffer.append(xinXi.getZhiwu());
											sBuffer = sBuffer.append("," + zhiwu[0]);
										}
										xinXi.setZhiwu(sBuffer.toString());
									}
								}
							}
							xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
							xinXi.setBumenid(sheTuanBuMenXinXin.getShetuanbumenxinxiid());
						}
					}
				}
				if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
					xinXi.setZhiwu("成员");
				}
				xinXis.add(xinXi);
			}
		} else {
			SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
					.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(Integer.parseInt(bumen));
			SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin = sheTuanService
					.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid());
			if (sheTuanBuMenXinXin != null) {
				if (sheTuanBuMenXinXin.getRenyuanids() != null) {
					String buMenRenYuans[] = sheTuanBuMenXinXin.getRenyuanids().split(",");
					SheTuanRenYuanXinXi xinXi = null;
					XueSheng xueSheng = null;
					BanJi banJi = null;
					for (int i = 0; i < buMenRenYuans.length; i++) {
						xinXi = new SheTuanRenYuanXinXi();
						xueSheng = new XueSheng();
						banJi = new BanJi();
						xueSheng = xueShengService.getUserById(Integer.parseInt(buMenRenYuans[i]));
						banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
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
						if (xueShengZuZhiXinXi.getBianji() != null) {
							if (xueShengZuZhiXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
								StringBuffer sBuffer = new StringBuffer();
								if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
									sBuffer = sBuffer.append("编辑");
								} else {
									sBuffer = sBuffer.append(xinXi.getZhiwu());
									sBuffer = sBuffer.append(",编辑");
								}
								xinXi.setZhiwu(sBuffer.toString());
							}
						}
						if (sheTuanBuMenXinXin.getZhiwu() != null) {
							String zhiwus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
							for (int j = 0; j < zhiwus.length; j++) {
								String zhiwu[] = zhiwus[j].split(",");
								if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
									StringBuffer sBuffer = new StringBuffer();
									if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
										sBuffer = sBuffer.append(zhiwu[0]);
									} else {
										sBuffer = sBuffer.append(xinXi.getZhiwu());
										sBuffer = sBuffer.append("," + zhiwu[0]);
									}
									xinXi.setZhiwu(sBuffer.toString());
								}
							}
						}
						xinXi.setBumen(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
						if (xinXi.getZhiwu() == null || xinXi.getZhiwu().equals("")) {
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
				SheTuanBuMenXinXin xinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(
						sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
				if (xinXin != null) {
					xinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
					sheTuanBuMenXinXins.add(xinXin);
				}
			}
		}
		map.put("bumen", sheTuanBuMenXinXins);
		map.put("renyuans", xinXis);
		map.put("bumenxinxiid", bumen);
		return JSONObject.fromObject(map);
	}

	// 社团设置部长
	@RequestMapping(value = "app_ST_SheZhiBuzhang")
	@ResponseBody
	public String app_ST_SheZhiBuzhang(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String xueshengid = request.getParameter("xueshengid");
		String sheTuanXinXiID = data[0];
		String xueShengID = data[1];
		String buMenXinXiID = data[2];
		if (!Util.isNumeric(xueShengID) || !Util.isNumeric(buMenXinXiID) || !Util.isNumeric(sheTuanXinXiID)) {
			return "dengchu";
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
				Integer.parseInt(sheTuanXinXiID), Integer.parseInt(xueshengid));
		SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
				.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(Integer.parseInt(buMenXinXiID));
		if (sheTuanXinXi == null) {
			return "dengchu";
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			return "dengchu";
		}
		sheTuanBuMenXinXin.setBuzhang(xueShengID);
		if (sheTuanBuMenXinXin.getZhiwu() != null) {
			String zhiWus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
			StringBuffer sBuffer = new StringBuffer();
			for (int i = 0; i < zhiWus.length; i++) {
				if (zhiWus[i].startsWith("部长")) {
					sBuffer.append("部长," + xueShengID + ";");
				} else {
					sBuffer.append(zhiWus[i]);
				}
			}
			sheTuanBuMenXinXin.setZhiwu(sBuffer.toString());
		} else {
			sheTuanBuMenXinXin.setZhiwu("部长," + xueShengID + ";");
		}
		boolean isIn;
		if (sheTuanBuMenXinXin.getRenyuanids() != null) {
			String sheYuanS = sheTuanBuMenXinXin.getRenyuanids();
			if (sheYuanS.startsWith(xueShengID + ",")) {
				isIn = true;
			} else {
				if (sheYuanS.lastIndexOf("," + xueShengID + ",") != -1) {
					isIn = true;
				} else {
					isIn = false;
				}
			}
		} else {
			isIn = false;
		}
		if (isIn == false) {
			StringBuffer sBuffer2 = null;
			if (sheTuanBuMenXinXin.getRenyuanids() == null) {
				sBuffer2 = new StringBuffer();
			} else {
				sBuffer2 = new StringBuffer(sheTuanBuMenXinXin.getRenyuanids());
			}
			sBuffer2 = sBuffer2.append(xueShengID + ",");
			sheTuanBuMenXinXin.setRenyuanids(sBuffer2.toString());
			int renshu = Integer.parseInt(sheTuanBuMenXinXin.getBumenrenshu()) + 1;
			sheTuanBuMenXinXin.setBumenrenshu(String.valueOf(renshu));
		}
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String xueshengid1 = "%," + xueShengID + ",%";
		String xueshengid2 = xueShengID + ",%";
		if (sheTuanBuMenJiBenXinXins != null) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(
						sheTuanBuMenJiBenXinXin.getBumenid(), format.format(new Date()), xueshengid1, xueshengid2);
				if (xin == null || xin.getShetuanbumenxinxiid() == sheTuanBuMenXinXin.getShetuanbumenxinxiid()) {
					continue;
				} else {
					String renYuanID[] = xin.getRenyuanids().split(",");
					StringBuffer renYuanS = new StringBuffer();
					for (int i = 0; i < renYuanID.length; i++) {
						if (renYuanID[i].equals(xueShengID)) {
							continue;
						} else {
							renYuanS.append(renYuanID[i] + ",");
						}
					}
					xin.setRenyuanids(renYuanS.toString());
					xin.setBumenrenshu(String.valueOf(Integer.parseInt(xin.getBumenrenshu()) - 1));
					try {
						sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(xin);
					} catch (Exception e) {

					}
				}
			}
		}
		String ret = "";
		int i = sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(sheTuanBuMenXinXin);
		if (i > 0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueShengID));
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你被设为"+sheTuanJiBenXinXi.getMingcheng()+""+sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid()).getBumenmingcheng()+"部长");
			tiXingService.insert(tiXing);
			ret = "success";
		} else {
			ret = "fail";
		}
		return ret;
	}

	// 学生组织设置部长
	@RequestMapping(value = "app_XS_SheZhiBuzhang")
	@ResponseBody
	public String app_XS_SheZhiBuzhang(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String xueshengid = request.getParameter("xueshengid");
		String zuzhixinxiid = data[0];
		String xueShengID = data[1];
		String buMenXinXiID = data[2];
		if (!Util.isNumeric(xueShengID) || !Util.isNumeric(buMenXinXiID) || !Util.isNumeric(zuzhixinxiid)) {
			return "dengchu";
		}
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
				.selectByXueShengZuZhiXinXiID(Integer.parseInt(zuzhixinxiid));
		if (xueShengZuZhiXinXi == null) {
			return "dengchu";
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(xueshengid)) {

			return "dengchu";
		}
		SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
				.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(Integer.parseInt(buMenXinXiID));
		sheTuanBuMenXinXin.setBuzhang(xueShengID);
		if (sheTuanBuMenXinXin.getZhiwu() != null) {
			String zhiWus[] = sheTuanBuMenXinXin.getZhiwu().split(";");
			StringBuffer sBuffer = new StringBuffer();
			for (int i = 0; i < zhiWus.length; i++) {
				if (zhiWus[i].startsWith("部长")) {
					sBuffer.append("部长," + xueShengID + ";");
				} else {
					sBuffer.append(zhiWus[i]);
				}
			}
			sheTuanBuMenXinXin.setZhiwu(sBuffer.toString());
		} else {
			sheTuanBuMenXinXin.setZhiwu("部长," + xueShengID + ";");
		}
		boolean isIn;
		if (sheTuanBuMenXinXin.getRenyuanids() != null) {
			String sheYuanS = sheTuanBuMenXinXin.getRenyuanids();
			if (sheYuanS.startsWith(xueShengID + ",")) {
				isIn = true;
			} else {
				if (sheYuanS.lastIndexOf("," + xueShengID + ",") != -1) {
					isIn = true;
				} else {
					isIn = false;
				}
			}
		} else {
			isIn = false;
		}
		if (isIn == false) {
			StringBuffer sBuffer2 = null;
			if (sheTuanBuMenXinXin.getRenyuanids() == null) {
				sBuffer2 = new StringBuffer();
			} else {
				sBuffer2 = new StringBuffer(sheTuanBuMenXinXin.getRenyuanids());
			}
			sBuffer2 = sBuffer2.append(xueShengID + ",");
			sheTuanBuMenXinXin.setRenyuanids(sBuffer2.toString());
			int renshu = Integer.parseInt(sheTuanBuMenXinXin.getBumenrenshu()) + 1;
			sheTuanBuMenXinXin.setBumenrenshu(String.valueOf(renshu));
		}
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String xueshengid1 = "%," + xueShengID + ",%";
		String xueshengid2 = xueShengID + ",%";
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(
						sheTuanBuMenJiBenXinXin.getBumenid(), format.format(new Date()), xueshengid1, xueshengid2);
				if (xin == null || xin.getShetuanbumenxinxiid() == sheTuanBuMenXinXin.getShetuanbumenxinxiid()) {
					continue;
				} else {
					String renYuanID[] = xin.getRenyuanids().split(",");
					StringBuffer renYuanS = new StringBuffer();
					for (int i = 0; i < renYuanID.length; i++) {
						if (renYuanID[i].equals(xueShengID)) {
							continue;
						} else {
							renYuanS.append(renYuanID[i] + ",");
						}
					}
					try {
						sheTuanService.updateSheTuanBuMenRenYuanByBuMenXinXiID(xin.getShetuanbumenxinxiid(),
								renYuanS.toString());
					} catch (Exception e) {
					}
				}
			}
		}
		String ret = "";
		int i = sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(sheTuanBuMenXinXin);
		if (i > 0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueShengID));
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你被设为"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid()).getMingcheng()+""+sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid()).getBumenmingcheng()+"部长");
			tiXingService.insert(tiXing);
			ret = "success";
		} else {
			ret = "fail";
		}
		return ret;
	}

	// 社团设置某成员为编辑
	@RequestMapping(value = "app_ST_SubZhiDingBianJi")
	@ResponseBody
	public String app_ST_SubZhiDingBianJi(HttpServletRequest request) {
		String shetuanxinxiid = request.getParameter("shetuanxinxiid");
		String xueshengid = request.getParameter("xueshengid");
		String userid = request.getParameter("userid");
		if (!Util.isNumeric(shetuanxinxiid) || !Util.isNumeric(xueshengid)) {
			return "dengchu";
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
				Integer.parseInt(shetuanxinxiid), Integer.parseInt(userid));
		if (sheTuanXinXi == null) {
			return "dengchu";
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			return "dengchu";
		}
		String xueshengid1 = "%," + xueshengid + ",%";
		String xueshengid2 = xueshengid + ",%";
		SheTuanXinXi xinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndXueShengID(
				Integer.parseInt(shetuanxinxiid), xueshengid1, xueshengid2);
		if (xinXi == null) {
			return "dengchu";
		}
		sheTuanXinXi.setBianji(xueshengid);
		int i = sheTuanService.updateSheTuanXinXi(sheTuanXinXi);
		String ret = "";
		if (i > 0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueshengid));
			tiXing.setShijian(new Date());
			tiXing.setZhuangtai(0);
			tiXing.setNeirong("你被设定为"+sheTuanJiBenXinXi.getMingcheng()+"编辑");
			tiXingService.insert(tiXing);
			ret = "success";
		} else {
			ret = "fail";
		}
		return ret;
	}

	// 学生组织设置某社员为编辑
	@RequestMapping(value = "app_XS_SubZhiDingBianJi")
	@ResponseBody
	public String app_XS_SubZhiDingBianJi(HttpServletRequest request) {
		String zuzhixinxiid = request.getParameter("zuzhixinxiid");
		String xueshengid = request.getParameter("xueshengid");
		String userid = request.getParameter("userid");
		if (!Util.isNumeric(zuzhixinxiid) || !Util.isNumeric(xueshengid)) {
			return "dengchu";
		}
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
				.selectByXueShengZuZhiXinXiID(Integer.parseInt(zuzhixinxiid));
		if (xueShengZuZhiXinXi == null) {
			return "dengchu";
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(userid)) {
			return "dengchu";
		}
		String xueshengid1 = "%," + xueshengid + ",%";
		String xueshengid2 = xueshengid + ",%";
		XueShengZuZhiXinXi xinXi = sheTuanService.selectXueShengZuZhiXinXiByIDAndXueShengID(
				xueShengZuZhiXinXi.getXueshengzuzhixinxiid(), xueshengid1, xueshengid2);
		if (xinXi == null) {
			return "dengchu";
		}
		xueShengZuZhiXinXi.setBianji(xueshengid);
		int i = sheTuanService.updateXueShengZuZhiXinXi(xueShengZuZhiXinXi);
		String ret = "";
		if (i > 0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueshengid));
			tiXing.setShijian(new Date());
			tiXing.setZhuangtai(0);
			tiXing.setNeirong("你被设定为"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid()).getMingcheng()+"编辑");
			tiXingService.insert(tiXing);
			ret = "success";
		} else {
			ret = "fail";
		}
		return ret;
	}

	// 社团查看社员信息中移入部门操作
	@RequestMapping(value = "app_ST_SheYuanYiRuBuMen")
	@ResponseBody
	public String app_ST_SheYuanYiRuBuMen(HttpServletRequest request) {
		String data[] = request.getParameter("CODE").split(",zytech,");
		String xueShengID = data[0];
		String sheTuanXinXiID = data[1];
		String buMenXinXiID = data[2];
		String userid = request.getParameter("xueshengid");
		if (!Util.isNumeric(xueShengID) || !Util.isNumeric(sheTuanXinXiID) || !Util.isNumeric(buMenXinXiID)) {
			return null;
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
				Integer.parseInt(sheTuanXinXiID), Integer.parseInt(userid));
		if (sheTuanXinXi == null) {
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String xueshengid1 = "%," + xueShengID + ",%";
		String xueshengid2 = xueShengID + ",%";
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(
						sheTuanBuMenJiBenXinXin.getBumenid(), format.format(new Date()), xueshengid1, xueshengid2);
				if (xin == null || xin.getShetuanbumenxinxiid() == Integer.parseInt(buMenXinXiID)) {
					continue;
				} else {
					if (xin.getRenyuanids() != null) {
						String renYuanIDs[] = xin.getRenyuanids().split(",");
						StringBuffer sBuffer = new StringBuffer();
						for (int i = 0; i < renYuanIDs.length; i++) {
							if (renYuanIDs[i].equals(xueShengID)) {
								continue;
							} else {
								sBuffer.append(renYuanIDs[i] + ",");
							}
						}
						xin.setRenyuanids(sBuffer.toString());
						xin.setBumenrenshu(String.valueOf(Integer.parseInt(xin.getBumenrenshu()) - 1));
						try {
							sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(xin);
						} catch (Exception e) {
						}
					}
				}
			}
		}
		SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
				.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(Integer.parseInt(buMenXinXiID));
		StringBuffer sBuffer = null;
		if (sheTuanBuMenXinXin.getRenyuanids() != null) {
			sBuffer = new StringBuffer(sheTuanBuMenXinXin.getRenyuanids());
		} else {
			sBuffer = new StringBuffer();
		}
		sBuffer.append(xueShengID + ",");
		sheTuanBuMenXinXin.setRenyuanids(sBuffer.toString());
		sheTuanBuMenXinXin.setBumenrenshu(String.valueOf(Integer.parseInt(sheTuanBuMenXinXin.getBumenrenshu()) + 1));
		int i = sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(sheTuanBuMenXinXin);
		String ret = "";
		if (i > 0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueShengID));
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你被移入"+sheTuanJiBenXinXi.getMingcheng()+""+sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid()).getBumenmingcheng());
			tiXingService.insert(tiXing);
			ret = "success";
		} else {
			ret = "fail";
		}
		return ret;
	}

	// 学生组织查看人员信息中移入部门操作
	@RequestMapping(value = "app_XS_RenYuanYiRuBuMen")
	@ResponseBody
	public String app_XS_RenYuanYiRuBuMen(HttpServletRequest request) {
		String data[] = request.getParameter("CODE").split(",zytech,");
		String xueShengID = data[0];
		String xueShengZuZhiXinXiID = data[1];
		String buMenXinXiID = data[2];
		String userid = request.getParameter("xueshengid");
		if (!Util.isNumeric(xueShengID) || !Util.isNumeric(xueShengZuZhiXinXiID) || !Util.isNumeric(buMenXinXiID)) {
			return "dengchu";
		}
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
				.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueShengZuZhiXinXiID));
		if (xueShengZuZhiXinXi == null) {
			return "dengchu";
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(userid)) {
			return "dengchu";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String xueshengid1 = "%," + xueShengID + ",%";
		String xueshengid2 = xueShengID + ",%";
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(
						sheTuanBuMenJiBenXinXin.getBumenid(), format.format(new Date()), xueshengid1, xueshengid2);
				if (xin == null || xin.getShetuanbumenxinxiid() == Integer.parseInt(buMenXinXiID)) {
					continue;
				} else {
					if (xin.getRenyuanids() != null) {
						String renYuanIDs[] = xin.getRenyuanids().split(",");
						StringBuffer sBuffer = new StringBuffer();
						for (int i = 0; i < renYuanIDs.length; i++) {
							if (renYuanIDs[i].equals(xueShengID)) {
								continue;
							} else {
								sBuffer.append(renYuanIDs[i] + ",");
							}
						}
						xin.setRenyuanids(sBuffer.toString());
						xin.setBumenrenshu(String.valueOf(Integer.parseInt(xin.getBumenrenshu()) - 1));
						try {
							sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(xin);
						} catch (Exception e) {
						}
					}
				}
			}
		}
		SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
				.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(Integer.parseInt(buMenXinXiID));
		StringBuffer sBuffer = null;
		if (sheTuanBuMenXinXin.getRenyuanids() != null) {
			sBuffer = new StringBuffer(sheTuanBuMenXinXin.getRenyuanids());
		} else {
			sBuffer = new StringBuffer();
		}
		sBuffer.append(xueShengID + ",");
		sheTuanBuMenXinXin.setRenyuanids(sBuffer.toString());
		sheTuanBuMenXinXin.setBumenrenshu(String.valueOf(Integer.parseInt(sheTuanBuMenXinXin.getBumenrenshu()) + 1));
		int i = sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(sheTuanBuMenXinXin);
		String ret = "";
		if (i > 0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(Integer.parseInt(xueShengID));
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你被移入"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid()).getMingcheng()+""+sheTuanService.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid()).getBumenmingcheng());
			tiXingService.insert(tiXing);
			ret = "success";
		} else {
			ret = "fail";
		}
		return ret;
	}

	// 社团解散判断
	@RequestMapping(value = "app_ST_SheTuanJieSan")
	@ResponseBody
	public String app_ST_SheTuanJieSan(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String shetuanxinxiid = request.getParameter("shetuanxinxiid");
		if (!Util.isNumeric(shetuanxinxiid)) {
			return "dengchu";
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiID(Integer.parseInt(shetuanxinxiid));
		if (sheTuanXinXi == null) {
			return "dengchu";
		}
		if (!sheTuanXinXi.getShezhang().equals(xueshengid)) {
			return "dengchu";
		}
		sheTuanXinXi.setMingcheng(
				sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid()).getMingcheng());
		SheTuanJieSan sheTuanJieSan = sheTuanService.selectSheTuanJieSanBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJieSan != null) {
			return "done";
		} else {
			return "noti";
		}
	}

	// 提交解散社团申请表
	@RequestMapping(value = "app_Subsjsst")
	@ResponseBody
	public String app_Subjiesanshetuan(HttpServletRequest request) {
		String shetuanxinxiid = request.getParameter("shetuanxinxiid");
		String xueshengid = request.getParameter("xueshengid");
		String liyou = request.getParameter("liyou");
		if (!Util.isNumeric(shetuanxinxiid)) {
			return "dengchu";
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
				Integer.parseInt(shetuanxinxiid), Integer.parseInt(xueshengid));
		if (sheTuanXinXi == null) {
			return "dengchu";
		}
		String ret = "";
		SheTuanJieSan sheTuanJieSan = new SheTuanJieSan();
		sheTuanJieSan.setFuzeren(Integer.parseInt(xueshengid));
		sheTuanJieSan.setJiesanyuanyin(liyou);
		sheTuanJieSan.setShetuanid(sheTuanXinXi.getShetuanid());
		int i = sheTuanService.insertSheTuanJieSan(sheTuanJieSan);
		if (i > 0) {
			ret = "success";
		} else {
			ret = "fail";
		}
		return ret;
	}

	// 查看部门社员信息
	@RequestMapping(value = "app_ST_ChaKanBuMenSheYuan")
	@ResponseBody
	public List<SheTuanRenYuanXinXi> app_ST_ChaKanBuMenSheYuan(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String shetuanxinxiid = request.getParameter("shetuanxinxiid");
		List<SheTuanRenYuanXinXi> renyuans = new ArrayList<>();
		if (!Util.isNumeric(shetuanxinxiid)) {
			return renyuans;
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiID(Integer.parseInt(shetuanxinxiid));
		if (sheTuanXinXi == null) {
			return renyuans;
		}
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		SheTuanBuMenXinXin xinXi = null;
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
			SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
					.selectSheTuanBuMenByBuMenIDAndNianDu(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
			if (sheTuanBuMenXinXin.getBuzhang() == null) {
				continue;
			}
			if (sheTuanBuMenXinXin.getBuzhang().equals(xueshengid)) {
				xinXi = sheTuanBuMenXinXin;
			}
		}
		if (xinXi == null) {
			return renyuans;
		}
		String renYuans[] = xinXi.getRenyuanids().split(",");
		SheTuanRenYuanXinXi xin = null;
		XueSheng xueSheng = null;
		BanJi banJi = null;
		for (int i = 0; i < renYuans.length; i++) {
			xin = new SheTuanRenYuanXinXi();
			xueSheng = new XueSheng();
			banJi = new BanJi();
			xueSheng = xueShengService.getUserById(Integer.parseInt(renYuans[i]));
			banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
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
			if (sheTuanXinXi.getBianji() != null) {
				if (sheTuanXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xin.getZhiwu() == null || xin.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					} else {
						sBuffer = sBuffer.append(xin.getZhiwu());
						sBuffer = sBuffer.append(",编辑");
					}
					xin.setZhiwu(sBuffer.toString());
				}
			}
			String zhiwus[] = xinXi.getZhiwu().split(";");
			for (int j = 0; j < zhiwus.length; j++) {
				String zhiwu[] = zhiwus[j].split(",");
				if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xin.getZhiwu() == null || xin.getZhiwu().equals("")) {
						sBuffer = sBuffer.append(zhiwu[0]);
					} else {
						sBuffer = sBuffer.append(xin.getZhiwu());
						sBuffer = sBuffer.append("," + zhiwu[0]);
					}
					xin.setZhiwu(sBuffer.toString());
				}
			}
			SheTuanBuMenJiBenXinXin sBuMenJiBenXinXin = sheTuanService
					.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(xinXi.getBumenid());
			xin.setBumen(sBuMenJiBenXinXin.getBumenmingcheng());
			if (xin.getZhiwu() == null || xin.getZhiwu().equals("")) {
				xin.setZhiwu("成员");
			}
			renyuans.add(xin);
		}
		return renyuans;
	}

	// 学生组织查看部门人员信息
	@RequestMapping(value = "app_XS_ChaKanBuMenRenYuan")
	@ResponseBody
	public List<SheTuanRenYuanXinXi> app_XS_ChaKanBuMenRenYuan(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String zuzhixinxiid = request.getParameter("zuzhixinxiid");
		List<SheTuanRenYuanXinXi> renyuans = new ArrayList<>();
		if (!Util.isNumeric(zuzhixinxiid)) {
			return renyuans;
		}
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
				.selectByXueShengZuZhiXinXiID(Integer.parseInt(zuzhixinxiid));
		if (xueShengZuZhiXinXi == null) {
			return renyuans;
		}
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		SheTuanBuMenXinXin xinXi = null;
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDu(
						sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
				if (sheTuanBuMenXinXin.getBuzhang() == null) {
					continue;
				}
				if (sheTuanBuMenXinXin.getBuzhang().equals(xueshengid)) {
					xinXi = sheTuanBuMenXinXin;
				}
			}
		}

		if (xinXi == null) {
			return renyuans;
		}
		String renYuans[] = xinXi.getRenyuanids().split(",");
		SheTuanRenYuanXinXi xin = null;
		XueSheng xueSheng = null;
		BanJi banJi = null;
		for (int i = 0; i < renYuans.length; i++) {
			xin = new SheTuanRenYuanXinXi();
			xueSheng = new XueSheng();
			banJi = new BanJi();
			xueSheng = xueShengService.getUserById(Integer.parseInt(renYuans[i]));
			banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
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
			if (xueShengZuZhiXinXi.getBianji() != null) {
				if (xueShengZuZhiXinXi.getBianji().equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xin.getZhiwu() == null || xin.getZhiwu().equals("")) {
						sBuffer = sBuffer.append("编辑");
					} else {
						sBuffer = sBuffer.append(xin.getZhiwu());
						sBuffer = sBuffer.append(",编辑");
					}
					xin.setZhiwu(sBuffer.toString());
				}
			}
			String zhiwus[] = xinXi.getZhiwu().split(";");
			for (int j = 0; j < zhiwus.length; j++) {
				String zhiwu[] = zhiwus[j].split(",");
				if (zhiwu[1].equals(xueSheng.getXueshengid().toString())) {
					StringBuffer sBuffer = new StringBuffer();
					if (xin.getZhiwu() == null || xin.getZhiwu().equals("")) {
						sBuffer = sBuffer.append(zhiwu[0]);
					} else {
						sBuffer = sBuffer.append(xin.getZhiwu());
						sBuffer = sBuffer.append("," + zhiwu[0]);
					}
					xin.setZhiwu(sBuffer.toString());
				}
			}
			SheTuanBuMenJiBenXinXin sBuMenJiBenXinXin = sheTuanService
					.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(xinXi.getBumenid());
			xin.setBumen(sBuMenJiBenXinXin.getBumenmingcheng());
			if (xin.getZhiwu() == null || xin.getZhiwu().equals("")) {
				xin.setZhiwu("成员");
			}
			renyuans.add(xin);
		}
		return renyuans;
	}

	// 社团查看纳新报名信息
	@RequestMapping(value = "app_ST_ChaKanBaoMingXinXi")
	@ResponseBody
	public JSONObject app_ST_ChaKanBaoMingXinXi(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String shetuanxinxiid = request.getParameter("shetuanxinxiid");
		List<NaXin> nXins = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("naxins", nXins);
		map.put("tai", "0");
		if (!Util.isNumeric(shetuanxinxiid)) {
			return JSONObject.fromObject(map);
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
				Integer.parseInt(shetuanxinxiid), Integer.parseInt(xueshengid));
		if (sheTuanXinXi == null) {
			return JSONObject.fromObject(map);
		}
		List<NaXin> naXins = sheTuanService.selectNaXinBySheTuanXinXiID(Integer.parseInt(shetuanxinxiid));
		for (NaXin naXin : naXins) {
			if (naXin.getBaomingbumenid() != null) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
						.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(naXin.getBaomingbumenid());
				SheTuanBuMenJiBenXinXin xin = sheTuanService
						.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid());
				if (xin != null) {
					naXin.setBaomingbumen(xin.getBumenmingcheng());
				}
			}
		}
		map.put("naxins", naXins);
		map.put("tai", "1");
		return JSONObject.fromObject(map);
	}

	// 学生组织查看纳新报名信息
	@RequestMapping(value = "app_XS_ChaKanBaoMingXinXi")
	@ResponseBody
	public JSONObject app_XS_ChaKanBaoMingXinXi(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String zuzhixinxiid = request.getParameter("zuzhixinxiid");
		List<NaXin> nXins = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("naxins", nXins);
		map.put("tai", "0");
		if (!Util.isNumeric(zuzhixinxiid)) {
			return JSONObject.fromObject(map);
		}
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
				.selectByXueShengZuZhiXinXiID(Integer.parseInt(zuzhixinxiid));
		if (xueShengZuZhiXinXi == null) {
			return JSONObject.fromObject(map);
		}
		if (!xueShengZuZhiXinXi.getFuzeren().equals(xueshengid)) {
			return JSONObject.fromObject(map);
		}
		List<NaXin> naXins = sheTuanService.selectNaXinByXueShengZuZhiXinXiID(Integer.parseInt(zuzhixinxiid));
		for (NaXin naXin : naXins) {
			if (naXin.getBaomingbumenid() != null) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
						.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(naXin.getBaomingbumenid());
				SheTuanBuMenJiBenXinXin xin = sheTuanService
						.selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(sheTuanBuMenXinXin.getBumenid());
				if (xin != null) {
					naXin.setBaomingbumen(xin.getBumenmingcheng());
				}
			}
		}
		map.put("naxins", naXins);
		map.put("tai", "1");
		return JSONObject.fromObject(map);
	}

	// 同意加入社团请求
	@RequestMapping(value = "app_ST_AcceptBaoMing")
	@ResponseBody
	public String app_ST_AcceptBaoMing(HttpServletRequest request) {
		String naxinid = request.getParameter("naxinid");
		String xueshengid = request.getParameter("xueshengid");
		if (!Util.isNumeric(naxinid)) {
			return "dengchu";
		}
		NaXin naXin = sheTuanService.selectNaXinByNaXinID(Integer.parseInt(naxinid));
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
				naXin.getBaomingshetuanid(), Integer.parseInt(xueshengid));
		if (sheTuanXinXi == null) {
			return "dengchu";
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			return "dengchu";
		}
		String ret = "";
		int i = sheTuanService.updateNaXinZhuangTaiByNaXinID(1, Integer.parseInt(naxinid));
		if (i > 0) {
			StringBuffer sheTuanRenYuans = new StringBuffer(sheTuanXinXi.getSheyuanids());
			sheTuanRenYuans = sheTuanRenYuans.append(naXin.getXueshengid() + ",");
			int j = sheTuanService.updateSheTuanRenYuanBySheTuanXinXiID(sheTuanXinXi.getShetuanxinxiid(),
					sheTuanRenYuans.toString());
			if (naXin.getBaomingbumenid() != null) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
						.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(naXin.getBaomingbumenid());
				StringBuffer buMenRenYuans = null;
				if (sheTuanBuMenXinXin.getRenyuanids() == null) {
					buMenRenYuans = new StringBuffer();
				} else {
					buMenRenYuans = new StringBuffer(sheTuanBuMenXinXin.getRenyuanids());
				}
				buMenRenYuans = buMenRenYuans.append(naXin.getXueshengid() + ",");
				int k = sheTuanService.updateSheTuanBuMenRenYuanByBuMenXinXiID(
						sheTuanBuMenXinXin.getShetuanbumenxinxiid(), buMenRenYuans.toString());
				if (j > 0 && k > 0) {
					TiXing tiXing = new TiXing();
					tiXing.setJieshourenid(naXin.getXueshengid());
					tiXing.setZhuangtai(0);
					tiXing.setShijian(new Date());
					tiXing.setNeirong("你的"+sheTuanJiBenXinXi.getMingcheng()+"报名已通过");
					tiXingService.insert(tiXing);
					ret = "success";
				}
			} else {
				if (j > 0) {
					TiXing tiXing = new TiXing();
					tiXing.setJieshourenid(naXin.getXueshengid());
					tiXing.setZhuangtai(0);
					tiXing.setShijian(new Date());
					tiXing.setNeirong("你的"+sheTuanJiBenXinXi.getMingcheng()+"报名已通过");
					tiXingService.insert(tiXing);
					ret = "success";
				}
			}
		} else {
			ret = "fail";
		}
		return ret;
	}

	// 学生组织同意加入申请
	@RequestMapping(value = "app_XS_AcceptBaoMing")
	@ResponseBody
	public String app_XS_AcceptBaoMing(HttpServletRequest request) {
		String naxinid = request.getParameter("naxinid");
		String xueshengid = request.getParameter("xueshengid");
		System.out.println(naxinid + "" + xueshengid);
		if (!Util.isNumeric(naxinid)) {
			return "dengchu";
		}
		NaXin naXin = sheTuanService.selectNaXinByNaXinID(Integer.parseInt(naxinid));
		XueShengZuZhiXinXi xinXi = sheTuanService.selectByXueShengZuZhiXinXiID(naXin.getBaomingxueshengzuzhiid());
		if (xinXi == null) {
			return "dengchu";
		}
		if (!xinXi.getFuzeren().equals(xueshengid)) {
			return "dengchu";
		}
		String ret = "";
		int i = sheTuanService.updateNaXinZhuangTaiByNaXinID(1, Integer.parseInt(naxinid));
		if (i > 0) {
			StringBuffer sheTuanRenYuans = new StringBuffer(xinXi.getRenyuanids());
			sheTuanRenYuans = sheTuanRenYuans.append(naXin.getXueshengid() + ",");
			xinXi.setRenyuanids(sheTuanRenYuans.toString());
			xinXi.setRenshu(xinXi.getRenshu() + 1);
			int j = sheTuanService.updateXueShengZuZhiXinXi(xinXi);
			if (naXin.getBaomingbumenid() != null) {
				SheTuanBuMenXinXin sheTuanBuMenXinXin = sheTuanService
						.selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(naXin.getBaomingbumenid());
				StringBuffer buMenRenYuans = null;
				if (sheTuanBuMenXinXin.getRenyuanids() == null) {
					buMenRenYuans = new StringBuffer();
				} else {
					buMenRenYuans = new StringBuffer(sheTuanBuMenXinXin.getRenyuanids());
				}
				buMenRenYuans = buMenRenYuans.append(naXin.getXueshengid() + ",");
				int k = sheTuanService.updateSheTuanBuMenRenYuanByBuMenXinXiID(
						sheTuanBuMenXinXin.getShetuanbumenxinxiid(), buMenRenYuans.toString());
				if (j > 0 && k > 0) {
					TiXing tiXing = new TiXing();
					tiXing.setJieshourenid(naXin.getXueshengid());
					tiXing.setZhuangtai(0);
					tiXing.setShijian(new Date());
					tiXing.setNeirong("你的"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xinXi.getXueshengzuzhiid()).getMingcheng()+"报名已通过");
					tiXingService.insert(tiXing);
					ret = "success";
				}
			} else {
				if (j > 0) {
					TiXing tiXing = new TiXing();
					tiXing.setJieshourenid(naXin.getXueshengid());
					tiXing.setZhuangtai(0);
					tiXing.setShijian(new Date());
					tiXing.setNeirong("你的"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xinXi.getXueshengzuzhiid()).getMingcheng()+"报名已通过");
					tiXingService.insert(tiXing);
					ret = "success";
				}
			}
		} else {
			ret = "fail";
		}
		return ret;
	}

	// 社团拒绝加入申请
	@RequestMapping(value = "app_ST_DenyBaoMing")
	@ResponseBody
	public String app_ST_DenyBaoMing(HttpServletRequest request) {
		String naxinid = request.getParameter("naxinid");
		String xueshengid = request.getParameter("xueshengid");
		if (!Util.isNumeric(naxinid)) {
			return "dengchu";
		}
		NaXin naXin = sheTuanService.selectNaXinByNaXinID(Integer.parseInt(naxinid));
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(
				naXin.getBaomingshetuanid(), Integer.parseInt(xueshengid));
		if (sheTuanXinXi == null) {
			return "dengchu";
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanJiBenXinXi.getZhuangtai()==false) {
			return "dengchu";
		}
		String ret = "";
		int i = sheTuanService.updateNaXinZhuangTaiByNaXinID(2, Integer.parseInt(naxinid));
		if (i > 0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(naXin.getXueshengid());
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你的"+sheTuanJiBenXinXi.getMingcheng()+"报名已被拒绝");
			tiXingService.insert(tiXing);
			ret = "success";
		} else {
			ret = "fail";
		}
		return ret;
	}

	// 学生组织拒绝加入申请
	@RequestMapping(value = "app_XS_DenyBaoMing")
	@ResponseBody
	public String app_XS_DenyBaoMing(HttpServletRequest request) {
		String naxinid = request.getParameter("naxinid");
		String xueshengid = request.getParameter("xueshengid");
		if (!Util.isNumeric(naxinid)) {
			return "dengchu";
		}
		NaXin naXin = sheTuanService.selectNaXinByNaXinID(Integer.parseInt(naxinid));
		XueShengZuZhiXinXi xinXi = sheTuanService.selectByXueShengZuZhiXinXiID(naXin.getBaomingxueshengzuzhiid());
		if (xinXi == null) {
			return "dengchu";
		}
		if (!xinXi.getFuzeren().equals(xueshengid)) {
			return "dengchu";
		}
		String ret = "";
		int i = sheTuanService.updateNaXinZhuangTaiByNaXinID(2, Integer.parseInt(naxinid));
		if (i > 0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(naXin.getXueshengid());
			tiXing.setZhuangtai(0);
			tiXing.setShijian(new Date());
			tiXing.setNeirong("你的"+sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xinXi.getXueshengzuzhiid()).getMingcheng()+"报名已被拒绝");
			tiXingService.insert(tiXing);
			ret = "success";
		} else {
			ret = "fail";
		}
		return ret;
	}
	
	//社团信息维护返回社团基本信息
	@RequestMapping(value="app_ST_XinXiWeiHu")
	@ResponseBody
	public SheTuanJiBenXinXi app_ST_XinXiWeiHu(HttpServletRequest request){
		String token = request.getParameter("token");
		String xueshengid = request.getParameter("xueshengid");
		String status = request.getParameter("status");
		String shetuanxinxiid = request.getParameter("shetuanxinxiid");
		String string= xueshengid+","+status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			SheTuanXinXi sheTuanXinXi=sheTuanService.selectSheTuanXinXiBySheTuanXinXiID(Integer.parseInt(shetuanxinxiid));
			if(sheTuanXinXi!=null){
				SheTuanJiBenXinXi sheTuanJiBenXinXi=sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
				if(sheTuanJiBenXinXi!=null){
					if (sheTuanJiBenXinXi.getZhuangtai()==false) {
						return null;
					}
					if (!sheTuanXinXi.getShezhang().equals(xueshengid)) {
						return null;
					}
					return sheTuanJiBenXinXi;
				}
				else {
					return new SheTuanJiBenXinXi();
				}
			}
			else {
				return new SheTuanJiBenXinXi();
			}
		}
		else {
			return null;
		}
	}
	
	//学生组织信息维护返回基本信息
	@RequestMapping(value="app_XS_XinXiWeiHu")
	@ResponseBody
	public XueShengZuZhiJiBenXinXi app_XS_XinXiWeiHu(HttpServletRequest request){
		String token = request.getParameter("token");
		String xueshengid = request.getParameter("xueshengid");
		String status = request.getParameter("status");
		String xueshengzuzhixinxiid = request.getParameter("xueshengzuzhixinxiid");
		String string= xueshengid+","+status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			XueShengZuZhiXinXi xueShengZuZhiXinXi=sheTuanService.selectByXueShengZuZhiXinXiID(Integer.parseInt(xueshengzuzhixinxiid));
			if (xueShengZuZhiXinXi==null) {
				return null;
			}
			XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
			if (xueShengZuZhiJiBenXinXi.getZhuangtai()==false) {
				return null;
			}
			if (!xueShengZuZhiXinXi.getFuzeren().equals(xueshengid)) {
				return null;
			}
			return xueShengZuZhiJiBenXinXi;
		}
		else {
			return null;
		}
	}
	
	//提交社团信息维护表
	@RequestMapping(value="app_SubSheTuanXinXiWeiHu")
	@ResponseBody
	public String app_SubSheTuanXinXiWeiHu(HttpServletRequest request){
		String token = request.getParameter("token");
		String xueshengid = request.getParameter("xueshengid");
		String status = request.getParameter("status");
		String string= xueshengid+","+status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			String jieshao = request.getParameter("jieshao");
			String zhuye = request.getParameter("zhuye");
			String shetuanid = request.getParameter("shetuanid");
			SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(Integer.parseInt(shetuanid));
			if (sheTuanJiBenXinXi==null) {
				return null;
			}
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanIDAndNianDu(sheTuanJiBenXinXi.getShetuanid(),format.format(date));
			if (sheTuanXinXi==null) {
				return null;
			}
			if (!sheTuanXinXi.getShezhang().equals(xueshengid)) {
				return null;
			}
			if (!zhuye.startsWith("http")) {
				StringBuffer sBuffer = new StringBuffer();
				sBuffer.append("http://");
				sBuffer.append(zhuye);
				sheTuanJiBenXinXi.setShetuanjieshaourl(sBuffer.toString());
			}else {
				sheTuanJiBenXinXi.setShetuanjieshaourl(zhuye);
			}
			sheTuanJiBenXinXi.setShetuanjieshao(jieshao);
			int i = sheTuanService.updateSheTuanJiBenXinXi(sheTuanJiBenXinXi);
			if (i>0) {
				return "success";
			}else {
				return "fail";
			}
		}
		else {
			return null;
		}
	}
	
	//提交学生组织信息维护表
	@RequestMapping(value="app_SubXueShengZuZhiXinXiWeiHu")
	@ResponseBody
	public String app_SubXueShengZuZhiXinXiWeiHu(HttpServletRequest request){
		String token = request.getParameter("token");
		String xueshengid = request.getParameter("xueshengid");
		String status = request.getParameter("status");
		String string= xueshengid+","+status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			String jieshao = request.getParameter("jieshao");
			String zhuye = request.getParameter("zhuye");
			String xueshengzuzhiid = request.getParameter("xueshengzuzhiid");
			XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(Integer.parseInt(xueshengzuzhiid));
			if (xueShengZuZhiJiBenXinXi==null) {
				return null;
			}
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(),format.format(date));
			if (xueShengZuZhiXinXi==null) {
				return null;
			}
			if (!xueShengZuZhiXinXi.getFuzeren().equals(xueshengid)) {
				return null;
			}
			if (!zhuye.startsWith("http")) {
				StringBuffer sBuffer = new StringBuffer();
				sBuffer.append("http://");
				sBuffer.append(zhuye);
				xueShengZuZhiJiBenXinXi.setJieshaourl(sBuffer.toString());
			}else {
				xueShengZuZhiJiBenXinXi.setJieshaourl(zhuye);
			}
			xueShengZuZhiJiBenXinXi.setJianjie(jieshao);
			int i = sheTuanService.updateXueShengZuZhiJiBenXinXi(xueShengZuZhiJiBenXinXi);
			if (i>0) {
				return "success";
			}else {
				return "fail";
			}
		}
		else {
			return null;
		}
	}

	// 退出社团
	@RequestMapping(value = "app_ST_TuiChuSheTuan")
	@ResponseBody
	public String app_ST_TuiChuSheTuan(HttpServletRequest request) {
		String ret = "";
		String xueshengid = request.getParameter("xueshengid");
		String xueshengid1 = "%," + xueshengid + ",%";
		String xueshengid2 = xueshengid + ",%";
		String sheTuanXinXiID = request.getParameter("CODE");
		if (!Util.isNumeric(sheTuanXinXiID)) {
			return "dengchu";
		}
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiIDAndXueShengID(
				Integer.parseInt(sheTuanXinXiID), xueshengid1, xueshengid2);
		if (sheTuanXinXi == null) {
			return "dengchu";
		}
		SheTuanBuMenXinXin sheTuanBuMenXinXin = null;
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xinXi = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(
						sheTuanBuMenJiBenXinXin.getBumenid(), format.format(new Date()), xueshengid1, xueshengid2);
				if (xinXi != null) {
					sheTuanBuMenXinXin = xinXi;
				}
			}
		}
		if (sheTuanXinXi.getShezhang().equals(xueshengid)) {
			ret = "havejob";
		} else {
			if (sheTuanBuMenXinXin != null) {
				if (sheTuanBuMenXinXin.getBuzhang().equals(xueshengid)) {
					ret = "havejob";
				} else {
					String buMenRenYuan[] = sheTuanBuMenXinXin.getRenyuanids().split(",");
					StringBuffer sBuffer = new StringBuffer();
					for (int i = 0; i < buMenRenYuan.length; i++) {
						if (buMenRenYuan[i].equals(xueshengid)) {
							continue;
						} else {
							sBuffer.append(buMenRenYuan[i] + ",");
						}
					}
					sheTuanBuMenXinXin.setRenyuanids(sBuffer.toString());
					sheTuanBuMenXinXin
							.setBumenrenshu(String.valueOf(Integer.parseInt(sheTuanBuMenXinXin.getBumenrenshu()) - 1));
					int j = sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(sheTuanBuMenXinXin);
					if (j > 0) {
						String sheYuanIDs[] = sheTuanXinXi.getSheyuanids().split(",");
						StringBuffer sBuffer2 = new StringBuffer();
						for (int i = 0; i < sheYuanIDs.length; i++) {
							if (sheYuanIDs[i].equals(xueshengid)) {
								continue;
							} else {
								sBuffer2.append(sheYuanIDs[i] + ",");
							}
						}
						sheTuanXinXi.setSheyuanids(sBuffer2.toString());
						sheTuanXinXi.setSheyuanrenshu(sheTuanXinXi.getSheyuanrenshu() - 1);
						int k = sheTuanService.updateSheTuanXinXi(sheTuanXinXi);
						if (k > 0) {
							ret = "success";
						} else {
							ret = "fail";
						}
					} else {
						ret = "fail";
					}
				}
			} else {
				String sheYuanIDs[] = sheTuanXinXi.getSheyuanids().split(",");
				StringBuffer sBuffer2 = new StringBuffer();
				for (int i = 0; i < sheYuanIDs.length; i++) {
					if (sheYuanIDs[i].equals(xueshengid)) {
						continue;
					} else {
						sBuffer2.append(sheYuanIDs[i] + ",");
					}
				}
				sheTuanXinXi.setSheyuanids(sBuffer2.toString());
				sheTuanXinXi.setSheyuanrenshu(sheTuanXinXi.getSheyuanrenshu() - 1);
				int k = sheTuanService.updateSheTuanXinXi(sheTuanXinXi);
				if (k > 0) {
					ret = "success";
				} else {
					ret = "fail";
				}
			}
		}
		return ret;
	}

	// 退出学生组织
	@RequestMapping(value = "app_XS_TuiChuXueShengZuZhi")
	@ResponseBody
	public String app_XS_TuiChuXueShengZuZhi(HttpServletRequest request) {
		String ret = "";
		String xueshengid = request.getParameter("xueshengid");
		String xueshengid1 = "%," + xueshengid + ",%";
		String xueshengid2 = xueshengid + ",%";
		String xueShengZuZhiXinXiID = request.getParameter("CODE");
		if (!Util.isNumeric(xueShengZuZhiXinXiID)) {
			return "dengchu";
		}
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService.selectXueShengZuZhiXinXiByIDAndXueShengID(
				Integer.parseInt(xueShengZuZhiXinXiID), xueshengid1, xueshengid2);
		if (xueShengZuZhiXinXi == null) {
			return "dengchu";
		}
		SheTuanBuMenXinXin sheTuanBuMenXinXin = null;
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
		if (!sheTuanBuMenJiBenXinXins.isEmpty()) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
				SheTuanBuMenXinXin xinXi = sheTuanService.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(
						sheTuanBuMenJiBenXinXin.getBumenid(), format.format(new Date()), xueshengid1, xueshengid2);
				if (xinXi != null) {
					sheTuanBuMenXinXin = xinXi;
				}
			}
		}
		if (xueShengZuZhiXinXi.getFuzeren().equals(xueshengid)) {
			ret = "havejob";
		} else {
			if (sheTuanBuMenXinXin != null) {
				if (sheTuanBuMenXinXin.getBuzhang().equals(xueshengid)) {
					ret = "havejob";
				} else {
					String buMenRenYuan[] = sheTuanBuMenXinXin.getRenyuanids().split(",");
					StringBuffer sBuffer = new StringBuffer();
					for (int i = 0; i < buMenRenYuan.length; i++) {
						if (buMenRenYuan[i].equals(xueshengid)) {
							continue;
						} else {
							sBuffer.append(buMenRenYuan[i] + ",");
						}
					}
					sheTuanBuMenXinXin.setRenyuanids(sBuffer.toString());
					sheTuanBuMenXinXin
							.setBumenrenshu(String.valueOf(Integer.parseInt(sheTuanBuMenXinXin.getBumenrenshu()) - 1));
					int j = sheTuanService.updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(sheTuanBuMenXinXin);
					if (j > 0) {
						String sheYuanIDs[] = xueShengZuZhiXinXi.getRenyuanids().split(",");
						StringBuffer sBuffer2 = new StringBuffer();
						for (int i = 0; i < sheYuanIDs.length; i++) {
							if (sheYuanIDs[i].equals(xueshengid)) {
								continue;
							} else {
								sBuffer2.append(sheYuanIDs[i] + ",");
							}
						}
						xueShengZuZhiXinXi.setRenyuanids(sBuffer2.toString());
						xueShengZuZhiXinXi.setRenshu(xueShengZuZhiXinXi.getRenshu() - 1);
						int k = sheTuanService.updateXueShengZuZhiXinXi(xueShengZuZhiXinXi);
						if (k > 0) {
							ret = "success";
						} else {
							ret = "fail";
						}
					} else {
						ret = "fail";
					}
				}
			} else {
				String sheYuanIDs[] = xueShengZuZhiXinXi.getRenyuanids().split(",");
				StringBuffer sBuffer2 = new StringBuffer();
				for (int i = 0; i < sheYuanIDs.length; i++) {
					if (sheYuanIDs[i].equals(xueshengid)) {
						continue;
					} else {
						sBuffer2.append(sheYuanIDs[i] + ",");
					}
				}
				xueShengZuZhiXinXi.setRenyuanids(sBuffer2.toString());
				xueShengZuZhiXinXi.setRenshu(xueShengZuZhiXinXi.getRenshu() - 1);
				int k = sheTuanService.updateXueShengZuZhiXinXi(xueShengZuZhiXinXi);
				if (k > 0) {
					ret = "success";
				} else {
					ret = "fail";
				}
			}
		}
		return ret;
	}

	// 查看进度
	@RequestMapping(value = "app_XianShiChuangJianJinDu")
	@ResponseBody
	public List<SheTuanChuangJian> app_XianShiChuangJianJinDu(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		if (xueshengid != null && xueshengid != "") {
			List<SheTuanChuangJian> sheTuanChuangJians = sheTuanService
					.selectSheTuanChuangJianByXueShengID(Integer.parseInt(xueshengid));
			return sheTuanChuangJians;
		} else {
			List<SheTuanChuangJian> sheTuanChuangJians = new ArrayList<>();
			return sheTuanChuangJians;
		}
	}
	
	//
	@RequestMapping(value="app_ZhiDaoJiaoShiList")
	@ResponseBody
	public List<YongHu> app_ZhiDaoJiaoShiList(HttpServletRequest request){
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String banjiid = request.getParameter("banjiid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			BanJi banJi = banJiService.selectByPrimaryKey(Integer.parseInt(banjiid));
			List<YongHu> jiaoShis = yongHuService.getAllByYuanXiID(banJi.getYuanxiid());
			return jiaoShis;
		}
		else {
			return null;
		}
	}

	// 创建社团
	@RequestMapping(value = "app_TiJiaoChuangJianSheTuan")
	@ResponseBody
	public String app_TiJiaoChuangJianSheTuan(HttpServletRequest request) {
		String retInfo = "";
		String liyou = request.getParameter("liyou");
		String xueshengid = request.getParameter("xueshengid");
		String dianhua = request.getParameter("dianhua");
		String mingcheng = request.getParameter("mingcheng");
		String jieshao = request.getParameter("jieshao");
		String xuexiaoxuehao = request.getParameter("xuexiaoxuehao");
		String jiaoshi = request.getParameter("jiaoshi");
		String xueXiaoXueHao[] = xuexiaoxuehao.split("_");
		System.out.println(mingcheng + "   " + xuexiaoxuehao);
		if (xueshengid == null || xueshengid == "") {
			return "dengchu";
		}
		SheTuanChuangJian sJian = sheTuanService
				.selectSheTuanChuangJianByXueXiaoIDAndMingCheng(Integer.parseInt(xueXiaoXueHao[0]), mingcheng);
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService
				.selectSheTuanJiBenXinXiByXueXiaoIDAndMingCheng(Integer.parseInt(xueXiaoXueHao[0]), mingcheng);
		if (sJian != null || sheTuanJiBenXinXi != null) {
			retInfo = "mingcheng";
		} else {
			SheTuanChuangJian sheTuanChuangJian = new SheTuanChuangJian();
			sheTuanChuangJian.setChuangjianren(Integer.parseInt(xueshengid));
			sheTuanChuangJian.setDianhua(dianhua);
			sheTuanChuangJian.setMingcheng(mingcheng);
			sheTuanChuangJian.setJieshao(jieshao);
			sheTuanChuangJian.setZhidaojiaoshi(jiaoshi);
			sheTuanChuangJian.setChuangjianliyou(liyou);
			sheTuanChuangJian.setXuexiaoid(Integer.parseInt(xueXiaoXueHao[0]));
			sheTuanChuangJian.setLeixing(false);
			sheTuanChuangJian.setZhuangtai(0);
			int i = sheTuanService.insertSheTuanChuangJian(sheTuanChuangJian);
			if (i > 0) {
				retInfo = "success";
			} else {
				retInfo = "fail";
			}
		}
		return retInfo;
	}

	// 创建学生组织
	@RequestMapping(value = "app_TiJiaoChuangJianXSZZ")
	@ResponseBody
	public String app_TiJiaoChuangJianXSZZ(HttpServletRequest request) {
		String retInfo = "";
		String liyou = request.getParameter("liyou");
		String xueshengid = request.getParameter("xueshengid");
		String dianhua = request.getParameter("dianhua");
		String mingcheng = request.getParameter("mingcheng");
		String jieshao = request.getParameter("jieshao");
		String xuexiaoxuehao = request.getParameter("xuexiaoxuehao");
		String jiaoshi = request.getParameter("jiaoshi");
		String xueXiaoXueHao[] = xuexiaoxuehao.split("_");
		if (xueshengid == null || xueshengid == "") {
			return "dengchu";
		}
		SheTuanChuangJian sJian = sheTuanService
				.selectSheTuanChuangJianByXueXiaoIDAndMingCheng(Integer.parseInt(xueXiaoXueHao[0]), mingcheng);
		Map<String, String> map = new HashMap<>();
		map.put("xuexiaoid", xueXiaoXueHao[0]);
		map.put("mingcheng", mingcheng);
		List<XueShengZuZhiJiBenXinXi> xi = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
		if (sJian != null || (xi != null && !xi.isEmpty())) {
			retInfo = "mingcheng";
		} else {
			SheTuanChuangJian sheTuanChuangJian = new SheTuanChuangJian();
			sheTuanChuangJian.setChuangjianren(Integer.parseInt(xueshengid));
			sheTuanChuangJian.setDianhua(dianhua);
			sheTuanChuangJian.setMingcheng(mingcheng);
			sheTuanChuangJian.setJieshao(jieshao);
			sheTuanChuangJian.setZhidaojiaoshi(jiaoshi);
			sheTuanChuangJian.setChuangjianliyou(liyou);
			sheTuanChuangJian.setXuexiaoid(Integer.parseInt(xueXiaoXueHao[0]));
			sheTuanChuangJian.setLeixing(true);
			sheTuanChuangJian.setZhuangtai(0);
			int i = sheTuanService.insertSheTuanChuangJian(sheTuanChuangJian);
			if (i > 0) {
				retInfo = "success";
			} else {
				retInfo = "fail";
			}
		}
		return retInfo;
	}

	// 显示社团活动页面数据
	@RequestMapping(value = "app_XianShiSheTuanHuoDong")
	@ResponseBody
	public Object app_XianShiSheTuanHuoDong(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String xuexiaoXuehao = request.getParameter("xuexiaoxuehao");
		String xuexiaoxuehao[] = xuexiaoXuehao.split("_");
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = new ArrayList<>();
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = new ArrayList<>();
		List<SheTuanHuoDongXinXi> sheTuanHuoDongXinXis = new ArrayList<>();
		Map<String, Object> map=new HashMap<>();
		map.put("stjibenxinxi", "");
		map.put("xszzjibenxinxi", "");
		if (xueshengid != null && xueshengid != "") {
			
			sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoID(Integer.parseInt(xuexiaoxuehao[0]));
			for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
				sheTuanJiBenXinXi.setChuanjianren(xueShengService
						.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
				if (request.getParameter("page") == null || request.getParameter("page") == ""){
					sheTuanHuoDongXinXis = sheTuanHuoDongService
							.selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(sheTuanJiBenXinXi.getShetuanid(),0,5);
					if(!sheTuanHuoDongXinXis.isEmpty()){
						sheTuanJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
					}
				}
			}
			xueShengZuZhiJiBenXinXis = sheTuanService
					.selectXueShengZuZhiJiBenXinXiByXueXiaoID(Integer.parseInt(xuexiaoxuehao[0]));
			for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
				xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
						.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
				if (request.getParameter("page") == null || request.getParameter("page") == ""){
					sheTuanHuoDongXinXis =sheTuanHuoDongService
							.selectSheTuanHuoDongXinXisByXueShengZuZhiIDAndLimit(xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(),0,5);
					if(!sheTuanHuoDongXinXis.isEmpty()){
						xueShengZuZhiJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
					}
				}
			}
		}
		map.put("stjibenxinxi", sheTuanJiBenXinXis);
		map.put("xszzjibenxinxi", xueShengZuZhiJiBenXinXis);
		return JSON.toJSON(map);
	}
	
	//在社团活动页面查询活动
	@RequestMapping(value="app_ChaXunSheTuanHuoDong")
	@ResponseBody
	public JSONObject app_ChaXunSheTuanHuoDong(HttpServletRequest request){
		String xingJi = request.getParameter("xingji");
		String xingZhi = request.getParameter("xingzhi");
		String code = request.getParameter("xuexiaoxuehao");
		Map<String, Object> map2 = new HashMap<>();
		String mingCheng = request.getParameter("mingcheng");
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = new ArrayList<>();
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = new ArrayList<>();
		List<SheTuanHuoDongXinXi> sheTuanHuoDongXinXis = new ArrayList<>();
		map2.put("xszzjibenxinxi", xueShengZuZhiJiBenXinXis);
		map2.put("stjibenxinxi", sheTuanJiBenXinXis);
		map2.put("xingji", xingJi);
		if (code != null && code != "") {
			if (xingZhi.equals("shetuan")) {
				Map<String, String> map = new HashMap<>();
				String xueXiaoXueHao[] = code.split("_");
				map.put("xuexiaoid", xueXiaoXueHao[0]);
				map.put("xingji", xingJi.toString());
				map.put("mingcheng", mingCheng);
				sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
				for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
					sheTuanJiBenXinXi.setChuanjianren(xueShengService
							.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
					sheTuanHuoDongXinXis = sheTuanHuoDongService.selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(sheTuanJiBenXinXi.getShetuanid(),0,5);
					if(!sheTuanHuoDongXinXis.isEmpty()){
						sheTuanJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
					}
				}
			}
			if (xingZhi.equals("xueshengzuzhi")) {
				Map<String, String> map = new HashMap<>();
				String xueXiaoXueHao[] = code.split("_");
				map.put("xuexiaoid", xueXiaoXueHao[0]);
				map.put("mingcheng", mingCheng);
				xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
				for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
					xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
							.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
				    sheTuanHuoDongXinXis =sheTuanHuoDongService
				    		.selectSheTuanHuoDongXinXisByXueShengZuZhiIDAndLimit(xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(),0,5);
				    if(!sheTuanHuoDongXinXis.isEmpty()){
						xueShengZuZhiJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
					}
				}
				xingJi = "";
			}
			if (xingZhi.equals("")) {
				if (!mingCheng.equals("") && !xingJi.equals("")) {
					Map<String, String> map = new HashMap<>();
					String xueXiaoXueHao[] = code.split("_");
					map.put("xuexiaoid", xueXiaoXueHao[0]);
					map.put("mingcheng", mingCheng);
					map.put("xingji", xingJi);
					sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
					for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
						sheTuanJiBenXinXi.setChuanjianren(xueShengService
								.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
						sheTuanHuoDongXinXis = sheTuanHuoDongService.selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(sheTuanJiBenXinXi.getShetuanid(),0,5);
						if(!sheTuanHuoDongXinXis.isEmpty()){
							sheTuanJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
						}
					}
					xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
					for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
						xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
								.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
					    sheTuanHuoDongXinXis =sheTuanHuoDongService
					    		.selectSheTuanHuoDongXinXisByXueShengZuZhiIDAndLimit(xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(),0,5);
					    if(!sheTuanHuoDongXinXis.isEmpty()){
							xueShengZuZhiJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
						}
					}
				} else if (!mingCheng.equals("") && xingJi.equals("")) {
					Map<String, String> map = new HashMap<>();
					String xueXiaoXueHao[] = code.split("_");
					map.put("xuexiaoid", xueXiaoXueHao[0]);
					map.put("mingcheng", mingCheng);
					map.put("xingji", xingJi);
					sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
					for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
						sheTuanJiBenXinXi.setChuanjianren(xueShengService
								.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
						sheTuanHuoDongXinXis = sheTuanHuoDongService.selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(sheTuanJiBenXinXi.getShetuanid(),0,5);
						if(!sheTuanHuoDongXinXis.isEmpty()){
							sheTuanJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
						}
					}
					xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
					for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
						xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
								.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
					    sheTuanHuoDongXinXis =sheTuanHuoDongService
					    		.selectSheTuanHuoDongXinXisByXueShengZuZhiIDAndLimit(xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(),0,5);
					    if(!sheTuanHuoDongXinXis.isEmpty()){
							xueShengZuZhiJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
						}
					}
				} else if (mingCheng.equals("") && !xingJi.equals("")) {
					Map<String, String> map = new HashMap<>();
					String xueXiaoXueHao[] = code.split("_");
					map.put("xuexiaoid", xueXiaoXueHao[0]);
					map.put("xingji", xingJi.toString());
					map.put("mingcheng", mingCheng);
					sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
					for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
						sheTuanJiBenXinXi.setChuanjianren(xueShengService
								.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
						sheTuanHuoDongXinXis = sheTuanHuoDongService.selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(sheTuanJiBenXinXi.getShetuanid(),0,5);
						if(!sheTuanHuoDongXinXis.isEmpty()){
							sheTuanJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
						}
					}
				} else {
					String xueXiaoXueHao[] = code.split("_");
					xueShengZuZhiJiBenXinXis = sheTuanService
							.selectXueShengZuZhiJiBenXinXiByXueXiaoID(Integer.parseInt(xueXiaoXueHao[0]));
					for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
						xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
								.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
					    sheTuanHuoDongXinXis =sheTuanHuoDongService
					    		.selectSheTuanHuoDongXinXisByXueShengZuZhiIDAndLimit(xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(),0,5);
					    if(!sheTuanHuoDongXinXis.isEmpty()){
							xueShengZuZhiJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
						}
					}
					sheTuanJiBenXinXis = sheTuanService
							.selectSheTuanJiBenXinXiByXueXiaoID(Integer.parseInt(xueXiaoXueHao[0]));
					for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
						sheTuanJiBenXinXi.setChuanjianren(xueShengService
								.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
						sheTuanHuoDongXinXis = sheTuanHuoDongService.selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(sheTuanJiBenXinXi.getShetuanid(),0,5);
						if(!sheTuanHuoDongXinXis.isEmpty()){
							sheTuanJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
						}
					}
				}
			}
			map2.put("xszzjibenxinxi", xueShengZuZhiJiBenXinXis);
			map2.put("stjibenxinxi", sheTuanJiBenXinXis);
			map2.put("xingji", xingJi);
		}
		return JSONObject.fromObject(map2);
	}
	
	//社团活动显示目录
	@RequestMapping(value="app_SheTuanHuoDongMuLu")
	@ResponseBody
	public Object app_SheTuanHuoDongMuLu(HttpServletRequest request){
		String shetuanid=request.getParameter("shetuanid");
		List<SheTuanHuoDongXinXi> sheTuanHuoDongXinXis = new ArrayList<>();
		Map<String, Object> mpp=new HashMap<>();
		int count = sheTuanHuoDongService.getCountBySheTuanID(Integer.parseInt(shetuanid));
		int pageSize = 10;
		int pages = (count / pageSize) + 1;
		mpp.put("huodong", "");
		mpp.put("pages", "");
		mpp.put("page", "");
		if(shetuanid!=null&&shetuanid!=""){
			if (request.getParameter("page") == null || request.getParameter("page") == ""){
				sheTuanHuoDongXinXis = sheTuanHuoDongService
						.selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(Integer.parseInt(shetuanid),0,5);
				mpp.put("huodong", sheTuanHuoDongXinXis);
				mpp.put("pages", pages);
				mpp.put("page", 1);
				return JSON.toJSON(mpp);
			}
			else {
				if (Util.isNumeric(request.getParameter("page"))) {
					int page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page <= pages) {
						sheTuanHuoDongXinXis = sheTuanHuoDongService
								.selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(Integer.parseInt(shetuanid),(page-1)*5,5);
						mpp.put("huodong", sheTuanHuoDongXinXis);
						mpp.put("pages", pages);
						mpp.put("page", page);
						return JSON.toJSON(mpp);
					}
					else {
						mpp.put("huodong", "");
						mpp.put("pages", "");
						mpp.put("page", "");
						return JSON.toJSON(mpp);
					}
				}
				else {
					mpp.put("huodong", "");
					mpp.put("pages", "");
					mpp.put("page", "");
					return JSON.toJSON(mpp);
				}
			}
		}
		return JSON.toJSON(mpp);
	}
	
	//学生组织活动显示目录
	@RequestMapping(value="app_XueShengZuZhiHuoDongMuLu")
	@ResponseBody
	public Object app_XueShengZuZhiHuoDongMuLu(HttpServletRequest request){
		String xueshengzuzhiid=request.getParameter("xueshengzuzhiid");
		List<SheTuanHuoDongXinXi> sheTuanHuoDongXinXis = new ArrayList<>();
		Map<String, Object> mpp=new HashMap<>();
		int count = sheTuanHuoDongService.getCountByXueShengZuZhiID(Integer.parseInt(xueshengzuzhiid));
		int pageSize = 10;
		int pages = (count / pageSize) + 1;
		mpp.put("huodong", "");
		mpp.put("pages", "");
		mpp.put("page", "");
		if(xueshengzuzhiid!=null&&xueshengzuzhiid!=""){
			if (request.getParameter("page") == null || request.getParameter("page") == ""){
				sheTuanHuoDongXinXis = sheTuanHuoDongService
						.selectSheTuanHuoDongXinXisByXueShengZuZhiIDAndLimit(Integer.parseInt(xueshengzuzhiid), 0, 5);
				mpp.put("huodong", sheTuanHuoDongXinXis);
				mpp.put("pages", pages);
				mpp.put("page", 1);
				return JSON.toJSON(mpp);
			}
			else {
				if (Util.isNumeric(request.getParameter("page"))) {
					int page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page <= pages) {
						sheTuanHuoDongXinXis = sheTuanHuoDongService
								.selectSheTuanHuoDongXinXisByXueShengZuZhiIDAndLimit(Integer.parseInt(xueshengzuzhiid), (page-1)*5, 5);
						mpp.put("huodong", sheTuanHuoDongXinXis);
						mpp.put("pages", pages);
						mpp.put("page", page);
						return JSON.toJSON(mpp);
					}
					else {
						mpp.put("huodong", "");
						mpp.put("pages", "");
						mpp.put("page", "");
						return JSON.toJSON(mpp);
					}
				}
				else {
					mpp.put("huodong", "");
					mpp.put("pages", "");
					mpp.put("page", "");
					return JSON.toJSON(mpp);
				}
			}
		}
		return JSON.toJSON(mpp);
	}
	
	//单一社团或学生组织某个活动详情
	@RequestMapping(value="app_SheTuanHuoDongXiangXi")
	@ResponseBody
	public SheTuanHuoDongXinXi app_SheTuanHuoDongXiangXi(HttpServletRequest request){
		String huodongid=request.getParameter("huodongid");
		SheTuanHuoDongXinXi sXi=new SheTuanHuoDongXinXi();
		sXi=sheTuanHuoDongService.selectByID(Integer.parseInt(huodongid));
		return sXi;
	}
}
