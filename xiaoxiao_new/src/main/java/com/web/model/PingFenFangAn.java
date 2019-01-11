package com.web.model;

public class PingFenFangAn {
    private Integer fanganid;

    private Integer jiaoshiid;
    
    private String jiaoshixingming;
    
    private String	fanganmingcheng;

    public String getFanganmingcheng() {
		return fanganmingcheng;
	}

	public void setFanganmingcheng(String fanganmingcheng) {
		this.fanganmingcheng = fanganmingcheng;
	}

	public String getJiaoshixingming() {
		return jiaoshixingming;
	}

	public void setJiaoshixingming(String jiaoshixingming) {
		this.jiaoshixingming = jiaoshixingming;
	}

	public Integer getFanganid() {
        return fanganid;
    }

    public void setFanganid(Integer fanganid) {
        this.fanganid = fanganid;
    }

    public Integer getJiaoshiid() {
        return jiaoshiid;
    }

    public void setJiaoshiid(Integer jiaoshiid) {
        this.jiaoshiid = jiaoshiid;
    }
}