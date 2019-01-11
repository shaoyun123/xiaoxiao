package com.web.dao;

import java.util.List;

import com.web.model.NianFen;

public interface NianFenMapper {
    int deleteByPrimaryKey(Integer ruxuenianfenid);

    int insert(NianFen record);

    int insertSelective(NianFen record);

    NianFen selectByPrimaryKey(Integer ruxuenianfenid);
    
    NianFen selectByRuXueNianFen(Integer ruxuenianfen);
    
    List<NianFen> getNianFen();

    int updateByPrimaryKeySelective(NianFen record);

    int updateByPrimaryKey(NianFen record);

	List<NianFen> findByNianFen(String ruxuenianfen);
}