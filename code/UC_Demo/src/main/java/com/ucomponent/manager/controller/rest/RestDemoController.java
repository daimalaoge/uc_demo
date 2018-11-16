package com.ucomponent.manager.controller.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
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
import com.ucomponent.po.Demo;
import com.ucomponent.repository.DemoRepository;
import com.ucomponent.utils.StringTools;
/**
 * 
 * @author 代码老哥
 *
 */
@RestController
@RequestMapping("/demo")
public class RestDemoController extends BaseRestController implements ICommons{
  @Autowired
  private DemoRepository demoRepository;

	@ActionName(value = "get demo list data") 
  @RequestMapping("/list/data")
	public JsonlistData listdata(HttpServletRequest request){
    //接收页面参数
    JsonlistData jd = new JsonlistData();
    //接收搜索参数
    String name = StringTools.getString(request.getParameter("name"));
    String status = StringTools.getString(request.getParameter("status"),"G_STATUS_USE");
    if(status.equals("on")) status="1";
    //获取列表数据    
    Sort sort = new Sort(Sort.Direction.DESC,"updateDatetime"); //创建时间降序排序
    Pageable pageable = new PageRequest(super.getPage(request),super.getPageLimit(request),sort);
    Page pagedata = demoRepository.findByNameContainingAndCodesetGstatusIn(name,status,pageable);
    //返回页面数据
    jd.setCode(UCMANAGER_LISTPAGE_CODE);
    jd.setCount(pagedata.getTotalElements());
    jd.setData(super.codeKeyConvert(pagedata.getContent()));
    return jd;
  }
  
  @ActionName(value = "Demo save")
  @RequestMapping("/bo/save")
  public String bosave(HttpServletRequest request,Demo vo){
  	String actionModel = StringTools.getString(request.getParameter("ACTIONMODE"));
		if(actionModel.equals("ADD")){
      vo.setCreateDatetime(new Date());
      vo.setUpdateDatetime(new Date());		
    }else if(actionModel.equals("EDIT")){
      vo.setUpdateDatetime(new Date());   
		}else {
		  
		}
		demoRepository.save(vo);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "Demo del")
  @RequestMapping("/bo/del")
  public String bodel(HttpServletRequest request){
  	String id = StringTools.getString(request.getParameter("id"));
		Demo demo = demoRepository.getOne(Integer.parseInt(id));
		demo.setCodesetGstatus("G_STATUS_DEL");
		demo.setUpdateDatetime(new Date());
		demoRepository.save(demo);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "Demo status")
  @RequestMapping("/bo/status")
  public String bostatus(HttpServletRequest request){
  	String id = StringTools.getString(request.getParameter("id"));
		Demo demo = demoRepository.getOne(Integer.parseInt(id));
		if(demo.getCodesetGstatus().equals("G_STATUS_USE")){
			demo.setCodesetGstatus("G_STATUS_NOUSE");
		}else if(demo.getCodesetGstatus().equals("G_STATUS_NOUSE")){
			demo.setCodesetGstatus("G_STATUS_USE");
		}
		demoRepository.save(demo);
  	return UCMANAGER_DATA_SUCCUSS;
  }
}
