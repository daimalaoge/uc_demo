package com.ucomponent.base.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.ucomponent.base.entity.CodeSetList;

/**
 * 2019年3月5日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
public class ControllerTools {
	public List<Object> codeKeyConvert(List<Object> jsonlist){
		List<Object> list = new ArrayList<Object>();
		try {
			list = deepCopy(jsonlist);
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
	  return list;
	}
	
	private static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {  
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
    ObjectOutputStream out = new ObjectOutputStream(byteOut);  
    out.writeObject(src);  
 
    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());  
    ObjectInputStream in = new ObjectInputStream(byteIn);  
    @SuppressWarnings("unchecked")  
    List<T> dest = (List<T>) in.readObject();  
    return dest;  
	}
}

