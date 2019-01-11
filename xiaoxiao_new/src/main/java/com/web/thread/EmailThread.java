package com.web.thread;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.web.model.LoginInfo;
import com.web.service.YongHuService;
import com.web.util.Tools;
import com.web.util.mail.EmailUtil;

public class EmailThread {
	
	private YongHuService yongHuService;
	
	protected static final Logger LOG = LoggerFactory.getLogger(EmailThread.class);
	
	private static String isQuery = "0";
	
	public EmailThread(YongHuService yongHuService){
		this.yongHuService=yongHuService;
	}
	public void xiaoXiThread() {
		Thread thread = new Thread(){
			@Override
			public void run() {
				System.out.println("启动同步线程...");
				while(true){
					try {
												
						Thread.sleep(10*1000);
						//执行要做的事
						if("0".equals(isQuery)){
							isQuery = "1";
							try{
								sendXiaoXi();
							}catch (Exception e) {
								isQuery = "0";
							}
							isQuery = "0";
						}
						
					} catch (InterruptedException e) {
						isQuery = "0";
						e.printStackTrace();
					} finally{
						closeLianJie();
					}
					
				}
			}
		};
		thread.start();
	}
	private static Store store = null;
	private static Folder folder = null;
	
	public void sendXiaoXi(){
		if(Tools.isEmpty(store) || !store.isConnected()){
			store = EmailUtil.getStore(); 
		}

		if(Tools.isEmpty(folder) || !folder.isOpen()){
			try {
				folder = store.getFolder("INBOX");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			try {
				folder.open(Folder.READ_WRITE);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		Message msgs[] = null;
		try {
			msgs = folder.getMessages();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// 邮件数量
		int count = msgs.length;
		/*System.out.println("读取到"+count+"封邮件.");
		LOG.debug("读取到"+count+"封邮件.");*/
		if (count == 0){
			
			return;
		}
		MimeMessage msg = null;
		String strInfo = "";
		String email = "";
		String[] temp = null;
		List<LoginInfo> loginInfoList  = null;
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Map<String,Object> updateParamMap = new HashMap<String,Object>();
		String tempStr = "";
		boolean canDelete = false;
		for (int i = 0; i < count; i++) {
			msg = (MimeMessage) msgs[i];
			/*Flags flags=msg.getFlags();  
			if(Flags.Flag.SEEN == msg.get){
				
			}*/
			canDelete = false;
			//msg.isExpunged()
			//if(!isSeen(msg)){
			try {
				strInfo = EmailUtil.recive(msg, 0,msg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				strInfo = "";
			}// 接收邮件内容，提取附件
			if(Tools.notEmpty(strInfo)){
				try {
					temp = strInfo.split("ZQJX");
					email = temp[0];
					tempStr = temp[1];
					paramMap.put("youXiang", email);
					loginInfoList =  yongHuService.selectLoginInfo(paramMap);
					if(Tools.notEmpty(loginInfoList)){
						for(LoginInfo loginInfo : loginInfoList){
							updateParamMap.put("status", loginInfo.getStatus());
							updateParamMap.put("yonghuid", loginInfo.getId());
							if(Tools.notEmpty(loginInfo.getYanZhengMa())){
								if(tempStr.indexOf(loginInfo.getYanZhengMa())!=-1){
									updateParamMap.put("yanZhengMa", loginInfo.getYanZhengMa());
									yongHuService.updatePasswordOrYanZhengMa(updateParamMap);
									Map<String,Object> parmMap = new HashMap<String,Object>();
									if("xuesheng".equals(loginInfo.getStatus())){
										parmMap.put("password", loginInfo.getXueHao());
										parmMap.put("xuexiaoxuehao", loginInfo.getXuexiao_xuehao());
										parmMap.put("status", loginInfo.getStatus());
										
									}else{
										parmMap.put("password", "000000");
										parmMap.put("loginName", loginInfo.getYongHuMing());
										parmMap.put("status", "laoshi");
									}
									int c = yongHuService.updatePasswordKaoShiById(parmMap);
									if(c > 0){
										canDelete = true;
										if("xuesheng".equals(loginInfo.getStatus())){
											String shunxuhao = System.currentTimeMillis()+"";
											String dizhiliebiao = "2,3";
											String tongbuneirong = "update xuesheng set miMaMD5 = '"+loginInfo.getXueHao()+"' where xuexiao_xuehao = '"+loginInfo.getXuexiao_xuehao()+"' ";
											String leimingcheng = "com.ccbupt.kaoshi.dao.XueSheng";
											Map<String,Object> pMap = new HashMap<String,Object>();
											pMap.put("shunxuhao", shunxuhao);
											pMap.put("dizhiliebiao", dizhiliebiao);
											pMap.put("tongbuneirong", tongbuneirong);
											pMap.put("leimingcheng", leimingcheng);
											yongHuService.insertKaoshiTongBuFaSong(pMap);
										}else{
											String shunxuhao = System.currentTimeMillis()+"";
											String dizhiliebiao = "2,3";
											String tongbuneirong = "update yonghu set miMaMD5 = '000000' where yongHuMing = '"+loginInfo.getYongHuMing()+"' ";
											String leimingcheng = "com.ccbupt.kaoshi.dao.YongHu";
											Map<String,Object> pMap = new HashMap<String,Object>();
											pMap.put("shunxuhao", shunxuhao);
											pMap.put("dizhiliebiao", dizhiliebiao);
											pMap.put("tongbuneirong", tongbuneirong);
											pMap.put("leimingcheng", leimingcheng);
											yongHuService.insertKaoshiTongBuFaSong(pMap);
										}
										
										//String leimingcheng = "com.ccbupt.kaoshi.dao.YongHu";
									}
								}
							}
							if(Tools.notEmpty(loginInfo.getCheckCodeFor())){
								if(tempStr.indexOf(loginInfo.getCheckCodeFor())!=-1 ){
									updateParamMap.put("checkCodeFor", loginInfo.getCheckCodeFor());
									canDelete = true;
									yongHuService.updatePasswordOrYanZhengMa(updateParamMap);
								}
							}
						}
					}
					if(canDelete){
						msg.setFlag(Flags.Flag.DELETED, true);
					}/*else{
						msg.setFlag(Flags.Flag.SEEN, true);
					}*/
					
				} catch (MessagingException e) {
					e.printStackTrace();
				}// 标记删除邮件
//				System.out.println(strInfo);
			}
				
			//}
		}
		//closeLianJie();
		
	}
	
	private static void closeLianJie(){
		try {
			folder.close(true);// 关闭邮件连接，并提交标记修改[删除邮件标记]
			folder = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		} finally {
		}
	}
}
