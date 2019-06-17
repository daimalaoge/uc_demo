package com.ucomponent.manager.sys.controller;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.BaseController;
import com.ucomponent.base.encrypt.EncryptPO;
import com.ucomponent.manager.sys.repository.MangSysCodesetRepository;
import com.ucomponent.manager.po.MangSysCodeset;
import com.ucomponent.utils.StringTools;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * 2019年2月26日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@Controller
@RequestMapping("/manager/sysd/setd")
public class MangSysSetdController extends BaseController implements ICommons {
	@Autowired
  private MangSysCodesetRepository sysCodesetRepository;
	
	@ActionName(value = "System set page List") 
	@RequestMapping("/list")
	@RequiresPermissions("/manager/sysd/setd/list")
  public String list(Model model,HttpServletRequest request){
		return "manager/sysd/setd/list";
  }

	@ActionName(value = "System set edit or add") 
	@RequestMapping("/boaction/{mode}")
	@RequiresPermissions({"/manager/sysd/setd/boaction/ADD","/manager/sysd/setd/boaction/EDIT"})
	public String boaction(Model model,HttpServletRequest request,@PathVariable String mode){
  	//设置列表项目数据
		String pcode = StringTools.getString(request.getParameter("pcode"));
		String plevels = StringTools.getString(request.getParameter("plevels"));
		model.addAttribute("pcode", pcode);
		if(pcode.equals("")) {
			model.addAttribute("levels", 1);
		}else{
			model.addAttribute("levels", Integer.parseInt(plevels) +2);
		}

  	//保存或升级操作
		if(mode.equals(UCMANAGER_ACTION_ADD)){
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_ADDTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_ADD);
			model.addAttribute("vo",new MangSysCodeset());
		}else if(mode.equals(UCMANAGER_ACTION_EDIT)){
			String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_EDITTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_EDIT);
			model.addAttribute("vo",sysCodesetRepository.getOne(Integer.parseInt(id)));
			model.addAttribute("encCode", EncryptPO.encPO(super.getEncrytKey(),sysCodesetRepository.getOne(Integer.parseInt(id))).getEncCode());

		}
    return "manager/sysd/setd/edit";
  }	
}

