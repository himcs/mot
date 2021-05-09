package io.github.himcs.mot.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import web.handler.UserFilter;

import javax.annotation.Resource;

@Configuration
public class MotWebMvcConfig implements WebMvcConfigurer {
    @Resource
    UserFilter userFilter;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userFilter).addPathPatterns("/api/**");
    }
}
