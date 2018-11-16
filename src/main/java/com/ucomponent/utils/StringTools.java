package com.ucomponent.utils;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 2018年6月20日
 * 代码老哥
 * NAME:
 * Descp:
**/
public class StringTools {
	public static String getString(String input) {
		if (null == input) {
			return "";
		}
		return input;
	}
	
	public static String getString(String input,String def) {
		if (null == input) {
			return def;
		}
		return input;
	}
	public static String getIntString(String input) {
		if (null == input) {
			return "";
		}
		return input.replaceAll(",","").trim();
	}



	
	public static String getHtmlString(String input) {
		if (null == input) {
			return "";
		}
		input = StringEscapeUtils.escapeHtml3(input);
		return input;
	}
	
	/**
	 * 解密
	 * @param inword
	 * @return
	 */
	public static String getDecHStr(String inword){
		inword = getString(inword);
		try {
			return new EncryptStringGen().decrypt(inword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 加密
	 * @param inword
	 * @return
	 */
	public static String getEncStr(String inword){
		inword = getString(inword);
		try {
			return new EncryptStringGen().encrypt(inword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
}
