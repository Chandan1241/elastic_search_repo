package com.chandan.boot.elasticsearch.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket restfulApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("elastic_search").select().paths(paths()).build()
				.apiInfo(apiInfo());
	}

	private Predicate<String> paths() {
		return or(regex("/user.*"), regex("/user.*"));
	}

	private ApiInfo apiInfo() {

		ApiInfo apiInfo = new ApiInfo("Spring boot Swagger with Gradle for Elastic search",
				"Spring Boot - Gralde build", "1.0", "http://localhost:8899/boot_elasticsearch/swagger-ui.html", "chandan2java@gmail.com", "Licence",
				"");
		return apiInfo;
	}

}
