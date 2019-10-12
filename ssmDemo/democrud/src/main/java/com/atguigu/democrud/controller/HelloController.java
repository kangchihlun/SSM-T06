package com.atguigu.democrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {


    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        // classpath:/templates/success.html
        // 丟到模板裡面，變數名 "hello"，值 = "你好"
        // 假設查出一些數據，在頁面展示
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","wanwu"));
        return "success";

        // ${hello} 在thy頁面獲取這裡傳入的變數
        //    OGL 獲取對象的屬性
        // *{} 變量選擇表達式，和 ${} 功能上一樣 配合 th:object 進行使用簡化 ${object.session.person}
        // #{} 獲取國際化內容
        // @{} 定義 URL 連結 主機名同域可以省略 @{/order/process(execId=${execId},`+)}
        // ~{} fragment expression
    }
}
