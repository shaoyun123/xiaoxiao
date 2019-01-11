package com.web.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.dao.DeYuPingFenFangAnMapper;
import com.web.dao.PingFenFangAnMapper;
import com.web.dao.XueQiDeYuMapper;
import com.web.dao.XueShengDeYuMapper;
import com.web.model.DeYuPingFenFangAn;
import com.web.model.PingFenFangAn;
import com.web.model.XueQiDeYu;
import com.web.model.XueShengDeYu;
import com.web.model.YongHu;
import com.web.service.DeYuService;

@Service
public class DeYuServiceImpl implements DeYuService{
	@Autowired
	private DeYuPingFenFangAnMapper deYuPingFenFangAnMapper;
	
	@Autowired
	private XueShengDeYuMapper xueShengDeYuMapper;
	
	@Autowired
	private XueQiDeYuMapper xueQiDeYuMapper;
	
	@Autowired
	private PingFenFangAnMapper pingFenFangAnMapper;
	@Override
	public List<DeYuPingFenFangAn> getListByFangAnID(int id) {
		List<DeYuPingFenFangAn> rootList = deYuPingFenFangAnMapper.selectAllByFangAnID(id);
		List<DeYuPingFenFangAn> FangAnList = new ArrayList<>();
		for (int i = 0; i < rootList.size(); i++) {
			// 找出一级List，shangjiid=0
			if (rootList.get(i).getShangjiid() == 0) {
				FangAnList.add(rootList.get(i));
			}
		}
		// 为一级List设置子项
		for (DeYuPingFenFangAn fangAn : FangAnList) {
			fangAn.setChildList(getChild(fangAn.getPingfenid(), rootList));
		}
		return FangAnList;
	}
	/**
	 * 递归查找子项
	 * 
	 * @param id
	 *            当前项id
	 * @param rootList
	 *            要查找的列表
	 * @return
	 */
	private List<DeYuPingFenFangAn> getChild(Integer id, List<DeYuPingFenFangAn> rootList) {
		// 子项
		List<DeYuPingFenFangAn> childList = new ArrayList<>();
		for (DeYuPingFenFangAn fangAn : rootList) {
			// 遍历所有节点，将父菜单id与传过来的id比较
			if (fangAn.getShangjiid() != null) {
				if (fangAn.getShangjiid().equals(id)) {
					childList.add(fangAn);
				}
			}
		}
		// 把子项的再循环一遍
		for (DeYuPingFenFangAn fangAn : childList) {// 项类型为1的子项还有子项
			if (fangAn.getXiangleixing() == 1) {
				// 递归
				fangAn.setChildList(getChild(fangAn.getPingfenid(), rootList));
			}
		} // 递归退出条件
		if (childList.size() == 0) {
			return null;
		}
		return childList;
	}
	@Override
	public List<XueShengDeYu> selectAllByXueShengID(int id) {
		return xueShengDeYuMapper.selectAllByXueShengID(id);
	}
	@Override
	public List<Integer> getPingfenIDByFangAnID(int fangAnId) {
		return deYuPingFenFangAnMapper.getPingFenIdByFangAnID(fangAnId);
	}
	@Override
	public int getFangAnIdByXueQiDeYuID(int xueQiDeYuId) {
		return xueQiDeYuMapper.getFangAnIdByXueQiDeYuID(xueQiDeYuId);
	}
	@Override
	public XueShengDeYu selectByXueShengID(int id) {
		return xueShengDeYuMapper.selectByXueShengID(id);
	}
	@Override
	public List<DeYuPingFenFangAn> getAllByFangAnID(int id) {
		return deYuPingFenFangAnMapper.selectAllByFangAnID(id);
	}
	@Override
	public int selectXueQiDeYuIDByXueQiID(int xueQiId) {
		return xueQiDeYuMapper.selectXueQiDeYuIDByXueQiID(xueQiId);
	}
	@Override
	public int insert(XueShengDeYu record) {
		return xueShengDeYuMapper.insert(record);
	}
	@Override
	public XueShengDeYu selectByXueShengIDAndXueQiDeYuID(Map<String, String> map) {
		return xueShengDeYuMapper.selectByXueShengIDAndXueQiDeYuID(map);
	}
	@Override
	public XueQiDeYu selectXueQiDeYuByXueQiIDAndBanJiID(int xueQiId,String banJiID1,String banJiID2) {
		return xueQiDeYuMapper.selectXueQiDeYuByXueQiIDAndBanJiID(xueQiId, banJiID1,banJiID2);
	}
	@Override
	public XueShengDeYu selectByXueShengIDAndDeYuFenID(Map<String, String> map) {
		return xueShengDeYuMapper.selectByXueShengIDAndDeYuFenID(map);
	}
	@Override
	public XueQiDeYu selectXueQiDeYuByXueQiDeYuID(int id) {
		return xueQiDeYuMapper.selectXueQiDeYuByXueQiDeYuID(id);
	}
	@Override
	public int updateByDeYuFenID(Map<String, String> map) {
		return xueShengDeYuMapper.updateByDeYuFenID(map);
	}
	@Override
	public XueShengDeYu selectByDeYuFenID(int deYuFenId) {
		return xueShengDeYuMapper.selectByDeYuFenID(deYuFenId);
	}
	@Override
	public int updateXueQiDeYuByXueQiDeYu(XueQiDeYu record) {
		return xueQiDeYuMapper.updateXueQiDeYuByXueQiDeYu(record);
	}
	@Override
	public int insertXueQiDeYu(XueQiDeYu record) {
		return xueQiDeYuMapper.insert(record);
	}
	@Override
	public XueQiDeYu selectXueQiDeYuByXueQiDeYu(XueQiDeYu record) {
		return xueQiDeYuMapper.selectXueQiDeYuByXueQiDeYu(record);
	}
	@Override
	public XueQiDeYu selectXueQiDeYuByXueQiID(int xueQiId) {
		return xueQiDeYuMapper.selectXueQiDeYuByXueQiID(xueQiId);
	}
	@Override
	public XueQiDeYu selectXueQiDeYuByXueQiIDAndShiYongBanJiIDs(int xueqiid, String shiyongbanjiid) {
		return xueQiDeYuMapper.selectXueQiDeYuByXueQiIDAndShiYongBanJiIDs(xueqiid, shiyongbanjiid);
	}
	@Override
	@Transactional
	public Boolean insertNewDeYuKaoPingBiao(HttpServletRequest request, HttpServletResponse response, int fanganid) {
		List<DeYuPingFenFangAn> list = getListByFangAnID(fanganid);
		boolean b = false; 
		PingFenFangAn pingFenFangAn = new PingFenFangAn();
		pingFenFangAn.setJiaoshiid(((YongHu)request.getSession().getAttribute("user")).getYonghuid());
		pingFenFangAn.setFanganmingcheng(request.getParameter("fanganmingcheng")); 
		pingFenFangAnMapper.insert(pingFenFangAn);
		for (DeYuPingFenFangAn deYuPingFenFangAn : list) {
			int id = deYuPingFenFangAn.getPingfenid();
			deYuPingFenFangAn.setFanganid(pingFenFangAn.getFanganid());
			deYuPingFenFangAnMapper.insert(deYuPingFenFangAn);
			int pingFenID = deYuPingFenFangAn.getPingfenid();
			DeYuPingFenFangAn fangAn = null;
			System.out.println(request.getParameter(id+"-1-mingcheng"));
			for (int i = 1; request.getParameter(id+"-"+i+"-mingcheng")!=null; i++) {
				fangAn = new DeYuPingFenFangAn();
				fangAn.setFanganid(pingFenFangAn.getFanganid());
				fangAn.setMingcheng(request.getParameter(id+"-"+i+"-mingcheng"));
				System.out.println(id+"-"+i+"-s:::::"+request.getParameter(id+"-"+i+"-s"));
				if (request.getParameter(id+"-"+i+"-s")!=null && request.getParameter(id+"-"+i+"-s").equals("0".toString())) {
					fangAn.setXueshengtianxie(Integer.parseInt(request.getParameter(id+"-"+i+"-c")));
					if (fangAn.getXueshengtianxie()==2) {
						fangAn.setDanxiangfen(Integer.parseInt(request.getParameter(id+"-"+i+"-dxf")));
					}
					fangAn.setManfen(Integer.parseInt(request.getParameter(id+"-"+i+"-manfen")));
					fangAn.setXiangleixing(Integer.parseInt(request.getParameter(id+"-"+i+"-s")));
					fangAn.setShangjiid(pingFenID);
					deYuPingFenFangAnMapper.insert(fangAn);
				}else if (request.getParameter(id+"-"+i+"-s")!=null && request.getParameter(id+"-"+i+"-s").equals("1".toString())) {
					fangAn.setXueshengtianxie(Integer.parseInt(request.getParameter(id+"-"+i+"-c")));
					fangAn.setManfen(Integer.parseInt(request.getParameter(id+"-"+i+"-manfen")));
					fangAn.setXiangleixing(Integer.parseInt(request.getParameter(id+"-"+i+"-s")));
					fangAn.setShangjiid(pingFenID);
					deYuPingFenFangAnMapper.insert(fangAn);
					int childPingFenID = fangAn.getPingfenid();
					DeYuPingFenFangAn childFangAn = null;
					for (int j = 1; request.getParameter(id+"-"+i+"-"+j+"-mingcheng")!=null; j++) {
						childFangAn = new DeYuPingFenFangAn();
						childFangAn.setFanganid(pingFenFangAn.getFanganid());
						childFangAn.setMingcheng(request.getParameter(id+"-"+i+"-"+j+"-mingcheng"));
						childFangAn.setXiangleixing(0);
						childFangAn.setXueshengtianxie(Integer.parseInt(request.getParameter(id+"-"+i+"-"+j+"-c")));
						if (childFangAn.getXueshengtianxie()==2) {
							childFangAn.setDanxiangfen(Integer.parseInt(request.getParameter(id+"-"+i+"-"+j+"-dxf")));
						}
						childFangAn.setManfen(Integer.parseInt(request.getParameter(id+"-"+i+"-"+j+"-manfen")));
						childFangAn.setXiangleixing(Integer.parseInt(request.getParameter(id+"-"+i+"-"+j+"-s")));
						childFangAn.setShangjiid(childPingFenID);
						deYuPingFenFangAnMapper.insert(childFangAn);
					}
				}
			}
		}
		b = true;
		return b;
	}
	@Override
	public List<XueQiDeYu> selectXueQiDeYuByBanJiID(String string, String string2) {
		return xueQiDeYuMapper.selectXueQiDeYuByBanJiID(string,string2);
	}
	@Override
	public List<Map<String, Object>> findShouYeXueShengDeYuByBanJiID(String string, String string2) {
		return xueQiDeYuMapper.findShouYeXueShengDeYuByBanJiID(string,string2);
	}
	@Override
	public PingFenFangAn getFangAnMingChengByFangAnID(Integer fanganid) {
		return pingFenFangAnMapper.selectByPrimaryKey(fanganid);
	}
	@Override
	public XueQiDeYu selectXueQiDeYuByXueQiIDAndBanJiIDAndFangAnID(Integer xueQiId, String banJiID1, String banJiID2,
			String fanganid) {
		// TODO Auto-generated method stub
		return xueQiDeYuMapper.selectXueQiDeYuByXueQiIDAndBanJiIDAndFangAnID(xueQiId, banJiID1,banJiID2,fanganid);
	}
	@Override
	public int updateXueShengDeYuZhuangTaiByList(List<XueShengDeYu> xueShengDeYus) {
		int i = 0;
		for (XueShengDeYu xueShengDeYu : xueShengDeYus) {
			if(xueShengDeYu!=null) {
				i = xueShengDeYuMapper.updateByPrimaryKeySelective(xueShengDeYu);
			}
			else {
				continue;
			}
		}
		return i;
	}
	@Override
	public int updateByPrimaryKeySelective(XueShengDeYu xueShengDeYu) {
		// TODO Auto-generated method stub
		return xueShengDeYuMapper.updateByPrimaryKeySelective(xueShengDeYu);
	}
	@Override
	public List<XueShengDeYu> selectAllByXueQiDeYuID(Integer xueqideyuid) {
		// TODO Auto-generated method stub
		return xueShengDeYuMapper.selectAllByXueQiDeYuID(xueqideyuid);
	}
	
}
