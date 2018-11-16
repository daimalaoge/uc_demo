package com.ucomponent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucomponent.po.UcmMenu;

@Repository
public abstract interface UcmMenuRepository extends JpaRepository<UcmMenu, Integer>{

}
