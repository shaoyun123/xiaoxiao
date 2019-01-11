   
/**     
 * @discription 在此输入一句话描述此文件的作用
 * @author xucs       
 * @created 2017-5-31 上午9:43:00
 * @update 2017-5-31 上午9:43:00    
 * tags     
 * see_to_target     
*/
    
package com.web.json;

import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

  
/**        
 * Title: ListJsonValueProcessor.java    
 * Description: 描述
 * @author xucs    
 * Company: 江苏增宇信息科技开发有限公司  
 * Copyright: Copyright (c) 2017
 * @created 2017-5-31 上午9:43:00 
 * @update 2017-5-31 上午9:43:00 
 * @version 1.0
 */

public class ListJsonValueProcessor implements JsonValueProcessor{

	  
    /** 
     * @discription 在此输入一句话描述作用
     * @author xucs       
     * @created 2016-12-19 下午3:27:45
     * @update 2016-12-19 下午3:27:45      
     * @param arg0
     * @param arg1
     * @return     
     * @see net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang.Object, net.sf.json.JsonConfig)     
     */  
	    
	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		if(value != null && value.getClass().isArray()){
			Object[] ts = (Object[]) value;
			if(ts.length > 0 && ts[0] instanceof List){
				return null;
			}
		}
		return value;
	}
	  
    /** 
     * @discription 在此输入一句话描述作用
     * @author xucs       
     * @created 2016-12-19 下午3:27:45
     * @update 2016-12-19 下午3:27:45      
     * @param key
     * @param value
     * @param jsonConfig
     * @return     
     * @see net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang.String, java.lang.Object, net.sf.json.JsonConfig)     
     */  
	    
	@Override
	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		if(value == null){
			return null;
		}
		if(value.getClass().isArray()){
			return JSONArray.fromObject(value, jsonConfig);
		}else if(value instanceof Collection<?>){
			return JSONArray.fromObject(((List<?>)value).toArray(), jsonConfig);
		}else{
			return JSONObject.fromObject(value, jsonConfig);
		}
	}
}
