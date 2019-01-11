package com.web.util.jiguang;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JiguangUtil {
	protected static final Logger LOG = LoggerFactory.getLogger(JiguangUtil.class);
	private static final String appKey ="4bc486cead975362176ad248"; // 校校
	//private static final String appKey ="1fd1d5125c5beaec2d1186e5"; // 测试
	private static final String masterSecret = "51adde7467994606e48df62a";// 校校
	//private static final String masterSecret = "260ee606a13595cf1f161df8 "; // 测试
	private static final ClientConfig clientConfig = ClientConfig.getInstance();
	private static final JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
	public static void main(String args[]){
		ClientConfig clientConfig = ClientConfig.getInstance();
		// TODO Auto-generated method stub
		//JPushClient jpushClient = new JPushClient("260ee606a13595cf1f161df8", "1fd1d5125c5beaec2d1186e5", null, clientConfig);
		PushPayload payload = null;
		PushPayload payload1 = null;
		PushPayload payload2 = null;
//		if(itype==0){
			JSONObject json = new JSONObject();
			json.put("dataId", "2537");
			json.put("dataType","4");
			json.put("title", "关于征集北京化工大学校史（博物）馆馆藏资料和实物的通知");
			json.put("content", "http://news.buct.edu.cn/xxgg/40247.htm");
			json.put("managerId", "176");
			// payload = buildPushObject_all_all_alert("新消息通知");//发送对象
			 //payload = buildPushObject_android_IOS_tag_alertWithTitle("test",json);
			 payload = buildPushObject_all_alias1_alert("10010_5125",json);
			// payload = buildPushObject_android_IOS_tag_alertWithTitle("xuesheng","标签广播");
//		}else if(iuser != null){                             
//			 payload = buildPushObject_android_tag_alertWithTitle(1,"标签广播");//发送对象
//			 payload = buildPushObject_all_alias1_alert(408,"一个人通知");//发送对象
			 
//		}else if(itype>0 && iuser == null){
//			 payload = buildPushObject_all_all_alert("所有人系统自动推送");//发送对象
//		payload1 = buildPushObject_android_IOS_tag_alertWithTitle(1,"给tag1发消");
		//payload2 = buildPushObject_all_alias1_alert(590,"您有一个新的派工单需要处理，派工单号：pgd2016110037，请及时处理。");//发送对象
			
//		}
		 PushResult result;
		try {
			result = jpushClient.sendPush(payload);//发送
//			result = jpushClient.sendPush(payload1);//发送
//			result = jpushClient.sendPush(payload2);//发送
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static PushResult send(PushPayload payload) throws APIConnectionException, APIRequestException{
		
		return jpushClient.sendPush(payload);//发送
	}
	
	public static PushPayload buildPushObject_android_IOS_tag_alertWithTitle(String type,JSONObject json) {
//		PushPayload payload = PushPayload.newBuilder()
//                .setPlatform(Platform.android_ios())
//                .setAudience(Audience.tag(type))
//                .setNotification(Notification.newBuilder()
//                        .setAlert(ctx)
//                        .addPlatformNotification(AndroidNotification.newBuilder()
//                                .addExtra("booleanExtra", false)
//                                .addExtra("numberExtra", 1)
//                                .build())
//                        .addPlatformNotification(IosNotification.newBuilder().setAlert(ctx).setBadge(5)
//                				.setSound("happy").addExtra("from", "JPush").build()
//                        		
//                        		).build()).setMessage(Message.content(""))
//                              .setOptions(Options.newBuilder()
//                              .setApnsProduction(true)
//                              .build())
//                        .build();

		 PushPayload payload = PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.tag(type))
	                .setNotification(Notification.newBuilder()
	                        .setAlert(json.get("content"))
	                        .addPlatformNotification(AndroidNotification.newBuilder()
	                                .addExtra("dataId", json.getString("dataId"))
	                                .addExtra("dataType", json.getString("dataType"))
	                                .addExtra("title", json.getString("title"))
	                                .addExtra("content", json.getString("content"))
	                                .addExtra("managerId", json.getString("managerId")).setTitle(json.getString("title"))
	                                .build())
	                        .addPlatformNotification(IosNotification.newBuilder().setAlert(json.get("content"))
	                				.setSound("happy")
	                                .addExtra("dataId", json.getString("dataId"))
	                                .addExtra("dataType", json.getString("dataType"))
	                                .addExtra("title", json.getString("title"))
	                                .addExtra("content", json.getString("content"))
	                                .addExtra("managerId", json.getString("managerId"))
	                                .build()
	                        		
	                        		).build())
	                        		.setMessage(Message.newBuilder()
	            	                        .setMsgContent(json.getString("content"))
	            	                        .setTitle(json.getString("title"))
	    	                                .addExtra("dataId", json.getString("dataId"))
	    	                                .addExtra("dataType", json.getString("dataType"))
	    	                                .addExtra("title", json.getString("title"))
	    	                                .addExtra("content", json.getString("content"))
	    	                                .addExtra("managerId", json.getString("managerId"))
	            	                        .build())
	                              .setOptions(Options.newBuilder()
	                              .setApnsProduction(true)
	                              .build())
	                        .build();
        return payload;
	}


	public static PushPayload buildPushObject_all_alias1_alert(String userId,JSONObject json) {
		
//		 PushPayload payload = PushPayload.newBuilder()
//	                .setPlatform(Platform.android_ios())
//	                .setAudience(Audience.alias(userId))
//	                .setNotification(Notification.newBuilder()
//	                        .setAlert(ctx)
//	                        .addPlatformNotification(AndroidNotification.newBuilder()
//	                                .addExtra("booleanExtra", false)
//	                                .addExtra("numberExtra", 1)
//	                                .build())
//	                        .addPlatformNotification(IosNotification.newBuilder().setAlert(ctx).setBadge(5)
//	                				.setSound("happy").addExtra("from", "JPush").build()
//	                        		
//	                        		).build()).setMessage(Message.content(""))
//	                              .setOptions(Options.newBuilder()
//	                              .setApnsProduction(true)
//	                              .build())
//	                        .build();
		 PushPayload payload = PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.alias(userId))
	                .setNotification(Notification.newBuilder()
	                        .setAlert(json.get("content"))
	                        .addPlatformNotification(AndroidNotification.newBuilder()
	                                .addExtra("dataId", json.getString("dataId"))
	                                .addExtra("dataType", json.getString("dataType"))
	                                .addExtra("title", json.getString("title"))
	                                .addExtra("content", json.getString("content"))
	                                .addExtra("managerId", json.getString("managerId")).setTitle(json.getString("title"))
	                                .build())
	                        .addPlatformNotification(IosNotification.newBuilder().setAlert(json.get("content"))
	                				.setSound("happy")
	                                .addExtra("dataId", json.getString("dataId"))
	                                .addExtra("dataType", json.getString("dataType"))
	                                .addExtra("title", json.getString("title"))
	                                .addExtra("content", json.getString("content"))
	                                .addExtra("managerId", json.getString("managerId"))
	                                .build()
	                        		
	                        		).build())
	                        		.setMessage(Message.newBuilder()
	            	                        .setMsgContent(json.getString("content"))
	            	                        .setTitle(json.getString("title"))
	    	                                .addExtra("dataId", json.getString("dataId"))
	    	                                .addExtra("dataType", json.getString("dataType"))
	    	                                .addExtra("title", json.getString("title"))
	    	                                .addExtra("content", json.getString("content"))
	    	                                .addExtra("managerId", json.getString("managerId"))
	            	                        .build())
	                              .setOptions(Options.newBuilder()
	                              .setApnsProduction(true)
	                              .build())
	                        .build();
	        return payload;
	}


	public static PushPayload buildPushObject_all_all_alert(JSONObject json) {
//		 PushPayload payload = PushPayload.newBuilder()
//	                .setPlatform(Platform.android_ios())
//	                .setAudience(Audience.all())
//	                .setNotification(Notification.newBuilder()
//	                        .setAlert(ctx)
//	                        .addPlatformNotification(AndroidNotification.newBuilder()
//	                                .addExtra("booleanExtra", false)
//	                                .addExtra("numberExtra", 1)
//	                                .build())
//	                        .addPlatformNotification(IosNotification.newBuilder().setAlert(ctx).setBadge(5)
//	                				.setSound("happy").addExtra("from", "JPush").build()
//	                        		
//	                        		).build()).setMessage(Message.content(""))
//	                              .setOptions(Options.newBuilder()
//	                              .setApnsProduction(true)
//	                              .build())
//	                        .build();
		 
		 PushPayload payload = PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.all())
	                .setNotification(Notification.newBuilder()
	                        .setAlert(json.get("content"))
	                        .addPlatformNotification(AndroidNotification.newBuilder()
	                                .addExtra("dataId", json.getString("dataId"))
	                                .addExtra("dataType", json.getString("dataType"))
	                                .addExtra("title", json.getString("title"))
	                                .addExtra("content", json.getString("content"))
	                                .addExtra("managerId", json.getString("managerId")).setTitle(json.getString("title"))
	                                .build())
	                        .addPlatformNotification(IosNotification.newBuilder().setAlert(json.get("content"))
	                				.setSound("happy")
	                                .addExtra("dataId", json.getString("dataId"))
	                                .addExtra("dataType", json.getString("dataType"))
	                                .addExtra("title", json.getString("title"))
	                                .addExtra("content", json.getString("content"))
	                                .addExtra("managerId", json.getString("managerId"))
	                				.build()
	                        		
	                        		).build())
	                        		.setMessage(Message.newBuilder()
	            	                        .setMsgContent(json.getString("content"))
	            	                        .setTitle(json.getString("title"))
	    	                                .addExtra("dataId", json.getString("dataId"))
	    	                                .addExtra("dataType", json.getString("dataType"))
	    	                                .addExtra("title", json.getString("title"))
	    	                                .addExtra("content", json.getString("content"))
	    	                                .addExtra("managerId", json.getString("managerId"))
	            	                        .build())
	                              .setOptions(Options.newBuilder()
	                              .setApnsProduction(true)
	                              .build())
	                        .build();
	        return payload;
	}
}
