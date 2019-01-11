package com.web.controller.app.stu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.web.model.TongZhi;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.service.BanJiService;
import com.web.service.NewsService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.PageData;

@Controller
public class AppNewsController extends BaseController{
	@Autowired
	private NewsService newsService;
	@Autowired
	private XueShengService xueshengService;
	@Autowired
	private BanJiService banjiService;
	@Autowired
	private YongHuService yonghuService;
	@Autowired
	private YuanXiService yuanxiService;

	/**
	 * 查询新闻列表-分页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "app_newsList")
	@ResponseBody
	public Object app_newsList(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String canyurenId = request.getParameter("canyurenId");
		String status = request.getParameter("status");
		XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(canyurenId));
//		String xuexiao = (request.getParameter("xuexiao") == null || "".equals(request.getParameter("xuexiao"))) ? "" : request.getParameter("xuexiao").split("_")[0];
		String xuexiao = "";
		if("xuesheng".equals(status)){
			xuexiao = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
		}else{
			YongHu yh = yonghuService.selectYongHuByID(Integer.parseInt(canyurenId));
			xuexiao = yuanxiService.selectByPrimaryKey(yh.getYuanxiid()).getXuexiaoid().toString();
		}
		Integer type = (request.getParameter("type") == null || "".equals(request.getParameter("type"))) ? 0 : Integer.parseInt(request.getParameter("type"));
		returnMap.put("status", "-1");
		if(token != null && !"".equals(token) && canyurenId != null && !"".equals(canyurenId) && xuexiao != null && !"".equals(xuexiao)){
			Integer pageSize = (request.getParameter("pageSize") == null || "".equals(request.getParameter("pageSize"))) ? 0 : Integer.valueOf(request.getParameter("pageSize")) ;
			Integer pageNum = (request.getParameter("pageNum") == null || "".equals(request.getParameter("pageNum"))) ? 0 : Integer.valueOf(request.getParameter("pageNum")) ;
			Map<String,Object> paramMap = new HashMap<String,Object>();
			
			Calendar calendar = Calendar.getInstance(); 
			calendar.setTime(new Date());
			if(request.getParameter("cal")==null || request.getParameter("cal").equals("")){
				calendar.add(calendar.MONTH, -3); //设置为前3月
			}else{
				int i = Integer.parseInt(request.getParameter("cal"));
				calendar.add(calendar.MONTH, -i);
			}
			Date dBefore = calendar.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");
			String riqi1 = sdf.format(dBefore);
			String riqi2 = sdf2.format(dBefore);
			paramMap.put("riqi1", riqi1);
			paramMap.put("riqi2", riqi2);
			paramMap.put("pageSize", pageSize);
			paramMap.put("pageNum",  pageNum*pageSize);
			paramMap.put("status",  status);
			paramMap.put("regexp", canyurenId+"_"+status+",");
			paramMap.put("xuexiao", xuexiao);
			paramMap.put("type", type);
//			List<TongZhi> newsList = null;
			List<Map<String,Object>> newsList = null;
			try{
				newsList = newsService.getNewsByParam(paramMap);
				returnMap.put("status", "1");
				returnMap.put("datas", newsList);
			}catch (Exception e) {
				// TODO: handle exception
				returnMap.put("status", "0");
			}
		}
		return JSONObject.fromObject(returnMap);
	}
	
//	//更改密码方法
//	@RequestMapping(value="app_UpdatePassword")
//	@ResponseBody
//	public String app_UpdatePasswordToXueSheng(HttpServletRequest request){
//		String token = request.getParameter("token");
//		String status = request.getParameter("status");
//		String code = request.getParameter("CODE");
//		String yuanmima = request.getParameter("yuanmima");
//		String xinmima = request.getParameter("xinmima");
//		String querenxinmima = request.getParameter("querenxinmima");
//		String data[] = code.split(",zytech,");
//		String string = data[0] + "," + status;
//		String str = Util.SHA1Encode(string);
//		if(str.equals(token)){
//			if(status.equals("xuesheng")){
//				String password =xueshengService.selectPassWdByID(Integer.parseInt(data[0]));
//				if(password.equals(yuanmima)){
//					if(xinmima.equals(querenxinmima)){
//						int i = xueshengService.updatePassWdByID(xinmima, Integer.parseInt(data[0]));
//						if(i!=0){
//							return "success";
//						}
//						else {
//							return "fail2";
//						}
//					}
//					else {
//						return "fail3";
//					}
//				}
//				else {
//					return "fail1";
//				}
//			}
//		}
//		else {
//			return null;
//		}
//		return null;
//	}
	
	/**
	 * 展示新闻类型
	 */
	@RequestMapping("app_newsLeiXingList")
	@ResponseBody
	public Object app_newsLeiXingList(HttpServletRequest request){
		Map<String,Object> returnMap = new HashMap<>();
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		PageData pd = this.getPageData();
		String jiaoLiuParam = pd.getString("dataInfo");
		JSONObject jsonObject = JSONObject.fromObject(jiaoLiuParam);
		Map<String,Object> parammap = (Map<String, Object>)JSONObject.toBean(jsonObject, Map.class);
		String xuexiao = (request.getParameter("xuexiao") == null || "".equals(request.getParameter("xuexiao"))) ? "" : request.getParameter("xuexiao").split("_")[0];
		returnMap.put("status", "-1");
		if(token != null && !"".equals(token)  && xuexiao != null && !"".equals(xuexiao)){
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(parammap.get("id").toString()));
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("status",  status);
			if("xuesheng".equals(status)){
				paramMap.put("xuexiao", banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid()));
			}else{
				YongHu yh = yonghuService.selectYongHuByID(Integer.parseInt(parammap.get("id").toString()));
				xuexiao = yuanxiService.selectByPrimaryKey(yh.getYuanxiid()).getXuexiaoid().toString();
				paramMap.put("xuexiao",xuexiao);
			}
			List<Map<String,Object>> newsList = null;
			try{
				newsList = newsService.getNewsLeiXingByParam(paramMap);
				returnMap.put("status", "1");
				returnMap.put("datas", newsList);
			}catch (Exception e) {
				// TODO: handle exception
				returnMap.put("status", "0");
			}
		}
		return JSONObject.fromObject(returnMap);
	}
	
	/**
	 * 某类型下的新闻
	 */
	@RequestMapping("app_newsLeiXingById")
	@ResponseBody
	public Object app_newsLeiXingById(HttpServletRequest request){
		Map<String,Object> returnMap = new HashMap<>();
		String token = request.getParameter("token");
		String canyurenId = request.getParameter("canyurenId");
		XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(canyurenId));
		String status = request.getParameter("status");
