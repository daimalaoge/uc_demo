package com.ucomponent.manager.controller.rest;

import java.util.List;

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
import com.ucomponent.po.SysMenu;
import com.ucomponent.repository.SysMenuRepository;
import com.ucomponent.utils.StringTools;

/**
 * 2019年2月26日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@RestController
@RequestMapping("/sysd/menu")
public class RestMenuController extends BaseRestController implements ICommons{
	@Autowired
  private SysMenuRepository sysMenuRepository;
	
	@ActionName(value = "get Menu list data") 
  @RequestMapping("/list/data")
	public List listdata(HttpServletRequest request){
		List list = sysMenuRepository.findAll();
    return super.codeKeyConvert(list);
  }

  @ActionName(value = "Menu save")
  @RequestMapping("/bo/save")
  public String bosave(HttpServletRequest request,SysMenu vo){
  	super.doSave(vo, request);
  	sysMenuRepository.save(vo);
  	return UCMANAGER_DATA_SUCCUSS;
  }

  @ActionName(value = "Menu status")
  @RequestMapping("/bo/status")
  public String bostatus(HttpServletRequest request){
  	String id = StringTools.getString(request.getParameter("id"));
  	SysMenu menu = sysMenuRepository.getOne(Integer.parseInt(id));
		super.doStatus(menu,request);
		sysMenuRepository.save(menu);
  	return UCMANAGER_DATA_SUCCUSS;
  }
}

