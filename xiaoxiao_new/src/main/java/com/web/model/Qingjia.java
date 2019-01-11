package com.web.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Qingjia {
    private Integer qingjiaid;

    private Integer xueshengid;
    
    private String xueshengxingming;
    
    private String xuehao;
    
    private String banjimingcheng;
    
    private int tianshu;

	private Integer qingjialeibie;

    private String qingjiashiyou;

    private Date qingjiakaishishijian;

    private Date qingjiajieshushijian;

    private Integer tijiaocishu;

    /**
     * 1	申请
     * 2	通过
     * 3	驳回
     * 4	待销假
     * 5	已销假
     * 6	辅导员 已上报
     * 7	书记 已上报
     */
    private Integer zhuangtai;

    private String pizhunren;

    private Date shenqingshijian;

    private String bingjiazhengming;

    private Date tongzhishijian;

    private String tongzhineirong;
    
    private List<String> tupian;

	public List<String> getTupian() {
		return tupian;
	}

	public void setTupian(List<String> tupian) {
		this.tupian = tupian;
	}

	public void setTuPian(List<String> tuPian) {
		this.tupian = tuPian;
	}

	public String getBanjimingcheng() {
		return banjimingcheng;
	}

	public void setBanjimingcheng(String banjimingcheng) {
		this.banjimingcheng = banjimingcheng;
	}

	public Integer getXueshengid() {
		return xueshengid;
	}

	public void setXueshengid(Integer xueshengid) {
		this.xueshengid = xueshengid;
	}
	
    public Integer getQingjiaid() {
        return qingjiaid;
    }

    public void setQingjiaid(Integer qingjiaid) {
        this.qingjiaid = qingjiaid;
    }

    public String getXueshengxingming() {
		return xueshengxingming;
	}

	public void setXueshengxingming(String xueshengxingming) {
		this.xueshengxingming = xueshengxingming;
	}

	public String getXuehao() {
		return xuehao;
	}

	public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
	}

	public int getTianshu() {
		return tianshu;
	}

	public void setTianshu(int tianshu) {
		this.tianshu = tianshu;
	}
    public Integer getQingjialeibie() {
        return qingjialeibie;
    }

    public void setQingjialeibie(Integer qingjialeibie) {
        this.qingjialeibie = qingjialeibie;
    }

    public String getQingjiashiyou() {
        return qingjiashiyou;
    }

    public void setQingjiashiyou(String qingjiashiyou) {
        this.qingjiashiyou = qingjiashiyou == null ? null : qingjiashiyou.trim();
    }

    public String getQingjiakaishishijian() {
    	String date ="";
    	if (qingjiakaishishijian!=null) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		date = sdf.format(qingjiakaishishijian);
		}
    	return date;
    }

    public void setQingjiakaishishijian(Date qingjiakaishishijian) {
        this.qingjiakaishishijian = qingjiakaishishijian;
    }

    public String getQingjiajieshushijian() {
    	String date ="";
    	if (qingjiajieshushijian!=null) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		date = sdf.format(qingjiajieshushijian);
		}
    	return date;
    }

    public void setQingjiajieshushijian(Date qingjiajieshushijian) {
        this.qingjiajieshushijian = qingjiajieshushijian;
    }

    public Integer getTijiaocishu() {
        return tijiaocishu;
    }

    public void setTijiaocishu(Integer tijiaocishu) {
        this.tijiaocishu = tijiaocishu;
    }

    public Integer getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(Integer zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public String getPizhunren() {
        return pizhunren;
    }

    public void setPizhunren(String pizhunren) {
        this.pizhunren = pizhunren;
    }

    public String getShenqingshijian() {
    	String date = "";
    	if (shenqingshijian!=null) {
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		date = format.format(shenqingshijian);
		}
		return date;
    }

    public void setShenqingshijian(Date shenqingshijian) {
        this.shenqingshijian = shenqingshijian;
    }

    public String getBingjiazhengming() {
        return bingjiazhengming;
    }

    public void setBingjiazhengming(String bingjiazhengming) {
        this.bingjiazhengming = bingjiazhengming == null ? null : bingjiazhengming.trim();
    }

    public Date getTongzhishijian() {
		return tongzhishijian;
    }

    public void setTongzhishijian(Date tongzhishijian) {
        this.tongzhishijian = tongzhishijian;
    }

    public String getTongzhineirong() {
        return tongzhineirong;
    }

    public void setTongzhineirong(String tongzhineirong) {
        this.tongzhineirong = tongzhineirong == null ? null : tongzhineirong.trim();
    }
}