package com.web.model;

public class SheTuanChuangJian {
    private Integer chuangjianid;

    private String mingcheng;
    
    private Integer xuexiaoid;

	private Boolean leixing;
    
    private String dianhua;

	private String jieshao;

    private String chuangjianliyou;

    private Integer chuangjianren;
    
    private String chuangjianrenxingming;
    
    private Integer shenheren;
    
    private String zhidaojiaoshi;

    public String getZhidaojiaoshi() {
		return zhidaojiaoshi;
	}

	public void setZhidaojiaoshi(String zhidaojiaoshi) {
		this.zhidaojiaoshi = zhidaojiaoshi;
	}

	public Integer getShenheren() {
		return shenheren;
	}

	public void setShenheren(Integer shenheren) {
		this.shenheren = shenheren;
	}

	public String getChuangjianrenxingming() {
		return chuangjianrenxingming;
	}

	public void setChuangjianrenxingming(String chuangjianrenxingming) {
		this.chuangjianrenxingming = chuangjianrenxingming;
	}

	private Integer zhuangtai;

    public Integer getChuangjianid() {
        return chuangjianid;
    }

    public void setChuangjianid(Integer chuangjianid) {
        this.chuangjianid = chuangjianid;
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

    public String getDianhua() {
		return dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}
	
    public Boolean getLeixing() {
        return leixing;
    }

    public void setLeixing(Boolean leixing) {
        this.leixing = leixing;
    }

    public String getJieshao() {
        return jieshao;
    }

    public void setJieshao(String jieshao) {
        this.jieshao = jieshao == null ? null : jieshao.trim();
    }

    public String getChuangjianliyou() {
        return chuangjianliyou;
    }

    public void setChuangjianliyou(String chuangjianliyou) {
        this.chuangjianliyou = chuangjianliyou == null ? null : chuangjianliyou.trim();
    }

    public Integer getChuangjianren() {
        return chuangjianren;
    }

    public void setChuangjianren(Integer chuangjianren) {
        this.chuangjianren = chuangjianren;
    }

    public Integer getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(Integer zhuangtai) {
        this.zhuangtai = zhuangtai;
    }
}