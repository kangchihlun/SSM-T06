package com.atguigu.mybatisdemo.config;


import com.atguigu.mybatisdemo.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {

    //做一個專門來序列化 employee 的
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
}
