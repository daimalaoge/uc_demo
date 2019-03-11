package com.ucomponent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ucomponent.po.SysRoleMenuRs;
import com.ucomponent.po.UserRoleRs;

/**
 * 2018年11月20日
 * 代码老哥
 * NAME:
 * Descp:
**/
public abstract interface UserRoleRsRepository extends JpaRepository<UserRoleRs, Integer>{
public abstract List<UserRoleRs> findByUserId(int rid);
	
	@Modifying
  @Transactional
  @Query("delete from UserRoleRs m where m.userId=?1")
  void deleteUrrsByRole(int roleid);
}
