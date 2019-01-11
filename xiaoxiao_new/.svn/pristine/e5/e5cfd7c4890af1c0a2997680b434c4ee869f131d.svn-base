package com.web.util.excel.annotation.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
     * Title: ExcelAnnotation.java    
     * Description: excel注解类
     * @author xucs    
     * Company: 江苏增宇信息科技开发有限公司  
     * Copyright: Copyright (c) 2016
     * @created 2016-8-23 下午5:36:44 
     * @update 2016-8-23 下午5:36:44 
     * @version 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAnnotation {
	
	/**
	 * 字段名称
	 */
	String field();
	
	/**
	 * 字段类型
	 */
	String fieldType();
	
	/**
	 * 字段中文名称
	 */
	String name();
	
	/**
	 * 排序字段，用于生成Excel模板时，表头字段的循序
	 */
	int orderNum();
	
	/**
	 * 字段最小长度
	 */
	String minLength() default "1";
	
	/**
	 * 字段最大长度
	 */
	String maxLength() default "32000";
	
	/**
	 * 字段类型
	 */
	String cellType() default "CELL_TYPE_STRING";
	
	/**
	 * 日期开始时间
	 */
	String beginDate() default "1970-1-1";
	
	/**
	 * 日期结束时间
	 */
	String endDate() default "2099-12-31";
	
	/**
	 * 字段最小值
	 */
	String minValue() default "0";
	
	/**
	 * 字段最大值
	 */
	String maxValue() default "0";
	
	/**
	 * 枚举类型数据。此处数据必须是Map类型的数据，key存放真实值，value存放显示值
	 */
	String enumValue() default "";
	
	/**
	 * 枚举类型数据。此处数据必须是Map类型的数据，key存放真实值，value存放显示值
	 */
	String enumExportValue() default "";
	
	/**
	 * 字段是否可以为空
	 */
	boolean notNull() default false;
	
	/**
	 * 字段是否进行特殊字符验证
	 */
	boolean openSpecial() default false;
	
	/**
	 * 特殊字符验证
	 */
	String exceptSpecialChar() default "";
	
	/**
	 * 日期验证
	 * @return
	 */
	String dateValidate() default "";
	
	/**
	 * 设置Decimal类型小数位数，默认两位
	 */
	int scale() default 2;
	/**
	 * 单元格长度
	 * @return
	 */
	int columnWidth() default 5000;
	/**
	 * 时间格式
	 * @return
	 */
	String dateFormat() default "yyyy-MM-dd";
	/**
	 * 
	 * @return
	 */
	String before() default "";
	/**
	 * 上级
	 * @return
	 */
	String parent() default "";
}
