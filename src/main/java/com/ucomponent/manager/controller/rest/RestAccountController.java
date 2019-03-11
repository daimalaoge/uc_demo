package com.ucomponent.manager.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.BaseRestController;
import com.ucomponent.base.entity.JsonlistData;
import com.ucomponent.po.SysRole;
import com.ucomponent.po.UserAccount;
import com.ucomponent.po.UserRoleRs;
import com.ucomponent.repository.SysRoleRepository;
import com.ucomponent.repository.UserAccountRepository;
import com.ucomponent.repository.UserRoleRsRepository;
import com.ucomponent.utils.MD5Util;
import com.ucomponent.utils.StringTools;

/**
 * 2019年2月26日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@RestController
@RequestMapping("/sysd/account")
public class RestAccountController extends BaseRestController implements ICommons{
	@Autowired
  private UserAccountRepository userAccountRepository;
	@Autowired
  private UserRoleRsRepository userRoleRsRepository;
	@Autowired
  private SysRoleRepository sysRoleRepository;
	
	@ActionName(value = "get UserAccount list data") 
  @RequestMapping("/list/data")
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
  		.findByLoginNameContainingAndLoginEmailContainingAndLoginPhoneContainingAndCodesetGstatusIn(name, loginEmail, loginPhone, status, pageable);
    }else {
    	pagedata = userAccountRepository
  		.findByIdAndLoginNameContainingAndLoginEmailContainingAndLoginPhoneContainingAndCodesetGstatusIn(Integer.parseInt(id), name, loginEmail, loginPhone, status, pageable);
    }
    
    //返回页面数据
    jd.setCode(UCMANAGER_LISTPAGE_CODE);
    jd.setCount(pagedata.getTotalElements());
    System.out.println("pagedata.getContent()-"+pagedata.getContent());
    jd.setData(super.codeKeyConvert(pagedata.getContent()));
    return jd;
  }
	
	@ActionName(value = "User get RoleSet list data") 
  @RequestMapping("/roleset/data")
	public JsonlistData rolesetList(HttpServletRequest request){    
		//接收页面参数
		String uid = StringTools.getString(request.getParameter("uid"));
    JsonlistData jd = new JsonlistData();
    List list = sysRoleRepository.findByCodesetGstatus("G_STATUS_USE");
		List<UserRoleRs> listr = userRoleRsRepository.findByUserId(Integer.parseInt(uid));
		int i = list.size();
		for(UserRoleRs rs:listr) {
			int roid = rs.getRoleId();
			for(Object inob:list) {
				SysRole in  = (SysRole)inob;
				if(in.getId() == roid) {
					in.setLAY_CHECKED(true);
				}
			}
		}
    jd.setData(list);
    return jd;
  }
  
  @ActionName(value = "UserAccount save")
  @RequestMapping("/bo/save")
  public String bosave(HttpServletRequest request,UserAccount vo){
  	String am = StringTools.getString(request.getParameter("ACTIONMODE"));
  	String name = StringTools.getString(request.getParameter("loginName"));
    String loginEmail = StringTools.getString(request.getParameter("loginEmail"));
    String loginPhone = StringTools.getString(request.getParameter("loginPhone"));

  	if(am.equals("ADD")) {
  		UserAccount uan = userAccountRepository.findByLoginNameAndCodesetGstatus(name, "G_STATUS_USE");
  		if(uan!= null ) {
  			return "-1";
  			//return "添加失败，登录名已有重复，请更换登录名";
  		}
  		UserAccount uae = userAccountRepository.findByLoginEmailAndCodesetGstatus(loginEmail, "G_STATUS_USE");
  		if(uae!= null ) {  	
  			return "-2";
  			//return "添加失败，登录邮箱已有重复，请更换登录邮箱";
  		}
  		UserAccount uap = userAccountRepository.findByLoginPhoneAndCodesetGstatus(loginPhone, "G_STATUS_USE");
  		if(uap!= null ) {  		
  			return "-3";
  			//return "添加失败，登录手机号已有重复，请更换登录手机号";
  		}
  		vo.setPassword(MD5Util.get2MD5(vo.getPassword()));
  	}else if(am.equals("EDIT")) {
  		UserAccount uan = userAccountRepository.findByLoginNameAndCodesetGstatus(name, "G_STATUS_USE");
  		if(uan != null && uan.getId() != vo.getId() ) {  
  			return "-4";
  			//return "修改失败，登录名已有重复，请更换登录名";
  		}
  		UserAccount uae = userAccountRepository.findByLoginEmailAndCodesetGstatus(loginEmail, "G_STATUS_USE");
  		if(uae != null && uae.getId() != vo.getId()  ) {  
  			return "-5";
  			//return "修改失败，登录邮箱已有重复，请更换登录邮箱";
  		}
  		UserAccount uap = userAccountRepository.findByLoginPhoneAndCodesetGstatus(loginPhone, "G_STATUS_USE");
  		if(uap != null && uap.getId() != vo.getId()  ) {  	
  			return "-6";
  			//return "修改失败，登录手机号已有重复，请更换登录手机号";
  		}
  		if(!vo.getPassword().equals("")) {
  			vo.setPassword(MD5Util.get2MD5(vo.getPassword()));
  		}
  	}
  	super.doSave(vo, request);
		userAccountRepository.save(vo);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "UserAccount del")
  @RequestMapping("/bo/del")
  public String bodel(HttpServletRequest request){
  	String id = StringTools.getString(request.getParameter("id"));
		UserAccount UserAccount = userAccountRepository.getOne(Integer.parseInt(id));	
		super.doDelete(UserAccount,request);
		userAccountRepository.save(UserAccount);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "UserAccount status")
  @RequestMapping("/bo/status")
  public String bostatus(HttpServletRequest request){
  	String id = StringTools.getString(request.getParameter("id"));
		UserAccount userAccount = userAccountRepository.getOne(Integer.parseInt(id));
		super.doStatus(userAccount,request);
		userAccountRepository.save(userAccount);
  	return UCMANAGER_DATA_SUCCUSS;
  }
  
  @ActionName(value = "User User-Role_RS save")
  @RequestMapping("/roleset/save")
	public String rolesetSave(HttpServletRequest request){
		String uid = StringTools.getString(request.getParameter("uid"));
		String treedate = StringTools.getString(request.getParameter("treedate"));
		userRoleRsRepository.deleteUrrsByRole(Integer.parseInt(uid));
		
		List<UserRoleRs> list = new ArrayList();
		if(!treedate.equals("")) {
			String[] ids = treedate.split(",");
			for(String id:ids) {
				UserRoleRs rs = new UserRoleRs();
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

