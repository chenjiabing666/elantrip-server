package com.techwells.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement   //开启事务功能，等同于xml配置方式的 <tx:annotation-driven />
@MapperScan(value="com.techwells.demo.dao")  //扫描dao
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer  {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	//继承SpringBootServletInitializer实现war包的发布
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }
}
