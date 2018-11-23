package com.ucomponent.manager.controller.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

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
