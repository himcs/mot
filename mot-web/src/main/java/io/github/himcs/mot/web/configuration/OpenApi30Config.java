package io.github.himcs.mot.web.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Mot API", version = "v1"))
@SecurityScheme(
        name = "auth",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER
)

public class OpenApi30Config {
}
