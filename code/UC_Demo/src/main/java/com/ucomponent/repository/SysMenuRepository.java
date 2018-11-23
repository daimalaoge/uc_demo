package com.ucomponent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ucomponent.po.SysMenu;
/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统菜单数据库操作
 * Descp:
**/
@Repository
public abstract interface SysMenuRepository extends JpaRepository<SysMenu, Integer>{
  @Query(value = "select DISTINCT menu.* from " +
      "ucm_sys_user_role_rs as ur,ucm_sys_role as role ,ucm_sys_role_menu_rs as rf, ucm_sys_menu as menu " +
      "where fun.id = rf.menu_id "
      + " and role.id = rf.role_id "
      + " and ur.role_id = role.id "
      + " and ur.user_id = ? "
      + " and menu.codeset_gstatus ='G_STATUS_USE' "
      //+ " and menu.codeset_menutype='MENU_TYPE_PAGE' "
      + " and role.codeset_gstatus = 'G_STATUS_USE' "
      + " and role.p_type='A' order by menu.seq", nativeQuery = true)
  public abstract List<SysMenu> getByUserId(int userId);
}
