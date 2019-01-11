package com.web.controller.app.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.controller.base.BaseController;
import com.web.model.BanJi;
import com.web.model.FuDaoYuan;
import com.web.model.JiaoLiu;
import com.web.model.KeCheng;
import com.web.model.LoginInfo;
import com.web.model.XueQi;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.service.BanJiService;
import com.web.service.FuDaoYuanService;
import com.web.service.KeChengService;
import com.web.service.NianFenService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.PageData;
import com.web.util.Tools;

import groovy.ui.text.StructuredSyntaxDocumentFilter;

@Controller
public class AppJiaoLiuController extends BaseController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private KeChengService kechengService;
	@Autowired
	private YongHuService yonghuServie;
	@Autowired
	private FuDaoYuanService fudaoyuanService;
	@Autowired
	private BanJiService banjiService;
	@Autowired
	private XueShengService xueshengSerice;
	@Autowired
	private YuanXiService yuanxiService;
	@Autowired
	private NianFenService nianfenService;
	@Autowired
	private XueQiService xueqiService;

	// 接受前端传递的用户名、密码、身份等参数，在数据库中查询，根据结果给前端返回相应的retInfo
	@RequestMapping(value = "app_JiaoLiiu")
	@ResponseBody
	public void app_JiaoLiiu(HttpServletRequest request) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String retInfo = "fail";
		PageData pd = this.getPageData();
		String jiaoLiuParam = pd.getString("jiaoLiuParam");
		JSONObject jsonObject = JSONObject.fromObject(jiaoLiuParam);
		Map<String, Object> paramMap = (Map<String, Object>) JSONObject.toBean(jsonObject, Map.class);
		String xuexiao = Tools.notEmpty(paramMap.get("xueXiaoID")) ? "" : paramMap.get("xueXiaoID").toString();
		String status = paramMap.get("status").toString();
		if (Tools.isEmpty(xuexiao)) {
			String id = paramMap.get("id").toString();
			if (status.equals("xuesheng")) {
				XueSheng xs = xueshengSerice.selectByPrimaryKey(Integer.parseInt(id));
				xuexiao = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			} else {
				xuexiao = yuanxiService
						.selectByPrimaryKey(yonghuServie.selectYongHuByID(Integer.parseInt(id)).getYuanxiid())
						.getXuexiaoid().toString();
			}
			// xuexiao =
			// paramMap.get("xuexiao_xuehao").toString().split("_")[0];
		}
		paramMap.put("xuexiao", xuexiao);
		retInfo = "fail";
		List<JiaoLiu> loginInfoList = new ArrayList<>();
		List<JiaoLiu> pingBiList = kechengService.selectPingBiList(paramMap);
		List<JiaoLiu> banqunList = new ArrayList<>();
		if (status.equals("xuesheng")) {
			loginInfoList = kechengService.selectJiaoLiuList(paramMap);
			String banjiid = paramMap.get("banJiID").toString();
			paramMap.put("banjiid", banjiid);
			banqunList.add(banjiService.selectBanJiJiaoLiuList(paramMap));
			for (JiaoLiu jiaoLiu : loginInfoList) {
				if (jiaoLiu != null) {
					KeCheng keCheng = kechengService
							.selectByPrimaryKey(Integer.parseInt(jiaoLiu.getId().split("_")[1]));
					XueQi xueQi = xueqiService.getByXueQiID(keCheng.getXueqiid());
					Integer xuenian = nianfenService.selectByPrimaryKey(xueQi.getNianfenid()).getRuxuenianfen();
					jiaoLiu.setXueqi(xueQi.getXueqi());
					jiaoLiu.setXuenian(xuenian);
				}
			}
		}
		if (status.equals("fudaoyuan")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowTime = sdf.format(new Date());
			String id = paramMap.get("id").toString();
			FuDaoYuan fudaoyuan = fudaoyuanService.getByfuDaoYuanID(Integer.parseInt(id));
			if (fudaoyuan != null) {
				String banjiids = fudaoyuan.getBanjiid();
				for (String banjiid : banjiids.split(",")) {
					BanJi bj = banjiService.selectByPrimaryKey(Integer.parseInt(banjiid));
					Integer ruXueNianFen = nianfenService.selectByPrimaryKey(bj.getRuxuenianfenid()).getRuxuenianfen();
					String biYeNianFen = (ruXueNianFen + bj.getLeixing()) + "-09-01";
					if (sdf.parse(biYeNianFen).getTime() > sdf.parse(nowTime).getTime()) {
						paramMap.put("banjiid", banjiid);
						banqunList.add(banjiService.selectBanJiJiaoLiuList(paramMap));
					}
				}
			}
		}
		if (status.equals("laoshi")) {
			loginInfoList = kechengService.selectJiaoLiuList(paramMap);
			for (JiaoLiu jiaoLiu : loginInfoList) {
				if (jiaoLiu != null) {
					List<String> bjmingchenglist = new ArrayList<>();
					List<String> banjiid = kechengService.getByAllBanJiIDByID(jiaoLiu.getId().split("_")[1]);
					for (String string : banjiid) {
						String banjimingcheng = banjiService.selectByPrimaryKey(Integer.parseInt(string))
								.getBanjimingcheng();
						if (banjimingcheng != null) {
							bjmingchenglist.add(banjimingcheng);
						} else {
							continue;
						}
					}
					jiaoLiu.setBanjimingcheng(bjmingchenglist);
				}
				KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(jiaoLiu.getId().split("_")[1]));
				XueQi xueQi = xueqiService.getByXueQiID(keCheng.getXueqiid());
				Integer xuenian = nianfenService.selectByPrimaryKey(xueQi.getNianfenid()).getRuxuenianfen();
				jiaoLiu.setXueqi(xueQi.getXueqi());
				jiaoLiu.setXuenian(xuenian);
			}
		}
		for (JiaoLiu jl : pingBiList) {
			if (jl.getLeiXing().equals("1")) {

			} else {
				String pingBiZhangHu = jl.getPingBiZhangHu();
				String xhao = pingBiZhangHu.substring((pingBiZhangHu.indexOf("_") + 1), pingBiZhangHu.length());
				XueSheng xs = xueshengSerice.selectByXueHao(xhao);
				if (xs != null) {
					jl.setPingBiZhangHu(xuexiao + "_" + xs.getYonghuming());
				} else {
					if (status.equals("fudaoyuan")) {
						String id = paramMap.get("id").toString();
						jl.setPingBiZhangHu(
								xuexiao + "_" + yonghuServie.selectYongHuByID(Integer.parseInt(id)).getYonghuming());
					}
				}
			}
		}
		Collections.sort(banqunList, new Comparator<JiaoLiu>() {
			public int compare(JiaoLiu arg0, JiaoLiu arg1) {
				return arg1.getRuxuenianfenid().compareTo(arg0.getRuxuenianfenid());
			}
		});

		if (loginInfoList != null || banqunList != null) {
			returnMap.put("data", loginInfoList);
			returnMap.put("qundata", banqunList);
			returnMap.put("pingBiList", pingBiList);
			retInfo = "";
		}
		returnMap.put("retInfo", retInfo);
		renderJson(returnMap);
	}

	// 接受前端用户信息在数据库中查询，根据结果给前端返回相应的retInfo
	@RequestMapping(value = "app_HuoQuMouGeZuChengYuan")
	@ResponseBody
	public void app_HuoQuMouGeZuChengYuan(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String retInfo = "fail";
		PageData pd = this.getPageData();
		String jiaoLiuParam = pd.getString("jiaoLiuParam");
		String zuId = pd.getString("zuId");
		JSONObject jsonObject = JSONObject.fromObject(jiaoLiuParam);
		Map<String, Object> paramMap = (Map<String, Object>) JSONObject.toBean(jsonObject, Map.class);
		String xuexiao = Tools.notEmpty(paramMap.get("xueXiaoID")) ? "" : paramMap.get("xueXiaoID").toString();
		Integer pageSize = (request.getParameter("pageSize") == null || "".equals(request.getParameter("pageSize"))) ? 0
				: Integer.valueOf(request.getParameter("pageSize"));
		Integer pageNum = (request.getParameter("pageNum") == null || "".equals(request.getParameter("pageNum"))) ? 0
				: Integer.valueOf(request.getParameter("pageNum"));
		String status = paramMap.get("status").toString();
		String id = paramMap.get("id").toString();
		if (Tools.isEmpty(xuexiao)) {
			// xuexiao =
			// paramMap.get("xuexiao_xuehao").toString().split("_")[0];
			if (status.equals("xuesheng")) {
				XueSheng xs = xueshengSerice.selectByPrimaryKey(Integer.parseInt(id));
				xuexiao = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			} else {
				xuexiao = yuanxiService
						.selectByPrimaryKey(yonghuServie.selectYongHuByID(Integer.parseInt(id)).getYuanxiid())
						.getXuexiaoid().toString();
			}
		}
		String firstChar[] = zuId.split(xuexiao);
		List<LoginInfo> yongHuList = new ArrayList<>();
		paramMap.put("xuexiao", xuexiao);
		paramMap.put("zuId", zuId);
		paramMap.put("pageSize", pageSize);
		paramMap.put("pageNum", pageNum * pageSize);
		if (firstChar[0].equals("g")) { // 课程组
			yongHuList = kechengService.selectZuYongHuList(paramMap);
		}
		if (firstChar[0].equals("b")) { // 班级群组
			String banjiids[] = zuId.split("_");
			String banjiid = banjiids[1];
			paramMap.put("banjiid", banjiid);
			yongHuList = banjiService.selectBanJiZuYongHuList(paramMap);
		}
		FuDaoYuan fuDaoYuan = new FuDaoYuan();
		YongHu yonghu = null;
		if ("xuesheng".equals(status)) {
			fuDaoYuan = fudaoyuanService.getBybanJiID(paramMap.get("banJiID").toString() + ",%",
					"%," + paramMap.get("banJiID").toString() + ",%");
			yonghu = yonghuServie.selectYongHuByID(fuDaoYuan.getFudaoyuanid());
		} else if("fudaoyuan".equals(status)) {
			fuDaoYuan = fudaoyuanService.getByfuDaoYuanID(Integer.parseInt(id));
			yonghu = yonghuServie.selectYongHuByID(fuDaoYuan.getFudaoyuanid());
		}
		else {
			yonghu = yonghuServie.selectYongHuByID(Integer.parseInt(id));
		}
		 
		String s = xuexiao + "_" + yonghu.getYonghuming();
		if (yongHuList != null) {
			for (LoginInfo loginInfo : yongHuList) {
				List<JiaoLiu> pingBiList = kechengService.selectPingBiList(paramMap);
				for (JiaoLiu jiaoLiu : pingBiList) {
					if (jiaoLiu.getRongId().equals(zuId)) {
						if (jiaoLiu.getLeiXing().equals("1")) {
							// loginInfo.setDianHua("1");
							// loginInfo.setId(Integer.parseInt(jiaoLiu.getId()));
						} else {
							if (jiaoLiu.getPingBiZhangHu().equals(loginInfo.getXuexiao_xuehao())) {
								loginInfo.setDianHua("1");
								loginInfo.setId(Integer.parseInt(jiaoLiu.getId()));
							}
							if (jiaoLiu.getPingBiZhangHu().equals(s)) {
								loginInfo.setDianHua("1");
								loginInfo.setId(Integer.parseInt(jiaoLiu.getId()));
							}
						}
					}
				}
			}
		}
		if (yongHuList != null) {
			returnMap.put("yongHuList", yongHuList);
			retInfo = "";
		}
		returnMap.put("retInfo", retInfo);
		renderJson(returnMap);

	}

	// 设置屏蔽信息
	@RequestMapping(value = "app_UpdatePingBiXinXi")
	@ResponseBody
	public void app_UpdatePingBiXinXi(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String retInfo = "fail";
		PageData pd = this.getPageData();
		String jiaoLiuParam = pd.getString("pingBiXinXi");
		JSONObject jsonObject = JSONObject.fromObject(jiaoLiuParam);
		Map<String, Object> paramMap = (Map<String, Object>) JSONObject.toBean(jsonObject, Map.class);
		String returnId = "";
		if (Tools.notEmpty(paramMap.get("id")) && !"0".equals(paramMap.get("id"))) {
			System.out.println("删除数据");
			kechengService.deletePingBiXinXiById(paramMap);
			retInfo = "";
		} else {
			System.out.println("插入数据");
			int id = kechengService.insertPingBiXinXi(paramMap);
			retInfo = "";

			if (id > 0) {
				returnId = paramMap.get("id").toString();
			}
		}
		returnMap.put("dataId", returnId);
		returnMap.put("retInfo", retInfo);
		renderJson(returnMap);

	}

}
