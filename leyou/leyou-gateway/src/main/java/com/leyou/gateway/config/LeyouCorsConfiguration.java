package com.leyou.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class LeyouCorsConfiguration {

    @Bean
    public CorsFilter corsFilter(){

        // 初始化 cors 配置對象
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 允許跨域的域名，如果要攜帶Cookie，不能寫成 * , * 代表所有域名都可跨域訪問
        corsConfiguration.addAllowedOrigin("http://manage.leyou.com");

        //允許設置Cookie
        corsConfiguration.setAllowCredentials(true);

        // 代表所有的請求方法
        corsConfiguration.addAllowedMethod("*");

        // 允許攜帶任何頭信息
        corsConfiguration.addAllowedHeader("*");

        // 初始化 cors 配置源對象
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",corsConfiguration);

        //返回 CorsFilter 實例，參數為 cors配置源對象
        return new CorsFilter(configurationSource);
    }
}
