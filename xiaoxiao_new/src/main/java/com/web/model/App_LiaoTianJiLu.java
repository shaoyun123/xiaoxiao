package com.web.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class App_LiaoTianJiLu {
	private Integer jiluid;
	
	private Integer qunid;
	
	private Integer xueshengid;
	
	private Integer jiaoshiid;
	
	private String liaotianneirong;
	
	private Date shijian;
	
	private String xingming;

	public String getLiaotianneirong() {
		return liaotianneirong;
	}

	public void setLiaotianneirong(String liaotianneirong) {
		this.liaotianneirong = liaotianneirong == null ? null : liaotianneirong.trim();
	}

	public Integer getJiaoshiid() {
		return jiaoshiid;
	}

	public void setJiaoshiid(Integer jiaoshiid) {
		this.jiaoshiid = jiaoshiid;
	}

	public Integer getXueshengid() {
		return xueshengid;
	}

	public void setXueshengid(Integer xueshengid) {
		this.xueshengid = xueshengid;
	}

	public Integer getQunid() {
		return qunid;
	}

	public void setQunid(Integer qunid) {
		this.qunid = qunid;
	}

	public Integer getJiluid() {
		return jiluid;
	}

	public void setJiluid(Integer jiluid) {
		this.jiluid = jiluid;
	}

	public String getShijian() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return sdf.format(shijian);
	}

	public void setShijian(Date shijian) {
		this.shijian = shijian;
	}

	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
}
