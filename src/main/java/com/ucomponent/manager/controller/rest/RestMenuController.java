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
    //接收页面参数
//    JsonlistData jd = new JsonlistData();
//    //接收搜索参数
//    String name = StringTools.getString(request.getParameter("name"));
//    String levels = StringTools.getString(request.getParameter("levels"));
//    String status = StringTools.getString(request.getParameter("status"),"G_STATUS_USE");
//   
//    //获取列表数据    
//    Sort sort = new Sort(Sort.Direction.DESC,"levels","seq"); //创建时间降序排序
//    Pageable pageable = new PageRequest(super.getPage(request),super.getPageLimit(request),sort);
//    Page pagedata = null;
//    if(levels.equals("")) {
//    	pagedata = sysMenuRepository.findByNameContainingAndCodesetGstatusIn(name,status,pageable);
//    }else {
//    	pagedata = sysMenuRepository.findByNameContainingAndLevelsAndCodesetGstatusIn(name,Integer.parseInt(levels),status,pageable);
//    }
//    //返回页面数据
//    jd.setCode(UCMANAGER_LISTPAGE_CODE);
//    jd.setCount(pagedata.getTotalElements());
//    jd.setData(super.codeKeyConvert(pagedata.getContent()));		
		List list = sysMenuRepository.findAll();
    return super.codeKeyConvert(list);
  }
	
	@ActionName(value = "get Menu inner list data") 
  @RequestMapping("/list2/data")
	public JsonlistData list2data(HttpServletRequest request){
    //接收页面参数
    JsonlistData jd = new JsonlistData();
    //接收搜索参数
    String pid = StringTools.getString(request.getParameter("pid"));
    String status = StringTools.getString(request.getParameter("status"),"G_STATUS_USE");
    if(status.equals("on")) status="1";
    //获取列表数据    
    Sort sort = new Sort(Sort.Direction.DESC,"levels","seq"); //创建时间降序排序
    Pageable pageable = new PageRequest(super.getPage(request),super.getPageLimit(request),sort);
    Page pagedata = sysMenuRepository.findByUpperIdAndCodesetGstatusIn(Integer.parseInt(pid),status,pageable);
    //返回页面数据
    jd.setCode(UCMANAGER_LISTPAGE_CODE);
    jd.setCount(pagedata.getTotalElements());
    jd.setData(super.codeKeyConvert(pagedata.getContent()));
    return jd;
  }
  
  @ActionName(value = "Menu save")
  @RequestMapping("/bo/save")
  public String bosave(HttpServletRequest request,SysMenu vo){
  	String am = StringTools.getString(request.getParameter("ACTIONMODE"));
  	String pid = StringTools.getString(request.getParameter("pid"));

  	if(pid.equals(""))pid = "0";
  	if(am.equals("ADD")) {
  		vo.setUpperId(Integer.parseInt(pid));
  	}
  	super.doSave(vo, request);
  	sysMenuRepository.save(vo);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "Menu del")
  @RequestMapping("/bo/del")
  public String bodel(HttpServletRequest request){
  	String id = StringTools.getString(request.getParameter("id"));
  	SysMenu menu = sysMenuRepository.getOne(Integer.parseInt(id));	
		super.doDelete(menu,request);
		sysMenuRepository.save(menu);
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

