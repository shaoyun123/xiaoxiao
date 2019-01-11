package com.web.hessian.utils;


public class SyncUtils {

	/**
	 * 设置服务器接口的service名字
	 */
	private static final String serviceName = "/app_hessianService";

	/**
	 * 设置服务器地址信息 最终拼接成http://xxx.xxx.xxx.xxx:0000/appname/hessianService
	 * @param ipAddr 地址
	 * @param port 端口
	 * @param address app名称
	 * @return
	 */
	public static String getSyncUrl(String ipAddr, String port, String address) {
		// 返回变量
		String url = "http://" + ipAddr;
		// 端口不空加上端口
		if (port != null && !"".equals(port)) {
			url += ":" + port;
		}
		// 如果部署地址不为空加上地址
		if (address != null && !"".equals(address)) {
			url += "/" + address;
		}
		// 加上服务标识
		url += serviceName;
		return url;
	}

}
