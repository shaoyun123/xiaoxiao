package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.XueShengSuShe;

public interface XueShengSuSheService {
	int deleteByPrimaryKey(Integer suSheId);
    int insert(XueShengSuShe record);
    int insertList(List<XueShengSuShe> list);
    XueShengSuShe selectByPrimaryKey(Integer suSheId);
    int updateByPrimaryKey(XueShengSuShe record);
    List<XueShengSuShe> getAllBySuSheLouId(Integer suSheLouId);
    
    XueShengSuShe selectXueShengSuSheByXueXiaoIDAndXiaoQuAndSuSheLouAndMenPaiHao(Integer xuexiaoid,String xiaoqu,String sushelou,String menpaihao);
    XueShengSuShe selectXueShengSuSheBySuSheLouIdAndMenPaiHao(Integer sushelouid, String menpaihao);
	Map<String, Object> getBySuSheID(Integer susheid);
			
}
