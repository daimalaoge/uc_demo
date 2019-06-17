package com.ucomponent.manager.sys.repository;

import com.ucomponent.manager.po.MangSysUserRoleRs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 2018年11月20日
 * 代码老哥
 * NAME:
 * Descp:
**/
public abstract interface MangSysUserRoleRsRepository extends JpaRepository<MangSysUserRoleRs, Integer>{
public abstract List<MangSysUserRoleRs> findByUserId(int rid);
	
	@Modifying
  @Transactional
  @Query("delete from MangSysUserRoleRs m where m.userId=?1")
  void deleteUrrsByRole(int userid);
}
