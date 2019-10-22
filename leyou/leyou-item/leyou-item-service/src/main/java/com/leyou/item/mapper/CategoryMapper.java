package com.leyou.item.mapper;


import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Delete;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/*
 *  SelectByIdListMapper<Category,Long> Long主鍵的類型
* */

public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category,Long> {

    @Delete("DELETE FROM tb_category WHERE id=#{id}")
    void deleteCategoryById(Long id);

}
