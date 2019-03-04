package com.ucomponent.manager.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.BaseRestController;
import com.ucomponent.base.entity.JsonlistData;
import com.ucomponent.po.SysRole;
import com.ucomponent.repository.SysRoleRepository;
import com.ucomponent.utils.StringTools;

/**
 * 2019年2月26日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@RestController
@RequestMapping("/sysd/role")
public class RestRoleController extends BaseRestController implements ICommons{
	@Autowired
  private SysRoleRepository sysRoleRepository;
	
	@ActionName(value = "get SysRole list data") 
  @RequestMapping("/list/data")
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
  
  @ActionName(value = "SysRole save")
  @RequestMapping("/bo/save")
  public String bosave(HttpServletRequest request,SysRole vo){
  	super.doSave(vo, request);
		sysRoleRepository.save(vo);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "SysRole del")
  @RequestMapping("/bo/del")
  public String bodel(HttpServletRequest request){
  	String id = StringTools.getString(request.getParameter("id"));
		SysRole sysRole = sysRoleRepository.getOne(Integer.parseInt(id));	
		super.doDelete(sysRole,request);
		sysRoleRepository.save(sysRole);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "SysRole status")
  @RequestMapping("/bo/status")
  public String bostatus(HttpServletRequest request){
  	String id = StringTools.getString(request.getParameter("id"));
		SysRole sysRole = sysRoleRepository.getOne(Integer.parseInt(id));
		super.doStatus(sysRole,request);
		sysRoleRepository.save(sysRole);
  	return UCMANAGER_DATA_SUCCUSS;
  }
}

