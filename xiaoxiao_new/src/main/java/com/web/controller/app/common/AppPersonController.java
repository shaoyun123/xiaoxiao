package com.web.controller.app.common;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.web.controller.base.BaseController;
import com.web.model.BanJi;
import com.web.model.BeiWL;
import com.web.model.ChaQinAnPai;
import com.web.model.ChaQinJieGuo;
import com.web.model.FuDaoYuan;
import com.web.model.HuoDong;
import com.web.model.JiaoShi;
import com.web.model.JiaoXueLou;
import com.web.model.JieCiFangAn;
import com.web.model.KeCheng;
import com.web.model.BeiZhu;
import com.web.model.LoginInfo;
import com.web.model.Qingjia;
import com.web.model.TiXing;
import com.web.model.XiaoXiFaSong;
import com.web.model.XueQi;
import com.web.model.XueSheng;
import com.web.model.XueXiao;
import com.web.model.YongHu;
import com.web.model.YuanXi;
import com.web.model.ZhuanYe;
import com.web.service.BanJiService;
import com.web.service.BeiWLService;
import com.web.service.ChaQinService;
import com.web.service.FuDaoYuanService;
import com.web.service.HuoDongService;
import com.web.service.JCSJService;
import com.web.service.JiGuangService;
import com.web.service.JiaoShiService;
import com.web.service.JiaoXueLouService;
import com.web.service.JieCiFangAnService;
import com.web.service.BeiZhuService;
import com.web.service.KeChengService;
import com.web.service.QingjiaService;
import com.web.service.TiXingService;
import com.web.service.XiaoQuService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.service.XueXiaoService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.service.ZhuanYeService;
import com.web.util.Const;
import com.web.util.DesUtil;
import com.web.util.Tools;
import com.web.util.UpdataImgerUtil;
import com.web.util.Util;

