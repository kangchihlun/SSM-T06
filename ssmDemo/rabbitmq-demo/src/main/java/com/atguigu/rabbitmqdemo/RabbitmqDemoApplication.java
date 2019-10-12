package com.atguigu.rabbitmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
*  RabbitAutoConfiguration
*  ConnectionFactory
*  RebbitProperties 封裝了 RabbitMQ配置
*  RabbitTemplate 給 RabbitMQ 發送和接收消息
*  AmqpAdmin 系統管理功能組件
* */
@SpringBootApplication
public class RabbitmqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqDemoApplication.class, args);
	}

}
