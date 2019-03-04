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
import com.ucomponent.po.SysCodeset;
import com.ucomponent.repository.SysCodesetRepository;
import com.ucomponent.utils.StringTools;

/**
 * 2019年2月26日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@RestController
@RequestMapping("/sysd/setd")
public class RestSetdController extends BaseRestController implements ICommons{
	@Autowired
  private SysCodesetRepository sysCodesetRepository;
	
	@ActionName(value = "get SysCodeset list data") 
  @RequestMapping("/list/data")
	public JsonlistData listdata(HttpServletRequest request){
    //接收页面参数
    JsonlistData jd = new JsonlistData();
    //接收搜索参数
    String name = StringTools.getString(request.getParameter("name"));
    String codekey = StringTools.getString(request.getParameter("codekey"));
    String upperCode = StringTools.getString(request.getParameter("upperCode"));
    String status = StringTools.getString(request.getParameter("status"),"G_STATUS_USE");
    //获取列表数据    
    Sort sort = new Sort(Sort.Direction.DESC,"level","upperCode","seq"); //创建时间降序排序
    Pageable pageable = new PageRequest(super.getPage(request),super.getPageLimit(request),sort);
    Page pagedata = sysCodesetRepository.findByNameContainingAndCodeKeyContainingAndUpperCodeContainingAndCodesetGstatusIn(name, codekey, upperCode, status, pageable);
    //返回页面数据
    jd.setCode(UCMANAGER_LISTPAGE_CODE);
    jd.setCount(pagedata.getTotalElements());
    jd.setData(super.codeKeyConvert(pagedata.getContent()));
    return jd;
  }
  
  @ActionName(value = "SysCodeset save")
  @RequestMapping("/bo/save")
  public String bosave(HttpServletRequest request,SysCodeset vo){
  	super.doSave(vo, request);
  	sysCodesetRepository.save(vo);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "SysCodeset del")
  @RequestMapping("/bo/del")
  public String bodel(HttpServletRequest request){
  	String id = StringTools.getString(request.getParameter("id"));
		SysCodeset sysCodeset = sysCodesetRepository.getOne(Integer.parseInt(id));	
		super.doDelete(sysCodeset,request);
		sysCodesetRepository.save(sysCodeset);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "SysCodeset status")
  @RequestMapping("/bo/status")
  public String bostatus(HttpServletRequest request){
  	String id = StringTools.getString(request.getParameter("id"));
		SysCodeset sysCodeset = sysCodesetRepository.getOne(Integer.parseInt(id));
		super.doStatus(sysCodeset,request);
		sysCodesetRepository.save(sysCodeset);
  	return UCMANAGER_DATA_SUCCUSS;
  }
}

