package com.systex.homework4.util;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoginFilter> loggingFilter(){
        FilterRegistrationBean<LoginFilter> registrationBean 
          = new FilterRegistrationBean<>();
          
        registrationBean.setFilter(new LoginFilter());
        registrationBean.addUrlPatterns("/lottery/*");
        registrationBean.setOrder(1);

        return registrationBean;    
    }
}
