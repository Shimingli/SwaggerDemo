package com.example.swagger.service.impl;

import com.example.swagger.dao.UserDao;
import com.example.swagger.domain.User;
import com.example.swagger.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author： Created by shiming on 2018/9/26 16:13
 * mailbox：lamshiming@sina.com
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    public Map<String, Object> addUser(User user) {
        userDao.addUser(user);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @Override
    public Map<String, Object> deleteUser(Long id) {
        userDao.deleteUser(id);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @Override
    public Map<String, Object> updateUser(User user) {
        userDao.updateUser(user);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }
}
