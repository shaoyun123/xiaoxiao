package com.web.service;

import java.util.List;

import com.web.model.NianFen;

public interface NianFenService {
	NianFen selectNianFenByRuXueNianFen(int ruxuenianfen);
	
	List<NianFen> getNianFen();
	
	NianFen selectByPrimaryKey(Integer ruxuenianfenid);

	List<NianFen> findByNianFen(String ruxuenianfen);
}
