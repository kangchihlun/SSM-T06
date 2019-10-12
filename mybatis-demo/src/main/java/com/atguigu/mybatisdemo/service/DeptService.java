package com.atguigu.mybatisdemo.service;

import com.atguigu.mybatisdemo.bean.Department;
import com.atguigu.mybatisdemo.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames="dept",cacheManager="deptCacheManager")
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;


    /*
    * 緩存的數據能存入redis
    * 但第二次無法返序列化成物件，會出現 Unrecognized field "departmentName"
    * cacheManager 默認用 empredisTemplate
    * */
    @Cacheable
    public Department getDepartmentById(Integer id) {
        System.out.println("SQL查詢部門員工"+id.toString());
        return departmentMapper.getDeptById(id);
    }

    public void insertDept(Department dept) {
        departmentMapper.insertDept(dept);
    }
}
