package com.ucomponent.manager.sys.controller;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.controller.BaseController;
import com.ucomponent.manager.po.MangSysMenu;
import com.ucomponent.manager.po.MangSysOrg;
import com.ucomponent.manager.po.MangUserAccount;
import com.ucomponent.manager.sys.repository.MangSysMenuRepository;
import com.ucomponent.manager.sys.repository.MangSysOrgRepository;
import com.ucomponent.manager.user.repository.MangUserAccountRepository;
import com.ucomponent.utils.MD5Util;
import com.ucomponent.utils.StringTools;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2019/6/10
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
@Controller
public class LoginController extends BaseController implements ICommons {
	@Autowired
	private MangUserAccountRepository userAccountRepository;
	@Autowired
	private MangSysMenuRepository sysMenuRepository;
	@Autowired
	private MangSysOrgRepository sysOrgRepository;

	@RequestMapping("/")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response){
		return "login";
	}

	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		//用户名密码校验，这部分代码放在login中进行
		String loginid =  StringTools.getString(request.getParameter("username"));
		String password = StringTools.getString(request.getParameter("password"));
		//判断当前的subject是否登录
		if (!loginid.equals("")){
			MangUserAccount user = userAccountRepository.findByLoginNameOrLoginEmailOrLoginPhoneOrLoginOtheridAndCodesetGstatus
							(loginid, loginid, loginid, loginid, "G_STATUS_USE");
			if (user == null) {
				model.addAttribute("ERROR","无此用户，请查看用户名是否正确");
			} else {
				if (user.getPassword().equals(MD5Util.get2MD5(password))) {
					//用户菜单设置
					List<MangSysMenu> mlist = new ArrayList<>();

					if (user.getAdminYn().equals("Y")) {
						mlist = sysMenuRepository.findByCodesetGstatusOrderByLevelsAscSeqAsc("G_STATUS_USE");
					} else {
						mlist = sysMenuRepository.getByUserId(user.getId());
					}
					Set<String> set = new HashSet();
					for(MangSysMenu m:mlist){
						if(!m.getUrlData().equals("") && !m.getUrlData().equals("#") && !m.getUrlData().equals("/")) {
							set.add(m.getUrlData());
						}
					}
					MangSysOrg org = sysOrgRepository.getOne(user.getOrgId());
					if (org.getCodesetGstatus().equals("G_STATUS_USE")) {
						//可以登录
						//生成SESSION密钥，用于数据操作
//						if (session.getAttribute(USER_ENCRYPT_KEY) == null) {
//							int ecint = (int) (Math.random() * 100000);
//							String ecstr = String.valueOf(user.getOrgId()) + "_" + String.valueOf(ecint);
//							session.setAttribute(USER_ENCRYPT_KEY, ecstr);
//						}
						String ecstr = loginid + "_" + MD5Util.get2MD5(password);
						model.addAttribute("user", user);
						model.addAttribute("menulist", mlist);
						//用户Token处理
						UsernamePasswordToken token = new UsernamePasswordToken(String.valueOf(user.getId()),password);
						try {
							Subject subject = SecurityUtils.getSubject();
							//将存有用户名和密码的token存进subject中
							subject.login(token);
							Session ssession = subject.getSession();
							ssession.setAttribute(SESSION_ORG, org);
							ssession.setAttribute(USER_ENCRYPT_KEY, ecstr);
							session.setAttribute(USER_PATH_SET, set);
							session.setAttribute(SESSION_ACCOUNT, user);
						}catch (UnknownAccountException uae){
							System.out.println("没有用户名为"+token.getPrincipal()+"的用户");
						} catch (IncorrectCredentialsException ice){
							System.out.println("用户名为："+token.getPrincipal()+"的用户密码不正确");
						} catch (LockedAccountException lae){
							System.out.println("用户名为："+token.getPrincipal()+"的用户已被冻结");
						} catch (AuthenticationException e){
							System.out.println("未知错误！");
						}
						return "manager/index";
					}else {
						model.addAttribute("ERROR","当前用户状态为无效，无法登录");
						return "login";
					}

				}else{
					model.addAttribute("ERROR","用户名或密码错误");
					return "login";
				}
			}
		}
		return "login";
	}
}
