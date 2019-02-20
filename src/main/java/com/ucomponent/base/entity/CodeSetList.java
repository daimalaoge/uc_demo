package com.ucomponent.base.entity;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import com.ucomponent.po.SysCodeset;
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
  
  private List<SysCodeset> list = new ArrayList<SysCodeset>();
  
  public void setList(List<SysCodeset> list) {
    this.list = list;
  }
  
  public void clearList() {
    this.list = new ArrayList<SysCodeset>();
  }

  public List<SysCodeset> getList(String pCodekey){
    List<SysCodeset> rlist = new ArrayList<SysCodeset>();
    for(SysCodeset uc:list){
      if(uc.getUpperCode().equals(pCodekey)){
        rlist.add(uc);
      }
    }
    return rlist;
  }
  
  public String getName(String codekey){
    List<SysCodeset> rlist = new ArrayList<SysCodeset>();
    for(SysCodeset uc:list){
      if(uc.getCodeKey().equals(codekey)){
        return uc.getName();
      }
    }
    return "";
  }
}

