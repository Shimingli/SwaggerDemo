package com.example.swagger.controller;

import com.example.swagger.bean.OtherProperties;
import com.example.swagger.bean.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author： Created by shiming on 2018/9/26 15:24
 * mailbox：lamshiming@sina.com
 */
@Controller
class HelloWordController {
    @Autowired
    private Properties properties;

    @Autowired
    private OtherProperties otherProperties;
    @RequestMapping("/hello")
    @ResponseBody
    public String helloWord(){
        return "hello Word";
    }


    @RequestMapping("/helloTwo")
    @ResponseBody
    public String helloWordTwo(){
        return "hello Word"+properties.getName()+"----"+properties.getTitle();
    }
    /*
    http://localhost:8080/helloThree
     */
    @RequestMapping("/helloThree")
    @ResponseBody
    public String helloThree(){
        return "hello Word"+otherProperties.getTxt();
    }

}