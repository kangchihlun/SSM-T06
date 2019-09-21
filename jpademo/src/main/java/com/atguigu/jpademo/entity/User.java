package com.atguigu.jpademo.entity;

import javax.persistence.*;

@Entity // 告訴Jpa這是一個實體類(和數據表映射的類)
@Table(name="tbl_user") //對應哪張表，不寫就是類名
public class User {

    @Id //主鍵
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主鍵
            Integer id;

    @Column(name="last_name",length = 50)//其中一個欄位
            String lastName;

    @Column // 可以全省略，直接就是變量名
            String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
