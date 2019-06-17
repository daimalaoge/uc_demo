package com.ucomponent.biz.po;

import com.ucomponent.base.controller.vo.BasePO;
import com.ucomponent.base.controller.vo.BizBasePO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: 测试增删改样例 PO</p>
 * <p>Description: </p>
 * <p>Copyright: Union Component</p>
 * @date:2019-06-17 11:54:23
 * @author:联合组件
 * @version:1.0
 **/ 
@Entity
@Data
@Table(name = "biz_demo")
public class Demo extends BasePO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String mingcheng = "" ; // 名称
	@Column
	private String weiyidaima = "" ; // 唯一代码
	@Column
	private double jine = 0;// 金额
	@Column
	private String codesetFenlei = "";// 分类
	@Column
	private String fashengriqi = "" ; // 发生日期
	@Column
	private int zongrenshu = 0;//  总人数


}
