package com.web.model;

import java.util.List;
import java.util.Map;

public class KeCheng {
    private Integer id;
    
    private Integer kechengid;
    
    private String renkelaoshiid;
    
    private String beizhuid;
    
    private String kechengbeizhu;
    
    private String jiekechengbeizhu;

    public String getRenkelaoshiid() {
		return renkelaoshiid;
	}

	public void setRenkelaoshiid(String renkelaoshiid) {
		this.renkelaoshiid = renkelaoshiid;
	}

	private Integer kechenghao;

    private String kechengmingcheng;

    private String tianjiarenleixing;
    
    private String kaifangyuanxi;

    private String tianjiarenid;

    private String renkejiaoshi;

    private Integer kaishizhou;

    private Integer jieshuzhou;

    private Integer danshuangzhou;
    
    private String danshuangzhoushuoming;

	private Integer zhouci;

    private Integer kaishijieci;

    private Integer jieshujieci;

    private String canyuren;
    
    private String banjiids;
    
    private String xiaoqu;

    private String shangkejiaoshi;

    private String xuenian;

    private Integer xueqi;
    
    private String shangkeriqi;
    
    private String kaishishijian;
    
    private String jieshushijian;
    
    private List<Map<String, String>> maps;
    
    private Integer isadd;
    
    private String xiaoquming;
    
    private String jiaoshiming;
    
    private Integer sumcanyuren;
    
    private String tianjiaren;
    
    private String mianxiuids;
    
    private String xuanxiuids;
    
    private String keDaiBiao;
    
    private String jiaoXueLouMing;
    
    private 	String banJiMingCheng;
    
    private List<String> tiaokes;
    
    private List<Map<String,String>> jiakes;
    
    private int leixing;
    
    private int xueqiid;
    
    private int shijiankechengid;
    
    
	public int getLeixing() {
		return leixing;
	}
	
	public String getBeizhuid() {
		return beizhuid;
	}

	public void setBeizhuid(String beizhuid) {
		this.beizhuid = beizhuid;
	}

	public void setLeixing(int leixing) {
		this.leixing = leixing;
	}

	public List<String> getTiaokes() {
		return tiaokes;
	}

	public void setTiaokes(List<String> tiaokes) {
		this.tiaokes = tiaokes;
	}


	public List<Map<String, String>> getJiakes() {
		return jiakes;
	}

	public void setJiakes(List<Map<String, String>> jiakes) {
		this.jiakes = jiakes;
	}

	public String getTianjiaren() {
		return tianjiaren;
	}

	public void setTianjiaren(String tianjiaren) {
		this.tianjiaren = tianjiaren;
	}

	public String getBanjiids() {
		return banjiids;
	}

	public void setBanjiids(String banjiids) {
		this.banjiids = banjiids;
	}

	public Integer getSumcanyuren() {
		return sumcanyuren;
	}

	public void setSumcanyuren(Integer sumcanyuren) {
		this.sumcanyuren = sumcanyuren;
	}

	public Integer getKechengid() {
		return kechengid;
	}

	public void setKechengid(Integer kechengid) {
		this.kechengid = kechengid;
	}

	public String getXiaoquming() {
		return xiaoquming;
	}
	
	public String getKechengbeizhu() {
		return kechengbeizhu;
	}

	public void setKechengbeizhu(String kechengbeizhu) {
		this.kechengbeizhu = kechengbeizhu;
	}

	public String getJiekechengbeizhu() {
		return jiekechengbeizhu;
	}

	public void setJiekechengbeizhu(String jiekechengbeizhu) {
		this.jiekechengbeizhu = jiekechengbeizhu;
	}

	public void setXiaoquming(String xiaoquming) {
		this.xiaoquming = xiaoquming;
	}

	public String getJiaoshiming() {
		return jiaoshiming;
	}

	public void setJiaoshiming(String jiaoshiming) {
		this.jiaoshiming = jiaoshiming;
	}

	public Integer getIsadd() {
		return isadd;
	}

	public void setIsadd(Integer isadd) {
		this.isadd = isadd;
	}

	public String getKaifangyuanxi() {
		return kaifangyuanxi;
	}

	public void setKaifangyuanxi(String kaifangyuanxi) {
		this.kaifangyuanxi = kaifangyuanxi;
	}

	public List<Map<String, String>> getMaps() {
		return maps;
	}

	public void setMaps(List<Map<String, String>> maps) {
		this.maps = maps;
	}

	public String getKaishishijian() {
		return kaishishijian;
	}

	public void setKaishishijian(String kaishishijian) {
		this.kaishishijian = kaishishijian;
	}

	public String getJieshushijian() {
		return jieshushijian;
	}

