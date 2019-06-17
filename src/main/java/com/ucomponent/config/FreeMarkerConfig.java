package com.ucomponent.config;

import com.ucomponent.authent.web.FtlRoleCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 2019/6/14
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
@Configuration
public class FreeMarkerConfig {
	@Autowired
	protected FtlRoleCheck ftlRoleCheck;
	@Autowired
	private freemarker.template.Configuration configuration;

	// Spring 初始化的时候加载配置
	@PostConstruct
	public void setConfigure() throws Exception {
		// 加载html的资源路径
		configuration.setSharedVariable("checkRole", ftlRoleCheck); //权限操作
	}

}
