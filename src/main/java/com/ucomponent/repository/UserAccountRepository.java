package com.ucomponent.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ucomponent.po.UserAccount;
/**
 * 2018年11月20日
 * 代码老哥
 * NAME:系统账号数据库操作
 * Descp:
**/
public abstract interface UserAccountRepository extends JpaRepository<UserAccount, Integer>{
  
  public abstract UserAccount findByLoginNameOrLoginEmailOrLoginPhoneOrLoginOtheridAndCodesetGstatus(String lname
      ,String lemail,String lphone,String loid,String status);
  
  public abstract Page<UserAccount> findByIdAndLoginNameContainingAndLoginEmailContainingAndLoginPhoneContainingAndCodesetGstatusIn(
  		int id,String name,String loginEmail,String loginPhone,String status,Pageable pageable);
  
  public abstract Page<UserAccount> findByLoginNameContainingAndLoginEmailContainingAndLoginPhoneContainingAndCodesetGstatusIn(
  		String name,String loginEmail,String loginPhone,String status,Pageable pageable);
  
  public abstract UserAccount findByLoginNameAndCodesetGstatus(String name,String status);
  
  public abstract UserAccount findByLoginEmailAndCodesetGstatus(String email,String status);
  
  public abstract UserAccount findByLoginPhoneAndCodesetGstatus(String loginPhone,String status);
}
