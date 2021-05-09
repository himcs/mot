package io.github.himcs.mot.web.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.himcs.mot.generator.entity.User;
import io.github.himcs.mot.generator.mapper.UserMapper;
import io.github.himcs.mot.web.dto.user.req.Login;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class AuthService {

    @Resource
    UserMapper userMapper;

    public String login(Login login) {
        String username = login.getUsername();
        String password = login.getPassword();
        User loginUser = userMapper.selectOne(
                new QueryWrapper<User>().lambda()
                        .eq(User::getLoginName, username)
                        .eq(User::getPassword, password));
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("login error");
        }
        loginUser.setPassword("");
        return AuthUtil.login(loginUser);
    }


    public User getCurrentUser() {
        return AuthUtil.getCurrentUser();
    }

    public User getCurrentUser(String uuid) {
        return AuthUtil.getCurrentUser(uuid);
    }
}
