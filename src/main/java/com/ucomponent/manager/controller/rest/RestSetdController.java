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

import java.util.List;

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
	public List listdata(HttpServletRequest request){
		List list = sysCodesetRepository.findAll();
		return super.codeKeyConvert(list);
	}
  
  @ActionName(value = "SysCodeset save")
  @RequestMapping("/bo/save")
  public String bosave(HttpServletRequest request,SysCodeset vo){
  	super.doSave(vo, request);
  	sysCodesetRepository.save(vo);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
//  @ActionName(value = "SysCodeset del")
//  @RequestMapping("/bo/del")
//  public String bodel(HttpServletRequest request){
//  	String id = StringTools.getString(request.getParameter("id"));
//		SysCodeset sysCodeset = sysCodesetRepository.getOne(Integer.parseInt(id));
//		super.doDelete(sysCodeset,request);
//		sysCodesetRepository.save(sysCodeset);
//  	return UCMANAGER_DATA_SUCCUSS;
//  }
  
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

