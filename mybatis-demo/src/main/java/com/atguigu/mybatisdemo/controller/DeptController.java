package com.atguigu.mybatisdemo.controller;

import com.atguigu.mybatisdemo.bean.Department;
import com.atguigu.mybatisdemo.bean.Employee;
import com.atguigu.mybatisdemo.mapper.DepartmentMapper;
import com.atguigu.mybatisdemo.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDeptById(id);
    }

    //  http://localhost:8080/dept?departmentName=人事部
    @GetMapping("/dept")
    public Department insertDept(Department dept){
        departmentMapper.insertDept(dept);
        return dept;
    }

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeMapper.getEmployeeById(id);
    }
}
