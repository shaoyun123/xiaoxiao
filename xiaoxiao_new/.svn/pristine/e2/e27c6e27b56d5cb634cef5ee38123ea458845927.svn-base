package com.web.controller.app.stu;

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

import com.web.model.ChaQinAnPai;
import com.web.model.ChaQinJieGuo;
import com.web.model.FuDaoYuan;
import com.web.model.Qingjia;
import com.web.model.XueSheng;
import com.web.service.ChaQinService;
import com.web.service.FuDaoYuanService;
import com.web.service.QingjiaService;
import com.web.service.XueShengService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
public class AppChaQinController {
	@Autowired
	private XueShengService xueshengService;
	@Autowired
	private FuDaoYuanService fuDaoYuanService;
	@Autowired
	private ChaQinService chaQinService;
	@Autowired
	private QingjiaService qingjiaService;

	// 判断是否有今日查寝 5127,zytech,500,zytech,117
	@RequestMapping(value="app_IsHavingChaQin")
	@ResponseBody
	public List<ChaQinAnPai> app_IsHavingChaQin(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String code=request.getParameter("CODE");
		String data[]=code.split(",zytech,");
		String token = request.getParameter("token");
		Integer pageSize = (request.getParameter("pageSize") == null || "".equals(request.getParameter("pageSize"))) ? 0 : Integer.valueOf(request.getParameter("pageSize")) ;
		Integer pageNum = (request.getParameter("pageNum") == null || "".equals(request.getParameter("pageNum"))) ? 0 : Integer.valueOf(request.getParameter("pageNum")) ;
		String status = request.getParameter("status");
		String strings = data[0] + "," + status;
		String str = Util.SHA1Encode(strings);
		String searchStatus = request.getParameter("searchStatus");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if (str.equals(token)) {
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			FuDaoYuan fuDaoYuan=fuDaoYuanService.getBybanJiID(data[1]+",%","%,"+data[1]+",%");
			if(fuDaoYuan != null){
				String date = getCurrTimeForString();
				paramMap.put("fudaoyuanId", fuDaoYuan.getFudaoyuanid());
				paramMap.put("date", date.split(" ")[0]);
				//paramMap.put("dateTime", date.split(" ")[1]);
				paramMap.put("susheId", data[2]);
				paramMap.put("pageSize", pageSize);
				paramMap.put("pageNum",  pageNum*pageSize );
				paramMap.put("searchStatus", searchStatus);
				List<ChaQinAnPai> chaQinAnPaiList = chaQinService.selectByYongHuIdAndRiQi(paramMap);
				if(chaQinAnPaiList!=null && chaQinAnPaiList.size() > 0){
					for (ChaQinAnPai chaQinAnPai : chaQinAnPaiList) {
						Map<String,Integer> map = new HashMap<>();
						map.put("susheid", Integer.parseInt(data[2]));
						map.put("anpaiid", chaQinAnPai.getAnpaiid());
						List<ChaQinJieGuo> jieGuos = chaQinService.selectChaQinJieGuoByanPaiIDAndsuSheID(map);
						if (jieGuos != null && jieGuos.size() > 0) {
							for (ChaQinJieGuo chaQinJieGuo : jieGuos) {
								if(data[0].equals(chaQinJieGuo.getXueshengid())){
									XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(chaQinJieGuo.getXueshengid()));
									chaQinAnPai.setShangchuanren(xueshengService.getUserById(chaQinJieGuo.getShangchuanren()).getXingming());
									chaQinAnPai.setZhaopian(chaQinJieGuo.getZhaopian());
									if (chaQinJieGuo.getZhuangtai() != -1) {
										if (chaQinJieGuo.getZhuangtai() == 1) {
											chaQinAnPai.setQueqin("不缺寝");
										} else {
											// 判断是否请假
											paramMap.put("zhuangtai", 2);
											paramMap.put("xueshengid", xueSheng.getXueshengid());
											paramMap.put("kaishishijian", chaQinAnPai.getRiqi() + chaQinAnPai.getKaishishijian());
											paramMap.put("jieshushijian", chaQinAnPai.getRiqi() + chaQinAnPai.getJieshushijian());
											Qingjia qingjia = qingjiaService.getByZhuangTaiAndShijianAndXueShengid(paramMap);
											if (qingjia != null) {
												chaQinAnPai.setQueqin("请假");
											} else {
												chaQinAnPai.setQueqin("缺寝");
											}
										}
									} else {
										chaQinAnPai.setQueqin("辅导员未查看");
									}
									break;
								}
							}
						}
					}
					return chaQinAnPaiList;
				}
				else {
					return new ArrayList<ChaQinAnPai>();
				}
			}else {
				return new ArrayList<ChaQinAnPai>();
			}
		}
		else {
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

	/*
	 * //返回今日查寝安排的信息
	 * 
	 * @RequestMapping(value="app_BackTodayChaQinXinXi")
	 * 
	 * @ResponseBody public ChaQinAnPai
	 * app_BackTodayChaQinXinXi(HttpServletRequest request){ String
	 * code=request.getParameter("CODE"); String data[]=code.split(",zytech,");
	 * String token = request.getParameter("token"); String status =
	 * request.getParameter("status"); String strings = data[0] + "," + status;
	 * String str = Util.SHA1Encode(strings); if (str.equals(token)) { Date date
	 * = new Date(); //SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyy-MM-dd"); FuDaoYuan
	 * fuDaoYuan=fuDaoYuanService.getBybanJiID(data[1]+",%","%,"+data[1]+",%");
	 * ChaQinAnPai chaQinAnPai =
	 * chaQinService.selectByYongHuIdAndRiQi(fuDaoYuan.getFudaoyuanid(), date);
	 * if(chaQinAnPai!=null){ return chaQinAnPai; } else { return null; } } else
	 * { return null; } }
	 */
}
