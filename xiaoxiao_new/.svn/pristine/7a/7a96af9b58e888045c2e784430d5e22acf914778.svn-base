package com.web.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class XueShengZuZhiJiBenXinXi {
    private Integer xueshengzuzhiid;

    private Integer xuexiaoid;

    private String mingcheng;

    private String jieshaourl;

    private String jianjie;

    private String chuangjianren;

    private Date chuangjianshijian;

    private Boolean zhuangtai;
    
    private Integer naxin;
    
    private Integer baomingzhuangtai;
    
    private String zhiwu;
    
    private String zhidaojiaoshi;
    
    public String getZhidaojiaoshi() {
		return zhidaojiaoshi;
	}

	public void setZhidaojiaoshi(String zhidaojiaoshi) {
		this.zhidaojiaoshi = zhidaojiaoshi;
	}

	private List<SheTuanHuoDongXinXi> huodong;
    
    public List<SheTuanHuoDongXinXi> getHuodong() {
		return huodong;
	}

	public void setHuodong(List<SheTuanHuoDongXinXi> huodong) {
		this.huodong = huodong;
	}
    
    public String getZhiwu() {
		return zhiwu;
	}

	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}

	public Integer getBaomingzhuangtai() {
		return baomingzhuangtai;
	}

	public void setBaomingzhuangtai(Integer baomingzhuangtai) {
		this.baomingzhuangtai = baomingzhuangtai;
	}
    
    public Integer getNaxin() {
		return naxin;
	}

	public void setNaxin(Integer naxin) {
		this.naxin = naxin;
	}

    public Integer getXueshengzuzhiid() {
        return xueshengzuzhiid;
    }

    public void setXueshengzuzhiid(Integer xueshengzuzhiid) {
        this.xueshengzuzhiid = xueshengzuzhiid;
    }

    public Integer getXuexiaoid() {
        return xuexiaoid;
    }

    public void setXuexiaoid(Integer xuexiaoid) {
        this.xuexiaoid = xuexiaoid;
    }

    public String getMingcheng() {
        return mingcheng;
    }

    public void setMingcheng(String mingcheng) {
        this.mingcheng = mingcheng == null ? null : mingcheng.trim();
    }

    public String getJieshaourl() {
        return jieshaourl;
    }

    public void setJieshaourl(String jieshaourl) {
        this.jieshaourl = jieshaourl == null ? null : jieshaourl.trim();
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie == null ? null : jianjie.trim();
    }

    public String getChuangjianren() {
        return chuangjianren;
    }

    public void setChuangjianren(String chuangjianren) {
        this.chuangjianren = chuangjianren == null ? null : chuangjianren.trim();
    }

    public String getChuangjianshijian() {
    	String ret = "";
    	if (chuangjianshijian!=null) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		ret=sdf.format(chuangjianshijian);
		}
    	return ret;
    }

    public void setChuangjianshijian(Date chuangjianshijian) {
        this.chuangjianshijian = chuangjianshijian;
    }

    public Boolean getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(Boolean zhuangtai) {
        this.zhuangtai = zhuangtai;
    }
}