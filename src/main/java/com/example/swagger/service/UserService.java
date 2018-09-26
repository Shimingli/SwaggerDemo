package com.example.swagger.service;

import com.example.swagger.domain.User;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * author： Created by shiming on 2018/9/26 16:08
 * mailbox：lamshiming@sina.com
 */

public interface UserService {
    /**
     * 通过id 获取 user
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 获取全部的user
      * @return
     */
    List<User> getUserList();

    /**
     * 添加user
     * @param user
     * @return
     */
    Map<String, Object> addUser( User user);

    /**
     * 删除user
     * @param id
     * @return 返回一个map
     */
    Map<String, Object> deleteUser( Long id);

    /**
     * 更新user
     * @param user
     * @return
     */
    Map<String, Object> updateUser(User user);
}
