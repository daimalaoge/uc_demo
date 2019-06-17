package com.ucomponent.manager.sys.controller.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Menu {
  private String title = "";
  private String icon = "";
  private String spread = "";
  private List<Menu> children = new ArrayList<Menu>();
  private String path = "";
  private boolean open = true;
  private int pid = 0;
}
