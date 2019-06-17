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
 * NAME:系统级数据字典
 * Descp:
**/
@Entity
@Data
@Table(name = "ucm_mang_sys_codeset")
public class MangSysCodeset extends BaseLayuiVO implements Serializable{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private String name = "";// '名称',
  @Column
  private String codeKey = "";// '编码',
  @Column
  private String level = "";// '级别',
  @Column
  private String descp = "";// '说明',
  @Column
  private String upperCode = "";// '上级编码',
  @Column(name="attr_value1")
  private String attrValue1 = "";// '',
  @Column(name="attr_value2")
  private String attrValue2 = "";// '',
  @Column(name="attr_value3")
  private String attrValue3 = "";// '',
  @Column(name="attr_value4")
  private String attrValue4 = "";// '',
  @Column(name="attr_value5")
  private String attrValue5 = "";// '',
  @Column(name="attr_value6")
  private String attrValue6 = "";// '',
  @Column(name="attr_value7")
  private String attrValue7 = "";// '',
  @Column(name="attr_value8")
  private String attrValue8 = "";// '',
  @Column(name="attr_value9")
  private String attrValue9 = "";// '',
  @Column(name="attr_value10")
  private String attrValue10 = "";// '',
  @Column
  private int seq = 0;// '顺序',
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
