package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.NeiRongTongZhi;

public interface NeiRongTongZhiService {

	int insert(NeiRongTongZhi tongzhi);

	List<NeiRongTongZhi> findByFaBuRenIDAndLeiXing(Map<String, Object> paramMap);

	NeiRongTongZhi selectByPrimaryKey(String id);

	int delete(String id);

	int update(NeiRongTongZhi tongZhi);

	List<NeiRongTongZhi> findByBanJiIDAndZhuanYeID(Map<String, Object> paramMap);

}
