package com.nowcoder.bookmanager.configuration;

import com.nowcoder.bookmanager.interceptor.HostInfoInterceptor;
import com.nowcoder.bookmanager.interceptor.LoginInterceptor;
import com.nowcoder.bookmanager.service.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.rmi.registry.Registry;

@Component
public class BookWebConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private HostInfoInterceptor hostInfoInterceptor;

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {

            public void addInterceptors(InterceptorRegistry registry){
                registry.addInterceptor(hostInfoInterceptor).addPathPatterns("/**");
                registry.addInterceptor(loginInterceptor).addPathPatterns("/books/**");

            }
        };
    }

}
