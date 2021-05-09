package io.github.himcs.mot.web.auth;

import io.github.himcs.mot.generator.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AuthUtil {
    private static final String SESSION_LOGIN_USER = "SESSION_LOGIN_USER";
    private static final String SESSION_IS_LOGIN = "SESSION_IS_LOGIN";

    private static Map<String, User> login = new ConcurrentHashMap<>();

    public static String login(User user) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_IS_LOGIN, true);
        session.setAttribute(SESSION_LOGIN_USER, user);
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        login.put(s, user);
        return s;
    }

    public static User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        return (User) session.getAttribute(SESSION_LOGIN_USER);
    }

    public static User getCurrentUser(String uuid) {
        User user = login.get(uuid);
        if (Objects.isNull(user)) {
            throw new RuntimeException("user not login");
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_IS_LOGIN, true);
        session.setAttribute(SESSION_LOGIN_USER, user);
        return user;
    }


    public static void logout() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute(SESSION_IS_LOGIN);
        session.removeAttribute(SESSION_LOGIN_USER);
    }
}
