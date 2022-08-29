package com.baul.estate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
public class CWebFluxConfigurer implements WebFluxConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOriginPatterns("*");
    }

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {

        configurer.addCustomResolver(new PostParamResolver());
        configurer.addCustomResolver(new GetParamResolver());

    }



}
