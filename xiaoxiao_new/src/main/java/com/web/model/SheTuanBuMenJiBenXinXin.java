package com.web.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SheTuanBuMenJiBenXinXin {
    private Integer bumenid;

    private Integer shetuanid;

    private Integer xueshengzuzhiid;

    private String bumenmingcheng;

    private Date chuangjianshijian;

    private String zhuangtai;
    
    private String zhize;

    public String getZhize() {
		return zhize;
	}

	public void setZhize(String zhize) {
		this.zhize = zhize;
	}

	public Integer getBumenid() {
        return bumenid;
    }

    public void setBumenid(Integer bumenid) {
        this.bumenid = bumenid;
    }

    public Integer getShetuanid() {
        return shetuanid;
    }

    public void setShetuanid(Integer shetuanid) {
        this.shetuanid = shetuanid;
    }

    public Integer getXueshengzuzhiid() {
        return xueshengzuzhiid;
    }

    public void setXueshengzuzhiid(Integer xueshengzuzhiid) {
        this.xueshengzuzhiid = xueshengzuzhiid;
    }

    public String getBumenmingcheng() {
        return bumenmingcheng;
    }

    public void setBumenmingcheng(String bumenmingcheng) {
        this.bumenmingcheng = bumenmingcheng == null ? null : bumenmingcheng.trim();
    }

    public String getChuangjianshijian() {
    	String date ="";
    	if (chuangjianshijian!=null) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		date = sdf.format(chuangjianshijian);
		}
    	return date;
    }

    public void setChuangjianshijian(Date chuangjianshijian) {
        this.chuangjianshijian = chuangjianshijian;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai == null ? null : zhuangtai.trim();
    }
}