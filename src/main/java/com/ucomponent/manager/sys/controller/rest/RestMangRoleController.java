package com.ucomponent.manager.sys.controller.rest;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.rest.BaseRestController;
import com.ucomponent.base.encrypt.EncryptPO;
import com.ucomponent.base.entity.JsonlistData;
import com.ucomponent.manager.sys.repository.MangSysMenuRepository;
import com.ucomponent.manager.sys.repository.MangSysRoleMenuRsRepository;
import com.ucomponent.manager.sys.repository.MangSysRoleRepository;
import com.ucomponent.manager.po.MangSysRole;
import com.ucomponent.manager.po.MangSysRoleMenuRs;
import com.ucomponent.manager.po.MangUserAccount;
import com.ucomponent.utils.StringTools;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 2019年2月26日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@RestController
@RequestMapping("/manager/sysd/role")
public class RestMangRoleController extends BaseRestController implements ICommons {
	@Autowired
  private MangSysRoleRepository sysRoleRepository;
	@Autowired
  private MangSysMenuRepository sysMenuRepository;
	@Autowired
  private MangSysRoleMenuRsRepository sysRoleMenuRsRepository;
	
	@ActionName(value = "get SysRole list data") 
  @RequestMapping("/list/data")
	@RequiresPermissions("/manager/sysd/role/list")
	public JsonlistData listdata(HttpServletRequest request){
    //接收页面参数
    JsonlistData jd = new JsonlistData();
    //接收搜索参数
    String name = StringTools.getString(request.getParameter("name"));
    String status = StringTools.getString(request.getParameter("status"),"G_STATUS_USE");
    //获取列表数据    
    Sort sort = new Sort(Sort.Direction.DESC,"updateDatetime"); //创建时间降序排序
    Pageable pageable = new PageRequest(super.getPage(request),super.getPageLimit(request),sort);
    Page pagedata = sysRoleRepository.findByNameContainingAndCodesetGstatusIn(name,status,pageable);
    //返回页面数据
    jd.setCode(UCMANAGER_LISTPAGE_CODE);
    jd.setCount(pagedata.getTotalElements());
		jd.setData(super.codeKeyConvert(pagedata.getContent()));
    return jd;
  }
	
	@ActionName(value = "get Role - Menu list data") 
  @RequestMapping("/menuset/data")
	@RequiresPermissions("/manager/sysd/role/menuset")
	public List menulistdata(HttpServletRequest request){
    List list = sysMenuRepository.findByCodesetGstatusOrderByLevelsAscSeqAsc("G_STATUS_USE");

    return super.codeKeyConvert(list);
  }
 
  @ActionName(value = "SysRole save")
  @RequestMapping("/bo/save")
  @RequiresPermissions({"/manager/sysd/role/boaction/ADD","/manager/sysd/role/boaction/EDIT"})
  public String bosave(HttpServletRequest request, MangSysRole vo){
	  MangSysRole po =(MangSysRole) EncryptPO.decPO(super.getEncrytKey(),vo);
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
		sysRoleRepository.save(po);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "SysRole del")
  @RequestMapping("/bo/del")
  @RequiresPermissions("/manager/sysd/role/bo/del")
  public String bodel(HttpServletRequest request){
	  String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
	  MangSysRole sysRole = sysRoleRepository.getOne(Integer.parseInt(id));
	  HttpSession session = request.getSession();
	  MangUserAccount user = super.getUser();
	  sysRole.setCodesetGstatus("G_STATUS_DEL");
	  sysRole.setUpdateDatetime(new Date());
	  sysRole.setUpdateUserId(user.getId());
		sysRoleRepository.save(sysRole);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "SysRole status")
  @RequestMapping("/bo/status")
  @RequiresPermissions("/manager/sysd/role/bo/status")
  public String bostatus(HttpServletRequest request){
	  String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
	  MangSysRole sysRole = sysRoleRepository.getOne(Integer.parseInt(id));
	  HttpSession session = request.getSession();
	  MangUserAccount user = super.getUser();
	  sysRole.setUpdateDatetime(new Date());
	  sysRole.setUpdateUserId(user.getId());
	  if(sysRole.getCodesetGstatus().equals("G_STATUS_USE")){
		  sysRole.setCodesetGstatus("G_STATUS_NOUSE");
	  }else if(sysRole.getCodesetGstatus().equals("G_STATUS_NOUSE")){
		  sysRole.setCodesetGstatus("G_STATUS_USE");
	  }
		sysRoleRepository.save(sysRole);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "SysRole Meue-Role_RS save")
  @RequestMapping("/menuset/save")
  @RequiresPermissions("/manager/sysd/role/menuset")
	public String menusetSave(HttpServletRequest request){
		String rid = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter("rid"));
		String treedate = StringTools.getString(request.getParameter("treedate"));
		sysRoleMenuRsRepository.deleteRmrsByRole(Integer.parseInt(rid));
		List<MangSysRoleMenuRs> list = new ArrayList();
		if(!treedate.equals("")) {
			String[] ids = treedate.split(",");

			for(String id:ids) {
				//id = StringTools.getDecHStr(id);
				MangSysRoleMenuRs rs = new MangSysRoleMenuRs();
				rs.setMenuId(Integer.parseInt(id));
				rs.setRoleId(Integer.parseInt(rid));
				list.add(rs);
			}
		}
		if(!list.isEmpty()) {
			sysRoleMenuRsRepository.saveAll(list);
		}
		return UCMANAGER_DATA_SUCCUSS;
	}
}

