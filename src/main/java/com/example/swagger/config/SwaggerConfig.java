package com.example.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * author： Created by shiming on 2018/9/26 18:10
 * mailbox：lamshiming@sina.com
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // 在配置类中添加@EnableSwagger2注解来启用Swagger2，apis()定义了扫描的包路径
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("RESTful API 文档")
                .contact(new Contact("shiming", "https://www.shiming.site/", null))
                .version("1.0")
                .build();
    }
}
