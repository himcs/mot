package io.github.himcs.mot.api;


import io.github.himcs.mot.common.Response;
import io.github.himcs.mot.dto.req.LoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mcs
 * @since 2021-05-07
 */
@RequestMapping("/api/user")
public interface UserApi {

    @PostMapping("/login")
    Response login(@RequestBody @Valid LoginDTO login);

    @GetMapping("/info")
    @Operation(summary = "info", security = @SecurityRequirement(name = "auth"))
    Response info();
}
