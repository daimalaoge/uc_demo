package com.ucomponent.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * 
 * @author 代码老哥
 *
 */
@Entity
@Data
@Table(name = "ucm_operation_log") 
public class UcmOperationLog implements Serializable {
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
  private int userId = 0;//
  @Column
  private Date createDatetime;// '创建日期',

}
