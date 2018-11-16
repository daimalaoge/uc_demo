package com.ucomponent.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 2018年10月20日
 * 代码老哥
 * NAME:系统菜单
 * Descp:
**/
@Entity
@Data
@Table(name = "ucm_menu") 
public class UcmMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int pId = 0;
	@Column
	private String name = "";
	@Column
	private String url = "";
	@Column
	private String level = "";
	@Column
	private String pType = "";
	@Column
	private String icon = "";
	@Column
	private int seq = 0;
	@Column
	private String status = "0";
	@Column
	private String type = "";
	@Column
	private String remarks = "";

}
