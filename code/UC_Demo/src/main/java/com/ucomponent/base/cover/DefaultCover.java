package com.ucomponent.base.cover;

import java.util.List;
import org.springframework.stereotype.Component;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 2018年6月20日
 * 代码老哥
 * NAME:
 * Descp:
**/
@Component
public class DefaultCover implements TemplateMethodModelEx {
	
	@Override
	public Object exec(List arguments) throws TemplateModelException {
//	  CodeSetMap scmap = CodeSetMap.getInstance();
//		if (null != arguments && 2 == arguments.size()) {
//			String key = String.valueOf(arguments.get(0));
//			try {
//				switch (key) {
//				case "codekey":					
//					return scmap.getMap().get(String.valueOf(arguments.get(1)));
//				
//				default:
//					return null;
//				}
//			} catch (Exception e) {
//				return null;
//			}
//		} else {
//			return null;
//		}
	  return null;
	}

}
