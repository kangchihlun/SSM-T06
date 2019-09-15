package com.atguigu.democrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

// ctrl+f9 重刷Thymeleaf模板引擎
@Controller
public class LoginController {

    //@GetMapping()
    @PostMapping("/user/login")
    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> msgMap,
                        HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password) ) {
            session.setAttribute("loginUser",username);
            // 登錄成功，防止表單重複提交，可以重定向到主頁
            return "redirect:/main";
        }
        else{
            // 登錄失敗
            msgMap.put("msg","用戶密碼錯誤，提示：1234");
            return "login.html";
        }

    }
}
