package com.leyou.item.controller;


import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(value = "pid",defaultValue = "0") Long pid){

        if ((pid == null) || (pid < 0)){
            // 400 參數不合法
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 三個月工程師寫法，why?
            //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().build();
        }

        List<Category> categories = this.categoryService.queryCategoriesById(pid);
        if (CollectionUtils.isEmpty(categories)) {
            // 404資源未找到
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.notFound().build();
        }

        // 200查詢成功
        return ResponseEntity.ok(categories);

    }

    @DeleteMapping("list")
    public ResponseEntity<String> deleteCategoriesByPid(@RequestParam(value = "pid",defaultValue = "0") Long pid){

        if ((pid == null) || (pid < 0)){
            // 400 參數不合法
            return ResponseEntity.badRequest().build();
        }
        this.categoryService.deleteCategoriesById(pid);

        // 200刪除成功
        return ResponseEntity.ok("success");

    }


    // 返回圖片作法
    @GetMapping("img")
    public void imgRst(HttpServletResponse response)throws IOException{
        response.setContentType("image/png");
        ServletOutputStream output = response.getOutputStream();
        String path = "https://spring.hhui.top/spring-blog/imgs/info/info.png";
        URL uri = new URL(path);
        BufferedImage img = ImageIO.read(uri);
        ImageIO.write(img,"png",response.getOutputStream());

    }
}
