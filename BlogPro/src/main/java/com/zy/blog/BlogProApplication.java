package com.zy.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zy.blog.mapper")
public class BlogProApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogProApplication.class, args);
	}
}
