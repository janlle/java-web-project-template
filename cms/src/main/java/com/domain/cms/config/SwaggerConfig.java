package com.domain.cms.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

/**
 * <p>
 *
 * @author leone
 * @since 1.8
 **/
@Configuration
public class SwaggerConfig {

    @Value("${swagger.show}")
    private Boolean enable;

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        Parameter parameter = new ParameterBuilder()
                .name("Authorization")
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue("token ")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .groupName("project-templet")
                .apiInfo(apiInfo())
                .globalOperationParameters(Collections.singletonList(parameter))
                .select()
                .apis(withClassAnnotation(Api.class))
                .paths(s -> true)
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("项目中文名cms")
                .description("接口")
                .version("1.0")
                .build();
    }

}
