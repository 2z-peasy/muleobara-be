package com.pj2z.pj2zbe.common.config;

import com.pj2z.pj2zbe.common.interceptor.AuthInterceptor;
import com.pj2z.pj2zbe.common.interceptor.AuthUserResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final AuthUserResolver authUserResolver;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true);

        // Swagger UI 관련 경로에 대해서도 CORS 허용
        // http://localhost:8080/swagger-ui/index.html#/
        registry.addMapping("/swagger-ui/**")
                .allowedOrigins("http://localhost:8080") // TODO": Swagger UI 서버주소에 맞게 추가
                .allowedMethods("GET", "POST")
                .allowedHeaders("*")
                .allowCredentials(true);

        registry.addMapping("/v3/api-docs/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/signup", "/callback/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authUserResolver);
    }
}
