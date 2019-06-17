package com.ucomponent.manager.user.repository;

import com.ucomponent.manager.po.MangUserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 2018年11月20日
 * 代码老哥
 * NAME:系统账号数据库操作
 * Descp:
**/
public abstract interface MangUserAccountRepository extends JpaRepository<MangUserAccount, Integer>{
  
  public abstract MangUserAccount findByLoginNameOrLoginEmailOrLoginPhoneOrLoginOtheridAndCodesetGstatus(String lname
				  , String lemail, String lphone, String loid, String status);

  public abstract Page<MangUserAccount> findByIdAndLoginNameContainingAndLoginEmailContainingAndLoginPhoneContainingAndCodesetGstatusIn(
				  int id, String name, String loginEmail, String loginPhone, String status, Pageable pageable);

  public abstract Page<MangUserAccount> findByLoginNameContainingAndLoginEmailContainingAndLoginPhoneContainingAndCodesetGstatusIn(
				  String name, String loginEmail, String loginPhone, String status, Pageable pageable);

	public abstract Page<MangUserAccount> findByOrgIdAndLoginNameContainingAndLoginEmailContainingAndLoginPhoneContainingAndCodesetGstatusIn(
					int orgid,String name, String loginEmail, String loginPhone, String status, Pageable pageable);

	public abstract Page<MangUserAccount> findByOrgIdAndIdAndLoginNameContainingAndLoginEmailContainingAndLoginPhoneContainingAndCodesetGstatusIn(
					int orgid,int id, String name, String loginEmail, String loginPhone, String status, Pageable pageable);

  public abstract MangUserAccount findByLoginNameAndCodesetGstatus(String name, String status);

  public abstract MangUserAccount findByLoginEmailAndCodesetGstatus(String email, String status);

  public abstract MangUserAccount findByLoginPhoneAndCodesetGstatus(String loginPhone, String status);
}
