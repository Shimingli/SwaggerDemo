package com.example.swagger.domain;

/**
 * author： Created by shiming on 2018/9/26 16:00
 * mailbox：lamshiming@sina.com
 * 用户实体类
 */

public class User {



    private Long id;

    private String name;

    private String age;

    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
