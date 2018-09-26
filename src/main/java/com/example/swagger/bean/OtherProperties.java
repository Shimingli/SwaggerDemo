package com.example.swagger.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * author： Created by shiming on 2018/9/26 15:45
 * mailbox：lamshiming@sina.com
 */
@Configuration
@ConfigurationProperties(prefix="other")
@PropertySource("classpath:other.properties")
@Component
public class OtherProperties {

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String txt;
}
