package com.web.controller.app.stu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSON;
import com.web.model.FuDaoYuan;
import com.web.model.ShuJi;
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
public class AppYiJianXiangController {
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

	// 我的意见显示
	@RequestMapping(value = "app_WoDeYiJian")
	@ResponseBody
	public Object app_WoDeYiJian(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = code + "," + status;
		String str = Util.SHA1Encode(strings);
		if(str.equals(token)){
			Map<String, Object> mpp=new HashMap<>();
			Map<String, Integer> map=new HashMap<>();
			int count = yijianxiangService.getCountByxueShengIDandtiJiaoZhuangTai(Integer.parseInt(code),"1");
			int pageSize = 10;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == ""){
				map.put("xueshengid", Integer.parseInt(code));
				map.put("start", 0);
				map.put("stop", 5);
				map.put("tijiaozhuangtai", 1);
				List<YiJianXiang> yiJianXiangs = yijianxiangService.getAllByxueShengIDandtiJiaoZhuangTaiandPage(map);
				for (YiJianXiang yiJianXiang : yiJianXiangs) {
					if (!(yiJianXiang.getTumingcheng() == null || yiJianXiang.getTumingcheng() == "")) {
						List<String> tu = new ArrayList<>();
						String tString[] = yiJianXiang.getTumingcheng().split(",");
						for (int i = 0; i < tString.length; i++) {
							tu.add(tString[i]);
						}
						yiJianXiang.setTupian(tu);
					}
					// 标记我有没有点赞这条意见
					String dianzanren = yiJianXiang.getDianzanrenid();
					int sign = 0;
					if (dianzanren != null && dianzanren != "") {
						String dianzanrens[] = dianzanren.split(",");
						for (String string : dianzanrens) {
							if (string.equals(code)) {
								sign = 1;
							}
						}
					}
					yiJianXiang.setIsdianzan(sign);
					// 插入意见回复实体类列表
					List<YiJianHuiFu> yiJianHuiFus = yijianhuifuService.getAllByyiJianID(yiJianXiang.getYijianid());
					if (!yiJianHuiFus.isEmpty()) {
						for (YiJianHuiFu yiJianHuiFu : yiJianHuiFus) {
							if (yiJianHuiFu.getJiaoshiid() != null) {
								String jiaoshixingming = yonghuService.selectYongHuByID(yiJianHuiFu.getJiaoshiid())
										.getYonghuxingming();
								yiJianHuiFu.setJiaoshixingming(jiaoshixingming);
							} else {
								String xueshengname = xueshengService.getUserById(yiJianHuiFu.getXueshengid()).getXingming();
								int banji = xueshengService.getUserById(yiJianHuiFu.getXueshengid()).getBanjiid();
								String banjiming = banjiService.selectByPrimaryKey(banji).getBanjimingcheng();
								yiJianHuiFu.setXueshengxingming(xueshengname);
								yiJianHuiFu.setBanjimingcheng(banjiming);
							}
							if (yiJianHuiFu.getBeihuifujiaoshiid() != null) {
								String beihuifujiaoshixingming = yonghuService
										.selectYongHuByID(yiJianHuiFu.getBeihuifujiaoshiid()).getYonghuxingming();
								yiJianHuiFu.setBeihuifujiaoshixingming(beihuifujiaoshixingming);
							} else if (yiJianHuiFu.getBeihuifuxueshengid() != null) {
								String beihuifuxueshengxingming = xueshengService
										.getUserById(yiJianHuiFu.getBeihuifuxueshengid()).getXingming();
								int beihuifubanjiid = xueshengService.getUserById(yiJianHuiFu.getBeihuifuxueshengid())
										.getBanjiid();
								String beihuifubanjiming = banjiService.selectByPrimaryKey(beihuifubanjiid).getBanjimingcheng();
								yiJianHuiFu.setBeihuifuxueshengxingming(beihuifuxueshengxingming);
								yiJianHuiFu.setBeihuifubanjimingcheng(beihuifubanjiming);
							}
						}
						yiJianXiang.setYijianhuifus(yiJianHuiFus);
					}

				}
				mpp.put("yijianxiangs", yiJianXiangs);
				mpp.put("pages", pages);
				mpp.put("page", 1);
				return JSON.toJSON(mpp);
			}
			else {
				if (Util.isNumeric(request.getParameter("page"))) {
					int page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page <= pages) {
						map.put("xueshengid", Integer.parseInt(code));
						map.put("tijiaozhuangtai", 1);
						map.put("start", (page - 1) * 5);
						map.put("stop", 5);
						List<YiJianXiang> yiJianXiangs = yijianxiangService.getAllByxueShengIDandtiJiaoZhuangTaiandPage(map);
						for (YiJianXiang yiJianXiang : yiJianXiangs) {
							if (!(yiJianXiang.getTumingcheng() == null || yiJianXiang.getTumingcheng() == "")) {
								List<String> tu = new ArrayList<>();
								String tString[] = yiJianXiang.getTumingcheng().split(",");
								for (int i = 0; i < tString.length; i++) {
									tu.add(tString[i]);
								}
								yiJianXiang.setTupian(tu);
							}
							// 标记我有没有点赞这条意见
							String dianzanren = yiJianXiang.getDianzanrenid();
							int sign = 0;
							if (dianzanren != null && dianzanren != "") {
								String dianzanrens[] = dianzanren.split(",");
								for (String string : dianzanrens) {
									if (string.equals(code)) {
										sign = 1;
									}
								}
							}
							yiJianXiang.setIsdianzan(sign);
							// 插入意见回复实体类列表
							List<YiJianHuiFu> yiJianHuiFus = yijianhuifuService.getAllByyiJianID(yiJianXiang.getYijianid());
							if (!yiJianHuiFus.isEmpty()) {
								for (YiJianHuiFu yiJianHuiFu : yiJianHuiFus) {
									if (yiJianHuiFu.getJiaoshiid() != null) {
										String jiaoshixingming = yonghuService.selectYongHuByID(yiJianHuiFu.getJiaoshiid())
												.getYonghuxingming();
										yiJianHuiFu.setJiaoshixingming(jiaoshixingming);
									} else {
										String xueshengname = xueshengService.getUserById(yiJianHuiFu.getXueshengid()).getXingming();
										int banji = xueshengService.getUserById(yiJianHuiFu.getXueshengid()).getBanjiid();
										String banjiming = banjiService.selectByPrimaryKey(banji).getBanjimingcheng();
										yiJianHuiFu.setXueshengxingming(xueshengname);
										yiJianHuiFu.setBanjimingcheng(banjiming);
									}
									if (yiJianHuiFu.getBeihuifujiaoshiid() != null) {
										String beihuifujiaoshixingming = yonghuService
												.selectYongHuByID(yiJianHuiFu.getBeihuifujiaoshiid()).getYonghuxingming();
										yiJianHuiFu.setBeihuifujiaoshixingming(beihuifujiaoshixingming);
									} else if (yiJianHuiFu.getBeihuifuxueshengid() != null) {
										String beihuifuxueshengxingming = xueshengService
												.getUserById(yiJianHuiFu.getBeihuifuxueshengid()).getXingming();
										int beihuifubanjiid = xueshengService.getUserById(yiJianHuiFu.getBeihuifuxueshengid())
												.getBanjiid();
										String beihuifubanjiming = banjiService.selectByPrimaryKey(beihuifubanjiid).getBanjimingcheng();
										yiJianHuiFu.setBeihuifuxueshengxingming(beihuifuxueshengxingming);
										yiJianHuiFu.setBeihuifubanjimingcheng(beihuifubanjiming);
									}
								}
								yiJianXiang.setYijianhuifus(yiJianHuiFus);
							}

						}
						mpp.put("yijianxiangs", yiJianXiangs);
						mpp.put("pages", pages);
						mpp.put("page", page);
						return JSON.toJSON(mpp);
					}
					else {
						mpp.put("jiatiao", "");
						mpp.put("pages", "");
						mpp.put("page", "");
						return JSON.toJSON(mpp);
					}
				}
				else {
					mpp.put("yijianxiangs", "");
					mpp.put("page", "");
					mpp.put("pages", "");
					return JSON.toJSON(mpp);
				}
			}
		}
		else {
			return null;
		}
	}

	// 学生对意见进行点赞
	@RequestMapping(value = "app_StudentDianZan")
	@ResponseBody
	public String app_StudentDianZan(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String data[] = code.split(",zytech,");
		String strings = data[0] + "," + status;
		String str = Util.SHA1Encode(strings);
		if(str.equals(token)){
			String result = "";
			if (data[0] != null && data[0] != "") {
				YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(data[1]));
				int dianzanliang = yiJianXiang.getDianzanliang();
				String dianzanren = yiJianXiang.getDianzanrenid();
				if (dianzanliang == 0) {
					dianzanren = data[0] + ",";
					dianzanliang++;
					result = "add," + dianzanliang;
				} else {
					String dianzanrens[] = dianzanren.split(",");
					String newdianzanren = "";
					int i = 0;
					for (String string : dianzanrens) {
						if (string.equals(data[0])) {
							i++;
						} else {
							newdianzanren += string + ",";
						}
					}
					if (i == 0) {
						dianzanren += data[0] + ",";
						dianzanliang++;
						result = "add," + dianzanliang;
					} else {
						dianzanren = newdianzanren;
						dianzanliang--;
						result = "del," + dianzanliang;
					}
				}
				YiJianXiang yiJianXiang2 = new YiJianXiang();
				yiJianXiang2.setYijianid(Integer.parseInt(data[1]));
				yiJianXiang2.setDianzanrenid(dianzanren);
				yiJianXiang2.setDianzanliang(dianzanliang);
				int j = yijianxiangService.updatedianZanByByPrimaryKey(yiJianXiang2);
				if (j == 0) {
					result = "fail,";
				}
			} else {
				return "weinull,";
			}
			return result;
		}
		else {
			return null;
		}
	}

	// 删除回复
	@RequestMapping(value = "app_DeleteHuiFu")
	@ResponseBody
	public String app_DeleteHuiFu(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String result = "";
		String data[] = code.split(",zytech,");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			if (data[0] != null && data[0] != "") {
				YiJianHuiFu yiJianHuiFu = yijianhuifuService.selectByPrimaryKey(Integer.parseInt(data[1]));
				if (yiJianHuiFu != null && data[0].equals(yiJianHuiFu.getXueshengid().toString())) {
					yijianhuifuService.deleteByPrimaryKey(Integer.parseInt(data[1]));
					YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(yiJianHuiFu.getYijianid());
					int pinglunliang = yiJianXiang.getPinglunliang();
					YiJianXiang yiJianXiang2 = new YiJianXiang();
					yiJianXiang2.setYijianid(yiJianHuiFu.getYijianid());
					yiJianXiang2.setPinglunliang(pinglunliang - 1);
					yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang2);
					result = "success";
				} else {
					result = "xiaoshi";
				}
			} else {
				result = "fail";
			}
			return result;
		}
		else {
			return null;
		}
	}

	// 在意见下提出评论
	@RequestMapping(value = "app_SubPingLun")
	@ResponseBody
	public String app_SubPingLun(HttpServletRequest request) {
		String studentid = request.getParameter("studentid");
		String yijianid = request.getParameter("yijianid");
		String huifuneirong = request.getParameter("pinglunneirong");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = studentid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			String result = "";
			if (studentid != null && studentid != "") {
				YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
				if (yiJianXiang == null) {
					result = "notsee";
				}
				System.out.println(huifuneirong);
				Date date = new Date();
				YiJianHuiFu yiJianHuiFu = new YiJianHuiFu();
				yiJianHuiFu.setYijianid(Integer.parseInt(yijianid));
				yiJianHuiFu.setHuifuneirong(huifuneirong);
				yiJianHuiFu.setShijian(date);
				yiJianHuiFu.setXueshengid(Integer.parseInt(studentid));
				int i = yijianhuifuService.insert(yiJianHuiFu);
				if (i != 0) {
					int pinglunliang = yiJianXiang.getPinglunliang();
					yiJianXiang.setPinglunliang(pinglunliang + 1);
					yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang);
					result = "success";
				} else {
					result = "notsuccess";
				}
			} else {
				result = "fail";
			}
			return result;
		}
		else {
			return null;
		}
	}

	// 单条意见进行操作后通过yijianid刷新该意见
	@RequestMapping(value = "app_ShuaXinDanTiaoYiJian")
	@ResponseBody
	public YiJianXiang app_ShuaXinDanTiaoYiJian(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = data[0] + "," + status;
		String str = Util.SHA1Encode(strings);
		if(str.equals(token)){
			if (data[0] != null && data[0] != "") {
				YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(data[1]));
				if (!(yiJianXiang.getTumingcheng() == null || yiJianXiang.getTumingcheng() == "")) {
					List<String> tu = new ArrayList<>();
					String tString[] = yiJianXiang.getTumingcheng().split(",");
					for (int i = 0; i < tString.length; i++) {
						tu.add(tString[i]);
					}
					yiJianXiang.setTupian(tu);
				}
				// 标记我有没有点赞这条意见
				String dianzanren = yiJianXiang.getDianzanrenid();
				int sign = 0;
				if (dianzanren != null && dianzanren != "") {
					String dianzanrens[] = dianzanren.split(",");
					for (String string : dianzanrens) {
						if (string.equals(data[0])) {
							sign = 1;
						}
					}
				}
				yiJianXiang.setIsdianzan(sign);
				// 插入意见回复实体类列表
				List<YiJianHuiFu> yiJianHuiFus = yijianhuifuService.getAllByyiJianID(yiJianXiang.getYijianid());
				if (!yiJianHuiFus.isEmpty()) {
					for (YiJianHuiFu yiJianHuiFu : yiJianHuiFus) {
						if (yiJianHuiFu.getJiaoshiid() != null) {
							String jiaoshixingming = yonghuService.selectYongHuByID(yiJianHuiFu.getJiaoshiid())
									.getYonghuxingming();
							yiJianHuiFu.setJiaoshixingming(jiaoshixingming);
						} else {
							String xueshengname = xueshengService.getUserById(yiJianHuiFu.getXueshengid()).getXingming();
							int banji = xueshengService.getUserById(yiJianHuiFu.getXueshengid()).getBanjiid();
							String banjiming = banjiService.selectByPrimaryKey(banji).getBanjimingcheng();
							yiJianHuiFu.setXueshengxingming(xueshengname);
							yiJianHuiFu.setBanjimingcheng(banjiming);
						}
						if (yiJianHuiFu.getBeihuifujiaoshiid() != null) {
							String beihuifujiaoshixingming = yonghuService
									.selectYongHuByID(yiJianHuiFu.getBeihuifujiaoshiid()).getYonghuxingming();
							yiJianHuiFu.setBeihuifujiaoshixingming(beihuifujiaoshixingming);
						} else if (yiJianHuiFu.getBeihuifuxueshengid() != null) {
							String beihuifuxueshengxingming = xueshengService
									.getUserById(yiJianHuiFu.getBeihuifuxueshengid()).getXingming();
							int beihuifubanjiid = xueshengService.getUserById(yiJianHuiFu.getBeihuifuxueshengid())
									.getBanjiid();
							String beihuifubanjiming = banjiService.selectByPrimaryKey(beihuifubanjiid).getBanjimingcheng();
							yiJianHuiFu.setBeihuifuxueshengxingming(beihuifuxueshengxingming);
							yiJianHuiFu.setBeihuifubanjimingcheng(beihuifubanjiming);
						}
					}
					yiJianXiang.setYijianhuifus(yiJianHuiFus);
				}
				return yiJianXiang;
			} else {
				return new YiJianXiang();
			}
		}
		else {
			return null;
		}
	}

	// 给教师一类的评论进行回复
	@RequestMapping(value = "app_SubHuiFuJiaoShi")
	@ResponseBody
	public String app_SubHuiFuJiaoShi(HttpServletRequest request) {
		String studentid = request.getParameter("studentid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String id = request.getParameter("id");
		String ids[] = id.split("_");
		String huifuneirong = request.getParameter("huifuneirong");
		String string = studentid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			String result = "";
			if (studentid != null && studentid != "") {
				YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(ids[0]));
				String beihuifujiaoshiid = ids[1];
				Date date = new Date();
				if (yiJianXiang == null) {
					result = "notsee";
				}
				YiJianHuiFu yiJianHuiFu = new YiJianHuiFu();
				yiJianHuiFu.setYijianid(Integer.parseInt(ids[0]));
				yiJianHuiFu.setHuifuneirong(huifuneirong);
				yiJianHuiFu.setShijian(date);
				yiJianHuiFu.setXueshengid(Integer.parseInt(studentid));
				yiJianHuiFu.setBeihuifujiaoshiid(Integer.parseInt(beihuifujiaoshiid));
				int i = yijianhuifuService.insert2(yiJianHuiFu);
				if (i != 0) {
					int pinglunliang = yiJianXiang.getPinglunliang();
					yiJianXiang.setPinglunliang(pinglunliang + 1);
					yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang);
					result = "success";
				} else {
					result = "notsuccess";
				}
			} else {
				result = "fail";
			}
			return result;
		}
		else {
			return null;
		}
	}

	// 对学生的评论进行回复
	@RequestMapping(value = "app_SubHuiFuXueSheng")
	@ResponseBody
	public String app_SubHuiFuXueSheng(HttpServletRequest request) {
		String studentid = request.getParameter("studentid");
		String id = request.getParameter("id");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String ids[] = id.split("_");
		String huifuneirong = request.getParameter("huifuneirong");
		String string = studentid+ "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			String result = "";
			if (studentid != null && studentid != "") {
				YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(ids[0]));
				String beihuifuxueshengid = ids[1];
				Date date = new Date();
				if (yiJianXiang == null) {
					result = "notsee";
				}
				YiJianHuiFu yiJianHuiFu = new YiJianHuiFu();
				yiJianHuiFu.setYijianid(Integer.parseInt(ids[0]));
				yiJianHuiFu.setHuifuneirong(huifuneirong);
				yiJianHuiFu.setShijian(date);
				yiJianHuiFu.setXueshengid(Integer.parseInt(studentid));
				yiJianHuiFu.setBeihuifuxueshengid(Integer.parseInt(beihuifuxueshengid));
				int i = yijianhuifuService.insert3(yiJianHuiFu);
				if (i != 0) {
					int pinglunliang = yiJianXiang.getPinglunliang();
					yiJianXiang.setPinglunliang(pinglunliang + 1);
					yijianxiangService.updatepingLunLiangByPrimaryKey(yiJianXiang);
					result = "success";
				} else {
					result = "notsuccess";
				}
			} else {
				result = "fail";
			}
			return result;
		}
		else {
			return null;
		}
	}

	// 保存新意见到草稿箱
	@RequestMapping(value = "app_SaveToCaoGaoXiang")
	@ResponseBody
	public String app_SaveToCaoGaoXiang(@RequestParam(value = "tupian", required = false) MultipartFile file,
			HttpServletRequest request, MultipartRequest multipartRequest) throws IOException {
		YiJianXiang yiJianXiang = new YiJianXiang();
		String mingcheng = request.getParameter("mingcheng");// 获取名称
		String neirong = request.getParameter("neirong");// 获取内容
		String banjiid = request.getParameter("banjiid");
		String xueshengid = request.getParameter("studentid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			String result = "";
			// 获取图片***********
			List<MultipartFile> files = multipartRequest.getFiles("tupian");
			String tumingcheng = "";
			if (xueshengid != null && xueshengid != "") {
				if (files.get(0).isEmpty()) {

				} else {
					MultipartFile newFile;
					StringBuffer sBuffer = new StringBuffer();
					for (int i = 0; i < files.size(); i++) {
						newFile = files.get(i);
						String s = newFile.getOriginalFilename();
						String Type = s.substring(s.lastIndexOf(".") + 1);
						String filename = UUID.randomUUID().toString().replaceAll("-", "");
						String path = request.getSession().getServletContext().getRealPath("/") +"upload/" + filename + "." + Type;
						File localFile = new File(path);
						System.out.println(localFile.getAbsolutePath());
						if (!localFile.exists()) {
							localFile.mkdirs();
						}
						newFile.transferTo(localFile);
						sBuffer.append(filename + "." + Type + ",");
					}
					tumingcheng = sBuffer.toString();
				}
				// 获取接收人***********
				String jieshourenvalue = request.getParameter("jieshouren");
				// 根据前台返回的jieshouren查到接收人id，辅导员、书记、学生处管理员
				int jieshouren = 0;
				FuDaoYuan fuDaoYuan = fudaoyuanService.getBybanJiID(banjiid+",%","%,"+banjiid+",%");
				if (jieshourenvalue.equals("1")) {// 辅导员
					jieshouren = fuDaoYuan.getFudaoyuanid();
				} else if (jieshourenvalue.equals("2")) {// 书记
					jieshouren = fuDaoYuan.getShujiid();
				} else if (jieshourenvalue.equals("3")) {// 学生处管理员
					ShuJi shuJi = shujiService.selectByPrimaryKey(fuDaoYuan.getShujiid());
					jieshouren = shuJi.getXueshengchuguanliyuanid();
				}

				String biaoqian = request.getParameter("biaoqian");// 获取标签
				String niming = request.getParameter("isniming");// 获取匿名状态

				// 赋值给实体类*********
				yiJianXiang.setYijianmingcheng(mingcheng);// 意见名称
				yiJianXiang.setXueshengid(Integer.parseInt(xueshengid));// 学生id
				yiJianXiang.setJieshourenleixing(Integer.parseInt(jieshourenvalue));// 接收人类型
				yiJianXiang.setJieshourenid(jieshouren);// 接收人id
				yiJianXiang.setWenzineirong(neirong);// 文字内容
				yiJianXiang.setTumingcheng(tumingcheng);// 图片名称
				if (niming.equals("0")) {
					yiJianXiang.setNimingbiaoji("0");// 匿名标记
				} else {
					yiJianXiang.setNimingbiaoji("1");// 匿名标记
				}
				yiJianXiang.setChulibiaoji("0");// 处理标记为0
				yiJianXiang.setGongbuzhuangtai("0");// 加精标记为0
				yiJianXiang.setGuoqibiaoji("0");// 过期标记为0
				yiJianXiang.setDianzanliang(0);// 点赞量为0
				yiJianXiang.setLiulanliang(0);// 浏览量为0
				yiJianXiang.setPinglunliang(0);// 评论量为0
				yiJianXiang.setBiaoqian(biaoqian);// 标签，意见类型
				// 保存草稿箱
				yiJianXiang.setTijiaozhuangtai("0");// 提交状态为0
				Date date = new Date();
				yiJianXiang.setBaocunshijian(date);// 保存时间
				yiJianXiang.setTijiaoshijian(date);
				int i = 0;
				if (tumingcheng.equals("")) {
					i = yijianxiangService.insert2(yiJianXiang);
				} else {
					i = yijianxiangService.insert(yiJianXiang);
				}
				if (i != 0) {
					result = "success";
				} else {
					result = "fail";
				}

			} else {
				result = "dengchu";
			}
			return result;
		}
		else {
			return null;
		}
	}

	// 保存新意并提交
	@RequestMapping(value = "app_SaveToTiJiao")
	@ResponseBody
	public String app_SaveToTiJiao(@RequestParam(value = "tupian", required = false) MultipartFile file,
			HttpServletRequest request) throws IOException {
		YiJianXiang yiJianXiang = new YiJianXiang();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String mingcheng = request.getParameter("mingcheng");// 获取名称
		String neirong = request.getParameter("neirong");// 获取内容
		String banjiid = request.getParameter("banjiid");
		String xueshengid = request.getParameter("studentid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			String result = "";
			// 获取图片***********
			List<MultipartFile> files = multipartRequest.getFiles("tupian");
			String tumingcheng = "";
			if (xueshengid != null && xueshengid != "") {
				if (files.get(0).isEmpty()) {

				} else {
					MultipartFile newFile;
					StringBuffer sBuffer = new StringBuffer();
					for (int i = 0; i < files.size(); i++) {
						newFile = files.get(i);
						String s = newFile.getOriginalFilename();
						String Type = s.substring(s.lastIndexOf(".") + 1);
						String filename = UUID.randomUUID().toString().replaceAll("-", "");
						String path = request.getSession().getServletContext().getRealPath("/") +"upload/" + filename + "." + Type;
						File localFile = new File(path);
						System.out.println(localFile.getAbsolutePath());
						if (!localFile.exists()) {
							localFile.mkdirs();
						}
						newFile.transferTo(localFile);
						sBuffer.append(filename + "." + Type + ",");
					}
					tumingcheng = sBuffer.toString();
				}
				// 获取接收人***********
				String jieshourenvalue = request.getParameter("jieshouren");
				// 根据前台返回的jieshouren查到接收人id，辅导员、书记、学生处管理员
				int jieshouren = 0;
				FuDaoYuan fuDaoYuan = fudaoyuanService.getBybanJiID(banjiid+",%","%,"+banjiid+",%");
				if (jieshourenvalue.equals("1")) {// 辅导员
					jieshouren = fuDaoYuan.getFudaoyuanid();
				} else if (jieshourenvalue.equals("2")) {// 书记
					jieshouren = fuDaoYuan.getShujiid();
				} else if (jieshourenvalue.equals("3")) {// 学生处管理员
					ShuJi shuJi = shujiService.selectByPrimaryKey(fuDaoYuan.getShujiid());
					jieshouren = shuJi.getXueshengchuguanliyuanid();
				}

				String biaoqian = request.getParameter("biaoqian");// 获取标签
				String niming = request.getParameter("isniming");// 获取匿名状态

				// 赋值给实体类*********
				yiJianXiang.setYijianmingcheng(mingcheng);// 意见名称
				yiJianXiang.setXueshengid(Integer.parseInt(xueshengid));// 学生id
				yiJianXiang.setJieshourenleixing(Integer.parseInt(jieshourenvalue));// 接收人类型
				yiJianXiang.setJieshourenid(jieshouren);// 接收人id
				yiJianXiang.setWenzineirong(neirong);// 文字内容
				yiJianXiang.setTumingcheng(tumingcheng);// 图片名称
				if (niming.equals("0")) {
					yiJianXiang.setNimingbiaoji("0");// 匿名标记
				} else {
					yiJianXiang.setNimingbiaoji("1");// 匿名标记
				}
				yiJianXiang.setChulibiaoji("0");// 处理标记为0
				yiJianXiang.setGongbuzhuangtai("0");// 加精标记为0
				yiJianXiang.setGuoqibiaoji("0");// 过期标记为0
				yiJianXiang.setDianzanliang(0);// 点赞量为0
				yiJianXiang.setLiulanliang(0);// 浏览量为0
				yiJianXiang.setPinglunliang(0);// 评论量为0
				yiJianXiang.setBiaoqian(biaoqian);// 标签，意见类型
				// 提交给接收人
				yiJianXiang.setTijiaozhuangtai("1");// 提交状态为1
				Date date = new Date();
				yiJianXiang.setBaocunshijian(date);// 保存时间
				yiJianXiang.setTijiaoshijian(date);
				int i = 0;
				if (tumingcheng.equals("")) {
					i = yijianxiangService.insert2(yiJianXiang);
				} else {
					i = yijianxiangService.insert(yiJianXiang);
				}
				if (i != 0) {
					result = "success";
				} else {
					result = "fail";
				}

			} else {
				result = "dengchu";
			}
			return result;
		}
		else {
			return null;
		}
	}

	// 显示草稿箱内的意见(保存但未提交的意见)
	@RequestMapping(value = "app_CaoGaoXiangXianShi")
	@ResponseBody
	public List<YiJianXiang> app_CaoGaoXiangXianShi(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = code + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			List<YiJianXiang> yiJianXiangs = new ArrayList<>();
			if (code != null && code != "") {
				yiJianXiangs = yijianxiangService.getAllByxueShengIDandtiJiaoZhuangTai(Integer.parseInt(code), "0");
				return yiJianXiangs;
			} else {
				return yiJianXiangs;
			}
		}
		else {
			return null;
		}
	}

	// 删除草稿箱的意见
	@RequestMapping(value = "app_DeleteCaoGao")
	@ResponseBody
	public String app_DeleteCaoGao(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String result = "";
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			if (data[0] != null && data[0] != "") {
				YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(data[1]));
				if (yiJianXiang != null && yiJianXiang.getXueshengid().toString().equals(data[0])) {
					yijianxiangService.deleteByPrimaryKey(Integer.parseInt(data[1]));
					result = "success";
				} else {
					result = "fail";
				}
			} else {
				result = "dengchu";
			}
			return result;
		}
		else {
			return null;
		}
	}

	// 根据yijianid找到这条意见，并返回该意见信息
	@RequestMapping(value = "app_ONECaoGaoBianJi")
	@ResponseBody
	public YiJianXiang app_ONECaoGaoBianJi(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			if (data[0] != null && data[0] != "") {
				YiJianXiang yiJianXiang = yijianxiangService.selectByPrimaryKey(Integer.parseInt(data[1]));
				if (yiJianXiang != null) {
					return yiJianXiang;
				} else {
					return new YiJianXiang();
				}
			}
			return new YiJianXiang();
		}
		else {
			return null;
		}
	}

	// 修改在草稿箱中未提交的意见
	@RequestMapping(value = "app_XiuGaiSaveCaoGao")
	@ResponseBody
	public String app_XiuGaiSaveCaoGao(@RequestParam(value = "tupian", required = false) MultipartFile file,
			HttpServletRequest request, MultipartRequest multipartRequest) throws IOException {
		YiJianXiang yiJianXiang = new YiJianXiang();
		String result = "";
		String yijianid = request.getParameter("yijianid");// 获取意见id
		String studentid = request.getParameter("studentid");
		String banjiid = request.getParameter("banjiid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = studentid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			YiJianXiang yiJianXiang2 = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if (yiJianXiang2 != null && yiJianXiang2.getXueshengid().equals(Integer.parseInt(studentid))) {
				String mingcheng = request.getParameter("mingcheng");// 获取名称
				String neirong = request.getParameter("neirong");// 获取内容
				// 获取图片***********
				List<MultipartFile> files = multipartRequest.getFiles("tupian");
				String tumingcheng = "";
				if (files.get(0).isEmpty()) {

				} else {
					MultipartFile newFile;
					StringBuffer sBuffer = new StringBuffer();
					for (int i = 0; i < files.size(); i++) {
						newFile = files.get(i);
						String s = newFile.getOriginalFilename();
						String Type = s.substring(s.lastIndexOf(".") + 1);
						String filename = UUID.randomUUID().toString().replaceAll("-", "");
						String path = request.getSession().getServletContext().getRealPath("/") +"upload/" + filename + "." + Type;
						File localFile = new File(path);
						System.out.println(localFile.getAbsolutePath());
						if (!localFile.exists()) {
							localFile.mkdirs();
						}
						newFile.transferTo(localFile);
						sBuffer.append(filename + "." + Type + ",");
					}
					tumingcheng = sBuffer.toString();
				}
				// 获取接收人***********
				String jieshourenvalue = request.getParameter("jieshouren");
				// 根据前台返回的jieshouren查到接收人id，辅导员、书记、学生处管理员
				int jieshouren = 0;
				FuDaoYuan fuDaoYuan = fudaoyuanService.getBybanJiID(banjiid+",%","%,"+banjiid+",%");
				if (jieshourenvalue.equals("1")) {// 辅导员
					jieshouren = fuDaoYuan.getFudaoyuanid();
				} else if (jieshourenvalue.equals("2")) {// 书记
					jieshouren = fuDaoYuan.getShujiid();
				} else if (jieshourenvalue.equals("3")) {// 学生处管理员
					ShuJi shuJi = shujiService.selectByPrimaryKey(fuDaoYuan.getShujiid());
					jieshouren = shuJi.getXueshengchuguanliyuanid();
				}

				String biaoqian = request.getParameter("biaoqian");// 获取标签
				String niming = request.getParameter("isniming");// 获取匿名状态
				// 赋值给实体类*********
				yiJianXiang.setYijianid(Integer.parseInt(yijianid));// 意见id
				yiJianXiang.setYijianmingcheng(mingcheng);// 意见名称
				yiJianXiang.setXueshengid(Integer.parseInt(studentid));// 学生id
				yiJianXiang.setJieshourenleixing(Integer.parseInt(jieshourenvalue));// 接收人类型
				yiJianXiang.setJieshourenid(jieshouren);// 接收人id
				yiJianXiang.setWenzineirong(neirong);// 文字内容
				yiJianXiang.setTumingcheng(tumingcheng);// 图片名称
				if (niming.equals("0")) {
					yiJianXiang.setNimingbiaoji("0");// 匿名标记
				} else {
					yiJianXiang.setNimingbiaoji("1");// 匿名标记
				}
				yiJianXiang.setChulibiaoji("0");// 处理标记为0
				yiJianXiang.setGongbuzhuangtai("0");// 加精标记为0
				yiJianXiang.setGuoqibiaoji("0");// 过期标记为0
				yiJianXiang.setDianzanliang(0);// 点赞量为0
				yiJianXiang.setLiulanliang(0);// 浏览量为0
				yiJianXiang.setPinglunliang(0);// 评论量为0
				yiJianXiang.setBiaoqian(biaoqian);// 标签，意见类型

				yiJianXiang.setTijiaozhuangtai("0");// 提交状态为0
				Date date = new Date();
				yiJianXiang.setBaocunshijian(date);// 保存时间
				yiJianXiang.setTijiaoshijian(date);
				int i = 0;
				if (tumingcheng.equals("")) {
					i = yijianxiangService.updateByPrimaryKey2(yiJianXiang);
				} else {
					i = yijianxiangService.updateByPrimaryKey(yiJianXiang);
				}
				if (i != 0) {
					result = "success";
				} else {
					result = "fail";
				}
			} else {
				result = "weinull";
			}
			return result;
		}
		else {
			return null;
		}
	}

	// 提交草稿箱内的意见
	@RequestMapping(value = "app_TiaoJiaoCaoGao")
	@ResponseBody
	public String app_TiaoJiaoCaoGao(@RequestParam(value = "tupian", required = false) MultipartFile file,
			HttpServletRequest request) throws IOException {
		YiJianXiang yiJianXiang = new YiJianXiang();
		String result = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String yijianid = request.getParameter("yijianid");// 获取意见id
		String studentid = request.getParameter("studentid");
		String banjiid = request.getParameter("banjiid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = studentid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			YiJianXiang yiJianXiang2 = yijianxiangService.selectByPrimaryKey(Integer.parseInt(yijianid));
			if (yiJianXiang2 != null && yiJianXiang2.getXueshengid().equals(Integer.parseInt(studentid))) {
				String mingcheng = request.getParameter("mingcheng");// 获取名称
				String neirong = request.getParameter("neirong");// 获取内容
				// 获取图片***********
				List<MultipartFile> files = multipartRequest.getFiles("tupian");
				String tumingcheng = "";
				if (files.get(0).isEmpty()) {

				} else {
					MultipartFile newFile;
					StringBuffer sBuffer = new StringBuffer();
					for (int i = 0; i < files.size(); i++) {
						newFile = files.get(i);
						String s = newFile.getOriginalFilename();
						String Type = s.substring(s.lastIndexOf(".") + 1);
						String filename = UUID.randomUUID().toString().replaceAll("-", "");
						String path = request.getSession().getServletContext().getRealPath("/") +"upload/" + filename + "." + Type;
						File localFile = new File(path);
						System.out.println(localFile.getAbsolutePath());
						if (!localFile.exists()) {
							localFile.mkdirs();
						}
						newFile.transferTo(localFile);
						sBuffer.append(filename + "." + Type + ",");
					}
					tumingcheng = sBuffer.toString();
				}
				// 获取接收人***********
				String jieshourenvalue = request.getParameter("jieshouren");
				// 根据前台返回的jieshouren查到接收人id，辅导员、书记、学生处管理员
				int jieshouren = 0;
				FuDaoYuan fuDaoYuan = fudaoyuanService.getBybanJiID(banjiid+",%","%,"+banjiid+",%");
				if (jieshourenvalue.equals("1")) {// 辅导员
					jieshouren = fuDaoYuan.getFudaoyuanid();
				} else if (jieshourenvalue.equals("2")) {// 书记
					jieshouren = fuDaoYuan.getShujiid();
				} else if (jieshourenvalue.equals("3")) {// 学生处管理员
					ShuJi shuJi = shujiService.selectByPrimaryKey(fuDaoYuan.getShujiid());
					jieshouren = shuJi.getXueshengchuguanliyuanid();
				}

				String biaoqian = request.getParameter("biaoqian");// 获取标签
				String niming = request.getParameter("isniming");// 获取匿名状态
				// 赋值给实体类*********
				yiJianXiang.setYijianid(Integer.parseInt(yijianid));// 意见id
				yiJianXiang.setYijianmingcheng(mingcheng);// 意见名称
				yiJianXiang.setXueshengid(Integer.parseInt(studentid));// 学生id
				yiJianXiang.setJieshourenleixing(Integer.parseInt(jieshourenvalue));// 接收人类型
				yiJianXiang.setJieshourenid(jieshouren);// 接收人id
				yiJianXiang.setWenzineirong(neirong);// 文字内容
				yiJianXiang.setTumingcheng(tumingcheng);// 图片名称
				if (niming.equals("0")) {
					yiJianXiang.setNimingbiaoji("0");// 匿名标记
				} else {
					yiJianXiang.setNimingbiaoji("1");// 匿名标记
				}
				yiJianXiang.setChulibiaoji("0");// 处理标记为0
				yiJianXiang.setGongbuzhuangtai("0");// 加精标记为0
				yiJianXiang.setGuoqibiaoji("0");// 过期标记为0
				yiJianXiang.setDianzanliang(0);// 点赞量为0
				yiJianXiang.setLiulanliang(0);// 浏览量为0
				yiJianXiang.setPinglunliang(0);// 评论量为0
				yiJianXiang.setBiaoqian(biaoqian);// 标签，意见类型

				yiJianXiang.setTijiaozhuangtai("1");// 提交状态为1
				Date date = new Date();
				yiJianXiang.setBaocunshijian(date);// 保存时间
				yiJianXiang.setTijiaoshijian(date);
				int i = 0;
				if (tumingcheng.equals("")) {
					i = yijianxiangService.updateByPrimaryKey2(yiJianXiang);
				} else {
					i = yijianxiangService.updateByPrimaryKey(yiJianXiang);
				}
				if (i != 0) {
					result = "success";
				} else {
					result = "fail";
				}
			} else {
				result = "weinull";
			}
			return result;
		}
		else {
			return null;
		}
	}
	
	//查询意见动态
	@RequestMapping(value="app_XianShiYiJianDongTai")
	@ResponseBody
	public Object app_XianShiYiJianDongTai(HttpServletRequest request){
		String xueshengid=request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		if(str.equals(token)){
			List<YiJianXiang> yiJianXiangs = new ArrayList<>();
			Map<String, Object> mpp=new HashMap<>();
			mpp.put("yijianxiangs", "");
			mpp.put("page", "");
			mpp.put("pages", "");
			if(xueshengid!=null&&xueshengid!=""){
				Map<String, String> map = new HashMap<>();
				map.put("kejianxueshengid1", xueshengid+",%");
				map.put("kejianxueshengid2", "%,"+xueshengid+",%");
				map.put("biaoqian", "");
				map.put("nimingbiaoji", "");
				map.put("tijiaozhuangtai", "1");
				map.put("gongbuzhuangtai", "1");
				int count = yijianxiangService.getCountByXSandBQandNM(map);
				int pageSize = 10;
				int pages = (count / pageSize) + 1;
				if (request.getParameter("page") == null || request.getParameter("page") == "") {
					map.put("start", "0");
					map.put("stop", "5");
					yiJianXiangs = yijianxiangService.getAllByXSandBQandNMandpage(map);
					if (!yiJianXiangs.isEmpty()) {
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
									if(string.equals(xueshengid)){
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
					}
					mpp.put("yijianxiangs", yiJianXiangs);
					mpp.put("page", 1);
					mpp.put("pages", pages);
					return JSON.toJSON(mpp);
				}
				else {
					mpp.put("yijianxiangs", "");
					mpp.put("page", "");
					mpp.put("pages", "");
					return JSON.toJSON(mpp);
				}
			}
			return JSON.toJSON(mpp);
		}
		else {
			return null;
		}
	}
	
	//下拉
	@RequestMapping(value="app_XiaLaYiJianDongTai")
	@ResponseBody
	public Object app_XiaLaYiJianDongTai(HttpServletRequest request){
		Map<String, Object> mpp=new HashMap<>();
		List<YiJianXiang> yiJianXiangs = new ArrayList<>();
		String xueshengid=request.getParameter("studentid");
		String banjiID=request.getParameter("banjiid");
		String biaoqian = request.getParameter("biaoqiancha");
		String fanwei = request.getParameter("fanweicha");
		String niming =  request.getParameter("zhuangtaicha");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		if(str.equals(token)){
			Map<String, String> map = new HashMap<>();
			mpp.put("yijianxiangs", "");
			mpp.put("page", "");
			mpp.put("pages", "");
			if(request.getParameter("page") != null && request.getParameter("page") != ""){
				if (Util.isNumeric(request.getParameter("page"))) {
					int page = Integer.parseInt(request.getParameter("page"));
					//
					if(fanwei.equals("")){
						map.put("kejianxueshengid1", xueshengid+",%");
						map.put("kejianxueshengid2", "%,"+xueshengid+",%");
						map.put("biaoqian", biaoqian);
						map.put("nimingbiaoji", niming);
						map.put("tijiaozhuangtai", "1");
						map.put("gongbuzhuangtai", "1");
						int count = yijianxiangService.getCountByXSandBQandNM(map);
						int pageSize = 10;
						int pages = (count / pageSize) + 1;
						if (page > 0 && page <= pages) {
							map.put("start", String.valueOf((page - 1) * 5));
							map.put("stop", "5");
							yiJianXiangs = yijianxiangService.getAllByXSandBQandNMandpage(map);
							if (!yiJianXiangs.isEmpty()) {
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
											if(string.equals(xueshengid)){
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
							}
							mpp.put("yijianxiangs", yiJianXiangs);
							mpp.put("page", page);
							mpp.put("pages", pages);
							return JSON.toJSON(mpp);
						}
						else {
							mpp.put("yijianxiangs", "");
							mpp.put("page", "");
							mpp.put("pages", "");
							return JSON.toJSON(mpp);
						}
					}
					if(fanwei.equals("本班")){
						map.put("kejianxueshengid1", xueshengid+",%");
						map.put("kejianxueshengid2", "%,"+xueshengid+",%");
						map.put("biaoqian", biaoqian);
						map.put("banji", banjiID);
						map.put("nimingbiaoji", niming);
						map.put("tijiaozhuangtai", "1");
						map.put("gongbuzhuangtai", "1");
						int count = yijianxiangService.getCountByXSandBQandBJandNM(map);
						int pageSize = 10;
						int pages = (count / pageSize) + 1;
						if (page > 0 && page <= pages) {
							map.put("start", String.valueOf((page - 1) * 5));
							map.put("stop", "5");
							yiJianXiangs = yijianxiangService.getAllByXSandBQandBJandNMandpage(map);
							if (!yiJianXiangs.isEmpty()) {
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
											if(string.equals(xueshengid)){
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
							}
							mpp.put("yijianxiangs", yiJianXiangs);
							mpp.put("page", page);
							mpp.put("pages", pages);
							return JSON.toJSON(mpp);
						}
						else {
							mpp.put("yijianxiangs", "");
							mpp.put("page", "");
							mpp.put("pages", "");
							return JSON.toJSON(mpp);
						}
					}
					if(fanwei.equals("本专业")){
						String zhuanyedaima = zhuanyeService.selectByPrimaryKey(banjiService.selectByPrimaryKey(Integer.parseInt(banjiID)).getZhuanyeid()).getDaima();
						String yuanxiid = banjiService.selectByPrimaryKey(Integer.parseInt(banjiID)).getYuanxiid().toString();
						map.put("kejianxueshengid1", xueshengid+",%");
						map.put("kejianxueshengid2", "%,"+xueshengid+",%");
						map.put("biaoqian", biaoqian);
						map.put("zhuanyedaima", zhuanyedaima);
						map.put("yuanxiid", yuanxiid);
						map.put("nimingbiaoji", niming);
						map.put("tijiaozhuangtai", "1");
						map.put("gongbuzhuangtai", "1");
						int count = yijianxiangService.getCountByXSandBQandZYandNM(map);
						int pageSize = 10;
						int pages = (count / pageSize) + 1;
						if (page > 0 && page <= pages) {
							map.put("start", String.valueOf((page - 1) * 5));
							map.put("stop", "5");
							yiJianXiangs = yijianxiangService.getAllByXSandBQandZYandNMandpage(map);
							if (!yiJianXiangs.isEmpty()) {
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
											if(string.equals(xueshengid)){
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
							}
							mpp.put("yijianxiangs", yiJianXiangs);
							mpp.put("page", page);
							mpp.put("pages", pages);
							return JSON.toJSON(mpp);
						}
						else {
							mpp.put("yijianxiangs", "");
							mpp.put("page", "");
							mpp.put("pages", "");
							return JSON.toJSON(mpp);
						}
					}
					if(fanwei.equals("本学院")){
						String yuanxiid = banjiService.selectByPrimaryKey(Integer.parseInt(banjiID)).getYuanxiid().toString();
						map.put("kejianxueshengid1", xueshengid+",%");
						map.put("kejianxueshengid2", "%,"+xueshengid+",%");
						map.put("biaoqian", biaoqian);
						map.put("yuanxiid", yuanxiid);
						map.put("nimingbiaoji", niming);
						map.put("tijiaozhuangtai", "1");
						map.put("gongbuzhuangtai", "1");
						int count = yijianxiangService.getCountByXSandBQandYXandNM(map);
						int pageSize = 10;
						int pages = (count / pageSize) + 1;
						if (page > 0 && page <= pages) {
							map.put("start", String.valueOf((page - 1) * 5));
							map.put("stop", "5");
							yiJianXiangs = yijianxiangService.getAllByXSandBQandYXandNMandpage(map);
							if (!yiJianXiangs.isEmpty()) {
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
											if(string.equals(xueshengid)){
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
							}
							mpp.put("yijianxiangs", yiJianXiangs);
							mpp.put("page", page);
							mpp.put("pages", pages);
							return JSON.toJSON(mpp);
						}
						else {
							mpp.put("yijianxiangs", "");
							mpp.put("page", "");
							mpp.put("pages", "");
							return JSON.toJSON(mpp);
						}
					}
					//
				}
				else {
					mpp.put("yijianxiangs", "");
					mpp.put("page", "");
					mpp.put("pages", "");
					return JSON.toJSON(mpp);
				}
			}
			return JSON.toJSON(mpp);
		}
		else {
			return null;
		}
	}
	
	//通过条件查询意见动态
	@RequestMapping(value="app_ChaXunYiJian")
	@ResponseBody
	public Object app_ChaXunYiJian(HttpServletRequest request){
		String xueshengid=request.getParameter("studentid");
		String banjiID=request.getParameter("banjiid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		if(str.equals(token)){
			Map<String, Object> mpp=new HashMap<>();
			mpp.put("yijianxiangs", "");
			mpp.put("page", "");
			mpp.put("pages", "");
			List<YiJianXiang> yiJianXiangs = new ArrayList<>();
			if(xueshengid!=null&&xueshengid!=""){
				String biaoqian = request.getParameter("biaoqiancha");
				String fanwei = request.getParameter("fanweicha");
				String niming =  request.getParameter("zhuangtaicha");
				if(fanwei.equals("")){
					Map<String, String> map = new HashMap<>();
					map.put("kejianxueshengid1", xueshengid+",%");
					map.put("kejianxueshengid2", "%,"+xueshengid+",%");
					map.put("biaoqian", biaoqian);
					map.put("nimingbiaoji", niming);
					map.put("tijiaozhuangtai", "1");
					map.put("gongbuzhuangtai", "1");
					int count = yijianxiangService.getCountByXSandBQandNM(map);
					int pageSize = 10;
					int pages = (count / pageSize) + 1;
					if (request.getParameter("page") == null || request.getParameter("page") == ""){
						map.put("start", "0");
						map.put("stop", "5");
						yiJianXiangs = yijianxiangService.getAllByXSandBQandNMandpage(map);
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
								if(string.equals(xueshengid)){
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
					mpp.put("yijianxiangs", yiJianXiangs);
					mpp.put("page", 1);
					mpp.put("pages", pages);
					return JSON.toJSON(mpp);
				}
				if(fanwei.equals("本班")){
					Map<String, String> map = new HashMap<>();
					map.put("kejianxueshengid1", xueshengid+",%");
					map.put("kejianxueshengid2", "%,"+xueshengid+",%");
					map.put("biaoqian", biaoqian);
					map.put("banji", banjiID);
					map.put("nimingbiaoji", niming);
					map.put("tijiaozhuangtai", "1");
					map.put("gongbuzhuangtai", "1");
					int count = yijianxiangService.getCountByXSandBQandBJandNM(map);
					int pageSize = 10;
					int pages = (count / pageSize) + 1;
					if (request.getParameter("page") == null || request.getParameter("page") == ""){
						map.put("start", "0");
						map.put("stop", "5");
						yiJianXiangs = yijianxiangService.getAllByXSandBQandBJandNMandpage(map);
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
								if(string.equals(xueshengid)){
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
					mpp.put("yijianxiangs", yiJianXiangs);
					mpp.put("page", 1);
					mpp.put("pages", pages);
					return JSON.toJSON(mpp);
				}
				if(fanwei.equals("本专业")){
					String zhuanyedaima = zhuanyeService.selectByPrimaryKey(banjiService.selectByPrimaryKey(Integer.parseInt(banjiID)).getZhuanyeid()).getDaima();
					String yuanxiid = banjiService.selectByPrimaryKey(Integer.parseInt(banjiID)).getYuanxiid().toString();
					Map<String, String> map = new HashMap<>();
					map.put("kejianxueshengid1", xueshengid+",%");
					map.put("kejianxueshengid2", "%,"+xueshengid+",%");
					map.put("biaoqian", biaoqian);
					map.put("zhuanyedaima", zhuanyedaima);
					map.put("yuanxiid", yuanxiid);
					map.put("nimingbiaoji", niming);
					map.put("tijiaozhuangtai", "1");
					map.put("gongbuzhuangtai", "1");
					int count = yijianxiangService.getCountByXSandBQandZYandNM(map);
					int pageSize = 10;
					int pages = (count / pageSize) + 1;
					if (request.getParameter("page") == null || request.getParameter("page") == ""){
						map.put("start", "0");
						map.put("stop", "5");
						yiJianXiangs = yijianxiangService.getAllByXSandBQandZYandNMandpage(map);
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
								if(string.equals(xueshengid)){
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
					mpp.put("yijianxiangs", yiJianXiangs);
					mpp.put("page", 1);
					mpp.put("pages", pages);
					return JSON.toJSON(mpp);
				}
				if(fanwei.equals("本学院")){
					String yuanxiid = banjiService.selectByPrimaryKey(Integer.parseInt(banjiID)).getYuanxiid().toString();
					Map<String, String> map = new HashMap<>();
					map.put("kejianxueshengid1", xueshengid+",%");
					map.put("kejianxueshengid2", "%,"+xueshengid+",%");
					map.put("biaoqian", biaoqian);
					map.put("yuanxiid", yuanxiid);
					map.put("nimingbiaoji", niming);
					map.put("tijiaozhuangtai", "1");
					map.put("gongbuzhuangtai", "1");
					int count = yijianxiangService.getCountByXSandBQandYXandNM(map);
					int pageSize = 10;
					int pages = (count / pageSize) + 1;
					if (request.getParameter("page") == null || request.getParameter("page") == ""){
						map.put("start", "0");
						map.put("stop", "5");
						yiJianXiangs = yijianxiangService.getAllByXSandBQandYXandNMandpage(map);
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
								if(string.equals(xueshengid)){
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
					mpp.put("yijianxiangs", yiJianXiangs);
					mpp.put("page", 1);
					mpp.put("pages", pages);
					return JSON.toJSON(mpp);
				}
			}
			return JSON.toJSON(mpp);
		}
		else {
			return null;
		}
	}
}
