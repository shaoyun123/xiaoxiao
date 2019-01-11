package com.web.controller.app.fudaoyuan;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.web.model.FuDaoYuan;
import com.web.model.Qingjia;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.model.YuanXi;
import com.web.service.BanJiService;
import com.web.service.FuDaoYuanService;
import com.web.service.QingjiaService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

@Controller
public class AppQingJiaController_fdy {
	@Autowired
	private FuDaoYuanService fuDaoYuanService;
	@Autowired
	private QingjiaService qingjiaService;
	@Autowired
	private YongHuService yonghuService;
	@Autowired
	private XueShengService xueShengService;
	@Autowired
	private YuanXiService yuanXiService;
	@Autowired
	private BanJiService banJiService;

	// 登录时获取辅导员或的一些信息，并缓存
	@RequestMapping(value = "app_tofudaoyuan_Or_jiaoshi")
	@ResponseBody
	public YongHu app_tofudaoyuan(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		YongHu retInfo = new YongHu();
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String data[] = code.split(",zytech,");
		map.put("id", data[0]);
		map.put("password", data[1]);
		if (data[2].equals("fudaoyuan")) {
			YongHu yongHu = yonghuService.selectFuDaoYuanByIdAndPasswd(map);
			String string = yongHu.getYonghuid() + "," + data[2];
			String str = Util.SHA1Encode(string);
			if (str.equals(token)) {
				retInfo.setYonghuid(yongHu.getYonghuid());
				retInfo.setYuanxiid(yongHu.getYuanxiid());
				retInfo.setYonghuxingming(yongHu.getYonghuxingming());
			} else {
				return null;
			}
		}
		else if (data[2].equals("jiaoshi")) {
			YongHu yongHu = yonghuService.selectJiaoShiByIdAndPasswd(map);
			String string = yongHu.getYonghuid() + "," + data[2];
			String str = Util.SHA1Encode(string);
			if (str.equals(token)) {
				retInfo.setYonghuid(yongHu.getYonghuid());
				retInfo.setYuanxiid(yongHu.getYuanxiid());
				retInfo.setYonghuxingming(yongHu.getYonghuxingming());
			} else {
				return null;
			}
		}
		return retInfo;
	}

