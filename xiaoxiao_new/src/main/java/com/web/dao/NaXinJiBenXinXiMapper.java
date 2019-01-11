package com.web.dao;

import com.web.model.NaXinJiBenXinXi;

public interface NaXinJiBenXinXiMapper {
	
	NaXinJiBenXinXi selectNaXinJiBenXinXiBySheTuanXinXiID(int shetuanxinxiid);

	NaXinJiBenXinXi selectNaXinJiBenXinXiByXueShengZuZhiXinXiID(int xueshengzuzhixinxiid);
	
	int insertNaXinJiBenXinXi(NaXinJiBenXinXi record);
	
	int updateNaXinJiBenXinXi(NaXinJiBenXinXi record);
}