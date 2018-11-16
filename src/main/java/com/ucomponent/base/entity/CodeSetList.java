package com.ucomponent.base.entity;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import com.ucomponent.po.UcmCodeset;
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
  
  private List<UcmCodeset> list = new ArrayList<UcmCodeset>();
  
  public void setList(List<UcmCodeset> list) {
    this.list = list;
  }
  
  public void clearList() {
    this.list = new ArrayList<UcmCodeset>();
  }

  public List<UcmCodeset> getList(String pCodekey){
    List<UcmCodeset> rlist = new ArrayList<UcmCodeset>();
    for(UcmCodeset uc:list){
      if(uc.getPCode().equals(pCodekey)){
        rlist.add(uc);
      }
    }
    return rlist;
  }
  
  public String getName(String codekey){
    List<UcmCodeset> rlist = new ArrayList<UcmCodeset>();
    for(UcmCodeset uc:list){
      if(uc.getCodeKey().equals(codekey)){
        return uc.getName();
      }
    }
    return "";
  }
}