	// 待处理假条
	@RequestMapping(value = "app_QingJiaDaiCchuLi_fdy")
	@ResponseBody
	public Object app_QingJiaDaiCchuLi_fdy(HttpServletRequest request) {
		String yonghuid = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			Map<String, Object> mapp = new HashMap<>();
			mapp.put("qingjias", "");
			mapp.put("pages", "");
			mapp.put("page", "");
			FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(Integer.parseInt(yonghuid));
			if (fuDaoYuan == null) {
				return JSON.toJSON(mapp);
			}
			String banJiIDs = fuDaoYuan.getBanjiid().substring(0, fuDaoYuan.getBanjiid().length() - 1);
			int count = qingjiaService.selectCountFuDaoYuanDaiChuLiByBanJiIDs(banJiIDs);
			int pageSize = 10;
			int pages = (count / pageSize) + 1;
			int page = 1;
			List<Qingjia> qingjias = new ArrayList<>();
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				Map<String, String> map = new HashMap<>();
				map.put("banjiids", banJiIDs);
				map.put("start", "0");
				map.put("stop", String.valueOf(pageSize));
				qingjias = qingjiaService.selectFuDaoYuanDaiChuLiByBanJiID(map);
			} else {
				if (!Util.isNumeric(request.getParameter("page"))) {
					return JSON.toJSON(mapp);
				}
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page <= pages) {
					Map<String, String> map = new HashMap<>();
					map.put("banjiids", banJiIDs);
					map.put("start", String.valueOf((page - 1) * 10));
					map.put("stop", String.valueOf(pageSize));
					qingjias = qingjiaService.selectFuDaoYuanDaiChuLiByBanJiID(map);
				} else {
					return JSON.toJSON(mapp);
				}
			}
			if (!qingjias.isEmpty()) {
				XueSheng xueSheng = null;
				for (Qingjia qingjia : qingjias) {
					xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
					qingjia.setXueshengxingming(xueSheng.getXingming());
					qingjia.setXuehao(xueSheng.getXuehao());
					try {
						qingjia.setTianshu(
								Util.daysBetween(qingjia.getQingjiakaishishijian(), qingjia.getQingjiajieshushijian()) + 1);
					} catch (ParseException e) {
					}
				}
			}
			mapp.put("qingjias", qingjias);
			mapp.put("pages", pages);
			mapp.put("page", page);
			return JSON.toJSON(mapp);
		}
		else {
			return null;
		}
	}

	// 已处理请假条
	@RequestMapping(value = "app_QingJiaYiCchuLi_fdy")
	@ResponseBody
	public Object app_QingJiaYiCchuLi_fdy(HttpServletRequest request) {
		String yonghuid = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			Map<String, Object> mapp = new HashMap<>();
			mapp.put("qingjias", "");
			mapp.put("pages", "");
			mapp.put("page", "");
			FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(Integer.parseInt(yonghuid));
			String banJiIDs = fuDaoYuan.getBanjiid().substring(0, fuDaoYuan.getBanjiid().length() - 1);
			Map<String, String> map = new HashMap<>();
			map.put("banjiids", banJiIDs);
			map.put("pizhunren", yonghuid);
			int count = qingjiaService.selectCountFuDaoYuanYiChuLiByBanJiIDsAndPiZhunRenID(map);
			int pageSize = 10;
			int pages = (count / pageSize) + 1;
			int page = 1;
			List<Qingjia> qingjias = new ArrayList<>();
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				map.put("start", "0");
				map.put("stop", String.valueOf(pageSize));
				qingjias = qingjiaService.selectFuDaoYuanYiChuLiByBanJiID(map);
			} else {
				if (!Util.isNumeric(request.getParameter("page"))) {
					return JSON.toJSON(mapp);
				}
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page <= pages) {
					map.put("start", String.valueOf((page - 1) * 10));
					map.put("stop", String.valueOf(pageSize));
					qingjias = qingjiaService.selectFuDaoYuanYiChuLiByBanJiID(map);
				} else {
					return JSON.toJSON(mapp);
				}
			}
			if (!qingjias.isEmpty()) {
				XueSheng xueSheng = null;
				for (Qingjia qingjia : qingjias) {
					xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
					qingjia.setXueshengxingming(xueSheng.getXingming());
					qingjia.setXuehao(xueSheng.getXuehao());
					try {
						qingjia.setTianshu(
								Util.daysBetween(qingjia.getQingjiakaishishijian(), qingjia.getQingjiajieshushijian()) + 1);
					} catch (ParseException e) {
					}
				}
			}
			mapp.put("qingjias", qingjias);
			mapp.put("pages", pages);
			mapp.put("page", page);
			return JSON.toJSON(mapp);
		}
		else {
			return null;
		}
	}

	// 假条详情
	@RequestMapping(value = "app_JiaTiaoDetails_fdy")
	@ResponseBody
	public Qingjia app_JiaTiaoDetails_fdy(HttpServletRequest request) {
		String status = request.getParameter("status");
		String yonghuid = request.getParameter("yonghuid");
		String jiaTiaoID = request.getParameter("id");
		String token = request.getParameter("token");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			if (!status.equals("fudaoyuan")) {
				return null;
			}
			if (!Util.isNumeric(jiaTiaoID)) {
				return null;
			}
			FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(Integer.parseInt(yonghuid));
			Qingjia qingjia = new Qingjia();
			qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
			if (qingjia == null) {
				return null;
			}
			String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
			XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
			String banJiID = String.valueOf(xueSheng.getBanjiid());
			boolean isIn = false;
			for (int i = 0; i < banJiIDs.length; i++) {
				if (banJiID.equals(banJiIDs[i])) {
					isIn = true;
				} else {
					continue;
				}
			}
			if (isIn == false) {
				return null;
			}
			qingjia.setXueshengxingming(xueSheng.getXingming());
			qingjia.setXuehao(xueSheng.getXuehao());
			qingjia.setBanjimingcheng(banJiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng());
			if(!qingjia.getZhuangtai().toString().equals("1")){
				YongHu yh = yonghuService.selectYongHuByID(Integer.parseInt(qingjia.getPizhunren()));
				qingjia.setPizhunren(yh.getYonghuxingming());
			}
			try {
				qingjia.setTianshu(
						Util.daysBetween(qingjia.getQingjiakaishishijian(), qingjia.getQingjiajieshushijian()) + 1);
			} catch (ParseException e) {
			}
			if (qingjia.getBingjiazhengming() != null && !qingjia.getBingjiazhengming().equals("")) {
				List<String> tuPian = new ArrayList<>();
				String tuPianS[] = qingjia.getBingjiazhengming().split(",");
				for (int i = 0; i < tuPianS.length; i++) {
					tuPian.add(tuPianS[i]);
				}
				qingjia.setTuPian(tuPian);
			}
			return qingjia;
		}
		else {
			return null;
		}
	}

	// 同意假条
	@RequestMapping(value = "app_AcceptJiaTiao_fdy")
	@ResponseBody
	public String app_AcceptJiaTiao_fdy(HttpServletRequest request) throws IOException, ParseException {
		String jiaTiaoID = request.getParameter("id");
		String status = request.getParameter("status");
		String token = request.getParameter("token");
		String yonghuid = request.getParameter("yonghuid");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			if (!status.equals("fudaoyuan")) {
				return null;
			}
			if (!Util.isNumeric(jiaTiaoID)) {
				return null;
			}
			FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(Integer.parseInt(yonghuid));
			Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
			if (qingjia == null) {
				return null;
			}
			String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
			XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
			String banJiID = String.valueOf(xueSheng.getBanjiid());
			boolean isIn = false;
			for (int i = 0; i < banJiIDs.length; i++) {
				if (banJiID.equals(banJiIDs[i])) {
					isIn = true;
				} else {
					continue;
				}
			}
			if (isIn == false) {
				return null;
			}
			if (qingjia.getZhuangtai() != 1) {
				return null;
			}
			if (Util.daysBetween(qingjia.getQingjiakaishishijian(), qingjia.getQingjiajieshushijian()) + 1 > 3) {
				return null;
			}
			qingjia.setZhuangtai(2);
			qingjia.setPizhunren(yonghuid);
			int i = qingjiaService.updateByQingJia(qingjia);
			String ret = "";
			if (i > 0) {
				ret = "success";
			} else {
				ret = "fail";
			}
			return ret;
		}
		else {
			return null;
		}
	}

	// 拒绝假条
	@RequestMapping(value = "app_DenytJiaTiao_fdy")
	@ResponseBody
	public String app_DenytJiaTiao_fdy(HttpServletRequest request) {
		String jiaTiaoID = request.getParameter("id");
		String status = request.getParameter("status");
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String liyou = request.getParameter("liyou");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			if (!status.equals("fudaoyuan")) {
				return null;
			}
			if (!Util.isNumeric(jiaTiaoID)) {
				return null;
			}
			FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(Integer.parseInt(yonghuid));
			Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
			if (qingjia == null) {
				return null;
			}
			String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
			XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
			String banJiID = String.valueOf(xueSheng.getBanjiid());
			boolean isIn = false;
			for (int i = 0; i < banJiIDs.length; i++) {
				if (banJiID.equals(banJiIDs[i])) {
					isIn = true;
				} else {
					continue;
				}
			}
			if (isIn == false) {
				return null;
			}
			if (qingjia.getZhuangtai() != 1) {
				return null;
			}
			qingjia.setTongzhineirong(liyou);
			qingjia.setZhuangtai(3);
			qingjia.setPizhunren(yonghuid);
			int i = qingjiaService.updateByQingJia(qingjia);
			String ret = "";
			if (i > 0) {
				ret = "success";
			} else {
				ret = "fail";
			}
			return ret;
		}
		else {
			return null;
		}
	}

	// 销假假条
	@RequestMapping(value = "app_XiaoJiatJiaTiao_fdy")
	@ResponseBody
	public String app_XiaoJiatJiaTiao_fdy(HttpServletRequest request) {
		String jiaTiaoID = request.getParameter("id");
		String status = request.getParameter("status");
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			if (!status.equals("fudaoyuan")) {
				return null;
			}
			if (!Util.isNumeric(jiaTiaoID)) {
				return null;
			}
			FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(Integer.parseInt(yonghuid));
			Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
			if (qingjia == null) {
				return null;
			}
			String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
			XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
			String banJiID = String.valueOf(xueSheng.getBanjiid());
			boolean isIn = false;
			for (int i = 0; i < banJiIDs.length; i++) {
				if (banJiID.equals(banJiIDs[i])) {
					isIn = true;
				} else {
					continue;
				}
			}
			if (isIn == false) {
				return null;
			}
			if (qingjia.getZhuangtai() != 4) {
				return null;
			}
			qingjia.setZhuangtai(5);
			qingjia.setPizhunren(yonghuid);
			int i = qingjiaService.updateByQingJia(qingjia);
			String ret = "";
			if (i > 0) {
				ret = "success";
			} else {
				ret = "fail";
			}
			return ret;
		}
		else {
			return null;
		}
	}

	// 上报假条
	@RequestMapping(value = "app_ShangBaotJiaTiao_fdy")
	@ResponseBody
	public String app_ShangBaotJiaTiao_fdy(HttpServletRequest request) throws IOException, ParseException {
		String jiaTiaoID = request.getParameter("id");
		String status = request.getParameter("status");
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			if (!status.equals("fudaoyuan")) {
				return null;
			}
			if (!Util.isNumeric(jiaTiaoID)) {
				return null;
			}
			FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(Integer.parseInt(yonghuid));
			Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
			if (qingjia == null) {
				return null;
			}
			String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
			XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
			String banJiID = String.valueOf(xueSheng.getBanjiid());
			boolean isIn = false;
			for (int i = 0; i < banJiIDs.length; i++) {
				if (banJiID.equals(banJiIDs[i])) {
					isIn = true;
				} else {
					continue;
				}
			}
			if (isIn == false) {
				return null;
			}
			if (qingjia.getZhuangtai() != 1) {
				return null;
			}
			if (Util.daysBetween(qingjia.getQingjiakaishishijian(), qingjia.getQingjiajieshushijian()) + 1 <= 3) {
				return null;
			}
			qingjia.setZhuangtai(6);
			qingjia.setPizhunren(yonghuid);
			int i = qingjiaService.updateByQingJia(qingjia);
			String ret = "";
			if (i > 0) {
				ret = "success";
			} else {
				ret = "fail";
			}
			return ret;
		}
		else {
			return null;
		}
	}

	// 请假列表查询
	@RequestMapping(value = "app_QingJiaLieBiao_fdy")
	@ResponseBody
	public Object app_QingJiaLieBiao_fdy(HttpServletRequest request) {
		String xueHao = request.getParameter("xh");
		String yonghuid = request.getParameter("CODE");
		String yuanxiid = request.getParameter("yuanxiid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			Map<String, Object> mpp = new HashMap<>();
			mpp.put("qingjias", "");
			mpp.put("page", "");
			mpp.put("pages", "");
			FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(Integer.parseInt(yonghuid));
			if (fuDaoYuan == null) {
				return JSON.toJSON(mpp);
			}
			int page = 1;
			int pages = 1;
			int count = 0;
			int pageSize = 10;
			String banJiIDs = fuDaoYuan.getBanjiid().substring(0, fuDaoYuan.getBanjiid().length() - 1);
			List<Qingjia> qingjias = new ArrayList<>();
			if (xueHao == null || xueHao.equals("")) {
				count = qingjiaService.selectCountAllByFuDaoYuanBanJiIDs(banJiIDs);
				pages = (count / pageSize) + 1;
				Map<String, String> map = new HashMap<>();
				if (request.getParameter("page") == null || request.getParameter("page") == "") {
					map.put("banjiids", banJiIDs);
					map.put("start", "0");
					map.put("stop", String.valueOf(pageSize));
					qingjias = qingjiaService.selectAllByFuDaoYuanBanJiIDs(map);
				} else {
					if (!Util.isNumeric(request.getParameter("page"))) {
						return JSON.toJSON(mpp);
					}
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page <= pages) {
						map.put("banjiids", banJiIDs);
						map.put("start", String.valueOf((page - 1) * 10));
						map.put("stop", String.valueOf(pageSize));
						qingjias = qingjiaService.selectAllByFuDaoYuanBanJiIDs(map);
					} else {
						return JSON.toJSON(mpp);
					}
				}
			} else {
				YuanXi yuanXi = yuanXiService.selectByPrimaryKey(Integer.parseInt(yuanxiid));
				XueSheng xueSheng = xueShengService.selectXueShengByXueXiaoXueHao(yuanXi.getXuexiaoid() + "_" + xueHao);
				if (xueSheng == null) {
					mpp.put("qingjias", qingjias);
					mpp.put("page", page);
					mpp.put("pages", pages);
					return JSON.toJSON(mpp);
				}
				qingjias = qingjiaService.getAllByXueShengID(xueSheng.getXueshengid());
			}
			if (!qingjias.isEmpty()) {
				XueSheng xueSheng = null;
				for (Qingjia qingjia : qingjias) {
					xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
					qingjia.setXueshengxingming(xueSheng.getXingming());
					qingjia.setXuehao(xueSheng.getXuehao());
					if (qingjia.getPizhunren() != null) {
						qingjia.setPizhunren(yonghuService.selectYongHuByID(Integer.parseInt(qingjia.getPizhunren()))
								.getYonghuxingming());
					}
					try {
						qingjia.setTianshu(
								Util.daysBetween(qingjia.getQingjiakaishishijian(), qingjia.getQingjiajieshushijian()) + 1);
					} catch (ParseException e) {
					}
				}
			}
			mpp.put("qingjias", qingjias);
			mpp.put("page", page);
			mpp.put("pages", pages);
			return JSON.toJSON(mpp);
		}
		else {
			return null;
		}
	}
}
