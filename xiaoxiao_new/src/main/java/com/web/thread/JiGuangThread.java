package com.web.thread;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

import com.web.model.XiaoXiFaSong;
import com.web.service.JiGuangService;
import com.web.util.jiguang.JiguangUtil;

public class JiGuangThread {
	
	private JiGuangService jiGuangService ;
	
	protected static final Logger LOG = LoggerFactory.getLogger(JiGuangThread.class);
	
	private static String isQuery = "0";
	
	public JiGuangThread(JiGuangService jiGuangService){
		this.jiGuangService=jiGuangService;
	}
	public void xiaoXiThread() {
		Thread thread = new Thread(){
			@Override
			public void run() {
				System.out.println("启动同步线程...");
				while(true){
					try {
												
						Thread.sleep(10*1000);
						//执行要做的事
						if("0".equals(isQuery)){
							isQuery = "1";
							try{
								sendXiaoXi();
							}catch (Exception e) {
								isQuery = "0";
							}
							isQuery = "0";
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
					
				}
			}
		};
		thread.start();
	}
	
	public void sendXiaoXi(){
        List<XiaoXiFaSong> xiaoXiFaSongList = jiGuangService.chaXunWeiFaSongXiaoXi();
		PushPayload payload = null;
		Map<String,Integer> map = new HashMap<String,Integer>();
		JSONObject json = new JSONObject();
		for(XiaoXiFaSong xiaoxi : xiaoXiFaSongList){
			if(xiaoxi.getFaSongMuBiao() != null && !"".equals(xiaoxi.getFaSongMuBiao()) && xiaoxi.getXueXiaoId() != null && !"".equals(xiaoxi.getXueXiaoId())){
				json.put("dataId", xiaoxi.getShuJuId() );
				json.put("dataType", xiaoxi.getShuJuLeiXing());
				json.put("title", xiaoxi.getXiaoXiMingCheng());
				json.put("content", xiaoxi.getXiaoXiNeiRong());
				json.put("managerId", xiaoxi.getXiaoXiId());
				if("3".equals(xiaoxi.getFaSongLeiXing()+"")){//发给所有人 
					payload = JiguangUtil.buildPushObject_all_all_alert(json);
				}else if("1".equals(xiaoxi.getFaSongLeiXing()+"")){//发给某一个人
					payload = JiguangUtil.buildPushObject_all_alias1_alert(xiaoxi.getXueXiaoId()+"_"+xiaoxi.getFaSongMuBiao(),json);
				}else if("2".equals(xiaoxi.getFaSongLeiXing()+"")){//发给一个组
					payload = JiguangUtil.buildPushObject_android_IOS_tag_alertWithTitle(xiaoxi.getXueXiaoId()+"_"+xiaoxi.getFaSongMuBiao(),json);
				}
				PushResult result = null;
				try {
					result = JiguangUtil.send(payload);
					LOG.info("Got result - " + result);
					map.put("xiaoXiId", xiaoxi.getXiaoXiId());
					map.put("shiFouChengGong", 1);
					jiGuangService.gengXinXiaoXiZhuangTai(map);
				} catch (APIConnectionException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} catch (APIRequestException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}//发送
			}
		}
	}
}
