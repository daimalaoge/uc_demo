package com.ucomponent.biz.demo.controller;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.base.controller.BaseController;
import com.ucomponent.base.encrypt.EncryptPO;

import com.ucomponent.base.entity.CodeSetList;
import com.ucomponent.manager.po.MangSysCodeset;
import com.ucomponent.utils.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.ucomponent.biz.demo.repository.DemoRepository;
import com.ucomponent.biz.po.Demo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Title: 测试增删改样例 Controller</p>
 * <p>Description: </p>
 * <p>Copyright: Union Component</p>
 * @date:2019-06-17 11:54:23
 * @author:联合组件
 * @version:1.0
 **/
@Controller
@RequestMapping("/biz/ceshi/demo")
public class DemoController  extends BaseController implements ICommons {
	@Autowired
	private DemoRepository repository;
	
	@ActionName(value = "测试增删改样例 action List")
	@RequestMapping("/list")
	@RequiresPermissions("/biz/ceshi/demo/list")
	public String list(Model model, HttpServletRequest request){
		//设置列表项目数据 - 搜索列表使用


		return "biz/demo/list";
	}

	@ActionName(value = "测试增删改样例 action Edit or Add")
	@RequestMapping("/boaction/{mode}")
	@RequiresPermissions({"/biz/ceshi/demo/boaction/ADD","/biz/ceshi/demo/boaction/EDIT"})
	public String boaction(Model model,HttpServletRequest request,@PathVariable String mode){

		//设置列表项目数据
		CodeSetList cslist = CodeSetList.getInstance();
		List<MangSysCodeset> list = cslist.getList("BIZ_FENLEI");
		model.addAttribute("BIZ_FENLEI",list);

		//保存或升级操作
		if(mode.equals(UCMANAGER_ACTION_ADD)){
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_ADDTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_ADD);
			model.addAttribute("vo",new Demo());
		}else if(mode.equals(UCMANAGER_ACTION_EDIT)){
			String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
			Demo vo = repository.findById(Integer.parseInt(id)).orElse(null);
			model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_EDITTITLE);
			model.addAttribute("ACTIONMODE",UCMANAGER_ACTION_EDIT);
			model.addAttribute("vo",vo);
			model.addAttribute("encCode", EncryptPO.encPO(super.getEncrytKey(),vo).getEncCode());
		}
		return "biz/demo/edit";
	}

	@ActionName(value = "测试增删改样例 action Infor")
	@RequestMapping("/infor")
	@RequiresPermissions("/biz/ceshi/demo/infor")
	public String infor(Model model,HttpServletRequest request){
		String id = StringTools.getDecHStr(super.getEncrytKey(),request.getParameter(UCMANAGER_PAGEENCPAR_NAME));
		Demo vo = repository.findById(Integer.parseInt(id)).orElse(null);
		model.addAttribute("SHOWTITLE",UCMANAGER_ACTION_INFORTITLE);
		model.addAttribute("vo",super.codeKeyConvert(vo));
		model.addAttribute("encCode", EncryptPO.encPO(super.getEncrytKey(),vo).getEncCode());

		return "biz/demo/infor";
	}
}
