package com.ucomponent.biz.demo.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucomponent.biz.po.Demo;

/**
 * <p>Title: 测试增删改样例 Repository</p>
 * <p>Description: </p>
 * <p>Copyright: Union Component</p>
 * @date:2019-06-17 11:54:23
 * @author:联合组件
 * @version:1.0
 **/ 
@Repository
public abstract interface DemoRepository extends JpaRepository<Demo, Integer>{

	public abstract Page<Demo> findByOrgIdAndMingchengContainingAndWeiyidaimaContainingAndCodesetGstatusIn(int orgid, String bizCodesetMingcheng, String bizCodesetWeiyidaima,String status,Pageable pageable);
	
	//查重查询
	public abstract Demo findByOrgIdAndWeiyidaimaAndCodesetGstatus(int orgid,String bizCodesetWeiyidaima,String status);

}
