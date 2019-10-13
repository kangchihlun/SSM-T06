package com.live.livedemo.dao;

import com.live.livedemo.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 2017/6/12.
 */

public interface UserDao extends CrudRepository<UserEntity,String>{
}
