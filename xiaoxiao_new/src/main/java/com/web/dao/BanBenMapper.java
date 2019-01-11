package com.web.dao;

import java.util.Map;

import com.web.model.BanBen;



public interface BanBenMapper {
	BanBen getZuiXinBanBenXinXi(Map<String,Object> param) throws Exception;
}
