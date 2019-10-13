package com.live.livedemo.controller;

import com.live.livedemo.dao.StatDao;
import com.live.livedemo.dao.UserDao;
import com.live.livedemo.entity.UserEntity;
import com.live.livedemo.service.IpUtil;
import com.live.livedemo.service.NameGenerator;
import com.live.livedemo.service.UserAgentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RestController
public class Demo {

    @Autowired
    private UserDao userDao;
    @Autowired
    private StatDao statDao;

    @GetMapping("/live_room")
    public String hello(HttpServletRequest request, Model model) {

        String ip = IpUtil.getIp(request);
        Random random = new Random(20);
        HttpSession session = request.getSession();
        UserEntity user;

        if (userDao.findOne(ip) != null){
            //用户曾经访问过
            System.out.println("用户曾经访问过");
            user = userDao.findOne(ip);
        }else {
            //用户未访问过，存储用户信息
            System.out.println("用户未访问过");
            user = new UserEntity();
            user.setIp(ip);
            user.setRandomName(NameGenerator.generate());
            //System.out.println("ip="+ip+"name="+user.getRandomName());
            userDao.save(user);
        }

        //System.out.println("ip="+ip+"name="+user.getRandomName());
        session.setAttribute("user",user);
        //判断用户是手机还是电脑端
        if (UserAgentUtil.JudgeIsMoblie(request)){
            //移动端访问
            return "live_m";
        }else {
            model.addAttribute("online_guests",getOnlineUser());
            model.addAttribute("history_guests",getHistoryGuests());
            return "live";
        }
    }

    public Set getOnlineUser(){
        return  statDao.getAllUserOnline();
    }

    public List getHistoryGuests(){
        return statDao.getGuestHistory();
    }
}
