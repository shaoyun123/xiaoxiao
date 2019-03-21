package com.web.controller.app.stu;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.web.model.XueSheng;
import com.alibaba.fastjson.JSON;
import com.web.controller.app.common.AppPersonController;
import com.web.controller.base.BaseController;
import com.web.model.BanJi;
import com.web.model.Menu;
import com.web.model.Qingjia;
import com.web.model.XueQi;
import com.web.model.XueQiDeYu;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.util.PageData;
import com.web.util.Tools;
import com.web.util.UpdataImgerUtil;
import com.web.util.Util;
import com.web.service.BanJiService;
import com.web.service.DeYuService;
import com.web.service.MenuService;
import com.web.service.QingjiaService;
import com.web.service.XueQiService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//登录时获取学生表中该学生信息及请假类操作
@Controller
public class AppXueSheng extends BaseController {
	@Autowired
	private XueShengService xueShengService;
	@Autowired
	private QingjiaService qingjiaService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private YongHuService yonghuService;
	@Autowired
	private XueQiService xueqiService;
	@Autowired
	private DeYuService deYuService;

	@Autowired
	private BanJiService banJiService;

	// 获取xuesheng表中学生id，班级id，学号
	@RequestMapping(value = "app_toxuesheng")
	@ResponseBody
	public XueSheng loginrecord(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		XueSheng retInfo = new XueSheng();
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String data[] = code.split(",zytech,");
		map.put("id", data[0]);
		map.put("password", data[1]);
		map.put("status", data[2]);
		if (data[2].equals("xuesheng")) {
			XueSheng xueSheng = xueShengService.selectUserByIdAndPasswd(map);
			xueSheng.setStatus("xuesheng");
			String string = xueSheng.getXueshengid() + "," + data[2];
			String str = Util.SHA1Encode(string);
			if (str.equals(token)) {
				return xueSheng;
			} else {
				return null;
			}
		}
		return retInfo;

	}

