package com.ucomponent.base.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.ucomponent.base.ICommons;
import com.ucomponent.base.entity.CodeSetList;
import com.ucomponent.utils.StringTools;

/**
 * 2018年10月27日
 * BaseController.java
 * 代码老哥
 * NAME:
 * Descp:
**/
public class BaseRestController implements ICommons{
	
	/**
	 * 得到页面数
	 * @param request
	 * @return
	 */
	public int getPage(HttpServletRequest request){
    String pagenum = StringTools.getString(request.getParameter("page"));
		int npage = 0;
    if(!pagenum.equals("")){
      npage = Integer.parseInt(pagenum) -1;
    }
    return npage;
	}
	/**
	 * 得到每页显示行数
	 * @param request
	 * @return
	 */
	public int getPageLimit(HttpServletRequest request){
  	String pagelimit = StringTools.getString(request.getParameter("limit"));
		int npagelimit = UCMANAGER_DISP_SIZE;
		if(!pagelimit.equals("")){
    	npagelimit = Integer.parseInt(pagelimit);
    }
    return npagelimit;
	}
	/**
	 * 对输入字符和Codeset进行转换
	 * @param json
	 * @return
	 */
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
	
	/**
	 * List深复制
	 * @param src
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
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
	
//	private static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
//		List<T> dest = new ArrayList();
//    for(T obj:src) {
//    	dest.add(obj);
//    }
//    return dest;  
//	}
	/**
	 * 保存
	 * @param vo
	 * @param request
	 */
	public void doSave(BasePO vo,HttpServletRequest request){
	  String actionModel = StringTools.getString(request.getParameter("ACTIONMODE"));
    if(actionModel.equals("ADD")){
      vo.setCreateDatetime(new Date());
      vo.setUpdateDatetime(new Date());   
    }else if(actionModel.equals("EDIT")){
      vo.setUpdateDatetime(new Date());   
    }
	}
	/**
	 * 删除
	 * @param vo
	 * @param request
	 */
	public void doDelete(BasePO vo){
	  vo.setCodesetGstatus("G_STATUS_DEL");
	  vo.setUpdateDatetime(new Date());
  }
	/**
	 * 更改状态
	 * @param vo
	 */
	public void doStatus(BasePO vo){
	  if(vo.getCodesetGstatus().equals("G_STATUS_USE")){
	    vo.setCodesetGstatus("G_STATUS_NOUSE");
    }else if(vo.getCodesetGstatus().equals("G_STATUS_NOUSE")){
      vo.setCodesetGstatus("G_STATUS_USE");
    }
  }
}
