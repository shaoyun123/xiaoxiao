package com.web.util.excel.annotation.datavalidate.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.web.util.excel.annotation.ExcelUtil;
import com.web.util.excel.annotation.anno.ExcelAnnotation;
import com.web.util.excel.annotation.datavalidate.DataValidateFilter;



/**
 * 
     * Title: DateValidateFilter.java    
     * Description: 时间类型验证
     * @author xucs    
     * Company: 江苏增宇信息科技开发有限公司  
     * Copyright: Copyright (c) 2016
     * @created 2016-8-23 下午5:13:58 
     * @update 2016-8-23 下午5:13:58 
     * @version 1.0
 */
public class DateValidateFilter implements DataValidateFilter {

	public <T> List<T> validate(List<T> validList, List<ExcelAnnotation> excelAnnotations, Class<T> clazz) {
		// 为null判断
		if ( validList == null || "".equals(validList)) {
			return null;
		}
		
		// 声明临时变量
		String dateValidate = null; // 数据值
		String fieldName = null;	// 字段名称
		Object fieldValue = null;	// 字段值
		Method method = null;		// 方法
		List<Object[]> dateValidateInfoList = new ArrayList<Object[]>(); // 临时数据变量
		List<T> invalidList = new ArrayList<T>(); // 返回值
		String[] groupInfo = null; // 临时信息
		String tempFieldName = ""; // 临时字段名称标识错误字段位置
		try {
			// 循环所有数据集合
			for (T t : validList) {
				tempFieldName = "";
				// 获取需要验证的字段
				for (ExcelAnnotation excelAnnotation : excelAnnotations) {
					if(excelAnnotation == null ) {
						continue;
					}
					dateValidate = excelAnnotation.dateValidate();		// 获取该字段是否需要验证日期
					// 如果需要进行日期验证
					if (dateValidate != null && !"".equals(dateValidate) && "Date".equals(excelAnnotation.cellType())) {
						// 获取字段名称
						fieldName = excelAnnotation.field();
						// 如果有错误字段以逗号隔开
						if(!"".equals(tempFieldName)){
							tempFieldName += ","+excelAnnotation.name();
						}else{
							tempFieldName += excelAnnotation.name();
						}
						// 获取方法
						method = ExcelUtil.searchMethod("get" + fieldName, clazz.getMethods());
						// 获取字段值
						fieldValue = method.invoke(t);
						// 阶段字段
						groupInfo= dateValidate.split(" ");
						// 如果是long类型进行时间添加数据如果是Date添加数据 都转换类型为Date 否则跳出循环
						if ("long".equals(excelAnnotation.fieldType()) || "Long".equals(excelAnnotation.fieldType())) {
							dateValidateInfoList.add(new Object[]{groupInfo[0], groupInfo[1], new Date((Long)fieldValue)});
						} else if ("Date".equals(excelAnnotation.fieldType())) {
							dateValidateInfoList.add(new Object[]{groupInfo[0], groupInfo[1], (Date) fieldValue});
						} else {
							continue;
						}
					}
				}
				// 开始验证，如果验证不通过，则在非法数据中添加
				if (!checkDateValidate(dateValidateInfoList)) {
					invalidList.add(t);
					// 设置错误字段描述值
					method = ExcelUtil.searchMethod("setcwms" , clazz.getMethods());
					if(method != null){
						method.invoke(t,tempFieldName+",日期大小错误");
					}
				}
				// 清空临时变量
				dateValidateInfoList.clear();
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
	     * @discription 校验日期。对传递过来的日期校验集，进行分组。
	     * @author xucs       
	     * @created 2016-8-23 下午5:36:09  
	     * @update 2016-8-23 下午5:36:09   
	     * @param dateValidateInfoList 数据集合
	     * @return
	 */
	private boolean checkDateValidate(List<Object[]> dateValidateInfoList) {
		if (dateValidateInfoList == null || dateValidateInfoList.size() <= 0) {
			return true;
		}
		// 首先获取一共有几组，获取他们的组名，放在Set中
		Set<String> groupNameSet = new HashSet<String>();
		for (Object[] dateValidateInfo : dateValidateInfoList) {
			groupNameSet.add((String)dateValidateInfo[0]);
		}
		// 开始分组，把dateValidateInfoList按照groupName分成若干组
		List<Object[]> subList = new ArrayList<Object[]>();
		Iterator<String> ite = groupNameSet.iterator();
		String groupName = null;
		while (ite.hasNext()) {		// 遍历每一个组
			groupName = ite.next();
			// 遍历获取该组的dateValidateInfo
			for (Object[] dateValidateInfo : dateValidateInfoList) {
				if (dateValidateInfo[0].equals(groupName)) {
					subList.add(dateValidateInfo);
				}
			}
			// 获取本组所有的数据后，开始比较，如果不符合要求，则直接返回false，以后的程序验证不再执行
			if (!compareDate(subList)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 
	     * @discription 比较日期大小，按照Date数组的顺序从小到大比较
	     * @author xucs       
	     * @created 2016-8-23 下午5:36:24  
	     * @update 2016-8-23 下午5:36:24   
	     * @param dateValidateInfoList 数据集合
	     * @return
	 */
	private boolean compareDate(List<Object[]> dateValidateInfoList) {
		// 为空判断，如果为null，或者dateValidateInfoList的长度为0个或1个，则不用比较，直接返回true
		if (dateValidateInfoList == null || dateValidateInfoList.size() < 2) {
			return true;
		}
		
		// 对dateValidateInfo进行排序，按照比较的顺序
		Collections.sort(dateValidateInfoList, new Comparator<Object[]>(){
			// 比较数据
			public int compare(Object[] obj1, Object[] obj2) {
				if (Integer.parseInt((String)obj1[1]) < Integer.parseInt((String)obj2[1])) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		
		// 开始比较。比较之前必须保证 该组的每个日期都不为null，或日期不为1970-1-1 08:00:00，否则不予比较，直接返回true
		Object[] obj = null;
		for(int i = 0; i < dateValidateInfoList.size(); i++) {
			obj = dateValidateInfoList.get(i);
			if (obj[2] == null || ((Date)obj[2]).getTime() == 0) {
				return true;
			}
		}
		
		// 判定完每个日期不为null后，开始比较
		for(int i = 0; i < dateValidateInfoList.size() - 1; i++) {
			if (((Date)dateValidateInfoList.get(i)[2]).getTime() > ((Date)dateValidateInfoList.get(i+1)[2]).getTime()) {
				return false;
			}
		}
		
		return true;
	}
}
