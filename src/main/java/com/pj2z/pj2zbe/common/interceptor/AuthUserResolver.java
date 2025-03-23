package com.pj2z.pj2zbe.common.interceptor;

import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.common.exception.CustomAuthenticationException;
import com.pj2z.pj2zbe.common.jwt.JwtUtil;
import com.pj2z.pj2zbe.user.entity.User;
import com.pj2z.pj2zbe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthUserResolver implements HandlerMethodArgumentResolver {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(UserCheck.class);
        boolean isMemberType = User.class.isAssignableFrom(parameter.getParameterType());

        return hasAnnotation && isMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String header = webRequest.getHeader("Authorization");
        String token = jwtUtil.resolveToken(header);

        return userRepository.findById(jwtUtil.getUserIdFromToken(token)).orElseThrow(() -> {
                    log.error("JWT 인증 실패: 인증되지 않은 사용자입니다.");
                    return new CustomAuthenticationException("인증되지 않은 사용자입니다.");
                }
        );
    }
}
