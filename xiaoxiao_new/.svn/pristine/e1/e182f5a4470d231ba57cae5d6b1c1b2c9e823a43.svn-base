package com.web.controller.app.stu;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.security.MessageDigest;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.web.model.App_LiaoTianJiLu;
import com.web.model.App_LiaoTianQun;
import com.web.model.BanJi;
import com.web.model.FuDaoYuan;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.service.App_LiaoTianJiLuService;
import com.web.service.App_LiaoTianQunService;
import com.web.service.BanJiService;
import com.web.service.FuDaoYuanService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.util.Util;

@Controller
public class AppLiaoTianController {
	@Autowired
	private BanJiService banjiService;
	@Autowired
	private App_LiaoTianJiLuService liaotianjiluService;
	@Autowired
	private App_LiaoTianQunService liaotianqunService;
	@Autowired
	private XueShengService xueShengService;
	@Autowired
	private YongHuService yonghuService;
	@Autowired
	private FuDaoYuanService fudaoyuanService;
	
	@RequestMapping(value="app_BanJiQun")
	@ResponseBody
	public BanJi app_BanJiQun(HttpServletRequest request){
		String banjiid=request.getParameter("banjiid");
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String data[] = code.split(",zytech,");
		String string= data[0]+","+data[1];
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			BanJi banJi=new BanJi();
			if(banjiid==null||banjiid==""){
				return banJi;
			}
			
			banJi=banjiService.selectByPrimaryKey(Integer.parseInt(banjiid));
			return banJi;
		}
		else {
			return null;
		}
	}
	
	@RequestMapping(value="app_BanJiQun_fdy")
	@ResponseBody
	public List<BanJi> app_BanJiQun_fdy(HttpServletRequest request){
		String fudaoyuanid=request.getParameter("id");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string= fudaoyuanid+","+status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			FuDaoYuan fuDaoYuan=fudaoyuanService.getByfuDaoYuanID(Integer.parseInt(fudaoyuanid));
			List<BanJi> banJis=new ArrayList<>();
			BanJi banJi=new BanJi();
			String item[]=fuDaoYuan.getBanjiid().split(",");
			for(int i=0;i<item.length;i++){
				if(item!=null&&item[i]!=""){
					banJi=banjiService.selectByPrimaryKey(Integer.parseInt(item[i]));
					banJis.add(banJi);
				}
				
			}
			return banJis;
		}
		else {
			return null;
		}
		
	}
	
	//获取之前聊天记录
	@RequestMapping(value="app_LiaoTianJiLu")
	@ResponseBody
	public Object app_LiaoTianJiLu(HttpServletRequest request){
		String banjiid=request.getParameter("banjiid");
		String id=request.getParameter("id");
		String token = request.getParameter("token");
		String status =request.getParameter("status");
		String string = id + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			Map<String, Object> mapp=new HashMap<>();
			mapp.put("jilus", "");
			mapp.put("pages", "");
			mapp.put("page", "");
			if(id==null||id==""){
				return JSON.toJSON(mapp);
			}
			App_LiaoTianQun liaoTianQun=liaotianqunService.selectByBanJiID(Integer.parseInt(banjiid));
			if(liaoTianQun==null){
				return JSON.toJSON(mapp);
			}
			int count=liaotianjiluService.selectCountLiaoTianJiLuByQunID(liaoTianQun.getQunid());
			int pageSize = 10;
			int pages = (count / pageSize) + 1;
			int page =1;
			List<App_LiaoTianJiLu> jiLus=new ArrayList<>();
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				Map<String, String> map = new HashMap<>();
				map.put("qunid", liaoTianQun.getQunid().toString());
				map.put("start", "0");
				map.put("stop", String.valueOf(pageSize));
				jiLus=liaotianjiluService.getALLByQunID(map);
			}
			else {
				if (!Util.isNumeric(request.getParameter("page"))) {
					return JSON.toJSON(mapp);
				}
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page <= pages){
					Map<String, String> map = new HashMap<>();
					map.put("qunid", liaoTianQun.getQunid().toString());
					map.put("start", String.valueOf((page - 1) * 10));
					map.put("stop", String.valueOf(pageSize));
					jiLus=liaotianjiluService.getALLByQunID(map);
				}else {
					return JSON.toJSON(mapp);
				}
			}
			if (!jiLus.isEmpty()) {
				for (App_LiaoTianJiLu jiLu : jiLus) {
					if(jiLu.getXueshengid()!=null){
						XueSheng xueSheng=xueShengService.getUserById(jiLu.getXueshengid());
						jiLu.setXingming(xueSheng.getXingming());
					}
					if(jiLu.getJiaoshiid()!=null){
						YongHu yongHu=yonghuService.selectYongHuByID(jiLu.getJiaoshiid());
						jiLu.setXingming(yongHu.getYonghuxingming());
					}
				}
			}
			mapp.put("jilus", jiLus);
			mapp.put("pages", pages);
			mapp.put("page", page);
			return JSON.toJSON(mapp);
		}
		else {
			return null;
		}
	}
	
	//根据发送方ID获得姓名
	@RequestMapping(value="app_XinXiFaSongName")
	@ResponseBody
	public String app_XinXiFaSongName(HttpServletRequest request){
		String id=request.getParameter("id");
		String banjiid=request.getParameter("banjiid");
		XueSheng xueSheng=xueShengService.getUserById(Integer.parseInt(id));
		if(xueSheng==null){
			FuDaoYuan fDaoYuan=fudaoyuanService.getBybanJiID(banjiid+",%","%,"+banjiid+",%");
			YongHu yongHu=yonghuService.selectYongHuByID(fDaoYuan.getFudaoyuanid());
			 return JSON.toJSONString(yongHu.getYonghuxingming());
		}
		return JSON.toJSONString(xueSheng.getXingming());
	}
	
	//sha1加密算法
	@RequestMapping(value="app_Sha1")
	@ResponseBody
	public String app_Sha1(HttpServletRequest request){
		String str=request.getParameter("signature");
		System.out.println(str);
		if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];      
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
	}
	
	//保存聊天信息
	@RequestMapping(value="app_SaveLiaoTianXinXi")
	@ResponseBody
	public String app_SaveLiaoTianXinXi(HttpServletRequest request) throws ParseException{
		String msg=request.getParameter("CODE");
		String banjiid=request.getParameter("banjiid");
		String id=request.getParameter("id");
		String status=request.getParameter("status");
		String token = request.getParameter("token");
		String string = id + "," + status;
		String str = Util.SHA1Encode(string);
		if(str.equals(token)){
			App_LiaoTianQun liaoTianQun=liaotianqunService.selectByBanJiID(Integer.parseInt(banjiid));
			App_LiaoTianJiLu aJiLu=new App_LiaoTianJiLu();
			if(status.equals("xuesheng")){
				aJiLu.setXueshengid(Integer.parseInt(id));
			}
			else {
				aJiLu.setJiaoshiid(Integer.parseInt(id));
			}
			aJiLu.setQunid(liaoTianQun.getQunid());
			aJiLu.setLiaotianneirong(msg);
			Date date=new Date();
			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			aJiLu.setShijian(date);
			int i=liaotianjiluService.insertByQunID(aJiLu);
			if(i>0){
				return "success";
			}
			else {
				return "fail";
			}
		}
		else {
			return null;
		}
	}
}
