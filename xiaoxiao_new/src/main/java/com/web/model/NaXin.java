package com.web.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NaXin {
    private Integer naxingid;

    private Integer naxingjibenxinxiid;

    private Integer xueshengid;

    private String xingming;

    private Boolean xingbie;

    private Date chushengriqi;

    private String lianxidianhua;

    private String aihaotechang;

    private String ziwojieshao;
    
    private String baomingbumen;
    
    private Integer baomingshetuanid;

    private Integer baomingxueshengzuzhiid;

    private Integer baomingbumenid;
    
    private Integer zhuangtai;

    public String getBaomingbumen() {
		return baomingbumen;
	}

	public void setBaomingbumen(String baomingbumen) {
		this.baomingbumen = baomingbumen;
	}

    public Integer getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(Integer zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

	public Integer getNaxingid() {
        return naxingid;
    }

    public void setNaxingid(Integer naxingid) {
        this.naxingid = naxingid;
    }

    public Integer getNaxingjibenxinxiid() {
        return naxingjibenxinxiid;
    }

    public void setNaxingjibenxinxiid(Integer naxingjibenxinxiid) {
        this.naxingjibenxinxiid = naxingjibenxinxiid;
    }

    public Integer getXueshengid() {
        return xueshengid;
    }

    public void setXueshengid(Integer xueshengid) {
        this.xueshengid = xueshengid;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming == null ? null : xingming.trim();
    }

    public Boolean getXingbie() {
        return xingbie;
    }

    public void setXingbie(Boolean xingbie) {
        this.xingbie = xingbie;
    }

    public String getChushengriqi() {
        String ret="";
    	if (chushengriqi!=null) {
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			ret=format.format(chushengriqi);
		}
    	return ret;
    }

    public void setChushengriqi(Date chushengriqi) {
        this.chushengriqi = chushengriqi;
    }

    public String getLianxidianhua() {
        return lianxidianhua;
    }

    public void setLianxidianhua(String lianxidianhua) {
        this.lianxidianhua = lianxidianhua == null ? null : lianxidianhua.trim();
    }

    public String getAihaotechang() {
        return aihaotechang;
    }

    public void setAihaotechang(String aihaotechang) {
        this.aihaotechang = aihaotechang == null ? null : aihaotechang.trim();
    }

    public String getZiwojieshao() {
        return ziwojieshao;
    }

    public void setZiwojieshao(String ziwojieshao) {
        this.ziwojieshao = ziwojieshao == null ? null : ziwojieshao.trim();
    }

    public Integer getBaomingshetuanid() {
        return baomingshetuanid;
    }

    public void setBaomingshetuanid(Integer baomingshetuanid) {
        this.baomingshetuanid = baomingshetuanid;
    }

    public Integer getBaomingxueshengzuzhiid() {
        return baomingxueshengzuzhiid;
    }

    public void setBaomingxueshengzuzhiid(Integer baomingxueshengzuzhiid) {
        this.baomingxueshengzuzhiid = baomingxueshengzuzhiid;
    }

    public Integer getBaomingbumenid() {
        return baomingbumenid;
    }

    public void setBaomingbumenid(Integer baomingbumenid) {
        this.baomingbumenid = baomingbumenid;
    }
}