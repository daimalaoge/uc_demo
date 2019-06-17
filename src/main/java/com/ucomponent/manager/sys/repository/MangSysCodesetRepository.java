package com.ucomponent.manager.sys.repository;

import com.ucomponent.manager.po.MangSysCodeset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统数据字典数据库操作
 * Descp:
**/
@Repository
public abstract interface MangSysCodesetRepository  extends JpaRepository<MangSysCodeset, Integer>{
  
  public abstract List<MangSysCodeset> findByCodesetGstatusOrderBySeq(String use);

  public abstract List<MangSysCodeset> findByOrderBySeq();
  
  public abstract List<MangSysCodeset> findByCodesetGstatusOrCodesetGstatusOrderBySeq(String use, String nouse);

  public abstract List<MangSysCodeset> findByUpperCodeAndCodesetGstatusOrderBySeq(String pcode, String status);

  public abstract Page<MangSysCodeset> findByNameContainingAndCodeKeyContainingAndUpperCodeContainingAndCodesetGstatusIn(String name, String codekey, String upperCode, String status, Pageable pageable);
}
