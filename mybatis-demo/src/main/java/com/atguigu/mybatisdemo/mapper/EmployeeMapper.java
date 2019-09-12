package com.atguigu.mybatisdemo.mapper;


import com.atguigu.mybatisdemo.bean.Employee;

public interface EmployeeMapper {

    public Employee getEmployeeById(Integer id);

    public void insertEmp(Employee employee);
}