@Controller
@Scope("request")
public class AppPersonController extends BaseController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private XueShengService xueshengService;
	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private XueXiaoService xueXiaoService;
	@Autowired
	private YuanXiService yuanXiService;
	@Autowired
	private BanJiService banJiService;
	@Autowired
	private ZhuanYeService zhuanYeService;
	@Autowired
	private KeChengService kechengService;
	@Autowired
	private HuoDongService huodongService;
	@Autowired
	private JCSJService jcsjService;
	@Autowired
	private XiaoQuService xiaoquService;
	@Autowired
	private JiaoShiService jiaoshiService;
	@Autowired
	private BeiWLService beiwlService;
	@Autowired
	private FuDaoYuanService fuDaoYuanService;
	@Autowired
	private ChaQinService chaQinService;
	@Autowired
	private QingjiaService qingjiaService;
	@Autowired
	private TiXingService tixingService;
	@Autowired
	private JiGuangService jiGuangService;
	@Autowired
	private XueQiService xueqiService;
	@Autowired
	private JiaoXueLouService jiaoXueLouService;
	@Autowired
	private BeiZhuService beiZhuService;
	@Autowired
	private JieCiFangAnService jiecifanganService;
	

	// 接受前端传递的用户名、密码、身份等参数，在数据库中查询，根据结果给前端返回相应的retInfo
	@RequestMapping(value = "app_tologin")
	@ResponseBody
	@Scope("request")
	public void app_toLogin(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String retInfo = "";
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		map.put("id", data[0]);
		map.put("password", data[1]);
		System.out.println("账户：" + code);
		/** 新修改 去掉此信息 **/
		// map.put("status", data[2]);
		retInfo = "fail";

		List<LoginInfo> loginInfoList = yongHuService.selectLoginInfo(map);
		if (Tools.notEmpty(loginInfoList)) {
			if (loginInfoList.size() > 0) {
				if (loginInfoList.get(0).getJueSeId()==1) {
					loginInfoList.get(0).setStatus("jiaoshi");
				}
				if(loginInfoList.get(0).getJueSeId()==2) {
					loginInfoList.get(0).setStatus("fudaoyuan");
				}
				String string = loginInfoList.get(0).getId() + "," + loginInfoList.get(0).getStatus();
				retInfo = Util.SHA1Encode(string);
				if (Tools.notEmpty(loginInfoList.get(0).getXuexiao_xuehao())) {
					loginInfoList.get(0).setPk(DesUtil.byteToHexString(DesUtil.DES_CBC_Encrypt(
							loginInfoList.get(0).getXuexiao_xuehao().getBytes(), "xiaoxiao".getBytes())));
				} else {
					loginInfoList.get(0).setPk("");
				}
				loginInfoList.get(0).setFasongyouxiang(getSendMainForFile());

				returnMap.put("data", loginInfoList.get(0));
			}
		}
		returnMap.put("retInfo", retInfo);
		returnMap.put("fasongyouxiang", getSendMainForFile());
		renderJson(returnMap);
		// if (data[2].equals("xuesheng")) {
		// XueSheng xueSheng = xueshengService.selectUserByIdAndPasswd(map);
		// if (xueSheng != null) {
		// xueSheng.setStatus("xuesheng");
		// String string= xueSheng.getXueshengid()+","+data[2];
		// retInfo = Util.SHA1Encode(string);
		// } else {
		// retInfo = "fail";
		// }
		// } else if (data[2].equals("fudaoyuan")) {
		// YongHu yongHu = yongHuService.selectFuDaoYuanByIdAndPasswd(map);
		// if (yongHu != null) {
		// yongHu.setStatus("fudaoyuan");
		// String string= yongHu.getYonghuid()+","+data[2];
		// retInfo = Util.SHA1Encode(string);
		// } else {
		// retInfo = "fail";
		// }
		// } else if (data[2].equals("shuji")) {
		// YongHu yongHu = yongHuService.selectShuJiByIdAndPasswd(map);
		// if (yongHu != null) {
		// yongHu.setStatus("shuji");
		// String string= yongHu.getYonghuid()+","+data[2];
		// retInfo = Util.SHA1Encode(string);
		// } else {
		// retInfo = "fail";
		// }
		// } else if (data[2].equals("jiaoshi")) {
		// YongHu yongHu = yongHuService.selectJiaoShiByIdAndPasswd(map);
		// if (yongHu != null) {
		// yongHu.setStatus("jiaoshi");
		// String string= yongHu.getYonghuid()+","+data[2];
		// retInfo = Util.SHA1Encode(string);
		// } else {
		// retInfo = "fail";
		// }
		// } else if (data[2].equals("guanliyuan")) {
		// YongHu yongHu =
		// yongHuService.selectXueShengChuGuanLiYuanByIdAndPasswd(map);
		// if (yongHu != null) {
		// yongHu.setStatus("guanliyuan");
		// String string= yongHu.getYonghuid()+","+data[2];
		// retInfo = Util.SHA1Encode(string);
		// } else {
		// retInfo = "fail";
		// }
		// }
	}

	// 接受前端传递的用户名、密码、身份等参数，在数据库中查询，根据结果给前端返回相应的retInfo
	@RequestMapping(value = "app_updatePasswordById")
	@ResponseBody
	public void app_updatePasswordById(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String retInfo = "";
		String yonghuid = request.getParameter("userId");
		String password = request.getParameter("userPassword");
		String status = request.getParameter("status");
		String xuexiaoxuehao = request.getParameter("xuexiaoxuehao");
		String loginName = request.getParameter("loginName");
		map.put("yonghuid", yonghuid);
		map.put("password", password);
		map.put("status", status);
		map.put("xuexiaoxuehao", xuexiaoxuehao);
		map.put("loginName", loginName);
		/** 新修改 去掉此信息 **/
		// map.put("status", data[2]);
		retInfo = "fail";
		try {
			yongHuService.updatePasswordById(map);
			retInfo = "";
		} catch (Exception e) {
			// TODO: handle exception
		}
		returnMap.put("retInfo", retInfo);
		renderJson(returnMap);
	}

	// 接受前端传递的用户名、密码、身份等参数，在数据库中查询，根据结果给前端返回相应的retInfo
	@RequestMapping(value = "app_getUserByName")
	@ResponseBody
	public void app_getUserByName(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String retInfo = "";
		String code = request.getParameter("userName");
		map.put("id", code);
		/** 新修改 去掉此信息 **/
		// map.put("status", data[2]);
		retInfo = "fail";
		List<LoginInfo> loginInfoList = yongHuService.selectLoginInfo(map);
		if (Tools.notEmpty(loginInfoList)) {
			if (loginInfoList.size() > 0) {
				String string = loginInfoList.get(0).getId() + "," + loginInfoList.get(0).getStatus();
				retInfo = Util.SHA1Encode(string);
				returnMap.put("data", loginInfoList.get(0));
				String yanZhengMa = getRandomNumber();
				returnMap.put("fasongyouxiang", getSendMainForFile());
				if (Tools.isEmpty(loginInfoList.get(0).getYanZhengMa())) {
					returnMap.put("yanZhengMa", yanZhengMa);
				} else {
					returnMap.put("yanZhengMa", loginInfoList.get(0).getYanZhengMa());
				}

				Map<String, Object> param = new HashMap<String, Object>();
				param.put("status", loginInfoList.get(0).getStatus());
				param.put("yonghuid", loginInfoList.get(0).getId());
				param.put("yanZhengMa", yanZhengMa);
				if (Tools.notEmpty(loginInfoList.get(0).getYouXiang())
						&& !Tools.notEmpty(loginInfoList.get(0).getYanZhengMa())
						&& Tools.isEmpty(loginInfoList.get(0).getCheckCodeFor())) {
					yongHuService.updateYanZhengMa(param);
				}
			}
		}
		returnMap.put("retInfo", retInfo);
		renderJson(returnMap);
	}

	private String getRandomNumber() {
		return ((int) ((Math.random() * 9 + 1) * 100000)) + "";
	}

	// 接受前端传递的用户名、密码、身份等参数，在数据库中查询，根据结果给前端返回相应的retInfo
	@RequestMapping(value = "app_getServerUrl")
	@ResponseBody
	public void app_getServerUrl(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("retInfo", getServerUrl());
		String xueshengid = request.getParameter("xueshengid");
		String xuexiaoxuehao = request.getParameter("xuexiaoxuehao");
		String xuehao = request.getParameter("xuehao");
		if (Tools.notEmpty(xuehao) && xuehao.equals("2013040004")) {
			returnMap.put("isTest", "0");
		} else {
			returnMap.put("isTest", getIsTest());
		}
		if (Tools.notEmpty(xuexiaoxuehao) || Tools.notEmpty(xuehao)) {
			System.out.println("进入考试----------------------------:" + xuexiaoxuehao);
		}
		Map<String, String> paramMap = new HashMap<String, String>();
		if (Tools.notEmpty(xuexiaoxuehao) && Tools.notEmpty(xuehao)) {

			paramMap.put("xuexiaoxuehao", xuexiaoxuehao);
			paramMap.put("xuehao", xuehao);
			paramMap.put("xueshengid", xueshengid);
			XueSheng xueSheng = xueshengService.getKaoShiXinXi(paramMap);
			returnMap.put("xuesheng", xueSheng);
		} else {
			paramMap.put("xuexiaoxuehao", "");
			paramMap.put("xuehao", "");
			paramMap.put("xueshengid", "");
			returnMap.put("xuesheng", new XueSheng());
		}
		renderJson(returnMap);
	}
	
	// 接受前端传递的用户名、密码、身份等参数，在数据库中查询，根据结果给前端返回相应的retInfo
	@RequestMapping(value = "app_getServerInfo")
	@ResponseBody
	public void app_getServerInfo(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String serverIp = getPropByName("serverip");
		String serverAppName = getPropByName("serverAppName");
		String serverPort = getPropByName("serverPort");
		
		String[] serverIps = serverIp.split(",");
		Integer connectNum = null;
		Integer selectNum = 0;
		for(int i = 0 ; i < serverIps.length ; i ++){
			connectNum = Const.SERVER_CONNECT_NUM.get(serverIps[i]) == null ? 0 : Const.SERVER_CONNECT_NUM.get(serverIps[i]);
			if(i != 0){
				if(connectNum < 170){
					selectNum = i;
					break;
				}
			}
		}
		returnMap.put("serverIp", serverIps[selectNum]);
		returnMap.put("serverAppName", serverAppName.split(",")[selectNum]);
		returnMap.put("serverPort", serverPort.split(",")[selectNum]);
		returnMap.put("num", selectNum);
		
		String xueshengid = request.getParameter("xueshengid");
		String xuexiaoxuehao = request.getParameter("xuexiaoxuehao");
		String xuehao = request.getParameter("xuehao");
		if (Tools.notEmpty(xuehao) && xuehao.equals("2013040004")) {
			returnMap.put("isTest", "0");
		} else {
			returnMap.put("isTest", getIsTest());
		}
		if (Tools.notEmpty(xuexiaoxuehao) || Tools.notEmpty(xuehao)) {
			System.out.println("进入考试----------------------------:" + xuexiaoxuehao);
		}
		Map<String, String> paramMap = new HashMap<String, String>();
		if (Tools.notEmpty(xuexiaoxuehao) && Tools.notEmpty(xuehao)) {

			paramMap.put("xuexiaoxuehao", xuexiaoxuehao);
			paramMap.put("xuehao", xuehao);
			paramMap.put("xueshengid", xueshengid);
			XueSheng xueSheng = xueshengService.getKaoShiXinXi(paramMap);
			returnMap.put("xuesheng", xueSheng);
		} else {
			paramMap.put("xuexiaoxuehao", "");
			paramMap.put("xuehao", "");
			paramMap.put("xueshengid", "");
			returnMap.put("xuesheng", new XueSheng());
		}
		renderJson(returnMap);
	}
	
	// 接受前端传递的用户名、密码、身份等参数，在数据库中查询，根据结果给前端返回相应的retInfo
	@RequestMapping(value = "app_checkServer")
	@ResponseBody
	public void app_checkServer(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("isOk", true);
		renderJson(returnMap);
	}
	
	// 接受前端传递的用户名、密码、身份等参数，在数据库中查询，根据结果给前端返回相应的retInfo
	@RequestMapping(value = "app_getConnectUrl")
	@ResponseBody
	public synchronized void app_getConnectUrl(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String dataStr = request.getParameter("data");
		String serverIp = getPropByName("serverip");
		String serverAppName = getPropByName("serverAppName");
		String serverPort = getPropByName("serverPort");
		
		String[] serverIps = serverIp.split(",");
		String[] dataStrs = dataStr.split(",");
		Integer num = 0;
		Integer connectNum = null;
		Integer selectNum = 0;
		for(int i = 0 ; i < dataStrs.length ; i ++){
			num = Integer.valueOf(dataStrs[i]);
			connectNum = Const.SERVER_CONNECT_NUM.get(serverIps[num]) == null ? 0 : Const.SERVER_CONNECT_NUM.get(serverIps[num]);
			if(num != 0){
				if(connectNum < 1){
					selectNum = num;
					break;
				}
			}
		}
		returnMap.put("serverIp", serverIps[selectNum]);
		returnMap.put("serverAppName", serverAppName.split(",")[selectNum]);
		returnMap.put("serverPort", serverPort.split(",")[selectNum]);
		returnMap.put("num", selectNum);
		renderJson(returnMap);
	}
		
	
	
	
	

	private String getSendMainForFile() {
		Properties pro = new Properties();
		InputStream inputStream = null;
		String email = "";
		try {
			String str = AppPersonController.class.getClassLoader().getResource("/").getPath() + "config.properties";
			inputStream = new FileInputStream(str);
			pro.load(inputStream);
			email = (String) pro.get("sendMail");

		} catch (Exception e) {
			// TODO: handle exception
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return email;
	}

	private String getIsTest() {
		Properties pro = new Properties();
		InputStream inputStream = null;
		String isTest = "";
		try {
			String str = AppPersonController.class.getClassLoader().getResource("/").getPath() + "config.properties";
			inputStream = new FileInputStream(str);
			pro.load(inputStream);
			isTest = (String) pro.get("isTest");

		} catch (Exception e) {
			// TODO: handle exception
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return isTest;
	}

	private String getServerUrl() {
		Properties pro = new Properties();
		InputStream inputStream = null;
		String serverUrl = "";
		try {
			String str = AppPersonController.class.getClassLoader().getResource("/").getPath() + "config.properties";
			inputStream = new FileInputStream(str);
			pro.load(inputStream);
			serverUrl = (String) pro.get("serverUrl");

		} catch (Exception e) {
			// TODO: handle exception
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return serverUrl;
	}
	
	private String getPropByName(String propName) {
		Properties pro = new Properties();
		InputStream inputStream = null;
		String serverUrl = "";
		try {
			String str = AppPersonController.class.getClassLoader().getResource("/").getPath() + "config.properties";
			inputStream = new FileInputStream(str);
			pro.load(inputStream);
			serverUrl = (String) pro.get(propName);

		} catch (Exception e) {
			// TODO: handle exception
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return serverUrl;
	}

	// 接受前端传递的用户名、密码、身份等参数，在数据库中查询，根据结果给前端返回相应的retInfo
	@RequestMapping(value = "app_checkemail")
	@ResponseBody
	public void app_checkemail(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String retInfo = "";
		String code = request.getParameter("youXiang");
		String userName = request.getParameter("userName");
		/** 新修改 去掉此信息 **/
		// map.put("status", data[2]);
		map.put("id", userName);
		retInfo = "fail";
		List<LoginInfo> loginInfoList = yongHuService.selectLoginInfo(map);
		if (Tools.notEmpty(loginInfoList)) {
			if (loginInfoList.size() > 0) {
				String yanZhengMa = getRandomNumber();
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("status", loginInfoList.get(0).getStatus());
				param.put("yonghuid", loginInfoList.get(0).getId());
				param.put("yanZhengMa", yanZhengMa);
				if (Tools.notEmpty(loginInfoList.get(0).getCheckCodeFor())) {
					returnMap.put("yanZhengMa", loginInfoList.get(0).getCheckCodeFor());
				} else {
					returnMap.put("yanZhengMa", yanZhengMa);
					param.put("youXiang", code);
					yongHuService.updateCheckCodeFor(param);
				}
				returnMap.put("fasongyouxiang", getSendMainForFile());
				retInfo = "";
			}
		}
		returnMap.put("retInfo", retInfo);
		renderJson(returnMap);
	}

	// 接受前端传递的用户名、密码、身份等参数，在数据库中查询，根据结果给前端返回相应的retInfo
	@RequestMapping(value = "app_getSendMail")
	@ResponseBody
	public void app_getSendMail(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("fasongyouxiang", getSendMainForFile());
		returnMap.put("retInfo", "");
		renderJson(returnMap);
	}

	// 获取学生个人详细信息
	@RequestMapping(value = "app_XueShengGeRenXinXiXianShi")
	@ResponseBody
	public JSONObject app_XueShengGeRenXinXiXianShi(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String banjiid = request.getParameter("banjiid");
		String xuexiaoxuehao = request.getParameter("xuexiaoXuehao");
		String id = request.getParameter("id");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = id + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			Map<String, Object> map = new HashMap<>();
			String xueXiaoXueHao[] = xuexiaoxuehao.split("_");
			if (id != null && id != "") {
				XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(id));
				XueXiao xueXiao = xueXiaoService.selectByID(Integer.parseInt(banJiService.findXueXiaoIDByBanJiID(xueSheng.getBanjiid())));
				BanJi banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
				YuanXi yuanXi = yuanXiService.selectByPrimaryKey(banJi.getYuanxiid());
				if(banJi.getZhuanyeid() == null || 0 == banJi.getZhuanyeid()){
					json.put("zhuanye", null);
				}else{
					ZhuanYe zhuanYe = zhuanYeService.selectByPrimaryKey(banJi.getZhuanyeid());
					json.put("zhuanye", zhuanYe);
				}
				json.put("xuesheng", xueSheng);
				json.put("xuexiao", xueXiao);
				json.put("yuanxi", yuanXi);
				json.put("banji", banJi);
				return json;
			} else {
				json.put("xuesheng", "");
				json.put("xuexiao", "");
				json.put("yuanxi", "");
				json.put("zhuanye", "");
				json.put("banji", "");
				return json;
			}
		} else {
			return null;
		}
	}

	// 获取辅导员个人详细信息
	@RequestMapping(value = "app_FuDaoYuanJiaoShiGeRenXinXiXianShi")
	@ResponseBody
	public JSONObject app_FuDaoYuanGeRenXinXiXianShi(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String yuanxiid = request.getParameter("yuanxiid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = id + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			Map<String, Object> map = new HashMap<>();
			if (id != null && id != "") {
				YongHu yongHu = yongHuService.selectYongHuByID(Integer.parseInt(id));
				YuanXi yuanXi = yuanXiService.selectByPrimaryKey(Integer.parseInt(yuanxiid));
				XueXiao xueXiao = xueXiaoService.selectByID(yuanXi.getXuexiaoid());
				json.put("yonghu", yongHu);
				json.put("xuexiao", xueXiao);
				json.put("yuanxi", yuanXi);
				return json;
			} else {
				json.put("yonghu", "");
				json.put("xuexiao", "");
				json.put("yuanxi", "");
				return json;
			}
		} else {
			return null;
		}
	}

	// 保存新邮箱和电话
	@RequestMapping(value = "app_SaveXinTelAndMail")
	@ResponseBody
	public String app_SaveXinTelAndMail(HttpServletRequest request) {
		String status = request.getParameter("status");
		String phone = request.getParameter("phone");
		String mail = request.getParameter("mail");
		String id = request.getParameter("id");
		String token = request.getParameter("mytoken");
		String string = id + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			if (status.equals("xuesheng")) {
				XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(id));
				xueSheng.setDianhua(phone);
				xueSheng.setYouxiang(mail);
				int i = xueshengService.updateInfo(xueSheng);
				if (i > 0) {
					return "success";
				} else {
					return "fail";
				}
			} else {
				YongHu yongHu = yongHuService.selectYongHuByID(Integer.parseInt(id));
				yongHu.setYonghushouji(phone);
				yongHu.setYonghuyouxiang(mail);
				int i = yongHuService.updateYongHu(yongHu);
				if (i > 0) {
					return "success";
				} else {
					return "fail";
				}
			}
		} else {
			return null;
		}
	}

	// 更改密码方法
	@Deprecated
	@RequestMapping(value = "app_UpdatePassword")
	@ResponseBody
	public String app_UpdatePasswordToXueSheng(HttpServletRequest request) {
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String code = request.getParameter("CODE");
		String yuanmima = request.getParameter("yuanmima");
		String xinmima = request.getParameter("xinmima");
		String querenxinmima = request.getParameter("querenxinmima");
		String data[] = code.split(",zytech,");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			if (status.equals("xuesheng")) {
				String xuehao = data[3].split("_")[1];
				String password = xueshengService.selectPassWdByID(Integer.parseInt(xuehao));
				if (password.equals(yuanmima)) {
					if (xinmima.equals(querenxinmima)) {
						int i = xueshengService.updatePassWdByID(xinmima, Integer.parseInt(data[0]));
						if (i != 0) {
							return "success";
						} else {
							return "fail2";
						}
					} else {
						return "fail3";
					}
				} else {
					return "fail1";
				}
			}
		} else {
			return null;
		}
		return null;
	}

	// 将学校名称返回给前台
	@RequestMapping(value = "app_XianShiXueXiaoMing")
	@ResponseBody
	public XueXiao app_XianShiXueXiaoMing(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String xuexiaoXuehao = request.getParameter("xuexiaoXuehao");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			String xuexiaoxuehao[] = xuexiaoXuehao.split("_");
			XueXiao xueXiao = xueXiaoService.selectByID(Integer.parseInt(xuexiaoxuehao[0]));
			if (xueXiao != null) {
				return xueXiao;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	// 根据课程id来查询该课程，并返回数据
	@RequestMapping(value = "app_XianShiDanMenKeCheng")
	@ResponseBody
	public Object app_XianShiDanMenKeCheng(HttpServletRequest request) throws Exception {
		String xueshengid = request.getParameter("xueshengid");
		String id = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String zhouci = request.getParameter("zhouci");
		String riqi = request.getParameter("riqi");
		String kaishijieci = request.getParameter("kaishijieci");
		String jieshujieci = request.getParameter("jieshujieci");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		Map<String, Object> map = new HashMap<>();
		if (str.equals(token)) {
			KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			String xueXiaoID = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid)).getXuexiaoXuehao().split("_")[0];
			String xueXiaoID = banJiService.findXueXiaoIDByBanJiID(xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid)).getBanjiid());
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("riqi", riqi);
			paramMap.put("xueXiaoID", xueXiaoID);
			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(paramMap);
			Map<String,Integer> maps = new HashMap<>();
			maps.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			maps.put("zhuangtai", 1);
			JieCiFangAn jieCiFangAn = jiecifanganService.selectByxueXiaoIDAndZhuangTai(maps);

			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(format.parse(xueQi.getKaishiriqi()));
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(format.parse(riqi));
			int day1 = cal1.get(Calendar.DAY_OF_YEAR);
			int day2 = cal2.get(Calendar.DAY_OF_YEAR);
			int year1 = cal1.get(Calendar.YEAR);
			int year2 = cal2.get(Calendar.YEAR);
			int timeDistance = 0;
			if (year1 != year2) { // 不同年
				for (int i = year1; i < year2; i++) {
					if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {// 闰年
						timeDistance += 366;
					} else { // 不是闰年
						timeDistance += 365;
					}
					timeDistance += day2 - day1;
				}
			} else {// 同年
				timeDistance = day2 - day1;
			}
			int zhou = timeDistance / 7 + 1;

