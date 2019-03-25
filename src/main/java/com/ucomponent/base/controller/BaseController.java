package com.ucomponent.base.controller;

import java.util.List;

/**
 * 2018年10月28日
 * BaseController.java
 * 代码老哥
 * NAME:
 * Descp:
**/
public class BaseController {
	public List<BaseLayuiVO> codeKeyConvert(List<BaseLayuiVO> jsonlist){
		
	  return new ControllerTools().codeKeyConvert(jsonlist);
	}
}
