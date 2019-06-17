package com.ucomponent.manager.po;

import com.ucomponent.base.controller.vo.BasePO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 2018年11月20日
 * 代码老哥
 * NAME:用户登录账户
 * Descp:
**/
@Entity
@Data
@Table(name = "ucm_mang_user_account")
public class MangUserAccount extends BasePO implements Serializable {
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
  private String adminYn = "N";
}
