package com.ucomponent.manager.po;

import com.ucomponent.base.controller.vo.BaseLayuiVO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 2018年9月30日
 * 代码老哥
 * NAME:操作日志
 * Descp:
**/
@Entity
@Data
@Table(name = "ucm_mang_sys_operation_log")
public class MangSysOperationLog extends BaseLayuiVO implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private String codesetOplogtype;// '日志类型',
  @Column
  private String title;// '日志标题',
  @Column
  private String remoteAddr;// '请求地址',
  @Column
  private String requestUri;// 'URI',
  @Column
  private String classFunc;// '运行类-函数',
  @Column
  private String method;// '方法名',
  @Column
  private String params;// '提交参数',
  @Column
  private String exception;// '异常',
  @Column
  private long runtime = 0;// 运行时间,
  @Column
  private String result = "";// 运行结果,
  @Column
  private String userId = "";//
  @Column
  private Date createDatetime;// '创建日期',

}
