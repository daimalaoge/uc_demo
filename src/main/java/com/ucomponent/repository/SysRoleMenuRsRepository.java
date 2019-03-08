package com.ucomponent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ucomponent.po.SysRoleMenuRs;
/**
 * 2018年11月20日
 * 代码老哥
 * NAME:
 * Descp:
**/
@Repository
public abstract interface  SysRoleMenuRsRepository extends JpaRepository<SysRoleMenuRs, Integer>{
	public abstract List<SysRoleMenuRs> findByRoleId(int rid);
	
	@Modifying
  @Transactional
  @Query("delete from SysRoleMenuRs m where m.roleId=?1")
  void deleteRmrsByRole(int roleid);
}
