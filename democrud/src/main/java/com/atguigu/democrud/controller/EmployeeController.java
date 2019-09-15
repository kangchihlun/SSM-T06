package com.atguigu.democrud.controller;

import com.atguigu.democrud.dao.EmployeeDao;
import com.atguigu.democrud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    // 查詢所有員工，返回列表頁面
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> col = employeeDao.getAll();
        model.addAttribute("emps",col);

        //Map<String,Object> map
        //map.put("emps", col);
        return "emp/list";
    }
}

