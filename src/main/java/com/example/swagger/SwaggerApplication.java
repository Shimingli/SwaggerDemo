package com.example.swagger;

import com.example.swagger.bean.OtherProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(OtherProperties.class)//要使用该配置Bean，同样也需要在入口类里使用注解@EnableConfigurationProperties({TestConfigBean.class})来启用该配置。
//@ImportResource({"classpath:some-application.xml"})//虽然Spring Boot并不推荐我们继续使用xml配置，但如果出现不得不使用xml配置的情况，Spring Boot允许我们在入口类里通过注解@ImportResource({"classpath:some-application.xml"})来引入xml配置文件。
public class SwaggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }
}
