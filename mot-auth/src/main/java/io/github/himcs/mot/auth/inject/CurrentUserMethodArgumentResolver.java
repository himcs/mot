package io.github.himcs.mot.auth.inject;

import io.github.himcs.mot.auth.annotation.CurrentUser;
import io.github.himcs.mot.auth.auth.AuthUtil;
import io.github.himcs.mot.generator.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class) && parameter.getParameterType().isAssignableFrom(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class)).getSession().getAttribute(AuthUtil.SESSION_LOGIN_USER);
    }
}
