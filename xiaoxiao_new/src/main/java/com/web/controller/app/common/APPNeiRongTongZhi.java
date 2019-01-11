package com.web.controller.app.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.controller.base.BaseController;
import com.web.model.BanJi;
import com.web.model.NeiRongTongZhi;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.service.BanJiService;
import com.web.service.NeiRongTongZhiService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.util.PageData;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
public class APPNeiRongTongZhi extends BaseController {

	@Autowired
	private XueShengService xueShengService;
	@Autowired
	private BanJiService banJiService;
	@Autowired
	private NeiRongTongZhiService neiRongTongZhiService;
	@Autowired
	private YongHuService yongHuService;
	
	// 内容通知 列表
	@RequestMapping(value="app_tongzhilist")
	@ResponseBody
	public List<NeiRongTongZhi> app_tongZhiList(HttpServletRequest request, HttpServletResponse response){
		PageData pd = this.getPageData();
		String dataInfo = pd.getString("dataInfo");
		JSONObject jsonObject = JSONObject.fromObject(dataInfo);
		Map<String,Object> paramMap = (Map<String, Object>)JSONObject.toBean(jsonObject, Map.class);
		String xueshengid = paramMap.get("id").toString();
		String status = paramMap.get("status").toString();
		String str = Util.SHA1Encode(xueshengid+","+status);
		String token = request.getParameter("token");
		List<NeiRongTongZhi> tongZhiS = new ArrayList<>();
		if(str.equals(token)){
			XueSheng xs = xueShengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
			BanJi banji = banJiService.selectByPrimaryKey(xs.getBanjiid());
			int zhuanyeid = banji.getZhuanyeid();
			paramMap.put("zhuanYeID", zhuanyeid);
			Integer pageSize = (request.getParameter("pageSize") == null || "".equals(request.getParameter("pageSize"))) ? 0 : Integer.valueOf(request.getParameter("pageSize")) ;
			Integer pageNum = (request.getParameter("pageNum") == null || "".equals(request.getParameter("pageNum"))) ? 0 : Integer.valueOf(request.getParameter("pageNum")) ;
			paramMap.put("pageSize", pageSize);
			paramMap.put("pageNum",  pageNum*pageSize);
			
			tongZhiS = neiRongTongZhiService.findByBanJiIDAndZhuanYeID(paramMap);
			for (NeiRongTongZhi neiRongTongZhi : tongZhiS) {
				YongHu yonghu = yongHuService.selectYongHuByID(neiRongTongZhi.getFaburenid());
				neiRongTongZhi.setFaburenxingming(yonghu.getYonghuxingming());
				String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
				neiRongTongZhi.setUrl(url);
			}
			return tongZhiS;
		}
		return null;
	}
	
	// 通过内容通知id，返回一个页面
	@RequestMapping(value="app_tongzhibyid")
	@ResponseBody
	public ModelAndView app_tongZhiByID(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
//		String dataInfo = pd.getString("dataInfo");
//		JSONObject jsonObject = JSONObject.fromObject(dataInfo);
//		Map<String,Object> paramMap = (Map<String, Object>)JSONObject.toBean(jsonObject, Map.class);
//		String xueshengid = paramMap.get("id").toString();
		String xueshengid = request.getParameter("ids");
//		String status = paramMap.get("status").toString();
		String status = request.getParameter("status");
		String str = Util.SHA1Encode(xueshengid+","+status);
		String token = request.getParameter("token");
		List<NeiRongTongZhi> tongZhiS = new ArrayList<>();
		if(str.equals(token)){
			String id = request.getParameter("id");
			NeiRongTongZhi tongZhi = neiRongTongZhiService.selectByPrimaryKey(id);
			mv.addObject("tongzhi", tongZhi);
			mv.setViewName("common/chakantongzhi");
			return mv;
		}
		return null;
	}
}
