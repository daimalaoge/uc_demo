package com.ucomponent.manager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucomponent.base.ICommons;
import com.ucomponent.po.SysMenu;
import com.ucomponent.po.UserAccount;
import com.ucomponent.repository.SysMenuRepository;
import com.ucomponent.repository.UserAccountRepository;
import com.ucomponent.utils.MD5Util;
/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统页面操作控制
 * Descp:
**/
@Controller
public class IndexController implements ICommons{
  @Autowired
  private UserAccountRepository userAccountRepository;
  @Autowired
  private SysMenuRepository sysMenuRepository;
  
  @RequestMapping("/index")
  private String index(Model model,HttpServletRequest request,HttpServletResponse response){
    HttpSession session = request.getSession();
    //用户名密码校验，这部分代码放在login中进行
    String loginid = "admin";
    String password = "123456";
    UserAccount user = userAccountRepository.findByLoginNameOrLoginEmailOrLoginPhoneOrLoginOtheridAndCodesetGstatus
        (loginid, loginid, loginid, loginid, "G_STATUS_USE");
    if(user == null){
      
    }else{
      if(user.getPassword().equals(MD5Util.get2MD5(password))){
        session.setAttribute(SESSION_ACCOUNT, user);
        //用户菜单设置
        List<SysMenu> mlist = sysMenuRepository.getByUserId(user.getId());
        session.setAttribute(SESSION_MENULIST, mlist);
        model.addAttribute("user",user);
        return "index";
      }
    }
    return "index";
  }
  
  @RequestMapping("/setting")
  private String setting(Model model,HttpServletRequest request,HttpServletResponse response){
    
    return "setting";
  }
  
  @RequestMapping("/main")
  private String mainpage(Model model,HttpServletRequest request,HttpServletResponse response){
    
    return "main";
  }
}
