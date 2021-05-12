package io.github.himcs.mot.auth.auth;

import io.github.himcs.mot.generator.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class AuthUtil {
    public static final String SESSION_LOGIN_USER = "SESSION_LOGIN_USER";
    public static final String SESSION_IS_LOGIN = "SESSION_IS_LOGIN";

    private static Map<String, User> login = new ConcurrentHashMap<>();

    private static String lastUid = "";

    public static String login(User user) {
        if (Objects.isNull(user)) {
            log.info("登录用户为null");
            clearSession();
            return null;
        }
        setSession(user);
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        login.put(s, user);
        lastUid = s;
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
            clearSession();
            return null;
        }
        setSession(user);
        return user;
    }

    public static User getLastUser() {
        return getCurrentUser(lastUid);
    }

    public static void logout() {
        clearSession();
    }

    public static void logout(String uuid) {
        login.remove(uuid);
        clearSession();
    }

    private static void setSession(User user) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_IS_LOGIN, true);
        session.setAttribute(SESSION_LOGIN_USER, user);
    }

    private static void clearSession() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute(SESSION_IS_LOGIN);
        session.removeAttribute(SESSION_LOGIN_USER);
    }

}
