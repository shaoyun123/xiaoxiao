package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.NaXin;
import com.web.model.NaXinJiBenXinXi;
import com.web.model.SheTuanBuMenJiBenXinXin;
import com.web.model.SheTuanBuMenXinXin;
import com.web.model.SheTuanChuangJian;
import com.web.model.SheTuanJiBenXinXi;
import com.web.model.SheTuanJieSan;
import com.web.model.SheTuanJingFei;
import com.web.model.SheTuanXinXi;
import com.web.model.XueShengZuZhiJiBenXinXi;
import com.web.model.XueShengZuZhiXinXi;

public interface SheTuanService {
	
	List<XueShengZuZhiJiBenXinXi> selectXueShengZuZhiJiBenXinXiByXueXiaoID(int xuexiaoid);
	
	XueShengZuZhiXinXi selectXueShengZuZhiXinXiByXueShengZuZhiID(int xueshengzuzhiid);
	
	XueShengZuZhiXinXi selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(int xueshengzuzhiid,String niandu);
	
	XueShengZuZhiJiBenXinXi selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndXueShengZuZhiID(Map<String, String> map);
	
	List<SheTuanJiBenXinXi> selectSheTuanJiBenXinXiByXueXiaoID(int xuexiaoid);
	
	SheTuanJiBenXinXi selectSheTuanJiBenXinXiByXueXiaoIDAndSheTuanID(Map<String, String> map);
	
	List<SheTuanJiBenXinXi> selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(Map<String, String> map);
	
	List<XueShengZuZhiJiBenXinXi> selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(Map<String, String> map);
	
	SheTuanXinXi selectSheTuanXinXiBySheTuanID(int shetuanid);
	
	SheTuanXinXi selectSheTuanXinXiBySheTuanIDAndNianDu(int shetuanid,String niandu);
	
	List<SheTuanBuMenJiBenXinXin> selectSheTuanBuMenJiBenXinXiBySheTuanID(int shetuanid);
	
	List<SheTuanBuMenJiBenXinXin> selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(int xueshengzuzhiid);
	
	SheTuanBuMenXinXin selectSheTuanBuMenByBuMenIDAndNianDu(int bumenid,String niandu);
	
	SheTuanBuMenXinXin selectSheTuanBuMenXinXiBySheTuanBuMenXinXiID(int shetuanbumenxinxiid);
	
	NaXinJiBenXinXi selectNaXinJiBenXinXiBySheTuanXinXiID(int shetuanxinxiid);
	
