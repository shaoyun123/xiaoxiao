package com.web.model;

public class JieCiFangAn {
    private Integer id;

    private Integer xuexiaoid;

    private String mingcheng;

    private Integer zhuangtai;

    private Integer qiyongguo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(Integer zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public Integer getQiyongguo() {
        return qiyongguo;
    }

    public void setQiyongguo(Integer qiyongguo) {
        this.qiyongguo = qiyongguo;
    }
}