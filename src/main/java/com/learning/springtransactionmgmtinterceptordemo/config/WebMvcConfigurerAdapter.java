package com.learning.springtransactionmgmtinterceptordemo.config;


import com.learning.springtransactionmgmtinterceptordemo.interceptor.HttpTracingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private HttpTracingInterceptor httpTracingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpTracingInterceptor).addPathPatterns("/book");
    }
}
