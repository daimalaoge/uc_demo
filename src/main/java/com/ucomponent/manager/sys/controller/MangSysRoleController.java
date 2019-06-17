package com.ucomponent.manager.sys.controller;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.BaseController;
import com.ucomponent.base.encrypt.EncryptPO;
import com.ucomponent.manager.sys.repository.MangSysRoleMenuRsRepository;
import com.ucomponent.manager.sys.repository.MangSysRoleRepository;
import com.ucomponent.manager.po.MangSysRole;
import com.ucomponent.manager.po.MangSysRoleMenuRs;
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
@RequestMapping("/manager/sysd/role")
public class MangSysRoleController extends BaseController implements ICommons {
	@Autowired
  private MangSysRoleRepository sysRoleRepository;
	@Autowired
  private MangSysRoleMenuRsRepository sysRoleMenuRsRepository;
	
	@ActionName(value = "Role page List") 
	@RequestMapping("/list")
	@RequiresPermissions("/manager/sysd/role/list")
  public String list(Model model,HttpServletRequest request){		
    return "manager/sysd/role/list";
  }
	
	@ActionName(value = "Role edit or add") 
	@RequestMapping("/boaction/{mode}")
	@RequiresPermissions({"/manager/sysd/role/boaction/ADD","/manager/sysd/role/boaction/EDIT"})
	public String boaction(Model model,HttpServletRequest request,@PathVariable String mode){
  	//设置列表项目数据

  	//保存或升级操作
		if(mode.equals(UCMANAGER_ACTION_ADD)){
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_ADDTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_ADD);
			model.addAttribute("vo",new MangSysRole());
		}else if(mode.equals(UCMANAGER_ACTION_EDIT)){
			String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_EDITTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_EDIT);
			model.addAttribute("vo",sysRoleRepository.getOne(Integer.parseInt(id)));
			model.addAttribute("encCode", EncryptPO.encPO(super.getEncrytKey(),sysRoleRepository.getOne(Integer.parseInt(id))).getEncCode());
		}
    return "manager/sysd/role/edit";
  }
	
	@ActionName(value = "Role - Menu setup") 
	@RequestMapping("/menuset")
	@RequiresPermissions("/manager/sysd/role/menuset")
	public String menuset(Model model,HttpServletRequest request){
		String rid = StringTools.getString(request.getParameter("rid"));
		model.addAttribute("rid",rid);
		rid = StringTools.getDecHStr(super.getEncrytKey(),rid);

		List<MangSysRoleMenuRs> list = sysRoleMenuRsRepository.findByRoleId(Integer.parseInt(rid));
		StringBuffer irs = new StringBuffer();
		int i = list.size();
		for(MangSysRoleMenuRs rs:list) {
			irs.append(String.valueOf(rs.getMenuId()));
			i--;
			if(i!=0) {
				irs.append(",");
			}
		}
		model.addAttribute("mr_rsdata",irs);
		return "manager/sysd/role/pop_menuset";
	}
	
}

