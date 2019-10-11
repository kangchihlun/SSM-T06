package com.atguigu.mybatisdemo;

import com.atguigu.mybatisdemo.bean.Employee;
import com.atguigu.mybatisdemo.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisDemoApplicationTests {

	@Autowired
	EmployeeMapper employeeMapper;


	@Test
	public void contextLoads() {
		Employee emp = employeeMapper.getEmpById(2);
		System.out.println(emp);
	}
}
