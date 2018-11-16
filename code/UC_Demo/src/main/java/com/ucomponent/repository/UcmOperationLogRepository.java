package com.ucomponent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucomponent.po.UcmOperationLog;

@Repository
public abstract interface UcmOperationLogRepository extends JpaRepository<UcmOperationLog, Integer>{

}
