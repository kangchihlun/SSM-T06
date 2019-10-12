package com.atguigu.jpademo.repository;

import com.atguigu.jpademo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 兩個范型繼承，第二個為序列化的主鍵類型
public interface UserRepository extends JpaRepository<User,Integer>{
    // 在 application.yml配置，會自動建表

}
