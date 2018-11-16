package com.ucomponent.manager.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucomponent.base.entity.CodeSetList;
import com.ucomponent.po.UcmCodeset;
import com.ucomponent.repository.UcmCodesetRepository;


@Service
public class InitService {
  @Autowired
  private UcmCodesetRepository ucmCodesetRepository;
  
  public void sysInit(){
    System.out.println("+++++++ SYS INIT +++++++");
    
    System.out.println("STEP 1. CodeSet init");
    CodeSetList scmap = CodeSetList.getInstance();
    List<UcmCodeset> list = ucmCodesetRepository.findByStatusOrderBySeq(0);
    scmap.setList(list);
  }
}
