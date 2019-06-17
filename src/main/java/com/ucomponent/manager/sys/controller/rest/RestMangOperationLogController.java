package com.ucomponent.manager.sys.controller.rest;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.rest.BaseRestController;
import com.ucomponent.base.entity.JsonlistData;
import com.ucomponent.manager.sys.repository.MangSysOperationLogRepository;
import com.ucomponent.utils.DateTools;
import com.ucomponent.utils.StringTools;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2019年2月20日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */
@RestController
@RequestMapping("/manager/sysd/oplog")
public class RestMangOperationLogController extends BaseRestController implements ICommons {
	@Autowired
  private MangSysOperationLogRepository sysOperationLogRepository;
	
	@ActionName(value = "get OperationLog list data") 
  @RequestMapping("/list/data")
	@RequiresPermissions("/manager/sysd/oplog/list")
	public JsonlistData listdata(HttpServletRequest request){
    //接收页面参数
    JsonlistData jd = new JsonlistData();
    //接收搜索参数
    String userid = StringTools.getString(request.getParameter("userid"));
    String title = StringTools.getString(request.getParameter("title"));
    String datestart = StringTools.getString(request.getParameter("datestart"));
    String dateend = StringTools.getString(request.getParameter("dateend"));
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date ds = new Date();
    Date de = new Date();
    if(dateend.equals("")) {
    	SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
    	dateend = sdfd.format(new Date());
    }
    if(datestart.equals("")) {
    	datestart = DateTools.getLastday(dateend, 7);
    }
		try {
			if(!datestart.equals("")) {
				ds = sdf.parse(datestart+" 00:00:00");
			}
			if(!dateend.equals("")) {
				de = sdf.parse(dateend+" 23:59:59");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    //获取列表数据    
    Sort sort = new Sort(Sort.Direction.DESC,"createDatetime"); //创建时间降序排序
    Pageable pageable = new PageRequest(super.getPage(request),super.getPageLimit(request),sort);
    Page pagedata = sysOperationLogRepository.findByUserIdContainingAndTitleContainingAndCreateDatetimeBetween(userid, title, ds, de, pageable);
    //返回页面数据
    jd.setCode(UCMANAGER_LISTPAGE_CODE);
    jd.setCount(pagedata.getTotalElements());
    jd.setData(super.codeKeyConvert(pagedata.getContent()));
    return jd;
  }
}

