package com.web.controller.web.common.util;

import java.util.ArrayList;
import java.util.List;

public class DownloadUtil {

	public static final String TEXT_CSS = "text/css";
	public static final String TEXT_HTML = "text/html";
	public static final String TEXT_PLAIN = "text/plain";
	public static final String TEXT_RICHTEXT = "text/richtext";
	public static final String TEXT_RTF = "text/rtf";
	public static final String TEXT_SGML = "text/sgml";
	public static final String TEXT_XML = "text/xml";
	
	public static final String IMAGE_CGM = "image/cgm";
	public static final String IMAGE_GIF = "image/gif";
	public static final String IMAGE_JPEG = "image/jpeg";
	public static final String IMAGE_PNG = "image/png";
	public static final String IMAGE_TIFF = "image/tiff";
	
	public static final String VIDIO_MPEG = "vidio_mpeg";
	public static final String VIDEO_AVI = "video/avi";
	
	public static final String MODEL_VRML = "model/vrml";
	public static final String MODEL_IGES = "model/iges";
	public static final String MODEL_MESH = "model/mesh";
	
	public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
	public static final String APPLICATION_JAVA = "application/java";
	public static final String APPLICATION_POSTSCRIPT = "application/postscript";
	public static final String APPLICATION_PDF = "application/pdf";
	public static final String APPLICATION_ZIP = "application/zip";
	public static final String APPLICATION_MSWORD = "application/msword";
	public static final String APPLICATION_MSEXCEL = "application/vnd.ms-excel";
	public static final String APPLICATION_XML = "application/xml";
	
	private static List<String> resContList = null;
	/**  
	 * 功能：对文件类型进行初始化  
	 */
	static {
	     resContList = new ArrayList<String>();
	     resContList.add(TEXT_CSS);
	     resContList.add(TEXT_HTML);
	     resContList.add(TEXT_PLAIN);
	     resContList.add(TEXT_RICHTEXT);
	     resContList.add(TEXT_RTF);
	     resContList.add(TEXT_SGML);
	     resContList.add(TEXT_XML);
	     resContList.add(IMAGE_CGM);
	     resContList.add(IMAGE_GIF);
	     resContList.add(IMAGE_JPEG);
	     resContList.add(IMAGE_PNG);
	     resContList.add(IMAGE_TIFF);
	     resContList.add(VIDIO_MPEG);
	     resContList.add(VIDEO_AVI);
	     resContList.add(MODEL_VRML);
	     resContList.add(MODEL_IGES);
	     resContList.add(MODEL_MESH);
	     resContList.add(APPLICATION_OCTET_STREAM);
	     resContList.add(APPLICATION_JAVA);
	     resContList.add(APPLICATION_POSTSCRIPT);
	     resContList.add(APPLICATION_PDF);
	     resContList.add(APPLICATION_ZIP);
	     resContList.add(APPLICATION_MSWORD);
	     resContList.add(APPLICATION_MSEXCEL);
	     resContList.add(APPLICATION_XML);
	}
	
	public static List<String> getResContList() {
		return resContList;
	}
}
