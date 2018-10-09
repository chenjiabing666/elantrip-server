package com.yilan.elantrip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * springBoot的启动类
 * @author 陈加兵
 *
 */
@EnableTransactionManagement   //开启事务功能，等同于xml配置方式的 <tx:annotation-driven />
@MapperScan(value="com.yilan.elantrip.dao")  //扫描dao
@SpringBootApplication
public class ElantripServerApplication extends SpringBootServletInitializer  {
	
	public static void main(String[] args) {
		SpringApplication.run(ElantripServerApplication.class, args);
	}
	
	//继承SpringBootServletInitializer实现war包的发布
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ElantripServerApplication.class);
    }
}
