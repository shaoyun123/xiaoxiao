package com.web.controller.app.stu;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.web.model.JLNR;
import com.web.service.FDAPService;
import com.web.service.JLNRService;
import com.web.service.YongHuService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
public class AppShenDuFuDao {
	@Autowired
	private JLNRService jlnrService;

	@Autowired
	private FDAPService fdapSrevice;
	
	@Autowired
	private YongHuService yonghuService;
	
	//通过xueshengid显示未被辅导员审核过的交流信息
	@RequestMapping(value = "app_XianShijiaoliuneirong1")
	@ResponseBody
	public Object app_XianShijiaoliuneirong1(HttpServletRequest request) {
		List<JLNR> jiaoliunr = new ArrayList<>();
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = code + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			Map<String, Object> mpp=new HashMap<>();
			if(code!=""&&code!=null){
				int count = jlnrService.getCountByxueShengIDAndUnaudited(Integer.parseInt(code));
				int pageSize = 10;
				int pages = (count / pageSize) + 1;
				if (request.getParameter("page") == null || request.getParameter("page") == "") {
					jiaoliunr = jlnrService.getALLByxueShengIDAndLimitAndUnaudited(Integer.parseInt(code),0,10);
					if(!jiaoliunr.isEmpty()){
						for (JLNR jlnr : jiaoliunr) {
							System.out.println(jlnr.getJiaoliumingcheng());
							int fudaoyuanid = jlnr.getFudaoyuanid();
							String shenheren = yonghuService.selectYongHuByID(fudaoyuanid).getYonghuxingming();
							jlnr.setShenheren(shenheren);
							int anpaiid = jlnr.getAnpaiid();
							String jiezhiriqi = fdapSrevice.getjieZhiShiJianByanPaiID(anpaiid);
							jlnr.setJiezhiriqi(jiezhiriqi);
						}
					}
					mpp.put("jlnr", jiaoliunr);
					mpp.put("pages", pages);
					mpp.put("page", 1);
					return JSON.toJSON(mpp);
				}
				else {
					if (Util.isNumeric(request.getParameter("page"))) {
						int page = Integer.parseInt(request.getParameter("page"));
						jiaoliunr = jlnrService.getALLByxueShengIDAndLimitAndUnaudited(Integer.parseInt(code),(page - 1) * 10,10);
						if(!jiaoliunr.isEmpty()){
							for (JLNR jlnr : jiaoliunr) {
								int fudaoyuanid = jlnr.getFudaoyuanid();
								String shenheren = yonghuService.selectYongHuByID(fudaoyuanid).getYonghuxingming();
								jlnr.setShenheren(shenheren);
								int anpaiid = jlnr.getAnpaiid();
								String jiezhiriqi = fdapSrevice.getjieZhiShiJianByanPaiID(anpaiid);
								jlnr.setJiezhiriqi(jiezhiriqi);
							}
						}
						mpp.put("jlnr", jiaoliunr);
						mpp.put("pages", pages);
						mpp.put("page", page);
						return JSON.toJSON(mpp);
					}else {
						mpp.put("jlnr", "");
						mpp.put("pages", "");
						mpp.put("page", "");
						return JSON.toJSON(mpp);
					}
				}
			}
			else {
				mpp.put("jlnr", "");
				mpp.put("pages", "");
				mpp.put("page", "");
				return JSON.toJSON(mpp);
			}
		}
		else {
			return null;
		}
	}
	
	//通过xueshengid显示已经提交并被辅导员通过的交流信息
	@RequestMapping(value = "app_XianShijiaoliuneirong2")
	@ResponseBody
	public Object app_XianShijiaoliuneirong2(HttpServletRequest request) {
		List<JLNR> jiaoliunr = new ArrayList<>();
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = code + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			Map<String, Object> mpp=new HashMap<>();
			if(code!=""&&code!=null){
				int count = jlnrService.getCountByxueShengIDAndPass(Integer.parseInt(code));
				int pageSize = 10;
				int pages = (count / pageSize) + 1;
				if (request.getParameter("page") == null || request.getParameter("page") == "") {
					jiaoliunr = jlnrService.getALLByxueShengIDAndLimitAndPass(Integer.parseInt(code),0,10);
					if(!jiaoliunr.isEmpty()){
						for (JLNR jlnr : jiaoliunr) {
							int fudaoyuanid = jlnr.getFudaoyuanid();
							String shenheren = yonghuService.selectYongHuByID(fudaoyuanid).getYonghuxingming();
							jlnr.setShenheren(shenheren);
						}
					}
					mpp.put("jlnr", jiaoliunr);
					mpp.put("pages", pages);
					mpp.put("page", 1);
					return JSON.toJSON(mpp);
				}
				else {
					if (Util.isNumeric(request.getParameter("page"))) {
						int page = Integer.parseInt(request.getParameter("page"));
						jiaoliunr = jlnrService.getALLByxueShengIDAndLimitAndPass(Integer.parseInt(code),(page - 1) * 10,10);
						if(!jiaoliunr.isEmpty()){
							for (JLNR jlnr : jiaoliunr) {
								int fudaoyuanid = jlnr.getFudaoyuanid();
								String shenheren = yonghuService.selectYongHuByID(fudaoyuanid).getYonghuxingming();
								jlnr.setShenheren(shenheren);
							}
						}
						mpp.put("jlnr", jiaoliunr);
						mpp.put("pages", pages);
						mpp.put("page", page);
						return JSON.toJSON(mpp);
					}else {
						mpp.put("jlnr", "");
						mpp.put("pages", "");
						mpp.put("page", "");
						return JSON.toJSON(mpp);
					}
				}
			}
			else {
				mpp.put("jlnr", "");
				mpp.put("pages", "");
				mpp.put("page", "");
				return JSON.toJSON(mpp);
			}
		}
		else {
			return null;
		}
	}
	
	//显示已经提交并被通过的交流信息的详细内容
	@RequestMapping(value = "app_XianShiJLNR_xiangqing")
	@ResponseBody
	public JSONObject app_XianShiJLNR_xiangqing(HttpServletRequest request){
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xueshengid = request.getParameter("xueshengid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			String xueshengshangchuan = jlnrService.getxueShengShangChuanByjiaoLiuID(Integer.parseInt(code));
			String fudaoyuanshenhe = jlnrService.getfuDaoYuanShenHeByjiaoLiuID(Integer.parseInt(code));
			String jiaoliumingcheng = jlnrService.getjiaoLiuMingChengByjiaoLiuID(Integer.parseInt(code));
			Map<String, String> map = new HashMap<String, String>();
			map.put("jiaoliumingcheng", jiaoliumingcheng);
			map.put("xueshengshangchuan", xueshengshangchuan);
			map.put("fudaoyuanshenhe", fudaoyuanshenhe);
			return JSONObject.fromObject(map);
		}
		else {
			return null;
		}
	}
	//撤回已上传但未审核的交流内容
	@RequestMapping(value="app_CheHuijiaoliuneirong")
	@ResponseBody
	public String app_CheHuijiaoliuneirong(HttpServletRequest request){
		String retinfo="";
		Map<String, String> map = new HashMap<String, String>();
		String jiaoliuid = request.getParameter("CODE");
		JLNR jlnr=jlnrService.selectByPrimaryKey(Integer.parseInt(jiaoliuid));
		String jiezhiriqi=fdapSrevice.getjieZhiShiJianByanPaiID(jlnr.getAnpaiid());
		Date date =new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int res=df.format(date).compareTo(jiezhiriqi);
		if(jlnr.getShenhezhuangtai().equals("0")&&res!=1){
			map.put("jiaoliuid", jiaoliuid);
			map.put("shangchuanzhuangtai", "0");
			int i=jlnrService.updateshangchuanzhuangtaiByjiaoLiuID(map);
			if(i>0){
				retinfo="success";
			}
			else {
				retinfo="nothingness";
			}
		}
		else {
			retinfo="fail";
			if(res==1){
				retinfo="timeup";
			}
		}
		return retinfo;
	}
	//上传未上传的交流内容
	@RequestMapping(value="app_ShangChuanjiaoliuneirong")
	@ResponseBody
	public String app_ShangChuanjiaoliuneirong(HttpServletRequest request){
		String retinfo="";
		Map<String, String> map = new HashMap<String, String>();
		String jiaoliuid = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xueshengid = request.getParameter("xueshengid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			JLNR jlnr=jlnrService.selectByPrimaryKey(Integer.parseInt(jiaoliuid));
			String jiezhiriqi=fdapSrevice.getjieZhiShiJianByanPaiID(jlnr.getAnpaiid());
			Date date =new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			int res=df.format(date).compareTo(jiezhiriqi);
			if(jlnr.getShangchuanzhuangtai().equals("0")&&res!=1){
				map.put("jiaoliuid", jiaoliuid);
				map.put("shangchuanzhuangtai", "1");
				map.put("xueshengid", jlnr.getXueshengid().toString());
				map.put("xueshengshangchuan", jlnr.getXueshengshangchuan());
				map.put("shangchuanriqi", df.format(date));
				int i=jlnrService.updateByPrimaryKey(map);
				if(i>0){
					retinfo="success";
				}
				else {
					retinfo="nothingness";
				}
			}
			else {
				retinfo="fail";
				if(res==1){
					retinfo="timeup";
				}
			}
			return retinfo;
		}
		else {
			return null;
		}
	}
	//通过anpaiid修改尚未上传的交流内容
	@ResponseBody
	@RequestMapping(value="app_XiuGaijiaoliuneirong")
	public Object app_XiuGaijiaoliuneirong(HttpServletRequest request){
		Map<String, String> map=new HashMap<String,String>();
		String code=request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xueshengid = request.getParameter("xueshengid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			String data[]=code.split(",zytech,");
			String mingcheng = fdapSrevice.getmingChengByanPaiID(Integer.parseInt(data[1]));
			String yaoqiu = fdapSrevice.getyaoQiuByanPaiID(Integer.parseInt(data[1]));
			String jiezhishijian = fdapSrevice.getjieZhiShiJianByanPaiID(Integer.parseInt(data[1]));
			String xueshengshangchuan = jlnrService.getxueShengShangChuanByjiaoLiuID(Integer.parseInt(data[0]));
			map.put("jiaoliuid", data[0]);
			map.put("mingcheng", mingcheng);
			map.put("yaoqiu", yaoqiu);
			map.put("jiezhishijian", jiezhishijian);
			map.put("xueshengshangchuan", xueshengshangchuan);
			return JSON.toJSON(map);
		}
		else {
			return null;
		}
	}
	//保存修改后的学生汇报内容
	@RequestMapping(value="app_SaveXiuGaijiaoliuneirong")
	@ResponseBody
	public String app_SaveXiuGaijiaoliuneirong(String jiaoliuid,String anpaid,String xueshengshangchuan,HttpServletRequest request)throws IOException{
		String retInfo="";
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String studentid = request.getParameter("studentid");
		String string = studentid + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			Map<String, String> map = new HashMap<String, String>();
			JLNR jlnr=jlnrService.selectByPrimaryKey(Integer.parseInt(jiaoliuid));
			if(jlnr.getShangchuanzhuangtai().equals("0")){
				map.put("jiaoliuid", jiaoliuid);
				map.put("xueshengshangchuan", xueshengshangchuan);
				int i=jlnrService.updatexueshengshangchuanByjiaoLiuID(map);
				if(i>0){
					retInfo="success";
				}
				else {
					retInfo="nothingness";
				}
			}
			else {
				retInfo="fail";
			}
			return retInfo;
		}
		else {
			return null;
		}
	}
}
