package com.ucomponent.base;

/**
 * 2018年6月20日
 * 代码老哥
 * NAME:
 * Descp:
**/
public abstract interface ICommons {
  //返回码
  public static final String ERROR_URL = "-9";
  public static final String ERROR_NOCONNECTION = "-8";
  public static final String ERROR_NOREASON = "-99";

  //系统是否使用验证码登录 Y是 N否
  public final String SYS_CHECKCODE = "spring.profiles.active";
	
	//ACCOUNT对象在session中命名
	public static final String SESSION_ACCOUNT = "SESSION_ACCOUNT";
	//ORG
	public static final String SESSION_ORG = "SESSION_ORG";
	//用户临时加解密KEY，首次登陆后生成，Session失效后失效
	public static final String USER_ENCRYPT_KEY = "SESSION_USER_ENCRYPT_KEY";
	//用户权限SET
	public static final String USER_PATH_SET = "USER_PATH_SET";
	//验证码
	public static final String SESSION_IDECODE_IMG = "SESSION_IDECODE_IMG";
	//用户
	public static final String STATUS_USE = "G_STATUS_USE";


	//BIZ 定义
	//action 定义
	public static final int MAX_CODE_LENTH = 45;  //最大CODE长度
	//action 定义
	public static final String ACTION= "ACT";
	//function 定义
	public static final String FUNCTION= "FUN";
	//page 定义
	public static final String PAGE = "PAG";
	//Inventory 定义
	public static final String INVENTORY = "INV";
	//platform 定义
	public static final String PLATFORM = "PLT";
	//orgnazition 定义
	public static final String ORG = "ORG";
	//field 定义
	public static final String FIELD = "FLD";

	//biz codeset 前缀
	public static final String BIZCODE_PRFIX = "biz_codeset_";
	public static final String GET_BIZ_CODESET = "getBizCodeset";
	public static final String SET_BIZ_CODESET = "setBizCodeset";

	//UC后台列表页显示条数
	public static final int UCMANAGER_DISP_SIZE = 20;
	//UC后台返回成功：1
	public static final String UCMANAGER_DATA_SUCCUSS = "1";
	//UC后台返回失败：2
	public static final String UCMANAGER_DATA_ERROR = "-99";
	//UC后台列表页正常显示
	public static final String UCMANAGER_LISTPAGE_CODE = "0";
	//UC后台数据增加
	public static final String UCMANAGER_ACTION_ADD = "ADD";
	//UC后台数据修改
	public static final String UCMANAGER_ACTION_EDIT= "EDIT";
	//加密ID页面参数名称
	public static final String UCMANAGER_PAGEENCPAR_NAME =	"encCode"	;
	//UC后台数据添加标题
	public static final String UCMANAGER_ACTION_ADDTITLE= "新增";
	//UC后台数据修改标题
	public static final String UCMANAGER_ACTION_EDITTITLE= "修改";
	//UC后台数据详情标题
	public static final String UCMANAGER_ACTION_INFORTITLE= "详情";
	/* EncryptFileGen Key vaule*/
	public static final String ENCRY_FILE_KEY =	"123456"	;
	/* EncryptStringGen Key vaule*/
	public static final String ENCRY_STRING_KEY =	""	;
	/* Token过期时间  */
	public static final int TOKEN_EXPIRE_TIME =	8 * 60 * 60 * 1000;
	/* Token定时器检查时间  */
	public static final int TOKEN_SCHEDULING_TIME =	60 * 60 * 1000;

	//默认平台CODE
	public static final String PLANTFORM_CODE =	"PLT_DEFORG_MANAGER"	;
	//默认平台SRCCODE
	public static final String PLANTFORM_SRCCODE =	"manager"	;
	//默认平台SRCCODE
	public static final int ORG_ID =1	;
}
