package com.ucomponent.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
/**
 * 2018年6月20日
 * 代码老哥
 * NAME:对象工具类
 * Descp:
**/
public class ObjectTools {
	public static Field[] getObject(Object model) {
		Field[] field = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		try {
			// 遍历所有属性
			for (int j = 0; j < field.length; j++) { 
				// 获取属性的名字
				String name = field[j].getName(); 
				// 将属性的首字符大写，方便构造get，set方法
				name = name.substring(0, 1).toUpperCase() + name.substring(1); 
				// 获取属性的类型
				String type = field[j].getGenericType().toString(); 
				// 如果type是类类型，则前面包含"class"，后面跟类名
				if (type.equals("class java.lang.String")) { 
					Method m = model.getClass().getMethod("get" + name);
					// 调用getter方法获取属性值
					String value = (String) m.invoke(model); 
					if (value == null) {
						m = model.getClass().getMethod("set" + name, String.class);
						m.invoke(model, "");
					}
				}
				if (type.equals("class java.lang.Integer")) {
					Method m = model.getClass().getMethod("get" + name);
					Integer value = (Integer) m.invoke(model);
					if (value == null) {
						m = model.getClass().getMethod("set" + name, Integer.class);
						m.invoke(model, 0);
					}
				}
				if (type.equals("class java.lang.Boolean")) {
					Method m = model.getClass().getMethod("get" + name);
					Boolean value = (Boolean) m.invoke(model);
					if (value == null) {
						m = model.getClass().getMethod("set" + name, Boolean.class);
						m.invoke(model, false);
					}
				}
				if (type.equals("class java.util.Date")) {
					Method m = model.getClass().getMethod("get" + name);
					Date value = (Date) m.invoke(model);
					if (value == null) {
						m = model.getClass().getMethod("set" + name, Date.class);
						m.invoke(model, new Date());
					}
				} 
				// 如果有需要,可以仿照上面继续进行扩充,再增加对其它类型的判断
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return field;
	}
}
