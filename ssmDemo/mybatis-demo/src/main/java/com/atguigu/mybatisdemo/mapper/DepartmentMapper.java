package com.atguigu.mybatisdemo.mapper;

import com.atguigu.mybatisdemo.bean.Department;
import org.apache.ibatis.annotations.*;

// 操作數據庫的 Mapper
@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id") // 自增id / 主鍵為 id
    @Insert("insert into department(departmentName) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id = #{id}")
    public int updateDept(Department department);
}
