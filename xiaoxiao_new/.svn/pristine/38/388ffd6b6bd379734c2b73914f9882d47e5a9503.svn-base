package com.web.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SheTuanJingFei {
    private Integer jinfeiid;

    private Integer xueshengzuzhixinxiid;

    private Integer shetuanxinxiid;

    private Integer jine;

    private String yongtu;

    private Integer shenqingren;

    private Date shenqingshijian;

    private Integer shifoupizhun;

    private Integer pizhunren;

    private Date pizhunshijian;
    
    private String mingcheng;

    public String getMingcheng() {
		return mingcheng;
	}

	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}

	public Integer getJinfeiid() {
        return jinfeiid;
    }

    public void setJinfeiid(Integer jinfeiid) {
        this.jinfeiid = jinfeiid;
    }

    public Integer getXueshengzuzhixinxiid() {
        return xueshengzuzhixinxiid;
    }

    public void setXueshengzuzhixinxiid(Integer xueshengzuzhixinxiid) {
        this.xueshengzuzhixinxiid = xueshengzuzhixinxiid;
    }

    public Integer getShetuanxinxiid() {
        return shetuanxinxiid;
    }

    public void setShetuanxinxiid(Integer shetuanxinxiid) {
        this.shetuanxinxiid = shetuanxinxiid;
    }

    public Integer getJine() {
        return jine;
    }

    public void setJine(Integer jine) {
        this.jine = jine;
    }

    public String getYongtu() {
        return yongtu;
    }

    public void setYongtu(String yongtu) {
        this.yongtu = yongtu == null ? null : yongtu.trim();
    }

    public Integer getShenqingren() {
        return shenqingren;
    }

    public void setShenqingren(Integer shenqingren) {
        this.shenqingren = shenqingren;
    }

    public String getShenqingshijian() {
        String ret="";
        if (shenqingren!=null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	ret = format.format(shenqingshijian);
		}
    	return ret;
    }

    public void setShenqingshijian(Date string) {
        this.shenqingshijian = string;
    }

    public Integer getShifoupizhun() {
        return shifoupizhun;
    }

    public void setShifoupizhun(Integer shifoupizhun) {
        this.shifoupizhun = shifoupizhun;
    }

    public Integer getPizhunren() {
        return pizhunren;
    }

    public void setPizhunren(Integer pizhunren) {
        this.pizhunren = pizhunren;
    }

    public String getPizhunshijian() {
        String ret="";
        if (pizhunshijian!=null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			ret = format.format(pizhunshijian);
		}
        return ret;
    }

    public void setPizhunshijian(Date pizhunshijian) {
        this.pizhunshijian = pizhunshijian;
    }
}