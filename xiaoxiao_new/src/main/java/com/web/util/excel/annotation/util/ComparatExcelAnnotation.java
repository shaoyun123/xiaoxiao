package com.web.util.excel.annotation.util;

import java.io.Serializable;
import java.util.Comparator;

import com.web.util.excel.annotation.anno.ExcelAnnotation;


/**
 * 
     * Title: ComparatExcelAnnotation.java    
     * Description: 排序
     * @author xucs    
     * Company: 江苏增宇信息科技开发有限公司  
     * Copyright: Copyright (c) 2016
     * @created 2016-8-23 下午5:12:58 
     * @update 2016-8-23 下午5:12:58 
     * @version 1.0
 */
public class ComparatExcelAnnotation implements Comparator<ExcelAnnotation>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 设置顺序
	public int compare(ExcelAnnotation excelAn1, ExcelAnnotation excelAn2) {
		if(excelAn1 == null || excelAn2 == null) {
			return 1;
		}
		if (excelAn1.orderNum() < excelAn2.orderNum()) {
			return -1;
		} else {
			return 1;
		}
	}
}
