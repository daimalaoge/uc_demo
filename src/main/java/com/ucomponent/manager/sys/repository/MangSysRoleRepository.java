package com.ucomponent.manager.sys.repository;

import com.ucomponent.manager.po.MangSysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 2018年11月20日
 * 代码老哥
 * NAME:
 * Descp:
**/
public abstract interface MangSysRoleRepository extends JpaRepository<MangSysRole, Integer>{
	public abstract Page<MangSysRole> findByNameContainingAndCodesetGstatusIn(String name, String status, Pageable pageable);
	public abstract List<MangSysRole> findByCodesetGstatus(String status);
}
