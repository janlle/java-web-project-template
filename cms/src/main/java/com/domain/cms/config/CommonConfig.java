package com.domain.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

/**
 * <p>
 *
 * @author leone
 * @since 1.8
 **/
@Configuration
public class CommonConfig {

    @Bean
    public WebMvcConfigurer corsConfigure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }
        };
    }

    @Bean
    public Converter<String, Date> dateToTimestampConverter() {
        return source -> new Date(Long.parseLong(source, 10));
    }

}
