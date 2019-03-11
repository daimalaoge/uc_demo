package com.ucomponent.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ucomponent.po.SysRole;

/**
 * 2018年11月20日
 * 代码老哥
 * NAME:
 * Descp:
**/
public abstract interface SysRoleRepository extends JpaRepository<SysRole, Integer>{
	public abstract Page<SysRole> findByNameContainingAndCodesetGstatusIn(String name,String status,Pageable pageable);
	public abstract List<SysRole> findByCodesetGstatus(String status);
}
