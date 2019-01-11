package com.web.service.Impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.web.dao.YongHuMapper;
import com.web.model.LoginInfo;
import com.web.model.YongHu;
import com.web.service.YongHuService;

@Service("yongHuService")
public class YongHuServiceImpl implements YongHuService {
	@Autowired
	YongHuMapper yongHuMapper;

	@Override
	public YongHu selectYongHuByID(int id) {
		return yongHuMapper.selectYongHuByID(id);
	}

	@Override
	public YongHu selectFuDaoYuanByIdAndPasswd(Map<String, String> map) {
		return yongHuMapper.selectFuDaoYuanByIdAndPasswd(map);
	}

	@Override
	public YongHu selectJiaoShiByIdAndPasswd(Map<String, String> map) {
		return yongHuMapper.selectJiaoShiByIdAndPasswd(map);
	}

	@Override
	public YongHu selectShuJiByIdAndPasswd(Map<String, String> map) {
		return yongHuMapper.selectShuJiByIdAndPasswd(map);
	}

	@Override
	public YongHu selectXueShengChuGuanLiYuanByIdAndPasswd(Map<String, String> map) {
		return yongHuMapper.selectXueShengChuGuanLiYuanByIdAndPasswd(map);
	}

	@Override
	public List<YongHu> getAllByYuanXiID(Integer yuanxiid) {
		// TODO Auto-generated method stub
		return yongHuMapper.getAllByYuanXiID(yuanxiid);
	}

	@Override
	public int updateYongHu(YongHu record) {
		return yongHuMapper.updateYongHu(record);
	}

	@Override
	public String selectPassWdByID(int id) {
		return yongHuMapper.selectPassWdByID(id);
	}

	@Override
	public int updatePassWdByID(String password, int id) {
		return yongHuMapper.updatePassWdByID(password, id);
	}

	@Override
	public List<LoginInfo> selectLoginInfo(Map<String, Object> paramMap) {
		return yongHuMapper.selectLoginInfo(paramMap);
	}

	@Override
	public int updateYanZhengMa(Map<String, Object> paramMap) {
		return yongHuMapper.updateYanZhengMa(paramMap);
	}

	@Override
	public int updateCheckCodeFor(Map<String, Object> paramMap) {
		return yongHuMapper.updateCheckCodeFor(paramMap);
	}

	@Override
	public int updatePasswordOrYanZhengMa(Map<String, Object> paramMap) {
		int i = yongHuMapper.updatePasswordOrYanZhengMa(paramMap);
		i = yongHuMapper.updatePasswordKaoShiById(paramMap);
		return i;
	}

	@Override
	public int updatePasswordById(Map<String, Object> paramMap) {
		int i = yongHuMapper.updatePasswordById(paramMap);
		i = yongHuMapper.updatePasswordKaoShiById(paramMap);

		if (i > 0) {
			if ("xuesheng".equals(paramMap.get("status").toString())) {
				String xuexiaoxuehao = paramMap.get("xuexiaoxuehao").toString();
				String shunxuhao = System.currentTimeMillis() + "";
				String dizhiliebiao = "2,3";
				String tongbuneirong = "update xuesheng set miMaMD5 = '" + paramMap.get("password").toString()
						+ "' where xuexiao_xuehao = '" + xuexiaoxuehao + "' ";
				String leimingcheng = "com.ccbupt.kaoshi.dao.XueSheng";
				Map<String, Object> pMap = new HashMap<String, Object>();
				pMap.put("shunxuhao", shunxuhao);
				pMap.put("dizhiliebiao", dizhiliebiao);
				pMap.put("tongbuneirong", tongbuneirong);
				pMap.put("leimingcheng", leimingcheng);
				yongHuMapper.insertKaoshiTongBuFaSong(pMap);

			} else {
				String shunxuhao = System.currentTimeMillis() + "";
				String dizhiliebiao = "2,3";
				String tongbuneirong = "update yonghu set miMaMD5 = '" + paramMap.get("password").toString()
						+ "' where yongHuMing = '" + paramMap.get("loginName").toString() + "' ";
				String leimingcheng = "com.ccbupt.kaoshi.dao.YongHu";
				Map<String, Object> pMap = new HashMap<String, Object>();
				pMap.put("shunxuhao", shunxuhao);
				pMap.put("dizhiliebiao", dizhiliebiao);
				pMap.put("tongbuneirong", tongbuneirong);
				pMap.put("leimingcheng", leimingcheng);
				yongHuMapper.insertKaoshiTongBuFaSong(pMap);
			}
		}
		return i;
	}

