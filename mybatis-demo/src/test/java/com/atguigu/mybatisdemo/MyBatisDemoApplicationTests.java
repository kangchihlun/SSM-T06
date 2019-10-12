package com.atguigu.mybatisdemo;

import com.atguigu.mybatisdemo.bean.Employee;
import com.atguigu.mybatisdemo.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisDemoApplicationTests {

	@Autowired
	EmployeeMapper employeeMapper;

	//專門操作字串
	@Autowired
	StringRedisTemplate stringRedisTemplate;

//	//k-v 操作都是對象
//	@Autowired
//	RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
		Employee emp = employeeMapper.getEmpById(2);
		System.out.println(emp);
	}



	@Test
	public void testRedis(){
		//stringRedisTemplate.opsForValue() //操作 String
		// 					 .opsForHash() //操作 hash
		//					 .opsForList() //操作列表
		//					 .opsForSet() //操作Set
		stringRedisTemplate.opsForValue().append("msg","hello"); // 保存
		String msg = stringRedisTemplate.opsForValue().get("msg");
		System.out.println(msg);

		stringRedisTemplate.opsForList().leftPush("myList","1"); // 頭推入一個值進列表
		stringRedisTemplate.opsForList().leftPush("myList","2"); // 頭推入一個值進列表，目前變成[2,1]
	}


	// 注入一個自訂義的(位於Configure) RedisTemplate -: Employee序列化器
	@Autowired
	RedisTemplate<Object,Employee> empredisTemplate;

	@Test
	public void testEmpSave(){
		Employee emp = employeeMapper.getEmpById(1);
		//不要用預設的
		//stringRedisTemplate.opsForValue().set("emp", emp);
		empredisTemplate.opsForValue().set("emp-01", emp);

		System.out.println(emp);
	}
}
