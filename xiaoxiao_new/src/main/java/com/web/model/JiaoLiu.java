package com.web.model;

import java.io.Serializable;
import java.util.List;

public class JiaoLiu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String mingCheng;
	
	private String imgPath;
	
	private String pingBi;
	
	private Integer mianDaRao;
	
	private String rongId;
	
	private String pingBiZhangHu;
	
	private String rongCount;
	
	private String leiXing;
	
	private Integer ruxuenianfenid;
	
	private List<String> banjimingcheng;
	
	private Integer xueqi;
	
	private Integer xuenian;
	

	public Integer getRuxuenianfenid() {
		return ruxuenianfenid;
	}

	public void setRuxuenianfenid(Integer ruxuenianfenid) {
		this.ruxuenianfenid = ruxuenianfenid;
	}

	public String getLeiXing() {
		return leiXing;
	}

	public void setLeiXing(String leiXing) {
		this.leiXing = leiXing;
	}

	public String getRongCount() {
		return rongCount;
	}

	public void setRongCount(String rongCount) {
		this.rongCount = rongCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMingCheng() {
		return mingCheng;
	}

	public void setMingCheng(String mingCheng) {
		this.mingCheng = mingCheng;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getPingBi() {
		return pingBi;
	}

	public void setPingBi(String pingBi) {
		this.pingBi = pingBi;
	}

	public Integer getMianDaRao() {
		return mianDaRao;
	}

	public void setMianDaRao(Integer mianDaRao) {
		this.mianDaRao = mianDaRao;
	}

	public String getRongId() {
		return rongId;
	}

	public void setRongId(String rongId) {
		this.rongId = rongId;
	}

	public String getPingBiZhangHu() {
		return pingBiZhangHu;
	}

	public void setPingBiZhangHu(String pingBiZhangHu) {
		this.pingBiZhangHu = pingBiZhangHu;
	}

	public List<String> getBanjimingcheng() {
		return banjimingcheng;
	}

	public void setBanjimingcheng(List<String> banjimingcheng) {
		this.banjimingcheng = banjimingcheng;
	}

	public Integer getXueqi() {
		return xueqi;
	}

	public void setXueqi(Integer xueqi) {
		this.xueqi = xueqi;
	}

	public Integer getXuenian() {
		return xuenian;
	}

	public void setXuenian(Integer xuenian) {
		this.xuenian = xuenian;
	}
	
	
	
	
}
