package io.github.himcs.mot.web.configuration;

import io.github.himcs.mot.generator.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.himcs.mot.auth.auth.AuthService;
import io.github.himcs.mot.auth.handler.UserFilter;


@Configuration
public class AuthConfig {

    @Bean
    public AuthService authService(UserMapper userMapper) {
        return new AuthService(userMapper, true);
    }

    @Bean
    public UserFilter userFilter(AuthService authService) {
        return new UserFilter(authService);
    }
}
