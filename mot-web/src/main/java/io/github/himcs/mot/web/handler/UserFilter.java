package io.github.himcs.mot.web.handler;

import io.github.himcs.mot.generator.entity.User;
import io.github.himcs.mot.web.auth.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
public class UserFilter extends HandlerInterceptorAdapter {
    private static final String HEADER_AUTH = "auth";

    private static final List<String> whiteList = new ArrayList<>(Collections.singletonList("/api/user/login"));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (whiteList.contains(requestURI)) {
            log.info("white: {}", requestURI);
            return true;
        }
        String header = request.getHeader(HEADER_AUTH);
        User currentUser = AuthUtil.getCurrentUser(header);
        if (Objects.isNull(currentUser)) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().write("need login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  {
        System.out.println("postHandle");
    }
}
