package com.web.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.XueShengSuSheMapper;
import com.web.model.XueShengSuShe;
import com.web.service.XueShengSuSheService;

@Service
public class XueShengSuSheServiceImpl implements XueShengSuSheService {
	@Autowired
	private XueShengSuSheMapper xueShengSuSheMapper;
	@Override
	public int deleteByPrimaryKey(Integer suSheId){
		return xueShengSuSheMapper.deleteByPrimaryKey(suSheId);
	}
	@Override
    public int insert(XueShengSuShe record){
		return xueShengSuSheMapper.insert(record);
    }
	@Override
   public XueShengSuShe selectByPrimaryKey(Integer suSheId){
		return xueShengSuSheMapper.selectByPrimaryKey(suSheId);
   }
	@Override
    public int updateByPrimaryKey(XueShengSuShe record){
		return xueShengSuSheMapper.updateByPrimaryKey(record);
    }
	@Override
    public List<XueShengSuShe> getAllBySuSheLouId(Integer suSheLouId){
    	return xueShengSuSheMapper.getAllBySuSheLouId(suSheLouId);
    }
	@Override
    public int insertList(List<XueShengSuShe> list){
		return xueShengSuSheMapper.insertList(list);
    }
	/* (non-Javadoc)
	 * @see com.web.service.XueShengSuSheService#selectXueShengSuSheByXueXiaoIDAndXiaoQuAndSuSheLouAndMenPaiHao(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public XueShengSuShe selectXueShengSuSheByXueXiaoIDAndXiaoQuAndSuSheLouAndMenPaiHao(Integer xuexiaoid,
			String xiaoqu, String sushelou, String menpaihao) {
		return xueShengSuSheMapper.selectXueShengSuSheByXueXiaoIDAndXiaoQuAndSuSheLouAndMenPaiHao(xuexiaoid, xiaoqu, sushelou, menpaihao);
	}
	@Override
	public XueShengSuShe selectXueShengSuSheBySuSheLouIdAndMenPaiHao(
			Integer sushelouid, String menpaihao){
		return xueShengSuSheMapper.selectXueShengSuSheBySuSheLouIdAndMenPaiHao(sushelouid, menpaihao);
	}
	@Override
	public Map<String, Object> getBySuSheID(Integer susheid) {
		return xueShengSuSheMapper.getBySuSheID(susheid);
	}
}
