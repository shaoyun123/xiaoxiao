package com.web.controller.base;


import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.web.json.DefaultJsonConfig;
import com.web.thread.JiGuangThread;
import com.web.util.Page;
import com.web.util.PageData;
import com.web.util.UuidUtil;
import com.web.util.resultrender.ResultRender;



public class BaseController implements Serializable, ResultRender{
	/**
	 * 编码
	 */
	protected static final String ENCODING_PREFIX = "encoding";
	/**
	 * 缓存标示
	 */
	protected static final String NOCACHE_PREFIX = "no-cache";
	/**
	 * 编码
	 */
	protected static final String ENCODING_DEFAULT = "utf-8";
	/**
	 * 缓存判断
	 */
	protected static final boolean NOCACHE_DEFAULT = true;
	
	//默认json格式设置
	protected DefaultJsonConfig defaultJsonConfig = new DefaultJsonConfig();
	
	/**
	 * log对象
	 */
	protected Logger logger = LoggerFactory.getLogger(JiGuangThread.class);

	private static final long serialVersionUID = 6357869213649815390L;
	/**
	 * request
	 */
	protected HttpServletRequest request; 
	/**
	 * response
	 */
    protected HttpServletResponse response; 
    /**
     * session
     */
    protected HttpSession session; 
    
