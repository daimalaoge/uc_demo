package com.ucomponent.manager.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucomponent.base.entity.CodeSetList;
import com.ucomponent.po.SysCodeset;
import com.ucomponent.repository.SysCodesetRepository;

/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统初始化
 * Descp:
**/
@Service
public class InitService {
  @Autowired
  private SysCodesetRepository ucmCodesetRepository;
  
  public void sysInit(){
    System.out.println("+++++++ SYS INIT +++++++");
    
    System.out.println("STEP 1. CodeSet init");
    CodeSetList scmap = CodeSetList.getInstance();
    List<SysCodeset> list = ucmCodesetRepository.findByStatusOrderBySeq(0);
    scmap.setList(list);
  }
}
