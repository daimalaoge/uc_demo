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
import com.ucomponent.po.SysCodeset;
import com.ucomponent.po.SysMenu;
import com.ucomponent.repository.SysMenuRepository;
import com.ucomponent.utils.StringTools;

/**
 * 2019年2月26日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@Controller
@RequestMapping("/sysd/menu")
public class MenuController extends BaseController implements ICommons{
	@Autowired
  private SysMenuRepository sysMenuRepository;
	
	@ActionName(value = "System Menu List") 
	@RequestMapping("/list")
  public String list(Model model,HttpServletRequest request){		
    return "sysd/menu/menuset";
  }
	
	@ActionName(value = "System Menu inner List") 
	@RequestMapping("/list2")
  public String list2(Model model,HttpServletRequest request){
		String pid = StringTools.getString(request.getParameter("pid"));
  	model.addAttribute("pid",pid);
  	
  	String plevels = StringTools.getString(request.getParameter("plevels"));
  	model.addAttribute("plevels",plevels);
    return "sysd/menu/list2";
  }

	@ActionName(value = "Menu edit or add") 
	@RequestMapping("/boaction/{mode}")
  public String boaction(Model model,HttpServletRequest request,@PathVariable String mode){
  	//设置列表项目数据
  	CodeSetList cslist = CodeSetList.getInstance();
  	List<SysCodeset> mlist = cslist.getList("MENU_TYPE");
  	model.addAttribute("MENUTYPE",mlist);
  	
  	String pid = StringTools.getString(request.getParameter("pid"));
  	model.addAttribute("pid",pid);
  	
  	String plevels = StringTools.getString(request.getParameter("plevels"));
  	if(plevels.equals(""))plevels="0";
  	model.addAttribute("plevels",Integer.parseInt(plevels));
  	
  	//保存或升级操作
		if(mode.equals(UCMANAGER_ACTION_ADD)){
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_ADDTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_ADD);
			model.addAttribute("vo",new SysMenu());
		}else if(mode.equals(UCMANAGER_ACTION_EDIT)){
		  String id = StringTools.getString(request.getParameter("id"));
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_EDITTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_EDIT);
			model.addAttribute("vo",sysMenuRepository.getOne(Integer.parseInt(id)));
			System.out.println("sysMenuRepository.getOne(Integer.parseInt(id))-"+sysMenuRepository.getOne(Integer.parseInt(id)));
		}
    return "sysd/menu/edit";
  }	
}

