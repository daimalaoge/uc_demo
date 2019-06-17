package com.ucomponent.manager.sys.controller;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.BaseController;
import com.ucomponent.manager.sys.repository.MangSysOperationLogRepository;
import com.ucomponent.utils.DateTools;
import com.ucomponent.utils.StringTools;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2019年2月20日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@Controller
@RequestMapping("/manager/sysd/oplog")
public class MangSysOperationLogController extends BaseController implements ICommons {
	@Autowired
  private MangSysOperationLogRepository sysOperationLogRepository;
	
	@ActionName(value = "Operation Log List")
	@RequiresPermissions("/manager/sysd/oplog/list")
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
    return "manager/sysd/oplog/list";
  }
}

