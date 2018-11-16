package com.ucomponent.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
