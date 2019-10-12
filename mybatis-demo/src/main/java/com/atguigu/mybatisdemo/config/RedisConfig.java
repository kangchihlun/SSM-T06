package com.atguigu.mybatisdemo.config;


import com.atguigu.mybatisdemo.bean.Department;
import com.atguigu.mybatisdemo.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {

    // 做一個專門來序列化 employee 的Redis Template
    @Bean
    public RedisTemplate<Object,Employee> empredisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException{
        RedisTemplate<Object,Employee> template = new RedisTemplate<Object,Employee>();
        template.setConnectionFactory(redisConnectionFactory);
        // Jackson 序列化器
        Jackson2JsonRedisSerializer<Employee> employeeJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Employee.class);
        template.setDefaultSerializer(employeeJackson2JsonRedisSerializer);
        return template;
    }


    // 自定義緩存管理器
    @Bean
    public RedisCacheManager empCacheManager(RedisTemplate<Object,Employee> empredisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(empredisTemplate);

        // 多一個前墜，會將cacheName 作為key前墜
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }



    // dept 的Redis Template 也要做一遍
    @Bean
    public RedisTemplate<Object,Department> deptRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException{
        RedisTemplate<Object,Department> template = new RedisTemplate<Object,Department>();
        template.setConnectionFactory(redisConnectionFactory);
        // Jackson 序列化器
        Jackson2JsonRedisSerializer<Department> deptJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Department.class);
        template.setDefaultSerializer(deptJackson2JsonRedisSerializer);
        return template;
    }

    // 自定義緩存管理器，注意一定要有一個當作 Primary Cache Manager 否則會報錯
    @Primary
    @Bean
    public RedisCacheManager deptCacheManager(RedisTemplate<Object,Department> deptRedisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(deptRedisTemplate);

        // 多一個前墜，會將cacheName 作為key前墜
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
}
