package io.github.himcs.mot.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.himcs.mot.common.Response;
import io.github.himcs.mot.generator.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import io.github.himcs.mot.auth.auth.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
public class UserFilter extends HandlerInterceptorAdapter {

    AuthService authService;
    private static final ObjectMapper mapper = new ObjectMapper();

    public UserFilter(AuthService authService) {
        this.authService = authService;
    }

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
        User currentUser = authService.getCurrentUser(header);
        if (Objects.isNull(currentUser)) {
            returnError(response, "user is not exist");
            return false;
        }
        return true;
    }

    public void returnError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(mapper.writeValueAsString(Response.ERROR(message)));
    }
}
