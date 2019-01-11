package com.web.util.excel.annotation.datavalidate.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.web.util.excel.annotation.ExcelUtil;
import com.web.util.excel.annotation.anno.ExcelAnnotation;
import com.web.util.excel.annotation.datavalidate.DataValidateFilter;



/**
 * 
     * Title: SpecialCharDataValidateFilter.java    
     * Description: 特殊字符验证 
     * @author xucs    
     * Company: 江苏增宇信息科技开发有限公司  
     * Copyright: Copyright (c) 2016
     * @created 2016-8-23 下午5:13:33 
     * @update 2016-8-23 下午5:13:33 
     * @version 1.0
 */
public class SpecialCharDataValidateFilter implements DataValidateFilter {
	
	public <T> List<T> validate(List<T> validList, List<ExcelAnnotation> excelAnnotations, Class<T> clazz) {
		// 为null判断
		if (validList == null || validList.size() <= 0 ) {
			return null;
		}
		
		// 声明临时变量
		boolean openSpecial = false;	// 是否是特殊字符
		String fieldName = null;		// 字段名称
		Object fieldValue = null;		// 字段值
		Method method = null;			// 方法
		String specialCharacters = null;// 字段特殊字符
		List<T> invalidList = new ArrayList<T>(); // 返回值
		
		try {
			// 循环数据
			for (T t : validList) {
				// 获取需要验证的字段
				for (ExcelAnnotation excelAnnotation : excelAnnotations) {
					if(excelAnnotation == null ) {
						continue;
					}
					openSpecial = excelAnnotation.openSpecial();		// 获取该字段是否启用特殊字符验证
					
					// 如果启用特殊字符验证，并且该字段为字符串类型，则进行判断。获取该字段的值，进行比较
					if (openSpecial && "String".equals(excelAnnotation.cellType())) {
						fieldName = excelAnnotation.field();
						method = ExcelUtil.searchMethod("get" + fieldName, clazz.getMethods());
						fieldValue = method.invoke(t);
						if (fieldValue == null) {
							continue; 
						}
						// 设置特殊字符
						specialCharacters = "[`~!@$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
						// 获取特殊字符
						if (excelAnnotation.exceptSpecialChar() != null && !"".equals(
								excelAnnotation.exceptSpecialChar())) {
							specialCharacters = specialCharacters.replaceAll("[" + 
									excelAnnotation.exceptSpecialChar() + "]", "");
						}
						if (hasSpecialCharacters(fieldValue.toString(), specialCharacters)) {
							invalidList.add(t);		// 在非法的数据中添加该条数据
							// 设置错误字段描述值
							method = ExcelUtil.searchMethod("setcwms" , clazz.getMethods());
							if(method != null){
								method.invoke(t,excelAnnotation.name()+",特殊字符验证错误");
							}
							break;					// 跳出本行的验证
						}
					}
				}
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return invalidList;
	}
	/**
	 * 
	 * @方法名：判断指定字符串是否含有特殊字符
	 * @功能说明：
	 * @author xucs
	 * @date  Jun 20, 2013 9:41:47 AM
	 * @param str 验证字符串
	 * @param specialCharacters 特殊字符
	 * @return
	 */
	private boolean hasSpecialCharacters(String str, String specialCharacters) {
		// 传入数据不为空
		if (str == null || str.length() == 0) {
			return false;
		}
		String _char = null;
		// 设置比较值
		Pattern pattern = Pattern.compile(specialCharacters);
		// 循环数据进行比较是否有特殊字符
		for (int i = 0; i < str.length(); i++) {
			_char = str.substring(i,  i+1);
			if (pattern.matcher(_char).matches()) {
				return true;
			}
		}
		
		return false;
	}
	/* (non-Javadoc)
	 * @see cn.com.zytech.sdk.excel.annotation.datavalidate.DataValidateFilter#validate(java.util.List, java.util.List, java.lang.Class)
	 */
}
