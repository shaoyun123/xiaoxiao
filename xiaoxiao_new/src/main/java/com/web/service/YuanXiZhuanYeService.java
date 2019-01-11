package com.web.service;

import java.util.List;

import com.web.model.YuanXiZhuanYe;

public interface YuanXiZhuanYeService {
	int deleteByPrimaryKey(Integer id);

    int insert(YuanXiZhuanYe record);

    int insertSelective(YuanXiZhuanYe record);

    YuanXiZhuanYe selectByPrimaryKey(Integer id);
    
    YuanXiZhuanYe selectByZhuanYeId(Integer zhuanyeid);

    int updateByPrimaryKeySelective(YuanXiZhuanYe record);

    int updateByPrimaryKey(YuanXiZhuanYe record);
    
    List<YuanXiZhuanYe> getAllByYuanXiID(Integer yuanxiid);
}
