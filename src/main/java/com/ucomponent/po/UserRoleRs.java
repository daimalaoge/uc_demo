package com.ucomponent.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ucomponent.base.controller.BaseLayuiVO;

import lombok.Data;

/**
 * 2018年11月21日
 * 代码老哥
 * NAME:角色-用户关系
 * Descp:
**/
@Entity
@Data
@Table(name = "ucm_user_role_rs") 
public class UserRoleRs implements Serializable{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private int roleId;
  @Column
  private int userId;

}
