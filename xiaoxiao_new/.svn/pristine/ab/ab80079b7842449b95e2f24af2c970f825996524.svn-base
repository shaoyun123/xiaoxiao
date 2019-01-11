   
/**     
 * @discription 在此输入一句话描述此文件的作用
 * @author xucs       
 * @created 2017-5-31 上午9:40:24
 * @update 2017-5-31 上午9:40:24    
 * tags     
 * see_to_target     
*/
    
package com.web.json;

import java.util.Date;

import com.web.util.DateUtil;
import com.web.util.StringUtils;


import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

  
/**        
 * Title: DateJsonValueProcessor.java    
 * Description: 描述
 * @author xucs    
 * Company: 江苏增宇信息科技开发有限公司  
 * Copyright: Copyright (c) 2017
 * @created 2017-5-31 上午9:40:24 
 * @update 2017-5-31 上午9:40:24 
 * @version 1.0
 */

public class DateJsonValueProcessor implements JsonValueProcessor{
	//默认时间格式
	public static String DEFAULT_PATTEN =   "yyyy-MM-dd HH:mm:ss";
	
	private String patten ;

	/**
	 * Constructor for DateJsonValueProcessor
	 * @param patten
	 */
	public  DateJsonValueProcessor(String patten) {
		super();
		this.patten = patten;
	}
	
	/**
	 * 没有参数过滤构造函数，默认时间格式:yyyy-MM-dd HH:mm:ss
	 * @see DateJsonValueProcessor.DEFAULT_PATTEN
	 * @author zohan
	 * Constructor for DateJsonValueProcessor
	 */
	public  DateJsonValueProcessor() {
		super();
	}
	
	
	

	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang.Object, net.sf.json.JsonConfig)
	 */
	@Override
	public Object processArrayValue(Object value, JsonConfig config) {
		if(value.getClass().isArray()){
			Date dates[] = (Date[]) value;
			String dateArray [] = new String[dates.length];
			for(int i =0;i<dates.length;i++){
				dateArray[i]=formatDate(dates[i]);
			}
			return dateArray;
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang.String, java.lang.Object, net.sf.json.JsonConfig)
	 */
	@Override
	public Object processObjectValue(String str, Object value, JsonConfig config) {	
		if(value == null ||"".equals(value)) return "";
		return formatDate((Date) value);
	}
	
	/**
	 * 
	     * @discription 格式化时间
	     * @author xucs       
	     * @created 2016-5-14 上午10:15:38  
	     * @update 2016-5-14 上午10:15:38   
	     * @param value
	     * @return
	 */
	private String formatDate(Date value){
		String date;
		try {
			if(!StringUtils.isNullOrEmpty(patten)){
				date = DateUtil.FormatDate( value, patten);				
			}else{
				date = DateUtil.FormatDate( value, DEFAULT_PATTEN);
			}
		} catch (Exception e) {
			date = DateUtil.FormatDate( value, DEFAULT_PATTEN);
		}
		
		return date;
	}
	

}
