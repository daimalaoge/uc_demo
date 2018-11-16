package com.ucomponent.base.entity;

import java.util.List;

import lombok.Data;
/**
 * 2018年9月30日
 * 代码老哥
 * NAME:页面用Json对象
 * Descp:
**/
@Data
public class JsonlistData {
  private String code = "0";
  private String msg = "暂无数据";
  private long count = 0;
  private List<Object> data = null;
}
