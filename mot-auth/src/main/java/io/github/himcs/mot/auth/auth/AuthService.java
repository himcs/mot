package io.github.himcs.mot.auth.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.himcs.mot.generator.entity.User;
import io.github.himcs.mot.generator.mapper.UserMapper;
import org.springframework.util.StringUtils;

import java.util.Objects;


public class AuthService {

    private UserMapper userMapper;
    private boolean debug = false;

    public AuthService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public AuthService(UserMapper userMapper, boolean debug) {
        this.userMapper = userMapper;
        this.debug = debug;
    }

    public String login(String username, String password) {
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
        if (debug) {
            User lastUser = AuthUtil.getLastUser();
            if (Objects.isNull(lastUser)) {
                User loginUser = userMapper.selectById(1);
                login(loginUser.getLoginName(), loginUser.getPassword());
                return AuthUtil.getLastUser();
            }
            return lastUser;
        }
        if (StringUtils.isEmpty(uuid)) {
            return null;
        }
        return AuthUtil.getCurrentUser(uuid);
    }
}
