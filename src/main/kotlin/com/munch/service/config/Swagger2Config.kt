package com.munch.service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class Swagger2Config {

    @Bean
    fun createRestApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(ApiInfoBuilder()
                        .title("SERVICE APIs")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.munch.service.controller"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(java.sql.Timestamp::class.java,java.sql.Date::class.java)

    }
}