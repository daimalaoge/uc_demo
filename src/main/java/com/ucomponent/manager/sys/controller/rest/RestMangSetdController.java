package com.ucomponent.manager.sys.controller.rest;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.rest.BaseRestController;
import com.ucomponent.base.encrypt.EncryptPO;
import com.ucomponent.base.entity.JsonlistData;
import com.ucomponent.manager.sys.repository.MangSysCodesetRepository;
import com.ucomponent.manager.po.MangSysCodeset;
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
@RequestMapping("/manager/sysd/setd")
public class RestMangSetdController extends BaseRestController implements ICommons {
	@Autowired
  private MangSysCodesetRepository sysCodesetRepository;
	
	@ActionName(value = "get SysCodeset list data") 
  @RequestMapping("/list/data")
	@RequiresPermissions("/manager/sysd/setd/list")
	public List listdata(HttpServletRequest request){
		JsonlistData jd = new JsonlistData();
		List list = sysCodesetRepository.findByOrderBySeq();
//		jd.setMsg("ok");
//		jd.setCount(list.size());
//		jd.setData(super.codeKeyConvert(list));
		return super.codeKeyConvert(list);
	}
  
  @ActionName(value = "SysCodeset save")
  @RequestMapping("/bo/save")
  @RequiresPermissions({"/manager/sysd/setd/boaction/ADD","/manager/sysd/setd/boaction/EDIT"})
  public String bosave(HttpServletRequest request, MangSysCodeset vo){
	  MangSysCodeset po =(MangSysCodeset) EncryptPO.decPO(super.getEncrytKey(),vo);
	  String actionModel = StringTools.getString(request.getParameter("ACTIONMODE"));
	  HttpSession session = request.getSession();
	  MangUserAccount user = super.getUser();

	  if(actionModel.equals("ADD")){
		  po.setCreateDatetime(new Date());
		  po.setUpdateDatetime(new Date());
		  po.setUpdateUserId(user.getId());
		  po.setCreateUserId(user.getId());
	  }else if(actionModel.equals("EDIT")){
		  po.setUpdateDatetime(new Date());
		  po.setUpdateUserId(user.getId());
	  }
  	sysCodesetRepository.save(po);
  	return UCMANAGER_DATA_SUCCUSS;
  }

  @ActionName(value = "SysCodeset status")
  @RequestMapping("/bo/status")
  @RequiresPermissions("/manager/sysd/setd/bo/status")
  public String bostatus(HttpServletRequest request){
	  String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
	  MangSysCodeset sysCodeset = sysCodesetRepository.getOne(Integer.parseInt(id));
	  HttpSession session = request.getSession();
	  MangUserAccount user = super.getUser();
	  sysCodeset.setUpdateDatetime(new Date());
	  sysCodeset.setUpdateUserId(user.getId());
	  if(sysCodeset.getCodesetGstatus().equals("G_STATUS_USE")){
		  sysCodeset.setCodesetGstatus("G_STATUS_NOUSE");
	  }else if(sysCodeset.getCodesetGstatus().equals("G_STATUS_NOUSE")){
		  sysCodeset.setCodesetGstatus("G_STATUS_USE");
	  }
		sysCodesetRepository.save(sysCodeset);
  	return UCMANAGER_DATA_SUCCUSS;
  }
}

