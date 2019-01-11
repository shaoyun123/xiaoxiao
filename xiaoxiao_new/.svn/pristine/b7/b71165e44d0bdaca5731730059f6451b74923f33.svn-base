package com.web.service.Impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.JLNRMapper;
import com.web.model.JLNR;
import com.web.service.JLNRService;
@Service
public class JLNRServiceImpl implements JLNRService{
	@Autowired
	private JLNRMapper jlnrMapper;

	@Override
	public List<JLNR> getALLByxueShengID(Integer xueshengid) {
		return jlnrMapper.getALLByxueShengID(xueshengid);
	}

	@Override
	public String getxueShengShangChuanByjiaoLiuID(Integer jiaoliuid) {
		return jlnrMapper.getxueShengShangChuanByjiaoLiuID(jiaoliuid);
	}
	
	@Override
	public String getxueShengShangChuanByanPaiID(Integer anpaiid) {
		return jlnrMapper.getxueShengShangChuanByjiaoLiuID(anpaiid);
	}

	@Override
	public int updateByPrimaryKey(Map<String, String> map) {
		return jlnrMapper.updateByPrimaryKey(map);
	}
	
	@Override
	public int updatexueshengshangchuanByanPaiID(Map<String, String> map) {
		return jlnrMapper.updatexueshengshangchuanByanPaiID(map);
	}
	
	@Override
	public int updatexueshengshangchuanByjiaoLiuID(Map<String, String> map) {
		return jlnrMapper.updatexueshengshangchuanByjiaoLiuID(map);
	}

	@Override
	public String getshangchuanzhuangtaiByjiaoLiuID(Integer jiaoliuid) {
		return jlnrMapper.getshangchuanzhuangtaiByjiaoLiuID(jiaoliuid);
	}

	@Override
	public int updateshangchuanzhuangtaiByjiaoLiuID(Map<String, String> map) {
		return jlnrMapper.updateshangchuanzhuangtaiByjiaoLiuID(map);
	}

	@Override
	public String getfuDaoYuanShenHeByjiaoLiuID(Integer jiaoliuid) {
		return jlnrMapper.getfuDaoYuanShenHeByjiaoLiuID(jiaoliuid);
	}

	@Override
	public String getjiaoLiuMingChengByjiaoLiuID(Integer jiaoliuid) {
		return jlnrMapper.getjiaoLiuMingChengByjiaoLiuID(jiaoliuid);
	}

	@Override
	public JLNR selectByPrimaryKey(Integer jiaoliuid) {
		return jlnrMapper.selectByPrimaryKey(jiaoliuid);
	}
	
	@Override
	public JLNR selectByanPaiID(Integer anpaiid) {
		return jlnrMapper.selectByanPaiID(anpaiid);
	}

	@Override
	public int insert(JLNR record) {
		return jlnrMapper.insert(record);
	}

	@Override
	public int updateByanPaiID(JLNR record) {
		return jlnrMapper.updateByanPaiID(record);
	}

	@Override
	public int deleteByanPaiID(Integer anpaiid) {
		return jlnrMapper.deleteByanPaiID(anpaiid);
	}

	@Override
	public List<JLNR> getAllByanPaiID(Integer anpaiid) {
		return jlnrMapper.getAllByanPaiID(anpaiid);
	}

	@Override
	public int updatefuDaoYuanShenHeByjiaoLiuID(JLNR record) {
		return jlnrMapper.updatefuDaoYuanShenHeByjiaoLiuID(record);
	}

	@Override
	public int getCountByxueShengIDAndPass(Integer xueshengid) {
		return jlnrMapper.getCountByxueShengIDAndPass(xueshengid);
	}

	@Override
	public List<JLNR> getALLByxueShengIDAndLimitAndPass(Integer xueshengid, int start, int stop) {
		return jlnrMapper.getALLByxueShengIDAndLimitAndPass(xueshengid, start, stop);
	}

	@Override
	public List<JLNR> getALLByxueShengIDAndLimitAndUnaudited(Integer xueshengid, int start, int stop) {
		return jlnrMapper.getALLByxueShengIDAndLimitAndUnaudited(xueshengid, start, stop);
	}

	@Override
	public int getCountByxueShengIDAndUnaudited(Integer xueshengid) {
		return jlnrMapper.getCountByxueShengIDAndUnaudited(xueshengid);
	}

}
