package com.ucomponent.base.entity;

import java.util.List;

import lombok.Data;

@Data
public class JsonlistData {
  private String code = "0";
  private String msg = "暂无数据";
  private long count = 0;
  private List<Object> data = null;
}
