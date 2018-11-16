package com.ucomponent;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.ucomponent.base.cover.DefaultCover;
import com.ucomponent.base.cover.MyFreemarkerView;
import com.ucomponent.manager.service.InitService;
import io.undertow.UndertowOptions;

/**
 * 2018年9月30日
 * 代码老哥
 * NAME:主程序
 * Descp:运行入口
**/
@SpringBootApplication
@EnableCaching
@ServletComponentScan
@ComponentScan(basePackages={"com.ucomponent"})
public class UCManagerApplication {
  @Autowired
  private InitService initService;
  
  public static void main(String[] args) {
    SpringApplication.run(UCManagerApplication.class, args);
  }
  
  @Bean
  public String initSystem(){
    initService.sysInit();
    return "INIT";
  }
  
  @Bean
  public CommandLineRunner customFreemarker(FreeMarkerViewResolver resolver) {
    return new CommandLineRunner() {
      @Override
      public void run(String... strings) throws Exception {
        System.out.println("+++++ ADD DEF Freemarker View +++++");
        //增加视图  
        resolver.setViewClass(MyFreemarkerView.class);  
        //添加自定义解析器        
        Map map = resolver.getAttributesMap();
        map.put("conver", new DefaultCover());        
      }
    };  
  }
//  /**
//   * 增加系统对undertow的支持
//   * @return
//   */
//  @Bean
//  public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
//    UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
//    // 这里也可以做其他配置
//    factory.addBuilderCustomizers(builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
//    return factory;
//  }
}
