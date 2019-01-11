package com.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author 54412
 *
 */
/**
 * @author 54412
 *
 */
public class Util {
	/**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
           Pattern pattern = Pattern.compile("[0-9]*");
           Matcher isNum = pattern.matcher(str);
           if( !isNum.matches() ){
               return false;
           }
           return true;
    }
    
    /**
     * 检查session是否为空
     * @param request
     * @return true or false
     */
    public static boolean checkSession(HttpServletRequest request) {
    		HttpSession session = request.getSession();
    		if (session.getAttribute("user")!=null && session.getAttribute("user")!="") {
    			return true;
    		}else {
    			return false;
    		}
    	}
    
    /**
     * 获取两个日期之间相差的天数
     * @param String date1, String date2, date2>date1
     * @return int days
     * @throws ParseException 
     */
    public static int daysBetween(String date1,String date2) throws ParseException {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Date d1 = sdf.parse(date1);
    	Date d2 = sdf.parse(date2);
    	int days = (int) ((d2.getTime() - d1.getTime()) / (1000*3600*24));
    	return days;
		
	}
    
    /**
     * 判断登录用户身份是否辅导员
     * @param request
     * @return true or false
     */
    public static boolean isFuDaoYuan(HttpServletRequest request) {
    	if (request.getSession().getAttribute("status")!=null) {
    		String status=(String) request.getSession().getAttribute("status");
    		if (status.equals("fudaoyuan")) {
    			return true;
    		}else {
    			return false;
    		}
    	}else{
    		return false;
    	}
	}
    
    /**
     * 判断登录用户身份是否学生
     * @param request
     * @return true or false
     */
    public static boolean isXueSheng(HttpServletRequest request) {
    	if (request.getSession().getAttribute("status")!=null) {
    		String status=(String) request.getSession().getAttribute("status");
    		if (status.equals("xuesheng")) {
    			return true;
    		}else {
    			return false;
    		}
    	}else{
    		return false;
    	}
	}
    
    /**
     * 判断登录用户身份是否管理员
     * @param request
     * @return true or false
     */
    public static boolean isGuanLiYuan(HttpServletRequest request) {
    	if (request.getSession().getAttribute("status")!=null) {
    		String status=(String) request.getSession().getAttribute("status");
    		if (status.equals("guanliyuan")) {
    			return true;
    		}else {
    			return false;
    		}
    	}else{
    		return false;
    	}
	}
    
    /**
     * 判断登录用户身份是否教师
     * @param request
     * @return true or false
     */
    public static boolean isJiaoShi(HttpServletRequest request) {
    	if (request.getSession().getAttribute("status")!=null) {
    		String status=(String) request.getSession().getAttribute("status");
    		if (status.equals("jiaoshi")) {
    			return true;
    		}else {
    			return false;
    		}
    	}else{
    		return false;
    	}
	}
    
    /**
     * 判断登录用户身份是否书记
     * @param request
     * @return true or false
     */
    public static boolean isShuJi(HttpServletRequest request) {
    	if (request.getSession().getAttribute("status")!=null) {
    		String status=(String) request.getSession().getAttribute("status");
    		if (status.equals("shuji")) {
    			return true;
    		}else {
    			return false;
    		}
		}else {
			return false;
		}
    	
	}
    
    
    /**
     * sha1加密 
     * @param String
     * @return String
     */
    public static String SHA1Encode(String string) {
    	return DigestUtils.sha1Hex(string).toString();
	}
    
    /**
     * 两个日期之间的周数
     * @param date1，date2
     * @return weeks
     */
    public static int WeeksBetweenDays(Date d1,Date d2) {
    	long n1 = d1.getTime();
    	long n2 = d2.getTime();
    	long diff = Math.abs(n1 - n2);
    	diff /= 3600 * 1000 * 24;
    	diff++;
    	if(diff % 7 != 0){
    		return (int)(diff / 7 + 1);
    	}else {
    		return (int)(diff / 7);
		}
	}
    
    /**
     * 产生指定位数的随机数
     * */
    public static String getRandomNumCode(int number){  
        String codeNum = "";  
            int [] numbers = {0,1,2,3,4,5,6,7,8,9};  
            Random random = new Random();  
            for (int i = 0; i < number; i++) {  
                int next = random.nextInt(10000);//目的是产生足够随机的数，避免产生的数字重复率高的问题    
                codeNum+=numbers[next%10];  
            }  
        return codeNum;   
    }
 }
 
