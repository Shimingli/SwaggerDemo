package com.example.swagger.dao;

import com.example.swagger.domain.User;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author： Created by shiming on 2018/9/26 16:14
 * mailbox：lamshiming@sina.com
 */

public interface UserDao {

    User getUserById(@Param("id") Long id);

    List<User> getUserList();

    Long addUser(User user);

    Long deleteUser(Long id);

    Long updateUser(User user);
}
