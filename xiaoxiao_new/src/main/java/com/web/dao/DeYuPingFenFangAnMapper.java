package com.web.dao;

import java.util.List;

import com.web.model.DeYuPingFenFangAn;

public interface DeYuPingFenFangAnMapper {
    int deleteByPrimaryKey(Integer pingfenid);

    int insert(DeYuPingFenFangAn record);

    int insertSelective(DeYuPingFenFangAn record);

    List<DeYuPingFenFangAn> selectAllByFangAnID(Integer id);

    int updateByPrimaryKeySelective(DeYuPingFenFangAn record);

    int updateByPrimaryKey(DeYuPingFenFangAn record);
    
    List<Integer> getPingFenIdByFangAnID(Integer FangAnId);
}