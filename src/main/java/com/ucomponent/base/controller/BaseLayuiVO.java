package com.ucomponent.base.controller;

import javax.persistence.Transient;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 2019年3月11日
 * @Author:Daimalaoge
 * @Title:
 * @Descpt:
 */

public class BaseLayuiVO {
	@JSONField(name ="LAY_CHECKED")
	@Transient
	private boolean LAY_CHECKED = false;

	@JsonProperty("LAY_CHECKED")
	public boolean isLAY_CHECKED() {
		return LAY_CHECKED;
	}
	
	@JsonProperty("LAY_CHECKED")
	public void setLAY_CHECKED(boolean lAY_CHECKED) {
		LAY_CHECKED = lAY_CHECKED;
	}
	
}

