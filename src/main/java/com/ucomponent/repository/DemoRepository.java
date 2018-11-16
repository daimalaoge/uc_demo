package com.ucomponent.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucomponent.po.Demo;

/**
 * 2018年10月22日
 * DemoRepository.java
 * 代码老哥
 * NAME:示例类数据库操作
 * Descp:
**/
@Repository
public abstract interface DemoRepository extends JpaRepository<Demo, Integer>{
	public abstract Page<Demo> findByNameContainingAndCodesetGstatusIn(String name,String status,Pageable pageable);

}
