package com.web.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChaQinAnPai {
    private Integer anpaiid;

    private Integer jiaoshiid;

    private Date riqi;

    private String paizhaoyaoqiu;

    private Date kaishishijian;

    private Date jieshushijian;

    private Integer zhuangtai;
    
    private Integer jieguo;
    
    private String shangchuanren;
    
    private String queqin;
    
    private String zhaopian;

    public String getZhaopian() {
		return zhaopian;
	}

	public void setZhaopian(String zhaopian) {
		this.zhaopian = zhaopian;
	}

	public String getShangchuanren() {
		return shangchuanren;
	}

	public void setShangchuanren(String shangchuanren) {
		this.shangchuanren = shangchuanren;
	}

	public String getQueqin() {
		return queqin;
	}

	public void setQueqin(String queqin) {
		this.queqin = queqin;
	}

	public Integer getAnpaiid() {
        return anpaiid;
    }

    public void setAnpaiid(Integer anpaiid) {
        this.anpaiid = anpaiid;
    }

    public Integer getJiaoshiid() {
        return jiaoshiid;
    }

    public void setJiaoshiid(Integer jiaoshiid) {
        this.jiaoshiid = jiaoshiid;
    }

    public String getRiqi() {
    	if (riqi!=null) {
 			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
 			return format.format(riqi);
 		}else {
 			return "";
 		}
    }

    public void setRiqi(Date riqi) {
        this.riqi = riqi;
    }

    public String getPaizhaoyaoqiu() {
        return paizhaoyaoqiu;
    }

    public void setPaizhaoyaoqiu(String paizhaoyaoqiu) {
        this.paizhaoyaoqiu = paizhaoyaoqiu == null ? null : paizhaoyaoqiu.trim();
    }

    public String getKaishishijian() {
    	if (kaishishijian!=null) {
 			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
 			return format.format(kaishishijian);
 		}else {
 			return "";
 		}
    }

    public void setKaishishijian(Date kaishishijian) {
        this.kaishishijian = kaishishijian;
    }

    public String getJieshushijian() {
        if (jieshushijian!=null) {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			return format.format(jieshushijian);
		}else {
			return "";
		}
    }

    public void setJieshushijian(Date jieshushijian) {
        this.jieshushijian = jieshushijian;
    }

    public Integer getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(Integer zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

	public Integer getJieguo() {
		return jieguo;
	}

	public void setJieguo(Integer jieguo) {
		this.jieguo = jieguo;
	}
    
}