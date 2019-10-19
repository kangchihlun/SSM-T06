package com.leyou.item.mapper;


import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Delete;
import tk.mybatis.mapper.common.Mapper;

public interface CategoryMapper extends Mapper<Category> {

    @Delete("DELETE FROM tb_category WHERE id=#{id}")
    void deleteCategoryById(Long id);

}
