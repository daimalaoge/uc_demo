package com.ucomponent.manager.sys.controller;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.BaseController;
import com.ucomponent.base.encrypt.EncryptPO;
import com.ucomponent.base.entity.CodeSetList;
import com.ucomponent.manager.sys.repository.MangSysMenuRepository;
import com.ucomponent.manager.po.MangSysCodeset;
import com.ucomponent.manager.po.MangSysMenu;
import com.ucomponent.utils.StringTools;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 2019年2月26日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@Controller
@RequestMapping("/manager/sysd/menu")
public class MangSysMenuController extends BaseController implements ICommons {
	@Autowired
  private MangSysMenuRepository sysMenuRepository;
	
	@ActionName(value = "System Menu List") 
	@RequestMapping("/list")
	@RequiresPermissions("/manager/sysd/menu/list")
  public String list(Model model,HttpServletRequest request){		
    return "manager/sysd/menu/list";
  }

	@ActionName(value = "Menu edit or add") 
	@RequestMapping("/boaction/{mode}")
	@RequiresPermissions({"/manager/sysd/menu/boaction/ADD","/manager/sysd/menu/boaction/EDIT"})
	public String boaction(Model model,HttpServletRequest request,@PathVariable String mode){
  	//设置列表项目数据
  	CodeSetList cslist = CodeSetList.getInstance();
  	List<MangSysCodeset> mlist = cslist.getList("MENU_TYPE");
  	model.addAttribute("MENUTYPE",mlist);
  	
  	String pid = StringTools.getString(request.getParameter("pid"));
  	String plevels = StringTools.getString(request.getParameter("plevels"));
  	if(plevels.equals(""))plevels="0";
		if(pid.equals(""))pid="0";
  	model.addAttribute("pid",Integer.parseInt(pid));
  	if(plevels.equals("0"))model.addAttribute("plevels",1);
		else if(plevels.equals("1"))model.addAttribute("plevels",2);
		else if(plevels.equals("2"))model.addAttribute("plevels",3);
	  else if(plevels.equals("3"))model.addAttribute("plevels",3);
  	//保存或升级操作
		if(mode.equals(UCMANAGER_ACTION_ADD)){
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_ADDTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_ADD);
			model.addAttribute("vo",new MangSysMenu());
		}else if(mode.equals(UCMANAGER_ACTION_EDIT)){
			String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
			if(!id.equals("")){
				model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_EDITTITLE);
				model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_EDIT);
				model.addAttribute("vo",sysMenuRepository.getOne(Integer.parseInt(id)));
				model.addAttribute("encCode", EncryptPO.encPO(super.getEncrytKey(),sysMenuRepository.getOne(Integer.parseInt(id))).getEncCode());
			}
		}
    return "manager/sysd/menu/edit";
  }	
}

