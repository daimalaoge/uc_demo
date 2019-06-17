package com.ucomponent.manager.sys.repository;

import com.ucomponent.manager.po.MangSysOrg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 2019/5/10
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
@Repository
public abstract interface MangSysOrgRepository  extends JpaRepository<MangSysOrg, Integer> {
}
