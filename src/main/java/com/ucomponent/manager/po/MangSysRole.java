package com.ucomponent.manager.po;

import com.ucomponent.base.controller.vo.BaseLayuiVO;
import com.ucomponent.base.controller.vo.BasePO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统角色
 * Descp:
**/
@Entity
@Data
@Table(name = "ucm_mang_sys_role")
public class MangSysRole extends BaseLayuiVO implements Serializable{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private String name = "";
  @Column
  private String remarks = "";
  @Column
  private String codesetGstatus = "G_STATUS_USE";
  @Column
  private Date createDatetime = new Date();
  @Column
  private Date updateDatetime = new Date();
  @Column(updatable=false)
  private int createUserId;
  @Column
  private int updateUserId;
}
