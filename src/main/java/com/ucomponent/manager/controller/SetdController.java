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
import com.ucomponent.po.SysCodeset;
import com.ucomponent.repository.SysCodesetRepository;
import com.ucomponent.utils.StringTools;


/**
 * 2019年2月26日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@Controller
@RequestMapping("/sysd/setd")
public class SetdController extends BaseController implements ICommons{
	@Autowired
  private SysCodesetRepository sysCodesetRepository;
	
	@ActionName(value = "System set page List") 
	@RequestMapping("/list")
  public String list(Model model,HttpServletRequest request){		
    return "sysd/setd/list";
  }

	@ActionName(value = "System set edit or add") 
	@RequestMapping("/boaction/{mode}")
  public String boaction(Model model,HttpServletRequest request,@PathVariable String mode){
  	//设置列表项目数据
		String pcode = StringTools.getString(request.getParameter("pcode"));
		String plevels = StringTools.getString(request.getParameter("plevels"));
		model.addAttribute("pcode", pcode);
		if(pcode.equals("")) {
			model.addAttribute("levels", 1);
		}else{
			model.addAttribute("levels", Integer.parseInt(plevels) +1);
		}

  	//保存或升级操作
		if(mode.equals(UCMANAGER_ACTION_ADD)){
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_ADDTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_ADD);
			model.addAttribute("vo",new SysCodeset());
		}else if(mode.equals(UCMANAGER_ACTION_EDIT)){
		  String id = StringTools.getString(request.getParameter("id"));
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_EDITTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_EDIT);
			model.addAttribute("vo",sysCodesetRepository.getOne(Integer.parseInt(id)));
		}
    return "sysd/setd/edit";
  }	
}

