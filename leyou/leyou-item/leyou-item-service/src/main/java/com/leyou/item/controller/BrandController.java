package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;



    // 分頁查詢品牌信息並且排序
    // 要求 URL: http://api.leyou.com/api/item/brand/page?key=&page=1&rows=5&sortBy=id&desc=false
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "desc",required = false) Boolean desc
    )
    {
        PageResult<Brand> result = this.brandService.queryBrandsByPage(key,page,rows,sortBy,desc);

        if( (result==null) || CollectionUtils.isEmpty(result.getItems()) ) {
            // 404資源未找到
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.notFound().build();
        }

        // 200查詢成功
        return ResponseEntity.ok(result);

    }



    // 新增品牌
    // POST : http://api.leyou.com/api/item/brand
    // 本來傳的 如果第一個參數是一個json對象 只能使用一個 class 去接收，不能再接收後續的 param，
    // saveBrand(@RequestBody Brand brand) // 整體接收，對於中間字段有自訂的沒法
    // 解法：前端傳送字段改代碼
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand,@RequestParam(value = "cids") List<Long> cids)
    {
        this.brandService.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandsByCid(@PathVariable(value = "cid") Long cid){
        List<Brand> brands = this.brandService.queryBrandsByCid(cid);
        if( CollectionUtils.isEmpty(brands) ) {
            return ResponseEntity.notFound().build();
        }
        // 200查詢成功
        return ResponseEntity.ok(brands);
    }

}
