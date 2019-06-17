package com.ucomponent.manager.sys.controller.rest;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.rest.BaseRestController;
import com.ucomponent.base.encrypt.EncryptPO;
import com.ucomponent.base.entity.JsonlistData;
import com.ucomponent.manager.sys.repository.MangSysRoleRepository;
import com.ucomponent.manager.user.repository.MangUserAccountRepository;
import com.ucomponent.manager.sys.repository.MangSysUserRoleRsRepository;
import com.ucomponent.manager.po.MangSysRole;
import com.ucomponent.manager.po.MangUserAccount;
import com.ucomponent.manager.po.MangSysUserRoleRs;
import com.ucomponent.utils.MD5Util;
import com.ucomponent.utils.StringTools;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 2019年2月26日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@RestController
@RequestMapping("/manager/sysd/account")
public class RestMangAccountController extends BaseRestController implements ICommons {
	@Autowired
  private MangUserAccountRepository userAccountRepository;
	@Autowired
  private MangSysUserRoleRsRepository userRoleRsRepository;
	@Autowired
  private MangSysRoleRepository sysRoleRepository;
	
	@ActionName(value = "get UserAccount list data") 
  @RequestMapping("/list/data")
	@RequiresPermissions("/manager/sysd/account/list")
	public JsonlistData listdata(HttpServletRequest request){
    //接收页面参数
    JsonlistData jd = new JsonlistData();
    //接收搜索参数
    String id = StringTools.getString(request.getParameter("id"));
    String name = StringTools.getString(request.getParameter("name"));
    String loginEmail = StringTools.getString(request.getParameter("loginEmail"));
    String loginPhone = StringTools.getString(request.getParameter("loginPhone"));
    String status = StringTools.getString(request.getParameter("status"),"G_STATUS_USE");
    //获取列表数据    
    Sort sort = new Sort(Sort.Direction.DESC,"updateDatetime"); //创建时间降序排序
    
    Pageable pageable = new PageRequest(super.getPage(request),super.getPageLimit(request),sort);
    Page pagedata = null;
    if(id.equals("")) {
    	pagedata = userAccountRepository
  		.findByOrgIdAndLoginNameContainingAndLoginEmailContainingAndLoginPhoneContainingAndCodesetGstatusIn(super.getOrgId(),name, loginEmail, loginPhone, status, pageable);
    }else {
    	pagedata = userAccountRepository
  		.findByOrgIdAndIdAndLoginNameContainingAndLoginEmailContainingAndLoginPhoneContainingAndCodesetGstatusIn(super.getOrgId(),Integer.parseInt(id), name, loginEmail, loginPhone, status, pageable);
    }
    
    //返回页面数据
    jd.setCode(UCMANAGER_LISTPAGE_CODE);
    jd.setCount(pagedata.getTotalElements());
    jd.setData(super.codeKeyConvert(pagedata.getContent()));
    return jd;
  }
	
