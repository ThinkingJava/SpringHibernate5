package com.ych.util;

public class Const {
/*
 * 标识码
 */
	/**
	 * 正确
	 */
 public static int RESULT_OK = 0;  
 /**
  * 表示服务器接收到的请求数据不完整
  */
 public static int RESULT_DATA_UNWELLFORMED = 1; //表示服务器接收到的请求数据不完整
 /**
  * 表示Web服务器处理方面出现了错误
  */
 public static int RESULT_WEB_ERROR = 2; //表示Web服务器处理方面出现了错误
 /**
  * 表示后台数据库处理出现错误
  */
 public static int RESULT_DB_ERROR = 3; //表示后台数据库处理出现错误
 /**
  * 权限不足
  */
 public static int RESULT_NOT_PRIV = 4; //权限不足
 /**
  * 没有合适的数据
  */
 public static int RESULT_NOT_DATA = 5; //没有合适的数据
 /**
  * 请求失败
  */
 public static int RESULT_ERROR = 6; //请求失败
 /**
  * 用户已经存在
  */
 public static int RESULT_USER_IN = 7; //用户已经存在
 /**
  * 用户名或密码错误
  */
 public static int RESULT_USERNAME_OR_PWD_ERROR = 8; //用户名或密码错误
 /**
  * 手机号码不正确
  */
 public static int RESULT_NOT_TELPHONE = 9;  //手机号码不正确
 /**
  * 用户未登录
  */
 public static int RESULT_NOT_LOGIN = 10;  //用户未登录
 /**
  * 无法连接
  */
 public static int RESULT_DB_NOT_CONNECT = 11; //无法连接
 
 
 
 
 /////////////////////////////////////
 
 /**
  * 微信支付二维码生成路径
  */
 public static String WX_QRCODE="/Resources/wx/pay/";
 /**
  * 微信返回成功状态
  */
 public static String WX_SUCCESS="SUCCESS";
 
 ///////////////////////////////////
 /**
  * 数据源前缀
  */
 public static String DATASOURCE = "datasource_";
 
 /**
  * 默认数据源
  */
public static String  DEFAULTSOURCE ="dataSource";
 /**
  * 系统用户
  */
 public static String SUPERUSER ="admin";
 /**
  * 公司图标
  */
 public static String COMPAYLOGO = "Resources/logo/logo.png";
 
 /**
  * 驱动名称
  */
 public static String DRIVERCLASSNAME="driverClassName";
 
 public static String DRIVERCLASSNAMECONTENT="com.microsoft.sqlserver.jdbc.SQLServerDriver";
 /**
  * 数据源url名称
  */
 public static String DRIVERURL="url";
 /**
  * 数据源url
  */
 public static String DRIVERURLCONTENT="jdbc:sqlserver://61.145.107.56:2000;DatabaseName=";
 /**
  * 登录用户名
  */
// public static String USERNAME="app";
// 
// public static String PASSWORD="1580";
 
 
 
}
