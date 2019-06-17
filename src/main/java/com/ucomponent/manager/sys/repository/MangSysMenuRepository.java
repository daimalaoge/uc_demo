package com.ucomponent.manager.sys.repository;

import com.ucomponent.manager.po.MangSysMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统菜单数据库操作
 * Descp:
**/
@Repository
public abstract interface MangSysMenuRepository extends JpaRepository<MangSysMenu, Integer>{
  @Query(value = "select DISTINCT menu.* from " +
      "ucm_mang_sys_user_role_rs as ur,ucm_mang_sys_role as role ,ucm_mang_sys_role_menu_rs as rf, ucm_mang_sys_menu as menu " +
      "where "
      + " role.id = rf.role_id "
      + " and ur.role_id = role.id "
      + " and ur.user_id = ? "
      + " and menu.id = rf.menu_id  "
      + " and menu.codeset_gstatus ='G_STATUS_USE' "
      //+ " and menu.codeset_menutype='MENU_TYPE_PAGE' "
      + " and role.codeset_gstatus = 'G_STATUS_USE' "
      + " order by menu.seq,menu.id,menu.levels", nativeQuery = true)
  public abstract List<MangSysMenu> getByUserId(int userId);
  
  public abstract Page<MangSysMenu> findByNameContainingAndLevelsAndCodesetGstatusIn(String name, int levels, String status, Pageable pageable);
  public abstract Page<MangSysMenu> findByNameContainingAndCodesetGstatusIn(String name, String status, Pageable pageable);

  public abstract Page<MangSysMenu> findByUpperIdAndCodesetGstatusIn(int pid, String status, Pageable pageable);
  
  public abstract List<MangSysMenu> findByCodesetGstatusOrderByLevelsAscSeqAsc(String status);

  public abstract List<MangSysMenu> findByCodesetGstatusInOrderBySeqAsc(String status[]);
}
