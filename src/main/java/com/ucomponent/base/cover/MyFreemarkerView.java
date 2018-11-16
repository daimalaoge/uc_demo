package com.ucomponent.base.cover;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * 2018年6月20日
 * 代码老哥
 * NAME:
 * Descp:
**/
public class MyFreemarkerView extends FreeMarkerView{
	@Override  
  protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {  
    model.put("Demo", "the freemarker view demo");  
    super.exposeHelpers(model, request);  
  }  
}
