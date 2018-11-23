package com.ucomponent.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018年6月20日
 * 代码老哥
 * NAME:
 * Descp:
**/
public abstract interface ICommons{
  //返回码
  public static final String ERROR_URL = "-9";
  public static final String ERROR_NOCONNECTION = "-8";
  public static final String ERROR_NOREASON = "-99";

  //系统是否使用验证码登录 Y是 N否
  public final String SYS_CHECKCODE = "spring.profiles.active";
	
	//ACCOUNT对象在session中命名
	public static final String SESSION_ACCOUNT = "SESSION_ACCOUNT";
	//ACCOUNT对象在session中命名
  public static final String SESSION_MENULIST = "SESSION_MENULIST";
	//ACCOUNT对象可通过的路径
	public static final String SESSION_BIZPATH = "SESSION_BIZPATH";
	//验证码
	public static final String SESSION_IDECODE_IMG = "SESSION_IDECODE_IMG";

	//UC后台列表页显示条数
	public static final int UCMANAGER_DISP_SIZE = 20;
	//UC后台返回成功：1
	public static final String UCMANAGER_DATA_SUCCUSS = "1";
	//UC后台列表页正常显示
	public static final String UCMANAGER_LISTPAGE_CODE = "0";
	//UC后台数据增加
	public static final String UCMANAGER_ACTION_ADD = "ADD";
	//UC后台数据修改
	public static final String UCMANAGER_ACTION_EDIT= "EDIT";
	//UC后台数据添加标题
	public static final String UCMANAGER_ACTION_ADDTITLE= "新增";
	//UC后台数据修改标题
	public static final String UCMANAGER_ACTION_EDITTITLE= "修改";
	/* EncryptFileGen Key vaule*/
	public static final String ENCRY_FILE_KEY =	"123456"	;
	/* EncryptStringGen Key vaule*/
	public static final String ENCRY_STRING_KEY =	"123456"	;
}
