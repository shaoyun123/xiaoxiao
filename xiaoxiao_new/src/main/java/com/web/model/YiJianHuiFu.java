package com.web.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YiJianHuiFu {
    private Integer huifuid;

    private Integer yijianid;

    private String huifuneirong;

    private Date shijian;

    private Integer jiaoshiid;

    private Integer xueshengid;

    private Integer beihuifujiaoshiid;

    private Integer beihuifuxueshengid;
    
    private String jiaoshixingming;
    
    private String xueshengxingming;
    
    private String banjimingcheng;
    
    private String beihuifujiaoshixingming;
    
    public String getBeihuifujiaoshixingming() {
		return beihuifujiaoshixingming;
	}

	public void setBeihuifujiaoshixingming(String beihuifujiaoshixingming) {
		this.beihuifujiaoshixingming = beihuifujiaoshixingming;
	}

	public String getBeihuifuxueshengxingming() {
		return beihuifuxueshengxingming;
	}

	public void setBeihuifuxueshengxingming(String beihuifuxueshengxingming) {
		this.beihuifuxueshengxingming = beihuifuxueshengxingming;
	}

	public String getBeihuifubanjimingcheng() {
		return beihuifubanjimingcheng;
	}

	public void setBeihuifubanjimingcheng(String beihuifubanjimingcheng) {
		this.beihuifubanjimingcheng = beihuifubanjimingcheng;
	}

	private String beihuifuxueshengxingming;
    
    private String beihuifubanjimingcheng;

    public String getJiaoshixingming() {
		return jiaoshixingming;
	}

	public void setJiaoshixingming(String jiaoshixingming) {
		this.jiaoshixingming = jiaoshixingming;
	}

	public String getXueshengxingming() {
		return xueshengxingming;
	}

	public void setXueshengxingming(String xueshengxingming) {
		this.xueshengxingming = xueshengxingming;
	}

	public String getBanjimingcheng() {
		return banjimingcheng;
	}

	public void setBanjimingcheng(String banjimingcheng) {
		this.banjimingcheng = banjimingcheng;
	}

	public Integer getHuifuid() {
        return huifuid;
    }

    public void setHuifuid(Integer huifuid) {
        this.huifuid = huifuid;
    }

    public Integer getYijianid() {
        return yijianid;
    }

    public void setYijianid(Integer yijianid) {
        this.yijianid = yijianid;
    }

    public String getHuifuneirong() {
        return huifuneirong;
    }

    public void setHuifuneirong(String huifuneirong) {
        this.huifuneirong = huifuneirong == null ? null : huifuneirong.trim();
    }

    public String getShijian() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return sdf.format(shijian);
    }

    public void setShijian(Date shijian) {
        this.shijian = shijian;
    }

    public Integer getJiaoshiid() {
        return jiaoshiid;
    }

    public void setJiaoshiid(Integer jiaoshiid) {
        this.jiaoshiid = jiaoshiid;
    }

    public Integer getXueshengid() {
        return xueshengid;
    }

    public void setXueshengid(Integer xueshengid) {
        this.xueshengid = xueshengid;
    }

    public Integer getBeihuifujiaoshiid() {
        return beihuifujiaoshiid;
    }

    public void setBeihuifujiaoshiid(Integer beihuifujiaoshiid) {
        this.beihuifujiaoshiid = beihuifujiaoshiid;
    }

    public Integer getBeihuifuxueshengid() {
        return beihuifuxueshengid;
    }

    public void setBeihuifuxueshengid(Integer beihuifuxueshengid) {
        this.beihuifuxueshengid = beihuifuxueshengid;
    }
}