package com.ucomponent.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucomponent.po.SysCodeset;
/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统数据字典数据库操作
 * Descp:
**/
@Repository
public abstract interface SysCodesetRepository  extends JpaRepository<SysCodeset, Integer>{
  
  public abstract List<SysCodeset> findByStatusOrderBySeq(int use);
  
  public abstract List<SysCodeset> findByStatusOrStatusOrderBySeq(int use,int nouse);
  
  public abstract List<SysCodeset> findByPCodeAndStatusOrderBySeq(String pcode,int status);
}
