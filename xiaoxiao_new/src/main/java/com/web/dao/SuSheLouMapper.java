package com.web.dao;

import java.util.List;
import com.web.model.SuSheLou;


public interface SuSheLouMapper {
	int deleteByPrimaryKey(Integer suSheLouId);
    int insert(SuSheLou record);
    SuSheLou selectByPrimaryKey(Integer suSheLouId);
    int updateByPrimaryKey(SuSheLou record);
    List<SuSheLou> getAllByXiaoQuId(Integer xiaoQuId);
    SuSheLou selectBySuSheLouMingAndXiaoQuId(String suSheLouMing,Integer xiaoQuId);
}
