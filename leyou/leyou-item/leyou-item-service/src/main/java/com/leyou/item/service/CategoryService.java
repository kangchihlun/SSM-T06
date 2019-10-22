package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    /*
    *
    * @param pid
    * */
    public List<Category> queryCategoriesById(Long pid) {

        Category record = new Category();
        record.setParentId(pid);
        return categoryMapper.select(record);
    }

    public int deleteCategoriesById(Long pid) {
        categoryMapper.deleteCategoryById(pid);
        return 0;
    }


    public List<String> queryNamesByIds(List<Long> ids){
        List<Category> categories = this.categoryMapper.selectByIdList(ids);
        return categories.stream().map(cat->cat.getName()).collect(Collectors.toList());
    }
}
