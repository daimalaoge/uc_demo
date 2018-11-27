package com.ucomponent.base.controller;

import java.util.Date;
import javax.persistence.Column;

import lombok.Data;

@Data
public class BasePO {
  @Column
  private String codesetGstatus = "G_STATUS_USE";
  @Column
  private Date createDatetime = new Date();
  @Column
  private Date updateDatetime = new Date();  
  @Column
  private int createUserId = 0;
  @Column
  private int updateUserId = 0;
}
