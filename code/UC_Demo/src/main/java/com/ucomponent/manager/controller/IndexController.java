package com.ucomponent.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统页面操作控制
 * Descp:
**/
@Controller
public class IndexController {
  @RequestMapping("/index")
  private String index(Model model,HttpServletRequest request,HttpServletResponse response){
    
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
