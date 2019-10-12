package com.atguigu.mybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


// 掃描以下階層的 mapper 包，mapper 目錄以後就不用寫 @Mapper
@MapperScan(value = "com.atguigu.mybatisdemo.mapper")
@SpringBootApplication
@EnableCaching
public class MyBatisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBatisDemoApplication.class, args);
	}

}
