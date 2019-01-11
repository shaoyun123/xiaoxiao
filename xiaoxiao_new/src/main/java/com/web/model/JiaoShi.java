package com.web.model;

public class JiaoShi {
    private Integer jiaoshiid;

    private String jiaoshiming;

    private Integer jiaoxuelouid;

    private Integer xiaoquid;

    public Integer getJiaoshiid() {
        return jiaoshiid;
    }

    public void setJiaoshiid(Integer jiaoshiid) {
        this.jiaoshiid = jiaoshiid;
    }

    public String getJiaoshiming() {
        return jiaoshiming;
    }

    public void setJiaoshiming(String jiaoshiming) {
        this.jiaoshiming = jiaoshiming == null ? null : jiaoshiming.trim();
    }

    public Integer getJiaoxuelouid() {
        return jiaoxuelouid;
    }

    public void setJiaoxuelouid(Integer jiaoxuelouid) {
        this.jiaoxuelouid = jiaoxuelouid;
    }

    public Integer getXiaoquid() {
        return xiaoquid;
    }

    public void setXiaoquid(Integer xiaoquid) {
        this.xiaoquid = xiaoquid;
    }
}