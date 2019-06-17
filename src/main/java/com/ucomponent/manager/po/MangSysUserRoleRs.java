package com.ucomponent.manager.po;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 2018年11月21日
 * 代码老哥
 * NAME:角色-用户关系
 * Descp:
**/
@Entity
@Data
@Table(name = "ucm_mang_sys_user_role_rs")
public class MangSysUserRoleRs implements Serializable{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private int roleId;
  @Column
  private int userId;

}
