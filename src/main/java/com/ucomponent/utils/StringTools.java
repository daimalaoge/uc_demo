package com.ucomponent.utils;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 2018年6月20日
 * 代码老哥
 * NAME:字符工具类
 * Descp:
**/
public class StringTools {
	/**
	 * 去掉null
	 * @param input
	 * @return
	 */
	public static String getString(String input) {
		if (null == input) {
			return "";
		}
		return input;
	}
	/**
	 * 如果为null则返回def
	 * @param input
	 * @param def
	 * @return
	 */
	public static String getString(String input,String def) {
		if (null == input) {
			return def;
		}
		return input;
	}
	/**
	 * 去除null和空格
	 * @param input
	 * @return
	 */
	public static String getIntString(String input) {
		if (null == input) {
			return "";
		}
		return input.replaceAll(",","").trim();
	}
	/**
	 * 过滤html字符
	 * @param input
	 * @return
	 */
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
