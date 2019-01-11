package com.web.util.mail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class EmailUtil {

	private static String strInfo;
	//private static MimeMessage msg = null;
	private String saveAttchPath = "";// 附件存储路径
	private StringBuffer bodytext = new StringBuffer();
	private static String dateformate = "yy-MM-dd HH:mm";// 时间显示格式
	private StringBuilder mail_info = new StringBuilder();
	// private static String desc_dir = "";
	private String path;// 邮件目录文件夹的UUID

	public static String BAASE_PATH = "/home/xiatao/kaoshi/download/mail_tmp/";// 邮件附件存储目录，该目录下UUID文件夹，每个文件夹下为一封邮件的附件文件/home/xiatao/kaoshi/
	
	private static Store store = null;
	private static Folder folder = null;
	private static int nPos;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		strInfo = "";
		
		store = getStore(); 
		
		if(folder == null){
			try {
				folder = store.getFolder("INBOX");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				folder.open(Folder.READ_WRITE);
				duYouJian();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// READ_ONLY);设置邮件读取方式
			
		}
		
		nPos = 0;
		boolean isConnected = store.isConnected();
		//			store.close();
		closeLianJie();
		isConnected = store.isConnected();
	}

	
	public static String duYouJian() throws MessagingException, IOException{
		Message msgs[] = null;
		try {
			msgs = folder.getMessages();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// 邮件数量
		int count = msgs.length;
		System.out.println("Message Count:" + count);
		if (count == 0)
			return strInfo;
		
//		if(nPos >= count){
//			return "";
//		}
		//count = count>10?10:count;
		MimeMessage msg = null;
		for (int i = 0; i < count; i++) {
			msg = (MimeMessage) msgs[i];
			//msg.isExpunged()
			if(!isSeen(msg)){
				strInfo = recive(msg, 0,msg);// 接收邮件内容，提取附件
				msg.setFlag(Flags.Flag.DELETED, true);// 标记删除邮件
				if(!"".equals(strInfo)){
					System.out.println(strInfo);
				}
				
			}
		}
		return strInfo;
	}
	
	public static void closeLianJie(){
		try {
			folder.close(true);// 关闭邮件连接，并提交标记修改[删除邮件标记]
			folder = null;
			store.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 获取发送邮件者信息
	 * 
	 * @return
	 * @throws MessagingException
	 */
	public static String getFrom(MimeMessage msg) throws MessagingException {
		InternetAddress[] address = (InternetAddress[]) msg.getFrom();
		String from = address[0].getAddress();
		if (from == null) {
			from = "";
		}
		String personal = address[0].getPersonal();
		if (personal == null) {
			personal = "";
		}
		//String fromaddr = personal + "<" + from + ">";
		return from;
	}

	/**
	 * 获取邮件收件人，抄送，密送的地址和信息。根据所传递的参数不同 "to"-->收件人,"cc"-->抄送人地址,"bcc"-->密送地址
	 * 
	 * @param type
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public String getMailAddress(String type,MimeMessage msg) throws MessagingException,
			UnsupportedEncodingException {
		String mailaddr = "";
		String addrType = type.toUpperCase();
		InternetAddress[] address = null;

		if (addrType.equals("TO") || addrType.equals("CC")
				|| addrType.equals("BCC")) {
			if (addrType.equals("TO")) {
				address = (InternetAddress[]) msg
						.getRecipients(Message.RecipientType.TO);
			}
			if (addrType.equals("CC")) {
				address = (InternetAddress[]) msg
						.getRecipients(Message.RecipientType.CC);
			}
			if (addrType.equals("BCC")) {
				address = (InternetAddress[]) msg
						.getRecipients(Message.RecipientType.BCC);
			}

			if (address != null) {
				for (int i = 0; i < address.length; i++) {
					String mail = address[i].getAddress();
					if (mail == null) {
						mail = "";
					} else {
						mail = MimeUtility.decodeText(mail);
					}
					String personal = address[i].getPersonal();
					if (personal == null) {
						personal = "";
					} else {
						personal = MimeUtility.decodeText(personal);
					}
					String compositeto = personal + "<" + mail + ">";
					mailaddr += "," + compositeto;
				}
				mailaddr = mailaddr.substring(1);
			}
		} else {
			throw new RuntimeException("Error email Type!");
		}
		return mailaddr;
	}

	/**
	 * 获取邮件主题
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	public static String getSubject(MimeMessage msg) {// throws UnsupportedEncodingException,
								// MessagingException{
		String subject = "";
		try {
			subject = MimeUtility.decodeText(msg.getSubject() == null ? "无主题"
					: msg.getSubject());
		} catch (UnsupportedEncodingException e) {
			// TODO 要不要在这里添加一点错误信息标记呢，或者要不要处理此处的错误呢，乖
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO: handle exception
		}
		if (subject == null) {
			subject = "无主题";
		}
		return subject;
	}

	/**
	 * 获取邮件发送日期，dateformate格式
	 * 
	 * @return
	 * @throws MessagingException
	 */
	public static String getSendDate(MimeMessage msg) throws MessagingException {
		Date sendDate = msg.getSentDate();
		SimpleDateFormat smd = new SimpleDateFormat(dateformate);
		return smd.format(sendDate);
	}

	/**
	 * 获取邮件正文内容
	 * 
	 * @return
	 */
	public String getBodyText() {

		return bodytext.toString();
	}

	/**
	 * 解析邮件，将得到的邮件内容保存到一个stringBuffer对象中，解析邮件 主要根据MimeType的不同执行不同的操作，一步一步的解析
	 * 
	 * @param part
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void getMailContent(Part part) throws MessagingException,
			IOException {

		String contentType = part.getContentType();
		int nameindex = contentType.indexOf("name");
		boolean conname = false;
		if (nameindex != -1) {
			conname = true;
		}
		System.out.println("CONTENTTYPE:" + contentType);
		if (part.isMimeType("text/plain") && !conname) {
			bodytext.append((String) part.getContent());
		} else if (part.isMimeType("text/html") && !conname) {
			bodytext.append((String) part.getContent());
		} else if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			int count = multipart.getCount();
			for (int i = 0; i < count; i++) {
				getMailContent(multipart.getBodyPart(i));
			}
		} else if (part.isMimeType("message/rfc822")) {
			getMailContent((Part) part.getContent());
		}

	}

	/**
	 * 判断邮件是否需要回执，如需回执返回true，否则返回false
	 * 
	 * @return
	 * @throws MessagingException
	 */
	public boolean getReplySign(MimeMessage msg) throws MessagingException {
		boolean replySign = false;
		String needreply[] = msg.getHeader("Disposition-Notification-TO");
		if (needreply != null) {
			replySign = true;
		}
		return replySign;
	}

	/**
	 * 获取此邮件的message-id
	 * 
	 * @return
	 * @throws MessagingException
	 */
	public String getMessageId(MimeMessage msg) throws MessagingException {
		return msg.getMessageID();
	}

	/**
	 * 判断此邮件是否已读，如果未读则返回false，已读返回true
	 * 
	 * @return
	 * @throws MessagingException
	 */
	public static boolean isNew(MimeMessage msg) throws MessagingException {
		boolean isnew = false;
		Flags flags = ((Message) msg).getFlags();
		Flags.Flag[] flag = flags.getSystemFlags();
		System.out.println("flags's length:" + flag.length);
		for (int i = 0; i < flag.length; i++) {
			if (flag[i] == Flags.Flag.SEEN) {
				isnew = true;
				System.out.println("seen message .......");
				break;
			}
		}
		return isnew;
	}

	/**
	 * 判断是是否包含附件
	 * 
	 * @param part
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public boolean isContainAttch(Part part) throws MessagingException,
			IOException {
		boolean flag = false;

		if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			int count = multipart.getCount();
			for (int i = 0; i < count; i++) {
				BodyPart bodypart = multipart.getBodyPart(i);
				String dispostion = bodypart.getDisposition();
				if ((dispostion != null)
						&& (dispostion.equals(Part.ATTACHMENT) || dispostion
								.equals(Part.INLINE))) {
					flag = true;
				} else if (bodypart.isMimeType("multipart/*")) {
					flag = isContainAttch(bodypart);
				} else {
					String conType = bodypart.getContentType();
					if (conType.toLowerCase().indexOf("appliaction") != -1) {
						flag = true;
					}
					if (conType.toLowerCase().indexOf("name") != -1) {
						flag = true;
					}
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			flag = isContainAttch((Part) part.getContent());
		}

		return flag;
	}

	/**
	 * 保存附件
	 * 
	 * @param part
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void jieXiYoujianFujian(Part part, String faJianRen,
			String youJianZhuTi, String faSongShiJian)
			throws MessagingException, IOException {
		String filename = "";
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart mpart = mp.getBodyPart(i);
				String dispostion = mpart.getDisposition();
				if ((dispostion != null)
						&& (dispostion.equals(Part.ATTACHMENT) || dispostion
								.equals(Part.INLINE))) {
					filename = mpart.getFileName();
					if (filename.toLowerCase().indexOf("gbk") != -1) {// gb2312，附件名称编码
						filename = MimeUtility.decodeText(filename);
					}
					if (filename.toLowerCase().indexOf("gb2312") != -1) {// gb2312，附件名称编码
						filename = MimeUtility.decodeText(filename);
					}
					if (filename.toLowerCase().indexOf("gb18030") != -1) {// gb2312，附件名称编码
						filename = MimeUtility.decodeText(filename);
					}
					baocunFujianWenjian(filename, mpart.getInputStream(),
							faJianRen, youJianZhuTi, faSongShiJian);
				} else if (mpart.isMimeType("multipart/*")) {
					jieXiYoujianFujian(mpart, faJianRen, youJianZhuTi,
							faSongShiJian);
				} else {
					filename = mpart.getFileName();
					if (filename != null
							&& (filename.toLowerCase().indexOf("gbk") != -1)) {// gb2312，附件名称编码
						filename = MimeUtility.decodeText(filename);
					}
					if (filename != null
							&& (filename.toLowerCase().indexOf("gb2312") != -1)) {// gb2312，附件名称编码gb18030
						filename = MimeUtility.decodeText(filename);
					}
					if (filename != null
							&& (filename.toLowerCase().indexOf("gb18030") != -1)) {// gb2312，附件名称编码gb18030
						filename = MimeUtility.decodeText(filename);
					}
					baocunFujianWenjian(filename, mpart.getInputStream(),
							faJianRen, youJianZhuTi, faSongShiJian);
				}
			}

		} else if (part.isMimeType("message/rfc822")) {
			jieXiYoujianFujian((Part) part.getContent(), faJianRen,
					youJianZhuTi, faSongShiJian);
		}
	}

	/**
	 * 获得保存附件的地址
	 * 
	 * @return
	 */
	public String getSaveAttchPath() {
		return saveAttchPath;
	}

	/**
	 * 设置保存附件地址
	 * 
	 * @param saveAttchPath
	 */
	public void setSaveAttchPath(String saveAttchPath) {
		this.saveAttchPath = saveAttchPath;
	}

	/**
	 * 设置日期格式
	 * 
	 * @param dateformate
	 */
	public void setDateformate(String dateformate) {
		this.dateformate = dateformate;
	}

	/**
	 * 保存文件内容
	 * 
	 * @param filename
	 * @param inputStream
	 * @throws IOException
	 */
	private void baocunFujianWenjian(String filename, InputStream inputStream,
			String faJianRen, String youJianZhuTi, String faSongShiJian)
			throws IOException {
		if (filename == null || filename.toLowerCase().equals("null"))
			return;
		String yuanshiFilename = filename;
		mail_info.append("\n附件名称:" + filename);
		String osname = System.getProperty("os.name");
		String storedir = getSaveAttchPath();
		if (osname == null) {
			osname = "";
		}

		if (osname.toLowerCase().indexOf("win") != -1) {
			if (storedir == null || "".equals(storedir)) {
				storedir = "d:\\temp\\";
			}
		} else {
		}

		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("_yyyy-MM-dd_HH.mm.ss");
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String jieshouShijian = sf1.format(date);
		if (filename.endsWith(".ANS")) {
			filename = filename.substring(0, filename.lastIndexOf('.')) + "-"
					+ sf.format(date) + ".ANS";
		} else if (filename.endsWith(".COR")) {
			filename = filename.substring(0, filename.lastIndexOf('.')) + "-"
					+ sf.format(date) + ".COR";
		} else if (filename.endsWith(".WRK")) {
			filename = filename.substring(0, filename.lastIndexOf('.')) + "-"
					+ sf.format(date) + ".WRK";
		} else {
			mail_info.append("\n状态:未解析");
			mail_info.append("\n处理时间:" + jieshouShijian);
			writeIgnoreItem(mail_info.toString());
			return;
		}

		String strTemp = storedir + "\\" + filename;
		strTemp = strTemp.replace("//", "\\");
		File storefile = new File(storedir + filename);
		// File storefile = new File(strTemp);
		System.out.println("storefile's path:" + storefile.toString());
		File f = new File(storedir);
		if (!f.exists()) {
			f.mkdirs();
		}
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;

		int nLen = 0;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(storefile));
			bis = new BufferedInputStream(inputStream);
			int c;
			byte[] data = new byte[1024];
			while ((c = bis.read(data)) != -1) {
				bos.write(data, 0, c);
				bos.flush();
				nLen += c;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bos.close();
			bis.close();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void writeIgnoreItem(String string) {
		FileWriter writer = null;
		File log = new File("/home/xiatao/kaoshi/download/mail/ignore.txt");
		if (!log.exists())
			try {
				log.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		try {
			writer = new FileWriter("ignore.txt", true);
			writer.write(string + "\n\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static String recive(Part part, int i,MimeMessage msg) {
		int res = 0;
		// System.out.println("------------------START-----------------------");
		// System.out.println("Message" + i + " subject:" + getSubject());//邮件主题
		// System.out.println("Message" + i + " from:" + getFrom());//发送用户
		// System.out.println("Message" + i + " isNew:" + isNew());//是否为新用户
		strInfo = "";
		String faJianRen = "";
		try {
			faJianRen = getFrom(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return strInfo;
		}
		String youJianZhuTi = getSubject(msg);
		String faSongShiJian = "";
		try {
			faSongShiJian = getSendDate(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			faSongShiJian = "";
		}
		//getMailContent(part);// 递归获取邮件内容
        StringBuffer content = new StringBuffer();  
        try {
			getMailTextContent(part, content);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			strInfo = "";
			return strInfo;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			strInfo = "";
			return strInfo;
		}  
        res = 1;
        String zhengWen = content.toString();
        System.out.println("正文:"+zhengWen);
		strInfo = faJianRen + "ZQJX" + youJianZhuTi + zhengWen + "ZQJX" + faSongShiJian + "ZQXJ";
		
//		System.out.println("------------------解析第" + msg.getMessageNumber() + "封邮件-------------------- ");  
//        System.out.println("主题: " + getSubject(msg));  
//        System.out.println("发件人: " + getFrom(msg));  
//        System.out.println("收件人：" + getReceiveAddress(msg, null));  
//        System.out.println("发送时间：" + getSentDate(msg, null));  
//        System.out.println("是否已读：" + isSeen(msg));  
//        System.out.println("邮件优先级：" + getPriority(msg));  
//        System.out.println("是否需要回执：" + isReplySign(msg));  
//        System.out.println("邮件大小：" + msg.getSize() * 1024 + "kb");  
//        boolean isContainerAttachment = isContainAttachment(msg);  
//        System.out.println("是否包含附件：" + isContainerAttachment);  
//        if (isContainerAttachment) {  
//            saveAttachment(msg, "f:\\mailTest\\"+msg.getSubject() + "_"+i+"_"); //保存附件  
//        }   
//        StringBuffer content = new StringBuffer();  
//        getMailTextContent(msg, content);  
//        System.out.println("邮件正文：" + (content.length() > 100 ? content.substring(0,100) + "..." : content));  
//        System.out.println("------------------第" + msg.getMessageNumber() + "封邮件解析结束-------------------- ");  
//        System.out.println();   
		/*
		mail_info.append("\n邮件:" + youJianZhuTi);
		mail_info.append("\n用户:" + faJianRen);
		mail_info.append("\n发送时间:" + faSongShiJian);
		boolean flag = isContainAttch(part);// 是否包含附件
		// System.out.println("Message" + i + " isContainAttch:" + flag);
		// System.out.println("Message" + i + " replySign:" + getReplySign());
		getMailContent(part);// 下载附件
		// System.out.println("Message" + i + " content:" + getBodyText());
		// setSaveAttchPath(KaoshiMail.BAASE_PATH + i);// 保存附件路径设置
		if (flag) {
			try {
				jieXiYoujianFujian(part, faJianRen, youJianZhuTi, faSongShiJian);// 保存附件文件
			} catch (Exception e) {
				// TODO 要不要在这里添加一点错误信息标记呢，或者要不要处理此处的错误呢，乖
				res = -1;
				e.printStackTrace();
			} finally {
				return res;
			}
		}
		// System.out.println("------------------END-----------------------");*/
		return strInfo;// 返回执行是否成功标记
	}
	
	 /** 
     * 获得邮件主题 
     * @param msg 邮件内容 
     * @return 解码后的邮件主题 
     */  
//    public static String getSubject(MimeMessage msg) throws UnsupportedEncodingException, MessagingException {  
//        return MimeUtility.decodeText(msg.getSubject());  
//    }  
      
    /** 
     * 获得邮件发件人 
     * @param msg 邮件内容 
     * @return 姓名 <Email地址> 
     * @throws MessagingException 
     * @throws UnsupportedEncodingException  
     */  
//    public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {  
//        String from = "";  
//        Address[] froms = msg.getFrom();  
//        if (froms.length < 1)  
//            throw new MessagingException("没有发件人!");  
//          
//        InternetAddress address = (InternetAddress) froms[0];  
//        String person = address.getPersonal();  
//        if (person != null) {  
//            person = MimeUtility.decodeText(person) + " ";  
//        } else {  
//            person = "";  
//        }  
//        from = person + "<" + address.getAddress() + ">";  
//          
//        return from;  
//    }  
      
    /** 
     * 根据收件人类型，获取邮件收件人、抄送和密送地址。如果收件人类型为空，则获得所有的收件人 
     * <p>Message.RecipientType.TO  收件人</p> 
     * <p>Message.RecipientType.CC  抄送</p> 
     * <p>Message.RecipientType.BCC 密送</p> 
     * @param msg 邮件内容 
     * @param type 收件人类型 
     * @return 收件人1 <邮件地址1>, 收件人2 <邮件地址2>, ... 
     * @throws MessagingException 
     */  
    public static String getReceiveAddress(MimeMessage msg, Message.RecipientType type) throws MessagingException {  
        StringBuffer receiveAddress = new StringBuffer();  
        Address[] addresss = null;  
        if (type == null) {  
            addresss = msg.getAllRecipients();  
        } else {  
            addresss = msg.getRecipients(type);  
        }  
          
        if (addresss == null || addresss.length < 1)  
            throw new MessagingException("没有收件人!");  
        for (Address address : addresss) {  
            InternetAddress internetAddress = (InternetAddress)address;  
            receiveAddress.append(internetAddress.toUnicodeString()).append(",");  
        }  
          
        receiveAddress.deleteCharAt(receiveAddress.length()-1); //删除最后一个逗号  
          
        return receiveAddress.toString();  
    }  
      
    /** 
     * 获得邮件发送时间 
     * @param msg 邮件内容 
     * @return yyyy年mm月dd日 星期X HH:mm 
     * @throws MessagingException 
     */  
    public static String getSentDate(MimeMessage msg, String pattern) throws MessagingException {  
        Date receivedDate = msg.getSentDate();  
        if (receivedDate == null)  
            return "";  
          
        if (pattern == null || "".equals(pattern))  
            pattern = "yyyy年MM月dd日 E HH:mm ";  
          
        return new SimpleDateFormat(pattern).format(receivedDate);  
    }  
      
    /** 
     * 判断邮件中是否包含附件 
     * @param msg 邮件内容 
     * @return 邮件中存在附件返回true，不存在返回false 
     * @throws MessagingException 
     * @throws IOException 
     */  
    public static boolean isContainAttachment(Part part) throws MessagingException, IOException {  
        boolean flag = false;  
        if (part.isMimeType("multipart/*")) {  
            MimeMultipart multipart = (MimeMultipart) part.getContent();  
            int partCount = multipart.getCount();  
            for (int i = 0; i < partCount; i++) {  
                BodyPart bodyPart = multipart.getBodyPart(i);  
                String disp = bodyPart.getDisposition();  
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {  
                    flag = true;  
                } else if (bodyPart.isMimeType("multipart/*")) {  
                    flag = isContainAttachment(bodyPart);  
                } else {  
                    String contentType = bodyPart.getContentType();  
                    if (contentType.indexOf("application") != -1) {  
                        flag = true;  
                    }    
                      
                    if (contentType.indexOf("name") != -1) {  
                        flag = true;  
                    }   
                }  
                  
                if (flag) break;  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            flag = isContainAttachment((Part)part.getContent());  
        }  
        return flag;  
    }  
      
    /**  
     * 判断邮件是否已读  
     * @param msg 邮件内容  
     * @return 如果邮件已读返回true,否则返回false  
     * @throws MessagingException   
     */  
    public static boolean isSeen(MimeMessage msg) {  
        try {
			return msg.getFlags().contains(Flags.Flag.SEEN);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}  
    }  
      
    /** 
     * 判断邮件是否需要阅读回执 
     * @param msg 邮件内容 
     * @return 需要回执返回true,否则返回false 
     * @throws MessagingException 
     */  
    public static boolean isReplySign(MimeMessage msg) throws MessagingException {  
        boolean replySign = false;  
        String[] headers = msg.getHeader("Disposition-Notification-To");  
        if (headers != null)  
            replySign = true;  
        return replySign;  
    }  
      
    /** 
     * 获得邮件的优先级 
     * @param msg 邮件内容 
     * @return 1(High):紧急  3:普通(Normal)  5:低(Low) 
     * @throws MessagingException  
     */  
    public static String getPriority(MimeMessage msg) throws MessagingException {  
        String priority = "普通";  
        String[] headers = msg.getHeader("X-Priority");  
        if (headers != null) {  
            String headerPriority = headers[0];  
            if (headerPriority.indexOf("1") != -1 || headerPriority.indexOf("High") != -1)  
                priority = "紧急";  
            else if (headerPriority.indexOf("5") != -1 || headerPriority.indexOf("Low") != -1)  
                priority = "低";  
            else  
                priority = "普通";  
        }  
        return priority;  
    }   
      
    /** 
     * 获得邮件文本内容 
     * @param part 邮件体 
     * @param content 存储邮件文本内容的字符串 
     * @throws MessagingException 
     * @throws IOException 
     */  
    public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {  
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断  
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;   
        if (part.isMimeType("text/plain") && !isContainTextAttach) {  
            content.append(part.getContent().toString());  
        } else if(part.isMimeType("text/html") && !isContainTextAttach){
        	//content.append(part.getContent().toString());  
        }else if (part.isMimeType("message/rfc822")) {   
            getMailTextContent((Part)part.getContent(),content);  
        } else if (part.isMimeType("multipart/*")) {  
            Multipart multipart = (Multipart) part.getContent();  
            int partCount = multipart.getCount();  
            for (int i = 0; i < partCount; i++) {  
                BodyPart bodyPart = multipart.getBodyPart(i);  
                getMailTextContent(bodyPart,content);  
            }  
        }  
    }  
      
    /**  
     * 保存附件  
     * @param part 邮件中多个组合体中的其中一个组合体  
     * @param destDir  附件保存目录  
     * @throws UnsupportedEncodingException  
     * @throws MessagingException  
     * @throws FileNotFoundException  
     * @throws IOException  
     */  
    public static void saveAttachment(Part part, String destDir) throws UnsupportedEncodingException, MessagingException,  
            FileNotFoundException, IOException {  
        if (part.isMimeType("multipart/*")) {  
            Multipart multipart = (Multipart) part.getContent();    //复杂体邮件  
            //复杂体邮件包含多个邮件体  
            int partCount = multipart.getCount();  
            for (int i = 0; i < partCount; i++) {  
                //获得复杂体邮件中其中一个邮件体  
                BodyPart bodyPart = multipart.getBodyPart(i);  
                //某一个邮件体也有可能是由多个邮件体组成的复杂体  
                String disp = bodyPart.getDisposition();  
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {  
                    InputStream is = bodyPart.getInputStream();  
                    saveFile(is, destDir, decodeText(bodyPart.getFileName()));  
                } else if (bodyPart.isMimeType("multipart/*")) {  
                    saveAttachment(bodyPart,destDir);  
                } else {  
                    String contentType = bodyPart.getContentType();  
                    if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {  
                        saveFile(bodyPart.getInputStream(), destDir, decodeText(bodyPart.getFileName()));  
                    }  
                }  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            saveAttachment((Part) part.getContent(),destDir);  
        }  
    }  
      
    /**  
     * 读取输入流中的数据保存至指定目录  
     * @param is 输入流  
     * @param fileName 文件名  
     * @param destDir 文件存储目录  
     * @throws FileNotFoundException  
     * @throws IOException  
     */  
    private static void saveFile(InputStream is, String destDir, String fileName)  
            throws FileNotFoundException, IOException {  
        BufferedInputStream bis = new BufferedInputStream(is);  
        BufferedOutputStream bos = new BufferedOutputStream(  
                new FileOutputStream(new File(destDir + fileName)));  
        int len = -1;  
        while ((len = bis.read()) != -1) {  
            bos.write(len);  
            bos.flush();  
        }  
        bos.close();  
        bis.close();  
    }  
      
    /** 
     * 文本解码 
     * @param encodeText 解码MimeUtility.encodeText(String text)方法编码后的文本 
     * @return 解码后的文本 
     * @throws UnsupportedEncodingException 
     */  
    public static String decodeText(String encodeText) throws UnsupportedEncodingException {  
        if (encodeText == null || "".equals(encodeText)) {  
            return "";  
        } else {  
            return MimeUtility.decodeText(encodeText);  
        }  
    }

	public void recive_new(Part part, int i,MimeMessage msg) throws MessagingException,
			IOException {
		System.out.println("------------------START-----------------------");
		System.out.println("Message" + i + " subject:" + getSubject(msg));
		System.out.println("Message" + i + " from:" + getFrom(msg));
		boolean is_new = isNew(msg);
		System.out.println("Message" + i + " isNew:" + is_new);
		if (!is_new)
			return;// 是否为新邮件，只获取新邮件
		boolean flag = isContainAttch(part);// 是否包含附件
		System.out.println("Message" + i + " isContainAttch:" + flag);
		System.out.println("Message" + i + " replySign:" + getReplySign(msg));// 是否需要回执
		getMailContent(part);// 递归获取邮件内容
		System.out.println("Message" + i + " content:" + getBodyText());// 邮件正文
		String faJianRen = getFrom(msg);
		String youJianZhuTi = getSubject(msg);
		String faSongShiJian = getSendDate(msg);
		if (flag) {
			jieXiYoujianFujian(part, faJianRen, youJianZhuTi, faSongShiJian);
		}
		System.out.println("------------------END-----------------------");
	}


	public StringBuffer getBodytext() {
		return bodytext;// 获取bodytext的值，亲
	}

	public void setBodytext(StringBuffer bodytext) {
		this.bodytext = bodytext;// 给this.bodytext赋值，思密达
	}

	public String getPath() {
		return path;// 获取path的值，亲
	}

	public void setPath(String path) {
		this.path = path;// 给this.path赋值，思密达
	}

	public String getDateformate() {
		return dateformate;// 获取dateformate的值，亲
	}

	public static String getStrInfo() {
		return strInfo;
	}

	public static void setStrInfo(String strInfo) {
		EmailUtil.strInfo = strInfo;
	}

	public StringBuilder getMail_info() {
		return mail_info;
	}

	public void setMail_info(StringBuilder mail_info) {
		this.mail_info = mail_info;
	}

	public static Store getStore() {
		try {
			if(store == null || !store.isConnected()){
				Properties props = new Properties();
//				props.setProperty("mail.smtp.host", "smtp.163.com");// 设置SMTP服务器地址
//				props.setProperty("mail.smtp.auth", "true");// 确认需要认证信息
//				Session session = Session.getDefaultInstance(props, null);// 创建会话
//				URLName urlname = new URLName("pop3", "pop.163.com", 110, null,
						//"kaoshiupload", "A19941220");// 设置该账户的基本属性，kaoshiupload为用户名，upload为密码
//						"x39947540", "XUCS39947540");// 设置该账户的基本属性，kaoshiupload为用户名，upload为密码
				//"13260359609", "xuqi7616556");// 设置该账户的基本属性，kaoshiupload为用户名，upload为密码
						//"13598038057", "a910706");// 设置该账户的基本属性，kaoshiupload为用户名，upload为密码
				// "kaoshiupload", "upload");// 设置该账户的基本属性，kaoshiupload为用户名，upload为密码

				//buct
				/**/
				
				
				
				props.setProperty("mail.smtp.host", "mail.buct.edu.cn");// 设置SMTP服务器地址
				props.setProperty("mail.smtp.auth", "true");// 确认需要认证信息
				props.setProperty("mail.smtp.port", "110");
				Session session = Session.getDefaultInstance(props, null);// 创建会话
				URLName urlname = new URLName("pop3", "mail.buct.edu.cn", 110, null,
////						//"kaoshiupload", "A19941220");// 设置该账户的基本属性，kaoshiupload为用户名，upload为密码
//						//"yanzheng5ic", "ks608kjds");// 设置该账户的基本属性，kaoshiupload为用户名，upload为密码
//				//"13260359609", "xuqi7616556");// 设置该账户的基本属性，kaoshiupload为用户名，upload为密码
						"2013014594", "kjds608ks");// 设置该账户的基本属性，kaoshiupload为用户名，upload为密码
				
				// "kaoshiupload", "upload");// 设置该账户的基本属性，kaoshiupload为用户名，upload为密码
				
				
				store = session.getStore(urlname);
				store.connect();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				System.out.println(new String(e.getMessage().getBytes("ISO-8859-1"),"GBK"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
		
		return store;
	}

	public static void setStore(Store store) {
		EmailUtil.store = store;
	}

	public static Folder getFolder() {
		return folder;
	}

	public static void setFolder(Folder folder) {
		EmailUtil.folder = folder;
	}
	
	
}
