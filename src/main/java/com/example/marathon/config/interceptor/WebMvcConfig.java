
package com.example.marathon.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public WebMvcConfig() {
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true).allowedOrigins(new String[]{"*"}).allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"}).allowedHeaders(new String[]{"*"}).exposedHeaders(new String[]{"*"});
    }
}
