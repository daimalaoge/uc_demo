package com.ucomponent.manager.sys.controller;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.controller.BaseController;
import com.ucomponent.manager.po.MangUserAccount;
import com.ucomponent.manager.sys.repository.MangSysMenuRepository;
import com.ucomponent.manager.sys.repository.MangSysOrgRepository;
import com.ucomponent.manager.user.repository.MangUserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 2019/6/12
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
@Controller
public class IndexController extends BaseController implements ICommons {
	@Autowired
	private MangUserAccountRepository userAccountRepository;
	@Autowired
	private MangSysMenuRepository sysMenuRepository;
	@Autowired
	private MangSysOrgRepository sysOrgRepository;

	@RequestMapping("/manager/index")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) {

		return "manager/index";
	}

	@RequestMapping("/setting")
	public String setting(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "manager/setting";
	}

	@RequestMapping("/manager/main")
	public String mainpage(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "manager/main";
	}

	@RequestMapping("/unauthor")
	public String unauthor(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "common/403";
	}

	@RequestMapping("/forget")
	public String forget(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "forget";
	}

	@RequestMapping("/register")
	public String register(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "register";
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("logout");
//		HttpSession session = request.getSession();
//		TokenMap tokenMap = TokenMap.getInstance();
//		tokenMap.removeToken((String)session.getAttribute(SESSION_LOGINID));
//		session.removeAttribute(SESSION_ACCOUNT);
//		session.removeAttribute(USER_ENCRYPT_KEY);
//		session.removeAttribute(SESSION_MENULIST);
//		session.removeAttribute(SESSION_ORG);

		return "login";
	}
}
