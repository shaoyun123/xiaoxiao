package com.web.websocket;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class MsgScoketHandle implements WebSocketHandler{
		/**已经连接的用户*/ 
		private static final ArrayList<WebSocketSession> users; 
		static { //保存当前连接用户 
			users = new ArrayList<WebSocketSession>(); 
		} 
		/**
	     * 建立链接
	     * @param webSocketSession
	     * @throws Exception
	     */ 
		@Override 
		public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception { //将用户信息添加到list中 
			users.add(webSocketSession); 
			System.out.println("=====================建立连接成功=========================="); 
			/*User user = (User) webSocketSession.getAttributes().get("user"); 
			if(user != null){ 
				System.out.println("当前连接用户======"+user.getName()); 
			} */
			System.out.println("webSocket连接数量====="+users.size()); 
		}
		/**
	     * 接收消息
	     * @param webSocketSession
	     * @param webSocketMessage
	     * @throws Exception
	     */ 
		@Override 
		public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception { 
			/*User user = (User) webSocketSession.getAttributes().get("user"); 
			System.out.println("收到用户:"+user.getName()+"的消息"); 
			System.out.println(webSocketMessage.getPayload().toString());*/ 
			System.out.println("==========================================="); 
		} 
		/**
	     * 异常处理
	     * @param webSocketSession
	     * @param throwable
	     * @throws Exception
	     */ 
		@Override 
		public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable){ 
			if (webSocketSession.isOpen()){ //关闭session 
				try { 
					webSocketSession.close(); 
				} catch (IOException e) { 
					  //移除用户
					users.remove(webSocketSession); 
				}
			}
		}
		/**
	     * 断开链接
	     * @param webSocketSession
	     * @param closeStatus
	     * @throws Exception
	     */ 
		@Override 
		public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception { 
			users.remove(webSocketSession); 
			/*User user = (User) webSocketSession.getAttributes().get("user"); */
			System.out.println("断开连接"); 
		} 
		@Override public boolean supportsPartialMessages() { 
			return false; 
		} 
		/**
	     * 发送消息给指定的用户
	     * @param user
	     * @param messageInfo
	     */ 
		/*public void sendMessageToUser(User user, TextMessage messageInfo){ 
			for (WebSocketSession session : users) { 
				User sessionUser = (User) session.getAttributes().get("user"); //根据用户名去判断用户接收消息的用户 
				if(user.getName().equals(sessionUser.getName())){ 
					try { 
						if (session.isOpen()){ 
							session.sendMessage(messageInfo); 
							System.out.println("发送消息给："+user.getName()+"内容："+messageInfo); 
						} 
						break; 
					} catch (IOException e) { 
						e.printStackTrace(); 
					} 
				} 
			} 
		}*/
}
