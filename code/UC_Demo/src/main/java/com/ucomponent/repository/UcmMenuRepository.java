package com.ucomponent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucomponent.po.UcmMenu;
/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统菜单数据库操作
 * Descp:
**/
@Repository
public abstract interface UcmMenuRepository extends JpaRepository<UcmMenu, Integer>{

}