//			KeCheng kecheng = new KeCheng();
			List<Map<String, Object>> shiJianMap = kechengService.getShangKeShiJianByID(id);
			for (Map<String, Object> map2 : shiJianMap) {
				if(map2.containsKey("shiJianLeiXing") && map2.get("shiJianLeiXing").toString().equals("1")){
					if(zhou <= Integer.parseInt(map2.get("jieShuZhou").toString())
							&& zhou >= Integer.parseInt(map2.get("kaiShiZhou").toString())
							&& zhouci.equals(map2.get("zhouCi").toString())){
						int kaishi = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), kaishijieci);
						int jieshu = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), jieshujieci);
						if(kaishi==Integer.parseInt(map2.get("kaiShiJieCi").toString())
								&& jieshu==Integer.parseInt(map2.get("jieShuJieCi").toString())){
							Map<String,String> m = new HashMap<>();
							m.put("yinyongid", id);
							m.put("beizhurenid", xueshengid);
							m.put("status", status);
							List<BeiZhu> beiZhus = beiZhuService.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(m);
							for (BeiZhu beiZhu : beiZhus) {
								if(beiZhu.getLeixing()==1){
									keCheng.setKechengbeizhu(beiZhu.getBeizhuneirong());
								}
								if(beiZhu.getLeixing()==2){
									String tiaojian=zhou+","+zhouci+","+kaishi+","+jieshu;
									if(tiaojian.equals(beiZhu.getTiaojian())){
										keCheng.setJiekechengbeizhu(beiZhu.getBeizhuneirong());
									}
								}
							}
							keCheng.setKaishizhou(Integer.parseInt(map2.get("kaiShiZhou").toString()));
							keCheng.setJieshuzhou(Integer.parseInt(map2.get("jieShuZhou").toString()));
							keCheng.setKaishijieci(Integer.parseInt(kaishijieci));
							keCheng.setJieshujieci(Integer.parseInt(jieshujieci));
							keCheng.setZhouci(Integer.parseInt(zhouci));
							keCheng.setKaishishijian(jcsjService.selectByPrimaryKey(kaishi).getKaishishijian());
							keCheng.setJieshushijian(jcsjService.selectByPrimaryKey(jieshu).getJieshushijian());
							JiaoShi js = jiaoshiService.selectByPrimaryKey(Integer.parseInt(map2.get("jiaoShiID").toString()));
							JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
							keCheng.setJiaoshiming(js.getJiaoshiming());
							keCheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
							keCheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
						}
					}
				}
				if(map2.containsKey("shiJianLeiXing") && map2.get("shiJianLeiXing").toString().equals("2")){
					if(zhou%2!=0){
						if(zhou <= Integer.parseInt(map2.get("jieShuZhou").toString())
							&& zhou >= Integer.parseInt(map2.get("kaiShiZhou").toString())
							&& zhouci.equals(map2.get("zhouCi").toString())){
							int kaishi = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), kaishijieci);
							int jieshu = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), jieshujieci);
							if(kaishi==Integer.parseInt(map2.get("kaiShiJieCi").toString())
									&& jieshu==Integer.parseInt(map2.get("jieShuJieCi").toString())){
								Map<String,String> m = new HashMap<>();
								m.put("yinyongid", id);
								m.put("beizhurenid", xueshengid);
								m.put("status", status);
								List<BeiZhu> beiZhus = beiZhuService.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(m);
								for (BeiZhu beiZhu : beiZhus) {
									if(beiZhu.getLeixing()==1){
										keCheng.setKechengbeizhu(beiZhu.getBeizhuneirong());
									}
									if(beiZhu.getLeixing()==2){
										String tiaojian=zhou+","+zhouci+","+kaishi+","+jieshu;
										if(tiaojian.equals(beiZhu.getTiaojian())){
											keCheng.setJiekechengbeizhu(beiZhu.getBeizhuneirong());
										}
									}
								}
								keCheng.setKaishizhou(Integer.parseInt(map2.get("kaiShiZhou").toString()));
								keCheng.setJieshuzhou(Integer.parseInt(map2.get("jieShuZhou").toString()));
								keCheng.setKaishijieci(Integer.parseInt(kaishijieci));
								keCheng.setJieshujieci(Integer.parseInt(jieshujieci));
								keCheng.setZhouci(Integer.parseInt(zhouci));
								keCheng.setKaishishijian(jcsjService.selectByPrimaryKey(kaishi).getKaishishijian());
								keCheng.setJieshushijian(jcsjService.selectByPrimaryKey(jieshu).getJieshushijian());
								JiaoShi js = jiaoshiService.selectByPrimaryKey(Integer.parseInt(map2.get("jiaoShiID").toString()));
								JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
								keCheng.setJiaoshiming(js.getJiaoshiming());
								keCheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
								keCheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
							}
						}
					}
				}
				if(map2.containsKey("shiJianLeiXing") && map2.get("shiJianLeiXing").toString().equals("3")){
					if(zhou%2==0){
						if(zhou <= Integer.parseInt(map2.get("jieShuZhou").toString())
							&& zhou >= Integer.parseInt(map2.get("kaiShiZhou").toString())
							&& zhouci.equals(map2.get("zhouCi").toString())){
							int kaishi = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), kaishijieci);
							int jieshu = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), jieshujieci);
							if(kaishi==Integer.parseInt(map2.get("kaiShiJieCi").toString())
									&& jieshu==Integer.parseInt(map2.get("jieShuJieCi").toString())){
								Map<String,String> m = new HashMap<>();
								m.put("yinyongid", id);
								m.put("beizhurenid", xueshengid);
								m.put("status", status);
								List<BeiZhu> beiZhus = beiZhuService.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(m);
								for (BeiZhu beiZhu : beiZhus) {
									if(beiZhu.getLeixing()==1){
										keCheng.setKechengbeizhu(beiZhu.getBeizhuneirong());
									}
									if(beiZhu.getLeixing()==2){
										String tiaojian=zhou+","+zhouci+","+kaishi+","+jieshu;
										if(tiaojian.equals(beiZhu.getTiaojian())){
											keCheng.setJiekechengbeizhu(beiZhu.getBeizhuneirong());
										}
									}
								}
								keCheng.setKaishizhou(Integer.parseInt(map2.get("kaiShiZhou").toString()));
								keCheng.setJieshuzhou(Integer.parseInt(map2.get("jieShuZhou").toString()));
								keCheng.setKaishijieci(Integer.parseInt(kaishijieci));
								keCheng.setJieshujieci(Integer.parseInt(jieshujieci));
								keCheng.setZhouci(Integer.parseInt(zhouci));
								keCheng.setKaishishijian(jcsjService.selectByPrimaryKey(kaishi).getKaishishijian());
								keCheng.setJieshushijian(jcsjService.selectByPrimaryKey(jieshu).getJieshushijian());
								JiaoShi js = jiaoshiService.selectByPrimaryKey(Integer.parseInt(map2.get("jiaoShiID").toString()));
								JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
								keCheng.setJiaoshiming(js.getJiaoshiming());
								keCheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
								keCheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
							}
						}
					}
				}
				if(map2.containsKey("shiJianLeiXing") && map2.get("shiJianLeiXing").toString().equals("4")){
					if(zhou == Integer.parseInt(map2.get("kaiShiZhou").toString())
							&& zhouci.equals(map2.get("zhouCi").toString())){
						int kaishi = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), kaishijieci);
						int jieshu = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), jieshujieci);
						if(kaishi==Integer.parseInt(map2.get("kaiShiJieCi").toString())
								&& jieshu==Integer.parseInt(map2.get("jieShuJieCi").toString())){
							Map<String,String> m = new HashMap<>();
							m.put("yinyongid", id);
							m.put("beizhurenid", xueshengid);
							m.put("status", status);
							List<BeiZhu> beiZhus = beiZhuService.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(m);
							for (BeiZhu beiZhu : beiZhus) {
								if(beiZhu.getLeixing()==1){
									keCheng.setKechengbeizhu(beiZhu.getBeizhuneirong());
								}
								if(beiZhu.getLeixing()==2){
									String tiaojian=zhou+","+zhouci+","+kaishi+","+jieshu;
									if(tiaojian.equals(beiZhu.getTiaojian())){
										keCheng.setJiekechengbeizhu(beiZhu.getBeizhuneirong());
									}
								}
							}
							keCheng.setKaishizhou(Integer.parseInt(map2.get("kaiShiZhou").toString()));
							keCheng.setKaishijieci(Integer.parseInt(kaishijieci));
							keCheng.setJieshujieci(Integer.parseInt(jieshujieci));
							keCheng.setZhouci(Integer.parseInt(zhouci));
							keCheng.setKaishishijian(jcsjService.selectByPrimaryKey(kaishi).getKaishishijian());
							keCheng.setJieshushijian(jcsjService.selectByPrimaryKey(jieshu).getJieshushijian());
							JiaoShi js = jiaoshiService.selectByPrimaryKey(Integer.parseInt(map2.get("jiaoShiID").toString()));
							JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
							keCheng.setJiaoshiming(js.getJiaoshiming());
							keCheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
							keCheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
						}
					}
				}
				if(map2.containsKey("shiJianLeiXing") && map2.get("shiJianLeiXing").toString().equals("6")){
					if(zhou == Integer.parseInt(map2.get("kaiShiZhou").toString())
							&& zhouci.equals(map2.get("zhouCi").toString())){
						int kaishi = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), kaishijieci);
						int jieshu = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), jieshujieci);
						if(kaishi==Integer.parseInt(map2.get("kaiShiJieCi").toString())
								&& jieshu==Integer.parseInt(map2.get("jieShuJieCi").toString())){
							Map<String,String> m = new HashMap<>();
							m.put("yinyongid", id);
							m.put("beizhurenid", xueshengid);
							m.put("status", status);
							List<BeiZhu> beiZhus = beiZhuService.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(m);
							for (BeiZhu beiZhu : beiZhus) {
								if(beiZhu.getLeixing()==1){
									keCheng.setKechengbeizhu(beiZhu.getBeizhuneirong());
								}
								if(beiZhu.getLeixing()==2){
									String tiaojian=zhou+","+zhouci+","+kaishi+","+jieshu;
									if(tiaojian.equals(beiZhu.getTiaojian())){
										keCheng.setJiekechengbeizhu(beiZhu.getBeizhuneirong());
									}
								}
							}
							keCheng.setKaishizhou(Integer.parseInt(map2.get("kaiShiZhou").toString()));
							keCheng.setKaishijieci(Integer.parseInt(kaishijieci));
							keCheng.setJieshujieci(Integer.parseInt(jieshujieci));
							keCheng.setZhouci(Integer.parseInt(zhouci));
							keCheng.setKaishishijian(jcsjService.selectByPrimaryKey(kaishi).getKaishishijian());
							keCheng.setJieshushijian(jcsjService.selectByPrimaryKey(jieshu).getJieshushijian());
							JiaoShi js = jiaoshiService.selectByPrimaryKey(Integer.parseInt(map2.get("jiaoShiID").toString()));
							JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
							keCheng.setJiaoshiming(js.getJiaoshiming());
							keCheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
							keCheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
						}
					}
				}
			}
			
			
			
			/**
			String danShuangZhouShuoMing[] = keCheng.getDanshuangzhoushuoming().split("zqxj");
			switch (danShuangZhouShuoMing[0]) {
			case "1": // 连续
				String zhouCi[] = danShuangZhouShuoMing[1].split(",");
				// 判断周次是否在
				if (Integer.parseInt(zhouCi[0]) <= zhou && Integer.parseInt(zhouCi[1]) >= zhou
						&& keCheng.getXuenian().equals(xueQi.getXuenian())
						&& keCheng.getXueqi().toString().equals(xueQi.getXueqi().toString())) {

					// 获取周几，节次，教室
					String total[] = danShuangZhouShuoMing[2].split("zxqj");
					for (String string2 : total) {
						String xinxi[] = string2.split(",");
						if (xinxi[0].equals(zhouci)) {

							String beizhuid = keCheng.getBeizhuid();
							String beiZhuids = "";
							if (!("".equals(beizhuid)) && null != beizhuid) {
								String ids[] = beizhuid.split(";");
								for (String string6 : ids) {
									String bz[] = string6.split(",");
									if (bz[0].equals(xueshengid)) {
										beiZhuids += bz[1] + ",";
									}
								}
								if (!("".equals(beiZhuids)) && null != beiZhuids) {
									String jiekechengbeizhu = zhou + "," + zhouci + "," + xinxi[1] + "," + xinxi[2];
									String beiZhu[] = beiZhuids.split(",");
									for (String string6 : beiZhu) {
										BeiZhu keChengBeiZhu = beiZhuService
												.selectByPrimaryKey(Integer.parseInt(string6));
										if (keChengBeiZhu.getLeixing() == 1
												&& keChengBeiZhu.getYinyongid() == keCheng.getId()
												&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
												&& keChengBeiZhu.getStatus().equals(status)) {
											kecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong());
										}
										if (keChengBeiZhu.getLeixing() == 2
												&& keChengBeiZhu.getYinyongid() == keCheng.getId()
												&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
												&& keChengBeiZhu.getStatus().equals(status)) {
											if (jiekechengbeizhu.equals(keChengBeiZhu.getTiaojian())) {
												kecheng.setJiekechengbeizhu(keChengBeiZhu.getBeizhuneirong());
											}
										}
									}
								}
							}

							kecheng.setId(keCheng.getId());
							kecheng.setKechengid(keCheng.getKechengid());
							kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
							kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
							kecheng.setKaishizhou(Integer.parseInt(zhouCi[0]));
							kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
							kecheng.setZhouci(Integer.parseInt(xinxi[0]));
							JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1]));
							JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
							kecheng.setKaishijieci(jcsj1.getJieci());
							kecheng.setJieshujieci(jcsj2.getJieci());
							kecheng.setKaishishijian(jcsj1.getKaishishijian());
							kecheng.setJieshushijian(jcsj2.getJieshushijian());

							JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
							kecheng.setJiaoshiming(jshi.getJiaoshiming());
							kecheng.setJiaoXueLouMing(
									jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
							kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng());
						}
					}
				}
				// 调课
				String tiaoKe[] = danShuangZhouShuoMing[4].split("zqjx");
				if (tiaoKe.length > 0) {
					for (String string2 : tiaoKe) {
						if (riqi.equals(string2.split(",")[0])) {
							String kc[] = string2.split(",");

							String beizhuid = keCheng.getBeizhuid();
							String beiZhuids = "";
							if (!("".equals(beizhuid)) && null != beizhuid) {
								String ids[] = beizhuid.split(";");
								for (String string6 : ids) {
									String bz[] = string6.split(",");
									if (bz[0].equals(xueshengid)) {
										beiZhuids += bz[1] + ",";
									}
								}
								if (!("".equals(beiZhuids)) && null != beiZhuids) {
									String jiekechengbeizhu = zhou + "," + zhouci + "," + kc[1] + "," + kc[2];
									String beiZhu[] = beiZhuids.split(",");
									for (String string6 : beiZhu) {
										BeiZhu keChengBeiZhu = beiZhuService
												.selectByPrimaryKey(Integer.parseInt(string6));
										if (keChengBeiZhu.getLeixing() == 1
												&& keChengBeiZhu.getYinyongid() == keCheng.getId()
												&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
												&& keChengBeiZhu.getStatus().equals(status)) {
											kecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong());
										}
										if (keChengBeiZhu.getLeixing() == 2
												&& keChengBeiZhu.getYinyongid() == keCheng.getId()
												&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
												&& keChengBeiZhu.getStatus().equals(status)) {
											if (jiekechengbeizhu.equals(keChengBeiZhu.getTiaojian())) {
												kecheng.setJiekechengbeizhu(keChengBeiZhu.getBeizhuneirong());
											}
										}
									}
								}
							}

							kecheng.setId(keCheng.getId());
							kecheng.setKechengid(keCheng.getKechengid());
							kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
							kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
							kecheng.setKaishizhou(zhou);
							// kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
							kecheng.setZhouci(Integer.parseInt(zhouci));
							JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1]));
							JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
							kecheng.setKaishijieci(jcsj1.getJieci());
							kecheng.setJieshujieci(jcsj2.getJieci());
							kecheng.setKaishishijian(jcsj1.getKaishishijian());
							kecheng.setJieshushijian(jcsj2.getJieshushijian());

							JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
							kecheng.setJiaoshiming(jshi.getJiaoshiming());
							kecheng.setJiaoXueLouMing(
									jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
							kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng());
						}
					}
				}
				break;

			case "2": // 单双周
				String zhouCi2[] = danShuangZhouShuoMing[1].split(",");
				// 判断周次是否在
				if (Integer.parseInt(zhouCi2[0]) <= zhou && Integer.parseInt(zhouCi2[1]) >= zhou
						&& keCheng.getXuenian().equals(xueQi.getXuenian())
						&& keCheng.getXueqi().toString().equals(xueQi.getXueqi().toString())) {
					String danShuang[] = danShuangZhouShuoMing[2].split("zqjx");
					if (zhou % 2 == 0) { // 双周
						// 获取周几，节次，教室
						String total[] = danShuang[1].split("zxqj");
						for (String string2 : total) {
							String xinxi[] = string2.split(",");
							if (xinxi[0].equals(zhouci)) {

								String beizhuid = keCheng.getBeizhuid();
								String beiZhuids = "";
								if (!("".equals(beizhuid)) && null != beizhuid) {
									String ids[] = beizhuid.split(";");
									for (String string6 : ids) {
										String bz[] = string6.split(",");
										if (bz[0].equals(xueshengid)) {
											beiZhuids += bz[1] + ",";
										}
									}
									if (!("".equals(beiZhuids)) && null != beiZhuids) {
										String jiekechengbeizhu = zhou + "," + zhouci + "," + xinxi[1] + "," + xinxi[2];
										String beiZhu[] = beiZhuids.split(",");
										for (String string6 : beiZhu) {
											BeiZhu keChengBeiZhu = beiZhuService
													.selectByPrimaryKey(Integer.parseInt(string6));
											if (keChengBeiZhu.getLeixing() == 1
													&& keChengBeiZhu.getYinyongid() == keCheng.getId()
													&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
													&& keChengBeiZhu.getStatus().equals(status)) {
												kecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong());
											}
											if (keChengBeiZhu.getLeixing() == 2
													&& keChengBeiZhu.getYinyongid() == keCheng.getId()
													&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
													&& keChengBeiZhu.getStatus().equals(status)) {
												if (jiekechengbeizhu.equals(keChengBeiZhu.getTiaojian())) {
													kecheng.setJiekechengbeizhu(keChengBeiZhu.getBeizhuneirong());
												}
											}
										}
									}
								}

								kecheng.setKechengid(keCheng.getKechengid());
								kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
								kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
								kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
								kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
								kecheng.setZhouci(Integer.parseInt(xinxi[0]));
								JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1]));
								JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
								kecheng.setKaishijieci(jcsj1.getJieci());
								kecheng.setJieshujieci(jcsj2.getJieci());
								kecheng.setKaishishijian(jcsj1.getKaishishijian());
								kecheng.setJieshushijian(jcsj2.getJieshushijian());

								JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
								kecheng.setJiaoshiming(jshi.getJiaoshiming());
								kecheng.setJiaoXueLouMing(jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())
										.getJiaoXueLouMing());
								kecheng.setXiaoquming(
										xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng());
							}
						}

					} else { // 单周
								// 获取周几，节次，教室
						String total[] = danShuang[0].split("zxqj");
						for (String string2 : total) {
							String xinxi[] = string2.split(",");
							if (xinxi[0].equals(zhouci)) {

								String beizhuid = keCheng.getBeizhuid();
								String beiZhuids = "";
								if (!("".equals(beizhuid)) && null != beizhuid) {
									String ids[] = beizhuid.split(";");
									for (String string6 : ids) {
										String bz[] = string6.split(",");
										if (bz[0].equals(xueshengid)) {
											beiZhuids += bz[1] + ",";
										}
									}
									if (!("".equals(beiZhuids)) && null != beiZhuids) {
										String jiekechengbeizhu = zhou + "," + zhouci + "," + xinxi[1] + "," + xinxi[2];
										String beiZhu[] = beiZhuids.split(",");
										for (String string6 : beiZhu) {
											BeiZhu keChengBeiZhu = beiZhuService
													.selectByPrimaryKey(Integer.parseInt(string6));
											if (keChengBeiZhu.getLeixing() == 1
													&& keChengBeiZhu.getYinyongid() == keCheng.getId()
													&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
													&& keChengBeiZhu.getStatus().equals(status)) {
												kecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong());
											}
											if (keChengBeiZhu.getLeixing() == 2
													&& keChengBeiZhu.getYinyongid() == keCheng.getId()
													&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
													&& keChengBeiZhu.getStatus().equals(status)) {
												if (jiekechengbeizhu.equals(keChengBeiZhu.getTiaojian())) {
													kecheng.setJiekechengbeizhu(keChengBeiZhu.getBeizhuneirong());
												}
											}
										}
									}
								}

								kecheng.setId(keCheng.getId());
								kecheng.setKechengid(keCheng.getKechengid());
								kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
								kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
								kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
								kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
								kecheng.setZhouci(Integer.parseInt(xinxi[0]));
								JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1]));
								JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
								kecheng.setKaishijieci(jcsj1.getJieci());
								kecheng.setJieshujieci(jcsj2.getJieci());
								kecheng.setKaishishijian(jcsj1.getKaishishijian());
								kecheng.setJieshushijian(jcsj2.getJieshushijian());

								JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
								kecheng.setJiaoshiming(jshi.getJiaoshiming());
								kecheng.setJiaoXueLouMing(jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())
										.getJiaoXueLouMing());
								kecheng.setXiaoquming(
										xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng());
							}
						}
					}
				}
				String tiaoKe2[] = danShuangZhouShuoMing[4].split("zqjx");
				if (tiaoKe2.length > 0) {
					for (String string3 : tiaoKe2) {
						if (riqi.equals(string3.split(",")[0])) {
							String kc[] = string3.split(",");

							String beizhuid = keCheng.getBeizhuid();
							String beiZhuids = "";
							if (!("".equals(beizhuid)) && null != beizhuid) {
								String ids[] = beizhuid.split(";");
								for (String string6 : ids) {
									String bz[] = string6.split(",");
									if (bz[0].equals(xueshengid)) {
										beiZhuids += bz[1] + ",";
									}
								}
								if (!("".equals(beiZhuids)) && null != beiZhuids) {
									String jiekechengbeizhu = zhou + "," + zhouci + "," + kc[1] + "," + kc[2];
									String beiZhu[] = beiZhuids.split(",");
									for (String string6 : beiZhu) {
										BeiZhu keChengBeiZhu = beiZhuService
												.selectByPrimaryKey(Integer.parseInt(string6));
										if (keChengBeiZhu.getLeixing() == 1
												&& keChengBeiZhu.getYinyongid() == keCheng.getId()
												&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
												&& keChengBeiZhu.getStatus().equals(status)) {
											kecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong());
										}
										if (keChengBeiZhu.getLeixing() == 2
												&& keChengBeiZhu.getYinyongid() == keCheng.getId()
												&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
												&& keChengBeiZhu.getStatus().equals(status)) {
											if (jiekechengbeizhu.equals(keChengBeiZhu.getTiaojian())) {
												kecheng.setJiekechengbeizhu(keChengBeiZhu.getBeizhuneirong());
											}
										}
									}
								}
							}

							kecheng.setKechengid(keCheng.getKechengid());
							kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
							kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
							kecheng.setKaishizhou(zhou);
							// kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
							kecheng.setZhouci(Integer.parseInt(zhouci));
							JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1]));
							JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
							kecheng.setKaishijieci(jcsj1.getJieci());
							kecheng.setJieshujieci(jcsj2.getJieci());
							kecheng.setKaishishijian(jcsj1.getKaishishijian());
							kecheng.setJieshushijian(jcsj2.getJieshushijian());

							JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
							kecheng.setJiaoshiming(jshi.getJiaoshiming());
							kecheng.setJiaoXueLouMing(
									jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
							kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng());
						}
					}
				}
				break;
			case "3": // 单次，不连续,只需比对时间
				String kcheng[] = danShuangZhouShuoMing[1].split("zqjx");
				for (String string4 : kcheng) {
					if (riqi.equals(string4.split(",")[0])) {
						String kc[] = string4.split(",");

						String beizhuid = keCheng.getBeizhuid();
						String beiZhuids = "";
						if (!("".equals(beizhuid)) && null != beizhuid) {
							String ids[] = beizhuid.split(";");
							for (String string6 : ids) {
								String bz[] = string6.split(",");
								if (bz[0].equals(xueshengid)) {
									beiZhuids += bz[1] + ",";
								}
							}
							if (!("".equals(beiZhuids)) && null != beiZhuids) {
								String jiekechengbeizhu = zhou + "," + zhouci + "," + kc[1] + "," + kc[2];
								String beiZhu[] = beiZhuids.split(",");
								for (String string6 : beiZhu) {
									BeiZhu keChengBeiZhu = beiZhuService.selectByPrimaryKey(Integer.parseInt(string6));
									if (keChengBeiZhu.getLeixing() == 1
											&& keChengBeiZhu.getYinyongid() == keCheng.getId()
											&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
											&& keChengBeiZhu.getStatus().equals(status)) {
										kecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong());
									}
									if (keChengBeiZhu.getLeixing() == 2
											&& keChengBeiZhu.getYinyongid() == keCheng.getId()
											&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
											&& keChengBeiZhu.getStatus().equals(status)) {
										if (jiekechengbeizhu.equals(keChengBeiZhu.getTiaojian())) {
											kecheng.setJiekechengbeizhu(keChengBeiZhu.getBeizhuneirong());
										}
									}
								}
							}
						}

						kecheng.setKechengid(keCheng.getKechengid());
						kecheng.setLeixing(3);
						kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
						kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
						kecheng.setKaishizhou(zhou);
						// kecheng.setJieshuzhou(zhou);
						kecheng.setZhouci(Integer.parseInt(zhouci));
						JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1]));
						JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
						kecheng.setKaishijieci(jcsj1.getJieci());
						kecheng.setJieshujieci(jcsj2.getJieci());
						kecheng.setKaishishijian(jcsj1.getKaishishijian());
						kecheng.setJieshushijian(jcsj2.getJieshushijian());

						JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
						kecheng.setJiaoshiming(jshi.getJiaoshiming());
						kecheng.setJiaoXueLouMing(
								jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
						kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng());
					}
				}
			}
			*/

			// int kaishijieci = keCheng.getKaishijieci();
			// int jieshujieci = keCheng.getJieshujieci();
			// JCSJ jcsj1 =jcsjService.selectByPrimaryKey(kaishijieci);
			// JCSJ jcsj2 =jcsjService.selectByPrimaryKey(jieshujieci);
			// String kaishishijian =
			// jcsjService.getkaiShiShiJianByid(jcsj1.getId());
			// String jieshushijian =
			// jcsjService.getjieShuShiJianByid(jcsj2.getId());
			// keCheng.setKaishishijian(kaishishijian);
			// keCheng.setJieshushijian(jieshushijian);
			// keCheng.setKaishijieci(jcsj1.getJieci());
			// keCheng.setJieshujieci(jcsj2.getJieci());
			// String xiaoquming =
			// xiaoquService.selectByPrimaryKey(Integer.parseInt(keCheng.getXiaoqu()))
			// .getMingcheng();
			// String jiaoshiming =
			// jiaoshiService.selectByPrimaryKey(Integer.parseInt(keCheng.getShangkejiaoshi()))
			// .getJiaoshiming();
			// keCheng.setXiaoquming(xiaoquming);
			// keCheng.setJiaoshiming(jiaoshiming);
			map.put("kecheng", keCheng);
			return JSON.toJSON(map);
		} else {
			return null;
		}
	}

	// 根据huodongid来查询本次活动
	@RequestMapping(value = "app_XianShiDanCiHuoDong")
	@ResponseBody
	public Object app_XianShiDanCiHuoDong(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String id = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		Map<String, Object> map = new HashMap<>();
		if (str.equals(token)) {
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			YongHu yongHu = yongHuService.selectYongHuByID(huoDong.getTianjiarenid());
			Map<String,String> paramMap = new HashMap<>();
			paramMap.put("huodongid", id);
			paramMap.put("renid", xueshengid);
			paramMap.put("renleixing", "0");
			List<Map<String, Object>> canYunRens = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
			List<Map<String, Object>> juJueRens = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
			if(huoDong.getTianjiarenid().toString().equals(xueshengid)){
				if(juJueRens!=null &&juJueRens.size()>0){
					huoDong.setZhuangtai(2);
				}else{
					huoDong.setZhuangtai(1);
				}
			}else{
				if(juJueRens!=null &&juJueRens.size()>0){
					huoDong.setZhuangtai(2);
				}else if(canYunRens!=null &&canYunRens.size()>0){
					huoDong.setZhuangtai(1);
				}else{
					huoDong.setZhuangtai(0);
				}
			}
			/**
			String s = "";
			String canyuren[] = huoDong.getCanyuren().split(";");
			for (String string : canyuren) {
				String canyurenid[] = string.split(",");
				if (canyurenid[1].equals(xueshengid) && canyurenid[0].equals("0")) {
					if (canyurenid[2].equals("0")) {
						s = canyurenid[2];
					}
					if (canyurenid[2].equals("1")) {
						s = canyurenid[2];
					}
					if (canyurenid[2].equals("2")) {
						huoDong.setZhuangtai(2);
					}
				}
			}
			if ("".equals(s)) {
				huoDong.setZhuangtai(0);
			} else {
				huoDong.setZhuangtai(Integer.parseInt(s));
			}
			 */
			if (yongHu != null) {
				huoDong.setFaqiren(yongHu.getYonghuxingming());
			} else {
				XueSheng xueSheng = xueshengService.getUserById(huoDong.getTianjiarenid());
				huoDong.setFaqiren(xueSheng.getXingming());
			}
			
			paramMap.put("yinyongid", id);
			paramMap.put("beizhurenid", xueshengid);
			paramMap.put("leixing", "3");
			paramMap.put("status", status);
			List<BeiZhu> beiZhuS = beiZhuService.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(paramMap);
			if(beiZhuS != null && beiZhuS.size()>0){
				for (BeiZhu beiZhu : beiZhuS) {
					huoDong.setBeizhu(beiZhu.getBeizhuneirong());
				}
			}else{
				huoDong.setBeizhu("");
			}
			/**
			String beizhuids = huoDong.getBeizhuids();
			if (!"".equals(beizhuids) && null != beizhuids) {
				String beizhuid[] = beizhuids.split(";");
				int i=0;
				for (String string : beizhuid) {
					String strs[] = string.split(",");
					if (strs[0].equals(xueshengid)) {
						BeiZhu beiZhu = beiZhuService.selectByPrimaryKey(Integer.parseInt(strs[1]));
						if (beiZhu.getLeixing() == 3 && beiZhu.getYinyongid() == Integer.parseInt(id)
								&& beiZhu.getBeizhurenid() == Integer.parseInt(xueshengid) && beiZhu.getStatus().equals(status)) {
								i=Integer.parseInt(strs[1]);
						}
					}
				}
				if(i!=0){
					BeiZhu beiZhu = beiZhuService.selectByPrimaryKey(i);
					huoDong.setBeizhuids(beiZhu.getBeizhuneirong()); // 备注内容
				}else{
					huoDong.setBeizhuids("");
				}
			}
			*/
			map.put("huodong", huoDong);
			return JSON.toJSON(map);
		} else {
			return null;
		}
	}

	// 根据备忘录id查询一次备忘，返回数据
	@RequestMapping(value = "app_XianShiDanCiBeiWang")
	@ResponseBody
	public JSONObject app_XianShiDanCiBeiWang(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String id = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		Map<String, Object> map = new HashMap<>();
		if (str.equals(token)) {
			BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			YongHu yongHu = yongHuService.selectYongHuByID(Integer.parseInt(beiWL.getUserid()));
			if (yongHu != null) {
				beiWL.setUsername(yongHu.getYonghuxingming());
			} else {
				XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(beiWL.getUserid()));
				beiWL.setUsername(xueSheng.getXingming());
			}
			Map<String,String> paramMap = new HashMap<>();
			paramMap.put("beiwlid", id);
			paramMap.put("renid", xueshengid);
			paramMap.put("renleixing", "0");
			List<Map<String, Object>> huiZhis = beiwlService.getBeiWLHuiZhiByBeiWLIDAndRenIDAndRenLeiXing(paramMap);
			if(huiZhis != null && huiZhis.size() > 0){
				for (Map<String, Object> map2 : huiZhis) {
					beiWL.setZhuangtai(map2.get("zhuangTai").toString());
				}
			}else{
				beiWL.setZhuangtai("0");
			}

			/**
			if (beiWL.getLeixing() == 2) {
				String jieshourens[] = beiWL.getJieshouren().split(";");
				for (String jieshouren : jieshourens) {
					String jieshou[] = jieshouren.split(",");
					if (jieshou[0].equals(xueshengid)) {
						beiWL.setZhuangtai(jieshou[1]);
					}
				}
			}
			if (beiWL.getLeixing() == 1) {
				if (beiWL.getBanjiids() == null || "".equals(beiWL.getBanjiids())) {
					String jieshourens[] = beiWL.getJieshouren().split(";");
					for (String jieshouren : jieshourens) {
						String jieshou[] = jieshouren.split(":");
						String sid[] = jieshou[1].split("!");
						for (String string : sid) {
							String[] s = string.split(",");
							if (s[0].equals(xueshengid)) {
								beiWL.setZhuangtai(s[1]);
							}
						}
					}
				} else {
					if (beiWL.getJieshouren() == null || "".equals(beiWL.getJieshouren())) {
						beiWL.setZhuangtai("0");
					} else {
						String jieshourens[] = beiWL.getJieshouren().split(";");
						int i = 0;
						for (String jieshouren : jieshourens) {
							String jieshou[] = jieshouren.split(",");
							if (jieshou[0].equals(xueshengid)) {
								beiWL.setZhuangtai(jieshou[1]);
								i = 1;
								break;
							}
						}
						if (i == 0) {
							beiWL.setZhuangtai("0");
						}
					}
				}
			}
			*/

			map.put("beiwang", beiWL);
			System.out.println(beiWL.getNeirong());
			return JSONObject.fromObject(map);
		} else {
			return null;
		}
	}

	// 对事件进行回执
	@RequestMapping(value = "app_HuiZhiShiJian")
	@ResponseBody
	public String app_HuiZhiShiJian(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String radio = request.getParameter("radio");
		String token = request.getParameter("token");
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			XueSheng xueSheng = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
			BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			int i = 0;
			try {
				Map<String,String> map = new HashMap<>();
				map.put("beiwlid", id);
				map.put("huizhirenid", xueSheng.getXueshengid().toString());
				map.put("zhuangtai", radio);
				map.put("leixing", "0");
				beiwlService.insert_beiwlhuizhi(map);
				i = 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (i != 0) {
				if (!("0".equals(radio))) {
					// 发送激光消息
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					xiaoXiFaSong.setXiaoXiMingCheng("回执信息");
					xiaoXiFaSong.setXiaoXiNeiRong(xueSheng.getXingming() + "回执事件$" + beiWL.getNeirong() + "$");
					xiaoXiFaSong.setShuJuId(beiWL.getId());
					xiaoXiFaSong.setShuJuLeiXing(3);
					xiaoXiFaSong.setFaSongLeiXing(2);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(banJiService.findXueXiaoIDByBanJiID(xueSheng.getBanjiid()));
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					Date date = new Date();
					tiXing.setNeirong(xueSheng.getXingming() + "回执事件$" + beiWL.getNeirong() + "$");
					tiXing.setShijian(date);
					tiXing.setZhuangtai(0);
					tiXing.setShujuid(beiWL.getId());
					tiXing.setType(2);
					tiXing.setJieshourenid(Integer.parseInt(beiWL.getUserid()));
					tixingService.insert(tiXing);
					xiaoXiFaSong.setFaSongMuBiao(beiWL.getUserid());
					jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				}
				return "success";
			} else {
				return "fail";
			}
		} else {
			return null;
		}
	}

	// 拍照上传寝室的人照片
	@RequestMapping(value = "app_UpdateImgerBase64")
	@ResponseBody
	public Object app_UpdateImgerBase64(HttpServletRequest request) throws ParseException {
		String token = request.getParameter("token");
		String xueshengid = request.getParameter("xueshengid");
		String status = request.getParameter("status");
		String banjiid = request.getParameter("banjiid");
		String base64Str = request.getParameter("base64Str");
		String anpaiid = request.getParameter("anpaiid");
		String susheid = request.getParameter("susheid");
		String id = request.getParameter("id");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			Map<String, Integer> map = new HashMap<>();
			Map<String, Object> map2 = new HashMap<>();
			map.put("anpaiid", Integer.parseInt(anpaiid));
			map.put("susheid", Integer.parseInt(susheid));
			ChaQinAnPai chaQinAnPai = chaQinService.selectChaQinAnPaiByPrimaryKey(Integer.parseInt(id));
			String date = getCurrTimeForString();
			String kaishi = chaQinAnPai.getRiqi() + " " + chaQinAnPai.getKaishishijian();
			String jieshu = chaQinAnPai.getRiqi() + " " + chaQinAnPai.getJieshushijian();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long kaiShiShiJian = sdf.parse(kaishi).getTime();
			long jieShuShiJian = sdf.parse(jieshu).getTime();
			long now = sdf.parse(date).getTime();
			if (now < kaiShiShiJian || now > jieShuShiJian) {
				map2.put("msg", "fail");
				return JSON.toJSON(map2);
			}
			String image[] = base64Str.split(",");
			String name = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
			String path = request.getSession().getServletContext().getRealPath("/") + "upload/cqap/" + id + "/";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			// String path="D:\\Program Files\\eclipse文件存放\\123.jpg";
			boolean x = UpdataImgerUtil.GenerateImage(image[1], path + name);
			Map<String, String> map3 = new HashMap<>();
			Map<String, String> map4 = new HashMap<>();
			if (x == true) {
				List<XueSheng> xueShengs = new ArrayList<>();
				List<XueSheng> xueShengs2 = new ArrayList<>();
				chaQinService.deleteChaQinJieGuoByanPaiIDAndsuSheID(map);
				FuDaoYuan fuDaoYuan = fuDaoYuanService.getBybanJiID(banjiid + ",%", "%," + banjiid + ",%");
				String banjis[] = fuDaoYuan.getBanjiid().split(",");
				for (String ban : banjis) {
					if (ban != null && ban != "") {
						map4.put("banjiid", ban);
						map4.put("susheid", susheid);
						xueShengs = xueshengService.selectXueShengBysuSheIDandbanJiID(map4);
						if (xueShengs != null && xueShengs.size() > 0) {
							xueShengs2.addAll(xueShengs);
						}
					}
				}
				for (XueSheng xueSheng : xueShengs2) {
					ChaQinJieGuo jieGuo = new ChaQinJieGuo();
					jieGuo.setAnpaiid(Integer.parseInt(anpaiid));
					jieGuo.setSusheid(Integer.parseInt(susheid));
					jieGuo.setXueshengid(xueSheng.getXueshengid().toString());
					jieGuo.setZhaopian(name);
					jieGuo.setShangchuanren(Integer.parseInt(xueshengid));
					chaQinService.insertChaQinJieGuo(jieGuo);
				}
				map2.put("msg", "OK");
			} else {
				map2.put("msg", "fail");
			}
			return JSON.toJSON(map2);
		} else {
			return null;
		}
	}

	// 更改头像
	@RequestMapping(value="app_updateTouXiang")
	@ResponseBody
	public Object app_updateTouXiang(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String id = request.getParameter("id");
		String str = Util.SHA1Encode(id+ "," + status);
		Map<String,String> map = new HashMap<>();
		if(str.equals(token)){
			String touxiang = request.getParameter("touxiang");
			String base64Str = request.getParameter("base64Str");
			
			String Type = touxiang.substring(touxiang.lastIndexOf(".") + 1);
			String name = UUID.randomUUID().toString().replaceAll("-", "");
			String path = request.getSession().getServletContext().getRealPath("/")+"upload"+File.separator;
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(path + name + "." + Type);
			boolean x = UpdataImgerUtil.GenerateImage(base64Str.split(",")[1], path + name + "." + Type);
			if(x == true){
				map.put("id", id);
				map.put("status", status);
				map.put("touxiang", name +"."+Type);
				try {
					int i = yongHuService.updateTouXiangByID(map);
					map.put("retInfo", "success");
				} catch (Exception e) {
					e.printStackTrace();
					map.put("retInfo", "fail");
				}
			}
		}
		return JSON.toJSON(map);
	}
	
	
	
	
	// 点击日程上的查寝返回相应数据
	@RequestMapping(value = "app_XianShiDanCiChaQin")
	@ResponseBody
	public Object app_XianShiDanCiChaQin(HttpServletRequest request) throws ParseException {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String banjiid = request.getParameter("banjiid");
		String strings = data[0] + "," + status;
		String str = Util.SHA1Encode(strings);
		Map<String, Integer> map = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		if (str.equals(token)) {
			ChaQinAnPai chaQinAnPai = chaQinService.selectChaQinAnPaiByPrimaryKey(Integer.parseInt(data[1]));
			map.put("susheid", Integer.parseInt(data[2]));
			map.put("anpaiid", Integer.parseInt(data[1]));
			List<ChaQinJieGuo> jieGuos = chaQinService.selectChaQinJieGuoByanPaiIDAndsuSheID(map);
			List<String> xingmings = new ArrayList<>();
			List<Integer> zhuangtais = new ArrayList<>();
			List<Map<String, String>> lists = new ArrayList<>();
			String zhaopian = "";
			String shangchuanren = "";
			if (jieGuos != null && jieGuos.size() > 0) {
				int jieguo = 1;
				for (ChaQinJieGuo chaQinJieGuo : jieGuos) {
					Map<String, String> mapp = new HashMap<>();
					XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(chaQinJieGuo.getXueshengid()));
					xingmings.add(xueSheng.getXingming());
					zhuangtais.add(chaQinJieGuo.getZhuangtai());
					shangchuanren = xueshengService.getUserById(chaQinJieGuo.getShangchuanren()).getXingming();
					zhaopian = chaQinJieGuo.getZhaopian();
					if (chaQinJieGuo.getZhuangtai() != -1) {
						if (chaQinJieGuo.getZhuangtai() == 1) {
							mapp.put("queqin", "不缺寝");
							mapp.put("qingjia", "");
						} else {
							// 判断是否请假
							Map<String, Object> paramMap = new HashMap<>();
							paramMap.put("zhuangtai", 2);
							paramMap.put("xueshengid", xueSheng.getXueshengid());
							paramMap.put("kaishishijian", chaQinAnPai.getRiqi() + chaQinAnPai.getKaishishijian());
							paramMap.put("jieshushijian", chaQinAnPai.getRiqi() + chaQinAnPai.getJieshushijian());
							Qingjia qingjia = qingjiaService.getByZhuangTaiAndShijianAndXueShengid(paramMap);
							if (qingjia != null) {
								mapp.put("qingjia", "请假");
								mapp.put("queqin", "缺寝");
							} else {
								mapp.put("qingjia", "没请假");
								mapp.put("queqin", "缺寝");
							}
						}
					} else {
						mapp.put("qingjia", "没请假");
						mapp.put("queqin", "缺寝");
					}
					lists.add(mapp);
				}
				map2.put("chaqin", chaQinAnPai);
				map2.put("chaqinqingjia", lists);
				map2.put("xingmings", xingmings);
				map2.put("zhuangtais", zhuangtais);
				map2.put("zhaopian", zhaopian);
				map2.put("shangchuanren", shangchuanren);
				map2.put("jieguo", jieguo);
				return JSON.toJSON(map2);
			} else {
				int jieguo = 0;
				map2.put("chaqin", chaQinAnPai);
				map2.put("xingmings", xingmings);
				map2.put("zhuangtais", zhuangtais);
				map2.put("zhaopian", zhaopian);
				map2.put("shangchuanren", shangchuanren);
				map2.put("jieguo", jieguo);
				return JSON.toJSON(map2);
			}
		} else {
			return null;
		}
	}

	public String getCurrTimeForString() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public String formatDate(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
}