	@Override
	public int updatePasswordKaoShiById(Map<String, Object> paramMap) {
		return yongHuMapper.updatePasswordKaoShiById(paramMap);
	}

	@Override
	public int insertKaoshiTongBuFaSong(Map<String, Object> paramMap) {
		return yongHuMapper.insertKaoshiTongBuFaSong(paramMap);
	}

	@Override
	public int insert(YongHu yongHu) {
		return yongHuMapper.insert(yongHu);
	}

	@Override
	public int updateTouXiangByID(Map<String, String> map) {
		// TODO Auto-generated method stub
		return yongHuMapper.updateTouXiangByID(map);
	}

	@Override
	public int updateTouXiang(MultipartFile file, Map<String, String> map) {
		int i = 0;
		String fileName = file.getOriginalFilename();
		String path = map.get("path").toString() + "upload" + File.separator;
		File files = new File(path);
		if (!files.exists()) {
			files.mkdirs();
		}
		// 获取图片后缀
		String prefix = fileName.substring((fileName.lastIndexOf(".") + 1));
		files = new File(path + map.get("touxiang").toString());

		String[] str = map.get("avatar_data").toString().split(",");
		// 获取旋转的角度
		int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
		
		// 获取截取的x坐标
		int x = 0;
		// 获取截取的y坐标
		int y = 0;
		if(r == 0 ){
			x = (int) Math.floor(Double.parseDouble(str[0].split(":")[1]));
			y = (int) Math.floor(Double.parseDouble(str[1].split(":")[1]));
		}
		// 获取截取的高度
		int h = (int) Math.floor(Double.parseDouble(str[2].split(":")[1]));
		// 获取截取的宽度
		int w = (int) Math.floor(Double.parseDouble(str[3].split(":")[1]));
		try {
			Iterator iterator = ImageIO.getImageReadersByFormatName(prefix);
			try {
				ImageReader reader = (ImageReader) iterator.next();
				// 转换成输入流
				InputStream in = file.getInputStream();
				ImageInputStream iis = ImageIO.createImageInputStream(in);
				reader.setInput(iis, true);
				ImageReadParam param = reader.getDefaultReadParam();
				Rectangle rect = new Rectangle(x, y, w, h);
				param.setSourceRegion(rect);
				BufferedImage bufferedimage = reader.read(0, param);

				int type = bufferedimage.getColorModel().getTransparency();
				BufferedImage rotateImage;
				Graphics2D graphics2d;
				(graphics2d = (rotateImage = new BufferedImage(w, h, type)).createGraphics()).setRenderingHint(
						RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				graphics2d.setPaint(Color.WHITE);
				graphics2d.fillRect(0, 0, w, h);
				graphics2d.rotate(Math.toRadians(r), w / 2, h / 2);
				graphics2d.drawImage(bufferedimage, 0, 0, Color.WHITE, null);
				graphics2d.dispose();

				ByteArrayOutputStream out = new ByteArrayOutputStream();
				boolean flag = ImageIO.write(rotateImage, prefix, out);
				// 转换后存入数据库
				byte[] b = out.toByteArray();
				FileOutputStream outs = new FileOutputStream(files);
				outs.write(b);
				outs.flush();
				outs.close();

				i = yongHuMapper.updateTouXiangByID(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

}
