package com.example.mycinema.config;

import com.example.mycinema.interceptor.LoginCheckInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private LoginCheckInterceptor loginCheckInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/api/**")
                .addPathPatterns("/orders/**")
                .addPathPatterns("/doLogout/**")
                .addPathPatterns("/user/**");
    }
}
