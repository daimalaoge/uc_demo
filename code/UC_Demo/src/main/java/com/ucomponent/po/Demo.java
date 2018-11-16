package com.ucomponent.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 2018年10月22日
 * Demo.java
 * 代码老哥
 * NAME:
 * Descp:
**/
@Entity
@Data
@Table(name = "ucm_demo") 
public class Demo implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private String name = "";
  @Column
  private String descp = "";
  @Column
  private String birthday = "";
  @Column
  private String provence = "";
  @Column
  private String city = "";
  @Column
  private String district = "";
  @Column
  private int point = 0;
  @Column
  private double deposit = 0.0; //'存款',
  @Column
  private String codesetSex = "";
  @Column
  private String codesetGstatus = "G_STATUS_USE";// '0 正常 1 无效 2 删除',
  @Column
  private Date createDatetime = new Date();
  @Column
  private Date updateDatetime = new Date();
  @Column
  private int createUserId = 0;
  @Column
  private int updateUserId = 0;
}
