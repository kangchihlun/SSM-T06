package com.leyou.item.controller;


import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    SpecificationService specificationService;

    /*
    * 根據分類id查詢參數組
    * @param cid
    * @return
    * */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid") Long cid){
        List<SpecGroup> specGroups = this.specificationService.queryGroupByCid(cid);
        if(CollectionUtils.isEmpty(specGroups)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(specGroups);
    }


    /*
    * 根據gid 查詢規格參數
    * @param cid
    * @return
    * */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(@RequestParam("gid") Long gid){
        List<SpecParam> specParams = this.specificationService.queryParams(gid);
        if(CollectionUtils.isEmpty(specParams)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(specParams);
    }
}


