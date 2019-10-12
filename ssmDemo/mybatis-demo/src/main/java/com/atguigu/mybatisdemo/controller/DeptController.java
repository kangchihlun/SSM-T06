package com.atguigu.mybatisdemo.controller;

import com.atguigu.mybatisdemo.bean.Department;
import com.atguigu.mybatisdemo.bean.Employee;
import com.atguigu.mybatisdemo.mapper.DepartmentMapper;
import com.atguigu.mybatisdemo.mapper.EmployeeMapper;
import com.atguigu.mybatisdemo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    DeptService deptService;


    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return deptService.getDepartmentById(id);
    }

    //  http://localhost:8080/dept?departmentName=人事部
    @GetMapping("/dept")
    public Department insertDept(Department dept){
        deptService.insertDept(dept);
        return dept;
    }


}
