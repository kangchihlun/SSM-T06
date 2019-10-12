package com.atguigu.rabbitmqdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.backoff.ObjectWaitSleeper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqDemoApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;


	/*
	* 單播點對點
	* */
	@Test
	public void contextLoads() {
		// message 要自己構造一個
		// rabbitTemplate.send(exchange,route,message)

		// object默認被當成消息體 會被被序列化，自動發送給rabbitmq
		// rabbitTemplate.convertAndSend(exchange,routeKey, object);
		Map<String,Object> map = new HashMap<>();
		map.put("msg","firstMessage");
		map.put("data", Arrays.asList("helloworld","123",true) );
		rabbitTemplate.convertAndSend("exchange.direct","atguigu.news", map);

	}

}
