package com.ucomponent.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ucomponent.base.encrypt.EncryptPO;
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
import com.ucomponent.po.SysCodeset;
import com.ucomponent.repository.DemoRepository;
import com.ucomponent.utils.StringTools;

/**
 * 2018年10月27日
 * DemoController.java
 * 代码老哥
 * NAME:示例类页面跳转
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
	  System.out.println("-----------+++++++++++++++++++");
  	return "demo/list";
  }
	
  @ActionName(value = "Demo edit or add") 
	@RequestMapping("/boaction/{mode}")
  public String boaction(Model model,HttpServletRequest request,@PathVariable String mode){
  	//设置列表项目数据
  	CodeSetList cslist = CodeSetList.getInstance();
  	List<SysCodeset> sexlist = cslist.getList("SEX");
  	model.addAttribute("SEXLIST",sexlist);
  	//保存或升级操作
		if(mode.equals(UCMANAGER_ACTION_ADD)){
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_ADDTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_ADD);
			model.addAttribute("vo",new Demo());
		}else if(mode.equals(UCMANAGER_ACTION_EDIT)){
		  String id = StringTools.getDecHStr(request.getParameter("encCode"));
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_EDITTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_EDIT);

			model.addAttribute("vo", EncryptPO.encPO(demoRepository.getOne(Integer.parseInt(id))));
			model.addAttribute("encCode",EncryptPO.encPO(demoRepository.getOne(Integer.parseInt(id))).getEncCode());
		}
    return "demo/edit";
  }	
}
