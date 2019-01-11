package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.JCSJMapper;
import com.web.model.JCSJ;
import com.web.service.JCSJService;
@Service
public class JCSJServiceImpl implements JCSJService{
	@Autowired
	private JCSJMapper jcsjMapper;


	@Override
	public String getkaiShiShiJianByid(Integer id) {
		// TODO Auto-generated method stub
		return jcsjMapper.getkaiShiShiJianByid(id);
	}

	@Override
	public String getjieShuShiJianByid(Integer id) {
		// TODO Auto-generated method stub
		return jcsjMapper.getjieShuShiJianByid(id);
	}

	@Override
	public int getCountByJieCiFangAnID(Integer jiecifanganid) {
		// TODO Auto-generated method stub
		return jcsjMapper.getCountByJieCiFangAnID(jiecifanganid);
	}

	@Override
	public List<JCSJ> getAllByjieCiFangAnID(Integer jiecifanganid) {
		// TODO Auto-generated method stub
		return jcsjMapper.getAllByjieCiFangAnID(jiecifanganid);
	}

	@Override
	public JCSJ selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return jcsjMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insert(JCSJ record) {
		// TODO Auto-generated method stub
		return jcsjMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKey(JCSJ record) {
		// TODO Auto-generated method stub
		return jcsjMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return jcsjMapper.deleteByPrimaryKey(id);
	}

	@Override
	public String getkaiShiShiJianByidTwo(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return jcsjMapper.getkaiShiShiJianByidTwo(paramMap);
	}

	@Override
	public String getjieShuShiJianByidTwo(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return jcsjMapper.getjieShuShiJianByidTwo(paramMap);
	}

	@Override
	public int findJCSJIDbyFangAnIdandJcsj(int jiecifanganid, String jcsj) {
		// TODO Auto-generated method stub
		return jcsjMapper.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,jcsj);
	}

}
