package com.atguigu.mybatisdemo.service;


import com.atguigu.mybatisdemo.bean.Employee;
import com.atguigu.mybatisdemo.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;


    // cacheManager 管理多個cache組件，對緩存的真正crud操作在 cache 組件中
    // 每個緩存組件有一個自己的唯一名字
    // cacheNames 指定緩存組件的名字
    // key 用 "#id" 意義等於 #root.args[0]、#a0、#p0 參數0
    // key generator 可以自己指定key生成器的組件id(跟key二選一，指定generator就不能用默認)
    // cacheManager 指定緩存管理器 cacheResolver 緩存解析器
    // condition 判斷條件符合才緩存
    // unless 當上面的條件為 true
    // sync 是否使用異步
    @Cacheable(cacheNames="emp",key="#id",condition="#id>=0")
    public Employee getEmp(Integer id){
        //return employeeMapper.getEmployeeById(id);
        // 此getEmployeeById乃interface自動生成，如果在interface再定義一次會報錯
        System.out.println("查詢{id}號員工");
        return employeeMapper.getEmpById(id);
    }
}
