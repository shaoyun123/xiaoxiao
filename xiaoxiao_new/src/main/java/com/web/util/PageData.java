package com.web.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
@SuppressWarnings({"unchecked","rawtypes"})
public class PageData extends HashMap implements Map{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @discription 将数据转成对象
	 * @author xucs       
	 * @created 2017-6-7 下午6:42:23  
	 * @update 2017-6-7 下午6:42:23   
	 * @param _class
	 * @return
	 */
	public <T> Object getDataForObject(Class<T> _class){
		Iterator entries = map.keySet().iterator();
		T instance = null;
		try {
			// 实例化对象
			instance = _class.newInstance();
			// 临时字符串
			String tempString = "";
			// 获取类的方法
			Method[] methods = _class.getDeclaredMethods();
			// 方法对象
			Method _method = null;
			// get方法对象
			Method _methodGet = null;
			// 临时串
			String temStr = "";
			// 类型字符串
			String typeString = "";
			// 顶级对象
			Object obj = null;
			// 循环数据如果有下一个继续循环
			while(entries.hasNext()){
				// 获取对象key
				tempString = (String) entries.next();
				// 获取set方法
				_method = searchMethod("set" + tempString, methods);
				// 获取get方法
				_methodGet = searchMethod("get" + tempString, methods);
				// 如果方法不为空
				if(_method != null){
					try {
						// 获取get方法返回的类型字符串
						typeString = _methodGet.getReturnType().toString();
						// 截取字符串
						temStr = typeString.split("\\.")[typeString.split("\\.").length - 1];
						// 如果是日期类型
						if(temStr.toUpperCase().equals("DATE")){
							// 获取数据
							obj = map.get(tempString);
							// 如果值不为空将值转成DATA类型
							if(map.get(tempString) != null){
								obj = DateUtil.fomatDate(obj.toString(),"yyyy-MM-dd");
							}
							// 如果是TIMESTAMP类型转换将数据转换为TIMESTAMP类型数据
						}else if(temStr.toUpperCase().equals("TIMESTAMP")){
							obj = map.get(tempString);
							if(map.get(tempString) != null){
								obj = DateUtil.fomatDateToTimestamp(obj.toString(),"yyyy-MM-dd HH:mm:ss");
							}
						}else{
							// 否则就直接获取数据
							obj = map.get(tempString);
						}
						// 执行set方法
						_method.invoke(instance,obj);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
		return instance;
	}
	
	/**
	 * 
	 * @discription 查找某个类的方法
	 * @author xucs       
	 * @created 2017-6-7 下午6:46:57  
	 * @update 2017-6-7 下午6:46:57   
	 * @param methodName 方法名称
	 * @param methods 方法集合
	 * @return
	 */
	private static Method searchMethod(String methodName, Method[] methods) {
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
	
	
	Map map = null;
	Map dataMap = null;
	HttpServletRequest request;
	
	
	public PageData(HttpServletRequest request){
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Map.Entry entry; 
		String name = "";  
		String value = "";  
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next(); 
			name = (String) entry.getKey(); 
			Object valueObj = entry.getValue(); 
			if(null == valueObj){ 
				value = ""; 
			}else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value = values[i] + ",";
				}
				value = value.substring(0, value.length()-1); 
			}else{
				value = valueObj.toString(); 
			}
			returnMap.put(name, value); 
		}
		map = returnMap;
	}
	
	public PageData() {
		map = new HashMap();
	}
	
	@Override
	public Object get(Object key) {
		Object obj = null;
		if(map.get(key) instanceof Object[]) {
			Object[] arr = (Object[])map.get(key);
			obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	
	public String getString(Object key) {
		return get(key) == null ? "" : get(key).toString();
	}
	
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}
	
	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public Set entrySet() {
		return map.entrySet();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set keySet() {
		return map.keySet();
	}

	public void putAll(Map t) {
		map.putAll(t);
	}

	public int size() {
		return map.size();
	}

	public Collection values() {
		return map.values();
	}
	
}
