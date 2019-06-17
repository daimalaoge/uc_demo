package com.ucomponent;

import com.ucomponent.base.cover.DefaultCover;
import com.ucomponent.base.cover.MyFreemarkerView;
import com.ucomponent.manager.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Map;

/**
 * 2018年9月30日
 * 代码老哥
 * NAME:主程序
 * Descp:运行入口
**/
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class UCDemoApplication {
  @Autowired
  private InitService initService;
  
  public static void main(String[] args) {

    SpringApplication.run(UCDemoApplication.class, args);
  }
  
  @Bean
  public String initSystem(){
    initService.sysInit();
    return "INIT";
  }

}
