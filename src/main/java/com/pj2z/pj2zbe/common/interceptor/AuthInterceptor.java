package com.pj2z.pj2zbe.common.interceptor;

import com.pj2z.pj2zbe.common.custom.AuthCheck;
import com.pj2z.pj2zbe.user.entity.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j(topic = "AuthInterceptor")
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;
        AuthCheck authCheck = method.getMethodAnnotation(AuthCheck.class);

        if (authCheck != null && authCheck.role() != Role.ROLE_ADMIN) {
            log.warn("Forbidden request: {}", request.getRequestURI());
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "권한이 없습니다.");
            return false;
        }

        return true;
    }
}
