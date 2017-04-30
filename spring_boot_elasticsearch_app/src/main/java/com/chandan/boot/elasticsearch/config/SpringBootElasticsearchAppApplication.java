package com.chandan.boot.elasticsearch.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@ComponentScan("com.chandan.boot.elasticsearch")
@EnableElasticsearchRepositories(basePackages = "com.chandan.boot.elasticsearch.dao")
public class SpringBootElasticsearchAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootElasticsearchAppApplication.class, args);
	}
}