    @ModelAttribute 
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){ 
        this.request = request; 
        this.response = response; 
        this.session = request.getSession(); 
    } 
	/**
	 * 
	 * @discription 获取pagedata 其封装了mvc方式，用作传参和接收使用
	 * @author xucs       
	 * @created 2017-5-17 上午11:54:32  
	 * @update 2017-5-17 上午11:54:32   
	 * @return
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}

	/**
	 * 
	 * @discription 得到得到一个新的view请求
	 * @author xucs       
	 * @created 2017-5-17 上午11:56:13  
	 * @update 2017-5-17 上午11:56:13   
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**
	 * 
	 * @discription 得到request对象
	 * @author xucs       
	 * @created 2017-5-17 上午11:56:23  
	 * @update 2017-5-17 上午11:56:23   
	 * @return
	 */
	public HttpServletRequest getRequest() {
		if(request == null){
			request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		}
		return request;
	}

	/**
	 * 
	 * @discription 得到32位的uuid
	 * @author xucs       
	 * @created 2017-5-17 上午11:56:32  
	 * @update 2017-5-17 上午11:56:32   
	 * @return
	 */
	public String get32UUID(){
		
		return UuidUtil.get32UUID();
	}
	
	/**
	 * 
	 * @discription 得到分页列表的信息 
	 * @author xucs       
	 * @created 2017-5-17 上午11:56:44  
	 * @update 2017-5-17 上午11:56:44   
	 * @return
	 */
	public Page getPage(){
		return new Page();
	}
	/**
	 * 
	 * @discription 打印日志前输出
	 * @author xucs       
	 * @created 2017-5-17 上午11:57:54  
	 * @update 2017-5-17 上午11:57:54   
	 * @param logger log对象
	 * @param interfaceName 接口名称
	 */
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	/**
	 * 
	 * @discription 日志输出后输出
	 * @author xucs       
	 * @created 2017-5-17 上午11:58:20  
	 * @update 2017-5-17 上午11:58:20   
	 * @param logger
	 */
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	
	public HttpServletResponse getResponse() {
		 return this.response;
    }
	
	
	
	/**
	 * 
     * @discription 直接输出内容的简便函数. eg. render("text/plain", "hello",
	 *              "encoding:UTF-8"); render("text/plain", "hello",
	 *              "no-cache:false"); render("text/plain", "hello",
	 *              "encoding:UTF-8", "no-cache:false");
     * @author xucs       
     * @created 2016-4-11 下午5:22:39  
     * @update 2016-4-11 下午5:22:39   
     * @param contentType 输出的文本格式 如："text/html"，“text/plain”，"text/xml"
     * @param content 对外输出的内容
     * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public void render(final String contentType, final String content, final String... headers) {
		HttpServletResponse response = getResponse();
		try {
			// 分析headers参数
			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;
			for (String header : headers) {
				String headerName = StringUtils.substringBefore(header, ":");
				String headerValue = StringUtils.substringAfter(header, ":");

				if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
					encoding = headerValue;
				} else if (StringUtils.equalsIgnoreCase(headerName, NOCACHE_PREFIX)) {
					noCache = Boolean.parseBoolean(headerValue);
				} else
					throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
			}

			// 设置headers参数
			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			response.addHeader("Access-Control-Allow-Origin","*");
			if("IE".equals(getRequest().getParameter("type"))){
				response.addHeader("XDomainRequestAllowed","1");
		    }
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);
			response.getWriter().flush();

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//Response在不同应用服务器中实现不同。在WAS中需要关闭。
			try {
				response.getWriter().close();
			} catch (IOException e) {
				// do mothing
			}
		}
	}

	/**
	 * 
     * @discription  直接输出文本.
     * @author xucs       
     * @created 2016-4-11 下午5:23:14  
     * @update 2016-4-11 下午5:23:14   
     * @param text 需要输出的字符串内容
     * @param headers 输出的页面头内容
	 */
	public void renderText(final String text, final String... headers) {
		render("text/plain", text, headers);
	}

	/**
	 * 
     * @discription 直接输出HTML.
     * @author xucs       
     * @created 2016-4-11 下午5:23:30  
     * @update 2016-4-11 下午5:23:30   
     * @param html html格式的字符串
     * @param headers 输出的页面头内容
	 */
	public void renderHtml(final String html, final String... headers) {
		render("text/html", html, headers);
	}

	/**
	 * 
     * @discription 直接输出XML.
     * @author xucs       
     * @created 2016-4-11 下午5:23:48  
     * @update 2016-4-11 下午5:23:48   
     * @param xml  xml格式的内容
     * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public void renderXml(final String xml, final String... headers) {
		render("text/xml", xml, headers);
	}

	/**
	 * 
     * @discription 直接输出流.
     * @author xucs       
     * @created 2016-4-11 下午5:24:48  
     * @update 2016-4-11 下午5:24:48   
     * @param contentType 输出类型
     * @param inputStream 输入流
     * @param headers 协议头数组对象
	 */
	public void renderStream(final String contentType, final InputStream inputStream, final String... headers) {
		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		try {
			response.setContentType(contentType);

			byte[] b = new byte[1024];
			int len = -1;
			while ((len = inputStream.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}

			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//Response在不同应用服务器中实现不同。在WAS中需要关闭。
			try {
				response.getWriter().close();
			} catch (IOException e) {
				// do mothing
			}
		}
	}
	
	/**
	 * 输出json数据
	 */
	@Override
	public void renderJson(Object object, String... headers) {
		if(object.getClass().isArray()){
			String jsonString = JSONArray.fromObject(object,defaultJsonConfig).toString();
			renderJsonString(jsonString, headers);
		}else if(object instanceof Collection<?>){
			String jsonString = JSONArray.fromObject(((List<?>)object).toArray(), defaultJsonConfig).toString();
			renderJsonString(jsonString, headers);
		}else if(object instanceof String){
			renderJsonString(object.toString(), headers);
		}else{
			String jsonString = JSONObject.fromObject(object,defaultJsonConfig).toString();
			renderJsonString(jsonString, headers);
		}
		
	}
	
	/**
	 * 输出json数据 为TEXT
	 */
	@Override
	public void renderJsonText(Object object, String... headers) {
		if(object.getClass().isArray()){
			String jsonString = JSONArray.fromObject(object,defaultJsonConfig).toString();
			renderJsonTextString(jsonString, headers);
		}else if(object instanceof Collection<?>){
			String jsonString = JSONArray.fromObject(((List<?>)object).toArray(), defaultJsonConfig).toString();
			renderJsonTextString(jsonString, headers);
		}else if(object instanceof String){
			renderJsonTextString(object.toString(), headers);
		}else{
			String jsonString = JSONObject.fromObject(object,defaultJsonConfig).toString();
			renderJsonTextString(jsonString, headers);
		}
		
	}
	
	/**
	 * 获取JSON字符串
	 */
	@Override
	public String getJsonString(Object object, String... headers) {
		if(object.getClass().isArray()){
			return JSONArray.fromObject(object,defaultJsonConfig).toString();
		}else if(object instanceof Collection<?>){
			return JSONArray.fromObject(((List<?>)object).toArray(), defaultJsonConfig).toString();
		}else if(object instanceof String){
			return JSONArray.fromObject(object.toString(), defaultJsonConfig).toString();
		}else{
			return JSONObject.fromObject(object,defaultJsonConfig).toString();
		}
	}
	
	/**
	 * 
     * @discription 直接输出JSON.
     * @author xucs       
     * @created 2016-5-14 上午10:18:08  
     * @update 2016-5-14 上午10:18:08   
     * @param string json字符串.
     * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	private void renderJsonString(final String string, final String... headers) {
		render("application/json", string, headers);
	}
	
	private void renderJsonTextString(final String string, final String... headers) {
		render("text/html", string, headers);
	}
	
}
