package com.ucomponent.base.controller;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public class BasePO implements Serializable{
	private static final long serialVersionUID = 1L;
  @Column
  private String codesetGstatus = "G_STATUS_USE";
  @Column
  private Date createDatetime = new Date();
  @Column
  private Date updateDatetime = new Date();  
  @Column
  private int createUserId = 100;
  @Column
  private int updateUserId = 0;
}
