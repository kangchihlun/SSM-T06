package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.extend.SpuBo;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    BrandMapper brandMapper;

    // 只要需要，可以大膽地注入
    @Autowired
    CategoryService categoryService;

    public PageResult<SpuBo> quertSpuByPage(String key, Boolean saleable, Integer page, Integer rows) {

        //模糊查詢
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();

        // 添加查詢條件
        this.spuMapper.selectByExample(example);
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("title","%"+key+"%");
        }

        // 添加Saleable上下架過濾條件
        if(saleable != null){
            //mysql 沒有Boolean
            criteria.andEqualTo("saleable",saleable);
        }

        // 添加分頁
        PageHelper.startPage(page,rows);

        // 執行查詢，獲取 Spu 集合
        List<Spu> spus = this.spuMapper.selectByExample(example);

        // 包装成pageInfo
        PageInfo<Spu> pageInfo = new PageInfo<>(spus);


        // 轉化成 SpuBo 集合
        List<SpuBo> spuboList = spus.stream().map(spu->{

            SpuBo spuBo = new SpuBo();

            // 快速對拷屬性
            BeanUtils.copyProperties(spu,spuBo);

            // 查詢品牌名稱
            Brand brand = this.brandMapper.selectByPrimaryKey(spu.getBrandId());
            spuBo.setBname(brand.getName());

            // 查詢分類名稱，多個分類都要查
            List<String> namecols = this.categoryService.queryNamesByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBo.setCname(StringUtils.join(namecols,"-"));


            return spuBo;
        }).collect(Collectors.toList());

        // 返回 PageResult<SpuBo>
        return new PageResult<>(pageInfo.getTotal(),spuboList);
    }


}
