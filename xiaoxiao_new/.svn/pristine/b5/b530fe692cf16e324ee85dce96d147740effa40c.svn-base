package com.web.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SheTuanHuoDongXinXi {
    private Integer huodongid;

    private Integer shetuanid;

    private Integer xueshengzuzhiid;

    private String huodongmingcheng;

    private Date huodongshijian;

    private String huodongdidian;

    private String huodongxiangqing;

    private Integer canyurenshu;

    private String huodongliucheng;

    private String huodongjilu;
    
    private String zhaopianlujing;
    
    private String huodongxingzhi;

    private Date fabushijian;

    public String getZhaopianlujing() {
		return zhaopianlujing;
	}

	public void setZhaopianlujing(String zhaopianlujing) {
		this.zhaopianlujing = zhaopianlujing;
	}


    public Integer getHuodongid() {
        return huodongid;
    }

    public void setHuodongid(Integer huodongid) {
        this.huodongid = huodongid;
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

    public String getHuodongmingcheng() {
        return huodongmingcheng;
    }

    public void setHuodongmingcheng(String huodongmingcheng) {
        this.huodongmingcheng = huodongmingcheng == null ? null : huodongmingcheng.trim();
    }

    public String getHuodongshijian() {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	if (huodongshijian!=null) {
			return format.format(huodongshijian);
		}else {
			return "";
		}
    }

    public void setHuodongshijian(Date huodongshijian) {
        this.huodongshijian = huodongshijian;
    }

    public String getHuodongdidian() {
        return huodongdidian;
    }

    public void setHuodongdidian(String huodongdidian) {
        this.huodongdidian = huodongdidian == null ? null : huodongdidian.trim();
    }

    public String getHuodongxiangqing() {
        return huodongxiangqing;
    }

    public void setHuodongxiangqing(String huodongxiangqing) {
        this.huodongxiangqing = huodongxiangqing == null ? null : huodongxiangqing.trim();
    }

    public Integer getCanyurenshu() {
        return canyurenshu;
    }

    public void setCanyurenshu(Integer canyurenshu) {
        this.canyurenshu = canyurenshu;
    }

    public String getHuodongliucheng() {
        return huodongliucheng;
    }

    public void setHuodongliucheng(String huodongliucheng) {
        this.huodongliucheng = huodongliucheng == null ? null : huodongliucheng.trim();
    }

    public String getHuodongjilu() {
        return huodongjilu;
    }

    public void setHuodongjilu(String huodongjilu) {
        this.huodongjilu = huodongjilu == null ? null : huodongjilu.trim();
    }

    public String getHuodongxingzhi() {
        return huodongxingzhi;
    }

    public void setHuodongxingzhi(String huodongxingzhi) {
        this.huodongxingzhi = huodongxingzhi == null ? null : huodongxingzhi.trim();
    }

    public String getFabushijian() {
    	String ret = "";
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	if (fabushijian!=null) {
			return format.format(fabushijian);
		}else {
			return ret;
		}
    }

    public void setFabushijian(Date fabushijian) {
        this.fabushijian = fabushijian;
    }
}