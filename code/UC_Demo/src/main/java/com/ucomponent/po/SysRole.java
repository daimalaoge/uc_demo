package com.ucomponent.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统角色
 * Descp:
**/
@Entity
@Data
@Table(name = "ucm_sys_role") 
public class SysRole implements Serializable{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private String name = "";
  @Column
  private String codesetGstatus = "G_STATUS_USE";
  @Column
  private String remarks = "";
  @ManyToOne
  @JoinColumn(name = "create_user_id")
  private UserAccount createUser;
  @Column
  private Date createDate = new Date();
  @ManyToOne
  @JoinColumn(name = "update_user_id")
  private UserAccount updateUser;
  @Column
  private Date updateDate = new Date();
}
