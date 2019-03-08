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
import javax.servlet.http.HttpSession;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.entity.CodeSetList;
import com.ucomponent.po.UserAccount;
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
		
	  return new ControllerTools().codeKeyConvert(jsonlist);
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
	  HttpSession session = request.getSession();
	  UserAccount user = (UserAccount)session.getAttribute(SESSION_ACCOUNT);
    if(actionModel.equals("ADD")){
      vo.setCreateDatetime(new Date());
      vo.setUpdateDatetime(new Date());
      vo.setUpdateUserId(user.getId());
      vo.setCreateUserId(user.getId());
    }else if(actionModel.equals("EDIT")){
      vo.setUpdateDatetime(new Date());
      vo.setUpdateUserId(user.getId());
    }
	}
	/**
	 * 删除
	 * @param vo
	 * @param request
	 */
	public void doDelete(BasePO vo,HttpServletRequest request){
		HttpSession session = request.getSession();
	  UserAccount user = (UserAccount)session.getAttribute(SESSION_ACCOUNT);
	  vo.setCodesetGstatus("G_STATUS_DEL");
	  vo.setUpdateDatetime(new Date());
	  vo.setUpdateUserId(user.getId());
  }
	/**
	 * 更改状态
	 * @param vo
	 */
	public void doStatus(BasePO vo,HttpServletRequest request){
		HttpSession session = request.getSession();
	  UserAccount user = (UserAccount)session.getAttribute(SESSION_ACCOUNT);
	  vo.setUpdateDatetime(new Date());
	  vo.setUpdateUserId(user.getId());
	  if(vo.getCodesetGstatus().equals("G_STATUS_USE")){
	    vo.setCodesetGstatus("G_STATUS_NOUSE");
    }else if(vo.getCodesetGstatus().equals("G_STATUS_NOUSE")){
      vo.setCodesetGstatus("G_STATUS_USE");
    }
  }
}
