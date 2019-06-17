package com.ucomponent.manager.sys.repository;

import com.ucomponent.manager.po.MangSysRoleMenuRs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 2018年11月20日
 * 代码老哥
 * NAME:
 * Descp:
**/
@Repository
public abstract interface  MangSysRoleMenuRsRepository extends JpaRepository<MangSysRoleMenuRs, Integer>{
	public abstract List<MangSysRoleMenuRs> findByRoleId(int rid);
	
	@Modifying
  @Transactional
  @Query("delete from MangSysRoleMenuRs m where m.roleId=?1")
  void deleteRmrsByRole(int roleid);
}
