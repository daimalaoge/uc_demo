package com.ucomponent.manager.po;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 2018年9月30日
 * 代码老哥
 * NAME:角色-菜单关系
 * Descp:
**/
@Entity
@Data
@Table(name = "ucm_mang_sys_role_menu_rs")
public class MangSysRoleMenuRs implements Serializable{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private int roleId;
  @Column
  private int menuId;
}
