package com.ucomponent.manager.user.controller.rest;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.rest.BaseRestController;
import com.ucomponent.base.encrypt.EncryptPO;
import com.ucomponent.manager.po.MangUserAccount;
import com.ucomponent.manager.user.repository.MangUserAccountRepository;
import com.ucomponent.utils.MD5Util;
import com.ucomponent.utils.StringTools;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 2019/6/16
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
@RestController
@RequestMapping("/manager/user/my")
public class RestUserController  extends BaseRestController implements ICommons {
	@Autowired
	private MangUserAccountRepository userAccountRepository;

	@ActionName(value = "User password change save")
	@RequestMapping("/password/save")
	//@RequiresPermissions("/manager/user/my/password")
	public String passwordsave(HttpServletRequest request, MangUserAccount vo){
		String passwordold = StringTools.getString(request.getParameter("passwordold"));
		String passwordnew1 = StringTools.getString(request.getParameter("passwordnew1"));
		String passwordnew2 = StringTools.getString(request.getParameter("passwordnew2"));

		if(passwordold.equals("")) {
			return "-1";
		}

		MangUserAccount uae = userAccountRepository.getOne(super.getUserId());
		if(!uae.getPassword().equals(MD5Util.get2MD5(passwordold))) {
			return "-1";
		}
		if(!passwordnew1.equals(passwordnew2)) {
			return "-2";
		}
		uae.setPassword(MD5Util.get2MD5(passwordnew1));
		super.doSave(uae, request);
		userAccountRepository.save(uae);
		return UCMANAGER_DATA_SUCCUSS;
	}
}
