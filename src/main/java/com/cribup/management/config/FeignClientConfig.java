package com.cribup.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
                HttpServletRequest request = servletRequestAttributes.getRequest();
                String authorizationHeader = request.getHeader("Authorization");
                if (authorizationHeader != null && !authorizationHeader.isBlank()) {
                    requestTemplate.header("Authorization", authorizationHeader);
                }
            }
        };
    }
}
