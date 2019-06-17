package com.ucomponent.biz.demo.controller.rest;

import javax.servlet.http.HttpServletRequest;

import com.ucomponent.base.controller.rest.BaseRestController;
import com.ucomponent.base.encrypt.EncryptPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.entity.JsonlistData;

import com.ucomponent.biz.demo.repository.DemoRepository;
import com.ucomponent.biz.po.Demo;
import com.ucomponent.utils.StringTools;

/**
 * <p>Title: 测试增删改样例 Rest Controller</p>
 * <p>Description: </p>
 * <p>Copyright: Union Component</p>
 * @date:2019-06-17 11:54:23
 * @author:联合组件
 * @version:1.0
 **/ 
@RestController
@RequestMapping("/biz/ceshi/demo")
public class RestDemoController extends BaseRestController implements ICommons{
  @Autowired
 	private DemoRepository repository;

	@ActionName(value = "Get 测试增删改样例 List Data") 
  @RequestMapping("/list/data")
  @RequiresPermissions("/biz/ceshi/demo/list")
	public JsonlistData listdata(HttpServletRequest request){
    //接收页面参数
    JsonlistData jd = new JsonlistData();
    //接收搜索参数
    String status = StringTools.getString(request.getParameter("status"),"G_STATUS_USE");
		String mingcheng = StringTools.getString(request.getParameter("bizCodesetMingcheng"));
		String weiyidaima = StringTools.getString(request.getParameter("bizCodesetWeiyidaima"));

    //获取列表数据    
    Sort sort = new Sort(Sort.Direction.DESC,"updateDatetime"); //创建时间降序排序
    Pageable pageable = new PageRequest(super.getPage(request),super.getPageLimit(request),sort);
    
    Page pagedata = repository.findByOrgIdAndMingchengContainingAndWeiyidaimaContainingAndCodesetGstatusIn(ORG_ID,mingcheng,weiyidaima,status,pageable);;

    //返回页面数据
    jd.setCode(UCMANAGER_LISTPAGE_CODE);
    jd.setCount(pagedata.getTotalElements());
    jd.setData(super.codeKeyConvert( pagedata.getContent()));
    return jd;
  }
  
  @ActionName(value = "测试增删改样例 Save")
  @RequestMapping("/bo/save")
  @RequiresPermissions({"/biz/ceshi/demo/boaction/ADD","/biz/ceshi/demo/boaction/EDIT"})  public String bosave(HttpServletRequest request,Demo vo){
	  Demo po =(Demo)EncryptPO.decPO(super.getEncrytKey(),vo);
	  String actionModel = StringTools.getString(request.getParameter("ACTIONMODE"));
	  if(actionModel.equals("ADD")){
			if(repository.findByOrgIdAndWeiyidaimaAndCodesetGstatus(ORG_ID,vo.getWeiyidaima(),"G_STATUS_USE") != null ){
				return "-1";
			}

	  }	  
  	super.doSave(po, request);
		repository.save(po);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "测试增删改样例 Del")
  @RequestMapping("/bo/del")
  @RequiresPermissions("/biz/ceshi/demo/bo/del")
  public String bodel(HttpServletRequest request){
	  if(!StringTools.getDecHStr(super.getEncrytKey(),request.getParameter("encCode")).equals("")) {
		  String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter("encCode"));
		  Demo po = repository.findById(Integer.parseInt(id)).orElse(null);
		  super.doDelete(po);
		  repository.save(po);
		  return UCMANAGER_DATA_SUCCUSS;
	  }else{
		  return UCMANAGER_DATA_ERROR;
	  }
  }
  
  @ActionName(value = "测试增删改样例 Status")
  @RequestMapping("/bo/status")
  @RequiresPermissions("/biz/ceshi/demo/bo/status")
  public String bostatus(HttpServletRequest request){
	  if(!StringTools.getDecHStr(super.getEncrytKey(),request.getParameter("encCode")).equals("")) {
	    String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter("encCode"));
		  Demo po = repository.findById(Integer.parseInt(id)).orElse(null);
			super.doStatus(po);
			repository.save(po);
	    return UCMANAGER_DATA_SUCCUSS;
	  }else{
		  return UCMANAGER_DATA_ERROR;
	  }
  }
}
