package com.ucomponent.manager.sys.repository;

import com.ucomponent.manager.po.MangSysOperationLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统操作日志数据库操作
 * Descp:
**/
@Repository
public abstract interface MangSysOperationLogRepository extends JpaRepository<MangSysOperationLog, Integer>{
	public abstract Page<MangSysOperationLog> findByUserIdContainingAndTitleContainingAndCreateDatetimeBetween(String userid, String title, Date datestart, Date dateend, Pageable pageable);
}
