package com.atguigu.democrud.config;

import com.atguigu.democrud.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@EnableWebMvc // 開啟之後會全面接管 SpringMVC，靜態資源路徑需要自己配
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    // 試圖映射，所有訪問 /atguigu 下的全部被導到 success 頁面
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/atguigu").setViewName("success");
    }

    // 所有的 WebMvcConfigurerAdapter組件都會一起起作用
    @Bean // 把組件註冊到容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
            }
        };
        return adapter;
    }

    @Bean
    public LocaleResolver localResolver(){
        return new MyLocaleResolver();
    }
}
