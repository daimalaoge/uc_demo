package com.ucomponent.manager.service;

import com.ucomponent.base.entity.BizCodeSetList;
import com.ucomponent.base.entity.CodeSetList;

import com.ucomponent.manager.platf.repository.MangBizCodesetRepository;
import com.ucomponent.manager.sys.repository.MangSysCodesetRepository;
import com.ucomponent.manager.po.MangBizCodeset;
import com.ucomponent.manager.po.MangSysCodeset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统初始化
 * Descp:
**/
@Service
public class InitService {
  @Autowired
  private MangSysCodesetRepository ucmCodesetRepository;
  @Autowired
  private MangBizCodesetRepository bizCodesetRepository;
  
  public void sysInit(){
    System.out.println("+++++++ SYS CODE INIT +++++++");
    System.out.println("STEP 2. CodeSet init");
    CodeSetList scmap = CodeSetList.getInstance();
    List<MangSysCodeset> list = ucmCodesetRepository.findByCodesetGstatusOrderBySeq("G_STATUS_USE");
    scmap.setList(list);
  }
}
