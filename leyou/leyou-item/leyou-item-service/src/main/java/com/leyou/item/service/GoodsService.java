package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.*;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.pojo.Stock;
import com.leyou.item.pojo.extend.SpuBo;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.mozilla.SignedPublicKeyAndChallenge;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

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


    // 要新增4張表
    @Transactional  // 串行事務，全部完成才完成
    public void saveGoods(SpuBo spuBo) {
        // 1. 先新增 spu 表
        // 設置前端沒傳的spu參數
        spuBo.setId(null); // 防止被注入假資料
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(spuBo.getCreateTime());

        spuMapper.insertSelective(spuBo);

        // 再新增 spu_detail by spuid
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
        spuDetailMapper.insertSelective(spuDetail);

        // 新增 sku by spuid
        spuBo.getSkus().forEach(sku->{
            sku.setId(null);
            sku.setSpuId(spuBo.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            skuMapper.insertSelective(sku);

            // 新增 stock by sku id
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock()); // 剩餘庫存
            stockMapper.insertSelective(stock);
        });
    }
}
