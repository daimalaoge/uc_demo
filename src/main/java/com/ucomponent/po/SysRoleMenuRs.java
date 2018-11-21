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
 * 2018年9月30日
 * 代码老哥
 * NAME:角色-菜单关系
 * Descp:
**/
@Entity
@Data
@Table(name = "ucm_sys_role_menu_rs") 
public class SysRoleMenuRs implements Serializable{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private int roleId;
  @Column
  private int menuId;
}
