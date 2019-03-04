package com.ucomponent.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.BaseController;
import com.ucomponent.po.SysRole;
import com.ucomponent.repository.SysRoleRepository;
import com.ucomponent.utils.StringTools;

/**
 * 2019年2月26日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@Controller
@RequestMapping("/sysd/role")
public class RoleController extends BaseController implements ICommons{
	@Autowired
  private SysRoleRepository sysRoleRepository;
	
	@ActionName(value = "Role page List") 
	@RequestMapping("/list")
  public String list(Model model,HttpServletRequest request){		
    return "sysd/role/list";
  }
	
	@ActionName(value = "Role edit or add") 
	@RequestMapping("/boaction/{mode}")
  public String boaction(Model model,HttpServletRequest request,@PathVariable String mode){
  	//设置列表项目数据

  	//保存或升级操作
		if(mode.equals(UCMANAGER_ACTION_ADD)){
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_ADDTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_ADD);
			model.addAttribute("vo",new SysRole());
		}else if(mode.equals(UCMANAGER_ACTION_EDIT)){
		  String id = StringTools.getString(request.getParameter("id"));
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_EDITTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_EDIT);
			model.addAttribute("vo",sysRoleRepository.getOne(Integer.parseInt(id)));
		}
    return "sysd/menu/edit";
  }	
}