	@ActionName(value = "User get RoleSet list data") 
  @RequestMapping("/roleset/data")
	@RequiresPermissions("/manager/sysd/account/roleset")
	public JsonlistData rolesetList(HttpServletRequest request){    
		//接收页面参数
		String uid = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter("uid"));
    JsonlistData jd = new JsonlistData();
    List list = sysRoleRepository.findByCodesetGstatus("G_STATUS_USE");
		List<MangSysUserRoleRs> listr = userRoleRsRepository.findByUserId(Integer.parseInt(uid));
		int i = list.size();
		for(MangSysUserRoleRs rs:listr) {
			int roid = rs.getRoleId();
			for(Object inob:list) {
				MangSysRole in  = (MangSysRole)inob;
				if(in.getId() == roid) {
					in.setLAY_CHECKED(true);
				}
			}
		}
    jd.setData(EncryptPO.encList(super.getEncrytKey(),list));
    return jd;
  }
  
  @ActionName(value = "UserAccount save")
  @RequestMapping("/bo/save")
  @RequiresPermissions({"/manager/sysd/account/boaction/ADD","/manager/sysd/account/boaction/EDIT"})
  public String bosave(HttpServletRequest request, MangUserAccount vo){
	  MangUserAccount po =(MangUserAccount) EncryptPO.decPO(super.getEncrytKey(),vo);
  	String am = StringTools.getString(request.getParameter("ACTIONMODE"));
  	String name = StringTools.getString(request.getParameter("loginName"));
    String loginEmail = StringTools.getString(request.getParameter("loginEmail"));
    String loginPhone = StringTools.getString(request.getParameter("loginPhone"));

  	if(am.equals("ADD")) {
		  MangUserAccount uan = userAccountRepository.findByLoginNameAndCodesetGstatus(name, "G_STATUS_USE");
  		if(uan!= null ) {
  			return "-1";
  			//return "添加失败，登录名已有重复，请更换登录名";
  		}
		  MangUserAccount uae = userAccountRepository.findByLoginEmailAndCodesetGstatus(loginEmail, "G_STATUS_USE");
  		if(uae!= null ) {  	
  			return "-2";
  			//return "添加失败，登录邮箱已有重复，请更换登录邮箱";
  		}
		  MangUserAccount uap = userAccountRepository.findByLoginPhoneAndCodesetGstatus(loginPhone, "G_STATUS_USE");
  		if(uap!= null ) {  		
  			return "-3";
  			//return "添加失败，登录手机号已有重复，请更换登录手机号";
  		}
		  po.setPassword(MD5Util.get2MD5(po.getPassword()));
  	}else if(am.equals("EDIT")) {
		  MangUserAccount uan = userAccountRepository.findByLoginNameAndCodesetGstatus(name, "G_STATUS_USE");
  		if(uan != null && uan.getId() != po.getId() ) {
  			return "-4";
  			//return "修改失败，登录名已有重复，请更换登录名";
  		}
		  MangUserAccount uae = userAccountRepository.findByLoginEmailAndCodesetGstatus(loginEmail, "G_STATUS_USE");
  		if(uae != null && uae.getId() != po.getId()  ) {
  			return "-5";
  			//return "修改失败，登录邮箱已有重复，请更换登录邮箱";
  		}
		  MangUserAccount uap = userAccountRepository.findByLoginPhoneAndCodesetGstatus(loginPhone, "G_STATUS_USE");
  		if(uap != null && uap.getId() != po.getId()  ) {
  			return "-6";
  			//return "修改失败，登录手机号已有重复，请更换登录手机号";
  		}
  		if(!po.getPassword().equals("")) {
			  po.setPassword(MD5Util.get2MD5(po.getPassword()));
  		}else{
			  MangUserAccount ua = userAccountRepository.getOne(vo.getId());
			  po.setPassword(ua.getPassword());
		   }
  	}
  	super.doSave(po, request);
		userAccountRepository.save(po);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "UserAccount del")
  @RequestMapping("/bo/del")
  @RequiresPermissions("/manager/sysd/account/bo/del")
  public String bodel(HttpServletRequest request){
  	String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
	  MangUserAccount UserAccount = userAccountRepository.getOne(Integer.parseInt(id));
		super.doDelete(UserAccount);
		userAccountRepository.save(UserAccount);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "UserAccount status")
  @RequestMapping("/bo/status")
  @RequiresPermissions("/manager/sysd/account/bo/status")
  public String bostatus(HttpServletRequest request){
  	String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
	  MangUserAccount userAccount = userAccountRepository.getOne(Integer.parseInt(id));
		super.doStatus(userAccount);
		userAccountRepository.save(userAccount);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "User User-Role_RS save")
  @RequestMapping("/roleset/save")
  @RequiresPermissions("/manager/sysd/account/roleset")
	public String rolesetSave(HttpServletRequest request){
		String uid = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter("uid"));
		String treedate = StringTools.getString(request.getParameter("treedate"));
		userRoleRsRepository.deleteUrrsByRole(Integer.parseInt(uid));
		
		List<MangSysUserRoleRs> list = new ArrayList();
		if(!treedate.equals("")) {
			String[] ids = treedate.split(",");
			for(String id:ids) {
				id = StringTools.getDecHStr(super.getEncrytKey(),id);
				MangSysUserRoleRs rs = new MangSysUserRoleRs();
				rs.setRoleId(Integer.parseInt(id));
				rs.setUserId(Integer.parseInt(uid));
				list.add(rs);
			}
		}
		if(!list.isEmpty()) {
			userRoleRsRepository.saveAll(list);
		}
		return UCMANAGER_DATA_SUCCUSS;
	}
}

