package com.ucomponent.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.BaseController;
import com.ucomponent.base.entity.CodeSetList;
import com.ucomponent.po.Demo;
import com.ucomponent.po.UcmCodeset;
import com.ucomponent.repository.DemoRepository;
import com.ucomponent.utils.StringTools;

/**
 * 2018年10月27日
 * DemoController.java
 * 代码老哥
 * NAME:
 * Descp:
**/
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController implements ICommons{
  @Autowired
  private DemoRepository demoRepository;
  
  @ActionName(value = "Demo List") 
	@RequestMapping("/list")
  public String list(Model model,HttpServletRequest request){		
    return "demo/list";
  }
	
  @ActionName(value = "Demo edit or add") 
	@RequestMapping("/boaction/{mode}")
  public String boaction(Model model,HttpServletRequest request,@PathVariable String mode){
  	//设置列表项目数据
  	CodeSetList cslist = CodeSetList.getInstance();
  	List<UcmCodeset> sexlist = cslist.getList("SEX");
  	model.addAttribute("SEXLIST",sexlist);
  	//保存或升级操作
		if(mode.equals(UCMANAGER_ACTION_ADD)){
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_ADDTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_ADD);
			model.addAttribute("vo",new Demo());
		}else if(mode.equals(UCMANAGER_ACTION_EDIT)){
		  String id = StringTools.getString(request.getParameter("id"));
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_EDITTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_EDIT);
			model.addAttribute("vo",demoRepository.getOne(Integer.parseInt(id)));
		}
    return "demo/edit";
  }
	
}
