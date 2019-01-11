package com.web.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SheTuanJiBenXinXi {
    private Integer shetuanid;

    private Integer xuexiaoid;

    private String mingcheng;

    private Integer xingji;

    private String shetuanjieshaourl;

    private String shetuanjieshao;

    private String chuanjianren;
    
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

    public Integer getShetuanid() {
        return shetuanid;
    }

    public void setShetuanid(Integer shetuanid) {
        this.shetuanid = shetuanid;
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

    public Integer getXingji() {
        return xingji;
    }

    public void setXingji(Integer xingji) {
        this.xingji = xingji;
    }

    public String getShetuanjieshaourl() {
        return shetuanjieshaourl;
    }

    public void setShetuanjieshaourl(String shetuanjieshaourl) {
        this.shetuanjieshaourl = shetuanjieshaourl == null ? null : shetuanjieshaourl.trim();
    }

    public String getShetuanjieshao() {
        return shetuanjieshao;
    }

    public void setShetuanjieshao(String shetuanjieshao) {
        this.shetuanjieshao = shetuanjieshao == null ? null : shetuanjieshao.trim();
    }

    public String getChuanjianren() {
        return chuanjianren;
    }

    public void setChuanjianren(String chuanjianren) {
        this.chuanjianren = chuanjianren;
    }

    public Boolean getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(Boolean zhuangtai) {
        this.zhuangtai = zhuangtai;
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
}