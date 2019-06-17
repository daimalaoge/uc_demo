package com.ucomponent.manager.sys.controller.rest;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.rest.BaseRestController;
import com.ucomponent.base.encrypt.EncryptPO;
import com.ucomponent.manager.sys.repository.MangSysMenuRepository;
import com.ucomponent.manager.po.MangSysMenu;
import com.ucomponent.manager.po.MangUserAccount;
import com.ucomponent.utils.StringTools;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 2019年2月26日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@RestController
@RequestMapping("/manager/sysd/menu")
public class RestMangMenuController extends BaseRestController implements ICommons {
	@Autowired
  private MangSysMenuRepository sysMenuRepository;
	
	@ActionName(value = "get Menu list data") 
  @RequestMapping("/list/data")
	@RequiresPermissions("/manager/sysd/menu/list")
	public List listdata(HttpServletRequest request){
		String[] status = new String[2];
		status[0] = "G_STATUS_NOUSE";
		status[1] = "G_STATUS_USE";
		List list = sysMenuRepository.findByCodesetGstatusInOrderBySeqAsc(status);
    return super.codeKeyConvert(list);
  }

  @ActionName(value = "Menu save")
  @RequestMapping("/bo/save")
  @RequiresPermissions({"/manager/sysd/menu/boaction/ADD","/manager/sysd/menu/boaction/EDIT"})
  public String bosave(HttpServletRequest request, MangSysMenu vo){
	  MangSysMenu po =(MangSysMenu) EncryptPO.decPO(super.getEncrytKey(),vo);
	  String actionModel = StringTools.getString(request.getParameter("ACTIONMODE"));
	  HttpSession session = request.getSession();
	  MangUserAccount user = super.getUser();
	  po.setUrl("#"+po.getUrlData());
	  if(actionModel.equals("ADD")){
		  po.setCreateDatetime(new Date());
		  po.setUpdateDatetime(new Date());
		  po.setUpdateUserId(user.getId());
		  po.setCreateUserId(user.getId());
	  }else if(actionModel.equals("EDIT")){
		  po.setUpdateDatetime(new Date());
		  po.setUpdateUserId(user.getId());
	  }
  	sysMenuRepository.save(po);
  	return UCMANAGER_DATA_SUCCUSS;
  }

  @ActionName(value = "Menu status")
  @RequestMapping("/bo/status")
  @RequiresPermissions("/manager/sysd/menu/bo/status")
  public String bostatus(HttpServletRequest request){
  	String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
  	MangSysMenu menu = sysMenuRepository.getOne(Integer.parseInt(id));
	  HttpSession session = request.getSession();
	  MangUserAccount user = super.getUser();
	  menu.setUpdateDatetime(new Date());
	  menu.setUpdateUserId(user.getId());
	  if(menu.getCodesetGstatus().equals("G_STATUS_USE")){
		  menu.setCodesetGstatus("G_STATUS_NOUSE");
	  }else if(menu.getCodesetGstatus().equals("G_STATUS_NOUSE")){
		  menu.setCodesetGstatus("G_STATUS_USE");
	  }
		sysMenuRepository.save(menu);
  	return UCMANAGER_DATA_SUCCUSS;
  }

	@ActionName(value = "Sys Menu del")
	@RequestMapping("/bo/del")
	@RequiresPermissions("/manager/sysd/menu/bo/del")
	public String bodel(HttpServletRequest request){
		String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
		MangSysMenu menu = sysMenuRepository.getOne(Integer.parseInt(id));
		HttpSession session = request.getSession();
		MangUserAccount user = super.getUser();
		menu.setCodesetGstatus("G_STATUS_DEL");
		menu.setUpdateDatetime(new Date());
		menu.setUpdateUserId(user.getId());
		sysMenuRepository.save(menu);
		return UCMANAGER_DATA_SUCCUSS;
	}
}

