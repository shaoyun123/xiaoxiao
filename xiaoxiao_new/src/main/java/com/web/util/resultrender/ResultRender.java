/**
 * 
 */
package com.web.util.resultrender;

import java.io.InputStream;

/**
 * 
     * Title: ResultRender.java    
     * Description: 对执行结果的不同格式封装
     * @author xucs    
     * Company: 江苏增宇信息科技开发有限公司  
     * Copyright: Copyright (c) 2016
     * @created 2016-5-14 上午10:07:12 
     * @update 2016-5-14 上午10:07:12 
     * @version 1.0
 */
public interface ResultRender {
	
	/**
	 * 
     * @discription 直接输出内容的简便函数. eg. render("text/plain", "hello",
     * 				"encoding:UTF-8"); render("text/plain", "hello",
     * 				"no-cache:false"); render("text/plain", "hello",
     * 				"encoding:UTF-8", "no-cache:false");
     * @author xucs       
     * @created 2016-5-14 上午10:07:37  
     * @update 2016-5-14 上午10:07:37   
     * @param contentType 输出的文本格式 如："text/html"，“text/plain”，"text/xml"
     * @param content 对外输出的内容
     * @param headers 可变的header数组
	 */
	public void render(final String contentType, final String content, final String... headers);
	
	/**
	 * 
     * @discription 直接输出文本
     * @author xucs       
     * @created 2016-5-14 上午10:08:27  
     * @update 2016-5-14 上午10:08:27   
     * @param text
     * @param headers
	 */
	public void renderText(final String text, final String... headers);
	
	/**
	 * 
     * @discription 直接输出HTML.
     * @author xucs       
     * @created 2016-5-14 上午10:08:36  
     * @update 2016-5-14 上午10:08:36   
     * @param html
     * @param headers
	 */
	public void renderHtml(final String html, final String... headers);
	
	/**
	 * 
     * @discription 直接输出XML.
     * @author xucs       
     * @created 2016-5-14 上午10:08:46  
     * @update 2016-5-14 上午10:08:46   
     * @param xml
     * @param headers
	 */
	public void renderXml(final String xml, final String... headers);
	
	/**
	 * 
     * @discription 直接输出JSON
     * @author xucs       
     * @created 2016-5-14 上午10:08:58  
     * @update 2016-5-14 上午10:08:58   
     * @param object
     * @param headers
	 */
	public void renderJson(final Object object, final String... headers);
	
	/**
	 * 
     * @discription 直接输出流.
     * @author xucs       
     * @created 2016-5-14 上午10:09:09  
     * @update 2016-5-14 上午10:09:09   
     * @param contentType
     * @param inputStream
     * @param headers
	 */
	public void renderStream(final String contentType, final InputStream inputStream, final String... headers);
	
	/**
	 * 
     * @discription 将Content-type设置为text/html格式
     * @author xucs       
     * @created 2016-8-24 下午4:22:56  
     * @update 2016-8-24 下午4:22:56   
     * @param object
     * @param headers
	 */
	public void renderJsonText(final Object object, final String... headers);
	
	/**
	 * 
     * @discription 将Content-type设置为text/html格式
     * @author xucs       
     * @created 2017-3-6上午11:07:56  
     * @update 2017-8-6 上午11:07:56   
     * @param object
     * @param headers
	 */
	public String getJsonString(final Object object, final String... headers);

}