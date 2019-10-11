package com.atguigu.mybatisdemo.mapper;


import com.atguigu.mybatisdemo.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {


    @Select("select * from employee where id=#{id}")
    Employee getEmpById(Integer id);

    @Update("UPDATE employee Set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id=#{id}")
    void updateEmp(Employee employee);

    @Delete("DELETE FROM employee WHERE id=#{id}")
    void deleteEmpById(Integer id);

    @Insert("INSERT INTO employee(lastName,email,gender,dId,i) VALUES(#{lastName},#{email},#{gender},#{dId})")
    void insertEmployee(Employee employee);

    @Select("select * from employee where lastName=#{lastName} ")
    Employee getEmpByLastName(String lastName);
}
