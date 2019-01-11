package com.web.model;

import java.util.Date;

public class FuDaoYuan {
    private Integer id;

    private Integer fudaoyuanid;

    private String banjiid;

    private Integer shujiid;

    private Boolean zhuangtai;

    private Date kaishishijian;

    private Date jieshushijian;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFudaoyuanid() {
        return fudaoyuanid;
    }

    public void setFudaoyuanid(Integer fudaoyuanid) {
        this.fudaoyuanid = fudaoyuanid;
    }

    public String getBanjiid() {
        return banjiid;
    }

    public void setBanjiid(String banjiid) {
        this.banjiid = banjiid == null ? null : banjiid.trim();
    }

    public Integer getShujiid() {
        return shujiid;
    }

    public void setShujiid(Integer shujiid) {
        this.shujiid = shujiid;
    }

    public Boolean getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(Boolean zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public Date getKaishishijian() {
        return kaishishijian;
    }

    public void setKaishishijian(Date kaishishijian) {
        this.kaishishijian = kaishishijian;
    }

    public Date getJieshushijian() {
        return jieshushijian;
    }

    public void setJieshushijian(Date jieshushijian) {
        this.jieshushijian = jieshushijian;
    }
}