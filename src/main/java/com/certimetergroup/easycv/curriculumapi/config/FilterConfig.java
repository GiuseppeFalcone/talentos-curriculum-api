package com.certimetergroup.easycv.curriculumapi.config;

import com.certimetergroup.easycv.curriculumapi.middleware.JwtClaimExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final JwtClaimExtractor jwtClaimExtractor;

    @Bean
    public FilterRegistrationBean<JwtClaimExtractor> registerJwtFilter() {
        FilterRegistrationBean<JwtClaimExtractor> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtClaimExtractor);
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}