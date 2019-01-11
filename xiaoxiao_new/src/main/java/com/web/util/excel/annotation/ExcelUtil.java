package com.web.util.excel.annotation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.RequestWrapper;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.web.model.XueSheng;
import com.web.util.excel.annotation.anno.ExcelAnnotation;
import com.web.util.excel.annotation.datavalidate.DataValidateFilter;
import com.web.util.excel.annotation.datavalidate.impl.DateValidateFilter;
import com.web.util.excel.annotation.datavalidate.impl.NotNullDataValidateFilter;
import com.web.util.excel.annotation.datavalidate.impl.SpecialCharDataValidateFilter;
import com.web.util.excel.annotation.util.ComparatExcelAnnotation;



/**
 * 
     * Title: ExcelUtil.java    
     * Description: excel工具
     * @author xucs    
     * Company: 江苏增宇信息科技开发有限公司  
     * Copyright: Copyright (c) 2016
     * @created 2016-8-23 下午3:26:53 
     * @update 2016-8-23 下午3:26:53 
     * @version 1.0
 */
public class ExcelUtil {
	
	/**
	 * 
	     * @discription 导入excel
	     * @author xucs       
	     * @created 2016-8-23 下午3:27:16  
	     * @update 2016-8-23 下午3:27:16   
	     * @param clazz Excel所导入的Class
	     * @param inputStream Excel文件输入流
	     * @param request 数据校验参数，可选参数
	     * @param fullTitle 全部的头名true（excel中的头与类里面的完全一样）false（ 可以不一样）
	     * @param dataValidateFilters 返回Excel中有效和无效的数据集合，以及ExcelAnnotation
	     * @return1、返回null 说明导入模板错误 2、返回数组长度为1并且第一个参数为null说明导入模板为2007及以上
	     * 版本其中报表@数据无法读入3、返回正常数据 数组长度为3，第一个数据为头，第二个数据为正常可保存数据，第三个为验证不通过数据
	 */
	@SuppressWarnings({"static-access" })
	public static <T> Object[] importExcel(Class<T> clazz, InputStream inputStream, HttpServletRequest request,
			boolean fullTitle, Map<String, Map<String, Object>> dataMap, DataValidateFilter... dataValidateFilters) {
		List<T> validList = new ArrayList<T>();
		List<T> invalidList = new ArrayList<T>();
		List<ExcelAnnotation> excelAnnotations = null;
		
		// 声明临时变量
		Method _method = null; 		// 方法
		Object _cellValue = null; 	// 单元格
		String _cellContent = null;	// 单元格内容
		ExcelAnnotation _excelAnnotation = null;	// 注解对象
		Cell _cell = null;	// 单元格
		Row _row = null;	// 行
		T instance = null;		// 对象
		try {
			Workbook workbook = new WorkbookFactory().create(inputStream);		// 获取Excel工作簿
			Field[] fields = clazz.getDeclaredFields();					// 获取clazz中定义的所有字段，找到需要导出到Excel模板的字段
			//excelAnnotations = getExcelAnnotation(fields);			// 获取Excel注解ExcelAnnotation
			Sheet sheet = workbook.getSheetAt(0);					// 获取第一个工作簿
			Method[] methods = clazz.getDeclaredMethods();
			boolean isBlankRow = true;									// 是否为空白行
			boolean isTypeError = false;								// 是否是类型错误
			int spanNum = 0;											// 跨越几个下标
			// 获取第一行头
			Row _rowTemp = sheet.getRow(0);
			// 获取每个单元格对应数据
			int temInt = 0;
			// 临时头map数据
			Map<String,String> tempMap = new HashMap<String,String>();
			// 临时导入的表头list
			List<String> tempList = new ArrayList<String>();
			String name = "";
			// 循环所有数据添加到map里
			while(_rowTemp.getCell(temInt) != null){
				try{
					// 获取头名称
					name = _rowTemp.getCell(temInt).getStringCellValue();
					// 如果去名称错误设置名称为---
					if(name == null || "".equals(name)){
						name = "---";
					}
					// 将名称放入map
					tempMap.put(name, name);
					// 添加名称的集合
					tempList.add(name);
					// 变量加一取下行数据
					temInt ++;
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 获取实体里所有的字段
			excelAnnotations = getExcelAnnotation(fields,tempList);
			// 判断表头是否匹配
			if (!compareHeader(sheet, excelAnnotations, fullTitle)) {
				return null;
			}
			// 开始按照excelAnnotations的顺序读取数据
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				spanNum = 0;
				// 实例化
				try {
					instance = clazz.newInstance();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				}
				// 空行
				isBlankRow = true;
				// 类型判断
				isTypeError = false;
				// 遍历每列
				for (int j = 0; j < excelAnnotations.size(); j++) {
					// 获取列的头信息
					_excelAnnotation = excelAnnotations.get(j);
					if(_excelAnnotation == null) {
						continue;
					}
					// 获取单元格
					_row = sheet.getRow(i);
					// 单元格不为空
					if (_row == null) {
						continue;
					}
					// 获取单元格
					_cell = _row.getCell(j+spanNum);
					// 获取方法
					_method = searchMethod("set" + _excelAnnotation.field(), methods);
					// 单元格
					_cellValue = null;
					
					// 判断cell是否为空，根据每个字段的类型，进行类型转换
					if (_cell != null) {
						// 单元格内容
						_cellContent = _cell.toString().trim();
						// 为空判断
						if (_cellContent == null || "".equals(_cellContent)) {
							continue;
						} else {
							isBlankRow = false;
						}
						// 提取每个字段的值
						if ("Enum".equals(_excelAnnotation.cellType())) {	// 判断是否为下拉菜单，如果是，则将数据转换成 下拉菜单中的 隐藏值
							_cellValue = convertEnum(_cellContent, _excelAnnotation, dataMap, instance);
							if(_cellValue == null || _cellValue.equals(_cellContent)){
								_cellValue = null;
								isTypeError = true;
								_method = ExcelUtil.searchMethod("setcwms" , clazz.getMethods());
								if(_method != null){
									_method.invoke(instance,_excelAnnotation.name()+",未在数据中找到该数据！");
								}
								_method = ExcelUtil.searchMethod("setrownum" , clazz.getMethods());
								if(_method != null){
									_method.invoke(instance,""+(i+1));
								}
								
							}
						} else if ("String".equals(_excelAnnotation.fieldType())) {
							// 这里去掉小数点和后面的数字，因为excel会自动给数字加上 .0
							int index = _cellContent.lastIndexOf(".");	// 小数点所在的位置
							if ( index > 0 && _cellContent.substring(index + 1, _cellContent.length()).equals("0")) {
								_cellContent = _cellContent.substring(0, index);
							}
							if(_cell.getCellType() == 0){
								_cellContent = ((int)_cell.getNumericCellValue())+"";
							}
							_cellValue = _cellContent;
							// 如果是Double转换Double类型并赋值
						} else if ("Double".equals(_excelAnnotation.fieldType()) || "double".equals(
								_excelAnnotation.fieldType())) {
							BigDecimal big = new BigDecimal(_cell.getStringCellValue());
							_cellValue = big.setScale(_excelAnnotation.scale(), BigDecimal.ROUND_HALF_UP).doubleValue();
							_cellValue = _cellValue == null ? "" : _cellValue;
							// 如果是Integer转换Integer类型并赋值
						} else if ("Integer".equals(_excelAnnotation.fieldType()) || "int".equals(
								_excelAnnotation.fieldType())) {
							_cellValue = (int) _cell.getNumericCellValue();
							_cellValue = _cellValue == null ? 0 : _cellValue;
							// 如果是Long转换Long类型并赋值
						} else if ("Long".equals(_excelAnnotation.fieldType()) || "long".equals(
								_excelAnnotation.fieldType())) {
							Date date = null;
							try{
								date = _cell.getDateCellValue();
							}catch (Exception e) {
								// 异常说明类型错误
								isTypeError = true;
								// 设置为null
								date = null;
								// 设置信息描述
								_method = ExcelUtil.searchMethod("setcwms" , clazz.getMethods());
								if(_method != null){
									_method.invoke(instance,_excelAnnotation.name()+",日期类型错误");
								}
								_method = ExcelUtil.searchMethod("setrownum" , clazz.getMethods());
								if(_method != null){
									_method.invoke(instance,""+(i+1));
								}
							}
							if (date == null) {
								continue;
							} else {
								_cellValue = date.getTime();
							}
							_cellValue = _cellValue == null ? 0 : _cellValue;
							// 如果是Long转换Long类型并赋值
						} else if ("Date".equals(_excelAnnotation.fieldType())) {
							Date date = null;
							try{
								date = _cell.getDateCellValue();
							}catch (Exception e) {
								// 异常说明类型错误
								isTypeError = true;
								// 设置为null
								date = null;
								_method = ExcelUtil.searchMethod("setcwms" , clazz.getMethods());
								if(_method != null){
									_method.invoke(instance,_excelAnnotation.name()+",日期类型错误");
								}
								_method = ExcelUtil.searchMethod("setrownum" , clazz.getMethods());
								if(_method != null){
									_method.invoke(instance,""+(i+1));
								}
							}
							if (date == null) {
								continue;
							} else {
								_cellValue = date;
							}
							_cellValue = _cellValue == null ? 0 : _cellValue;
						}
					}
					// 对字段赋值
					_method.invoke(instance, _cellValue);
					
					_method = ExcelUtil.searchMethod("setrownum" , clazz.getMethods());
					if(_method != null){
						_method.invoke(instance,""+(i+1));
					}
				}
				// 如果不是类型错误并且只有不为空白行，才将此行加入结果集中
				if (!isTypeError && !isBlankRow) {
					validList.add(instance);
				}
				// 如果类型错误添加到错误数据中
				if(isTypeError){
					invalidList.add(instance);
				}
			}
		} catch (IllegalAccessException e) {
			return new Object[]{null};
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			return new Object[]{null};
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			return new Object[]{null};
		}	catch (InvalidFormatException e1) {
			// TODO Auto-generated catch block
			return new Object[]{null};
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			return new Object[]{null};
		}	
		
		// 执行系统内定的数据校验过滤器
		List<T> invalidDatas = null;
		// 非空验证
		invalidDatas = new NotNullDataValidateFilter().validate(validList, excelAnnotations, clazz);
		removeInvalidDatas(invalidDatas, validList, invalidList);
		// 特殊字符验证
		invalidDatas = new SpecialCharDataValidateFilter().validate(validList, excelAnnotations, clazz);
		removeInvalidDatas(invalidDatas, validList, invalidList);
		// 日期大小验证
		invalidDatas = new DateValidateFilter().validate(validList, excelAnnotations, clazz);
		removeInvalidDatas(invalidDatas, validList, invalidList);
		
		// 执行自定义数据校验过滤器
		if (dataValidateFilters != null && dataValidateFilters.length > 0) {
			for (DataValidateFilter dataValidateFilter : dataValidateFilters) {
				invalidDatas = dataValidateFilter.validate(validList, excelAnnotations, clazz);
				removeInvalidDatas(invalidDatas, validList, invalidList);
			}
		}
		
		// 返回Excel导入结果。validList为导入的合法的数据，invalidList为导入的非法的数据
		return new Object[]{getHeaders(excelAnnotations), validList, invalidList};
	}

	/**
	 * 
	     * @discription 获取Excel表头
	     * @author xucs       
	     * @created 2016-8-23 下午3:28:09  
	     * @update 2016-8-23 下午3:28:09   
	     * @param excelAnnotations 报表头注解集合
	     * @return
	 */
	private static List<String> getHeaders(List<ExcelAnnotation> excelAnnotations) {
		// 判断空值
		if (excelAnnotations == null || excelAnnotations.size() <= 0) {
			return null;
		}
		
		// 初始化返回值
		List<String> headers = new ArrayList<String>();
		
		// 开始遍历产生表头
	    for (int i = 0; i < excelAnnotations.size(); i++) {
	    	if(excelAnnotations.get(i) == null ) {
	    		continue;
	    	}
	    	headers.add(excelAnnotations.get(i).name());
	    }
	    
	    return headers;
	}
	
	
	/**
	 * 
	     * @discription 比较excel中的表头和原定义的表头是否相同，是则返回true，否则返回false
	     * @author xucs       
	     * @created 2016-8-23 下午3:28:25  
	     * @update 2016-8-23 下午3:28:25   
	     * @param sheet excel工作簿
	     * @param excelAnnotations  头信息
	     * @param fullTitle 全部头
	     * @return
	 */
	private static boolean compareHeader(Sheet sheet, List<ExcelAnnotation> excelAnnotations,boolean fullTitle) {
		if (sheet == null || sheet.getRow(0) == null || (excelAnnotations == null || excelAnnotations.size() <= 0)) {
			return false;
		}
		// 如果需要全部头 如果excel表头列数 不等于 excelAnnotations的个数，则表示excel中的表头和原定义的表头不同，返回false
		if(fullTitle){
			if (sheet.getRow(0).getLastCellNum() != excelAnnotations.size()) {
				return false;
			}
		}
		//  如果需要全部头 如果excel中的表头和原定义的表头里面定义的内容不同，则返回false，否则返回true
		if(fullTitle){
		Row _row = sheet.getRow(0);
			for (int i = 0; i < _row.getLastCellNum(); i++) {
				if (!_row.getCell(i).getStringCellValue().equals(excelAnnotations.get(i).name())) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 
	     * @discription 获取ExcelAnnotation注解
	     * @author xucs       
	     * @created 2016-8-23 下午3:29:04  
	     * @update 2016-8-23 下午3:29:04   
	     * @param fields 字段集合
	     * @param titleList 导入excel的头集合
	     * @return
	 */
	private static List<ExcelAnnotation> getExcelAnnotation(Field[] fields,List<String> titleList) {
		if (fields == null || fields.length == 0) {
			return null;
		}
		
		List<ExcelAnnotation> annotations = new ArrayList<ExcelAnnotation>();	// 初始化返回值
		// 临时变量
		ExcelAnnotation annotation = null;
		// 导入的正确头
		List<String> importTitleList = new ArrayList<String>();
		// 临时map集合
		Map<String,ExcelAnnotation> tempMap = new HashMap<String,ExcelAnnotation>();
		// 遍历获取每个字段的ExcelAnnotation
		for (int i = 0; i < fields.length; i++) {
			// 获取对象
			annotation = fields[i].getAnnotation(ExcelAnnotation.class);
			// 如果对象不为空添加正确excel头并以name为名称添加到正确map集合里
			if (annotation != null ) {
				importTitleList.add(annotation.name());
				tempMap.put(annotation.name(), annotation);
			}
		}
		// 循环导入excel的头集合如果在正确的里面有添加这个导入对象否则添加一个null对象
		for(int i = 0 ; i < titleList.size() ; i ++){
			if(importTitleList.contains(titleList.get(i))){
				annotations.add(tempMap.get(titleList.get(i)));
			}else{
				annotations.add(null);
			}
		}
		// 取消对数据的排序原因是有null的情况这样必须设置导入顺序
		// 将获取的字段Annotation，按照orderNum进行排序
		Collections.sort(annotations, new ComparatExcelAnnotation());
		return annotations;
	}
	
	/**
	 * 
	     * @discription 查找方法
	     * @author xucs       
	     * @created 2016-8-23 下午3:29:27  
	     * @update 2016-8-23 下午3:29:27   
	     * @param methodName 方法名称
	     * @param methods 方法集合
	     * @return
	 */
	public static Method searchMethod(String methodName, Method[] methods) {
		if (methods == null || methods.length == 0) {
			return null;
		}
		// 循环方法集合 如果匹配上就返回数据
		for (Method method : methods) {
			if (method.getName().equalsIgnoreCase(methodName)) {
				return method;
			}
		}
		return null;
	}

	/**
	 * 
	     * @discription 通过enumName查找到enumName所对应的隐藏值
	     * @author xucs       
	     * @created 2016-8-23 下午3:29:44  
	     * @update 2016-8-23 下午3:29:44   
	     * @param enumName  需要查找的name
	     * @param enumValue 内存字典的code
	     * @return
	 */
	private static Object convertEnum(String content, ExcelAnnotation _excelAnnotation, Map<String, Map<String, Object>> dataMap, Object o) {
		if(content == null || "".equals(content)){
			return content;
		}
		if(dataMap != null && dataMap.size() > 0){
			Map<String,Object> map = dataMap.get(_excelAnnotation.enumExportValue());
			if(map != null && map.size() > 0){
				content = map.get(content) == null ? null : map.get(content).toString();
			}else{
				content = null;
			}
		}
		return content;
	}
	
	private static Method getter(String field, Object o) throws NoSuchMethodException, SecurityException {
		String pre = field.substring(0, 1);
		String suf = field.substring(1);
		String name = "get" + pre.toUpperCase() + suf;
		Method method = o.getClass().getDeclaredMethod(name);
		return method;
	}
	
	/**
	 * 
	     * @discription 从合法数据中移除非法数据，并在非法数据中添加上该数据
	     * @author xucs       
	     * @created 2016-8-23 下午3:30:06  
	     * @update 2016-8-23 下午3:30:06   
	     * @param invalidDatas 非法数据
	     * @param validList 正常数据
	     * @param invalidList 最终的非法数据
	 */
	private static <T> void removeInvalidDatas(List<T> invalidDatas, List<T> validList, List<T> invalidList) {
    	if (invalidDatas == null || invalidDatas.size() <= 0) {
    		return;
    	}
    	// 删除所有非法数据
    	validList.removeAll(invalidDatas);
    	// 添加非法数据
		invalidList.addAll(invalidDatas);
		
		// 清空非法数据
		invalidDatas.clear();
    }
	
	
	
	
	
	
	
	
	
	
	
//	
//	private static List<ExcelAnnotation> getExcelAnnotation(Field[] fields) {
//		if (fields == null || fields.length == 0) {
//			return null;
//		}
//		List<ExcelAnnotation> annotations = new ArrayList<ExcelAnnotation>();	// 初始化返回值
//		// 临时变量
//		ExcelAnnotation annotation = null;
//		// 遍历获取每个字段的ExcelAnnotation
//		for (int i = 0; i < fields.length; i++) {
//			// 获取对象
//			annotation = fields[i].getAnnotation(ExcelAnnotation.class);
//			// 如果对象不为空添加正确excel头并以name为名称添加到正确map集合里
//			if (annotation != null ) {
//				annotations.add(annotation);
//			}
//		}
//		return annotations;
//	}
//    
//	
	/*public static void main(String[] args){
		File media = new File("D:\\test.xlsx");
		InputStream inputStream = null; // 获取上传的excel
		try {
			inputStream = new FileInputStream(media);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Map<String, Object>> dataMap = new HashMap<String, Map<String, Object>>();
		Object[] object = ExcelUtil.importExcel(XueSheng.class, inputStream, null, false, dataMap);
		//Object[] object = excelutil.importExcel(XueSheng.class, inputStream, null, false, dataMap);
		System.out.println();
	}*/
	
	
}