	// 通过xueshengid查询该学生请假条信息
	@RequestMapping(value = "app_toqingjiatiao")
	@ResponseBody
	public Object myApplication(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String zhuangtai = request.getParameter("zhuangtai");
		String string = code + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			Map<String, Integer> map = new HashMap<>();
			Map<String, Object> mpp = new HashMap<>();
			int count = qingjiaService.getCountByXueShengID(Integer.parseInt(code));
			List<Qingjia> jiatiao = new ArrayList<>();

			int pageSize = 10;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				map.put("id", Integer.parseInt(code));
				map.put("start", 0);
				map.put("stop", 10);
				if ("0".equals(zhuangtai)) {
					jiatiao = qingjiaService.getAllByXueShengID(map);
				} else {
					map.put("zhuangtai", Integer.parseInt(zhuangtai));
					jiatiao = qingjiaService.getAllByXueShengIDAndZhuangtai(map);
				}
				if (!jiatiao.isEmpty()) {
					for (Qingjia qingjia : jiatiao) {

						// BanJi banJi =
						// banJiService.selectByPrimaryKey(xueShengService.selectByPrimaryKey(Integer.parseInt(code)).getBanjiid());
						// qingjia.setTongzhineirong(banJi.getBanjimingcheng());

						if (qingjia.getPizhunren() != null) {
							qingjia.setPizhunren(yonghuService
									.selectYongHuByID(Integer.parseInt(qingjia.getPizhunren())).getYonghuxingming());
						}
					}
				}
				mpp.put("jiatiao", jiatiao);
				mpp.put("pages", pages);
				mpp.put("page", 1);
				return JSON.toJSON(mpp);
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					int page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page <= pages) {
						map.put("id", Integer.parseInt(code));
						map.put("start", (page - 1) * 10);
						map.put("stop", 10);
						if ("0".equals(zhuangtai)) {
							jiatiao = qingjiaService.getAllByXueShengID(map);
						} else {
							map.put("zhuangtai", Integer.parseInt(zhuangtai));
							jiatiao = qingjiaService.getAllByXueShengIDAndZhuangtai(map);
						}
						if (!jiatiao.isEmpty()) {
							for (Qingjia qingjia : jiatiao) {
								// BanJi banJi =
								// banJiService.selectByPrimaryKey(xueShengService.selectByPrimaryKey(Integer.parseInt(code)).getBanjiid());
								// qingjia.setTongzhineirong(banJi.getBanjimingcheng());
								if (qingjia.getPizhunren() != null) {
									qingjia.setPizhunren(
											yonghuService.selectYongHuByID(Integer.parseInt(qingjia.getPizhunren()))
													.getYonghuxingming());
								}

							}
						}
						mpp.put("jiatiao", jiatiao);
						mpp.put("pages", pages);
						mpp.put("page", page);
						return JSON.toJSON(mpp);
					} else {
						mpp.put("jiatiao", "");
						mpp.put("pages", "");
						mpp.put("page", "");
						return JSON.toJSON(mpp);
					}
				} else {
					mpp.put("jiatiao", "");
					mpp.put("pages", "");
					mpp.put("page", "");
					return JSON.toJSON(mpp);
				}
			}
		} else {
			return null;
		}
	}

	// 通过status传进来是学生还是老师等返回相应的menu功能模块
	@RequestMapping(value = "app_getmenu")
	@ResponseBody
	public JSONArray getmenu(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String status = request.getParameter("status");
		List<Menu> user = new ArrayList<>();
		if (Tools.notEmpty(status)) {
			user = menuService.getAllMenuByStatus(status);
			if (status.equals("xuesheng")) {
				PageData pd = this.getPageData();
				String dataParam = pd.getString("dataInfo");
				JSONObject jsonObject = JSONObject.fromObject(dataParam);
				Map<String, Object> paramMap = (Map<String, Object>) JSONObject.toBean(jsonObject, Map.class);
				String code = request.getParameter("CODE");
				String data[] = code.split(",zytech,");
				String banjiid = data[0];
				XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(paramMap.get("id").toString()));
				// String xueXiaoID = data[1].split("_")[0];
				String xueXiaoID = banJiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
				if (xueXiaoID == null || "".equals(xueXiaoID)) {
					xueXiaoID = paramMap.get("xueXiaoID").toString();
				}
				XueQi xueQi = null;
				XueQiDeYu xueQiDeYu = null;
					List<XueQi> xueqis = xueqiService.findByXueXiaoID(xueXiaoID);
					if (xueqis != null && xueqis.size() > 0) {
						for (XueQi xueQi2 : xueqis) {
							XueQiDeYu deyu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi2.getXueqiid(),
									data[0] + ",%", "%," + data[0] + ",%");
							if (null != deyu) {
								if (!deyu.getZhuangtai().toString().equals("2")) {
									xueQiDeYu = deyu;
									xueQi = xueQi2;
								}
							}
						}
					}
					if (Tools.notEmpty(xueQiDeYu)) {
						json.put("zhuangtai", xueQiDeYu.getZhuangtai());
					} else {
						json.put("zhuangtai", "2");
					}
				List<String> deYuActionList = menuService.findAllDeYuAction(status);

				json.put("deYuActionList", deYuActionList);

			}
		}
		json.put("user", user);
		// System.out.println(JSONArray.fromObject(user));
		return JSONArray.fromObject(json);

	}

	@RequestMapping(value = "app_getstatus")
	@ResponseBody
	public JSONArray getStatus(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String code = request.getParameter("CODE");
		String status = request.getParameter("status");
		String data[] = code.split(",zytech,");
		String banjiid = data[0];
		String xueXiaoID = data[1].split("_")[0];

		List<Menu> user = menuService.getDeYuMenuByStatus(status);

		Map<String, String> map = new HashMap<String, String>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		map.put("riqi", format.format(date));
		map.put("xueXiaoID", xueXiaoID);

		XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
		if (xueQi != null) {
			XueQiDeYu xueQiDeYu = deYuService.selectXueQiDeYuByXueQiIDAndBanJiID(xueQi.getXueqiid(), data[0] + ",%",
					"%," + data[0] + ",%");
			json.put("zhuangtai", xueQiDeYu.getZhuangtai());
		}
		json.put("user", user);
		return JSONArray.fromObject(json);

	}

	// 通过学生id获得班级名称
	@RequestMapping(value = "app_banJiXinXi")
	@ResponseBody
	public Object app_newsList(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String xueShengId = request.getParameter("xueshengid");
		String banJiId = request.getParameter("banJiID");
		String status = request.getParameter("status");
		returnMap.put("status", "-1");
		String string = xueShengId + "," + status;
		String str = Util.SHA1Encode(string);
		if (xueShengId != null || !("".equals(xueShengId))) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("xueShengId", xueShengId);
			paramMap.put("banJiId", banJiId);
			BanJi banJi = null;
			try {
				banJi = xueShengService.getBanJiByParam(paramMap);
				returnMap.put("status", "1");
				returnMap.put("banJi", banJi);
			} catch (Exception e) {
				returnMap.put("status", "0");
			}
		}
		return JSONObject.fromObject(returnMap);
	}

	// 通过请假id获取盖条信息全部信息
	@RequestMapping(value = "app_getxiangxi")
	@ResponseBody
	public Qingjia getxiangxi(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String id = request.getParameter("id");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = id + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			Qingjia qingjia = qingjiaService.getById(Integer.parseInt(code));
			BanJi banJi = banJiService
					.selectByPrimaryKey(xueShengService.selectByPrimaryKey(qingjia.getXueshengid()).getBanjiid());
			qingjia.setBanjimingcheng(banJi.getBanjimingcheng());
			if (qingjia.getPizhunren() != null) {
				qingjia.setPizhunren(
						yonghuService.selectYongHuByID(Integer.parseInt(qingjia.getPizhunren())).getYonghuxingming());
			}
			if (qingjia.getBingjiazhengming() != null && !qingjia.getBingjiazhengming().equals("")) {
				List<String> tuPian = new ArrayList<>();
				String tuPianS[] = qingjia.getBingjiazhengming().split(",");
				for (int i = 0; i < tuPianS.length; i++) {
					// String
					// path=request.getSession().getServletContext().getRealPath("/")+"upload"+File.separator;
					// String imgstr[]=tuPianS[i].split(".");
					// String
					// basechuan=UpdataImgerUtil.GetImageStr(path,imgstr[0],imgstr[1]);
					tuPian.add(tuPianS[i]);
				}
				qingjia.setTuPian(tuPian);
			}
			return qingjia;
		} else {
			return null;
		}
	}

	// 通过请假id对数据库表中zhuangtai内容进行改变
	@RequestMapping(value = "app_xiaojia")
	@ResponseBody
	public String xiaojia(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String id = request.getParameter("id");
		String string = id + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			String retinfo = "";
			Qingjia qingjia = qingjiaService.getById(Integer.parseInt(code));
			if (qingjia.getZhuangtai() == 2) {
				int i = qingjiaService.updateStatusByQingjiaId(Integer.parseInt(code));
				if (i > 0) {
					retinfo = "success";
				} else {
					retinfo = "fail";
				}
			} else {
				retinfo = "dengchu";
			}
			return retinfo;
		} else {
			return null;
		}
	}

	// 修改假条功能
	@RequestMapping(value = "app_xiugaiqingjia")
	@ResponseBody
	public String xiugaiqingjia(String qingjiaid, String qingjialeibie, String qingjiashiyou, String kaishishijian,
			String jieshushijian, HttpServletRequest request) throws IOException {
		String retInfo = "";
		String imgSrc[] = request.getParameter("bingjiazhengming").split(",");
		List<String> imgList = Arrays.asList(imgSrc);
		String token = request.getParameter("token");
		String deleteImg = request.getParameter("deleteImg");
		String code = request.getParameter("mykey");
		String data[] = code.split(",zytech,");
		String string = data[0] + "," + data[1];
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			Map<String, String> map = new HashMap<String, String>();
			Date date = new Date();
			Date date1 = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String datetime = format.format(date1);
			int res = datetime.compareTo(kaishishijian);
			System.out.println(datetime + ">>" + kaishishijian);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			if (res > 0) {
				return "timeout";
			}
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("xueshengid", data[0]);
			map2.put("qingjiaid", qingjiaid);
			// 防止用户通过url恶意传递参数修改数据库，和数据库进行验证
			int zhuangTai = qingjiaService.selectZhuangTaiByXueShengIDAndQingJiaId(map2);
			Qingjia qingjia = qingjiaService.getById(Integer.parseInt(qingjiaid));
			if (zhuangTai == 1) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				map.put("qingjiaid", qingjiaid);
				map.put("leibie", qingjialeibie);
				map.put("shiyou", qingjiashiyou);
				map.put("kaishishijian", kaishishijian);
				map.put("jieshushijian", jieshushijian);
				map.put("shenqingshijian", df.format(date));
				List<MultipartFile> files = multipartRequest.getFiles("files");
				String[] imgs = (qingjia.getBingjiazhengming() == null || "".equals(qingjia.getBingjiazhengming()))
						? null : qingjia.getBingjiazhengming().split(",");
				if (Tools.notEmpty(imgs) && files.isEmpty()) {
					StringBuffer sBuffer = new StringBuffer();
					String[] deleteImgs = imgSrc;
					if (Tools.notEmpty(deleteImg)) {
						deleteImgs = deleteImg.split(",");
					}
					List<String> deleteImgList = Arrays.asList(deleteImgs);
					for (int i = 0; i < imgs.length; i++) {
						if (!deleteImgList.contains(imgs[i])) {
							sBuffer.append(imgs[i] + ",");
						}
					}
					map.put("bingjiazhengming", sBuffer.toString());
					int i = qingjiaService.xiuGaiById(map);
					if (i != 0) {
						retInfo = "success";
						return retInfo;
					} else {
						retInfo = "fail";
						return retInfo;
					}
				} else {
					MultipartFile newFile;
					StringBuffer sBuffer = new StringBuffer();
					if (Tools.notEmpty(imgs)) {
						String[] deleteImgs = deleteImg.split(",");
						List<String> deleteImgList = Arrays.asList(deleteImgs);
						for (int i = 0; i < imgs.length; i++) {
							if (!deleteImgList.contains(imgs[i])) {
								sBuffer.append(imgs[i] + ",");
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
							String path = request.getSession().getServletContext().getRealPath("/") + "upload"
									+ File.separator;
							File localFile = new File(path);
							if (!localFile.exists()) {
								localFile.mkdirs();
							}
							localFile = new File(path + filename + "." + Type);
							newFile.transferTo(localFile);
							sBuffer.append(filename + "." + Type + ",");
						}
					}
					map.put("bingjiazhengming", sBuffer.toString());
					int i = qingjiaService.xiuGaiById(map);
					if (i != 0) {
						retInfo = "success";
						return retInfo;
					} else {
						retInfo = "fail";
						return retInfo;
					}
				}
			} else {
				retInfo = "nothingness";
				return retInfo;
			}
		} else {
			return null;
		}
	}

	// 再次提交假条功能
	@RequestMapping(value = "app_zaicitijiao")
	@ResponseBody
	public String zaicitijiao(String qingjiaid, String qingjialeibie, String qingjiashiyou, String kaishishijian,
			String jieshushijian, HttpServletRequest request) throws IOException {
		String retInfo = "";
		String imgSrc[] = request.getParameter("bingjiazhengming").split(",");
		List<String> imgList = Arrays.asList(imgSrc);
		String token = request.getParameter("token");
		String code = request.getParameter("mykey");
		String data[] = code.split(",zytech,");
		String string = data[0] + "," + data[1];
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			Map<String, String> map = new HashMap<String, String>();
			Date date = new Date();
			Date date1 = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String datetime = format.format(date1);
			int res = datetime.compareTo(kaishishijian);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			if (res > 0) {
				return "timeout";
			}
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("xueshengid", data[0]);
			map2.put("qingjiaid", qingjiaid);
			// 防止用户通过url恶意传递参数修改数据库，和数据库进行验证
			int zhuangTai = qingjiaService.selectZhuangTaiByXueShengIDAndQingJiaId(map2);
			Qingjia qingjia = qingjiaService.getById(Integer.parseInt(qingjiaid));
			int tiJiaoCiShu = qingjiaService.selectTiJiaoCiShuByXueShengIDAndQingJiaId(map2);
			if (zhuangTai == 3 && tiJiaoCiShu < 3) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				map.put("qingjiaid", qingjiaid);
				map.put("leibie", qingjialeibie);
				map.put("shiyou", qingjiashiyou);
				map.put("kaishishijian", kaishishijian);
				map.put("jieshushijian", jieshushijian);
				map.put("shenqingshijian", df.format(date));

				List<MultipartFile> files = multipartRequest.getFiles("files");
				if (files.isEmpty()) {
					if (qingjia.getQingjialeibie().toString().equals("2")) {
						String[] imgs = qingjia.getBingjiazhengming().split(",");
						StringBuffer sBuffer = new StringBuffer();
						for (int i = 0; i < imgs.length; i++) {
							if (imgList.contains(imgs[i])) {
								sBuffer.append(imgs[i] + ",");
							}
						}
						map.put("bingjiazhengming", sBuffer.toString());
					}
					int i = qingjiaService.updateById(map);
					if (i != 0) {
						retInfo = "success";
						return retInfo;
					} else {
						retInfo = "fail";
						return retInfo;
					}
				} else {
					MultipartFile newFile;
					StringBuffer sBuffer = new StringBuffer();
					if (qingjia.getQingjialeibie().toString().equals("2")) {
						String[] imgs = qingjia.getBingjiazhengming().split(",");
						for (int i = 0; i < imgs.length; i++) {
							if (imgList.contains(imgs[i])) {
								sBuffer.append(imgs[i] + ",");
							}
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
						String path = request.getSession().getServletContext().getRealPath("/") + "upload"
								+ File.separator;
						File localFile = new File(path);
						if (!localFile.exists()) {
							localFile.mkdirs();
						}
						localFile = new File(path + filename + "." + Type);
						newFile.transferTo(localFile);
						sBuffer.append(filename + "." + Type + ",");
					}
					map.put("bingjiazhengming", sBuffer.toString());
					int i = qingjiaService.updateById(map);
					if (i != 0) {
						retInfo = "success";
						return retInfo;
					} else {
						retInfo = "fail";
						return retInfo;
					}
				}
			} else {
				retInfo = "nothingness";
				return retInfo;
			}
		} else {
			return null;
		}
	}

	// 申请假条功能
	@RequestMapping(value = "app_shenqingjiatiao")
	@ResponseBody
	public String createjiatiao(String xueshengid, String qingjialeibie, String qingjiashiyou, String kaishishijian,
			String jieshushijian, HttpServletRequest request) throws IOException {
		String retInfo = "";
		Map<String, String> map = new HashMap<String, String>();
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			Date date = new Date();
			Date date1 = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String datetime = format.format(date1);
			int res = datetime.compareTo(kaishishijian);	
//			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// List<MultipartFile> files =
			// multipartRequest.getFiles("zhengming");
			if (res > 0) {
				return "timeout";
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("xueshengID", xueshengid);
			map.put("leibie", qingjialeibie);
			map.put("shiyou", qingjiashiyou);
			map.put("kaishishijian", kaishishijian);
			map.put("jieshushijian", jieshushijian);
			map.put("shenqingshijian", df.format(date));
//			List<MultipartFile> files = multipartRequest.getFiles("files");
//			if (files.isEmpty()) {
				int i = qingjiaService.insertByXueShengID(map);
				if (i != 0) {
					retInfo = "success";
					return retInfo;
				} else {
					retInfo = "fail";
					return retInfo;
				}
//			} else {
//				MultipartFile newFile;
//				StringBuffer sBuffer = new StringBuffer();
//				for (int i = 0; i < files.size(); i++) {
//					newFile = files.get(i);
//					if (newFile.isEmpty()) {
//						continue;
//					}
//					String s = newFile.getOriginalFilename();
//					String Type = s.substring(s.lastIndexOf(".") + 1);
//					String filename = UUID.randomUUID().toString().replaceAll("-", "");
//					String path = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator;
//					File localFile = new File(path);
//					if (!localFile.exists()) {
//						localFile.mkdirs();
//					}
//					localFile = new File(path + filename + "." + Type);
//					newFile.transferTo(localFile);
//					sBuffer.append(filename + "." + Type + ",");
//				}
//				map.put("bingjiazhengming", sBuffer.toString());
//				int i = qingjiaService.insertByXueShengIDWithFile(map);
//				if (i != 0) {
//					retInfo = "success";
//					return retInfo;
//				} else {
//					retInfo = "fail";
//					return retInfo;
//				}
//			}
		} else {
			return null;
		}
	}
}
