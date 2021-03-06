package com.web.hessian.utils;

import com.caucho.hessian.client.HessianProxyFactory;
import com.web.hessian.client.ClientHessian;


public class SendUtil {
	
	
	public static String sendToAliServer(Integer ipNum,Integer num){
		// 创建hessian的工厂
		HessianProxyFactory factory = new HessianProxyFactory();
		// 设置允许重载
		factory.setOverloadEnabled(true);
		factory.setConnectTimeout(5000);
		// 设置那个service 此service时本地的service 其中只需要包含有服务器要调用的接口方法即可
		ClientHessian sync = null;
		try {
			sync = (ClientHessian) factory.create(
					ClientHessian.class,SyncUtils.getSyncUrl("60.205.179.223", "80", "xiaoyuan"));
//			ClientHessian.class,SyncUtils.getSyncUrl("122.207.68.137", "8890", "xiaoyuan"));
//			ClientHessian.class,SyncUtils.getSyncUrl("192.168.0.42", "8080", "xiaoxiao"));
			System.out.println(sync.sumServerConnectByIp(ipNum, num));
			return "1";
		}catch (Exception e) {
			// TODO: handle exception
			return "0";
		}
	}
	public static String getAliConnect(){
		// 创建hessian的工厂
		HessianProxyFactory factory = new HessianProxyFactory();
		// 设置允许重载
		factory.setOverloadEnabled(true);
		factory.setConnectTimeout(5000);
		// 设置那个service 此service时本地的service 其中只需要包含有服务器要调用的接口方法即可
		ClientHessian sync = null;
		try {
			sync = (ClientHessian) factory.create(
					ClientHessian.class,SyncUtils.getSyncUrl("60.205.179.223", "80", "xiaoyuan"));
//			ClientHessian.class,SyncUtils.getSyncUrl("122.207.68.137", "8890", "xiaoyuan"));
//			ClientHessian.class,SyncUtils.getSyncUrl("192.168.0.42", "8080", "xiaoxiao"));
			System.out.println(sync.getAliConnect());
			return "1";
		}catch (Exception e) {
			// TODO: handle exception
			return "0";
		}
	}
	
	public static void main(String args[]){
		//sendToAliServer(1,20);
		//sendToAliServer(0,0);
		getAliConnect();
	}
}