//		String xuexiao = (request.getParameter("xuexiao") == null || "".equals(request.getParameter("xuexiao"))) ? "" : request.getParameter("xuexiao").split("_")[0];
		String xuexiao = "";
		if("xuesheng".equals(status)){
			xuexiao = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
		}else{
			YongHu yh = yonghuService.selectYongHuByID(Integer.parseInt(canyurenId));
			xuexiao = yuanxiService.selectByPrimaryKey(yh.getYuanxiid()).getXuexiaoid().toString();
		}
		Integer type = (request.getParameter("type") == null || "".equals(request.getParameter("type"))) ? 0 : Integer.parseInt(request.getParameter("type"));
		returnMap.put("status", "-1");
		if(token != null && !"".equals(token) && canyurenId != null && !"".equals(canyurenId) && xuexiao != null && !"".equals(xuexiao)){
			Integer pageSize = (request.getParameter("pageSize") == null || "".equals(request.getParameter("pageSize"))) ? 0 : Integer.valueOf(request.getParameter("pageSize")) ;
			Integer pageNum = (request.getParameter("pageNum") == null || "".equals(request.getParameter("pageNum"))) ? 0 : Integer.valueOf(request.getParameter("pageNum")) ;
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("pageSize", pageSize);
			paramMap.put("pageNum",  pageNum*pageSize);
			paramMap.put("status",  status);
			paramMap.put("regexp", canyurenId+"_"+status+",");
			paramMap.put("xuexiao", xuexiao);
			paramMap.put("type", type);
//			List<TongZhi> newsList = null;
			List<Map<String,Object>> newsList = null;
			try{
				newsList = newsService.getNewsByParam(paramMap);
				returnMap.put("status", "1");
				returnMap.put("datas", newsList);
			}catch (Exception e) {
				// TODO: handle exception
				returnMap.put("status", "0");
			}
		}
		return JSONObject.fromObject(returnMap);
	}
	
}
