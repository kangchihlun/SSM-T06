package com.atguigu.springboot06.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.*;
import java.util.logging.Filter;

// 因為配置 application.yml 對應不上，自己寫一個config注入

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix="spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    /// 配置 druid 監控
    // 1. 配置一個管理後台的 Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

        // 配置 Servlet 參數，使用map
        Map<String,String> initParms = new HashMap<>();
        initParms.put("loginUsername","admin");
        initParms.put("loginPassword","123456");
        initParms.put("allow","localhost"); //  不寫就是允許所有訪問" "
        initParms.put("deny","192.168.15.21");

        bean.setInitParameters(initParms);
        return bean;
    }

    // 2. 配置一個web 監控 filter
    @Bean
    public FilterRegistrationBean webStateFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParms = new HashMap<>();
        initParms.put("exclusion","*.js,*.css,/druid/*");
        bean.setInitParameters(initParms);
        bean.setUrlPatterns(Arrays.asList("/*")); // 攔截所有請求

        return bean;
    }
}
