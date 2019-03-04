package com.ucomponent.manager.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.BaseController;
import com.ucomponent.repository.SysOperationLogRepository;
import com.ucomponent.utils.DateTools;
import com.ucomponent.utils.StringTools;

/**
 * 2019年2月20日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@Controller
@RequestMapping("/sysd/oplog")
public class OperationLogController extends BaseController implements ICommons{
	@Autowired
  private SysOperationLogRepository sysOperationLogRepository;
	
	@ActionName(value = "Operation Log List") 
	@RequestMapping("/list")
  public String list(Model model,HttpServletRequest request){		
    String datestart = StringTools.getString(request.getParameter("datestart"));
    String dateend = StringTools.getString(request.getParameter("dateend"));

    if(dateend.equals("")) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	dateend = sdf.format(new Date());
    	model.addAttribute("dateend", dateend);
    }
    if(datestart.equals("")) {
    	datestart = DateTools.getLastday(dateend, 7);
    	model.addAttribute("datestart", datestart);
    }
    return "sysd/oplog/list";
  }
}

