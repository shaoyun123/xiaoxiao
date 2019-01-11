package com.web.util.excel.annotation.datavalidate;


import java.util.List;

import com.web.util.excel.annotation.anno.ExcelAnnotation;



/**
 * 
     * Title: DataValidateFilter.java    
     * Description: 数据验证接口
     * @author xucs    
     * Company: 江苏增宇信息科技开发有限公司  
     * Copyright: Copyright (c) 2016
     * @created 2016-8-23 下午5:13:20 
     * @update 2016-8-23 下午5:13:20 
     * @version 1.0
 */
public interface DataValidateFilter {
	
	/**
	 * 
	     * @discription 数据验证接口
	     * @author xucs       
	     * @created 2016-8-23 下午5:14:12  
	     * @update 2016-8-23 下午5:14:12   
	     * @param validList 无效数据
	     * @param excelAnnotations 注解集合
	     * @param clazz 类
	     * @return 返回无效数据集合
	 */
	public <T> List<T> validate(List<T> validList, List<ExcelAnnotation> excelAnnotations, Class<T> clazz);
}
