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
 * 2018年11月20日
 * 代码老哥
 * NAME:用户登录账户
 * Descp:
**/
@Entity
@Data
@Table(name = "ucm_user_account") 
public class UserAccount implements Serializable{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private String loginName = "";
  @Column
  private String loginEmail = "";
  @Column
  private String loginPhone = "";
  @Column
  private String loginOtherid = "";
  @Column
  private String password = "";
  @Column
  private String codesetGstatus = "";
  @Column
  private Date createDatetime = new Date();
  @Column
  private Date updateDatetime = new Date();
  @Column
  private int createUserId = 0;
  @Column
  private int updateUserId = 0;
}
