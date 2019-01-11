   
/**     
 * @discription 在此输入一句话描述此文件的作用
 * @author xucs       
 * @created 2017-5-31 上午9:39:45
 * @update 2017-5-31 上午9:39:45    
 * tags     
 * see_to_target     
*/
    
package com.web.json;

import java.util.Date;
import java.util.List;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
  
/**        
 * Title: DefaultJsonConfig.java    
 * Description: 描述
 * @author xucs    
 * Company: 江苏增宇信息科技开发有限公司  
 * Copyright: Copyright (c) 2017
 * @created 2017-5-31 上午9:39:45 
 * @update 2017-5-31 上午9:39:45 
 * @version 1.0
 */

public class DefaultJsonConfig extends JsonConfig{
	public DefaultJsonConfig(){
		//super.setIgnoreJPATransient(true);
		//super.setIgnoreTransientFields(true);
		super.setCycleDetectionStrategy(CycleDetectionStrategy.NOPROP);
		super.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		super.registerJsonValueProcessor(List.class, new ListJsonValueProcessor());
	}
}
