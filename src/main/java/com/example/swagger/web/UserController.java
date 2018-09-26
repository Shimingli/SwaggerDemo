package com.example.swagger.web;

import com.example.swagger.domain.User;
import com.example.swagger.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author： Created by shiming on 2018/9/26 16:42
 * mailbox：lamshiming@sina.com
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService cityService;

    //{"id":1,"name":"仕明","age":"25","description":"仕明是个好同学"}
    // http://localhost:8080/user/1
    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable(value = "id") Long id) {
        System.out.println("id="+id);
        User userById = cityService.getUserById(id);
        return userById;
    }
}
