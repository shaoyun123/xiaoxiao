package com.web.controller.app.stu;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.web.service.KeChengWenDangService;

import net.sf.json.JSONObject;


@Controller
public class KeChengWenDangController {
	@Autowired
	private KeChengWenDangService keChengWenDangService;
	


	/**
	 * 每秒扫描一次uuid,查看是否有人扫码
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "app_scanlogin")
	public JSONObject app_scanlogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xueshengid = request.getParameter("xueshengid");
		String kechengid = request.getParameter("kechengid");
		String uuid = request.getParameter("uuid");
		String leixing = request.getParameter("leixing");
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> saomaMap = keChengWenDangService.selectsaomaxinxi(uuid);
		int i = 0;
		if(saomaMap!=null) {
			if(saomaMap.get("keChengID")!=null) {
				jsonObject.put("status","shixiao");
			}else {
				saomaMap.put("keChengID", kechengid);
				saomaMap.put("xueShengID", xueshengid);
				saomaMap.put("leiXing", leixing);
				saomaMap.put("id", saomaMap.get("ID"));
				i= keChengWenDangService.updatesaomashangchuan(saomaMap);
			}
		}else {
			jsonObject.put("status", "error");
		}
		if(i!=0) {
			jsonObject.put("status","success");
		}
		else {
			jsonObject.put("status","fail");
		}
		return jsonObject;
				
	}
	
}
