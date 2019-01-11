package com.web.util.excel.annotation.datavalidate.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.web.util.excel.annotation.ExcelUtil;
import com.web.util.excel.annotation.anno.ExcelAnnotation;
import com.web.util.excel.annotation.datavalidate.DataValidateFilter;


/**
 * 
     * Title: NotNullDataValidateFilter.java    
     * Description: 为空判断 
     * @author xucs    
     * Company: 江苏增宇信息科技开发有限公司  
     * Copyright: Copyright (c) 2016
     * @created 2016-8-23 下午5:13:48 
     * @update 2016-8-23 下午5:13:48 
     * @version 1.0
 */
public class NotNullDataValidateFilter implements DataValidateFilter {
	@Override
	public <T> List<T> validate(List<T> validList, List<ExcelAnnotation> excelAnnotations, Class<T> clazz) {
		// 为null判断
		if (validList == null || validList.size() <= 0) {
			return null;
		}
		
		// 声明临时变量
		boolean notNull = false;	// 是否是空字段
		String fieldName = null;	// 字段名称
		Object fieldValue = null;	// 字段值
		Method method = null;		// 方法
		List<T> invalidList = new ArrayList<T>(); // 返回值
		
		try {
			// 循环数据
			for (T t : validList) {
				// 获取需要验证的字段
				for (ExcelAnnotation excelAnnotation : excelAnnotations) {
					if(excelAnnotation == null ) {
						continue;
					}
					notNull = excelAnnotation.notNull();		// 获取该字段是否可以为空
					// 如果不能为null则进行判断。获取该字段的值，进行比较
					if (notNull) {
						// 字段名称
						fieldName = excelAnnotation.field(); 
						// 获取方法
						method = ExcelUtil.searchMethod("get" + fieldName, clazz.getMethods());
						// 获取字段值
						fieldValue = method.invoke(t);
						// 如果为null或者为空说明是非法数据结束循环
						if (fieldValue == null || "".equals(fieldValue)) {
							invalidList.add(t);		// 在非法的数据中添加该条数据
							// 设置错误字段描述值
							method = ExcelUtil.searchMethod("setcwms" , clazz.getMethods());
							if(method != null){
								if(excelAnnotation.cellType() != null && "Enum".equals(excelAnnotation.cellType())){
									method.invoke(t,excelAnnotation.name()+",未找到系统对应数据");
								}else{
									method.invoke(t,excelAnnotation.name()+",不可为空");
								}
							}
							break;					// 跳出本行的验证
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return invalidList;
	}
	
}