	NaXinJiBenXinXi selectNaXinJiBenXinXiByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid);
	
	int insertNaXin(NaXin record);
	
	NaXin selectNaXinByXueShengIDAndSheTuanXinXiID(int xueshengid,int shetuanxinxiid);
	
	NaXin selectNaXinByXueShengIDAndXueShengZuZhiXinXiID(int xueshengid,int xueshengzuzhixinxiid);
	
	NaXin selectNaXinByNaXinID(int naxinid);
	
	int updateNaXinZhuangTaiByNaXinID(int zhuangtai,int naxinid);
	
	List<NaXin> selectNaXinBySheTuanXinXiID(int shetuanxinxiid);
	
	List<NaXin> selectNaXinByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid);
	
	SheTuanXinXi selectSheTuanXinXiBySheTuanXinXiIDAndXueShengID(int shetuanxinxiid,String sheyuanid1,String sheyuanid2);
	
	XueShengZuZhiXinXi	selectXueShengZuZhiXinXiByIDAndXueShengID(int xueshengzuzhixinxiid,String sheyuanid1,String sheyuanid2);
	
	List<SheTuanXinXi> selectSheTuanXinXiByXueShengIDAndNianDuAndXueXiaoID(String sheyuanid1,String sheyuanid2,String niandu,int xuexiaoid);
	
	List<XueShengZuZhiXinXi> selectXueShengZuZhiXinXiByXueShengIDAndNianDu(String renyuanid1,String renyuanid2,String niandu);
	
	XueShengZuZhiJiBenXinXi selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(int xueshengzuzhiid);
	
	SheTuanJiBenXinXi selectSheTuanJiBenXinXiBySheTuanID(int shetuanid);
	
	SheTuanChuangJian selectSheTuanChuangJianByXueXiaoIDAndMingCheng(int xuexiaoid,String mingcheng);
	
	SheTuanChuangJian selectByID(int id);
	
	List<SheTuanChuangJian> selectSheTuanChuangJianByBanJiIDs(String banjiids);
	
	SheTuanJiBenXinXi selectSheTuanJiBenXinXiByXueXiaoIDAndMingCheng(int xuexiaoid,String mingcheng);
	
	int insertSheTuanChuangJian(SheTuanChuangJian sheTuanChuangJian);
	
	List<SheTuanChuangJian> selectSheTuanChuangJianByXueShengID(int xueshengid);
	
	SheTuanXinXi selectSheTuanXinXiBySheTuanXinXiIDAndSheZhangID(int shetuanxinxiid,int shezhang);
	
	SheTuanXinXi selectSheTuanXinXiBySheTuanXinXiID(int shetuanxinxiid);
	
	int insertSheTuanJingFeiShenQing(SheTuanJingFei record);
	
	List<SheTuanJingFei> selectBySheTuanXinXiID(int shetuanxinxiid);
	
	List<SheTuanJingFei> selectSheTuanJinFeiByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid);
	
	XueShengZuZhiXinXi selectByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid);
	
	SheTuanBuMenXinXin selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(int bumenid,String niandu,String renyuanid_1,String renyuanid_2);

	int updateSheZhangBySheTuanXinXiID(int shetuanxinxiid,int shezhangid);
	
	SheTuanBuMenJiBenXinXin selectSheTuanBuMenJiBenXinXiBySheTuanBuMenID(int shetuanbumenid);
	
	int updateSheTuanBuMenXinXiBySheTuanBuMenXinXi(SheTuanBuMenXinXin record);
	
	List<SheTuanBuMenXinXin> selectSheTuanBuMenXinXiByXueShengIDAndNianDu(String xueshengid1,String xueshengid2,String niandu);
	
	SheTuanBuMenJiBenXinXin selectSheTuanBuMenJiBenXinXiBySheTuanIDAndMingCheng(int shetuanid,String mingcheng);
	
	SheTuanBuMenJiBenXinXin selectSheTuanBuMenJiBenXinXiByXueShengZuZhiIDAndMingCheng(int xueshengzuzhiid,String mingcheng);
	
	int insertSheTuanBuMenJiBenXinXi(SheTuanBuMenJiBenXinXin record);
	
	int insertSheTuanBuMenXinXi(SheTuanBuMenXinXin record);
	
	int updateSheTuanXinXi(SheTuanXinXi record);
	
	int updateSheTuanRenYuanBySheTuanXinXiID(int shetuanxinxiid,String renyuan);
	
	int updateSheTuanBuMenRenYuanByBuMenXinXiID(int bumenxinxiid,String renyuan);
	
	int updateSheTuanBuMenJiBenXinXiZhuangTaiByBuMenID(int bumenid);
	
	int updateFuZeRenByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid,int fuzerenid);
	
	int updateXueShengZuZhiXinXi(XueShengZuZhiXinXi xi);
	
	int updateSheTuanChuangJianBySheTuanChuangJian(SheTuanChuangJian record);
	
	int insertSheTuanJieSan(SheTuanJieSan record);
	
	SheTuanJieSan selectSheTuanJieSanBySheTuanID(int shetuanid);
	
	List<SheTuanChuangJian> selectSheTuanChuangJianByXueXiaoID(int xuexiaoid);
	
	Boolean tongYiChuangJianSheTuan(SheTuanChuangJian record);
	
	Boolean tongYiChuangJianXueShengZuZhi(SheTuanChuangJian record);
	
	int insertSheTuanJiBenXinXi(SheTuanJiBenXinXi record);
	
	int insertXueShengZuZhiJiBenXinXi(XueShengZuZhiJiBenXinXi record);
	
	int insertNaXinJiBenXinXi(NaXinJiBenXinXi record);
	
	int updateNaXinJiBenXinXi(NaXinJiBenXinXi record);
	
	List<SheTuanJingFei> selectByXueXiaoID(int xuexiaoid);
	
	SheTuanJingFei selectByJingFeiID(int jingfeiid);
	
	int updateSheTuanJingFei(SheTuanJingFei record);
	
	List<SheTuanJieSan> selectSheTuanJieSanByXueXiaoID(int xuexiaoid);
	
	SheTuanJieSan selectSheTuanJieSanByID(int id);
	
	int updateSheTuanJieSan(SheTuanJieSan record);
	
	Boolean tongYiJieSan(SheTuanJieSan record);
	
	int insertSheTuanXinXi(SheTuanXinXi record);
	
	Boolean sheTuanXinXiWeiHu(SheTuanXinXi record,String renyuans[]);
	
	Boolean xueShengZuZhiWeiHu(XueShengZuZhiXinXi record,String renyuans[]);
	
	int updateSheTuanJiBenXinXi(SheTuanJiBenXinXi record);
	
	int updateXueShengZuZhiJiBenXinXi(XueShengZuZhiJiBenXinXi record);
}
