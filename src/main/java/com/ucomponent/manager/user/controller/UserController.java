package com.ucomponent.manager.user.controller;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 2019/6/16
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
@Controller
@RequestMapping("/manager/user/my")
public class UserController extends BaseController implements ICommons {
	@ActionName(value = "My Password Page")
	//@RequiresPermissions("/manager/user/my/password")
	@RequestMapping("/password")
	public String password(Model model, HttpServletRequest request){
		return "/manager/user/my/password";
	}
}
