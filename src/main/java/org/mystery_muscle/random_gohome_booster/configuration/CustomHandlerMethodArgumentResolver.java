package org.mystery_muscle.random_gohome_booster.configuration;

import lombok.extern.slf4j.Slf4j;
import org.mystery_muscle.random_gohome_booster.annotation.CurrentUser;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.exception.MMException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class CustomHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentUser.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        if (parameter.getParameterType().equals(Member.class)) {
            if (parameter.getParameterAnnotation(CurrentUser.class) != null) {
                if (request.getSession().getAttribute("currentUser") != null) {
                    return (Member)  request.getSession().getAttribute("currentUser");
                } else {
                    log.info("CurrentUser is null");
                    return null;
                }
            }
        }
        return WebArgumentResolver.UNRESOLVED;
    }
}
