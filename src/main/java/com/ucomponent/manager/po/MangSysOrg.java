package com.ucomponent.manager.po;

import com.ucomponent.base.controller.vo.BaseLayuiVO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 2019/5/9
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
@Entity
@Data
@Table(name = "ucm_mang_sys_org")
public class MangSysOrg extends BaseLayuiVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name = "";
	@Column(updatable=false)
	private String code = "";
	@Column
	private String codesetGstatus = "G_STATUS_USE";
	@Column
	private Date createDatetime = new Date();
	@Column
	private Date updateDatetime = new Date();
	@ManyToOne
	@JoinColumn(name="create_user_id",updatable=false)
	private MangUserAccount createUser;
	@ManyToOne
	@JoinColumn(name="update_user_id",updatable=false)
	private MangUserAccount updateUser;
}
