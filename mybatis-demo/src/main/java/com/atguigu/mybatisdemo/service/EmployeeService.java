package com.atguigu.mybatisdemo.service;


import com.atguigu.mybatisdemo.bean.Employee;
import com.atguigu.mybatisdemo.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;


/*
*  CacheConfig 整個類下共用同一個cache space，不用到方法內各別指定
*  也可以在此指定 cache manager
*
* */
@Service
@CacheConfig(cacheNames="emp",cacheManager="empCacheManager")
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    // Cacheable 換存調用之前先看指定key有沒有數據
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


    /*
    * 先調用方法，調用完成更新緩存
    * @CachePut 既調用方法，又更新緩存數據
    * 修改數據庫某個數據，同時又更新緩存
    * localhost:8080/emp?id=1&lastName=zhangsan&gender=0
    * ***  注意key值要跟上面update快取用的key一致，才會刷新到 cache 內
    *   #employee.id  或是 result.id
    * */
    @CachePut(value="emp",key="#employee.id")
    public Employee updateEmp(Employee employee){
        System.out.println("員工更新方法調用");
        employeeMapper.updateEmp(employee);

        return employee;
    }


    /*
    *  @CacheEvict 緩存清除
    *   key:指定要清除的數據
    *   allEntries = true 刪除emp底下的全部緩存
    * */
    @CacheEvict(value="emp",key="#id")
    public void deleteEmp(Integer id){
        System.out.println("員工刪除");
        employeeMapper.deleteEmpById(id);
    }


    /*
    *  多個緩存規則合併呼叫
    * */
    @Caching(
            cacheable = {
                @Cacheable(value="emp",key="#lastName")
            },
            put = {
                    // 從更新完的結果更新緩存
                @CachePut(value="emp",key="#result.id"),
                @CachePut(value="emp",key="#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }
}
