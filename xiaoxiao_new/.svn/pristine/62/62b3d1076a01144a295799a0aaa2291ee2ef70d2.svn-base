package com.web.websocket;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
@Component
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor{
	/**
	 * 握手前
	 */
	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 握手后
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest){ 
			ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request; 
			HttpSession session = servletServerHttpRequest.getServletRequest().getSession(false); 
			String serverIpNum = ((ServletServerHttpRequest) request).getServletRequest().getParameter("serverIpNum");
			attributes.put("serverIpNum", serverIpNum);
			if(session != null){ //从session中获取当前用户 
				Enumeration<String> names = session.getAttributeNames();
				while (names.hasMoreElements()) {
					String name = names.nextElement();
					attributes.put(name, session.getAttribute(name));
				}
			} 
		} 
		return true;
	}
	

}
