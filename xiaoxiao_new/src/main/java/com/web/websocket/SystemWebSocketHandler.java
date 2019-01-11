package com.web.websocket;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.web.hessian.utils.SendUtil;
import com.web.util.Tools;

public class SystemWebSocketHandler implements WebSocketHandler {
	
	private final static Logger log = Logger.getLogger(SystemWebSocketHandler.class);
	private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Integer ipNum = session.getAttributes().get("serverIpNum") == null ? null : Integer.valueOf(session.getAttributes().get("serverIpNum").toString());
		if(Tools.notEmpty(ipNum)){
			System.out.println(SendUtil.sendToAliServer(ipNum, users.size() + 1));
		}
		System.out.println("连接上服务器");
		users.add(session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		try {
			if (session.isOpen()) {
				session.close();
			}
			Integer ipNum = session.getAttributes().get("serverIpNum") == null ? null : Integer.valueOf(session.getAttributes().get("serverIpNum").toString());
			if(Tools.notEmpty(ipNum)){
				SendUtil.sendToAliServer(ipNum, users.size() -1);
			}
			users.remove(session);
		} catch (Exception e) {
			log.error("下线异常");
			log.error(e);
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.out.println("断开服务器链接");
		try {
			Integer ipNum = session.getAttributes().get("serverIpNum") == null ? null : Integer.valueOf(session.getAttributes().get("serverIpNum").toString());
			if(Tools.notEmpty(ipNum)){
				SendUtil.sendToAliServer(ipNum,  users.size() -1);
			}
			users.remove(session);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有用户发送指定信息
	 *
	 * @param message
	 */
	public void sendMessageToUsers(String test_id, TextMessage message) {
	}

	public void sendMessageToUser(WebSocketSession user, TextMessage message) {
		try {
			System.out.println(message.getPayload());
			user.sendMessage(message);
		} catch (Exception e) {
			log.error("数据发送给在线用户异常");
			log.error(e);
		}
	}
    
 
}