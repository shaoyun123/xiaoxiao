package com.web.model;

public class BeiZhu {
	private int id;
	
	private int yinyongid;
	
	private int leixing;
	
	private String tiaojian;
	
	private String beizhuneirong;
	
	private int beizhurenid;
	
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getLeixing() {
		return leixing;
	}

	public void setLeixing(int leixing) {
		this.leixing = leixing;
	}

	public String getBeizhuneirong() {
		return beizhuneirong;
	}

	public void setBeizhuneirong(String beizhuneirong) {
		this.beizhuneirong = beizhuneirong;
	}

	public int getBeizhurenid() {
		return beizhurenid;
	}

	public void setBeizhurenid(int brizhurenid) {
		this.beizhurenid = brizhurenid;
	}

	public int getYinyongid() {
		return yinyongid;
	}

	public void setYinyongid(int yinyongid) {
		this.yinyongid = yinyongid;
	}

	public String getTiaojian() {
		return tiaojian;
	}

	public void setTiaojian(String tiaojian) {
		this.tiaojian = tiaojian;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
