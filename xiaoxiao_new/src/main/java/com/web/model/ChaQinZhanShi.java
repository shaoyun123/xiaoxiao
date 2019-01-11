package com.web.model;

import java.util.List;

public class ChaQinZhanShi {
	private Integer jieguoid;
	
	public Integer getJieguoid() {
		return jieguoid;
	}
	public void setJieguoid(Integer jieguoid) {
		this.jieguoid = jieguoid;
	}

	private Integer shifoushenhe;
	
	public Integer getShifoushenhe() {
		return shifoushenhe;
	}
	public void setShifoushenhe(Integer shifoushenhe) {
		this.shifoushenhe = shifoushenhe;
	}

	private Integer anpaiid;
	
	public Integer getAnpaiid() {
		return anpaiid;
	}
	public void setAnpaiid(Integer anpaiid) {
		this.anpaiid = anpaiid;
	}
	private Integer susheid;
	
	private String sushemingcheng;
	
	private Integer zhuangtai;
	
	private List<XueSheng> weidaoxuesheng;
	
	private List<XueSheng> qingjiaxuesheng;

	public List<XueSheng> getQingjiaxuesheng() {
		return qingjiaxuesheng;
	}
	public void setQingjiaxuesheng(List<XueSheng> qingjiaxuesheng) {
		this.qingjiaxuesheng = qingjiaxuesheng;
	}
	public Integer getSusheid() {
		return susheid;
	}
	public void setSusheid(Integer susheid) {
		this.susheid = susheid;
	}
	public String getSushemingcheng() {
		return sushemingcheng;
	}
	public void setSushemingcheng(String sushemingcheng) {
		this.sushemingcheng = sushemingcheng;
	}
	public Integer getZhuangtai() {
		return zhuangtai;
	}
	public void setZhuangtai(Integer zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	public List<XueSheng> getWeidaoxuesheng() {
		return weidaoxuesheng;
	}
	public void setWeidaoxuesheng(List<XueSheng> weidaoxuesheng) {
		this.weidaoxuesheng = weidaoxuesheng;
	}
}