	public void setJieshushijian(String jieshushijian) {
		this.jieshushijian = jieshushijian;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKechenghao() {
        return kechenghao;
    }

    public void setKechenghao(Integer kechenghao) {
        this.kechenghao = kechenghao;
    }

    public String getKechengmingcheng() {
        return kechengmingcheng;
    }

    public void setKechengmingcheng(String kechengmingcheng) {
        this.kechengmingcheng = kechengmingcheng == null ? null : kechengmingcheng.trim();
    }

    public String getTianjiarenleixing() {
        return tianjiarenleixing;
    }

    public void setTianjiarenleixing(String tianjiarenleixing) {
        this.tianjiarenleixing = tianjiarenleixing == null ? null : tianjiarenleixing.trim();
    }

    public String getTianjiarenid() {
        return tianjiarenid;
    }

    public void setTianjiarenid(String tianjiarenid) {
        this.tianjiarenid = tianjiarenid == null ? null : tianjiarenid.trim();
    }

    public String getRenkejiaoshi() {
        return renkejiaoshi;
    }

    public void setRenkejiaoshi(String renkejiaoshi) {
        this.renkejiaoshi = renkejiaoshi == null ? null : renkejiaoshi.trim();
    }

    public Integer getKaishizhou() {
        return kaishizhou;
    }

    public void setKaishizhou(Integer kaishizhou) {
        this.kaishizhou = kaishizhou;
    }

    public Integer getJieshuzhou() {
        return jieshuzhou;
    }

    public void setJieshuzhou(Integer jieshuzhou) {
        this.jieshuzhou = jieshuzhou;
    }

    public Integer getDanshuangzhou() {
        return danshuangzhou;
    }

    public void setDanshuangzhou(Integer danshuangzhou) {
        this.danshuangzhou = danshuangzhou;
    }
    
    public String getDanshuangzhoushuoming() {
		return danshuangzhoushuoming;
	}

	public void setDanshuangzhoushuoming(String danshuangzhoushuoming) {
		this.danshuangzhoushuoming = danshuangzhoushuoming;
	}

    public Integer getZhouci() {
        return zhouci;
    }

    public void setZhouci(Integer zhouci) {
        this.zhouci = zhouci;
    }

    public Integer getKaishijieci() {
        return kaishijieci;
    }

    public void setKaishijieci(Integer kaishijieci) {
        this.kaishijieci = kaishijieci;
    }

    public Integer getJieshujieci() {
        return jieshujieci;
    }

    public void setJieshujieci(Integer jieshujieci) {
        this.jieshujieci = jieshujieci;
    }

    public String getCanyuren() {
		return canyuren;
	}

	public void setCanyuren(String canyuren) {
		this.canyuren = canyuren;
	}

	public String getXiaoqu() {
        return xiaoqu;
    }

    public void setXiaoqu(String xiaoqu) {
        this.xiaoqu = xiaoqu == null ? null : xiaoqu.trim();
    }

    public String getShangkejiaoshi() {
        return shangkejiaoshi;
    }

    public void setShangkejiaoshi(String shangkejiaoshi) {
        this.shangkejiaoshi = shangkejiaoshi == null ? null : shangkejiaoshi.trim();
    }

    public String getXuenian() {
        return xuenian;
    }

    public void setXuenian(String xuenian) {
        this.xuenian = xuenian == null ? null : xuenian.trim();
    }

    public int getXueqiid() {
		return xueqiid;
	}

	public void setXueqiid(int xueqiid) {
		this.xueqiid = xueqiid;
	}

	public Integer getXueqi() {
        return xueqi;
    }

    public void setXueqi(Integer xueqi) {
        this.xueqi = xueqi;
    }
    
    public String getShangkeriqi() {
		return shangkeriqi;
	}

	public void setShangkeriqi(String shangkeriqi) {
		this.shangkeriqi = shangkeriqi;
	}

	public String getMianxiuids() {
		return mianxiuids;
	}

	public void setMianxiuids(String mianxiuids) {
		this.mianxiuids = mianxiuids;
	}

	public String getXuanxiuids() {
		return xuanxiuids;
	}

	public void setXuanxiuids(String xuanxiuids) {
		this.xuanxiuids = xuanxiuids;
	}
	
	public String getKeDaiBiao() {
		return keDaiBiao;
	}

	public void setKeDaiBiao(String keDaiBiao) {
		this.keDaiBiao = keDaiBiao;
	}
	
	public String getJiaoXueLouMing() {
		return jiaoXueLouMing;
	}

	public void setJiaoXueLouMing(String jiaoXueLouMing) {
		this.jiaoXueLouMing = jiaoXueLouMing;
	}
	
	public String getBanJiMingCheng() {
		return banJiMingCheng;
	}

	public void setBanJiMingCheng(String banJiMingCheng) {
		this.banJiMingCheng = banJiMingCheng;
	}

	public int getShijiankechengid() {
		return shijiankechengid;
	}

	public void setShijiankechengid(int shijiankechengid) {
		this.shijiankechengid = shijiankechengid;
	}
	
	
}