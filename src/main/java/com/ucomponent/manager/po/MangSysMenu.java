package com.ucomponent.manager.po;

import com.ucomponent.base.controller.vo.BaseLayuiVO;
import com.ucomponent.base.controller.vo.BasePO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 2018年10月20日
 * 代码老哥
 * NAME:系统菜单
 * Descp:
**/
@Entity
@Data
@Table(name = "ucm_mang_sys_menu")
public class MangSysMenu extends BaseLayuiVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int upperId = 0;
	@Column
	private String name = "";
	@Column
	private String url = "";
	@Column
  private String urlData = "";
	@Column
	private int levels = 0;
	@Column
	private String icon = "";
	@Column
	private int seq = 0;
	@Column
	private String codesetMenutype = "";
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
