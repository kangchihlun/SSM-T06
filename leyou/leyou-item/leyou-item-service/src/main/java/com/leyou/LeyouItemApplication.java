package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;
import com.leyou.utils.SpringUtil;
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.item.mapper") //掃這個包
public class LeyouItemApplication {
    @Bean
    public SpringUtil getSpingUtil() {
        return new SpringUtil();
    }
    public static void main(String[] args) {

        SpringApplication.run(LeyouItemApplication.class, args);
    }
}
