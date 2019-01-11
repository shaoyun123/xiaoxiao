package com.web.hessian.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.web.hessian.service.IServerHessian;
import com.web.util.Const;

public class ServerHessianImpl implements IServerHessian {

	@Override
	public String sumServerConnectByIp(Integer ipNum,Integer num) {
		// TODO Auto-generated method stub
		String serverIp = getPropByName("serverip");
		String[] serverIps = serverIp.split(",");
		Const.SERVER_CONNECT_NUM.put(serverIps[ipNum], num);
		System.out.println(serverIps[ipNum]+"当前已登录:"+(num));
		return serverIps[ipNum]+"当前已登录:"+(num);
	}
	
	private String getPropByName(String propName) {
		Properties pro = new Properties();
		InputStream inputStream = null;
		String serverUrl = "";
		try {
			String str = ServerHessianImpl.class.getClassLoader().getResource("/").getPath() + "config.properties";
			inputStream = new FileInputStream(str);
			pro.load(inputStream);
			serverUrl = (String) pro.get(propName);

		} catch (Exception e) {
			// TODO: handle exception
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return serverUrl;
	}
	
	public String getAliConnect(){
		String serverIp = getPropByName("serverip");
		String returnStr = "";
		String[] serverIps = serverIp.split(",");
		for(String str : serverIps){
			if("".equals(returnStr)){
				returnStr = str+":当前登录数:"+Const.SERVER_CONNECT_NUM.get(str);
			}else{
				returnStr += ","+str+":当前登录数:"+Const.SERVER_CONNECT_NUM.get(str);
			}
		}
		return returnStr;
	}
	
}
