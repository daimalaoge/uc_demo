package com.ucomponent.manager.sys.controller;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.BaseController;
import com.ucomponent.base.controller.vo.BaseLayuiVO;
import com.ucomponent.base.encrypt.EncryptPO;
import com.ucomponent.manager.user.repository.MangUserAccountRepository;
import com.ucomponent.manager.sys.repository.MangSysUserRoleRsRepository;
import com.ucomponent.manager.po.MangUserAccount;
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
@RequestMapping("/manager/sysd/account")
public class MangSysAccountController extends BaseController implements ICommons {
	@Autowired
  private MangUserAccountRepository userAccountRepository;
	@Autowired
  private MangSysUserRoleRsRepository userRoleRsRepository;
	
	@ActionName(value = "Account page List")
	@RequiresPermissions("/manager/sysd/account/list")
	@RequestMapping("/list")
  public String list(Model model,HttpServletRequest request){
    return "manager/sysd/account/list";
  }

	@ActionName(value = "Account edit or add")
	@RequiresPermissions({"/manager/sysd/account/boaction/ADD","/manager/sysd/account/boaction/EDIT"})
	@RequestMapping("/boaction/{mode}")
  public String boaction(Model model,HttpServletRequest request,@PathVariable String mode){
  	//设置列表项目数据

  	//保存或升级操作
		if(mode.equals(UCMANAGER_ACTION_ADD)){
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_ADDTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_ADD);
			model.addAttribute("vo",new MangUserAccount());
		}else if(mode.equals(UCMANAGER_ACTION_EDIT)){
		  String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_EDITTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_EDIT);

			java.util.Optional po = userAccountRepository.findById(Integer.parseInt(id));
			if(po.isPresent()){
				BaseLayuiVO ma = (BaseLayuiVO)po.get();
				ma = EncryptPO.encPO(super.getEncrytKey(),ma);
				model.addAttribute("vo", EncryptPO.encPO(super.getEncrytKey(),ma));
				model.addAttribute("encCode",EncryptPO.encPO(super.getEncrytKey(),userAccountRepository.getOne(Integer.parseInt(id))).getEncCode());

			}

		}
    return "manager/sysd/account/edit";
  }

	@ActionName(value = "Account - Role setup")
	@RequiresPermissions("/manager/sysd/account/roleset")
	@RequestMapping("/roleset")
	public String roleset(Model model,HttpServletRequest request){
		String uid = StringTools.getString(request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
		model.addAttribute("uid",uid);
		return "manager/sysd/account/pop_roleset";
	}
}

