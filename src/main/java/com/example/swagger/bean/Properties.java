package com.example.swagger.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * author： Created by shiming on 2018/9/26 18:25
 * mailbox：lamshiming@sina.com
 */
@Component
public class Properties {
	
	@Value("${name}")
	private String name;
	
	@Value("${title}")
	private String title;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
