package io.github.himcs.mot.web.controller;


import io.github.himcs.mot.api.UserApi;
import io.github.himcs.mot.auth.annotation.CurrentUser;
import io.github.himcs.mot.auth.auth.AuthService;
import io.github.himcs.mot.common.Response;
import io.github.himcs.mot.dto.req.LoginDTO;
import io.github.himcs.mot.generator.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mcs
 * @since 2021-05-07
 */
@RestController
public class UserController implements UserApi {

    @Resource
    AuthService authService;

    public Response login(@RequestBody @Valid LoginDTO login) {
        String uuid = authService.login(login.getUsername(), login.getPassword());
        return Response.OK(uuid);
    }

    public Response info() {
        return Response.OK(authService.getCurrentUser());
    }

    public Response inject(@CurrentUser User user) {
        return Response.OK(user.toString());
    }
}
