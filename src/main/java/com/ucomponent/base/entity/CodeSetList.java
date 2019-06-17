package com.ucomponent.base.entity;

import com.ucomponent.manager.po.MangSysCodeset;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统字典单例模式
 * Descp:
**/
@Scope("prototype")
public class CodeSetList {
  private static final CodeSetList instance = new CodeSetList();
 
  private CodeSetList(){}
 
  public static CodeSetList getInstance(){
    return instance;
  }

  //private Map<String,String> map = new HashMap<String,String>();
  
  private List<MangSysCodeset> list = new ArrayList<MangSysCodeset>();
  
  public void setList(List<MangSysCodeset> list) {
    this.list = list;
  }
  
  public void clearList() {
    this.list = new ArrayList<MangSysCodeset>();
  }

  public List<MangSysCodeset> getList(String pCodekey){
    List<MangSysCodeset> rlist = new ArrayList<MangSysCodeset>();
    for(MangSysCodeset uc:list){
      if(uc.getUpperCode().equals(pCodekey)){
        rlist.add(uc);
      }
    }
    return rlist;
  }

  public MangSysCodeset getByCode(String codekey){
    for(MangSysCodeset uc:list){
      if(uc.getCodeKey().equals(codekey)){
        return uc;
      }
    }
    return null;
  }
  
  public String getName(String codekey){
    for(MangSysCodeset uc:list){
      if(uc.getCodeKey().equals(codekey)){
        return uc.getName();
      }
    }
    return "";
  }
}

