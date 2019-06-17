package com.ucomponent.base.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.controller.vo.BaseLayuiVO;
import com.ucomponent.base.encrypt.EncryptPO;
import com.ucomponent.base.entity.CodeSetList;

/**
 * 2019年3月5日
 * 5月7日
 * 加入递归方法
 * 可以设置Object中ManyToOne的codeset
 * @Author:Daimalaoge
 */
public class ControllerTools  implements ICommons {
	public BaseLayuiVO codeKeyConvert(String key,BaseLayuiVO jobj){
		CodeSetList cslist = CodeSetList.getInstance();
		Object jo = new Object();
		try {
			jo = deepCopyObject(jobj);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Method[] md = jo.getClass().getMethods();
		Field[] fs = jo.getClass().getDeclaredFields();
		for (Field field : fs) {
			// 得到成员变量的类型的类类型
			Class fieldType = field.getType();
			if(!isPoType(fieldType)){
				try {
					field.setAccessible(true);
					Object value = field.get(jo); // 取变量的值
					codeKeyConvertObject(value);
				}catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for(Method m:md){
			String mname = m.getName();
			if(mname.indexOf("getCodeset") != -1 ){
				String cname = mname.replaceAll("getCodeset", "");
				Class<?> clazz = jo.getClass();
				try {
					Method method = clazz.getMethod(mname);
					Method setmethod = clazz.getMethod("setCodeset"+cname,String.class);
					setmethod.invoke(jo,cslist.getName((String)method.invoke(jo)));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return (BaseLayuiVO)jo;
	}
	/**
	 * System Use
	 * @param key
	 * @param jsonlist
	 * @return
	 */
	public List<BaseLayuiVO> codeKeyConvert(String key,List<BaseLayuiVO> jsonlist){
		System.out.println(jsonlist);
		List<BaseLayuiVO> list = new ArrayList<BaseLayuiVO>();
		try {
			list = deepCopyList(jsonlist);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CodeSetList cslist = CodeSetList.getInstance();
		for(Object jo:list){
			Method[] md = jo.getClass().getMethods();
			Field[] fs = jo.getClass().getDeclaredFields();
			for (Field field : fs) {
			// 得到成员变量的类型的类类型
			Class fieldType = field.getType();
				if(!isPoType(fieldType)){
					try {
						field.setAccessible(true);
						Object value = field.get(jo); // 取变量的值
						codeKeyConvertObject(value);
					}catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			for(Method m:md){
				String mname = m.getName();
				if(mname.indexOf("getCodeset") != -1 ){
					String cname = mname.replaceAll("getCodeset", "");
					Class<?> clazz = jo.getClass();
					try {
						Method method = clazz.getMethod(mname);
						Method setmethod = clazz.getMethod("setCodeset"+cname,String.class);
						setmethod.invoke(jo,cslist.getName((String)method.invoke(jo)));
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		list = EncryptPO.encList(key,list);
		return list;
	}
	private Object codeKeyConvertObject(Object jo){
		Method[] md = jo.getClass().getMethods();
		Field[] fs = jo.getClass().getDeclaredFields();
		for (Field field : fs) {
			// 得到成员变量的类型的类类型 - 递归
			Class fieldType = field.getType();
			if(!isPoType(fieldType)){
				try {
					field.setAccessible(true);
					Object value = field.get(jo); // 取变量的值
					codeKeyConvertObject(value);
				}catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		CodeSetList cslist = CodeSetList.getInstance();
		for(Method m:md){
			String mname = m.getName();
			if(mname.contains("getCodeset") ){
				String cname = mname.replaceAll("getCodeset", "");
				Class<?> clazz = jo.getClass();
				try {
					Method method = clazz.getMethod(mname);
					Method setmethod = clazz.getMethod("setCodeset"+cname,String.class);
					setmethod.invoke(jo,cslist.getName((String)method.invoke(jo)));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return jo;
	}

	private static <T> List<T> deepCopyList(List<T> src) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);

		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		@SuppressWarnings("unchecked")
		List<T> dest = (List<T>) in.readObject();
		return dest;
	}

	private static Object deepCopyObject(Object src) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);

		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		@SuppressWarnings("unchecked")
		Object dest = (Object) in.readObject();
		return dest;
	}
	/**
	 * 判断object是否为基本类型
	 * @param className
	 * @return 是：true
	 */
	public static boolean isBaseType(Class className) {
		if (className.equals(Integer.class) ||
						className.equals(Byte.class) ||
						className.equals(Long.class) ||
						className.equals(Double.class) ||
						className.equals(Float.class) ||
						className.equals(Character.class) ||
						className.equals(Short.class) ||
						className.equals(Boolean.class)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断object是否为PO常用定义类型（基本类型、引用类型）
	 * @param Class cls
	 * @return 是：true
	 */
	public static boolean isPoType(Class cls) {
		if(isBaseType(cls)) return true;
		String clsname = cls.getName();
		if (clsname.contains("java.lang")
						||clsname.contains("java.util")
						|| clsname.contains("int")
						|| clsname.contains("long")
						|| clsname.contains("boolean")
						|| clsname.contains("double")
						|| clsname.contains("byte")
						|| clsname.contains("double")
						|| clsname.contains("float")
						|| clsname.contains("short")) {
			return true;
		}
		return false;
	}

}

