package com.jwd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class RequestConfig implements WebMvcConfigurer {
    //@Autowired
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor(jwtTokenProvider))
            .excludePathPatterns("/project_list", "/login", "/v2/api-docs", "/name");
            //.excludePathPatterns("/*");
    }
}
