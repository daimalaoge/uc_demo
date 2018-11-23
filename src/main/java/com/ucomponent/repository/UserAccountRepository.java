package com.ucomponent.repository;

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
}
