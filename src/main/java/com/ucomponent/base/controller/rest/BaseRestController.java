package com.ucomponent.base.controller.rest;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.controller.vo.BaseLayuiVO;
import com.ucomponent.base.controller.vo.BasePO;
import com.ucomponent.base.controller.ControllerTools;

import com.ucomponent.manager.po.MangSysOrg;
import com.ucomponent.manager.po.MangUserAccount;
import com.ucomponent.utils.StringTools;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 2018年10月27日
 * BaseController.java
 * 代码老哥
 * NAME:
 * Descp:
**/
public class BaseRestController implements ICommons {
	/**
	 * 得到页面数
	 * @param request
	 * @return page
	 */
	public int getPage(HttpServletRequest request){
    String pagenum = StringTools.getString(request.getParameter("page"));
    if("undefined".equals(pagenum))pagenum="1";
		int npage = 0;
    if(!pagenum.equals("")){
      npage = Integer.parseInt(pagenum) -1;
    }
    return npage;
	}
	/**
	 * 得到每页显示行数
	 * @param request
	 * @return pagelimit
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
	 * @return list
	 */
	public List<BaseLayuiVO> codeKeyConvert(List<BaseLayuiVO> jsonlist){
		Subject subject = SecurityUtils.getSubject();
		Session ssession = subject.getSession();
		String key = (String)ssession.getAttribute(USER_ENCRYPT_KEY);
		if(key != null){
			return new ControllerTools().codeKeyConvert(key,jsonlist);
		}else{
			return null;
		}
	}

	/**
	 * 对输入字符和Codeset进行转换
	 * @return Object
	 */
	public BaseLayuiVO codeKeyConvert(BaseLayuiVO jobj){
		Subject subject = SecurityUtils.getSubject();
		Session ssession = subject.getSession();
		String key = (String)ssession.getAttribute(USER_ENCRYPT_KEY);
		if(key != null){
			return new ControllerTools().codeKeyConvert(key,jobj);
		}else{
			return null;
		}
	}

	/**
	 *
	 * @param key
	 * @param jsonlist
	 * @return
	 */
	public List<BaseLayuiVO> codeKeyConvert(String key,List<BaseLayuiVO> jsonlist){
		return new ControllerTools().codeKeyConvert(key,jsonlist);
	}
	/**
	 * 保存
	 * @param vo
	 * @param request
	 */
	public void doSave(BasePO vo, HttpServletRequest request){
	  String actionModel = StringTools.getString(request.getParameter("ACTIONMODE"));
//	  HttpSession session = request.getSession();
//		MangUserAccount user = super.getUser();
		Subject subject = SecurityUtils.getSubject();
		Session ssession = subject.getSession();
		MangUserAccount user = (MangUserAccount)ssession.getAttribute(SESSION_ACCOUNT);
    if(actionModel.equals("ADD")){
      vo.setCreateDatetime(new Date());
      vo.setUpdateDatetime(new Date());
      vo.setUpdateUserId(user.getId());
      vo.setCreateUserId(user.getId());
      vo.setOrgId(user.getOrgId());
    }else if(actionModel.equals("EDIT")){
      vo.setUpdateDatetime(new Date());
      vo.setUpdateUserId(user.getId());
    }
	}
	/**
	 * 删除
	 * @param vo
	 */
	public void doDelete(BasePO vo){
//		HttpSession session = request.getSession();
//		MangUserAccount user = super.getUser();
		Subject subject = SecurityUtils.getSubject();
		Session ssession = subject.getSession();
		MangUserAccount user = (MangUserAccount)ssession.getAttribute(SESSION_ACCOUNT);
	  vo.setCodesetGstatus("G_STATUS_DEL");
	  vo.setUpdateDatetime(new Date());
	  vo.setUpdateUserId(user.getId());
  }
	/**
	 * 更改状态
	 * @param vo
	 */
	public void doStatus(BasePO vo){
//		HttpSession session = request.getSession();
//		MangUserAccount user = super.getUser();
		Subject subject = SecurityUtils.getSubject();
		Session ssession = subject.getSession();
		MangUserAccount user = (MangUserAccount)ssession.getAttribute(SESSION_ACCOUNT);
	  vo.setUpdateDatetime(new Date());
	  vo.setUpdateUserId(user.getId());
	  if(vo.getCodesetGstatus().equals("G_STATUS_USE")){
	    vo.setCodesetGstatus("G_STATUS_NOUSE");
    }else if(vo.getCodesetGstatus().equals("G_STATUS_NOUSE")){
      vo.setCodesetGstatus("G_STATUS_USE");
    }
  }

	/**
	 *
	 * @return
	 */
  public int getUserId(){
//	  HttpSession session = request.getSession();
//	  MangUserAccount user = super.getUser();
	  Subject sub = SecurityUtils.getSubject();
	  return Integer.parseInt(sub.getPrincipal().toString());
  }

	/**
	 *
	 * @return
	 */
	public MangUserAccount getUser(){
//	  HttpSession session = request.getSession();
//	  MangUserAccount user = super.getUser();
		Subject subject = SecurityUtils.getSubject();
		Session ssession = subject.getSession();
		MangUserAccount user = (MangUserAccount)ssession.getAttribute(SESSION_ACCOUNT);
		return user;
	}

	/**
	 *
	 * @return
	 */
	public int getOrgId(){
//		HttpSession session = request.getSession();
//		MangUserAccount user = super.getUser();
//		return user.getOrgId();
		Subject subject = SecurityUtils.getSubject();
		Session ssession = subject.getSession();
		MangSysOrg org = (MangSysOrg)ssession.getAttribute(SESSION_ORG);
		return org.getId();
	}

	/**
	 *
	 * @return
	 */
	public String getOrgCode(){
//		HttpSession session = request.getSession();
//		MangSysOrg org = (MangSysOrg)session.getAttribute(SESSION_ORG);
		Subject subject = SecurityUtils.getSubject();
		Session ssession = subject.getSession();
		MangSysOrg org = (MangSysOrg)ssession.getAttribute(SESSION_ORG);
		return org.getCode();
	}

	/**
	 *
	 * @return
	 */
	public String getEncrytKey(){
//		HttpSession session = request.getSession();
//		String key = (String)session.getAttribute(USER_ENCRYPT_KEY);
		Subject subject = SecurityUtils.getSubject();
		Session ssession = subject.getSession();
		String key = (String)ssession.getAttribute(USER_ENCRYPT_KEY);
		if(key != null){
			return key;
		}else{
			return "";
		}
	}

}
